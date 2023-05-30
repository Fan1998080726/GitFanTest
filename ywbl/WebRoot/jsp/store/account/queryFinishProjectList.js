/**
 * 材料结算
 */
function meterial(pro_id){
	$.dialog({
		url:'account!queryMeterialAccountPage.action?pro_id='+pro_id,
		title: '材料结算', 
		width: 600, 
		height: 600
	});
}


/**
 * 机械结算
 */
function device(pro_id){
	$.dialog({
		url:'account!queryDeviceAccountPage.action?pro_id='+pro_id,
		title: '设备结算', 
		width: 600, 
		height: 600
	});
}

/**
 * 查询功能
 */
function chaxun(){
	$('#userform').attr("action", "account!queryFinishProjectList.action");
	$('#userform').submit();
}