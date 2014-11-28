<%@page import="com.itg.weixin.model.UserEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserEntity loginUser = (UserEntity) request.getSession()
			.getAttribute("loginUser");
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
<title>微信功能管理</title>
</head>
<script type="text/javascript">
	function InitTreeData() {
		$('#tree').tree({
			//url : 'demo01.ashx',
			checkbox : true,
			onClick : function(node) {
				alert(node.text);
			},
			onContextMenu : function(e, node) {
				e.preventDefault();
				$('#tree').tree('select', node.target);
				$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	}
	function remove() {

		var nodes = $('#tree').tree('getChecked');
		var ids = '';
		for ( var i = 0; i < nodes.length; i++) {
			if (ids != '')
				ids += ',';
			ids += nodes[i].id;
			//$('#tree').tree('remove',nodes[i].target);
		}
		$.post("demo01.ashx", {
			"ids" : ids,
			"type" : "del"
		}, function(data) {
			InitTreeData();
		});

	}
	function update() {
		var node = $('#tree').tree('getSelected');
		if (node) {
			node.text = '修改'; //-->txt-->DB
			node.iconCls = 'icon-save'; //-->sel-->DB
			$('#tree').tree('update', node);
		}
	}
	function append() {

		var node = $('#tree').tree('getSelected');

		$('#tree').tree('append', {
			parent : (node ? node.target : null),
			data : [ {
				text : 'new1',//  -->txt-->DB
				id : '1',
				checked : true
			} ]
		});

	}

	$(function() {
		InitBaseMenu();
	});

	function InitBaseMenu() {
		$('#tree').tree({
			url : './json/function.json',
			onDblClick : function(node) {
				if ($("#tab_" + node.id).length > 0) {
					return;
				}
				addTab(node);
			},
			onContextMenu : function(e, node) {
				e.preventDefault();
				$('#tree').tree('select', node.target);
				$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	}

	function addTab(node, url) {
		$('#mainTabs').tabs(
				'add',
				{
					id : "tab_" + node.id,
					title : node.text,
					content : '<iframe width="99%" height="99%" src="' + node.url
							+ '"></iframe>',
					closable : true,
					tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							alert('refresh');
						}
					} ]
				});
	}
</script>
<style>
#header-inner {
	text-align: right;
	margin: 0 auto;
	padding: 10px 0;
}
</style>
</head>
<body class="easyui-layout" style="text-align: left">
	<div data-options="region:'north'"
		style="height: 40px; text-align: center">
		<div id="header-inner">
			<table cellpadding="0" cellspacing="0" style="width: 100%;">
				<tr>
					<td style="font-size: 15px;">微信功能管理系统</td>
					<td>欢迎您,<%=loginUser.getUserdesc()%>&nbsp&nbsp<a
						href="./logout.html">注销</a></td>
				</tr>
			</table>

		</div>
	</div>
	<div data-options="region:'west',split:true" title="功能菜单"
		style="width: 300px;">
		<ul id="tree" class="easyui-tree">

		</ul>

	</div>
	<div data-options="region:'center'">
		<div id="mainTabs" class="easyui-tabs" fit="true" border="false"
			plain="true">
			<div title="About" style="padding: 10px">
				<p style="font-size: 14px">jQuery EasyUI framework helps you
					build your web pages easily.</p>
				<ul>
					<li>easyui is a collection of user-interface plugin based on
						jQuery.</li>
					<li>easyui provides essential functionality for building
						modem, interactive, javascript applications.</li>
					<li>using easyui you don't need to write many javascript code,
						you usually defines user-interface by writing some HTML markup.</li>
					<li>complete framework for HTML5 web page.</li>
					<li>easyui save your time and scales while developing your
						products.</li>
					<li>easyui is very easy but powerful.</li>
				</ul>
			</div>
			<div title="Help" data-options="iconCls:'icon-help',closable:true"
				style="padding: 10px">This is the help content.</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div onclick="append()" iconcls="icon-add">添加节点</div>
		<div onclick="remove()" iconcls="icon-remove">删除节点</div>
		<div onclick="update()" iconcls="icon-edit">修改节点</div>
	</div>
</body>
</html>