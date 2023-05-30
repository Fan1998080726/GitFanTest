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

    <title>投诉平台</title>
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














 <link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
<script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/demo.tables.js"></script>
<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
<script src="js/bootstrap.min.js"></script>






<script type="text/javascript">
/*updatePrjcheckPersoninfoPage.js*/

function doSub(){
// 	alert("ssss");
	var laborUnit = $("#laborUnit").val();
	if(laborUnit==''){
		alert("劳务单位不可为空！");
		return false;
	}
	
	var laborUnitTel = $("#laborUnitTel").val();
	if(laborUnitTel==''){
		alert("劳务单位联系电话不可为空！");
		return false;
	}
	
	var laborUnitName = $("#laborUnitName").val();
	if(laborUnitName==''){
		alert("劳务单位联系人不可为空！");
		return false;
	}
	
	var test1  = $("#test1").val();
	if(test1==''){
		alert("欠薪开始日期不可为空！");
		return false;
	}
	var test2 = $("#test2").val();
	if(test2==''){
		alert("欠薪截止日期不可为空！");
		return false;
	}
	
	

	
	
	
	
	
	
	
	
	
	var  opflage = false ;
	 $(".noNull").each(function(){
	        var name = $(this).attr("name");
	        if($(this).val()==""){
	        alert($(this).attr('notNull')+"不能为空");
	        opflage=false;
            return false;
    		}else{
    	
		  opflage=true;
	        	
	        }
	 })   
	 if(opflage==true){
	        	$(".registerform").submit();
	 }	 
	 

	
	
	
	
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
//				alert("8888"+data);
//				alert("2222"+data.status);
if(data.status=='y'){		
// 	var prjId=document.getElementById("prjId").value;
//	alert("prjid="+prjid);
	alert("提交成功，请关注(哈尔滨建筑业)工种号及时收到反馈通知!");
// 	location.replace("WxServlet?opflag=queryTable&pid="+prjId);
	location.replace("");
}else if(data.status=='n'){
	$.alert("填报失败,请联系系统管理员!");
}

			}
		});
})




function editable1(select1){
   if(select1.value == ""){
      var newvalue = prompt("请输入","");
      if(newvalue){
         addSelected(select1,newvalue,newvalue);
      }
   }
}

function editable2(select1){
   if(select1.value == ""){
      var newvalue = prompt("请输入","");
      if(newvalue){
         addSelected(select1,newvalue,newvalue);
      }
   }
}

function editable(select1){
   if(select1.value == ""){
      var newvalue = prompt("请输入","");
      if(newvalue){
         addSelected(select1,newvalue,newvalue);
      }
   }
}
function addSelected(fld1,value1,text1){
    if (document.all)    {
            var Opt = fld1.document.createElement("OPTION");
            Opt.text = text1;
            Opt.value = value1;
            fld1.options.add(Opt);
            Opt.selected = true;
    }else{
            var Opt = new Option(text1,value1,false,false);
            Opt.selected = true;
            fld1.options[fld1.options.length] = Opt;
    }
}














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
        <div class="titleBox">农民工投诉</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=updateComplaints">
		<input type="hidden" id ="prjId" name="prjId" value="${prjId}"/>				
		<input type="hidden" id ="dept_name" name="dept_name" value="${dept_name }"/>				
		<input type="hidden" id ="openid" name="openid" value="${openid}"/>				
		<input type="hidden" id ="projectName" name="projectName" value="${prjname}"/>				
        <div class="list  no-hairlines-md">
        <ul class="loginForms">
        
        <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         被投诉单位 
		<select  name="unitName">
			<c:forEach items="${UnitEngineeringlists}"  var="m" >
				<option value="${m.name }">${m.name }</option>
			</c:forEach>
		</select>
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
                  <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         被投诉单位联系电话：<input name="unitTel" type="text" placeholder="" pattern=".{5,}"
          validate=""  
          
           class="noNull"   notNull="被投诉单位联系电话" 
   datatype="*1-100"  
          required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
          
          
                  <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
       劳务单位 
		<select  name="laborUnit"	onChange="editable(this);"  id="laborUnit"	> 
			<c:forEach items="${UnitEngineeringlist1}"  var="m" >
				<option value="${m.name }">${m.name }</option>
			</c:forEach>
							<option value="">未有本人所属劳务单位</option>
			
		</select>
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
                  <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
       劳务单位联系人
		<select  name="laborUnitName"	onChange="editable1(this);"	id="laborUnitName" >
			<c:forEach items="${UnitEngineeringlist2}"  var="m" >
				<option value="${m.name }">${m.name }</option>
			</c:forEach>
							<option value="">未有本劳务单位联系人</option>
			
		</select>
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
                  <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
       劳务单位联系人电话
		<select  name="laborUnitTel"	onChange="editable2(this);"	id="laborUnitTel">
			<c:forEach items="${UnitEngineeringlist3}"  var="m" >
				<option value="${m.name }">${m.name }</option>
			</c:forEach>
							<option value="">未有本劳务单位联系人电话</option>
			
		</select>
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
          
          
          
          
          
          
          
          
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         
         
                        <span>欠薪日期：</span>
  <input type="text" class="layui-input" id="test1"	
  name="startime"	   	 placeholder="yyyy-MM-dd"	 readonly="readonly"	
  
  >
  </br>
