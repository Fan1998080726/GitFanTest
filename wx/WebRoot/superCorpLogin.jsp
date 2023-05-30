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

    <title>哈尔滨建筑业</title>
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


<%-- <script type="text/javascript" src="<%=path %>/consCorpLogin.js"></script> --%>
<script>

/*updatePrjcheckPersoninfoPage.js*/

function doSub(){
 
	$(".registerform").submit();
}
$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		var demo = $(".registerform").Validform({
			ajaxPost:true,	 //异步
			tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}
			},
			callback:function(data){
if(data.message=='y1'){
	
	sessionStorage.setItem("username",data.username);
	
	alert("登录成功！" );	
	
	location.replace("updateSuperCorpPage.jsp");
}else if(data.message=='n'){
	alert("用户名或密码错误,请联系企管站!");
}

			}
		});
})

</script>
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
        <div id="login" class="page"><div class="mainBox">
        <div class="titleBox">监理企业月报表</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=consCorpLogin">
						
						<input  name="states"  value="jl"   type="hidden" >
        <div class="list  no-hairlines-md">
        <ul class="loginForms">
        <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">用户名</div> -->
         <div class="item-input-wrap">
         <input name="username" type="text" placeholder="请输入用户名" pattern=".{5,}" validate="" data-error-message="请输入用户名，最少5位" required="required">
          <span class="input-clear-button"></span></div></div>
          </li>
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">密码</div>  -->
          <div class="item-input-wrap"><input  name="password"  type="password" placeholder="请输入密码" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
              <li></li></ul>
              </div> 
        <a href="#" onclick="doSub();"/><div class="button button-large button-fill bigBtn">登录</div> </a>
        </form>
        <!----></div></div>
    </div></div>

      
      
    </div>
<script>
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