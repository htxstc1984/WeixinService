<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.itg.weixin.vo.NewsVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>国贸新闻</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script src="<%=basePath%>js/comm.js"></script>

<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var pageNum = 1;
	var newsType = 2;
	var activeBtn = "btn_xw";
	function showResult() {
		$.post("<%=basePath%>getPageNews.html", {
			pageNum : pageNum,
			newsType : newsType
		}, function(data, status) {
			eval("var newsList = " + data);
			var html = "";
			for ( var i = 0; i < newsList.length; i++) {
				html = html + "<li>";
				
				html = html + "<a href=\"" + basePath
						+ "getNew.html?infoId=" + newsList[i].infoId + "\">";
				//if(newsList[i].content!=""){
				//	html = html + "<img src='"+newsList[i].content+"' />";
				//}
				html = html + "<h4>" + newsList[i].title + "</h4>";
				html = html + "<p><font color='red'>发布于:"
						+ newsList[i].infoDate.substring(0,10) + "</font></p>";
				html = html + "</a></li>";
			}
			$("#newsView").append(html);
			$("#newsView").listview('refresh')
		});

	}
	
	function changeNewType(type,id)
	{
		if(type!=newsType){
			newsType = type;
			pageNum = 1;
			activeBtn = id;
			$("#newsView").empty();
			if(newsType==4){
				$("#moreDiv").hide();
				$("#div_guide").show();
				return;
			}
			$("#div_guide").hide();
			$("#moreDiv").show();
			showResult();
		}
		
	}
	$(function() {
		$("#btnMore").click(function() {
			loadData();
		});
	});
	
	function init(){
		$("#div_guide").hide();
		$(document).delegate('[data-role="navbar"] a', 'click', function () {
		    $(this).addClass('ui-btn-active');
		    $('#'+activeBtn).removeClass('ui-btn-active');
		    changeNewType(this.type,this.id);
		    return false;//stop default behavior of link
		});
		showResult();
	}

	function loadData(){
		pageNum = pageNum + 1;
		showResult();
	}
</script>
</head>
<body onload="init()">
	<div data-role="page" data-theme="e">
		<div data-role="header">  
			<div data-role="navbar" id="navbar">
			　　 <ul>
				<li><a id="btn_xw" type=2 class='ui-btn-active'>公司新闻</a></li>
				<li><a id="btn_mt" type=3 >媒体观察</a></li>
				
			   </ul>
			</div><!-- /navbar -->
		</div>  

		<div data-role="content">
			<ul data-role="listview" data-split-icon="gear" id="newsView">
			</ul>
			<p>&nbsp;</p>
			<!--  
			<div id="div_guide">
				<h5>按以下步骤获取历史推送的图文消息</h5>
				<div data-role='collapsible' data-collapsed='false'>
					<h3>第一步</h3>
					<img style="width: 100%;height: 100%" src='<%=basePath%>image/gud1_1.jpg'>
				</div>
				<div data-role='collapsible' data-collapsed='false'>
					<h3>第二步</h3>
					<img style="width: 100%;height: 100%" src='<%=basePath%>image/gud2_1.jpg'>
				</div>
				<div data-role='collapsible' data-collapsed='false'>
					<h3>第三步</h3>
					<img style="width: 100%;height: 100%" src='<%=basePath%>image/gud3_1.jpg'>
				</div>
			</div>
			-->
			<div id="moreDiv">
			  <button id="btnMore" data-icon="refresh">显示更多</button>
			</div>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
</body>
</html>