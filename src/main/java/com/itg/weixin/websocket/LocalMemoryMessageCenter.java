package com.itg.weixin.websocket;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import org.jboss.netty.channel.Channel;

import com.itg.weixin.websocket.MessageCenter.OnlineUser;

public class LocalMemoryMessageCenter implements MessageCenter{
	final ConcurrentHashMap<String, Entry> index = new ConcurrentHashMap<String, Entry>();

	public boolean login(String user, Channel ch) {
		Entry e = new Entry(ch, user, Utils.getCurrentSeconds());
		return index.putIfAbsent(user, e) == null;
	}

	public boolean logout(String user) {
		return index.remove(user) != null;
	}

	public List<Message> queryUserMessages(String user) {
		Entry e = index.get(user);
		return e==null ? null : e.msgs;
	}

	public Channel queryUserChannel(String user) {
		Entry e = index.get(user);
		return e==null ? null : e.ch;
	}

	public boolean addMessage(String user, Message msg) {
		Entry e = index.get(user);
		if (e == null)
			return false;
		e.msgs.add(msg);
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration<OnlineUser> onlineUsers() {
		return (Enumeration)index.elements();
	}

	public void releaseExternalResources() {
		index.clear();
	}

}

class Entry implements OnlineUser{
	public final Channel ch;
	public final String user;
	public final int ts;
	public final List<Message> msgs;
	public Entry(Channel ch, String user, int ts) {
		this.ch = ch;
		this.user = user;
		this.ts = ts;
		this.msgs = new LinkedList<Message>();
	}
	public String getUser() {
		return user;
	}
	public Channel getChannel() {
		return ch;
	}
	public int getLoginTime() {
		return ts;
	}
	
}
