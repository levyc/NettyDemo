package singlechat.server.netty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import singlechat.server.message.dao.Message;
import singlechat.server.message.dao.MessageModel;
import singlechat.server.user.dao.User;
import singlechat.server.user.dao.UserModel;

public class ChatHandler extends SimpleChannelInboundHandler<Object> {
	private Map<String, UserModel> onlineMap = new HashMap<>();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object object)
			throws Exception {
		if (object instanceof User) {// handle login
			User user = (User) object;
			if (handleLogin(user)) {
				ctx.writeAndFlush("login success,welcome to  chat room:"
						+ user.getName());
				System.out.println(user.getName() + ":online");
				List<Message> messagesList = autoGetMessage(user);
				for (Message message : messagesList) {
					ctx.writeAndFlush(message);
				}
			}
		} else if (object instanceof Message) {// handler send message
			Message message = (Message) object;
			if (saveMessage(message))
				ctx.writeAndFlush("send finish");
		}

	}

	private boolean handleLogin(User user) {
		UserModel uu = (UserModel) UserModel.dao
				.findFirst("select * from  user where name = '" + user.getName()
						+ "'  and password= '" + user.getPassword() + "'");
		System.out.println("uu:");
		if (uu == null)
			return false;
		return true;
	}

	private List<Message> autoGetMessage(User user) {
		List<Message> list = new ArrayList<>();
		List<MessageModel> messageList = MessageModel.dao
				.find("select * from message where receiver = '"
						+ user.getName() + "' and state = 1");
		for (MessageModel model : messageList) {
			Message message = new Message();
			message.setContent(model.getStr("content"));
			message.setId(model.getInt("id"));
			message.setReceiver(model.getStr("receiver"));
			message.setSender(model.getStr("sender"));
			message.setState(1);
			message.setSendDate(model.getStr("sendDate"));
			list.add(message);
		}
		return list;
	}

	private boolean saveMessage(Message message) {
		MessageModel model = new MessageModel();
		model.set("sendDate", message.getSendDate());
		model.set("sender", message.getSender());
		model.set("receiver", message.getReceiver());
		model.set("content", message.getContent());
		model.set("state", 1);
		return model.save();
	}

}
