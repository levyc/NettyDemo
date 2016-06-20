package singlechat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import singlechat.server.user.dao.User;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object arg1)
			throws Exception {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		User user = new User();
		user.setDescription("levyccccccccccc");
		user.setId(007);
		user.setName("levy");
		user.setPassword("327920");
		ctx.write(user);
		ctx.flush();
		System.out.println("有发出去的吗");
	}

}
