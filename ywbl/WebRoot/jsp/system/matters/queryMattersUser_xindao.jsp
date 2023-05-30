<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/common/commonlayUi.jsp" %>
 
 
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
	
 

	  layer.prompt({title: '请填写办结说明：', formType: 2}, function(text, index){
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







</script>
<style type="">
/* a {font-size:16px}  */
 a:link {text-decoration:none;} //未访问：蓝色、无下划线  

</style>
</head>
<body style="background-color: white;" >
<div class="main">
  <div class="breadcrumb">当前位置：新到事项
<!--   疫情防控检查-项目检查 -->
<!--     <a href="prjcheckinfo!batchCode.action">生成</a> -->
<!--     <a href="prjcheckinfo!queryProject.action">进入</a> -->
    </div>
  <div class="main3">
  <!--toolbar-->
  <div class="toolbar">
    <form action="" method="post" name="prjcheckinfoform" 	class="prjcheckinfoform"	id="prjcheckinfoform">
      <ul class="clearfix">
  	<input value="${endcontent}"   name="endcontent"  id="endcontent"	 type="hidden"/>
  	<input value="${str}"   name="str"  type="hidden"/>
     
 
                 <li>
            <span>项目名称 ：</span><input name="prjname" id="prjname" value="${ prjname }" type="text" class="span3" placeholder="请输入文字...">
        </li>
     
      </ul>	
    </form>
    <div class="action">
<%--     	         <c:if test=" ${dept_name!='企业用户组' }"> --%>
    	<input type="button" name="btn_update" onclick="chaxun()" value="" style="background:url(img/search.png) no-repeat;"/>
  


    </div><div class="clear"></div>
  </div>
<!--table-->



<form aciont="prjcheckinfo!queryPrjcheckinfo.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table     class="layui-table" >
            <thead>
              <tr>
                <th><img src="img/th-img.png"/>序号</th>
                <th><img src="img/th-img.png"/>事项类型</th>
                <th><img src="img/th-img.png"/>监督事项</th>
                <th width="15%"><img src="img/th-img.png"/>操作</th>
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
	                 
<!-- 	                 保存时 -->
				<a  href="matters!queryMattersById.action?id=${m.id}&cid=${m.cid}&cstate=${m.cstate}">查看</a>/
	    
<%-- 	    ${m.unitstate } --%>
<%-- 	    ${m.cstate } --%>

<!-- 	    		牵头部门需要反馈 -->
	           <c:if test="${m.unitstate=='01'}">
				<a  href="#"   onclick="endState('${m.id}')" >反馈</a>
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
$('#prjcheckinfoform').attr("action","matters!updateMattersPage.action?builyear="+PrjNum);
$('#prjcheckinfoform').submit();
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
