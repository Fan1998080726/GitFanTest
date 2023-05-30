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
	var user_code = $('#usercode').val();
	$('#userform').attr("action", "device!queryDeviceList.action");
	$('#userform').submit();
}

/**
 * 关闭弹出窗口
 */
function closeDialog() {
	$.closeDialog();
}

/**
 * 选择分配工程
 */
function selectProject(){
	url="device!selectProjectPage.action";
	$.dialog({
		url:url,
		title: '选择分配工程', 
		width: 800, 
		height: 600});
}
/**
 * 选择分配工程后
 */
function getChildData(data){
	var temp=data.split(",");
	document.getElementById("ml_to").value=temp[0];
	document.getElementById("pro_name").value=temp[1];
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
			$.alert("请选择要删除的条目！");
			return;
		}
}
/**
 * 用户删除确认操作
 * @param btn
 */
function showResult(btn){
	if("yes" == btn){
		document.listform.action = "device!delDevice.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}
//var win;
/**
 * 创建新租赁信息
 */
function addPage() {
	var url = "device!addPage.action";
	window.location.href=url;
	
}

/**
 * 跳转至编辑页面
 */
function editpage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "device!editPage.action?ml_id=" + ids;
		window.location.href=url;
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}

}

/**
 * 编辑页面提交
 */
function doEdit() {
	
	$(".registerform").submit();
}

/**
 * 添加页面提交
 */
function doSub() {
	$(".registerform").submit();
}


function getDate(date){
	 var dates = date.split("-");
	 var dateReturn = '';
	 
	 for(var i=0; i<dates.length; i++){
	  dateReturn+=dates[i];
	 }
	 return dateReturn;
}