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
//<!--
	function chaxun() {
		var user_code = $('#usercode').val();
		$('#userform').attr("action", "storetype!selectProjectPage.action");
		$('#userform').submit();
	}
	
	function getProject(){
		var pro_id=$('input[name=selectFlag]:checked');
		$.closeDialog(pro_id.val(),'selectId');
	}
	
	
//-->
</script>

<body>
<div class="main">
  <div class="main5">
 
<!--search-->
<form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>工程名：</span> <input name="user_code" id="usercode" value="${ user_code }" type="text" class="span3" placeholder="请输入工程名...">
        </li>
        <li>
         <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
        <input type="submit" value="" onclick="getProject();" style="background:url(img/queding.png) no-repeat; width:64px; height:25px; border:none; margin:10px; float:right;"/>
      </ul>
      
</form>


      

<!--table-->
        <div class="da-panel-content">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th></th>
                <th>工程名称</th>
                <th>工程地址</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="project" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	                <td><input type="radio" value="${project.pro_id },${ project.pro_name }" name="selectFlag"/></td>
	                <td>${ project.pro_name }</td>
	                <td>${ project.pro_place }</td>
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
