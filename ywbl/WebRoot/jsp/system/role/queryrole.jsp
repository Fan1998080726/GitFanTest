<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/common/common.jsp" %>
<%//////System.out.println("queryrole.jsp...begin"); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>建筑市场监管信息平台</title>
<!-- <link href="<%=request.getContextPath() %>/css/right.css" rel="stylesheet" type="text/css"> -->
<!-- <script src="<%=request.getContextPath() %>/js/color.js" type="text/javascript"></script> -->
<link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
<script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/demo.tables.js"></script>
<script type="text/javascript" src="<%=path %>/jsp/system/role/role.js"></script>
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
</script>
</head>

<body>
<div class="main">
  <div class="breadcrumb">当前位置：角色管理</div>
  <div class="main3">
 
  <!--toolbar-->
  <div class="toolbar">
    <form method="post" id="roleform">
      <ul class="clearfix">
        <li>
            <span>角色名称：</span> <input name="user_code" id="rolecode" value="${ user_code }" type="text" class="span3" placeholder="请输入文字...">
        </li>
        <li>
          <a href="javascript:chaxun()" ><img src="img/search.png"></a>
        </li>
      </ul>
    </form>
    <div class="action">
      <input type="button" name="btn_add" onclick="addPage()" value="" style="background:url(img/t-add.png) no-repeat;display:none"/>
      <input type="button" name="btn_update" onclick="editpage()" value="" style="background:url(img/t-edit.png) no-repeat;display:none"/>
      <input type="button" name="btn_del" onclick="del()" value="" style="background:url(img/t-delete.png) no-repeat;display:none"/>
      
    </div><div class="clear"></div>
  </div>



<!--table-->
<form aciont="rolemanager!query.action" method="post" name="listform" id="listform">
        <div class="da-panel-content">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                    <input type="checkbox" value="option1" id="checkAll">
                </th>
                <th><img src="img/th-img.png"/> 序号</th>
                <th><img src="img/th-img.png"/> 角色名称</th>
                <th><img src="img/th-img.png"/> 所属部门</th>
                <th><img src="img/th-img.png"/> 备注</th>
                <c:if test="${'1'==menu.rm_update }">
                <th><img src="img/th-img.png"/> 操作</th>
                </c:if>
              </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list}" var="rolelist" varStatus="stauts">
	              <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	                <td  style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
			<c:if test="${ rolelist.ROLE_ID eq '1' || rolelist.ROLE_ID eq '2' || rolelist.ROLE_ID eq '3' || rolelist.ROLE_ID eq '4' || rolelist.ROLE_ID eq '5'|| rolelist.ROLE_ID eq '6'|| rolelist.ROLE_ID eq '7'|| rolelist.ROLE_ID eq '8'}">
					<input type="checkbox" name="checkbox2" value="" disabled="disabled"/>
			</c:if>
			<c:if test="${ rolelist.ROLE_ID != '1' && rolelist.ROLE_ID != '2' && rolelist.ROLE_ID != '3' && rolelist.ROLE_ID != '4' && rolelist.ROLE_ID != '5' && rolelist.ROLE_ID != '6' && rolelist.ROLE_ID != '7'&& rolelist.ROLE_ID != '8'}">
					<input type="checkbox" name="selectFlag" value="${rolelist.ROLE_ID }" id="checkbox_id"/>
			</c:if>
	                </td>
	                <td>${ stauts.count }</td>
	                <td>${ rolelist.ROLE_NAME }${ rolelist.ROLE_ID }</td>
	                <td>${ rolelist.DEPT_NAME }</td>
	                <td>${ rolelist.MEMO }</td>
	        <!-- 
	        <c:if test="${ rolelist.ROLE_ID eq '1'}">
				<td class="s3">权限不可改</td>
			</c:if>
			<c:if test="${ rolelist.ROLE_ID != '1'}">
				<td class="s3"><a href="#" onclick="editRole('${rolelist.ROLE_ID }');">[编辑权限菜单]</a></td>
			</c:if>
			 -->
			 <c:if test="${'1'==menu.rm_update }">
				<td class="s3"><a href="#" onclick="editRole('${rolelist.ROLE_ID }');">[编辑权限菜单]</a></td>
			</c:if>
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
</div>








<%-- 
<div class="right">
  <div class="right_a2">
    <div class="title"><h1>当前位置：角色管理</h1></div>
    <div class="rightm">
        <div>


<!--------------------------------------------表格部分开始----------------------------------------------->
<table width="100%" align="center" cellpadding="0" cellspacing="0">
    <tr><td height="30">
    
<!---------------查询行开始----------------------->
              <table width="100%" cellspacing="0" cellpadding="0"  style="border-bottom:1px solid #ffffff; background:#eaf7ff; height:40px;">
                <tr><td align="center">
                  <table><tr>
                  <td>
                    <span class="input1">
                    <form method="post" id="roleform" name="roleform">角色名称：
                     <input name="user_code" type="text" class="input2" id = "usercode" value="${ user_code }"/>
                      <input class="input3" type="image" src="<%=request.getContextPath() %>/img/search.gif"  border="0" border="0"  onclick="chaxun()">
                      </form>
                    </span>
                  </td></tr></table></td>
                </tr>
              </table>
<!---------------查询行结束----------------------->                 
          
