<%@page import="WYBack_Stage.Dao.Mete_ReadClass"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Mation"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
	String em_id=request.getParameter("em_id");//表编号
%>
		<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('<%=divid %>');">×</b></div>
<% 
	TB_Estate_Mation tem = new Mete_ReadClass().findMa(em_id);
%>
<table style="display:block; width:800px; margin:0 auto;">
	<tr height="45"></tr>
	<tr  height="45">
		<td align="right" width="150">表名称：</td>
		<td width="300">
			<input id="Maid" class="input_txt"  name="Maid" value="<%=tem.getMa_id() %>"/>
		</td>
	</tr>
	
	<tr height="45">
		<td align="right" width="150">上次抄表数：</td>
		<td width="200">
			<input  class="input_txt" id=LastReadNum  name="LastReadNum" value="<%=tem.getLastReadNum() %>"/>
		</td>
	</tr>
	<tr height="45">
		<td align="right" width="150">本次抄表数：</td>
		<td width="200" >
			<input class="input_txt" id=NowReadNum  name="NowReadNum" value="<%=tem.getNowReadNum() %>"/>
		</td>
	</tr>
	<tr height="45">
		<td align="right" width="150">用量：</td>
		<td width="200">
			<input class="input_txt" id="UserNumber"  name="UserNumber" value="<%=tem.getUserNumber() %>"/>
		</td>
	</tr>
	<tr height="45">
		<td align="right" width="150">抄表日期：</td>
		<td width="200">
			<input class="input_txt" id="ReadDate"  name="ReadDate" value="<%=tem.getReadDate().substring(0, 10) %>"/>
		</td>
	</tr>
	<tr height="45">
		<td align="right" width="150">抄表类型：</td>
		<td width="200">
			<select id="MeteType" class="input_txt">
				<%
					if(tem.getType()==0){
				%>
					<option value="<%=tem.getType()%>">水费</option>
					<option value="1">电费</option>
					<option value="2">燃气费</option>
				<%
					}else if(tem.getType()==1){
				%>
					<option value="<%=tem.getType() %>">电费</option>
					<option value="0">水费</option>
					<option value="2">燃气费</option>
				<%
					}else{
				%>
					<option value="<%=tem.getType() %>">燃气费</option>
					<option value="0">电费</option>
					<option value="1">水费</option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr height="45">
		<td align="right" width="150">计量单位：</td>
		<td width="200">
			<select id="Unit" class="input_txt">
				<%
					if(tem.getUnit()==0){
				%>
					<option value="<%=tem.getUnit() %>">立方</option>
					<option value="1">度</option>
				<%
					}else{
				%>
					<option value="<%=tem.getUnit() %>">度</option>
					<option value="0">立方</option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr height="45"></tr>
	<tr height="45">
		<td  align="center"  colspan="5">
			<input class="submit_input"  type="button" value="确认修改" onclick="Mete_update('<%=em_id %>','<%=request.getContextPath() %>/MeteUpate_Servlet','show_mete','<%=request.getContextPath() %>/YCWYPage/BackPage/charge/mete_selectshow.jsp?type=0','div_edit_app_show')" />
		</td>
	</tr>
</table>
<%
}
%>