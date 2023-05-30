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
				$.closeDialog();
			}
		});
})

function doSub() {
	
	$(".registerform").submit();
}
</script>
<body>
	<div class="main">
		<div class="main5">
		
         <!--toolbar-->
		 
		
			<!--form-->
			<form id="form" class="registerform" action="report!save.action">
			
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:20%;"><span class="need">*</span> 中标合同价:</th>
    <td style="width:30%;">
    	<input type="hidden" value="${report.pr_id}" id="pr_id" name="report.pr_id" class="inputxt"/>
    	<input type="text" value="${report.pr_price}" id="pr_price" name="report.pr_price" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="中标合同价可有八位整数两位小数!"/>
    	<div class="Validform_checktip">中标合同价可有八位整数两位小数!</div>
	</td>
    <th style="width:20%;"><span class="need">*</span> 上报金额:</th>
    <td style="width:30%;">
    	<input type="text" value="${report.pr_report }" id="pr_report" name="report.pr_report" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="上报金额可有八位整数两位小数!"/>
    	<div class="Validform_checktip">上报金额可有八位整数两位小数!</div>
	</td>
  </tr>
  <tr>
    <th><span class="need">*</span> 审批金额:</th>
    <td colspan="3">
    	<input type="text" value="${report.pr_approval }" id="pr_approval"  name="report.pr_approval" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="审批金额可有八位整数两位小数!"/>
    	<div class="Validform_checktip">审批金额可有八位整数两位小数!</div>
	</td>
  </tr>
  <tr>
    <th><span class="need">*</span>支付金额:</th>
    <td>
    	<input type="text" value="${report.pr_pay }" id="pr_pay" name="report.pr_pay" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="支付金额可有八位整数两位小数!"/>
    	<div class="Validform_checktip">支付金额可有八位整数两位小数!</div>
    </td>
    <th><span class="need">*</span>支付比例:</th>
    <td>
    	<input type="text" value="${report.pr_rate }" id="pr_rate" name="report.pr_rate" class="inputxt"  datatype="/^\d{1,1}(?:\.\d{0,2})?$/" errormsg="支付比例可有一位整数两位小数!"/>
    	<div class="Validform_checktip">支付比例可有一位整数两位小数!</div>
    </td>
  </tr>
  <tr>
    <th><span class="need">*</span>累计支付:</th>
    <td>
    	<input type="text" value="${report.pr_total_pay }" id="pr_total_pay" name="report.pr_total_pay" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="累计支付可有八位整数两位小数!" />
    	<div class="Validform_checktip">累计支付可有八位整数两位小数!</div>
	</td>
    <th><span class="need">*</span>累计支付比例:</th>
    <td>
    	<input type="text" value="${report.pr_total_rate }" id="pr_total_rate" name="report.pr_total_rate" class="inputxt" datatype="/^\d{1,1}(?:\.\d{0,2})?$/" errormsg="累计支付比例可有一位整数两位小数!" />
    	<div class="Validform_checktip">累计支付比例可有一位整数两位小数!</div>
	</td>
  </tr>
</table>
	
			</form>
			
			<input type="button" onclick="doSub();" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:25px; border:none; margin-top:10px; float:right;" />

		</div>
	</div>

</body>
</html>