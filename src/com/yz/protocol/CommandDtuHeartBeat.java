package com.yz.protocol;


import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.mina.CmdFactoryBase.MONITOR_CMD_TYPE;


public class CommandDtuHeartBeat extends CommandBase{

	public CommandDtuHeartBeat(ICmdParser parser) {
		super(parser);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.DTU_HEARTBEAT;
	}

	public CommandDtuHeartBeat(ICmdParser parser, byte[] data) {
		super(parser, data);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.DTU_HEARTBEAT;
	}
	
	
}
