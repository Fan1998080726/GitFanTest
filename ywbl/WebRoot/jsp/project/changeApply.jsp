<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path %>/jsp/project/changeApply.js"></script>

</head>
<body>
	<div class="main">
	<div class="breadcrumb">当前位置：变更申请</div>
		<div class="main3" style="padding:10px;">
			<div class="toolbar">
				<div class="action">
					<input name="btn_add" type="button" onclick="addPage();" style="background:url(<%=path%>/img/t-add.png) no-repeat;display:none" />
					<input name="btn_update" type="button" onclick="editpage();" value="" style="background:url(<%=path%>/img/t-edit.png) no-repeat;display:none" /> 
					<input name="btn_del" type="button" onclick="del();" value="" style="background:url(<%=path%>/img/t-delete.png) no-repeat;display:none" />
					
				</div>
				<div class="clear"></div>
			</div>
			<form id="form" method="post">
				<table class="table table-striped table-bordered table-condensed">
	            <thead>
	              <tr>
	                <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="option1" id="checkAll"></th>
	                <th><img src="img/th-img.png"/> 变更申请人</th>
	                <th><img src="img/th-img.png"/> 变更时间</th>
	                <th><img src="img/th-img.png"/> 变更状态</th>
	              </tr>
	            </thead>
	            <tbody>
	            	<c:forEach items="${list}" var="changeApply" varStatus="stauts">
		              <tr ondblclick="showDetail('${changeApply.aaId }')" <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
		                <td style="background:#e3f3f9; border-right:1px solid #91abb9;">
		                	<input type="checkbox" value="${changeApply.aaId }" name="selectFlag">
		                </td>
		                <td>${ changeApply.aaUserName }</td>
		                <td>${ changeApply.aaDate }</td>
		                <td>${ changeApply.aaFlagName }</td>
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

</html>
