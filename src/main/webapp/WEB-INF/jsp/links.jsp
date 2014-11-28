<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>关联企业微信链接</title>
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
		<!--
		<div data-role="header">
			<h1>关联企业微信链接</h1>
		</div>
		-->
		<!-- /header -->

		<div data-role="content">
			<ul data-role="listview" data-inset="true">
			    <li>
			        <img src="<%=basePath%>image/round_kg.png">
			        <h2>国贸控股</h2>
			        <p></p></a>
			    </li>
			    <li data-theme="e" data-role="list-divider"></li> 
			    <li><a href="http://117.25.165.29/itg/wap/project.asp?id=6&mainId=6&fromusername=itgGroup">
			        <img src="<%=basePath%>image/round_dc.png">
			        <h2>国贸地产</h2>
			        <p>微信号：itg_gmdc</p></a>
			    </li>
			    <li data-theme="e" data-role="list-divider"></li> 
			    <li>
			        <img src="<%=basePath%>image/round_qc.png">
			        <h2>国贸汽车</h2>
			        <p>微信号：itgmotor</p></a>
			    </li>
			    <li data-theme="e" data-role="list-divider"></li> 
			    <li>
			        <img src="<%=basePath%>image/round_qh.png">
			        <h2>国贸期货</h2>
			        <p>微信号：gmqh888</p></a>
			    </li>
			    <li data-theme="e" data-role="list-divider"></li> 
			    <li>
			        <img src="<%=basePath%>image/round_wl.png">
			        <h2>国贸物流</h2>
			        <p>微信号：itg_gmwl</p></a>
			    </li>
			    <li data-theme="e" data-role="list-divider"></li> 
			    <li>
			        <img src="<%=basePath%>image/round_jhx.png">
			        <h2>国贸金海峡</h2>
			        <p>微信号：itg_gmjhx</p></a>
			    </li>
			</ul>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>