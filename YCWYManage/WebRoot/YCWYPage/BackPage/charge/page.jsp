<%@page language="java" pageEncoding="UTF-8"%>
<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
%>
<%
	int Pagecount=Integer.parseInt(request.getParameter("Pagecount"));//共几页
	int Countnum=Integer.parseInt(request.getParameter("Countnum"));//共几条
	int Pagenum=Integer.parseInt(request.getParameter("Pagenum"));//第几页
	String suburl=request.getParameter("suburl");//显示页面的路径
	String divid=request.getParameter("divid");//返回页面所放的 div ids
%>
<ul>
	<li>共<%=Pagecount %>页/<%=Countnum %>条</li>
<%
	if(Pagenum==1) {
%>
	<li>上一页</li>
<%}else{%>
	<li onclick="Back('<%=suburl%>','<%=Pagenum-1 %>','<%=divid%>')">上一页</li>
<%} %>
	<li>[<%=Pagenum %>]</li>
<%
	if(Pagenum==Pagecount|Pagecount==0) {
%>
	<li>下一页</li>
<%
	} else {
%>
	<li onclick="Next('<%=suburl%>','<%=Pagenum+1 %>','<%=divid%>')">下一页</li>
<%
	}
%>
	<li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=suburl%>',this,'<%=divid%>')">
		<%
			for(int i=1;i<=Pagecount;i++) {
		%>
		<option value="<%=i %>" <%if(i==Pagenum){%>selected="selected"<%} %>><%=i %></option>
		<%
			}
		%>
	</select>页</li>
</ul>