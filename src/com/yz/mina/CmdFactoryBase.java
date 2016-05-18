package com.yz.mina;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yz.protocol.CommandCraneAttendance;
import com.yz.protocol.CommandCraneRealTimeStatus;
import com.yz.protocol.CommandCraneTiming;
import com.yz.protocol.CommandDtuHeartBeat;
import com.yz.protocol.CommandDustData;
import com.yz.protocol.CommandLiftAttendance;
import com.yz.protocol.CommandLiftRealTimeStatus;
import com.yz.protocol.CommandLiftTiming;
import com.yz.protocol.CommandNoiseData;
import com.yz.protocol.CraneAttendanceCmdFactory;
import com.yz.protocol.CraneRealTimeStatusCmdFactory;
import com.yz.protocol.CraneTimingCmdFactory;
import com.yz.protocol.DtuHeartBeatCmdFactory;
import com.yz.protocol.DustDataCmdFactory;
import com.yz.protocol.LiftAttendanceCmdFactory;
import com.yz.protocol.LiftRealTimeStatusCmdFactory;
import com.yz.protocol.LiftTimingCmdFactory;
import com.yz.protocol.NoiseDataCmdFactory;
import com.yz.utils.DataConvertor;

public class CmdFactoryBase implements ICmdParser {
	private static final Logger log = LoggerFactory
			.getLogger(CmdFactoryBase.class);
	public static final String SESSION_PARAM_CMD_FACTORY = "cmd_factory";

	public enum MONITOR_CMD_TYPE {
		UNKNOWN_CMD(-1), DTU_HEARTBEAT(0), LIFT_TIMING(1), LIFT_REALTIME_STATUS(
				2), LIFT_ATTENDANCE(3), // 考勤数据上传
		DUST_DATA(4), NOISE_DATA(5), CRANE_TIMING(6), 
		CRANE_ATTENDANCE(7),
		CRANE_REALTIME_STATUS(8);

		private final int _val;

		private MONITOR_CMD_TYPE(int val) {
			_val = val;
		}

		public int getValue() {
			return this._val;
		}

		public static MONITOR_CMD_TYPE valueOf(int val) {
			switch (val) {
			case 0:
				return DTU_HEARTBEAT;
			case 1:
				return LIFT_TIMING;
			case 2:
				return LIFT_REALTIME_STATUS;
			case 3:
				return LIFT_ATTENDANCE;
			case 4:
				return DUST_DATA;
			case 5:
				return NOISE_DATA;
			case 6:
				return CRANE_TIMING;
			case 7:
				return CRANE_ATTENDANCE;
			case 8:
				return CRANE_REALTIME_STATUS;
			default:
				return UNKNOWN_CMD;

			}
		}
	}

	public static MONITOR_CMD_TYPE getCommandType(byte[] data) {

		// int command = data[7] & 0xFF;
		if (data[0] == 0x41) {
			byte tmpbytes[] = new byte[] { 0x41, 0x41, 0x41, 0x41 };

			int tmp_sum = 0;

			for (int i = 0; i < tmpbytes.length; i++) {
				if (tmpbytes[i] == data[i]) {
					tmp_sum++;
				}
			}

			if (tmp_sum == 4) {
				return MONITOR_CMD_TYPE.valueOf(0);
			}
		}

		if (data[0] == 0x42) {
			byte tmpbytes[] = new byte[] { 0x42, 0x42, 0x42, 0x42 };

			int tmp_sum = 0;

			for (int i = 0; i < tmpbytes.length; i++) {
				if (tmpbytes[i] == data[i]) {
					tmp_sum++;
				}
			}

			if (tmp_sum == 4) {
				return MONITOR_CMD_TYPE.valueOf(5);
			}
		}

		if (data[0] == (byte) 0xA0 && data[1] == (byte) 0x0A) {
			if (data[9] == 0x10) {
				return MONITOR_CMD_TYPE.valueOf(2);
			}
			if (data[9] == 0x12) {
				return MONITOR_CMD_TYPE.valueOf(1);
			}
			if (data[9] == 0x25) {
				return MONITOR_CMD_TYPE.valueOf(3);
			}
		}

		if (data[1] == 0x04 && data[2] == 0x04) {
			return MONITOR_CMD_TYPE.valueOf(4);
		}

		if (data[0] == (byte) 0xA0) {
			if(data[1] == 0x18 && data[7] == 0x00 && data[8] == 0x01)
				return MONITOR_CMD_TYPE.valueOf(6);
			if(data[1] == 0x24 && data[7] == 0x00 && data[8] == 0x12)
				return MONITOR_CMD_TYPE.valueOf(7);
			if(data[1] == 0x46 && data[7] == 0x00 && data[8] == 0x07)
				return MONITOR_CMD_TYPE.valueOf(8);
		}

		byte xmlbytes[] = new byte[5];

		for (int i = 0; i < xmlbytes.length; i++) {
			xmlbytes[i] = data[i];
		}
		if (DataConvertor.bytesToHexString(xmlbytes).equals("<?xml")) {
			if (DataConvertor.bytesToHexString(data).contains("SIGNALSART")) {
				return MONITOR_CMD_TYPE.valueOf(7);
			} else if (DataConvertor.bytesToHexString(data).contains(
					"SIGNALEND")) {
				return MONITOR_CMD_TYPE.valueOf(8);
			} else {
				return MONITOR_CMD_TYPE.valueOf(-1);

			}
		} else {

			int sum = 0;
			int flag_ff = 0;
			for (int i = 0; i < 4; i++) {
				if (data[i] == -1)
					sum++;
			}

			for (int i = 0; i < data.length; i++) {
				if (data[i] == -1) {

				}
			}

			if (sum == 4) {
				int command = data[6];
				return MONITOR_CMD_TYPE.valueOf(command);
			} else {
				return MONITOR_CMD_TYPE.valueOf(-1);
			}
		}
	}

