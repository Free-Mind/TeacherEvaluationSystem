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

<title>教师评价系统注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="http://libs.baidu.com/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/sha1.js"></script>
<script type="text/javascript"
	src="http://libs.baidu.com/jqueryui/1.8.22/jquery-ui.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="http://libs.baidu.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link href="css/register.css" rel="stylesheet">
<script type="text/javascript" src="js/register.jquery.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
	<div id="title"></div>
	<div id="main">
		<div id="line1">
			<span id="redstar">*</span><span id="info">&nbsp;账号：　　</span>
			<input type="text" id="username" maxlength="16" /><!--  <i class="fa fa-check"></i>-->
			<div id="username_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line2">
			<span id="redstar">*</span><span id="info">&nbsp;密码：　　</span>
			<input type="password" id="password" maxlength="16" /><!--<i class="fa fa-close"></i>-->
			<div id="password_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line3">
			<span id="redstar">*</span><span id="info">&nbsp;确认密码：</span>
			<input type="password" id="password2" maxlength="16" />
			<div id="password2_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line4">
			<span id="redstar">*</span><span id="info">&nbsp;姓名：　　</span>
			<input type="text" id="name" maxlength="16" />
			<div id="name_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line5">
			<span id="redstar">*</span><span id="info">&nbsp;工作单位：</span>
			<input type="text" id="organization" maxlength="16" />
			<div id="organization_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line6">
			<span id="redstar">*</span><span id="info">&nbsp;职称：　　</span>
			<input type="text" id="tech_title" maxlength="16" />
			<div id="tech_title_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line7">
			<span id="redstar">*</span><span id="info">&nbsp;性别：　　　</span>
			<input type="radio" name="sex" id="sex" value="true"  />男　
			<input type="radio" name="sex" id="sex" value="false" />女　
			<div id="sex_tag"></div>
		</div>
		<div id="line8">
			<span id="redstar">*</span><span id="info">&nbsp;出生日期：</span>
			<input type="date" id="birthday"  style="height:25px;"/>
			<div id="birthday_tag" style="width:20px;float:right;"></div>
		</div>
		<div id="line9">
			<span id="redstar">*</span><span id="info">&nbsp;账号类型：　</span>
			<input type="radio" name="type" id="type" value="teacher" />教师
			<input type="radio" name="type" id="type" value="scorer" />评分员
			<input type="radio" name="type" id="type" value="dean" />教务主任
			<input type="radio" name="type" id="type" value="admin_uncheck" />管理员
			<div id="type_tag"></div>
		</div>
		<div id="button_area">
			<button id="submit" class="btn btn-default">提交</button>
			<!--  <button id="reset" class="btn">重置</button>-->
			<button id="return" class="btn">登录</button>
		</div>
		<div id="error_msg"></div>
	</div>
</body>
</html>
