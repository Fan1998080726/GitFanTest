<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>

<script type="text/javascript" src="<%=path %>/jsp/store/storetype/materialapply.js"></script>
<script>
function getChildData(data){
	$('#listform').submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：材料申领</div>
   <div class="main3">
   
  <!--toolbar-->
   <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addPage();" value="" style="background:url(<%=path %>/img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;display:none"/>
      <%--<input type="button" id="btn_del" onclick="del();" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;display:none"/>
     
    --%></div>
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
                <th>申领数量</th>
                <th>单位</th>
                <th>出库时间</th>
                <th>出库人</th>
                <th>状态</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="ma" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
	             	<c:if test="${ ma.ma_flag == 1 }">
	             		<input type="checkbox" value="" name="checkbox2"  disabled="disabled" />
	             	</c:if>
	             	<c:if test="${ ma.ma_flag == 0 }">
	             		<input type="checkbox" value="${ma.ma_id }" name="selectFlag" />
	             	</c:if>
	             	</td>
	                <td>${ ma.mt_name }</td>
	                <td>${ ma.ma_num }</td>
	                <td>${ ma.ma_unit }</td>
	                <td>${ ma.ma_out_date }</td>
	                <td>${ ma.ma_out_user }</td>
	                <c:if test="${ ma.ma_flag == 0 }">
	               		<td style="color:#ff002b;">申请中</td>
	                </c:if>
	                <c:if test="${ ma.ma_flag == 1 }">
	                	<td style="color:#3a8f00;">已出库</td>
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
