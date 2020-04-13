<%@page import="WYCommunity.T_time"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
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
	String ts_id=session.getAttribute("U_ID").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
	String divid=request.getParameter("divid");
	String Eo_id=S_string.formatString(request.getParameter("Eo_id"));
	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
	
	TB_Estate_Order order = new MyTB_Estate_Order().select_orders_mx(Eo_id); 
%>
  
   <!-- 弹出层关闭按钮 -->
  <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('select_orders_xq');">×</b></div>
  	<input type="hidden" id="Bu_id" name="Bu_id" value="<%=order.getBu_id() %>"/>
  	<div class="new-layer" >
	<table width="100%" class="tableTbody">
	
		<tr>
			<td align="right">所在小区:</td>
			<td >
				<select class="input_txt" disabled="disabled" id="Es_id" name="Es_id" onchange="select_louyu(this,'louyu','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_ly.jsp"%>','<%=ts_id %>')" >
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
				<div id="louyu">
					<jsp:include flush="ture" page="charge_select_ly2.jsp">
					<jsp:param value="<%=ts_id %>" name="ts_id"/>
					<jsp:param value="<%=order.getEs_id() %>" name="Es_id"/>
					<jsp:param value="<%=order.getBu_id() %>" name="Bu_id"/>
					</jsp:include>
				</div>
			</td>
			<td align="right">业主姓名：</td>
			<td><%=order.getOwnerName()%></td>
			<td align="right">房屋编号：</td>
			<td><%=order.getEhNumber()%></td>
		</tr>
		<tr>
			<td align="right">银行流水号：</td>
			<td><%=order.getBankid()%></td>
			<td align="right">应缴金额：</td>
			<td><%=S_string.DecimalFormat_string(order.getTotal(),2)%></td>
			<td align="right">实缴金额：</td>
			<td><%=S_string.DecimalFormat_string(order.getTotal_sj(),2)%></td>
			<td align="right">订单类型：</td>
			<td><%if(order.getOrderType()==1){%>查缴订单<%}else if(order.getOrderType()==2){%>预缴订单<%}else{%>固额订单<%}%></td>
		</tr>
		<tr>
			<td align="right">缴费方式：</td>
			<td align="left">
		    <% if(order.getPayType().equals("")){%>未支付<%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
			</td>
			<td align="right">创建时间：</td>
			<td><%=order.getCreat_time()%></td>
			<td align="right">支付时间：</td>
			<td><%=order.getPay_time() %></td>
			<td align="right">支付状态：</td>
			<td><%if(order.getPayStatus()==0){%><font color='red'>未支付</font><%}else if(order.getPayStatus()==1){%><font color='green'>已支付</font><%}else if(order.getPayStatus()==2){%>部分支付<%} %></td>
		</tr>
		<tr>
			<td align="right">费用开始期：</td>
			<td><%=order.getCost_startTime()%></td>
			<td align="right">费用结束期：</td>
			<td><%=order.getCost_endTime()%></td>
			<td align="right">收取开始时间：</td>
			<td><%=order.getScost_startTime()%></td>
			<td align="right">收取结束时间：</td>
			<td><%=order.getScost_endTime()%></td>
			<td colspan="6"></td>
		</tr>
		<tr height="45">
			<td  align="center" colspan="10">
				<input type="button"  class="submit_input" value="确定" onclick="close_tanchu_div('select_orders_xq');">					
			</td>
		</tr>
	</table>
	</div>
<%} %>
 
