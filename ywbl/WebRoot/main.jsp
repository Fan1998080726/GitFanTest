<!--  main.jsp 20190824  -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--  <%@ include file="/jsp/common/commonlayUi.jsp" %> --%>

 <%  
	String path = request.getContextPath();
 %>

<head>
<%-- <%@ include file="/jsp/common/common.jsp"%> --%>
<%-- <%@ include file="/jsp/common/commonlayUi.jsp"%> --%>
  <meta name="keywords" content="">
    <meta name="description" content=>
    <meta name="renderer" content="webkit">
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
    <link href="<%=path %>/js/dialog/skins/chrome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/artDialog.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/jquery.dialog.js"></script>
<script type="text/javascript" src="<%=path %>/js/stoprightkey.js"></script>


<link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />  



    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    
    .layui-nav * {
    font-size: 15px;
}
.layuimini-menu-left1 .layui-nav-itemed > .layui-nav-child {
    background-color: #1c88d9 !important;
}
.layui-layout-admin .layuimini-logo {
    background-color: #0084ff  !important;
}
.layui-layout-admin .layuimini-logo h1 {
    color: rgb(255, 255, 255);
}

.layuimini-menu-left1 .layui-nav .layui-nav-item a {
    height: 50px;
    line-height: 40px;
    padding-right: 30px;
}
.layuimini-menu-left1 .layui-nav .layui-nav-item a {
    height: 50px;
    line-height: 55px;
    padding-right: 30px;
}
    </style>
    <style id="layuimini-bg-color">
    </style>
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin"			>

    <div class="layui-header header" >
        <div class="layui-logo  layuimini-logo" style="background-color: #1890ff">
        
        </div>

        <div class="layuimini-header-content">
            <a>
                <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
            </a>

            <!--电脑端头部菜单-->
<!--             <ul class="layui-nav layui-layout-left layuimini-header-menu layuimini-menu-header-pc layuimini-pc-show"> -->
<!--             </ul> -->

 

            <ul class="layui-nav layui-layout-right">


	<c:if test="${user_id!='10359' }">
	
   <li class="layui-nav-item" >
            <b  style="color: black;">承办事项${matterCount1}项</b>
            <b  style="color: black;">办结${matterCount4}项</b>
   	</li>
            <li  class="layui-nav-item" >
                   <img src="img/state2.png" alt="" />
                      <b  style="color: black;">即将到期${matterCount2}项</b>&nbsp;&nbsp;
	</li>
	          <li  class="layui-nav-item" >
                   <img src="img/state3.png" alt="" />
                      <b  style="color: black;" >已超期${matterCount3}项</b>&nbsp;&nbsp;
	</li>
	</c:if>


           
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" data-refresh="刷新"><i class="fa fa-refresh"></i></a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" data-clear="清理" class="layuimini-clear"><i class="fa fa-trash-o"></i></a>
                </li>
                <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                    <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
                </li>
                <li class="layui-nav-item layuimini-setting">
                    <a href="javascript:;">${username }</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;"  onclick="changeUser()"
                            
                            data-title="基本资料" data-icon="fa fa-gears">基本资料<span class="layui-badge-dot"></span></a>
                        </dd>
                        <dd>
                            <a href="javascript:;" layuimini-content-href="usermanager!queryPass.action?state=2" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                        </dd>
                        <dd>
                            <hr>
                        </dd>
                        <dd>
                            <a href="javascript:;" class="login-out">退出登录</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layuimini-select-bgcolor" lay-unselect>
                
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
<!--                     <a href="javascript:;" data-bgcolor="配色方案"><i class="fa fa-ellipsis-v"></i></a> -->
                </li>
            </ul>
        </div>
    </div>

    <!--无限极左侧菜单-->
    <div class="layui-side layui-bg-black layuimini-menu-left1 " style="background-color:#1890ff !important"	>
    <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="multi_module_0"	>
   
   
   
   
         	<c:forEach items="${usermenu }" var="um" varStatus="status">
   
   <li class="layui-nav-item menu-li  "> 
   <a target="_self" href="javascript:;">
        <i class="layui-icon"  style="margin-left:-10px;color: rgb(255, 255, 255) !important" >  ${um.iconCls}</i> 
   
<!--     <i class="fa fa-home"></i>   -->
   <span class="layui-left-nav"		style="font-size:25px;color: rgb(255, 255, 255) !important;"	>	
  <b> &nbsp;&nbsp;${um.title }</b></span>
   <span class="layui-nav-more"></span></a>  
   
   <dl class="layui-nav-child "			 >
             		<c:forEach items="${um.children }" var="child">
   <dd class="menu-dd  " > 
   <a href="javascript:;"   	layuimini-tab-close="other"   layuimini-href="${child.href }" target="_self"	  onclick="tt()"	>  
   <span class="layui-left-nav"   style="margin-left:-10px;color: rgb(255, 255, 255) !important" > &nbsp;&nbsp;
								  ${child.text}		<c:if test="${child.menuId=='100502'}">
									<span class="layui-badge ">${count0 }</span>
									</c:if>
									
