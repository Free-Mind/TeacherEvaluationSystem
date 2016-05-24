$(function(){
	var type = 1;
	$("#submit").on("click",function(){
		//评分员使用用户名密码登录
		if(type == 2){
			var username = $("#username").val();
			var pw_type = $("#password").val();
			if(username == ""){
				$("#error_msg").html("<p>请输入账号！</p>");
				return;
			}
			if(pw_type == ""){
				$("#error_msg").html("<p>请输入密码！</p>");
				return;
			}
			var pw_en = hex_sha1(pw_type);
			var object = {
				username : username,
				password : pw_en
			}
			var url = "data/checkUP";
			$.ajax({
				url:url,
				data:JSON.stringify(object),
				contentType : 'application/json;charset=utf-8',
				type:"post",
				success:function(data){
					var flag  = data.result;
					var msg = data.msg;
					if(flag){
						window.location.href = msg;
					}else{
						$("#error_msg").html("<p>"+msg+"</p>");
						$("#username").val(username);
						$("#password").val("");
					}
				}
			});
		}else{
			var code = $("#code").val();
			if(code == ""){
				$("#error_msg").html("<p>请输入邀请码</p>");
				return;
			}
			obj = new Object();
			obj.psw = code;
			var url = "data/checkpsw";
			$.ajax({
				url:url,
				data:JSON.stringify(obj),
				contentType : 'application/json;charset=utf-8',
				type:"post",
				success:function(data){
					var flag  = data.result;
					var msg = data.msg;
					if(flag){
						window.location.href = msg;
					}else{
						$("#error_msg").html("<p>"+msg+"</p>");
					}
				}
			});
		}
		
	//var data = AjaxWithData(object,url);
	});
	$("#register").on("click",function(){
		window.location.href = "register";
	});
	$("#admin").on("click",function(){
		$("#admin-wrap").show();
		$("#scorer-login-type").hide();
		type = 1;
	});
	$("#user").on("click",function(){
		$("#admin-wrap").hide();
		$("#scorer-login-type").show();
		type = 2;
	});
});

