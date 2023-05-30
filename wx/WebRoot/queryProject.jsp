<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0092)http://bigapp.scedu.net/yqtb/mobile/?openid=_Z5um5kjcSk2Z3_GmpSUWK43CirFybhq4lMHXVgTyXU3pzj9 -->
<html class="md device-pixel-ratio-1 device-desktop device-windows"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="Content-Security-Policy" content="default-src * &#39;self&#39; &#39;unsafe-inline&#39; &#39;unsafe-eval&#39; data: gap: content:">
<!--    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <meta name="theme-color" content="#2196f3">
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no">
    <%@ include file="/common/common.jsp"%>
    	<script type="text/javascript"
		src="<%=path%>/js/jquery/jquery-1.8.3.min.js"></script>


	<link href="<%=path%>/css/layui/css/layui.css" rel="stylesheet"
		type="text/css" />

	<script type="text/javascript" src="<%=path%>/css/layui/layui.all.js"></script>
<!-- 引入样式 -->
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <title>项目记录</title>
<%-- <%@ include file="/common/common.jsp"%> --%>
 <link href="common.css" rel="stylesheet" type="text/css"/>	
<script type="text/javascript">
function serch(){
	var prjName = document.getElementById("prjName").value;
	if(prjName.length>=4){
		$('#form1').submit();
	}else{
		alert("最少输入4个文字");
	}
	

// 	$('#form1').submit();
}
function fnIntoMonomer(proid){
	
	location.replace("WxServlet?opflag=queryMonomer&proid="+proid);
}
</script>
 
</head>
<body>
<div class="wrapper">
<table width="90%"		 border="0" cellpadding="0" cellspacing="0"class="layui-table" >
  <tbody>
    <tr>
    
      <th width="66%"  >
   <b> 项目名称 </b>
      
      </th>
      <th width="34%"  >  
      	<b>开发单位</b>
      </th>
    </tr>
    
   <c:forEach items="${list}" var="m">
       <tr>
      <td class="kuang_bottom1 kuang_right wz"> ${m.xmmc }</td>
      <td align="center" valign="middle" class="kuang_bottom1"> ${m.qymc }</td>
    </tr>
    <tr style="height: 50px;">
      <td colspan="2" align="center" class="kuang_bottom1 jr">
      
      <button type="button"    class="layui-btn layui-btn-radius layui-btn-normal"  
       onclick="fnIntoMonomer('${m.id}')"
       style="width:35%;height: 40px" >
	<font size="4">
      进&nbsp;&nbsp;&nbsp;&nbsp;入
	</font>
       </button>
      </td>
      </tr>
   </c:forEach>
    
 

  </tbody>
</table>
    
</div>
</body>	
 </html>