<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0092)http://bigapp.scedu.net/yqtb/mobile/?openid=_Z5um5kjcSk2Z3_GmpSUWK43CirFybhq4lMHXVgTyXU3pzj9 -->
<html class="md device-pixel-ratio-1 device-desktop device-windows"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="Content-Security-Policy" content="default-src * &#39;self&#39; &#39;unsafe-inline&#39; &#39;unsafe-eval&#39; data: gap: content:">
 	
<title>单项工程名称</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<!-- <link href="common.css" rel="stylesheet" type="text/css"/>	 -->


    <%@ include file="/common/common.jsp"%>
    	<script type="text/javascript"
		src="<%=path%>/js/jquery/jquery-1.8.3.min.js"></script>


	<link href="<%=path%>/css/layui/css/layui.css" rel="stylesheet"
		type="text/css" />

	<script type="text/javascript" src="<%=path%>/css/layui/layui.all.js"></script>

</head>
<script type="text/javascript">
function fnIntoCheckMonomer(mid){
 	location.replace("WxServlet?opflag=queryCheckMonomer&mid="+mid+"&proid="+'${proid}');
}
function fnReturn(){
 	location.replace("WxServlet?opflag=queryProjectDataList");
}
 
</script>
<body>
<div class="wrapper">


 <button type="button"    class="layui-btn layui-btn-radius layui-btn-normal"  
      onclick="fnReturn()"
       style="width:100%;height: 40px" >
	<font size="4">
      返&nbsp;&nbsp;&nbsp;&nbsp;回
	</font>
       </button>
       
       
       
 <table width="90%"		 border="0" cellpadding="0" cellspacing="0"class="layui-table" >
  <tbody>
    <tr>
    
      <th width="66%"  >
   <b>单项工程名称</b>
      
      </th>
      <th width="34%"  >  
      	<b>操作</b>
      </th>
    </tr>
    
   <c:forEach items="${list}" var="m">
       <tr  style="height:80px">
      <td class="kuang_bottom1 kuang_right wz"> ${m.unitname } </td>
      <td align="center" valign="middle" class="kuang_bottom1">  
      
            <button type="button"    class="layui-btn layui-btn-radius layui-btn-normal"  
       onclick="fnIntoCheckMonomer('${m.id}')"
       style="width:100%;height: 40px" >
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