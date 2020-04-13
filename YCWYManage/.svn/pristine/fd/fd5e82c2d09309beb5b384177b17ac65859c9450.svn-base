<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.Bchaxun"%>
<%@page import="WYCommunity.T_time"%>
<%@page import="WYBack_Stage.Dao.TstionDao"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.*"%>
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
%>
<div class="addBoxList">
	<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">
		<thead class="thead">
			<tr height="38">
		<th width="10%">
			对账日期
		</th>
		<th width="9%">
			系统支付笔数
		</th>
		<th width="9%">
			建行支付笔数
		</th>
		<th width="9%">
			系统支付金额
		</th>
		<th width="9%">
			建行支付金额
		</th>		
		<!-- <th width="9%">
			系统退款笔数
		</th>
		<th width="9%">
			建行退款笔数
		</th>
		<th width="9%">
			系统退款金额
		</th>	
		<th width="9%">
			建行退款金额
		</th> -->	
		<th width="9%">
			现金缴费笔数
		</th>	
		<th width="9%">
			现金缴费金额
		</th>			
	</tr>
		</thead>
		<tbody class="tableTbody">
	<% 
	String timesk=S_string.formatString( request.getParameter("timesk") );
	String timesj=S_string.formatString( request.getParameter("timesj") );
	String ts_id=session.getAttribute("U_ID").toString();
	
	/****************************************************************************************************/
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
	String gtdm=new TstionDao().getgtdmall(ts_id);
	/****************************************************************************************************/
	
	List<String> list = new T_time().getdatelist(timesk,timesj);
	
	for(int i=0;i<list.size();i++)
	{
		String temptimes=(String)list.get(i);
		
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("temptimes", temptimes);
		map.put("ts_id", ts_id);
		map.put("shdm", shdm);
		map.put("gtdm", gtdm);
		TB_Estate_Order tborder = new TstionDao().getcondition1(map);
		TB_Estate_Order tborder1 = new TstionDao().getcondition2(map);
		
		int   sfbs= Integer.parseInt(tborder.getSjbishu())   ;	//系统支付笔数
		float sfje=Float.parseFloat(tborder.getSjje());		//系统支付金额
		int   tkbs= Integer.parseInt(tborder1.getTkbishu());	//系统退款笔数
		float tkje=Float.parseFloat(tborder1.getTkje());		//系统退款金额   
		
	
		Bchaxun bch = new TstionDao().getcondition(map);
		
		int   jsfbs=Integer.parseInt(bch.getSjbishu());	//建行支付笔数
		float jsfje=Float.parseFloat(bch.getSjje());	//建行支付金额
		int   jtkbs=Integer.parseInt(bch.getTkbishu());	//建行退款笔数
		float jtkje=Float.parseFloat(bch.getTkje());	//建行退款金额
		
		int   xjsfbs=Integer.parseInt(tborder.getXsjbishu());	//现金缴费笔数
		float xjsfje=Float.parseFloat(tborder.getXsjje());		//现金缴费金额
	%>
	<tr height="38">
		<td align="center"><%=temptimes %></td>
		<td align="center"><%=sfbs %></td>
		<td align="center"><%=jsfbs %></td>
		<td align="center"><%=sfje %></td>
		<td align="center"><%=jsfje %></td>
		<%-- <td align="center"><%=tkbs %></td>
		<td align="center"><%=jtkbs %></td>
		<td align="center"><%=tkje %></td>
		<td align="center"><%=jtkje %></td> --%>
		<td align="center"><%=xjsfbs %></td>
		<td align="center"><%=xjsfje %></td>
	</tr>
	<% 
	}
	%>
</tbody>
	</table>
	</div>	
<%
}
%>