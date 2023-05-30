<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%//////System.out.println("ssssssssssss"); %>
<html>
<head>
	<meta charset="utf-8">
		<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
		<link href="css/layout.css" type="text/css" rel="stylesheet">
		<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<!--表单验证-->
		<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=path %>/jsp/system/role/role.js"></script>
	</head>
	<script type="text/javascript">
		$(function(){
			//$(".registerform").Validform();  //就这一行代码！;
				
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
							$.closeDialog();
						}else{
							
							$.alert('保存失败,角色名称已存在！');
						}
					}
				});
		})

	</script>
<body>
	<div class="main">
	  
  <div class="main5">
  
  
	  <form name="form1" class="registerform" action="rolemanager!edit.action">
	  <input type="hidden" name="role.role_id" value="${role.role_id }"/>
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 角色名称:</th>
    <td style="width:65%;"><input type="text" value="${role.role_name }" id="role_name" name="role.role_name" class="inputxt" datatype="*1-20" errormsg="角色名称不可为空,最多20个字符！" />
								<div class="Validform_checktip">角色名称不可为空,最多20个字符！</div></td>
  </tr>
  <tr>
    <th style="width:35%;"><span class="need">*</span> 所属部门:</th>
    <td style="width:65%;">
    <select name="role.dept_id" class="inputxt" nullmsg="所属部门不可为空！">
    	<option value="">请选择</option>
    	<c:forEach var="m" items="${deptList}">
    		<option value="${m.dept_id }" <c:if test="${m.dept_id==role.dept_id }">selected</c:if>>${m.dept_name }</option>
    	</c:forEach>
    </select>
	<div class="Validform_checktip">所属部门不可为空！</div></td>
  </tr>
  <tr>
    <th>备注:</th>
    <td><input type="text" value="${role.memo }" id="memo" name="role.memo" class="inputxt" /></td>
  </tr>
</table>

			</form>
			<input type="button" onclick="doEdit()" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; float:right;"/>
		</div>
  	</div>
	</body>
</html>