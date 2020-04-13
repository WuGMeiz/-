<%@page import="ccbjf.system.util.TimesUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>客户端 发起被扫支付页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 导入被扫支付的样式及脚本开始 -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/swept/css/style.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/swept/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/swept/js/script.js"></script>
	<!-- 导入被扫支付的样式及脚本结束 -->
	
</head>
<body>

<% 
	/*** 被扫支付从客户端页面需要传值 ***/
	String url = request.getContextPath()+"/swept/SweptAway.jsp";	//	去支付前处理报文签名的页面地址
	String ORDERID = "cs"+new Date().getTime();	//订单号
	String PAYMENT = "0.01";	//订单金额
%>
<center>
	<table>
		<tr>
			<td align="right">
				应收金额：
			</td>
			<td align="left">
				<input type="text" id="PAYMENT" name="PAYMENT" value="0.01"  readonly="readonly">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- 客户端发起调用被扫支付时，调用showThis(弹出层ID,请求处理地址,订单号,订单金额)的js方法 -->
				<input type="button" value="点击扫描收款" onclick="showThis('showswept','<%=url %>','<%=ORDERID %>','<%=PAYMENT %>');"/>
				
			</td>
		</tr>
	</table>
</center>

<!--调用被扫支付的层（必须加在客户端页面最下方）开始-->
<div class="cover">
    <div class="coverContent" id="showswept">
      
    </div>
</div>
<!--调用被扫支付的层（必须加在客户端页面最下方）结束-->

</body>
</html>
