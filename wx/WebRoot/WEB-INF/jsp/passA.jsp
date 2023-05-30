<!-- wxlogin.jsp 20200106-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<title>一地“疫”（一）码项目管理系统</title>
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/css1.css"/>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<!-- <style>body{background:#a91515}</style> -->

</head>
<body>
<div class="main">

</br></br>
<div align="center"> <span    style="font-size:20px"> 通知:&nbsp;&nbsp;监管平台网址变更为:http://221.207.229.44:7777</span> </div>
<div class="all"><div class="img"><img src="images/bj.png"></div></div>
<div class="all"><div class="h1">${prjName}</div></div>
	<c:if test="${info=='禁止通过'}">
<style>body{background:#a91515}</style>
<div class="all"><div class="h1">${info}</div></div>
	</c:if>
		<c:if test="${info!='禁止通过'}">
<div class="all"><div class="h1">${info}</div></div>
	</c:if>
<div class="zg">
	<ul>
    	<li><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span>${name}</li>
        <li><span>手机号码:</span>${phone}</li>
        <li><span>身份证号:</span>${cardnum}</li>
        <li><span>单位名称:</span>${corpname}</li>
        <li><span>人员类型:</span>${personType}</li>
        <li><span>职业类别:</span>${personOne}</li>
        <li><span>当前温度:</span>${tiwen}</li>
<%--         <li><span>温度状态：</span>${prjName}</li> --%>
		<li><span>咨询微信:</span>t13351802533</li>
    </ul>
</div>
</div>
</body>
</html>
