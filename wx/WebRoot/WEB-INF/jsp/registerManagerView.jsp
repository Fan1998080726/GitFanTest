<!-- registerManager.jsp 20200106-->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%System.out.println("into registerManagerView.jsp");%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>一地“疫”（一）码项目管理系统</title>
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/css.css"/>

</head>
 <!-- request.setAttribute("prjName", prjName);
			             request.setAttribute("Corp", Corp);
			             request.setAttribute("name", name);
			             request.setAttribute("cardnum", cardnum);
			             request.setAttribute("phone", phone);
			             request.setAttribute("username", userInfo.getLogin_id());
			             System.out.println("userInfo.getLogin_id()==="+userInfo.getLogin_id()); -->
<body>

<form name="form1" method="post" action="">
<div class="all"><div class="h2">${sysManagerVo.prjName} <br>系统管理人员注册成功!</div></div>
<div class="all2">
	<ul>
    	<li>单位名称：${sysManagerVo.corp}</li>
    	<li>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${sysManagerVo.name}</li>
        <li>身份证号：${sysManagerVo.cardnum}</li>
        <li>手机号码：${sysManagerVo.phone}</li>
         <li>请登录哈尔滨市住建局监管信息平台（http://221.207.229.44:7777/）管理当前工程人员信息，用户名：${sysManagerVo.username}，初始密码：123456(请及时修改)。</li>
    </ul>
</div>
</form>
</body>
</html>
