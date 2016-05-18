package com.yz.protocol;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.yz.mina.CmdFactoryBase;
import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.utils.CRC16;
import com.yz.utils.DataConvertor;

public class CraneAttendanceCmdFactory extends CmdFactoryBase implements
		ICmdParser {

	public CraneAttendanceCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.CRANE_ATTENDANCE;
	}

	@Override
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub

		send_ack(this.m_oData, session);
		return super.OnAfter_Ack(session, cmd);
	}

	private void send_ack(byte[] data, IoSession session) {
		// TODO Auto-generated method stub
		byte[] send = new byte[18];
		send[0] = (byte) 0xA1;
		send[1] = (byte) 0x12;
		for (int i = 2; i < 9; i++)
			send[i] = data[i];
		Date date = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yy MM dd HH mm ss");

		String time = df.format(date);

		String[] cmds = time.split(" ");
		byte[] aaa = new byte[cmds.length];
		int i = 0;
		for (String b : cmds) {
			if (b.equals("FF")) {
				aaa[i++] = -1;
			} else {
				aaa[i++] = Integer.valueOf(b, 16).byteValue();
			}
		}

		for (int j = 0; j < cmds.length; j++)
			send[9 + j] = aaa[j];

		send[15] = 0x31; // 0x31:塔司 0x32:检查人员 0x33:安装人员 0x34:维保人员

		int crc = CRC16.calcCrc16(send, 0, 16);
		send[16] = (byte) ((crc & 0xff00) >> 8);
		send[17] = (byte) (crc & 0x00ff);
		

		System.out.println("CRANE Timing "
				+ DataConvertor.bytesToHexString(send));

		session.write(IoBuffer.wrap(send));
	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub

		if (cmd.getCmdType() == this.expected_cmd) {

			OnAfter_Ack(session, cmd);
		}
	}
}
