
function gotoUpdate(){
	window.location.href="contract!updateContractPage.action?type=update";
}

function fileDownload(fileUrl,fileName){
	window.location.href="project!fileDownload.action?fileUrl="+fileUrl+"&fileName="+fileName;
}

function changeFlag(){
	
	$.confirm("是否确认保存，保存后信息将无法修改！",
		function(){
		showResult("yes");// 确认按钮回调方法
	}, function(){
	});
}

/**
 * 用户删除确认操作
 * @param btn
 */
function showResult(btn){
	if("yes" == btn){
		   window.location.href="contract!changeContractFlag.action";
	}
}