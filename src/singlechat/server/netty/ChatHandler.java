package singlechat.server.netty;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import redis.clients.jedis.Jedis;
import singlechat.server.user.dao.User;
import utils.SerializeUtils;

public class ChatHandler extends SimpleChannelInboundHandler<Object> {
	private Map<String, User> onlineMap = new HashMap<>();

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object arg1)
			throws Exception {
		System.out.println("y有发出去吗");
		User user = (User) arg1;
		Jedis dJedis = new Jedis("localhost", 6379);
		dJedis.set(("user:" + user.getId()).getBytes(),
				SerializeUtils.serialize(user));
		byte[] userB = dJedis.get(user.getName().getBytes());
		User userU = (User) SerializeUtils.unserialize(userB);
		if (userU == null)
			return;
		if (user.getId() == userU.getId()) {
			onlineMap.put(user.getName(), user);
		}
		arg0.writeAndFlush("login success:" + user.getName());
		System.out.println("receive" + user.getDescription());
	}

}
