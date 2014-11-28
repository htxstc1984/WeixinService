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
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function g(id) {
		return document.getElementById(id);
	}
	function arrayBuffer2String(buf, offset, len) {
		if (len == 0)
			return "";
		if (offset >= buf.byteLength)
			return "";
		var tmp = new Uint16Array(new ArrayBuffer(len));
		var src = new DataView(buf, offset, len);
		for ( var i = 0; i < tmp.length; i++) {
			tmp[i] = src.getUint16(i * 2, false);
		}

		return String.fromCharCode.apply(null, tmp);
	}
	function setString2ArrayBuffer(view, offset, str) {
		var l = str.length * 2;
		view.setUint32(offset, l, false); // len
		offset += 4;
		for ( var i = 0; i < str.length; i++) {
			view.setUint16(offset, str.charCodeAt(i), false);
			offset += 2;
		}
		return l + 4;
	}
	function decodeFrame(data) {
		if (data.byteLength < 24)
			return undefined;

		var view = new DataView(data);
		var totalLength = view.getUint32(0);
		if (totalLength != data.byteLength)
			return undefined;

		var offset = 4;
		var msg = {};
		msg.ts = view.getUint32(offset);
		offset += 4;

		var strLen = view.getUint32(offset);
		offset += 4;
		msg.type = arrayBuffer2String(data, offset, strLen);
		offset += strLen;

		strLen = view.getUint32(offset);
		offset += 4;
		msg.user = arrayBuffer2String(data, offset, strLen);
		offset += strLen;

		strLen = view.getUint32(offset);
		offset += 4;
		msg.content = arrayBuffer2String(data, offset, strLen);
		offset += strLen;

		console.info("decoded frame: " + JSON.stringify(msg));
		return msg;
	}
	function encodeFrame(msg) {
		var buf = new ArrayBuffer(512);
		var view = new DataView(buf);
		var offset = 0;
		view.setUint32(offset, 0, false); // total
		offset += 4;
		view.setUint32(offset, 0, false); // ts
		offset += 4;

		offset += setString2ArrayBuffer(view, offset, msg.type);

		offset += setString2ArrayBuffer(view, offset, msg.user);

		offset += setString2ArrayBuffer(view, offset, msg.content);
		view.setUint32(0, offset, false);

		return buf.slice(0, offset);
	}

	var sock = undefined;
	var S = (function() {
		this.olCounts = 0;
		this.updateOnlineCounts = function(delta) {
			S.olCounts += delta;
			g("rOnlineCount").textContent = S.olCounts.toString();
		};
		this.addOnlineUser = function(user, strTs, autoUpdateCounts) {
			if (g("user_" + user))
				return;
			var li = document.createElement("li");
			li.id = "user_" + user;
			li.textContent = user + "(登录于" + strTs + ")";
			g("rOnlineUsers").appendChild(li);
			if (autoUpdateCounts) {
				S.updateOnlineCounts(1);
			}
		};
		this.removeOnlineUser = function(user, autoUpdateCounts) {
			var li = g("user_" + user);
			if (li) {
				li.parentNode.removeChild(li);
				if (autoUpdateCounts)
					S.updateOnlineCounts(-1);
			}
		};
		this.clear = function() {
			S.olCounts = 0;
			g("rOnlineCount").textContent = S.olCounts.toString();
			g("rOnlineUsers").innerHTML = "";
			;
		};
		this.flushOnlineUsers = function() {
			G.request("http://172.18.0.147:9999/online-users", function(data) {
				var ul = JSON.parse(data);
				S.clear();
				for ( var i = 0; i < ul.length; i++) {
					S.addOnlineUser(ul[i].user, G.ts2TimeString(ul[i].ts),
							false);
				}
				S.updateOnlineCounts(ul.length);
			});
		}
		return this;
	})();
	var G = (function() {
		this.ts2TimeString = function(ts) {
			var d = new Date(ts * 1000);
			return [ d.getFullYear(), d.getMonth() + 1 > 9 ? '-' : '-0',
					d.getMonth() + 1, d.getDate() > 9 ? '-' : '-0',
					d.getDate(), d.getHours() > 9 ? ' ' : ' 0', d.getHours(),
					d.getMinutes() > 9 ? ':' : ':0', d.getMinutes(),
					d.getSeconds() > 9 ? ':' : ':0', d.getSeconds() ].join("");
		};
		this.request = function(url, callback) {
			var xhr = new XMLHttpRequest();
			xhr.open("GET", url, false);
			if (callback) {
				xhr.onreadystatechange = function(e) {
					if (xhr.readyState == 4 && xhr.status == 200)
						callback.apply(undefined, [ xhr.responseText ]);
				};
			}
			xhr.send();
		};
		this.onUserEnter = function(user, ts) {
			var strTs = G.ts2TimeString(ts);
			var li = document.createElement("li");
			li.className = "chatinfo";
			li.textContent = user + " 加入了聊天室 " + strTs;
			g("chatbox").appendChild(li);

			S.addOnlineUser(user, strTs, true);
		};
		this.showMessage = function(user, ts, msg) {
			var li = document.createElement("li");
			li.className = "chatmsg";
			var line1 = document.createElement("div");
			line1.textContent = user + " " + G.ts2TimeString(ts);
			var line2 = document.createElement("p");
			line2.textContent = msg;
			li.appendChild(line1);
			li.appendChild(line2);
			var box = g("chatbox");
			box.appendChild(li);
			box.scrollTop = box.scrollHeight;
		};
		this.onUserExit = function(user, ts) {
			var li = document.createElement("li");
			li.className = "chatinfo";
			li.textContent = user + " 退出了聊天室 " + G.ts2TimeString(ts);
			g("chatbox").appendChild(li);
			S.removeOnlineUser(user, true);
		};
		return this;
	})();

	function onConnectWebSocket() {
		if (sock) {
			sock.close();
			sock = undefined;
			return;
		}
		var user = g("chatUserName").value;
		if (user == undefined || user == "") {
			alert("用户名不能为空");
			return;
		}
		if (user.length > 20) {
			alert("用户名不能超过20字");
			return;
		}

		sock = new WebSocket("ws://172.18.0.147:9999/chat?name=" + user);
		sock.binaryType = "arraybuffer";
		sock.onopen = function(e) {
			g("sendPublicMsg").disabled = false;
			S.flushOnlineUsers();
			document.title = "【" + user + "】的聊天室";
		}
		sock.onclose = function(e) {
			sock = undefined;
			g("sendPublicMsg").disabled = true;
			g("action").textContent = "加入";
			S.clear();
			document.title = "请登录";
		}
		sock.onmessage = function(e) {
			var msg = decodeFrame(e.data);
			if (msg.type == "login")
				G.onUserEnter(msg.user, msg.ts);
			else if (msg.type == "public")
				G.showMessage(msg.user, msg.ts, msg.content);
			else if (msg.type == "logout")
				G.onUserExit(msg.user, msg.ts);
		}
		sock.onerror = function(e) {
			if (sock) {
				sock.close();
				sock = undefined;
				alert("连接被中断，或用户名 " + g("chatUserName").value + " 已被占用，请重试");
			}
			console.info("error");
		}
		g("action").textContent = "退出";
	}
	function onSendMessage() {
		var msg = g("msgInputBox").value;
		if (msg == "")
			return;
		if (msg.length > 100) {
			alert("单条消息不能超过100字");
			return;
		}
		sock.send(encodeFrame({
			"user" : "",
			"type" : "public",
			"content" : msg
		}));
	}
	window.onload = function() {
		document.title = "请登录";
		S.flushOnlineUsers();
	};
