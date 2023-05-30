<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>建筑市场监管信息平台</title>
<%@ include file="/jsp/common/common.jsp"%>
<link href="css/layout.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
<!--表单验证-->
<link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
<!--tree-->
<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
</head>

<body>
<div class="main">
<div class="main5">

    <input type="button" onclick="update();" value="" style="background:url(<%=path%>/img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;"/>
   
    <!--form-->
    <form id="form" class="registerform" action="project!updateProjectChild.action">
    	<input type="hidden" id="pc_pid" name="pc_pid" value="${pc_pid }"/>
    	<input type="hidden" name="pc_id" value="${proChild.pc_id }"/>

        
       <!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 子工程名：</th>
    <td style="width:65%;"><input type="text" value="${proChild.pc_name }" name="proChild.pc_name" class="inputxt" datatype="*1-50" errormsg="子工程名称不可为空,最多50个字符！" />
            <div class="Validform_checktip">子工程名称不可为空,最多50个字符！</div></td>
  </tr>
  <tr>
    <th><span class="need">*</span> 工程描述：</th>
    <td><textarea errormsg="工程描述最多100个字符！"  datatype="*1-100" class="inputxt" name="proChild.pc_describe">${proChild.pc_describe }</textarea>
            <div class="Validform_checktip">工程描述最多100个字符！</div></td>
  </tr>
</table>


    </form>

  </div>
</div>

<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>


</body>
<script src="js/bootstrap.min.js"></script>
<script src="jsp/project/updateProjectChildPage.js"></script>

</html>