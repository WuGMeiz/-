<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@ page language="java" pageEncoding="UTF-8"%>
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
	 
	
	 String Total = request.getParameter("total");
	 String ts_id = request.getParameter("ts_id");
	 String bankid = request.getParameter("bankid"); 
	 /*** 被扫支付从客户端页面需要传值 ***/
	 String url = request.getContextPath()+"/YCWYPage/BackPage/swept/SweptAway.jsp";	//	去支付前处理报文签名的页面地址
	 
%>
<!-- <script type="text/javascript">
$("#btnbtn").click();
</script> -->
   
<input type="hidden" id="total_sj" name="total_sj" value="<%=Total %>" />
<input type="hidden" id="Eo_id" name="Eo_id" value="<%=bankid %>" />

 <input type="button" id="btnbtn"  class="none" value="扫码支付"  
 onclick="showThis('3','showswept','<%=url %>','<%=ts_id %>','<%=Total %>')" /> 			 			
 
<%
 }
%>
