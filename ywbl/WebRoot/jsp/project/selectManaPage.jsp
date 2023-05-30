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
<link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
<script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/demo.tables.js"></script>
</head>
<script type="text/javascript">
<!--
	function chaxun() {
		$('#userform').attr("action", "project!selectManaPage.action");
		$('#userform').submit();
	}
	
	function getManager(){
		var user_id=$('input[name=selectFlag]:checked');
		$.closeDialog('0;'+user_id.val());
	}
	
//-->
</script>

<body>
<div class="main">
 
  <div class="main5">
  
    <!--toolbar-->
  <div class="toolbar">
    <div class="action">
      <input type="button" value="" onclick="getManager();" style="background:url(img/queding.png);"/>
    </div>
  </div>
  
<!--search-->
<form method="post" id="userform">
      <ul class="clearfix" style="margin-left:10px;">
        <li>
            <span>用户姓名：</span><input name="user_code" id="usercode" value="${ user_code }" type="text" class="span3" placeholder="请输入文字...">
        </li>
        <li>
          <div class="search"><a href="javascript:chaxun()" ><img src="img/search.png"/></a></div>
        </li>
      </ul>
</form>
<!--table-->
        <div class="da-panel-content">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th></th>
                <th>登录名</th>
                <th>用户姓名</th>
                <th>用户角色</th>
                <th>联系电话</th>
                <th>电子邮件</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="userlist" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	                <td><input type="radio" value="${userlist.id },${ userlist.user_name }" name="selectFlag"/></td>
	                <td>${ userlist.login_id }</td>
	                <td>${ userlist.user_name }</td>
	                <td>${ userlist.role_name }</td>
	                <td>${ userlist.phone }</td>
	                <td>${ userlist.email }</td>
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
</div>
</body>
</html>
