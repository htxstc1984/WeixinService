<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = (String) request.getAttribute("title");
	String slogans = (String) request.getAttribute("slogans");
%>
<!DOCTYPE html>
<html>
<head>
<title><%=title%></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>css/msgbar.css" rel="stylesheet">
</head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<style>
body {
	margin: 0;
	position: relative;
	width: 100%;
	height: 100%;
}
.divHeader{
	position: fixed;
	background-color: red;
	width: 100%;
	height: 40px;
	top:0;
}

.btn_left {

	display: block;
	float: left;
	margin-top: 20px;
	width: 100px;
	height: 30px;
	top:10px;
	border-radius: 5px;
	box-shadow: #666 0px 0px 10px;
	background-color: blue
}
.btn_left a{
	text-decoration: none;
	line-height: 30px;
}

</style>
<script type="text/javascript">
	function switchdiv(divid) {
		$('#' + divid).toggle();
	}
	$(function() {
		var slogans = <%=slogans%>;

		for ( var i = 0; i < slogans.length; i++) {
			var slogan = slogans[i];
			var html = "";
			html+="<div class='status-item' id='block_232'>";
			html+="<div class='mod'>";
			html+="<div class='bd layout-2'>";
			html+="<p class='text'>"+slogan.createDate+" 发布：</p>";
			html+="<blockquote>";
			html+="<p>";
			html+="<font size='4' color='brown' style='vertical-align: top;'><strong>“</strong></font><font size='3'>"+slogan.content+"<font size='4' color='brown' style='vertical-align: bottom;'><strong>”</strong></font></font>";
			html+="</p>";
			html+="</blockquote>";
			html+="</div>";
			html+="</div>";
			html+="</div>";
			$('#msgList').append(html);
		}
	});
	function redirect2page(redirect){
		location.href = "<%=basePath%>"+redirect;
	}
</script>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<body>
	<div id="page" data-role="page" data-theme="e">
		<div data-role="header" data-position="fixed">
			<h1 id="headerText"><%=title%></h1>
			<a href="javascript:redirect2page('collectSlogan.html')" class="ui-btn-left" data-role="button" data-inline="true" data-icon="info" data-theme="e">查看规则</a>
			<a href="javascript:redirect2page('pushSlogan.html')" class="ui-btn-right" data-role="button" data-inline="true" data-icon="info" data-theme="e">参与活动</a>
		</div>
		<!-- /header -->
		<div data-role="content" class="content" >
			<div class="getstart" id="msgList">
			</div>
			

		</div>
	</div>
	
</body>
</html>

