<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%-- <fmt:setBundle basename="application" var="downloadurl"/>
<fmt:message key="url" var="url" bundle="${downloadurl}"/> --%>
<meta charset="utf-8">
 <link href="common.css" rel="stylesheet" type="text/css"/>	

<%
	String path = request.getContextPath();
%>

<%--     <%@ include file="/common/common.jsp"%> --%>
    	<script type="text/javascript"
		src="<%=path%>/js/jquery/jquery-1.8.3.min.js"></script>


	<link href="<%=path%>/css/layui/css/layui.css" rel="stylesheet"
		type="text/css" />

	<script type="text/javascript" src="<%=path%>/css/layui/layui.all.js"></script>
<title>配套工程记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<link href="common.css" rel="stylesheet" type="text/css"/>	
</head>
<script>
	function fnqueryCheck(id){
		location.replace("WxServlet?opflag=queryCheckById&id="+id+'&mid='+'${mid}'+"&proid="+'${proid}');
	}
	function returnMonner(id){
		location.replace("WxServlet?opflag=queryMonomer&proid="+'${proid}');
	}
	
	
	
	function addCheck(str){
		location.replace("WxServlet?opflag=updateCheckPage&mid="+'${mid}'+"&proid="+'${proid}'+"&str="+str);
	}
</script>
<body>
<div class="wrapper">

<div align="right">
    <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"	  onclick="returnMonner('${proid}')"	>返回</button>
</div>

<!-- 基础完工记录 -->
<form action="">
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="margin-left">
  <tbody>
    <tr>
      <td width="3%"><div class="yuan"></div></td>
      <td width="44%" class="wz">配套工程记录</td>
      
      
      
      <c:if test="${monomerVo.sendtime!=null}">
        <c:if test="${monomerVo.state2!='提交' }">
        
      <td width="33%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>确认完工</button>
      </td>
      <td width="20%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"			onclick="addCheck('1')">添加</button>
      </td>
      </c:if>
      </c:if>
      
      
      
      
    </tr>
    <tr>
      <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
             <tr>
            <td width="4%" class="kuang_right1"></td>
            <td width="1%" align="center" valign="middle"></td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
          </tr> 
          <tr>
            <td width="4%" class="kuang_right1">&nbsp;</td>
            <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1">序号</td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1">姓名</td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1">时间</td>
          </tr> 
          <c:forEach items="${list1 }"  var="m"  varStatus="status">
          <tr>
            <td height="46" class="kuang_right1">&nbsp;</td>
            <td align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1">${status.count }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.checkpeson }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.inittime }</td>
          </tr>
                    <tr>
            <td height="60" class="kuang_right1">&nbsp;</td>
             <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1"   colspan="4" >
                    <c:if test="${monomerVo.state2!='提交' }">
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"	 onclick="fnqueryCheck('${m.id}')"		>查看</button>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"					>修改</button>
             </c:if>
             
            <c:if test="${monomerVo.state2=='提交' }">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		 style="width:60%"	 onclick="fnqueryCheck('${m.id}')"	>查看</button>
             </c:if>
             
             
              </td>
          </tr>
           
      </c:forEach>
        </tbody>
      </table></td>
      </tr>
  </tbody>
</table>
  </form>
  </br>
  </br>
     

<!-- 主体完工记录 -->
<form action="">
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="margin-left">
  <tbody>
    <tr>
      <td width="3%">
         <c:if test="${monomerVo.state3=='提交' }">
      <div class="yuan"></div>
      </c:if>
         <c:if test="${monomerVo.state3!='提交' }">
      <div class="yuan1"></div>
      </c:if>
      
      </td>
      <td width="44%" class="wz">
      
      主体完工记录
      
      
      </td>
      
      <c:if test="${monomerVo.basisovertime!=null}">
      
       <c:if test="${monomerVo.state3!='提交' }">
      <td width="33%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>确认完工</button>
      </td>
      <td width="20%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>添加</button>
      </td>
      </c:if>
      </c:if>
      
      
      
      
    </tr>
    <tr>
      <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
                   <tr>
            <td width="4%" class="kuang_right1"></td>
            <td width="1%" align="center" valign="middle"></td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
          </tr> 
          <tr>
            <td width="4%" class="kuang_right1">&nbsp;</td>
            <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1">序号</td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1">姓名</td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1">时间</td>
          </tr> 
          <c:forEach items="${list2}"  var="m"  varStatus="status">
          <tr>
            <td height="46" class="kuang_right1">&nbsp;</td>
            <td align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1">${status.count }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.checkpeson }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.inittime }</td>
          </tr>
                    <tr>
            <td height="60" class="kuang_right1">&nbsp;</td>
             <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1"   colspan="4" >
              <c:if test="${monomerVo.state3!='提交' }">
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		 onclick="fnqueryCheck('${m.id}')"	>查看</button>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"					>修改</button>
             </c:if>
                     <c:if test="${monomerVo.state3=='提交' }">
                                  <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:60%"		 onclick="fnqueryCheck('${m.id}')"		>查看</button>
                     
                     </c:if>
             
              </td>
          </tr>
           
      </c:forEach>
        </tbody>
      </table></td>
      </tr>
  </tbody>
