<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>经营业务</title>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,user-scalable=no" />
</head>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

<body id="mod0">
    <div id="mod1" class="wp onshow">
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">供应链管理</font></h3>
        <div class="main">
        	<img src="<%=basePath %>/image/location-2.jpg" style="width:100%;">
            <div class="box" style="text-align: left;;margin-top:30%">
		　 		<p>&nbsp;&nbsp;&nbsp;&nbsp;供应链管理涵盖贸易、物流等领域，连年进入中国进出口额最大的500家企业行列，为中国物流百强企业、全国先进物流企业、中国汽车流通行业经销商集团百强。</p>
            </div>
        </div>
    </div>
    <div id="mod2" class="wp nodis">
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">房地产经营</font></h3>
        <div class="main">
        	<img src="<%=basePath %>/image/location-3.jpg" style="width:100%;">
            <div class="box" style="text-align: left;margin-top:30%;">
		　 		<p>&nbsp;&nbsp;&nbsp;&nbsp;房地产经营涵盖住宅与商业地产，为中国房地产100强和影响中国房地产品牌企业。公司长期致力于高端楼盘的开发建设，形成了以厦门为中心，辐射福建、安徽、江西、上海等地的区域布局。</p>
            </div>
        </div>
    </div>
    <div id="mod3" class="wp nodis">
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">金融服务</font></h3>
        <div class="main">
        	<img src="<%=basePath %>/image/location-4.jpg" style="width:100%;">
            <div class="box" style="text-align: left;margin-top:30%;">
		　 		<p>&nbsp;&nbsp;&nbsp;&nbsp;金融服务是公司新兴业务。全资的国贸期货为国内大型期货经纪公司，拥有金融期货经纪、结算双资质以及资产管理牌照。担保公司具有金融性担保资质，运用多元化融资手段为企业及个人发展提供专业化的融资服务。典当公司是专门发放质押贷款的边缘性金融机构，是以货币借贷为主、商品销售为辅的市场中介组织。此外，公司还拥有三钢闽光、海南橡胶等优质金融资产。</p>
            </div>
        </div>
    </div>
    <!--  
	<div id="mod4" class="wp nodis" >
		<h3 style="margin-top:10%;margin-left:40px;text-align: left;display: inline-block;line-height:40px;width: 100%;"><font style="font-size:22px;color: red;text-shadow: black;">区域公司业务</font></h3>
        <div class="main">
        	<div style="text-align: left;font-size:5px">
        		<p>
	            	<font color="blue">上海启润实业有限公司</font><br />
			　 		主营包括钢材、纸张、棉纺织品、汽车、农产品等进出口及国内贸易、期货代理业务及国际货运代理业务等<br />
					<font color="blue">广州启润实业有限公司</font><br />
			　 		主营钢材、纸张、农产品等贸易<br />
					<font color="blue">天津启润投资有限公司</font><br />
			　 		主营煤炭、钢材、纸张、纸浆、铁矿等大宗工业原材料进出口业务<br />
					<font color="blue">成都启润投资有限公司</font><br />
			　 		主营铁矿，钢材，有色金属，纸张，饲料，粮油食品等<br />
					<font color="blue">宝达投资（香港）有限公司</font><br />
			　 		主营铁矿产品、纺织制品、煤炭、橡胶、化纤产品、服装、棉花、棉纱和化工产品等<br />
					<font color="blue">台湾宝达兴业有限公司（台湾办事处）</font><br />
			　 		集团对台贸易的重要窗口<br />
					<font color="blue">国贸新加坡有限公司</font><br />
			　 		主营铁矿、煤炭、棕榈油等大宗商品进出口业务<br />
				</p>
			</div>
        </div>
    </div>
    -->
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
