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

function fileDownload(qc_file_name, qc_file_url){
	window.location.href="quality!fileDownload.action?qc_file_name="+qc_file_name+"&qc_file_url="+qc_file_url;
}
function addPage() {
	var url = "jsp/quality/addquality.jsp";
	var title = "质量管控添加";
	
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
 * 质量管控修改页面
 */
function editpage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "quality!updatePage.action?qc_id=" + ids;
		var title = "质量管控修改";
		
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


function chaxun() {
	var qc_title = $('#qc_title').val();
	$('#qualityform').attr("action", "quality!queryQualityControls.action");
	$('#qualityform').submit();
}
function getChildData(data){
	$('#listform').submit();
}

function show(qc_id){
	var url = "quality!showQualityControl.action?qc_id="+qc_id+"";
	var title = "安全管控详情";
	
	$.dialog({
		url:url,
		title: title, 
		data:{
			
		},
		width: 450, 
		height: 400
		});
}

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
		document.listform.action = "quality!delete.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}