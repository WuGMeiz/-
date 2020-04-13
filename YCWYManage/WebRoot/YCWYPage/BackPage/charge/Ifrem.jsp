<%@page import="WYCommunity.Base64Utils"%>
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
    <title>水费抄表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="scription" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/YCWYPage/BackPage/css/content.css"/>
	<script src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/index.js" type="text/javascript"></script>
  </head>
  
  <body style="border:none;">
  	<%
		String  ts_id = session.getAttribute("U_ID").toString(); 
  		String  YZNR = session.getAttribute("YZNR").toString();
  		List<TB_Estate_Village> list = null;
  		if(YZNR!=null && !YZNR.equals("")){
  			list = new Mete_ReadClass().findxiaoqu(ts_id,YZNR);
  		}else{
  			list = new Mete_ReadClass().findxiaoquALL(ts_id);
  		}
		  	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
			wulifilepath=wulifilepath.replace('\\','/');
			String filename="批量录入抄表信息模版.xls";
	  		//String ts_id="1";
			
  	%>
  	<form name="PLMete" method="post" enctype="multipart/form-data"  action="<%=request.getContextPath()%>/MetePl_Servlet">
   	<table class="showTdiv">
   		<tr height="45">
   			<td align="right">小区名称:</td>
   			<td>
   				<div id="xiaoqu">
	   				<select id="xiaoquid" class="input_txt" name="xiaoquid" onchange="showlou('lou','<%=ts_id %>','<%=request.getContextPath() %>/YCWYPage/BackPage/charge/show_l.jsp' )">
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
   		</tr>
   		<tr height="45">
   			<td align="right">楼宇名称:</td>
   			<input type="hidden" name="sign" id="sign" value="1">
   			<td>
	   			<div id="lou">
	   				<select id="louid" class="input_txt"  name="louid">
	   					<option value="">请选择楼宇名称</option>
	   				</select>
		   		</div>
   			</td>
   		</tr>
   		<tr id="sl_unite">
   		</tr>
   		<tr height="45">
   			<td align="right">费用类型:</td>
   			<td>
   				<select id="type" class="input_txt"  name="type" >
   					<option value="0">水费</option>
   					<option value="1">电费</option>
   					<option value="2">燃气费</option>
   				</select>
   			</td>
   		</tr>
   		<tr height="45">
   			<td align="right">计量单位:</td>
   			<td>
   				<select id="unit" class="input_txt" name="unit" >
   					<option value="0">立方</option>
   					<option value="1">度</option>
   				</select>
   			</td>
   		</tr>
   		<tr height="60">	
			<td align="right">
				选择文件：
			</td>
			<td align="left">
				<input type="file" id="METE_file" name="METE_file" /><font style="color: red;font-size: 11px;"> *</font>
			</td>
		</tr>
   		<tr height="45">
			<td colspan="2" align="center">
				<input type="submit" onclick="return TB_Mete_pl();" class="submit_input" value=" 导 入 ">
				&emsp;<input type="reset"   class="submit_input" value=" 重  置 ">
				<input type="hidden" name="return_divid" value="showmain"  >
				<input type="hidden" name="ts_id" value="<%=ts_id %>" >
				<input type="hidden" name="return_url" class="input_txt" value="<%=request.getContextPath()+"/YCWYPage/BackPage/charge/mete_reading.jsp" %>" >
			</td>
					
		</tr>
		<tr height="60">	
			<td align="right">
			</td>
			<td align="left">
				<input type="button" id="ex" name="ex" value="【生成模版】" onclick="metecreateReport_sev('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename)%>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/MetecreateReport_sev.jsp','dcddbb');"  />
				<input type="button" id="dcddbb" name="dcddbb" value="【下载模版】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"/YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;" />
			</td>
		</tr>
   	</table>
   	<div> 
			<font style="color: red;font-size: 12px;padding-left: 180px;" >
				提示：您可以批量导入抄表信息，请点击【下载模版】按钮下载到本地，按照模版要求填写表格内容。
			</font>
		</div>
   	</form>
   	<div id="Loading_div_da" style="width:100%;height:100%; z-index:1000000; filter:Alpha(opacity=10);opacity:0.1;"></div>
  	<div id="Loading_div_xiao" style="margin-left:-50px; text-align:center; color:red;width:100px;height:100px;display:none;z-index:1000001;  filter:Alpha(opacity=70);opacity:0.7;">
 		<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
  	</div>
  </body>
</html>
