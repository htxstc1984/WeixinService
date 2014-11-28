package com.itg.weixin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.xml.XMLSerializer;

public class Test3 {

	public Test3() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
		Date from = fmt.parse("20140214010000");
		
		Date now = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now_str = matter1.format(now);
		System.out.print(now_str);
	}
}
