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
<script type="text/javascript" src="<%=path %>/jsp/system/user/usermanager.js"></script>
<script>
function getChildData(data){
	$('#listform').submit();
}


</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：用户管理</div>
  
  
  <div class="main3">
  
  <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>${displayName }:</span> <input name="user_code" id="usercode" value="${ user_code }" type="text" class="span3" placeholder="登录名">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input name="btn_add" type="button" onclick="addPage();" value="" style="background:url(img/t-add.png) no-repeat;display:none"/>
      <input name="btn_update" type="button" onclick="editpage();" value="" style="background:url(img/t-edit.png) no-repeat;display:none"/>
      <input name="btn_del" type="button" onclick="del();" value="" style="background:url(img/t-delete.png) no-repeat;display:none"/>
    </div>
    <div class="clear"></div>
  </div>
  
  
  <!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                   <input type="checkbox" value="option1" id="checkAll">
                </th>
                <th><img src="img/th-img.png"/> 登录名</th>
                <th><img src="img/th-img.png"/> ${displayName }</th>
                <th><img src="img/th-img.png"/> 用户角色</th>
                <th><img src="img/th-img.png"/> 所属部门</th>
                <th><img src="img/th-img.png"/> 联系电话</th>
                <th><img src="img/th-img.png"/> ${displayEmail}</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="userlist" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	        <c:if test="${ userlist.ID eq '3'}">
	                <td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;"><input type="checkbox" value="${userlist.ID }" name="checkbox2" disabled="disabled"></td>
			</c:if>
			<c:if test="${ userlist.ID != '3'}">
	                <td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;"><input type="checkbox" value="${userlist.ID }" name="selectFlag"></td>
			</c:if>
	                <td>${ userlist.LOGIN_ID }</td>
	                <td>${ userlist.USER_NAME }</td>
	                <td>${ userlist.ROLE_NAME }</td>
	                <td>${ userlist.DEPT_NAME }</td>
	                <td>${ userlist.PHONE }</td>
	                <td>${ userlist.EMAIL }</td>
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
