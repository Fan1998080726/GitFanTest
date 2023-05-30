/*20200305*/
function ajaxSubmit(){
	if(""==$("#myFile").val()){
		$.alert("请选择文件！");
		return;
	}
	
	if($("#myFile").val().length<4){
		$.alert("请上传xls文件！");
		return;
	}
	
	var type=$("#myFile").val().substr($("#myFile").val().length-4,$("#myFile").val().length);
	if(".xls"!=type){//后缀不为xls
		$.alert("请上传xls文件！");
		return;
	}
	
	var options = {
		    url:'excelUpload!createProjectChild.action',
		    success: function(data) {
		    	
		    	var json = $.parseJSON(data); 
		    	if(json.status=="n"){
		    		$.alert(json.info);
		    	}else{
		    		$.closeDialog(data,"uploadExcel");
		    		
		    	}
		    	//reset();
		    	//window.returnValue='1';
		    	//window.close();
		    },
		    error:function(data){
		    	$.alert("文件上传出错！");
		    } };
	$('#filesForm').ajaxSubmit(options);
}