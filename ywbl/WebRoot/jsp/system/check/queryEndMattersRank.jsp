<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<meta charset="utf-8">
 <%@ include file="/jsp/common/commonlayUi.jsp" %>
<%-- <% --%>
<%-- %> --%>
<%-- <link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />   --%>
<%--   <link href="<%=path %>/css/zj-skin.css" rel="stylesheet" type="text/css" />   --%>

 
	<script>
 		
	 
 		
		function MattersAudit(str){
			 layer.confirm('是否确认审核?', {
			        btn: ['确定', '取消']
			    }, function (index, layero) {
// 			    	alert("str="+str);
					$.ajax({
				        type: "post",
				    	data : $(".layui-form").serialize(),  
				        url: "matters!AuditMatters.action?str="+str,
				        success: function(data){
				        	   //无法关闭这个消息框
				            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
				            fnIntoView();
// 				            layer.msg("删除成功!",{ icon: 1, time: 1000 });
// 				            location.reload();
				        }
				    });
			    }
			        );
			 
			 
			 
			 
			
		}
	
 
			
			
 
			
			
			
			
			 
 
		 
			
			

			function queryAllFeedBack(){
				
			 	 layer.open({
			  		  type: 2,
			  		  title:'',
			  			setOpaqueRate:'1',
			  		  area: ['1000px', '700px'],
			  		  fixed: true, //不固定
			  		  maxmin: true,
			  		  content: 'feedback!queryAllFeedBack.action?mattersid='+'${mattersVo.id }'
			  		}); 
			}



			
			
			
			
			new Function($('.run').text())()
			var index = parent.layer.getFrameIndex(window.name); 
			//给父页面传值

			 function fnIntoView(){
// 		 		alert("22222");
			   	parent.onTest(1); 
// 		 	   	alert("xxxx");
				parent.layer.tips('Look here', '#parentIframe', {time: 5000}); 
// 		 		alert("111");
			    parent.layer.close(index);
			}
			
			
			
			
			
			
			
			
			function  onmousedown_left(){
			       location.reload();
			}
		  	function search(){
				$('#form1').attr("action","check!queryEndMattersRank.action");
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
 
  <input	 value=""  name="ids"  type="hidden"   id="ids"/>
  	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>办结率排名</legend>
</fieldset> 
   	  <form class="layui-form" 	id="form1" >
  <input	 value=""  name="ids"  type="hidden"   id="ids"/>
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
			 
   <input type="text" name="worktime" id="date2" lay-verify="date" 
            readonly="readonly"		value="${worktime}"
        placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
      </div>
    </div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

<!--      <button type="button"	onclick="ExcelReport()"	 class="layui-btn layui-btn-normal">导出</button> -->
 
  </div>
  
  <div align="right">
  	     <button type="button"	onclick="search()"	 class="layui-btn layui-btn-normal">
          <i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   查询</button>
   <button type="button"	onclick="clearFrom('form1')" class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe669;</i>   
    重置</button>
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
    <th>承包事项总数</th>
    <th>当前应结事项数</th>
    <th>当前已结项数</th>
    <th>办结率</th>
  </tr>
  </thead>
   <tbody>
   <c:forEach items="${list}" var="m">
   <tr  align="center">
   	<td>
   	
<%--    	${m.rankcount} --%>
   	   	<span class="layui-badge"  style="font-size:18px">${m.rankcount}</span>
   	</td>
   	<td>${m.user_name}</td>
   	<td>${m.count1}</td>
   	<td>${m.count2}</td>
   	<td>${m.count3}</td>
 
   	<td>
 
   	<c:if test="${m.count3!='0' }">
   	
   	
   	
   	<fmt:formatNumber type="number" value="${(m.count3/m.count1)*100}" pattern="#.00"/>%
   	
   	
   	</c:if>
   	<c:if test="${m.count3=='0' }">
   	 0.0%
   	</c:if>
   	
   	</td>
   	
   	
<%--    	<td>${m.count4}</td> --%>
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
		  
	   	  //年选择器
	   	  laydate.render({
	   	    elem: '#test2'
	   	    ,type: 'year'
	   	  });
	 
		});

	 </script>
</html>
