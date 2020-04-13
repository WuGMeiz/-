<%@page import="WYBack_Stage.Dao.MyTB_SEV_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_SEV"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
response.addHeader("x-frame-options","SAMEORIGIN");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- 导入被扫支付的样式及脚本开始 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/YCWYPage/BackPage/swept/css/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/YCWYPage/BackPage/swept/js/jquery-1.8.2.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/YCWYPage/BackPage/swept/js/script.js"></script>
<!-- 导入被扫支付的样式及脚本结束 -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/YCWYPage/BackPage/css/welcome.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/content.css" id="cssfile"/>
<!-- 日历控件 start 必须按Date.css、Isie.js、Date.js顺序引入放可使用,并且  Date.js必须要放在页面<body>里才可以-->
<link href="<%=request.getContextPath() %>/datejsorcss/Date.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/datejsorcss/Isie.js"></script>
<link id="alertMsg" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/alertMsg.css" type="text/css" rel="stylesheet" />
<link id="welcss" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/welcss.css" />
<link id="jhshgl" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/jhshgl.css" />
<%--  <script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/jquery-1.9.1.min.js"></script> --%>
<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/js.js"></script>
<%-- <script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/jquery-3.1.1.min.js"></script> --%>
<script src="<%=request.getContextPath() %>/My97DatePicker/4.8/WdatePicker.js"></script>
<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/index.js"></script>
<title>E桥通综合服务平台</title>
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon"/>
<link rel="icon" href="images/logo.ico" type="image/x-icon"/>

 

</head>
<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
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
    String org_id = session.getAttribute("ORG_ID").toString();
    String url1=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
%>
<%
TB_SEV tbsev = new MyTB_SEV_DAO().getTB_SEV1(session.getAttribute("U_ID").toString());
String sname = tbsev.getTs_company_name();
String logo=tbsev.getTs_yyzz();
String A_ID = tbsev.getA_id();
session.setAttribute("A_ID",A_ID);
%>

<body onload="tick();">
<!-- 日历控件 start 必须按Date.css、Isie.js、Date.js顺序引入放可使用,并且--Date.js必须要放在页面<body>里才可以--> 
<script type="text/javascript" src="<%=request.getContextPath() %>/datejsorcss/Date.js"></script>
<!-- 日历控件 start -->
<!-- <table border="0" width="100%">
	<tr> -->
		<td style="width: 238px;">
<!--左边-->
<div class="aside">
	<div class="logo"><img src="images/sx_01.png"/></div>
	<div class="list_clasify">
		<jsp:include flush="true" page="/YCWYPage/BackPage/menu.jsp"></jsp:include>
   	</div>
	
    <!--基本信息-->
    <div class="jbxxMine">
    	<div class="jbxxMineNav">
        	<ul>
            	<!-- <li><span>修改密码</span></li> -->
                <li onclick="tuichu_xitong('<%=url1 %>');"><span>退出系统</span></li>
            </ul>
        </div>
    	<span class="jbxxMineTitle"><%=session.getAttribute("USER_NAME").toString() %></span>
    </div>
    <!--/基本信息-->
</div>
<!--/左边-->
<!-- </td>
<td > -->
<!--右边-->
<div class="section">
	
	<div class="rightTop">
			 <% 
	if(!logo.equals(""))
	{
		String imgurl = request.getContextPath()+logo;
	%>
			<div class="urposition">&nbsp;
			<img class="list_logo_img1"  src="<%=imgurl %>"/>
			<h2><%=sname %></h2>
			</div>
	<%	
		}else{%> 
		<div class="urposition">&nbsp;
			<%-- <img class="list_logo_img1"  src="<%=request.getContextPath() %>/Managepage/zxschoolSev/images/logo.png"/> --%>
			<h2><%=sname %></h2>
		</div>
		 <%} %> 
		<div class="time"><span id="localtime" style="font-size: 12px;float: right;"></span></div>
	</div>
	<!--内容-->
	<div class="content" id="showmain">
		<table border="0" width="100%">
			<tr>
				<td align="center">
					<img src="images/sx_13.png" class="welcome"/>
				</td>
			</tr>
			<tr>
				<td align="center" style="font-size: 26px;">
					欢迎您回来：<%=session.getAttribute("USER_NAME").toString() %>
				</td>
			</tr>
		</table>
	</div>
	<!--/内容-->
</div>
<!--/右边-->
<!-- </td> -->
<div class="clear"></div>
<!-- </tr>
</table> -->
<!-- 显示等待图片的读取层 -->
	<div id="Loading_div_da"
		style="z-index:1000000;filter:Alpha(opacity=10);opacity:0.1;"></div>
	<div id="Loading_div_xiao"
		style="margin-left:-50px; text-align:center; color:red;width:100px;height:100px;display:none;z-index:1000001;  filter:Alpha(opacity=70);opacity:0.7;">
		<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
	</div>
<!--调用被扫支付的层（必须加在客户端页面最下方）开始-->
<div class="cover">
    <div class="coverContent" id="showswept">
    </div>
</div>
<!--调用被扫支付的层（必须加在客户端页面最下方）结束-->

</body>

</html>
<%}%>
