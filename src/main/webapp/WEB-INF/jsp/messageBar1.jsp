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
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>
<script type="text/javascript">
	var fromId = 0;
	var maxid = 0;
	var isInited = false;

	function setGood(id){
		alert($("#eeerrr").html());
		$("#btn_"+id).addClass("ui-disabled");
		$("#btn_"+id).html("<span class=\"ui-btn-inner\"><span class=\"ui-btn-text\">12人赞</span><span class=\"ui-icon ui-icon-check\">&nbsp;</span></span>");
	}
	
	function getApprovedMsg() {
		$.post("<%=basePath%>getApprovedMsg.html",
						{
							fromId : fromId
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
										+ "<div data-role=\"collapsible\" id=\"block_"+msgList[i].id+"\" data-theme=\"e\" data-content-theme=\"d\">";
								html = html + "<h3>"+msgList[i].message+"</h3>";
								html = html + "<div class=\"ui-grid-a\">";
								html = html + "<div class=\"ui-block-a\">";
								html = html
										+ "<label><font size=\"3\" style=\"font-weight: bold;\">发言者 : "
										+ msgList[i].nickname
										+ "</font><br/><font size=\"3\" style=\"font-weight: bold;\">内&nbsp;&nbsp;&nbsp;&nbsp;容 : "
										+ msgList[i].message
										+ "</font></label>";
								html = html + "</div>";
								html = html + "<div class=\"ui-block-b\">";
								html = html
										+ "<a href=\"javascript:setGood('"
										+ msgList[i].id
										+ "')\" id=\"btn_"
										+ msgList[i].id
										+ "\" data-mini=\"true\" data-role=\"button\" data-icon=\"star\" data-theme=\"b\" data-iconshadow=\"false\" data-inline=\"true\">赞</a>";
								html = html + "</div>";
								html = html + "</div>";
								html = html + "</div>";
								
								$(".content div:eq(0)").before(html);
								$("#block_"+msgList[i].id).collapsible();
								$("#btn_"+msgList[i].id).button();
								//$("#colset").append(html);
								
							}
							//$("#colset").collapsibleset( "refresh" ); 
							//$("#btn18").button();
						});
	}
	
	//getApprovedMsg();
</script>
<style type="text/css">
div .ui-grid-a .ui-block-a {
	width: 75%
}

div .ui-grid-a .ui-block-b {
	width: 25%
}
</style>
</head>
<body>
	<div data-role="page" data-theme="e">
		<div data-role="header">
			<h1>aaa</h1>
		</div>
		<!-- /header -->

		<div data-role="content" class="content" id="eeerrr">

				<div class="ui-collapsible-content ui-body-d" aria-hidden="false">
					
					<div class="ui-grid-a">
						<div class="ui-block-a">
							<label><font size="3" style="font-weight: bold;">发言者
									: htx</font><br />
							<font size="3" style="font-weight: bold;">内&nbsp;&nbsp;&nbsp;&nbsp;容
									: sssss</font></label>
						</div>
						<div class="ui-block-b" id="asd">
							<a href="javascript:setGood('18')" id="btn_18" data-mini="true" data-role="button" data-icon="star" data-theme="b" data-iconshadow="false" data-inline="true">赞</a>
						</div>
					</div>
				</div>
				<br/>
				<div class="ui-collapsible-content ui-body-d" aria-hidden="false">
					
					<div class="ui-grid-a">
						<div class="ui-block-a">
							<label><font size="3" style="font-weight: bold;">发言者
									: htx</font><br />
							<font size="3" style="font-weight: bold;">内&nbsp;&nbsp;&nbsp;&nbsp;容
									: sssss</font></label>
						</div>
						<div class="ui-block-b" id="asd">
							<a href="javascript:setGood('18')" id="btn_18" data-mini="true" data-role="button" data-icon="star" data-theme="b" data-iconshadow="false" data-inline="true">赞</a>
						</div>
					</div>
				</div>
		</div>

		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>