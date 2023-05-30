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
<script type="text/javascript" src="<%=path %>/js/demo.tables.js"></script>
<script type="text/javascript">
function showOrder(){
	var url = "jsp/project/showOrder.jsp";
	$.dialog({
		url:url,
		title: '变更申请-详细信息', 
		width: 600, 
		height: 400
		});
}

</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：变更申请</div>
  <div class="main3">
  
  <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="userform">
      <ul class="clearfix">
        <li>
            <span>快速查找</span> <input name="user_code" id="usercode" value="${ user_code }" type="text" class="span3" placeholder="请输入内容...">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input name="btn_add" type="button" onclick="addPage();" value="" style="background:url(<%=path %>/img/t-add.png) no-repeat;display:none"/>
	  <input name="btn_update" type="button" onclick="editpage();" value="" style="background:url(<%=path %>/img/t-edit.png) no-repeat;display:none"/>
      <input name="btn_del" type="button" onclick="del();" value="" style="background:url(<%=path %>/img/t-delete.png) no-repeat;display:none"/>
      
    </div>
    <div class="clear"></div>
  </div>
<!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th>变更时间</th>
                <th>变更描述</th>
                <th>申请人</th>
                <th>审批人</th>
                <th>附件</th>
                <th>状态</th>
              </tr>
            </thead>
            <tbody>
	              <tr class="odd" ondblclick="showOrder()">
	                <td>2014-04-01</td>
	                <td>成本变更申请</td>
	                <td>工程技术部</td>
	                <td>财务部</td>
	                <td><a href="#">附件1.doc</a></td>
	                <td>申请中</td>
	              </tr>
	              <tr ondblclick="showOrder()">
	                <td>2013-05-21</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>通过</td>
	              </tr>
	              <tr class="odd" ondblclick="showOrder()">
	                <td>2013-05-21</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>未通过</td>
	               </tr>
	              <tr ondblclick="showOrder()">
	                <td>2013-05-30</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>通过</td>
	              </tr>
	              <tr class="odd" ondblclick="showOrder()">
	                <td>2013-05-28</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>未通过</td>
	               </tr>
	              <tr ondblclick="showOrder()">
	                <td>2013-05-25</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>通过</td>
	              </tr>
	              <tr class="odd" ondblclick="showOrder()">
	                <td>2013-05-26</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>未通过</td>
	               </tr>
	              <tr ondblclick="showOrder()">
	                <td>2013-05-20</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>通过</td>
	              </tr>
	              <tr class="odd" ondblclick="showOrder()">
	                <td>2013-05-21</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>未通过</td>
	               </tr>
	              <tr ondblclick="showOrder()">
	                <td>2013-05-21</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>通过</td>
	              </tr>
	              <tr class="odd" ondblclick="showOrder()">
	                <td>2013-05-21</td>
	                <td>变更</td>
	                <td>XXX</td>
	                <td>XXX</td>
	                <td>附件1.doc</td>
	                <td>未通过</td>
	               </tr>
	              

            </tbody>
          </table>
          </form>
          
          <!-------------显示页数开始------------>

		<!-------------显示页数结束------------>
        </div>

  </div>
</div>
</body>
</html>
