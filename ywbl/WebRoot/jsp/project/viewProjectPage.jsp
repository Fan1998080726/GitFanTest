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
<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
	
	<!--easyUI-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.jdirk.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.extensions.all.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.icons.all.min.js"></script>
	
</head>

<script type="text/javascript">
<!--

<c:if test="${1==result}">$.alert("有文件因文件名过长或大小超过1M，上传失败。");</c:if>
//-->
</script>
<body>
<div class="main">
	<c:if test="${flag != 0 }">
		<div class="breadcrumb">当前位置：工程基本信息</div>
	</c:if>
  

  <!--main3-->
  <div class="main3">
    
    <!--form-->
    <form id="form">
     <div class="easyui-tabs" style="margin:30px;">
		<div title="工程基本信息" data-options="selected:true" style="padding:10px;">
			<div class="formsub">
 
        <table class="table2 table2-striped table2-bordered table2-condensed table2-down">
         	<c:if test="${'7'==projectFlag }">
		      <div class="action"  style="margin-bottom:10px;">
		      <input name="btn_update" type="button" onclick="gotoUpdate();" value="" style="background:url(img/t-edit.png); width:64px; height:25px; margin:0; border:none;display:none"/>
		      <input name="btn_update" type="button" onclick="changeFlag();" value="" style="background:url(img/tijiao.png); width:64px; height:25px; margin:0; border:none;display:none"/>
		      </div>
			</c:if>
          <tr class="odd">
            <th style="width:20%;">工程名称：</th>
            <td style="width:30%;">${project.pro_name }</td>
            <th style="width:20%;">工程类型：</th>
            <td style="width:30%;" id="type">${project.pro_type }</td>
          </tr>
          <tr>
            <th>工程投资（元）：</th>
            <td>${project.pro_invest }</td>
            <th>工程量：</th>
            <td>${project.pro_measure }</td>
          </tr>
          <tr class="odd">
            <th>开始时间：</th>
            <td>${project.pro_start_date}</td>
            <th>结束时间：</th>
            <td>${project.pro_end_date }</td>
          </tr>
          <tr>
            <th>工程地址：</th>
            <td>${project.pro_place}</td>
            <th>施工状态：</th>
            <td>
				<c:if test="${4==project.pro_state }">前期准备</c:if>
				<c:if test="${5==project.pro_state }">施工中</c:if>
				<c:if test="${6==project.pro_state }">已竣工</c:if>
			</td>
          </tr>
          <tr class="odd">
            <th>注册建造师：</th>
            <td id="manager">${project.pro_manager_name }</td>
            <th>&nbsp;</th>
            <td>
				&nbsp;
			</td>
          </tr>
          <tr>
            <th>技术员：</th>
            <td colspan="3">${pro_assistants}</td>
          </tr>
          <tr>
            <th>工程描述：</th>
            <td colspan="3">${project.pro_describe}</td>
          </tr>
          <tr class="odd">
            <th>附件下载：</th>
            <td colspan="3" class="text1">
            	<c:forEach var="m" items="${fileList}">
	            	<a href="javascript:fileDownload('${m.pf_url }','${m.pf_name }');">${m.pf_name }</a><br/>
            	</c:forEach>
            </td>
          </tr>
        </table>

      </div>
		</div>
	</div>
    </form>

  </div>
</div>
<!--表单验证-->
<!-- <script type="text/javascript" src="js/form/jquery-1.9.1.min.js"></script> -->
<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>

</body>
<script src="js/bootstrap.min.js"></script>
<script src="jsp/project/viewProjectPage.js"></script>

</html>