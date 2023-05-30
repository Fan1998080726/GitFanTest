$(function(){
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
})
/**
 * 
 * Description:添加视频
 */
function addPage(){
	$.dialog({
		id: 'videoViewAdd', //最好传入，否则不能弹出多个dialog
		url:'videoview!videoViewAdd.action',
		title: '添加视频', 
		width: 400, 
		height: 250,
		data:{
		}
	});
}



/**
 * 
 * Description:查看视频
 */
function showDetail(id){
	$.dialog({
		id: 'videoViewDetail', //最好传入，否则不能弹出多个dialog
		url:'videoview!videoViewDetail.action?id='+id,
		title: '查看视频', 
		width: 600, 
		height:500,
		data:{
		}
	});
}

/**
 * 
 * Description:刷新页面
 * @param data
 */
function getChildData(data){
//	if(data.flag="videoViewAdd"){
//		window.location.href = 'videoview!videoViewList.action';
		window.location.reload();
//	}
}
/**
 * 
 * Description:删除操作
 */
function del(){
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除？",
				function(){
			showResult();// 确认按钮回调方法
		}, function(){
			$.alert("您取消了删除操作！");
		});
		}else {
			$.alert("请选择要删除的条目！");
			return;
		}
}
function showResult(){
	var options = {
		    url:'videoview!delVideoView.action',
		    dataType:'json',
		    success: function(data) {
		    	if(data.flag == "success"){
		    		//window.location.href='videoview!videoViewList.action';
		    		window.location.reload();
		    	}else{
		    		$.alert(data.message);
		    	}
		    },error:function(data){
		    	$.alert("删除失败！");
		    } };
	
	$('#form').ajaxSubmit(options);
}
/**
 * 
 * Description:弹出修改
 */
function editpage(){
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		if (a == 1) {
			var ids = "";
			$("input[name='selectFlag']:checked").each(function() {
				ids += $(this).val();
			});
			
			$.dialog({
				id: 'videoViewUpd', //最好传入，否则不能弹出多个dialog
				url:'videoview!videoViewUpd.action?id='+ ids,
				title: '编辑视频信息', 
				width: 400, 
				height: 250,
				data:{
				}
			});
		} else {
			$.alert("请选择一条信息进行编辑！");
		}

	} else {
		$.alert("请选择要编辑的信息");
	}

}