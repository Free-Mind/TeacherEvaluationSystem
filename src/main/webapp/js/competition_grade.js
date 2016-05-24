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
			teacher_grade.grade = parseInt(grade_data[j].value);
			result_data[j] = teacher_grade;
		}
		$.ajax({
			type : "POST",
			url : "data/submitCompetitionScore",
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

