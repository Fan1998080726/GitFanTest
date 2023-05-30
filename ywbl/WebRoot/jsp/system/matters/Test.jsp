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


function  endState(id){
	
 

	  layer.prompt({title: '请填写办结说明：', formType: 2}, 
			  function(text, index){
		  layer.confirm('是否对该事项进行办结?', {
		        btn: ['确定', '取消']
		    }, function (index, layero) {
		    	
		 		$("#endcontent").val(text);
				$.ajax({
			        type: "post",
			    	data : $(".prjcheckinfoform").serialize(),  
			        url: "matters!endState.action?id="+id,
			        success: function(data){
			        	   //无法关闭这个消息框
			            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
			            layer.msg("办结成功!",{ icon: 1, time: 1000 });
			            location.reload();
			        }
			    });
		    }
		        );
		  
// 	    layer.close(index);
	  });
	
	
	
	
	
}


function  ReturnDataById(id,cid,cstate){
	 layer.open({
 		  type: 2,
 		  title:'事项详情',
 			setOpaqueRate:'1',
 	 		  area: ['100%', '90%'],
 		  fixed: true, //不固定
 		  maxmin: true,
 		  content: 'feedback!queryUserFeedbackMattersById.action?id='+id+"&cid="+cid+"&cstate="+cstate
 		}); 
}


function refreshView(){
	
    location.reload();
}

