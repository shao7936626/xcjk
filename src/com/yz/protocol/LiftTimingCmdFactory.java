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

public class LiftTimingCmdFactory extends CmdFactoryBase implements ICmdParser{

	private byte[] data;
	
	public LiftTimingCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.data = data;
		this.expected_cmd = MONITOR_CMD_TYPE.LIFT_TIMING;
	}

	@Override
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub
		return super.OnAfter_Ack(session, cmd);
	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub
		
		if(cmd.getCmdType() == this.expected_cmd)
		{
			
			byte[] send = new byte[16];
			
			for(int i = 0;i<10;i++)
				send[i] = data[i];
			
			send[2] = 0x06;
			
			
			
			Date date=new Date();
			
			SimpleDateFormat df = new SimpleDateFormat("yy MM dd HH mm ss");
			
			String time=df.format(date);
			
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
			
	        for(int j =0 ;j<cmds.length ;j++)
	        	send[10+j] = aaa[j];
			
//			send[10] = 0x16;
//			send[11] = 0x05;
//			send[12] = 0x12;
//			send[13] = 0x12;
//			send[14] = 0x58;
//			send[15] = 0x16;
			
			int crc = CRC16.calcCrc16(send);
			
			byte[] n_send = new byte[18]; 
			

			for(int k = 0;k<16;k++)
				n_send[k] = send[k];
			
			n_send[16] = (byte) ((crc & 0xff00) >> 8);
			n_send[17] = (byte)(crc & 0x00ff);
			
			System.out.println("LIFT Timing "+ DataConvertor.bytesToHexString(n_send));
			
//			String Reply_cmd = "FF FF FF FF 01 F0 9F 00 00 08 01 98";
//			String[] cmds = Reply_cmd.split(" ");
//	        byte[] aaa = new byte[cmds.length];
//	        int i = 0;
//	        for (String b : cmds) {
//	            if (b.equals("FF")) {
//	                aaa[i++] = -1;
//	            } else {
//	                aaa[i++] = Integer.valueOf(b, 16).byteValue();
//	            }
//	        }
	        session.write(IoBuffer.wrap(n_send));
			
			OnAfter_Ack(session, cmd);
		}
	}
}
