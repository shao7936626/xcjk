package com.yz.protocol;

import org.apache.mina.core.session.IoSession;

import com.yz.mina.CmdFactoryBase;
import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;

public class LiftRealTimeStatusCmdFactory extends CmdFactoryBase implements ICmdParser{

	private String driverID;
	private String operatingStatus;
	
	public LiftRealTimeStatusCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.LIFT_REALTIME_STATUS;
	}

	@Override
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub
		upload_RealTimeStatus(this.m_oData,session);
		return super.OnAfter_Ack(session, cmd);
	}

	private void upload_RealTimeStatus(byte[] data, IoSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub
		
		if(cmd.getCmdType() == this.expected_cmd)
		{
			
//			String Reply_cmd = "FF FF FF FF 01 F0 9F 00 00 08 01 98";
//			String[] cmds = Reply_cmd.split(" ");
//	        byte[] aaa = new byte[cmds.length];
//	        int i = 0;
//	        for (String b : cmds) {
//	            if (b.equals("FF")) {
//	                aaa[i++] = -1;
//	            } else {
//	                aaa[i++] = Integer.valueOf(b, 16).byteValue();;
//	            }
//	        }
//	        session.write(IoBuffer.wrap(aaa));
			
			
			
			OnAfter_Ack(session, cmd);
		}
	}
	
}
