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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/zp/main.css" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>


<body id="mod0">
	<div id="mod1" class="wp<%=(modid==null || modid.equalsIgnoreCase("1")) ? " onshow":" nodis"%>">
        <div class="main">
            <div class="box">
                
            </div>
        </div>
    </div>
    <div id="mod2" class="wp nodis">
        <div class="main">
            <div class="box">
                <div class="word fadein">
                	<img src="<%=basePath%>image/zp/pic/2/2-2-w.png" />
                </div>
            </div>
        </div>
    </div>
    <div id="mod3" class="wp nodis">
        <div class="main">
            <div class="box">
                <div class="word fadein">
                	<img src="<%=basePath%>image/zp/pic/2/2-3-w.png" />
                </div>
            </div>
        </div>
    </div>
    <div id="mod4" class="wp nodis">
        <div class="main">
            <div class="box">
                <div class="word fadein">
                	<img src="<%=basePath%>image/zp/pic/2/2-4-w.png" />
                </div>
            </div>
        </div>
    </div>
    <div id="mod5" class="wp nodis">
        <div class="main">
            <div class="box">
                <div class="word slide">
                	<img src="<%=basePath%>image/zp/pic/2/2-5-w.png" />
                </div>
            </div>
        </div>
    </div>
    <div id="mod6" class="wp nodis">
        <div class="main">
            <div class="box">
                <div class="word slide">
                	<img src="<%=basePath%>image/zp/pic/2/2-6-w.png" />
                </div>
            </div>
        </div>
    </div>
    <div id="mod8" class="wp<%=(modid!=null && modid.equalsIgnoreCase("8")) ? " onshow":" nodis"%>">
        <div class="main">
            <div class="box">
                <div class="word">
                	
                </div>
            </div>
        </div>
    </div>
    <div id="mod9" class="wp<%=(modid!=null && modid.equalsIgnoreCase("9")) ? " onshow":" nodis"%>">
        <div class="main">
            <div class="box">
                <div class="word">
                	
                </div>
            </div>
        </div>
    </div>
    <div id="mod10" class="wp<%=(modid!=null && modid.equalsIgnoreCase("10")) ? " onshow":" nodis"%>">
        <div class="main">
            <div class="box">
                <div class="word">
                	
                </div>
            </div>
        </div>
    </div>
    <div id="mod11" class="wp nodis">
        <div class="main">
            <div class="box">
                <div class="word">
                	
                </div>
            </div>
        </div>
    </div>
    
    <div id="prev" style="display:none"></div>
    <div id="next"></div>
</body>
<script src="<%=basePath%>js/zp/touch.js"></script>
<script type="text/javascript">
	var modid='<%=(modid==null || modid.equalsIgnoreCase("1")) ? "1":"0"%>';
	$(document).ready(function(){
		
		if (modid!="1") {
			$(".nodis").css({display:'none'});
			$("#prev").css({display:'none'});
			$("#next").css({display:'none'});
		}
	});
</script>
</html>
