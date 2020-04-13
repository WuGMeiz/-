<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
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
	String isswept=new MyTB_Estate_Order().getIsswept(ts_id);
	String divid=request.getParameter("divid");
	String sqlstr=request.getParameter("sqlstr");//以“;” 分隔的订单号
	String znj_sum = request.getParameter("znj_sum");//以“;” 分隔的订单号
	String[] znj_sum_split = znj_sum.split(";");
	String[] strEo_id = sqlstr.split(";");
	
	MyTB_Estate_Order estater_order = new MyTB_Estate_Order();
	 String str = "";
	for(int i = 0 ; i< znj_sum_split.length ; i++){
	
	boolean bl = estater_order.updateTotalZNJ(ts_id, strEo_id[i], znj_sum_split[i]);
	str += bl;
	
	}
	if(str.indexOf("false") ==-1) {
		int offset = 0;
		int cnt = 0;
		while((offset = sqlstr.indexOf(";", offset)) != -1){
	     offset = offset + ";".length();
	     cnt++;
		}
		String l_content="批量修改滞纳金："+cnt+"个,"+"订单号:"+sqlstr;
		ChangeDao.add_Log(l_content, session.getAttribute("USER_ID").toString() , "2", session.getAttribute("TU_ID").toString());
	}
	//sqlstr = sqlstr.replaceAll(";", "','");
	String type = request.getParameter("type");
	TB_Estate_Order order = new MyTB_Estate_Order().getPLXJSFOrders(sqlstr);

	/*** 被扫支付从客户端页面需要传值 ***/
	String url = request.getContextPath()+ "/YCWYPage/BackPage/swept/SweptAway.jsp"; //	去支付前处理报文签名的页面地址
%>
 
<!-- 弹出层关闭按钮 -->
  <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('select_orders_pl');">×</b></div>
	<div class="new-layer">
	<table width="100%" class="tableTbody" >
		<tr height="45">
			<td align="right">所在小区</td>
			<input  type="hidden" id="Eo_id" name="Eo_id" value="<%=sqlstr %>"/>
			<td><%=order.getEsName() %>--<%=order.getBuName() %><%if(!"".equals(order.getUnName())){%>
			--<%=order.getUnName() %><%} %>--<%=order.getEhNumber() %></td>
		</tr>
		 <tr height="45">
			<td align="right">姓名：</td>
			<td><%=order.getOwnerName() %></td>
		</tr>
		<tr height="45">
			<td align="right">订单类型：</td>
			<td><%if(order.getOrderType()==1){%>查缴订单<%}else if(order.getOrderType()==2){%>预缴订单<%}else if(order.getOrderType()==3){%>固额订单<%}%></td>
		</tr>
		<tr height="45">
			<td align="right">缴费项：</td>
			<td><%=order.getItemName() %>
			</td>
		</tr>
		   
		<tr height="45">
			<td align="right">明细：</td>
			<td><%=order.getRemark()+order.getTotal_znj()%>
			
			</td>
		</tr>
		<tr height="45">
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
		<!-- 滞纳金计算 -->
		<%
			String sum_znj = "";
			double temp = 0.00;
			for(int i = 0 ; i < znj_sum_split.length ; i++){
			
			temp += Double.parseDouble(znj_sum_split[i]);
			
			}
			sum_znj = String.valueOf(temp);
		
		 %>
		<tr height="45">
			<td align="right">滞纳金额：</td>
			<td>
			<%=S_string.DecimalFormat_string(sum_znj,2) %>
			<input class="input_txt" id="znj_sum" name="znj_sum" type="hidden" value=<%=znj_sum %>/>
			</td>
			 
		</tr>
		<%
		   double yjje = Double.parseDouble(order.getTotal())+Double.parseDouble(sum_znj);
		 %>
		<tr height="45">
			<td align="right">应缴金额：</td>
			<td>
			<%=S_string.DecimalFormat_string(order.getTotal(),2) %>
			<input class="input_txt" id="total" name="total" type="hidden" value=<%=S_string.DecimalFormat_string(order.getTotal(),2) %>/>
			</td>
			 
		</tr>
		
		
		<tr height="45">
			<td align="right">实缴金额：</td>
			<td><input class="input_txt" id="total_sj" name="total_sj" disabled="disabled" value="<%=S_string.DecimalFormat_string(String.valueOf(yjje),2) %>"/>
			</td>
		</tr>  
		<tr height="45">
			<td  align="center" colspan="8">
			<% 
 			if("1".equals(isswept)){
			%>
			   <input type="button"  class="submit_input" value="被扫支付" onclick="showThis('<%=type %>','showswept','<%=url %>','<%=order.getTs_id() %>','<%=S_string.DecimalFormat_string(String.valueOf(yjje),2) %>')" /> 			 			
				
				<%
 			}
				if("1".equals(type)){
				%>
				<input type="button"  class="submit_input" value="线下支付" onclick="pldelTB_Estate_OrderXj('<%=sqlstr %>','<%=request.getContextPath()+"/TB_Estate_Order_plxj"%>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/charge_select_see.jsp?type=1','select_orders');return false;"> 
				<%	
				}else{
				%>
				<input type="button"  class="submit_input" value="线下支付" onclick="pldelTB_Estate_OrderXj('<%=sqlstr %>','<%=request.getContextPath()+"/TB_Estate_Order_plxj"%>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/gue_cx_see.jsp?type=1','select_gue_orders');return false;"> 
				<%	
				}
				%>
			</td>
		</tr>
	</table>
</div>
 
 
<%} %>
