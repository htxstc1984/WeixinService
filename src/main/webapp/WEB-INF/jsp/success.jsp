<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = (String) request.getAttribute("title");
	String sucMsg = (String) request.getAttribute("sucMsg");
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
</head>
<body>
	<div data-role="page" data-theme="e">
		<div data-role="header">
			<h1><%=title%></h1>
		</div>
		<!-- /header -->

		<div data-role="content">
			<p style="color: #999"><%=sucMsg%></p>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>