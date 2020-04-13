<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_ORG"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control", "no-store");//HTTP 1.1
response.setHeader("Pragma", "no-cache");//HTTP 1.0
response.setDateHeader("Expires", 0);//prevents caching at the proxy server
//防止乱码
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");//这样设置可以让弹出框在IE和火狐下显示正常
response.setCharacterEncoding("UTF-8");
%>
<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
	String ORG_ID = session.getAttribute("ORG_ID").toString();
%>
<%
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		String u_id = session.getAttribute("U_ID").toString();
		String orgName = S_string.formatString(request.getParameter("org_name"));
		String org_id = S_string.formatString(request.getParameter("org_id"));
		String orgRemark = S_string.formatString(request.getParameter("orgRemark"));
		String orgid=session.getAttribute("ORG_ID").toString();
		int org_level=new MyTB_SEV_ORG_DAO().getorg_level(orgid,u_id);
		String Condition="";
		String org_Name="";
		if(!orgName.equals("")) {
			org_Name=" and org_name like '%"+orgName+"%' ";
		}
		
		String remark="";
		if(!orgRemark.equals("")) {
		    remark=" and remark like '%"+orgRemark+"%' ";
		}
		
		/* String orgup="";
		if(!org_id.equals("")) {
			orgup=" and org_id='"+org_id+"' ";
		} */
        if(org_level==0){
             Condition=" 1=1 and status='1' and org_level>'"+org_level+"' and ts_id='"+u_id+"' "+org_Name+remark; 
		}else{
			 Condition=" 1=1 and status='1' and org_level>='"+org_level+"' and org_id='"+orgid+"' and ts_id='"+u_id+"' "+org_Name+remark;
		}
		session.setAttribute("Condition",Condition);
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_SEV_ORG");

	//分页条件
	if(session.getAttribute("Condition")!=null) {
		pages.setCondition(session.getAttribute("Condition").toString());
	}

	//每页显示多少条
	pages.setPagesize(10);
	//设置要显示哪页
	if(request.getParameter("pagenum")!=null) {
		String temp=request.getParameter("pagenum").toString();
		pages.setPagenum(Integer.parseInt(temp));
		session.setAttribute("pagenum", temp);
	} else {
		if(session.getAttribute("pagenum")!=null) {
			pages.setPagenum(Integer.parseInt(session.getAttribute("pagenum").toString()));
		}
	}
	List<TB_SEV_ORG> all=new MyTB_SEV_ORG_DAO().getTB_SEV_ORGPage(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
%>
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">
	<thead class="thead">
		<tr height="35">
				<td   align="center">序号 </td>
				<td   align="center">单位名称 </td>
				<td  align="center">上级单位 </td>
				<td   align="center">单位描述 </td>
				<td   align="center">操作 </td>
			</tr>
	</thead>
	<tbody class="tableTbody">
<%
	if(all.size()>0) {
		for (int i=0;i<all.size();i++) {
			TB_SEV_ORG dept = all.get(i);
%>
	<tr height="35">
		<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
		<td align="center"><input class="input_txt1" type="text" id="org_name<%=i %>" value="<%=dept.getOrg_name() %>"/></td>
		<td align="center">   <%=new MyTB_SEV_ORG_DAO().getOrg_name(dept.getUp_org_id())%> </td>
		<td align="center"><input class="input_txt1" type="text" id="remark<%=i %>" value="<%=dept.getRemark() %>"/></td>
		<td align="center">
		<%
			String level = session.getAttribute("ORG_LEVEL").toString();
			if(level.equals(dept.getOrg_level()) ){
		%>
			<font style="color: red;font-size: 11px;">不能对本级机构进行操作</font>
		<%
			} else {
		%>
			<input type="button"  value="修改"
			onclick="updateorg('<%=dept.getOrg_id() %>','<%=i%>','<%=request.getContextPath()%>/UpdateOrgServlet','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/system/org_show.jsp?type=0"%>')" />
			<input type="button" value="删除" 
			onclick="delectorg('<%=dept.getOrg_id() %>','<%=i%>','<%=request.getContextPath()%>/DelectOrgServlet','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/system/org_show.jsp?type=0"%>')" />
		<%
			} 
		%>	
		</td>
	</tr>
<%
		}
%>
<%
	} else {
%>
	<tr height="35">
		<td colspan="7" align="center">
			暂无数据
		</td>
	</tr>
<%
	}
	String url = request.getContextPath()+"/YCWYPage/BackPage/system/org_show.jsp";
%>
	</tbody>
</table>
<!--分页-->
	<div class="pages">
		<ul>
			<li>共&nbsp;<%=pages.getPagecount()%>&nbsp;页&nbsp;/&nbsp;<%=pages.getCountnum()%>&nbsp;条&nbsp;
				<%
					if (pages.getPagenum() == 1) {
				%>

				<li>上一页</li>

<%
	} else {
%>	
		<li onclick="javascript:Back('<%=request.getContextPath()%>/YCWYPage/BackPage/system/org_show.jsp','<%=pages.getPagenum() - 1%>','show_update')">上一页</li>
    
<%
    	}
    %>    
  <li>  [<%=pages.getPagenum()%>] </li>
<%
	if (pages.getPagenum() == pages.getPagecount() | pages.getPagecount() == 0) {
%>	
		<li>下一页</li>
<%
	} else {
%>     
  	<li onclick="javascript:Next('<%=request.getContextPath()%>/YCWYPage/BackPage/system/org_show.jsp','<%=pages.getPagenum() + 1%>','show_update')">下一页</li>
  	
<%
  		}
  	%>
     <li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=request.getContextPath()%>/YCWYPage/BackPage/system/org_show.jsp',this,'show_update')">
	<%
		for (int i = 1; i <= pages.getPagecount(); i++) {
	%>
	<option value="<%=i%>" <%if (i == pages.getPagenum()) {%> selected="selected" <%}%>><%=i%></option>
	<%
		}
	%>
	</select>    
   	</li>
			</ul>
		</div>
		<!--/分页-->
<div class="clear"></div>
<!-- 查看修改读取层 -->
<div id="div_user_show" class="gxg_tcdiv"></div>
<!-- 查看修改读取层 -->
<div id="div_edit_user_show" class="gxg_tcdiv"></div>
<%
}
%>