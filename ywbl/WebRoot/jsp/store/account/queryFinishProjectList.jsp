<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>
<script type="text/javascript" src="<%=path%>/jsp/store/account/queryFinishProjectList.js"></script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：材料设备结算</div>
  
   <div class="main3">
   
  <!--toolbar-->
   <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>工程名</span> <input name="pro_name" id="pro_name" value="${ pro_name }" type="text" class="span3">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>

    <div class="clear"></div>
  </div>

	<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="option1" id="checkAll"></th>
                <th>工程名程</th>
                <th>工程类型</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${projectList}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="${m.pro_id }" name="selectFlag"></td>
	                <td>${ m.pro_name }</td>
	                <td>${ m.pro_type }</td>
	                <td>${ m.pro_start_date }</td>
	                <td>${ m.pro_end_date }</td>
	                <td>
	                	<a href="javascript:meterial(${m.pro_id });">材料结算</a>
	                	<a href="javascript:device(${m.pro_id });">设备结算</a>
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
		  <!-------------显示页数结束--------->
        </div>

  </div>
</div>
</body>
</html>
