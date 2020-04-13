<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order_bb_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
	if(request.getParameter("type")==null) {
		String ts_id=S_string.formatString(request.getParameter("ts_id"));
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
		String itemName=S_string.formatString(request.getParameter("itemName"));
		String timesk=S_string.formatString(request.getParameter("timesk"));
		String timesj=S_string.formatString(request.getParameter("timesj"));
		String payType=S_string.formatString(request.getParameter("payType"));
		ts_id=" and a.ts_id='"+ts_id+"' ";
		if(!Es_id.equals("")){
			Es_id=" and a.Es_id='"+Es_id+"' ";
		}
		if(!Bu_id.equals("")){
			Bu_id=" and a.Bu_id='"+Bu_id+"' ";
		}
		if(!itemName.equals("")){
			itemName=" and b.Ei_id='"+itemName+"' ";
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
		String LEVELS=session.getAttribute("LEVELS").toString();
		String tu_id = session.getAttribute("TU_ID").toString();
		
		if("1".equals(LEVELS)){
		   LEVELS="  and (e.BuHead='' or e.BuHead is null or e.BuHead='"+tu_id+"') ";
		}else{
		   LEVELS="";
		}
		if(!"".equals(payType)){
		 payType=" and a.payType='"+payType+"'";
		}
		String Condition=" 1=1 and a.status='1' and b.status='1' and c.status='1' and d.status='1' and e.status='1' and a.payStatus='1' "+ts_id+Es_id+create_time+itemName+Bu_id+LEVELS+payType;
		session.setAttribute("Condition", Condition);
		
		String Innerj = "a inner join TB_Estate_item b on a.payItem=b.Ei_id inner join TB_Estate_Village c on a.Es_id=c.Es_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_Build e on a.Bu_id=e.Bu_id ";
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
	pages.setPagesize(100);
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
	
	List<TB_Estate_Order> list = new MyTB_Estate_Order_bb_DAO().select_orders_baobiaoxinxi(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
%>
<%
		String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
		wulifilepath=wulifilepath.replace('\\','/');
		String filename="交易管理统计数据表.csv";
%>
<div id="tablelist">
  <body>
  	<table>
	  	<tr height="45">
		  	<td height="40" width="100%" >
				<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/baobiao/createReport.jsp','dcddbb');"/>
				<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
			</td>
		</tr>
	</table>
  	<table align="center" class="tableList" width="100%" >
  		<thead class="thead">
	  	<tr height="45">
		  	<td align="center">小区名称</td>
		  	<td align="center">收费项</td>
		  	<td align="center">交易笔数</td>
		  	<td align="center">交易金额</td>
	  	</tr >
	  	</thead>
	  	<tbody class="tableTbody">
	  	
		<%
		 if(list.size()>0){
		 	int zfbs=0;
		 	double total_sj_hj=0;
	  		TB_Estate_Order order = null;
	  		for(int i=0;i<list.size();i++){
	  			order=(TB_Estate_Order)list.get(i);
	  			zfbs+=Integer.parseInt(order.getNum());
	  			total_sj_hj+=Double.parseDouble(order.getTotal_sj_all());
	  	%>
	  	<tr height="45">
	  		<td align="center"><%=order.getEsName() %></td>
	  		<td align="center"><%=order.getItemName() %></td>
	  		<td align="center"><%=order.getNum() %></td>
	  		<td align="center"><%=S_string.DecimalFormat_string(order.getTotal_sj_all(),2) %></td>
	  	</tr>	
	  	<%	}
	  	%>
	  	<tr height="45">
	  		<td align="center" ></td>
	  		<td align="center" >合计：单位/元</td>
	  		<td align="center" ><%=zfbs %></td>
	  		<td align="center"><%=S_string.DecimalFormat_double(total_sj_hj,2) %></td>
	  	</tr>	
	  	<%
	  	}else{ %>
	  	<tr><td height="45" align="center" colspan="5">未查询到订单信息！！！</td></tr>
		<%} %>
		</tbody>
  	</table>
<%
String url = request.getContextPath()+"/YCWYPage/BackPage/tstion/baobiao_show.jsp";
%>
<!-- 
<div class="pages">
	<jsp:include page="/YCWYPage/BackPage/charge/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="tablelist"/>
	</jsp:include>
</div>
 -->
<!-- 等待刷新的div层 -->
<div id="select_orders_xq" ></div>
  <div id="select_orders_xg" ></div> 
<!-- 显示等待图片的读取层 -->
<div id="Loading_div_da" style="z-index:1000000;filter:Alpha(opacity=10);opacity:0.1;"></div>
<div id="Loading_div_xiao" style="margin-left:-50px;text-align:center;color:red;width:100px;height:100px;display:none;z-index:1000001;filter:Alpha(opacity=70);opacity:0.7;">
<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
</div>
</div>





<%
}
%>