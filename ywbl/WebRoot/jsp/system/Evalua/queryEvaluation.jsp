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
 
function  onEvaula(id){
	 layer.open({
 		  type: 2,
 		  title:'选择事项扣分',
 		 	setOpaqueRate:'1',
 		  area: ['80%', '80%'],
 		  fixed: true, //不固定
 		  maxmin: true,
 		  content: 'Evalua!queryUserMattersList.action?id='+id
//  		  content: 'feedback!queryUserFeedbackMattersById.action'
 		}); 
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




<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>当前位置： 评价打分</legend>
</fieldset>
     <div  align="right">
 
         <button type="button"	onclick="chaxun()"   class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   
    查询</button>
 
 </div>
    <form action="" method="post"   name="form1" 	 	id="form1" 	class="prjcheckinfoform"	 >
      	<input value="${endcontent}"   name="endcontent"  id="endcontent"	 type="hidden"/>
  	<input value="${builyear}"   name="builyear"  type="hidden"/>
      
  	<input value="${str}"   name="str"  type="hidden"/>
      
 			
 			             <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"> 处室名称：</label>
      <div class="layui-input-inline">
    <input name="username" id="username" 
            value="${ username }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
      
    </div>
  </div>
 			
 			
           
    </form>


<form aciont="prjcheckinfo!queryPrjcheckinfo.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table     class="layui-table" >
            <thead>
              <tr>
                <th width="10%"	><img src="img/th-img.png"/>序号</th>
                <th><img src="img/th-img.png"/>处室名称</th>
                <th><img src="img/th-img.png"/>分数</th>
                <th width="15%"><img src="img/th-img.png"/>操作</th>
              </tr>
            </thead>
            <tbody align="center">
            	<c:forEach items="${list}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>  >  
	                 <td   >
	                 ${stauts.count}
					 </td>
	                 <td   >
	                 ${m.user_name}
					 </td>
	                 <td   >
	                 ${m.score}
					 </td>
	  
	                 <td   >
	                     <button type="button"	onclick="onEvaula('${m.id}')"	 class="layui-btn layui-btn-normal">
	                         	<i class="layui-icon"  style="font-size: 18px">&#xe62a;</i>   
	                     打分</button>
	   				 
					 </td>
	              </tr>
				</c:forEach>
            </tbody>
          </table>
    </form>
          <!-------------显示页数开始------------>
			<DIV ID=TableTail>
				<DIV ID=PageSelectorBar>
					<jsp:include page="/jsp/page/page.jsp" />
				</DIV>
			</DIV> 
		<!-------------显示页数结束------------>
     </div>
     
    
     
  </div>
</body>
<script>
 

</script>
</html>
