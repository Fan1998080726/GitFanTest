function ajaxSubmit(){
	var data = $.getParentData();
    var type = document.getElementById("type").value;
//    alert("type="+type);
    var options;
    var obj = document.getElementById('myFile_1') ; 
//    	alert("obj="+file);
    var file=document.getElementById("myFile_1").value;
    var fileName= file.substring(file.length-3);
    if(fileName!='pdf'){
    	alert("请上传pdf格式!!!");
    	obj.outerHTML=obj.outerHTML; 
    	return;
    }
//    alert("type==="+type);
    if(type=='y'){
	 options = {
		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
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
		    	$.alert("上传失败!");
		    } };
    }
    else if(type=='t'){
   	 options = {
   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
   		    success: function(data) {
   		    	var json = $.parseJSON(data); 
   		    	if(json.flag=="0"){
   		    		$.alert(json.message);
   		    	}else{
   		    		$.closeDialog1(data,'uploadFilesId');
   		    	}
   		    	//reset();
   		    	//window.returnValue='1';
   		    	//window.close();
   		    },
   		    error:function(data){
		    	$.alert("上传失败!");
   		    } };
       }
    else if(type=='zjlgcs'){
      	 options = {
      		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
      		    success: function(data) {
      		    	var json = $.parseJSON(data); 
      		    	if(json.flag=="0"){
      		    		$.alert(json.message);
      		    	}else{
      		    		$.closeDialog_zjlgcs(data,'uploadFilesId');
      		    	}
      		    	//reset();
      		    	//window.returnValue='1';
      		    	//window.close();
      		    },
      		    error:function(data){
    		    	$.alert("上传失败!");
      		    } };
          }
    else if(type=='contractScan'){
//    	alert("data.filePath=="+data.filePath);
     	 options = {
     		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
     		    success: function(data) {
     		    	var json = $.parseJSON(data); 
     		    	if(json.flag=="0"){
     		    		$.alert(json.message);
     		    	}else{
     		    		$.closeDialog_contractScan(data,'uploadFilesId');
     		    	}
     		    	//reset();
     		    	//window.returnValue='1';
     		    	//window.close();
     		    },
     		    error:function(data){
    		    	$.alert("上传失败!");
     		    } };
         }
    else if(type=='orther'){
     	 options = {
     		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
     		    success: function(data) {
     		    	var json = $.parseJSON(data); 
     		    	if(json.flag=="0"){
     		    		$.alert(json.message);
     		    	}else{
     		    		$.closeDialog_orther(data,'uploadFilesId');
     		    	}
     		    	//reset();
     		    	//window.returnValue='1';
     		    	//window.close();
     		    },
     		    error:function(data){
    		    	$.alert("上传失败!");
     		    } };
         }    else if(type=='1'){
         	 options = {
          		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
          		    success: function(data) {
          		    	var json = $.parseJSON(data); 
          		    	if(json.flag=="0"){
          		    		$.alert(json.message);
          		    	}else{
          		    		$.closeDialog_1(data,'uploadFilesId');
          		    	}
          		    	//reset();
          		    	//window.returnValue='1';
          		    	//window.close();
          		    },
          		    error:function(data){
        		    	$.alert("上传失败!");
          		    } };
              }   else if(type=='2'){
              	 options = {
               		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
               		    success: function(data) {
               		    	var json = $.parseJSON(data); 
               		    	if(json.flag=="0"){
               		    		$.alert(json.message);
               		    	}else{
               		    		$.closeDialog_2(data,'uploadFilesId');
               		    	}
               		    	//reset();
               		    	//window.returnValue='1';
               		    	//window.close();
               		    },
               		    error:function(data){
            		    	$.alert("上传失败!");
               		    } };
                   }   else if(type=='3'){
                   	 options = {
                   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
                   		    success: function(data) {
                   		    	var json = $.parseJSON(data); 
                   		    	if(json.flag=="0"){
                   		    		$.alert(json.message);
                   		    	}else{
                   		    		$.closeDialog_3(data,'uploadFilesId');
                   		    	}
                   		    	//reset();
                   		    	//window.returnValue='1';
                   		    	//window.close();
                   		    },
                   		    error:function(data){
                		    	$.alert("上传失败!");
                   		    } };
                       }   else if(type=='4'){
                       	 options = {
                       		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
                       		    success: function(data) {
                       		    	var json = $.parseJSON(data); 
                       		    	if(json.flag=="0"){
                       		    		$.alert(json.message);
                       		    	}else{
                       		    		$.closeDialog_4(data,'uploadFilesId');
                       		    	}
                       		    	//reset();
                       		    	//window.returnValue='1';
                       		    	//window.close();
                       		    },
                       		    error:function(data){
                    		    	$.alert("上传失败!");
                       		    } };
                      }  else if(type=='5'){
                             	 options = {
                                		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
                                		    success: function(data) {
                                		    	var json = $.parseJSON(data); 
                                		    	if(json.flag=="0"){
                                		    		$.alert(json.message);
                                		    	}else{
                                		    		$.closeDialog_5(data,'uploadFilesId');
                                		    	}
                                		    	//reset();
                                		    	//window.returnValue='1';
                                		    	//window.close();
                                		    },
                                		    error:function(data){
                                		    	$.alert("上传失败!");
                                		    } };
                    }else if(type=='6'){
                    	 options = {
                     		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
                     		    success: function(data) {
                     		    	var json = $.parseJSON(data); 
                     		    	if(json.flag=="0"){
                     		    		$.alert(json.message);
                     		    	}else{
                     		    		$.closeDialog_6(data,'uploadFilesId');
                     		    	}
                     		    	//reset();
                     		    	//window.returnValue='1';
                     		    	//window.close();
                     		    },
                     		    error:function(data){
                    		    	$.alert("上传失败!");
                     		    } };
         }else if(type=='7'){
        	 options = {
          		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
          		    success: function(data) {
          		    	var json = $.parseJSON(data); 
          		    	if(json.flag=="0"){
          		    		$.alert(json.message);
          		    	}else{
          		    		$.closeDialog_7(data,'uploadFilesId');
          		    	}
          		    	//reset();
          		    	//window.returnValue='1';
          		    	//window.close();
          		    },
          		    error:function(data){
        		    	$.alert("上传失败!");
          		    } };
}else if(type=='8'){
	 options = {
   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
   		    success: function(data) {
   		    	var json = $.parseJSON(data); 
   		    	if(json.flag=="0"){
   		    		$.alert(json.message);
   		    	}else{
   		    		$.closeDialog_8(data,'uploadFilesId');
   		    	}
   		    	//reset();
   		    	//window.returnValue='1';
   		    	//window.close();
   		    },
   		    error:function(data){
		    	$.alert("上传失败!");
   		    } };
}else if(type=='9'){
	 options = {
   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
   		    success: function(data) {
   		    	var json = $.parseJSON(data); 
   		    	if(json.flag=="0"){
   		    		$.alert(json.message);
   		    	}else{
   		    		$.closeDialog_9(data,'uploadFilesId');
   		    	}
   		    	//reset();
   		    	//window.returnValue='1';
   		    	//window.close();
   		    },
   		    error:function(data){
		    	$.alert("上传失败!");
   		    } };
}else if(type=='10'){
	 options = {
   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
   		    success: function(data) {
   		    	var json = $.parseJSON(data); 
   		    	if(json.flag=="0"){
   		    		$.alert(json.message);
   		    	}else{
   		    		$.closeDialog_10(data,'uploadFilesId');
   		    	}
   		    	//reset();
   		    	//window.returnValue='1';
   		    	//window.close();
   		    },
   		    error:function(data){
		    	$.alert("上传失败!");
   		    } };
}else if(type=='11'){
	 options = {
	   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
	   		    success: function(data) {
	   		    	var json = $.parseJSON(data); 
	   		    	if(json.flag=="0"){
	   		    		$.alert(json.message);
	   		    	}else{
	   		    		$.closeDialog_11(data,'uploadFilesId');
	   		    	}
	   		    	//reset();
	   		    	//window.returnValue='1';
	   		    	//window.close();
	   		    },
	   		    error:function(data){
			    	$.alert("上传失败!");
	   		    } };
	}else if(type=='12'){
		 options = {
		   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
		   		    success: function(data) {
		   		    	var json = $.parseJSON(data); 
		   		    	if(json.flag=="0"){
		   		    		$.alert(json.message);
		   		    	}else{
		   		    		$.closeDialog_12(data,'uploadFilesId');
		   		    	}
		   		    	//reset();
		   		    	//window.returnValue='1';
		   		    	//window.close();
		   		    },
		   		    error:function(data){
				    	$.alert("上传失败!");
		   		    } };
		}else if(type=='13'){
			 options = {
			   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
			   		    success: function(data) {
			   		    	var json = $.parseJSON(data); 
			   		    	if(json.flag=="0"){
			   		    		$.alert(json.message);
			   		    	}else{
			   		    		$.closeDialog_13(data,'uploadFilesId');
			   		    	}
			   		    	//reset();
			   		    	//window.returnValue='1';
			   		    	//window.close();
			   		    },
			   		    error:function(data){
					    	$.alert("上传失败!");
			   		    } };
			}else if(type=='14'){
				 options = {
				   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
				   		    success: function(data) {
				   		    	var json = $.parseJSON(data); 
				   		    	if(json.flag=="0"){
				   		    		$.alert(json.message);
				   		    	}else{
				   		    		$.closeDialog_14(data,'uploadFilesId');
				   		    	}
				   		    	//reset();
				   		    	//window.returnValue='1';
				   		    	//window.close();
				   		    },
				   		    error:function(data){
						    	$.alert("上传失败!");
				   		    } };
				}else if(type=='15'){
					 options = {
					   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
					   		    success: function(data) {
					   		    	var json = $.parseJSON(data); 
					   		    	if(json.flag=="0"){
					   		    		$.alert(json.message);
					   		    	}else{
					   		    		$.closeDialog_15(data,'uploadFilesId');
					   		    	}
					   		    	//reset();
					   		    	//window.returnValue='1';
					   		    	//window.close();
					   		    },
					   		    error:function(data){
							    	$.alert("上传失败!");
					   		    } };
					}else if(type=='16'){
						 options = {
						   		    url:'filesUpload!uploadFiles1.action?filePath='+data.filePath,
						   		    success: function(data) {
						   		    	var json = $.parseJSON(data); 
						   		    	if(json.flag=="0"){
						   		    		$.alert(json.message);
						   		    	}else{
						   		    		$.closeDialog_16(data,'uploadFilesId');
						   		    	}
						   		    	//reset();
						   		    	//window.returnValue='1';
						   		    	//window.close();
						   		    },
						   		    error:function(data){
								    	$.alert("上传失败!");
						   		    } };
						}
	$('#filesForm').ajaxSubmit(options);
}



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