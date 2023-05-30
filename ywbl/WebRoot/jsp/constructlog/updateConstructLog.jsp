<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> 
	<%@ include file="/jsp/common/common.jsp"%>
     <script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
     <script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
     <script type="text/javascript" src="<%=path %>/jsp/constructlog/constructlog.js"></script>
     <link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
	<!--表单验证-->
	<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	$(function(){
		//$(".registerform").Validform();  //就这一行代码！;
			
			var demo = $(".registerform").Validform({
				ajaxPost:true,	 //异步
			
				tiptype:function(msg,o,cssctl){
					//msg：提示信息;
					//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
					//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
					if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
						var objtip=o.obj.siblings(".Validform_checktip");
						cssctl(objtip,o.type);
						objtip.text(msg);
					}
				},
				callback:function(data){
					if (data.status=="y") {
						$.closeDialog();
					}else{
						
						$.alert('保存失败！');
					}
				}
			});
		demo.addRule([{
			ele:"select",
			datatype:"*"
		}]);
	})
	
	function addFileUpload(){
		$.dialog({
			id:'uploadFilesId',
			url:'filesUpload!uploadFile.action',
			title: '上传文件', 
			width: 300, 
			height: 400,
			data:{
				filePath:'D:/fileUpload/'
			}
			});
	}
	function getChildData(data){
		var json = $.parseJSON(data);
		$(json.files).each(function(){
			$('#fileUploadTable').append("<tr><td>"+this.name+"<input type='hidden' value='"+this.name+"' name='fileName'/>"+
					"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>");
		})
	}
	</script>
</head>

<body>
	<div class="main">
		<div class="main5">
		
		<!--toolbar-->
		 <input type="button" onclick="doSub();" value="" style="background:url(<%=path %>/img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />


			<!--form-->
			<form id="form" action="constructlog!updateConstructLog.action" class="registerform" method="post">
			
						<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 上报时间：</th>
    <td style="width:65%;"><input type="text" id="sc_title" class="inputxt" name="constructLog.cl_date" value="${ constructLog.cl_date }" onclick="$.calendar();" datatype="*1-100" nullmsg="请添加时间信息" readonly="readonly" />
						<input type="hidden"  name="constructLog.cl_id" value="${ constructLog.cl_id }" /></td>
  </tr>
  <tr>
    <th><span class="need">*</span>附件(最大10M)：</th>
    <td><input name = "" type="button" value ="上传附件" onclick="addFileUpload();"/>
							<table id = "fileUploadTable">
								<c:forEach var="log" items="${ list }" varStatus="i">
									<tr>
										<td>${ log.clf_name }<input type='hidden' value='${ log.clf_name }' name='fileName'/>
										<input type='hidden' name='filePath' value='${ log.clf_url }'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>
										</td>
									</tr>
								</c:forEach>
							</table></td>
  </tr>
  <tr>
    <th>上报描述：</th>
    <td><textarea id="sc_remark" name="constructLog.cl_describe"  rows="5" cols="5">${ constructLog.cl_describe }</textarea></td>
  </tr>
</table>

			</form>
			
		<div class="clear"></div>
	    <!--tree-->
	    <div class="tree">
	      <table id="tg" style="width:763px;"></table><div class="clear" style="height:10px;"></div>
	    </div>
		</div>
	</div>
</body>

</html>