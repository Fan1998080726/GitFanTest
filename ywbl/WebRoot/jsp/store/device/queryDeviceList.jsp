<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>
<script type="text/javascript" src="<%=path %>/jsp/store/device/device.js"></script>
<script>
function getChildData(data){
	$('#listform').submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：设备租赁管理</div>
  
  <div class="main3">
  
  <!--toolbar-->
   <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="user_code" id="usercode" value="${ user_code }" type="text" class="span3" placeholder="请输入文字...">
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
              <tr>
                <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="option1" id="checkAll"></th>
                <th>设备名</th>
                <th>租赁组织</th>
                <th>分配工程</th>
                <th>租赁开始时间</th>
                <th>租赁结束时间</th>
                <th>费用(元/天)</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${materialDevices}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
					<td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="${m.ml_id }" name="selectFlag"></td>
	                <td>${ m.ml_name }</td>
	                <td>${ m.ml_from }</td>
	                <td>${ m.pro_name }</td>
	                <td>${ m.ml_start_date }</td>
	                <td>${ m.ml_end_date }</td>
	                <td>${ m.ml_cost }</td>
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
