<%@page language="java" pageEncoding="UTF-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
%>

<%
if (session.getAttribute("USER_ID") == null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
%>

<div class="tables" id="showmain">

	<iframe id="pjname" name="pjname" width="100%" height="450" frameborder="0" scrolling="no" src="<%=request.getContextPath()+"/YCWYPage/BackPage/charge/gueframe.jsp"%>"></iframe>
</div>

<%
}
%>
