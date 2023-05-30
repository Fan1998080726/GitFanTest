<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>建筑市场监管信息平台</title>
<%@ include file="/jsp/common/common.jsp"%>
<link href="css/layout.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
<!--tree-->
<link rel="stylesheet" type="text/css" href="css/tree/easyui.css">
<link rel="stylesheet" type="text/css" href="css/tree/icon.css">
<script type="text/javascript" src="js/tree/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/tree/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/tree/jquery.jdirk.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.extensions.all.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.icons.all.min.js"></script>

</head>
<script type="text/javascript">
<!--
function gotoUpdate(){
	window.location.href="project!updateProChildListPage.action";
}
//-->
</script>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：子工程管理</div>
  <div class="main3">
  
  <!--toolbar-->
  <div class="toolbar">
    

      	<c:if test="${'7'==projectFlag }">
	      	<div class="action" style="float:left;">
	      	<input name="btn_update" type="button" onclick="gotoUpdate();" value="" style="background:url(img/t-edit.png) no-repeat;display:none"/>
	      	</div>
		</c:if>
    <div class="clear"></div>
  </div>
  
 

    <!--tree-->
    <div class="tree">
      <table id="tg"></table><div class="clear" style="height:10px;"></div>
    </div>
  </div>
</div>

<script type="text/javascript">


$(function(){
    $('#tg').treegrid({
        url: 'project!getProjectChildListJson.action',
        title: '子工程列表',
        rownumbers: true,
        animate: true,
        idField: 'pc_id',
        treeField: 'pc_name',
        fitColumns: true,
        columns: [[{
            title: '名称',
            field: 'pc_name',
            width: 150
        },{
            title: '描述',
            field: 'pc_describe',
            width: 280
        }]],
        enableHeaderContextMenu: false
    });
});
</script>

</body>
<script src="js/bootstrap.min.js"></script>
</html>