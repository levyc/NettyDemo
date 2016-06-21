package singlechat.client;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import singlechat.server.message.dao.Message;
import singlechat.server.user.dao.User;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object object)
			throws Exception {
		if (object instanceof String)
			System.out.println((String) object);
		System.out.println();
		if (object instanceof Message) {// handle receive message
			Message message = (Message) object;
			System.out.println("                      " + message.getSendDate()
					+ "                      ");
			System.out.println("[" + message.getSender() + "]");
			System.out.println("         " + message.getContent());
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// ----发送user作为登录请求
		User user = new User("levy", "levy", "young for you");
		ctx.write(user);
		ctx.flush();
		// 发送message信息实体
		String date = new Date().toLocaleString();
		Message message = new Message("levy", "hxy", "IIIIIIIIIIIIIIIII", date);
		ctx.writeAndFlush(message);
	}

}
