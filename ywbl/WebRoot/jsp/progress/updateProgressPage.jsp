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
	
	<!-- 时间控件 -->
	<script type="text/javascript" src="<%=path%>/js/lhgcalendar/lhgcalendar.min.js"></script>
  </head>

	<body>
		<div class="main">
<div class="main5">
		      <input type="button" onclick="save();" value="" style="background:url(<%=path%>/img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;"/>

		  
		    
		    <!--form-->
		    <form id="form" class="registerform" action="<%=path%>/progress!updateProgress.action">
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><input type="hidden" id="pp_id" name="progressPlan.pp_id"/>
		          	<input type="hidden" id="pc_id" name="progressPlan.pc_id"/><span class="need">*</span> 开始时间：</th>
    <td style="width:65%;"><input id="pp_start_date" name="progressPlan.pp_start_date" onclick="$.calendar();" readonly="readonly" class="inputxt" datatype="*" nullmsg="开始时间不可为空！"/>
		            <div class="Validform_checktip">开始时间不可为空！</div></td>
  </tr>
  <tr>
    <th style="width:35%;"><span class="need">*</span> 结束时间：</th>
    <td style="width:65%;"><input id="pp_end_date" name="progressPlan.pp_end_date"  onclick="$.calendar();" readonly="readonly" class="inputxt" datatype="*" nullmsg="结束时间不可为空！" />
		            <div class="Validform_checktip">结束时间不可为空！</div></td>
  </tr>
  <tr>
    <th><span class="need">*</span> 工程量：</th>
    <td><input id="pp_measure" name="progressPlan.pp_measure" type="text" class="inputxt" datatype="/^[1-9]\d{0,8}(?:\.\d{0,2})?$/" nullmsg="工程量不可为空！" errormsg="工程量不可为空或0,可有八位整数两位小数！" />
		            <div class="Validform_checktip">工程量不可为空或0,可有八位整数两位小数！</div></td>
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

		$('#pp_id').val(data.pp_id);
		$('#pc_id').val(data.pc_id);
		$('#pp_start_date').val(data.pp_start_date);
		$('#pp_end_date').val(data.pp_end_date);
		$('#pp_measure').val(data.pp_measure);
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
					$.closeDialog(data);
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
  		var startDate = document.getElementById("pp_start_date").value;
		var endDate = document.getElementById("pp_end_date").value;
	 
		if(startDate!='' && endDate!='' && (getDate(startDate)-getDate(endDate)>0)){
		    $.alert("结束时间不能小于开始时间");
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
	//关闭页面
	function closeDialog(){
		$.closeDialog();
	}
	
	
	//时间控件
	$.fn.runCode = function () {
	var getText = function(elems) {
		var ret = "", elem;

		for ( var i = 0; elems[i]; i++ ) {
			elem = elems[i];
			if ( elem.nodeType === 3 || elem.nodeType === 4 ) {
				ret += elem.nodeValue;
			} else if ( elem.nodeType !== 8 ) {
				ret += getText( elem.childNodes );
			};
		};

		return ret;
	};
		
		var code = getText(this);
		new Function(code).call(window);
		
		return this;
	};

	$(function(){
		// 按钮触发代码运行
		$(document).bind('click', function(event){
			var target = event.target,
				$target = $(target);

			if ($target.hasClass('runcode')) {
				$('#' + target.name).runCode();
			};
		});
	});
  </script>
</html>
