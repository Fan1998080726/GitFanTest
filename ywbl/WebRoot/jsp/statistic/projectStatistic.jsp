<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>建筑市场监管信息平台</title>
	<link href="<%=request.getContextPath()%>/css/layout.css"
		type="text/css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
			type="text/css" rel="stylesheet">

			<script type="text/javascript"
				src="<%=request.getContextPath()%>/js/jquery/jquery-1.7.2.min.js"></script>
			<script src="<%=request.getContextPath()%>/js/charts/highcharts.js"></script>
			<script
				src="<%=request.getContextPath()%>/js/charts/highcharts-more.js"></script>
			<script type="text/javascript">
				$(function() {
					//----------------------------------------------------------                                                          
					//----------------------------------------------------------                                                          
					//------------------------进度对比-----------------------------                                                          
					//----------------------------------------------------------                                                          
					//----------------------------------------------------------                                                          
					var options1 = {
						chart : {
							type : 'bar',
							renderTo : 'div1'
						},
						title : {
							text : '进度对比'
						},
						subtitle : {
							text : ''
						},
						xAxis : {
							categories : [],
							title : {
								text : null
							}
						},
						yAxis : {
							min : 0,
							title : {
								text : '工程量',
								align : 'high'
							},
							labels : {
								overflow : 'justify'
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
							bar : {
								dataLabels : {
									enabled : true
								}
							}
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'top',
							x : -40,
							y : 100,
							floating : true,
							borderWidth : 1,
							backgroundColor : '#FFFFFF',
							shadow : true
						},
						credits : {
							enabled : false
						},
						series : [ {
							name : '实际进度',
							data : []
						}, {
							name : '计划进度',
							data : []
						} ]
					};

					//-----------------------------------------------------------                                                                   
					//-----------------------------------------------------------                                                                   
					//---------------------成本对比---------------------------------                                                                   
					//-----------------------------------------------------------                                                                   
					//-----------------------------------------------------------  
					Highcharts.setOptions({
						colors : [ "#86777F", "#42A07B" ]
					});
					var options2 = {
						chart : {
							type : 'bar',
							renderTo : 'div2'
						},
										title : {
											text : '成本对比'
										},
										subtitle : {
											text : ''
										},
										xAxis : {
											categories : [],
											title : {
												text : null
											}
										},
										yAxis : {
											min : 0,
											title : {
												text : '单位 (元)',
												align : 'high'
											},
											labels : {
												overflow : 'justify'
											}
										},
										tooltip : {
											headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
											pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
													+ '<td style="padding:0"><b>{point.y:.2f}(元)</b></td></tr>',
											footerFormat : '</table>',
											shared : true,
											useHTML : true
										},
										plotOptions : {
											bar : {
												dataLabels : {
													enabled : true
												}
											}
										},
										legend : {
											layout : 'vertical',
											align : 'right',
											verticalAlign : 'top',
											x : -40,
											y : 100,
											floating : true,
											borderWidth : 1,
											backgroundColor : '#FFFFFF',
											shadow : true
										},
										credits : {
											enabled : false
										},
										series : [
												{
													name : '实际消耗成本',
													data : []
												},
												{
													name : '计划成本',
													data : []
												} ]
									};
					//-----------------------------------------------------------------
					//-----------------------------------------------------------------
					//--------------------质量管控统计------------------------------------
					//-----------------------------------------------------------------
					//-----------------------------------------------------------------
					Highcharts.setOptions({
						colors : [ '#50B432', '#ED561B', '#DDDF00', '#24CBE5',
								'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ]
					});
					var options3= {
						chart : {
							type : 'line',
							renderTo : 'div3'
						},
										title : {
											text : '质量管控统计'
										},
										subtitle : {
											text : ''
										},
										xAxis : {
											categories : []
										},
										yAxis : {
											title : {
												text : '质量报警次数'
											}
										},
										tooltip : {
											enabled : false,
											formatter : function() {
												return '&lt;b&gt;'
														+ this.series.name
														+ '&lt;/b&gt;&lt;br/&gt;'
														+ this.x + ': '
														+ this.y + '°C';
											}
										},
										plotOptions : {
											line : {
												dataLabels : {
													enabled : true
												},
												enableMouseTracking : false
											},
											series : {
												events : {
												legendItemClick: function(event) { 
												 
												                      return false
												     }
												   }
												}
										},
										series : [ {
											name : '质量报警',
											data : []
										} ]
									};
					//-----------------------------------------------------------------
					//-----------------------------------------------------------------
					//--------------------安全管控统计------------------------------------
					//-----------------------------------------------------------------
					//-----------------------------------------------------------------
					Highcharts.setOptions({
						colors : [ '#058DC7', '#50B432', '#ED561B', '#DDDF00',
								'#24CBE5', '#64E572', '#FF9655', '#FFF263',
								'#6AF9C4' ]
					});
					var options4 = {
						chart : {
							type : 'line',
							renderTo : 'div4'
						},
										title : {
											text : '安全管控统计'
										},
										subtitle : {
											text : ''
										},
										xAxis : {
											categories : []
										},
										yAxis : {
											title : {
												text : '安全报警次数'
											}
										},
										tooltip : {
											enabled : false,
											formatter : function() {
												return '&lt;b&gt;'
														+ this.series.name
														+ '&lt;/b&gt;&lt;br/&gt;'
														+ this.x + ': '
														+ this.y + '°C';
											}
										},
										plotOptions : {
											line : {
												dataLabels : {
													enabled : true
												},
												enableMouseTracking : false
											},
											series : {
												events : {
												legendItemClick: function(event) { 
												 
												                      return false
												     }
												   }
												}
										},
										//plotOptions: { 
										   // series: { 
										       // cursor: 'pointer', 
										      //  events: { 
										           // click: function(event) { 
										             //   alert(this.name +' clicked\n'+'highcharts 交流群294191384'); 
										         //   } 
										       // } 
										   // } 
										//},  
										series : [ {
											name : '安全报警',
											data : []
										} ]
									};
									
					var url = "statistic!projectStatistic.action";
					$.getJSON(url, function(data) {
						var temp1 = data[0];
						for ( var i = 0; i < temp1.length; i++) {//进度对比
							options1.xAxis.categories.push(temp1[i].pc_name);
							options1.series[0].data.push(temp1[i].actual);
							options1.series[1].data.push(temp1[i].plan);
						}
						var chart1 = new Highcharts.Chart(options1);
	
						var temp2 = data[1];
						for ( var i = 0; i < temp2.length; i++) {//进度对比
							options2.xAxis.categories.push(temp2[i].pc_name);
							options2.series[0].data.push(temp2[i].actual);
							options2.series[1].data.push(temp2[i].plan);
						}
						var chart2 = new Highcharts.Chart(options2);
						
						var temp3 = data[2];
						for ( var i = 0; i < temp3.length; i++) {//进度对比
							options3.xAxis.categories.push(temp3[i].mm);
							options3.series[0].data.push(temp3[i].num);
						}
						var chart3 = new Highcharts.Chart(options3);
						
						var temp4 = data[3];
						for ( var i = 0; i < temp4.length; i++) {//进度对比
							options4.xAxis.categories.push(temp4[i].mm);
							options4.series[0].data.push(temp4[i].num);
						}
						var chart4 = new Highcharts.Chart(options4);
					});
				});

				//----------------------
				/**
				 * Grid theme for Highcharts JS
				 * @author Torstein Honsi
				 */

				Highcharts.theme = {
					colors : [ '#50B432', '#ED561B', '#DDDF00', '#24CBE5',
							'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
					chart : {
						backgroundColor : {
							linearGradient : {
								x1 : 0,
								y1 : 0,
								x2 : 1,
								y2 : 1
							},
							stops : [ [ 0, 'rgb(255, 255, 255)' ],
									[ 1, 'rgb(240, 240, 255)' ] ]
						},
						borderWidth : 2,
						plotBackgroundColor : 'rgba(255, 255, 255, .9)',
						plotShadow : true,
						plotBorderWidth : 1
					},
					title : {
						style : {
							color : '#000',
							font : 'bold 16px "Trebuchet MS", Verdana, sans-serif'
						}
					},
					subtitle : {
						style : {
							color : '#666666',
							font : 'bold 12px "Trebuchet MS", Verdana, sans-serif'
						}
					},
					xAxis : {
						gridLineWidth : 1,
						lineColor : '#000',
						tickColor : '#000',
						labels : {
							style : {
								color : '#000',
								font : '11px Trebuchet MS, Verdana, sans-serif'
							}
						},
						title : {
							style : {
								color : '#333',
								fontWeight : 'bold',
								fontSize : '12px',
								fontFamily : 'Trebuchet MS, Verdana, sans-serif'

							}
						}
					},
					yAxis : {
						minorTickInterval : 'auto',
						lineColor : '#000',
						lineWidth : 1,
						tickWidth : 1,
						tickColor : '#000',
						labels : {
							style : {
								color : '#000',
								font : '11px Trebuchet MS, Verdana, sans-serif'
							}
						},
						title : {
							style : {
								color : '#333',
								fontWeight : 'bold',
								fontSize : '12px',
								fontFamily : 'Trebuchet MS, Verdana, sans-serif'
							}
						}
					},
					legend : {
						itemStyle : {
							font : '9pt Trebuchet MS, Verdana, sans-serif',
							color : 'black'

						},
						itemHoverStyle : {
							color : '#039'
						},
						itemHiddenStyle : {
							color : 'gray'
						}
					},
					labels : {
						style : {
							color : '#99b'
						}
					},

					navigation : {
						buttonOptions : {
							theme : {
								stroke : '#CCCCCC'
							}
						}
					}
				};

				// Apply the theme
				var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

				
			</script>
</head>
<body>
	<div class="main">
		<div class="breadcrumb">当前位置：当前项目查询</div>
		<div class="main3">

			<div id="div1" class="charts-x"></div>
			<div id="div2" class="charts-x"></div>
			<div class="clear"></div>
			<div id="div3" class="charts-x"></div>
			<div id="div4" class="charts-x"></div>

		</div>
	</div>

</body>
</html>