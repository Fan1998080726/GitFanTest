<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%@page import="com.sdkj.util.context.Pagination"%>
<%
	Pagination pagination = (Pagination)request.getAttribute("pm"); 
%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<script language="javascript" src="<%=request.getContextPath() %>/jsp/page/pager-taglib.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/jsp/page/pagestyle.css" />

<form action="./${pm.url }" method="post">
	<%
	Map map = pagination.getHiddenMap();
	if(map !=null&&!map.isEmpty()){
	Iterator it = map.entrySet().iterator();  
	while (it.hasNext()) {   
		 Map.Entry entry = (Map.Entry)it.next();   
         Object key = entry.getKey();   
         
         Object value = entry.getValue();
	%>
	<input type="hidden" name="<%=key %>" id="<%=key %>" value="<%=value %>" />

	<%} }
	
	
	
	Map map1 = pagination.getHiddenArrayMap();
	if(map1 !=null&&!map1.isEmpty()){
	Iterator it = map1.entrySet().iterator();  
	while (it.hasNext()) {   
		 Map.Entry entry = (Map.Entry)it.next();   
         Object key = entry.getKey();   
         
         Object value = entry.getValue();
         if(value!=null){
         String[] s = (String[])value;
         for(int i=0;i<s.length;i++){
        	 
         
	%>
	<input type="hidden" name="<%=key %>" id="<%=key+String.valueOf(i) %>" value="<%=s[i] %>" />

	<%} }
	}
	}
	%>
	<input type="hidden" id="totalCount" value="${pm.totalCount}" />
	<pg:pager id="pm" maxPageItems="${pm.pageSize}" items="${pm.totalCount}"
		export="offset,currentPageNumber=pageNumber">
		<DIV ID="PageArea">
			<c:choose>
				<c:when test="${pm.totalCount ne 0}">
					<div class="manu">
						<pg:first><a href="javascript:doPostBack('pm',1)">
								<nobr>首页</nobr>
							</a></pg:first>
						<pg:prev export="first"><a href="javascript:doPostBack('pm',${first})">上一页</a></pg:prev>
						<pg:pages export="pageNumber,first">
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber}">
									<span class="current">${pageNumber }</span>
								</c:when>
								<c:otherwise>
									<a href="javascript:doPostBack('pm',${first})">${pageNumber}</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next export="first"><a href="javascript:doPostBack('pm',${first})">下一页</a></pg:next>
						<pg:last export="first"><a href="javascript:doPostBack('pm',${first})">
								<nobr>末页</nobr>
							</a></pg:last>

						<pg:skip pages="${pageNumber}">
							<input name="pm.offset" type="hidden" value="0" id="offset" />
							<input name="pm.pageNumber" type="text" number="${pageNumber}" value="${pageNumber}"
								pages="" class="InputStyleGO" style="height:17px;"></input>
							<input name="pm.submit" type="submit" value="GO" onclick="return PT_CheckSubmit('pm',2)"
								class="itTextC" />
						</pg:skip>
					</div>
				</c:when>
				<c:otherwise>
					没有数据需要显示
				</c:otherwise>
			</c:choose>
		</DIV>
		<DIV ID=PageShow>
			<pg:page>
				显示第&nbsp;<span><strong>${pm.currentPageNo+1} - ${pm.currentPageNo+pm.size}</strong></span>&nbsp;条
				共&nbsp;<strong><span>${pm.totalCount}</strong></span>&nbsp;条
			</pg:page>
		</DIV>
		<div class="clear"></div>

	</pg:pager>
</form>