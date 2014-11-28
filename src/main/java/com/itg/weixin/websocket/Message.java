package com.itg.weixin.websocket;

import java.util.Date;

public class Message {
	public final int ts;
	public final String content;
	public final String user;
	public final String type; 
	public Message(int ts, String content, String user, String type) {
		this.ts = ts;
		this.content = content;
		this.user = user;
		this.type = type;
	}
	@Override
	public String toString() {
		return new StringBuilder(content.length() + user.length() + 20)
				.append("{user:").append(user)
				.append(", type:").append(type)
				.append(", content:").append(content)
				.append(", ts:").append(new Date(ts))
				.append("}").toString();
	}
}
