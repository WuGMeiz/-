<%@page import="java.util.*"%>
<%@page import="WYBack_Stage.Bean.TB_CDE_MENU"%>
<%@page import="WYBack_Stage.Dao.MyTB_CDE_MENU"%>
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
                
<ul class="list_clasifyUl">
<%

	String ROLE_CODE=session.getAttribute("ROLE_CODE").toString();
	String menu=new MyTB_CDE_MENU().getMenu(ROLE_CODE);
	List<TB_CDE_MENU> list=new MyTB_CDE_MENU().getMenu(menu,"1");
	String code="";
	String level="";
	if(list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
			TB_CDE_MENU mm=(TB_CDE_MENU)list.get(i);
			String menuName=mm.getMenu_name();
			String menu_code=mm.getMenu_code();
			int menu_level=Integer.parseInt( mm.getMenu_level() )+1;//下级菜单级别
			 if(i==0)
			{
				code=menu_code;
				level=menu_level+"";
			} 
			  
%>
 


	 <li class="xtgl<%=i%>">
	
	<a class="list_clasifyarrow" onclick="menu1('showmenu2<%=i%>','<%=request.getContextPath()%>/YCWYPage/BackPage/menu2.jsp','<%=menu%>','<%=menu_code%>','<%=menu_level%>');"><%=menuName %></a> 
	<div class="list_clasifyNav" id="showmenu2<%=i%>">
		<jsp:include page="menu2.jsp">
			<jsp:param value="<%=menu %>" name="menu"/>
			<jsp:param value="<%=code %>" name="menu_code"/>
			<jsp:param value="<%=level %>" name="menu_level"/>
		</jsp:include>
		
	</div>
	 </li>
<%
}
		}
	 %>	
</ul>
 <%	
}
%> 