<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
 <%@ include file="/jsp/common/commonlayUi.jsp" %>
<%-- <% --%>
<%-- %> --%>
<%-- <link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />   --%>
<%--   <link href="<%=path %>/css/zj-skin.css" rel="stylesheet" type="text/css" />   --%>

 
	<script>
 		
		function delFeedback(){
 
	
			
			var checkId = "";
			$('input[name="checkId"]:checked').each(function(){
				checkId=checkId+$(this).val()+",";
				});	  
			
			$("#ids").val(checkId);
			
			 layer.confirm('是否确认删除反馈?', {
			        btn: ['确定', '取消']
			    }, function (index, layero) {
			    	
			    	
			    	
					$.ajax({
				        type: "post",
				    	data : $(".layui-form").serialize(),  
				        url: "feedback!delFeedBack.action",
				        success: function(data){
				        	   //无法关闭这个消息框
				            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
				            layer.msg("删除成功!",{ icon: 1, time: 1000 });
				            location.reload();
				        }
				    });
			    }
			        );
			 
			 
			 
			 
			
		}
	
			
			function fnIsAllCheck(str,str1){
				if(str=='1'){
		                    $('table input[id=source_unit]').attr('checked', true);
				}
				if(str=='2'){
                    $('table input[id=cooperate_unit]').attr('checked', true);
		}
			}
			
			
			function fnIsNotCheck(str,str1){
				if(str=='1'){
		                    $('table input[id=source_unit]').attr('checked', false);
				}
				if(str=='2'){
                    $('table input[id=cooperate_unit]').attr('checked', false);
		}
			}
			
			
			
			
			
			
			
			
			


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
							"<a href=http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/"+this.name+" target='_bank' class='input1' ><img src='/dbxtdownload/"+this.name+"' style='width: 200px; height: 100px;'  /></a><input type='hidden' value='"+this.name+"' name='fileName'/>"+
							"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><input type='hidden' name='type' value='remark'/><td><input class='layui-btn layui-btn-normal'   type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()' style='width:50px;'/></td></tr>");
				})
			}			

			function delFile(pf_id,btn){
				 $("tr[id="+pf_id+"]").remove();
			}



			function onFeedback(){
				
			 	 layer.open({
			  		  type: 2,
			  		  title:'',
			  			setOpaqueRate:'1',
			  		  area: ['600px', '400px'],
			  		  fixed: true, //不固定
			  		  maxmin: true,
			  		  content: 'feedback!updateUserFeedbackMattersPage.action?mattersid='+'${mattersVo.id }'
			  		}); 
			}
			

			function sendDelay(){
				
			 	 layer.open({
			  		  type: 2,
			  		  title:'延期申请',
			  		  setOpaqueRate:'1',
			  		  area: ['600px', '500px'],
			  		  fixed: true, //不固定
			  		  maxmin: true,
			  		  content: 'delay!ApplyDelay.action?mattersid='+'${mattersVo.id }'
			  		}); 
			}
			
			function queryDelayById(){
				
			 	 layer.open({
			  		  type: 2,
			  		  title:'延期申请',
			  		  setOpaqueRate:'1',
			  		  area: ['600px', '400px'],
			  		  fixed: true, //不固定
			  		  maxmin: true,
			  		  content: 'delay!queryDelayById.action?mattersid='+'${mattersVo.id }'
			  		}); 
			}
			
			

			function queryAllFeedBack(){
				
			 	 layer.open({
			  		  type: 2,
			  		  title:'',
			  			setOpaqueRate:'1',
// 			  		  area: ['1000px', '700px'],
					  area: ['80%', '80%'],
			  		  fixed: true, //不固定
			  		  maxmin: true,
			  		  content: 'feedback!queryAllFeedBack.action?mattersid='+'${mattersVo.id }'
			  		}); 
			}



			
			
			
			
			
			
			
			
			function refreshView(){
				
			    location.reload();
			}

			
			
			
			function  onmousedown_left(){
			       location.reload();
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
  
  <input	 value=""  name="ids"  type="hidden"   id="ids"/>
   <div align="right"	>
    <button type="button"   onclick="closeWindows()" 	class="layui-btn layui-btn-normal">
     <i class="layui-icon"  style="font-size: 18px">&#x1006;</i>
    关闭</button>
 
</div>
  
  
<!--   	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"> -->
<!--   <legend>操作</legend> -->
<!-- </fieldset>  -->
    
  	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>反馈信息</legend>
</fieldset> 
 
 <div align="right"	>
    <button type="button"   onclick="queryAllFeedBack()" 	class="layui-btn layui-btn-normal">查看全部</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
</div>
 

	<c:forEach items="${feedList }"   var="v"	  begin="0"	end="0"	>
	
	
	
		   <div class="layui-form-item layui-form-text">
     <label class="layui-form-label"	 style="width: 150px">
    ${v.inittime }
    </br>
    ${v.feedback_person }
    </label>
       
    
        <div class="layui-input-block">
        
      <textarea placeholder="请输入内容" 	style="width: 85%"	class="layui-textarea" name="feedbackVo.feedback_breaks">反馈信息：${v.feedback_breaks }</textarea>
   
             <input type="checkbox"   lay-skin="primary"	value="${v.id }"	 name="checkId"   id="checkId"   >
    </div>
   
        <div class="layui-input-block" align="right" 	>
 
  			           <c:forEach var="m" items="${FeedbackList}">
  			           <c:if test="${m.c_id==v.fid}">
  			           		<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
 				 附件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 					</a>
 					
 					</c:if>
              </c:forEach>
  			
    </div>
   
    
  </div>
  
  
	</c:forEach>

  
  
  
	   <div class="layui-form-item layui-form-text">
 
    
    
       <div class="layui-input-block">
       
    			<span onclick="addFileUpload()" class="btn btn-small btn-info" style="color:#FFFFFF; font-weight:bold;">添加文件22</span> 		
<!--     			  <button type="button"	 onclick="addFileUpload()" class="layui-btn layui-btn-normal">添加文件</button> -->
		<table id="fileUploadTable" style="margin-top:8px;" class="table2" align="center"  >
              <c:forEach var="m" items="${feedbackfileList}">
                	<tr id="${m.cf_id }"></tr>
                	<td>
                	
					 <input type="button" value="删除"	 class="layui-btn layui-btn-normal" onclick="delFile('${m.cf_id }',this);" style="width:50px;"/>
					</td>
                	 
                	<tr id="${m.cf_id }">
					<td colspan='3'>
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
 					<img src="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" style="width: 300px; height: 200px;"  />
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
	  <form class="layui-form"  >
	  

	  
	  
	  <input type="hidden" name="mattersVo.id" value="${mattersVo.id }"/>
	  <input id="state" type="hidden" name="mattersVo.state" value="${mattersVo.state }"/>
	  <input type="hidden" value="${mattersVo.inittime }" name="mattersVo.inittime" class="inputxt" />
	  <input type="hidden" value="${mattersVo.updatetime }" name="mattersVo.updatetime" class="inputxt" />
	  <input type="hidden" value="${mattersVo.matters_id }" name="mattersVo.matters_id"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.fid }" name="mattersVo.fid"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.flag }" name="mattersVo.flag"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.sendtime }" name="mattersVo.sendtime"    class="inputxt" />
