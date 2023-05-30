<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="jsp/system/user/usermanager.js"></script>
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
					
					$.alert('保存失败,用户名已存在！');
				}
			}
		});
	demo.addRule([{
		ele:"select",
		datatype:"*"
	}]);
})

</script>
<body>
	<div class="main">
    	<div class="main5">
    	
    	  <!--toolbar-->
		

			<!--form-->
			<form id="form" class="registerform" action="usermanager!edit.action">
			<input type="hidden" name="userInfo.id" id="id" value="${ userInfo.id }"/>
			

<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:20%;"> 登录名:(统一社会信用代码)</th>
    <td style="width:30%;"><input type="text" value="${userInfo.login_id }" id="login_id" name="userInfo.login_id"  readonly="readonly" class="inputxt"/>
							</td>
    <th style="width:20%;"><span class="need">*</span> 密码:</th>
    <td style="width:30%;"><input type="password" value="${userInfo.login_password }" id="login_password" name="userInfo.login_password" class="inputxt" datatype="*1-50"" errormsg="密码不可为空,最多20个字符！" />
							<div class="Validform_checktip">密码不可为空,最多20个字符！</div></td>
  </tr>
  <tr>
    <%-- <th><span class="need">*</span> ${displayName }:</th>
    <td><input type="text" value="${userInfo.user_name }" id="user_name" name="userInfo.user_name" class="inputxt" datatype="*1-50" errormsg="用户姓名不可为空,最多50个字符！" />
							<div class="Validform_checktip">${displayName }不可为空,最多50个字符！</div></td> --%>
							<th> ${displayName}:</th>
    <td><input type="text" value="${userInfo.user_name }" id="user_name" name="userInfo.user_name" class="inputxt"  />
							</td>
    <th>联系电话:</th>
    <td><input type="text" value="${userInfo.phone }" id="phone" name="userInfo.phone" class="inputxt" /></td>
  </tr>
  <tr>
    <th>${displayAddress}:</th>
     <td><input type="text" value="${userInfo.address }" id="address"  name="userInfo.address" class="inputxt" />
    </td>
   <th>${displayEmail}:</th>
    <td><input type="text" value="${userInfo.email }" id="email"  name="userInfo.email" class="inputxt" />
  </tr>
  <c:if test="${dept_name!='审批办'&&dept_name!='设计处'&&dept_name!='燃气办'}">
  <tr>
    <th><span class="need">*</span>所属部门:</th>
    <td>
    	<select name="userInfo.dept_id" onchange="changeDept(this.value);" class="inputxt" nullmsg="所属部门不可为空！">
    		<option value="">请选择</option>
    		<c:forEach var="m" items="${deptList }">
				<option value="${m.dept_id }" <c:if test="${m.dept_id==userInfo.dept_id }">selected</c:if>>${m.dept_name }</option>
			</c:forEach>
    	</select>
    	<div class="Validform_checktip">所属部门不可为空！</div>
	</td>
    <th><span class="need">*</span>用户角色:</th>
    <td>
		<select name="userInfo.role_id" id="role_id" class="inputxt" nullmsg="所属角色不可为空！">
			<option value="">请选择</option>
			<c:forEach var="m" items="${roleList }">
				<option value="${m.role_id }" <c:if test="${m.role_id==userInfo.role_id }">selected</c:if>>${m.role_name }</option>
			</c:forEach>
		</select>
		<div class="Validform_checktip">所属角色不可为空！</div>
	</td>
  </tr>
  </c:if>
        <c:if test="${dept_name=='审批办' }">
    <input type="hidden" value="26" id="dept_id"  name="userInfo.dept_id" />
    <input type="hidden" value="105" id="role_id"  name="userInfo.role_id" />
  </c:if>
   <c:if test="${dept_name=='设计处' }">
    <input type="hidden" value="44" id="dept_id"  name="userInfo.dept_id" />
    <input type="hidden" value="175" id="role_id"  name="userInfo.role_id" />
  </c:if>
     <c:if test="${dept_name=='燃气办' }">
    <input type="hidden" value="84" id="dept_id"  name="userInfo.dept_id" />
    <input type="hidden" value="672" id="role_id"  name="userInfo.role_id" />
  </c:if>
 <tr>
    <th>备注:</th>
        <td><input type="text" value="${userInfo.memo }" id="memo"  name="userInfo.memo" class="inputxt" />
   <th><!-- 建设单位组织机构代码证号: --></th>
    <td>
<%--    <c:if test="${user_name=='admin' }">
      <input type="text" value="${userInfo.buildCorpCode }" id="buildCorpCode" name="userInfo.buildCorpCode" class="inputxt"  />
    </c:if>
     <c:if test="${user_name!='admin' }">
      <input type="text" value="${userInfo.buildCorpCode }" id="buildCorpCode" name="userInfo.buildCorpCode" class="inputxt" datatype="*1-20" errormsg="建设单位组织机构代码证号不可为空,最多20个字符！" />
      <div class="Validform_checktip">建设单位组织机构代码证号不可为空,最多20个字符！</div>
    </c:if> --%>
   <input type="hidden" value="${userInfo.buildCorpCode }" id="buildCorpCode" name="userInfo.buildCorpCode" class="inputxt"  />
    </td>
  </tr>
</table>			
			
			
			
			</form>
			 <input type="button" onclick="doSub2();" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; float:right;" /> 

		</div>
	</div>

</body>
</html>