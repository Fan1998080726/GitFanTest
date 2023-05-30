<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
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

    <title>施工现场项目管理</title>
<%@ include file="/common/common.jsp"%>
   <script src="js/jquery-3.3.1.min.js"></script>
        <script src="./CorpSystem_files/vue.min.js"></script>
        <script src="./CorpSystem_files/clipboard.js"></script>
<!--    <script src="js/vue.js"></script>-->
    <link rel="stylesheet" href="./CorpSystem_files/framework7.bundle4.4.min.css">
<!--    <link href="https://cdn.bootcss.com/framework7/5.5.0/css/framework7.bundle.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="./CorpSystem_files/icons.css">
    <link rel="stylesheet" href="./CorpSystem_files/app.css">
<!--    <script src="https://cdn.bootcss.com/framework7/5.5.0/js/framework7.bundle.min.js"></script>-->
    <script src="./CorpSystem_files/framework7.bundle.min.js"></script>
    
    
	<script type="text/javascript"  src="<%=path%>/js/jquery/jquery-1.7.2.min.js"></script>
	<link href="<%=path%>/css/layui/css/layui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/css/layui/layui.all.js"></script>
    
    		<script type="text/javascript" src="<%=path %>/js/dialog/artDialog.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/iframeTools.source.js"></script>
<script type="text/javascript" src="<%=path %>/js/dialog/jquery.dialog.js"></script>
		<link href="<%=path %>/css/zj-skin.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
    
    
    
    
<script type="text/javascript">
 

function doSub(){
	location.replace("WxServlet?opflag=queryCheckMonomer&mid="+'${mid }'+"&proid="+'${proid}');
}
 









var fileSum=1;
var tupian;
var type;
function addFileUpload(table,count){
	alert("111");
	if(fileSum == 5){
		 $.alert("最多只能同时上传5个文件！");
		return;
	}
	
	fileSum = fileSum+1;
	$('#'+table).append('<tr><td width="100px"><label>文件名称：</label></td><td><input id="myFile_'+fileSum+'" type="file" name="myFile_'+fileSum+'"/></td></tr>');
}

	function addFileUpload(table,count){
		
		tupian = table;
		type=count;
      	 layer.open({
	   		  type: 2,
	   		  title:'请选择一家开发企业',
	   			setOpaqueRate:'1',
	   		  area: ['600px', '450px'],
	   		  fixed: false, //不固定
	   		  maxmin: false,
	   		  content: 'WxServlet?opflag=updatePhotoPage'
	   		}); 
      	 
      	 
// 	$.dialog({
// 		id:'uploadFilesId',
// 		url:'WxServlet?opflag=updatePhotoPage',
// 		title: '上传文件', 
// 		width: 500, 
// 		height: 200,
// 		data:{
// 			filePath:'C:/download/'
// 		}
// 		});
} 
function getChildData1(data){

	//上传附件需要用到的
	var json = $.parseJSON(data);
	//alert(json);
//		if(tupian=='cns'){
		//document.getElementById(tupian).innerHTML = '';
//		}
	$(json.files).each(function(){
		//文件路径
			var filePath = this.name;
// 			//获取最后一个.的位置
// 			var index= filePath.lastIndexOf(".");
// 			//获取后缀
// 			var ext  = filePath.substr(index+1);
// 			 if(ext.toLowerCase()!="jpg".toLowerCase()   &&   ext.toLowerCase()!="png".toLowerCase()){
// 		        alert("请上传格式为.jpg和.png图片");  
// 		        return false;
// 		       } 
			$('#'+tupian).append(
					"<tr><td><div align='left'><a href=http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/"+this.name+" target='_bank' class='input1' ><img src='http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/"+this.name+"' style='width: 400px; height: 200px;' /></a><input type='hidden' value="+type+" name='fileType'/><input   id='fileName' type='hidden' value='"+this.name+"' name='fileName'/>"+
					"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()' style='width:50px;'/></div></td></tr>");
		
		
	})
}



function delFile(pf_id,btn){
	 $("tr[id="+pf_id+"]").remove();
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
        <div class="titleBox">工程检查过程</div> 
         <form name="form1" class="registerform"
						action="WxServlet?opflag=superviosrcompany">
 		
 		
 		 <input  name="username"  id="username"  value=""    type="hidden"    > 
         <div class="list  no-hairlines-md">
        <ul class="loginForms">
        
 
      <li class="item-content item-input">
        <div class="item-inner">
 		备注
         <div class="item-input-wrap"><textarea rows="" cols=""></textarea><span class="input-clear-button"></span></div>
          </div>
          </li>
          
          
                <li class="item-content item-input">
        <div class="item-inner">
 		 记录照片
 		 
 		 
 	 
 	 
 	 
 	 					<tr >

						
						<td colspan="3" width="275" height="40" align="left" valign="left">&nbsp;
							<div align="left">
					 
					 
					     <img alt="go" src="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/webp.jpg" />  
					 
					 
					 
					 <input type="button"   value="上传图片"   onclick="addFileUpload('jctp','检查图片')"    />
							</div>
							<table id="jctp" style="margin-top: 8px;"
								class="table2">
								<c:forEach var="m" items="${UploadFile}">
									<c:if test="${m.cf_type == '检查图片'}">
									<tr id="${m.cf_id }">
										<td><div align="left">
												<a href="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/${m.cf_name }"
													target="_bank"> <img
													src="http://<%=request.getServerName()%>:<%=request.getServerPort() %>/download/${m.cf_name }"
													style="width: 400px; height: 200px;" />
												</a>
											</div></td>
										<td><input type="button" value="删除"
											onclick="delFile('${m.cf_id }',this);" style="width: 50px;" />
										</td>
										<input type="hidden" name="fileName" value="${m.cf_name }" />
										<input type="hidden" name="filePath" value="${m.cf_url }" />
										<input type="hidden" name="fileType" value="${m.cf_type }" />
									</tr>
										</c:if>
								</c:forEach>
							</table>
						</td>
					</tr>
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 		 
         <div class="item-input-wrap">
     		</div>
          </div>
          </li>
          
          
          
          
          
              <li></li>
              
              
              </ul>
              </div> 
         <a   onclick="doSub()"><div class="button button-large button-fill bigBtn">返&nbsp;&nbsp;回</div> </a>
        </form>
        <!----></div></div>
    </div></div>

      
      
    </div>
 

</body></html>