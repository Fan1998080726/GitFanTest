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
	src="<%=path%>/jsp/store/storetype/materialapply.js"></script>
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
					var objtip = o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				}
			},
			callback : function(data) {
				if (data.status == "y") {
					$.alert('保存成功！');
					$.closeDialog();
				} else {

					$.alert('保存失败！');
				}
			}
		});
		demo.addRule([ {
			ele : "select",
			datatype : "*"
		} ]);
	})
	function closeView() {
		$.closeDialog();
	}
</script>
<body>
	<div class="main">


		<div class="main5">
			<!--toolbar-->
			<%--<input type="button" onclick="closeView();"
					style="background:url(img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
			--%>
			<!--form-->
			<form id="form" class="registerform" action="device!updateDeviceApply.action">
				<input type="hidden" name="deviceApply.mda_flag" value="0">
				<input type="hidden" name="deviceApply.mda_id" value="${deviceApply.mda_id}">
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th style="width:25%;"> 设备名:</th>
						<td style="width:25%;">
							<input type="text" readonly="readonly" id="mda_name" name="deviceApply.mda_name" value="${deviceApply.mda_name}" class="inputxt"
									 />
						</td>
						
						<th style="width:25%;"> 数量:</th>
						<td style="width:25%;">
							<input type="text" id="mda_num" readonly="readonly"
									name="deviceApply.mda_num" value="${deviceApply.mda_num}" class="inputxt"
									/>
						</td>
					</tr>
					<tr>
						<th> 单位:</th>
						<td>
							<input type="text" id="mda_unit" readonly="readonly"
									name="deviceApply.mda_unit" value="${deviceApply.mda_unit}" class="inputxt"
									/>
						</td>
						
						<th> 申领日期:</th>
						<td><input type="text" readonly="readonly" value="${deviceApply.mda_apply_date}" 
							id="mda_date" name="deviceApply.mda_apply_date" class="inputxt"  />
						</td>
					</tr>
					<tr>
						<th> 价格(元/天):</th>
						<td>
							<input type="text" id="mda_price" readonly="readonly"
									name="deviceApply.mda_price" value="${deviceApply.mda_price}" class="inputxt"
									/>
						</td>
						<th> 描述:</th>
						<td>
							<input type="text" id="mda_describe" name="deviceApply.mda_describe" value="${deviceApply.mda_describe}" class="inputxt"
									readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th> 租赁日期:</th>
						<td>
							<input type="text" id="mda_price" 
									name="deviceApply.mda_out_date" value="${deviceApply.mda_out_date}" class="inputxt"
									readonly="readonly" />
						</td>
						<th> 返库日期:</th>
						<td>
							<input type="text" id="mda_describe" name="deviceApply.mda_back_date" value="${deviceApply.mda_back_date}" class="inputxt"
									readonly="readonly" />
						</td>
					</tr>
				</table>
			</form>
  </div>
  </div>
</body>
</html>