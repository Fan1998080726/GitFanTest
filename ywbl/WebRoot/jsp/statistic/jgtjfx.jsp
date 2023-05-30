<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title></title> 
	<%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
	<link rel="stylesheet" href="<%=path%>/css/form/style.css" type="text/css" media="all" />
	<link href="<%=path%>/css/form/demo.css" type="text/css" rel="stylesheet" />
	<!--tree-->
	<script type="text/javascript" src="<%=path%>/js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/charts/highcharts.js"></script>
	<script type="text/javascript" src="<%=path%>/js/form/exporting.js"></script>
	<script>
		 $(function () {
			 chart = new Highcharts.Chart({
			        chart: {
			        	renderTo: "t1",        
			            type: 'pie',
			            options3d: {
			                enabled: true,
			                alpha: 45,
			                beta: 0
			            }
			        },
					credits:{
					     enabled:false // 禁用版权信息
					},
			        title: {
			            text: '开竣工比例分析'
			        },
			        tooltip: {
			              //pointFormat: '比例: {point.percentage:.1f} %'
			              formatter:function(){
			            	  return '<b>'+this.point.name+':'+Highcharts.numberFormat(this.percentage,2)+'%('+
			            	  Highcharts.numberFormat(this.y,0,',')+'个)</b>';
			              }
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                depth: 35,
			                dataLabels: {
			                    enabled: true,
			                    format: '{point.name}'
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '',
			            data: []
			        }]
			    });
			/*    */
			 var browsers = [];
			 var url = "tBProjectInfo!queryAnalyzeJgblfx1.action";
				$.getJSON(url, function(data) {
					/* var temp1 = data[0];
					for ( var i = 0; i < temp1.length; i++) {
						 browsers.push([temp1[i].name,temp1[i].count_]);
					} */ 
					 browsers.push(["竣工备案证",data]);
					 chart.series[0].setData(browsers); 
				});
			 var url = "tBProjectInfo!queryAnalyzeJgblfx.action";
				$.getJSON(url, function(data) {
					/* 
					for ( var i = 0; i < temp1.length; i++) {
						 browsers.push([temp1[i].name,temp1[i].count_]);
					}  */
					 browsers.push(["质量监督注册",data]);
					 chart.series[0].setData(browsers); 
				});
			
			});
	</script>
</head>
<body>
	<div class="main">
		<div class="breadcrumb">当前位置：开竣工比例分析</div>

		<div class="main3">
	<!-- 	<div class="charts-x"><div class="box" id="t1"></div></div> -->
		<div class="box" id="t1"></div>
		<div class="clear"></div>
	<!-- 	<div class="charts-x"><div class="box" id="t3"></div></div>
		<div class="charts-x"><div class="box" id="t4"></div></div> -->
		</div>
	</div>
</body>
</html>