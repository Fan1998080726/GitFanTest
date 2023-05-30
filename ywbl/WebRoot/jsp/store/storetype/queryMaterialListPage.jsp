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
   <div class="main3">
   
  <!--toolbar-->
   <div class="toolbar">
    <form method="post" id="userform">
    	<input type="hidden" name="mt_name" value="${ mt_name }" />
        <input type="hidden" name="mt_id" value="${ mt_id }" />
        <input type="hidden" name="param_flag" value="${ param_flag }" />
        <input type="hidden" name="mt_unit" value="${ mt_unit }" />
      <ul class="clearfix">
        <li>
            <span>相关工程</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="请输入类型...">
        </li>
        <li>
          <a href="javascript:chaxunMaterial()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form><%--
    <div class="action">
      <input type="button" onclick="addPage();" value="" style="background:url(<%=path %>/img/t-add.png) no-repeat;"/>
      <input type="button" onclick="del();" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;"/>
      <input type="button" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;"/>
    </div>
    --%><div class="clear"></div>
  </div>

	<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
        
          <table class="table table-striped table-bordered table-condensed">
            <thead>
             <tr><%--
                <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="option1" id="checkAll"></th>
                --%><th>操作人</th>
                <th>操作日期</th>
                <th>材料名</th>
                <th>数量</th>
                <th>单位</th>
                <th>操作类型</th>
                <th>相关工程</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="material" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<%--<td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="${material.ml_id }" name="selectFlag"></td>
	                --%><td>${ material.ml_user }</td>
	                <td>${ material.ml_date }</td>
	                <td>${ mt_name }</td>
	                <td>${ material.ml_num }</td>
	                <td>${ mt_unit }</td>
	                <c:if test="${material.ml_flag=='41'}">
	                <td style="color:#3a8f00;">入库</td></c:if>
	                <c:if test="${material.ml_flag=='42'}">
	                <td style="color:#ff002b;">出库</td></c:if>
	                <c:if test="${material.ml_flag=='43'}">
	                <td style="color:#0783C4;">返库</td></c:if>
	                
	                <td>
	                	${material.pro_id}
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
