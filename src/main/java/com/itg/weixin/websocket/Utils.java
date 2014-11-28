package com.itg.weixin.websocket;

import java.util.Enumeration;


import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

import com.itg.weixin.websocket.MessageCenter.OnlineUser;

public class Utils {
	public static String toString(Enumeration<OnlineUser> ul){
		StringBuilder sb = new StringBuilder(200);
		sb.append("[");
		while (ul.hasMoreElements()){
			OnlineUser ou = ul.nextElement();
			String user = ou.getUser().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\\\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
			sb.append("{\"user\":\"").append(user).append("\",\"ts\":").append(ou.getLoginTime()).append("}");
			if (ul.hasMoreElements())
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static int getCurrentSeconds(){
		return (int)(System.currentTimeMillis()/1000);
	}
	
	public static HttpResponse newEmptyResponse(HttpResponseStatus status, String origin){
		DefaultHttpResponse resp = new DefaultHttpResponse(HttpVersion.HTTP_1_1, status);
		resp.setChunked(false);
		resp.setContent(null);
		resp.setHeader("Content-Length", "0");
		if (origin != null && origin.length() > 0){
			resp.setHeader("Access-Control-Allow-Origin", origin);
			resp.setHeader("Access-Control-Allow-Methods", "GET");
		}
		return resp;
	}
}
