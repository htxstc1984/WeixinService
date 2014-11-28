package com.itg.weixin.websocket;

import java.util.Enumeration;
import java.util.List;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.ExternalResourceReleasable;

public interface MessageCenter extends ExternalResourceReleasable {
	
	public boolean login(String user, Channel ch);
	
	public boolean logout(String user);
	
	public List<Message> queryUserMessages(String user);
	
	public Channel queryUserChannel(String user);
	
	public boolean addMessage(String user, Message msg);
	
	public Enumeration<OnlineUser> onlineUsers();
	
	public static interface OnlineUser{
		String getUser();
		Channel getChannel();
		int getLoginTime();
	}
}
