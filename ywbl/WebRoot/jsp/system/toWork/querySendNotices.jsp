<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%-- <%@ include file="/jsp/common/common.jsp" %> --%>
<%@ include file="/jsp/common/commonlayUi.jsp"%>




<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!-- <link href="<%=request.getContextPath() %>/css/right.css" rel="stylesheet" type="text/css"> -->
<!-- <script src="<%=request.getContextPath() %>/js/color.js" type="text/javascript"></script> -->
<link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
<style type="text/css">
.tfgdiv{
   width:400px;

   height:280px;
   
   margin-left:30%;
   
   margin-top:5%;

   border:	#F0F0F0 1px solid;

   background:#fff;

   color:#333;

   filter:progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4);

   -moz-box-shadow: 2px 2px 10px #909090;

   -webkit-box-shadow: 2px 2px 10px #909090;

   box-shadow:2px 2px 10px #909090;
}
</style>
<script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/demo.tables.js"></script>

<script>
function    locking(){
document.all.ly.style.display="block";
document.all.ly.style.width=document.body.clientWidth;
document.all.ly.style.height=document.body.clientHeight;
document.all.Layer2.style.display='block';
}
function    Lock_CheckForm(theForm){
document.all.ly.style.display='none';
document.all.Layer2.style.display='none';
return   false;
}
function getChildData(){
	$('#listform').submit();
}
function getManager(){
	var user_id=$('input[name=selectFlag]:checked');

	$.closeDialog_queryPrjCheckInfo_selectPage('0;'+user_id.val());
}






function  MattersDel(id,matterId){

	 layer.confirm('是否确认删除?', {
        btn: ['确定', '取消']
    }, function (index, layero) {
		$.ajax({
	        type: "post",
	        url: "matters!DelMatters.action?id="+id+"&matterId="+matterId,
	        success: function(data){
	        	   //无法关闭这个消息框
	            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
	            layer.msg("删除成功!",{ icon: 1, time: 1000 });
	            location.reload();
	        }
	    });
    }
        );
}


function  queryMatters(id,cid,cstate){
 	 layer.open({
  		  type: 2,
  		  title:'事项查看',
  			setOpaqueRate:'1',
    		  area: ['90%', '90%'],
  		  fixed: false, //不固定
  		  maxmin: true,
  		  content: 'matters!queryMattersById.action?id='+id+"&cid="+cid+"&cstate="+cstate
  		}); 
}

function  ReturnData(id,cid,cstate){
 	 layer.open({
  		  type: 2,
  		  title:'事项反馈',
  			setOpaqueRate:'1',
  		  area: ['90%', '90%'],
  		  fixed: false, //不固定
  		  maxmin: true,
  		  content: 'feedback!queryUserFeedbackMatters.action?id='+id+"&cid="+cid+"&cstate="+cstate
  		}); 
}


function  ReturnDataById(id,cid,cstate){
 	 layer.open({
  		  type: 2,
  		  title:'事项详情',
  			setOpaqueRate:'1',
    		  area: ['90%', '90%'],
  		  fixed: false, //不固定
  		  maxmin: true,
  		  content: 'feedback!queryUserFeedbackMattersById.action?id='+id+"&cid="+cid+"&cstate="+cstate
  		}); 
}





function refreshView(){
	
    location.reload();
}

function addSendNotice(){
    var index = layer.open({
        title: '通知公告',
        type: 2,
        shade: 0.2,
        maxmin:true,
        shadeClose: true,
        area: ['90%', '90%'],
        content: 'toWork!UpdateSendNoticesPage.action',
    });
    $(window).on("resize", function () {
        layer.full(index);
    });
    
    
    
//     location.replace("toWork!UpdateSendNoticesPage.action");
}

function onUpdate(id){
    var index = layer.open({
        title: '通知公告',
        type: 2,
        shade: 0.2,
        maxmin:true,
        shadeClose: true,
        area: ['90%', '90%'],
        content: 'toWork!UpdateSendNoticesPage.action?noticeVo.id='+id,
    });
//     $(window).on("resize", function () {
//         layer.full(index);
//     });
//     location.replace("toWork!UpdateSendNoticesPage.action?noticeVo.id="+id);

}

function onQueryById(id){
	
	 layer.open({
 		  type: 2,
 		  title:'公告详情',
 			setOpaqueRate:'1',
 			  area: ['50%', '70%'],
 		  fixed: false, //不固定
 		  maxmin: true,
 		  content: 'toWork!querySendNoticesByID.action?noticeVo.id='+id
 		}); 
	
 

}



