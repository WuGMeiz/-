<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
%>
<% 
	String divid=request.getParameter("divid");
%>
<div class="tables">
	<iframe id="pjname" name="pjname" height="600"  width="100%" frameborder="0" scrolling="no" src="<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwaddpl_show.jsp" %>"></iframe>

</div>
<div class="AlertMessageShow" id="AlertMessageShow" style="display: none;">
	<div class="AlertMessageHeader">
    	<p>提示消息</p>
    </div>
    <div class="AlertMessageContent">
    	<p class="AlertMessage" id="AlertMessage">此处设置要弹出的内容</p>
    </div>
    <div class="AlertMessageFooter">
    	<input type="hidden" id="AlertReturnDiv" />
    	<input type="hidden" id="AlertReturnURL" />
    	<input type="button" id="AlertMessageConfirm" value="确定" style="float:right; margin-right:5%; margin-top:3px; width:120px;" 
		onclick="confirmAlertMsg();" />
    </div>
</div>
<%
}
%>
