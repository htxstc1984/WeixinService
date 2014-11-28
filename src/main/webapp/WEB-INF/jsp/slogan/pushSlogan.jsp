<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = (String) request.getAttribute("title");
	String openid = (String) request.getSession()
			.getAttribute("openid");
	String truename = (String) request.getSession().getAttribute(
			"truename");
	String email = (String) request.getSession().getAttribute("email");
	String mobile = (String) request.getSession()
			.getAttribute("mobile");
%>
<!DOCTYPE html>
<html>
<head>
<title>提交口号</title>
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
}

.divBlock {
	position: relative;
	margin-top: 20px;
	width: 70%;
}

.sloganContent {
	width: 100%;
	overflow: hidden;
	margin-top: 20px;
	height: 200px;
	border: 1px black solid;
	border-radius: 5px;
	box-shadow: #666 0px 0px 10px;
	resize: none;
}

.itemdiv {
	width: 100%;
	height: 36px;
}
/*
span {
	display: block;
	line-height: 36px;
	float: left;
	text-align: right;
}
*/
.input {
	display: block;
	float: left;
	width: 70%;
	border-radius: 5px;
	line-height: 30px;
	box-shadow: #666 0px 0px 10px;
}

.btn_submit {
	display: block;
	float: left;
	margin-top: 20px;
	width: 100px;
	height: 40px;
	border-radius: 5px;
	box-shadow: #666 0px 0px 10px;
}

.btn_reset {
	display: block;
	float: right;
	margin-top: 20px;
	width: 100px;
	height: 40px;
	border-radius: 5px;
	box-shadow: #666 0px 0px 10px
}
</style>
<script type="text/javascript">
	var inittruename = "<%=truename%>";
	function switchdiv(divid) {
		$('#' + divid).toggle();
	}
	function redirect2page(redirect){
		location.href = "<%=basePath%>"+redirect;
	}
	function check(){
		
		if(inittruename == ""){
			truenameval = $("#truename").val();
			
			if(!truenameval || Trim(truenameval)==""){
				alert("请输入姓名");
				return false;
			}
			mobileval = $("#mobile").val();
			if(!mobileval || Trim(mobileval)==""){
				alert("请输入手机号码");
				return false;
			}
		}
		contentval = $("#content").val();
		if(!contentval || Trim(contentval)==""){
			alert("请输入内容");
			return false;
		}
		return true;
	}
	function Trim(string){
		return string.replace(/( +)$/g,"").replace(/^( +)/g,"");
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
			<a href="javascript:redirect2page('getSlogans.html')" class="ui-btn-right" data-role="button" data-inline="true" data-icon="info" data-theme="e">已提交口号</a>
		</div>
		<!-- /header -->
		<div data-role="content" class="content" >
			<form action="<%=basePath%>addSlogan.html" method="post" onsubmit="return check()">
				<%
					if (truename != null) {
						out.println("<h3><span>" + truename + "，您好</span></h3>");
						out.println("<input type='hidden' name='mobile' value='"+mobile+"'>");
						out.println("<input type='hidden' name='truename' value='"+truename+"'>");
					} else {
						out.println("<label for='truename'>请输入姓名:</label><input required='required' type='text' id='truename' name='truename' />");
						out.println("<label for='mobile'>请输入手机:</label><input required='required' type='text' id='mobile' name='mobile' />");
					}
				%>
				<textarea style="resize:none;height: 200px" required="required" placeholder="请输入您参与活动征集的口号 " name="content" id="content"></textarea>
				<input type="submit" value="提交"/> 
				
			</form>
		</div>
	</div>
	
</body>
</html>

