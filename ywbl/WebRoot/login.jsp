<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String userAgent = request.getHeader("User-Agent");
System.out.println(userAgent);

%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>督办系统</title>
<link href="/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" SRC="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" SRC="js/Cookie.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="images/favicon.ico">
    <link rel="stylesheet" href="lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="css/layuimini.css?v=2.0.4.2" media="all">
    <link rel="stylesheet" href="css/themes/default.css" media="all">
    <link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <style id="layuimini-bg-color">
    </style>
<script type="text/javascript">
	function loginSubmit2(){
      	if ($("#IfSave").prop("checked") == true) {   
            saveCookie("Infonick", $("#username").val(), 365);
           
            saveCookie("InfoIfSave", 1, 365);
       }else {
           deleteCookie("Infonick")
         
           deleteCookie("InfoIfSave")
      }		
		var param={};
		param["login_id"] = $("#username").val();
		param["login_password"] = $("#userpass").val();
		param["code"] = $("#code").val();
		param["iWidth"] =  window.screen.availWidth;
		param["iHeight"] = window.screen.availHeight+40;
	 $.post("log!login.action",param, function(data) {
			if(data==null || data.msg==undefined){
	        	location.replace("project!selectProject.action");
	        }else{
	        	alert(data.msg);
	        	if(data.msg=='用户名不能为空！')
	        		$("#username").focus();
	        	if(data.msg=='密码不能为空！')
	        		$("#userpass").focus();
	        	if(data.msg=='验证码错误！'){
	        		
	        		$("#code").focus();
	        	} 
	        	if(data.msg=='用户名或者密码错误！')
	        		$("#userpass").focus();
	        }
		}); 
	}
	
//初始化
function switchCode(){
	var timenow = new Date();
	$("#codeNum").attr("src","log!imageCode.action?d="+timenow);
}

/* $(function () { 
	$('#username').blur(function () //失去焦点时触发的时间 
	{ 
		alert($('#username').val());
	if ($('#username').val() == 'marry') { 
	$('#q').text('用户名已存在！') 
	} 
	else { $('#q').text('ok!') } 
	});
}); */
</script>
<style type="text/css">
<!--
.STYLE2 {font-size: 13px}
-->
</style>
</head>
<body  >
    <div class="layuimini-loader">
        <div class="layuimini-loader-inner"></div>
    </div>
<div class="main">
<div class="main01">
  <div class="login">
    <form id="form" action="log!login.action" method="post">
    <div>
       <input id="username" name="user.login_id" value=""  type="text" size="20" placeholder="用户名" class="input01"  onkeypress="javascrip:if(window.event.keyCode==13){loginSubmit2();return false;}" />
    </div>
    <div>
      <input id="userpass" name="user.login_password" value=""  type="password" placeholder="密码"  class="input02"  onkeypress="javascrip:if(window.event.keyCode==13){loginSubmit2();return false;}"/>
    </div>
     <div>
         <label>
             <input  name="codeMsg" 
             type="text" placeholder="验证码" 
             id="code"  style="width:100px;"  onkeypress="javascrip:if(window.event.keyCode==13){loginSubmit2();return false;}"/> 
         </label>        
		 <span style="padding-top:5px;">
         <img  src="log!imageCode.action" title="点击更换验证码"  id="codeNum"
			 onclick="javascript: switchCode()"  /> <i></i>
			 </span>
     </div>
        <span class="STYLE2">
     <input type="checkbox" name="IfSave" value="0" class="radio" title="记住用户名" id="IfSave"><label for="IfSave" style="color:#fff;">记住用户名</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--      <label for="IfSave" style="color:#fff;"><a href="bgzn.doc"  style="color: rgb(234, 234, 8);"/>办事指南</a></label> -->
     </span><BR>
      </BR>
     <div>
    <a href="#"><img src="images/login_dl.gif" width="84" height="30"  onclick="loginSubmit2();"/></a>
    <a href="#"><img src="images/login_cz.gif" width="84" height="30"   onclick="reset();"/></a>
    </div>
     <div id="sp">
     	
     </div>
    </form>
  </div>
  <div class="lk"   style="margin-top:20px">版权所有：哈尔滨市住房和城乡建设局&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：信息中心</div>
        <div class="layui-tab-content">
<!--                 <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show"></div> -->
            </div></div>
</div>


<script type="text/javascript" src="js/login/jQuery.js"></script>
<script type="text/javascript" src="js/login/fun.base.js"></script>
<script type="text/javascript" src="js/login/script.js"></script>
<script language="javascript">

       if(getCookie("Infonick")!=null){
              document.getElementById("username").value=getCookie("Infonick");
              if (getCookie("InfoIfSave")==1){
            	//  $("#IfSave").prop("checked") = true;
            	  document.getElementById("IfSave").checked=true;
              }
       }
</script>
<script src="lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['jquery', 'layer', 'miniAdmin','miniTongji'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            miniAdmin = layui.miniAdmin,
            miniTongji = layui.miniTongji;

        var options = {
            iniUrl: "api/init.json",    // 初始化接口
            clearUrl: "api/clear.json", // 缓存清理接口
            urlHashLocation: true,      // 是否打开hash定位
            bgColorDefault: false,      // 主题默认配置
            multiModule: true,          // 是否开启多模块
            menuChildOpen: false,       // 是否默认展开菜单
            loadingTime: 0,             // 初始化加载时间
            pageAnim: true,             // iframe窗口动画
            maxTabNum: 20,              // 最大的tab打开数量
        };
        miniAdmin.render(options);

        // 百度统计代码，只统计指定域名
        miniTongji.render({
            specific: true,
            domains: [
                '99php.cn',
                'layuimini.99php.cn',
                'layuimini-onepage.99php.cn',
            ],
        });

        $('.login-out').on("click", function () {
            layer.msg('退出登录成功', function () {
                window.location = 'page/login-3.html';
            });
        });
    });
</script>
</body>
</html>

