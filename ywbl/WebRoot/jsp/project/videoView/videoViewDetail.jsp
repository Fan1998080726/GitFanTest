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
			<table
				class="table2 table2-striped table2-bordered table2-condensed table2-down">
				<tr>
					<th width="150px">文件名称：</th>
					<td>${videoViewVo.videoName}</td>
				</tr>
				<tr>
					<th width="150px">上传人员：</th>
					<td>${videoViewVo.videoUserName}</td>
				</tr>
				<tr>
					<th width="150px">上传时间：</th>
					<td>${videoViewVo.videoDate}</td>
				</tr>
				<tr>
					<th width="150px">备注：</th>
					<td>${videoViewVo.videoRemark}</td>
				</tr>
			</table>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0"
				height="325" width="566">
				<param name="movie"
					value="jsp/video/flvplayer.swf?file=${httpurl }${videoViewVo.videoUrl}">
					<param name="quality" value="high">
						<param name="allowFullScreen" value="true" />
						<embed
							src="jsp/video/flvplayer.swf?file=${httpurl }${videoViewVo.videoUrl}"
							quality="high"
							pluginspage="http://www.macromedia.com/go/getflashplayer"
							type="application/x-shockwave-flash" width="320" height="240">
						</embed>
		</div>
	</div>
</body>

</html>