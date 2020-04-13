<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_ExoptionDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Exoption"%>
<%-- <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/YCWYPage/FrontPage/js/script.js"></script> --%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<%
	if (session.getAttribute("USER_ID") == null) {
		String url = request.getContextPath()
				+ "/YCWYPage/BackPage/login.jsp";
		out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
		out.println("window.location='" + url + "';");
		out.println("</script>");
	} else {
	String op_id = request.getParameter("op_id");
%>
<div class="div_app_showTite">
	<b title="关闭" onclick="close_tanchu_div('div_user_show');">×</b>
</div>
<!-- <div align="center" style="width: 1255px;height:500px;"> -->
<div class="tables" id="show2">
<iframe id="pjname" name="pjname" scrolling="no" width="100%" height="450" frameborder="0" src="<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_update2.jsp?op_id="+op_id%>"></iframe>
</div>
<%}%>

