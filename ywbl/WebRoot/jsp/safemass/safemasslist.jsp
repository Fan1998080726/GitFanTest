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
<script type="text/javascript" src="<%=path%>/jsp/safemass/safemass.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：安全管控</div>
  
  <!--toolbar-->
  <div class="toolbar">
    <div class="action">

</div>
  </div>
  
  <div class="main3">
  
        <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="safeform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="sc_title" id="sc_title" value="${sc_title }" type="text" class="span3" placeholder="请输入文字...">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addPage();"  style="background:url(<%=path%>/img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;display:none"/>
      <input type="button" name="btn_del" onclick="del();" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;display:none"/>
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
                <th><img src="img/th-img.png"/> 标题</th>
                <th><img src="img/th-img.png"/> 类型</th>
                <th><img src="img/th-img.png"/> 子工程节点</th>
                <th><img src="img/th-img.png"/> 附件</th>
                <th><img src="img/th-img.png"/> 上报时间</th>
                <th><img src="img/th-img.png"/> 操作</th>
              </tr>
            </thead>
            <tbody>
				<c:forEach items="${list}" var="safe_control" varStatus="stauts">
	             	<tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="checkbox" value="${safe_control.sc_id }" name="selectFlag"></td>
	                <td>${ safe_control.sc_title }</td>
	                <td>${ safe_control.sc_name }</td>
	                <td>${ safe_control.pc_name }</td>
	                <td>${ safe_control.sc_file_name }</td>
	                <td>${ safe_control.sc_date }</td>
	                <td class="s3"><a href="#" onclick="show('${safe_control.sc_id }');">[查看详情]</a></td>
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
