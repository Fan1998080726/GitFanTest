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
		<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
		

<style type="text/css">
.tfgdiv{
   width:400px;

   height:280px;
   
   margin-left:30%;
   
   margin-top:5%;

   border:	#F0F0F0 1px solid;

   background:#fff;

   color:#333;

   filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);

   -moz-box-shadow: 2px 2px 10px #909090;

   -webkit-box-shadow: 2px 2px 10px #909090;

   box-shadow:2px 2px 10px #909090;
}
</style>
<script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/demo.tables.js"></script>

<script>
 
function  ReturnDataById(id,cid,cstate){
	 layer.open({
 		  type: 2,
 		  title:'事项详情',
 			setOpaqueRate:'1',
 		  area: ['1200px', '800px'],
 		  fixed: true, //不固定
 		  maxmin: true,
 		  content: 'feedback!queryUserFeedbackMattersById.action?id='+id+"&cid="+cid+"&cstate="+cstate
 		}); 
}

function chaxun(){
	$('#form1').attr("action","matters!queryAllMattersData.action");
	$('#form1').submit();
}

function ExcelMatters(){
	$('#form1').attr("action","matters!ExcelMatters.action");
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
 
<!--  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;"> -->
<!--   <legend>当前位置： 综合查询</legend> -->
<!-- </fieldset> -->


            <form action="" method="post" name="form1" 	  class="layui-form"  	id="form1">
<!--     <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;width: 100%"> -->
<!--   <li class="layui-nav-item"  style="background-color: #c7c7c7" 	> -->
<!--     <a href="javascript:;"></a> -->
<!-- <!--     <dl class="layui-nav-child"> --> 
    
    
    
<!--       <div class="layui-form-item layui-nav-child" style="white-space:inherit;background-color:#ffffff;"> -->
<!--     <div class="layui-inline" > -->
<!--       <label class="layui-form-label"> 监督事项：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="supervision_matter" id="supervision_matter"  -->
<%--             value="${ supervision_matter }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
      
<!--     </div> -->
    
    
    
    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label"> 主要任务 ：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="main_task" id="main_task"  -->
<%--             value="${ main_task }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
      
<!--     </div> -->
    
    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label"> 事项来源：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="matter_source" id="matter_source"  -->
<%--             value="${ matter_source }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label"> 办理状态：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--                   <select 	name="state"	> -->
<%--             		<option value=""	 	<c:if test="${state==''}">selected</c:if>>全部</option> --%>
<%--             		<option	value="05"	<c:if test="${state=='05'}">selected</c:if>>办结</option> --%>
<%--             		<option value="01" <c:if test="${state=='01'}">selected</c:if>>正常</option> --%>
<%--             		<option value="02"	<c:if test="${state=='02'}">selected</c:if>>预警</option> --%>
<%--             		<option value="03" <c:if test="${state=='03'}">selected</c:if>>超期</option> --%>
<!--             </select> -->
 
<!--       </div> -->
<!--     </div> -->
     
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">牵头单位：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="source_unit" id="source_unit"  -->
<%--             value="${ source_unit }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
    

    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">配合单位：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="cooperate_unit" id="cooperate_unit"  -->
<%--             value="${ cooperate_unit }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">分管领导：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="charge_lead" id="charge_lead"  -->
<%--             value="${ charge_lead }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">批示领导：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="chaosong_lead" id="chaosong_lead"  -->
<%--             value="${ chaosong_lead }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
     
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">交办人：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="handover_person" id="handover_person"  -->
<%--             value="${ handover_person }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">督办文号：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="supervision_code" id="supervision_code"  -->
<%--             value="${ supervision_code }" type="text"  class="layui-input" placeholder="请输入文字..."> --%>
<!--       </div> -->
<!--     </div> -->
    
    
    
    
    
<!--            <div class="layui-inline"> -->
<!--       <label class="layui-form-label">交办时间：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="sendtime1" id="date"    readonly="readonly" -->
<%--             value="${ sendtime1 }" type="text"  class="layui-input" placeholder="请选择时间..."> --%>
<!--       </div> -->
<!--     </div> -->
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">至：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="sendtime2" id="date1"    readonly="readonly" -->
<%--             value="${ sendtime2 }" type="text"  class="layui-input" placeholder="请选择时间..."> --%>
<!--       </div> -->
<!--     </div> -->
    
    
    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">完成时限：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="end_time1" id="date2"  -->
<%--             value="${ end_time1 }" type="text"  class="layui-input" placeholder="请选择时间..."> --%>
<!--       </div> -->
<!--     </div> -->
    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">至：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="end_time2" id="date3"  -->
<%--             value="${ end_time2 }" type="text"  class="layui-input" placeholder="请选择时间..."> --%>
<!--       </div> -->
<!--     </div> -->
 
    
<!--        </br> -->
    
    
    
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">办结时间：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="banjie1" id="date4"  -->
<%--             value="${ banjie1 }" type="text"  class="layui-input" placeholder="请选择时间..."> --%>
<!--       </div> -->
<!--     </div> -->
<!--        <div class="layui-inline"> -->
<!--       <label class="layui-form-label">至：</label> -->
<!--       <div class="layui-input-inline"> -->
<!--     <input name="banjie2" id="date5"  -->
<%--             value="${banjie2}" type="text"  class="layui-input" placeholder="请选择时间..."> --%>
<!--       </div> -->
<!--     </div> -->

    
<!--   </div> -->
<!--   </li> -->
 
<!-- </ul> -->
   <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"> 监督事项：</label>
      <div class="layui-input-inline">
    <input name="supervision_matter" id="supervision_matter" 
            value="${ supervision_matter }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
       <div class="layui-inline">
      <label class="layui-form-label"> 主要任务 ：</label>
      <div class="layui-input-inline">
    <input name="main_task" id="main_task" 
            value="${ main_task }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
     </div>
       <div class="layui-inline">
      <label class="layui-form-label"> 事项来源：</label>
      <div class="layui-input-inline">
    <input name="matter_source" id="matter_source" 
            value="${ matter_source }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
    
       <div class="layui-inline">
      <label class="layui-form-label"> 办理状态：</label>
      <div class="layui-input-inline">
                  <select 	name="state"	>
            		<option value=""	 	<c:if test="${state==''}">selected</c:if>>全部</option>
            		<option	value="05"	<c:if test="${state=='05'}">selected</c:if>>办结</option>
            		<option value="01" <c:if test="${state=='01'}">selected</c:if>>正常</option>
            		<option value="02"	<c:if test="${state=='02'}">selected</c:if>>预警</option>
            		<option value="03" <c:if test="${state=='03'}">selected</c:if>>超期</option>
            </select>
 
      </div>
    </div>
     
       <div class="layui-inline">
      <label class="layui-form-label">牵头单位：</label>
      <div class="layui-input-inline">
    <input name="source_unit" id="source_unit" 
            value="${ source_unit }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
    

    
       <div class="layui-inline">
      <label class="layui-form-label">配合单位：</label>
      <div class="layui-input-inline">
    <input name="cooperate_unit" id="cooperate_unit" 
            value="${ cooperate_unit }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
       <div class="layui-inline">
      <label class="layui-form-label">分管领导：</label>
      <div class="layui-input-inline">
    <input name="charge_lead" id="charge_lead" 
            value="${ charge_lead }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
       <div class="layui-inline">
      <label class="layui-form-label">抄送领导：</label>
      <div class="layui-input-inline">
    <input name="chaosong_lead" id="chaosong_lead" 
            value="${chaosong_lead}" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
     
       <div class="layui-inline">
      <label class="layui-form-label">交办人：</label>
      <div class="layui-input-inline">
    <input name="handover_person" id="handover_person" 
            value="${ handover_person }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
       <div class="layui-inline">
      <label class="layui-form-label">督办文号：</label>
      <div class="layui-input-inline">
    <input name="supervision_code" id="supervision_code" 
            value="${ supervision_code }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
    </div>
    
    
    
    
    
           <div class="layui-inline">
      <label class="layui-form-label">交办时间：</label>
      <div class="layui-input-inline">
    <input name="sendtime1" id="date"    readonly="readonly"
            value="${ sendtime1 }" type="text"  class="layui-input" placeholder="请选择时间...">
      </div>
    </div>
       <div class="layui-inline">
      <label class="layui-form-label">至：</label>
      <div class="layui-input-inline">
    <input name="sendtime2" id="date1"    readonly="readonly"
            value="${ sendtime2 }" type="text"  class="layui-input" placeholder="请选择时间...">
      </div>
    </div>
    
    
    
       <div class="layui-inline">
      <label class="layui-form-label">完成时限：</label>
      <div class="layui-input-inline">
    <input name="end_time1" id="date2" 
            value="${ end_time1 }" type="text" 	 readonly="readonly"   class="layui-input" placeholder="请选择时间...">
      </div>
    </div>
    
       <div class="layui-inline">
      <label class="layui-form-label">至：</label>
      <div class="layui-input-inline">
    <input name="end_time2" id="date3" 
            value="${ end_time2 }" type="text"	 readonly="readonly"  class="layui-input" placeholder="请选择时间...">
      </div>
    </div>
 
       </br>
    
    
    
       <div class="layui-inline">
      <label class="layui-form-label">办结时间：</label>
      <div class="layui-input-inline">
    <input name="banjie1" id="date4" 
            value="${ banjie1 }" type="text" 	 readonly="readonly"  class="layui-input" placeholder="请选择时间...">
      </div>
    </div>
       <div class="layui-inline">
      <label class="layui-form-label">至：</label>
      <div class="layui-input-inline">
    <input name="banjie2" id="date5" 
            value="${banjie2}" type="text" 	 readonly="readonly"  class="layui-input" placeholder="请选择时间...">
      </div>
    </div>
       </br>
          <div class="layui-inline">
      <label class="layui-form-label">年度</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input"	
        	lay-filter="yearValue"     value="${yearValue}"  	name="yearValue"
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
			 
   <input type="text" name="worktime" id="date6" lay-verify="date" 
            readonly="readonly"		value="${worktime}"
        placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
      </div>
    </div>
    
  </div>
  
  
             <div  align="right">
 

         <button type="button"	onclick="chaxun()"   class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   
    查询</button>
         <button type="button"	onclick="ExcelMatters()"	 class="layui-btn layui-btn-normal">    
          <i class="layui-icon"  style="font-size: 18px">&#xe67d;</i>   导出</button>
    
          <button type="button"	onclick="clearFrom('form1')" class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe669;</i>   
    重置</button>
 </div>
  	<input value="${endcontent}"   name="endcontent"  id="endcontent"	 type="hidden"/>
  	<input value="${builyear}"   name="builyear"  type="hidden"/>
    </form>


<form aciont="prjcheckinfo!queryPrjcheckinfo.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table     class="layui-table" >
            <thead>
              <tr>
                <th  width="4%">序号</th>
	                <th>事项类型</th>
	                <th>监督事项</th>
	                <th>事项来源</th>
	                <th>完成时限</th>
	                <th>分管市领导</th>
	                <th	width="10%">牵头单位</th>
	                <th>进展情况</th>
	                
	                
	                <th>交办人</th>
                <th width="5%"> 操作</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>  >  
	                 <td   >
	                 ${stauts.count}
					 </td>
	                 <td   >
	                 ${m.matter_type }
					 </td>
	                 <td   >
	                 ${m.supervision_matter}
					 </td>
	                 <td   >
	                 ${m.matter_source}
					 </td>
	                 <td   >
	                 ${m.end_time}
					 </td>
	                 <td   >
	                 ${m.charge_lead}
					 </td>
					 
					 
	                 <td   >
	                 ${m.source_unit}
					 </td>
	                 <td   >
	                 ${m.feedBackContent}
					 </td>
					 
					 
	                 <td   >
	                 ${m.handover_person}
					 </td>
	                 <td   >
	    
							 <a  href="#" onclick="ReturnDataById('${m.id}','${m.cid}','${m.cstate}')" 	class="layui-btn layui-btn-normal" >
				 <span class="iconfont"  style="margin-left:5px">&#xe613;</span>&nbsp;&nbsp;查看</a> 
				
				
			 
				
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
	  laydate.render({
	    elem: '#date3'
	  });
	  laydate.render({
	    elem: '#date4'
	  });
	  laydate.render({
	    elem: '#date5'
	  });
	  laydate.render({
	    elem: '#date6'
	  });
   	  //年选择器
   	  laydate.render({
   	    elem: '#test2'
   	    ,type: 'year'
   	  });
	  
	  
//         form.on('radio(ontime)', function (data) {        
//     		$('#form1').attr("action","check!queryWorkMassRank.action");
//    		$('#form1').submit();
//         });
	  
	  
	  
	  
	  
	  
	  
	});
 </script>
</html>
