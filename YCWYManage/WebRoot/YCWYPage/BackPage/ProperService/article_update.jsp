
<%@page import="WYBack_Stage.Dao.TB_Village_Dao"%>
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
	String cms_a_id=request.getParameter("cms_a_id");
	String cms_c_id=request.getParameter("cms_c_id");
	String cms_category_id=new TB_Village_Dao().getcms_category_id(cms_c_id);
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_sj_xxbj_show');">×</b></div>
		
<div align="center" class="gxg_tcdiv_nr">
<iframe id="pjname" name="pjname" width="100%" height="550" frameborder="0" scrolling="no" style="background-color:white" src="<%=request.getContextPath()+ "/YCWYPage/BackPage/ProperService/show_xinxi_update.jsp?cms_a_id="+cms_a_id+"&cms_c_id="+cms_c_id+"&cms_category_id="+cms_category_id %>"></iframe>

</div>
<%
}
%>
