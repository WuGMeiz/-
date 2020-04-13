<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
%>
<%
	String type = request.getParameter("type");
	String TSID = request.getParameter("TSID");
	String Eoid=request.getParameter("ORDERID");
	String ORDERID="";
	ORDERID=Eoid;
	
	String tu_id = session.getAttribute("TU_ID").toString();
	
	//订单号，从订单查询页面传递过来
	if(!"3".equals(type)){
	ORDERID = "BS"+request.getParameter("ORDERID")+"O"+new Date().getTime();
	}
	//订单金额（扣款金额），从订单查询页面传递过来
	String PAYMENT = request.getParameter("PAYMENT");
	String url = request.getContextPath()+"/YCWYPage/BackPage/swept/SweptAPay.jsp";	//本地调用被扫接口的页面地址
	if(!"3".equals(type)){//1 收费单管理下面的被扫      2批量导入下面的被扫      3人工自助下面的被扫，这个前面更新bankid了，因此此处不需要更新
		//new MyTB_Estate_Order().updateBSorder(Eoid, ORDERID , TSID);
		new MyTB_Estate_Order().updateBSorder(Eoid, ORDERID , TSID ,tu_id);
	}
%>
	<!--扫码结果，倒计时提示-->
	<div class="timerTip"></div>
	<!--展示当前订单进行状态-->
	<p class="orderState">收款金额：<%=PAYMENT %>元，请客户出示付款码</p>
	<p class="tip">等待扫码...请勿触碰键盘，点击下方按钮可取消支付</p>
	<div>
	
	<%
	if("1".equals(type)){
	%>
	<input type="text" class="none" id="QRCODE" onchange="waitForRst(this,'<%=ORDERID %>','<%=PAYMENT %>','<%=url%>','<%=TSID%>','select_orders','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_see.jsp?type=1"%>');"/>

	<%	
	}else if("2".equals(type)){
	%>
	<input type="text" class="none" id="QRCODE" onchange="waitForRst(this,'<%=ORDERID %>','<%=PAYMENT %>','<%=url%>','<%=TSID%>','select_gue_orders','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/gue_cx_see.jsp?type=1"%>');"/>
	
	<%	
	}else if("3".equals(type)){
	%>
	<input type="text" class="none" id="QRCODE" onchange="waitForRst(this,'<%=ORDERID %>','<%=PAYMENT %>','<%=url%>','<%=TSID%>','select_gue_orders','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/charge_manual.jsp"%>');"/>
		
	<%	
	}
	%>
	   
 
 	</div>
 
		<input type="button" value="取消支付" class="centerBtn close" onclick="realClose();" />
	<%
}
%>