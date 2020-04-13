<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYCommunity.T_time"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	String ts_id = session.getAttribute("U_ID").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
	String divid=request.getParameter("divid");
	String Eo_id=S_string.formatString(request.getParameter("Eo_id"));
	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
	TB_Estate_Order order = new MyTB_Estate_Order().select_orders_mx(Eo_id); 
%>
<!-- 弹出层关闭按钮 -->
  <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('select_orders_xg');">×</b></div>
  	<input type="hidden" id="Bu_id" name="Bu_id" value="<%=order.getBu_id() %>"/>
	<div class="new-layer">
	<table width="100%" class="tableTbody" >
		<tr height="45">
			<input type="hidden" id="Eo_id" name="Eo_id" value="<%=order.getEo_id() %>" />
			<!-- var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&total_sj="+total_sj+"&payType="+payType+"&Bu_id="+Bu_id; -->
			<input type="hidden" id="ts_id" name="ts_id" value="<%=request.getParameter("ts_id") %>" />
			<input type="hidden" id="total" name="total" value="<%=request.getParameter("total") %>" />
			<input type="hidden" id="total_znj" name="total_znj" value="<%=request.getParameter("total_znj") %>" />
			<input type="hidden" id="pay_status" name="pay_status" value="<%=request.getParameter("pay_status") %>" />
			<input type="hidden" id="total_sj" name="total_sj" value="<%=request.getParameter("total_sj") %>" />
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
	 		<%if(order.getOrderType()==1){%>
	 			<td align="right">费用起止日期：</td>
				<td><%=order.getCost_startTime().substring(0, 10) %>至<%=order.getCost_endTime().substring(0, 10) %></td>
			<%}else if(order.getOrderType()==2){%>

				<td align="right">订单编号：</td>
				<td><%=order.getEo_id() %></td>
			<%}%>
			
			<td align="right">缴费方式：</td>
			<td>
			<% if(order.getPayType().equals("")){%><%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
		   </td>
		</tr>
		<tr height="45">
			<td align="right">应缴金额：</td>
			<td><%=S_string.DecimalFormat_string(order.getTotal(),2) %></td>
			<td align="right">滞纳金金额：</td>
			<td><%=request.getParameter("total_znj") %></td>
		</tr>
		<tr height="45">
			<td align="right">实缴金额：</td>
			<td style="color:red;"><%=S_string.DecimalFormat_string(order.getTotal_sj(),2)%></td>
			<td align="right">缴费时间：</td>
			<td><%=order.getPay_time()%></td>
		</tr>
			<tr height="45">
			<td align="right">退款说明：</td>
			<td ><input class="input_txt" id="tk_Reason" name="tk_Reason" value=""/></td>
		</tr>
		<tr height="45">
			<td  align="center" colspan="8">
				<input type="button"  class="submit_input" value="确认退款" onclick="update_orders_rgtk2('<%=request.getContextPath()+"/TB_Estate_Order_uprgsfServlrt?args=2"%>','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/charge_self_see.jsp"%>','select_gue_orders');"> 			
			</td>
		</tr>
	</table>
</div>
<%} %>
