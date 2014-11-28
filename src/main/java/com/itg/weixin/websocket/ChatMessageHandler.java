package com.itg.weixin.websocket;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

public class ChatMessageHandler extends SimpleChannelUpstreamHandler{

	static final String WS_PATH = "/chat";
	static final String ONLINE_USERS_PATH = "/online-users";
	static final WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
			"ws://" + Main.BIND_HOST + ":" + Main.BIND_PORT + WS_PATH, null, false);
	
	MessageCenter messageCenter;
	Broadcaster broadcaster;
	
	public ChatMessageHandler(MessageCenter messageCenter,
			Broadcaster broadcaster) {
		this.messageCenter = messageCenter;
		this.broadcaster = broadcaster;
	}
	
	@Override
	public void messageReceived(final ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		if (e.getMessage() instanceof HttpRequest){
			HttpRequest req = (HttpRequest)e.getMessage();
			final QueryStringDecoder queryString = new QueryStringDecoder(req.getUri(), Main.UTF8);
			System.out.println("get request: " + req.getUri());

			if (!"GET".equals(req.getMethod().getName())){
				ctx.getChannel().write(Utils.newEmptyResponse(HttpResponseStatus.NOT_FOUND, req.getHeader("Origin")));
				return;
			}
			
			if (WS_PATH.equals(queryString.getPath())){
				List<String> nameArray = queryString.getParameters().get("name");
				if (nameArray == null || nameArray.size() != 1 || nameArray.get(0).length() == 0){
					ctx.getChannel().write(Utils.newEmptyResponse(HttpResponseStatus.BAD_REQUEST, req.getHeader("Origin")));
					ctx.getChannel().close();
					return;
				}
				
				final String user = nameArray.get(0);
				if (!messageCenter.login(user, e.getChannel())){
					ctx.getChannel().write(Utils.newEmptyResponse(HttpResponseStatus.FORBIDDEN, req.getHeader("Origin")));
					ctx.getChannel().close();
					return;
				}
				
				System.out.println("user login: " + user);
				ChannelFuture handshakeFuture = factory.newHandshaker(req).handshake(ctx.getChannel(), req);
				handshakeFuture.addListener(new ChannelFutureListener() {
					
					public void operationComplete(ChannelFuture future) throws Exception {
						Message msg = new Message(Utils.getCurrentSeconds(), "", user, "login");
						broadcaster.doBroadcast(msg);
						ctx.setAttachment(user);
					}
				});
			}
			else if (ONLINE_USERS_PATH.equals(queryString.getPath())){
				DefaultHttpResponse resp = new DefaultHttpResponse(HttpVersion.HTTP_1_1, 
						HttpResponseStatus.OK);
				String json = Utils.toString(messageCenter.onlineUsers());
				resp.setContent(ChannelBuffers.wrappedBuffer(json.getBytes(Main.UTF8)));
				resp.setHeader("Content-Type", "text/plain; charset=utf-8");
				resp.setHeader("Content-Length", resp.getContent().readableBytes());
				String origin = req.getHeader("Origin");
				if (origin != null && origin .length() > 0){
					resp.setHeader("Access-Control-Allow-Origin", origin);
					resp.setHeader("Access-Control-Allow-Methods", "GET");
//					resp.setHeader("Access-Control-Allow-Origin", origin);
				}
				resp.setChunked(false);
				ctx.getChannel().write(resp);
				return;
			}
			else{
				ctx.getChannel().write(Utils.newEmptyResponse(HttpResponseStatus.NOT_FOUND, req.getHeader("Origin")));
				return;
			}
		}
		else if (e.getMessage() instanceof Message){
			Message msg = (Message)e.getMessage();
			Message actualMsg = new Message(msg.ts, msg.content, (String)ctx.getAttachment(), msg.type);
			messageCenter.addMessage(actualMsg.user, actualMsg);
			broadcaster.doBroadcast(actualMsg);
		}
		else{
			ctx.sendUpstream(e);
		}
		
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		String user = (String)ctx.getAttachment();
		if (user != null && messageCenter.logout(user)){
			broadcaster.doBroadcast(new Message(Utils.getCurrentSeconds(), "", user, "logout"));
			System.out.println("user logout: " + user);
		}
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		Main.channels.add(e.getChannel());
	}
	
}
