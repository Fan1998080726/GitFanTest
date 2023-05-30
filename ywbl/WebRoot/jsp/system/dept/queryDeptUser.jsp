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
<script>
function updateAppUser(){
	var userId=$("input[name='userId']:checked").val();
	if(null!=userId&&''!=userId){
		$.ajax({
			type: "get",
			url: "dept!updateAppUser.action?userId="+userId+"&deptId="+${dept_id},
			success: function(data, textStatus){
				$.closeDialog();
			},
			error: function(){
				$.closeDialog();
			}
		});
	}else{
		$.alert("请选择审批员！");
	}
	
}
</script>
</head>
<body>
<div class="main">
  
  
  <div class="main3">
  
  <!--toolbar-->
  <div class="toolbar">

    <div class="action">
      <input type="button" onclick="updateAppUser();" value="" style="background:url(img/queding.png) no-repeat;"/>
    </div>
    <div class="clear"></div>
  </div>
  
  
  <!--table-->
        <div class="da-panel-content">
        <form method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                   
                </th>
                <th><img src="img/th-img.png"/> 登录名</th>
                <th><img src="img/th-img.png"/> 用户姓名</th>
                <th><img src="img/th-img.png"/> 用户角色</th>
                <th><img src="img/th-img.png"/> 联系电话</th>
                <th><img src="img/th-img.png"/> 电子邮件</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="userlist" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	                <td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
	                	<input type="radio" value="${userlist.id }" name="userId" <c:if test="${app_user==userlist.id }">checked</c:if>/>
	                </td>
	                <td>${ userlist.login_id }</td>
	                <td>${ userlist.user_name }</td>
	                <td>${ userlist.role_name }</td>
	                <td>${ userlist.phone }</td>
	                <td>${ userlist.email }</td>
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
