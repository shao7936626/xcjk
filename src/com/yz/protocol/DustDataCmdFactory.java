package com.yz.protocol;

import org.apache.mina.core.session.IoSession;

import com.yz.mina.CmdFactoryBase;
import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;

public class DustDataCmdFactory extends CmdFactoryBase implements ICmdParser {

	public DustDataCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.DUST_DATA;
	}

	@Override
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub

		upload_dustdata(this.m_oData, session);
		return super.OnAfter_Ack(session, cmd);
	}

	private void upload_dustdata(byte[] data, IoSession session) {
		// TODO Auto-generated method stub
		
		
		String data1 ;//粉尘浓度值
		//String data2; //空气洁净度等级
		
		int dust = ((data[3]&0xff)<<8) +(data[4]&0xff);
		
		//float airLevel = (float) ((data[5]&0xff)/(10.0));
		
		//System.out.println("the dust is"+dust +"and the airlevle is "+airLevel);
	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub

		if (cmd.getCmdType() == this.expected_cmd) {

			OnAfter_Ack(session, cmd);
		}
	}
}
