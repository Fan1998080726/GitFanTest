<!-- wxlogin.jsp 20200106-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
System.out.println("path=="+path);
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

<body>

<div class="all"><div class="img"><img src="<%=path %>/images/bj.png"></div></div>
<div class="all"><div class="h1">一地“疫”（一）码系统开通流程</div></div>
<div class="all"><div class="h2">首先：关注“哈尔滨建筑业”微信公众号，点击项目服务——现场管理——弹出“流程说明”——阅读注册等。</div></div>

<div class="all"><div class="h2">一地“疫”（一）码系统开通流程</div></div>

<div class="all"><div class="h2">第一步：“找码”项目单位找码——根据微信公众号提示的网址找找到工程项目，打印本工地的二维码。</div></div>


<div class="all"><div class="h2">第二步：“开码”工地系统管理员开通工地二维码——工地系统管理员（项目单位指定专人）扫码，进行首次注册，注册成功后即本工地二维码开通。</div></div>

<div class="all"><div class="h2">注意事项：工地开通二维码仅限一人一次，由项目单位指定专人负责。</div></div>



<div class="all"><div class="h2">第三步：“扫码”将二维码贴在工地进出口。所有人首次进入工地进行扫码注册。</div></div>

<div class="all"><div class="h2">开通注册成功过后，要求每人进出工地进行扫码，测温（填报温度）进场。若疫情结束，扫码直接进出工地。</div></div>

<div class="all"><div class="h2">※※如新开工地没有二维码，可请向市、县（市）住建审批（管理）部门申请。审批（管理）部门通过“监管平台”将工地信息录入，即生成新开工地二维码。</div></div>


<div class="all"><div class="h2">公众号“企业报表”完善内容：</div></div>

<div class="all"><div class="h2">需要企管站等部门提供：企业信息、人员信息等（表格样式）</div></div>
</body>

</html>
