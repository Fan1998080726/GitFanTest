<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
    <meta http-equiv="Content-Security-Policy" content="default-src * &#39;self&#39; &#39;unsafe-inline&#39; &#39;unsafe-eval&#39; data: gap: content:">
<!--    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <meta name="theme-color" content="#2196f3">
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <title>施工现场项目管理</title>
<%@ include file="/common/common.jsp"%>
   <script src="js/jquery-3.3.1.min.js"></script>
        <script src="./CorpSystem_files/vue.min.js"></script>
        <script src="./CorpSystem_files/clipboard.js"></script>
<!--    <script src="js/vue.js"></script>-->
    <link rel="stylesheet" href="./CorpSystem_files/framework7.bundle4.4.min.css">
<!--    <link href="https://cdn.bootcss.com/framework7/5.5.0/css/framework7.bundle.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="./CorpSystem_files/icons.css">
    <link rel="stylesheet" href="./CorpSystem_files/app.css">
<!--    <script src="https://cdn.bootcss.com/framework7/5.5.0/js/framework7.bundle.min.js"></script>-->
    <script src="./CorpSystem_files/framework7.bundle.min.js"></script>
    
    
	<script type="text/javascript"  src="<%=path%>/js/jquery/jquery-1.8.3.min.js"></script>
	<link href="<%=path%>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/css/layui/layui.all.js"></script>
    
    
    
<script type="text/javascript">
 

function doSub(){
	location.replace("WxServlet?opflag=queryCheckMonomer&mid="+'${mid }'+"&proid="+'${proid}');
}
 


</script>
<%-- <script type="text/javascript" src="<%=path %>/updateproject.js"></script> --%>
    <!-- Your custom app scripts -->
    <script src="./CorpSystem_files/sjcl.js"></script>
    <script src="./CorpSystem_files/base64.js"></script>

<style>
    .testBtn{
        display: flex;
        flex-wrap: wrap;
    }
    .testBtn .button{
        width: 31%;
        box-sizing: border-box;
        margin: 3px;
    }
</style></head>
<body style="visibility: visible;">

<div id="app" class="framework7-root">
    <!--首页信息-->
    <div class="view view-main safe-areas">
    <div class="page loginPage page-current" data-name="login">
        <div id="login" class="page-content infinite-scroll"><div class="mainBox">
        <div class="titleBox">工程检查过程</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=superviosrcompany">
 		
 		
 		 <input  name="username"  id="username"  value=""    type="hidden"    > 
         <div class="list  no-hairlines-md">
        <ul class="loginForms">
        
      <li class="item-content item-input">
        <div class="item-inner">
 		检查人${mid }
         <div class="item-input-wrap">
         <input name="prjname" type="text"  value="${recordVo.checkpeson}"
         id="prjname"    validate="" data-error-message="检查人" required="required">
          <span class="input-clear-button"></span></div>
          </div>
          </li>
      <li class="item-content item-input">
        <div class="item-inner">
 		检查人联系电话 
         <div class="item-input-wrap">
         <input name="prjname" type="text"  value="${recordVo.checkphone}"
         id="prjname"    validate="" data-error-message="检查人" required="required">
          <span class="input-clear-button"></span></div>
          </div>
          </li>
          
          
                <li class="item-content item-input">
        <div class="item-inner">
 		 记录照片
 		 
 		 
 		 
 		 <c:forEach items="${fileList }"	var="m"	>
 		 <a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/${m.cf_name }" target="_bank">
 		 </br>
                	<img src="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/${m.cf_name }" style="width: 350px; height:200px;"  /></a>
 		 </c:forEach>
 		 
         <div class="item-input-wrap">
     		</div>
          </div>
          </li>
          
          
          
          
          
              <li></li>
              
              
              </ul>
              </div> 
         <a   onclick="doSub()"><div class="button button-large button-fill bigBtn">返&nbsp;&nbsp;回</div> </a>
        </form>
        <!----></div></div>
    </div></div>

      
      
    </div>
<script>


	var  s =  sessionStorage.getItem("username");
document.getElementById("username").value=s;

    var h=location.href;
    var p=h.indexOf('#/');
    var s=h.substr(p+2);
    pushState=false;
    if(p>0 && s!=''){pushState=true;}
    const version='1.0.0.8'
    var mv='?v='+version
    for(let i of ['js/api.js','js/routes.js','js/app.js','js/components.js','js/tempProps.js']){
        document.write("<script src="+(i+mv)+"><\/script>")
    }
    setTimeout(()=>{
        app.view.main.params.pushState = true;
    },200)
</script>

</body></html>