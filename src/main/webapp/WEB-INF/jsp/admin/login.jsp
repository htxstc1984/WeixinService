<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet"
	href="./easyui/themes/default/easyui.css">
<link type="text/css" rel="stylesheet" href="./easyui/themes/icon.css">
<script type="text/javascript" src="./easyui/jquery.min.js"></script>
<script type="text/javascript" src="./easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
</head>

<body>
	<div id="loginWin" class="easyui-window" title="登录"
		style="width: 350px; height: 188px; padding: 5px;" minimizable="false"
		maximizable="false" resizable="false" collapsible="false">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 5px; background: #fff; border: 1px solid #ccc;">
				<form id="loginForm" method="post" action="./loginAction.html">
					<div style="padding: 5px 0;">
						<label for="login">帐号:</label> <input type="text" name="username"
							style="width: 260px;"></input>
					</div>
					<div style="padding: 5px 0;">
						<label for="password">密码:</label> <input type="password"
							name="password" style="width: 260px;"></input>
					</div>
					<div style="padding: 5px 0; text-align: center; color: red;"
						id="showMsg"></div>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: right; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)" onclick="login()">登录</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="cleardata()">重置</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	document.onkeydown = function(e) {
		var event = e || window.event;
		var code = event.keyCode || event.which || event.charCode;
		if (code == 13) {
			login();
		}
	}
	$(function() {
		$("input[name='username']").focus();
	});
	function cleardata() {
		$('#loginForm').form('clear');
	}
	function login() {
		if ($("input[name='username']").val() == ""
				|| $("input[name='password']").val() == "") {
			$("#showMsg").html("用户名或密码为空，请输入");
			$("input[name='username']").focus();
		} else {
			//ajax异步提交  
			$('#loginForm').submit();
		}
	}
</script>
</html>