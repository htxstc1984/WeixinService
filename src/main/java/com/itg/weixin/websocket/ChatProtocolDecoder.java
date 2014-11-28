package com.itg.weixin.websocket;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.UpstreamMessageEvent;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;

public class ChatProtocolDecoder extends SimpleChannelUpstreamHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		if (e.getMessage() instanceof WebSocketFrame) {
			WebSocketFrame frame = (WebSocketFrame) e.getMessage();

			ChannelBuffer buf = frame.getBinaryData();
			if (buf == null) {
				ctx.getChannel().close();
				return;
			}

			if (buf.order().equals(ByteOrder.LITTLE_ENDIAN))
				buf = ChannelBuffers.wrappedBuffer(buf.toByteBuffer().order(
						ByteOrder.BIG_ENDIAN));
			int actualTotal = buf.readableBytes();
			if (actualTotal != buf.readInt()) {
				System.out.println("bad frame, ignore");
				return;
			}

			buf.readInt(); // ignore ts
			int len = buf.readInt();
			byte[] bytes = new byte[len];
			buf.readBytes(bytes, 0, len);
			String type = new String(bytes, Main.UTF16);

			len = buf.readInt();
			bytes = new byte[len];
			buf.readBytes(bytes, 0, len);
			String user = new String(bytes, Main.UTF16);

			len = buf.readInt();
			bytes = new byte[len];
			buf.readBytes(bytes, 0, len);
			String content = new String(bytes, Main.UTF16);

			Message msg = new Message(Utils.getCurrentSeconds(), content, user,
					type);
			ctx.sendUpstream(new UpstreamMessageEvent(e.getChannel(), msg, e
					.getChannel().getRemoteAddress()));
		} else
			ctx.sendUpstream(e);
	}
}
