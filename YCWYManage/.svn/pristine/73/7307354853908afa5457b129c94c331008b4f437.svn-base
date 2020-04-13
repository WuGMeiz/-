<%@page import="WYBack_Stage.Dao.TB_Estate_VillageDao"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_ORG"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />

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
String org_id_level = session.getAttribute("ORG_ID").toString();//登录的level得到的org_id
String ts_id = session.getAttribute("U_ID").toString();
String es_id= S_string.formatString(request.getParameter("es_id"));
if(!"0".equals(es_id)){
String yznr = new TB_Estate_VillageDao().getyznr(es_id,ts_id);
%>
	
			<table class="show_table">
			<tr height="35">
			<td align="right" width="20%" >
				选择验证内容：
			</td>
			<td align="left" width="80%">	
				<input type="checkbox"  name="checkboxyz" style="height:16px;vertical-align:middle;" checked="checked" disabled="disabled" />
				 房屋编号 
&nbsp;&nbsp; 
				<input type="checkbox"  name="checkboxyz" style="height:16px;vertical-align:middle;" <%if(yznr.substring(1,2).equals("1")){ %> checked="checked"  <%} %>/>
				 业主姓名 
&nbsp;&nbsp; 		
		    	<input type="checkbox"  name="checkboxyz" style="height:16px;vertical-align:middle;" <%if(yznr.substring(2,3).equals("1")){ %> checked="checked"  <%} %>/>
		    	 业主电话 
		    	
		    	
		    </td>
		</tr>		
		<tr height="55">
			<td align="left" colspan="2">
				<input type="button"class="submit_input" style="margin-left:30%;"  value=" 设 置 " onclick="TBPay_UserdlSet('checkboxyz','<%=request.getContextPath()%>/TB_Estate_VillageServlet','<%=request.getContextPath() %>/YCWYPage/BackPage/system/signin.jsp');">
			</td>
		</tr>
	</table>

<%
}
}
%>
