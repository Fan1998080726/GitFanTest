<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
		<title>建筑市场监管信息平台</title> 
		 <%@ include file="/jsp/common/commonlayUi.jsp" %>
<%-- <%@ include file="/jsp/common/common.jsp"%> --%>

	<script>
			function  doSub(state){
				
				$("#state").val(state);
 
				var  flag = false;
 			if($("#supervision_matter").val()==''){
 				 layer.msg('请输入监督事项！');
 				flag = false
 				 return false;
 			}
				
 			if($("input[id='source_unit']").is(':checked')!=false){
 				flag = true;
 			}else{
 			      layer.msg('请勾选牵头单位！');
 			      return false;
 			}
 			 
 			if($("#date").val()==''){
 				 layer.msg('请选择完成时限！');
 				flag = false
 				 return false;
 			}
 			if($("#date1").val()==''){
 				flag = false
 				 layer.msg('请选择反馈时限！');
 				 return false;
 			}
 			
 	 
 			if(state=='02'){
 				if(flag){
 				 layer.confirm('是否确认发送?', {
 			        btn: ['确定', '取消']
 			    }, function (index, layero) {
 			    	loading("发送中。。。")
 			    	
 		 				$.ajax({
 		 					type:'post',//可选get
 		 					url:'matters!SaveMattersData.action',
 		 				 	data : $(".layui-form").serialize(),
 		 					success:function(data){
 		 						 	alert("发送成功！");
 		 						 	closeWindows();
//  		 							location.replace("matters!queryMattersAdmin.action");
 		 					},
 		 					error:function(){
 		 						$.alert('保存失败！');
 		 					}
 		 				});
 		 			
 			    }
 			        );
 			    }
 			}else{
 				
 		    	if(flag){
 				$.ajax({
	 					type:'post',//可选get
	 					url:'matters!SaveMattersData.action',
	 				 	data : $(".layui-form").serialize(),
	 					success:function(data){
	 						 	alert("保存成功！");
	 						 	closeWindows();
// 	 							location.replace("matters!queryMattersAdmin.action");
	 					},
	 					error:function(){
	 						$.alert('保存失败！');
	 					}
	 				});
 		    	}
 				
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
							" <tr><td>"+
							"	<input type='hidden' name='saomiaodate' value='"+mytime+"'/></br>"+
							"	<a href=http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/"+this.name+" target='_bank' class='layui-btn layui-btn-normal' >附件</a><input type='hidden' value='"+this.name+"' name='fileName'/>"+
							"	<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><input type='hidden' name='type' value='remark'/><td><input  type='button'   value='删除' 	 	class='layui-btn layui-btn-normal'	  onclick='javascript:$(this).parent().parent().remove()' style='width:50px;'/> </td></tr>");
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
.layui-form-label {
    float: right;
    display: block;
    width:120px;
    font-weight: 200;
    line-height: 20px;
    text-align: right;
    padding: 9px 15px;
}
     </style>
 
		
	</head>
<body  style="background-color: white;" >
<!-- 	<div class="main"> -->
<!--   <div class="breadcrumb">当前位置：事项编辑</div> -->
<!--   <div class="main3"> -->
<!-- 	  <form name="form1" class="registerform"  > -->
	    	  <form class="layui-form"  >
	  <input type="hidden" name="mattersVo.id" value="${mattersVo.id }"/>
	  <input id="state" type="hidden" name="mattersVo.state" value="${mattersVo.state }"/>
	  <input type="hidden" value="${mattersVo.inittime }" name="mattersVo.inittime" class="layui-input" />
	  <input type="hidden" value="${mattersVo.updatetime }" name="mattersVo.updatetime" class="layui-input" />
	  <input type="hidden" value="${mattersVo.matters_id }" name="mattersVo.matters_id"    class="layui-input" />
	  <input type="hidden" value="${mattersVo.fid }" name="mattersVo.fid"    class="layui-input" />
	  <input type="hidden" value="${mattersVo.flag }" name="mattersVo.flag"    class="layui-input" />
	  <input type="hidden" value="${mattersVo.sendtime }" name="mattersVo.sendtime"    class="layui-input" />
<!--table-->		
<table	  class="layui-table"  align="left">

 
  
  
  <tr>
    <th><label class="layui-form-label"> 事项类型: </label></th>
    <td><input type="text" value="${mattersVo.matter_type }" name="mattersVo.matter_type" class="layui-input"  /></td>
  </tr>
    <tr>
    <th><label class="layui-form-label">监督事项:</label></th>
    <td><input type="text" 	id="supervision_matter"	value="${mattersVo.supervision_matter }" name="mattersVo.supervision_matter" class="layui-input" /></td>
  </tr>
    <tr>
    <th><label class="layui-form-label">主要任务:</label></th>
    <td><input type="text" value="${mattersVo.main_task }" name="mattersVo.main_task" class="layui-input" /></td>
  </tr>
  <tr>
    <th><label class="layui-form-label">督办文号:</label></th>
    <td><input type="text" value="${mattersVo.supervision_code }" name="mattersVo.supervision_code" class="layui-input" /></td>
  </tr>
  <tr>
    <th><label class="layui-form-label">事项来源:</label></th>
    <td><input  type="text" value="${mattersVo.matter_source }" name="mattersVo.matter_source" class="layui-input" /></td>
  </tr>
  <tr>
    <th><label class="layui-form-label">分管领导:</label></th>
    <td><input type="text" value="${mattersVo.charge_lead }" name="mattersVo.charge_lead" class="layui-input" /></td>
  </tr>
 
  <tr>
    <th><label class="layui-form-label">抄送领导:</label></th>
    <td><input type="text" value="${mattersVo.chaosong_lead }" name="mattersVo.chaosong_lead" class="layui-input" /></td>
  </tr>
  
  <tr>
    <th><label class="layui-form-label">牵头单位:</label></th>
    <td > 
  <div align="left" > 
  
  
        <c:forEach items="${parmList}"  var="m"  varStatus="status">
 	<input  value="${m.id }" 	 style="height: 80px"
    		<c:if test="${m.qiantouIscheck=='1'}">checked="checked"</c:if>
    		name="mattersVo.source_unit"   id="source_unit"	type="checkbox"	   title="${m.login_id }"  />
		<c:if test="${status.count%5==0}">
		</br>
		</br>
		        </c:if>
    </c:forEach>
    
 </div>
    
    
<!--         <a onclick="fnIsAllCheck('1',this)"  href="#" >全选</a> -->
<!--         <a onclick="fnIsNotCheck('1',this)"  href="#" >取消全选</a> -->
    </td>
  </tr>
  <tr>
    <th><label class="layui-form-label">配合单位:</label></th>
    <td> 
    
      <div align="left" >  
  
  
        <c:forEach items="${parmList}"  var="m"  varStatus="status">
<%--           <c:if test="${m.peiheIscheck=='1' }"> --%>
 	<input  value="${m.id }" 	 style="height: 80px"
    		<c:if test="${m.peiheIscheck=='1'}">checked="checked"</c:if> 
    		name="mattersVo.cooperate_unit"   id="cooperate_unit"	type="checkbox"	   title="${m.login_id }"  />
 
		<c:if test="${status.count%5==0}">
		</br>
		</br>
<%-- 		</c:if> --%>
		</c:if>
    </c:forEach>
    
 </div>
<!--     <a onclick="fnIsAllCheck('2',this)"  href="#" >全选</a> -->
<!--         <a onclick="fnIsNotCheck('2',this)"  href="#" >取消全选</a> -->
    
    </td>
  </tr>
  <tr>
    <th><label class="layui-form-label">完成时限:</label></th>
    <td><input type="text" value="${mattersVo.end_time }"		
  		readonly="readonly"  
     id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" 
     name="mattersVo.end_time" class="layui-input" /></td>
  </tr>
  <tr>
    <th><label class="layui-form-label">反馈时限:</label></th>
    <td><input type="text" value="${mattersVo.fankui_time }" 
         id="date1" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" 
      readonly="readonly"  
    name="mattersVo.fankui_time" class="layui-input" /></td>
  </tr>
  
  <tr>
    <th><label class="layui-form-label">预警时限（不选系统默认预警时间）:</label></th>
    <td><input type="text" value="${mattersVo.warn_time }" 
         id="date2" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" 
      readonly="readonly"  
    name="mattersVo.warn_time" class="layui-input" /></td>
  </tr>
  
  <tr>
    <th><label class="layui-form-label">反馈类型:</label></th>
    <td>

       <input value="1"  type="radio"  name="mattersVo.feed_type"		
         title="自定义反馈"		lay-filter="ontime"	<c:if test="${mattersVo.feed_type=='1'}">checked</c:if>    checked="checked" />
       <input value="2"  type="radio"  name="mattersVo.feed_type"		
         title="按周"		lay-filter="ontime"	<c:if test="${mattersVo.feed_type=='2'}">checked</c:if>    />
       <input value="3"  type="radio"  name="mattersVo.feed_type"		
         title="按月"		lay-filter="ontime"	<c:if test="${mattersVo.feed_type=='3'}">checked</c:if>     />
         
         
         
         


</td>
  </tr>
  
  
  <tr>
    <th><label class="layui-form-label">交接人:</label></th>
    <td><input type="text" value="${mattersVo.handover_person }" name="mattersVo.handover_person" class="layui-input" /></td>
  </tr>
  <tr>
    <th><label class="layui-form-label">交办人联系电话:</label></th>
    <td><input type="text" 	 lay-verify="required|phone" 	value="${mattersVo.handover_tel }" name="mattersVo.handover_tel" class="layui-input" /></td>
  </tr>
  
  
  <tr>
    <th><label class="layui-form-label">附件             <button type="button" 	onclick="addFileUpload()"	class="layui-btn layui-btn-normal">添加文件</button>
    </label></th>
    
    <td colspan="3">
    
    
<!-- <span onclick="addFileUpload()" class="btn btn-small btn-info" style="color:#FFFFFF; font-weight:bold;">添加文件</span> 		 -->


		<table id="fileUploadTable" style="margin-top:8px;" class="table2" align="center"  >
              <c:forEach var="m" items="${companyfileList}">
        
           
                	<tr id="${m.cf_id }">
					<td colspan='3'>
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" 	class="layui-btn layui-btn-normal"	target="_bank">
 				 附件
 					</a>
					</td>		
					     	<td>
 					 <input type="button" value="删除" 	class="layui-btn layui-btn-normal"  onclick="delFile('${m.cf_id }',this);" style="width:50px;"/>
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
    <th><label class="layui-form-label">备注:</label></th>
    <td><input type="text" value="${mattersVo.breaks }" name="mattersVo.breaks" class="layui-input" /></td>
  </tr>

</table>
		</form>
				<div align="center">
				    <button type="button" 	onclick="doSub('01')"	class="layui-btn layui-btn-normal">暂存</button>
				    <button type="button" 	onclick="doSub('02')"	class="layui-btn layui-btn-normal">发送</button>
<!-- 				    <button type="button" onclick="javascript:history.back(1);"  class="layui-btn layui-btn-normal">返回</button> -->
				
<!-- 		<input type="button" onclick="doSub('01')" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; "/> -->
<!-- 		<input type="button" onclick="doSub('02')" value="" style="background:url(img/bj.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; "/> -->
<!-- 		<input type="button" onclick="javascript:history.back(1);" value="" style="background:url(img/t_back.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; "/> -->
		</div>
<!--   </div> -->
<!--   	</div> -->
	</body>
	
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
	 
	 <script>
	 layui.use(['form', 'layedit', 'laydate'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate;
		  
		  //日期
		  laydate.render({
		    elem: '#date'
		  });
		  laydate.render({
		    elem: '#date1'
		  });
		  laydate.render({
		    elem: '#date2'
		  });
		 
		  //自定义验证规则
		  form.verify({
		    title: function(value){
		      if(value.length < 5){
		        return '标题至少得5个字符啊';
		      }
		    }
		    ,pass: [
		      /^[\S]{6,12}$/
		      ,'密码必须6到12位，且不能出现空格'
		    ]
		  });
		  
		  //指定开关事件
		  form.on('switch(switchTest)', function(data){
		    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
		      offset: '6px'
		    });
		    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
		  });
		  
		  //提交事件
		  form.on('submit(demo1)', function(data){
		    layer.alert(JSON.stringify(data.field), {
		      title: '最终的提交信息'
		    })
		    return false;
		  });
		 
		  //表单赋值
		  layui.$('#LAY-component-form-setval').on('click', function(){
		    form.val('example', {
		      "username": "贤心" // "name": "value"
		      ,"password": "123456"
		      ,"interest": 1
		      ,"like[write]": true //复选框选中状态
		      ,"close": true //开关状态
		      ,"sex": "女"
		      ,"desc": "我爱 layui"
		    });
		  });
		  
		  //表单取值
		  layui.$('#LAY-component-form-getval').on('click', function(){
		    var data = form.val('example');
		    alert(JSON.stringify(data));
		  });
		  
		});
	 </script>
	 
	 
</html>
