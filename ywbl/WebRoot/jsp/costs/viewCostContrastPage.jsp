<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
	<meta charset="utf-8">
    <title>建筑市场监管信息平台</title>
	<%@ include file="/jsp/common/common.jsp"%>
	<!--easyUI-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/tree/easyui-lang-zh_CN.js"></script>

  </head>

  <body style="background:#fff;">
  	<div class="main" style="background:#fff;">
  		<!--div class="breadcrumb">当前位置：成本对比详情列表</div-->
			<!--tree-->
		    <div class="tree">
		      <table id="tg" style="width:970px; height:385px;"></table><div class="clear" style="height:10px;"></div>
		    </div>
	  </div>
	</div>
  </body>
  
	<script type="text/javascript">
		var editingId;
		var idIndex = 0;
		$(function(){
		    $('#tg').treegrid({
		    	url:'cost!viewCostContrast.action',
		        title: '成本对比详情列表',
		        rownumbers: true,
		        idField: 'id',
		        treeField: 'name',
		        columns: [[ {
		            title: 'id',
		            field: 'id',
		            hidden: true
		        },{
		            title: '名称',
		            field: 'name',
		            width: 280
		        },{
		            title: 'is_child',
		            field: 'is_child',
		            hidden: true
		        },{
		            title: 'plan_id',
		            field: 'plan_id',
		            hidden: true
		        },{
		            title: '成本描述',
		            field: 'plan_name',
		            width: 140
		        },{
		            title: 'type_id',
		            field: 'type_id',
		            hidden: true
		        },{
		            title: '成本类型',
		            field: 'type_name',
		            width: 140
		        },{
		            title: '计划成本',
		            field: 'value',
		            width: 140
		        },{
		            title: '消耗成本',
		            field: 'actual_value',
		            width: 140
		        }]]

		    });
		    	
		});
		
		function getChildData(data){
			$('#tg').treegrid('reload');	// 重新载入所有行
		}
	</script>
</html>
