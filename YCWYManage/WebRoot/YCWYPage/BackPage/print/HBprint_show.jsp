<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%
if (session.getAttribute("USER_ID") == null) 
{
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} 
else
{
	String divid=request.getParameter("divid");
	String parID=request.getParameter("parID");
%>
	<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_edit_app_show');displayFlash('HBorderPrint');">×</b></div>

	<div align="center" class="" style="height: 400px">

		<iframe width="100%" height="1100px" id="HBorderPrint" frameborder="0" scrolling="no" 
			src="<%=request.getContextPath()%>/YCWYPage/BackPage/print/HBorderPrint.jsp?divid=<%=divid%>&parID=<%=parID%>"></iframe>
	</div>
		
<%
}
%>