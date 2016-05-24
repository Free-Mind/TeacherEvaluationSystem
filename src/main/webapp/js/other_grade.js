$(function(){
	//用来记录所有输入的合法性；
	var grade_data = new Array();
	$("input").blur(function(){
		var val = $(this).val();
		var id  = $(this).attr("id");
		var i = parseInt($(this).data("i"));
		var result = check(val);
		if(result){
			$(this).parent().find("#right").show();
			$(this).parent().find("#false").hide();
		}else{
			$(this).parent().find("#false").show();
			$(this).parent().find("#right").hide();
		}
		var obj = new Object();
		obj.id = id;
		obj.status = result;
		obj.value = val;
		grade_data[i] = obj;
	});
	//点击提交按钮
	$("#submit").on("click",function(){
		var result_data = new Array();
		for(var i =0; i < grade_data.length; i++){
			if(!grade_data[i].status){
				alert("输入数据不合法");
				return;
			}
		}
		for(var j=0;j<grade_data.length;j++){
			var teacher_grade = new Object();
			teacher_grade.id = grade_data[j].id;
			teacher_grade.otherGrade = parseInt(grade_data[j].value);
			result_data[j] = teacher_grade;
		}
		$.ajax({
			type : "POST",
			url : "data/submitOtherScore",
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(result_data),
			success : function(msg){
				alert(msg.result);
			}
		});
	});
})

function check(val){
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
