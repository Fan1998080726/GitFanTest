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
  
   <div class="main3">
   
  <!--toolbar-->
   <div class="toolbar">

    <div class="clear"></div>
  </div>

	<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th>材料名</th>
                <th>数量</th>
                <th>操作时间</th>
                <th>操作用户</th>
                <th>操作类型</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="m" varStatus="status">
	              <tr <c:if test="${status.count%2==1 }">class="odd"</c:if>>
	                <td>${ m.mt_id }</td>
	                <td>${ m.ml_num }</td>
	                <td>${ m.ml_date }</td>
	                <td>${ m.ml_user }</td>
	                <td>
	                	<c:if test="${ '41'==m.ml_flag }">入库</c:if>
	                	<c:if test="${ '42'==m.ml_flag }">出库</c:if>
	                	<c:if test="${ '43'==m.ml_flag }">返库</c:if>
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
