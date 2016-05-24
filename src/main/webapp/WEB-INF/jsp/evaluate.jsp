<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>教师评价系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-2.2.1.min.js"></script>
<link href="css/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link href="css/evaluate.css" rel="stylesheet">
<script type="text/javascript" src="js/evaluate.jquery.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="js/Map.js"></script>
</head>
<body>
	<div id="hide_param" style="display:none;">${tid}</div>
	<div id="head" align="center">
	<h3>请对该老师的课程进行评价</h3>
	<div id="ps_msg">
	<h4>提示：其他加减分范围为-10~10，其余三项得分范围为0~100</h4>
	</div>
	</div>
	<div id="main" align="center"></div>
</body>
</html>
