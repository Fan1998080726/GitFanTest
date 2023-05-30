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
<script type="text/javascript">
	function serch(){
		var tt = document.getElementById("select").value;
		if(tt=="no"){
			alert("请选择姓名");
		}else{
			 $.ajax({
		          type: 'POST',
		          url: 'WxServlet?opflag=updateAdmin',
		          data: $('#form1').serialize(), // 表单数据序列化
		          success: function (res) {
		        	 
		        	  if(res=="ok"){
		        		  alert("变更成功");
		        		  window.location.href="EnterpriseServiceSub.html";
		        	  }else {//20200512 fcl
		        		  alert("该人员已在其他工程担任管理人员！");
		        		  window.location.href="EnterpriseServiceSub.html";
		        		  
		        	  }
		          }
		      });
		}
		
	}
</script>
</head>

<body style="visibility: visible;">
<div id="app" class="framework7-root">
<div class="view view-main safe-areas">
    <div class="page loginPage page-current" data-name="login">
        <div id="login" class="page-content infinite-scroll"><div class="mainBox">
        <div class="titleBox">工地管理员变更</div> 
         <form id="form1" class="registerform" action="WxServlet?opflag=updateAdmin" method="post">
         <input type="hidden" name="oldopenid" value="${oldOpenId }">
        <div class="list  no-hairlines-md">
	        <br>
		          <ul>
		          	<li class="sli1">${list[0].get("corpname") }</li>
		          	<li class="sli2">
				          	<select name="select" id="select">
				          	<option value="no"/>请选择姓名</option>
					          	<c:forEach items="${list }" var="l">
							           <option value='${l}'/>${l.get("name") } ${l.get("phone") }</option>
								</c:forEach>
				           </select>
					</li>
		          </ul>
		          <br>
				<div class="button button-large button-fill bigBtn" onclick="serch();">变更</div>
    </form>
        
        </div>
        </div> </div> </div>
</div>
</body></html>