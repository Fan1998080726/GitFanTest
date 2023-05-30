<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
	<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path %>/jsp/store/device/device.js"></script>
</head>
	<script type="text/javascript">
$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		
		var demo = $(".registerform").Validform({
			//ajaxPost:true,	 //异步
		
			tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			}
		});
	
})

</script>
<body>

	<div class="main">
	<div class="breadcrumb">当前位置：设备租赁管理</div>

		<div class="main5">
		
		<!--toolbar-->
		<input type="button" onclick="doSub();" value="" style="background:url(img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" /> 
		<input type="button" onclick="javascript:history.back(1);" style="background:url(img/t_back.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;"/>

			<!--form-->
			<form id="form" class="registerform" action="device!addDevice.action">
			
			<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:20%;"><span class="need">*</span> 设备名:</th>
    <td style="width:30%;"><input type="text" value="${materialDevice.ml_name }" id="ml_name" name="materialDevice.ml_name" class="inputxt" datatype="*1-100" nullmsg="设备名不可为空！" />
							<div class="Validform_checktip">设备名不可为空！</div></td>
	<th style="width:20%;"><span class="need">*</span> 租赁开始时间:</th>
    <td style="width:30%;"><input type="text" value="${materialDevice.ml_start_date }" id="ml_start_date" onclick="$.calendar();" readonly="readonly" name="materialDevice.ml_start_date" class="inputxt" datatype="*1-20" nullmsg="租赁开始时间不可为空！" />
							<div class="Validform_checktip">租赁开始时间不可为空！</div></td>

  </tr>
  <tr>
    <th><span class="need">*</span> 费用(元/天):</th>
    <td><input type="text" value="${materialDevice.ml_cost }" id="ml_cost" name="materialDevice.ml_cost" class="inputxt" datatype="n1-20" nullmsg="费用不可为空！" errormsg="请输入有效数字！"  />
							<div class="Validform_checktip">费用不可为空！</div></td>
	<th><span class="need">*</span> 租赁组织:</th>
    <td><input type="text" value="${materialDevice.ml_form }" id="ml_form" name="materialDevice.ml_from" class="inputxt" datatype="*1-20" nullmsg="租赁组织不可为空！" />
							<div class="Validform_checktip">租赁组织不可为空！</div></td>
  </tr>
  <tr>
    
	<th><span class="need">*</span> 分配工程:</th>
    <td><input type="text" value="${pro_name }" id="pro_name" name="pro_name" onclick="selectProject();" readonly="readonly" class="inputxt" datatype="*1-20" nullmsg="分配工程不可为空！" />
							<input type="hidden" id="ml_to" name="materialDevice.ml_to" value="${materialDevice.ml_to }"/>
							<div class="Validform_checktip">分配工程不可为空！</div></td>

  </tr>

</table>
			</form>

		</div>
	</div>

</body>
</html>