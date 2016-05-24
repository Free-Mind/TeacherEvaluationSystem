<%@ page language="java" 
import="java.util.*"
import = "com.te.vo.TeacherVO"
import = "com.te.vo.ResultVO"
pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
<title>教师评价系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<head>
	<script type="text/javascript" src="js/jquery-2.2.1.min.js"></script>
	<link href="css/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
	<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<script type="text/javascript" src="css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	<link href="css/super_manage.css" rel="stylesheet">
	<script type="text/javascript" src="js/super_manage.js"></script>
</head>
<body>
	<table border="1">
		<tr><td>姓名</td><td>性别</td><td>单位</td><td>职称</td><td>设置为管理员</td></tr>
		<%
			ResultVO result = (ResultVO)request.getAttribute("result");
			TeacherVO[] datas = (TeacherVO[])result.getT();
			for(int i=0;i<datas.length;i++){
		 %>
		 <tr>
		 	<td><% out.print(datas[i].getName()); %></td>
		 	<td><% out.print(datas[i].getSex()); %></td>
		 	<td><% out.print(datas[i].getOrganization()); %></td>
		 	<td><% out.print(datas[i].getTechTitle()); %></td>
		 	<td><button class="button" data-id=<% out.print(datas[i].getId());%>>设置</button></td>
		 </tr>
		 <%} %>
	</table>
</body>
</html>