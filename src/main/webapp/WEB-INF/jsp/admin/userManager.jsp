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
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet"
	href="./easyui/themes/default/easyui.css">
<link type="text/css" rel="stylesheet" href="./easyui/themes/icon.css">
<script type="text/javascript" src="./easyui/jquery.min.js"></script>
<script type="text/javascript" src="./easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户维护</title>
</head>
<script type="text/javascript">
	var operation = "";
	function newUser() {
		operation = "insert";
		win.window('open');
		form.form('clear');
		form.url = './insertUser.html';
	}
	function editUser() {
		operation = "edit";
		var row = grid.datagrid('getSelected');
		if (row) {
			win.window('open');
			form.form('load', './getUserByID.html?id=' + row.id);
			form.url = './updateUser.html';
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择用户资料。'
			});
		}
	}
	function saveUser() {
		form.form('submit', {
			url : form.url,
			success : function(data) {
				eval('data=' + data);
				if (data.success) {
					grid.datagrid('reload');
					win.window('close');
				} else {
					$.messager.alert('错误', data.msg, 'error');
				}
			}
		});
	}
	function closeWindow() {
		win.window('close');
	}
</script>

</head>
<body class="easyui-layout" style="text-align: left">
	<table id="user-table" class="easyui-datagrid" title="Basic DataGrid"
		data-options="singleSelect:true,collapsible:true,url:'./getWxUsers.html',method:'get',toolbar:toolbar">
		<thead>
			<tr>
				<th data-options="field:'id',width:100">ID</th>
				<th data-options="field:'username',width:150">用户名</th>
				<th data-options="field:'userdesc',width:250,align:'right'">描述</th>
				<th data-options="field:'wxaccount',width:200">微信账号</th>
				<th data-options="field:'wxurl',width:200,align:'center'">链接</th>
				<th data-options="field:'bz',width:280,align:'right'">备注</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		var toolbar = [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				newUser();
			}
		}, {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				editUser();
			}
		}, {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				alert('save')
			}
		} ];
	</script>
	<div id="user-window" title="用户窗口" style="width: 400px; height: 350px;">
		<div style="padding: 20px 20px 40px 80px;">
			<form method="post">
				<table>
					<tr>
						<td>id：</td>
						<td><input name="id"></input></td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input name="username"></input></td>
					</tr>
					<tr>
						<td>描述：</td>
						<td><input name="userdesc"></input></td>
					</tr>
					<tr>
						<td>微信账号：</td>
						<td><input name="wxaccount"></input></td>
					</tr>
					<tr>
						<td>微信链接：</td>
						<td><input name="wxurl"></input></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input name="bz"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="saveUser()" id="btn-save"
				icon="icon-save">保存</a> <a href="javascript:void(0)"
				onclick="closeWindow()" id="btn-cancel" icon="icon-cancel">取消</a>
		</div>
	</div>
	<script type="text/javascript">
		$('#btn-save,#btn-cancel').linkbutton();
		win = $('#user-window').window({
			closed : true
		});
		form = win.find('form');
		grid = $('#user-table');
	</script>
</body>
</html>