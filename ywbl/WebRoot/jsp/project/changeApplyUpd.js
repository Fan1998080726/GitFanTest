$(function() {
		var demo = $(".registerform").Validform({
			ajaxPost : true, //异步
			tiptype : function(msg, o, cssctl) {
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip = o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				}
			},
			callback : function(data) {
				if (data.status == "y") {
					$.closeDialog();
				} else {
					$.alert('保存失败！');
				}
			}
		});
		demo.addRule([ {
			ele : "select",
			datatype : "*"
		} ]);
	})
	/**
	 * 
	 * Description:弹出文件上传页面
	 * @author txb
	 */
	function addFileUpload() {
		$.dialog({
			id : 'uploadFilesId',
			url : 'filesUpload!uploadFile.action',
			title : '上传文件',
			width : 300,
			height : 300,
			data : {
				filePath : 'D:/fileUpload/'
			}
		});
	}
/**
 * 
 * Description:接收返回dialog数据
 * @param data
 * @author txb
 */
	function getChildData(data) {
		var json = $.parseJSON(data);
		$(json.files)
				.each(
						function() {
							$('#fileUploadTable')
									.append(
											"<tr><td>"
													+ this.name
													+ "<input type='hidden' value='"+this.name+"' name='fileName'/>"
													+ "<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>");
						})
	}
	/**
	 * 添加页面提交
	 * 变更id
	 */
	function doSub(flag,id) {
		var tablebody = $("#fileUploadTable tr").length;
		if(tablebody != 0){
			var options = {
				    url:'project!changeApplyUpd.action?flag='+flag+'&id='+id,
				    success: function(data) {
				    	if(data == "yes"){
				    		var json = {'flag':'updChangeApply'};
				    		$.closeDialog(json,'updChangeApply');
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
	
	