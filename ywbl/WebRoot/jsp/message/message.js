$(function() {
	$("#checkAll").click(function() {
		if (this.checked) {
			$("input[name='selectFlag']:checkbox").each(function() { // 遍历所有的name为selectFlag的
				// checkbox
				$(this).attr("checked", true);
			})
		} else { // 反之 取消全选
			$("input[name='selectFlag']:checkbox").each(function() { // 遍历所有的name为selectFlag的
				// checkbox
				$(this).attr("checked", false);
			})
		}
	})
});



function del(flag) {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除该信息？",
				function(){
			showResult("yes",flag);// 确认按钮回调方法
		}, function(){
			$.alert("您取消了删除操作！");
		});
		}else {
			$.alert("请选择要删除的信息！");
			return;
		}
}


/**
 * 用户删除确认操作
 * @param btn
 */
function showResult(btn,flag){

	if("yes" == btn){
		
		var options = {
				url : 'message!delMessage.action?mstype='+flag,
				dataType:'json',
				success : function(data) {
					if (data.flag == "success") {
						parent.showCount();
						if("27"==flag){
							window.location.href="message!queryMessagelist.action";
						}else{
							window.location.href="message!queryWarninglist.action";
						}
						
					} else {
						$.alert("操作失败！");
					}
				},
				error : function(data) {
					$.alert("操作失败！");
				}
			};
		$('#listform').ajaxSubmit(options);
		
	}
}
