﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String modid = (String) request.getAttribute("modid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>公司概况</title>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,user-scalable=no" />
</head>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

<body id="mod0">
	<div id="mod1" class="wp onshow">
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">公司概况</font></h3>
        <div class="main">
        	<img src="<%=basePath %>/image/dsz.jpg" style="width:50%;">
            <div class="box" style="text-align: left;margin-top:30%;">
		　 		<p>&nbsp;&nbsp;&nbsp;&nbsp;厦门国贸集团股份有限公司是一家国有控股上市公司。始创于1980年，1996年在上海证券交易所上市。经过三十多年发展，已形成供应链管理、房地产经营和金融服务三大核心业务。<br>&nbsp;&nbsp;&nbsp;&nbsp;公司秉承“持续创造新价值”的使命，“创新、恒信、成长”的核心价值观，“激扬无限、行稳致高”的企业精神，“大舞台、大学校、大家庭”的文化理念，锐意进取、不断创新，全力将企业打造成为令人尊敬的优秀综合服务商。</p>
            </div>
        </div>
    </div>
    <div id="mod2" class="wp nodis">
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">组织架构</font></h3>
        <div class="main" style="margin-top:20%;">
        	<a href="<%=basePath %>imageView.html?src=image/location-zzjg2.png"><img src="<%=basePath %>/image/location-zzjg1.png" style="width:100%;"></a>
        </div>
    </div>
    
    <div id="mod3" class="wp nodis">
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">区域公司</font></h3>
        <div class="main">
        	<a href="<%=basePath %>imageView.html?src=image/location-qygs1.png"><img src="<%=basePath %>/image/location-qygs-map.png" style="width:100%;"></img></a>
            <div class="box" style="text-align: left; font-size: 13px;line-height: 25px;margin-top:30%;">
            	<!--
            	<p>
	            	<font color="blue">上海启润实业有限公司</font><br />
			　 		上海市浦东新区张杨路620号中融恒瑞国际大厦<br />
					<font color="blue">广州启润实业有限公司</font><br />
			　 		广州市天河区体育西路191号中石化大厦B塔31楼<br />
					<font color="blue">天津启润投资有限公司</font><br />
			　 		天津市河西区友谊路与平江道交口东南侧大安大厦A座1301室<br />
					<font color="blue">成都启润投资有限公司</font><br />
			　 		成都市锦江区东御街57号16层C区<br />
					<font color="blue">宝达投资（香港）有限公司</font><br />
			　 		香港上环干诺道中200号信德中心西翼3202室<br />
					<font color="blue">台湾宝达兴业有限公司（台湾办事处）</font><br />
			　 		台北市松山区东兴路8号统一证券大厦11楼<br />
					<font color="blue">国贸新加坡有限公司</font><br />
			　 		新加坡珠烈街65号华侨银行中心37层01/02<br />
				</p>
				-->
				<p>
	            	<font color="blue">上海启润实业有限公司</font><br />
					<font color="blue">广州启润实业有限公司</font><br />
					<font color="blue">天津启润投资有限公司</font><br />
					<font color="blue">成都启润投资有限公司</font><br />
					<font color="blue">宝达投资（香港）有限公司</font><br />
					<font color="blue">台湾宝达兴业有限公司（台湾办事处）</font><br />
					<font color="blue">国贸新加坡有限公司</font><br />
				</p>
				
				<a href="<%=basePath %>imageView.html?src=image/location-qygs1.png"><font color="red"><u>显示更多详情</u></font></a>
            </div>
        </div>
    </div>
    <div id="mod4" class="wp nodis">
        <h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">联系方式</font></h3>
        <div class="main">
        	<img src="<%=basePath %>/image/location-5.jpg" style="width:100%;">
            <div class="box" style="text-align: left;margin-top:30%">
		　 		<p>
					<font color="blue"><b>地　　址:</b></font>&nbsp;&nbsp;厦门市湖滨南路国贸大厦8-18层 <br/>
					<font color="blue"><b>电　　话:</b></font><a href="tel:86-592-5161888">&nbsp;&nbsp;86-592-5161888</a> <br/>
					<font color="blue"><b>传　　真:</b></font>&nbsp;&nbsp;86-592-5160280 <br/>
					<font color="blue"><b>电子邮件:</b></font>&nbsp;&nbsp;itgchina@itg.com.cn <br/>
					<font color="blue"><b>邮政编码:</b></font>&nbsp;&nbsp;361004 <br/>
					<font color="blue"><b>公司网址:</b></font>&nbsp;&nbsp;<u><a href="http://www.itg.com.cn">www.itg.com.cn</a></u><br/>
					<font color="blue"><b>官方微信:</b></font>&nbsp;&nbsp;itg_china <br/>
				</p>
            </div>
        </div>
    </div>
    <div id="prev" style="display:none"></div>
    <div id="next"></div>
</body>
<script src="<%=basePath%>js/zp/touch_demo7.js"></script>
<script type="text/javascript">
	var noTouch = false;
	$(document).ready(function(){
		
	});
	$('.box').each(function(){$(this).css({marginTop:'-'+$(this).outerHeight()/2+'px'});});
</script>
</html>
