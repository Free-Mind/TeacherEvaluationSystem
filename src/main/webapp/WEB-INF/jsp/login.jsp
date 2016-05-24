<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>教师评价系统登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-2.2.1.min.js"></script>
<link href="css/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sha1.js"></script>

<link href="css/login.css" rel="stylesheet">
<script type="text/javascript" src="js/login.jquery.js"></script>
</head>

<body>
	<div id="login_area" >
		<div id="user_type">
			<span id="u_msg">*</span><span id="up">账户类型：</span>
			<span id="up">系统管理员</span><input type="radio" name="radio" id="admin" checked="checked"/>
			<span id="up">普通用户</span><input type="radio" name="radio" id="user"/>
		</div>
		<div id="admin-wrap">
			<span id="up">邀请码：</span><input id="code" maxlength="16" type="text"/>
		</div>
		<div id="scorer-login-type">
			<div id="login_username">
			<span id="u_msg">*</span><span id="un">账号：</span> <input type="text"
				id="username" name="username" maxlength="16" />
			</div>
			<div id="login_password">
			<span id="u_msg">*</span><span id="up">密码：</span> <input
				type="password" id="password" name="password" maxlength="16" />
			</div>
		</div>
		<div id="login_button">
			<button id="submit" class="btn">登录</button>
			<button id="register" class="btn">注册</button>
		</div>
		<div id="error_msg"></div>
	</div>
</body>
</html>
