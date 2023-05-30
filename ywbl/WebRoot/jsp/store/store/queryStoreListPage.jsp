<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>

<!--table-->
<link rel="stylesheet" type="text/css" href="<%=path %>/css/table.css" media="screen" />
<script type="text/javascript" src="<%=path %>/js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/demo.tables.js"></script>
<script type="text/javascript" src="<%=path %>/jsp/system/store/storeType.js"></script>
<script>
function getChildData(data){
	$('#listform').submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：材料管理</div>
  
   <div class="main3">
   
  <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="请输入材料名...">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addPage();" value="" style="background:url(<%=path %>/img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;display:none"/>
      <input type="button" name="btn_del" onclick="del();" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;display:none"/>
      
    </div>
    <div class="clear"></div>
  </div>
  
<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr><%--
                <th style="width:40px; background:#E9F6EC;"><input type="checkbox" value="option1" id="checkAll"></th>
                --%><th>材料名</th>
                <th>库存量</th>
                <th>描述</th>
                <th>盘点时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            <tr>
            	<td>土石方</td>
            	<td>土石方</td>
            </tr>
            <tr>
            	<td>水泥</td>
            	<td>水泥</td>
            </tr>
            <tr>
            	<td>水管</td>
            	<td>水管</td>
            </tr>
            	<%--<c:forEach items="${list}" var="userlist" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	        <c:if test="${ userlist.id eq '3'}">
	                <td style="background:#E9F6EC;"><input type="checkbox" value="${userlist.id }" name="checkbox2" disabled="disabled"></td>
			</c:if>
			<c:if test="${ userlist.id != '3'}">
	                <td style="background:#E9F6EC;"><input type="checkbox" value="${userlist.id }" name="selectFlag"></td>
			</c:if>
	                <td>${ userlist.login_id }</td>
	                <td>${ userlist.user_name }</td>
	                <td>${ userlist.role_name }</td>
	                <td>${ userlist.phone }</td>
	                <td>${ userlist.email }</td>
	              </tr>
				</c:forEach>
            --%></tbody>
          </table>
          </form>
          
          <!-------------显示页数开始------------>
			<%--<DIV ID=TableTail>
				<DIV ID=PageSelectorBar>
					<jsp:include page="/jsp/page/page.jsp" />
				</DIV>
			</DIV> 
		--%><!-------------显示页数结束------------>
        </div>

  </div>
</div>
</body>
</html>
