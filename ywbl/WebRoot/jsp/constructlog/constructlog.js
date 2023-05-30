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

function chaxun() {
	var param_code = $('#paramcode').val();
	$('#userform').attr("action", "usermanager!query.action");
	$('#userform').submit();
}

/**
 * 关闭弹出窗口
 */
function closeDialog() {
	//alert();
	$.closeDialog();
}

function addPage() {
	var url = "constructlog!addConstructLogaddpage.action";
	$.dialog({
		url:url,
		title: '上传文件', 
		width: 500, 
		height: 400,
		data:{
		}
		});
	
}
function chaxun() {
	var sc_title = $('#sc_title').val();
	$('#safeform').attr("action", "safe!querySafeControls.action");
	$('#safeform').submit();
}
function getChildData(data){
	$('#listform').submit();
}

function show(logid){
	var url = "constructlog!showConstructLog.action?logid="+logid+"";
	var title = "施工日志查看";
	
	$.dialog({
		url:url,
		title: title, 
		data:{
			
		},
		width: 600, 
		height: 500
		});
}

/**
 * 用户删除
 */
function del() {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除该信息？",
				function(){
			showResult("yes");// 确认按钮回调方法
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
function showResult(btn){
	if("yes" == btn){
		document.listform.action = "constructlog!delConstructLog.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}


function editpage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "constructlog!updateConstructLogpage.action?logid=" + ids;
		$.dialog({
			url:url,
			title: '修改施工日志', 
			width: 500, 
			height: 400
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}

}
/**
 * 添加页面提交
 */
function doSub() {
	
	var tablebody = $("#fileUploadTable tr").length;
	if(tablebody != 0){
		$(".registerform").submit();
	}else{
		$.alert("请上传附件");
		return false;
	}
	
}
/**
 * 编辑页面提交
 */
function doEdit() {
	var tablebody = $("#fileUploadTable tr").length;
	if(tablebody != 0){
		$(".registerform").submit();
	}else{
		$.alert("请上传附件");
		return false;
	}
}
