<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>监理企业信用评价系统</title>
<script type="text/javascript" src="js/contractShow.js"></script>
<link href="css/contract.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jquery/jquery-132min2.js"></script>
<script type="text/javascript" src="js/hiddenss.js"></script>
<script type="text/javascript" src="js/city.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/jsp/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/contract.js"></script>
<link href="<%=path%>/css/ui-lightness/jquery-ui-1.8.22.custom.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/js/dialog/skins/chrome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/dialog/artDialog.js"></script>
<script type="text/javascript" src="<%=path%>/js/dialog/artDialog.source.js"></script>
<script type="text/javascript" src="<%=path%>/js/dialog/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=path%>/js/dialog/iframeTools.js"></script>
<script type="text/javascript" src="<%=path%>/js/dialog/jquery.dialog.js"></script>
		    <script type="text/javascript" src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
			<script src="<%=path%>/js/jquery.form.js"></script>
		<script src="<%=path%>/jsp/uploadFile.js"></script>
				<script type="text/javascript" >
				
			 
		</script>	
</head>
<body>
<div>
	<div align="right"  >
				<input type="button" value="确定" onclick="ajaxSubmit()" style="width:64px;" /> 
   </div>  
		<div class="formsub">
		<form id="filesForm" enctype="multipart/form-data" method="post" action="">
			<table align="center">
				<tr>
					<td>1.</td>
					<td><input type="file" id="myFile_1" name="myFile_1" value=""/></td>
			        <input type="hidden" id="type"  value="13"/>
				</tr>
				
			</table>
		</form>
		</div>
	</div>

</body>
</html>