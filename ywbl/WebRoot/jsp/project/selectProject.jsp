<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>
<%@ include file="/jsp/common/common.jsp"%>
<%
// 	System.out.print("sssss11111");
// 	response.sendRedirect("welcome.jspl");
%>
<style type="text/css">
html {
	position:absolute;
	top:0;
	bottom:0;
	left:0;
	right:0;
	padding:105px 0 31px;
	overflow:hidden;
	box-sizing:border-box;
}

body {
	height: 100%;
}	
.table a {
	color: blue;
	text-decoration: none;
}

.table a:hover {
	color: blue;
	text-decoration: underline;
}
</style>
<script type="text/javascript" src="js/rili.js"></script>
<script type="text/javascript" src="jsp/project/selectProject.js"></script>
</head>
<script type="text/javascript">
<!--
	function selectProject(pro_id) {
		window.location.href = "project!goMainPage.action?pro_id=" + pro_id;
	}
	function formSubmit() {
		document.getElementById("form").submit();
	}
	function sysout() {
		$.confirm("是否确认退出系统？", function() {
			window.location.href = "log!outlog.action";// 确认按钮回调方法
		}, function() {
			//$.tips("您取消了删除操作！");
		});
	}
	//跳过选择工程
	function skip() {
		window.location.href = "project!goMainPage.action";
	}

	function initArray() {
		for (i = 0; i < initArray.arguments.length; i++)
			this[i] = initArray.arguments[i];
	}
	var isnMonths = new initArray("1月", "2月", "3月", "4月", "5月", "6月", "7月",
			"8月", "9月", "10月", "11月", "12月");
	var isnDays = new initArray("星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
			"星期六", "星期日");
	today = new Date();
	hrs = today.getHours();
	min = today.getMinutes();
	sec = today.getSeconds();
	clckh = "" + ((hrs > 12) ? hrs - 12 : hrs);
	clckm = ((min < 10) ? "0" : "") + min;
	clcks = ((sec < 10) ? "0" : "") + sec;
	clck = (hrs >= 12) ? "下午" : "上午";
	var stnr = "";
	var ns = "0123456789";
	var a = "";
