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
    	<input type="text" value="${report.pr_price}" id="pr_price" name="report.pr_price" class="inputxt" />
	</td>
    <th style="width:20%;"><span class="need">*</span> 上报金额:</th>
    <td style="width:30%;">
    	<input type="text" value="${report.pr_report }" id="pr_report" name="report.pr_report" class="inputxt"/>
	</td>
  </tr>
  <tr>
    <th><span class="need">*</span> 审批金额:</th>
    <td colspan="2">
    	<input type="text" value="${report.pr_approval }" id="pr_approval"  name="report.pr_approval" class="inputxt" />
	</td>
  </tr>
  <tr>
    <th><span class="need">*</span>支付金额:</th>
    <td>
    	<input type="text" value="${report.pr_pay }" id="pr_pay" name="report.pr_pay" class="inputxt"/>
    </td>
    <th><span class="need">*</span>支付比例:</th>
    <td>
    	<input type="text" value="${report.pr_rate }" id="pr_rate" name="report.pr_rate" class="inputxt"/>
    </td>
  </tr>
  <tr>
    <th><span class="need">*</span>累计支付:</th>
    <td>
    	<input type="text" value="${report.pr_total_pay }" id="pr_total_pay" name="report.pr_total_pay" class="inputxt"/>
	</td>
    <th><span class="need">*</span>累计支付比例:</th>
    <td>
    	<input type="text" value="${report.pr_total_rate }" id="pr_total_rate" name="report.pr_total_rate" class="inputxt" />
	</td>
  </tr>
</table>
	
			</form>
			

		</div>
	</div>

</body>
</html>