<!-- 									90-90*0.4=54-->
									
<!-- 									56 -->
									
									
									<c:if test="${child.menuId=='100503'}"><span class="layui-badge">${count1 }</span></c:if>
									<c:if test="${child.menuId=='100504'}"><span class="layui-badge">${count4 }</span></c:if>
									<c:if test="${child.menuId=='100505'}"><span class="layui-badge">${count3 }</span></c:if>
									<c:if test="${child.menuId=='100506'}"><span class="layui-badge">${count5 }</span></c:if>
									<c:if test="${child.menuId=='100507'}"><span class="layui-badge">${count6 }</span></c:if>
									<c:if test="${child.menuId=='100510'}"><span class="layui-badge">${count7}</span></c:if>
									<c:if test="${child.menuId=='100512'}"><span class="layui-badge">${count8 }</span></c:if>
									<c:if test="${child.menuId=='100513'}"><span class="layui-badge">${count9 }</span></c:if>
									<c:if test="${child.menuId=='100515'}"><span class="layui-badge">${count10 }</span></c:if>
									<c:if test="${child.menuId=='102802'}"><span class="layui-badge">${sendCount}</span></c:if>
 </span></a> 
   
   </dd>
   </c:forEach>
   
    </dl>
    
      </li>
      </c:forEach>
 
 		<script>
								function tt(){
									  $('.close-all').on('click', function() {
									        var tabtitle = $(".layui-tab-title li");
									        var ids = new Array();
									        $.each(tabtitle, function (i) {
									            ids[i] = $(this).attr("lay-id");
									        })
									        //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
									        active.tabDeleteAll(ids);
									    });
							 
									if(loading)
										return false;
									loading=true;
								}
							</script>
 
 
 
 
 
 </ul>
    </div>
    
    
    
    

    <!--初始化加载层-->
    <div class="layuimini-loader">
        <div class="layuimini-loader-inner"></div>
    </div>

    <!--手机端遮罩层-->
<!--     <div class="layuimini-make"></div> -->

    <!-- 移动导航 -->
<!--     <div class="layuimini-site-mobile"><i class="layui-icon"></i></div> -->






















    <div class="layui-body">

        <div class="layuimini-tab layui-tab-rollTool layui-tab" lay-filter="layuiminiTab" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id=""></li>
            </ul>
            <div class="layui-tab-control">
                <li class="layuimini-tab-roll-left layui-icon layui-icon-left"></li>
                <li class="layuimini-tab-roll-right layui-icon layui-icon-right"></li>
                <li class="layui-tab-tool layui-icon layui-icon-down">
                    <ul class="layui-nav close-box">
                        <li class="layui-nav-item">
                            <a href="javascript:;"><span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                                <dd><a href="javascript:;" layuimini-tab-close="current">关 闭 当 前</a></dd>
                                <dd><a href="javascript:;" layuimini-tab-close="other">关 闭 其 他</a></dd>
                                <dd><a href="javascript:;" layuimini-tab-close="all">关 闭 全 部</a></dd>
                            </dl>
                        </li>
                    </ul>
                </li>
            </div>
            <div class="layui-tab-content">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show"></div>
 </div>
        </div>

    </div>
</div>
<script src="lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="js/lay-config.js?v=2.0.0" charset="utf-8"></script>

<%-- <script type="text/javascript" src="css/layui/layui.js"></script> --%>





<script>



function changeUser(){
	
	 layer.open({
		  type: 2,
		  title:'',
			setOpaqueRate:'1',
		  area: ['50%', '50%'],
		  fixed: false, //不固定
		  maxmin: false,
		  content: 'matters!UpdateUserPage.action'
		}); 
}
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
            maxTabNum:10,              // 最大的tab打开数量
        };
        miniAdmin.render(options);

        $('.login-out').on("click", function () {
      
    		
   		 layer.confirm('是否确认退出系统？', {
   		        btn: ['确定', '取消']
   		    }, function (index, layero) {
   				window.location.href = "log!outlog.action";// 确认按钮回调方法
    		    },function(){
   		        layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
   	            layer.msg("取消成功!",{ icon: 1, time: 1000 });
    		    }
   		 	 );
        });
    });
    
    
    
    
    
    

    if('${phone}'==''){
    	 alert("请先完善您的个人信息！");
//          layer.msg('请先完善您的个人信息！');
    	 window.setTimeout(hello,3000); 

// 	   location.replace("matters!UpdateUserPage.action");
// 	   window.location.href = "matters!UpdateUserPage.action";// 确认按钮回调方法
    }
    
    function hello(){ 
   	 layer.open({
   		  type: 2,
   		  title:'完善个人信息',
   		  setOpaqueRate:'1',
   		  area: ['50%', '50%'],
   		  fixed: true, 
   		  maxmin: true,
   		  content: 'matters!UpdateUserPage.action'
   		}); 
    	} 
 
    
    function refreshView(){
    	
        location.reload();
    }
     
    
    
</script>
</body>
</html>
