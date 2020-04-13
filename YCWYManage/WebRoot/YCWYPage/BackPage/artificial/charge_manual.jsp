<%@page import="WYCommunity.MakeOrderNum"%>
<%@page import="WYCommunity.T_time"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
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
	 String yznr = session.getAttribute("YZNR").toString();
	 List<TB_Estate_Village> list= new ChangeDao().select_xiaoqu(ts_id);
	 String isswept=new MyTB_Estate_Order().getIsswept(ts_id);
	 /*** 被扫支付从客户端页面需要传值 ***/
	 String url3 = request.getContextPath()+"/YCWYPage/BackPage/swept/SweptAway.jsp";	//	去支付前处理报文签名的页面地址
%>
<div class="content" id="content">
	<div class="containtNav">
			<table class="showTdiv" >
			<tbody>
				<tr height="45">
				
				<td align="right">选择小区：&nbsp;</td>
				<td>
				<input type="hidden" name="ts_id" id="ts_id" value="<%=ts_id %>" />
					<select class="input_txt"   id="Es_id" name="Es_id" 
					onchange="select_louyuLD1(this,'louyu','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/charge_select_ly.jsp"%>','<%=ts_id %>','jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/jfx2.jsp"%>')" 
					<%-- onblur="TBitemjfx_select('jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/jfx2.jsp"%>')" --%>  style="width: 320px;"/>
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
				
				<td >
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
			<td align="right">房屋编号：&nbsp;</td>
			<td>
			<input type="text" id="EhNumber" name="EhNumber" class="input_txt"  style="width: 320px;" 
			onchange="select_EhName(this,'EhNames','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/charge_ehNames.jsp"%>','<%=ts_id %>','jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/jfx4.jsp"%>');"
			/>
			</td>
			</tr>
			<tr height="45">
			<td align="right">户主姓名：&nbsp;</td>
			<td id="EhNames">
			<input type="text" id="EhName" name="EhName" class="input_txt"  style="width: 320px;" readonly="readonly"/>
			</td>
			</tr>
		   <tr height="45">
			<td align="right">缴费方式：&nbsp;</td>
			<td >
			 <select class="input_txt"  id="payType" name="payType">
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
			<td align="right" >收费项目：&nbsp;</td>
			<td>
			 <div id="jfxdiv">
				<jsp:include page="/YCWYPage/BackPage/artificial/jfx4.jsp" flush="true"></jsp:include>
			 </div>
			</td>
			</tr>
			<tr height="45">
			<td align="right">缴费金额：&nbsp;</td>
			<td>
			  <div id="jine">
			  	<input type="text" class="input_txt" placeholder="请输入缴费金额" name="total" id="total" value=""  style="width: 320px;"/>
			  </div>
			</td>
			</tr>
			
			<tr height="45">
				<td  align="center" colspan="2">
				<% 
	 			if("1".equals(isswept)){
				%>
					<input type="button" id="BSbtn" class="submit_input" value="被扫支付" onclick="add_orderBS1('<%=url3 %>','<%=request.getContextPath()%>/Order_rgsf_Upswept','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/Swept.jsp"%>','cont')" /> 
				<%} %>
					<input type="button" class="submit_input" onclick="add_order_rgsf('<%=request.getContextPath()%>/Tb_Estate_Order_rgsf','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/charge_manual.jsp"%>','content')" value="线下支付">					
				</td>
			</tr>
			 
			</tbody>
			</table>  
		</div>
	</div>	
<div id="cont"></div>
		 

<%
 }
%>