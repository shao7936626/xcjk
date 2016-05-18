package com.yz.protocol;

import com.yz.mina.CommandBase;
import com.yz.mina.ICmdParser;
import com.yz.mina.CmdFactoryBase.MONITOR_CMD_TYPE;

public class CommandNoiseData extends CommandBase{

	public CommandNoiseData(ICmdParser parser, byte[] data) {
		super(parser, data);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.NOISE_DATA;
	}

	public CommandNoiseData(ICmdParser parser) {
		super(parser);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.NOISE_DATA;
	}

}
