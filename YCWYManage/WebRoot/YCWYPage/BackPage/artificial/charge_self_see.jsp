<%@page import="java.net.URLDecoder"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_paper"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_paperDao"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
	request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String tsid ="";

if (session.getAttribute("USER_ID") == null) 
{
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} 
else 
{
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		tsid = session.getAttribute("U_ID").toString();
		String ts_id=tsid;
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
		String Un_id=S_string.formatString(request.getParameter("Un_id"));
		String timesk=S_string.formatString(request.getParameter("timesk"));
		String timesj=S_string.formatString(request.getParameter("timesj"));
		String EhNumber=S_string.formatString(request.getParameter("EhNumber"));
		String OwnerName=S_string.formatString(request.getParameter("OwnerName"));
		String itemName=S_string.formatString(request.getParameter("itemName"));
		String payType=S_string.formatString(request.getParameter("payType"));
		String payItem=S_string.formatString(request.getParameter("payItem"));
		String payStatus=S_string.formatString(request.getParameter("payStatus"));
		String sfy=S_string.formatString(request.getParameter("sfy"));
		ts_id=" and a.ts_id='"+ts_id+"' ";
		if(!Es_id.equals("")){
			Es_id=" and a.Es_id='"+Es_id+"' ";
		}
		if(!Bu_id.equals("")){
			Bu_id=" and a.Bu_id='"+Bu_id+"' ";
		}
		if(!Bu_id.equals("")&&!Un_id.equals("")){
			Un_id=" and d.Un_id='"+Un_id+"' ";
		}
		if(!"".equals(sfy)){
		   sfy=" and a.tu_id='"+sfy+"'";
		}
		if(!"".equals(payStatus)){
		   payStatus=" and a.payStatus='"+payStatus+"'";
		}else{
		  payStatus=" and a.payStatus='1' ";
		}
		String create_time="";
		if(!timesk.equals("") && !timesj.equals("")) {
			create_time=" and a.pay_time>='"+timesk.substring(0, 10)+" "+ timesk.substring(10)+"' and a.pay_time<='"+timesj.substring(0, 10)+" "+ timesj.substring(10)+"' ";
		}
		if(!timesk.equals("") && timesj.equals("")) {
			create_time=" and a.pay_time>='"+timesk.substring(0, 10)+" "+ timesk.substring(10)+"' ";
		}
		if(timesk.equals("") && !timesj.equals("")) {
			create_time=" and a.pay_time<='"+timesj.substring(0, 10)+" "+ timesj.substring(10)+"' ";
		}
		if(!EhNumber.equals("")){
			EhNumber=" and d.EhNumber ='"+EhNumber+"' ";
		}
		if(!OwnerName.equals("")){
			OwnerName=" and d.OwnerName like '%"+OwnerName+"%' ";
		}
		if(!itemName.equals("")){
			itemName=" and e.Ei_id = '"+itemName+"' ";
		}
		if(!payType.equals("")){
			payType=" and a.payType = '"+payType+"' ";
		}
		if(!payItem.equals("")){
			payItem=" and a.payItem = '"+payItem+"' ";
		}
		String LEVELS=session.getAttribute("LEVELS").toString();
		String tu_id = session.getAttribute("TU_ID").toString();
		
		if("1".equals(LEVELS)){
		   LEVELS="  and (c.BuHead='' or c.BuHead is null or c.BuHead='"+tu_id+"') ";
		}else{
		   LEVELS="";
		}
		String Condition=" 1=1 and a.status='1' and b.status='1' and c.status='1' and d.status='1' and e.status='1' and a.orderType='2' "+ts_id+Es_id+Bu_id+Un_id+create_time+EhNumber+OwnerName+itemName+payType+payItem+LEVELS+payStatus+sfy;
		session.setAttribute("Condition", Condition);
		
		String Innerj = " a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id ";
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
	pages.setPagesize(10);
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
	List<TB_Estate_Order> list = new MyTB_Estate_Order().select_orders(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
	wulifilepath=wulifilepath.replace('\\','/');
	String filename="订单自助缴费明细表.csv";
%>
  <body>
  	<table>
	  	<tr height="45">
		  	<td height="40" width="100%" >
				<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/createReport_zzjf.jsp','dcddbb');"/>
				<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
			</td>
		</tr>
	</table>
  	<table align="center"  class="tableList" width="100%">
  		<thead class="thead" height="45">	
		  	<td align="center" >小区名称</td>
		  	<td align="center">楼宇名称</td>
		  	<td align="center">单元名称</td>
		  	<td align="center">业主姓名</td>
		  	<td align="center">房屋编号</td>
		  	<td align="center">缴费时间</td>
		  	<td align="center">缴费金额</td>
		  	<td align="center">缴费方式</td>
		  	<td align="center">缴费项</td>
		  	<td align="center" colspan="2">操作</td>
	  	</thead>
	  	<tbody class="tableTbody">
		<% if(list.size()>0){
	  		TB_Estate_Order order = null;
	  		double z_total=0;
	  		for(int i=0;i<list.size();i++){
	  			order=(TB_Estate_Order)list.get(i);
	  				/* SHL修改 start */
	  			Map<String,Object> map = new TB_Estate_paperDao().selectPidandoaoerNum(order.getBankid());
	  			/* SHL修改end */
	  			z_total+=Double.parseDouble(S_string.DecimalFormat_string(order.getTotal_sj(),2));
	  	%>
	  	<tr height="45">
	  	<input type="hidden" id="Eo_id<%=i %>" name="Eo_id" value="<%=order.getEo_id() %>"/>
	  	<input type="hidden" id="Bu_id<%=i %>" name="Bu_id" value="<%=order.getBu_id() %>"/>
	  	
	  	<input type="hidden" id="total<%=i %>" name="total" value="<%=order.getTotal() %>"/>
	  	<input type="hidden" id="total_znj<%=i %>" name="total_znj" value="<%=order.getTotal_znj() %>"/>
	  	<input type="hidden" id="total_sj<%=i %>" name="total_sj" value="<%=order.getTotal_sj() %>"/>
	  	<input type="hidden" id="pay_status<%=i %>" name="pay_status" value="<%=order.getPayStatus() %>"/>
	  		
	  	<td align="center"><a onclick="new_show_see_tanchu_div2('select_orders_xq','','','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_xq.jsp"%>','<%=i%>');"><%=order.getEsName() %></a></td>
	  	<td align="center"><%=order.getBuName() %></td>
	  	<td align="center"><%=order.getUnName() %></td>
	  	<td align="center"><%=order.getOwnerName() %></td>
	  	<td align="center"><%=order.getEhNumber() %></td>
	  	<td align="center"><%=order.getPay_time() %></td>
	  	<td align="center"><%=S_string.DecimalFormat_string(order.getTotal_sj(),2) %></td>
	  	<td align="center">
	  	 <% if(order.getPayType().equals("")){%><%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
	  	</td>
	  	<td align="center"><%=order.getItemName()%></td>
	  	<td align="center"></td>
  		<td align="center">
  		<!-- SHL修改 start  -->
  		 <% if(  Integer.valueOf(map.get("status").toString()) == 1 && Integer.valueOf(map.get("print_status").toString()) == 0){ %>
			  <button id="xjtk" name="xjtk" disabled="disabled" >普通凭证</button>
			    <button id="xjtk" name="xjtk" disabled="disabled" >电子凭证</button>
	  			<%}else{ %>
				  <input type="button" name="modify" value="普通凭证"onclick="edit_show_see_tanchu_div('div_edit_app_show','','','<%=request.getContextPath()
											+ "/YCWYPage/BackPage/print/HBprint_show.jsp"%>','<%=order.getBankid() %>');return false;" />
					&nbsp;
					<input type="button" name="modify" value="电子凭证"onclick="edit_show_see_tanchu_div('div_edit_app_show','','','<%=request.getContextPath()
											+ "/YCWYPage/BackPage/print/HBprint_show1.jsp"%>','<%=order.getBankid() %>');return false;" />
	  			<%} %>
	  			<!-- SHL修改end -->
  		<%-- <button id="xjtk" name="xjtk"  onclick="edit_show_see_tanchu_div('div_edit_app_show','','','<%=request.getContextPath()
											+ "/YCWYPage/BackPage/print/print_show.jsp"%>','<%=order.getEo_id() %>');return false;" />普通凭证</button>
	  	 <button id="xjtk" name="xjtk" onclick="edit_show_see_tanchu_div('div_edit_app_show','','','<%=request.getContextPath()
											+ "/YCWYPage/BackPage/print/print_show1.jsp"%>','<%=order.getEo_id() %>');return false;" />电子凭证</button> --%>
		
		<%
		 if(order.getTk_status().equals("1") || "2".equals(session.getAttribute("IsDrawBack").toString())){
		%>									
		<button id="xjtk" name="xjtk" disabled="disabled" />线下退款</button>
		<%}else{ %>
		<button id="xjtk1" name="xjtk1" onclick="update_orders_rgtk3('select_orders_xg','','','/YCWYManage/YCWYPage/BackPage/artificial/charge_select_edit3.jsp?type=2','<%=i %>','<%=tsid %>');return false;" />线下退款</button>
		
		<%} %>
		</td>
											
											
  		
	  	</tr>
	  	<%}
	  	%>
	  	<tr height="45">
	  	<td colspan="5"></td>
	  	<td align="center">合计：单位/元</td>
	  	<td align="center"><%=S_string.DecimalFormat_double(z_total,2) %></td>
	  	<td colspan="4"></td>
	  	</tr>
	  	<%
	  	}else{ %>
	  	<tr height="45"><td  align="center" colspan="11">未查询到订单信息！！！</td></tr>
		<%} %>
		</tbody>
  	</table>
<%
String url = request.getContextPath()+"/YCWYPage/BackPage/artificial/charge_self_see.jsp";
%>
<div class="pages">
	<jsp:include page="/YCWYPage/BackPage/charge/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="select_gue_orders"/>
	</jsp:include>
</div>
<!-- 等待刷新的div层 -->

<div id="select_orders_xq" ></div>
  <div id="select_orders_xg" ></div> 
<!-- 显示等待图片的读取层 -->
<div id="Loading_div_da" style="z-index:1000000;filter:Alpha(opacity=10);opacity:0.1;"></div>
<div id="Loading_div_xiao" style="margin-left:-50px;text-align:center;color:red;width:100px;height:100px;display:none;z-index:1000001;filter:Alpha(opacity=70);opacity:0.7;">
<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
</div>
<%} %>
 