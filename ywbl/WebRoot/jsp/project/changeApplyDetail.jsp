<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link rel="stylesheet" href="css/form/style.css" type="text/css"
		media="all" />
	<!--表单验证-->

	<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
	<script type="text/javascript"
		src="<%=path%>/jsp/project/changeApplyDetail.js"></script>
</head>

<body>
	<div class="main">
		<div class="main5">
			<!--toolbar-->
			<c:if test="${changeApply.aaFlag eq 29 }">
				<input type="button" onclick="doSub('${changeApply.aaId}');"
					value=""
					style="background:url(<%=path%>/img/tijiao.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
			</c:if>
			<!--form-->
			<form id="form" action="" class="registerform" method="post">
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th>变更时间：</th>
						<td><input type="text" value="${changeApply.aaDate}" readOnly />
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span>附件(最大10M)：</th>
						<td>
							<table id="fileUploadTable"
								class="table2 table2-striped table2-bordered table2-condensed table2-down">
								<c:forEach items="${changeApply.files}" var="files">
									<tr>
										<td><a style="color: black" href="javascript:fileDownload('${files.cafName }','${files.cafUrl }')">${files.cafName }</a></td>
									</tr>
								</c:forEach>
							</table></td>
					</tr>
					<tr>
						<th>备注：</th>
						<td><textarea id="remark" name="remark" rows="5" cols="5"
								readOnly>${changeApply.aaRemark }</textarea></td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</body>

</html>