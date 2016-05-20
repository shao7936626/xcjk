package com.yz.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.yz.utils.DataConvertor;

public class MinaServerHandler implements IoHandler {

	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.closeOnFlush();
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("enter messageReceived "
				+ DataConvertor.bytesToHexString(DataConvertor
						.toByteArray(message)));
		CmdFactoryBase cmdFactory = CmdFactoryBase.SelectCmdFactory(session,
				message);
		if (cmdFactory != null) {
			CommandBase cmd = cmdFactory.CreateCommand(session, message);
			if (null != cmd) {
				cmdFactory.Process(session, cmd);
			}
		}

	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("opened " + session.getRemoteAddress().toString());
		// session.write(arg0);
	}

}
