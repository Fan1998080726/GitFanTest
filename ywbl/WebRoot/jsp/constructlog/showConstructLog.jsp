<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="<%=path%>/css/layout.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=path %>/jsp/constructlog/constructlog.js"></script>
	<script type="text/javascript">
	function fileDownload(sc_file_name, sc_file_url){
		var url = "constructlog!fileDownload.action?sc_file_name="+sc_file_name+"&sc_file_url="+sc_file_url+"";
		window.location.href=url;
	}
	</script>
	

</head>

<body>
	<div class="main">
		<div class="main5">
		

			<!--form-->
			<form id="form" enctype="multipart/form-data" action="safe!addSafeControl.action" class="registerform" method="post">
			
						<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;">上报人员：</th>
    <td style="width:65%;"><input type="text" id="sc_title" name="constructLog.user_name" value="${constructLog.user_name}"/></td>
  </tr>
  <tr>
    <th>附件：</th>
    <td><c:forEach var="log" items="${ list }" varStatus="i">
							           <p><a href="javascript:fileDownload('${log.clf_name }','${log.clf_url}');"><span style="color: black;">${log.clf_name}</span>$</a></p>
                             </c:forEach></td>
  </tr>
  <tr>
    <th>上报时间：</th>
    <td><input type="text" id="sc_title" name="constructLog.cl_date" value="${constructLog.cl_date}"/></td>
  </tr>
  <tr>
    <th>上报描述：</th>
    <td><textarea id="sc_remark" name="constructLog.cl_describe" >${constructLog.cl_describe }</textarea></td>
  </tr>
</table>
			</form>
			
		<div class="clear"></div>
		</div>
	</div>
</body>

</html>