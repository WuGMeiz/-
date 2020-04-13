<%@page import="WYBack_Stage.Dao.MyTBAdmin_Log"%>
<%@page import="WYBack_Stage.Bean.TBAdmin_Log"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.S_string"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<%
//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
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
		String tuid=session.getAttribute("TU_ID").toString();
		String ts_id = session.getAttribute("U_ID").toString();
		String user_id = session.getAttribute("USER_ID").toString();
	
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		
		String Condition=" 1=1 and a.status=1 and a.user_id='"+user_id+"'";
	
		if(request.getParameter("timesk")!=null && request.getParameter("timesj")!=null) {
			String timesk=request.getParameter("timesk");
			String timesj=request.getParameter("timesj");
			
			if(!timesk.equals("") && !timesj.equals("")) {
				Condition+=" and l_time>='"+timesk+" 00:00:00.000"+"' and l_time<='"+timesj+" 23:59:59.000"+"'";
			} else if(timesk.equals("") && !timesj.equals("")) {
				Condition+=" and l_time<='"+timesj+" 23:59:59.000"+"'";
			} else if(!timesk.equals("") && timesj.equals("")) {
				Condition+=" and l_time>='"+timesk+" 00:00:00.000"+"'";
			}
		}
		String cznr=S_string.formatString(request.getParameter("cznr"));
		if(!cznr.equals("")) {
			Condition+=" and l_content like '%"+cznr+"%'  ";
		}
		session.setAttribute("Condition",Condition);
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TBAdmin_Log a");

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
	List<TBAdmin_Log> list=new MyTBAdmin_Log().getadmin_log(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
%>

<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">
<thead class="thead">
	<tr height="35">
		<th width="5%">序号</th>
		<th width="45%">操作内容</th>
		<th width="15%">操作时间</th>
		<th width="15%">操作员号</th>
		<th width="10%">姓名</th>
	</tr>
	</thead>
	<tbody class="tableTbody">
	<%
	if(list.size()>0){
	for (int i = 0;i < list.size();i++) {
		TBAdmin_Log Log = list.get(i);
	%>
	<tr height="35">
		<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
		<td align="center"><%=Log.getL_content() %></td>
		<td align="center"><%=Log.getL_time() %></td>
		<td align="center"><%=Log.getCzybh() %></td>
		<td align="center"><%=Log.getCzyxm() %></td>
	</tr>
	<%
	}
	} else {
%>
	<tr height="35">
		<td colspan="5" align="center">
		暂无数据
		</td>
	</tr>
	<%
	}
	String url = request.getContextPath()+"/YCWYPage/BackPage/system/system_log_show.jsp";
%>
</tbody>
</table>

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
		<li onclick="javascript:Back('<%=request.getContextPath()%>/YCWYPage/BackPage/system/system_log_show.jsp','<%=pages.getPagenum() - 1%>','system_log_show')">上一页</li>
    
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
  	<li onclick="javascript:Next('<%=request.getContextPath()%>/YCWYPage/BackPage/system/system_log_show.jsp','<%=pages.getPagenum() + 1%>','system_log_show')">下一页</li>
  	
<%
  		}
  	%>
     <li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=request.getContextPath()%>/YCWYPage/BackPage/system/system_log_show.jsp',this,'system_log_show')">
	<!-- <option value="0">&nbsp;</option> -->
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
<div class="clear"></div>
<%
}
%>