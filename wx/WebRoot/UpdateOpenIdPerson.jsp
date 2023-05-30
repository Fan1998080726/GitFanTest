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
<%
	String path = request.getContextPath();
%>
    <title> </title>
<%-- <%@ include file="/common/common.jsp"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.7.2.min.js"></script>
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
<script type="text/javascript">



	
// 	 $(function(){
			if('${jlUnList}'=='[]'){
			
			 	alert("您的人员信息未确认，请联系监理企业及时做信息确认！");		
			 	closeWindow();
			}
// 	 })
	 
	 		var time=0;  
			function closeWindow(){  
				window.setTimeout('closeWindow()',100);  
			 
			   	WeixinJSBridge.call('closeWindow');
			   //this.window.opener=null; //关闭窗口时不出现提示窗口  
			  //window.close();  
			  
			}
	 
	
</script>
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
        
         		       
        
       
        <div class="titleBox" align="center"> 企业变更
 </div> 
         <form name="form1"  
         class="layui-form" 
						action="WxServlet?opflag=roadcompany">
						
        <div class="list  no-hairlines-md" align="center">
        <ul class="loginForms">
 
<!--         <li class="item-content item-input"> -->
        
        
        
        
              <b> 监理企业：</b>
        
                   <select name="creditCode" 	 id="creditCode"	lay-verify="required" lay-search=""   >
          <option value="">直接选择或搜索选择监理企业</option>
         
        	<c:forEach	 items="${jlUnList}"  var="m" >
 	<option  value="${m.fryyzzzch}" >${m.name } </option>
 	</c:forEach>
        </select>
        </br>
           </ul>
              </div> 
                      <div class="button button-large button-fill bigBtn"  onclick="fnConfirm()" >变&nbsp;&nbsp;&nbsp;更 </div> 
        </form>
      </div></div>
    </div></div>

      
      
    </div>


</body>
    <script src="<%=path %>/css/layui/layui.js" charset="utf-8"></script>

<script>

layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  
	  //日期
	  laydate.render({
	    elem: '#date'
	  });
	  laydate.render({
	    elem: '#date1'
	  });
	  
	  //创建一个编辑器
	  var editIndex = layedit.build('LAY_demo_editor');
	 
	  //自定义验证规则
	  form.verify({
	    title: function(value){
	      if(value.length < 5){
	        return '标题至少得5个字符啊';
	      }
	    }
	    ,pass: [
	      /^[\S]{6,12}$/
	      ,'密码必须6到12位，且不能出现空格'
	    ]
	    ,content: function(value){
	      layedit.sync(editIndex);
	    }
	  });
	  
	  //监听指定开关
	  form.on('switch(switchTest)', function(data){
	    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
	      offset: '6px'
	    });
	    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
	  });
	  
	  //监听提交
	  form.on('submit(demo1)', function(data){
	    layer.alert(JSON.stringify(data.field), {
	      title: '最终的提交信息'
	    })
	    return false;
	  });
	 
	  //表单赋值
	  layui.$('#LAY-component-form-setval').on('click', function(){
	    form.val('example', {
	      "username": "贤心" // "name": "value"
	      ,"password": "123456"
	      ,"interest": 1
	      ,"like[write]": true //复选框选中状态
	      ,"close": true //开关状态
	      ,"sex": "女"
	      ,"desc": "我爱 layui"
	    });
	  });
	  
	  //表单取值
	  layui.$('#LAY-component-form-getval').on('click', function(){
	    var data = form.val('example');
	    alert(JSON.stringify(data));
	  });
	  
	});
	 function fnConfirm(){
		 var msg = "是否变更为该企业?"; 

		 if (confirm(msg)==true){ 
				showResult();
		  return true; 

		 }else{ 
			 alert("您取消了操作！");
		  return false; 

		 }
	 
// 		$.confirm("是否确认关联当前微信号？",
// 				function(){
// 			showResult("yes",PrjNum);// 确认按钮回调方法
// 		}, function(){
// 			$.alert("您取消了操作！");
// 		});
 }
 
 function showResult(){
	
// 	 var  username=$("#username").val();
// 	 var  phone=$("#phone").val();
// 	 var  idcard=$("#idcard").val();
	 var  creditCode=$("#creditCode").val();
	 
// 	 alert("creditCode="+creditCode);
 
	 var  openid='${openid}';
 
	   $.ajax( {
	        type : 'POST',
	        url : 'WxServlet?opflag=updatePerson',
	        dataType:"json",
	        data : {openid:openid,creditCode:creditCode},
	        success : function(msg) {
// 	        	alert("msg="+msg);
	        	alert("变更成功，请等待监理企业进行人员确认！");
	    		wx.closeWindow();
	        } 
	    });
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }
 
  
 
</script></html>