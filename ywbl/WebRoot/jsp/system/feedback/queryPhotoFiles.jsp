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

			function queryAllFeedBack(){
				
			 	 layer.open({
			  		  type: 2,
			  		  title:'',
			  			setOpaqueRate:'1',
			  		  area: ['1200px', '800px'],
			  		  fixed: false, //不固定
			  		  maxmin: false,
			  		  content: 'feedback!queryAllFeedBack.action?mattersid='+'${mattersVo.id }'
			  		}); 
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
	  

	  
	  
	  <input type="hidden" name="mattersVo.id" value="${mattersVo.id }"/>
	  <input id="state" type="hidden" name="mattersVo.state" value="${mattersVo.state }"/>
	  <input type="hidden" value="${mattersVo.inittime }" name="mattersVo.inittime" class="inputxt" />
	  <input type="hidden" value="${mattersVo.updatetime }" name="mattersVo.updatetime" class="inputxt" />
	  <input type="hidden" value="${mattersVo.matters_id }" name="mattersVo.matters_id"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.fid }" name="mattersVo.fid"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.flag }" name="mattersVo.flag"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.sendtime }" name="mattersVo.sendtime"    class="inputxt" />
<!--table-->		
<div align="right">
    <button type="button"	onclick="closeWindows()"	 class="layui-btn layui-btn-normal">关闭</button>
</div>

     <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>附件信息</legend>
</fieldset> 
	  
  <div class="layui-form"  align="left" >
  <table class="layui-table"  align="left" >
   
  <tr>
    <th>附件 </th>
    <td colspan="3">
              <c:forEach var="m" items="${companyfileList}">
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
 					 查看
 					</a>
              </c:forEach>
									</td>
  </tr>
 
</table>
	</div>
		</form>
	
				<div align="center">
 		</div>
  </div>
  	</div>
  	
	</body>
	
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
	 
	 <script>
	 
	 </script>
</html>