//-->
</script>
<body>
	<!--header-->
	<div class="header">

		<!--div class="header-2">
			<samp>当前用户：<%=request.getSession().getAttribute("user")%><br />
			           当前工程：<%=request.getSession().getAttribute("projectName")%>
			</samp>
		</div-->
		<div class="header-1" style="height:100px;">
			<ul>
				<li style="background:url(img/user.png) no-repeat left 3px;">欢迎您，<span><%=request.getSession().getAttribute("user")%></span>
				</li>
			</ul>
		</div>
		<div class="header-3" style="height:100px;"></div>
		<div class="header-2" style="margin:0 10px;">
			<ul class="nav nav-pills">
				<%--<li><a href="<%=path%>/jsp/message/messageList.jsp" target="main"><img src="img/3-shan.gif" />
					<p>信息</p>
				</a>
				</li>
				--%>
				<li><a href="#" onclick="sysout();"><img src="img/4.png" />
						<p>退出</p> </a></li>
				<li class="clear"></li>
				<div class="right">
					<SCRIPT language=javascript>
					<!--
						function getFullYear(d) {//d is a date object
							yr = d.getYear();
							if (yr < 1000)
								yr += 1900;
							return yr;
						}
						document.write("<div>");//don't delete this line
						document.write("<div>" + getFullYear(today) + "年"
								+ isnMonths[today.getMonth()] + ""
								+ today.getDate() + "日,"
								+ isnDays[today.getDay()] + "</div></div>");
					//-->
					</SCRIPT>

				</div>
			</ul>

		</div>
	</div>
	<div style="position:relative;  background:#C8E4F5; height:100%; margin:0 10px;">
		<div
			style="position:absolute; top:10px; bottom:10px; left:10px; right:10px; padding:20px;  overflow:auto; background:#fff;"">


			<!--公告-->
			<div class="gonggao">
				<div class="title">
					<img src="img/gonggao.png" />质量报警
				</div>
				<div class="g-box">
					<a href="#"></a>
					<table class="table table-striped table-bordered table-condensed"
						style="border:none;">
						<c:forEach items="${qualityMap.qclist }" var="qc" varStatus="i">
							<tr <c:if test="${i.count%2==1 }">class="odd"</c:if>>
								<td style="text-align:left;"><img
									src="img/xinxi-list.png" /><span onclick="show1('${qc.qcId}')">${qc.qcTitle}</span>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<!--信息-->
			<div class="gonggao">
				<div class="title">
					<img src="img/xinxi.png" />安全报警
				</div>
				<div class="g-box">
					<table class="table table-striped table-bordered table-condensed"
						style="border:none;">
						<c:forEach items="${qualityMap.sclist }" var="sc" varStatus="i">
							<tr <c:if test="${i.count%2==1 }">class="odd"</c:if>>
								<td style="text-align:left; "><img
									src="img/xinxi-list.png" /><span onclick="show2('${sc.scId}')">${sc.scTitle}</span>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<!--日历-->
			<script type="text/javascript">
				writeCalendar()
			</script>

			<div class="clear"></div>

			<!--search-->
			<form id="form" action="project!selectProject.action">
				<ul class="clearfix" style="margin:0; background:#faf0e6;">
					<li
						style="background:url(img/search1.png) no-repeat 0 2px; padding-left:27px;">
						<span>工程名称</span> <input name="pro_name" value="${pro_name }"
						type="text" class="span3" /></li>
					<li><span>施工状态</span> <select id="select01" name="pro_state">
							<option value="">--请选择--</option>
							<option value="4" <c:if test="${4==pro_state }">selected</c:if>>前期准备</option>
							<option value="5" <c:if test="${5==pro_state }">selected</c:if>>施工中</option>
							<option value="6" <c:if test="${6==pro_state }">selected</c:if>>已竣工</option>
					</select>
					</li>
					<li><a href="javascript:formSubmit();"><img
							src="img/search.png" />
					</a>
					</li>
					<li><a href="javascript:skip();"><img
							src="img/09.png" /></a>
					</li>
				</ul>
			</form>


			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th><img src="img/th-img.png" /> 工程名称</th>
						<th><img src="img/th-img.png" /> 施工地点</th>
						<th><img src="img/th-img.png" /> 总投资（元）</th>
						<th><img src="img/th-img.png" /> 开始时间</th>
						<th><img src="img/th-img.png" /> 结束时间</th>
						<th><img src="img/th-img.png" /> 施工状态</th>
						<th><img src="img/th-img.png" /> 工程状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="m" items="${projectList }" varStatus="i">
						<tr <c:if test="${i.count%2==1 }">class="odd"</c:if>>
							<td><a href="javascript:selectProject(${m.pro_id });">${m.pro_name
									}</a>
							</td>
							<td>${m.pro_place }</td>
							<td>${m.pro_invest }</td>
							<td>${m.pro_start_date }</td>
							<td>${m.pro_end_date }</td>
							<c:if test="${4==m.pro_state }">
								<td style="color:#ff002b;">前期准备</td>
							</c:if>
							<c:if test="${5==m.pro_state }">
								<td style="color:#0783C4;">施工中</td>
							</c:if>
							<c:if test="${6==m.pro_state }">
								<td style="color:#3a8f00;">已竣工</td>
							</c:if>
							<c:if test="${7==m.pro_flag }">
								<td>暂存</td>
							</c:if>
							<c:if test="${8==m.pro_flag }">
								<td>审批中</td>
							</c:if>
							<c:if test="${19==m.pro_flag }">
								<td>审批通过</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-------------显示页数开始------------>
			<DIV ID=TableTail>
				<DIV ID=PageSelectorBar>
					<jsp:include page="/jsp/page/page.jsp" />
				</DIV>
			</DIV>
			<!-------------显示页数结束------------>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer">
		<p>当前系统版本：V1.0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			©版权所有：哈尔滨市住房和城乡建设局</p>
	</div>
</body>
</html>
