<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.webxml.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String code = (String) request.getAttribute("code");
%>
<!DOCTYPE html>
<html>
<head>
<title>内部员工认证</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>
</head>
<script type="text/javascript">
	function auth()
	{
		window.location="<%=basePath%>bindpsn.html?code=<%=code%>";
	}
</script>
<body>
	<div data-role="page" data-theme="e">
		<div data-role="header">
			<h1>再次确认</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
			<label>您好,请确认是否进行微信平台认证:</label> <br> <a href="javascript:auth()"
				data-role="button" id="btn_sub">确认</a>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>