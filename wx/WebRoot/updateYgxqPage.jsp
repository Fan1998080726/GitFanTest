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

    <title>一地一码施工现场管理系统</title>
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
        <div class="titleBox">用工需求</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=updateYgxqPage">
						
        <div class="list  no-hairlines-md">
        <ul class="loginForms">
        
        <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         <input name="nowPopulation" type="text" placeholder="现有人数" pattern=".{5,}" validate="" data-error-message="现有人数" required="required">
          <span class="input-clear-button"></span></div></div>
          </li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">用工数量(缺口)</div>  -->
          <div class="item-input-wrap"><input  name="userNick"  type="text" placeholder="请输入用工数量(缺口)" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">工种</div>  -->
          <div class="item-input-wrap">
<!--           <input  name="userType"  type="text" placeholder="工种" validate=""  required="required"> -->
<!--            <span class="input-clear-button"></span> -->
           
             <select name="userType" >
            <option value='木工'>木工</option>
            <option value='钢筋工'>钢筋工</option>
            <option value='砌筑工'>砌筑工</option>
             <option value='砼工'>砼工</option>
            <option value='模板工'>模板工</option>
            <option value='架子工'>架子工</option>
             <option value='抹灰工'>抹灰工</option>
            <option value='水暖工'>水暖工</option>
            <option value='防水工'>防水工</option>
             <option value='电气工'>电气工</option>
            <option value='力工'>力工</option>
            <option value='其他工种'>其他工种</option>
        </select>
           
          </div></div></li>

           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">用工开始时间</div>  -->
          <div class="item-input-wrap"><input  name="startTime"  type="text" placeholder="请输入用工开始时间"   validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">用工结束时间</div>  -->
          <div class="item-input-wrap"><input  name="endTime"  type="text" placeholder="请输入用工结束时间" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">扶贫</div>  -->
          <div class="item-input-wrap">
           <span class="input-clear-button"></span>
           <select name="fp">
           <option value="否"/>请选择是否属于扶贫项目</option>
           <option value="否"/>否</option>
           <option value="是"/>是</option>
           </select>
          </div></div></li>
          
            
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">百大</div>  -->
          <div class="item-input-wrap">
           <select name="bd">
           <option value="否"/>请选择是否属于百大项目</option>
           <option value="否"/>否</option>
           <option value="是"/>是</option>
           </select>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">园区</div>  -->
          <div class="item-input-wrap">
                     <select name="yq">
                       <option value="否"/>请选择是否属于园区项目</option>
           <option value="否"/>否</option>
           <option value="是"/>是</option>
           </select>
          </div></div></li>
            
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">其他</div>  -->
          <div class="item-input-wrap">
           <select name="qt">
           <option value="否"/>请选择是否属于其他项目</option>
           <option value="否"/>否</option>
           <option value="是"/>是</option>
           </select>
          </div></div></li>
          
            
          
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