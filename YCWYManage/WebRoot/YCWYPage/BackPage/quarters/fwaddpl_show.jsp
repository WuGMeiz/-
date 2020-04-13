<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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

	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径    
	wulifilepath=wulifilepath.replace('\\','/');
	String filename="房屋信息批量导入模板.xls";
	 String ts_id= session.getAttribute("U_ID").toString();
	List<TB_Estate_Village> list= new ChangeDao().select_xiaoqu(ts_id);
	String tu_id = session.getAttribute("TU_ID").toString();
	String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
 %>
<html>
   <head>

	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/YCWYPage/BackPage/js/index.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/YCWYPage/BackPage/swept/js/jquery-1.8.2.min.js"></script>
	<link href="<%=request.getContextPath() %>/YCWYPage/BackPage/css/welcss.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	*{font-family:"微软雅黑";}
	.input_txt {
	    border: 1px solid #cccccc;
	    border-radius: 3px;
	    height: 32px;
	    outline: medium none;
	}
	.submit_input {
	    border: medium none;
	    border-radius: 3px;
	    color: #fff;
	    background-color:#307ecc ;
	    cursor: pointer;
	    font-size: 16px;
	    height: 32px;
	    outline: medium none;
	    width: 91px;
	}
	.show_table1{
		width: 500px;
		margin: 0 auto;
	}
	.show_table1 select{
		width:100%;
	}
	</style>
	</head>
 
   <body style="background-color: #FFFFFF;">

	<form name="uploadxinxi" method="post" enctype="multipart/form-data"  action="<%=request.getContextPath()%>/WyTB_House_addpl">
		<table class="show_table1" border="0" cellpadding="0" cellspacing="0">
			<tr height="55">	
				<td align="right" width="100">选择小区：&nbsp;</td>
				<td  width="300">
					<select class="input_txt" id="Es_id" name="Es_id" 
					onchange="select_louyu(this,'louyu','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_ly.jsp"%>','<%=ts_id %>')" />
						<option value="">--请选择小区--</option>
							<%
							if(list.size()>0){
								TB_Estate_Village tev=null;
								for(int i=0;i<list.size();i++){
									tev=(TB_Estate_Village)list.get(i);
										if(!yznr.equals("")){
										
											if(yznr.contains(tev.getEs_id()+"")){
	
						%>
							<option value="<%=tev.getEs_id() %>" ><%=tev.getEsName()%></option>
							<% 
											}
										}else{
							%>
							<option value="<%=tev.getEs_id() %>" ><%=tev.getEsName()%></option>
							<% 
										}
								}
							}
							 %>
					</select>
				</td>
				</tr>
			<tr height="45">
				<td align="right">选择楼宇：&nbsp;</td>
				<td >
					<div id="louyu">
						<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/charge_select_ly.jsp">
						<jsp:param value="<%=ts_id %>" name="ts_id"/>
						</jsp:include>
					</div>
				</td>
			</tr>
			<tr height="45">
				<td align="right">选择单元：&nbsp;</td>
				<td >
					<div id="dy">
						<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/charge_select_dy.jsp">
						<jsp:param value="<%=ts_id %>" name="ts_id"/>
						</jsp:include>
					</div>
				 </td>
			</tr>	
			<tr height="45">
				<td align="right">房屋类型：&nbsp;</td>
				<td>
				  <select id="EhType" class="input_txt" name="EhType" >
				  <option value="">--请选择房屋类型-</option>
				  <option value="">--暂未设置房屋类型-</option>
				   <%
			 			List<TB_Estate_Housetype> housetypes=new MyTB_House_DAO().findHouType();
			 		     if(housetypes.size()>0){
			 		      for(TB_Estate_Housetype type:housetypes){
			 		        if(type.getHt_id()==1){
			 		    %>
			 		      	<option value="<%=type.getHt_id()%>" selected="selected"><%=type.getHtName() %></option>
			 		    <%}else{ %>
			 		        <option value="<%=type.getHt_id()%>"><%=type.getHtName() %></option>
			 		    <%}}}else{ %>
			 		        <option value="">--暂无房屋类型设置-</option>
				 		<%} %>
				  </select> 
				</td>
			</tr>
			<tr height="45">	
			    <td align="right" width="20%">
			    	选择文件：
			    </td>
			    <td align="left"  >
			    	<input type="hidden" name="return_divid" value="showmain" >
					<input type="hidden" name="return_url" value="<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwaddpl.jsp" %>" >
			      <input type="file" id="yonghu_file" name="yonghu_file"  /><font style="color: red;font-size: 11px;"> *</font>
			    </td>
			    </tr>
			<tr height="45">
				<td colspan="2" align="center">
				<input type="submit" onclick="return TBPay_User_addpl();" value=" 导 入 "  class="submit_input">
				&emsp;<input type="reset"   class="submit_input" value=" 重  置 ">
			    </td>
			</tr>
			<tr height="55">	
				<td align="center" width="100%" colspan="3">
					<input type="button" id="ex" name="ex" value="【生成模板】" onclick="createReport_sev('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/quarters/createReport_sev.jsp','dcddbb');"  />
					<input type="button" id="dcddbb" name="dcddbb" value="【下载模板】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"/YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;" />
				</td>
			</tr>
		</table>
	</form>
	
		<table class="show_table" >
			<tr height="25">	
				<td align="left" style="color: red;font-size: 12px;padding-left: 25px;">
				温馨提示：<br/>
				房屋性质 ，您可以填写如“高层、排屋、小高层、低层、别墅 等等 ”；<br/>
				房屋结构 ，您可以填写如“砖木结构、砖混结构、钢筋混凝土结构、钢结构 等等 ”；<br/>
				房屋类型 ，您可以填写如“住宅、公寓、别墅、写字楼、商铺、其他 等等 ”；<br/>
				楼宇朝向 ，您可以填写如“东向、西向、南向、北向、东南、东北、西南、西北 等等 ”；<br/>
				房屋现状 ，您可以填写如“自用、租赁、空闲 等等 ”；<br/>
				产权状况 ，您可以填写如“大产权，小产权 等等 ”；<br/>
				房屋用途 ，您可以填写如“商用，民用 等等 ”；<br/>
				您可以批量导入房屋信息，请点击【下载模版】按钮下载到本地，按照模版要求填写房屋信息。
			</tr>
		</table>
<!-- 显示等待图片的读取层 -->	
 <div id="Loading_div_da" style="width:1000px;height:600px;display: none; z-index:1000000; filter:Alpha(opacity='50');"></div>
 <div id="Loading_div_xiao" style="margin-left:-50px; text-align:center; color:red;width:100px;height:100px;display:none;z-index:1000001;  filter:Alpha(opacity=70);">
 </div> 	
 
  </body>
</html>

<%

}
%>