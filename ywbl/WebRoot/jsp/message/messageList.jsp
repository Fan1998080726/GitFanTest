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
<!--table-->
<link rel="stylesheet" type="text/css" href="<%=path %>/css/table.css" media="screen" />
<script type="text/javascript" src="<%=path %>/jsp/message/message.js"></script>
<script type="text/javascript">
function queryViewmessage(id,mrid) {
	var a = 'span'+mrid;
	$('#'+a).html("已读");
	var url = "message!querymesview.action?id="+id+"&mrid="+mrid;
	var title = "消息信息详细";
	$.dialog({
		url:url,
		title: title, 
		width: 600, 
		height: 400
		});
}
function chaxun(){
	$('#listform1').attr('aciont','message!queryMessagelist.action');
	$('#listform1').submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：消息信息</div>
  

  
  <div class="main3">
  
    <!--toolbar-->
  <div class="toolbar">
  <!--search-->
<form method="post" id="listform1" >
      <ul class="clearfix">
        <li>
            <span>消息标题：</span> <input type="text" class="span3" name="title" value="${ title }">
        </li>
        <li>
         <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
</form>
     <div class="action">
            <input name="btn_del" type="button" onclick="del(27);" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;display:none"/>
           
    </div>
    <div class="clear"></div>
  </div>

<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed" id = "tg">
            <thead>
              <tr>
              <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="option1" id="checkAll"></th>
                <th>消息标题</th>
                <th>发件人</th>
                <th>发件时间</th>
                <th>状态 </th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="i" items="${list}" varStatus="t">
            	 <tr <c:if test="${t.count%2==1 }">class="odd"</c:if> ondblclick="queryViewmessage('${ i.ms_id}' , '${ i.mr_id }')">
	              <td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="${i.mr_id}" name="selectFlag"></td>
	                <td>${ i.ms_title }</td>
	                <td>${ i.fajian }</td>
	                <td>${ i.ms_date }</td>
	                <td><span id = "span${i.mr_id}"><c:if test="${ i.mr_read eq 0 }">已读</c:if><c:if test="${ i.mr_read eq 1 }">未读</c:if></span></td>
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
</div>
</body>
</html>
