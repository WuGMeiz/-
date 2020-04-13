<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_USER"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYCommunity.Page"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%

//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control", "no-store");//HTTP 1.1
response.setHeader("Pragma", "no-cache");//HTTP 1.0
response.setDateHeader("Expires", 0);//prevents caching at the proxy server
%>
<%
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
%>
<%
	if(request.getParameter("type")==null) {
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		
		String ts_id = session.getAttribute("U_ID").toString();
		String org_id = S_string.formatString(request.getParameter("Org_id"));
		String orgName = S_string.formatString(request.getParameter("czyname"));
		String czyid = S_string.formatString(request.getParameter("czyid"));
		String org_level = S_string.formatString(request.getParameter("org_level"));
		String tu_id = session.getAttribute("TU_ID").toString();
		String Condition="";
		String czyorg_id="";
		if(!org_id.equals("")) {
			czyorg_id=" and a.org_id ='"+org_id+"' ";
		}
		String userid="";
		if(!czyid.equals("")) {
		    userid=" and a.userid like '%"+czyid+"%' ";
		}
		String username="";
		if(!orgName.equals("")) {
		    username=" and a.username like '%"+orgName+"%' ";
		}
		if(org_level.equals("0")){
		Condition=" 1=1 and a.status='1' and b.status='1' and a.levels>='"+org_level+"' and b.org_level>='"+org_level+"' and a.u_id='"+ts_id+"' " +czyorg_id + userid+username;
		}else{
		    Condition=" 1=1 and a.status='1' and b.status='1' and a.levels>='"+org_level+"' and b.org_level>='"+org_level+"' and a.u_id='"+ts_id+"' and a.tu_id='"+tu_id+"' " +czyorg_id + userid+username;
		}
		session.setAttribute("Condition",Condition);
		String Innerj="  inner  join TB_SEV_ORG b on b.org_id=a.org_id";
		session.setAttribute("Innerj", Innerj);
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_SEV_USER a");

	//设置多表联合查询时的连接语句
	pages.setInnerj(session.getAttribute("Innerj").toString());
	//分页条件
	if(session.getAttribute("Condition")!=null) {
		pages.setCondition(session.getAttribute("Condition").toString());
	}

	//每页显示多少条
	pages.setPagesize(10);
	//设置要显示哪页
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
	List<TB_SEV_USER> all=new MyTB_SEV_USER().getuserPage(pages.getPagesize(),pages.getPagenum(),pages.getCondition());

%>
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">
<thead class="thead">
	<tr height="35">
			<th width="2%">
			序号
		</th>
		<th width="20%">
			所属单位
		</th>
		<th width="15%">
			操作员号
		</th>
		<th width="10%">
			姓名
		</th>
		<th width="15%"> 
			电话 
		</th>
		<th width="15%">
			创建日期
		</th>
		<th width="17%">
			操作
		</th>
		</tr>
	</thead>
	<tbody class="tableTbody">
<%
	if(all.size()>0) {
		for (int i=0;i<all.size();i++) {
			TB_SEV_USER user = all.get(i);
			String orgname=new MyTB_SEV_ORG_DAO().getOrg_name(user.getOrg_id());
%>
	<tr height="35">
		<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
		<td align="center"><%=orgname%></td>
		<td align="center">
			<%=user.getUserid()%>
			<input type="hidden" value="<%=user.getPassword()%>" id="oldpassword" name="oldpassword"/>
		</td>
		<td align="center">
		<input type="hidden" id="delectczy_name<%=i %>" name="delectczy_name<%=i %>" value="<%=user.getUsername()%>">
		<%=user.getUsername()%></td>
		<td align="center">
			<%=user.getPhone() %>
		</td>	
			
		<td align="center">
			<%=user.getCreate_time()%>
		</td>
	
		
		<td align="center">
		<%
			String tu_id = session.getAttribute("TU_ID").toString();
			if(tu_id.equals(user.getTu_id())) {
		%>
			<font style="color: red;font-size: 11px;">不能对自身登录账号进行操作</font>
		<%
			} else {
		%>
		<input type="button" onclick="resetPassword('<%=request.getContextPath()%>/ResetPasswordServlet','<%=user.getTu_id()%>','111111','<%=request.getContextPath() %>/YCWYPage/BackPage/system/operator.jsp?type=0')" value="密码重置">
		<input type="button" value="编辑" onclick="edit_show_see_tanchu_div('div_user_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/system/operator_edit.jsp"%>','<%=user.getTu_id() %>');">
		<input type="button" value="删除" onclick="delectableczy('<%=user.getUserid() %>','<%=i%>','show_select_divid','<%=request.getContextPath()%>/TB_CDE_ROLE_STATE?act=3','<%=request.getContextPath() %>/YCWYPage/BackPage/system/operator_show.jsp?type=0');">
		<%
			}
		%>
		</td>
	</tr>
<%
		}
%>
	<tr height="35">
		<td colspan="8" style="text-align:left;padding-left:5px;">
			<font style="color:red;font-size:12px;">提示：点击密码重置按钮可快速将登录密码重置为6个1</font>
		</td>
	</tr>
<%
	} else {
%>
	<tr height="35">
		<td colspan="8" align="center">
		无数据
		</td>
	</tr>
<%
	}
	String url = request.getContextPath()+"/YCWYPage/BackPage/system/operator_show.jsp";
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
		<li onclick="javascript:Back('<%=request.getContextPath()%>/YCWYPage/BackPage/system/operator_show.jsp','<%=pages.getPagenum() - 1%>','show_select_divid')">上一页</li>
    
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
  	<li onclick="javascript:Next('<%=request.getContextPath()%>/YCWYPage/BackPage/system/operator_show.jsp','<%=pages.getPagenum() + 1%>','show_select_divid')">下一页</li>
  	
<%
  		}
  	%>
     <li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=request.getContextPath()%>/YCWYPage/BackPage/system/operator_show.jsp',this,'show_select_divid')">
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
		<div id="div_user_show" class="gxg_tcdiv " ></div>
		<!-- 查看修改读取层 -->
		<div id="div_edit_user_show" class="gxg_tcdiv"></div>
<%
}
%>