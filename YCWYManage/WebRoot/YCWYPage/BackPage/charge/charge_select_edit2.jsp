<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYCommunity.T_time"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
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
	String ts_id = session.getAttribute("U_ID").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
	String isswept=new MyTB_Estate_Order().getIsswept(ts_id);
	String divid=request.getParameter("divid");
	String type=request.getParameter("type");
	String Eo_id=S_string.formatString(request.getParameter("Eo_id"));
	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
	String znj=S_string.formatString(request.getParameter("total_znj"));
	new MyTB_Estate_Order().updateTotalZNJ(ts_id, Eo_id, znj);
	TB_Estate_Order order = new MyTB_Estate_Order().select_orders_mx(Eo_id); 
	/*** 被扫支付从客户端页面需要传值 ***/
	String url = request.getContextPath()+"/YCWYPage/BackPage/swept/SweptAway.jsp";	//	去支付前处理报文签名的页面地址
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
			<td><%if(order.getOrderType()==1){%>查缴订单<%}else if(order.getOrderType()==2){%>预缴订单<%}else if(order.getOrderType()==3){%>固额订单<%}%></td>
			<td align="right">缴费项：</td>
			<td><%=order.getItemName() %></td>
		</tr>
		<tr height="45">
			<td align="right">费用起止日期：</td>
			<td><%if(order.getCost_startTime().equals("")){%><%}else{%><%=order.getCost_startTime().substring(0, 10) %>至<%=order.getCost_endTime().substring(0, 10) %><%} %></td>
			<td align="right">缴费方式：</td>
			<td>
			 <select class="input_txt"   id="jfType" name="jfType">
				<option value="">--请选择线下缴费方式--</option>
				<%if("".equals(session.getAttribute("PayWays").toString()) ){ %>
				<option value="1">现金支付</option>
				<option value="6">主扫支付</option>
				<option value="3">转账支付</option>
				<option value="4">刷卡支付</option>
				<option value="5">调账支付</option>
				<%}else{ 
				%>
				 <%if(session.getAttribute("PayWays").toString().contains("1")){ %>  
				  <option value="1">现金支付</option>
				 <%} %>
				 <%if(session.getAttribute("PayWays").toString().contains("6")){ %>  
				  <option value="6">主扫支付</option>
				 <%} %>
				 <%if(session.getAttribute("PayWays").toString().contains("3")){ %>  
				  <option value="3">转账支付</option>
				 <%} %>
				 <%if(session.getAttribute("PayWays").toString().contains("4")){ %>  
				  <option value="4">刷卡支付</option>
				 <%} %>
				 <%if(session.getAttribute("PayWays").toString().contains("5")){ %>  
				  <option value="5">调账支付</option>
				 <%} %>
				<%} %>
			 </select> 
			</td>
		</tr>
		<tr height="45">
			<td align="right">应缴金额：</td>
			<td><%=S_string.DecimalFormat_string(order.getTotal(),2) %></td>
			<td align="right">滞纳金金额：</td>
			<td><%=request.getParameter("total_znj") %></td>
		</tr>
		 
		<% String payManey = S_string.DecimalFormat_string(Double.parseDouble(request.getParameter("total_znj")) + Double.parseDouble(S_string.DecimalFormat_string(order.getTotal(),2))+"",2) ; %>
		<tr height="45">
			<td align="right">实缴金额：</td>
			<td><input class="input_txt" id="total_sj" name="total_sj" disabled="disabled" 
			value="<%=payManey %>"/></td>
		</tr>
		<tr height="45">
			<td  align="center" colspan="8">
			<% 
 			if("1".equals(isswept)){
			%>
				<input type="button"  class="submit_input" value="被扫支付" onclick="showThis('<%=type %>','showswept','<%=url %>','<%=order.getTs_id() %>','<%=payManey%>')" /> 			 			
 			<%} %>
				<%
				
					if("1".equals(type)){
				%>
					<input type="button"  class="submit_input" value="线下支付" onclick="update_orders_rgsf('<%=request.getContextPath()+"/TB_Estate_Order_uprgsfServlrt?args=1"%>','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_see.jsp?type=1"%>','select_orders');"> 
				<%		
					}else if("2".equals(type)){
				%>
					<input type="button"  class="submit_input" value="线下支付" onclick="update_orders_rgsf('<%=request.getContextPath()+"/TB_Estate_Order_uprgsfServlrt?args=1"%>','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/gue_cx_see.jsp?type=1"%>','select_gue_orders');"> 
				<%		
					}
				%>
			</td>
		</tr>
	</table>
</div>
 
 
<%} %>
