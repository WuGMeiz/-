<%@page import="WYCommunity.T_time"%>
<%@page import="WYCommunity.Base64Utils"%>
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
	String tsid = session.getAttribute("U_ID").toString();
	String ts_id=tsid;
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
		String Un_id=S_string.formatString(request.getParameter("Un_id"));
		String EhNumber=S_string.formatString(request.getParameter("EhNumber"));
		
		String timesk=S_string.formatString(request.getParameter("timesk"));
		String timesj=S_string.formatString(request.getParameter("timesj"));
		
		String payStatus=S_string.formatString(request.getParameter("payStatus"));	
		
		String itemName=S_string.formatString(request.getParameter("itemName"));
		String orderType=S_string.formatString(request.getParameter("orderType"));
		String payType=S_string.formatString(request.getParameter("payType"));
		String OwnerName=S_string.formatString(request.getParameter("OwnerName"));
	
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
		if(!EhNumber.equals("")){
			EhNumber=" and d.EhNumber = '"+EhNumber+"' ";
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
			//if(payType.equals("0")){
				payType=" and a.payType='"+payType+"' ";
			/* }else{
				payType=" and a.payType>='"+payType+"' ";
			} */
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
		String Condition=" 1=1 and a.status='1' and b.status='1'and c.status='1' and d.status='1' and e.status='1' and a.orderType='1' "+ts_id+Bu_id+Un_id+Es_id+EhNumber+create_time+payStatus+itemName+orderType+payType+OwnerName+LEVELS;
		session.setAttribute("Condition", Condition);
		//System.out.println(Condition);
		
		String Innerj = " a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id ";
		session.setAttribute("Innerj", Innerj);
	}
	
%>

<%
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
	String filename="收费单查询明细表.csv";
%>
<div id="tablelist">
  <body>
  	<table>
	  	<tr height="45">
		  	<td height="40" width="100%" >
				<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/createReport.jsp','dcddbb');"/>
				<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
			</td>
		</tr>
	</table>
<div style="width: 100%; overflow: hidden; overflow-x: auto;" >
  	<table align="center" class="tableList" width="120%" >
  		<thead class="thead">
	  	<tr height="45">
		  	<td align="center" colspan="3">所在小区</td>
		  	<td align="center">业主姓名</td>
		  	<td align="center">缴费项</td>
		  	<td align="center">费用起止日期</td>
		  	<td align="center">应缴金额</td>
		  	<td align="center">滞纳金金额</td>
		  	<td align="center">实缴金额</td>
		  	<td align="center">缴费方式</td>
		  	<td align="center">支付状态</td>
	  	</tr >
	  	</thead>
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
	  	<input  type="hidden" id="Eo_id<%=i %>" name="Eo_id" value="<%=order.getEo_id() %>"/>
	  	<input type="hidden" id="Bu_id<%=i %>" name="Bu_id" value="<%=order.getBu_id() %>"/>
	  	<td align="center" colspan="3">
	  	<%=order.getEsName() %>--<%=order.getBuName() %><%if(!"".equals(order.getUnName())){%>--<%=order.getUnName() %><%} %>--<%=order.getEhNumber() %></td>
	    <td align="center"><%=order.getOwnerName() %></td>
	  	<td align="center"><%=order.getItemName() %></td>
	  	<td align="center"><%if(order.getCost_startTime().equals("")){%><%}else{%><%=order.getCost_startTime().substring(0, 10) %>至<%=order.getCost_endTime().substring(0, 10) %><%} %></td>
		<td align="center"><%=S_string.DecimalFormat_string(order.getTotal(),2) %> </td>
		<td align="center">
		
		<%	if(order.getPayStatus()==0){
				String yj_total = order.getTotal();//应缴金额
			String znjDay = order.getZnjDay();//滞纳规定天数
			String znjRatio = order.getZnjRatio();//滞纳比例
			 
			double znj_total = 0;//滞纳金
			double znj_gongshi = 0;//滞纳金计算公式
			if(order.getScost_endTime().equals("1900-01-01 00:00:00.0") || order.getScost_endTime().equals("") || order.getScost_endTime()==null){
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
			total_znj_all+=znj_total;
			%>
				<input type="hidden" id="total_znj<%=i %>" name="total_znj" value="<%=znj_total%>"/>
				<%=znj_total %>
			<%
		  	}else{
		  		total_znj_all=total_znj_all+Double.parseDouble(order.getTotal_znj());
		  	%>
				<%=S_string.DecimalFormat_string(order.getTotal_znj(),2) %>
				<input type="hidden" id="total_znj<%=i %>" name="total_znj" value="<%=S_string.DecimalFormat_string(order.getTotal_znj(),2) %>"/>
		<%	} %>
		</td>
		<input type="hidden" id="total<%=i %>" name="total" value="<%=S_string.DecimalFormat_string(order.getTotal(),2) %>"/>
		
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
			<td align="center"><%=S_string.DecimalFormat_double(total_znj_all,2)%></td>
			<td align="center"><%=S_string.DecimalFormat_double(total_sj_all,2)%></td>
			<td colspan="2"></td>
	  	</tr>
	  	<%
	  	}else{ %>
	  	<tr height="45"><td  align="center" colspan="11">未查询到订单信息！！！</td></tr>
		<%} %>
		</tbody>
  	</table>
  </div>
<%
String url = request.getContextPath()+"/YCWYPage/BackPage/baobiao/cj_baobiao_see.jsp";
%>
<div class="pages">
	<jsp:include page="/YCWYPage/BackPage/charge/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="tablelist"/>
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
</div>
<%} %>
 