$(function() {

})
/**
 * 
 * Description:
 * @author txb
 */
function doSub() {
	if($('#file').val().length>0){
		if (!/^.*?\.(jpg|jpeg)$/.test($('#file').val()
				.toLowerCase())) {
			$.alert("只能上传jpeg、jpg格式的文件！");
			return;
		}else{
			$.confirm("原文件将被替换,是否继续？",function(){
				submit();
			}, function(){
			});
		}
		
	}else{
		submit();
	}
}
/**
 * 
 * Description:保存修改的内容
 * @author txb
 */
function submit(){
	var options = {
			url : 'realView!saveRealViewUpd.action',
			dataType:'json',
			success : function(data) {
				if (data.flag == "success") {
					var json = {
						'flag' : 'realViewUpd'
					};
					$.closeDialog(json, 'realViewUpd');
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