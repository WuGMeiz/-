<%@page import="WYBack_Stage.Bean.TB_CDE_MENU"%>
<%@page import="WYBack_Stage.Dao.MyTB_CDE_MENU"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List"/>

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
%>

    <ul>
    <% 
    	String menu=request.getParameter("menu");
    	String menu_code=request.getParameter("menu_code1");
    	String menu_level=request.getParameter("menu_level");
		List list2=new MyTB_CDE_MENU().getMenu2(menu,menu_code,menu_level);
   
		for (int i=0; i<list2.size(); i++)
		{
			TB_CDE_MENU mm2=(TB_CDE_MENU)list2.get(i);
		
			String URL2=mm2.getUrl();
			String menuName2=mm2.getMenu_name();
    %>
        	<li id="<%=menu_code %>li<%=i%>" onclick="menu2('<%=menu_code %>','<%=i %>','<%=list2.size() %>','<%=request.getContextPath() +"/"+URL2 %>');" ><span><%=menuName2 %></span>
        	</li>
	<% 
		}
	%>
    </ul>
    <div class="clear"></div>
   
<%
}
%>