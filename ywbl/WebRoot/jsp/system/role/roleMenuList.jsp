<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<title>角色菜单管理</title>
<link rel="StyleSheet" type="text/css" href="js/dtree/dtree.css" />
<script type="text/javascript" src="js/dtree/dtree.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css" />
<link rel="stylesheet" type="text/css" href="css/web2.css" />
<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
			
<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
<style type="text/css">
<!--
.tdClass {
	valign: top;
}
input{width:auto;}
-->
</style>
</head>
<body>
<!--table-->
 <div class="da-panel-content">
 <input type="button" onclick="commit()" value="" style="background:url(img/t-save.png) no-repeat; width:62px; height:24px; border:none; margin-top:10px; float:right;"/>
        <div style="clear:both; margin-bottom:5px;"></div>
        <form action="" method="post" id="listform" name="listform" class="registerform" enctype="multipart/form-data" >
        <input type="hidden" name="roleId" id="roleId" value="${roleId }"/>
        <input type="hidden" name="user_code" id="user_code" value="${user_code }"/>
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th><img src="img/th-img.png"/> 功能名</th>
                <th><img src="img/th-img.png"/> 查看</th>
                <th><img src="img/th-img.png"/> 添加</th>
                <th><img src="img/th-img.png"/> 修改</th>
                <th><img src="img/th-img.png"/> 删除</th>
              </tr>
            </thead>
            <tbody>
				<c:forEach items="${list}" var="m" varStatus="i">
				<c:if test="${m.is_child==0 }">
					<tr>
		                <td>${ m.menu_name }</td>
		                <td></td>
		                <td></td>
		                <td></td>
		                <td></td>
		            </tr>
				</c:if>
	            <c:if test="${m.is_child!=0 }">
					<tr class="odd">
		                <td>${ m.menu_name }</td>
		                <td>
		                <input type="hidden" name="menuList[${i.count}].menu_id" value="${m.menu_id }"/>
		                	<c:if test="${null==m.role_id||''==m.role_id }">
		                		<input type="checkbox" id="role_id_${i.count}" name="menuList[${i.count}].role_id" value="" onclick="selmain(this,${i.count});"/>
		                	</c:if>
		                	<c:if test="${null!=m.role_id&&''!=m.role_id }">
		                		<input type="checkbox" id="role_id_${i.count}" name="menuList[${i.count}].role_id" checked="checked" value="${m.role_id }" onclick="selmain(this,${i.count});"/>
		                	</c:if>
		                </td>
		                <td>
		                	<c:if test="${1!=m.rm_add }">
		                		<input type="checkbox" id="rm_add_${i.count}" name="menuList[${i.count}].rm_add" value="0"  onclick="sel(this,${i.count});"/>
		                	</c:if>
		                	<c:if test="${1==m.rm_add}">
		                		<input type="checkbox" id="rm_add_${i.count}" name="menuList[${i.count}].rm_add" checked="checked" value="1" onclick="sel(this,${i.count});"/>
		                	</c:if>
		                </td>
		                <td>${ n.rm_update }
		                	<c:if test="${1!=m.rm_update }">
		                		<input type="checkbox" id="rm_update_${i.count}" name="menuList[${i.count}].rm_update" value="0"  onclick="sel(this,${i.count});"/>
		                	</c:if>
		                	<c:if test="${1==m.rm_update}">
		                		<input type="checkbox" id="rm_update_${i.count}" name="menuList[${i.count}].rm_update" checked="checked" value="1" onclick="sel(this,${i.count});"/>
		                	</c:if>
		                </td>
		                <td>
		                	<c:if test="${1!=m.rm_del }">
		                		<input type="checkbox" id="rm_del_${i.count}" name="menuList[${i.count}].rm_del" value="0" onclick="sel(this,${i.count});" />
		                	</c:if>
		                	<c:if test="${1==m.rm_del}">
		                		<input type="checkbox" id="rm_del_${i.count}" name="menuList[${i.count}].rm_del" checked="checked" value="1" onclick="sel(this,${i.count});"/>
		                	</c:if>
		                </td>
		              </tr>
				</c:if>
				</c:forEach>
            </tbody>
          </table>
          </form>

  </div>
</body>
<script type="text/javascript" src="<%=path%>/js/system/roleMenuList.js"></script>
</html>