</script>
<style type="text/css">
.chatinfo {
	color: #CCC;
}

.chatmsg>div {
	color: #09F;
}

.chatmsg>p {
	line-height: 1.2em;
	text-indent: 2em;
	margin: 0;
	padding: 0;
}

li {
	list-style: none;
}

#left {
	width: 800px;
	float: left;
}

#chatbox {
	width: 98%;
	overflow-y: scroll;
	overflow-x: hidden;
	height: 600px;
	margin: 2px 2px 5px 2px;
	padding: 5px 5px 5px 10px;
	border: solid 1px #CCC;
}

#chatbox li {
	margin-bottom: 5px;
}

#inputZone {
	text-align: right;
	margin: 2px;
	margin-right: 0;
	padding: 5px;
	padding-right: 0;
}

#msgInputBox {
	width: 731px;
}

#right {
	margin-left: 820px;
}
</style>
</head>

<body>
	<div id="left">
		<div>
			名字：<input id="chatUserName" />
			<button id="action" onclick="onConnectWebSocket()">加入</button>
		</div>
		<ul id="chatbox"></ul>
		<hr />
		<div id="inputZone">
			<input type="text" id="msgInputBox" />
			<button id="sendPublicMsg" onclick="onSendMessage()" disabled>发送</button>
		</div>
	</div>

	<div id="right">
		<h3>
			当前在线(<span id="rOnlineCount">0</span>)
		</h3>
		<ul id="rOnlineUsers"></ul>
	</div>
</body>
</html>