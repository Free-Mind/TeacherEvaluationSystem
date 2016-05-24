$(function(){
	var url = "data/getUser";
	$.ajax({
		url:url,
		contentType : 'application/json;charset=utf-8',
		type:"get",
		success:function(data){
			var user = data.msg;
			$("#userinfo").html("欢迎您，"+user);
		}
	});
	LoadSelfinfo();
	
	
	$("#selfinfo").on("click",function(){
		LoadSelfinfo();
	});
	
	$("#addcourse").on("click",function(){
		LoadAddCoursePage();
	});
	$("#logout").on("click",function(){
		$.ajax({
			url: 'data/Logout',
			contentType : 'application/json;charset=utf-8',
			type:"get",
			success:function(data){
				if(data.result){
					alert("退出成功！");
					window.location.href = "login";				
				}else{
					alert("出现未知错误！");
				}				
			}
		});
	});
	
	$(document).on("click","#addhad_action",function(){
		AddCourseHad();	
		getCourseSelected();
	});
	$(document).on("click","#addnew_action",function(){
		AddCourseNew();
		getCourseSelected();
	});
	$(document).on("click","#search",function(){
		SearchCourse();
	});
	
});



function LoadAddCoursePage(){
	$.get("html/addcourse.html",function(data){
		$("#main").html(data);
	});
	getCourseSelected();
	var url = "data/isCanAdd";
	$.ajax({
		url: url,
		contentType : 'application/json;charset=utf-8',
		type:"get",
		success:function(data){
			if(!data.result){
				$("#course_selecting").css({"display":"none"});			
			}
		}
	});
	
}

function getCourseSelected(){
	var url = "data/getCourseSelected";
	var content = "<tr><td>课程ID</td><td>课程名称</td><td>培训层次</td><td>课程类型</td><td>课时</td><td>授课时间</td></tr>";
	$.ajax({
		url: url,
		contentType : 'application/json;charset=utf-8',
		type:"get",
		success:function(data){
			if(data.result){
				var carr = data.t;
				for(var i=0;i<carr.length;i++){
					content += "<tr><td>"+carr[i].id+"</td>";
					content += "<td>"+carr[i].courseName + "</td>";
					content += "<td>"+carr[i].level + "</td>";
					if(carr[i].courseType)
						content += "<td>选修</td>";
					else
						content += "<td>必修</td>";
					content += "<td>"+carr[i].courseHour + "</td>";
					content += "<td>"+DtToDate(carr[i].startTime) + "</td></tr>";
				}
				$("#course_selected_table").html(content);	
			}
		}
	});
}

function AddCourseHad(){
	var courseid = $("#courseid").val();
	var url = "data/addCourseHad";
	var object = courseid;
	$.ajax({
		url: url,
		data: JSON.stringify(object),
		contentType : 'application/json;charset=utf-8',
		type:"post",
		success:function(data){
			if(data.result){
				alert("新增课程成功！");
				$("#courseid").val("");
			}else{
				alert(data.msg);
			}	
		}
	});
}

function AddCourseNew(){
	var coursename = $("#coursename").val();
	var level = $("#level option:selected").text();
	var coursetype = $("#course_type option:selected").val();
	var coursetime = $("#ctime").val();
	var coursestart = $("#cstart").val();
	
	if(coursetype == "0"){
		coursetype = 0;
	}else{
		coursetype = 1;
	}
	var object = {
		courseName : coursename,
		level : level ,
		courseType : coursetype,
		courseHour : coursetime,
		startTime : coursestart
	};
	var url = "data/addCourseNew";
	$.ajax({
		url : url,
		data: JSON.stringify(object), 
		contentType : 'application/json;charset=utf-8',
		type:"post",
		success:function(data){
			if(data.result){
				alert("新增课程成功！");
				$("#coursename").val("");
				$("#ctime").val("");
				$("#cstart").val("");
			}else{
				alert(data.msg);
			}	
		}
	});
}


function SearchCourse(){
	var coursename = $("#coursename_search").val();
	var level = $("#level_search option:selected").text();
	var coursetype = $("#course_type_search option:selected").val();
	var coursetime = $("#ctime_search").val();
	var coursestart = $("#cstart").val();
	if(coursetype == "0"){
		coursetype = 0;
	}else if(coursetype == "1"){
		coursetype = 1;
	}else{
		coursetype = null;
	}
	if(level == "全部"){
		level = null;
	}
	var object = {
		courseName : coursename,
		level : level ,
		courseType : coursetype,
		courseHour : coursetime,
		startTime : coursestart
	};
	var url = "data/searchCourse";
	var content = "<table border=\"1\" align=\"center\"><tr><td>课程ID</td><td>课程名称</td><td>培训层次</td><td>课程类型</td><td>课时</td><td>授课时间</td></tr>";
	$.ajax({
		url : url,
		data: JSON.stringify(object), 
		contentType : 'application/json;charset=utf-8',
		type:"post",
		success:function(data){
			if(data.result){
				var carr = data.t;
				for(var i=0;i<carr.length;i++){
					content += "<tr><td>"+carr[i].id+"</td>";
					content += "<td>"+carr[i].courseName + "</td>";
					content += "<td>"+carr[i].level + "</td>";
					if(carr[i].courseType)
						content += "<td>选修</td>";
					else
						content += "<td>必修</td>";
					content += "<td>"+carr[i].courseHour + "</td>";
					content += "<td>"+DtToDate(carr[i].startTime) + "</td></tr>";
				}
				content += "</table>";
				$("#search_result_area").html(content);
			}	
		}
	});
}

function DtToDate(time){
	var time = new Date(time);
	var year = time.getUTCFullYear();
	var month = time.getUTCMonth()+1;
	var day = time.getUTCDate()+1;
	var date = year + "-" + month + "-" + day;
	return date;
}

