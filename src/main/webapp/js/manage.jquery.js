$(function(){
	getSW();
	$(document).on("click","#search",function(){
		SearchCourse();
	});
	
	$("#scoresubmit").on("click",function(){
		var p1i = $("#p1").val();
		var p2i = $("#p2").val();
		var p3i = $("#p3").val();
		var object = {
			p1I : p1i,
			p2I : p2i,
			p3I : p3i
		};
		var url = "data/changeSW";
		$.ajax({
			url:url,
			data:JSON.stringify(object), 
			contentType : 'application/json;charset=utf-8',
			type:"post",
			success:function(data){
				if(data.result){
					alert("修改成功！");
					getSW();
				}
			}
		});
	});
	
	$("#coursesubmit").on("click",function(){
		var cid = $("#cid").val();
		var cw = $("#cw").val();
		var object = {
			cId : cid,
			courseWeight : cw
		};
		var url = "data/changeCW";
		$.ajax({
			url:url,
			data:JSON.stringify(object), 
			contentType : 'application/json;charset=utf-8',
			type:"post",
			success:function(data){
				if(data.result){
					alert("修改成功！");
				}
			}
		});
	});
	
	
	
});


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

function getSW(){
	var url = "data/getScoreWeight";
	$.ajax({
		url:url,
		contentType : 'application/json;charset=utf-8',
		type:"post",
		success:function(data){
			if(data.result){
				var scoreweight = data.t;
				$("#p1").val(scoreweight.p1I);
				$("#p2").val(scoreweight.p2I);
				$("#p3").val(scoreweight.p3I);
			}
		}
	});
}
