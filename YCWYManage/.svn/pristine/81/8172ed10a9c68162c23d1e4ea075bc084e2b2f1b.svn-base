<%@page import="WYBack_Stage.Dao.TB_Estate_RepPeopleDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_RepPeople"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_UnitDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Unit"%>
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
%>
<%
	
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		
		
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String Condition=" 1=1 and r.status='1' and v.status='1' and r.Es_id='"+Es_id+"'";
		session.setAttribute("Condition",Condition);
		
		String Innerj = " r inner join TB_Estate_Village v on r.Es_id=v.Es_id ";
		session.setAttribute("Innerj", Innerj);
		
		
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_RepPeople");

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
		
	List<TB_Estate_RepPeople> list=new TB_Estate_RepPeopleDao().getRepPeo(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
	
%>
<div style="width:100%; overflow-x:scroll;">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="38">
					<th style="width: 3%">序号</th>
					<th style="width: 8%">维修人姓名</th>
					<th style="width: 8%">小区名称</th>
					<th style="width: 8%">联系方式</th>
					<th style="width: 8%">备注</th>
					<th style="width: 8%" >编辑</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_RepPeople tev= (TB_Estate_RepPeople) list.get(i);
				
			%>
						
				<tr height="38">
					<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
					<td align="center">
					   <input type="text" class="input_txt1" id="ReName<%=i%>" name="ReName<%=i%>" value="<%=tev.getReName() %>"  maxlength="15" />
					</td>
					<td align="center">
					  <%=tev.getEsName() %>
					</td>
					
					<td align="center">
						<input type="text" class="input_txt1" id="phone<%=i%>" name="phone<%=i%>" value="<%=tev.getPhone() %>"  maxlength="15" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="remark<%=i%>" name="remark<%=i%>" value="<%=tev.getRemark() %>"  maxlength="50" />
					</td>
					
					<td align="center">
						<input width="45%" type="button" name="xiu<%=i%>" value=" 修改 "
							onclick="updateRepPeo('<%=tev.getRe_id() %>','<%=i%>','<%=request.getContextPath()%>/TB_Estate_RepPeopleServlet?arg=2','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/repairPeo_update.jsp?type=1"%>')" />
					  <input width="45%" type="button" onfocus="this.blur();" name="shan<%=i%>" value="删除"  
					   onclick="delectRepPeo('<%=tev.getRe_id()%>','<%=i%>','<%=request.getContextPath()%>/TB_Estate_RepPeopleServlet?arg=3','show_update','<%=request.getContextPath()
									+ "/YCWYPage/BackPage/ProperService/repairPeo_update.jsp?type=1"%>');" />
					</td>
				</tr>
				<%
				}}else{ %>
				<tr height="38">
				<td colspan="6" align="center">暂无内容</td>
				</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/ProperService/repairPeo_update.jsp";
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