function  onUndoMatters(id){
	 layer.confirm('是否确认撤销该条事项?', {
	       btn: ['确定', '取消']
	   }, function (index, layero) {
			$.ajax({
		        type: "post",
		        url: "matters!UndoSendMatters.action?id="+id,
		        success: function(data){
		        	   //无法关闭这个消息框
		            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
//	 	            layer.msg("撤销成功!",{ icon: 1, time: 1000 });
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
<body>
<!-- <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;"> -->
<!--   <legend>当前位置：事项管理</legend> -->
<!-- </fieldset> -->
     <form action="" method="post" name="prjcheckinfoform" 	
     class="layui-form" id="prjcheckinfoform">
     
     <input value="${str}"  name="str"	 type="hidden" />
     

             <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">监督事项：</label>
      <div class="layui-input-inline">
      <input type="text" name="prjname" 
      value="${prjname }"
      lay-verify="title" autocomplete="off" placeholder="请输入监督事项" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">交办人：</label>
      <div class="layui-input-inline">
      <input type="text" name="handover_person" 
      value="${handover_person }"
      lay-verify="title" autocomplete="off" placeholder="请输入交办人" class="layui-input">
      </div>
    </div>
 
  </div>
    </form>
 
    <div  align="right">
    <button type="button"	onclick="chaxun()"   class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe615;</i>   
    查询</button>
    <button type="button"	onclick="updatePrjcheckinfoPage()"   class="layui-btn layui-btn-normal ">
 	<i class="layui-icon"  style="font-size: 18px">&#xe654;</i>   
    添加</button>
     
    </div> 
<form aciont="prjcheckinfo!queryPrjcheckinfo.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table     class="layui-table" >
            <thead>
              <tr>
                <th><img src="img/th-img.png"/>序号</th>
                <th>事项类型</th>
	                <th>监督事项</th>
	                <th>事项来源</th>
	                <th width="8%"  >完成时限</th>
	                <th>分管市领导</th>
	                <th>进展情况</th>
	                <th	width="20%" >牵头单位</th>
	                <th>交办人</th>
                <th width="20%"><img src="img/th-img.png"/>操作</th>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="m" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>  >  
	                 <td   >
	                 ${stauts.count}
					 </td>
	              <td   >
	                 ${m.matter_type }
					 </td>
	                 <td   >
	                 ${m.supervision_matter}
					 </td>
	                 <td   >
	                 ${m.matter_source}
					 </td>
	                 <td   >
	                 ${m.end_time}
					 </td>
	                 <td   >
	                 ${m.charge_lead}
					 </td>
					         <td   >
	                 ${m.feedBackContent}
					 </td>
	                 <td   >
	                 ${m.source_unit}
					 </td>
	                 <td   >
	                 ${m.handover_person}
					 </td>
	         
					 
					 
					 
	                 <td   >
	                 
<!-- 	                 保存时 -->
	                 <c:if test="${m.state=='01'}">
				<a  href="#"  onclick="updateMatters('${m.id}')"  class="layui-btn layui-btn-normal">
     <i class="layui-icon"  style="font-size: 18px">&#xe642;</i>    修改</a> 
     
								<a  href="#" 	onclick="MattersDel('${m.id}','${m.matters_id}')"  class="layui-btn layui-btn-normal">
     <i class="layui-icon"  style="font-size: 18px">&#xe640;</i>   删除</a>
	                 </c:if>
	                 
	                 
	                 
	                 <c:if test="${m.state!='01'}">
					 <a  href="#" onclick="ReturnDataById('${m.id}','${m.cid}','${m.cstate}')"	class="layui-btn layui-btn-normal" >
				 <span class="iconfont"  style="margin-left:5px">&#xe613;</span>&nbsp;查看</a>
<!-- 				还没有做提前办结	            -->
	           <c:if test="${m.state!='06'}">
				<a  href="#"   onclick="endState('${m.id}')"  	class="layui-btn layui-btn-normal" >
				 <span class="iconfont"  style="margin-left:5px">&#xe66c;</span>&nbsp;办结</a>
				 </br>
				 </br>
	      		<a  onclick="onUndoMatters('${m.id}')"	  class="layui-btn layui-btn-normal " >
	           							    	<i class="layui-icon"  style="font-size: 18px">&#xe666;</i> 撤销</a>
				 
				 
				 
				 
	           </c:if>      
<!-- 				提前办结了 -->
	           <c:if test="${m.state=='06'}">
			
				  <button type="button" class="layui-btn layui-btn-disabled">	已办结</button>
				
	           </c:if>      
				
				
				
				
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
/* function ztsj(){
	var s = $("#xmzt").val();
	if(s!=null && s!=""){
		$("#sjsp").text(s);
	}
} */

function xmztupdate(pid,pname,pstatus){
	
	
	
	 var myDate = new Date();
	    var myYear = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
	    var myMonth = myDate.getMonth() + 1; //获取当前月份(0-11,0代表1月)
	    var myToday = myDate.getDate(); //获取当前日(1-31)
	    
	    if(myMonth==1 || myMonth==2 || myMonth==3 || myMonth==4 || myMonth==5 || myMonth==6 || myMonth==7 || myMonth==8 || myMonth==9){
	    	myMonth = "0"+myMonth;
	    }
	    
	    if(myToday==1 || myToday==2 || myToday==3 || myToday==4 || myToday==5 || myToday==6 || myToday==7 || myToday==8 || myToday==9){
	    	myToday = "0"+myToday;
	    }
	  
	    var nowTime;

	    nowTime = myYear + '-' + myMonth + '-' + myToday ;
	    //console.log(nowTime);
	$("#xmzt").val(pstatus);

	$("#stime").val(nowTime);
	$("#prjid").val(pid);
	$("#prjname").val(pname);
	$("#tfgdiv").show();
}
function gb(){
	$("#tfgdiv").hide();
}

function doSubTfg(){
	if($("#xmzt").val()==""){
		alert("请选择项目状态！")
		return;
	}
	$.ajax({
		type:'post',
		url:'tfginfo!addTfgInfo.action',
		data:{"prjid":$("#prjid").val(),"prjname":$("#prjname").val(),"xmzt":$("#xmzt").val(),"stime":$("#stime").val(),"remark":$("#remark").val()},
		success:function(data){
			alert("保存成功！");
			//$("#tfgdiv").hide();
			location.reload("prjcheckinfo!queryPrjcheckinfo.action");
		}
	});
}

/*queryPrjcheckinfo.js 20200310*/
$(function() {
	$("#checkAll").click(function() {
		if (this.checked) {
			$("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox
						$(this).attr("checked", true);
					})
		} else {   //反之 取消全选 
			$("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox
						$(this).attr("checked", false);
					})
		}
	})
 });
function checkAll(mainObj) {
	var name = mainObj.name;
	var names = document.getElementsByName(name);
	if (mainObj.checked == true) {
		for (var i = 0; i < names.length; i++) {
			addAllIdByMainObj(names[i], mainObj.checked);
		}
	} else {
		for (var i = 0; i < names.length; i++) {
			addAllIdByMainObj(names[i]);
		}
	}
}
function chaxun(){
	$('#prjcheckinfoform').attr("action","matters!queryMattersAdmin.action");
	$('#prjcheckinfoform').submit();
}
function del(PrjNum) {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除？",
				function(){
			showResult("yes",PrjNum);// 确认按钮回调方法
		}, function(){
			$.alert("您取消了删除操作！");
		});
	}else {
		$.alert("请选择要删除的条目！");
		return;
	}
}
function queryViewmessage(id) {
$('#prjcheckinfoform').attr("action","prjcheckinfo!getPrjcheckinfoById.action?id="+id);
$('#prjcheckinfoform').submit();
}
/**
 * 删除确认操作
 * @param btn
 */
function showResult(btn,PrjNum){
	var s='';
	$('input[name="selectFlag"]:checked').each(function(){
	   s+=$(this).val()+',';
	});
	$.ajax({
		url:'prjcheckinfo!del.action?ids='+ s,
		success:function(data){
			if(''==data){
				$.alert("部门下有所属权限，不可删除！");
			}else{
				$.alert("操作成功！");
				location.reload("prjcheckinfo!queryPrjcheckinfo.action?PrjNum="+PrjNum);
			}
		},
		error:function(){
			$.alert("编辑失败！");
		}
	});
}
function updatePrjcheckinfoPage(PrjNum) {
// $('#prjcheckinfoform').attr("action","matters!updateMattersPage.action?builyear="+PrjNum);
// $('#prjcheckinfoform').submit();


layer.open({
	  type: 2,
	  title:'事项管理',
		setOpaqueRate:'1',
	  area: ['95%', '95%'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: 'matters!updateMattersPage.action'
	}); 
	
	}
	
function updateMatters(id) {
// $('#prjcheckinfoform').attr("action","matters!updateMattersPage.action?builyear="+PrjNum);
// $('#prjcheckinfoform').submit();


layer.open({
	  type: 2,
	  title:'事项管理',
		setOpaqueRate:'1',
	  area: ['95%', '95%'],
	  fixed: true, //不固定
	  maxmin: true,
	  content: 'matters!updateMattersPage.action?mattersVo.id='+id
	}); 
	
	}
	
	
	
function editpage(ids,builyear) {
$('#prjcheckinfoform').attr("action","prjcheckinfo!updatePrjcheckinfoPage.action?prjcheckinfoVo.id="+ids+"&builyear="+builyear);
$('#prjcheckinfoform').submit();
}
function statistics() {
	$('#prjcheckinfoform').attr("action","prjcheckinfo!statistics.action");
	$('#prjcheckinfoform').submit();
	}

 

</script>
</html>
