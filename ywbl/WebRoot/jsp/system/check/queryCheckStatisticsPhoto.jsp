<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
 <%@ include file="/jsp/common/commonlayUi.jsp" %>
	<script> 
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

<script>


</script>
 
<body  style="background-color: white;" >
	<div >
  <div>
  	  <form class="layui-form"  >
  <input	 value=""  name="ids"  type="hidden"   id="ids"/>
  	  <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>考核统计图 </legend>
</fieldset> 
 	   <div class="layui-form-item">
 <div   class="layui-inline">
    <label class="layui-form-label">年份</label>
    <div class="layui-input-block">
    
<!--             <input type="text" class="layui-input" id="test2" placeholder="yyyy"> -->
    
      <select name="khYear" lay-filter="khYear"  id="khYear"  >
        <option value=""></option>
        <option value="2022"		<c:if test="${year=='2022'}">selected</c:if>  >2022</option>
        <option value="2023"	<c:if test="${year=='2023'}">selected</c:if>>2023</option>
        <option value="2024"	<c:if test="${year=='2024'}">selected</c:if>>2024</option>
        <option value="2025"	<c:if test="${year=='2025'}">selected</c:if>>2025</option>
      </select>
    </div>
  </div>
  
 <div  class="layui-inline">
    <label class="layui-form-label">部门类型</label>
    <div class="layui-input-block">
      <select name="deptState" 		lay-filter="deptState"   id="deptState" >
        <option value="0"		<c:if test="${deptState=='0'}">selected</c:if>	  >处室</option>
        <option value="1"		<c:if test="${deptState=='1'}">selected</c:if>		>直属单位</option>
      </select>
    </div>
  </div>
  
  
   <div  class="layui-inline">
    <label class="layui-form-label">部门 </label>
    <div class="layui-input-block">
<!--       <select name="deptUsername" 		lay-filter="deptUsername"   id="deptUsername" > -->
         <select name="modules"  	lay-filter="modules"   id="modules"	lay-verify="modules" 	lay-search=""	>
                   <option value="">直接选择或搜索选择</option>
			<c:forEach items="${userlist}"  var="m">
					<option  value="${m.id }"	<c:if test="${modules==m.id}">selected</c:if> >${m.user_name}</option>
			</c:forEach>
      </select>
    </div>
  </div>
  
  
  
  
  
 </div>
  
     
  
 
	     
<!--  <div class="layui-collapse"> -->
<!--    <div class="layui-colla-item"> -->
<!--      <h2    id="openid" class="layui-colla-title"    onclick="openfun('1')"     >隐藏</h2> -->
    <div  align="left" id="main" style="width:180%;height:400px;float: left;"  
 > 
<!--                 <div></div> -->
<!--     </div> -->
<!--    </div> -->
 </div>
	 </form> 
 
 
  </div>
  	</div>
  	
	</body>
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 

	 <script>
	 
	    layui.use(['form', 'layedit', 'laydate'], function () { 
            var form = layui.form; 
            form.on('select(khYear)', function (data) { 
         		console.log("data==="+data.value);
 	        	   window.location.href="check!queryCheckStatisticsPhoto.action?year="+data.value+"&deptState="+$("#deptState").val();
          		
            }); 
            form.on('select(deptState)', function (data) { 
            	window.location.href="check!queryCheckStatisticsPhoto.action?deptState="+data.value+"&year="+$("#khYear").val();
          		
            }); 
            
            form.on('select(modules)', function (data) { 
            	window.location.href="check!queryMonthDeptCheckStatisticsPhoto.action?modules="+data.value+"&year="+$("#khYear").val();
            }); 
            
        }); 
 
	 
	 </script>
	 <script>


	 
	 

	 
	 
	 
	 var app = {};
	 
	 var chartDom = document.getElementById('main');
	 var myChart = echarts.init(chartDom);
	 var option;

	 const posList = [
	   'left',
	   'right',
	   'top',
	   'bottom',
	   'inside',
	   'insideTop',
	   'insideLeft',
	   'insideRight',
	   'insideBottom',
	   'insideTopLeft',
	   'insideTopRight',
	   'insideBottomLeft',
	   'insideBottomRight'
	 ];
	 app.configParameters = {
	   rotate: {
	     min: -90,
	     max: 90
	   },
	   align: {
	     options: {
	       left: 'left',
	       center: 'center',
	       right: 'right'
	     }
	   },
	   verticalAlign: {
	     options: {
	       top: 'top',
	       middle: 'middle',
	       bottom: 'bottom'
	     }
	   },
	   position: {
	     options: posList.reduce(function (map, pos) {
	       map[pos] = pos;
	       return map;
	     }, {})
	   },
	   distance: {
	     min: 0,
	     max: 100
	   }
	 };
	 app.config = {
	   rotate: 90,
	   align: 'left',
	   verticalAlign: 'middle',
	   position: 'insideBottom',
	   distance: 15,
	   onChange: function () {
	     const labelOption = {
	       rotate: app.config.rotate,
	       align: app.config.align,
	       verticalAlign: app.config.verticalAlign,
	       position: app.config.position,
	       distance: app.config.distance
	     };
	     myChart.setOption({
	       series: [
	         {
	           label: labelOption
	         },
	         {
	           label: labelOption
	         },
	         {
	           label: labelOption
	         },
	         {
	           label: labelOption
	         }
	       ]
	     });
	   }
	 };
	 const labelOption = {
	   show: true,
	   position: app.config.position,
	   distance: app.config.distance,
	   align: app.config.align,
	   verticalAlign: app.config.verticalAlign,
	   rotate: app.config.rotate,
	   formatter: '',
	   formatter: '{c}',
	   fontSize: 14,
	   rich: {
	     name: {}
	   }
	 };
	 option = {
	   tooltip: {
	     trigger: 'axis',
	     axisPointer: {
	       type: 'shadow'
	     }
	   },
	   legend: {
	     data: ['总数', '完成']
	   },
	   toolbox: {
	     show: true,
	     orient: 'vertical',
	     left: 'right',
	     top: 'center',
	     feature: {
	       mark: { show: true },
	       dataView: { show: true, readOnly: false },
	       magicType: { show: true, type: ['line', 'bar', 'stack'] },
	       restore: { show: true },
	       saveAsImage: { show: true }
	     }
	   },
	   xAxis: [
	     {
	       type: 'category',
	       axisTick: { show: false },
	       data:${deptlist}
	     }
	   ],
	   yAxis: [
	     {
	       type: 'value'
	     }
	   ],
	   series: [
	     {
	       name: '总数',
	       type: 'bar',
	       barGap: 0,
	       label: labelOption,
	       emphasis: {
	         focus: 'series'
	       },
	       data:${listcount1}
	     },
	     {
	       name: '完成',
	       type: 'bar',
	       label: labelOption,
	       emphasis: {
	         focus: 'series'
	       },
	       data: ${listcount2}
	     } 
	   ],color:[ 
	    	 '#428bca','#ff0000'
	    	    ]
	 };
	 option && myChart.setOption(option);

	 
	 
	 
	 
	 
	 
	 
// 		 layui.use('form', function(){
// 		    var form = layui.form,
// 		    form.on('select(type)'),function(){
		        
// 		        var type=$('#type').find('option:selected').val();
// //		        console.log("type="+type);
// 		        alert("type==="+type);
		        
// 		    });
// 		});
	 
	 
	 
	 </script>
</html>
