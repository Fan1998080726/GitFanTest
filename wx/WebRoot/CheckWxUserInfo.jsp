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

    <title>检查人员验证</title>
<%@ include file="/common/common.jsp"%>
<!--    <script src="js/jquery-3.3.1.min.js"></script>-->
        <script src="./CorpSystem_files/vue.min.js"></script>
        <script src="./CorpSystem_files/clipboard.js"></script>
<!--    <script src="js/vue.js"></script>-->
    <link rel="stylesheet" href="./CorpSystem_files/framework7.bundle4.4.min.css">
<!--    <link href="https://cdn.bootcss.com/framework7/5.5.0/css/framework7.bundle.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="./CorpSystem_files/icons.css">
    <link rel="stylesheet" href="./CorpSystem_files/app.css">
<!--    <script src="https://cdn.bootcss.com/framework7/5.5.0/js/framework7.bundle.min.js"></script>-->
    <script src="./CorpSystem_files/framework7.bundle.min.js"></script>


<script type="text/javascript" src="<%=path %>/updateConsCorpPage.js"></script>
    <!-- Your custom app scripts -->
    <script src="./CorpSystem_files/sjcl.js"></script>
    <script src="./CorpSystem_files/base64.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />  

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
        <div id="login" class="page"><div class="mainBox">
        
        
        	<c:if test="${isFocus=='0'}">
        <div class="titleBox" align="center">您还未关注该公众号，请先关注在重新扫码！
	</br>
(长按图片关注公众号)
</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=roadcompany">
						
        <div class="list  no-hairlines-md">
        <ul class="loginForms">
 
        <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">企业月报表(1-3)月</div> -->
         <div class="item-input-wrap">
       
           <span class="input-clear-button"></span></div></div>
          </li>
  
          
          <c:if test="${isFocus=='0' }">
          	        <div	align="center"	>
       <img src="<%=path %>/images/HarbinBuild.jpg">
          </div>
          </c:if>  
              <li></li></ul>
              </div> 
              </c:if>
        	<c:if test="${isFocus=='1'}">
        <div class="titleBox" align="center"> 绑定微信号 
        
       
</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=roadcompany">
						
        <div class="list  no-hairlines-md" align="center">
        <ul class="loginForms">
 
        <li class="item-content item-input">
        <div class="item-inner">
        <div class="item-title item-floating-label">检查人：${personname}</div>
            </div>
          </li>
             <li class="item-content item-input">
        <div class="item-inner">
        <div class="item-title item-floating-label">检查人手机号：${phone}</div>
            </div>
          </li>
     
           </ul>
              </div> 
                      <div class="button button-large button-fill bigBtn"  onclick="fnConfirm()" >确认  </div> 
        </form>
        </c:if>
      </div></div>
    </div></div>

      
      
    </div>


</body>
    <script src="<%=path %>/css/layui/layui.js" charset="utf-8"></script>

<script>

 
	 function fnConfirm(){
		 var msg = "是否确认关联当前微信号?"; 

		 if (confirm(msg)==true){ 
				showResult();
		  return true; 

		 }else{ 
			 alert("您取消了操作！");
		  return false; 

		 }
	 
		$.confirm("是否确认关联当前微信号？",
				function(){
			showResult("yes",PrjNum);// 确认按钮回调方法
		}, function(){
			$.alert("您取消了操作！");
		});
 }
 
 function showResult(){
	
	 var  pid='${pid}';
	 var  openid='${openid}';
	 alert("openid="+openid+"pid"+pid);
	 
	 
	   $.ajax( {
	        type : 'POST',
	        url : 'WxServlet?opflag=relevanceUser',
	        dataType:"json",
	        data : {pid:pid,openid:openid},
	        success : function(msg) {
	        	alert("关联成功！");
	    		wx.closeWindow();
	        }
	    });
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }
 
  
 
</script></html>