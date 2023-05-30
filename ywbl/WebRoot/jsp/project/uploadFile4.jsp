<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="css/layout.css" type="text/css" rel="stylesheet">
		<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
			<!--表单验证-->
			<link rel="stylesheet" href="css/form/style.css" type="text/css"
				media="all" />
			<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
			<!--tree-->
			<script type="text/javascript" src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
			<script src="<%=path%>/js/jquery.form.js"></script>
			
</head>

<style>
#bg{   
    display: none;   
    position: absolute;   
    top: 0%;   
    left: 0%;   
    width: 100%;   
    height: 100%;   
    background-color: black;   
    z-index:1001;   
    -moz-opacity: 0.3;   
    opacity:.30;   
    filter: alpha(opacity=30);  
}  
  
#imge{
    position:fixed;_position:absolute;
      display: none;  
    top:85%;left:77%;
    width:15%;
    overflow:hidden;
/*     background:url(/images/5-121204193R0-50.gif) no-repeat;z-index:7; */
    margin:-190px 0 0 -160px;
    padding-top:0;
    }
</style>
<script>
$(function(){
	if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
	    $('[placeholder]').focus(function() {
	        var input = $(this);
	        if (input.val() == input.attr('placeholder')) {
	            input.val('');
	            input.removeClass('placeholder');
	        }
	    }).blur(function() {
	        var input = $(this);
	        if (input.val() == '' || input.val() == input.attr('placeholder')) {
	            input.addClass('placeholder');
	            input.val(input.attr('placeholder'));
	        }
	    }).blur();
	};
	})
	function placeholderSupport() {
	    return 'placeholder' in document.createElement('input');
	}


function showPopDiv() {   
	$("#imge").css("display","block");
	document.getElementById("imge").src="images/5-121204193R0-50.gif";
    document.getElementById("bg").style.display ="block";  
    
}  

//取消加载效果
function hiddenPopDiv(){
	document.getElementById("image").src="";
    $("#imge").css("display","none");
    $("#bg").css("display","none");
} 
</script>

<body>
<div class="main">
	<div class="main5">
	<div id="bg"></div>
	<img id="imge" src="images/5-121204193R0-50.gif"></img>
	<script>var loading=false;hiddenPopDiv();</script>
				<input type="button" value="" onclick="ajaxSubmit('${flage}')" 
					style="background:url(img/queding.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px; " /> 

		<div class="formsub">
		<form id="filesForm" enctype="multipart/form-data" method="post" action="filesUpload!uploadFiles.action">
			<table align="center">
				<tr>
					<td>1.</td>
					<td><input type="file" id="myFile_1" name="myFile_1"/></td>
					<td><input type="button" value="清空" onclick="deleteFile('myFile_1')" style="width:64px;"/></td>
				</tr>
				
			</table>
		</form>
		</div>
	</div>
</div>
<script>
function ajaxSubmit(flage){
	showPopDiv();if(loading)return false;loading=true 
	var xmlHttp = new XMLHttpRequest();
	var data = $.getParentData();
	var options = {
			
		    url:'filesUpload!uploadFiles.action?filePath='+data.filePath,
		    success: function(data) {
		    	var json = $.parseJSON(data); 
		    	var list1 = new Array();
		    	for(var i in eval(json.files)){
		    	
		    	list1[i]=eval(json.files)[i].realPath;
		    	}
		    	if(json.flag=="0"){
		    		$.alert(json.message);
		    	}else{
// 		    		alert("上传图片成功");
		    		jQuery.ajax({
		    		     type: "POST",
		    		     url: 'filesUpload!yasuo.action?list1='+list1,
		    		     text: json,
		    		     success: function(msg){
		    		     
		    		     if(msg==null || msg==""){
		    		    	 $.closeDialog(data,'uploadFilesId',flage);
		    		     }else {
		    		    	 $.closeDialog(data,'uploadFilesId',flage);
		    		     }
		    		     },
		    		      error: function() {
		    		    	  $.closeDialog(data,'uploadFilesId',flage);
		    		     }
		    		    });		    		
// 		    	$.ajax("filesUpload!yasuo.action?list1="+list1);
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
/* 	var obj = document.getElementById(id) ;   
	obj.outerHTML=obj.outerHTML;  */
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
</script>
</body>
</html>