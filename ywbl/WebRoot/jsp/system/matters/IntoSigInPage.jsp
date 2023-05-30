<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
		<title>建筑市场监管信息平台</title> 
		 <%@ include file="/jsp/common/commonlayUi.jsp" %>

	<script>
			function  save(){
 				 layer.confirm('是否确认签收?', {
 			        btn: ['确定', '取消']
 			    }, function (index, layero) {
 		 				$.ajax({
 		 					type:'post',//可选get
 		 					url:'matters!SaveSigIn.action',
 		 				 	data : $(".layui-form").serialize(),
 		 					success:function(data){
 		 						 	alert("签收成功！");
 		 						 	closeWindows();
 		 					},
 		 					error:function(){
 		 						$.alert('保存失败！');
 		 					}
 		 				});
 			    }
 			        );
  			
 		 
 		
			
			}
			new Function($('.run').text())()
			var index = parent.layer.getFrameIndex(window.name); 
			function closeWindows(){
				//给父页面传值
				   	parent.refreshViewTop(1); 
					parent.layer.tips('Look here', '#parentIframe', {time: 5000}); 
				    parent.layer.close(index);
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
<div align="right"	>

 
    <button type="button"	onclick="closeWindows()"	 class="layui-btn layui-btn-normal">关闭</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
</div>
	    	  <form class="layui-form"  >
	  <input id="state" type="hidden" name="siginTableVo.matters_id" value="${siginTableVo.matters_id}"/>
	  <input id="state" type="hidden" name="siginTableVo.id" value="${siginTableVo.id}"/>
<!--table-->		
<table	  class="layui-table"  align="left">
  <tr>
    <th><label class="layui-form-label">承办人:</label></th>
    <td><input type="text" value="${username}"	readonly="readonly"	 name="siginTableVo.cb_person" class="layui-input" /></td>
  </tr>
   
  
  
   
  <tr>
    <th><label class="layui-form-label">承办所属处室:</label></th>
    <td><input type="text" value="${memo}" name="siginTableVo.cb_unit" class="layui-input" /></td>
  </tr>
   
  <tr>
    <th><label class="layui-form-label">承办人联系方式:</label></th>
    <td><input type="text" value="${phone}" name="siginTableVo.cb_tel" class="layui-input" /></td>
  </tr>
   
   
   
   
     <tr>
    <th><label class="layui-form-label">签收人:</label></th>
    <td><input type="text" value="${username}"	readonly="readonly"	 name="siginTableVo.sigin_person" class="layui-input" /></td>
  </tr>
   
  
  
   
  <tr>
    <th><label class="layui-form-label">签收处室:</label></th>
    <td><input type="text" value="${memo}" name="siginTableVo.sigin_unit" class="layui-input" /></td>
  </tr>
   
  <tr>
    <th><label class="layui-form-label">签收人联系电话:</label></th>
    <td><input type="text" value="${phone}" name="siginTableVo.sigin_tel" class="layui-input" /></td>
  </tr>
   
   
   

</table>
		</form>
				<div align="center">
				    <button type="button" 	onclick="save()"	class="layui-btn layui-btn-normal">保存</button>
		</div>
 
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
