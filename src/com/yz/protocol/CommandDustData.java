package com.yz.protocol;

import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.mina.CmdFactoryBase.MONITOR_CMD_TYPE;

public class CommandDustData extends CommandBase{

	public CommandDustData(ICmdParser parser, byte[] data) {
		super(parser, data);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.DUST_DATA;
	}

	public CommandDustData(ICmdParser parser) {
		super(parser);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.DUST_DATA;
	}

}
