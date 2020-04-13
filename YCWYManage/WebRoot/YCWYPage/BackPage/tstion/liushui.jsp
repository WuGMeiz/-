<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%
if(session.getAttribute("USER_ID")==null)
{	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
out.println("window.location='"+url+"';");
out.println("</script>");
} 
else
{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String ts_id=session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
%>
<div class="tables">
<table class="show_table">
	<tr>	
		<td align="left" style="color: red;font-size: 12px;" colspan="4">
		&emsp;提示：网上支付由于建行的记账规则，所以最早只能查询到前一天的数据。
		</td>	
	</tr>	
	<tr>
		<td align="right" width="20%">查询小区：</td>
		<td align="left" width="15%">
			<select id="Es_id"  name="Es_id" class="input_txt" >
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
		<td align="left" width="50%">
			起始时间：
			<input type="text" class="input_txt" id="timesk" name="timesk" style="width: 160px;" onclick="showcalendar(event,this);" 
				onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly"
				value="<%= sdf.format(date)%>">
			结束时间：<input type="text" class="input_txt"  id="timesj" name="timesj" style="width: 160px;" onclick="showcalendar(event,this);" 
			onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly"
			value="<%= sdf.format(date)%>">
		</td>
		<td align="left" width="15%">
			<input type="button" name="xinjjs" class="submit_input" value="查询订单" onclick="select_liushui('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/tstion/liushui_show.jsp"%>');return false;"/>	
		</td>
	</tr>
</table>
<hr width="100%" style="margin-bottom: 10px;margin-top: 10px;color: #999999;text-align: center;"/>

<div id="show_update">

</div>	
</div>
<!-- 查看修改读取层 -->  
<div id="div_app_show" class="gxg_tcdiv">
</div>	
<!-- 查看修改读取层 -->  
<div id="div_edit_app_show" class="gxg_tcdiv">
</div>	
<%
}
%>