/**
 * 类似alert
 */
jQuery.alert = function(message) {
	art.dialog.through({
		title: '警告',
		icon: 'warning',
	    content: message,
	    width:200,
	    lock: true,
	    ok: true,
	    close: function(event, ui) { 
	    $.post("ConWork_MassIncidentInfo!AlertNumber.action");
	    } //这是关闭事件的回调函数,在这写你的逻辑
	});
};
/**
 * tips 提示 
 */
jQuery.tips = function(message){
	 art.dialog.tips(message,1.5);
}
/**
 * confirm 提示
 */
jQuery.confirm = function(message,yes,no){
	art.dialog.confirm(message,yes,no);
}
/**
 * url dialog
 * 弹出窗口时候最好传入id，以免弹出多个dialog时候出错
 */
jQuery.dialog = function(option){
	var defaultVal = {
			id:"_urlDialog",
			title : "默认title",
			height : 150,
			width : 300,
			lock:true,
			resize:false
		};
	var obj = $.extend(defaultVal, option);
	art.dialog.data('data',obj.data);
	art.dialog.open(obj.url,obj);
}
/**
 * 返回子页面 数据
 */
//jQuery.returnData = function(data){
//	parent.getChildData(data);
//}
/**
 * 得到父页面传过来的json
 */
jQuery.getParentData = function(){
	return art.dialog.data('data');
}


/**
 * 关闭页面   20191129 fcl 发现有的jsp页面调用此方法传递3个参数，经过分析新建立3个参数的方法
 */
jQuery.closeDialogSGXK = function(data,dialogId,flage){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialogSGXK(data,dialogId,flage);
    }, 0)
}
function _closeDialogSGXK(data,dialogId,flage){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildDataSGXK(data,flage);
	}else{
		art.dialog.list[dialogId].close();
		getChildDataSGXK(data,flage);
	}
	
}
/**
 * 关闭页面
 */
jQuery.closeDialog = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog(data,dialogId);
    }, 0)
}
function _closeDialog(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData(data);
	}
	
}
jQuery.closeDialog1 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog1(data,dialogId);
    }, 0)
}
function _closeDialog1(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData1(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData1(data);
	}
	
}

/*20180612 txb add*/
jQuery.closeDialog_zjlgcs = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_zjlgcs(data,dialogId);
    }, 0)
}
/*20180613 txb add*/
jQuery.closeDialog_orther = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_orther(data,dialogId);
    }, 0)
}
function _closeDialog_zjlgcs(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_zjlgcs(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_zjlgcs(data);
	}
	
}
function _closeDialog_orther(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_orther(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_orther(data);
	}
	
}
//20160803 add
jQuery.closeDialog_selectPage = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_selectPage(data,dialogId);
    }, 0)
}
function _closeDialog_selectPage(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id

	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_selectPage(data);
	}
	
}



//20160817 add 监察大队日报表选择项目报建后，关闭页面
jQuery.closeDialog_queryTBProjectInfo_selectPage = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_queryTBProjectInfo_selectPage(data,dialogId);
    }, 0)
}
function _closeDialog_queryTBProjectInfo_selectPage(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryTBProjectInfo_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryTBProjectInfo_selectPage(data);
	}
	
}


//20170425 add 中标备案信息管理选择项目报建后，关闭页面
jQuery.closeDialog_TBTenderInfoSg = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_TBTenderInfoSg(data,dialogId);
    }, 0)
}
function _closeDialog_TBTenderInfoSg(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryTBTenderInfoSg_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryTBTenderInfoSg_selectPage(data);
	}
	
}

//20170425 add 总监理工程师选择项目报建后，关闭页面
jQuery.closeDialog_JlgcsPeople = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_JlgcsPeople(data,dialogId);
    }, 0)
}
function _closeDialog_JlgcsPeople(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryJlgcsPeople_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryJlgcsPeople_selectPage(data);
	}
	
}

//20170425 add 建设单位选择项目报建后，关闭页面
jQuery.closeDialog_BuildCorp = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_BuildCorp(data,dialogId);
    }, 0)
}
function _closeDialog_BuildCorp(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryBuildCorp_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryBuildCorp_selectPage(data);
	}
	
}

//20170425 add 监理单位选择项目报建后，关闭页面
jQuery.closeDialog_JlCorp = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_JlCorp(data,dialogId);
    }, 0)
}
function _closeDialog_JlCorp(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryJlCorp_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryJlCorp_selectPage(data);
	}
	
}

//20170425 add 建造师选择项目报建后，关闭页面
jQuery.closeDialog_JzsPeople = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_JzsPeople(data,dialogId);
    }, 0)
}
function _closeDialog_JzsPeople(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryJzsPeople_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryJzsPeople_selectPage(data);
	}
	
}

