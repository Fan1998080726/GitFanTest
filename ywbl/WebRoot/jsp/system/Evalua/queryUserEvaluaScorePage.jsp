<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%-- <%@ include file="/jsp/common/common.jsp" %> --%>
 <%@ include file="/jsp/common/commonlayUi.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!-- <link href="<%=request.getContextPath() %>/css/right.css" rel="stylesheet" type="text/css"> -->
<!-- <script src="<%=request.getContextPath() %>/js/color.js" type="text/javascript"></script> -->
		<!--表单验证-->
		

<style type="text/css">
</style>

<script>
 
 
function confimSave(){
	
 	var chks=$("input[id='checkId']:checked");
	 if(chks.length!=0){
			 layer.confirm('是否确认扣分?', {
			        btn: ['确定', '取消']
			    }, function (index, layero) {
			    	var  checkIds ='';
			    	var  scores ='';
			    	$('input[name="checkId"]:checked').each(function(){
						var ss=$(this).val();
						checkIds+=$(this).val()+',';
						scores+=$("#score"+$(this).val()).val()+',';
			    	})
			    	$("#checkIds").val(checkIds);
			    	$("#scores").val(scores);
			    	
			    	
			    	loading("打分中。。。");
					$.ajax({
				        type: "post",
				    	data : $("#layui-form").serialize(),  
				        url: "Evalua!SaveEvualScore.action",
				        success: function(data){
				        	   //无法关闭这个消息框
				            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
				            layer.msg("扣分成功!",{ icon: 1, time: 1000 });
				            alert("打分成功！");
				            closeWindows();
				        }
				    });
			    }
			        );
	 }else{
		 alert("请勾选评价指标！");
	 }
	
}




function chaxun(){
	$('#form1').attr("action","Evalua!queryEvaluation.action");
	$('#form1').submit();
}

function refreshView(){
	
    location.reload();
}
 

</script>
<style type="">
/* a {font-size:16px}  */
 a:link {text-decoration:none;} //未访问：蓝色、无下划线  

</style>
</head>
<body>
<div class="main" 	 style="background-color: white;">
 
 
  <div class="main3">
<form aciont="prjcheckinfo!queryPrjcheckinfo.action" method="post"	 name="layui-form" id="layui-form">
<input name="checkIds"  id="checkIds"   type="hidden"/>
 
<input name="userid"  id="userid" value="${userid }"   type="hidden"/>
<input name="mid"  id="mid" value="${mid }"   type="hidden"/>



        <div class="da-panel-content">
          <table     class="layui-table" >
            <thead>
              <tr>
                <th><img src="img/th-img.png"/>选择</th>
                <th><img src="img/th-img.png"/>序号</th>
                <th width="70%"><img src="img/th-img.png"/>评价指标</th>
                <th><img src="img/th-img.png"/>分数</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach  items="${standList }"	var="m"	 varStatus="status">
                 <tr  >  
	                 <td   >
	         		<input	type="checkbox" 	value="${m.id}"		name="checkId"		id="checkId"	/>
					 </td>
	                 <td   >
	               ${status.count}
					 </td>
	                 <td   >
	                 ${m.zbname}
					 </td>
	  
	                 <td   >
	                 <input  id="score${m.id}" name="score${m.id}"  value="${m.score }"	type="hidden" /> 
	   				       ${m.score }
					 </td>
	              </tr>
            </c:forEach>
 	         
 	         
	              
	              
	              
            </tbody>
            
          </table>
          <div  align="center"	>
          	    <button type="button"   onclick="confimSave()"  data-refresh="刷新"	class="layui-btn layui-btn-normal">扣分</button>
          	
          </div>
    </form>
          <!-------------显示页数开始------------>
 
		<!-------------显示页数结束------------>
     </div>
     
    
     
  </div>
</body>

<script>
 

</script>
</html>
