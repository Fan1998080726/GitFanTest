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

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <title>一地疫码现场管理系统</title>
<%@ include file="/common/common.jsp"%>
  <link rel="stylesheet" href="./CorpSystem_files/framework7.bundle4.4.min.css">
<!--    <link href="https://cdn.bootcss.com/framework7/5.5.0/css/framework7.bundle.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="./CorpSystem_files/icons.css">
    <link rel="stylesheet" href="./CorpSystem_files/app.css">
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
</script>
<style type="text/css">
	ul{
		padding-top: 5% !important;
		font-size: 16px;
		background: #ddd;
	}
	.sli1{
		font-weight:bold;
		color: #333;
	}
	.sli2{
		padding-top: 5px !important;
	}
	.bg{
		background: #ddd;
	}
	hr{
	height:2px;
	}
</style>
</head>

<body style="visibility: visible;">
<div id="app" class="framework7-root">
<div class="view view-main safe-areas">
    <div class="page loginPage page-current" data-name="login">
        <div id="login" class="page-content infinite-scroll"><div class="mainBox">
        <div class="titleBox">工地管理员查询</div> 
         <form id="form1" class="registerform" action="WxServlet?opflag=serchAdmin" method="post">
        <div class="list  no-hairlines-md">
	        <ul class="loginForms">
		        <li class="item-content item-input">
			        <div class="item-inner">
				         <div class="item-input-wrap">
					         	<input name="prjName" id="prjName" type="text" placeholder="请输入工程名称(最少输入4个文字)" validate="" data-error-message="请输入工程名称(最少输入4个文字)" required="required" value="${prjName }">
					          <span class="input-clear-button"></span>
				          </div>
		          	</div>
		         </li>
	        </ul>
	        <div class="button button-large button-fill bigBtn" onclick="serch();">查询</div>
	        <div style="text-align:center;margin-top: 4%;"> <c:if test="${!empty count }">（查询结果：共<b>${count }</b>条）</c:if></div>
          <c:if test="${count==0 }">
          <br>
          		没有查询到工地管理员(可能符合该条件的工程没有注册管理员)
          </c:if>
	          <c:forEach items="${list }" var="l">
		          <ul>
		          	<li class="sli1">${l.get("prjname") }</li>
		          	<li class="sli2">姓名:${l.get("user_name") }</li>
		          	<li>电话:${l.get("phone") }</li>
		          </ul>
				<div style="height:1px;width:100%;border-top:1px solid #ccc;margin-top:15px;"></div>
			</c:forEach>
    </form>
        
        </div>
        </div> </div> </div>
</div>
</body></html>