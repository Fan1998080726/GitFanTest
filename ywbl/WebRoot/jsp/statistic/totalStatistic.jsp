<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
	<link rel="stylesheet" href="<%=path%>/css/form/style.css"
		type="text/css" media="all" />
	<link href="<%=path%>/css/form/demo.css" type="text/css"
		rel="stylesheet" />
	<!--tree-->
	<script type="text/javascript"
		src="<%=path%>/js/form/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/charts/highcharts.js"></script>
	<script type="text/javascript" src="<%=path%>/js/form/exporting.js"></script>
	<script>
		$(function() {
			var options1 = {
				chart : {
					renderTo : 't1',
					type : 'column'
				},
				title : {
					text : '进度延迟排行'
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					categories : [ '' ]
				},
				yAxis : {
					min : 0,
					title : {
						text : '工程量'
					},
					labels: {
	                    formatter: function() {
	                        return this.value;
	                    }
	                }
				},
				tooltip : {
					headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
					pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
							+ '<td style="padding:0"><b>{point.y:.2f}</b></td></tr>',
					footerFormat : '</table>',
					shared : true,
					useHTML : true
				},
				plotOptions : {
					column : {
						pointPadding : 0.2,
						borderWidth : 0
					}
				},
				series : []
			};

			var options2 = {
				chart : {
					renderTo : 't2',
					type : 'column'
				},
								title : {
									text : '成本超支排行'
								},
								subtitle : {
									text : ''
								},
								xAxis : {
									categories : [ '' ]
								},
								yAxis : {
									min : 0,
									title : {
										text : '成本值（元）'
									},
									labels: {
					                    formatter: function() {
					                        return this.value;
					                    }
					                }
								},
								tooltip : {
									headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
									pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
											+ '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
									footerFormat : '</table>',
									shared : true,
									useHTML : true
								},
								plotOptions : {
									column : {
										pointPadding : 0.2,
										borderWidth : 0
									}
								},
								series : []
							};

			var options3 = {
				chart : {
					renderTo : 't3',
					type : 'column'
				},
								title : {
									text : '质量报警排行'
								},
								subtitle : {
									text : ''
								},
								xAxis : {
									categories : [ '' ]
								},
								yAxis : {
									min : 0,
									title : {
										text : '数量（个）'
									},
									labels: {
					                    formatter: function() {
					                        return this.value;
					                    }
					                }
								},
								tooltip : {
									headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
									pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
											+ '<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
									footerFormat : '</table>',
									shared : true,
									useHTML : true
								},
								plotOptions : {
									column : {
										pointPadding : 0.2,
										borderWidth : 0
									}
								},
								series : []
							};

			var options4 = {
				chart : {
					renderTo : 't4',
					type : 'column'
				},
								title : {
									text : '安全报警排行'
								},
								subtitle : {
									text : ''
								},
								xAxis : {
									categories : [ '' ]
								},
								yAxis : {
									min : 0,
									title : {
										text : '数量（个）'
									},
									labels: {
					                    formatter: function() {
					                        return this.value;
					                    }
					                }
								},
								tooltip : {
									headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
									pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
											+ '<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
									footerFormat : '</table>',
									shared : true,
									useHTML : true
								},
								plotOptions : {
									column : {
										pointPadding : 0.2,
										borderWidth : 0
									}
								},
								series : []
							};
			

			var url = "statistic!totleStatistic.action";
			$.getJSON(url, function(data) {
				var temp1 = data[0];
				for ( var i = 0; i < temp1.length; i++) {
					options1.series.push({name:temp1[i].pro_name,data:[temp1[i].num]});
				}
				var chart1 = new Highcharts.Chart(options1);
				
				var temp2 = data[1];
				for ( var i = 0; i < temp2.length; i++) {
					options2.series.push({name:temp2[i].pro_name,data:[temp2[i].num]});
				}
				var chart2 = new Highcharts.Chart(options2);
				
				var temp3 = data[2];
				for ( var i = 0; i < temp3.length; i++) {
					options3.series.push({name:temp3[i].pro_name,data:[temp3[i].num]});
				}
				var chart3 = new Highcharts.Chart(options3);
				
				var temp4 = data[3];
				for ( var i = 0; i < temp4.length; i++) {
					options4.series.push({name:temp4[i].pro_name,data:[temp4[i].num]});
				}
				var chart4 = new Highcharts.Chart(options4);
			});
		});
	</script>
</head>
<body>
	<div class="main">
		<div class="breadcrumb">当前位置：总体查询</div>

		<div class="main3">
			<div class="charts-x">
				<div class="box" id="t1"></div>
			</div>
			<div class="charts-x">
				<div class="box" id="t2"></div>
			</div>
			<div class="clear"></div>
			<div class="charts-x">
				<div class="box" id="t3"></div>
			</div>
			<div class="charts-x">
				<div class="box" id="t4"></div>
			</div>
		</div>
	</div>
</body>
</html>