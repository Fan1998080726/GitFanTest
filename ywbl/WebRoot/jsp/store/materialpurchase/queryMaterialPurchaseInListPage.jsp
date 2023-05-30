<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>

<script type="text/javascript" src="<%=path %>/jsp/store/materialpurchase/materialpurchase.js"></script>
<script>
function getChildData(data){
	$('#listform').submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：材料入库管理</div>
   <div class="main3">
   
  <!--toolbar-->
   <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="">
        </li>
        <li>
          <a href="javascript:chaxun1()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <!--<div class="action">
      <input type="button" name="btn_add" onclick="addPage();" value="" style="background:url(<%=path %>/img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;display:none"/>
      <input type="button" name="btn_del" onclick="del();" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;display:none"/></div>
     -->
    <div class="clear"></div>
  </div>

	<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
             <tr>
                <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="option1" id="checkAll"></th>
                <th>采购单号</th>
                <th>采购工程</th>
                <th>申请时间</th>
                <th>申请人</th>
                <th>备注</th>
                <th>状态</th>
                <th>入库</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="mp" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if> ondblclick="queryMaterialPurchase(${mp.mp_id });" >
	             	<td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
	             	<c:if test="${ mp.mp_flag != 36 }">
	             		<input type="checkbox" value="" name="checkbox2"  disabled="disabled" />
	             	</c:if>
	             	<c:if test="${ mp.mp_flag == 36 }">
	             		<input type="checkbox" value="${mp.mp_id }" name="selectFlag" />
	             	</c:if>
	             	</td>
	                <td>${ mp.mp_name }</td>
	                <td>${ mp.pro_name }</td>
	                <td>${ mp.mp_date }</td>
	                <td>${ mp.mp_user }</td>
	                <td>${ mp.mp_remark }</td>
	                <c:if test="${ mp.mp_flag == 38 }">
	                	<td style="color:#3a8f00;">审批通过</td>
	                	<td ><a style="color:#3a8f00;" href="javascript:inputMaterial(${mp.mp_id });">入库</a></td>
	                </c:if>
	                <c:if test="${ mp.mp_flag == 39 }">
	                	<td>已入库</td>
	                	<td></td>
	                </c:if>
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
