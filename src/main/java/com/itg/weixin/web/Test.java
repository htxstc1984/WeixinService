package com.itg.weixin.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.itg.weixin.vo.WeixinMsgVO;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// IPsnServicePortType service = new IPsnService()
		// .getIPsnServiceSOAP11PortHttp();
		// String str = service.getPsnPhone("htx", "��ͥ��");
		//
		// System.out.println(str);

//		 try {
//		 authByMMS("13950002585","aaaaaa");
//		 } catch (SQLException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }

		URL accessUrl;
		String responseContent = null;
		try {
			accessUrl = new URL(
					"http://localhost:8090/WeixinService/weixinrec.html");

			HttpURLConnection urlcon = (HttpURLConnection) accessUrl
					.openConnection();
			urlcon.setRequestMethod("POST");
			urlcon.setRequestProperty("content-type", "text/html");

			urlcon.setDoOutput(true);
			String content = "<xml>"
					+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
					+ "<FromUserName><![CDATA[oGWhot6q83jPLENglsitEv1xjYCw]]></FromUserName>"
					+ "<CreateTime>1348831860</CreateTime>"
					+ "<MsgType><![CDATA[event]]></MsgType>"
					+ "<Event><![CDATA[CLICK]]></Event>"
					+ "<EventKey><![CDATA[itg35]]></EventKey>"
					//+ "<Content><![CDATA[投票]]></Content>"
					//+ "<MsgId>1234567890123456</MsgId>" 
					+ "</xml>";
			// urlcon.setRequestProperty("content", content);
			byte[] b = content.toString().getBytes();
			urlcon.getOutputStream().write(b, 0, b.length);
			urlcon.getOutputStream().flush();
			urlcon.getOutputStream().close();

			InputStream in = urlcon.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();

			rd.close();
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(responseContent);

	}

	public static String authByMMS(String telno, String msg)
			throws SQLException {
		try {

			String url = "jdbc:MySql://59.57.246.61:3306/mas?&dbcharset=gb2312&characterEncoding=gb2312";
			Connection conn = (Connection) DriverManager.getConnection(url,
					"OATEST", "123456789");
			PreparedStatement ps = conn
					.prepareStatement("insert into api_mt_BBB(mobiles,content,is_wap) values(?,?,0)");

			ps.setString(1, telno);
			ps.setString(2, msg);
			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			return "短信发送失败，请与管理员联系";
		}

		return "已向" + telno + "发送校验码，请收到后给我们发送校验码进行认证";
	}
}
