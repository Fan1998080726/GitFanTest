<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link rel="stylesheet" href="css/form/style.css" type="text/css"
		media="all" />
	<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
	<script type="text/javascript"
		src="<%=path%>/jsp/project/realView/realViewAdd.js"></script>
</head>

<body>
	<div class="main">
		<div class="main5">
			<!--toolbar-->
			<input type="button" onclick="doSub();" value=""
				style="background:url(<%=path%>/img/tijiao.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
			<!--form-->
			<form id="form" action="" class="registerform" method="post">
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th><span class="need">*</span>三维实景：</th>
						<td><input id="file" name="file" type="file" value="上传附件"/></td>
					</tr>
					<tr>
						<th>描述：</th>
						<td><textarea id="remark" name="rv.realRemark" rows="5" cols="5" datatype="*1-20"></textarea>
						</td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</body>

</html>