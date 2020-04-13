<%@page import="WYBack_Stage.Dao.TB_Estate_ExoptionDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Exoption"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//防止乱码
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");//这样设置可以让弹出框在IE和火狐下显示正常
	response.setCharacterEncoding("UTF-8");
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%
	if (session.getAttribute("USER_ID") == null) {
		String url = request.getContextPath() + "/ManagePage/login.jsp";
		out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
		out.println("window.location='" + url + "';");
		out.println("</script>");
	} else {
		TB_Estate_Exoption exoption = new TB_Estate_ExoptionDao()
				.select_Exoption_Designation(Integer.parseInt(request
						.getParameter("op_id")));
%>
<head>
<%-- <link rel=
"stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ManagePage/css/public.css"
	id="screenSelect" /> --%>
	<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/style.css" /> --%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/YCWYPage/BackPage/css/content.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/YCWYPage/BackPage/js/index.js"></script>

</head>
<body>
<div class="new_content">
   <br>
	<!--选项卡容器-->
	<form id="form2" name="form2" method="post"
	enctype="multipart/form-data" accept-charset="UTF-8"
	action="<%=request.getContextPath()%>/TB_Estate_ExoptionUpdateServlet">
	<table class="showTdiv">
		<tr height="45">
   			<td align="right">选项类型:</td>
   			<td>
   				<select id="if_tw" name="if_tw" class="input_txt hidd_fist"
					style="width: 100px;" onchange="divDisplay(this.value);">
						<%
							switch (exoption.getIf_tw()) {
								case 0:
						%>
							<option value="">--请选择选项类型--</option>
							<option value="1">图文</option>
							<option value="0" selected>图片</option>
							<option value="2">文字</option>							
						<%
							break;
								case 1:
						%>
							<option value="">--请选择选项类型--</option>
							<option value="1" selected>图文</option>
							<option value="0">图片</option>
							<option value="2">文字</option>
						<%
							break;
								case 2:
						%>
							<option value="">--请选择选项类型--</option>
							<option value="1">图文</option>
							<option value="0">图片</option>
							<option value="2" selected>文字</option>
						<%
							break;
								}
						%>
					
				</select>
   			</td>
   		</tr>
   		<tr height="45">
   			<td align="right">选项名称:</td>
   			<td>
   				<input type="text" id="optionName" name="optionName" class="input_txt" value="<%=exoption.getOptionName()%>" width="100px" maxlength="20" />
   			</td>
   		</tr>
   		<tr height="45">
   			<td align="right">上传图片:</td>
   			<td>
   				<input type="file" id="InfImage" name="InfImage" class="hidden" accept="image/*" onchange="previewImg(this);" /> 
				<label for="InfImage" class="imgScale"></label>
   			</td>
   		</tr>
   		<tr height="45">
	   		<td colspan="2" align="center">
	   			<input type="hidden" id="op_id" name="op_id" value="<%=exoption.getOp_id()%>">
				<input type="hidden" id="arg" name="arg" value="1">
				<input type="hidden" id="return_divid" name="return_divid" value="show_update" />
		        <input type="hidden" id="close_divid" name="close_divid" value="div_user_show" />
		        <input type="hidden" id="return_url" name="return_url" value="<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_update.jsp?type=1" %>"/>
				<input type="submit" class="submit_input" value="提交"
				onclick="return Update_Exoption();">
			</td>
   		</tr>
	</table>

	<div class="payArea">
		
	</div>
</form>
</div>
	<!-- 显示等待图片的读取层 -->
<div id="Loading_div_da"
	style="z-index:1000000;filter:Alpha(opacity=10);opacity:0.1;"></div>
<div id="Loading_div_xiao"
	style="margin-left:-50px; text-align:center; color:red;width:100px;height:100px;display:none;z-index:1000001;  filter:Alpha(opacity=70);opacity:0.7;">
	<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
</div>	
</body>
<%
}
%>