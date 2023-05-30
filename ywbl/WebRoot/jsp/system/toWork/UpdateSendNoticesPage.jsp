<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
		<title>建筑市场监管信息平台</title> 
		 <%@ include file="/jsp/common/commonlayUi.jsp" %>

	<script>
			function  doSub(state){
 				
				if(state=='01'){
					$.ajax({
		 					type:'post',//可选get
		 					url:'toWork!SaveSendNotice.action?state='+state,
		 				 	data : $(".layui-form").serialize(),
		 					success:function(data){
		 						 	alert("暂存成功！");
// 		 							location.replace("toWork!querySendNotices.action");


		 						 	closeWindows();

		 					},
		 					error:function(){
		 						$.alert('保存失败！');
		 					}
		 				});
					
				}else{
				 
					 layer.confirm('是否确认发布?', {
		 			        btn: ['确定', '取消']
		 			    }, function (index, layero) {
		 			 
		 		 				$.ajax({
		 		 					type:'post',//可选get
		 		 					url:'toWork!SaveSendNotice.action?state='+state,
		 		 				 	data : $(".layui-form").serialize(),
		 		 					success:function(data){
		 		 						 	alert("发送成功！");
// 		 		 							location.replace("toWork!querySendNotices.action");
		 		 						 	closeWindows();
		 		 					},
		 		 					error:function(){
		 		 						$.alert('保存失败！');
		 		 					}
		 		 				});
		 			    }
		 			        );
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
							" "+
							" "+
							"<input type='hidden' name='saomiaodate' value='"+mytime+"'/></br>"+
							"<a href=http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/"+this.name+" target='_bank' class='input1' >附件</a><input type='hidden' value='"+this.name+"' name='fileName'/>"+
							"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><input type='hidden' name='type' value='notice'/><td><input  type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()' style='width:50px;'/> ");
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
	  <input type="hidden" name="noticeVo.id" value="${noticeVo.id }"/>
	  <input type="hidden" name="noticeVo.fid" value="${noticeVo.fid }"/>
	  <input type="hidden" name="noticeVo.sendtime" value="${noticeVo.sendtime }"/>
 
<!--table-->		
<table	  class="layui-table"  align="left">
  <tr>
    <th><label class="layui-form-label">标题:</label></th>
    <td>  <div class="layui-input-block"   style="margin-left: 0px">
      <textarea placeholder="请输入内容" class="layui-textarea"	name="noticeVo.titlecontent"	>${noticeVo.titlecontent}</textarea>
    </div></td>
  </tr>
  <tr>
    <th  style="width: 20%"><label class="layui-form-label">附件 </label></th>
    <td colspan="3">

    <button type="button" 	onclick="addFileUpload()"	class="layui-btn layui-btn-normal">添加文件</button>

		<table id="fileUploadTable" style="margin-top:8px;" class="table2" align="center"  >
              <c:forEach var="m" items="${companyfileList}">
                	<tr id="${m.cf_id }"></tr>
           
                	 
                	<tr id="${m.cf_id }">
					<td colspan='3'>
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
							附件
 					</a>
					</td>	
					     	<td>
 					 <input type="button" value="删除" onclick="delFile('${m.cf_id }',this);" style="width:50px;"/>
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
    <td>  <div class="layui-input-block"   style="margin-left: 0px">
      <textarea placeholder="请输入内容" class="layui-textarea"	name="noticeVo.noctioncontent"	>${noticeVo.noctioncontent}</textarea>
    </div></td>
  </tr>

</table>
		</form>
				<div align="center">
				    <button type="button" 	onclick="doSub('02')"	class="layui-btn layui-btn-normal">发布</button>
				    <button type="button" 	onclick="doSub('01')"	class="layui-btn layui-btn-normal">暂存</button>
<!-- 				    <button type="button" onclick="javascript:history.back(1);"  class="layui-btn layui-btn-normal">返回</button> -->
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
