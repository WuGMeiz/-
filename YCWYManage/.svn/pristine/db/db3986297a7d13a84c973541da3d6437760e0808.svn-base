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
    String tu_id = session.getAttribute("TU_ID").toString();
    String ts_id = session.getAttribute("U_ID").toString();
	String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
	List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_app_show');">×</b></div>
<div class="new-layer">	
<div align="center" class="gxg_tcdiv_nr">
<br>
		 <form name="form1"> 
			<table border="0" class="nowrap" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td align="right">小区信息:</td>
					<td height="40" width="15%">
					<select id="es_id" name="es_id" class="input_txt"  >
						<option value="">--请选择小区--</option>
							<%
							if(list.size()>0){
								TB_Estate_Village village=null;
								for(int i=0;i<list.size();i++){
									village=(TB_Estate_Village)list.get(i);
									if(!yznr.equals("")){
										
										if(yznr.contains(village.getEs_id()+"")){

					%>
						<option value="<%=village.getEs_id() %>" ><%=village.getEsName()%></option>
						<% 
										}
									}else{
							%>
							<option value="<%=village.getEs_id() %>" ><%=village.getEsName()%></option>
							<% 
									}
								}
							}
							 %>
					</select><font style="color: red;">*</font>
					</td>
					<td align="right">标题名称:</td>
					<td align="left" width="20%">
						<input type="text" id="title1" name="title1" class="input_txt"  /><font style="color: red;">*</font>
					</td>
					<td align="right" width="15%">说明:</td>
					<td align="left">
						<input type="text" id="description" name="description" class="input_txt"  />
					</td>
				</tr>
				<tr height="45">
				    <td align="right">类别:</td>
					<td align="left" width="15%">
						<select id="Type1" name="Type1" class="input_txt" >
					 			<option value="">--请选择类别--</option>
	   					         <option value="1" selected="selected">满意度调查</option>
	   					         <option value="2">投票</option>
					 	</select><font style="color: red;">*</font>
					</td>
					 <td align="right">状态:</td>
					<td align="left" width="15%">
					    <select id="status1" name="status1" class="input_txt" >
						<option value="">--请选择状态--</option>
	   					<option value="1" selected="selected">启用</option>
	   					<option value="2">暂停</option>
					 	</select><font style="color: red;">*</font>
					</td>
					<td align="right" >参与次数:</td>
					<td align="left" width="15%">
						<input type="text" id="counts" name="counts" class="input_txt" value="1" maxlength="2"/>
					</td>
				</tr>
				<tr height="45">
				   <td align="right" >开始时间:</td>
					<td align="left">
						<input type="text" class="input_txt" id="start_time" name="start_time" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					    <font style="color: red;">*</font>
					</td>
					<td align="right">结束时间:</td>
					<td align="left" width="15%">
						<input type="text" class="input_txt" id="end_time" name="end_time" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					    <font style="color: red;">*</font>
					</td>
					<td align="right">备注:</td>
					<td align="left" width="15%">
						<input type="text" id="remark" name="remark" class="input_txt"  />
					</td>
				</tr>
			
				<tr height="100">
					<td align="center" colspan="6">
						<input type="button" class="submit_input"  onfocus="this.blur();" name="tianjia" value=" 添加 " onclick="ExamineAdd('<%=divid %>','<%=request.getContextPath()%>/TB_Estate_RepPeopleServlet?arg=6','<%=request.getContextPath() %>/YCWYPage/BackPage/ProperService/mytpsz.jsp');" />
						&emsp;<input type="reset" class="submit_input" value="重置">
					
					</td>
				</tr>
			</table>
		</form>
</div>
</div>
<%
}
%>
