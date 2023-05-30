<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
		<title>建筑市场监管信息平台</title> 
		 <%@ include file="/jsp/common/commonlayUi.jsp" %>

	<script>
 
			
			
	</script>
		
		     <style type= "text/css" >
 ul {
    width: 500px; 
    overflow: hidden;
    white-space:nowrap;  
}
li{
    list-style: none;
    float: left; //向左排列
    margin-left: 15px;
    width: 130px;
}
.layui-form-label {
    float: right;
    display: block;
    width:120px;
    font-weight: 200;
    line-height: 20px;
    text-align: right;
    padding: 9px 15px;
}
     </style>
 
		
	</head>
<body  style="background-color: white;" >
<!-- 	<div class="main"> -->
<!--   <div class="breadcrumb">当前位置：事项编辑</div> -->
<!--   <div class="main3"> -->
<!-- 	  <form name="form1" class="registerform"  > -->
	    	  <form class="layui-form"  >
	  <input type="hidden" name="noticeVo.id" value="${noticeVo.id }"/>
	  <input type="hidden" name="noticeVo.fid" value="${noticeVo.fid }"/>
	  <input type="hidden" name="noticeVo.sendtime" value="${noticeVo.sendtime }"/>
 
 
 <div align="right">
 				    <button type="button" 	onclick="closeWindows()"	class="layui-btn layui-btn-normal">关闭</button>
 
 </div>
 
<table	  class="layui-table"  align="left">
  </tr>
  <tr>
    <th><label class="layui-form-label">标题:</label></th>
    <td>  <div class="layui-input-block"   style="margin-left: 0px">
       ${noticeVo.titlecontent} 
    </div></td>
  </tr>
  <tr>
    <th  style="width: 20%"><label class="layui-form-label">附件 </label></th>
    <td colspan="3">


		<table id="fileUploadTable" style="margin-top:8px;" class="table2" align="center"  >
              <c:forEach var="m" items="${companyfileList}">
					<td colspan='3'>
					<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/dbxtdownload/${m.cf_name }" target="_bank">
 				 附件
 					</a>
					</td>									
	 
              </c:forEach>
            </table>							
									</td>
  </tr>
  <tr>
    <th><label class="layui-form-label">备注:</label></th>
    <td>  <div class="layui-input-block"   style="margin-left: 0px">
       ${noticeVo.noctioncontent} 
    </div></td>
  </tr>

</table>
		</form>
				<div align="center">
<!-- 				    <button type="button" 	onclick="backs()"	class="layui-btn layui-btn-normal">返回</button> -->
				    
<!-- 				  		<input type="button" onclick="window.history.go(-1);" style="background:url(img/t_back.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;"/> -->
				  
				    
 		</div>
<!--   </div> -->
<!--   	</div> -->
	</body>
	
	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
	 
	 <script>
	 
	 function backs(){
// 		 window.history.back();
// 		 location.replace("toWork!querySendUserNotices.action");
		 window.history.go(-1);
	 }
	 
	 
	 
	 </script>
	 
	 
</html>
