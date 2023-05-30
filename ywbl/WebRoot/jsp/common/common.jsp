<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>


<%-- <c:if test="${dept_id!='43'&&dept_id!='84'}"> --%>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
<%-- </c:if> --%>
<%-- <c:if test="${dept_id=='43'||dept_id=='84'}"> --%>
<%-- <link href="<%=path %>/css/gasbootstrap.min.css" rel="stylesheet"> --%>
<%-- </c:if> --%>


<c:if test="${dept_name=='企业用户组'&&role_id!='198'}">
<link href="<%=path %>/css/layout_corp.css" rel="stylesheet">
</c:if>
<!-- Dialog相关文件 含1个css 4个js（包括jquery） -->
<link href="<%=path %>/js/dialog/skins/chrome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/artDialog.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/jquery.dialog.js"></script>
<script type="text/javascript" src="<%=path %>/js/stoprightkey.js"></script>

<c:if test="${dept_id=='43'||dept_id=='84'}">
<link href="<%=path %>/css/layui/gascss/layui.css" rel="stylesheet" type="text/css" />  

</c:if>
<c:if test="${dept_id!='43'&&dept_id!='84'}">
<link href="<%=path %>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />  
</c:if>
<c:if test="${dept_name!='企业用户组'||role_id=='198'}">
<%-- <c:if test="${dept_id!='43'&&dept_id!='84'}"> --%>
<link href="<%=path %>/css/layout.css" rel="stylesheet">

<%-- </c:if> --%>
</c:if>
 <link href="<%=path %>/css/zj-skin.css" rel="stylesheet" type="text/css" />  
 <script type="text/javascript" src="<%=path %>/css/layui/layui.all.js"></script> 
<!-- 权限按钮操作 -->
<script type="text/javascript">
<!--
$(function(){
	var s = "<%=request.getParameter("PrjNum")==null?"":request.getParameter("PrjNum")%>";
	
	var url=window.location.href;
	var testurl = url;
	if(url.indexOf("?")!=-1){
		url=url.substring(url.lastIndexOf("/")+1,url.indexOf("?"));
	}else if(url.indexOf("#")!=-1){
		url=url.substring(url.lastIndexOf("/")+1,url.indexOf("#"));
	}else{
		url=url.substring(url.lastIndexOf("/")+1,url.length);
	}
	if(s!="")
		url = "tBProjectInfo!queryTBProjectInfo.action?op="+url;
	 else if(testurl.indexOf("testingAgency!queryTestingAgency.action")!=-1){
		url = testurl;
		url=url.substring(url.lastIndexOf("/")+1,url.length);
	} 
	 else if(testurl.indexOf("testingAgencyPersonnel!queryTestingAgencyPersonnel.action")!=-1){
			url = testurl;
			url=url.substring(url.lastIndexOf("/")+1,url.length);
		} 
	$.ajax({
		type:'post',//可选get
		url:'./rolemanager!getUserRight.action',//这里是接收数据的PHP程序
		dataType:'Json',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
		data: {url:url},
		success:function(data){
			if(null!=data&&''!=data){
				if(data[0].rm_add==1){//有添加权限
					//暂存按钮
					//var btn_zc=document.getElementById("btn_zc");
					//if(undefined!=btn_zc){
					//	btn_zc.style.display="none";
					//}
					//保存、提交按钮
					//var btn_zc=document.getElementById("btn_zc");
					//if(undefined!=btn_zc){
					//	btn_tj.style.display="none";
					//}
					var btn_adds=document.getElementsByName("btn_add");
					for(var i=0;i<btn_adds.length;i++){
						btn_adds[i].style.display="";
					}
				}
				if(data[0].rm_update==1){//有修改权限
					var btn_update=document.getElementsByName("btn_update");
					for(var i=0;i<btn_update.length;i++){
						btn_update[i].style.display="";
					}
				}
				if(data[0].rm_del==1){//有删除权限
					var btn_del=document.getElementsByName("btn_del");
					for(var i=0;i<btn_del.length;i++){
						btn_del[i].style.display="";
					}
				}
			}
		},
		error:function(){
		}
	});
});
function loading(msg) {
    layer.msg(msg, {
        icon: 16,
        shade: [0.9, '#000005'],//遮罩的颜色与透明度
        time: false  //取消自动关闭
    })
};
//-->






new Function($('.run').text())()
var index = parent.layer.getFrameIndex(window.name); 
function closeWindows(){
	//给父页面传值
	   	parent.refreshView(1); 
		parent.layer.tips('Look here', '#parentIframe', {time: 5000}); 
	    parent.layer.close(index);
	
}



</script>
<script type="text/javascript">
//对输入金额进行校验
function clearNoNum(obj){
obj.value = obj.value.replace(/[^\d.]/g,"");//清除"数字"和"."以外的字符
obj.value = obj.value.replace(/^\./g,"");//验证第一个字符是数字而不是字符
obj.value = obj.value.replace(/\.{2,}/g,".");//只保留第一个.清除多余的
obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/,'$1$2.$3');//只能输入两个小数
}
</script>