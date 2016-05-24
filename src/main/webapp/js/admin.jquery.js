$(function(){
	$("#submit").on("click",function(){
		var psw = $("#password").val();
		obj = {psw:psw};
		var url = "data/checkpsw";
		$.ajax({
			url:url,
			data:JSON.stringify(obj),
			contentType : 'application/json;charset=utf-8',
			type:"post",
			success:function(data){
				var msg = data.msg;
				if(data.result){
					window.location.href = msg;
				}else{
					$("#error_msg").html("<p>"+msg+"</p>");
					$("#password").val("");
				}
			}
		});
	});
});