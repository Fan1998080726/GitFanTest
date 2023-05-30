<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path %>/css/layout.css" rel="stylesheet">
<!-- Dialog相关文件 含1个css 4个js（包括jquery） -->
<link href="<%=path %>/js/dialog/skins/chrome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/artDialog.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/jquery.dialog.js"></script>
<script type="text/javascript" src="<%=path %>/js/stoprightkey.js"></script>
<!-- 权限按钮操作 -->

	<!--表单验证-->
	<script type="text/javascript" src="<%=path %>/js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/lhgcalendar/lhgcalendar.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/alert.js"></script>