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
<script type="text/javascript"
	src="http://libs.baidu.com/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://libs.baidu.com/jqueryui/1.8.22/jquery-ui.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="http://libs.baidu.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link href="css/login.css" rel="stylesheet">
<script type="text/javascript" src="js/admin.jquery.js"></script>
</head>

<body>
	<div id="login_area">
		
		<div id="login_password">
			<span id="u_msg">*</span><span id="up">请输入授权码：</span> <input
				type="password" id="password" name="password" maxlength="16" />
		</div>
		<div id="login_button">
			<button id="submit" class="btn btn-primary">确认</button>
		</div>
		<div id="error_msg"></div>
	</div>
</body>
</html>
