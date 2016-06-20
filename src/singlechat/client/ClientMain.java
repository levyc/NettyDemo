package singlechat.client;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientMain {
	private String host;
	private int port;
	private boolean stop = false;

	public ClientMain(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) throws IOException {
		// new ClientMain(args[0], Integer.parseInt(args[1])).run();
		new ClientMain("127.0.0.1", 8888).run();
	}

	public void run() throws IOException {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ClientIniter());

		try {
			Channel channel = bootstrap.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
