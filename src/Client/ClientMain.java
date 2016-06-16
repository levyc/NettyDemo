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
	public static void main(String[] args) throws IOException {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new ClientIniter());

		try {
			Channel channel = bootstrap.connect("127.0.0.1", 8888).sync()
					.channel();
			while (true) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(System.in));
				String input = reader.readLine();
				if (input != null) {
					channel.writeAndFlush(input);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}
