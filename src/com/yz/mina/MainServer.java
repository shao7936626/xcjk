package com.yz.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MainServer {

	private static final int PORT = 10024;// would pull it from configure file in
											// other day

	public static void main(String[] args) throws IOException {
	

		IoAcceptor acceptor = new NioSocketAcceptor();

		// Attach the business logic to the server
		acceptor.setHandler(new MinaServerHandler());
		acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new MyBytesCodecFactory()));
		acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
		
		// Configurate the buffer size and the iddle time
		acceptor.getSessionConfig().setReadBufferSize(128);


//		DataCacheFactory.Init();
		// And bind !
		acceptor.bind(new InetSocketAddress(PORT));
		

		
	}
}
