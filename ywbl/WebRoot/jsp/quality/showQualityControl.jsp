<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="<%=path%>/css/layout.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/jsp/quality/quality.js"></script>
</head>

<body>
	<div class="main">
		<div class="main5">
		

			<!--form-->
			<form id="form" enctype="multipart/form-data" action="quality!addQualityControl.action" class="registerform" method="post">
			
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;">标题：</th>
    <td style="width:65%;"><input type="text" id="qc_title" name="quality_control.qc_title" readonly="readonly" value="${quality_control.qc_title}"/></td>
  </tr>
  <tr>
    <th>类型：</th>
    <td><input type="text" id="sc_name" name="quality_control.sc_name" readonly="readonly" value="${quality_control.sc_name }"/></td>
  </tr>
  <tr>
    <th>子工程节点：</th>
    <td><input type="text" id="pc_name" name="quality_control.pc_name" readonly="readonly" value="${quality_control.pc_name }"/></td>
  </tr>
  <tr>
    <th>上报时间：</th>
    <td><input type="text" id="qc_date" name="quality_control.qc_date" readonly="readonly" value="${quality_control.qc_date }"/></td>
  </tr>
  <tr>
    <th> 附件：</th>
    <td><a href="javascript:fileDownload('${quality_control.qc_file_name }','${quality_control.qc_file_url }');" style="color:#014265;">${quality_control.qc_file_name }</a></td>
  </tr>
  <tr>
    <th>描述：</th>
    <td><textarea id="qc_remark" name="quality_control.qc_remark" readonly="readonly" rows="5" cols="10">${quality_control.qc_remark }</textarea></td>
  </tr>
</table>
			</form>
			
		<div class="clear"></div>
		</div>
	</div>
</body>

</html>