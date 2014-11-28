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
	function remove() {

		var nodes = $('#tree').tree('getChecked');
		var ids = '';
		for ( var i = 0; i < nodes.length; i++) {
			if (ids != '')
				ids += ',';
			ids += nodes[i].id;
			//$('#tree').tree('remove',nodes[i].target);
		}

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
	var menuData;
	$(function() {
		//$.getJSON("./testJson.html", function(data) {
		//	menuData = data;
		//	createMenuTree(data);
		//});
	});

	function loadJsonData() {
		eval("var newData=" + $('#menuJson')[0].value);
		createMenuTree(newData);
	}

	function createMenuTree(data) {
		$.each(data.button, function(infoIndex, button) {
			transNode(button, 1);
		});
		$('#tree').tree({
			data : data.button,
			checkbox : true,
			onClick : function(node) {
				$('#detail').form('load', {
					name : node.name,
					type : node.type,
					url : node.url,
					key : node.key
				});
			},
		//onContextMenu : function(e, node) {
		//	e.preventDefault();
		//	$('#tree').tree('select', node.target);
		//	$('#mm').menu('show', {
		//		left : e.pageX,
		//		top : e.pageY
		//	});
		//}
		});
	}

	function transNode(object, type) {
		if (type == 1) {
			object.id = object.name;
			object.text = object.name;
			if (object.sub_button) {
				object.children = object.sub_button;
				$.each(object.children, function(infoIndex, child) {
					transNode(child, 1);
				});
			}
		}
		if (type == 2) {
			delete object.id;
			delete object.text;
			if (object.children) {
				object.sub_button = object.children;
				$.each(sub_button, function(infoIndex, child) {
					transNode(child, 2);
				});
				delete object.children;
			}
		}

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
	<div data-options="region:'west',split:true" title="菜单目录"
		style="width: 300px;">
		<ul id="tree" class="easyui-tree">

		</ul>

	</div>
	<div data-options="region:'center'">
		<div class="easyui-layout" style="text-align: left">
			<table>
				<tr>
					<td>
						<form id="detail" method="post">
							<table cellpadding="5">
								<tr>
									<td>类型:</td>
									<td><select disabled="disabled" class="easyui-combobox" name="type" id="type">
											<option value="click">click</option>
											<option value="view">view</option>
									</select></td>
								</tr>
								<tr>
									<td>文字:</td>
									<td><input disabled="disabled" class="easyui-validatebox" type="text"
										name="name" id="name" data-options="required:true"></input></td>
								</tr>
								<tr>
									<td>关键字(click类型):</td>
									<td><input disabled="disabled" class="easyui-validatebox" type="text"
										name="key" id="key"></input></td>
								</tr>
								<tr>
									<td>链接(view类型):</td>
									<td><input disabled="disabled" class="easyui-validatebox" type="text"
										name="url" id="url"></input></td>
								</tr>
								<tr>
									<td><input type="button" onclick="loadJsonData()" value="加载"></input></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				<tr>
					<td><textarea style="height: 350px; width: 700px"
							required="required" cols="30" rows="100" maxlength='3000'
							placeholder="请输入菜单代码" name="menuJson" id="menuJson"></textarea></td>
				</tr>
			</table>

		</div>

	</div>

	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div onclick="append()" iconcls="icon-add">添加节点</div>
		<div onclick="remove()" iconcls="icon-remove">删除节点</div>
		<div onclick="update()" iconcls="icon-edit">修改节点</div>
	</div>
</body>
</html>