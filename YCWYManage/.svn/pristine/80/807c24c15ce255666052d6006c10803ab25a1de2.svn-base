<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
	String ts_id = session.getAttribute("U_ID").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
	String divid=request.getParameter("divid");
	String Eo_id=S_string.formatString(request.getParameter("Eo_id"));
	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
	TB_Estate_Order order = new MyTB_Estate_Order().select_orders_mx(Eo_id); 
%>
  <body>
<!-- 弹出层关闭按钮 -->
  <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('select_orders_xg');">×</b></div>
  	<input type="hidden" id="Bu_id" name="Bu_id" value="<%=order.getBu_id() %>"/>
	<div class="new-layer">
	<table width="100%" class="tableTbody" >
		<tr height="45">
			<input type="hidden" id="Eo_id" name="Eo_id" value="<%=order.getEo_id() %>" />
			<td align="right">所在小区:</td>
			<td >
				<select disabled="disabled" class="input_txt" id="Es_id" name="Es_id" onchange="select_louyu(this,'louyu1','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_ly2.jsp"%>','<%=ts_id %>')" >
					<option value="">--请选择小区--</option>
						<%
						if(list.size()>0){
							TB_Estate_Village village=null;
							for(int i=0;i<list.size();i++){
								village=(TB_Estate_Village)list.get(i);
						%>
						<option value="<%=village.getEs_id()%>" <%if(order.getEs_id()==village.getEs_id()){%> selected="selected" <%} %>><%=village.getEsName() %></option>
						<%
							}
						} 
						 %>
				</select>
			</td>
		
			<td align="right">所在楼宇:</td>
			<td >
				<div id="louyu1">
					<jsp:include flush="ture" page="charge_select_ly2.jsp">
					<jsp:param value="<%=ts_id %>" name="ts_id"/>
					<jsp:param value="<%=order.getEs_id() %>" name="Es_id"/>
					<jsp:param value="<%=order.getBu_id() %>" name="Bu_id"/>
					</jsp:include>
				</div>
			</td>
			
		</tr>
		<tr height="45">
			<td align="right">订单类型：</td>
			<td><%if(order.getOrderType()==1){%>查缴订单<%}else if(order.getOrderType()==2){%>预缴订单<%}%></td>
			<td align="right">缴费项：</td>
			<td><%=order.getItemName() %></td>
		</tr>
		<tr height="45">
		<td align="right">加减金额：</td>
			<td><input class="input_txt" id="b2" name="b2" value=""/></td>
			<td align="right">修改说明：</td>
			<td><input class="input_txt" id="up_Reason" name="up_Reason" value=""/></td>
			<td></td>
		</tr>
		<tr height="45">
			<td align="right">应缴金额：</td>
			<td><input class="input_txt" id="total" name="total" value="<%=S_string.DecimalFormat_string(order.getTotal(),2) %>"/></td>
			<td colspan="6"></td>
		</tr>
		<tr height="45">
			<td  align="center" colspan="8">
				<input type="button"  class="submit_input" value="修改" onclick="update_orders('<%=request.getContextPath()+"/TB_Estate_Order_upServlrt"%>','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_see.jsp?type=1"%>','<%=ts_id%>','select_orders','0');">					
			</td>
		</tr>
	</table>
</div>
  </body>
<%} %>
</html>
