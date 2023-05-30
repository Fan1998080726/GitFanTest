$(function() {

})
function doSub() {
	
	if ($('#file').val().length == 0) {
		$.alert("请选择需要上传的文件！");
	} else {
		if (!/^.*?\.(3gp|3GP|avi|AVI|flv|FLV)$/.test($('#file').val()
				.toLowerCase())) {
			$.alert("只能上传3gp、avi、flv格式的文件！");
			return;
		}
		
		var options = {
			url : 'videoview!saveVideoViewAdd.action',
			dataType:'json',
			success : function(data) {
				if (data.flag == "success") {
					var json = {
						'flag' : 'videoViewAdd'
					};
					$.closeDialog(json, 'videoViewAdd');
				} else {
					$.alert(data.message);
				}
			},
			error : function(data) {
				$.alert("操作失败！");
			}
		};
		$('#form').ajaxSubmit(options);
	}
}