<%@page import="WYCommunity.T_time"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
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
	String tsid = session.getAttribute("U_ID").toString();
	String ts_id=tsid;
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
		String Un_id=S_string.formatString(request.getParameter("Un_id"));
		session.setAttribute("qxid", Es_id);
		session.setAttribute("lyid", Bu_id);
		session.setAttribute("dyid", Un_id);
		String EhNumber=S_string.formatString(request.getParameter("EhNumber"));
		
		String timesk=S_string.formatString(request.getParameter("timesk"));
		String timesj=S_string.formatString(request.getParameter("timesj"));
		
		String payStatus=S_string.formatString(request.getParameter("payStatus"));	
		
		String itemName=S_string.formatString(request.getParameter("itemName"));
		session.setAttribute("jfxid", itemName);
		String orderType=S_string.formatString(request.getParameter("orderType"));
		String payType=S_string.formatString(request.getParameter("payType"));
		String OwnerName=S_string.formatString(request.getParameter("OwnerName"));
	    String sfy=S_string.formatString(request.getParameter("sfy"));
		ts_id=" and a.ts_id='"+ts_id+"' ";
		if(!Es_id.equals("")){
			Es_id=" and a.Es_id='"+Es_id+"' ";
		}
		
		if(!Bu_id.equals("")){
			Bu_id=" and a.Bu_id='"+Bu_id+"' ";
		}
		if(!Un_id.equals("")&&!Bu_id.equals("")){
			Un_id=" and d.Un_id='"+Un_id+"' ";
		}
		if(!EhNumber.equals("")){
			EhNumber=" and d.EhNumber='"+EhNumber+"' ";
		}
		
		if(!"".equals(sfy)){
		   sfy=" and a.tu_id='"+sfy+"'";
		}
	
		String create_time="";
		if(!timesk.equals("") && !timesj.equals("")) {
			create_time=" and a.pay_time>='"+timesk+" 00:00:00' and a.pay_time<='"+timesj+" 23:59:59' ";
		}
		if(!timesk.equals("") && timesj.equals("")) {
			create_time=" and a.pay_time>='"+timesk+" 00:00:00' ";
		}
		if(timesk.equals("") && !timesj.equals("")) {
			create_time=" and a.pay_time<='"+timesj+" 23:59:59' ";
		}
		if(!payStatus.equals("")){
			payStatus=" and a.payStatus='"+payStatus+"' ";
		}
		if(!itemName.equals("")){
			itemName=" and e.Ei_id='"+itemName+"' ";
		}
		if(!orderType.equals("")){
			orderType=" and a.orderType='"+orderType+"' ";
		}
		if(!payType.equals("")){
			payType=" and a.payType='"+payType+"' ";
		}
		if(!OwnerName.equals("")){
			OwnerName=" and d.OwnerName like '%"+OwnerName+"%' ";
		}
		String LEVELS=session.getAttribute("LEVELS").toString();
		String tu_id = session.getAttribute("TU_ID").toString();
		
		if("1".equals(LEVELS)){
		   LEVELS="  and (c.BuHead='' or c.BuHead is null or c.BuHead='"+tu_id+"') ";
		}else{
		   LEVELS="";
		}
		String Condition=" 1=1 and a.status='1' and b.status='1'and c.status='1' and d.status='1' and p.print_status = 0 and p.status=1 and e.status='1'  "+ts_id+Bu_id+Es_id+Un_id+EhNumber+create_time+payStatus+itemName+payType+OwnerName+LEVELS+sfy;
		session.setAttribute("Condition", Condition);
		//System.out.println(Condition);
		
		String Innerj = " a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id  inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType  inner JOIN TB_Estate_paper p  on a.bankid = p.bankid  ";
		session.setAttribute("Innerj", Innerj);
	}
	
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Order");
	
	//设置多表联合查询时的连接语句
	pages.setInnerj(session.getAttribute("Innerj").toString());
	//分页条件
	if(session.getAttribute("Condition")!=null) {
		pages.setCondition(session.getAttribute("Condition").toString());
	}
	//每页显示多少条
	pages.setPagesize(20);
	//设置要显示哪页
	if(request.getParameter("pagenum")!=null) {
		String temp=request.getParameter("pagenum").toString();
		pages.setPagenum(Integer.parseInt(temp));
		session.setAttribute("pagenum", temp);
	} else {
		if(session.getAttribute("pagenum")!=null) {
			pages.setPagenum(Integer.parseInt(session.getAttribute("pagenum").toString()));
		}
	}
	List<TB_Estate_Order> list = new MyTB_Estate_Order().select_orders1(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	String qxid=session.getAttribute("qxid").toString();
	String lyid=session.getAttribute("lyid").toString();
	String dyid=session.getAttribute("dyid").toString();
	String jfxid=session.getAttribute("jfxid").toString();
%>
<div id="tablelist">
  	<table>
	  	<tr height="45">
		  	<%-- <td height="40" width="100%" >
				<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/createReport.jsp','dcddbb');"/>
				<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
			    <input type="button" id="qbsc" name="qbsc" value="【全部删除】"  onclick="TBOrder_qbsc('1','<%=qxid %>','<%=lyid %>','<%=dyid %>','<%=jfxid %>','<%=request.getContextPath()+"/TBGrOrder_Deleteqb"%>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/charge_select_see.jsp?type=1','select_orders');return false;"/>
			
			</td> --%>
		</tr>
	</table>
<div style="width: 100%; overflow: hidden; overflow-x: auto;" >
  	<table align="center" class="tableList" width="120%" >
  		<tr class="thead" height="45">
	  		<td align="center"></td>
		  	<td align="center" colspan="3">所在小区</td>
		  	<td align="center">订单流水号</td>
		  	<td align="center">业主姓名</td>
		  	<td align="center">缴费项</td>
		  	<td align="center">费用起止日期</td>
		  	<td align="center">支付时间</td>
		  	<td align="center">票据单号</td>
		  	<!-- <td align="center">应缴金额</td> -->
		  	<!-- <td align="center">滞纳金金额</td> -->
		  	<td align="center">实缴金额</td>
		  	<td align="center">缴费方式</td>
		  	<td align="center">状态</td>
		  	<td align="center" colspan="6">操作</td>
	  	</tr>
	  	<tbody class="tableTbody"> 
		<% if(list.size()>0){
	  		TB_Estate_Order order = null;
	  		double total_yj_all=0;
	  		double total_znj_all=0;
	  		double total_sj_all=0;
	  		for(int i=0;i<list.size();i++){
	  			order=(TB_Estate_Order)list.get(i);
	  			total_yj_all+=Double.parseDouble(order.getTotal());
	  			total_sj_all+=Double.parseDouble(order.getTotal_sj());
	  	%>
	  	<tr height="45">
	  	<td>
	  	<input  type="hidden" id="Eo_id<%=i %>" name="Eo_id" value="<%=order.getEo_id() %>"/>
	  	<input type="hidden" id="Bu_id<%=i %>" name="Bu_id" value="<%=order.getBu_id() %>"/>
	  	<input type="hidden" id="Eh_id<%=i %>" name="Eh_id" value="<%=order.getEh_id() %>"/>
	  	</td>
	  	<td align="center" colspan="3">
	  	<%=order.getEsName() %>--<%=order.getBuName() %><%if(!"".equals(order.getUnName())){%>--<%=order.getUnName() %><%} %>--<%=order.getEhNumber() %></td>
	  	<td align="center"><%=order.getBankid() %></td>
	  	<td align="center"><%=order.getOwnerName() %></td>
	  	<td align="center"><%=order.getItemName() %></td>
	  	<td align="center"><%if(order.getCost_startTime().equals("")){%><%}else{%><%=order.getCost_startTime().substring(0, 10) %>至<%=order.getCost_endTime().substring(0, 10) %><%} %></td>
	    <td align="center"><%= order.getPay_time() %> </td>
		<td align="center"><%= order.getPjNumber() %> </td>
	
		<input type="hidden" id="total<%=i %>" name="total" value="<%=S_string.DecimalFormat_string(order.getTotal(),2) %>"/>
	  	<td align="center"><%=S_string.DecimalFormat_string(order.getTotal_sj(),2) %> </td>
	  	<input type="hidden" id="total_sj<%=i %>" name="total_sj" value="<%=S_string.DecimalFormat_string(order.getTotal_sj(),2) %>"/>
		<td align="center">
		<% if(order.getPayType().equals("")){%><%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
		</td>
		<td align="center">
		<%if(order.getPrintStatus()==1 && order.getPrintStatus1() == 0){%>
		<font color='green'>已打印</font>
		<%-- <%}else if(order.getPrintStatus()==1 && order.getPrintStatus1() == 1){%>
		<font color='red'>未打印</font> --%>
		<%}else if(order.getPrintStatus()==0){%>
		<font color='red'>已作废</font>
		<%}else if(order.getPayStatus()==2){%>
		部分支付
		<%}else if(order.getPayStatus()==3){%>
		已全额退款
		<%} %> </td>
	  	<input type="hidden" id="pay_status<%=i %>" name="pay_status" value="<%=order.getPayStatus() %>"/>
	  	
	  	
	  	<!-- 操作 start -->
	
	  		<td align="center">
	  		<%if(order.getPrintStatus()==0){%>
	  		  已作废
	  		 <%}else{ %>
	  		   <input type="button"  value="作废"
				onclick="deletePrint1('<%=request.getContextPath()+"/DeletePrintServlet"%>','<%=request.getContextPath()+"/YCWYPage/BackPage/print/print_see.jsp?type=1"%>','<%= order.getBankid()%>','<%= order.getEs_id()%>','select_orders')"  />
			 <%--  <button id="xjtk" name="xjtk" onclick="deletePrint('<%= order.getBankid()%>','<%= order.getEs_id()%>')" />作废</button> --%>
	  		 <%} %>
			</td>
	  	</tr>
	  	
	  	<!-- 操作end -->
	  	
	  	<%}%>
	  	
	  	
	  	<%}else{ %>
	  	<tr height="45"><td  align="center" colspan="17">未查询到票据信息！！！</td></tr>
		<%} %>
		</tbody> 
  	</table>
  </div>
<%
String url = request.getContextPath()+"/YCWYPage/BackPage/print/print_see.jsp";
%>
<div class="pages">
	<jsp:include page="/YCWYPage/BackPage/charge/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="select_orders"/>
	</jsp:include>
</div>
<!-- 等待刷新的div层 -->
<!-- 查看修改读取层 -->  
<div id="div_edit_app_show" class="gxg_tcdiv" style="height:150%;display: none;">
</div>
<!-- 显示等待图片的读取层 -->
<div id="Loading_div_da" style="z-index:1000000;filter:Alpha(opacity=10);opacity:0.1;"></div>
<div id="Loading_div_xiao" style="margin-left:-50px;text-align:center;color:red;width:100px;height:100px;display:none;z-index:1000001;filter:Alpha(opacity=70);opacity:0.7;">
<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
</div>
</div>

<%} %>

	







 