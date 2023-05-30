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
 		
	  function search(){
			$('#form1').attr("action","check!queryCheckStatistics.action");
			$('#form1').submit();
		  
	  }
 		
	  function ExcelReport(){
			$('#form1').attr("action","check!ExcelReport.action");
			$('#form1').submit();
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
  	  <form class="layui-form"   id="form1"	>
  <input	 value=""  name="ids"  type="hidden"   id="ids"/>
  	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>考核统计</legend>
</fieldset> 

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">查询日期</label>
      <div class="layui-input-inline">
        <input type="text" name="startdate" id="date" lay-verify="date" 
        readonly="readonly" value="${startdate}"
        placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">至</label>
      <div class="layui-input-inline">
        <input type="text" name="enddate" id="date1" lay-verify="date" 
            readonly="readonly"		value="${enddate}"
        placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">年度</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input"		lay-filter="yearValue"     value="${yearValue}"  	name="yearValue"
        style="width:100px"	readonly="readonly"	 id="test2" placeholder="yyyy">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">季度</label>
      <div class="layui-input-inline">
			<select	name="quarter"		lay-filter="quarter"   id="quarter" >
				<option	>--请选择--</option>
				<option value="1"	<c:if test="${quarter=='1'}">selected</c:if>	>第一季度</option>
				<option value="2"	<c:if test="${quarter=='2'}">selected</c:if>	>第二季度</option>
				<option value="3"	<c:if test="${quarter=='3'}">selected</c:if>	>第三季度</option>
				<option value="4"	<c:if test="${quarter=='4'}">selected</c:if>	>第四季度</option>
			</select>      

      </div>
    </div>
    
    <div class="layui-inline">
      <label class="layui-form-label">按周</label>
      <div class="layui-input-inline">
			 
   <input type="text" name="worktime" id="date3" lay-verify="date" 
            readonly="readonly"		value="${worktime}"
        placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
      </div>
    </div>
    
    

  </div>
  
 
    <div align="right">
     <button type="button"	onclick="search()"	 class="layui-btn layui-btn-normal">     <i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   查询</button>
     <button type="button"	onclick="ExcelReport()"	 class="layui-btn layui-btn-normal">     <i class="layui-icon"  style="font-size: 18px">&#xe67d;</i>   导出</button>
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
    <th>承办事项</th>
    <th>待完成</th>
    <th>已超期</th>
    <th>办结</th>
  </tr>
  </thead>
   <tbody>
   <c:forEach items="${list}" var="m">
   <tr  align="center">
   	<td>
   	<span class="layui-badge"  style="font-size:18px">${m.rankcount}</span>
   	
   	</td>
   	<td>${m.user_name}</td>
   	<td>${m.count1}</td>
   	<td>${m.count2}</td>
   	<td>${m.count3}</td>
   	<td>${m.count4}</td>
   </tr>
   </c:forEach>
   </tbody>

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

	 layui.use(['form', 'layedit', 'laydate'], function(){
		 
		 
		 
		  var form = layui.form
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate;
          form.on('select(quarter)', function (data) { 
 		 	var  year = 	$("#test2").val();  		
          	if(year==null&&data.value!='--请选择--'){
          		alert("请先选择年度！");
          	}
          	return false;
          }); 
          
          
 
		  //日期
		  laydate.render({
		    elem: '#date'
		  });
		  laydate.render({
		    elem: '#date1'
		  });
		  laydate.render({
		    elem: '#date3'
		  });
		  
	   	  //年选择器
	   	  laydate.render({
	   	    elem: '#test2'
	   	    ,type: 'year'
	   	  });
	 
		});
	 </script>
</html>
