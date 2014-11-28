<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String modid = (String) request.getAttribute("modid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>纯CSS Lightbox效果 （无需JS）</title>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,user-scalable=no" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<style>
body {
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	width: 100%;
	height: 100%;
	overflow: hidden;
	position:relative;
}

a {
	color: #000;
	text-decoration: none;
}

.img {
	border: 0px;
}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display:none;
	top: 10%;
	left: 5%;
	width: 80%;
	height: 60%;
	border: 16px solid #FFF;
	border-bottom: none;
	background-color: white;
	z-index: 1002;
	overflow: hidden;
}

.small_div {
	position: absolute;
	width: 100%;
	height: 100%;
}

.small_div img {
	width: 20%;
}
</style>

</head>
<body>
	<div style="position: relative;width: 100%;height: 100%">
		<div class="small_div">
			<img onclick="document.getElementById('light').style.display ='block'; src="<%=basePath%>/image/location-4.jpg" />
			
		</div>
		<div id="light" class="white_content">
			<img rel="<%=basePath%>/image/develop-xc.png"/>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/js/imageView.js"></script>
<script type="text/javascript">
	$(function() {
	    $('#light').imageView({width: 800, height:500});
	});
</script>
</html>

