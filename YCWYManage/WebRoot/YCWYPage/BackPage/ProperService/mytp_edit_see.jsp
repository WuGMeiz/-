<%@page import="WYBack_Stage.Bean.TB_Estate_Examine"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_ExDao"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>


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
	String parID=request.getParameter("parID");
    String ts_id = session.getAttribute("U_ID").toString();
    TB_Estate_Examine exam=new TB_Estate_ExDao().getExamInfo(ts_id, parID);
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_edit_app_show');">×</b></div>
<div class="new-layer">	
<div align="center" class="gxg_tcdiv_nr">
<br>
		 <form name="form1"> 
			<table border="0" class="nowrap" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td align="right">小区信息:</td>
					<td height="40" width="15%">
					<select id="es_id2" name="es_id2" class="input_txt"  >
						<option value="<%=exam.getEs_id()%>"><%=exam.getEsName() %></option>
							
					</select><font style="color: red;">*</font>
					</td>
					<td align="right">标题名称:</td>
					<td align="left" width="20%">
						<input type="text" id="title2" name="title2" class="input_txt" value="<%=exam.getTitle() %>" /><font style="color: red;">*</font>
					</td>
					<td align="right" width="15%">说明:</td>
					<td align="left">
						<input type="text" id="description2" name="description2" class="input_txt" value="<%=exam.getDescription() %>" />
					</td>
				</tr>
				<tr height="45">
				    <td align="right">类别:</td>
					<td align="left" width="15%">
						<select id="Type2" name="Type2" class="input_txt" >
					         <%
					          if(exam.getType()==1){
					         %>
					           <option value="1" selected="selected">满意度调查</option>
					         <%}else if(exam.getType()==2){ %>
					           <option value="2" selected="selected">投票</option>
					         <%} %>
				 			 <option value="">--请选择类别--</option>
   					         <option value="1">满意度调查</option>
   					         <option value="2">投票</option>
					 	</select><font style="color: red;">*</font>
					</td>
					 <td align="right">状态:</td>
					<td align="left" width="15%">
					    <select id="status2" name="status2" class="input_txt" >
					    <%
					      if(exam.getStatus()==1){
					    %>
					      <option value="1" selected="selected">启用</option>
					    <%}else if(exam.getStatus()==2){ %>
					      <option value="2" selected="selected">暂停</option>
					    <%} %>
						<option value="">--请选择状态--</option>
	   					<option value="1">启用</option>
	   					<option value="2">暂停</option>
					 	</select><font style="color: red;">*</font>
					</td>
					<td align="right" >参与次数:</td>
					<td align="left" width="15%">
						<input type="text" id="counts2" name="counts2" class="input_txt" value="<%=exam.getCounts() %>" maxlength="2"/>
					</td>
				</tr>
				<tr height="45">
				   <td align="right" >开始时间:</td>
					<td align="left">
						<input type="text" class="input_txt" id="start_time2" name="start_time2" value="<%=exam.getStart_time() %>" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					    <font style="color: red;">*</font>
					</td>
					<td align="right">结束时间:</td>
					<td align="left" width="15%">
						<input type="text" class="input_txt" id="end_time2" name="end_time2" value="<%=exam.getEnd_time() %>" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					    <font style="color: red;">*</font>
					</td>
					<td align="right">备注:</td>
					<td align="left" width="15%">
						<input type="text" id="remark2" name="remark2" class="input_txt" value="<%=exam.getRemark() %>" />
					</td>
				</tr>
			
				<tr height="100">
					<td align="center" colspan="6">
						<input type="button" class="submit_input"  onfocus="this.blur();" name="tianjia" value=" 修改 " onclick="ExamineUpdate('<%=parID %>','<%=request.getContextPath()%>/TB_Estate_RepPeopleServlet?arg=8','<%=request.getContextPath() %>/YCWYPage/BackPage/ProperService/mytp_update.jsp?type=1','show_update','div_edit_app_show');" />
					
					</td>
				</tr>
			</table>
		</form>
</div>
</div>
<%
}
%>
