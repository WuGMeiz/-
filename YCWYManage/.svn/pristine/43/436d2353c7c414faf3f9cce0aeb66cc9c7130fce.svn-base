<%@page import="WYBack_Stage.Dao.Mete_ReadClass"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>综合服务系统</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/YCWYPage/BackPage/css/content.css"/>
	<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/index.js" type="text/javascript"></script>

  </head>
  
  <body>
  <%
  if (session.getAttribute("U_ID") == null) {
		String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
		out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	    out.println("window.location='"+url+"';");
	    out.println("</script>");
	} else {
		 String  ts_id = session.getAttribute("U_ID").toString(); 
		 String  YZNR = session.getAttribute("YZNR").toString(); 
		 List<TB_Estate_Village> list = null;
	  		if(YZNR!=null && !YZNR.equals("")){
	  			list = new Mete_ReadClass().findxiaoqu(ts_id,YZNR);
	  		}else{
	  			list = new Mete_ReadClass().findxiaoquALL(ts_id);
	  		}
  %>
	    <table width="100%">
	    	<tr height="45">
	    			<td align="right">小区名称:</td>
	    			<td>
	   				<div id="xiaoqu">
		   				<select id="xiaoquid" class="input_txt" name="xiaoquid"  onchange="showlou('lou','<%=ts_id %>','<%=request.getContextPath() %>/YCWYPage/BackPage/charge/show_l.jsp' )";>
		   						<option value="">请选择小区名称</option>
		   			<%
		   				if(list.size()>0){
		   					for(TB_Estate_Village tev : list){
		   			%>
			   					<option value="<%=tev.getEs_id() %>"><%=tev.getEsName()  %></option>
		   			<%
		   					}
		   				}
		   			%>
		   				</select>
	   				</div>
	   			</td>
	   			<td align="right">楼宇名称:</td>
	   			<input type="hidden" name="sign" id="sign" value="2">
	   			<td>
		   			<div id="lou">
		   				<select id="louid" class="input_txt" name="louid" >
		   					<option value="">请选择楼宇名称</option>
		   				</select>
			   		</div>
	   			</td>
	   			<td align="right">单元名称:</td>
	   			<td align="left">
					<div id="sl_unite">
						<jsp:include flush="ture" page="charge_select_unite.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							<jsp:param value="2" name="sign"/>
						</jsp:include>
					</div>
				</td>
	   			<td></td>
	   		</tr>
	   		<tr height="45">
	   			<td align="right">房屋编号:</td>
	  			<td>
	  				<input id="fwid" class="input_txt" name="fwid" value=""/>
	   			</td>
	   			<td align="right">抄表类型:</td>
	   			<td>
					<select id="type" class="input_txt" name="type" >
						<option value="">请选择抄表类型</option>
						<option value="0">水费</option>
	   					<option value="1">电费</option>
	   					<option value="2">燃气费</option>
					</select>
				</td>
				<td align="right">抄表信息状态:</td>
				<td>
					<select id="orderStatus" class="input_txt" name="orderStatus" >
						<option value="">请选择抄表信息状态</option>
						<option value="0">未生存缴费订单</option>
						<option value="1">已生成缴费订单</option>
					</select>
				</td>
				<td align="right">
					<input type="hidden"  id="ts_id" name="ts_id" value="<%=ts_id %>"/>
	  				<input type="button" class="submit_input" value="查询" onclick="selectMete('show_mete','<%=request.getContextPath() %>/YCWYPage/BackPage/charge/mete_selectshow.jsp')"/>
	   			</td>
	    	</tr>
	    </table>
		<div id="show_mete"></div>
	    <div id="Loading_div_da" style="width:100%;height:100%; z-index:1000000; filter:Alpha(opacity=10);opacity:0.1;"></div>
	  	<div id="Loading_div_xiao" style="margin-left:-50px; text-align:center; color:red;width:100px;height:100px;display:none;z-index:1000001;  filter:Alpha(opacity=70);opacity:0.7;">
	 		<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
	  	</div>
		<!-- 查看修改读取层 -->  
		<div id="div_app_show" class="gxg_tcdiv" style="height:150%;display: none;">
		</div>	
		<!-- 查看修改读取层 -->  
		<div id="div_edit_app_show" class="gxg_tcdiv" style="height:150%;display: none;">
		</div>	
  </body>
  <%
  } 
  %>
</html>
