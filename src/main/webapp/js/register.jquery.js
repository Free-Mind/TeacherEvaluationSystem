var f_username,f_password,f_password2,f_name,f_org,f_title,f_birth;
var warning = "请检查输入！";
var da;
$(function(){
	$("input").blur(function(){
		var id = $(this).attr("id");
		if(id == "sex" || id == "type")
			return;
		//alert(id);
		var value = $("#"+id).val();
		if(value != ""){
			if(id == "username"){
				//用户名是否有效 即数据库中是否存在该用户名
				checkUsername(value,id);
			}else if(id == "password2"){
				var p1 = $("#password").val();
				if(value == p1 && value != ""){
					drawTag(id,true);
					f_password2 = true;
				}else{
					drawTag(id,false);
				}
			}else{
				drawTag(id,true);
				if(id == "password")
					f_password = true;
				if(id == "name")
					f_name = true;
				if(id == "birthday")
					f_birth = true;
				if(id == "organization")
					f_org = true;
				if(id == "tech_title")
					f_title = true;
			}
		}else{
			drawTag(id,false);
		}
	});
	
	$("#submit").on("click",function(){
		var sex = $('input[name="sex"]:checked').val();
		var type = $('input[name="type"]:checked').val();
		if(sex == undefined){
			alert(warning);
			return;
		}
			
		if(type == undefined){
			alert(warning);	
			return;
		}
			
		//alert(sex+type);
		if(f_username && f_password && f_password2 && f_name && f_org && f_title && f_birth){
			var object = {
				id : $("#username").val(),
				password : hex_sha1($("#password").val()),
				name : $("#name").val(),
				organization : $("#organization").val(),
				techTitle : $("#tech_title").val(),
				sex : sex,
				birthday : $("#birthday").val(),
				type : type
			};
			var url = "data/registerForTeacher";
			
			$.ajax({
				url:url,
				data:JSON.stringify(object),
				contentType : 'application/json;charset=utf-8',
				type:"post",
				success:function(data){
					if(data.result){
						alert("注册成功！");
						window.location.href = "login";
					}else{
						$("#error_msg").html(data.msg);
					}
				}
			});
			
			//var data = AjaxWithData(object,url);
			
		}else{
			alert(warning);
		}
	});
	
	$("#return").on("click",function(){
		window.location.href = "login";
	});
});
function drawTag(id,status){
	if(status){
		$("#"+id+"_tag").html("<i class=\"fa fa-check\"></i>");
	}else{
		$("#"+id+"_tag").html("<i class=\"fa fa-close\"></i>");
	}
}
function checkUsername(username,id){
	var url = "data/checkUsername";
	var object = {username:username};
	//setTimeout(AjaxWithData(object,url),1000);
	
	$.ajax({
		url:url,
		data:JSON.stringify(object),
		contentType : 'application/json;charset=utf-8',
		type:"post",
		success:function(data){
			if(data.result){
				drawTag(id,true);
				f_username = true;
			}
			else
				drawTag(id,false);
		}
	});
	
	//alert(data.result);
	/*
	if(da.result){
		drawTag(id,true);
		return;
	}
	$("#error_msg").html(da.msg);
	drawTag(id,false); */
}