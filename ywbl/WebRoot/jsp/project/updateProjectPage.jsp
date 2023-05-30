<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="css/layout.css" type="text/css" rel="stylesheet">
		<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
			<!--表单验证-->
			<link rel="stylesheet" href="css/form/style.css" type="text/css"
				media="all" />
			<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
			<!--tree-->
			<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
			<script type="text/javascript"
				src="js/lhgcalendar/lhgcalendar.min.js"></script>
			<script type="text/javascript"
				src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
				
	<!--easyUI-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.jdirk.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.extensions.all.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.icons.all.min.js"></script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}

.selectbox{width:500px;height:220px;margin:40px auto 0 auto;}
.selectbox div{float:left;}
.selectbox .select-bar{padding:0 20px;}
.selectbox .select-bar select{width:150px;height:200px;border:1px solid #95b8e7; padding:4px;}
.selectbox .btn{width:36px; height:30px; margin-top:10px; cursor:pointer; font-weight:bold;}
</style>
</head>

<body>
	<div class="main">
		<div class="breadcrumb">当前位置：工程基本信息</div>

		

		<!--main3-->
		<div class="main3">
		
    <!--form-->
    <form  id="form" class="registerform" enctype="multipart/form-data" method="post">
         <input type="hidden" name="project.pro_id" value="${project.pro_id }" />
		 <input type="hidden" name="delFileId" id="delFileId"/>
		 <input type="hidden" name="selectUser" id="selectUser"/>
     <div class="easyui-tabs" style="margin:30px;">
		<div title="工程基本信息" data-options="selected:true" style="padding:10px;">
			<div class="formsub">
 
        <table class="table2 table2-striped table2-bordered table2-condensed table2-down">
         
      <div class="action"  style="margin-bottom:10px;">
      <input type="button" onclick="update(7);" value="" style="background:url(img/t-zancun.png); width:62px; height:24px; margin:0; border:none;" /> 
      <input type="button" onclick="update(8);" value="" style="background:url(img/tijiao.png); width:64px; height:25px; margin:0; border:none;" />
      </div>
      
          <tr>
            <th style="width:20%;"><span>*</span>工程名称：</th>
            <td style="width:30%;"><input type="text" value="${project.pro_name }" name="project.pro_name" readonly
							class="inputxt" />
							</td>
            <th style="width:20%;"><span>*</span>工程类型：</th>
            <td style="width:30%;"><input type="text" value="${project.pro_type }" name="project.pro_type"
							class="inputxt" datatype="*1-25" errormsg="工程类型不可为空,最多25个字符！" />
							<div class="Validform_checktip">工程类型不可为空,最多25个字符！</div></td>
          </tr>
          <tr>
            <th><span>*</span>工程投资（元）：</th>
            <td><input type="text" value="${project.pro_invest }"
							name="project.pro_invest" class="inputxt" readonly/>
				</td>
            <th></th>
            <td></td>
          </tr>
          <tr>
            <th><span>*</span>开始时间：</th>
            <td><input readonly="readonly" id="pro_start_date" 
							value="${project.pro_start_date}" name="project.pro_start_date"
							class="inputxt" datatype="*" nullmsg="工程开始时间不可为空！" />
							<div class="Validform_checktip">工程开始时间不可为空！</div></td>
            <th><span>*</span>结束时间：</th>
            <td><input readonly="readonly" id="pro_end_date"
							value="${project.pro_end_date }" name="project.pro_end_date"
							class="inputxt" datatype="*" nullmsg="工程结束时间不可为空！" />
							<div class="Validform_checktip">工程结束时间不可为空！</div></td>
          </tr>
          <tr>
            <th><span>*</span>工程地址：</th>
            <td><input type="text" value="${project.pro_place }" readonly
							name="project.pro_place" class="inputxt"  />
							</td>
            <th><span>*</span>注册建造师：</th>
            <td><input onclick="selectManaPage();" readonly="readonly" id="pro_manager_name" value="${project.pro_manager_name }"
							class="inputxt" datatype="*"
							nullmsg="注册建造师不可为空！" />
							<input type="hidden" id="pro_manager" name="project.pro_manager" value="${project.pro_manager }"/>
							<div class="Validform_checktip">注册建造师不可为空！</div></td>
          </tr>
           <tr>
            <th>分配技术员：</th>
            <td colspan="3">
            <div class="selectbox">
				<div class="select-bar">
					<select multiple="multiple" id="select1">
					<c:forEach var="m" items="${userList}">
						<option value="${ m.id}">${m.user_name }</option>
					</c:forEach>
					</select>
				</div>
				<div class="btn-bar">
					<span id="add"><input type="button" class="btn btn-info" value=">"/></span><br />
					<span id="add_all"><input type="button" class="btn btn-info" value=">>"/></span><br />
					<span id="remove"><input type="button" class="btn btn-info" value="<"/></span><br />
					<span id="remove_all"><input type="button" class="btn btn-info" value="<<"/></span>
				</div>
				
				<div class="select-bar"><select multiple="multiple" name="selectUser" id="select2">
					<c:forEach var="m" items="${userForProject}">
						<option value="${ m.id}">${m.user_name }</option>
					</c:forEach>
				</select></div>
				
			</div>
			</td>
          </tr>
          <tr>
            <th>工程描述：</th>
            <td colspan="3"><textarea class="input-xlarge" id="textarea"
							name="project.pro_describe" rows="3" datatype="*0-250" errormsg="工程描述最多250个字符！"
							style="width: 805px; height: 116px;">${project.pro_describe }</textarea>
							<div class="Validform_checktip">工程描述最多250个字符！</div>
							</td>
          </tr>
          <tr class="odd">
            <th>附件上传：</th>
            <td colspan="3" class="text1">
            	<span onclick="addFileUpload()" class="btn btn-small btn-info" style="color:#fff; font-weight:bold;">添加文件上传</span>
						<table id="fileUploadTable" style="margin-top:8px;">
							<c:forEach var="m" items="${fileList}">
								<tr>
									<td>${m.pf_name }
									</td>
									<td>
									<input type="button" value="删除" onclick="delFile(${m.pf_id },this);" />
									</td>
								</tr>
							</c:forEach>
							<%--<tr>
								<td width="100px"><label>文件名称：</label>
								</td>
								<td><input id="myFile_1" type="file" name="myFile_1" style="margin-left:6px;"/>
								</td>
							</tr>
						--%>
						</table>
            </td>
          </tr>
        </table>

      </div>
		</div>
	</div>
    </form>
    



		</div>
	</div>

	<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>

</body>
<script src="js/bootstrap.min.js"></script>
<script src="jsp/project/updateProjectPage.js"></script>
</html>