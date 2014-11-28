<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="gbk">
<title>信息审核</title>
<!--根据JQuery.UI的例子改写，by wallimn, 2012-08-01-->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="<%=basePath%>js/paginate.js"></script>
<style>
body {
	font-size: 10px;
}

table,input,button {
	font-size: medium;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 4px;
	text-align: left;
}

div#approved-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#approved-contain table td,div#approved-contain table th {
	border: 1px solid #eee;
	padding: .6em 4px;
	text-align: left;
}

   
.pagination a {
    text-decoration: none;
	border: 1px solid #AAE;
	color: #15B;
}

.pagination a, .pagination span {
    display: inline-block;
    padding: 0.1em 0.4em;
    margin-right: 5px;
	margin-bottom: 5px;
}

.pagination .current {
    background: #26B;
    color: #fff;
	border: 1px solid #AAE;
}

.pagination .current.prev, .pagination .current.next{
	color:#999;
	border-color:#999;
	background:#fff;
}

</style>
<script>
	var fromId = 0;
	var timerHandle;
	
	function approve(btn){
		var nickname = $('#nk_'+btn.value)[0].value;
		var message = $('#msg_'+btn.value)[0].value;
		if(nickname==""){
			alert("昵称没有填写");
			return ;
		}
		if(message==""){
			alert("信息没有填写");
			return ;
		}
		$.post("<%=basePath%>approveMsg.html", {
			recId : btn.value,
			nickname : nickname,
			message : message
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var rs = " + data);
			if(rs.code==0){
				alert(rs.msg);
				$("tr[value=user_"+btn.value+"]").remove();
			}else{
				alert(rs.msg);
			}
		});
	}
	
	function setBlack(id,openid,psnname,bz){
		if (!confirm("是否确定将此信息发言人设置为黑名单？")) {
			return;
		}
		
		$.post("<%=basePath%>setBlack.html", {
			recId : id,
			openid : openid,
			psnname : psnname,
			bz : bz
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var rs = " + data);
			if(rs.code==0){
				alert(rs.msg);
				$("tr[value=user_"+id+"]").remove();
			}else{
				alert(rs.msg);
			}
		});
	}
	
	function unApprove(btn){
		if (!confirm("是否确定将此信息驳回？")) {
			return;
		}
		$.post("<%=basePath%>unApproveMsg.html", {
			recId : btn.value
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var rs = " + data);
			if(rs.code==0){
				alert(rs.msg);
				$("tr[value=user_"+btn.value+"]").remove();
			}else{
				alert(rs.msg);
			}
		});
	}
	
	function redoApprove(id){
		if (!confirm("是否确定将此信息取消审核？取消后所有赞会清空")) {
			return;
		}
		$.post("<%=basePath%>redoApproveMsg.html", {
			recId : id
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var rs = " + data);
			if(rs.code==0){
				alert(rs.msg);
				$("tr[value=rec_"+id+"]").remove();
			}else{
				alert(rs.msg);
			}
		});
	}
	
	function getReceiveMsg() {
		$.post("<%=basePath%>getReceiveMsg.html", {
			fromId : fromId
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var msgList = " + data);
			if(msgList==""){
				return;
			}
			var html = "";
			for ( var i = 0; i < msgList.length; i++) {
				fromId = msgList[i].id;
				html = html + "<tr value=user_"+msgList[i].id+">";
				html = html + "<td width='50'>"+msgList[i].id+"</td>";
				html = html + "<td width='100'><input style=\"width: 98%\" id='nk_"+msgList[i].id+"' type='text' value='"+msgList[i].nickname+"'/></td>";
				html = html + "<td width='80'>"+msgList[i].psnname+"</td>";
				html = html + "<td width='120'>"+msgList[i].bz+"</td>";
				html = html + "<td width='40'>"+msgList[i].keyword+"</td>";
				//html = html + "<td><input id='msg_"+msgList[i].id+"' type='text' class='text' value='"+msgList[i].message+"'/></td>";
				html = html + "<td width='500'><textarea id='msg_"+msgList[i].id+"' style=\"resize:none;font-size: 15px;width: 98%\" maxlength='144'  name=\"textarea\" cols=\"12\" rows=\"3\">"+msgList[i].message+"</textarea></td>";
				html = html + "<td width='250'>";
				html = html + "<button style=\"background-color: green;color: white\" onclick=\"approve(this)\" value="+msgList[i].id+" class=\"approveButton\">通过</button>";
				html = html + "&nbsp;&nbsp;<button style=\"background-color: red;color: white\" onclick=\"unApprove(this)\" value="+msgList[i].id+" class=\"unApprButton\">不通过</button>";
				html = html + "&nbsp;&nbsp;<button style=\"color: gray\" onclick=\"setBlack('"+msgList[i].id+"','"+msgList[i].openid+"','"+msgList[i].psnname+"','"+msgList[i].bz+"')\" value="+msgList[i].id+" class=\"unApprButton\">禁言</button>";
				html = html + "</td>";
				html = html + "<tr>";
			}
			$("#users-msgBox").append(html);
		});
	}
	
	function initPagination(pageCount){
		// 创建分页
		$("#Pagination").pagination(pageCount, {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 4, //主体页数
			callback: pageselectCallback,
			items_per_page: 1, //每页显示1项
			prev_text: "前一页",
			next_text: "后一页"
		});
	}
	
	function getApprovedMessageByPage(page_index){
		$.post("<%=basePath%>getApprovedMsgByPage.html", {
			pageNo : page_index
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var msgList = " + data);
			if(msgList==""){
				return;
			}
			var html = "";
			for ( var i = 0; i < msgList.length; i++) {
				html = html + "<tr value=rec_"+msgList[i].id+">";
				html = html + "<td width='50'>"+msgList[i].id+"</td>";
				var appr_txt = (msgList[i].isapprove=="1")?"<font color='green'>审核已通过</font>":"<font color='red'>审核未通过</font>";
				html = html + "<td width='100'>"+appr_txt+"</td>";
				html = html + "<td width='100'>"+msgList[i].nickname+"</td>";
				html = html + "<td width='80'>"+msgList[i].psnname+"</td>";
				html = html + "<td width='120'>"+msgList[i].bz+"</td>";
				html = html + "<td width='40'>"+msgList[i].keyword+"</td>";
				//html = html + "<td><input id='msg_"+msgList[i].id+"' type='text' class='text' value='"+msgList[i].message+"'/></td>";
				html = html + "<td width='450'>"+msgList[i].message+"</td>";
				html = html + "<td width='150'>";
				html = html + "<button style=\"background-color: red;color: white\" onclick=\"redoApprove("+msgList[i].id+")\" value="+msgList[i].id+" class=\"approveButton\">取消审核</button>";
				html = html + "</td>";
				html = html + "<tr>";
			}
			$("#approved-msgBox").append(html);
			
		});
	}
	
	function pageTop(){
		  return $("#main").height();
		}
	
	function pageselectCallback(page_index, jq){
		$("#approved-msgBox").empty();
		getApprovedMessageByPage(page_index+1);
	}

	function displayApproved(){
		$('#users-contain').hide();
		$('#approved-contain').show();
		$("#Pagination").empty();
		$("#approved-msgBox").empty();
		$.post("<%=basePath%>getApprovedPageCount.html", {
			
		}, function(data, status) {
			if(status!="success"){
				return;
			}
			eval("var count = " + data);
			if(count==0){
				alert("无法获取审核消息，请刷新");
				return;
			}
			initPagination(count);
		});
		$('#btnAppred').attr({"disabled":"disabled"});
		$('#btnAppring').removeAttr("disabled");
	}
	
	function displayApproving(){
		clearInterval(timerHandle);
		$("#users-msgBox").empty();
		fromId = 0;
		getReceiveMsg();
		timerHandle=setInterval(getReceiveMsg,5000);
		$('#users-contain').show();
		$('#approved-contain').hide();
		$('#btnAppring').attr({"disabled":"disabled"});
		$('#btnAppred').removeAttr("disabled");
	}
	
	$(function() {
		getReceiveMsg();
		timerHandle=setInterval(getReceiveMsg,5000);
	});
