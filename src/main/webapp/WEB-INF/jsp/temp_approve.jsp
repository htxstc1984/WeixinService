<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="gbk">
<title>dialog demos</title>
<!--根据JQuery.UI的例子改写，by wallimn, 2012-08-01-->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<style>
body {
	font-size: 10px;
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
</style>
<script>
	$(function() {
		var name = $("#name"), email = $("#email"), password = $("#password"), rowindex = $("#rowindex"), allFields = $(
				[]).add(name).add(email).add(password).add(rowindex);

		$("#dialog-form")
				.dialog(
						{
							autoOpen : false,
							height : 280,
							width : 350,
							modal : true,
							buttons : {
								"Create/Edit" : function() {
									if (rowindex.val() == "") {//新增
										$("#users tbody")
												.append(
														"<tr>"
																+ "<td>"
																+ name.val()
																+ "</td>"
																+ "<td>"
																+ email.val()
																+ "</td>"
																+ "<td>"
																+ password
																		.val()
																+ "</td>"
																+ '<td><button class="EditButton" >Edit</button><button class="DeleteButton">Delete</button></td>'
																+ "</tr>");
										bindEditEvent();
									} else {//修改
										var idx = rowindex.val();
										var tr = $("#users>tbody>tr").eq(idx);
										//$("#debug").text(tr.html());
										tr.children().eq(0).text(name.val());
										tr.children().eq(1).text(email.val());
										tr.children().eq(2)
												.text(password.val());
									}
									$(this).dialog("close");
								},
								Cancel : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								//allFields.val( "" ).removeClass( "ui-state-error" );
								;
							}
						});

		function bindEditEvent() {
			//绑定Edit按钮的单击事件
			$(".EditButton").click(function() {
				var b = $(this);
				var tr = b.parents("tr");
				var tds = tr.children();
				//设置初始值
				name.val(tds.eq(0).text());
				email.val(tds.eq(1).text());
				password.val(tds.eq(2).text());

				var trs = b.parents("tbody").children();
				//设置行号，以行号为标识，进行修改。
				rowindex.val(trs.index(tr));

				//打开对话框
				$("#dialog-form").dialog("open");
			});

			//绑定Delete按钮的单击事件
			$(".DeleteButton").click(function() {
				var tr = $(this).parents("tr");
				tr.remove();
			});
		}
		;

		bindEditEvent();

		$("#create-user").button().click(function() {
			//清空表单域
			allFields.each(function(idx) {
				this.value = "";
			});
			$("#dialog-form").dialog("open");
		});
	});
</script>
</head>
<body>

	<div class="demo">

		<div id="dialog-form" title="Create/Edit User">
			<form>
				<fieldset>
					<label for="name">Name</label> <input type="text" name="name"
						id="name" class="text" /> <label for="email">Email</label> <input
						type="text" name="email" id="email" value="" class="text" /> <label
						for="password">Password</label> <input type="password"
						name="password" id="password" value="" class="text" /> <input
						type="hidden" name="rowindex" id="rowindex" value="" />
				</fieldset>
			</form>
		</div>


		<div id="users-contain">
			<h1>Existing Users:</h1>
			<table id="users">
				<thead>
					<tr class="ui-widget-header ">
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
						<th style="width: 12em;">Operation</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>wallimn</td>
						<td>wallimn@sohu.com</td>
						<td>wallimn</td>
						<td><button class="EditButton">Edit</button>
							<button class="DeleteButton">Delete</button></td>
					</tr>
					<tr>
						<td>John Doe</td>
						<td>john.doe@example.com</td>
						<td>johndoe</td>
						<td><button class="EditButton">Edit</button>
							<button class="DeleteButton">Delete</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<button id="create-user">Create new user</button>

	</div>
	<!-- End demo -->
	<div id="debug"></div>
</body>
</html>
