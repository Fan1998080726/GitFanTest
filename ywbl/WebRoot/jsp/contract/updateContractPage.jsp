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
			<script type="text/javascript"
				src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
	<!--easyUI-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.jdirk.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.extensions.all.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.icons.all.min.js"></script>
	
</head>
<body>
	<div class="main">
		<div class="breadcrumb">当前位置：新建合同</div>

			<!--main3-->
			<div class="main3">

				<!--form-->
				<div class="easyui-tabs" style="margin:30px;">
		<div title="新建合同" data-options="selected:true" style="padding:10px;">
		
				<div class="formsub">
					
					
			
			
			<form id="form" class="registerform" onkeydown="if(event.keyCode==13){return false;}" enctype="multipart/form-data" method="post">
			<input type="hidden" name="contract.c_id"
					value="${contract.c_id }" />
					<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
						<tr>
						  <th colspan="5"><span>*</span> 发包人：</th>
							<td colspan="10">
								<input type="text" name="contract.c_employer" value="${contract.c_employer }"
								class="inputxt" datatype="*1-50" errormsg="发包人不可为空,最多50个字符！" />
								<div class="Validform_checktip">发包人名称不可为空,最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 承包人：</th>
							<td colspan="10">
								<input type="text" name="contract.c_contractor" value="${contract.c_contractor }"
								class="inputxt" datatype="*1-50" errormsg="发包人不可为空,最多50个字符！" />
								<div class="Validform_checktip">承包人名称不可为空,最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5" width="20%"><span>*</span> 工程名称：</th>
							<td colspan="3" width="30%">
								<input type="text" name="contract.c_name" value="${contract.c_name }"
								class="inputxt" datatype="*1-50" errormsg="工程名称不可为空,最多50个字符！" />
								<div class="Validform_checktip">工程不可为空,最多50个字符！</div>
							</td>
							<th colspan="3" width="20%"><span>*</span> 工程地点：</th>
							<td colspan="4" width="30%">
								<input type="text" name="contract.c_address" value="${contract.c_address }"
								class="inputxt" datatype="*1-50" errormsg="工程地点不可为空,最多50个字符！" />
								<div class="Validform_checktip">工程地点不可为空,最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 承包范围：</th>
						   <td colspan="10" >
								<textarea class="input-xlarge" id="textarea"
								name="contract.c_range" rows="3" datatype="*1-250" errormsg="承包范围不可为空,最多250个字符！"
								style="width: 805px; height: 116px;">${contract.c_range }</textarea>
								<div class="Validform_checktip">承包范围不可为空,最多250个字符！</div>
						   </td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 工程开始时间：</th>
							<td colspan="3">
								<input type="text" name="contract.c_start_date" id="c_start_date" value="${contract.c_start_date }"
								onclick="$.calendar();" readonly="readonly"
								class="inputxt" datatype="*" nullmsg="开始时间不可为空！" />
								<div class="Validform_checktip">开始时间不可为空！</div>
							</td>
							<th colspan="3"><span>*</span> 工程结束时间：</th>
							<td colspan="4">
								<input type="text" name="contract.c_end_date" id="c_end_date" value="${contract.c_end_date }"
								onclick="$.calendar();" readonly="readonly"
								class="inputxt" datatype="*" nullmsg="结束时间不可为空！" />
								<div class="Validform_checktip">结束时间不可为空！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 工程质量标准：</th>
							<td colspan="10">
								<textarea class="input-xlarge" id="textarea"
								name="contract.c_standard" rows="3" datatype="*1-250" errormsg="工程质量标准不可为空,最多250个字符！"
								style="width: 805px; height: 116px;">${contract.c_standard }</textarea>
								<div class="Validform_checktip">工程质量标准不可为空,最多250个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 计价方式为：</th>
							<td colspan="3">
								<input type="text" name="contract.c_mode" value="${contract.c_mode }"
								class="inputxt" datatype="*1-50" errormsg="计价方式不可为空,最多50个字符！" />
								<div class="Validform_checktip">计价方式不可为空,最多50个字符！</div>
							</td>
							<th colspan="3"><span>*</span> 合同金额(元)：</th>
							<td colspan="4">
								<input type="text" name="contract.c_invest" value="${contract.c_invest }"
								class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" nullmsg="合同金额不可为空,可有八位整数两位小数！" errormsg="合同金额不可为空,可有八位整数两位小数！" />
								<div class="Validform_checktip">合同金额不可为空,可有八位整数两位小数！</div>

							</td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 合同订立时间：</th>
							<td colspan="3">
								<input type="text" name="contract.c_conclusion_date" id="c_conclusion_date"
								onclick="$.calendar();" readonly="readonly" value="${contract.c_conclusion_date }"
								class="inputxt" datatype="*" nullmsg="合同订立时间不可为空！" />
								<div class="Validform_checktip">合同订立时间不可为空！</div>
							</td>
							<th colspan="3"><span>*</span> 合同订立地点：</th>
							<td colspan="4">
								<input type="text" name="contract.c_conclusion_place" value="${contract.c_conclusion_place }"
								class="inputxt" datatype="*1-50" errormsg="合同订立地点不可为空,最多50个字符！" />
								<div class="Validform_checktip">合同订立地点不可为空,最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5"><span>*</span> 双方约定：</th>
							<td colspan="10">
								<textarea class="input-xlarge" id="textarea"
								name="contract.c_appoint" rows="3" datatype="*1-250" errormsg="双方约定不可为空,最多250个字符！"
								style="width: 805px; height: 116px;">${contract.c_appoint }</textarea>
								<div class="Validform_checktip">双方约定不可为空,最多250个字符！</div>
							</td>
						</tr>
						<tr>
						  <th rowspan="4"  width="6%">发包人：</th>
							<th colspan="4">地址：</th>
							<td colspan="10"><input type="text" name="contract.c_e_address" value="${contract.c_e_address }" 
							class="inputxt" datatype="*0-50"  ignore="ignore" errormsg="地址最多50个字符！"/>
							<div class="Validform_checktip">地址最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="4">电话：</th>
							<td colspan="3"><input type="text" name="contract.c_e_phone" value="${contract.c_e_phone }" 
							class="inputxt" datatype="m"  ignore="ignore" errormsg="请输入正确的手机号码！"/>
							<div class="Validform_checktip">请输入正确的手机号码！</div>
							</td>
							<th colspan="3">传真：</th>
							<td colspan="4"><input type="text" name="contract.c_e_fax"  value="${contract.c_e_fax }"
							class="inputxt" datatype="*0-12"  ignore="ignore" errormsg="传真最多12个字符！"/>
							<div class="Validform_checktip">传真最多12个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="4">开户银行：</th>
							<td colspan="3"><input type="text" name="contract.c_e_bank"  value="${contract.c_e_bank }"
							class="inputxt" datatype="*0-50"  ignore="ignore" errormsg="开户银行最多50个字符！"/>
							<div class="Validform_checktip">开户银行最多50个字符！</div>
							</td>
							<th colspan="3">账号：</th>
							<td colspan="4"><input type="text" name="contract.c_e_account"  value="${contract.c_e_account }"
							class="inputxt" datatype="*0-50"  ignore="ignore" errormsg="账号最多50个字符！"/>
							<div class="Validform_checktip">账号最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="4">邮政编码：</th>
							<td colspan="3"><input type="text" name="contract.c_e_zipcode"  value="${contract.c_e_zipcode }"
							class="inputxt" datatype="p"  ignore="ignore" errormsg="请输入正确的邮政编码！"/>
							<div class="Validform_checktip">请输入正确的邮政编码！</div>
							</td>
							<th colspan="3">电子邮箱：</th>
							<td colspan="4"><input type="text" name="contract.c_e_email"  value="${contract.c_e_email }"
							class="inputxt" datatype="e"  ignore="ignore" errormsg="请输入正确的电子邮箱！"/>
							<div class="Validform_checktip">请输入正确的电子邮箱！</div>
							</td>
						</tr>
						<tr>
						  <th rowspan="4">承包人：</th>
							<th colspan="4">地址：</th>
							<td colspan="10"><input type="text" name="contract.c_c_address" value="${contract.c_c_address }"
							class="inputxt" datatype="*0-50"  ignore="ignore" errormsg="地址最多50个字符！"/>
							<div class="Validform_checktip">地址最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="4">电话：</th>
							<td colspan="3"><input type="text" name="contract.c_c_phone" value="${contract.c_c_phone }"
							class="inputxt" datatype="m"  ignore="ignore" errormsg="请输入正确的手机号码！"/>
							<div class="Validform_checktip">请输入正确的手机号码！</div>
							</td>
							<th colspan="3">传真：</th>
							<td colspan="4"><input type="text" name="contract.c_c_fax" value="${contract.c_c_fax}"
							class="inputxt" datatype="*0-12"  ignore="ignore" errormsg="传真最多12个字符！"/>
							<div class="Validform_checktip">传真最多12个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="4">开户银行：</th>
							<td colspan="3"><input type="text" name="contract.c_c_bank" value="${contract.c_c_bank}" 
							class="inputxt" datatype="*0-50"  ignore="ignore" errormsg="开户银行最多50个字符！"/>
							<div class="Validform_checktip">开户银行最多50个字符！</div>
							</td>
							<th colspan="3">账号：</th>
							<td colspan="4"><input type="text" name="contract.c_c_account" value="${contract.c_c_account}" 
							class="inputxt" datatype="*0-50" ignore="ignore" errormsg="账号最多50个字符！"/>
							<div class="Validform_checktip">账号最多50个字符！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="4">邮政编码：</th>
							<td colspan="3"><input type="text" name="contract.c_c_zipcode" value="${contract.c_c_zipcode}" 
							class="inputxt" datatype="p"  ignore="ignore" errormsg="请输入正确的邮政编码！"/>
							<div class="Validform_checktip">请输入正确的邮政编码！</div>
							</td>
							<th colspan="3">电子邮箱：</th>
							<td colspan="4"><input type="text" name="contract.c_c_email" value="${contract.c_c_email}" 
							class="inputxt" datatype="e"  ignore="ignore" errormsg="请输入正确的电子邮箱！"/>
							<div class="Validform_checktip">请输入正确的电子邮箱！</div>
							</td>
						</tr>
						<tr>
						  <th colspan="5">附件：</th>
							<td colspan="10">

								附件上传：<span
									onclick="addFileUpload()" class="btn btn-small btn-info" style="color:#fff; font-weight:bold;">添加文件上传</span>
								<table id="fileUploadTable" style="margin-top:8px;">
									<c:forEach var="m" items="${fileList}">
										<tr>
											<td>${m.cf_name }
											</td>
											<td>
											<input type="button" value="删除" onclick="delFile(${m.cf_id },this);" />
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						
					</table>
				</form>
				
				<!--toolbar-->
				<div class="action"  style="width:136px; margin:10px auto 0;">
					<input type="button" onclick="update(12);" value=""
						style="background:url(<%=path %>/img/t-zancun.png) no-repeat; width:64px; height:25px; border:none; margin:0;" />
                    <input
						type="button" onclick="update(13);" value=""
						style="background:url(<%=path %>/img/tijiao.png) no-repeat; width:64px; height:25px; border:none; margin:0;" />
				</div>
			
				</div>
				
				</div>
				</div>
				
			</div>
		
	</div>
</body>
<script type="text/javascript" src="<%=path%>/jsp/contract/updateContractPage.js"></script>
</html>