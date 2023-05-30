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
 * 关闭弹出窗口
 */
function closeDialog() {
	$.closeDialog();
}


function chaxun() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "purchase!selectMaterial.action");
	$('#userform').submit();
}


/**
 * 材料管理查询
 */
function storeTypeChaxun(){
	$('#userform').attr("action", "storetype!queryStoreTypeList.action");
	$('#userform').submit();
}
/**
 * 查询材料详情
 */
function chaxunMaterial(){
	$('#userform').attr("action", "storetype!queryMaterialList.action");
	$('#userform').submit();
}

/**
 * 删除类型信息确认提示
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
 * 删除类型确认操作
 * @param btn
 */
function showResult(btn){
	if("yes" == btn){
		document.listform.action = "storetype!delStoreType.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}
//var win;
/**
 * 跳转至编辑页面
 */
function addPage() {
	var url = "storetype!editPage.action?ml_id=null";
	$.dialog({
		url:url,
		title: '添加新材料', 
		width: 500, 
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
		var url = "storetype!editPage.action?mt_id=" + ids;
		$.dialog({
			url:url,
			title: '修改材料信息', 
			width: 900, 
			height: 400
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}

}
/**
 * 添加/编辑页面提交
 */
function doSub() {
	
	var startDate = document.getElementById("time").value;
	var endDate = document.getElementById("ml_date").value;
	if(startDate!='' && endDate!='' && (getDate(startDate)-getDate(endDate)>0)){
	    $.alert("出库时间不能小于申领时间");
	    return false;
	}
	
	$(".registerform").submit();
}


function doSubStoreType() {
	$(".registerform").submit();
}


function doSub2() {
	
	var param = $.getParentData();
	var mt_id = param.mt_id;
	var mlDate = $('#ml_date').val();
	var proId = $('#pro_name').val();
	if(mlDate != '' && proId !=''){
		$.ajax({
			type:'post',//可选get
			url:'storetype!getMinMlDate.action',//这里是接收数据的后台程序
			dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
			data:{
				'mtId':mt_id,
				'proId':proId
			},
			success:function(data){
				if(data=="0"){
					$.alert("没有出库记录，不能返库！");
				}else{
					var b  = getDate(data);
					var c  = getDate(mlDate);
					if(c-b<0){
						$.alert("返库时间不能小于出库时间");
					}else{
						$(".registerform").submit();
					}
				}

			},
			error:function(){
				$.alert("操作失败！");
			}
		});
	}else{
		$(".registerform").submit();
	}
	
	
	
	
}

function getDate(date){
	 var dates = date.split("-");
	 var dateReturn = '';
	 
	 for(var i=0; i<dates.length; i++){
	  dateReturn+=dates[i];
	 }
	 return dateReturn;
}
/**
 * 入库页面
 */
function addInMaterial(mt_id) {
	var mt_id=mt_id;
	var url = "storetype!addInMaterialPage.action?mt_id="+mt_id;
	$.dialog({
		url:url,
		title: '材料入库', 
		width: 800, 
		height: 600
		});
}

/**
 * 出库页面
 */
function addOutMaterial() {
	var url = "storetype!addOutMaterialPage.action";
	$.dialog({
		url:url,
		title: '材料出库', 
		width: 500,
		height: 400
		});
}

/**
 * 跳转至返库页面
 */
function backInMaterial(mt_id) {
	var mt_id=mt_id;
	var url = "storetype!addBackInMaterialPage.action?mt_id="+mt_id;
	$.dialog({
		url:url,
		title: '材料返库', 
		data:{
			mt_id:mt_id
		},
		width: 500,
		height: 450
		});
}

/**
 * 选择分配工程
 */
function selectProject(){
	url="storetype!selectProjectPage.action";
	$.dialog({
		id:'selectId',
		url:url,
		title: '选择工程', 
		width: 800, 
		height: 670});
}
/**
 * 选择分配工程后
 */
function getChildData(data){
	var temp=data.split(",");
	document.getElementById("pro_id").value=temp[1];
	document.getElementById("pro_name").value=temp[0];
}

/**
 * 出入库详情查询页面
 */
function queryMaterial(mt_name,mt_unit,mt_id) {
	var url = "storetype!queryMaterialList.action?mt_name="+mt_name+"&mt_unit="+mt_unit+"&param_flag=0&mt_id="+mt_id;
	$.dialog({
		url:url,
		title: '出入库详情', 
		width: 900,
		height: 600
		});
}

