<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<html>
<head>
<meta charset="utf-8">
<%@ include file="/jsp/common/common.jsp"%>
<!--table-->
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/demo.tables.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tree/icon.css">
<script type="text/javascript" src="<%=path%>/js/tree/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/tree/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/jsp/costs/queryCostContrastPage.js"></script>
<title>建筑市场监管信息平台</title>
</head>
<body>

	<div class="main">
		<div class="breadcrumb">当前位置：成本对比</div>
		<div class="main3">
		
		<!-------------左侧------------>
		<div class="m-left" style="min-width:675px;">
		
			<%--成本消耗信息对比图开始--%>
			<div class="charts charts2" style="margin:20px 0 20px 20px;">
			    <div class="title"><img src="img/th-img.png"/>  成本对比信息</div>
				<div class="box">
				
				<div class="p-line">
					<p>总成本:${costPlan.cp_type}</p>
					<div class="progress progress-info active">
						<div class="bar" style="width: 100%;"></div>
					</div>
					<span>100%</span>
				</div>
					
				<div class="p-line">
					<p>已消耗：${costPlan.cp_value}</p>
					<div class="progress progress-orange active">
						<div class="bar"
							style="width: ${costPlan.cp_value/costPlan.cp_type * 100}%;">
						</div>
					</div>
					<span><fmt:formatNumber type="number" value="${costPlan.cp_value/costPlan.cp_type * 100}" maxFractionDigits="2"/>%</span>
				</div>
					
					<c:if test="${costPlan.cp_type-costPlan.cp_value >=0}">
				<div class="p-line">
					<p>剩余：<fmt:formatNumber value="${costPlan.cp_type-costPlan.cp_value}" pattern="###.00" /></p>
					<div class="progress progress-success active">
						<div class="bar"
							style="width:  ${100 - costPlan.cp_value/costPlan.cp_type * 100}%;">
						</div>
					</div>
					<span><fmt:formatNumber type="number" value="${100 - costPlan.cp_value/costPlan.cp_type * 100}" maxFractionDigits="2"/>%</span>
				</div></c:if>
					
					<c:if test="${costPlan.cp_type-costPlan.cp_value <0}">
				<div class="p-line">
					<p>超出：<fmt:formatNumber value="${costPlan.cp_value-costPlan.cp_type}" pattern="###.00" /></p>
					<div class="progress progress-danger active">
						<div class="bar"
							style="width:  ${costPlan.cp_value/costPlan.cp_type * 100 - 100}%;">
						</div>
					</div>
					<span><fmt:formatNumber type="number" value="${costPlan.cp_value/costPlan.cp_type * 100-100}" maxFractionDigits="2"/>%</span>
				</div></c:if>
				</div>
			</div>
			<%--成本消耗信息对比图结束--%>
			<div class="clear"></div>
			<%--实际消耗列表开始 --%>

			<div class="da-panel-content" style="margin:0 0 20px 20px;">
				<div class="action">
			      <input type="button" name="btn_update" onclick="update();" value="" style="background:url(img/t-edit.png); width:62px; height:24px; margin:0; border:none;" /> 
			      <input type="button" name="btn_del" onclick="del();" value="" style="background:url(img/t-delete.png); width:62px; height:24px; margin:0; border:none;" />
			    </div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th></th>
							<th>子工程</th>
							<th>录入时间</th>
							<th>消耗成本(元)</th>
							<th>备注</th>
							<th>录入人员</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${costActuals}" var="c" varStatus="stauts">
							<tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
								<td><input type="radio" name="ca_id" value="${ c.ca_id }" <c:if test="${'1'==c.ca_flag }">disabled="disabled"</c:if>/></td>
								<td>${ c.pc_name }</td>
								<td>${ c.ca_date }</td>
								<td>${ c.ca_value }</td>
								<td>${ c.ca_remark }</td>
								<td>${ c.ca_user }</td>
								<td>
									<c:if test="${'0'==c.ca_flag }">正常</c:if>
									<c:if test="${'1'==c.ca_flag }">审批中</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%--实际消耗列表开始结束 --%>
<!-------------显示页数开始------------>
	<DIV ID=TableTail>
		<DIV ID=PageSelectorBar>
			<jsp:include page="/jsp/page/page.jsp" />
		</DIV>
	</DIV> 
<!-------------显示页数结束------------>
      
			</div>
			
			</div>
			
			<!-------------右侧------------>
			<div class="m-right">
			   <div class="m-right2" style="min-width:655px;">
                 <div class="title">成本表</div>
                 <div class="main" style="background:#fff;">
			<!--tree-->
		    <div class="tree" >
		      <table id="tg" style="height:525px;"></table><div class="clear" style="height:10px;"></div>
		    </div>
	  </div>
	</div>
               </div>
            </div>
            

		</div>
	</div>

</body>

</html>