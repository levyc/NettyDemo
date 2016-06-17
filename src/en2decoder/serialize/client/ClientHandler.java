package en2decoder.serialize.client;

import en2decoder.serialize.server.SubscribeReq;
import en2decoder.serialize.server.SubscribeRes;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object arg1)
			throws Exception {
		SubscribeRes res = (SubscribeRes) arg1;
		System.out.println("client receive" + res);

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			SubscribeReq req = new SubscribeReq();
			req.setId(i);
			req.setName("levy");
			req.setProductName("milk");
			req.setAddress("china");
			ctx.write(req);
		}
		ctx.flush();
	}

}