<!---------------编辑行开始----------------------->
              <table width="100%" cellspacing="0" cellpadding="0" style="border-bottom:1px solid #ffffff; height:30px;">
                <tr height="30" class="tit">
                    <td widht="50%"><table width="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="6%" height="19" valign="bottom"><div align="center"><img src="<%=request.getContextPath() %>/img/tb.gif" width="14" height="14" /></div></td>
                        <td width="94%" valign="bottom"><span>角色信息列表</span></td>
                      </tr>
                    </table>
                    </td>
                    <td><div align="right"><span>
                      <img src="<%=request.getContextPath() %>/img/add.gif" width="10"  /> <input type="button" value="添加" onClick="addPage()" class="tianjia"/>   &nbsp;
                      <img src="<%=request.getContextPath() %>/img/del.gif" width="10"  /> <input type="button" value="删除" onClick="del()" class="tianjia"/>    &nbsp;&nbsp;
                      <img src="<%=request.getContextPath() %>/img/edit.gif" width="10"  /> <input type="button" value="编辑" onClick="editpage()" class="tianjia"/>   &nbsp;&nbsp;&nbsp;&nbsp;</span>
                        </div>
                    </td>
                </tr>
              </table>
<!---------------编辑行结束----------------------->
  
       <form aciont="" method="post" name="listform2">
        <table width="100%" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
<!-------------标题行开始------------->    
          <tr>
              <td width="4%" class="s1">
                <div align="center">
                  <input type="checkbox"  id="checkAll" />
                </div>
              </td>
              <td width="10%" class="s2"><span>序号</span></td>
              <td width="15%" class="s2"><span>角色名称</span></td>
              <td width="14%" class="s2"><span>备注</span></td>
              <td width="16%" class="s2"><span>操作</span></td>
          </tr>
<!-------------标题行结束------------->

<!-------------列表信息开始------------->
          </tr>
          <c:forEach items="${list}" var="rolelist" varStatus="stauts">
          <tr>
              <td class="s3">
                <div align="center">
					<c:if test="${ rolelist.ROLE_ID eq '1'}">
					<input type="checkbox" name="checkbox2" value="" disabled="disabled"/>
					</c:if>
					<c:if test="${ rolelist.ROLE_ID != '1'}">
					<input type="checkbox" name="selectFlag" value="${rolelist.ROLE_ID }" id="checkbox_id"/>
					</c:if>
                </div>
              </td>
              <td class="s3"><span>${ stauts.count }</span></td>
              <td class="s3">${ rolelist.role_name }</td>
              <td class="s3">${ rolelist.memo }</td>
            <c:if test="${ rolelist.ROLE_ID eq '1'}">
				<td class="s3">权限不可改</td>
			</c:if>
			<c:if test="${ rolelist.ROLE_ID != '1'}">
				<td class="s3"><a href="#" onclick="editRole('${rolelist.ROLE_ID }');">[编辑权限菜单]</a></td>
			</c:if>
              
          </tr>
         </c:forEach>
<!-------------列表信息结束------------->
        </table>
        </form>
<!-------------显示页数开始------------>  
    <DIV ID=TableTail>
		<DIV ID=PageSelectorBar>
			<jsp:include page="/jsp/page/page.jsp" />
		</DIV>
	</DIV>
<!-------------显示页数结束------------>        
        </td>
    </tr>
</table>
<!-------------------------------------------表格部分结束--------------------------------------------->

        </div>     
    </div>
  </div>
</div>


<!--------------------------------添加用户弹出框------------------------------->
<div id="ly" style="position: absolute; top: 0px; left:0px; filter: alpha(opacity=60); background-color: #777;z-index: 2; display: none;"></div>

<div id="Layer2" align="center" class="tanchu">
<table width="640" border="0" cellpadding="0" cellspacing="0" >
<tr >
<td class="tanchutit"><h1 style="padding-left:16px; float:left;" >添加用户</h1>
<div align="right" style="float:right;">
      <a href=JavaScript:; class="STYLE1" onclick="Lock_CheckForm(this);"><img src="<%=request.getContextPath() %>/img/off.png" width="20px" height="20px"></a>
</div></td>
</tr>
<tr>
<td align="center">    
<form action="" name="addform" method="post" id="addform">
<table  cellpadding="0" cellspacing="0" class="tianjiatab">
          <tr>
              <td>登  陆 名     :<input name="" type="text"  class="input2"></td>
              <td>登陆密码 :<input name="" type="text" class="input2"></td>         
          </tr>
          <tr>
              <td>用户姓名:<input name="" type="text" class="input2"></td>
              <td>联系电话:<input name="" type="text" class="input2"></td>         
          </tr>
          <tr>
              <td>电子邮件:<input name="" type="text" class="input2"></td>
              <td>地址        :<input name="" type="text" class="input2"></td>         
          </tr>
          <tr>
              <td>备注        :<input name="" type="text" class="input2"></td>
              <td>用户角色:<input name="" type="text" class="input2"></td>         
          </tr>
</table>
</form>
</td>
</tr>
<tr><td align="center" ><a href="#"><img src="<%=request.getContextPath() %>/img/queding.gif" onclick="adduser()"/></a></td></tr>
<tr><td height="7"></td></tr>
</table>
<!-------------------------------添加用户弹出框结束---------------------------------->


 --%>
</body>
</html>
<%//////System.out.println("queryrole.jsp...end"); %>