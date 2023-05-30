<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
	<meta charset="utf-8">
    <title>建筑市场监管信息平台</title>
	<%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
	<link type="text/css" media="all" rel="stylesheet" href="<%=path%>/css/form/style.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/form/demo.css"/>
	<script type="text/javascript" src="<%=path%>/js/form/Validform_v5.3.2_min.js"></script>
  </head>

	<body>
		<div class="main">
<div class="main5">

		      <input type="button" onclick="save();" value="" style="background:url(<%=path%>/img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;"/>

		  
		    
		    <!--form-->
		    <form id="form" class="registerform" action="<%=path%>/progress!addProgressActual.action">
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><input type="hidden" id="pp_id" name="progressActual.pp_id"/><span class="need">*</span>工程量：</th>
    <td style="width:65%;"><input id="pa_remark" name="progressActual.pa_measure" type="text" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/" nullmsg="工程量不可为空！" errormsg="工程量不可为空,可有八位整数两位小数！"/>
		            <div class="Validform_checktip">工程量不可为空,可有八位整数两位小数！</div></td>
  </tr>
  <tr>
    <th>备注:</th>
    <td><textarea id="pa_measure" name="progressActual.pa_remark" datatype="*0-100" class="inputxt" errormsg="备注最多100个字符！" ></textarea>
		            <div class="Validform_checktip">备注最多100个字符！</div></td>
  </tr>
</table>

		    </form>
		
		  </div>
		</div>
	</body>
  
  <script type="text/javascript">
	$(function(){
		//父页面接值 计划进度id 隐藏域赋值
		var data = $.getParentData();
		$('#pp_id').val(data.plan_id);
		
		$(".registerform").Validform({
			ajaxPost:true,	 //异步
		
			tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			},
			callback:function(data){
				if (data.status=="y") {
					$.closeDialog(data);
				}
			}
		});
	});
  	
	//保存进度
  	function save(){
  		$(".registerform").submit();
  	}
	//关闭页面
	function closeDialog(){
		$.closeDialog();
	}
  </script>
</html>
