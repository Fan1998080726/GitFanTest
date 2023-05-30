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
  		<div class="breadcrumb">当前位置：计划成本</div>
			<!--tree-->
			<div class="main2">
		    <div class="tree">
		      <table id="tg"></table><div class="clear" style="height:10px;"></div>
		    </div>
		    </div>
	  </div>
	</div>
  </body>
  
	<script type="text/javascript">
		var editingId;
		var idIndex = 0;
		$(function(){
		    $('#tg').treegrid({
		    	url:'cost!queryCost.action',
		        title: '计划成本',
		        rownumbers: true,
		        idField: 'id',
		        treeField: 'name',
		        animate: true,
		        fitColumns: true,
		        columns: [[ {
		            title: 'id',
		            field: 'id',
		            hidden: true
		        },
		        {
		            title: '数量',
		            field: 'cp_num',
		            hidden: true
		        }
		        ,{
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
		            title: '成本值(元)',
		            field: 'value',
		            width: 140
		        }]],enableHeaderContextMenu: false
		   <c:if test="${'7'==projectFlag }">
		    	,toolbar: [{
		            id: 'idAdd',
		            text: '添加',
		            iconCls: 'icon-add',
		            <c:if test="${'1'!=menu.rm_add }">
		            disabled: true,
		            </c:if>
		            handler: function(){
		            	//添加方法 弹出页
		            	var row = $('#tg').treegrid('getSelected');
		            	if (row) {
							
			            	if (row.is_child=='0') {
			        			$.dialog({
			        				id:'id1',
			    					url:'jsp/costs/updateCostPage.jsp',
			    					title: '添加', 
			    					width: 500, 
			    					height: 400,
			    					data:{
			    						'pc_id':row.id
			    					}
			    				});
							}else{
								$.alert("只可以对最后一层制定计划成本！");
							}
						}else{
							$.alert("请选择工程节点!");
						}
		            	
		            }
		        }, '-',{
		            id: 'idEdit',
		            text: '编辑',
		            iconCls: 'icon-edit',
		            <c:if test="${'1'!=menu.rm_update }">
		            disabled: true,
		            </c:if>
		            handler: function(){
		            	//修改方法
		    			if (editingId != undefined){
		    				$('#tg').treegrid('select', editingId);
		    				return;
		    			}
		    			var row = $('#tg').treegrid('getSelected');
		    			//判断选中的行是否是底层树节点 实现可编辑
		    			if (row){
		    				if (row.is_child==2) {
		    					$.dialog({
		    						id:'id1',
			    					url:'jsp/costs/updateCostPage.jsp',
			    					title: '修改', 
			    					width: 500, 
			    					height: 400,
			    					data:{
			    						'cp_id':row.plan_id,
			    						'cp_name':row.plan_name,
			    						'cp_type':row.type_id,
			    						'cp_value':row.value,
			    						'cp_num':row.cp_num,
			    						'type_id':row.type_id
			    					}
			    				});
							} else {
								$.alert("请选择计划成本进行修改！");
							}
		    			}else{
							$.alert("请选择计划成本!");
						}
		            }
		        }, '-',{
		            id: 'idDelete',
		            text: '删除',
		            iconCls: 'icon-remove',
		            <c:if test="${'1'!=menu.rm_del }">
		            disabled: true,
		            </c:if>
		            handler: function(){
		            	
		            	//删除方法
		            	if (editingId != undefined){
		    				$('#tg').treegrid('select', editingId);
		    				return;
		    			}
		    			var row = $('#tg').treegrid('getSelected');
		    			//判断选中的行是否是底层树节点 实现可编辑
		    			if (row){
		    				if (row.is_child==2) {
				            	$.confirm("确认要删除该计划成本？",function(){
					            		// 确认按钮回调方法
				            		$.ajax({
										type:'post',//可选get
										url:'cost!delCost.action',//这里是接收数据的后台程序
										dataType:'Json',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
										data:{
											'costPlan.cp_id':row.plan_id
										},
										success:function(data){
											if('1'==data.flag){
												$.alert('删除成功！');
												$('#tg').treegrid('reload');
											}else if('2'==data.flag){
												$.alert('此计划成本下已经录入实际成本，不能删除！');
											}else if('3'==data.flag){
												$.alert('操作失败！');
											}
										},
										error:function(){
											$.alert("操作失败！");
										}
									});
				            	}, function(){
					            	    // 取消按钮回调方法
					            	});
							} else {
								$.alert("只能删除计划成本！");
							}
		    			}else{
		    				$.alert("请选择计划成本!");
		    			}
		            }
		        }, '-']
		  </c:if>
		    });
		    	
		});
		
		function getChildData(data){
			$('#tg').treegrid('reload');	// 重新载入所有行
		}
	</script>
</html>
