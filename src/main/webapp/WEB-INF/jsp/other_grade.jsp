<%@ page language="java" 
import="java.util.*"
import="com.te.vo.ResultVO"
import="com.te.vo.TeacherVO"
pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/jquery-2.2.1.min.js"></script>
<link href="css/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link href="css/other_grade.css" rel="stylesheet">
<script type="text/javascript" src="js/other_grade.js"></script>

</head>

<body>
	<nav class="navbar navbar-default" role="navigation"
		style="width:75%;margin-left:auto;margin-right:auto">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">教师评价系统</a>
		</div>
	</nav>
	<div id="main">
		<table border="1">
		<tr><td>姓名</td><td>性别</td><td>单位</td><td>职称</td><td>打分</td></tr>
		<%
			ResultVO result = (ResultVO)request.getAttribute("result");
			List<TeacherVO> teachers = (List)result.getT();
			for(int i = 0;i<teachers.size();i++){
		 %>
		 <tr><td><% out.print(teachers.get(i).getName()); %></td>
		 <%
		 	if(teachers.get(i).getSex()){
		  %>
		 	<td>男</td>
		 <% }else{ %>
		 	<td>女</td>
		 <% }%>
		 	<td><% out.print(teachers.get(i).getOrganization()); %></td>
		 	<td><% out.print(teachers.get(i).getTechTitle()); %></td>
		 	<td id="grade">
		 		<div id='block'><div id='scorelabel'>
		 			<span>其他项加减分：</span>
		 			<input data-i="<% out.print(i);%>" class="grade-input" type="number" 
		 				id="<% out.print(teachers.get(i).getId());%>" style='width:50px;'/> 
		 			<i id="right" class="fa fa-check"></i>
		 			<i id="false" class="fa fa-close"></i>
		 		</div>
		 	</td></tr>
		 <% }%>
		 </table>
	</div>
	<div id="submit-wrap">
		<button id="submit">提交</button>
	</div>
	<div>
		
	</div>
</body>
</html>
