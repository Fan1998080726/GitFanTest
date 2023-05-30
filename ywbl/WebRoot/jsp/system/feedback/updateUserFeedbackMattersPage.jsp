<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
 <%@ include file="/jsp/common/commonlayUi.jsp" %>
<%--  <%@ include file="/jsp/common/common.jsp" %> --%>
 
<%-- <% --%>
<%-- %> --%>
<%-- <link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />   --%>
<%--   <link href="<%=path %>/css/zj-skin.css" rel="stylesheet" type="text/css" />   --%>

 
	<script>
			var fileSum=1;
			function addFileUpload(){
	 
				if(fileSum == 5){
					 $.alert("最多只能同时上传5个文件！");
					return;
				}
				fileSum = fileSum+1;
				$('#fileUploadTable').append('<tr><td width="100px"><label>文件名称：</label></td><td><input id="myFile_'+fileSum+'" type="file" name="myFile_'+fileSum+'"/></td></tr>');
			}
			
			
				function addFileUpload(){
				$.dialog({
					id:'uploadFilesId',
					url:'filesUpload!uploadFile.action',
					title: '上传文件', 
					width: 500, 
					height: 400,
					data:{
						filePath:'C:/dbxtdownload/'
					}
					});
			} 

			var mytime='${mytime}';
			function getChildData(data){
				var json = $.parseJSON(data);
				$(json.files).each(function(){
					$('#fileUploadTable').append(
							"<tr>"+
							"<td>"+
							"<input type='hidden' name='saomiaodate' value='"+mytime+"'/></br>"+
							"<a href=http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/"+this.name+" target='_bank' class='input1' >附件</a>&nbsp;&nbsp;&nbsp;<input type='hidden' value='"+this.name+"' name='fileName'/>"+
							"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><input type='hidden' name='type' value='feedbackFile'/><td><input class='layui-btn layui-btn-normal'   type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()' style='width:50px;'/></td></tr>");
				})
			}			

			function delFile(pf_id,btn){
				 $("tr[id="+pf_id+"]").remove();
			}



			function onFeedback(){
				if($("#feedback_breaks").val()==''){
					alert("请填写反馈内容");
					return false;
				}
				
				 layer.confirm('是否确认反馈?', {
			        btn: ['确定', '取消']
			    }, function (index, layero) {
					$.ajax({
				        type: "post",
				    	data : $(".layui-form").serialize(),  
				        url: "feedback!SaveFeedBack.action",
				        success: function(data){
				        	   //无法关闭这个消息框
				            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
				            layer.msg("反馈成功!",{ icon: 1, time: 1000 });
						   	fnIntoView();
				        }
				    });
			    }
			        );
				 
				 
				 

			}


			
			
			
			
			
			
			
			
			
			
			
			
			
	</script>
		
		     <style type= "text/css" >
 ul {
    width: 500px; 
    overflow: hidden;
    white-space:nowrap;  
}
li{
    list-style: none;
    float: left; //向左排列
    margin-left: 15px;
    width: 130px;
}
     </style>
 
		
	</head>

<body  style="background-color: white;" >
 
	<div >
  <div>
  	  <form class="layui-form"  >
  
  <input type="hidden" name="feedbackVo.id"	 value="${feedbackVo.id }"  />
  <input  type="hidden" name="feedbackVo.fid"	 value="${feedbackVo.fid }"  />
  <input type="hidden"  name="feedbackVo.matter_id"	 value="${feedbackVo.matter_id }"  />
  
<!--   	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> -->
<!--   <legend>操作</legend> -->
<!-- </fieldset>  -->
</br>
<div align="right"	>
    <button type="button"	onclick="onFeedback()"	 class="layui-btn layui-btn-normal">保存</button>
    <button type="button"	onclick="closeWindows()"	 class="layui-btn layui-btn-normal">关闭</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
</div>

    
    
    
  	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>事项反馈</legend>
</fieldset> 
	   <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">反馈信息</label>
    <div class="layui-input-block">
      <textarea placeholder="请输入内容" class="layui-textarea"	id="feedback_breaks"	 name="feedbackVo.feedback_breaks"></textarea>
    </div>
  </div>
	   <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">文件上传</label>
    
    
       <div class="layui-input-block">
       
<!--     			<span onclick="addFileUpload()" class="btn btn-small btn-info" style="color:#FFFFFF; font-weight:bold;">添加文件</span> 		 -->
    			  <button type="button"	 onclick="addFileUpload()" class="layui-btn layui-btn-normal">添加文件</button>
		<table id="fileUploadTable" style="margin-top:8px;" class="table2" align="center"  >
              <c:forEach var="m" items="${feedbackfileList}">
                	<tr id="${m.cf_id }"></tr>
                	<td>
                	
					 <input type="button" value="删除"	 class="layui-btn layui-btn-normal" onclick="delFile('${m.cf_id }',this);" style="width:50px;"/>
					</td>
                	 
                	<tr id="${m.cf_id }">
					<td colspan='3'>
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
 				附件
 					</a>
					</td>									
					<input type="hidden" name="fileName" value="${m.cf_name }"/>
					<input type="hidden" name="filePath" value="${m.cf_url }"/>
					<input type="hidden" name="type" value="${m.cf_type }"/>
					</tr>
              </c:forEach>
            </table>			
    </div>
  </div>
	  
	 </form> 
 
  	</div>
  	
	</body>
	
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
	 
	<script>
	new Function($('.run').text())()
	var index = parent.layer.getFrameIndex(window.name); 
	//给父页面传值

	 function fnIntoView(){
// 		alert("22222");
	   	parent.onmousedown_left(1); 
// 	   	alert("xxxx");
		parent.layer.tips('Look here', '#parentIframe', {time: 5000}); 
// 		alert("111");
	    parent.layer.close(index);
	}
	</script>
</html>
