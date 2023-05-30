$(function(){
//	showCount();
	
	//window.setInterval("showCount()", 6000*60*60); 
});
function showCount(){
	$.ajax({
		url:'project!showCount.action',
		type: "post",
		dataType:'json',
		success:function(data){
			$('#messageNum').html('');
			$('#warningNum').html('');
			$('#shan').attr('src','img/xx.png');
			$('#warning').attr('src','img/warning.png');
			$.each(data, function (n, value) {  
				//alert(value.type);
	              if(value.type=='1'){//消息
	            	  $('#shan').attr('src','img/xx.gif');
	            	  $('#messageNum').html('（'+value.count+'）');
	              }else if(value.type=='2'){//预警
	            	  $('#warning').attr('src','img/warning.gif');
	            	  $('#warningNum').html('（'+value.count+'）');
	              }
	          });  
		}
	});
}

