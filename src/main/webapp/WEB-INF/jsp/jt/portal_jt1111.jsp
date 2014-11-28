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
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="微信">

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

<title>厦门国贸集团</title>
<script type="text/javascript" src="./@system/js/jquery.js"></script>
<script type="text/javascript" src="./@system/js/yyucadapter.js"></script>
<script type="text/javascript" src="./@system/js/jquery.js"></script>
<script type="text/javascript" src="./@system/js/yyucadapter.js"></script>
<link type="text/css" rel="stylesheet" href="./res/wz/style/list.css" />
<script type="text/javascript" src="./js/mwm/estate/3dskin.js"></script>
<script type="text/javascript" src="./res/mu/mu.js"></script>
<script type="text/javascript" src="./res/swipe.js"></script>
<link rel="stylesheet" type="text/css" href="./css/mwm/reset.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="./css/mwm/home-38.css"
	media="all" />
<script type="text/javascript" src="./js/mwm/maivl.js"></script>
<script type="text/javascript" src="./js/mwm/swipe.js"></script>
<script type="text/javascript" src="./js/mwm/zepto.js"></script>
<link rel="stylesheet" type="text/css" href="./css/mwm/font-awesome.css"
	media="all" />
<script type="text/javascript">
	$(function() {
		$('img[rrurl]').click(function() {
			location.href = $(this).attr('rrurl');
		});
		$('table').attr("cellpadding", "0").attr("cellspacing", "0");
		$('[fixed="fixed"]').css('position', 'fixed');
		resize();
		$(window).resize(function() {
			resize();
		});
		$('.add_qq_more').each(
				function() {
					var tourl = $.trim($(this).attr('toto'));
					if (tourl == '') {
						tourl = 'javascript:;'
					}
					if (tourl.indexOf(':') == -1) {
						tourl = tourl + '.html';
					}
					if (tourl != '') {
						if (tourl.indexOf('tel') !== 0) {
							if (tourl.indexOf('?') > 0) {
								tourl = tourl + '&wxid=#mp.weixin.qq.com';
							} else {
								tourl = tourl + '#mp.weixin.qq.com';
							}

						}
						if ($(this).is('a')) {
							$(this).attr('href', tourl);
						} else if ($(this).find('a').size() > 0) {
							$(this).find('a').each(
									function() {
										if ($.trim($(this).attr('href'))
												.indexOf('http') == -1) {
											$(this).attr('href', tourl);
										}
									});
						} else {
							$(this).wrap('<a href="'+tourl+'"></a>');
						}
					}
				});
		if ($('.mainpicarea').is('div')) {
			var tplth = $('.mainpicarea').find('td').length;
			$('#ppoool')
					.append('<li class="on" style="margin-left:5px;"></li>');
			for ( var i = 1; i < tplth; i++) {
				$('#ppoool').append('<li style="margin-left:5px;"></li>');
			}
			$('.mainpicarea').qswipe({
				stime : 3600
			});
			$('.mainpicarea').on('dragok', function(e, msg) {
				if ((msg + '').indexOf('.') > 0) {
					msg = 0;
				}
				$('#ppoool').find('li').removeClass('on');
				$('#ppoool').find('li').eq(msg).addClass('on');
			});

		}

	});
	function resize() {
		var ww = $(window).width();
		$('.picshowtop,.mainpicshow').css('width', ww + 'px');
		$('#tpdhtr').children('td').css('width', ww + 'px');
		$('#tpdhtr').children('td').find('img').css('width', ww + 'px');
		$('img').each(function() {
			var pw = $(this).parent().width();
			var ppw = $(this).parent().parent().width();
			if ($(this).width() > ppw) {
				$(this).width(ppw);
			}
		});
	}
