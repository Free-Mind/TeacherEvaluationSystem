var user_type= '';
function LoadSelfinfo(){
	$.get("html/selfinfo.html",function(data){
		$("#main").html(data);
	});
	var url = "data/getUserinfo"
	$.ajax({
		url:url,
		contentType : 'application/json;charset=utf-8',
		type:"get",
		success:function(data){
			$("#username").text(data.t.id);
			$("#name").text(data.t.name);
			$("#org").text(data.t.organization);
			$("#tech_title").text(data.t.techTitle);
			if(data.t.sex)
				$("#sex").text("男");
			else
				$("#sex").text("女");
			var time = new Date(data.t.birthday);
			var year = time.getUTCFullYear();
			var month = time.getUTCMonth()+1;
			var day = time.getUTCDate()+1;
			var birth = year + "-" + month + "-" + day
			$("#birth").text(birth);
			var type = data.t.type;
			user_type = type;
			if(type == "teacher")
				$("#type").text("老师");
			else if(type == "scorer")
				$("#type").text("评分员");
			else if(type == "admin")
				$("#type").text("管理员");
			else if(type == "dean")
				$("#type").text("教导主任");
		}
	});
}