package en2decoder.serialize.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientMain {

	private String host;
	private int port;

	public ClientMain(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) {
		new ClientMain(args[0], Integer.parseInt(args[1])).run();
	}

	public void run() {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ClientIniter());
		try {
			bootstrap.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("connect fail");
			System.exit(1);
		}
	}
}
