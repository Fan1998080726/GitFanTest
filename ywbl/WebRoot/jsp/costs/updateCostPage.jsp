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
		    <form id="form" class="registerform" action="<%=path%>/cost!updateCost.action">

<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 描述：</th>
    <td style="width:65%;"><input type="hidden" id="cp_id" name="costPlan.cp_id"/>
		          	<input type="hidden" id="pc_id" name="costPlan.pc_id"/>
		            <input id="cp_name" name="costPlan.cp_name" class="inputxt" type="text" datatype="*1-50" errormsg="描述不可为空,最多50个字符!" />
		            <div class="Validform_checktip">描述不可为空,最多50个字符！</div></td>
  </tr>
  <tr>
    <th><span class="need">*</span> 类型：</th>
    <td><select id="cp_type" name="costPlan.cp_type">
		            	<option value="1">人工</option>	
		            	<option value="2">材料</option>	
		            	<option value="3">机械</option>	
		            	<option value="35">其他</option>	
		            </select>
		            <div class="Validform_checktip">请选择类型！</div></td>
  </tr>
  <tr>
    <th><span class="need">*</span> 成本值：</th>
    <td><input id="cp_value" name="costPlan.cp_value" type="text" class="inputxt" datatype="/^\d{1,8}(?:\.\d{0,2})?$/"  nullmsg="成本值不可为空,可有八位整数两位小数！" errormsg="成本值不可为空,可有两位小数八位整数！" />
		            <div class="Validform_checktip">成本值不可为空,可有两位小数八位整数！</div></td>

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

		$('#cp_id').val(data.cp_id);
		$('#pc_id').val(data.pc_id);
		$('#cp_name').val(data.cp_name);
		$('#cp_type').val(data.cp_type);
		$('#cp_value').val(data.cp_value);
		var demo = $(".registerform").Validform({
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
					var json = {flag:1};
					$.closeDialog(json,"id1");
				}
			}
		});
		
		//通过demo.addRule 添加校验规则 根据空间类型
		demo.addRule([{
			ele:"select",
			datatype:"*"
		}]);
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