	public static CmdFactoryBase SelectCmdFactory(IoSession session,
			Object message) {
		CmdFactoryBase factory = null;
		byte[] data = DataConvertor.toByteArray(message);
		MONITOR_CMD_TYPE eCmdType = getCommandType(data);
		if (MONITOR_CMD_TYPE.UNKNOWN_CMD == eCmdType) {

			// log.debug("Not expected first cmd type");
			return null;
		}
		switch (eCmdType) {
		case DTU_HEARTBEAT:
			factory = new DtuHeartBeatCmdFactory(data);
			break;
		case LIFT_TIMING:
			factory = new LiftTimingCmdFactory(data);
			break;

		case LIFT_REALTIME_STATUS:
			factory = new LiftRealTimeStatusCmdFactory(data);
			break;
		case LIFT_ATTENDANCE:
			// log.debug("switch factory");
			factory = new LiftAttendanceCmdFactory(data);
			break;

		case DUST_DATA:
			// log.debug("heart beat factory");
			factory = new DustDataCmdFactory(data);
			break;

		case NOISE_DATA:
			// log.debug("msg push ack factory");
			factory = new NoiseDataCmdFactory(data);
			break;

		case CRANE_TIMING:
			// log.debug("img upload factory");
			factory = new CraneTimingCmdFactory(data);
			break;

		case CRANE_ATTENDANCE:
			// log.debug("img upload factory");
			 factory = new CraneAttendanceCmdFactory(data);
			break;
		case CRANE_REALTIME_STATUS:
			// log.debug("img upload factory");
			 factory = new CraneRealTimeStatusCmdFactory(data);
			break;
		}

		return factory;
	}

	public CmdFactoryBase(byte[] data) {
		m_oData = data;
	}

	protected byte[] m_oData = null;
	protected MONITOR_CMD_TYPE expected_cmd;

	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// cmd.Parse(session, m_oData);
	}

	public CommandBase CreateCommand(IoSession session, Object message) {

		m_oData = DataConvertor.toByteArray(message);
		CommandBase cmd = null;

		MONITOR_CMD_TYPE eCmdType = getCommandType(m_oData);

		switch (eCmdType) {
		case DTU_HEARTBEAT:
			log.debug("CMD_LOGIN");
			cmd = new CommandDtuHeartBeat(this, m_oData);
			break;
		case LIFT_TIMING:
			log.debug("CMD_BYE");
			cmd = new CommandLiftTiming(this, m_oData);
			break;
		case LIFT_REALTIME_STATUS:
			cmd = new CommandLiftRealTimeStatus(this, m_oData);
			break;
		case LIFT_ATTENDANCE:
			cmd = new CommandLiftAttendance(this, m_oData);
			break;
		case DUST_DATA:
			cmd = new CommandDustData(this, m_oData);
			break;
		case NOISE_DATA:
			cmd = new CommandNoiseData(this, m_oData);
			break;
		case CRANE_TIMING:
			cmd = new CommandCraneTiming(this, m_oData);
			break;
		case CRANE_ATTENDANCE:
			 cmd = new CommandCraneAttendance(this,m_oData);
			 break;
		case CRANE_REALTIME_STATUS:
			 cmd = new CommandCraneRealTimeStatus(this,m_oData);
			break;
		}
		return cmd;
	}

	public int GetByeAckFlag(CommandBase cmd) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public void UpdatePushTask() {
		// TODO Auto-generated method stub

	}

	public byte[] getSerialNum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSerialNum(byte[] serial) {
		// TODO Auto-generated method stub

	}

	public void TaskDispose() {
		// TODO Auto-generated method stub

	}

}
