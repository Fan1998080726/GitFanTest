<!-- wxlogin.jsp 20200106-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
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
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/scCard.js"></script>
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
//增加验证
function check(){
	document.getElementById("load1").style.display = "none";
	  document.getElementById("load0").style.display = "block";
//  	alert("into check()");
  /* var cardnum=document.form1.cardnum.value;
//    alert(cardnum 	);
   if(trim(cardnum)=="")
   {
   alert("身份证号不能为空");
   document.form1.cardnum.focus();
   return false;
   }
   var s = checkCard(cardnum);
   if(s==false){
   	alert("身份证号格式错误!");
   	return false;
   } */
   	
   var tiwen=document.form1.tiwen.value;
//    alert(tiwen 	);
   if(trim(tiwen)=="")
   {
   alert("体温不能为空");
   document.form1.tiwen.focus();
   return false;
   }else{
	   
	   var re=/^\d{1,2}([\.]\d{0,2})?$/;
      if(re.exec(tiwen) == null){
		alert("温度格式不对,请输入数字!");
		return false;
      }
   }
		   
		
   var data = $("#form1").serialize(); // 表单序列化
   $.ajax( {
       type : 'POST',
        /* url : 'prjcheckinfo!wxloginIn.action', */ 
          url : 'WxServlet?opflag=wxloginIn', 
       dataType:"json",
       data : data,
       success : function(msg) {
    		var prjname = document.form1.prjname.value;
           	var name = msg.name;
//            	var  cardnum =document.form1.cardnum.value;
   var  tiwen = document.form1.tiwen.value;
   var phone =msg.phone;
	var personOne = msg.personOne;
   var  prjId = msg.prjId;
   var  corpname = msg.corpname;
   var  personType = msg.personType;
   var curl ="";
   var cardnum =msg.cardnum;
          if("y"==msg.status){
        	 	curl="WxServlet?opflag=passA";
           }else{
        	   alert("填报体温出现异常，请联系系统管理员，电话：13351802533");
           } 
         curl = curl +"&prjId="+prjId
			+"&prjname="+prjname
   			+"&name="+name
   			+"&tiwen="+tiwen
   			+"&personType="+personType
   			+"&corpname="+corpname
   			+"&phone="+phone
   			+"&personOne="+personOne
   			+"&cardnum="+cardnum;
			curl = curl.replace(/#/g,"号");

         setTimeout(function (){
        	   	location.replace(curl);
        	   }, 500);
       }
   });
 	//form1.submit();
}


</script>
</head>

<body>
<form name="form1" id="form1" method="post" action="prjcheckinfo!wxloginIn.action">
<input type="hidden" name="prjId" value="${prjId}" />
<input type="hidden" id="prjname" name="prjname" value="${prjname}" />
<input type="hidden" id="buildCorpName" name="buildCorpName" value="${buildCorpName}" />
<input type="hidden" id="consCorpName"  name="consCorpName" value="${consCorpName}" />
<input type="hidden" id="superCorpName"  name="superCorpName" value="${superCorpName}" />
<input type="hidden" id="corpname"  name="corpname" value="${corpname}" />
<input type="hidden" id="state"  name="state" value="${state}" /> 
<input type="hidden" id="personType"  name="personType" value="${personType}" />
<input type="hidden" id="personOne"  name="personOne" value="${personOne}" />

<div class="all"><div class="img"><img src="<%=path %>/images/bj.png"></div></div>
<div class="all"><div class="h1">${prjname}</div></div>
<div class="all">
	<ul>
<%--     	<li><input type="text" id="cardnum" name="cardnum" value="${cardnum}" class="input" placeholder="请输入您的身份证号码" /></li> --%>
<%--     	<li><div class="h3">${info }</div></li> --%>
        <li><input type="text" id="tiwen" name="tiwen" value="${tiwen}"  class="input" placeholder="请输入您的体温" /></li>
            <div id="load0" style="display:none;" align="center">数据传输中....</div>
<div align="center" id="load1" style="display:block;">
        <li><input type="button" class="button1" value="提交信息"  onClick="return check();"></li>
        </div>
    </ul>
</div>

<div class="all1">
<%-- <c:if test="${managerOp=='1' }"> --%>
<%-- <div class="zhuce"><a href="WxServlet?opflag=updateRegisterPersonPage&prjId=${prjId}">现场人员注册</a></div> --%>
<%-- </c:if> --%>
<%-- <c:if test="${managerOp=='0' }"> --%>
<!-- <div class="zhuce"><a href="registerManager.jsp">系统管理人员注册</a></div> -->
<%-- </c:if> --%>
</div>
</body>
</from>
</html>
