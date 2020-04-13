<%@page import="WYBack_Stage.Bean.Bchaxun"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYBack_Stage.Dao.TstionDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
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


	if(request.getParameter("type")==null)
	{	
		session.setAttribute("Condition",null);	//清除其它具有分页功能的页面可能保存的session值
		
		String TYPE=request.getParameter("TYPE");
		String timesk=request.getParameter("timesk");
		String timesj=request.getParameter("timesj");
		
		String ts_id=session.getAttribute("U_ID").toString();
		String shdm=new TstionDao().getshdm(ts_id);
		/**2015年3月5日修改银行后台同一个商户编号可以添加成多个商户增加功能
		*（如商户编号105130173720007，有多个机构按多商户添加时可以带后缀区分，如：105130173720007-1，105130173720007-2的形式命名商户编号）
		* 所以在使用银行编号时，需要去掉shdm中包含的“-1”后缀
		*/
		int hzxb=shdm.indexOf("-");//得到“-”所在字符串中的下标值，如果不存在返回-1
		if(hzxb>0)//存在“-”号时进行字符串截取
		{
			shdm=shdm.substring(0, hzxb);
		}
		/**************************************************/
		String gtdm=new TstionDao().getgtdmall(ts_id);
		String Condition=" TYPE='"+TYPE+"' and ORDERDATE>='"+timesk+" 00:00:00' and ORDERDATE<='"+timesj+" 23:59:59' and MERCHANTID='"+shdm+"' and POSID in("+gtdm+") ";

		session.setAttribute("Condition",Condition);	

	}		
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Bank_water");
	
	//分页条件
	if(session.getAttribute("Condition")!=null)
	{
		pages.setCondition(session.getAttribute("Condition").toString());
	}	
	
	//每页显示多少条
	pages.setPagesize(10);
	//设置要显示哪页
	if(request.getParameter("pagenum")!=null)
	{
		pages.setPagenum(Integer.parseInt(request.getParameter("pagenum").toString()));
	}
	
	List list=new TstionDao().getBchaxunPage(pages.getPagesize(),pages.getPagenum(),pages.getCondition());

%>	
	<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">
		<thead class="thead">
			<tr height="38">
		<th width="15%">
			交易日期
		</th>
		<th width="15%">
			记账日期
		</th>
		<th width="15%">
			订单号
		</th>			
		<th width="15%">
			支付金额
		</th>
		<th width="14%">
			退款金额
		</th>	
		<th width="14%">
			柜台号
		</th>
		<th width="12%">
			订单状态
		</th>	
	</tr>
	</thead>
		<tbody class="tableTbody">
	<% 
	if(list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
			Bchaxun bc=(Bchaxun)list.get(i);
	%>
	<tr height="38">
		<td align="center"><%=bc.getORDERDATE() %></td>
		<td align="center"><%=bc.getACCDATE() %></td>
		<td align="center"><%=bc.getORDERID() %></td>
		<td align="center"><%=bc.getAMOUNT() %></td>
		<td align="center"><%=bc.getREFUND() %></td>
		<td align="center"><%=bc.getPOSID() %></td>
		<td align="center"><%=bc.getSTATUS() %></td>
	</tr>
	<% 
		}
	%>
<%
	}
	else
	{
%>
	<tr height="38">
		<td colspan="7" align="center">
			没有查询到数据	
		</td>
	</tr>	
<% 
	}
%>	
</table>	
<!--分页-->
	<div class="pages">
		<ul>
			<li>共&nbsp;<%=pages.getPagecount()%>&nbsp;页&nbsp;/&nbsp;<%=pages.getCountnum()%>&nbsp;条&nbsp;
				<%
					if (pages.getPagenum() == 1) {
				%>

				<li>上一页</li>

<%
	} else {
%>	
		<li onclick="javascript:Back('<%=request.getContextPath()%>/YCWYPage/BackPage/tstion/liushui_show.jsp','<%=pages.getPagenum() - 1%>','show_update')">上一页</li>
    
<%
    	}
    %>    
  <li>  [<%=pages.getPagenum()%>] </li>
<%
	if (pages.getPagenum() == pages.getPagecount() | pages.getPagecount() == 0) {
%>	
		<li>下一页</li>
<%
	} else {
%>     
  	<li onclick="javascript:Next('<%=request.getContextPath()%>/YCWYPage/BackPage/tstion/liushui_show.jsp','<%=pages.getPagenum() + 1%>','show_update')">下一页</li>
  	
<%
  		}
  	%>
     <li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=request.getContextPath()%>/YCWYPage/BackPage/tstion/liushui_show.jsp',this,'show_update')">
	<option value="0">&nbsp;</option>
	<%
		for (int i = 1; i <= pages.getPagecount(); i++) {
	%>
	<option value="<%=i%>" <%if (i == pages.getPagenum()) {%> selected="selected" <%}%>><%=i%></option>
	<%
		}
	%>
	</select>    
   	</li>
			</ul>
		</div>
		<!--/分页-->
<div class="clear"></div>
<%
}
%>