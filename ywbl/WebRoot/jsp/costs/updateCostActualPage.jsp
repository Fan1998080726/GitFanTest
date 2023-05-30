<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
	<meta charset="utf-8">
    <title>建筑市场监管信息平台</title>
	<%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
	<link type="text/css" media="all" rel="stylesheet" href="<%=path%>/css/form/style.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/form/demo.css"/>
	<script type="text/javascript" src="<%=path%>/js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path %>/jsp/costs/updateCostActualPage.js"></script>
  </head>

<body onload="checkForm();">
<div class="main">
<div class="main5">

		      <input id="aa" type="button" onclick="save(1);" value="" style="background:url(<%=path%>/img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;"/>

<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%; border-bottom:0;"><span class="need">*</span> 类型：</th>
    <td style="width:65%; border-bottom:0;">
    				<%--<select id="cp_type"  onchange="test()" >
		            	<option value="1">人工</option>	
		            	<option value="2">材料</option>	
		            	<option value="3">机械</option>	
		            	<option value="35">其他</option>	
		            </select>
		             --%><input id="cp_name" name="type_id" readonly="readonly" class="inputxt" type="text" />
		            </td>
  </tr>
</table>

<!-- 人工 -->
<!--form-->
 <form id="form1" class="registerform1" action="<%=path%>/cost!updateCostActual.action">
 <div id="1" style="display:block;">
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 描述：</th>
    <td style="width:65%;">
		          	<input type="hidden" id="cp_id" name="costActual.cp_id"/>
		            <input id="cp_name" name="cp_name" readonly="readonly" class="inputxt" type="text" />
		            </td>
  </tr>
  <tr>
    <th><span class="need">*</span> 消耗值：</th>
    <td><input id="cp_value" name="costActual.ca_value" type="text" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="消耗值不可为空，可有两位小数八位整数！" />
		            <div class="Validform_checktip">消耗值不可为空，可有两位小数八位整数！</div></td>
  </tr>
  <tr>
    <th>备注：</th>
    <td><input id="cp_value" name="costActual.ca_remark" type="text"  ignore="ignore" class="inputxt" datatype="*0-100" errormsg="输入内容应在0-100个字符之间!" />
		            <div class="Validform_checktip">备注可为空，但不能超过100个字符！</div></td>
  </tr>
</table>
</div>
</form>

<!-- 材料 -->
<!--form-->
<form id="form2" class="registerform2" action="<%=path%>/cost!updateCostActual.action">
 <div id="2" style="display:none;">
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 材料名：</th>
    <td style="width:65%;">
    				<input type="hidden" id="cp_id" name="costActual.cp_id"/>
    				<!--input type="hidden" id="ca_value" name="costActual.ca_value"/-->
		          	<input id="cp_name" name="costActual.cp_name" readonly="readonly" class="inputxt" type="text" />
		            </td>
  </tr>
  <%--<tr>
    <th><span class="need">*</span> 数量：</th>
    <td><input id="cp_value" name="costActual.ca_num" type="text" class="inputxt" datatype="n1-10" errormsg="成本值需输入正整数,最多10位!" />
		            <div class="Validform_checktip">成本值需输入数字！</div></td>
  </tr>
   --%><tr>
    <th><span class="need">*</span> 消耗值：</th>
    <td><input id="cp_value" name="costActual.ca_value" type="text" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="消耗值不可为空，可有两位小数八位整数！" />
		            <div class="Validform_checktip">消耗值不可为空，可有两位小数八位整数！</div></td>
  </tr>
  <tr>
    <th>备注：</th>
    <td><input id="cp_value" name="costActual.ca_remark" type="text"  ignore="ignore" class="inputxt" datatype="*0-100" errormsg="输入内容应在0-100个字符之间!" />
		            <div class="Validform_checktip">备注可为空，但不能超过100个字符！</div></td>
  </tr>
</table>
</div>
</form>

<!-- 机械 -->
<!--form-->
<form id="form3" class="registerform3" action="<%=path%>/cost!updateCostActual.action">
 <div id="3" style="display:none;">
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 设备名：</th>
    <td style="width:65%;">
    				<input type="hidden" id="cp_id" name="costActual.cp_id"/>
		          	
		          	<input id="cp_name" name="costActual.cp_name" readonly="readonly" class="inputxt" type="text" />
		            </td>
  </tr>
  <%--<tr>
    <th><span class="need">*</span> 数量：</th>
    <td><input id="cp_value" name="costActual.ca_num" type="text" class="inputxt" datatype="n1-10" errormsg="成本值需输入正整数,最多10位!" />
		            <div class="Validform_checktip">成本值需输入数字！</div></td>
  </tr>
  --%><tr>
    <th><span class="need">*</span> 消耗值：</th>
    <td><input id="cp_value" name="costActual.ca_value" type="text" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="消耗值不可为空，可有两位小数八位整数！" />
		            <div class="Validform_checktip">消耗值不可为空，可有两位小数八位整数！"</div></td>
  </tr>
  <tr>
    <th>备注：</th>
    <td><input id="cp_value" name="costActual.ca_remark" type="text"  ignore="ignore" class="inputxt" datatype="*0-100" errormsg="输入内容应在0-100个字符之间!" />
		            <div class="Validform_checktip">备注可为空，但不能超过100个字符！</div></td>
  </tr>
</table>
</div>
</form>

<!-- 其他 -->
<!--form-->
<form id="form4" class="registerform35" action="<%=path%>/cost!updateCostActual.action">
 <div id="35" style="display:none;">
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 描述：</th>
    <td style="width:65%;">
    				<input type="hidden" id="cp_id" name="costActual.cp_id"/>
		          	<input id="cp_name" name="cp_name" readonly="readonly" class="inputxt" type="text" />
		            </td>
  </tr>
  <tr>
    <th><span class="need">*</span> 成本值：</th>
    <td><input id="cp_value" name="costActual.ca_value" type="text" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="消耗值不可为空，可有两位小数八位整数！" />
		            <div class="Validform_checktip">消耗值不可为空，可有两位小数八位整数！</div></td>
  </tr>
   <tr>
    <th>备注：</th>
    <td><input id="cp_value" name="costActual.ca_remark" type="text"  ignore="ignore" class="inputxt" datatype="*0-100" errormsg="输入内容应在0-100个字符之间!" />
		            <div class="Validform_checktip">备注可为空，但不能超过100个字符！</div></td>
  </tr>
</table>
</div>
</form>
		  </div>
		</div>
</body>
</html>
