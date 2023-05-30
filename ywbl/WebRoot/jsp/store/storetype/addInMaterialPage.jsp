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
	src="<%=path%>/jsp/store/storetype/storetype.js"></script>
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
</script>
<style type="text/css">
input[type="text"]{padding:2px 0;}
</style>
<body>
	<div class="main">


		<div class="main5">
			<!--toolbar-->
			<input type="button" onclick="doSub();"
				style="background:url(img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />

			<!--form-->
			<form id="form" class="registerform" action="storetype!addInMaterial.action">
				<input type="hidden" name="materialLog.mt_id" value="${mt_id}" />
				<input type="hidden" name="materialLog.ml_user" value="${user_id}" />
				<input type="hidden" name="materialLog.pro_id" value="0" />
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th style="width:35%;"><span class="need">*</span> 数量:</th>
						<td style="width:65%;">
							<input type="text" id="ml_num"
									name="materialLog.ml_num" class="inputxt"
									datatype="n1-20" errormsg="数量只能为整数！" nullmsg="数量不可为空！" />
							<div class="Validform_checktip">数量不可为空，只能输入数字！</div>
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span> 日期:</th>
						<td><input type="text" readonly="readonly" onclick="$.calendar({ maxDate:'%y-%M-%d' });" datatype="*"
							id="ml_date" name="materialLog.ml_date" class="inputxt" nullmsg="请选择日期！" />
							<div class="Validform_checktip">日期不可为空！</div>
						</td>
					</tr>

				</table>


			</form>
		</div>
	</div>
</body>
</html>