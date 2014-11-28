<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String keyword = (String) request.getAttribute("keyword");
	String unitname = (String) request.getAttribute("unitname");
	String deptname = (String) request.getAttribute("deptname");
	String psnname = (String) request.getAttribute("psnname");
	
	String openid = (String) request.getSession().getAttribute("openid");
%>
<!DOCTYPE html>
<html>
<head>
<title>发送消息</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>
<script type="text/javascript">
	var openid="<%=openid%>";
	var keyword_ser = "<%=keyword%>";
	var unitname = "<%=unitname%>";
	var deptname = "<%=deptname%>";
	var psnname = "<%=psnname%>";
	var keywords = new Array();
	keywords = keyword_ser.split(",");
	
	function send(){
		var keyword = $("#txt_kw")[0].value;
		var nickname = $("#txt_nn")[0].value;
		var message = $("#ta_msg")[0].value;
		
		if($.inArray(keyword, keywords) < 0){
			//alert("您输入的通行证不正确，无法发送信息");
			//confirm("您输入的通行证不正确，无法发送信息");
			showInfo("您输入的通行证不正确，无法发送信息");
			return;
		}
		
		if(!nickname || Trim(nickname)==""){
			//alert("请输入昵称，注意用词");
			showInfo("请输入昵称，注意用词");
			return;
		}
		
		if(!message || Trim(message)=="" || message.length > 144){
			//alert("请输入信息，并且字数不能超过144个字符");
			showInfo("请输入信息，并且字数不能超过144个字符");
			return;
		}
		showLoader();
		$.post("<%=basePath%>send.html", {
			openid : openid,
			nickname : nickname,
			unitname : unitname,
			deptname : deptname,
			psnname  : psnname,
			message : message,
			keyword : keyword
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			hideLoader();
			if(window.localStorage){
				window.localStorage["txt_nn"] = $('#txt_nn')[0].value ;
			}
			eval("var rs = " + data);
			if(rs.code==0){
				showInfo(rs.msg);
				clear();
			}else{
				showInfo(rs.msg);
			}
		});
	}
	
	function clear(){
		$("#ta_msg")[0].value = "";
	}
	
	function Trim(string){
		return string.replace(/( +)$/g,"").replace(/^( +)/g,"");
	}
	
	function showInfo(text){
		$('#infoText').html(text);
		$('#infoBox').popup('open');
	}
	
	//$.mobile.pushStateEnabled=false;
	function init(){
		//history.replaceState(null, "搜索", "/sendMsg.html");
		document.addEventListener('WeixinJSBridgeReady',
				function onBridgeReady() {
					WeixinJSBridge.call('hideOptionMenu');
				});

		document.addEventListener('WeixinJSBridgeReady',
				function onBridgeReady() {
					WeixinJSBridge.call('hideToolbar');
				});
		
		if(window.localStorage){
			if(window.localStorage["txt_nn"]){
				$('#txt_nn')[0].value = window.localStorage["txt_nn"];
			}
		}
	}
	
	//显示加载器  
	function showLoader() {
		//显示加载器.for jQuery Mobile 1.2.0  
		$.mobile.loading('show', {
			text : '加载中，如长时间无反应，请返回刷新', //加载器中显示的文字  
			textVisible : true, //是否显示文字  
			theme : 'e', //加载器主题样式a-e  
			textonly : false, //是否只显示文字  
			html : "" //要显示的html内容，如图片等  
		});
	}

	//隐藏加载器.for jQuery Mobile 1.2.0  
	function hideLoader() {
		//隐藏加载器  
		$.mobile.loading('hide');
	}
	
	function gotoMessagePage(){
		location.replace("<%=basePath%>messageBar.html?openid=<%=openid%>");
	}
</script>
</head>
<body onload="init()">
	<div data-role="page" data-theme="e">
		<div data-role="header" data-position="fixed">
			<h1>发送消息</h1>
			<a href="javascript:gotoMessagePage()" class="ui-btn-right" data-role="button" data-inline="true" data-icon="info" data-theme="e">大家说...</a>
			<!-- <a href="javascript:send()" data-icon="check" data-theme="b" class="ui-btn-right">发送</a> -->
		</div>
		<!-- /header -->
		
		<div data-role="popup" id="infoBox" data-theme="a">
			<p id="infoText">消息</p>
		</div>

		<div data-role="content">
			<form>
				<label for="txt_kw">请输入通行证:</label>
     			<input type="text" name="txt_kw" id="txt_kw" placeholder="请输入当前的关键词" value="">
     			<label for="txt_nn">请输入您的昵称:</label>
     			<input type="text" name="txt_nn" id="txt_nn" value="">
				<label for="ta_msg">说几句吧:</label>
				<textarea style="resize:none;height: 200px" required="required" cols="12" rows="12" maxlength='144' placeholder="亲，请对国贸说几句话吧" name="ta_msg" id="ta_msg"></textarea>
			</form>
			
			<button onclick="send()">发送</button>

		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>