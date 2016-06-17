package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
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

	private boolean stop = false;

	public static void main(String[] args) throws IOException {
		new ClientMain(args[0], Integer.parseInt(args[1])).run();
	}

	public void run() throws IOException {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ClientIniter());

		try {
			Channel channel = bootstrap.connect(host, port).sync().channel();
			while (!stop) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));
				String input = reader.readLine();
				if (input != null) {
					if ("quit".equals(input)) {
						System.exit(0);
					}
					channel.writeAndFlush(input);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
