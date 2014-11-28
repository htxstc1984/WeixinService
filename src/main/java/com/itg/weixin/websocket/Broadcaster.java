package com.itg.weixin.websocket;

import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.jboss.netty.util.ExternalResourceReleasable;

import com.itg.weixin.websocket.MessageCenter.OnlineUser;

public class Broadcaster implements ExternalResourceReleasable {
	ExecutorService executor = Executors.newFixedThreadPool(4);
	final MessageCenter center;
	
	public Broadcaster(MessageCenter center) {
		this.center = center;
	}
	
	int predictBufferSize(Message msg){
		return 20 + (msg.type.length()+msg.content.length()+msg.user.length())*2;
	}
	
	public void doBroadcast(final Message msg){
		if (executor.isShutdown())
			return;	
		
		executor.execute(new Runnable() {
			public void run() {
				final Enumeration<OnlineUser> en = center.onlineUsers();
				
				ChannelBuffer buf = ChannelBuffers.buffer(predictBufferSize(msg));
				buf.writeInt(0);
				buf.writeInt(msg.ts);
				byte[] strBytes = msg.type.getBytes(Main.UTF16);
				buf.writeInt(strBytes.length > 2 ? strBytes.length-2 : 0);
				if (strBytes.length > 2)
					buf.writeBytes(strBytes, 2, strBytes.length-2);
				
				strBytes = msg.user.getBytes(Main.UTF16);
				buf.writeInt(strBytes.length > 2 ? strBytes.length-2 : 0);
				if (strBytes.length > 2)
					buf.writeBytes(strBytes, 2, strBytes.length-2);
				
				strBytes = msg.content.getBytes(Main.UTF16);
				buf.writeInt(strBytes.length > 2 ? strBytes.length-2 : 0);
				if (strBytes.length > 2)
					buf.writeBytes(strBytes, 2, strBytes.length-2);
				
				buf.setInt(0, buf.writerIndex());
				BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buf);
				
				while (en.hasMoreElements()){
					OnlineUser ou = en.nextElement();
					if (ou.getChannel().isConnected())
						ou.getChannel().write(frame);
				}
			}
		});
	}

	public void releaseExternalResources() {
		executor.shutdownNow();
	}
}
