<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
<link rel="stylesheet" href="css/form/style.css" type="text/css"
	media="all" />
<!--tree-->
<link rel="stylesheet" type="text/css" href="<%=path %>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/tree/icon.css">
		<script type="text/javascript" src="<%=path %>/js/tree/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/tree/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=path %>/js/form/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript"
			src="<%=path %>/jsp/project/updateProChildListPage.js"></script>
</head>

<body>
	<div class="main">
		<div class="breadcrumb">当前位置：子工程管理</div>

		<!--toolbar-->
		<div class="toolbar">
			<div class="action">

				<input type="submit" onclick="gotoUpdate();" value="确定"
					style="background:url(img/t-submit.png) no-repeat center 6px;" />
			</div>
		</div>

		<!--main3-->
		<div class="main3">

			<!--tree-->
			<div class="tree">
				<table id="tg"></table>
				<div class="clear" style="height:10px;"></div>
			</div>
		</div>
		
		<!-- js用 存放剪切节点id和名称 -->
		<input type="hidden" id="cut_id"/>
		<input type="hidden" id="cut_name"/>
	</div>
<!-- 右键菜单 -->
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="add()" data-options="iconCls:'icon-add'">新增</div>
    <div onClick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
    <div onClick="del()" data-options="iconCls:'icon-remove'">删除</div>
</div>

</body>
</html>