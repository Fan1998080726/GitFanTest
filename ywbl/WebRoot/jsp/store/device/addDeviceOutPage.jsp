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
	src="<%=path%>/jsp/store/device/deviceapply.js"></script>
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
	
		//保存进度
  	function doSave(){
		
  		var mda_date = document.getElementById("mda_date").value;
		var mda_out_date = document.getElementById("mda_out_date").value;
		if((getDate(mda_date)-getDate(mda_out_date)>0)){
		    $.alert("租赁时间不能小于申领时间,申领时间为: " + mda_date);
		    return false;
		}
  		$(".registerform").submit();
  	}
	
	
  	function getDate(date){
		 var dates = date.split("-");
		 var dateReturn = '';
		 
		 for(var i=0; i<dates.length; i++){
		  dateReturn+=dates[i];
		 }
		 return dateReturn;
	}
</script>
<body>
	<div class="main">


		<div class="main5">
			<!--toolbar-->
			<input type="button" onclick="doSave();"
					style="background:url(img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
			<!--form-->
			<form id="form" class="registerform" action="device!addDeviceOut.action">
				<input type="hidden" name="deviceApply.mda_flag" value="1">
				<input type="hidden" name="deviceApply.mda_id" value="${deviceApply.mda_id}">
				<input type="hidden" name="deviceApply.mda_apply_user" value="${deviceApply.mda_apply_user}">
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th style="width:25%;"> 设备名:</th>
						<td style="width:25%;">
							<input type="text" id="mda_name" readonly="readonly" name="deviceApply.mda_name" value="${deviceApply.mda_name}" class="inputxt"
									datatype="*1-20" errormsg="设备名不可为空！" />
						</td>
						
						<th style="width:25%;"> 数量:</th>
						<td style="width:25%;">
							<input type="text" id="mda_num" 
									readonly="readonly" name="deviceApply.mda_num" value="${deviceApply.mda_num}" class="inputxt"
									datatype="n1-20" errormsg="数量不可为空,，只能输入数字！" />
						</td>
					</tr>
					<tr>
						<th> 单位:</th>
						<td>
							<input type="text" id="mda_unit"
									readonly="readonly" name="deviceApply.mda_unit" value="${deviceApply.mda_unit}" class="inputxt"
									datatype="*1-20" errormsg="单位不可为空！" />
						</td>
						
						<th> 申领日期:</th>
						<td><input type="text" readonly="readonly" name="deviceApply.mda_apply_date" value="${deviceApply.mda_apply_date}"
							id="mda_date"  class="inputxt"  errormsg="日期不可为空！" />
						</td>
					</tr>
					<tr>
						<th> 单价(元/天):</th>
						<td>
							<input type="text" id="mda_price" readonly="readonly"
								 name="deviceApply.mda_price" value="${deviceApply.mda_price}" class="inputxt"
									datatype="*1-20" errormsg="价格不可为空！" />
						</td>
						<th> 描述:</th>
						<td>
							<input type="text" id="mda_describe" name="deviceApply.mda_describe" readonly="readonly" value="${deviceApply.mda_describe}" class="inputxt"
									datatype="*1-100" ignore="ignore" errormsg="描述不可超过100个字符！" />
						</td>
					</tr>
					<tr>
						
						<th><span class="need">*</span>租赁日期:</th>
						<td colspan="3"><input type="text" readonly="readonly" name="deviceApply.mda_out_date" onclick="$.calendar();"
							id="mda_out_date"  class="inputxt"  nullmsg="租赁日期不可为空！" datatype = "*"/>
							<div class="Validform_checktip">租赁日期为设备租回起算，不可为空！</div>
						</td>
					</tr>
				</table>
			</form>
  </div>
  </div>
</body>
</html>