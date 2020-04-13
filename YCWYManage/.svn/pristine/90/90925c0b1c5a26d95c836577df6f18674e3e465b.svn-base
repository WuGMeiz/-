<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List"/>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<% 
if(session.getAttribute("USER_ID")==null)
{
	out.print("sessionOvertime-您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！");
}
else
{
	session.setAttribute("cms_c_id",null);
%>
<div class="tables">
<table class="show_table">
	<tr>
		<td align="left" >
		<input type="button" name="tianjia" class="submit_input" value=" 发 布 " onclick="new_show_see_tanchu_div('div_sj_xxbj_show','','','<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/article_add.jsp');" />
		</td>
	</tr>
</table>
<div id="Article_edit_show" style="height: 150%">
	<jsp:include flush="true" page="article_edit_show.jsp">
		<jsp:param value="1" name="cms_c_id"/>
	</jsp:include>
</div>
<!-- 查看修改读取层 -->  
<div id="div_sj_xxbj_show" class="gxg_tcdiv" >
</div>	
</div>
<%
}
%>