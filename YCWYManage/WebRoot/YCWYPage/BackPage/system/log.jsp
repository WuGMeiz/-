<%@ page language="java" pageEncoding="UTF-8"%>

<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
%>
<div class="addBox">
	<table class="show_table" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr height="45">
			<td align="right" width="10%">
				开始时间：
			</td>
			<td align="left" width="20%">
				<input type="text" id="timesk" name="timesk" class="input_txt" style="width:100%" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
			</td>
			<td align="right" width="10%">
				结束时间：
			</td>
			<td align="left" width="20%">
				<input type="text" id="timesj" name="timesj" class="input_txt" style="width:100%"  onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
			</td>
			<td align="right" width="10%">
				操作内容：
			</td>
			<td align="left" width="15%">
				<input type="text" id="cznr" name="cznr" class="input_txt"/>
			</td>
			<td align="center">
				<input type="button" name="tianjia"  class="submit_input" value=" 查看 " onclick="admin_log_show('system_log_show','<%=request.getContextPath()%>/YCWYPage/BackPage/system/system_log_show.jsp')" />
			</td>
		</tr>
	</table>
	<div id="system_log_show" class="addBoxList">
		<jsp:include flush="true" page="system_log_show.jsp"></jsp:include>
	</div>
</div>
<%
}
%>