至
  </br>
  <input type="text" class="layui-input" id="test2" 	name="endtime"	placeholder="yyyy-MM-dd"	
             class="noNull"   notNull="欠薪截止日期" 
   datatype="*1-100"
   readonly="readonly"	>
 
          </div></div>
          </li>
                      <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
       欠薪金额（元）：<input name="oweMoney" type="number"   value="" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控"
                     class="noNull"   notNull="欠薪金额" 
   datatype="*1-100"
           required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
        项目经理姓名：<input name="projectPersonName" type="text"   value="" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控"
                         class="noNull"   notNull="项目经理姓名" 
   datatype="*1-100"
          
           required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         项目经理联系方式：<input name="projectPersonTel" type="text"   value="" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控" 
                               class="noNull"   notNull=" 项目经理联系方式" 
   datatype="*1-100"
          required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
      	班组长姓名：<input name="teamName" type="text"   value="" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控"

                     class="noNull"   notNull=" 班组长姓名" 
   datatype="*1-100"
required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         班组长联系方式：<input name="teamTel" type="text"   value="" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控" required="required"
                      class="noNull"   notNull=" 班组长联系方式" 
   datatype="*1-100"
          
          >
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
          
          
          
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
        投诉人姓名：<input name="persoNname" type="text"   value="${name }" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控"
                      class="noNull"   notNull=" 投诉人姓名" 
   datatype="*1-100"
           required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
        投诉人身份证号：<input name="cardnum" type="text"   value="${cardnum }" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控" 
                         class="noNull"   notNull=" 投诉人身份证号" 
   datatype="*1-100"
          required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
            <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
        工种：<input name="personOne" type="text"   value="${personOne }" placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控" required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
          
          
          
        <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
         联系电话：<input name="personTel" type="text"   value="${phone }"		placeholder="" pattern=".{5,}"
          validate="" data-error-message="疫情防控"
                    validate=""  
                           class="noNull"   notNull=" 联系电话" 
   datatype="*1-100"
           required="required">
          <span class="input-clear-button"> </span>
          </div></div>
          </li>
      
         
          
                  <li class="item-content item-input">
        <div class="item-inner">
<!--         <div class="item-title item-floating-label">现有人数</div> -->
         <div class="item-input-wrap">
        投诉内容：<textarea rows="10"
        name="complaintsContend"
         cols="10"
                            class="noNull"   notNull=" 投诉内容" 
   datatype="*1-100"
         
         ></textarea>
           <span class="input-clear-button"></span></div></div>
          </li>
          
<!--                   <li class="item-content item-input"> -->
<!--         <div class="item-inner"> -->
<!-- <!--         <div class="item-title item-floating-label">现有人数</div> --> 
<!--          <div class="item-input-wrap"> -->
<!--          <input name="other" type="text" placeholder="其他" pattern=".{5,}" validate="" data-error-message="其他" required="required"> -->
<!--           <span class="input-clear-button"></span></div></div> -->
<!--           </li> -->
 
            
          
              <li></li></ul>
              </div> 
        <a href="#" onclick="doSub();"/><div class="button button-large button-fill bigBtn">上报</div> </a>
        </form>
        <!----></div></div>
    </div></div>

      
      
    </div>
    <script src="<%=path %>/css/layui/layui.js" charset="utf-8"></script>
<script>
layui.use('laydate', function(){
  var laydate = layui.laydate;
  var laydate1 = layui.laydate;

  
  //常规用法
  laydate.render({
    elem: '#test1'
  });
  laydate1.render({
	    elem: '#test2'
	  });
 
});
</script>
    
    
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