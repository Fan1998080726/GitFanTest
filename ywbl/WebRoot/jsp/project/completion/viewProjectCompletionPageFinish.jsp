<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
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
	
	<!--easyUI-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
</head>

<script type="text/javascript">

function fileDownload(sc_file_name, sc_file_url){
	var url = "completion!fileDownload.action?sc_file_name="+sc_file_name+"&sc_file_url="+sc_file_url+"";
	window.location.href=url;
}

</script>
<body>
<div class="main">
		<div class="breadcrumb">当前位置：竣工申请</div>

  <!--main3-->
  <div class="main3">
    
    <!--form-->
    <form id="form" action="completion!addCompletionPro.action" class="registerform" method="post">
     <div class="easyui-tabs" style="margin:30px;">
		<div title="工程基本信息" data-options="selected:true" style="padding:10px;">
			<div class="formsub">
 
        <table class="table2 table2-striped table2-bordered table2-condensed table2-down">
          <tr class="odd">
            <th style="width:20%;">工程名称：</th>
            <td style="width:30%;">${project.pro_name }</td>
            <th style="width:20%;">工程类型：</th>
            <td style="width:30%;">${project.pro_type }</td>
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
            <td>${project.pro_manager_name }</td>
            <th>&nbsp;</th>
            <td>
				&nbsp;
			</td>
          </tr>
          <tr>
            <th>工程描述：</th>
            <td colspan="3">${project.pro_describe}</td>
          </tr>
           <tr>
		    <th><span class="need">*</span>附件(最大10M)：</th>
		    <td colspan="3">
					 		<c:forEach var="log" items="${ fileList }" varStatus="i">
					 		<table id = "fileUploadTable" class="table2 table2-striped table2-bordered table2-condensed table2-down">
									<tr>
										<td>
										 <p><a href="javascript:fileDownload('${log.caf_name }','${log.caf_url}');"><span style="color: black;">${log.caf_name}</span>$</a></p>
										</td>
									</tr>
							</table>
							</c:forEach>
			</td>
		 </tr>
		 	<tr>
				    <th>备注：</th>
				    <td colspan="3"><textarea style="width:80%;" id="ca_remark" name="apply.ca_remark"  cols="5" rows="5"  readonly="readonly">${ apply.ca_remark  }</textarea></td>
			</tr>
        </table>
      </div>
		</div>
	</div>
    </form>
  </div>
</div>
</body>
</html>