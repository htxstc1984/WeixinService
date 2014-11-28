<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = (String) request.getAttribute("title");
	String slogans = (String) request.getAttribute("slogans");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
<title>全部口号</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>easyui/demo/demo.css">
<script type="text/javascript"
	src="<%=basePath%>easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>easyui/jquery.edatagrid.js"></script>
<script type="text/javascript">
	$(function() {
		$('#dg').edatagrid({
			url : '<%=basePath%>getAllSlogans.html',
			saveUrl : 'save_user.php',
			updateUrl : 'update_user.php',
			destroyUrl : 'destroy_user.php',
			onLoadSuccess : function(data){
				var p = $('#dg').datagrid('getPager'); 
		        $(p).pagination({ 
		            displayMsg: '当前显示 {from} - {to} 条记录    共 {total} 条记录,共'+data.rs.length+'人发言',
		        }); 
			}
		});
		var p = $('#dg').datagrid('getPager'); 
        $(p).pagination({ 
            pageSize: 15,//每页显示的记录条数，默认为10
            pageList:[15,25,35,45,55,100],//每页显示几条记录
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录    共 {total} 条记录',
            onBeforeRefresh:function(){ 
                $(this).pagination('loading');//正在加载数据中...
                alert('before refresh');
                $(this).pagination('loaded'); //数据加载完毕
            } 
        }); 
	});
</script>
</head>
<body>
	<h2>主题口号</h2>
	
	<table id="dg" title="主题口号" style="width:90%;height:550px"
			toolbar="#toolbar" pagination="true" idField="id"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<!--  
			<tr>
				<th field="openid" width="50" editor="{type:'validatebox',options:{required:true}}">微信id</th>
				<th field="truename" width="50" editor="{type:'validatebox',options:{required:true}}">姓名</th>
				<th field="mobile" width="50" editor="text">手机号码</th>
				<th field="createDate" width="50" editor="{type:'validatebox',options:{validType:'email'}}">发布日期</th>
				<th field="content" width="150" editor="{type:'validatebox',options:{validType:'email'}}">口号内容</th>
			</tr>
			-->
			<tr>
				<th field="openid" width="50">微信id</th>
				<th field="truename" width="50">姓名</th>
				<th field="mobile" width="50">手机号码</th>
				<th field="createDate" width="50">发布日期</th>
				<th field="content" width="150" editor="textarea">口号内容</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<!--  
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">新建</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
		-->
		<a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">取消</a>
	</div>
</body>
</html>