<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="css/layout.css" type="text/css" rel="stylesheet">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<!--表单验证-->
	<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jsp/system/user/usermanager.js"></script>
</head>
	<script type="text/javascript">
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
			
				if (data.status=="y") {
					$.closeDialog("","addChangeApply");
				}else{
					$.alert('操作失败！');
				}
			}
		});
	demo.addRule([{
		ele:"select",
		datatype:"*"
	}]);
})

</script>
<body>
	<div class="main">
		<div class="main5">
		
         <!--toolbar-->
		 
			<!--form-->
			<form id="form" class="registerform" action="progress!insertUpdate.action">
			<input type="hidden" name="progressActual.pa_id" value="${pa_id }"/>
			<input type="hidden" name="type" value="${type }"/>
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
<c:if test="${'update'==type }">
  <tr>
    <th style="width:20%;"><span class="need">*</span> 修改值:</th>
    <td style="width:30%;"><input type="text" id="login_id" name="progressActual.pa_measure" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" nullmsg="工程量不可为空！" errormsg="工程量不可为空,可有八位整数两位小数！" />
		                    <div class="Validform_checktip">工程量不可为空,可有八位整数两位小数！</div></td>
  </tr>
</c:if>
  <tr>
    <th style="width:20%;"><span class="need">*</span> 备注:</th>
    <td style="width:30%;"><input type="text" id="login_id" name="progressActual.pa_remark" class="inputxt" datatype="*1-50" errormsg="备注不可为空，最多50字符！" />
		                    <div class="Validform_checktip">备注不可为空，最多50字符!</div></td>
  </tr>
</table>
	
			</form>
			
			<input type="button" onclick="doSub();" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:25px; border:none; margin-top:10px; float:right;" />

		</div>
	</div>

</body>
</html>