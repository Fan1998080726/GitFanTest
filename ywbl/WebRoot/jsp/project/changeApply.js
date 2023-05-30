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
 * Description:弹出添加变更请求
 * @author txb
 */
function addPage(){
	$.ajax({
		url:'project!queryProjectStatus.action',
		dataType:'json',
		success:function(data){
			if('6'==data.state){
				$.alert('已竣工的工程不能提出变更申请！');
			}else{
				$.dialog({
					id: 'addChangeApply', //最好传入，否则不能弹出多个dialog
					url:'project!changeApplyAddPage.action',
					title: '添加变更申请', 
					width: 500, 
					height: 400,
					data:{
					}
				});
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}
/**
 * 
 * Description:弹出修改变更请求
 * @author txb
 */
function editpage(){
	
	$.ajax({
		url:'project!queryProjectStatus.action',
		dataType:'json',
		success:function(data){
			if('6'==data.state){
				$.alert('已竣工的工程不能编辑变更申请！');
			}else{
				toUpdDialog();
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}
function toUpdDialog(){
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		if (a == 1) {
			var ids = "";
			$("input[name='selectFlag']:checked").each(function() {
				ids += $(this).val();
			});
			
			$.ajax({
				url:'project!changeApplyStatus.action?id='+ ids,
				success:function(data){
					if(data == "0"){
						$.alert("编辑失败！");
					}else if(data == "1"){
						
						var url = "project!changeApplyUpdPage.action?id=" + ids;
						$.dialog({
							id: 'updChangeApply', //最好传入，否则不能弹出多个dialog
							url:url,
							title: '编辑变更申请', 
							width: 500, 
							height: 400,
							data:{
							}
						});
						
					}else if(data == "2"){
						$.alert("非暂存状态的变更申请不能编辑！");
					}
				},
				error:function(){
					$.alert("编辑失败！");
				}
			});
			
		} else {
			$.alert("请选择一条信息进行编辑！");
		}

	} else {
		$.alert("请选择要编辑的信息");
	}
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
		    url:'project!deleteChangeApply.action',
		    success: function(data) {
		    	if(data == "0"){
		    		$.alert("删除失败！");
		    	}else if(data == "1"){
		    		$.alert("非暂存状态的变更申请不能删除！");
		    	}else if(data == "2"){
		    		window.location.href='project!changeApply.action';
		    	}
		    },error:function(data){
		    	$.alert("删除失败！");
		    } };
	
	$('#form').ajaxSubmit(options);
}
function getChildData(data){
	if(data.flag == 'addChangeApply'){
		window.location.href='project!changeApply.action';
	}else if(data.flag == 'changeApplyDetail'){
		window.location.href='project!changeApply.action';
	}else if(data.flag == 'updChangeApply'){
		window.location.href='project!changeApply.action';
	}
}
/**
 * 
 * Description:详情页面
 * @author txb
 */
function showDetail(id){
	$.dialog({
		id: 'changeApplyDetail', //最好传入，否则不能弹出多个dialog
		url:'project!changeApplyDetail.action?id='+id,
		title: '查看变更申请', 
		width: 500, 
		height: 400,
		data:{
		}
	});
}