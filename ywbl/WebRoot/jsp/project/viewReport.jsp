<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
//////System.out.println("url================================");
 	String url = request.getParameter("c_url");
 	
 	url = url.substring(url.lastIndexOf("/")+1, url.length());
	//////System.out.println("url=="+url); 
	//System.out.println("url=="+url); 
%>
<style>
@media print
{
.PageNext{page-break-after:always;}
.NotPrint{display:none;}
}
table{
table-layout:fixed;
word-break: break-all;
word-wrap: break-word;
}
</style>
<script type="text/javascript">
function pt(){
	window.print();
	$("#ptForm").submit();
}
</script>
<div align="center">
<table borderColor=#ffffff cellSpacing=1 cellPadding=1 align=center  bgcolor="#666666" border=0>

<tr>
<td valign=center align=middle bgColor=#ffffff><Img width="100%" height="100%" Src="http://<%=request.getRemoteAddr()%>:<%=request.getServerPort() %>/auditdownload/<%=url %>" border="0" align="absmiddle"></td>
</tr>

</table>
<br></br>
<!-- <div align="center"><input type="button" value="打印" class="NotPrint" onclick="pt()" /></div> -->
</div>
