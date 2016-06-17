package en2decoder.serialize.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class Serveriniter extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		pipeline.addLast(new ObjectDecoder(1024 * 1024,
				ClassResolvers.weakCachingConcurrentResolver(
						this.getClass().getClassLoader())));
		pipeline.addLast(new ObjectEncoder());
		pipeline.addLast(new SerializeHandler());
	}

}
