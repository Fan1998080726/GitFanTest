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
		<script type="text/javascript" src="<%=path %>/jsp/system/dept/updateDeptPage.js"></script>
	</head>

<body>
	<div class="main">
	  
	    <div class="main5">

	  
	  <form name="form1" class="registerform" action="dept!updateDept.action">
	  <input type="hidden" name="deptVo.dept_id" value="${deptVo.dept_id }"/>
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 部门名称:</th>
    <td style="width:65%;"><input type="text" value="${deptVo.dept_name }" name="deptVo.dept_name" class="inputxt" datatype="*1-20" errormsg="部门名称不可为空,最多20个字符！" />
								<div class="Validform_checktip">部门名称不可为空,最多20个字符！</div></td>
  </tr>
  <tr>
    <th>描述:</th>
    <td><input type="text" value="${deptVo.dept_describe }" name="deptVo.dept_describe" class="inputxt" /></td>
  </tr>
</table>

		</form>
		
		<input type="button" onclick="doSub()" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; float:right;"/>
  </div>
  
  	</div>
	</body>
</html>