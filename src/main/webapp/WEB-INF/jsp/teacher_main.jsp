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

<title>教师评价系统</title>

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
<script type="text/javascript"
	src="js/getUserinfo.jquery.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="css/teacher_main.css" rel="stylesheet">
<script type="text/javascript" src="js/teacher_main.jquery.js"></script>
</head>

<body>

	<nav class="navbar navbar-default" role="navigation"
		style="width:75%;margin-left:auto;margin-right:auto">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">教师评价系统</a>
		</div>
		<div>
			<ul class="nav navbar-nav" style="float:right;">
				<li class="active"><a href="javascript:void(0)" id="selfinfo">个人信息</a></li>
				<li><a href="javascript:void(0)" id="addcourse">添加课程</a></li>
				<li class="dropdown"><a href="javascript:void(0)"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						id="userinfo">a</span> <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0)" id="logout">退出</a></li>

					</ul></li>
			</ul>
		</div>
	</nav>
	<div id="main"></div>
</body>
</html>
