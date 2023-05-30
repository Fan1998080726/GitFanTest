function ajaxSubmit(){
	var data = $.getParentData();
	var options = {
		    url:'filesUpload!uploadFiles.action?filePath='+data.filePath,
		    success: function(data) {
		    	var json = $.parseJSON(data); 
		    	if(json.flag=="0"){
		    		$.alert(json.message);
		    	}else{
		    		
		    		$.closeDialog(data,'uploadFilesId');
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
//function clear(id){
//	alert(id)
/*	var obj = document.getElementById(id) ;   
	obj.outerHTML=obj.outerHTML; */
//	var obj = document.getElementById(id) ;   
//	obj.select();   
//	document.selection.clear(); 
//}
/**
* file,input type=file 的name
**/
function deleteFile(file){
   var ie = (navigator.appVersion.indexOf("MSIE")!=-1);//IE 
   var ff = (navigator.userAgent.indexOf("Firefox")!=-1);//Firefox 

   if(ie)
    refreshUploader($("input[name="+file+"]")[0]);
   else
    $("input[name="+file+"]").attr("value","");

}
function refreshUploader(file){
   var file2= file.cloneNode(false);
   file2.onchange= file.onchange;
   file.parentNode.replaceChild(file2,file);
}