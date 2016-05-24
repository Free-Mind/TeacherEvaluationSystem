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
<link href="http://libs.baidu.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="http://libs.baidu.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link href="css/manage.css" rel="stylesheet">
<script type="text/javascript" src="js/manage.jquery.js"></script>

</head>

<body>
	<div id="title" align="center">
		<h3>管理员界面</h3>
	</div>
	<div id="main">
		<div id="scoreweight">
			<div id="scoretopmsg" align="center">
				<h4>评分权重修改</h4>
			</div>
			<div id="inputarea1" align="center">
				<span>教学准备：</span> <input type="number" maxlength="3" id="p1"
					style="width:80px;height:25px;" /> <span>教学实施：</span> <input
					type="number" maxlength="3" id="p2" style="width:80px;height:25px;" />
				<span>教学效果：</span> <input type="number" maxlength="3" id="p3"
					style="width:80px;height:25px;" />
				<button class="btn btn-default" id="scoresubmit">提交</button>
			</div>
		</div>
		<hr />
		<div id="courseweight">
			<div id="coursetopmsg" align="center">
				<h4>课程权重修改</h4>
			</div>
			<div id="inputarea1" align="center">
				<span>课程号：</span> <input type="text" maxlength="16" id="cid"
					style="width:80px;height:25px;" /> <span>权值：</span> <input
					type="number" maxlength="3" id="cw" style="width:80px;height:25px;" />
				<button class="btn btn-default" id="coursesubmit">提交</button>
			</div>
			<div id="searcharea" align="center">
				<h3>课程搜索</h3>
				<div id="course_searching" align="center">
					<h4>搜索已有课程</h4>
					<div id="search_area" align="center">
						<span>课程名称：</span> <input type="text" maxlength="16"
							name="coursename_search" id="coursename_search"
							style="width:120px;height:25px;" /> <span>培训层次：</span> <select
							id="level_search" style="width:80px;height:25px;">
							<option value="-1">全部</option>
							<option value="0">本科</option>
							<option value="1">轮训</option>
							<option value="2">外训</option>
							<option value="3">研究生</option>
							<option value="4">博士生</option>
						</select> <span>课程类型：</span> <select id="course_type_search" style="width:80px;height:25px;">
							<option value="-1">全部</option>
							<option value="0">必修</option>
							<option value="1">选修</option>
						</select> <span>课时： </span> <input type="number" maxlength="16"
							name="ctime_search" id="ctime_search" style="width:80px;height:25px;" /> <span>开课时间：</span>
						<input type="date" maxlength="16" name="cstart" id="cstart" style="width:150px;height:25px;" />
						<button id="search" class="btn btn-default">搜索</button>
					</div>
					<hr />
					<div id="search_result_area"></div>

				</div>
			</div>
		</div>
	</div>
	<div id="eval-teacher">
		<a href="http://localhost:8080/TeacherEvaluation/scorer_main">为教师打课堂效果分</a>
		</br>
		<a href="http://localhost:8080/TeacherEvaluation/other_grade">为教师打其他项加减分</a>
		</br>
		<a id="get_all_score" href="http://localhost:8080/TeacherEvaluation/scorer_main">查看所有老师总成绩</a>
	</div>
</body>
</html>
