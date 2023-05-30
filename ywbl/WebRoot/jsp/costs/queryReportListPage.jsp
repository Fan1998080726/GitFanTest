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
<script type="text/javascript">
$(function() {
	
	$("#checkAll").click(function() {
		if (this.checked) {
			$("input[name='selectFlag']:checkbox").each(function() { // 遍历所有的name为selectFlag的
				// checkbox
				$(this).attr("checked", true);
			})
		} else { // 反之 取消全选
			$("input[name='selectFlag']:checkbox").each(function() { // 遍历所有的name为selectFlag的
				// checkbox
				$(this).attr("checked", false);
			})
		}
	})
});

/**
 * 关闭弹出窗口
 */
function closeDialog() {
	$.closeDialog();
}

/**
 * 删除
 */
function del() {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除报表？",
				function(){
			showResult("yes");// 确认按钮回调方法
		}, function(){
			$.tips("您取消了删除操作！");
		});
		}else {
			$.alert("请选择要删除的条目！");
			return;
		}
}

/**
 * 用户删除确认操作
 * @param btn
 */
function showResult(btn){
	if("yes" == btn){
		document.listform.action = "report!delete.action";
		document.listform.submit();
		$.tips("删除成功！");
	}
}

/**
 * 添加弹出窗口
 */
function addPage() {
	var url = "report!addPage.action";
	$.dialog({
		url:url,
		title: '支付报表-添加', 
		width: 900, 
		height: 400
		});

}
/**
 * 查看弹出窗口
 */
function showPage(pr_id) {
	var url = "report!editPage.action?type=show&pr_id="+pr_id;
	$.dialog({
		url:url,
		title: '支付报表-查看详情', 
		width: 900, 
		height: 400
		});

}
function getChildData(data){
	$('#listform').submit();
}

function editpage(){
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		
		if (a == 1) {
			
			var ids = "";
			$("input[name='selectFlag']:checked").each(function() {
				ids += $(this).val();
			});
			var url = "report!editPage.action?type=edit&pr_id="+ids;
			$.dialog({
				url:url,
				title: '支付报表-编辑', 
				width: 900, 
				height: 400
			});
		} else {
			$.alert("请选择一条信息进行编辑！");
		}

	} else {
		$.alert("请选择要编辑信息");
	}

}
</script>
</head>
<body>
<div class="main">
  <div class="breadcrumb">当前位置：支付报表</div>
  
  
  <div class="main3">
  
  <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="userform">
    </form>
    <div class="action">
      <input name="btn_add" type="button" onclick="addPage();" value="" style="background:url(img/t-add.png) no-repeat;display:none"/>
      <input name="btn_update" type="button" onclick="editpage();" value="" style="background:url(img/t-edit.png) no-repeat;display:none"/>
      <input name="btn_del" type="button" onclick="del();" value="" style="background:url(img/t-delete.png) no-repeat;display:none"/>
    </div>
    <div class="clear"></div>
  </div>
  
  
  <!--table-->
        <div class="da-panel-content">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                   <input type="checkbox" value="option1" id="checkAll">
                </th>
                <th><img src="img/th-img.png"/> 中标合同价</th>
                <th><img src="img/th-img.png"/> 上报金额</th>
                <th><img src="img/th-img.png"/> 审批金额</th>
                <th><img src="img/th-img.png"/> 支付金额</th>
                <th><img src="img/th-img.png"/> 支付比例</th>
                <th><img src="img/th-img.png"/> 累计支付金额</th>
                <th><img src="img/th-img.png"/> 累计支付比例</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="reportVo" varStatus="stauts">
	              <tr ondblclick="showPage('${reportVo.pr_id }')" <c:if test="${stauts.count%2==1 }">class="odd"</c:if> >
	                <td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;"><input type="checkbox" value="${reportVo.pr_id }" name="selectFlag"></td>
	                <td>${reportVo.pr_price }</td>
	                <td>${reportVo.pr_report }</td>
	                <td>${reportVo.pr_approval }</td>
	                <td>${reportVo.pr_pay }</td>
	                <td>${reportVo.pr_rate }</td>
	                <td>${reportVo.pr_total_pay }</td>
	                <td>${reportVo.pr_total_rate }</td>
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
