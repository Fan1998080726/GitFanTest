<!-- registerManager.jsp 20200106-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<title>一地“疫”（一）码项目管理系统</title>
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/css.css"/>
<script type="text/javascript" src="<%=path %>/js/scCard.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

function  trim(strInput)
{
    strInput=new String(strInput)
    var iLoop=0;
    var iLoop2=-1;
    var strChr;
    if((strInput == null)||(strInput == "<NULL>"))
        return "";
    if(strInput)
    {
        for(iLoop=0;iLoop<strInput.length-1;iLoop++)
        {
            strChr=strInput.charAt(iLoop);
            if(strChr!=' ')
                break;
        }
        for(iLoop2=strInput.length-1;iLoop2>=0;iLoop2--)
        {
            strChr=strInput.charAt(iLoop2);
            if(strChr!=' ')
                break;
        }
    }
    
    if(iLoop<=iLoop2)
    {
        return strInput.substring(iLoop,iLoop2+1);
    }
    else
    {
        return "";
    }
}
function isLegal(ch)
{
 if(ch >= '0' && ch <= '9')return true;
 if(ch=='.')return true;
 return false;
}
function isAllLegal(str1)
{
 for (i=0; i<str1.length; i++) {
  if (!isLegal(str1.charAt(i)))
  {
   return false;
  }
 }
 return true;
}
var isCommitted = false;//表单是否已经提交标识，默认为false
//增加验证
function check(){
	
	document.getElementById("load1").style.display = "none";
	  document.getElementById("load0").style.display = "block";
	  
	
	if(isCommitted==false){
      
	
  var name=document.getElementById("name").value;
  var cardnum=document.getElementById("cardnum").value;
//   alert("bbb");
  var phone=document.getElementById("phone").value;
//   alert("ccc");
  if(trim(name)=="")
  {
  alert("姓名不能为空");
//   document.form1.name.focus();
  document.getElementById("name").focus();
  return false;
  }
//   alert("111");
   if(trim(cardnum)=="")
   {
   alert("身份证号不能为空");
//    document.form1.cardnum.focus();
   document.getElementById("cardnum").focus();
   return false;
   }
   var s = checkCard(cardnum);
   if(s==false){
   	alert("身份证号格式错误!");
   	return false;
   }
   	
//    alert("222");
   var phone=document.getElementById("phone").value;
   if(trim(phone)=="")
   {
   alert("手机号不能为空");
//    document.form1.phone.focus();
   document.getElementById("phone").focus();
   return false;
   }
isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true11   
    var data = $("#queryForm").serialize(); // 表单序列化
    $.ajax( {
        type : 'POST',
        url : 'WxServlet?opflag=sysManagerNew',
        dataType:"json",
        data : data,
        success : function(msg) {
//         	alert("msg==="+msg.status);
           if("Iterative"==msg.status){
            	alert("身份证号重复！");
            } else if("pass"==msg.status){
            	
//              	alert(msg.status+"11111o");
				var prjId =  document.getElementById("prjId").value;
				var  cardnum = document.getElementById("cardnum").value;
				var username = msg.username;
            	/* var code =  document.getElementById("code").value;
            	
            	var  prjName = document.getElementById("prjName").value;
    var  Corp =document.getElementById("Corp").value;
    var  name = document.getElementById("name").value;
    
    var  phone = document.getElementById("phone").value;
     alert(prjName+" "+Corp+" "+name+" "+cardnum+" "+phone+" "+username); */
    	location.replace("WxServlet?opflag=sysManagerView&prjId="+prjId+"&cardnum="+cardnum+"&username="+username);
            } 
           
        }
    });
	  return ;//返回true让表单正常提交12       
	
	  
    
      }else{         
      	return false;//返回false那么表单将不提交14       
      	}
}
</script>
</head>

<body onload="load()">
<form id="queryForm"   method="post" >
<input id="prjId" type="hidden" name="prjId" value="${prjId}" />
<input id="prjName" type="hidden" name="prjName" value="${prjname}" />
<div class="all"><div class="h2">${prjname} <br>系统管理人员注册</div></div>
<div class="all2">
	<ul>
 
    	<li>
    	<select id="Corp" name="Corp" class="input1" />
    	
    
    		    	<c:forEach 	 items="${UnitEngineeringlists}"  var="m">
<option value="${m.name}" >${m.name}</option> 
    	</c:forEach>
    	
    	
    	    	<c:forEach 	 items="${UnitEngineeringlists2 }"  var="m">
<option value="${m.sname}" >${m.sname}</option>
    	</c:forEach>
    	    	<c:forEach 	 items="${UnitEngineeringlists3 }"  var="m">
