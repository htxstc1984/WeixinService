<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = (String) request.getAttribute("title");
	String infoMsg = (String) request.getAttribute("infoMsg");
%>
<!DOCTYPE html>
<html>
<head>
<title>征集口号</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<style>
body {
	margin: 0;
	position: relative;
	width: 100%;
	height: 100%;
	overflow: hidden;
	/*background: url('<%=basePath%>image/itg35/itg35.jpg') top center no-repeat;*/
	/*background-size:cover;*/
}

.btn_say {
	position: fixed;
	left: 30%;
	bottom: 50px;
	width: 40%;
	height: 40px;
	line-height: 40px;
	border: 1px black solid;
	border-radius: 5px;
	box-shadow: #666 0px 0px 10px;
	background-color: gray;
	opacity:0.8;
}
.btn_say a{
	display:block;
	width:100%;
	height:100%;
	color:red;
	text-decoration: none;
	
}

.div_his {
	position: fixed;
	overflow: scroll;
	left: 15%;
	bottom: 15%;
	width: 70%;
	height: 70%;
	border: 1px black solid;
	border-radius: 5px;
	box-shadow: #666 0px 0px 10px;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function switchdiv(divid) {
		$('#' + divid).toggle();
	}
</script>
<body>
	<div style="position:relative ;width: 100%;height: 100%;margin: 0;z-index: -999;overflow: hidden;">
		<img style="width: 100%;height: 100%" src="<%=basePath%>image/itg35/itg35.jpg">
	</div>
	<center>
		<div class="btn_say">
			<a href="<%=basePath%>pushSlogan.html"><strong>我要参与</strong></a>
		</div>

	</center>
</body>
</html>

