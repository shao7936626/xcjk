package com.yz.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.yz.mina.CmdFactoryBase;
import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.utils.CRC16;
import com.yz.utils.DataConvertor;

public class LiftAttendanceCmdFactory extends CmdFactoryBase implements
		ICmdParser {

	public LiftAttendanceCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.LIFT_ATTENDANCE;
	}

	@Override
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub

		send_ack(this.m_oData, session);

		upload_LiftAttendance(this.m_oData, session);
		return super.OnAfter_Ack(session, cmd);
	}

	private void send_ack(byte[] data, IoSession session) {
		// TODO Auto-generated method stub
		byte[] send_data = new byte[13];
		for (int i = 0; i < 12; i++) {
			send_data[i] = data[i];
		}

		send_data[12] = 0x01;

		int crc = CRC16.calcCrc16(send_data);

		byte[] final_send_data = new byte[15];

		for (int j = 0; j < 13; j++)
			final_send_data[j] = send_data[j];

		final_send_data[13] = (byte) ((crc & 0xff00) >> 8);
		final_send_data[14] = (byte) (crc & 0x00ff);

		session.write(IoBuffer.wrap(final_send_data));
	}

	private void upload_LiftAttendance(byte[] data, IoSession session) {
		// TODO Auto-generated method stub
		System.out.println("考勤数据有了");

		byte[] b_driveId = new byte[18];

		for (int i = 0; i < 18; i++) {
			b_driveId[i] = data[i + 12];
		}

		String s_driveId = DataConvertor.toString(b_driveId);

		System.out.println("the s_driveId is " + s_driveId);

	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub

		if (cmd.getCmdType() == this.expected_cmd) {

			OnAfter_Ack(session, cmd);
		}
	}
}
