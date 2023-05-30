$(function() {

})
function doSub() {
	
	if ($('#file').val().length == 0) {
		$.alert("请选择需要上传的文件！");
	} else {
		if (!/^.*?\.(jpg|jpeg)$/.test($('#file').val()
				.toLowerCase())) {
			$.alert("只能上传jpeg、jpg格式的文件！");
			return;
		}
		
		var options = {
			url : 'realView!saveRealViewAdd.action',
			dataType:'json',
			success : function(data) {
				if (data.flag == "success") {
					var json = {
						'flag' : 'realViewAdd'
					};
					$.closeDialog(json, 'realViewAdd');
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