<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.webxml.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String openid = (String) request.getSession().getAttribute("openid");
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
	function register()
	{
		var type=$("input:checked")[0].value;
		var v_email=$("#email")[0].value;
		var v_tel=$("#tel")[0].value;
		var content;
		if (type=="email") {
			content = v_email;
		}else{
			content = v_tel;
		}
		$('#btn_sub').button("disable");
		$.post("<%=basePath%>sendAuth.html", {
			type : type,
			openid : "<%=openid %>",
			content : content
		}, function(data, status) {
			eval("var rs = " + data);
			if(rs.code==0){
				$("#info_content").hide();
				if(type=="email"){
					$("#email_content").show();
				}else{
					$("#mms_content").show();
				}
				$('#btn_sub').button("enable");
			}
			else{
				var html = "";
				html = html
						+ "<div type='errMsg' data-role=\"content\"><p style=\"color: #999\">"
						+ rs.msg + "</p></div>";
				$("div[type='errMsg']").remove();
				$("#info_content").append(html).trigger("create");
				$('#btn_sub').button("enable");
			}
		});
		
		//window.location="<%=basePath %>sendAuth.html?type="+type+"&openid=<%=openid %>&content="+content;
	}
	
	function auth()
	{	
		$.post("<%=basePath%>checkmms.html", {
			openid : "<%=openid%>",
							mmscode : $("#mmscode")[0].value
						},
						function(data, status) {
							if (status == "success") {
								//alert("Data: " + data + "\nStatus: " + status);
								var result = jQuery.parseJSON(data);
								if (result.code == 0) {
									$("#mms_content").hide();
									$("#mms_success").show();
									//window.location.replace("<%=basePath%>query.html");
								} else {
									var html = "";
									html = html
											+ "<div type='errMsg' data-role=\"content\"><p style=\"color: #999\">"
											+ result.msg + "</p></div>";
									if ($("div[type='errMsg']")) {
										$("div[type='errMsg']").remove();
									}
									$("#mms_content").append(html)
											.trigger("create");
								}
							}
						});

	}
	
	$.mobile.pushStateEnabled=false;
		
	function onload(){
		var state = { //这里可以是你想给浏览器的一个State对象，为后面的StateEvent做准备。
						title : "内部员工认证",
						url : "/register.html"};
		history.replaceState(null, "内部员工认证", "/register.html");
	}
</script>
<body onload="onload()">
	<div id="container" data-role="page" data-theme="e">
		<div data-role="header">
			<h1>内部员工认证</h1>
		</div>
		<!-- /header -->

		<div id="info_content" data-role="content">
			<fieldset data-role="controlgroup">
				<legend>请选择一种认证方式(必须使用EHR的有效信息):</legend>
				<input type="radio" name="radio-choice" id="radio-choice-1"
					value="tel" checked="checked" /> <label for="radio-choice-1">短信认证</label>
				<input type="radio" name="radio-choice" id="radio-choice-2"
					value="email" /> <label for="radio-choice-2">邮箱认证</label>
			</fieldset>
			<label for="tel">请输入手机号:</label> <input type="tel" placeholder="13850000000" name="tel"
				id="tel" value="" /> <label for="email">请输入邮箱:</label> <input
				type="email" name="email" placeholder="zhangsan@126.com" id="email" value="" /> <br> <button id="btn_sub" type="button" onclick="register()">提交</button>
		</div>
		
		<div id="mms_content" data-role="content" hidden="true">
			<div id="inputPanel">
				 <label for="mmscode">请输入短信验证码:</label> <input
					placeholder="123456" type="tel" name="mmscode" id="mmscode"
					value="" /> <br> <a href="javascript:auth()"
					data-role="button" id="btn_sub">提交</a>
			</div>
		</div>
		
		<div id="email_content" data-role="content" hidden="true">
			<div>
				 <label>已发送认证邮件到您的邮箱，请进入邮箱确认</label> 
			</div>
		</div>
		
		<div id="mms_success" data-role="content" hidden="true">
			<div>
				 <label>认证成功，请返回选择其他员工专区的操作</label> 
			</div>
		</div>
		
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>