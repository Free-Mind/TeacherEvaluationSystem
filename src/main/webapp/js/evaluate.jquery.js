var map ;
var val_map ;
var user_type = "";
//需要提交的分数，同时记录当前输入的分数是否合法
$(function(){
	var input_data = new Array();
	map = new Map();
	val_map = new Map();
	var tid = $("#hide_param").text();
	var url = "data/getCourseToEvaluate?tid="+tid;
	$.ajax({
		url : url,
		contentType : 'application/json;charset=utf-8',
		type:"get",
		success:function(data){
			if(data.result){
				var result = data.t;
				var t = result.list;
				var type = result.type;
				for(var i=0;i<t.length;i++){
					var content = "<table border=\"1\"><tr><td>课程名称</td><td>培训层次</td><td>课程类型</td><td>课时</td><td>授课时间</td></tr>";
					content += "<tr><td>"+t[i].courseName+"</td>";
					content += "<td>"+t[i].level+"</td>";
					if(t[i].courseType)
						content += "<td>选修</td>";
					else
						content += "<td>必修</td>";
					content += "<td>"+t[i].courseHour+"</td>";
					var date = DtToDate(t[i].startTime);
					content += "<td>"+date+"</td></tr></table><br />";
					content += "<p>请评价</p>";
					if(type.type == "scorer"){
						user_type = "scorer";
						content += "<div id='block'><div id='scorelabel'><span>教学准备得分：</span><input type=\"number\" id='"+t[i].id+"_p1' style='width:50px;' /><div id='"+t[i].id+"_p1_tag' class='tag'></div></div>";
						content += "<div id='scorelabel2'><span>教学实施得分：</span><input type=\"number\" id='"+t[i].id+"_p2' style='width:50px;' /><div id='"+t[i].id+"_p2_tag' class='tag'></div></div></div><br />";
						var prepare_grade = new Object();
						prepare_grade.id = t[i].id+"_p1";
						prepare_grade.status = false;
						prepare_grade.value = 0;
						
						var implement_grade = new Object();
						implement_grade.id = t[i].id+"_p2";
						implement_grade.status = false;
						implement_grade.value = 0;
						
						var j = i*2;
						input_data[j] = prepare_grade;
						input_data[j+1] = implement_grade;
					}
					if(type.type == "admin"){
						user_type = "admin";
						content += "<div id='block'><div id='scorelabel'><span>教学效果得分：</span><input type=\"number\" id='"+t[i].id+"_p3' style='width:50px;' /><div id='"+t[i].id+"_p3_tag' class='tag'></div></div>";
						var effect_grade = new Object();
						effect_grade.id = t[i].id+"_p3";
						effect_grade.status = false;
						effect_grade.value = 0;
						input_data[i] = effect_grade;
					}
				//	content += "<div id='scorelabel2'><span>其他加减分：&nbsp;&nbsp;&nbsp;</span><input type=\"number\" id='"+t[i].id+"_pl' style='width:50px;' /><div id='"+t[i].id+"_pl_tag' class='tag'></div></div></div><hr />";
					$("#main").append(content);
				}
				$("#main").append("<button id='submit' class='btn btn-primary'>提交</button>");
			}
		}
	});
	
	$(document).on("blur","input",function(){
		var val = $(this).val();
		var type = $(this).attr("id");
		//alert(val+" "+type);
		var result = check1(val);
		drawTag(type,result);
		if(result){
			//当前数据合法可用，更新status数组
			for(i=0;i<input_data.length;i++){
				if(input_data[i].id == type){
					input_data[i].value = parseInt(val);
					input_data[i].status = true;
					return;
				}
			}
		}else{
			//当前数据不合法，更新status数组
			for(i=0;i<input_data.length;i++){
				if(input_data[i].id == type){
					input_data[i].status = false;
					return;
				}
			}
		}
	});
	
	$(document).on("click","#submit",function(){
		var submit_data = new Array();
		var tid = $("#hide_param").text();
		for(i = 0;i < input_data.length;i++){
			if(!input_data[i].status){
				return;
			}
		}
		//当前用户是评分员
		if(user_type == "scorer"){
			for(i=0;i<input_data.length/2;i++){
				new_data = new Object();
				new_data.tid = tid;
				m = i*2;
				new_data.cid = input_data[m].id.split("_")[0];
				new_data.p1 = input_data[m].value;
				new_data.p2 = input_data[m+1].value;
				submit_data[i] = new_data;
			}
		}else{
			//当前用户是管理员
			for(j = 0;j<input_data.length;j++){
				new_data = new Object();
				new_data.tid = tid;
				new_data.cid = input_data[j].id.split("_")[0];
				new_data.p3 = input_data[j].value;
				submit_data[j] = new_data;
			}
		}
		var url = "data/submitScore";
		$.ajax({
			url : url,
			data : JSON.stringify(submit_data),
			contentType : 'application/json;charset=utf-8',
			type:"post",
			success:function(data){
				if(data.result){
					alert("评分成功！");
					window.close();
				}else{
					alert("出现异常，评分失败！");
				}
			}
		});
	});
});
function DtToDate(time){
	var time = new Date(time);
	var year = time.getUTCFullYear();
	var month = time.getUTCMonth()+1;
	var day = time.getUTCDate()+1;
	var date = year + "-" + month + "-" + day;
	return date;
}
function drawTag(id,status){
	if(status){
		$("#"+id+"_tag").html("<i class=\"fa fa-check\"></i>");
		map.container[id] = true;
	}else{
		$("#"+id+"_tag").html("<i class=\"fa fa-close\"></i>");
	}
}
function check1(val){
	if(parseInt(val)==val){
		if(val>=0 && val<=100)
			return true;
		return false;
	}else{
		return false;
	}
}

function check2(val){
	if(parseInt(val)==val){
		if(val>=-10 && val<=10)
			return true;
		return false;
	}else{
		return false;
	}
}

function MapToObject(val_map){
	var size = val_map.size();
	var o_arr = [];
	var tid = $("#hide_param").text();
	var keyset = map.keySet();
	var cid_arr = new Array(size/4);
	for(var key in keyset){
		var temp_arr = keyset[key].split("_");
		var flag = true;
		for(var t in cid_arr){
			if(temp_arr[0] == cid_arr[t])
				flag = false;
		}
		if(flag){
			cid_arr.push(temp_arr[0]);
		}
	}
	for(var i=0;i<cid_arr.length;i++){
		if(cid_arr[i] == undefined)
			continue;
		var object = {
			tid:tid,
			cid:cid_arr[i],
			p1:0,
			p2:0,
			p3:0,
			pl:0
		};
		o_arr.push(object);
	}
	for(var key in keyset){
		var val = val_map.get(keyset[key])
		var key_arr = keyset[key].split("_");
		for(var object in o_arr){
			if(o_arr[object].cid == key_arr[0]){
				o_arr[object][key_arr[1]] = val;
				continue;
			}
		}
	}
	return o_arr;
}



