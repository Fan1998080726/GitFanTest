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

  <body>
  	<div class="main">
  		<div class="breadcrumb">当前位置：成本录入</div>
			<!--tree-->
			<div class="main2">
		    <div class="tree">
		      <table id="tg"></table><div class="clear" style="height:10px;"></div>
		    </div>
		    </div>
	  </div>
	</div>
  <!-- 右键菜单 -->
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="add()" data-options="iconCls:'icon-add'">新增</div>
    <div onClick="end()" data-options="iconCls:'icon-ok'">竣工</div>
</div>
  </body>
  
	<script type="text/javascript">
		var editingId;
		var idIndex = 0;
		$(function(){
		    $('#tg').treegrid({
		    	url:'cost!queryCostActual.action',
		        title: '成本录入',
		        rownumbers: true,
		        idField: 'id',
		        treeField: 'name',
		        animate: true,
		        fitColumns: true,
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
		            title: '计划成本(元)',
		            field: 'value',
		            width: 140
		        },{
		            title: '消耗成本(元)',
		            field: 'actual_value',
		            width: 140
		        },{
		            title: '状态',
		            field: 'is_end',
		            width: 100,
		            formatter:function(value, row, index){
		            	if('0'==row.is_end){//完结
		            		return "完结";
		            	}
						if('1'==row.is_end){//在建
		            		return "在建";
		            	}
		            	
					}
		        }]],
		    <c:if test="${'5'==flag }">
		    onContextMenu: onRowContextMenu, //右键菜单
		    	toolbar: [{
		            id: 'idAdd',
		            text: '添加',
		            iconCls: 'icon-add',
		            //disabled: true,
		            handler: function(){
		            	add();
		            }
		        },'-',{
		            id: 'idEnd',
		            text: '竣工',
		            iconCls: 'icon-ok',
		            handler: function(){
		            	end();
		            }
		        }],
		    </c:if>
		  	//enableHeaderClickMenu: false,
            enableHeaderContextMenu: false,
            //enableRowContextMenu: false,         //此属性开启表头列名称右键点击菜单
            //pagingMenu: { submenu: false}      //开启行右键菜单的翻页功能，此属性可丰富配置，详情见 API 文档
		    });
		    	
		});
		
		function getChildData(data){
			$('#tg').treegrid('reload');	// 重新载入所有行
		}
		
		//结束成本
		function endCostActual(plan_id){
			$.confirm("是否要结束成本？",
				function(){
					endCost(plan_id);// 确认按钮回调方法
				}, function(){
					$.alert("您取消了操作！");
				});
		}
		
		function endCost(plan_id){
			$.ajax({
				url:'cost!endCost.action?plan_id='+plan_id,
				dataType:'json',
				success:function(data){
					location.reload();
				},
				error:function(){
					$.alert('操作失败！');
				}
			});
		}
		
		function add(){
			//添加方法 弹出页
        	var row = $('#tg').treegrid('getSelected');
        	if (row) {
				if('0'==row.is_end){
        			$.alert("该项已被完结，无法继续添加！");
        			return;
        		}
            	if (row.is_child=='2') {
        			$.dialog({
        				id:'id1',
    					url:'jsp/costs/updateCostActualPage.jsp',
    					title: '添加', 
    					width: 500, 
    					height: 400,
    					data:{
    						'cp_id':row.plan_id,
    						'type_id':row.type_id,
    						'plan_name':row.plan_name,
    						'type_name':row.type_name
    					}
    				});
				}else{
					$.alert("请选择成本计划!");
				}
			}else{
				$.alert("请选择成本计划!");
			}
		}
		
		function end(){
			//竣工方法
        	var row = $('#tg').treegrid('getSelected');
        	if (row) {
        	
        		if('0'==row.is_end){
        			$.alert("该项已被完结！");
        			return;
        		}
            	if (row.plan_id!='') {
            			endCostActual(row.plan_id);
				}else{
					$.alert("请选择成本计划!");
				}
			}else{
				$.alert("请选择成本计划!");
			}
		}
		
		//添加右击菜单内容
		function onRowContextMenu(e, rowIndex, rowData){
		    e.preventDefault();
		    $(this).treegrid('select', rowIndex.id);
		    $('#mm').menu('show', {
		        left:e.pageX,
		        top:e.pageY
		    });       
		}
	</script>
</html>
