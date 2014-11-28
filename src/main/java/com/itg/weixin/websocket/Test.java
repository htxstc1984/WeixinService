package com.itg.weixin.websocket;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String v = "asda\"'<s>\\\r\n";
		System.out.println(v);
		System.out.println(v.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\\\"").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
	}
	
	static void out(byte[] b){
		for (int i=0;i<b.length;i++)
			System.out.print(b[i] + " ");
		System.out.println();
	}

}
