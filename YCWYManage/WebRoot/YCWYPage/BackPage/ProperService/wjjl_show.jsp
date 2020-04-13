<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.Map" />
<%@page import="WYBack_Stage.Bean.TB_Estate_Extopic"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Exoption"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_RepPeopleDao"%><!-- 8888888 -->
<%@page import="WYBack_Stage.Dao.GetWj_Dao"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYCommunity.Base64Utils"%>


<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<%
if (session.getAttribute("USER_ID") == null) 
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
    String divid=request.getParameter("divid");
  	String ex_id=request.getParameter("ex_id");
  	String Eh_id=request.getParameter("eh_id");
  	String selectTime=request.getParameter("selectTime");
  	GetWj_Dao dao=new GetWj_Dao();
  	Map<TB_Estate_Extopic, List> list=dao.select_XuanXiang1(Integer.valueOf(ex_id),Integer.valueOf(Eh_id),selectTime);

%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_user_show1');">×</b></div>
<!-- <div class="new-layer">	 -->
<div align="center" class="gxg_tcdiv_nr">
		<br>
			<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="40">
					<th style="width: 3%">序号</th>
					<th style="width: 5%">题目要求</th>
					<th style="width: 20%">选项内容</th>
					
				</tr>
			</thead>
				<tbody class="tableTbody">
				<% if(list.size()>0){
				 int i=0;
						for(Map.Entry<TB_Estate_Extopic, List> entry:list.entrySet()){
	
						 TB_Estate_Extopic topic=entry.getKey();
						 List<TB_Estate_Exoption> options=entry.getValue();
						 i=i+1;
				 %>
				<tr height="40">
				<td align="center">
					    <%=i %>
					</td>
				<td align="center">
					    <%=topic.getTopic() %>
					</td>
				<td align="center">
				<%
				 int to_id=topic.getTo_id();
				
				 //List<TB_Estate_Exoption> options=dao1.getTB_Estate_Exoption(to_id);
			
				 //List<Integer> a=dao1.getXuanXiangId(ex_id,Eh_id,to_id,selectTime);
				
				 for(int j=0;j<options.size();j++){
				   TB_Estate_Exoption option =options.get(j);
				
				 %>
				
				<%--  <input type="checkbox" disabled="disabled" 
				  <%if(option.getId()!=0){ %>checked="checked"<%} %>/>  --%>
				  <%if(option.getId()!=0){ %>
				  <input type="checkbox" disabled="disabled" checked="checked"/>
				  <%}else{ %>
				  <input type="checkbox" disabled="disabled"/>
				  <%} %>
				<%-- <% if(option.getIf_tw() != 2) {%>
							<img alt="" style="width: 40px;" src="data:image/jpg;base64,<%=option.getImages() %>" />
					<%}%> --%>
			
			    <%=option.getOptionName() %>
				 <%} %>
				</td>	
					
			</tr>
			<%}} %>
			</table>

		
</div>

<%
}
%>
