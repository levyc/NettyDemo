package singlechat.server.netty;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import singlechat.server.message.dao.MessageModel;
import singlechat.server.user.dao.UserModel;

public class MainServer {
	private int port;

	public MainServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new MainServer(8888).run();
	}

	public void run() {
		initDB();
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

	private void initDB() {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(
				"jdbc:mysql://127.0.0.1/singlechat?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull",
				"root", "");
		c3p0Plugin.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.addMapping("message", MessageModel.class);
		arp.addMapping("user", "id", UserModel.class);
		arp.start();
	}

}
