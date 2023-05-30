function loading(msg) {
    layer.msg(msg, {
        icon: 16,
        shade: [0.9, '#000005'],//遮罩的颜色与透明度
        time: false  //取消自动关闭
    })
};

function ajaxSubmit(){
//	loading("上传中。。。");
	var data = $.getParentData();
 	console.log("data"+data);
//    var type = document.getElementById("type").value;
    var options;
        	 options = {
   		    url:'WxServlet?opflag=SavePhoto&filePath=C:/download1/',
   		    success: function(data) {
   		    		alert("6666666");

   		    },
   		    error:function(data){
   		    	var json = $.parseJSON(data);
   		    	$.alert("上传失败!");
   		    }
   		    };
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