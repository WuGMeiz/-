<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/YCWYPage/BackPage/css/welcome.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/content.css" id="cssfile"/>
	<!-- 日历控件 start 必须按Date.css、Isie.js、Date.js顺序引入放可使用,并且  Date.js必须要放在页面<body>里才可以-->
	<link href="<%=request.getContextPath() %>/datejsorcss/Date.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/datejsorcss/Isie.js"></script>
	<link id="alertMsg" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/alertMsg.css" type="text/css" rel="stylesheet" /> 
	<%--  <link id="welcss" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/welcss.css" /> --%>
	<link id="jhshgl" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/jhshgl.css" />
	<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/jquery-1.9.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/js.js"></script>
	<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/index.js"></script>
	<title>山西综合服务平台</title>
  </head>
  
  <body style="border:none;" >
 	<!-- 日历控件 start 必须按Date.css、Isie.js、Date.js顺序引入放可使用,并且--Date.js必须要放在页面<body>里才可以--> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/datejsorcss/Date.js"></script>
	<!-- 日历控件 start -->
  	<%
		String  ts_id = session.getAttribute("U_ID").toString(); 
		String tu_id = session.getAttribute("TU_ID").toString();
		String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
		List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
	  	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
		wulifilepath=wulifilepath.replace('\\','/');
		String filename="批量导入信息模版.xls";
  	%>
  	<form name="PLMete" method="post" enctype="multipart/form-data"  action="<%=request.getContextPath()%>/GuePl_Servlet">
   	<table class="showTdiv">
   		<tr height="45">
   			<td align="right">小区名称:</td>
   			<td>
   				<div id="xiaoqu">
	   				<select id="xiaoquid" class="input_txt" name="xiaoquid"  onchange="showlou('lou','<%=ts_id %>','<%=request.getContextPath() %>/YCWYPage/BackPage/charge/show_l.jsp' )";>
	   						<option value="">请选择小区名称</option>
	   			<%
	   				if(list.size()>0){
	   					TB_Estate_Village village=null;
								for(int i=0;i<list.size();i++){
									village=(TB_Estate_Village)list.get(i);
									if(!yznr.equals("")){
									if(yznr.contains(village.getEs_id()+"")){
	   			%>
		   					<option value="<%=village.getEs_id() %>"><%=village.getEsName()  %></option>
	   			<%
	   						}
	   						}
	   						else{
	   						%>
		   					<option value="<%=village.getEs_id() %>"><%=village.getEsName()  %></option>
	   			<%
	   						
	   						}
	   					}
	   				}
	   			%>
	   				</select>
   				</div>
   			</td>
   		</tr>
   		<tr height="45">
   			<td align="right">楼宇名称:</td>
   			<input type="hidden" name="sign" id="sign" value="1">
   			<td>
	   			<div id="lou">
	   				<select id="louid" class="input_txt"  name="louid" >
	   					<option value="">请选择楼宇名称</option>
	   				</select>
		   		</div>
   			</td>
   		</tr>
   		<tr id="sl_unite">
		</tr>
       	<tr height="45">
			<td align="right">费用起止日期：<font style="color: red;">*</font></td>
			<td align="left">
				<input type="text" style="width: 45.7%;" class="input_txt" id="cost_startTime" name="cost_startTime" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />至
				<input type="text" style="width: 45.7%;" class="input_txt" id="cost_endTime" name="cost_endTime" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
			</td>
		</tr>
   		<tr height="60">	
			<td align="right">
				选择文件：
			</td>
			<td align="left">
				<input type="file" id="Gue_file" name="Gue_file" /><font style="color: red;font-size: 11px;"> *</font>
			</td>
		</tr>
   		<tr height="45">
			<td colspan="2" align="center">
				<input type="submit" onclick="return TB_gu_pl();" class="submit_input" value=" 导 入 ">
				&emsp;<input type="reset"   class="submit_input" value=" 重  置 ">
				<input type="hidden" name="return_divid" value="showmain"  >
				<input type="hidden" name="ts_id" value="<%=ts_id %>" >
				<input type="hidden" name="return_url" class="input_txt" value="<%=request.getContextPath()+"/YCWYPage/BackPage/charge/gue_pl.jsp" %>" >
			</td>
					
		</tr>
		<tr height="60">	
			<td align="right">
			</td>
			<td align="left">
				<input type="button" id="ex" name="ex" value="【生成模版】" onclick="gue_createReport_sev('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename)%>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/gue_createReport_sev.jsp','dcddbb');"  />
				<input type="button" id="dcddbb" name="dcddbb" value="【下载模版】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"/YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;" />
			</td>
		</tr>
   	</table>
   	<div> 
			<font style="color: red;font-size: 12px;padding-left: 225px;" >
				提示：您可以批量导入固额订单信息，请点击【下载模版】按钮下载到本地，按照模版要求填写表格内容。<br/>
			</font>
			<font style="color: red;font-size: 12px;padding-left: 350px;" >
			如对应的缴费项金额输入“0”，则不生成该缴费项的订单。
			</font>
		</div>
   	</form>
   	<div id="Loading_div_da" style="width:100%;height:100%; z-index:1000000; filter:Alpha(opacity=10);opacity:0.1;"></div>
  	<div id="Loading_div_xiao" style="margin-left:-50px; text-align:center; color:red;width:100px;height:100px;display:none;z-index:1000001;  filter:Alpha(opacity=70);opacity:0.7;">
 		<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
  	</div>
  </body>
</html>