</script>
</head>
<body>
	<div id='main' class="demo">
		<h1>消息审核:&nbsp;&nbsp;<button id='btnAppred' onclick="displayApproved()">显示已审核信息</button>&nbsp;&nbsp;<button disabled="disabled" id='btnAppring' onclick="displayApproving()">回到审核界面</button></h1>
		<div id="users-contain">
			<table id="users" style="font-size: medium;">
				<thead style="display:block;">
					<tr class="ui-widget-header ">
						<th width="50">编号</th>
						<th width="100">昵称</th>
						<th width="80">真实姓名</th>
						<th width="120">部门信息</th>
						<th width="40">KEY</th>
						<th width="500">消息</th>
						<th width="250">操作</th>
					</tr>
				</thead>
				<tbody id="users-msgBox"  style="height:500px;overflow:auto;display:block;">
				</tbody>
			</table>
			
		</div>
		<div id="approved-contain" style="display: none;margin: 0">
			
			<table id="users" style="font-size: medium;">
				<thead style="display:block;">
					<tr class="ui-widget-header ">
						<th width="50">编号</th>
						<th width="100">状态</th>
						<th width="100">昵称</th>
						<th width="80">真实姓名</th>
						<th width="120">部门信息</th>
						<th width="40">KEY</th>
						<th width="450">消息</th>
						<th width="150">操作</th>
					</tr>
				</thead>
				<tbody id="approved-msgBox"  style="height:500px;overflow:auto;display:block;">
				</tbody>
			</table>
			<div id="Pagination" class="pagination">
			</div>
		</div>
	</div>
	<!-- End demo -->
	<div id="debug"></div>
</body>
</html>
