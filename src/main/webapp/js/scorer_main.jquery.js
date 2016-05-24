$(function(){
	//绑定退出事件
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
	
	$.ajax({
		url: 'data/getUser',
		contentType : 'application/json;charset=utf-8',
		type:"get",
		success:function(data){
			if(data.result){
				var name = data.msg;
				$("#usrname").html("欢迎您，"+name);
			}else{
				$("#usrname").html("获取用户名失败！");
			}
		}
	});
	LoadSelfinfo();
	
	$("#selfinfo").on("click",function(){
		LoadSelfinfo();
	});
	
	$("#evaluate").on("click",function(){
		if(user_type == "dean"){
			alert("教导主任不能打分！");
			return;
		}
		$.get("html/selecting.html",function(data){
			$("#main").html(data);
		});
		var content = "<tr><td>姓名</td><td>性别</td><td>单位</td><td>职称</td><td>操作</td></tr>";
		$.ajax({
			url : 'data/getEvaluateTeacher',
			contentType : 'application/json;charset=utf-8',
			type:"post",
			success:function(data){
				if(data.result){
					var tvos = data.t;
					for(var i=0;i<tvos.length;i++){
						content += "<tr><td>"+tvos[i].name+"</td>";
						if(tvos[i].sex)
							content += "<td>男</td>";
						else
							content += "<td>女</td>";
						content += "<td>"+tvos[i].organization+"</td>";
						content += "<td>"+tvos[i].techTitle+"</td>";
						content += "<td> <div id=\"evaluate_click\"><a href=\"javascript:void(0)\" id=\""+tvos[i].id+"\">评价</a></div></td></tr>";
					}
					$("#selecting_table").html(content);
					$("#msg").html("");
				}
			}
		});
	});
	
	
	$(document).on("click","#evaluate_click",function(){
		var id = $(this).find("a").attr("id");
		//alert(id);
		var url = "evaluate?tid="+id;
		window.open(url);
	})
	
	
	$("#queryscore").on("click",function(){
		
		$.get("html/showscore.html",function(data){
			$("#main").html(data);
		});
		var content = "<tr><td>用户名</td><td>姓名</td><td>工作单位</td><td>成绩</td></tr>";
		$.ajax({
			url : 'scorecompute/getAllAvgScore',
			contentType : 'application/json;charset=utf-8',
			type:"post",
			success:function(data){
				if(data.result){
					var list = data.t.s_list;
					var date = DtToDatetime(data.t.date);
					$("#msg_div").append("数据更新于："+date);
					for(var i=0;i<list.length;i++){
						content += "<tr><td>"+list[i].tid+"</td>";
						content += "<td>"+list[i].name+"</td>";
						content += "<td>"+list[i].org+"</td>";
						content += "<td>"+Math.round(list[i].score*100)/100+"</td></tr>";
					}
					$("#score_table").html(content);
					if(user_type == "admin" || user_type == "dean"){
						$("#msg_div").append("<button id='download' class='btn btn-primary'>下载</button>");
					}
					$("#download").on("click",function(){
					window.open("scorecompute/download");
					});
				}
			}
		});
		
	});
	
});

function DtToDatetime(time){
	var time = new Date(time);
	var year = time.getUTCFullYear();
	var month = time.getUTCMonth()+1;
	var day = time.getUTCDate();
	var hour = time.getHours();
	if(hour<10)hour = "0"+hour;
	var min = time.getUTCMinutes();
	if(min<10)min = "0"+min;
	var sec = time.getUTCSeconds();
	if(sec<10)sec = "0"+sec;
	var date = year + "-" + month + "-" + day + " " + hour + ":" + min +":" + sec;
	return date;
}


