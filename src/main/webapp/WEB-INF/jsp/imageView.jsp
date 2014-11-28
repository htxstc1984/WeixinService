<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String src = (String) request.getAttribute("src");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看大图</title>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,minimum-scale=0.3,maximum-scale=1.0" />
</head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

<style>
body{
	position: relative;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: #A0D3E0;
}
</style>
<body>
	<img src="<%=basePath+src %>" alt="" />
</body>
</html>