</table>
  </form>
  
  </br>
  </br>
  

<!-- 装修完工记录 -->
<form action="">
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="margin-left">
  <tbody>
    <tr>
      <td width="3%">
         <c:if test="${monomerVo.state4!='提交' }">
      <div class="yuan1"></div>
      </c:if>
         <c:if test="${monomerVo.state4=='提交' }">
      <div class="yuan"></div>
      </c:if>
      
      
      
      </td>
      <td width="44%" class="wz">装修完工记录</td>
      
      <c:if test="${monomerVo.basisovertime!=null}">
      <c:if test="${monomerVo.state4!='提交' }">
      <td width="33%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>确认完工</button>
      </td>
      <td width="20%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>添加</button>
      </td>
      </c:if>
      
      
      </c:if>
      
    </tr>
    <tr>
      <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
                   <tr>
            <td width="4%" class="kuang_right1"></td>
            <td width="1%" align="center" valign="middle"></td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
          </tr> 
          <tr>
            <td width="4%" class="kuang_right1">&nbsp;</td>
            <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1">序号</td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1">姓名</td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1">时间</td>
          </tr> 
          <c:forEach items="${list3}"  var="m"  varStatus="status">
          <tr>
            <td height="46" class="kuang_right1">&nbsp;</td>
            <td align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1">${status.count }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.checkpeson }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.inittime }</td>
          </tr>
                    <tr>
            <td height="60" class="kuang_right1">&nbsp;</td>
             <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1"   colspan="4" >
             <c:if test="${monomerVo.state4!='提交' }">
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"	 onclick="fnqueryCheck('${m.id}')"		>查看</button>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"					>修改</button>
             </c:if>
             
                               <c:if test="${monomerVo.state4=='提交' }">
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:60%"	 onclick="fnqueryCheck('${m.id}')"		>查看</button>
             </c:if>
             
             
             
             
             
             
             
             
             
              </td>
          </tr>
           
      </c:forEach>
        </tbody>
      </table></td>
      </tr>
  </tbody>
</table>
  </form>
  
  
  
  </br>
  

<!-- 竣工记录 -->
<form action="">
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="margin-left">
  <tbody>
    <tr>
      <td width="3%">
      <c:if test="${monomerVo.state5!='提交' }">
      <div class="yuan1"></div>
      </c:if>
      <c:if test="${monomerVo.state5=='提交' }">
      <div class="yuan"></div>
      </c:if>
      
      
      </td>
      <td width="44%" class="wz"> 竣工记录</td>
              <c:if test="${monomerVo.basisovertime!=null}">
           <c:if test="${monomerVo.state5!='提交' }">
   
      <td width="33%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>确认完工</button>
      </td>
      <td width="20%">
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		>添加</button>
      </td>
      </c:if>
      </c:if>
    </tr>
    <tr>
      <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
                   <tr>
            <td width="4%" class="kuang_right1"></td>
            <td width="1%" align="center" valign="middle"></td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1"></td>
          </tr> 
          <tr>
            <td width="4%" class="kuang_right1">&nbsp;</td>
            <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td width="12%" align="center" valign="middle" class="bt1 kuang_bottom1">序号</td>
            <td width="22%" align="center" valign="middle" class="bt1 kuang_bottom1">姓名</td>
            <td width="40%" align="center" valign="middle" class="bt1 kuang_bottom1">时间</td>
          </tr> 
          <c:forEach items="${list4}"  var="m"  varStatus="status">
          <tr>
            <td height="46" class="kuang_right1">&nbsp;</td>
            <td align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1">${status.count }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.checkpeson }</td>
            <td align="center" valign="middle" class="kuang_bottom1">${m.inittime }</td>
          </tr>
                    <tr>
              
            <td height="60" class="kuang_right1">&nbsp;</td>
             <td width="1%" align="center" valign="middle">&nbsp;</td>
            <td align="center" valign="middle" class="kuang_bottom1"   colspan="4" >
                <c:if test="${monomerVo.state5!='提交' }">
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"		 onclick="fnqueryCheck('${m.id}')"	>查看</button>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:100px"					>
             修改</button>
             </c:if>
             
                         <c:if test="${monomerVo.state5=='提交' }">
                                      <button type="button" class="layui-btn layui-btn-normal layui-btn-radius"		style="width:60%"		 onclick="fnqueryCheck('${m.id}')"	0	>查看</button>
                         
                         
                         </c:if>
             
              </td>
          </tr>
           
      </c:forEach>
        </tbody>
      </table></td>
      </tr>
  </tbody>
</table>
  </form>
  
 
  </br>
  </br>
  </br>
  </br>
  </br>
  
   
</div>
</body>	


</html>

