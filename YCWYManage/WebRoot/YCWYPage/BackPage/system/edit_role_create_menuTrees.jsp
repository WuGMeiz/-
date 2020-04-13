<%@page import="WYBack_Stage.Bean.TB_CDE_MENU"%>
<%@page import="WYBack_Stage.Dao.MyTB_CDE_MENU"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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

<% 

	String MENU_CODE_select=session.getAttribute("MENU_CODE_select").toString();
	List menulist=new S_string().getIndexString(MENU_CODE_select,',');

	//int menu_level=Integer.parseInt(request.getParameter("menu_level"));	//上级的级别
	int hang=Integer.parseInt(request.getParameter("hang"));	//上级在同级中是第几行
	int size=Integer.parseInt(request.getParameter("size"));	//上级一共有几个同级内容
	String menu_code=request.getParameter("menu_code");	//上级的机构代码

	String td0="<td style='height:16px;width:0px;' ></td>";	
	String td1="<td style='height:16px;width:19px;' ></td>";
	String tds="<td style='height:16px;width:19px;background-image:url("+request.getContextPath()+"/YCWYPage/BackPage/images/I.png);' ></td>";
	
	//String hangjilu=request.getParameter("hangjilu");	//接受上级页面传过来的打印空格或竖线的标示
	String hangjilu=S_string.formatString(request.getParameter("hangjilu"));		
	if(!hangjilu.equals(""))//(request.getParameter("hangjilu")!=null) //判断hangjilu如果有值
	{
		if((hang+1)==size)//判断如果是最后一行时给hangjilu赋值0，标示打印一个空格
		{
			//hangjilu=hangjilu+",0";
			hangjilu=hangjilu+td1;
		}
		else	//判断如果不是最后一行时给hangjilu赋值1，标示打印一个竖线
		{
			//hangjilu=hangjilu+",1";
			hangjilu=hangjilu+tds;
		}
	}
	else	//判断如果没有传值，说明是根机构调用，给hangjilu赋值0，标示打印一个空格
	{
		if((hang+1)==size)//判断如果是最后一行时给hangjilu赋值0，标示打印一个空格,因为菜单树是从1开始，所以这里赋值“0,0”
		{
			hangjilu=td0+td1;//"0,0";
		}
		else	//判断如果不是最后一行时给hangjilu赋值1，标示打印一个竖线,因为菜单树是从1开始，所以这里赋值“1,0”
		{
			hangjilu=tds+td0;//"1,0";
		}	
		
	}
	
	//List hanglist=null;

	//hanglist=new S_string().getIndexString(hangjilu,',');
%>

		<% 
			String ROLE_CODE=session.getAttribute("ROLE_CODE_XG").toString();		
			String menu=new MyTB_CDE_MENU().getMenu(ROLE_CODE);
			List list2=new MyTB_CDE_MENU().get_Menu_All(menu,menu_code);//.getMenu(menu_code,2);//.get_Menu_All(menu_code);

			for (int ii=0; ii<list2.size(); ii++)
			{
		     	TB_CDE_MENU menu1 = (TB_CDE_MENU)list2.get(ii);
		     	
			    String menu_level1=menu1.getMenu_level();
			    String menu_code1=menu1.getMenu_code();   
		  
			    String url="edit_role_create_menuTrees.jsp?menu_level="+menu_level1+"&&hang="+ii+"&&size="+list2.size()+"&&menu_code="+menu_code1+"&&hangjilu="+hangjilu;//包含页面的路径及参数
			    String jia="";
			    String jian="";
			    if(ii==(list2.size()-1))
			    {
			     	jia="tree_xia_jia";
			     	jian="tree_xia_jian";
			    }
			    else
			    {
			     	jia="tree_zhong_jia";
			     	jian="tree_zhong_jian";
			    }
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<%=hangjilu %>
				<td id="<%="menu_jiajian_"+menu_code1 %>" class="<%=jian %>" onclick="menu_tree_all('<%="menu_jiajian_"+menu_code1 %>','<%=jia %>','<%=jian %>','<%="show_menu_tree_all"+menu_code1 %>');return false;"></td>
				<td style="height:16px;font-size:12px;">
				<input type="checkbox"  name="<%=menu_code %>" style="height:16px;vertical-align:middle;" value="<%=menu_code1 %>" 
			    <% 
			    	int num2=0;
					int z=0;
					int s=0;
					int g=0;
					int c=0;
			    	for(int m1=0;m1<menulist.size();m1++)
			    	{
			    		String mmm=(String)menulist.get(m1);
			    		if(mmm.equals(menu_code1))
			    		{
			    			num2++;
			    		}
			    	}

			    	if(num2>0)
			    	{
			    		
			    %>
			    checked="checked"
			    <% 
			    	}
			    	else
			    	{
			    %> 
			     
			    <% 
			    	}
			    %>					
				onClick="role_Check_menu(this,'<%=menu_code1 %>')"> <a href="" onclick="menu_tree_all('<%="menu_jiajian_"+menu_code1 %>','<%=jia %>','<%=jian %>','<%="show_menu_tree_all"+menu_code1 %>');return false;"><%=menu1.getMenu_name()%></a>	
				</td>
				
			</tr>
		</table>								  	
 	  	<div id="<%="show_menu_tree_all"+menu_code1 %>" style="display: block;text-align: left;" > 
				<jsp:include flush="true" page="<%=url %>"></jsp:include>	
	  	</div>	 	 	 				   				    		

		<% 
			
		}
		%>																			

<%
}
%>