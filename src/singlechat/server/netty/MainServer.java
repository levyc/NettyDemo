package singlechat.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MainServer {
	private int port;

	public MainServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new MainServer(8888).run();
	}

	public void run() {
		EventLoopGroup acceptor = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
		bootstrap.group(acceptor, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ServerIniter());
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
