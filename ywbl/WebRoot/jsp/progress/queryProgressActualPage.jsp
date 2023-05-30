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
  		<div class="breadcrumb">当前位置：进度录入</div>
			<!--tree-->
			<div class="main3">
		    <div class="tree">
		      <table id="tg"></table><div class="clear" style="height:10px;"></div>
		    </div>
		    </div>
	  </div>
	</div>
  </body>
  <!-- 右键菜单 -->
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="add()" data-options="iconCls:'icon-add'">新增</div>
    <div onClick="end()" data-options="iconCls:'icon-ok'">竣工</div>
</div>
	<script type="text/javascript">
		var editingId;
		$(function(){
		    $('#tg').treegrid({
		    	url:'progress!queryProgressActual.action',
		        title: '进度录入',
		        rownumbers: true,
		        animate: true,
		        idField: 'id',
		        treeField: 'name',
		        fitColumns: true,
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
		            width: 100
		        },{
		            title: '已完成工程量',
		            field: 'actual_value',
		            width: 100
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
		            <c:if test="${'1'!=menu.rm_add }">
		            disabled: true,
		            </c:if>
		            handler: function(){
		            	add();
		            }
		        }, '-',{
		            id: 'idEnd',
		            text: '竣工',
		            iconCls: 'icon-ok',
		            <c:if test="${'1'!=menu.rm_add }">
		            disabled: true,
		            </c:if>
		            handler: function(){
		            	end();
		            }
		        }],
		        </c:if>
		        enableHeaderContextMenu: false
		    });
		});
		
		//添加右击菜单内容
		function onRowContextMenu(e, rowIndex, rowData){
		    e.preventDefault();
		    $(this).treegrid('select', rowIndex.id);
		    $('#mm').menu('show', {
		        left:e.pageX,
		        top:e.pageY
		    });       
		}
		
		//弹出添加进度页面
		function addProgressActual(plan_id){
			
			$.dialog({
					url:'jsp/progress/addProgressActualPage.jsp',
					title: '添加', 
					width: 500, 
					height: 400,
					data:{
						'plan_id':plan_id
					}
				});
		}
		
		//结束进度
		function endProgressActual(plan_id){
			$.confirm("是否要结束进度？",
				function(){
				endProgress(plan_id);// 确认按钮回调方法
			}, function(){
				$.alert("您取消了操作！");
			});
		}
		
		function endProgress(plan_id){
			$.ajax({
				url:'progress!endProgress.action?plan_id='+plan_id,
				dataType:'json',
				success:function(data){
					location.reload();
				},
				error:function(){
					$.alert('操作失败！');
				}
			});
		}
		
		function getChildData(data){
			$('#tg').treegrid('reload');	// 重新载入所有行
		}
		
		function add(){
			//添加方法
        	var row = $('#tg').treegrid('getSelected');
        	if (row) {
        		if('0'==row.is_end){
        			$.alert("该项已被完结，无法继续添加！");
        			return;
        		}
            	if (row.plan_id!='') {
            			addProgressActual(row.plan_id);
				}else{
					$.alert("请选择计划进度!");
				}
			}else{
				$.alert("请选择计划进度!");
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
            			endProgressActual(row.plan_id);
				}else{
					$.alert("请选择计划进度!");
				}
			}else{
				$.alert("请选择计划进度!");
			}
		}
	</script>
</html>
