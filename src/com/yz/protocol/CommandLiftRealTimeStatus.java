package com.yz.protocol;

import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.mina.CmdFactoryBase.MONITOR_CMD_TYPE;

public class CommandLiftRealTimeStatus extends CommandBase{

	public CommandLiftRealTimeStatus(ICmdParser parser, byte[] data) {
		super(parser, data);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.LIFT_REALTIME_STATUS;
	}

	public CommandLiftRealTimeStatus(ICmdParser parser) {
		super(parser);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.LIFT_REALTIME_STATUS;
	}

}
