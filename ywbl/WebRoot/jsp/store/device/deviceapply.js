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

/**
 * 获取一条记录的数据
 */
function getData(mt_id,mt_name,mt_unit){
	document.getElementById("mt_id").value=mt_id;
	document.getElementById("mt_name").value=mt_name;
	document.getElementById("ma_unit").value=mt_unit;
}
/**
 * 关闭弹出窗口
 */
function closeDialog() {
	$.closeDialog();
}


function chaxun() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "device!queryDeviceApplyList.action");
	$('#userform').submit();
}

function chaxunOut() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "device!queryDeviceOutList.action");
	$('#userform').submit();
}

//var win;
/**
 * 跳转至添加页面
 */
function addPage() {
	var url = "device!addDeviceApplyPage.action";
	$.dialog({
		url:url,
		title: '添加设备申领', 
		width: 700, 
		height: 400
		});
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
		var url = "device!updateDeviceApplyPage.action?mda_id=" + ids;
		$.dialog({
			url:url,
			title: '修改设备申领', 
			width: 700, 
			height: 400
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}

}

/**
 * 跳转至添加设备出库页面
 */
function addDeviceOutPage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "device!addDeviceOutPage.action?mda_id=" + ids;
		$.dialog({
			url:url,
			title: '设备出库', 
			width: 700, 
			height: 450
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}
}

/**
 * 跳转至设备返库页面
 */
function addBackPage(mda_id) {
	var mda_id=mda_id;
	var url = "device!addDeviceBackPage.action?mda_id="+mda_id;
	$.dialog({
		url:url,
		title: '添加设备返库', 
		width: 700, 
		height: 400
		});
}

function viweDeviceApplyPage(mda_id) {
	var mda_id=mda_id;
	var url = "device!viweDeviceApplyPage.action?mda_id="+mda_id;
	$.dialog({
		url:url,
		title: '查看设备租赁详情', 
		width: 700, 
		height: 400
		});
}

/**
 * 添加/编辑页面提交
 */
function doSub() {
	
	$.ajax({
		url:'project!queryProjectStatus.action',
		dataType:'json',
		success:function(data){
			if('6'==data.state){
				$.alert('已竣工的工程不能提出设备申领！');
			}else{
				$(".registerform").submit();
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}