</script>
<script type="text/javascript">
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		window.shareData = {
			"imgUrl" : "http://172.16.10.56:8099/",
			/* "timeLineLink": "http://172.16.10.56:8099/weiweb/110/",
			"sendFriendLink": "http://172.16.10.56:8099/weiweb/110/",
			"weiboLink": "http://172.16.10.56:8099/weiweb/110"/, */
			"timeLineLink" : location.href,
			"sendFriendLink" : location.href,
			"weiboLink" : location.href,
			"tTitle" : "厦门国贸集团",
			"fTitle" : "厦门国贸集团",
		};
		// 发送给好友
		WeixinJSBridge.on('menu:share:appmessage', function(argv) {
			WeixinJSBridge.invoke('sendAppMessage', {
				"img_url" : window.shareData.imgUrl,
				"img_width" : "640",
				"img_height" : "640",
				"link" : window.shareData.sendFriendLink,
				"desc" : window.shareData.fContent,
				"title" : window.shareData.fTitle
			}, function(res) {
				_report('send_msg', res.err_msg);
			})
		});

		// 分享到朋友圈
		WeixinJSBridge.on('menu:share:timeline', function(argv) {
			WeixinJSBridge.invoke('shareTimeline', {
				"img_url" : window.shareData.imgUrl,
				"img_width" : "640",
				"img_height" : "640",
				"link" : window.shareData.timeLineLink,
				"desc" : window.shareData.tContent,
				"title" : window.shareData.tTitle
			}, function(res) {
				_report('timeline', res.err_msg);
			});
		});

		// 分享到微博
		WeixinJSBridge.on('menu:share:weibo', function(argv) {
			WeixinJSBridge.invoke('shareWeibo', {
				"content" : window.shareData.wContent,
				"url" : window.shareData.weiboLink,
			}, function(res) {
				_report('weibo', res.err_msg);
			});
		});
	}, false)
</script>
<style type="text/css">
.mainpicshow {
	height: 0px;
	overflow: hidden;
}

.mainpicarea {
	height: 0px;
}

.mainpicarea table,.mainpicarea tr,.mainpicarea td {
	border: none;
	border-image-width: 0px;
}

.mainpicarea img {
	height: 0px;
}

#ppoool {
	height: 20px;
	position: relative;
	z-index: 10;
	margin-top: 0px;
	text-align: right;
	padding-right: 15px;
	background-color: rgba(0, 0, 0, 0.3);
}

#ppoool>li {
	display: inline-block;
	margin: 5px 0;
	width: 8px;
	height: 8px;
	background-color: #757575;
	border-radius: 8px;
}

#ppoool>li.on {
	background-color: #ffffff;
}
</style>
</head>
<body>
	<div
		style="top: 0px; left: 0px; width: 100%; height: 100%; position: fixed; z-index: -999; background-image: url('./ups/2014/05/111/9e7a768161a075faed658ad6c366d45a.png'); background-size: 100%, 100%;">
	</div>
	<div class="mainshowtop">
		<div id="page">
			<div id="news">
				<div class="news-title bgcolor add_qq_more">
					<i class="icon icon-5 m_pic"></i><a href="javascript:;"
						class="m_text">最新消息
						<div class="right-arrow"></div>
					</a>
				</div>
				<div class="menulist">
					<a href="javascript:;" class="menu add_qq_more">
						<div class="img">
							<img
								src="./res/wz/images/df5cd403-eac9-4512-8da8-c5f1328faf19.jpg"
								class="m_pic">
						</div>
						<h2 class="fontcolor m_text">XXXX0927</h2>
						<p class="summary m_desc">XXXX新版发布，应用商店火热上线</p> <span
						class="time m_date">2013-09-28 19:44</span>
					</a>
				</div>
			</div>
			<div class="box-btn bgcolor add_qq_more">
				<a href="javascript:;"><div class="m_text">xxxxxxxxxxxxx</div></a>
			</div>
			<div id="mainmenu" class="fn-clear">
				<ul>
					<li class="add_qq_more"><a href="javascript:;">
							<p class="title m_text">关于xxxx</p>
							<p class="summary m_desc">xxxxxxxxxxxxxxxxxxxxxxx</p> <img
							src="./res/wz/images/cover1.jpg" class="m_pic">
							<div class="right-arrow m_pic"></div>
					</a></li>
					<div class="clear" style="clear: both;"></div>
				</ul>
			</div>
		</div>
	</div>
	<div class="mfooter" id="wxgjfooter"
		style="text-align: center; width: 100%; height: 20px; line-height: 20px; margin-top: 0px; vertical-align: bottom;">
		<div style="width: 0px; height: 0px; overflow: hidden;"></div>
		<div class="mfooter" id="wxgjfooter"
			style="text-align: center; width: 100%; height: 20px; line-height: 20px; margin-top: 0px; vertical-align: bottom;">
			<div style="width: 0px; height: 0px; overflow: hidden;"></div>
			<br> <br> <br>
			<script src="./weiweb/menu/2.js" type="text/javascript"></script>
			<link rel="stylesheet" type="text/css" href="./res/mu/mu.css"
				media="all" />
		</div>
</body>
<script type="text/javascript">
	
</script>
</html>