package en2decoder.serialize.server;

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerializeHandler extends SimpleChannelInboundHandler<Object> {

	private AtomicInteger count = new AtomicInteger(1);
	public static final int SUCCESSFUL = 1;
	public static final int FAIL = 0;

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object arg1)
			throws Exception {
		SubscribeReq req = (SubscribeReq) arg1;
		System.out.println("server receive:" + req);
		SubscribeRes res = new SubscribeRes();
		res.setUuid(count.get());
		res.setAddress(req.getAddress());
		res.setName(req.getName());
		res.setProduct(req.getProductName());
		if ("levy".equals(req.getName()))
			res.setStatus(SUCCESSFUL);
		else
			res.setStatus(FAIL);
		count.incrementAndGet();
		arg0.writeAndFlush(res);
	}

}