//20170425 add 施工单位选择项目报建后，关闭页面
jQuery.closeDialog_TBCorpBasicinfoSg = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_TBCorpBasicinfoSg(data,dialogId);
  }, 0)
}
function _closeDialog_TBCorpBasicinfoSg(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryTBCorpBasicinfoSg_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryTBCorpBasicinfoSg_selectPage(data);
	}
	
}
//20170425 add 勘察单位选择项目报建后，关闭页面
jQuery.closeDialog_HarbinEconAndDesignInfo = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_HarbinEconAndDesignInfo(data,dialogId);
  }, 0)
}
function _closeDialog_HarbinEconAndDesignInfo(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryHarbinEconAndDesignInfo_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryHarbinEconAndDesignInfo_selectPage(data);
	}
	
}

//20170425 add 设计单位选择项目报建后，关闭页面
jQuery.closeDialog_HarbinEconAndDesignInfo1 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		  * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_HarbinEconAndDesignInfo1(data,dialogId);
  }, 0)
}
function _closeDialog_HarbinEconAndDesignInfo1(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_queryHarbinEconAndDesignInfo1_selectPage(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_queryHarbinEconAndDesignInfo1_selectPage(data);
	}
	
}



/*20180616 txb add*/
jQuery.closeDialog_contractScan = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialog_contractScan(data,dialogId);
    }, 0)
}
function _closeDialog_contractScan(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_contractScan(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_contractScan(data);
	}
	
}

/*fcl add*/
jQuery.closeDialog_1 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_1(data,dialogId);
	}, 0)
}
function closeDialog_1(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_1(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_1(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_2 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_2(data,dialogId);
	}, 0)
}
function closeDialog_2(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_2(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_2(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_3 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_3(data,dialogId);
	}, 0)
}
function closeDialog_3(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_3(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_3(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_4 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_4(data,dialogId);
	}, 0)
}
function closeDialog_4(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_4(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_4(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_5 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_5(data,dialogId);
	}, 0)
}
function closeDialog_5(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_5(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_5(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_6 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_6(data,dialogId);
	}, 0)
}
function closeDialog_6(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_6(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_6(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_7 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_7(data,dialogId);
	}, 0)
}
function closeDialog_7(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_7(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_7(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_8 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_8(data,dialogId);
	}, 0)
}
function closeDialog_8(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_8(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_8(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_9 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_9(data,dialogId);
	}, 0)
}
function closeDialog_9(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_9(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_9(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_10 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_10(data,dialogId);
	}, 0)
}
function closeDialog_10(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_10(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_10(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_11 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_11(data,dialogId);
	}, 0)
}
function closeDialog_11(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_11(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_11(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_12 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_12(data,dialogId);
	}, 0)
}
function closeDialog_12(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_12(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_12(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_13 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_13(data,dialogId);
	}, 0)
}
function closeDialog_13(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_13(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_13(data);
	}
	
}



/*fcl add*/
jQuery.closeDialog_14 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_14(data,dialogId);
	}, 0)
}
function closeDialog_14(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_14(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_14(data);
	}
	
}


/*fcl add*/
jQuery.closeDialog_15 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_15(data,dialogId);
	}, 0)
}
function closeDialog_15(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_15(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_15(data);
	}
	
}




/*fcl add*/
jQuery.closeDialog_16 = function(data,dialogId){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener.closeDialog_16(data,dialogId);
	}, 0)
}
function closeDialog_16(data,dialogId){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildData_16(data);
	}else{
		art.dialog.list[dialogId].close();
		getChildData_16(data);
	}
	
}
 


//fcl 20191206 污水排放上传图片 
jQuery.closeDialogPS = function(data,dialogId,flage){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialogPS(data,dialogId,flage);
	}, 0)
}
function _closeDialogPS(data,dialogId,flage){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildDataPS(data,flage);
	}else{
		art.dialog.list[dialogId].close();
		getChildDataPS(data,flage);
	}
	
}


//txb 20191206 奚望  添加附件 
jQuery.closeDialogXW = function(data,dialogId,flage){
	setTimeout(function () { //解决jquery ie9 bug date string 未定义
		/**
		 * 调用打开这个dialog的窗口的_closeDialog方法，传入data,和需要关闭的id
		 */
		art.dialog.opener._closeDialogXW(data,dialogId,flage);
	}, 0)
}
function _closeDialogXW(data,dialogId,flage){ 
	//当弹出多个dialog时候，每个dialog的id应该不同，所以关闭时应该传入id
	if(dialogId === undefined){
		art.dialog.list['_urlDialog'].close();
		getChildDataXW(data,flage);
	}else{
		art.dialog.list[dialogId].close();
		getChildDataXW(data,flage);
	}
	
}