<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.webxml.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String openid = (String) request.getAttribute("openid");
	String sucMsg = (String) request.getAttribute("sucMsg");
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
		$.post("<%=basePath%>checkmms.html", {
			openid : "<%=openid%>",
							mmscode : $("#mmscode")[0].value
						},
						function(data, status) {
							if (status == "success") {
								//alert("Data: " + data + "\nStatus: " + status);
								var result = jQuery.parseJSON(data);
								if (result.code == 0) {
									var html = "";
									html = html
											+ "<div type='errMsg' data-role=\"content\"><p style=\"color: #999\">"
											+ result.msg + "</p></div>";
									if ($("div[type='errMsg']")) {
										$("div[type='errMsg']").remove();
									}
									if ($("#inputPanel")) {
										$("#inputPanel").remove();
									}
									$("#content").append(html)
											.trigger("create");
								} else {
									var html = "";
									html = html
											+ "<div type='errMsg' data-role=\"content\"><p style=\"color: #999\">"
											+ result.msg + "</p></div>";
									if ($("div[type='errMsg']")) {
										$("div[type='errMsg']").remove();
									}
									$("#content").append(html)
											.trigger("create");
								}
							}
						});

	}
	$( function() {
	    //默认设置，一个小圈圈在转
	    $('#default').live( 'tap', function() {
	      $.mobile.loadingMessageTextVisible = false;
	      $.mobile.showPageLoadingMsg();
	    } );
	    //小圈子外围加矩形的背景
	    $('#loadingMessageTextVisible').live( 'tap', function() {
		$.mobile.loadingMessageTextVisible = true;
		$.mobile.loadingMessageTheme = 'a';
	        $.mobile.showPageLoadingMsg();
	    } );
	    //矩形背景样式为e
	    $('#loadingMessageTheme').live( 'tap', function() {
		$.mobile.loadingMessageTextVisible = true;
		$.mobile.loadingMessageTheme = 'e';
	        $.mobile.showPageLoadingMsg();
	    } );
	    //小圈子下面加上文字
	    $('#customText').live( 'tap', function() {
		$.mobile.loadingMessageTextVisible = true;
	        $.mobile.showPageLoadingMsg( 'a', "Please wait..." );
	    } );
	    //只有文字，没有小圈子在转
	    $('#noSpinner').live( 'tap', function() {
		$.mobile.loadingMessageTextVisible = true;
	        $.mobile.showPageLoadingMsg( 'a', "Please wait...", true );
	    } );

	  } );
</script>
<body onload="check()">
	<div data-role="page" data-theme="e">
		<div data-role="header">
			<h1>请填写短信认证码</h1>
		</div>
		<!-- /header -->

		<div id="content" data-role="content">
			<div id="inputPanel">
				<label><%=sucMsg%></label> <label for="mmscode">请输入短信验证码:</label> <input
					placeholder="123456" type="tel" name="mmscode" id="mmscode"
					value="" /> <br> <a href="javascript:auth()"
					data-role="button" id="btn_sub">提交</a>
			</div>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>