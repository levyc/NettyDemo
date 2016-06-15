package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerMain {
	public static void main(String[] args) {
		EventLoopGroup acceptor = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.option(ChannelOption.SO_BACKLOG,1024);
		bootstrap.group(acceptor,worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ServerIniterHandler());
		try {
			Channel channel = bootstrap.bind(8888).sync().channel();
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			acceptor.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
}
