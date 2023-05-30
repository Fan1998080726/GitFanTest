<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>

<%@ include file="/jsp/common/common.jsp"%>

<script type="text/javascript" src="<%=path %>/jsp/store/device/deviceapply.js"></script>
<script>
function getChildData(data){
	$('#listform').submit();
}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：设备租赁管理</div>
   <div class="main3">
   
  <!--toolbar-->
   <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="">
        </li>
        <li>
          <a href="javascript:chaxunOut()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addDeviceOutPage();" value="" style="background:url(<%=path %>/img/08.png) no-repeat;display:none"/>
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
                <th>设备名</th>
                <th>申领数量</th>
                <th>单位</th>
                <th>单价（元/天）</th>
                <th>申领时间</th>
                <th>申领工程</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="mda" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
	             	<c:if test="${ mda.mda_flag !=0 }">
	             		<input type="checkbox" value="" name="checkbox2"  disabled="disabled" />
	             	</c:if>
	             	<c:if test="${ mda.mda_flag == 0 }">
	             		<input type="checkbox" value="${mda.mda_id }" name="selectFlag" />
	             	</c:if>
	             	</td>
	                <td>${ mda.mda_name }</td>
	                <td>${ mda.mda_num }</td>
	                <td>${ mda.mda_unit }</td>
	                <td>${ mda.mda_price }</td>
	                <td>${ mda.mda_apply_date }</td>
	                <td>${ mda.pro_name }</td>
	                <c:if test="${ mda.mda_flag == 0 }">
	               		<td style="color:#ff002b;">申请中</td>
	                </c:if>
	                <c:if test="${ mda.mda_flag == 1 }">
	                	<td style="color:#3a8f00;">已出库</td>
	                </c:if>
	                <c:if test="${ mda.mda_flag == 2 }">
	                	<td style="color:#0783C4;">已返库</td>
	                </c:if>
	               	<td>
	               		<c:if test="${ mda.mda_flag == 1 }">
	               			<a href="javascript:addBackPage(${mda.mda_id });">返库</a>
	               		</c:if>
	               		<c:if test="${ mda.mda_flag == 2 }">
	               			<a href="javascript:viweDeviceApplyPage(${mda.mda_id });">查看详情</a>
	               		</c:if>
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
