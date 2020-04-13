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
			<td><%=order.getEsName()%></td>
			<td align="right">所在楼宇:</td>
			<td><%=order.getBuName()%></td>
			<td align="right">所在单元:</td>
			<td><%=order.getUnName()%></td>
			<td align="right">业主姓名：</td>
			<td><%=order.getOwnerName()%></td>
		</tr>
		<tr>
			<td align="right">房屋编号：</td>
			<td><%=order.getEhNumber()%></td>
			<td align="right">银行流水号：</td>
			<td><%=order.getBankid()%></td>
			<td align="right">应缴金额：</td>
			<td><%=S_string.DecimalFormat_string(order.getTotal(),2)%></td>
			
			<td align="right">滞纳金额：</td> 
			<td>
			<%	if(order.getPayStatus()==0){
				String yj_total = order.getTotal();//应缴金额
			String znjDay = order.getZnjDay();//滞纳规定天数
			String znjRatio = order.getZnjRatio();//滞纳比例
			 
			double znj_total = 0;//滞纳金
			double znj_gongshi = 0;//滞纳金计算公式
			if(order.getScost_endTime().equals("1900-01-01 00:00:00.0") ||order.getScost_endTime().equals("")||order.getScost_endTime()==null){
				 znj_total = 0;//滞纳金
				 znj_gongshi = 0;//滞纳金计算公式
			}else{
				String str = order.getScost_endTime().substring(0,10)+" 23:59:59";
				String str1 = new T_time().getTime();
				String Scost_endTime = new T_time().getymdhms(str);//缴费结束日期
				long endtime = Long.parseLong(Scost_endTime);
				
				String the_day = new T_time().getymdhms(str1);//当前时间
				long theday = Long.parseLong(the_day);
				
				int zndaynum = 0;
				if(endtime-theday<0){
					zndaynum = new  T_time().getDaysBetween(str,str1);//计算滞纳天数
				}
				
				if(znjDay.equals("") || znjDay.equals("null")){//如果为空
						znj_gongshi = 0;
				}else if(Integer.parseInt(znjDay)<0){//如果为负数
					if((zndaynum+Integer.parseInt(znjDay))<=0){//小于等于0，为宽限天数
						znj_gongshi = 0;
					}else{
						znj_gongshi = (zndaynum+Integer.parseInt(znjDay))*Double.parseDouble(znjRatio);//（滞纳天数+滞纳规定天数）*滞纳金比例
					}
				}else{//如果为正数
					
					if((zndaynum-Integer.parseInt(znjDay))<0){//大于0
						znj_gongshi = zndaynum*Double.parseDouble(znjRatio);//（滞纳天数）*滞纳金比例
					}else{
						znj_gongshi = Integer.parseInt(znjDay)*Double.parseDouble(znjRatio);//滞纳规定天数*滞纳金比例
					}
				}
			}
			znj_total = Double.parseDouble(yj_total)*znj_gongshi;//滞纳金	
			%>
			<%=S_string.DecimalFormat_double(znj_total,2) %>
			<%
		  	}else{
		  	%>
				<%=S_string.DecimalFormat_string(order.getTotal_znj(),2) %>
		<%	} %></td>
		</tr>
		<tr>
			<td align="right">实缴金额：</td>
			<td><%=S_string.DecimalFormat_string(order.getTotal_sj(),2)%></td>
			<td align="right">订单类型：</td>
			<td><%if(order.getOrderType()==1){%>查缴订单<%}else if(order.getOrderType()==2){%>预缴订单<%}else{%>固额订单<%}%></td>
			<td align="right">缴费项：</td>
			<td><%=order.getItemName() %></td>
			<td align="right">缴费方式：</td>
			<td align="left">
			  <% if(order.getPayType().equals("")){%><%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
			</td>
		</tr>
		<tr>
			<td align="right">创建时间：</td>
			<td><%=order.getCreat_time()%></td>
			<td align="right">支付时间：</td>
			<td><%=order.getPay_time() %></td>
			<td align="right">支付状态：</td>
			<td><%if(order.getPayStatus()==0){%><font color='red'>未支付</font><%}else if(order.getPayStatus()==1){%><font color='green'>已支付</font><%}else if(order.getPayStatus()==2){%>部分支付<%} %></td>
			<td align="right">退款金额：</td>
			<td><%=order.getTk_total()%></td>
		</tr>
		<tr>
			<td align="right">退款时间：</td>
			<td><%=order.getTk_time()%></td>
			<td align="right">退款方式：</td>
			<td><%=order.getTk_type()%></td>
			<td align="right">退款状态：</td>
			<td><%if(order.getTk_status().equals("1")){%>全部退款<%}else if(order.getTk_status().equals(2)){%>部分退款<%}else{%><%=order.getTk_status()%><%}%></td>
			<td align="right">退款原因：</td>
			<td><%=order.getTk_Reason()%></td>
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
		</tr>
		<tr height="45">
			<td  align="center" colspan="8">
				<input type="button"  class="submit_input" value="确定" onclick="close_tanchu_div('select_orders_xq');">					
			</td>
		</tr>
	</table>
	</div>
<%} %>
 
