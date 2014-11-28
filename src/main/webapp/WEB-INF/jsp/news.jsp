<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.itg.weixin.vo.NewsVO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	NewsVO newsBody = (NewsVO) request.getAttribute("newsBody");
	String title = newsBody.getTitle();
	String content = newsBody.getContent();
%>
<!DOCTYPE html>
<html>
<head>
<title>国贸新闻</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="<%=title%>" />
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>

<script type="text/javascript">
	function onload() {
		$("img").removeAttr("style");
		$("img").attr("width", "80%").attr("height", "");
	}
	
</script>

</head>
<body onload="onload()">
	<div data-role="page" data-theme="e">

		<!-- /header -->

		<div data-role="content">
			<h2 align="center"><%=title%></h2>
			<%=content%>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>