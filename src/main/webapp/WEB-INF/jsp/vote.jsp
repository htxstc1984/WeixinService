<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.itg.weixin.vo.VoteItemVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mainid = (String) request.getAttribute("mainid");
	String openid = (String) request.getSession().getAttribute("openid");
	String content = (String) request.getAttribute("content");
	String psnname = (String) request.getAttribute("psnname");
	List<VoteItemVO> list = (List<VoteItemVO>) request.getAttribute("itemList");
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
</head>

<script type="text/javascript">
	var openid="<%=openid%>";
	var mainid="<%=mainid%>";
	var psnname="<%=psnname%>";
	
	function clear(){
		$("input[type='checkbox']").attr("checked", false).checkboxradio("refresh");
	}
	
	
	
	function submit(isReVote){
		var items = $("input[type='checkbox']");
		var selectednum = 0;
		var in_cond = "";
		for ( var i = 0; i < items.length; i++) {
			var checked = items[i].checked;
			if(checked){
				selectednum = selectednum + 1;
				in_cond = in_cond + items[i].value + ",";
			}
		}
		
		if (selectednum != 3) {
			showInfo("请选择3个节目提交");
		}else{
			in_cond = in_cond.substring(0,in_cond.length-1);
			showLoader();
			$.post("<%=basePath%>vote.html", {
				openid : openid,
				idList : in_cond,
				mainid : mainid
			}, function(data, status) {
				if(status!="success"){
					showInfo("网络超时");
					return;
				}
				hideLoader();
				eval("var rs = " + data);
				if(rs.code==0){
					showInfo(rs.msg);
					$("#title_rs").text(psnname+"，您已投票，如需重投，请重新选择.");
				}else{
					showInfo(rs.msg);
					clear();
				}
			});
		}
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
	
	function showInfo(text){
		$('#infoText').html(text);
		$('#infoBox').popup('open');
	}
	
	//$.mobile.pushStateEnabled=false;
	function init(){
		//history.replaceState(null, "搜索", "/vote.html");
		document.addEventListener('WeixinJSBridgeReady',
				function onBridgeReady() {
					WeixinJSBridge.call('hideOptionMenu');
				});

		document.addEventListener('WeixinJSBridgeReady',
				function onBridgeReady() {
					WeixinJSBridge.call('hideToolbar');
				});
	}
	
</script>

<body onload="init()">
	<div data-role="page" data-theme="e">
		<div data-role="header"  data-position="fixed">
			<%
				out.println("<h3>我喜爱的节目</h3>");
				//if(content.equalsIgnoreCase("empty")){
					//out.println("<a href=\"javascript:clear()\" data-icon=\"delete\" data-theme=\"b\">清空</a>");
					//out.println("<h1>节目评选</h1>");
					//out.println("<a href=\"javascript:submit()\" data-icon=\"check\" data-theme=\"b\">投票</a>");
				//}else{
				//	out.println("<h1>节目评选</h1>");
				//}
			%>
		    
		</div>
		<!-- /header -->
		
		<div data-role="popup" id="infoBox" data-theme="a">
			<p id="infoText">消息</p>
		</div>

		<div data-role="content">
			<form>
			    <fieldset data-role="controlgroup" data-iconpos="right">
			        
			        <%
			        	Map<String,String> selectMap = new HashMap<String,String>();
			        	if(content.equalsIgnoreCase("empty")){
			        		out.println("<legend><font id=\"title_rs\" size=\"4\">"+psnname+",请选择其中3项:</font></legend>");
			        	}else{
			        		String[] selects = content.split(",");
			     			for(int i=0;i<selects.length;i++){
			     				selectMap.put(selects[i], selects[i]);
			     			}
							out.println("<legend><font id=\"title_rs\" size=\"4\">"+psnname+"，您已投票，如需重投，请重新选择.</font></legend>");
						}
			        	for(int i=0;i<list.size();i++)
			        	{
			        		String checked;
			        		VoteItemVO vo = list.get(i);
			        		if(selectMap.isEmpty()){
			        			checked = "false";
			        		}else{
			        			checked = selectMap.containsKey(String.valueOf(vo.getItemno()))?"checked='true'":"";
			        		}
			        		out.println("<input type=\"checkbox\" "+checked+" value=\""+vo.getItemno()+"\" name=\"item_"+vo.getItemno()+"\" id=\"item_"+vo.getItemno()+"\">");
			        		out.println("<label for=\"item_"+vo.getItemno()+"\"><font size=\"3\">"+vo.getItemno()+"&nbsp;&nbsp;"+vo.getItemname()+"</font><br/><font color=\"blue\" size=\"3\">"+vo.getItemdesc()+"</font></label>");
			        	}
			        %>
			    </fieldset>
			</form>
			<%
				if(content.equalsIgnoreCase("empty")){
					out.println("<div class=\"ui-grid-a\">");
					out.println("<div class=\"ui-block-a\">");
					//out.println("<button data-theme=\"b\" onclick=\"clear()\" >清空</button>");
					out.println("<a href=\"javascript:clear()\" data-role='button' data-theme='b'>清空</a>");
					out.println("</div>");
					out.println("<div class=\"ui-block-b\">");
					out.println("<button data-theme=\"b\" onclick=\"submit()\" >投票</button>");
					out.println("</div>");
					out.println("</div>");
				}else{
					out.println("<div class=\"ui-grid-a\">");
					out.println("<div class=\"ui-block-a\">");
					//out.println("<button data-theme=\"b\" onclick=\"clear()\" >清空</button>");
					out.println("<a href=\"javascript:clear()\" data-role='button' data-theme='b'>清空</a>");
					out.println("</div>");
					out.println("<div class=\"ui-block-b\">");
					out.println("<button data-theme=\"b\" onclick=\"submit()\" >重投</button>");
					out.println("</div>");
					out.println("</div>");
				}
			%>
		</div>
		<!-- /content -->
		
	</div>
	
	<!-- /page -->
	
</body>
</html>