<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String openid = (String) request.getSession()
			.getAttribute("openid");
%>
<!DOCTYPE html>
<html>
<head>
<title>元宵互动</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="<%=basePath%>css/msgbar.css" rel="stylesheet">

<style type="text/css">
div .ui-grid-a .ui-block-a {
	width: 75%
}

div .ui-grid-a .ui-block-b {
	width: 25%
}

.getstart {
	margin: 5px 0 0 5px;
	width: 98%;
}

blockquote p {
	font: 12px/1.6 arial, helvetica, sans-serif；
}

body,div,p,blockquote {
	margin: 0;
	padding: 0
}

.icon {
	width: 50px;
	Height: 50px;
	background: url(/images/icon.png)
}

icon .count {
	background: #ff0000;
	position: absolute;
	top: -8px;
	Right: -8px;
	min-width: 14px;
	height: 14px;
	color: #ffffff;
	border-radius: 50%;
	padding: 2px;
	text-align: center;
	font-size: 12px;
}
</style>

<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>
<script type="text/javascript">
	var fromId = 0;
	var minId = 0;
	var maxid = 0;
	var isInited = true;
	var basePath = "<%=basePath%>";
	var openid = "<%=openid%>";

	function setGood(id, goods) {
		showLoader();
		$
				.post(
						basePath + "setGood.html",
						{
							apprId : id,
							openid : openid
						},
						function(data, status) {
							if (status != "success") {
								return;
							}
							hideLoader();
							eval("var rs = " + data);
							if (rs.code == 0) {
								$("#btn_" + id).addClass("ui-disabled");

								$("#btn_" + id)
										.html(
												"<span class=\"ui-btn-inner\"><span class=\"ui-btn-text\">"
														+ (goods + 1)
														+ "人赞</span>"
														+ "<span class=\"ui-icon ui-icon-check\">&nbsp;</span></span>");
							} else {
								showInfo(rs.msg);
							}

						});
	}

	function closePanel() {
		$("#mypanel").panel("close");
	}

	function getBlockHtml(obj) {
		var html = "";
		html = html + "<div class=\"status-item\" id=\"block_"+obj.id+"\">";
		html = html + "<div class=\"mod\">";
		html = html + "<div class=\"bd layout-2\">";
		html = html + "<p class=\"text\">" + obj.nickname + " 说【" + obj.keyword
				+ "】：</p>";
		html = html + "<blockquote>";
		html = html + "<p>";
		html = html + "<font size='4' color='brown' style='vertical-align: top;'><strong>“</strong></font><font size=\"3\">" + obj.message + "<font size='4' color='brown' style='vertical-align: bottom;'><strong>”</strong></font></font>";
		html = html + "</p>";
		html = html + "</blockquote>";
		html = html + "</div>";
		html = html
				+ "<div data-role=\"controlgroup\"  id=\"ctgrp_"
		+ obj.id
		+ "\" data-type=\"horizontal\" data-mini=\"true\" style=\"text-align: right;\">";
		if (obj.hasgood > 0) {
			html = html
					+ "<a href=\"#\" id=\"btn_"
			+ obj.id
			+ "\" data-role=\"button\" class=\"ui-disabled\" data-icon=\"check\" data-theme=\"c\"><span id= class=\"count\">"
					+ obj.good_count + "</span>人赞</a>";
		} else {
			html = html
					+ "<a href=\"javascript:setGood('"
					+ obj.id
					+ "',"
					+ obj.good_count
					+ ")\" id=\"btn_"
					+ obj.id
					+ "\" data-role=\"button\" data-icon=\"star\" data-theme=\"c\">赞<span id= class=\"count\">"
					+ obj.good_count + "</span></a>";
		}

		html = html + "</div>";
		html = html + "</div>";
		html = html + "</div>";
		return html;
	}

	function getApprovedMsg(isInit) {
		if(!isInit){
			showLoader();
		}
		$.post(basePath + "getApprovedMsg.html", {
			fromId : fromId,
			openid : openid
		}, function(data, status) {
			if (status != "success") {
				return;
			}
			if(!isInit){
				hideLoader();
			}
			eval("var msgList = " + data);
			if (msgList == "") {
				return;
			}
			if ($(".getstart div").length == 0) {
				$(".getstart").append("<div></div>");
			}
			for ( var i = 0; i < msgList.length; i++) {
				fromId = msgList[i].id;
				if(i==0){
					minId = msgList[i].id;
				}
				var html = getBlockHtml(msgList[i]);
				$(".getstart div:eq(0)").before(html);
				$("#btn_" + msgList[i].id).button();
				$("#ctgrp_" + msgList[i].id).controlgroup();
			}
		});
	}

	function getNewMsg() {
		fromId = 0;
		minId = 0;
		$(".getstart div").remove();
		getApprovedMsg(false);
	}

	function getApprovedMsgByGood() {
		fromId = 0;
		minId = 0;
		$(".getstart div").remove();
		showLoader();
		$.post(basePath + "getApprovedMsgByGood.html", {
			openid : openid
		}, function(data, status) {
			if (status != "success") {
				return;
			}
			hideLoader();
			eval("var msgList = " + data);
			if (msgList == "") {
				return;
			}

			if (msgList.length > 0) {

				for ( var i = 0; i < msgList.length; i++) {
					fromId = msgList[i].id;
					var html = getBlockHtml(msgList[i]);
					$(".getstart").append(html);
					$("#btn_" + msgList[i].id).button();
					$("#ctgrp_" + msgList[i].id).controlgroup();
				}
			}
		});
	}

	function getApprovedMsgBySelf() {
		fromId = 0;
		minId = 0;
		$(".getstart div").remove();
		showLoader();
		$.post(basePath + "getApprovedMsgBySelf.html", {
			openid : openid
		}, function(data, status) {
			if (status != "success") {
				return;
			}
			hideLoader();
			eval("var msgList = " + data);
			if (msgList == "") {
				return;
			}
			
			if (msgList.length > 0) {
				for ( var i = 0; i < msgList.length; i++) {
					fromId = msgList[i].id;
					var html = getBlockHtml(msgList[i]);
					$(".getstart").append(html);
					$("#btn_" + msgList[i].id).button();
					$("#ctgrp_" + msgList[i].id).controlgroup();
				}
			}
		});
	}
	
	function getApprovedMsgByPre() {
		showLoader();
		$.post(basePath + "getApprovedMsgPre.html", {
			openid : openid,
			ltId   : minId
		}, function(data, status) {
			if (status != "success") {
				return;
			}
			hideLoader();
			eval("var msgList = " + data);
			if (msgList == "") {
				return;
			}

			if (msgList.length > 0) {
				for ( var i = 0; i < msgList.length; i++) {
					minId = msgList[i].id;
					var html = getBlockHtml(msgList[i]);
					$(".getstart").append(html);
					$("#btn_" + msgList[i].id).button();
					$("#ctgrp_" + msgList[i].id).controlgroup();
				}
			}
		});
	}
	
	function refreshByType(type) {
		$('#popupMenu').popup('close');
		var text = "";
		if (type == 1) {
			text = "最新消息";
			getNewMsg();
			$("#moreDiv").show();
		}
		if (type == 2) {
			text = "最多赞消息";
			getApprovedMsgByGood();
			$("#moreDiv").hide();
		}
		if (type == 3) {
			text = "我的消息";
			getApprovedMsgBySelf();
			$("#moreDiv").hide();
		}
		if (type != 0) {
			$("#headerText").html(text);
		}
	}
	
	//$.mobile.pushStateEnabled=false;
	function init(){
		//history.replaceState(null, "元宵互动", "/msgBar.html");
		document.addEventListener('WeixinJSBridgeReady',
				function onBridgeReady() {
					WeixinJSBridge.call('hideOptionMenu');
				});

		document.addEventListener('WeixinJSBridgeReady',
				function onBridgeReady() {
					WeixinJSBridge.call('hideToolbar');
				});
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
	
	function gotoSendPage(){
		location.replace("<%=basePath%>getSendPage.html?openid=<%=openid%>");
	}
	
	function showInfo(text){
		$('#infoText').html(text);
		$('#infoBox').popup('open');
	}

	getApprovedMsg(true);

</script>

</head>
<body onload="init()">
	<div id="page" data-role="page" data-theme="e">
		<div data-role="header" data-position="fixed">
			<h1 id="headerText">最新消息</h1>
			<a href="#popupMenu" class="ui-btn-left" data-rel="popup" data-role="button" data-inline="true" data-icon="refresh" data-theme="e">刷新消息</a>
			<a href="javascript:gotoSendPage()" class="ui-btn-right" data-role="button" data-inline="true" data-icon="info" data-theme="e">我要说...</a>
		</div>
		<!-- /header -->
		
		<div data-role="popup" id="infoBox" data-theme="a">
			<p id="infoText">消息</p><font style="vertical-align: bottom;"></font>
		</div>

		<div data-role="popup" id="popupMenu" data-theme="d" style="position:fixed;left:10px;top: 40px">
		        <ul data-role="listview" data-inset="true" style="min-width:210px;" data-theme="d">
		            <!-- <li data-role="divider" data-theme="e">请选择以下操作</li> -->
		            <li><a href="javascript:refreshByType(1)">最新消息</a></li>
		            <li><a href="javascript:refreshByType(2)">最多赞消息(前50)</a></li>
		            <li><a href="javascript:refreshByType(3)">我的消息</a></li>
		        </ul>
		</div>

		<div data-role="content" class="content" >
			<div class="getstart" id="msgList">
			</div>
			
			<div id="moreDiv">
				<button id="btnMore" onclick="getApprovedMsgByPre()" data-icon="refresh">显示更多</button>
			</div>
		</div>
	
		
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>