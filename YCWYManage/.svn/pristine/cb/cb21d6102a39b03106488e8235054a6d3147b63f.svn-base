<!DOCTYPE html>
<%@page import="WYCommunity.MyCookie"%>
<%
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link type="text/css" rel="stylesheet" href="css/login.css" />
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/YCWYPage/BackPage/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/YCWYPage/BackPage/js/index.js"></script>
<title>E桥通综合服务平台</title>
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon"/>
<link rel="icon" href="images/logo.ico" type="image/x-icon"/>
</head>

<body>
<!--头部-->
<div class="header">
    <div class="headerNav">
        <img src="images/bank_cc.png"/>
    </div>
 </div>
 <!--/头部-->
 <!--登录框-->
<div class="container">
     <%
			session.setAttribute("USER_ID",null);//用户登录账号
			session.setAttribute("TU_ID",null);//用户登录账号
			session.setAttribute("LOGIN_IP",null);//用户上次登录IP
			session.setAttribute("LOGIN_TIME",null);
			String CookiesName="TB_SEV_USER";
			String user_id=MyCookie.getCookies(request, CookiesName);//从Cookie中取得保存的账户
		%>
     <form action="<%=request.getContextPath()%>/WuYeLoginServlet">
     	<div class="title"><h2>管理员登录</h2></div>
    	<div class="loginFrmP"><input type="text" id="user_id" name="user_id" value="<%=user_id%>"/></div>
        <div class="loginFrmP3"><input type="password" id="password" name="password" value=""/></div>
        <div class="loginFrmP2">
        	<input type="text" id="code" name="code"/>
        	<span class="loginFrmyzm"><img id="yzm" name="yzm" src="<%=request.getContextPath() %>/YCWYPage/BackPage/yzm.jsp"/></span>
        	<a href="#" style="font-size:14px; line-height:32px;" onclick="refresh_yzm('yzm','<%=request.getContextPath() %>/YCWYPage/BackPage/yzm.jsp');return false;">&nbsp;换一张</a>
        </div>
        
          <p  style="clear:left;padding-top:24px;+padding-top:12px;_padding-top:12px;">
				<span><input type="checkbox" name="jzyhm" id="jzyhm" <%if(!user_id.equals("")){%>checked="checked"<%}%>/>&nbsp;&nbsp;记住用户名</span>
		  </p>
       
        <div class="loginFrmbtn clear">
           <input type="submit" value="登 录" onclick="return wuye_login();" />
        </div>
    </form>
</div>
<div class="clear"></div>
 <!--/登录框-->
<div class="footer">
 <small>©本平台由石家庄市科威计算机工程有限公司提供&nbsp;&nbsp;备案号： 冀ICP备12020723号-2&nbsp;&nbsp;
     客服热线电话：400-0062-111&nbsp;&nbsp;技术支持电话：0351-4090566</small>
</div>
<script type="text/javascript">
//首页主体部分高度
$(document).ready(function(){
    var windowheight= $(window).height();
    var footheight=$(".footer").height();
    $(".container").height(windowheight-footheight-343+"px");
});</script>
</body>
</html>