<c:if test="${m.jname!=''}">
<option value="${m.jname}" >${m.jname}</option>
</c:if> 
    	</c:forEach>
    	    	<c:forEach 	 items="${UnitEngineeringlists4 }"  var="m">
<c:if test="${m.fbname!=''}">
<option value="${m.fbname}" >${m.fbname}</option>
</c:if> 
    	</c:forEach>
    	    	<c:forEach 	 items="${UnitEngineeringlists5}"  var="m">
<c:if test="${m.lwname!=''}">
<option value="${m.lwname}" >${m.lwname}</option>
</c:if> 
    	</c:forEach>
 
    	    	<c:forEach 	 items="${UnitEngineeringlists6}"  var="m">
<c:if test="${m.sjname!=''}">
<option value="${m.sjname}" >${m.sjname}</option>
</c:if>  
    	</c:forEach>

<%-- <option value="${buildCorpName}" >${buildCorpName}</option> --%>
<%-- <option value="${consCorpName}" >${consCorpName}</option> --%>
<%-- <option value="${superCorpName}" >${superCorpName}</option> --%>
<option value="其他单位" >其他单位</option>
</select>
</li>
    	<li><input id="name" type="text" name="name" class="input1" value="${name}" placeholder="请输入姓名" /></li>
    	<li><input id="cardnum" type="text" name="cardnum" class="input1" value="${cardnum}"  placeholder="请输入您的身份证号" /></li>
    	<li><div class="error">${info}</div></li>
        <li><input id="phone" type="text" name="phone" class="input1" value="${phone}"  placeholder="请输入手机号" /></li>
        <li>
    	<select id="ifbeen" name="ifbeen" class="input1" >
<option value="否" >没到过疫情严重地区旅居</option>
<option value="是" >到过疫情严重地区旅居</option>
</select>
</li>
	<li>
	    	<select id="ifaffirm" name="ifaffirm" class="input1" >
				<option value="否" >无接触确认或疑惑病例人员</option>
			<option value="是" >接触确认或疑惑病例人员</option>
</select>
</li>
	<li>
	    	<select id="iffever" name="iffever" class="input1" >
<option value="否" >没有发热、乏力、干咳等症状</option>
<option value="是" >有发热、乏力、干咳等症状</option>
</select>
</li>
     <select  name="personType" class="input1"  id="personType" onchange='changeCity()' >

</select>

	

    <select name="personOne" class="input1" id='personOne'>
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
            <option value='建筑电工'>建筑电工</option>
            <option value='建筑起重司索信号工'>建筑起重司索信号工</option>
            <option value='施工升降机司机'>施工升降机司机</option>
            <option value='建筑架子工'>建筑架子工</option>
            <option value='建筑起重机械安装拆卸工'>建筑起重机械安装拆卸工</option>
            <option value='塔式起重机司机'>塔式起重机司机</option>
            <option value='其他工种'>其他工种</option>
        </select>
    </ul>
</div>
</form>
       
             <div id="load0" style="display:none;" align="center">数据传输中....</div>
<div align="center" id="load1" style="display:block;">
        <li><input type="button" class="button" value="提交信息"  onClick="return check();"></li>
 </div>
        
<%-- <iframe src="prjcheckinfo!wxloginIn.action?prjId=${prjId}" name="main1" width="800px;" height="1000px;">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe> --%>


<script>
        var province=document.getElementById("personType");
        var city=document.getElementById("personOne");
        var arr_prov=new Array(new Option("劳务人员","劳务人员"),new Option("管理人员","管理人员"));
        var arr_city=new Array();
        arr_city[0]=new Array(new Option("木工",'木工'),new Option("钢筋工",'钢筋工'),new Option("砌筑工",'砌筑工')
        ,new Option("砼工",'砼工'),new Option("模板工",'模板工'),new Option("架子工",'架子工')
        ,new Option("抹灰工",'抹灰工'),new Option("水暖工",'水暖工'),new Option("防水工",'防水工')
        ,new Option("电气工",'电气工'),new Option("力工",'力工'),new Option("其他工种",'其他工种'));
        arr_city[1]=new Array(new Option("建造师",'建造师'),new Option("总监理工程师",'总监理工程师'),new Option("技术负责人",'技术负责人'),new Option("其他",'其他'));
        //动态载入所有省份
        function load(){
            for(var i=0;i<arr_prov.length;i++){
                province.options[i]=arr_prov[i];
            }
        }
        //选中省份之后，根据索引动态载入相应城市
        function changeCity(){
            //清空上次的选项
            city.options.length=0;
            //获取省一级的下拉列表选中的索引
            var index=province.selectedIndex;
            for(var i=0;i<arr_city[index].length;i++){
                city.options[i]=arr_city[index][i];
            }
        }
    </script>
</body>
</html>
