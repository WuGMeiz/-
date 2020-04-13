<%@page import="WYBack_Stage.Bean.TB_CDE_MENU"%>
<%@page import="WYBack_Stage.Dao.MyTB_CDE_MENU"%>
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
} else {
%>
<form>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="middle" align="left" style="padding-left: 6px;">
		<input type="checkbox" name="chkall" value="chkall" style="height:18px;vertical-align:middle;" onclick="CheckAll(this,this.form)">&nbsp;<font color="#FF0000">全选</font>
		</td>
	</tr>
	<tr>
		<td width="100%" align="left">
		<div id="menu" class="menutree_scroll">
		<%
		String tuid = session.getAttribute("TU_ID").toString();
		String menu=new MyTB_CDE_MENU().getMenu1(tuid);
		List<TB_CDE_MENU> list1=new MyTB_CDE_MENU().getMenu(menu,"1");
		for (int i=0;i<list1.size();i++) {
			TB_CDE_MENU menu1 = list1.get(i);
			String menu_level=menu1.getMenu_level();
			String menu_code1=menu1.getMenu_code();
			String url="create_Menu_Tree.jsp?menu_level="+menu_level+"&&hang="+i+"&&size="+list1.size()+"&&menu_code="+menu_code1;//包含页面的路径及参数
			String jia="";
			String jian="";
			if(list1.size()==1) {
				jia="tree_xia_jia";
				jian="tree_xia_jian";
			} else {
				if(i==0) {
					jia="tree_shang_jia";
					jian="tree_shang_jian";
				} else if(i==(list1.size()-1)) {
					jia="tree_xia_jia";
					jian="tree_xia_jian";
				} else {
					jia="tree_zhong_jia";
					jian="tree_zhong_jian";
				}
			}
		%>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td id="<%="menu_jiajian_"+menu_code1 %>" class="<%=jian %>" onclick="menu_tree_all('<%="menu_jiajian_"+menu_code1 %>','<%=jia %>','<%=jian %>','<%="show_menu_tree_all"+i %>');return false;"></td>
				<td style="height:16px;font-size:12px;">
				<input type="checkbox" name="checkbox1" style="height:16px;vertical-align:middle;" value="<%=menu1.getMenu_code()%>" onClick="role_Check_menu(this,'<%=menu1.getMenu_code()%>')"><a href="" onclick="menu_tree_all('<%="menu_jiajian_"+menu_code1 %>','<%=jia %>','<%=jian %>','<%="show_menu_tree_all"+i %>');return false;"><%=menu1.getMenu_name()%></a>
				</td>
			</tr>
		</table>
		<div id="<%="show_menu_tree_all"+i %>" style="display: block;text-align: left;">
			<jsp:include flush="true" page="<%=url %>"></jsp:include>
		</div>
		<%
		}
		%>
		</div>
		</td>
	</tr>
</table>
</form>
<%
}
%>