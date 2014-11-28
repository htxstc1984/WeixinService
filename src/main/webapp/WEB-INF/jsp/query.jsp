<%@page import="com.sun.xml.bind.v2.model.nav.Navigator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cn.com.webxml.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String openid = (String) request.getSession().getAttribute("openid");
	String psnname = (String) request.getSession().getAttribute("psnname");
%>
<!DOCTYPE html>
<html>
<head>
<title>搜索</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv=”Expires” CONTENT=”0″>
<meta http-equiv=”Cache-Control” CONTENT=”no-cache”>
<meta http-equiv=”Pragma” CONTENT=”no-cache”>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>
</head>
<script type="text/javascript">
	//var result = jQuery.parseJSON("{\"code\":0,\"msg\":\"success\",\"psn\":[{\"unitname\":\"厦门国贸集团本部\",\"deptname\":\"信息技术部\",\"psnname\":\"陈晨\",\"officephone\":\"5897501\",\"mobile\":\"13799785598或18650816689\",\"email\":\"chenc@itg.com.cn\",\"yglb\":\"\"},{\"unitname\":\"大宗贸易板块本部\",\"deptname\":\"铁矿中心\",\"psnname\":\"陈晨\",\"officephone\":\"15960238190\",\"mobile\":\"15960238190\",\"email\":\"chenchen@itg.com.cn\",\"yglb\":\"\"}]}");
	function showResult() {
		$.post("<%=basePath%>getQueryRs.html", {
			openid : "<%=openid%>",
							cond : $("#cond")[0].value
						},
						function(data, status) {
							//alert("Data: " + data + "\nStatus: " + status);
							//var result = jQuery.parseJSON(data);
							eval("var result = " + data);
							if (result.code == 0) {
								var psn = result.psn;
								var html = "";
								for ( var i = 0; i < psn.length; i++) {
									html = html
											+ "<div type=\"rs\" id=\"resultShow\" data-role=\"collapsible\"><h3>"
											+ psn[i].psnname + "</h3>";
									html = html
											+ "<p><b><font color='blue'>公司:</font></b>"
											+ psn[i].unitname + "</p>";
									html = html
											+ "<p><b><font color='blue'>部门:</font></b>"
											+ psn[i].deptname + "</p>";
									if (psn[i].yglb != "0001V410000000001OPY") {

										html = html
												+ "<p><b><font color='blue'>座机:</font></b><a href='tel:"+psn[i].officephone+"'>"
												+ psn[i].officephone
												+ "</a></p>";
										html = html
												+ "<p><b><font color='blue'>手机:</font></b><a href='tel:"+psn[i].mobile+"'>"
												+ psn[i].mobile + "</a></p>";
									}

									html = html
											+ "<p><b><font color='blue'>邮箱:</font></b>"
											+ psn[i].email + "</p></div>";
								}
								$("div[type='rs']").remove();
								$("div[type='errMsg']").remove();
								$("#cnt_cx").append(html).trigger("create");
							} else {
								var html = "";
								html = html
										+ "<div type='errMsg' data-role=\"content\"><p style=\"color: #999\">"
										+ result.msg + "</p></div>";
								if ($("div[type='rs']")) {
									$("div[type='rs']").remove();
								}
								$("div[type='errMsg']").remove();
								$("#cnt_cx").append(html).trigger("create");
							}
						});

	}
	
	$.mobile.pushStateEnabled=false;
	var activeBtn = "btn_cx";
	function onload(){
		var state = { //这里可以是你想给浏览器的一个State对象，为后面的StateEvent做准备。
						title : "搜索",
						url : "query.html#mp.weixin.qq.com"};
		debugger;
		history.replaceState(null, "搜索", "/query.html#mp.weixin.qq.com");
		
		
					
		$(document).delegate('[data-role="navbar"] a', 'click', function () {
			if(activeBtn != this.id){
				$(this).addClass('ui-btn-active');
			    $('#'+activeBtn).removeClass('ui-btn-active');
			    $("div[ctl='"+activeBtn+"']").hide();
			    $("div[ctl='"+this.id+"']").show();
			    activeBtn = this.id;
			    return false;//stop default behavior of link
			}
		});
	}
	
	function removeAuth(){
		$("#popcom").popup();
		$("#popcom").popup("open");
	}
	
	function confirmRemove(){
		$.post("<%=basePath%>confirmRemoveAuth.html", {
		}, function(data, status) {
			eval("var rs = " + data);
			if(rs.code==0){
				window.location.replace("<%=basePath%>query.html");
			}
			else{
				var html = "";
				html = html
						+ "<div type='errMsg' data-role=\"content\"><p style=\"color: #999\">"
						+ rs.msg + "</p></div>";
				$("div[type='errMsg']").remove();
				$("#cnt_sz").append(html).trigger("create");
			}
		});
	}
</script>
<style type="text/css">
div#findBar.ui-grid-a .ui-block-a {
	width: 75%
}

div#findBar.ui-grid-a .ui-block-b {
	width: 25%
}
</style>
<body onload="onload()">
	<!--  onload="check()" -->
	<div id="container" data-role="page" data-theme="e">
		<div data-role="header">
			<h3 style="vertical-align: middle;">欢迎您，<%=psnname%></h3><div data-role="navbar" id="navbar"><ul><li><a id="btn_cx" type=2 class='ui-btn-active'>查询内部联系方式</a></li><li><a id="btn_sz" type=3>个人设置</a></li></ul></div>
		</div>
		<!-- /header -->

		<div data-role="content" id="cnt_cx" ctl="btn_cx">
			<label for="search-basic">请输入搜索条件:</label>
			<div id="findBar" class="ui-grid-a">
				<div class="ui-block-a">
					<input id="cond" placeholder="" data-theme="e" type="search"
						name="sw" id="sw" value="" data-mini="true" />
				</div>
				<div class="ui-block-b">
					<button id="doFind" type="button" data-theme="b" data-icon="search"
						data-mini="true" onclick="showResult()">查询</button>
				</div>
			</div>

		</div>
		
		<div data-role="content" id="cnt_sz" hidden="true" ctl="btn_sz">
			<a href="#popupDialog" data-role="button" data-position-to="window" data-transition="pop" data-rel="popup" id="btn_remove">解除认证</a>
			<div data-role="popup" id="popupDialog" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">
			    <div data-role="header" data-theme="a">
			    <h1>确认</h1>
			    </div>
			    <div role="main" class="ui-content">
			        <h3 class="ui-title">请确认是否解除认证？</h3>
			        <a href="#" data-role="button" class="ui-btn-inline ui-btn-b" data-rel="back">取消</a>
			        <a href="#" onclick="confirmRemove()" data-role="button" class="ui-btn-inline ui-btn-b" data-rel="back" data-transition="flow">确定</a>
			    </div>
			</div>
		</div>
		<!-- /content -->
		
		<div id="popmsg" data-role="popup">
			<label><h3 id="h_msg">aaa</h3></label>
		</div><!--弹出层,默认不显示-->
		
	</div>
	<!-- /page -->
</body>
</html>