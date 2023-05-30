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
});
/**
 * 
 * Description:查看三维实景
 * @param id 三维实景id
 * @author txb
 */
function showDetail(id){
	$.dialog({
		id: 'realViewDetail', //最好传入，否则不能弹出多个dialog
		url:'realView!realViewDetail.action?id='+id,
		title: '查看三维实景', 
		width: 600, 
		height: 490,
		data:{
		}
	});
}
/**
 * 
 * Description:添加三维实景
 * @author txb
 */
function addPage(){
	$.dialog({
		id: 'realViewAdd', //最好传入，否则不能弹出多个dialog
		url:'realView!realViewAdd.action',
		title: '添加三维实景', 
		width: 400, 
		height: 250,
		data:{
		}
	});
}
/**
 * 
 * Description:刷新页面
 * @param data
 * @author txb
 */
function getChildData(data){
//	if(data.flag="realViewAdd"){
		//window.location.href = 'realView!realViewList.action';
		window.location.reload();
//	}
}
/**
 * 
 * Description:删除操作
 * @author txb
 */
function del(){
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除变更请求？",
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
		    url:'realView!delRealView.action',
		    dataType:'json',
		    success: function(data) {
		    	if(data.flag == "success"){
//		    		window.location.href='realView!realViewList.action';
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
 * @author txb
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
				id: 'realViewUpd', //最好传入，否则不能弹出多个dialog
				url:'realView!realViewUpd.action?id='+ ids,
				title: '编辑三维实景', 
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