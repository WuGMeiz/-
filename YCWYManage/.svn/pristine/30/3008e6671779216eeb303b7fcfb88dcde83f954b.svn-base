<%@ page language="java" pageEncoding="UTF-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%
if (session.getAttribute("USER_ID") == null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
%>
<div class="tables">
<table class="show_table" style="width:50%; margin:0 auto;">
	<tr height="45">
		<td width="30%" align="right">旧密码：</td>
		<td width="70%" align="left">
			<input type="password" id="oldPassword" size="18" maxlength="15" class="input_txt" style="width:65%;"/>
		</td>
	</tr>
	<tr height="45">
		<td align="right">新密码：</td>
		<td align="left">
			<input type="password" id="newPassword" size="18" maxlength="15" class="input_txt" style="width:65%;"/>
		</td>
	</tr>
	<tr height="45">
		<td align="right">确认密码：</td>
		<td align="left">
			<input type="password" id="comfirmPassword" size="18" maxlength="15" class="input_txt" style="width:65%;"/>
		</td>
	</tr>
	<tr height="100">
		<td colspan="2" align="center">
<font style="color: red;">温馨提示：密码长度应为6-12位，请设置时注意密码长度。</font>
		</td>
	</tr>
	<tr height="100">
		<td colspan="2" align="center">
			<input type="button" onclick="updateMima('<%=request.getContextPath() %>/WuYePasWordServlet','<%=request.getContextPath() %>/YCWYPage/BackPage/system/password.jsp');" value="提&nbsp;&nbsp;交" class="submit_input">
		</td>
	</tr>
</table>
</div>
<%
}
%>