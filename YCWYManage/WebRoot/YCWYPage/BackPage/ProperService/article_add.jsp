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
	out.print("sessionOvertime-您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！");
} 
else 
{
%>
<% 
	String divid=request.getParameter("divid");
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_sj_xxbj_show');">×</b></div>
			
<div align="center" class="gxg_tcdiv_nr">
<iframe id="pjname" name="pjname" width="100%" height="540" frameborder="0" scrolling="no" style="background-color:white" src="<%=request.getContextPath()+ "/YCWYPage/BackPage/ProperService/show_xinxi_add.jsp"%>"></iframe>
</div>
<%
}
%>
