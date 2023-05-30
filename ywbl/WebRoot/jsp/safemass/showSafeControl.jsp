<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="<%=path%>/css/layout.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/jsp/safemass/safemass.js"></script>
</head>

<body>
	<div class="main">
		<div class="main5">
		

			<!--form-->
			<form id="form" enctype="multipart/form-data" action="safe!addSafeControl.action" class="registerform" method="post">
			
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;">标题：</th>
    <td style="width:65%;"><input type="text" id="sc_title" name="safe_control.sc_title" readonly="readonly" value="${safe_control.sc_title}"/></td>
  </tr>
  <tr>
    <th>类型：</th>
    <td><input type="text" id="sc_name" name="safe_control.sc_name" readonly="readonly" value="${safe_control.sc_name }"/></td>
  </tr>
  <tr>
    <th>子工程节点：</th>
    <td><input type="text" id="pc_name" name="safe_control.pc_name" readonly="readonly" value="${safe_control.pc_name }"/></td>
  </tr>
  <tr>
    <th>上报时间：</th>
    <td><input type="text" id="sc_type" name="safe_control.sc_date" readonly="readonly" value="${safe_control.sc_date }"/></td>
  </tr>
  <tr>
    <th> 附件：</th>
    <td><a href="javascript:fileDownload('${safe_control.sc_file_name }','${safe_control.sc_file_url }');" style="color:#014265;">${safe_control.sc_file_name }</a></td>
  </tr>
  <tr>
    <th>描述：</th>
    <td><textarea id="sc_remark" name="safe_control.sc_remark" rows="5" cols="10" readonly="readonly">${safe_control.sc_remark }</textarea></td>
  </tr>
</table>
			</form>
			
		<div class="clear"></div>
		</div>
	</div>
</body>

</html>