$(function() {

})
/**
 * 
 * Description:
 */
function doSub() {
	if($('#file').val().length>0){
		$.confirm("原文件将被替换,是否继续？",function(){
			if (!/^.*?\.(3gp|3GP|avi|AVI|flv|FLV)$/.test($('#file').val()
					.toLowerCase())) {
				$.alert("只能上传3gp、avi、flv格式的文件！");
				return;
			}else{
				submit();
			}
		}, function(){
		});

	}else{
		submit();
	}
}
/**
 * 
 * Description:保存修改的内容
 */
function submit(){
	var options = {
			url : 'videoview!saveVideoViewUpd.action',
			dataType:'json',
			success : function(data) {
				if (data.flag == "success") {
					var json = {
						'flag' : 'videoViewUpd'
					};
					$.closeDialog(json, 'videoViewUpd');
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