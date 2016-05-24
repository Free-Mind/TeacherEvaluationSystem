$(function(){
	$(".button").on("click",function(){
		var id = $(this).data("id");
		obj = new Object();
		obj.adminId = id;
		$.ajax({
			url : "data/submitAdmin",
			data : JSON.stringify(obj),
			type : "post",
			contentType : 'application/json;charset=utf-8',
			success : function(data){
				result = data.result;
				if(result)
					alert("修改成功");
			}
		});
	});
});
