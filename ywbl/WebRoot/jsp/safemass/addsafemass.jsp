<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<link href="<%=path%>/css/layout.css" type="text/css" rel="stylesheet">
	<link href="<%=path%>/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<!--表单验证-->
	<link rel="stylesheet" href="<%=path%>/css/form/style.css" type="text/css" media="all" />
	<link href="<%=path%>/css/form/demo.css" type="text/css" rel="stylesheet" />
	<!--tree-->
	<script type="text/javascript" src="<%=path%>/js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/lhgcalendar/lhgcalendar.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jquery.jdirk.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.extensions.all.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tree/jeasyui.icons.all.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/tree/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/js/fileupload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/jsp/safemass/safemass.js"></script>
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
				beforeSubmit:function(curform){
					//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
					//这里明确return false的话表单将不会提交;
					var flag = $('#pc_id').val();
					var row = $('#tg').treegrid('getSelected');
					if (flag==null||flag==0){
					if (row && row.pc_id!=0) {
						$('#pc_id').val(row.pc_id);
						$('#pc_name').val(row.pc_name);
					}else{
						$.alert("请选择子工程!");
						return false;  //这里明确return false的话表单将不会提交;	
						}
					}
					if(flag!=null&& row && row.pc_id!=0){
						$('#pc_id').val(row.pc_id);
						$('#pc_name').val(row.pc_name);
					}else{
						$.alert("请选择子工程!");
						return false;  //这里明确return false的话表单将不会提交;	
					}
				},
				callback:function(data){
					parent.parent.showCount();
					$.closeDialog();
				}
			});
			
			
		demo.addRule([{
			ele:"select",
			datatype:"*"
		}]);
		
		
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
	        }]],
	        enableHeaderContextMenu: false,
	        onLoadSuccess:function(){
	        	$('#tg').treegrid('select',$('#pc_id').val());
	        }
	    });
	})
	
	/**
	 * 保存
	 */
	function doSub() {
		if(null != $('#sc_file_name').val() && 0==$('#uploadfile').val().length){
			$(".registerform").submit();
		}else{
			ajaxFileUpload();
		}
		//$(".registerform").submit();
	}
	
	function ajaxFileUpload(){
	    	var FileId = 'uploadfile';
	    	$.ajaxFileUpload
	        (
	            {
	                url:'safe!ajaxFileUpload.action',
	                secureuri:false,
	                fileElementId:FileId,
	                dataType: 'json',
	                success: function (data, status)
	                {   
	                	if(data.status=='y'){
							$('#sc_file_url').val(data.sc_file_url);
							$('#sc_file_name').val(data.sc_file_name);
							$(".registerform").submit();
	                	}else{
	                		$.alert(data.info);
	                		return false;
	                	}
	                },
	                error: function (data, status, e)
	                {
	                	$.alert("文件上传失败!");
	                }
	            }
	        )
		}
	/**
	 * 关闭弹出窗口
	 */
	function closeDialog() {
		$.closeDialog();
	}
	</script>
</head>

<body>
	<div class="main">
		<div class="main5">

			<!--form-->
			<form id="form" enctype="multipart/form-data" action="safe!addSafeControl.action" class="registerform" method="post">
				<div class="formsub" style="padding:10px;">
				
				<!--toolbar-->
				<input type="button" onclick="doSub();" value="" style="background:url(<%=path %>/img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" /> 
		
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
    <th style="width:35%;"><span class="need">*</span> 标题：</th>
    <td style="width:65%;">
    					<input type="hidden" id="sc_id" name="safe_control.sc_id" value="${safe_control.sc_id }"/>
    					<input type="hidden" id="pc_id" name="safe_control.pc_id" value="${ safe_control.pc_id}"/>
    					<input type="hidden" id="pc_name" name="safe_control.pc_name" value="${safe_control.pc_name }"/>
						<input type="text" id="sc_title" name="safe_control.sc_title" value="${ safe_control.sc_title}" class="inputxt" datatype="*1-20" errormsg="标题不可为空,最多20个字符！" />
							<div class="Validform_checktip">标题不可为空,最多20个字符！</div></td>
  </tr>
  <tr>
    <th><span class="need">*</span> 类型：</th>
    <td><select id="sc_type" name="safe_control.sc_type">
									<option value="" style="font-size:12px;">请选择</option>
									<option value="27" <c:if test="${safe_control.sc_type==27}">selected="selected"</c:if> style="font-size:12px;">消息</option>
									<option value="28" <c:if test="${safe_control.sc_type==28}">selected="selected"</c:if> style="font-size:12px;">报警</option>
							</select>
							<div class="Validform_checktip">类型不可为空！</div></td>
  </tr>
  <tr>
    <th><span class="need">*</span> 附件(最大10M)：</th>
    <td><input id="uploadfile" type="file" name="uploadfile" />
							<input type="hidden" id="sc_file_name" name="safe_control.sc_file_name" value="${safe_control.sc_file_name }"/>
							<input type="hidden" id="sc_file_url" name="safe_control.sc_file_url" value="${safe_control.sc_file_url }"/>
							<c:if test="${safe_control.sc_file_name!=null}">
								<a href="javascript:fileDownload('${safe_control.sc_file_name }','${safe_control.sc_file_url }');" style="color:#014265;">${safe_control.sc_file_name }</a>
							</c:if>
							</td>
  </tr>
  <tr>
    <th><span class="need"></span> 描述：</th>
    <td><textarea id="sc_remark" name="safe_control.sc_remark" rows="5" cols="5"><c:if test="${safe_control.sc_remark!=null}">${safe_control.sc_remark }</c:if></textarea>
						</td>
  </tr>
</table>


<div class="clear"></div>
	    <!--tree-->
	    <div class="tree" style="margin-top:10px;">
	      <table id="tg" style="width:725px;"></table><div class="clear" style="height:10px;"></div>
	    </div>
	    
					
				</div>
			</form>
			
		
		</div>
	</div>
</body>

</html>