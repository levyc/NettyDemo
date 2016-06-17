package chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerMain {

	private int port;

	public ServerMain(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new ServerMain(Integer.parseInt(args[0])).run();
	}

	public void run() {
		EventLoopGroup acceptor = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		bootstrap.group(acceptor, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ServerIniterHandler());
		try {
			Channel channel = bootstrap.bind(port).sync().channel();
			System.out.println("server strart running in port:" + port);
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			acceptor.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
