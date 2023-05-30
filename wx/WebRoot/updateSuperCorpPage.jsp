<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<script type="text/javascript">
 

function doSub(){
// 	alert("ssss");
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
	if(data.status=='y'){		
		//	alert("prjid="+prjid);
			alert("填报成功!");
			location.replace("PersonService.html");
		//	location.replace("PersonService.html");
			}else if(data.status=='n'){
			$.alert("填报失败,请联系系统管理员!");
}

			}
		});
})


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
        <div class="titleBox">监理企业月报表</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=superviosrcompany">
 		
 		
 		 <input  name="username"  id="username"  value=""    type="hidden"    > 
         <div class="list  no-hairlines-md">
        <ul class="loginForms">
        
      <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">企业月报表(1-3)月</div> -->
         <div class="item-input-wrap">
         <input name="prjname" type="text" placeholder="工程名称" pattern=".{5,}" 
         id="prjname"
         validate="" data-error-message="工程名称" required="required">
          

         
          <span class="input-clear-button"></span></div></div>
          </li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">从业人数</div>  -->
          <div class="item-input-wrap"><input  name="buildcompany"  type="text" placeholder="建设单位" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">合同金额(万元)</div>  -->
          <div class="item-input-wrap"><input  name="cbcompany"  type="text" placeholder="承包单位" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>

           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">完成产值(万元)</div>  -->
          <div class="item-input-wrap"><input  name="bygcgk"  type="text" placeholder="本月工程概况"   validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="bygcxxjd"  type="text" placeholder="本月工程形象进度" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
           <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">新开工面积(米)</div>  -->
          <div class="item-input-wrap"><input  name="sjwcyjhwcbj"  type="text" placeholder="实际完成与计划完成比较" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="jdfx"  type="text" placeholder="进度分析" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="zlqkfx"  type="text" placeholder="质量情况分析" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="zqcsjxg"  type="text" placeholder="争取措施及效果" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="sbgzl"  type="text" placeholder="申报工作量" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="spgzl"  type="text" placeholder="审批工作量" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="zjs"  type="text" placeholder="增减数" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="sbgck"  type="text" placeholder="申报工程款" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="spgck"  type="text" placeholder="审批工程款" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="ljgck"  type="text" placeholder="累计工程款" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
		  
		  <li class="item-content item-input">
          <div class="item-inner">
<!--           <div class="item-title item-floating-label">承揽工程数量(个)</div>  -->
          <div class="item-input-wrap"><input  name="tzkzfx"  type="text" placeholder="投资控制分析" validate=""  required="required"> <span class="input-clear-button"></span>
          </div></div></li>
          
              <li></li></ul>
              </div> 
        <a href="#" onclick="doSub();"/><div class="button button-large button-fill bigBtn">上报</div> </a>
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