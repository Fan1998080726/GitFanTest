<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<% 
String datetime=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()); //获取系统时间 
%>
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
	$(function() {
		var demo = $(".registerform").Validform({
			ajaxPost : true, //异步

			tiptype : function(msg, o, cssctl) {
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip = $("#msgdemo2");//o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				}
			},
			callback : function(data) {
				if (data.status == "y") {
					$.alert('保存成功！');
					$.closeDialog("","addId");
				} else {

					$.alert(data.info);
				}
			}
		});
		demo.addRule([ {
			ele : "select",
			datatype : "*"
		} ]);
	})

</script>
<body>
	<div class="main">

		<div class="main5">
			<!--toolbar-->
			<input type="button" onclick="doSub(36);"	style="background:url(img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
			<input type="button" name="btn_tj" onclick="update(37);" value="" style="background:url(img/tijiao.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
			<!--form-->
			<form id="form" class="registerform" action="purchase!addMaterialPurchase.action">
				<input type="hidden" id="mp_flag" name="materialPurchase.mp_flag" value="36">
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th style="width:25%;"><span class="need">*</span> 采购单号:</th>
						<td style="width:75%;">
							<input type="text" id="mp_name" name="materialPurchase.mp_name" value="<%=datetime%>" readonly="readonly" class="inputxt"
									datatype="n1-20" nullmsg="采购单号不能为空!" errormsg="采购单号为数字且不可为空，最多20字符！" />
							<div class="Validform_checktip">采购单号为数字且不可为空，最多20字符！</div>
						</td></tr>
						<tr>
						<th><span class="need">*</span> 采购列表:</th>
						<td>
							<table id="tab1" class="table2 table2-striped table2-bordered table2-condensed table2-down">
								<tr>
									<td colspan="5" ><input type="button" onclick="selectMaterial();" style="background:url(<%=path %>/img/t-add.png) no-repeat;width:64px; height:25px; border:none; margin-bottom:10px;"" /></td>
								</tr>
								<tr>
									<td><span class="need">*</span> 材料名称</td>
									<td><span class="need">*</span> 规格</td>
									<td><span class="need">*</span> 数量</td>
									<td> 单价（元）</td>
									<td>操作</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th> 备注:</th>
						<td>
							<input type="text" id="mda_unit"
									name="materialPurchase.mp_remark" class="inputxt"
									datatype="*1-20" ignore="ignore" errormsg="备注可为空，但不能超过20字符！" />
							<div class="Validform_checktip">备注可为空，但不能超过20字符！</div>
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span> 日期:</th>
						<td><input type="text" readonly="readonly" onclick="$.calendar();" datatype="*1-20" nullmsg="请选择日期！"  errormsg="请选择日期！"
							id="mda_date" name="materialPurchase.mp_date" class="inputxt" />
							<div class="Validform_checktip">日期不可为空！</div>
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span> 附件:</th>
					<td colspan="3" class="text1">
            	<span onclick="addFileUpload()" class="btn btn-small btn-info" style="color:#fff; font-weight:bold;">添加文件上传</span>
						<table id="fileUploadTable" style="margin-top:8px;">
							<c:forEach var="m" items="${fileList}">
								<tr>
									<td>${m.mpfile_name }
									</td>
									<td>
									<input type="button" value="删除" onclick="delFile(${m.mpfile_id },this);" />
									</td>
								</tr>
							</c:forEach>
						</table>
            </td>
					</tr>
				</table>
				<span id="msgdemo2" style="margin-left:30px;"></span>
				
			</form>
  </div>
  </div>
</body>
</html>