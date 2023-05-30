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



/**
 * 添加页面提交
 */
function doSub(flag) {
	
	if(flag == 1){
		var tablebody = $("#fileUploadTable tr").length;
		if(tablebody != 0){
			
			
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
								
								$.alert('保存成功！');
								
							}else{
								
								window.location.reload();
							}
						}
					});
				demo.addRule([{
					ele:"select",
					datatype:"*"
				}]);
			})
			
			$("#form").attr("action","completion!addCompletionPro.action?apply.ca_flag=32");
			$(".registerform").submit();
		}else{
			$.alert("请上传附件");
			return false;
		}
	}else{
		$.ajax({
			url:'project!queryProjectStatus.action',
			dataType:'json',
			success:function(data){
				if(data.status == '19'){
					
					var tablebody = $("#fileUploadTable tr").length;
					if(tablebody != 0){
						$("#form").attr("action","completion!addCompletionProFinish.action?apply.ca_flag=33");
						$("#form").submit();
					}else{
						$.alert("请上传附件");
						return false;
					}
					
					
				}else{
					$.alert("本项目没有审批通过，不能提交竣工申请！");
				}
			},
			error:function(){
				$.alert("提交失败！");
			}
		});
	}
	

	
}

function addFileUpload(){
	$.dialog({
		id:'uploadFilesId',
		url:'filesUpload!uploadFile.action',
		title: '上传文件', 
		width: 400, 
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
    
    </form>
    <!--form-->
    <form id="form" action="completion!addCompletionPro.action" class="registerform" method="post" >
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
		    <c:choose>
					 <c:when test = "${ apply.ca_flag == 32 ||  apply.ca_flag == null}">
					  		    <input name = "" type="button" value ="上传附件" onclick="addFileUpload();"/>
							<table id = "fileUploadTable" class="table2 table2-striped table2-bordered table2-condensed table2-down">
								<c:forEach var="log" items="${ fileList }" varStatus="i">
									<tr>
										<td>${ log.caf_name }<input type='hidden' value='${ log.caf_name }' name='fileName'/>
										<input type='hidden' name='filePath' value='${ log.caf_url }'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>
										</td>
									</tr>
								</c:forEach>
							</table>
					 </c:when>
					 <c:otherwise>
					 		<c:forEach var="log" items="${ fileList }" varStatus="i">
					 		<table id = "fileUploadTable" class="table2 table2-striped table2-bordered table2-condensed table2-down">
									<tr>
										<td>
										 <p><a href="javascript:fileDownload('${log.caf_name }','${log.caf_url}');"><span style="color: black;">${log.caf_name}</span>$</a></p>
										</td>
									</tr>
							</table>
							</c:forEach>
					 </c:otherwise>
			</c:choose>
			</td>
		 </tr>
		 	<tr>
				    <th>备注：</th>
				    <td colspan="3"><textarea style="width:80%;" id="ca_remark" name="apply.ca_remark"  cols="5" rows="5"  <c:if test="${apply.ca_flag != 32 && apply.ca_flag != null}">readonly</c:if>>${ apply.ca_remark  }</textarea></td>
			</tr>
        </table>
       <!--toolbar-->
				<div class="action" id="toolbar" style="width:136px; margin:10px auto 0;">
				<c:if test="${ apply.ca_flag != 34  && apply.ca_flag != 33}">
					<input name="btn_update" type="button" onclick="doSub(1);" value=""
						style="background:url(<%=path %>/img/t-zancun.png) no-repeat; width:64px; height:25px; border:none; margin:0;display:none" />
                    <input  name="btn_update"
						type="button" onclick="doSub(2);" value=""
						style="background:url(<%=path %>/img/tijiao.png) no-repeat; width:64px; height:25px; border:none; margin:0;display:none" />
				</c:if>
				</div>
      </div>
		</div>
	</div>
    </form>
  </div>
</div>
</body>
</html>