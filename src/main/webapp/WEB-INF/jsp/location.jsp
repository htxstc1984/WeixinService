<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html> 
<html> 
	<head> 
		<title>厦门国贸概况</title> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<!-- Mobile Devices Support @begin -->
		<meta content="application/xhtml+xml;charset=UTF-8"
			http-equiv="Content-Type">
		<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
		<meta content="no-cache" http-equiv="pragma">
		<meta content="0" http-equiv="expires">
		<meta content="telephone=no, address=no" name="format-detection">
		<meta name="viewport"
			content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<!-- apple devices fullscreen -->
		<meta name="apple-mobile-web-app-status-bar-style"
			content="black-translucent" />
		<!-- Mobile Devices Support @end -->
		
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1">  -->
	
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
	    <style>
		.ui-font-gray{color:#999; font-size:12px;}
		</style>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=24333c34186641da7cc866949d19f234"></script>
	</head> 
	<script type="text/javascript">
		function initialize()
		{
			// 百度地图API功能
			var map = new BMap.Map("mapContainer");            // 创建Map实例
			var point = new BMap.Point(118.125181, 24.481354);    // 创建点坐标
			map.centerAndZoom(point,18);                     // 初始化地图,设置中心点坐标和地图级别。
			map.enableScrollWheelZoom();                            //启用滚轮放大缩小
			map.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));     //2D图，卫星图
			map.addControl(new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT}));    //左上角，默认地图控件
			map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
			
			var point2 = new BMap.Point(118.125181, 24.481354);    // 创建点坐标
			var marker = new BMap.Marker(point2);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		}

  	</script>
<body  onload="initialize()"> 

<div data-role="page" data-theme="e">

	<div data-role="header">
        <h1>厦门国贸概况</h1>
	</div><!-- /头部 -->
	
	<div data-role="content">	
		<div data-role="collapsible" data-collapsed="false">
		　 <h3>公司概况</h3>
		  <img src="<%=basePath %>/image/dsz.jpg">
		　 <p>厦门国贸集团股份有限公司是一家国有控股上市公司。始创于1980年，1996年在上海证券交易所上市。经过三十多年发展，已形成供应链管理、房地产经营和金融服务三大核心业务。<br/> &nbsp;&nbsp;&nbsp;&nbsp;公司秉承“持续创造新价值”的使命，“创新、恒信、成长”的核心价值观，“激扬无限、行稳致高”的企业精神，“大舞台、大学校、大家庭”的文化理念，锐意进取、不断创新，全力将企业打造成为令人尊敬的优秀综合服务商。</p>
		</div>
		<div data-role="collapsible" data-collapsed="false">
		　 <h3>联系方式</h3>
		　 	<p>
				<font color="blue"><b>地　　址:</b></font>&nbsp;&nbsp;中国厦门市湖滨南路国贸大厦18层 <br/>
				<font color="blue"><b>电　　话:</b></font><a href="tel:86-592-5161888">&nbsp;&nbsp;86-592-5161888</a> <br/>
				<font color="blue"><b>传　　真:</b></font>&nbsp;&nbsp;86-592-5160280 <br/>
				<font color="blue"><b>电子邮件:</b></font>&nbsp;&nbsp;itgchina@itg.com.cn <br/>
				<font color="blue"><b>邮政编码:</b></font>&nbsp;&nbsp;361004 <br/>
			</p>
		</div>
		<div data-role="collapsible" data-collapsed="false">
		　 <h3>地理位置</h3>
		　 <div id="mapContainer" style="height:300px; width:100%"></div>
		</div>
	</div><!-- /内容 -->
	
	<div data-role="footer">
		<h4>by itg</h4>
	</div><!-- /底部 --></div><!-- /页面 -->
	
</body>
</html>