<!-- wxlogin.jsp 20200106-->
<%-- <%System.out.println("into registerPerson.jsp.jsp.........."); %> --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

// System.out.println("into registerPerson.jsp................");
String path = request.getContextPath();
%>
<!doctype html>
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
//增加验证
function check(){
	document.getElementById("load1").style.display = "none";
	  document.getElementById("load0").style.display = "block";
	  
	  var name=document.getElementById("name").value;
	  var cardnum=document.getElementById("cardnum").value;
	  var phone=document.getElementById("phone").value;
	  
	  var prjname=document.getElementById("prjname").value;
	  var prjId=document.getElementById("prjId").value;
	  var corpname=document.getElementById("corpname").value;
	  
	  if(trim(name)=="")
	  {
	  alert("姓名不能为空!");
	  document.getElementById("name").focus();
	  return false;
	  }
	   if(trim(cardnum)=="")
	   {
	   alert("身份证号不能为空!");
	   document.getElementById("cardnum").focus();
	   return false;
	   }
	    var s = checkCard(cardnum);
	    if(s==false){
	    	alert("身份证号格式错误!");
	    	return false;
	    }
	    	
	   if(trim(phone)=="")
	   {
	   alert("手机号不能为空!");
	   document.getElementById("phone").focus();
	   return false;
	   }else{
		   var myreg=/^[1][3,4,5,6,7,8,9][0-9]{9}$/;
		   if (!myreg.test(phone)) {
			   alert("手机号码格式错误!");
			   return false;
			   }
	   }
 	//form1.submit();
// 		alert(name+"   "+cardnum+"   "+phone);
		
	   var data = $("#form1").serialize(); // 表单序列化
	   $.ajax( {
	       type : 'POST',
	        url : 'WxServlet?opflag=updateRegisterPerson', 
	       dataType:"json",
	       data : data,
	       success : function(msg) {
	   var curl ="";
	           	curl="WxServlet?opflag=wxregisterFinished";
	         setTimeout(function (){
	        	   	location.replace(curl+"&prjId="+prjId
	        	   			+"&prjName="+prjname
	        	   			+"&corpname="+corpname
	        	   			+"&tiwen="+document.getElementById("tiwen").value
	        	   			+"&state="+document.getElementById("state").value
	        	   			+"&cardnum="+cardnum);
	        	   }, 500); 
	       }
	   });
}

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
//////////////////////////////
</script>
</head>
<body onload="load()">
<form id="form1"   method="post" >
<input type="hidden" id="prjId" name="prjId" value="${prjId}" />
<input type="hidden" id="prjname" name="prjname" value="${prjname}" />
<input type="hidden" id="tiwen" name="tiwen" value="${tiwen}" />
<input type="hidden" id="state"  name="state" value="${state}" />

 <div class="all"><div class="img"><img src="<%=path %>/images/index_01.png"></div></div>
<div class="all"><div class="h1">${prjname} 人员注册</div></div>
<div class="all">
	<ul>
    	<li>      
    	<select  name="corpname" class="input1"  id="corpname"/>
<option value="${buildCorpName}" >${buildCorpName}</option>
<option value="${consCorpName}" >${consCorpName}</option>
<option value="${superCorpName}" >${superCorpName}</option>
<option value="其他单位" >其他单位</option>
</select>
    	</li>
    	<li><input type="text" class="input1" id="name" name="name" value="${name}" placeholder="请输入姓名" /></li>
    	<li><input type="text" class="input1" id="cardnum" name="cardnum" value="${cardnum}"  placeholder="请输入您的身份证号" /></li>
    <li><div class="h3">${info }</div></li>
    	<li><input type="text" class="input1" id="phone" name="phone" value="${phone}"  placeholder="请输入您的手机号" /></li>

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
        <li>
        <select  name="personType" class="input1"  id="personType" onchange='changeCity()'/>

</select>
</li>
<li>
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
            <option value='力工'>力工</option>
            <option value='其他工种'>其他工种</option>
        </select>
    </div>
        </li>
        
        
              <div id="load0" style="display:none;" align="center">数据传输中....</div>
<div align="center" id="load1" style="display:block;">
        <li><input type="button" class="button1" value="提交信息"  onClick="return check();"></li>
        
        </div>
        
        
        
    </ul>
</div>

<div>
<!--         <select class='prov' id='prov' onchange='changeCity()'> -->
<!--             <option value=''>--请选择省--</option> -->
<!--         </select> -->
    
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
</from>
</html>
