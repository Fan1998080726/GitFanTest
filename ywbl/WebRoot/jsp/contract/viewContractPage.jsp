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
			<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
			<script type="text/javascript"
				src="js/lhgcalendar/lhgcalendar.min.js"></script>
			<script src="<%=path%>/jsp/contract/viewContractPage.js"></script>
			
	<!--easyUI-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.jdirk.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.extensions.all.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.icons.all.min.js"></script>

<style type="text/css">
input[disabled], select[disabled], textarea[disabled], input[readonly], select[readonly], textarea[readonly] {
 background:none;
 border:none;
}
</style>
	
</head>
<body>
	<div class="main">
		<c:if test="${flag eq 0 }">
		<div class="breadcrumb">当前位置：合同详情</div>
		</c:if>

		<!--main3-->
		<div class="main3">
		
		<div class="easyui-tabs" style="margin:30px;">
		<div title="合同详情" data-options="selected:true" style="padding:10px;">
		
		<div class="formsub">
					
					<!--toolbar-->
	<c:if test="${'12'==contractFlag }">
			<div class="action" style="margin:10px 0;">
				<input type="button" onclick="gotoUpdate();" value=""
					style="background:url(<%=path %>/img/t-edit.png) no-repeat; width:64px; height:25px; border:none; margin:0;" />
				<input type="button" onclick="changeFlag();" value=""
					style="background:url(<%=path%>/img/tijiao.png) no-repeat;  width:64px; height:25px; border:none; margin:0;" />
			</div>
	 </c:if>
			<div class="clear"></div>
			
			
				<table class="table2 table2-striped table2-bordered table2-condensed table-down">
					<tr>
					  <th colspan="5">发包人：</th>
						<td colspan="10">${contract.c_employer } </td>
					</tr>
					<tr>
					  <th colspan="5">承包人：</th>
						<td colspan="10">${contract.c_contractor } </td>
					</tr>
					<tr>
					  <th colspan="5" width="20%">工程名称：</th>
						<td colspan="3" width="30%">${contract.c_name }
						</td>
						<th colspan="3" width="20%">工程地点：</th>
						<td colspan="4" width="30%">${contract.c_address }
						</td>
					</tr>
					<tr>
					  <th colspan="5">承包范围：</th>
						<td colspan="10">${contract.c_range } </td>
					</tr>
					<tr>
					  <th colspan="5">工程开始时间：</th>
						<td colspan="3">${contract.c_start_date }
							</td>
						<th colspan="3">工程结束时间：</th>
						<td colspan="4">${contract.c_end_date }
							</td>
					</tr>
					<tr>
					  <th colspan="5">工程质量标准：</th>
						<td colspan="10">${contract.c_standard }</td>
					</tr>
					<tr>
					  <th colspan="5">计价方式为：</th>
						<td colspan="3">${contract.c_mode } </td>
						<th colspan="3">合同金额(元)：</th>
						<td colspan="4">${contract.c_invest }
						</td>
					</tr>
					<tr>
					  <th colspan="5">合同订立时间：</th>
						<td colspan="3">${contract.c_conclusion_date }
							</td>
						<th colspan="3"  style="min-width:100px;">合同订立地点：</th>
						<td colspan="4">${contract.c_conclusion_place }
						</td>
					</tr>
					<tr>
					  <th colspan="5">双方约定：</th>
						<td colspan="10">${contract.c_appoint }</td>
					</tr>
					<tr>
					  <th rowspan="4"  width="6%"  style="min-width:60px;">发包人：</th>
						<th colspan="4">地址：</th>
						<td colspan="10">${contract.c_e_address }
						</td>
					</tr>
					<tr>
					  <th colspan="4">电话：</th>
						<td colspan="3">${contract.c_e_phone }
							</td>
						<th colspan="3">传真：</th>
						<td colspan="4"  width="24%">${contract.c_e_fax } </td>
					</tr>
					<tr>
					  <th colspan="4" style="min-width:75px;">开户银行：</th>
						<td colspan="3">${contract.c_e_bank }
						</td>
						<th colspan="3">账号：</th>
						<td colspan="4">${contract.c_e_account } </td>
					</tr>
					<tr>
					  <th colspan="4">邮政编码：</th>
						<td colspan="3">${contract.c_e_zipcode }
						</td>
						<th colspan="3">电子邮箱：</th>
						<td colspan="4">${contract.c_e_email }
							</td>
					</tr>
					<tr>
					  <th rowspan="4">承包人：</th>
					  <th colspan="4">地址：</th>
					  <td colspan="10">${contract.c_c_address }
					</td>
					</tr>
					<tr>
					  <th colspan="4">电话：</th>
						<td colspan="3">${contract.c_c_phone }
							</td>
						<th colspan="3">传真：</th>
						<td colspan="4">${contract.c_c_fax } </td>
					</tr>
					<tr>
					  <th colspan="4">开户银行：</th>
						<td colspan="3">${contract.c_c_bank }
						</td>
						<th colspan="3">账号：</th>
						<td colspan="4">${contract.c_c_account } </td>
					</tr>
					<tr>
					  <th colspan="4">邮政编码：</th>
						<td colspan="3">${contract.c_c_zipcode }
						</td>
						<th colspan="3">电子邮箱：</th>
						<td colspan="4">${contract.c_c_email}
							</td>
					</tr>
					<tr>
					  <th colspan="5">附件：</th>
						<td colspan="10">
							<c:forEach var="m" items="${fileList}">
				            	<a href="javascript:fileDownload('${m.cf_url }','${m.cf_name }');">${m.cf_name }</a><br/>
			            	</c:forEach>
						</td>
					</tr>
					
				</table>
			</div>
			
			</div>
			</div>
			
		</div>
	</div>
</body>
</html>