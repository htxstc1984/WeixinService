<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.webxml.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	ChinaStockWebServiceSoap soap = new ChinaStockWebService()
			.getChinaStockWebServiceSoap();

	String time = "";
	String price = "";
	try {
		ArrayOfString result = soap.getStockInfoByCode("sh600755");
		time = result.getString().get(2);
		price = result.getString().get(3);
		//returnRs = "【" + result.getString().get(2) + "】价格为:"
		//		+ result.getString().get(3);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>
<style>
#bodybg {
	position: absolute;
	width: 100%;
	height: 100%;
	left: 0px;
	top: 0px;
	z-index: 0;
}

#bodybg .stretch {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<div data-role="page" data-theme="e">
		<div data-role="header">
			<h1>国贸实时股价</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
			<div data-role="collapsible" data-collapsed="false">
			　 <h3>实时价格</h3>
			　 <p>【<%=time%>】价格为:<%=price%></p>
			</div>
			<div data-role="collapsible" data-collapsed="false">
			　 <h3>趋势图</h3>
			　<img width="100%"
				height="60%"
				src="http://www.webxml.com.cn/WebServices/ChinaStockWebService.asmx/getStockImageByCode?theStockCode=sh600755">
			</div>
		</div>
		<!-- /content -->
	</div>
	
	<!-- /page -->
</body>
</html>