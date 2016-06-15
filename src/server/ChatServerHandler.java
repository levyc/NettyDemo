package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
	
	public static final ChannelGroup group = 
			new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		Channel channel = arg0.channel();
		for(Channel ch:group){
			if(ch==channel){
				ch.writeAndFlush("you say"+arg1+"\n");	
			}
			else {
				ch.writeAndFlush("User from"+channel.remoteAddress()+arg1+"\n");
			}
		}
		
		
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		for(Channel ch:group){
			ch.writeAndFlush("User from"+channel.remoteAddress()+"is comming");
		}
		group.add(channel);
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		for(Channel ch:group){
			ch.writeAndFlush("User from"+channel.remoteAddress()+"is comming");
		}
		group.remove(channel);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println("User from:"+channel.remoteAddress()+"online");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println("User from:"+channel.remoteAddress()+"offline");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("some error,and exit");
		ctx.close().sync();
	}
	
}
