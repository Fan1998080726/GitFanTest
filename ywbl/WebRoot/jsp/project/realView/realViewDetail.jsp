<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link rel="stylesheet" href="css/form/style.css" type="text/css"
		media="all" />
	<script type="text/javascript"
		src="<%=path%>/jsp/project/realView/realViewDetail.js"></script>
</head>

<body>
	<div class="main">
		<div class="main5">
			<!--toolbar-->
			<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th width="150px">文件名称：</th>
						<td>${realView.realName}</td>
					</tr>
					<tr>
						<th width="150px">上传人员：</th>
						<td>${realView.realUserName}</td>
					</tr>
					<tr>
						<th width="150px">上传时间：</th>
						<td>${realView.realDate}</td>
					</tr>
				</table>				
			<object id="myFlash2"
						data="<%=request.getContextPath()%>/gcjg-debug/nbjt.swf"
						type="application/x-shockwave-flash" width="566" height="300">
						<param name="movie" value="<%=request.getContextPath()%>/gcjg-debug/nbjt.swf" />
						<param name="wmode" value="transparent" />
						<param name="FlashVars" value="flexType=Real3d&eventId=${realView.realId}" />
			</object>
		</div>
	</div>
</body>

</html>