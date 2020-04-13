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
	
	List<TB_Estate_Order> listOrder = new MyTB_Estate_Order().selectHBorders_mx(Eo_id);
%>
  
   <!-- 弹出层关闭按钮 -->
  <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('select_orders_xq');">×</b></div>
  	 <div class="new-layer" style="height: 400px">
		<table align="center" class="tableList" width="100%" >
  		<thead class="thead" height="45">
	  		<td></td>
		  	<td align="center" colspan="3">所在小区</td>
		  	<td align="center">缴费项</td>
		  	<td align="center">缴费时间</td>
		  	<td align="center">应缴金额</td>
		  	<td align="center">实缴金额</td>
		  	<td align="center">缴费方式</td>
		  	<td align="center">支付状态</td>
	  	</thead>
	  	<tbody class="tableTbody">
		<% if(list.size()>0){
	  		TB_Estate_Order order = null;
	  		double total_yj_all=0;
	  		double total_znj_all=0;
	  		double total_sj_all=0;
	  		for(int i=0;i<listOrder.size();i++){
	  			order=(TB_Estate_Order)listOrder.get(i);
	  			total_yj_all+=Double.parseDouble(order.getTotal());
	  			total_sj_all+=Double.parseDouble(order.getTotal_sj());
	  	%>
	  	<tr height="45">
	  	<td>
	  	<input  type="hidden" id="Eo_id<%=i %>" name="Eo_id" value="<%=order.getBankid() %>"/>
	  	<input type="hidden" id="Bu_id<%=i %>" name="Bu_id" value="<%=order.getBu_id() %>"/>
	  	</td>
	  	<td align="center" colspan="3">
	  	<%=order.getEsName() %>--<%=order.getBuName() %><%if(!"".equals(order.getUnName())){%>--<%=order.getUnName() %><%} %>--<%=order.getOwnerName() %> </td>
	  	<td align="center"><%=order.getItemName() %></td>
	  	<td align="center"><%=order.getPay_time()%></td>
		<td align="center"><%=S_string.DecimalFormat_string(order.getTotal(),2) %> </td>
		
	  	<td align="center"><%=S_string.DecimalFormat_string(order.getTotal_sj(),2) %> </td>
		<td align="center">
	     <% if(order.getPayType().equals("")){%><%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
		</td>
		<td align="center"><%if(order.getPayStatus()==0){%><font color='red'>未支付</font><%}else if(order.getPayStatus()==1){%><font color='green'>已支付</font><%}else if(order.getPayStatus()==2){%>部分支付<%} else if(order.getPayStatus()==3){%>已全额退款<%} %></td>
	  
	  	</tr>
	  	<%}
	  	%>
	  	<tr height="45">
	  		
			<td colspan="5"></td>
			<td align="center">合计：单位/元</td>
			<td align="center"><%=S_string.DecimalFormat_double(total_yj_all,2)%></td>
			<td align="center"><%=S_string.DecimalFormat_double(total_sj_all,2)%></td>
			<td colspan="5"></td>
	  	</tr>
	  	<%
	  	}else{ %>
	  	<tr height="45"><td  align="center" colspan="13">未查询到订单信息！！！</td></tr>
		<%} %>
		</tbody>
  	</table>
  	</div>
<%} %>
 
