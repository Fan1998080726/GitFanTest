
/*20170401  cx  选择弹出选择添加方法 */
function changeBuilderLicence_selectPage(){
    
	$.dialog({
		url:'tbBuilderLicenceManage!changeBuilderLicence_selectPage.action',
		title: '选择施工许可证号',
		width: 800,
		height: 600});
}
function getChildData_selectPage(data){
	var temp2=data.split(";");
	if(temp2.length>1){
		var temp=temp2[1].split("%^&");
		
		document.getElementById("builderLicenceNum").value=$.trim(temp[0]);
		document.getElementById("projectName").value=$.trim(temp[1]);
		document.getElementById("projectLocation").value=$.trim(temp[11]);
			document.getElementById("buildCorpName").value=$.trim(temp[12]);
			document.getElementById("consCorpName").value=$.trim(temp[13]);
			document.getElementById("superCorpName").value=$.trim(temp[6]);
			document.getElementById("prjSize").value=((($.trim(temp[23])+$.trim(temp[24]))||($.trim(temp[25])+$.trim(temp[26]))||($.trim(temp[27])+$.trim(temp[28]))+$.trim(temp[29])));
			document.getElementById("structureType").value=$.trim(temp[22]);
			document.getElementById("pliesNum").value=$.trim(temp[16]);
			document.getElementById("prjNum").value=$.trim(temp[21]);
		
	}else{
		var json = $.parseJSON(data);
		$(json.files).each(function(){
			$('#fileUploadTable').append("<tr><td>"+this.name+"<input type='hidden' value='"+this.name+"' name='fileName'/>"+
					"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>");
		})
	}
}