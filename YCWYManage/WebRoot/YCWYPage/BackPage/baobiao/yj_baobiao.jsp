<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
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
	String divid=request.getParameter("divid");
%>
<%
	String ts_id=session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
%>  
			<table width="100%" height="100%" >
				<tr  height="45">
					<td align="right">小区名称：</td>
					<td>
						<select id="Es_id" name="Es_id" class="input_txt" 
						onchange="select_louyu(this,'louyu','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_ly.jsp"%>','<%=ts_id %>')"
						onblur="TBitemjfx_select('jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/jfx1.jsp"%>')">
							<option value="">--请选择单位--</option>
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
					<td align="right">楼宇名称：</td>
					<td >
						<div id="louyu">
							<jsp:include flush="ture" page="charge_select_ly.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td>
					<td align="right">缴费时间：</td>
					<td colspan="2">
						<input type="text" style="width: 30%;" class="input_txt" id="timesk" name="timesk" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />至
						<input type="text" style="width: 30%;" class="input_txt" id="timesj" name="timesj" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					</td>
				</tr>
				<tr height="45">
				<td align="right">房屋编号：</td>
					<td>
						<input class="input_txt" id="EhNumber" name="EhNumber" type="text" maxlength="100"/>
					</td>
					<td align="right">业主姓名：</td>
					<td>
						<input class="input_txt" id="OwnerName" name="OwnerName" type="text" />
					</td>
					<td align="right">收费项目：</td>
					<td>
					
						<div id="jfxdiv">
							<jsp:include page="/YCWYPage/BackPage/charge/jfx1.jsp" flush="true"></jsp:include>
						</div>
					</td>
					
					<td>
						<input type="reset" class="submit_input" value="查询" onclick="select_orders_zzjf('select_zzjf','<%=request.getContextPath()+"/YCWYPage/BackPage/baobiao/yj_baobiao_see.jsp"%>','<%=ts_id%>')">
					</td>
					
				</tr>
			</table>
<div id="select_zzjf"></div>			
<%
}
%>
