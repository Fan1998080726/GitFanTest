<!-- uploadExcel.jsp 20200305 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="css/layout.css" type="text/css" rel="stylesheet">
		<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
			<!--表单验证-->
			<link rel="stylesheet" href="css/form/style.css" type="text/css"
				media="all" />
			<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
			<!--tree-->
			<script type="text/javascript" src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
			<script src="<%=path%>/js/jquery.form.js"></script>
			<script src="<%=path%>/jsp/project/uploadExcel.js"></script>
</head>
<div class="main">
	<div class="main5">
				<input type="button" value="" onclick="ajaxSubmit()"
					style="background:url(img/queding.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px; " /> 

		<div class="formsub">
		<form id="filesForm" enctype="multipart/form-data" method="post" action="excelUpload!createProjectChild.action">
			<input type="file" id="myFile" name="myFile"/>
		</form>
		</div>
	</div>
</div>
</html>