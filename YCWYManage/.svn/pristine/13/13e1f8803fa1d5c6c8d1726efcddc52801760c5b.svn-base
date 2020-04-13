<%@page import="WYBack_Stage.Dao.TB_Estate_ExDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Examine"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
%>

<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String Type=S_string.formatString(request.getParameter("Type"));
		String timesk=S_string.formatString(request.getParameter("timesk"));
		String status=S_string.formatString(request.getParameter("status"));
		String title=S_string.formatString(request.getParameter("title"));
		String Condition=" 1=1  and v.status='1'and r.status<>0 and r.Es_id='"+Es_id+"'";
		if(!"".equals(Type)){
		  Condition+=" and r.type='"+Type+"'";
		}
		if(!timesk.equals("")) {
		  Condition+=" and r.creat_time>='"+timesk+" 00:00:00' and r.creat_time<='"+timesk+" 23:59:59' ";
	    }
	    if(!"".equals(status)){
	      Condition+=" and r.status='"+status+"'";
	    }
	    if(!"".equals(title)){
	     Condition+=" and r.title like '%"+title+"%'";
	    }
		session.setAttribute("Condition",Condition);
		
		String Innerj = " r inner join TB_Estate_Village v on r.Es_id=v.Es_id ";
		session.setAttribute("Innerj", Innerj);
		
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Examine");

	//设置多表联合查询时的连接语句
	pages.setInnerj(session.getAttribute("Innerj").toString());
	
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
		
	List<TB_Estate_Examine> list=new TB_Estate_ExDao().getExamine(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
%>

<div style="width:100%; overflow-x:scroll;">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="40">
					<th style="width: 5%">小区名称</th>
					<th style="width: 6%">问卷标题</th>
					<th style="width: 5%">类别</th>
					<th style="width: 6%">开始时间</th>
					<th style="width: 6%">结束时间</th>
					<th style="width: 15%">说明</th>
					<th style="width: 5%">状态</th>
					<!-- <th style="width: 8%">备注</th> -->
					<th style="width: 8%" >编辑</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_Examine examine=(TB_Estate_Examine)list.get(i);
					String type="满意度调查",str="";
					if(examine.getType()==1){
					  type="满意度调查";
					}else if(examine.getType()==2){
					  type="投票";
					}
					if(examine.getStatus()==1){
					  str="启用";
					}else if(examine.getStatus()==2){
					  str="暂停";
					}
						
			%>
				<tr height="40">
					<td align="center">
					  <%=examine.getEsName() %>
					</td>
					<td align="center">
					  <%=examine.getTitle() %>
					</td>
					<td align="center">
					    <%=type %>
					</td>
					<td align="center">
					    <%=examine.getStart_time()%>
					</td>
					<td align="center">
					    <%=examine.getEnd_time()%>
					</td>
					<td align="center">
					    <%=examine.getDescription()%>
					</td>
					<td align="center">
					    <%=str%>
					</td>
				    <td align="center">
				     <input type="button" name="xiu<%=i%>" value="编辑" onclick="edit_show_see_tanchu_div( 'div_edit_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/mytp_edit_see.jsp"%>','<%=examine.getEx_id() %>');return false;"/>
					 <input type="button" name="shan<%=i%>" value="删除" onclick="TBExamine_delete('<%=examine.getEx_id()%>','<%=request.getContextPath()%>/TB_Estate_RepPeopleServlet?arg=7','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/mytp_update.jsp?type=1" %>','show_update');" />  
				    </td>
				</tr>		
				
				<%
				}}else{ %>
				<tr height="38">
				<td colspan="8" align="center">暂无内容</td>
				</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/ProperService/mytp_update.jsp";
%>
	</tbody>
</table>
</div>
<div class="pages">
	<jsp:include page="/YCWYPage/BackPage/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="show_update"/>
	</jsp:include>
</div>
<div class="clear"></div>
<div id="div_user_show" class="gxg_tcdiv"></div>
<!-- 查看修改读取层 -->
<div id="div_edit_user_show" class="gxg_tcdiv"></div>
<%
}
%>