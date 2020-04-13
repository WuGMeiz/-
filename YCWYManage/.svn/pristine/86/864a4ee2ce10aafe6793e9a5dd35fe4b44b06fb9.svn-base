<%@page import="WYBack_Stage.Bean.TB_CDE_MENU"%>
<%@page import="WYBack_Stage.Dao.MyTB_CDE_MENU"%>
<%@page import="WYCommunity.S_string"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
%>

<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
}else{
%>
<%
	int hang=Integer.parseInt(request.getParameter("hang"));//上级在同级中是第几行
	int size=Integer.parseInt(request.getParameter("size"));//上级一共有几个同级内容
	String menu_code=request.getParameter("menu_code");//上级的菜单代码

	String td0="<td style='height:16px;width:0px;'></td>";
	String td1="<td style='height:16px;width:19px;'></td>";
	String tds="<td style='height:16px;width:19px;background-image:url("+request.getContextPath()+"/YCWYPage/BackPage/images/I.png);'></td>";
	
	String hangjilu=S_string.formatString(request.getParameter("hangjilu"));//接受上级页面传过来的打印空格或竖线的标示
	if(!hangjilu.equals("")) {//判断hangjilu如果有值
		if((hang+1)==size) {//判断如果是最后一行时给hangjilu赋值0，标示打印一个空格
			hangjilu=hangjilu+td1;
		} else {//判断如果不是最后一行时给hangjilu赋值1，标示打印一个竖线
			hangjilu=hangjilu+tds;
		}
	} else {//判断如果没有传值，说明是根机构调用，给hangjilu赋值0，标示打印一个空格
		if((hang+1)==size) {//判断如果是最后一行时给hangjilu赋值0，标示打印一个空格,因为菜单树是从1开始，所以这里赋值“0,0”
			hangjilu=td0+td1;//"0,0";
		} else {//判断如果不是最后一行时给hangjilu赋值1，标示打印一个竖线,因为菜单树是从1开始，所以这里赋值“1,0”
			hangjilu=tds+td0;//"1,0";
		}
	}
%>
		<%
			String tuid = session.getAttribute("TU_ID").toString();
			String menu=new MyTB_CDE_MENU().getMenu1(tuid);
			List<TB_CDE_MENU> list2=new MyTB_CDE_MENU().get_Menu_All(menu,menu_code);
			for (int ii=0;ii<list2.size();ii++) {
				TB_CDE_MENU menu1 = list2.get(ii);
				String menu_level1=menu1.getMenu_level();
				String menu_code1=menu1.getMenu_code();
				String url="create_Menu_Tree.jsp?menu_level="+menu_level1+"&&hang="+ii+"&&size="+list2.size()+"&&menu_code="+menu_code1+"&&hangjilu="+hangjilu;//包含页面的路径及参数
				String jia="";
				String jian="";
				if(ii==(list2.size()-1)) {
					jia="tree_xia_jia";
					jian="tree_xia_jian";
				} else {
					jia="tree_zhong_jia";
					jian="tree_zhong_jian";
				}
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<%=hangjilu %>
				<td id="<%="menu_jiajian_"+menu_code1 %>" class="<%=jian %>" onclick="menu_tree_all('<%="menu_jiajian_"+menu_code1 %>','<%=jia %>','<%=jian %>','<%="show_menu_tree_all"+menu_code1 %>');return false;"></td>
				<td style="height:16px;font-size:12px;">
					<input type="checkbox" name="<%=menu_code %>" style="height:16px;vertical-align:middle;" value="<%=menu_code1 %>" disabled="disabled" onClick="role_Check_menu(this,'<%=menu_code1 %>')"><a href="" onclick="menu_tree_all('<%="menu_jiajian_"+menu_code1 %>','<%=jia %>','<%=jian %>','<%="show_menu_tree_all"+menu_code1 %>');return false;"><%=menu1.getMenu_name()%></a>
				</td>
			</tr>
		</table>
		<div id="<%="show_menu_tree_all"+menu_code1 %>" style="display: block;text-align: left;">
			<jsp:include flush="true" page="<%=url %>"></jsp:include>
		</div>
	<%
		}
	%>
<%
}
%>