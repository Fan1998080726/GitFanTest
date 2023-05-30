$(function() {
	})
	/**
	 * 添加页面提交
	 */
	function doSub(id) {
		var tablebody = $("#fileUploadTable tr").length;
		if(tablebody != 0){
			var options = {
				    url:'project!changeApplySubmit.action?id='+id+'&remark='+$('#remark').val(),
				    success: function(data) {
				    	if(data == "yes"){
				    		var json = {'flag':'changeApplyDetail'};
				    		$.closeDialog(json,'changeApplyDetail');
				    	}else{
				    		$.alert("添加失败");
				    	}
				    },error:function(data){
				    } };
			
			$('#form').ajaxSubmit(options);
		}else{
			$.alert("请上传附件");
			return false;
		}
		
	}
	
function fileDownload(sc_file_name, sc_file_url){
	var url = "constructlog!fileDownload.action?sc_file_name="+sc_file_name+"&sc_file_url="+sc_file_url+"";
	window.location.href=url;
}
	