<!--table-->		
     <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>事项信息</legend>
</fieldset> 
	  
  <div class="layui-form"  align="left" >
  <table class="layui-table"  align="left" >
  <tr align="left"	>
    <th>事项类型:</th>
    <td> ${mattersVo.matter_type } </td>
  </tr>
    <tr>
    <th>监督事项:</th>
    <td> ${mattersVo.supervision_matter } </td>
  </tr>
    <tr>
    <th>主要任务:</th>
    <td> ${mattersVo.main_task } </td>
  </tr>
  <tr>
    <th>督办文号:</th>
    <td> ${mattersVo.supervision_code } </td>
  </tr>
  <tr>
    <th>事项来源:</th>
    <td> ${mattersVo.matter_source } </td>
  </tr>
  <tr>
    <th>分管领导:</th>
    <td> ${mattersVo.charge_lead }  </td>
  </tr>
 
  <tr>
    <th>抄送领导:</th>
    <td> ${mattersVo.chaosong_lead } </td>
  </tr>
  
  <tr>
    <th>牵头单位:</th>

    
    <td width="60%"  > 
  <div align="left" > 
  
  
        <c:forEach items="${parmList}"  var="m"  varStatus="status">
        <c:if test="${m.qiantouIscheck=='1' }">
 	<input  value="${m.id }" 	 style="height: 80px"
    		<c:if test="${m.qiantouIscheck=='1'}">checked="checked"</c:if>
    		name="mattersVo.source_unit"   id="source_unit"	type="checkbox"	   title="${m.login_id }"  />
 

		<c:if test="${status.count%3==0}">
		</br>
		</br>
		</c:if>
		        </c:if>
    </c:forEach>
    
 </div>
    
    </td>
  </tr>
  <tr>
    <th>配合单位:</th>
    
    
    
    
    
        <td width="60%"  > 
  <div align="left" >  
  
  
        <c:forEach items="${parmList}"  var="m"  varStatus="status">
          <c:if test="${m.peiheIscheck=='1' }">
 	<input  value="${m.id }" 	 style="height: 80px"
    		<c:if test="${m.peiheIscheck=='1'}">checked="checked"</c:if> 
    		name="mattersVo.cooperate_unit"   id="cooperate_unit"	type="checkbox"	   title="${m.login_id }"  />
 
		<c:if test="${status.count%3==0}">
		</br>
		</br>
		</c:if>
		</c:if>
    </c:forEach>
    
 </div>
    
    </td>
    
    
  </tr>
  <tr>
    <th>完成时限:</th>
    <td> ${mattersVo.end_time } </td>
  </tr>
  <tr>
    <th>反馈时限:</th>
    <td> ${mattersVo.fankui_time } </td>
  </tr>
  <tr>
    <th>交接人:</th>
    <td> ${mattersVo.handover_person } </td>
  </tr>
  <tr>
    <th>交办人联系电话:</th>
    <td> ${mattersVo.handover_tel } </td>
  </tr>
  
  
  <tr>
    <th>附件 </th>
    <td colspan="3">
              <c:forEach var="m" items="${companyfileList}">
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
 				
 				 
 				  
 
     <button type="button"  	class="layui-btn layui-btn-normal"> 附件</button>
 
 
 					</a>
              </c:forEach>
									</td>
  </tr>
  
  
  
  
  
  
  
  
  
  
  <tr>
    <th>备注:</th>
    <td> ${mattersVo.breaks } </td>
  </tr>
  
  
  <tr>
    <th>签收信息:</th>
    <td>
    <c:forEach items="${SiginList}" var="m">
    签收单位：${m.sigin_unit }
    &nbsp;&nbsp;
   签收人： ${m.sigin_person }
     &nbsp;&nbsp;
   签收时间： ${m.inittime }
     &nbsp;&nbsp;
   签收人联系电话： ${m.sigin_person }
   </br>
   承办单位：${m.cb_unit }
     &nbsp;&nbsp;
   承办人：${m.cb_person }
     &nbsp;&nbsp;
   承办人联系电话：${m.cb_tel }
   </br>
   </br>
    </c:forEach>
     </td>
  </tr>

</table>
	</div>
		</form>
	
<!-- 				<div align="center"> -->
<!-- 		<input type="button" onclick="javascript:history.back(1);" value="" style="background:url(img/t_back.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; "/> -->
<!-- 		</div> -->
  </div>
  	</div>
  	
	</body>
	
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
	 
	 <script>
	 
	 </script>
</html>
