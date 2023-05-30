/*checkPeronRegister.js 	20200331 txb */

function doSub(){
	if(document.getElementById("name").value==''){
		alert("姓名不能为空!");
		return;
	}
	if(document.getElementById("phone").value==''){
		alert("电话不能为空!");
		return;
	}
	if(document.getElementById("dept_name").value==''){
		alert("所属区域不能为空!");
		return;
	}
	
	$(".registerform").submit();
}
$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		var demo = $(".registerform").Validform({
			ajaxPost:true,	 //异步
			tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			},
			callback:function(data){
if(data.message=='y'){
	alert("现场检查人员注册信息提交成功，区县管理员审核通过后，将发微信通知您,请注意查看!");
	location.replace("EnterpriseService.html");
}else if(data.message=='n'){
	alert("现场检查人员信息注册失败，请联系系统管理员 13351802533 !");
}

			}
		});
})
