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

function ConsCorpRegister(){
   	
	
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
//	            	var  cardnum =document.form1.cardnum.value;
	   var  tiwen = document.form1.tiwen.value;
// 	   alert("tiwen=="+tiwen)
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

// 				alert(curl);
	         setTimeout(function (){
	        	   	location.replace(curl);
	        	   }, 500);
	       }
	   });
	 	//form1.submit();
	}
	
	
function SuperCorpRegister(){
   	
		
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
//    alert("tiwen=="+tiwen)
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

// 			alert(curl);
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


<div class="all"><div class="img"><img src="<%=path %>/images/bj.png"></div></div>
<div class="all"><div class="h1">施工企业注册</div></div>
<div class="all">
	<ul>
<%--     	<li><input type="text" id="cardnum" name="cardnum" value="${cardnum}" class="input" placeholder="请输入您的身份证号码" /></li> --%>
<%--     	<li><div class="h3">${info }</div></li> --%>
        <li></li>
        <li><input type="button" class="button" value="施工企业注册"  onClick="return ConsCorpRegister();"></li>
        <li></li>
         <li><input type="button" class="button" value="监理企业注册"  onClick="return SuperCorpRegister();"></li>
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
