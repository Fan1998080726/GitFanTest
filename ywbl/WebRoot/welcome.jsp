<!-- welcome.jsp 20190824 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<%@ include file="/jsp/common/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<c:if test="${dept_name!='企业用户组'&&dept_name!='勘察设计企业' }">
<title>建筑市场监管信息平台</title>
	</c:if>
	
	<c:if test="${dept_name=='企业用户组' }">
<title>网上申报系统</title>
	</c:if>
	<c:if test="${dept_name=='勘察设计企业' }">
<title>勘察设计企业合同登记管理系统</title>
	</c:if>
<style type="text/css">
html{width:100%; height:100%;}
img{border:none;}
</style>
</head>

<body >

<c:if test="${dept_name!='企业用户组'&&dept_name!='勘察设计企业'&&username!='市级管理员'&&dept_name!='建管处'&&role_id!='198'&&dept_name!='松北区住建局'
				&&dept_name!='道里区住建局'&&dept_name!='南岗区住建局'
				&&dept_name!='道外区住建局'&&dept_name!='香坊区住建局'
				&&dept_name!='平房区住建局'&&dept_name!='呼兰区住建局'
				&&dept_name!='阿城区住建局'&&dept_name!='双城区住建局'
				&&dept_name!='依兰县住建局'&&dept_name!='方正县住建局'
				&&dept_name!='宾县住建局'&&dept_name!='巴彦县住建局'
				&&dept_name!='木兰县住建局'&&dept_name!='通河县住建局'
				&&dept_name!='延寿县住建局'&&dept_name!='尚志市住建局'
				&&dept_name!='五常市住建局'&&dept_name!='宾西住建局'
				&&dept_name!='阿城开发区住建局'&&dept_name!='燃气办'&&dept_id!='84'
}">
<c:if test="${complaintsState!='ts' }">

<body style="background:url(img/hy-bg.jpg) no-repeat  center bottom #3787BA;background-size:100% 100% ;">

 
</body></c:if>
 


	</c:if>
<!-- 	fcl 20200423 -->
<c:if test="${username=='市级管理员' }">
<body style="background:url(img/hy-bg.jpg) no-repeat center bottom #3787BA;">
<div style="position:absolute; bottom:130px; left:50%; margin-left:-235px;"><a href="#"><img src="img/hy-btn5.png" /></a></div>
	</c:if>
<!-- fcl 	20200618 -->
<c:if test="${username!='智慧住建'&&dept_id!='84'}">
<c:if test="${dept_name=='建管处'||role_id=='198'||dept_name=='松北区住建局'
				||dept_name=='道里区住建局'||dept_name=='南岗区住建局'
				||dept_name=='道外区住建局'||dept_name=='香坊区住建局'
				||dept_name=='平房区住建局'||dept_name=='呼兰区住建局'
				||dept_name=='阿城区住建局'||dept_name=='双城区住建局'
				||dept_name=='依兰县住建局'||dept_name=='方正县住建局'
				||dept_name=='宾县住建局'||dept_name=='巴彦县住建局'
				||dept_name=='木兰县住建局'||dept_name=='通河县住建局'
				||dept_name=='延寿县住建局'||dept_name=='尚志市住建局'
				||dept_name=='五常市住建局'||dept_name=='宾西住建局'
				||dept_name=='阿城开发区住建局'}">
<c:if test="${complaintsState!='ts' }">
<body style="background:url(images/bjs.png) no-repeat center bottom #3787BA;">
<!-- <div style="position:absolute; bottom:130px; left:50%; margin-left:-235px;"><a href="#"><img src="img/bjs.png" /></a></div> -->


</c:if>
<c:if test="${complaintsState=='ts' }">
	<body style="background:url(img/hy-bg.jpg) no-repeat center bottom #3787BA;">
<div style="position:absolute; bottom:130px; left:50%; margin-left:-235px;"><a href="#"><img src="img/tousu-bj.png" /></a></div>
</body>
</c:if>
	</c:if>
	
	</c:if>
<%-- 	${role_id } --%>
	<c:if test="${dept_name=='企业用户组'}">
	<c:if test="${role_id!='198' }">
<body style="background:url(img/hy-bgcorp.png) no-repeat center bottom #3787BA;">
<div style="position:absolute; bottom:130px; left:50%; margin-left:-235px;"><a href="#"><img src="img/hy-btncorp.png" /></a></div>
	</c:if>
	</c:if>
	
	<c:if test="${username=='智慧住建'}">
	
					 
	
 

 

	</c:if>
	
	
	<c:if test="${username=='燃气办'||role_id=='672'}">
		<c:if test="${role_id!='198'}">
	<body style="background:url(img/gasbeijing.jpg) no-repeat center bottom #3787BA;">
<div style="position:absolute; bottom:130px; left:50%; margin-left:-235px;"><a href="#"><img src="img/hy-btngas.png" /></a></div>
	</c:if>
	</c:if>
	
	
	<c:if test="${dept_name=='勘察设计企业' }">
<body style="background:url(img/hy-bgcorp.png) no-repeat center bottom #3787BA;">
<div style="position:absolute; bottom:130px; left:50%; margin-left:-235px;"><a href="#"><img src="img/hy-btn-kcsj.png" /></a></div>
	</c:if>
</body>
</html>
