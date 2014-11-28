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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>大家说</title>
<style type="text/css">
ul,li {
	list-style: none;
	margin: 0;
	padding: 0;
}

.scroll {
	width: 1000px;
	height: 640px;
	overflow: hidden;
	border: 0px solid #333;
	margin: 2% auto 0;
}

.scroll li {
	width: 1000px;
	overflow: hidden;
	margin-top:20px
}

.scroll li a:hover {
	text-decoration: underline;
}
</style>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	var fromId = 0;
	var maxid = 0;
	var isInited = false;
	var firstLiLen = "";
	
	function autoScroll(){ 
		firstLiLen = "-" + ($('ul li:eq(0)').height()+20);
		var secs = $('ul li:eq(0)').height() / 40 * 300;
		$("#msgBox").animate({ 
	        marginTop : firstLiLen 
	    },secs,function(){ 
	    	$("#msgBox").css({marginTop : "0px"}).find("li:first").appendTo(this); 
	    });
	} 

	function getApprovedMsg() {
		$.post("<%=basePath%>getApprovedMsg.html",
						{
							fromId : fromId,
							openid : ""
						},
						function(data, status) {
							if (status != "success") {
								return;
							}
							eval("var msgList = " + data);
							if (msgList == "") {
								return;
							}

							for ( var i = 0; i < msgList.length; i++) {
								var html = "";
								fromId = msgList[i].id;
								maxid = maxid + 1;
								html = html
										+ "<li><font size='7px' style=\"font-family: '楷体_GB2312';color:white;line-height:150%;vertical-align: top;word-wrap:break-word;\">"
										+ msgList[i].nickname
										+ "&nbsp;说『"
										+ msgList[i].keyword + "』</font><font size='7px' style=\"font-family: '楷体_GB2312';color:yellow;line-height:150%;vertical-align: top;word-wrap:break-word;\"><br/>&nbsp;“"
										+ msgList[i].message + "”</font></li>";

								//html = html
								//		+ "<li><table><tbody><tr><td style='vertical-align: top'><font size='7' style=\"font-family: '楷体';color:white;vertical-align: middle;word-wrap:break-word;\">"
								//		+ msgList[i].nickname
								//		+ "&nbsp;</font></td>"
								//		+ "<td><font size='7' style=\"font-family: '楷体';color:white;vertical-align: middle;word-wrap:break-word;\">说“"
								//		+ msgList[i].keyword
								//		+ "”："
								//		+ msgList[i].message
								//		+ "</font></td></tr></tbody></table></li>";
								if (isInited) {
									var len = $("ul li").length - 1;
									if (len > 5) {
										len = 5;
									}
									$("ul li:eq(" + len + ")").after(html);
								} else {
									$("#msgBox").prepend(html);
								}
								if (i == msgList.length - 1) {
									if (!isInited) {
										isInited = true;
									}
								}
							}

						});
	}
	getApprovedMsg();
	setInterval(getApprovedMsg, 8000);
	autoScroll();
	setInterval(autoScroll, 3000);
</script>
</head>
<body background="<%=basePath%>image/bg.jpg">
	<div>
		<h1 align="center">
			<strong><font size='18px' color="white" style="font-family: sans-serif;">大家说</font></strong>
		</h1>
	</div>
	<div class="scroll">
		<ul id="msgBox" class="list">
			<!-- <li><table><tr><td width="10%">aaa说</td><td><font style="font-family: sans-serif;">aaddd</font></td></tr></table></li> -->
		</ul>
	</div>


</body>
</html>
