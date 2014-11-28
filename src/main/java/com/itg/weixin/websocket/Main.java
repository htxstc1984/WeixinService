package com.itg.weixin.websocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

public class Main {
	public static final String BIND_HOST = "172.18.0.147";
	public static final int BIND_PORT = 9999;
	public static final Charset UTF8;
	public static final Charset UTF16;
	static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {
		UTF8 = Charset.forName("UTF-8");
		UTF16 = Charset.forName("UTF-16");
	}
	public static final ChannelGroup channels = new DefaultChannelGroup(
			"chat-server");

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Global.center = new LocalMemoryMessageCenter();
		Global.broadcaster = new Broadcaster(Global.center);

		ServerBootstrap boot = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newSingleThreadExecutor(),
						Executors.newCachedThreadPool()));
		boot.setPipelineFactory(new ChatPiplineFactory());
		Channel sch = boot.bind(new InetSocketAddress(BIND_PORT));

		System.out.println("按下回车停止：");
		System.in.read();

		sch.close().awaitUninterruptibly();
		Global.center.releaseExternalResources();
		Global.broadcaster.releaseExternalResources();
		channels.close().awaitUninterruptibly();
		boot.releaseExternalResources();
	}

	static class ChatPiplineFactory implements ChannelPipelineFactory {

		public ChannelPipeline getPipeline() throws Exception {
			ChannelPipeline p = Channels.pipeline();
			p.addLast("decoder", new HttpRequestDecoder());
			p.addLast("chat-decoder", new ChatProtocolDecoder());
			p.addLast("aggregator", new HttpChunkAggregator(65536));
			p.addLast("encoder", new HttpResponseEncoder());
			p.addLast("handler", new ChatMessageHandler(Global.center,
					Global.broadcaster));
			return p;
		}

	}

}
