<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>

<script type="text/javascript" src="<%=path %>/jsp/store/storetype/storetype.js"></script>
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
            <span>材料名</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="请输入类型...">
        </li>
        <li>
          <a href="javascript:storeTypeChaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addPage();" value="" style="background:url(<%=path %>/img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;display:none"/>
      
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
                <th>材料名</th>
                <th>描述</th>
                <th>库存量</th>
                <th>单位</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="type" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="${type.mt_id }" name="selectFlag"></td>
	                <td>${ type.mt_name }</td>
	                <td>${ type.mt_remark }</td>
	                <td>${ type.ml_num }</td>
	                <td>${ type.mt_unit }</td>
	                <td>
	                	<a href="javascript:backInMaterial(${type.mt_id });">返库</a>
	                	<a href="javascript:queryMaterial('${ type.mt_name }','${ type.mt_unit }',${type.mt_id });">查看详情</a>
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
