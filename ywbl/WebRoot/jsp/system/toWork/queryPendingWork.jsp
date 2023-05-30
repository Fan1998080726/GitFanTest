<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
 <%@ include file="/jsp/common/commonlayUi.jsp" %>
<%-- <% --%>
<%-- %> --%>
<%-- <link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />   --%>
<%--   <link href="<%=path %>/css/zj-skin.css" rel="stylesheet" type="text/css" />   --%>
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">

 <style>
    .layui-top-box {padding:40px 20px 20px 350px;color:#fff}
    .panel {margin-bottom:17px;background-color:#fff;border:1px solid transparent;border-radius:3px;-webkit-box-shadow:0 1px 1px rgba(0,0,0,.05);box-shadow:0 1px 1px rgba(0,0,0,.05)}
    .panel-body {padding:15px}
    .panel-title {margin-top:0;margin-bottom:0;font-size:14px;color:inherit}
    .label {display:inline;padding:.2em .6em .3em;font-size:75%;font-weight:700;line-height:1;color:#fff;text-align:center;white-space:nowrap;vertical-align:baseline;border-radius:.25em;margin-top: .3em;}
    .layui-red {color:red}
    .main_btn > p {height:40px;}
</style>
	<script>

 
			
 $(function(){
                
　　// test 的点击事件
　　$("#btn1").click(function(){
		location.replace("delay!queryAllDelay.action");
})
                
　　// test 的点击事件
　　$("#btn2").click(function(){
 				location.replace("matters!queryHeyanMatters.action");
		})
　　// test 的点击事件
　　$("#btn3").click(function(){
 				location.replace("matters!queryFeedBackMatters.action");
		})

 })
			
			
			
			
			
	</script>
	 
	</head>

<body  style="background-color: white;" >
 
  
  	  <form class="layui-form"  >
 
  
   <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>待办事项</legend>
</fieldset> 
<!-- <div class="layui-inline" title="打印" lay-event="LAYTABLE_PRINT"><i class="layui-icon layui-icon-print"></i></div> -->






<div class="layuimini-main layui-top-box">
        <div class="layui-row layui-col-space10">


            <div class="layui-col-md3"	id="btn1">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-blue">
                        <div class="panel-body">
                            <div class="panel-title">
                                <span class="label pull-right layui-bg-cyan">实时</span>
                                <h5>延期申请</h5>
                            </div>
                            <div class="panel-content"	align="center">
                                <h1 class="no-margins">
                                <span class="layui-badge "  > ${DelayCount }</span>
                                           </h1>
                                                </br>
<!--                                 <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> 1234</div> -->
<!--                                 <small>当前分类总记录数</small> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md3" 	id="btn2">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-green">
                        <div class="panel-body">
                            <div class="panel-title">
                                <span class="label pull-right layui-bg-orange">实时</span>
                                <h5>核验申请</h5>
                            </div>
                            <div class="panel-content" align="center">
                                <h1 class="no-margins"><span class="layui-badge "  >${heyanCount }  </span></h1>
                                     </br>
<!--                                 <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> 1234</div> -->
<!--                                 <small>当前分类总记录数</small> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md3" 	id="btn3">
                <div class="col-xs-6 col-md-3">
                    <div class="panel layui-bg-orange">
                        <div class="panel-body">
                            <div class="panel-title">
                                <span class="label pull-right layui-bg-green">实时</span>
                                <h5>处室反馈</h5>
                            </div>
                            <div class="panel-content" align="center">
                                <h1 class="no-margins"><span class="layui-badge "  >${feedbackCount }  </span></h1>
                                </br>
<!--                                 <div class="stat-percent font-bold text-gray"><i class="fa fa-commenting"></i> 1234</div> -->
<!--                                 <small>当前分类总记录数</small> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>









<!-- <div align="center"> -->

<%-- <button  class="layui-btn" 	 id="btn1"  type="button"	style="width: 60%">延期申请<span class="layui-badge layui-bg-gray"  > ${DelayCount }</span></button></br></br></br></br> --%>
<!-- <hr class="layui-border-red"> -->
<%-- <button class="layui-btn" 	 id="btn2"   type="button"   style="width: 60%">核验申请<span class="layui-badge layui-bg-gray"  >${heyanCount }   --%>
<!-- </span></button></br></br></br></br> -->
<!-- <hr class="layui-border-red"> -->
<!-- <button class="layui-btn" 	 id="btn3"   type="button"   style="width: 60%">处室反馈<span class="layui-badge layui-bg-gray"  > -->
<%-- ${feedbackCount }  </span></button> --%>
<!-- </br></br></br></br> -->
<!-- <hr class="layui-border-red"> -->
<!-- </div> -->


	 </form> 
 
 
  	
	</body>
	
<%-- 	 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script>  --%>
	 
	<script>
	function bt1(){
		alert("s");
	}
// 	new Function($('.run').text())()
// 	var index = parent.layer.getFrameIndex(window.name); 
// 	//给父页面传值

// 	 function fnIntoView(){
// // 		alert("22222");
// 	   	parent.onmousedown_left(1); 
// // 	   	alert("xxxx");
// 		parent.layer.tips('Look here', '#parentIframe', {time: 5000}); 
// // 		alert("111");
// 	    parent.layer.close(index);
// 	}
	</script>
</html>
