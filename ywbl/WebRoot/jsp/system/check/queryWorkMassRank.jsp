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
 		
  	function ExcelReport(){
		$('#form1').attr("action","check!ExcelWorkMassRank.action");
		$('#form1').submit();
  	}
  	
 		
  	function search(){
		$('#form1').attr("action","check!queryWorkMassRank.action");
		$('#form1').submit();
  	}
  	function  QueryAllFeedback(id){
  		
  		
   
   
  		
  		
  		
  		
  		 layer.open({
  	 		  type: 2,
  	 			setOpaqueRate:'1',
  	 		  area: ['80%', '80%'],
  			  fixed: true, 
  	   		  maxmin: true,
  	 		  content: 'feedback!QueryAllFeedbackById.action?id='+id+"&worktime="+$("#date1").val()
  	 		}); 
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
  	  <form class="layui-form" 	id="form1" >
  <input	 value=""  name="ids"  type="hidden"   id="ids"/>
  	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>工作质量排名</legend>
</fieldset> 
	  
	  
	
	   <div class="layui-form-item">
       <input value="0"  type="radio"  name="timeType"		  title="按周"		lay-filter="ontime"	<c:if test="${timeType=='0'}">checked</c:if>    checked="checked" />
      <input value="1"  type="radio"	  name="timeType"	  title="按年" 		lay-filter="ontime"<c:if test="${timeType=='1'}">checked</c:if> />
    <div class="layui-inline">

      <div class="layui-input-inline"  >
      
      
      <c:if test="${timeType=='0'}">
        <input type="text" name="worktime" id="date1" lay-verify="date" 
            readonly="readonly"		value="${worktime}"
        placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
      </c:if>
 
<%--               <c:if test="${timeType=='1'}"> --%>
                <div  id="YearTime" >

<!--                     <select name="yearValue" lay-filter="yearValue"  id="yearValue"     > -->
<!--         <option value=""></option> -->
<%--         <option value="2022"		<c:if test="${yearValue=='2022'}">selected</c:if>  >2022</option> --%>
<%--         <option value="2023"	<c:if test="${yearValue=='2023'}">selected</c:if>>2023</option> --%>
<!--       </select> -->
      
      
       
        <input type="text" class="layui-input"	 value="${yearValue}"	name="yearValue"
        style="width:100px"	readonly="readonly"	 id="test2" placeholder="yyyy">
    
      
      
      
        
                </div>
<%--         </c:if> --%>
      </div>
    </div>
    
    
    
     <button type="button"	onclick="search()"	 class="layui-btn layui-btn-normal">
     <i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   
     查询</button>
     <button type="button"	onclick="ExcelReport()"	 class="layui-btn layui-btn-normal">
     <i class="layui-icon"  style="font-size: 18px">&#xe67d;</i>   
     导出</button>
 
  </div>
  
	 </form> 
	  <form class="layui-form"  >
 
<!--table-->		
 
	  
  <div class="layui-form"  align="left" >
  <table class="layui-table"  align="left" >
  <thead>
  <tr align="left"	>
    <th>名次</th>
    <th>承办单位</th>
    <th>分数</th>
    <th>反馈次数</th>
    		<c:if test="${timeType=='0'}">
    <th>操作</th>
        </c:if>
  </tr>
  </thead>
   <tbody>
   <c:forEach items="${list}" var="m">
   <tr  align="center">
   	<td>   	<span class="layui-badge"  style="font-size:18px">${m.rankcount}</span></td>
   	<td>${m.user_name}</td>
   	<td>${m.score}</td>
   	<td>${m.feedcount}</td>

   		<c:if test="${timeType=='0'}">
   	<td><a  onclick="QueryAllFeedback('${m.userid }')"  
   		class="layui-btn layui-btn-normal"  >
				 <span class="iconfont"  style="margin-left:5px">&#xe613;</span>&nbsp; 
	            	   查看 </a> 	</td>
   	   	</c:if>
   </tr>
   </c:forEach>
   </tbody>

</table>
	</div>
		</form>
  </div>
  	</div>
  	
	</body>
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
 	 <script>
	 
	 if('${timeType}'=='0'){
		   $("#YearTime").hide();
	 }
	 
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
		  
		  
	         form.on('radio(ontime)', function (data) {        
	     		$('#form1').attr("action","check!queryWorkMassRank.action");
	    		$('#form1').submit();
	         });
		  
		  
	   	  //年选择器
	   	  laydate.render({
	   	    elem: '#test2'
	   	    ,type: 'year'
	   	  });
		  
		  
		  
		  
		});

	 </script>
</html>
