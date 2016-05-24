function AjaxWithData(obj,url){
	$.ajax({
		url:url,
		data:JSON.stringify(obj),
		contentType : 'application/json;charset=utf-8',
		type:"post",
		success:function(data){
			da = data;
		}
	});
}