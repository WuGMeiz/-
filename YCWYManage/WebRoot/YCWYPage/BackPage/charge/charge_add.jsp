<%@page import="WYBack_Stage.Bean.TB_Estate_item"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%
 if(session.getAttribute("USER_ID")==null)
{	
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	out.println("window.location='"+url+"';");
	out.println("</script>");
} 
else
{  
	String ts_id= session.getAttribute("U_ID").toString();
	List<TB_Estate_Village> list= new ChangeDao().select_xiaoqu(ts_id);
	String yznr = session.getAttribute("YZNR").toString();
%>
<div class="content">
	<div class="containtNav">
		<form action="post">
			<table class="showTdiv" >
				<tr height="45">
					<td align="right">选择小区：&nbsp;</td>
					<td>
						<select class="input_txt" id="Es_id" name="Es_id" 
						onchange="select_louyuLD(this,'louyu','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_ly.jsp"%>','<%=ts_id %>','jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/jfx.jsp"%>')" 
						<%-- onblur="TBitemjfx_select('jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/jfx.jsp"%>')" --%> />
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
						</select>
					</td>
				</tr>
				<tr height="45">
					<td align="right">选择楼宇：&nbsp;</td>
					<input type="hidden" name="sign" id="sign" value="1">
					<td>
						<div id="louyu">
							<jsp:include flush="ture" page="charge_select_ly.jsp">
								<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td>
				</tr>
				<tr id="sl_unite">
				</tr>
				<tr height="45">
					<td align="right" >房屋编号：&nbsp;</td>
					<td>
						<input type="text" id="EhNumber" name="EhNumber" class="input_txt"  />
					</td>
				</tr>
				<tr height="45">
					<td align="right"  >费用起止日期：<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" style="width: 45.7%;" class="input_txt" id="cost_startTime" name="cost_startTime" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />至
						<input type="text" style="width: 45.7%;" class="input_txt" id="cost_endTime" name="cost_endTime" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					</td>
				</tr>
				<tr height="45">
					<td align="right" >费用收取日期：&nbsp;</td>
					<td align="left">
						<input type="text" style="width: 45.7%;" class="input_txt" id="Scost_startTime" name="Scost_startTime" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />至
						<input type="text" style="width: 45.7%;" class="input_txt" id="Scost_endTime" name="Scost_endTime" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					</td>
				</tr>
				<tr height="45">
					<td align="right">收费项目：&nbsp;</td>
					<td>
						<div id="jfxdiv">
							<jsp:include page="/YCWYPage/BackPage/charge/jfx.jsp" flush="true"></jsp:include>
						</div>
					</td>
				</tr>
				<tr>
				<td align="right" >是否抹零：&nbsp;</td>
					<td align="left">
					<!-- id="is_numberInt" -->
						<input type="Radio"  name="is_numberInt" value="1">是	&nbsp;&nbsp;
						<input type="Radio"  name="is_numberInt" value="0" checked="checked">否					
					</td>
				</tr>
				<tr height="45">
					<td  align="center" colspan="2">
						<input type="button" onfocus="this.blur();" class="submit_input" 
						onclick="TB_Order('itembox','<%=request.getContextPath()%>/WyTBorder_add','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_add.jsp"%>');" value="提交">					
						&emsp;
						<input type="reset" class="submit_input" value="重置">
					</td>
				</tr>
			</table>  
		</form>
	</div>
</div>	
<%
 }
%>