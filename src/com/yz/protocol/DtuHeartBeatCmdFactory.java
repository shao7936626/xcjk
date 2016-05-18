package com.yz.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.yz.mina.CmdFactoryBase;
import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.utils.CRC16;
import com.yz.utils.DataConvertor;

public class DtuHeartBeatCmdFactory extends CmdFactoryBase implements
		ICmdParser {

	public DtuHeartBeatCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.DTU_HEARTBEAT;
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
		if (data[0] == 0x41) {
			// 扬尘

			byte[] number = new byte[3];
			for (int i = 0; i < 3; i++)
				number[i] = data[i + 5];
			String temp = DataConvertor.toString(number);

			byte[] send = new byte[8];

			int s0 = Integer.valueOf(temp);

			send[0] = (byte) s0;
			send[1] = 0x04;
			send[5] = 0x02;
			
			int crc = CRC16.calcCrc16(send,0,6);

			send[6] = (byte) ((crc & 0xff00) >> 8);
			send[7] = (byte) (crc & 0x00ff);

			session.write(IoBuffer.wrap(send));

		}

		if (data[0] == 0x42) {
			// 噪音

		}
	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub

		if (cmd.getCmdType() == this.expected_cmd) {

			String Reply_cmd = "FF FF FF FF 01 F0 9F 00 00 08 01 98";
			String[] cmds = Reply_cmd.split(" ");
			byte[] aaa = new byte[cmds.length];
			int i = 0;
			for (String b : cmds) {
				if (b.equals("FF")) {
					aaa[i++] = -1;
				} else {
					aaa[i++] = Integer.valueOf(b, 16).byteValue();
					;
				}
			}
			session.write(IoBuffer.wrap(aaa));

			OnAfter_Ack(session, cmd);
		}
	}

}
