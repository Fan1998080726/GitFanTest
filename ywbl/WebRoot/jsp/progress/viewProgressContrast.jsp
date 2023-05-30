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

  <body class="main" style="background:#fff;">
  	<div>
  		<!-- div class="breadcrumb">当前位置：进度对比详情查询</div-->
			<!--tree-->
		    <div class="tree">
		      <table id="tg" style="width:970px; height:385px;" ></table><div class="clear" style="height:10px;"></div>
		    </div>
	  </div>
	</div>
  </body>
  
	<script type="text/javascript">
	var editingId;
	$(function(){
	    $('#tg').treegrid({
	    	url:'progress!viewProgressContrast.action?projectId=1',
	        title: '子工程信息',
	        idField: 'id',
	        treeField: 'name',
	        columns: [[ {
	            title: 'id',
	            field: 'id',
	            hidden: true
	        },{
	            title: 'plan_id',
	            field: 'plan_id',
	            hidden: true
	        },{
	            title: '名称',
	            align: 'left',
	            field: 'name',
	            width: 280
	        },{
	            title: '开始时间',
	            field: 'begin',
	            width: 140
	        },{
	            title: '结束时间',
	            field: 'end',
	            width: 140
	        },{
	            title: '计划工程量',
	            field: 'value',
	            width: 145
	        },{
	            title: '已完成工程量',
	            field: 'actual_value',
	            width: 150
	        }]]
	    });
	});

	function addProgressActual(plan_id){
		//alert(plan_id);
	}
	</script>
</html>
