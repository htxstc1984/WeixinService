<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml;charset=utf-8" />
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,user-scalable=no" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<title>宣讲行程 - 厦门国贸校园招聘</title>
</head>

<style>
body {
	background-color: gray;
}

.main_content {
	position: relative;
	margin: 100px auto;
	width: 275px;
	overflow: hidden;
	border-radius: 5px;
	background-color: white;
}

.tab {
	position: relative;
	top: 5px;
	width: 98%;
}

.tab th {
	width: 80px;
	text-align: right;
}

.tab tr:nth-child(odd) {
	background-color: #D0D0D1;
}

#formsubmit {
	width: 273px;
	height: 35px;
	display: inline-block;
	border: 1px solid #333;
	border-radius: 5px;
	box-shadow: 2px 2px 2px #333;
	color: #FFF;
	font-size: 16px;
	font-weight: 700;
	text-align: center;
	line-height: 35px;
	background: -webkit-gradient(linear, center top, center bottom, from(#8DBB3B),
		to(#6AA43E));
}
</style>

<body>
	<center>
	<div class="main_content">
		<form id="formdata" name="formdata" method="post">
			<input type="hidden" name="openid" value="sadasd"></input>
			<center>
			<table border="0" class="tab">
				<tr class="first">
					<th>姓名</th>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><label><input type="radio" name="sex" value="1">男</label>
						<label><input type="radio" name="sex" value="2">女</label>
					</td>
				</tr>
				<tr>
					<th>手机号</th>
					<td><input type="text" name="phone" placeholder="请输入11位手机号" /></td>
				</tr>
				<tr>
					<th>学历</th>
					<td><label><input type="radio" name="education"
							value="1">本科</label> <label><input type="radio"
							name="education" value="2">硕士</label> <label><input
							type="radio" name="education" value="3">博士</label></td>
				</tr>
				<tr>
					<th>学校</th>
					<td><input type="text" name="school" placeholder="请输入完整学校名称" /></td>
				</tr>
				<tr>
					<th>专业</th>
					<td><input type="text" name="pro" /></td>
				</tr>
				<tr>
					<th>家庭所在地</th>
					<td><input type="text" name="home" placeholder="如：厦门" /></td>
				</tr>
				<tr>
					<th>经历</th>
					<td colspan="2" class="last"><textarea name="descr"
							placeholder="请输入主要实践经历(不限字数)"></textarea></td>
				</tr>
			</table>
			</center>
		</form>
		<a href="javascript:;" id="formsubmit">提交微简历</a>
	</div>
	</center>
</body>
<script type="text/javascript">
	$('#formsubmit').on('click', function() {
		
		$.ajax({
            type: "POST",
            dataType: "html",
            url: "<%=basePath%>subResume.html",
            data: $('#formdata').serialize(),
            success: function (result) {
            	eval("var rs = " + result);
    			alert(rs.msg);
            },
            error: function(data) {
                alert("error:"+data.responseText);
             }

        });
		
	});
	function shareTimeline() {
		WeixinJSBridge.invoke('shareTimeline', {
			'title' : '宣讲行程 - 厦门国贸校园招聘',
			'link' : 'http://chinacnd.sinaapp.com/html/xingcheng.html',
			'desc' : '宣讲行程 - 厦门国贸校园招聘',
			'img_url' : 'http://chinacnd.sinaapp.com/static/css/xingcheng.jpg'
		});
	}
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.on('menu:share:timeline', function(argv) {
			shareTimeline();
		});
	}, false);
</script>
</html>