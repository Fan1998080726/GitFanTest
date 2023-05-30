<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- /*checkPeronRegister.jsp 20200331*  	20200331 txb / -->
<!DOCTYPE html>
<!-- saved from url=(0092)http://bigapp.scedu.net/yqtb/mobile/?openid=_Z5um5kjcSk2Z3_GmpSUWK43CirFybhq4lMHXVgTyXU3pzj9 -->
<html class="md device-pixel-ratio-1 device-desktop device-windows"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="Content-Security-Policy" content="default-src * &#39;self&#39; &#39;unsafe-inline&#39; &#39;unsafe-eval&#39; data: gap: content:">
<!--    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">-->
    <meta name="viewport" content="width=device-width, ipnitial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <meta name="theme-color" content="#2196f3">
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <title>一地一码现场管理系统</title>
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


<script type="text/javascript" src="<%=path %>/checkPeronRegister.js"></script>
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
        <div class="titleBox">检查人员注册登记</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=checkPeronRegister">
						
        <div class="list  no-hairlines-md">
        <ul class="loginForms">
        
        <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">企业月报表(1-3)月</div> -->
         <div class="item-input-wrap">
         <input name="name" id="name" type="text" placeholder="请输入姓名" validate="" data-error-message="请输入姓名" required="required">
          <span class="input-clear-button"></span></div></div>
          </li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">从业人数</div>  -->
          <div class="item-input-wrap"><input id="phone"   name="phone"  type="text" placeholder="请输入电话" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
              <select name="dept_name" id="dept_name" >
         <option value="" >请选择所属区县</option>
         <option value="道里区住建局" <c:if test="${dept_name=='道里区住建局' }">selected</c:if>>道里区住建局</option> 
         <option value="南岗区住建局" <c:if test="${dept_name=='南岗区住建局' }">selected</c:if>>南岗区住建局</option> 
         <option value="道外区住建局" <c:if test="${dept_name=='道外区住建局' }">selected</c:if>>道外区住建局</option> 
         <option value="香坊区住建局" <c:if test="${dept_name=='香坊区住建局' }">selected</c:if>>香坊区住建局</option>
         <option value="平房区住建局" <c:if test="${dept_name=='平房区住建局' }">selected</c:if>>平房区住建局</option> 
         <option value="松北区住建局" <c:if test="${dept_name=='松北区住建局' }">selected</c:if>>松北区住建局</option> 
         <option value="呼兰区住建局" <c:if test="${dept_name=='呼兰区住建局' }">selected</c:if>>呼兰区住建局</option> 
         <option value="阿城区住建局" <c:if test="${dept_name=='阿城区住建局' }">selected</c:if>>阿城区住建局</option> 
         <option value="双城区住建局" <c:if test="${dept_name=='双城区住建局' }">selected</c:if>>双城区住建局</option> 
         <option value="依兰县住建局" <c:if test="${dept_name=='依兰县住建局' }">selected</c:if>>依兰县住建局</option> 
         <option value="方正县住建局" <c:if test="${dept_name=='方正县住建局' }">selected</c:if>>方正县住建局</option> 
         <option value="宾县住建局" <c:if test="${dept_name=='宾县住建局' }">selected</c:if>>宾县住建局</option> 
         <option value="巴彦县住建局" <c:if test="${dept_name=='巴彦县住建局' }">selected</c:if>>巴彦县住建局</option> 
         <option value="木兰县住建局" <c:if test="${dept_name=='木兰县住建局' }">selected</c:if>>木兰县住建局</option> 
         <option value="通河县住建局" <c:if test="${dept_name=='通河县住建局' }">selected</c:if>>通河县住建局</option> 
         <option value="延寿县住建局" <c:if test="${dept_name=='延寿县住建局' }">selected</c:if>>延寿县住建局</option> 
         <option value="尚志市住建局" <c:if test="${dept_name=='尚志市住建局' }">selected</c:if>>尚志市住建局</option> 
         <option value="五常市住建局" <c:if test="${dept_name=='五常市住建局' }">selected</c:if>>五常市住建局</option> 
         <option value="宾西住建局" <c:if test="${dept_name=='宾西住建局' }">selected</c:if>>宾西住建局</option> 
         <option value="木兰县住建局" <c:if test="${dept_name=='木兰县住建局' }">selected</c:if>>木兰县住建局</option> 
         <option value="阿城开发区住建局" <c:if test="${dept_name=='阿城开发区住建局' }">selected</c:if>>阿城开发区住建局</option> 
        </select>
        
              <li></li></ul>
              </div> 
        <a href="#" onclick="doSub();"/><div class="button button-large button-fill bigBtn">上报</div> </a>
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