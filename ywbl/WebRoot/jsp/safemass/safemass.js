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

function fileDownload(sc_file_name, sc_file_url){
	window.location.href="safe!fileDownload.action?sc_file_name="+sc_file_name+"&sc_file_url="+sc_file_url;
}

/**
 * 安全管控添加页面
 */
function addPage() {
	var url = "jsp/safemass/addsafemass.jsp";
	var title = "安全管控添加";
	
	$.dialog({
		url:url,
		title: title, 
		data:{
			
		},
		width: 800, 
		height: 600
		});

}
/**
 * 安全管控修改页面
 */
function editpage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "safe!updatePage.action?sc_id=" + ids;
		var title = "安全管控修改";
		
		$.dialog({
			url:url,
			title: title, 
			data:{
			},
			width: 800, 
			height: 600
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}
	
}
/**
 * 安全管控查询
 */
function chaxun() {
	var sc_title = $('#sc_title').val();
	$('#safeform').attr("action", "safe!querySafeControls.action");
	$('#safeform').submit();
}
function getChildData(data){
	$('#listform').submit();
}

function show(sc_id){
	var url = "safe!showSafeControl.action?sc_id="+sc_id+"";
	var title = "安全管控详情";
	
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
 * 删除
 */
function del(){
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除信息？此操作不可逆！",
				function(){
			showResult("yes");// 确认按钮回调方法
		}, function(){
			$.alert("您取消了删除操作！");
		});
	}else {
		$.alert("请选择要删除的条目！");
		return;
	}
}

function showResult(btn){
	if("yes" == btn){
		document.listform.action = "safe!delete.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}