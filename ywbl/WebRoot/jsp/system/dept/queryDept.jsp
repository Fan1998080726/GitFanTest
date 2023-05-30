<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/common/common.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门管理</title>
<!-- <link href="<%=request.getContextPath() %>/css/right.css" rel="stylesheet" type="text/css"> -->
<!-- <script src="<%=request.getContextPath() %>/js/color.js" type="text/javascript"></script> -->
<link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
<script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/demo.tables.js"></script>
<script type="text/javascript" src="<%=path %>/jsp/system/dept/queryDept.js"></script>
<script>
function    locking(){
document.all.ly.style.display="block";
document.all.ly.style.width=document.body.clientWidth;
document.all.ly.style.height=document.body.clientHeight;
document.all.Layer2.style.display='block';
}
function    Lock_CheckForm(theForm){
document.all.ly.style.display='none';
document.all.Layer2.style.display='none';
return   false;
}
function getChildData(){
	$('#listform').submit();
}
</script>
</head>

<body>
<div class="main">
  <div class="breadcrumb">当前位置：部门管理</div>
  <div class="main3">
 
  <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="deptform">
      <ul class="clearfix">
        <li>
            <span>部门名称：</span><input name="deptName" id="deptName" value="${ deptName }" type="text" class="span3" placeholder="请输入文字...">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addPage()" value="" style="background:url(img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage()" value="" style="background:url(img/t-edit.png) no-repeat;display:none"/>
      <input type="button" name="btn_del" onclick="del()" value="" style="background:url(img/t-delete.png) no-repeat;display:none"/>
    </div><div class="clear"></div>
  </div>



<!--table-->
<form aciont="rolemanager!query.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                    <input type="checkbox" value="option1" id="checkAll">
                </th>
                <th><img src="img/th-img.png"/> 序号</th>
                <th><img src="img/th-img.png"/> 部门名称</th>
                <th><img src="img/th-img.png"/> 备注</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	                <td  style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
					<c:if test="${ m.dept_id eq '1' || m.dept_id eq '2' || m.dept_id eq '3' || m.dept_id eq '4' || m.dept_id eq '5'|| m.dept_id eq '6'|| m.dept_id eq '7'|| m.dept_id eq '8'}">
						<input type="checkbox" name="selectFlag" value="${m.dept_id }" id="checkbox_id" disabled="disabled"/>
					</c:if>
					<c:if test="${ m.dept_id != '1' && m.dept_id != '2' && m.dept_id != '3' && m.dept_id != '4' && m.dept_id != '5' && m.dept_id != '6' && m.dept_id != '7'&& m.dept_id != '8'}">
						<input type="checkbox" name="selectFlag" value="${m.dept_id }" id="checkbox_id"/>
					</c:if>
	                </td>
	                <td>${ stauts.count }</td>
	                <td>${ m.dept_name }</td>
	                <td>${ m.dept_describe }</td>
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