function SendNoticesDel(id){

	 layer.confirm('是否确认删除通知公告?', {
       btn: ['确定', '取消']
   }, function (index, layero) {
		$.ajax({
	        type: "post",
	        url: "toWork!SendNoticesDel.action?id="+id,
	        success: function(data){
	        	   //无法关闭这个消息框
	            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
	            layer.msg("删除成功!",{ icon: 1, time: 1000 });
	            alert("删除成功！");
	            location.reload();
	        }
	    });
   }
       );
}

function onUndo(id){

	 layer.confirm('是否确认撤销该条通知公告?', {
       btn: ['确定', '取消']
   }, function (index, layero) {
		$.ajax({
	        type: "post",
	        url: "toWork!UndoNoticesState.action?id="+id,
	        success: function(data){
	        	   //无法关闭这个消息框
	            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
// 	            layer.msg("撤销成功!",{ icon: 1, time: 1000 });
	            alert("撤销成功！");
	            location.reload();
	        }
	    });
   }
       );
}

</script>
<style type="">
/* a {font-size:16px}  */
 a:link {text-decoration:none;} //未访问：蓝色、无下划线  

</style>
</head>
<body	style="background-color: white;"	>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>当前位置： 通知公告</legend>
</fieldset>
     <div  align="right">
 
         <button type="button"	onclick="chaxun()"   class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   
    查询</button>
     <button type="button"	onclick="addSendNotice()"	 class="layui-btn layui-btn-normal">
     	<i class="layui-icon"  style="font-size: 18px">&#xe654;</i>   
     添加</button>
 </div>
    <form action="" method="post" name="prjcheckinfoform" 	class="prjcheckinfoform"	id="prjcheckinfoform">
  	<input value="${str}"   name="str"  type="hidden"/>
      
 			
 			             <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label"> 标题：</label>
      <div class="layui-input-inline">
    <input name="titlecontent" id="titlecontent" 
            value="${ titlecontent }" type="text"  class="layui-input" placeholder="请输入文字...">
      </div>
      
    </div>
  </div>
 			
 			
           
    </form>
<%--     	         <c:if test=" ${dept_name!='企业用户组' }"> --%>



    
<!--table-->



<form aciont="prjcheckinfo!queryPrjcheckinfo.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table     class="layui-table" >
            <thead>
              <tr>
                <th><img src="img/th-img.png"/>序号</th>
                <th><img src="img/th-img.png"/>标题</th>
                <th><img src="img/th-img.png"/>备注</th>
                <th><img src="img/th-img.png"/>时间</th>
           		<th><img src="img/th-img.png"/>操作</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>  >  
	                 <td   >
	                 ${stauts.count}
					 </td>
	                 <td   >
	                 ${m.titlecontent}
					 </td>
	                 <td   >
	                 ${m.noctioncontent}
					 </td>
	                 <td   >
	                 ${m.inittime}
					 </td>
	                 <td   >
	                 <c:if test="${m.state=='02' }">
	                 
	                 	           		<a  onclick="onQueryById('${m.id}')"	  class="layui-btn layui-btn-normal " >
	           							<span class="iconfont"  style="margin-left:5px">&#xe613;</span> 已发</a>
	                 		
	                 
	                 	           		<a  onclick="onUndo('${m.id}')"	  class="layui-btn layui-btn-normal " >
	           							    	<i class="layui-icon"  style="font-size: 18px">&#xe666;</i> 撤销</a>
	                 		
	                 		
	                 		
	                 		
	                 		
	                 </c:if>
	                  <c:if test="${m.state=='01' }">
	                  
	           			<a  onclick="onUpdate('${m.id}')"	  class="layui-btn layui-btn-normal">
     	<i class="layui-icon"  style="font-size: 18px">&#xe642;</i>    编辑</a>
	           			
	           			
	           					<a  href="#" 	onclick="SendNoticesDel('${m.id}')"  class="layui-btn layui-btn-normal">
     <i class="layui-icon"  style="font-size: 18px">&#xe640;</i>   删除</a>
	           			
	           			
	           			</c:if>
					 </td>
					  
	              </tr>
				</c:forEach>
            </tbody>
          </table>
    </form>
          <!-------------显示页数开始------------>
			<DIV ID=TableTail>
				<DIV ID=PageSelectorBar>
					<jsp:include page="/jsp/page/page.jsp" />
				</DIV>
			</DIV> 
		<!-------------显示页数结束------------>
     </div>
     
    
     
  </div>
</body>
<script>
function chaxun(){
	$('#prjcheckinfoform').attr("action","toWork!querySendNotices.action");
	$('#prjcheckinfoform').submit();
}
 
</script>
</html>
