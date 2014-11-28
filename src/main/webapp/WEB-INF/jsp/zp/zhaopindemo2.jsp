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
<title>国贸校招</title>
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,user-scalable=no" />
</head>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/zp/main2.css" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<style>
	#mod2 ul{width: 100%}
	#mod2 li:nth-child(odd){text-align: left;display: block;height: 13%}
	#mod2 li:nth-child(even){text-align: right;display: block;height: 13%}
	.mod2-left{
		width: 55%;
		height:100%;
		display: inline-block;
		background-image: url(<%=basePath%>image/zp/test/2-3-left1.png);
		background-repeat: no-repeat;
		margin-top: 30px;
		background-size:contain; 
		text-align: left;
		line-height: 100%;
	}
	.mod2-right{
		width: 55%;
		height:100%;
		display: inline-block;
		background-image: url(<%=basePath%>image/zp/test/2-3-right1.png);
		background-repeat: no-repeat;
		margin-top: 30px;
		background-size:contain; 
		text-align: right;
		line-height: 100%;
	}
	#mod2 li a{
		display:block;
		height:100%;
		margin-top:10%;
		text-align: center;
	}
	
	
	
	#mod4 .region{
		width: 100%;
		float: left;
		text-align: center;
		
	}
	
	#mod4 .zw{
		display: inline-block;
		width: 20%;
		padding: 5px 5px 5px 5px;
	}
	
	#mod4 .zw_p{
		height:20%;
		border-radius:10px 10px 10px 10px ;
		background-color: white;
	}
	
	#mod4 img{
		width: 90% ;
	}
	
	#mod4 a{
		line-height:60px;
		display: block ;
		color: white;
	}
</style>

<body id="mod0">
	<div class="detail_cover detail_hide" id="detail_cover"></div>
	<div id="mod1" class="wp onshow">
        <div class="main">
        	<div>
        		<img style="position: absolute;top:0;left:0;z-index: 1;width: 100%;height: 100%" src="<%=basePath%>image/zp/test/2-2-b.png" />
        	</div>
            <div class="fadein">
            	<img style="position: absolute;top:0;left:0;z-index: 2;width: 100%;height: 100%" src="<%=basePath%>image/zp/test/2-2-w.png" />
            </div>
        </div>
    </div>
    <div id="mod2" class="wp">
        <div class="main">
        	<div class="detail_hide" onclick="hidedetail('mod2_1')" id="mod2_1"><a>2014年10月15日 18:30<br />工商管理学院文泉五楼会议室</a></div>
        	<div class="detail_hide" onclick="hidedetail('mod2_2')" id="mod2_2"><a>2014年10月17日 18:30<br />伯伶楼二楼多功能厅</a></div>
        	<div class="detail_hide" onclick="hidedetail('mod2_3')" id="mod2_3"><a>2014年11月03日 18:30<br />就业指导中心209室</a></div>
        	<div class="detail_hide" onclick="hidedetail('mod2_4')" id="mod2_4"><a>2014年11月05日 18:30<br />东校区行政楼B101</a></div>
        	<div class="detail_hide" onclick="hidedetail('mod2_5')" id="mod2_5"><a>2014年11月07日 18:30<br />武东校区学生中心三楼招聘大厅</a></div>
        	<div>
        		<img style="position: absolute;top:0;left:0;z-index: 1;width: 100%;height: 100%"  src="<%=basePath%>image/zp/test/2-3-b.png" />
        	</div>
            <div style="position: absolute;top:20%;left:0;z-index: 2;width: 100%;height: 100%" >
            	<ul style="width: 100%;height: 100%" >
            		<li><div class="mod2-left show_left"><a href="javascript:showdetail('mod2_1')">中南财经政法 10月15日</a></div></li>
            		<li><div class="mod2-right show_right"><a href="javascript:showdetail('mod2_2')">南开大学 10月17日</a></div></li>
            		<li><div class="mod2-left show_left"><a href="javascript:showdetail('mod2_3')">四川大学 11月3日</a></div></li>
            		<li><div class="mod2-right show_right"><a href="javascript:showdetail('mod2_4')">中山大学 11月5日</a></div></li>
            		<li><div class="mod2-left show_left"><a href="javascript:showdetail('mod2_5')">上海财经大学 11月7日</a></div></li>
            	</ul>
            </div>
        </div>
    </div>
    <div id="mod4" class="wp">
        <div class="main">
        	<div>
        		<img style="position: absolute;top:0;left:0;z-index: 1;width: 100%;height: 100%"  src="<%=basePath%>image/zp/test/2-5-b.png" />
        	</div>
            <div style="position: absolute;top:60%;left:0;z-index: 2;width: 100%;height: 100%" >
            	<div class="region">
            		<div class="show_left">
	            		<div class="zw"><div class="zw_p" style="background-color: #47B96A"><a>后勤职能类</a></div></div>
	            		<div class="zw"><div><img src="<%=basePath%>image/zp/test/zw.png"/></div></div>
	            		<div class="zw"><div class="zw_p" style="background-color: #98D6A3"><a>贸易类</a></div></div>
            		</div>
            	</div>
            	<div class="region">
            		<div class="show_right">
            			<div class="zw"><div class="zw_p" style="background-color: #FFA2A7"><a>物流类</a></div></div>
	            		<div class="zw"><div class="zw_p" style="background-color: #FFD155"><a>汽车类</a></div></div>
	            		<div class="zw"><div class="zw_p" style="background-color: #FEB28C"><a>金融类</a></div></div>
            		</div>
            	</div>
            </div>
        </div>
    </div>
    <div id="mod5" class="wp">
        <div class="main">
        	<div>
        		<img style="position: absolute;top:0;left:0;z-index: 1;width: 100%;height: 100%"  src="<%=basePath%>image/zp/test/2-6-b.jpg" />
        	</div>
            
        </div>
    </div>
    
    <div id="prev" style="display:none"></div>
    <div id="next"></div>
</body>
<script src="<%=basePath%>js/zp/touch_demo2.js"></script>
<script type="text/javascript">
	var noTouch = false;
	var modid='<%=(modid==null || modid.equalsIgnoreCase("1")) ? "1":"0"%>';
	$(document).ready(function(){
		
		if (modid!="1") {
			$(".nodis").css({display:'none'});
			$("#prev").css({display:'none'});
			$("#next").css({display:'none'});
		}
	});
	
	function showdetail(id){
		
		$("#detail_cover").removeClass("detail_hide");
		$("#"+id).addClass("detail");
		$("#"+id).removeClass("detail_hide");
		noTouch = true;
	}
	
	function hidedetail(id){
		$("#"+id).removeClass("detail");
		$("#"+id).addClass("detail_hide");
		$("#detail_cover").addClass("detail_hide");
		noTouch = false;
	}
</script>
</html>

