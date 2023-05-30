<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>建筑市场监管信息平台</title>
<%@ include file="/jsp/common/common.jsp"%>
</head>
<script type="text/javascript">
	$(function(){
		parent.parent.showCount();
	})
</script>
<body>
<div class="main">
  <!--main3-->
  <div class="main5">
    <!--form-->
    <form id="form">
      <div class="formsub">
        <h3 style="text-align:left; margin:50px 0 40px; border-bottom:2px solid #C7D6DD; height:80px; line-height:80px;">
           				消息详细信息
        </h3>
        <table class="table2 table2-striped table2-bordered table2-condensed table-down">
          <tr class="odd">
            <th>题目：</th>
            <td>${ messageVo.ms_title }</td>

          </tr>
          <tr >
              <th>发件人：</th>
            <td>${ messageVo.msuser }</td>
            </tr>
            
           <tr class="odd">
            <th>发件时间：</th>
            <td>${ messageVo.ms_date }</td>

          </tr>
          <tr>
            <th>内容：</th>
            <td >${ messageVo.ms_content }</td>
          </tr>

        </table>

      </div>
    </form>
  </div>
</div>
</body>
</html>