<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
		<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
		<link href="css/layout.css" type="text/css" rel="stylesheet">
		<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<!--表单验证-->
		<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
		<script src="js/bootstrap.min.js"></script>

	<script>
			function  doSub(state){
				
				$("#state").val(state);
// 				console.log($("input[id='source_unit']").attr('checked'));
				
				console.log($("input[id='source_unit']").is(':checked'));//false

				var  flag = false;
				
 			if($("input[id='source_unit']").is(':checked')!=false){
 				flag = true;
 			}
			
 			if(flag==false){
 				alert("请勾选牵头单位！");
 			}
 			
 			if(flag){
 				$.ajax({
 					type:'post',//可选get
 					url:'matters!SaveMattersData.action',//这里是接收数据的PHP程序
 				 	data : $(".registerform").serialize(),
 					success:function(data){
 						 	alert("保存成功！");
 							location.replace("matters!queryMattersAdmin.action");
 					},
 					error:function(){
 						$.alert('保存失败！');
 					}
 				});
 			}
			
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
							"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><input type='hidden' name='type' value='remark'/><td><input  type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()' style='width:50px;'/></td></tr>");
				})
			}			

			function delFile(pf_id,btn){
				 $("tr[id="+pf_id+"]").remove();
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
<body>
	<div class="main">
  <div class="breadcrumb">当前位置：事项详情</div>
  <div class="main3">
	  <form name="form1" class="registerform"  >
	  <input type="hidden" name="mattersVo.id" value="${mattersVo.id }"/>
	  <input id="state" type="hidden" name="mattersVo.state" value="${mattersVo.state }"/>
	  <input type="hidden" value="${mattersVo.inittime }" name="mattersVo.inittime" class="inputxt" />
	  <input type="hidden" value="${mattersVo.updatetime }" name="mattersVo.updatetime" class="inputxt" />
	  <input type="hidden" value="${mattersVo.matters_id }" name="mattersVo.matters_id"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.fid }" name="mattersVo.fid"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.flag }" name="mattersVo.flag"    class="inputxt" />
	  <input type="hidden" value="${mattersVo.sendtime }" name="mattersVo.sendtime"    class="inputxt" />
<!--table-->		
<table class="table2 table2-striped table2-bordered table2-condensed table2-down">
  <tr>
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

    
    <td style="width:60%" > 
        <ul>
        <c:forEach items="${parmList}"  var="m" >
	<li>
    		<input  value="${m.id }" 	 disabled="disabled"
    		<c:if test="${m.qiantouIscheck=='1'}">checked="checked"</c:if>
    		name="mattersVo.source_unit"   id="source_unit"	type="checkbox"	 />${m.login_id }
<%--     		${m.qiantouIscheck } --%>
	</li>        
    </c:forEach>
        </ul>
 
    </td>
  </tr>
  <tr>
    <th>配合单位:</th>
    <td> 
    
            <ul>
        <c:forEach items="${parmList}"  var="m" >
	<li>
    		<input  value="${m.id }"   disabled="disabled"
    		    		<c:if test="${m.peiheIscheck=='1'}">checked="checked"</c:if>
    			 name="mattersVo.cooperate_unit" 	
    			 id="cooperate_unit"
    			type="checkbox"	 />${m.login_id }
	</li>        
    </c:forEach>
        </ul>
    
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
		<table id="fileUploadTable" style="margin-top:8px;" class="table2" align="center"  >
              <c:forEach var="m" items="${companyfileList}">
                	<tr id="${m.cf_id }"></tr>
                	<td>
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
									
									</td>
  </tr>
  
  
  
  
  
  

  
  
  
  <tr>
    <th>备注:</th>
    <td> ${mattersVo.breaks } </td>
  </tr>

</table>
		</form>
				<div align="center">
		<input type="button" onclick="javascript:history.back(1);" value="" style="background:url(img/t_back.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; "/>
		</div>
  </div>
  	</div>
	</body>
</html>
