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
 	function  doSub(username){
 		
 		var password = document.getElementById("passWord").value;
 		var newpassword = document.getElementById("newpassWord").value;
 		
 		if(password==newpassword){
 			$(".registerform").attr("action", "gasUser!updateGasPass.action?passWord="+password+"&username="+username);
 			$(".registerform").submit();
 		}else{
 			$.alert("二次密码输入不一致,请重新输入！");
 			return;
 		}
 		
 		
 	}
</script>
<body>
	<div class="main">
    	<div class="main5">
    	
    	  <!--toolbar-->
		

			<!--form-->
			<form id="form" class="registerform" action="">
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th>密码:</th>
        <td>
        
        
        <input type="hidden"  id="username"  name="username" class="inputxt"  value="${username}" />
        
        
        <input type="text"  id="passWord"  name="passWord" class="inputxt" datatype="*1-50" errormsg="密码不可为空,最多20个字符！" />
    <div class="Validform_checktip">密码不可为空,最多20个字符！</div>
  </tr>
  <tr>
    <th>确认密码:</th>
        <td><input type="text"  id="newpassWord"  name="newpassWord" class="inputxt" datatype="*1-20"" errormsg="新密码不可为空,最多20个字符！"/>
    <div class="Validform_checktip">新密码不可为空,最多20个字符！</div>
  </tr>
  
</table>			
			
			
			
			</form>
			 <input type="button" onclick="doSub('${username}');" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; float:right;" /> 

		</div>
	</div>

</body>
</html>