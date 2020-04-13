<%@page import="WYCommunity.Gue_Excel"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%
	if(session.getAttribute("USER_ID")==null)
{
		String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
		out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	    out.println("window.location='"+url+"';");
	    out.println("</script>");
}
else
{
    String ts_id=session.getAttribute("U_ID").toString();
    String a_id=session.getAttribute("A_ID").toString();
    String Es_id = request.getParameter("xiaoquid");
	String wulifilepath=Base64Utils.jiemi(request.getParameter("wulifilepath"));
	String filename=Base64Utils.jiemi(request.getParameter("filename"));
	boolean bl = new Gue_Excel().Excel_out_Mb1(ts_id,a_id,Es_id,wulifilepath,filename);//生成报表
	if(bl)
	{
		out.print("dcbbok-生成模版成功！");
	}
	else
	{
		out.print("errors-生成模版失败,请选择小区或在计费管理下添加该小区的缴费项！");
	}
}
%>