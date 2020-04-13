<%@page import="WYBack_Stage.Dao.TB_Estate_RepPeopleDao"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
%>

<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
	String Condition=session.getAttribute("Condition").toString();
	String wulifilepath=Base64Utils.jiemi(request.getParameter("wulifilepath"));
	String filename=Base64Utils.jiemi(request.getParameter("filename"));
	boolean bl = new TB_Estate_RepPeopleDao().Excel_out_TouInfo(wulifilepath,filename,Condition);//生成报表
	if(bl) {
		out.print("dcbbok-生成报表成功！");
	} else {
		out.print("errors-生成报表失败！");
	}
}
%>