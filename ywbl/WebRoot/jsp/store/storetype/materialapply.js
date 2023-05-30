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
	$('#userform').attr("action", "storetype!editMaterialApplyPage.action");
	$('#userform').submit();
}

function chaxunOut() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "storetype!queryMaterialOutList.action");
	$('#userform').submit();
}
function chazhao() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "storetype!addMaterialApplyPage.action");
	$('#userform').submit();
}
function updateChazhao() {
	var user_code = $('#paramcode').val();
	var ma_id = $('#ma_id').val();
	$('#userform').attr("action", "storetype!editMaterialApplyPage.action?ma_id="+ma_id);
	$('#userform').submit();
}
//var win;
/**
 * 跳转至添加页面
 */
function addPage() {
	var url = "storetype!addMaterialApplyPage.action";
	$.dialog({
		url:url,
		title: '添加材料申领', 
		width: 800, 
		height: 600
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
		var url = "storetype!editMaterialApplyPage.action?ma_id=" + ids;
		$.dialog({
			url:url,
			title: '修改材料申领', 
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
 * 跳转至添加材料出库页面
 */
function addMaterialOutPage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "storetype!addMaterialOutPage.action?ma_id=" + ids;
		$.dialog({
			url:url,
			title: '材料出库', 
			width: 500, 
			height: 400
			});
	}else
		$.alert("请选择一条信息！");
		} else {
		$.alert("请选择一条信息！");
	}
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
				$.alert('已竣工的工程不能提出材料申领！');
			}else{
				$(".registerform").submit();
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});

}

