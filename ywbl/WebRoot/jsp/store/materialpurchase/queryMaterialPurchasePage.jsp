<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/form/style.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jsp/store/materialpurchase/materialpurchase.js"></script>
<script type="text/javascript" src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
</head>
	<script type="text/javascript">
	function fileDownload(sc_file_name, sc_file_url){
		var url = "constructlog!fileDownload.action?sc_file_name="+sc_file_name+"&sc_file_url="+sc_file_url+"";
		window.location.href=url;
	}
	</script>
<body>
	<div class="main">


		<div class="main5">
			<!--toolbar-->

			<!--form-->
			<form id="form" class="registerform" action="purchase!updateMaterialPurchase.action">
				<input type="hidden" name="materialPurchase.mp_flag" value="${materialPurchase.mp_flag}" />
				<input type="hidden" name="materialPurchase.mp_id" value="${materialPurchase.mp_id}" />
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th style="width:25%;"><span class="need">*</span> 采购单号:</th>
						<td style="width:75%;">
							<input type="text" id="mp_name" name="materialPurchase.mp_name" value="${materialPurchase.mp_name}" class="inputxt"
									readonly="readonly" />
						</td></tr>
						<tr>
						<th><span class="need">*</span> 采购列表:</th>
						<td>
							<table id="tab1" class="table2 table2-striped table2-bordered table2-condensed table2-down">
								<%--<tr>
									<td colspan="8" ><input type="button" onclick="selectMaterial();" style="background:url(<%=path %>/img/t-add.png) no-repeat;width:64px; height:25px; border:none; margin-bottom:10px;"" /></td>
								</tr>
								--%><tr>
									<td>材料名称</td>
									<td>规格</td>
									<td>数量</td>
									<td>单价（元）</td>
								</tr>
								 <c:forEach items="${purchaseInfos}" var="mpi" varStatus="stauts">
								 <tr>
								 	<td>
								 		${mpi.mt_name}
								 	</td>
								 	<td>${mpi.mpinfo_standard}</td>
								 	<td>${mpi.mpinfo_num}</td>
								 	<td>${mpi.mpinfo_price}</td>
								 </tr>
								 </c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<th> 备注:</th>
						<td>
							<input type="text" id="mda_unit"
									name="materialPurchase.mp_remark" value="${materialPurchase.mp_remark}" class="inputxt" readonly="readonly"
									/>
							
						</td>
					</tr>
					<tr>
						<th> 日期:</th>
						<td><input type="text" readonly="readonly" 
							id="mda_date" name="materialPurchase.mp_date" value="${materialPurchase.mp_date}" class="inputxt" />
							
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span> 附件:</th>
						<td>
							 <c:forEach var="log" items="${ list }" varStatus="i">
							           <p><a href="javascript:fileDownload('${log.mpfile_name }','${log.mpfile_url}');"><span style="color: black;">${log.mpfile_name}</span>$</a></p>
                             </c:forEach>
							</td>
					</tr>
				</table>
			</form>
  </div>
  </div>
</body>
</html>