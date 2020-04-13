<%@page import="WYBack_Stage.Dao.TB_Estate_VillageDao"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYCommunity.MD5"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_ORG"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
%>
<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
    String org_id = session.getAttribute("ORG_ID").toString();
    String divid=request.getParameter("divid");
    String tu_id = session.getAttribute("TU_ID").toString();
    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
%>

 <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_user_show');">×</b></div>
     <%int org_level=new MyTB_SEV_ORG_DAO().getorg_level(org_id,ts_id);
     List<TB_Estate_Village> list= new TB_Estate_VillageDao().select_xiaoqu(ts_id);
     String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
     %>
   <table border="0" width="100%" cellpadding="0" cellspacing="0">
   <%if(org_level==0){
   %>
       <tr height="45">
		<td width="12%" align="right" >商户名称：&nbsp;</td>
		<td width="13%" align="left" >
			<select id="Org_id1" name="Org_id1" class="input_txt">
			<option value="">--请选择商户名称--</option>
				<%
					List<TB_SEV_ORG> lista=new MyTB_SEV_ORG_DAO().getTB_SEV_ORGAll(org_id,ts_id);
					for(int i=0;i<lista.size();i++) {
						TB_SEV_ORG cat=lista.get(i);
				%>
				<option value="<%=cat.getOrg_id() %>"><%=cat.getOrg_name() %></option>
				<%
					}
				%>
			</select>
		</td>
		<td width="10%" align="right">操作员姓名：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyname1" name="czyname1" value=""  class="inputhalf" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
		<td width="10%" align="right">操作员账号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyid1" name="czyid1" maxlength="3"  value=""  class="inputhalf" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
	    
	 </tr>
	 <tr height="45">
		<td width="10%" align="right">手机号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyphone1" name="czyphone1"  value=""  class="input_txt" maxlength="11"/></td>
		<td width="10%" align="right">备注：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyremark1" name="czyremark1" maxlength="25" value=""  class="inputhalf" maxlength="25"/></td>
	    <td width="10%" align="right">开通退款权限：&nbsp;</td>
	    <td width="13%" align="left">
		    <input type="radio" name="isTkqx1" checked="checked" id="cus1" value="1" />
            <label for="cus1">开通</label>
            <input type="radio" name="isTkqx1" id="cus2" value="2"/>
            <label for="cus2">不开通</label>
		</td>
	 </tr>
	 <tr height="35">
		<td align="right">
			缴费方式权限：
		</td>
		<td align="left" colspan="5"></td>
	 </tr>
	 <tr>
	   <td colspan="6">
	      <ul class="qxul">
		   <li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="1"/>现金支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="6"/>主扫支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="3"/>转账支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="4"/>刷卡支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="5"/>调账支付
			</li>
				
		   <div style="clear:both;"></div>
		  </ul>
	   </td>
	 </tr>
	 <tr height="35">
		<td align="right">
			管理的小区：
		</td>
		<td align="left" colspan="5"></td>
	</tr>
	 <tr height="45">
	 	<td colspan="6">
	 		<ul class="qxul">
			 <%
			 if(list.size()>0){
					TB_Estate_Village village=null;
					for(int i=0;i<list.size();i++){
						village=(TB_Estate_Village)list.get(i);
						if(yznr.contains(village.getEs_id()+"")){
						    %>   
						<li class="qxli" align="right">
						<input type="checkbox" checked="checked" name="xiaoquname1" id="xiaoquname1" 
						value="<%= village.getEs_id()%>"/> <%=village.getEsName() %></li>
					<%	}else{%>
					    <li class="qxli" align="right">
						<input type="checkbox"  name="xiaoquname1" id="xiaoquname1" 
						value="<%= village.getEs_id()%>"/> <%=village.getEsName() %></li>
					<%}
						%>	
				<%	}
				}else{
				    %> 
				    <li colspan="4" align="center">暂无小区，请添加小区</li>
				<%        
				}
			 %>
			 <div style="clear:both;"></div>
		 	</ul>
		 </td>
	 </tr>
	
 <%  }else{
 %>
     <tr height="35">
		<td width="12%" align="right" >商户名称：&nbsp;</td>
		<td width="13%" align="left" >
			<select id="Org_id1" name="Org_id1" class="input_txt">
			<option value="">--请选择商户名称--</option>
				<%
					List<TB_SEV_ORG> lista=new MyTB_SEV_ORG_DAO().getTB_SEV_ORGAll(org_id,ts_id);
					for(int i=0;i<lista.size();i++) {
						TB_SEV_ORG cat=lista.get(i);
				%>
				<option value="<%=cat.getOrg_id() %>"><%=cat.getOrg_name() %></option>
				<%
					}
				%>
			</select>
		</td>
		<td width="10%" align="right">操作员姓名：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyname1" name="czyname1" value=""  class="inputhalf" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
		<td width="10%" align="right">操作员账号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyid1" name="czyid1"  value="" maxlength="3" class="inputhalf" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
	 </tr>
	 <tr height="35">
		<td width="10%" align="right">手机号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyphone1" name="czyphone1"  value=""  class="input_txt" maxlength="11"/></td>
		<td width="10%" align="right">备注：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyremark1" name="czyremark1" maxlength="25" value=""  class="inputhalf" maxlength="25"/></td>
	    <td width="10%" align="right">开通退款权限：&nbsp;</td>
	    <td width="13%" align="left">
		    <input type="radio" name="isTkqx1" checked="checked" id="cus1" value="1" />
            <label for="cus1">开通</label>
            <input type="radio" name="isTkqx1" id="cus2" value="2"/>
            <label for="cus2">不开通</label>
		</td>
	 </tr>
	  <tr height="35">
		<td align="right">
			缴费方式权限：
		</td>
		<td align="left" colspan="5"></td>
	 </tr>
	 <tr>
	   <td colspan="6">
	      <ul class="qxul">
		   <li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="1"/>现金支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="6"/>主扫支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="3"/>转账支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="4"/>刷卡支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" checked="checked" name="xxjf1" id="xxjf1" 
				value="5"/>调账支付
			</li>
				
		   <div style="clear:both;"></div>
		  </ul>
	   </td>
	 </tr>
	 <tr height="35">
		<td align="right">
			管理的小区：
		</td>
		<td align="left" colspan="5"></td>
	</tr>
	 <tr height="45">
	 	<td colspan="6">
	 		<ul class="qxul">
			 <%
			 if(list.size()>0){
					TB_Estate_Village village=null;
					for(int i=0;i<list.size();i++){
						village=(TB_Estate_Village)list.get(i);
						if(yznr.contains(village.getEs_id()+"")){%>
						    
						<li class="qxli" align="right">
						<input type="checkbox" checked="checked" name="xiaoquname1" id="xiaoquname1" value="<%= village.getEs_id()%>"/><%=village.getEsName() %></li>
					<%	}else{%>
					    <li class="qxli" align="right">
						<input type="checkbox"  name="xiaoquname1" id="xiaoquname1" value="<%= village.getEs_id()%>"/><%=village.getEsName() %></li>
					<%}
						%>	
				<%	}
				}
			 %>
			 <div style="clear:both;"></div>
		 	</ul>
		 </td>
	 </tr>
	<tr height="35">
		<td align="right">
			操作员权限：
		</td>
		<td align="left" colspan="3"></td>
	</tr>
<% }
   %>
   </table>
   <table style="width:100%;">
		<tr>
			<td align="right" width="15%"></td>
			<td align="left">
			<div id="menu" style="width:980px;height:450px;text-align:center;padding-top:5px;border:1px solid #6699CC;">
				<jsp:include flush="true" page="new_Menu_Tree.jsp"></jsp:include>
			</div>
			</td>
		</tr>
	</table>
	<table style="width:100%;">
				<tr height="50">
					<td align="center">
						<input type="button" id="addczy2" class="submit_input" value="提&nbsp;&nbsp;交" onclick="addczy('xiaoquname1','<%=request.getContextPath()%>/TB_CDE_ROLE_STATE?act=1','<%=request.getContextPath() %>/YCWYPage/BackPage/system/operator.jsp');">
					</td>
				</tr>
			</table>	
			<!-- 查看修改读取层 -->
		<div id="div_user_show" class="gxg_tcdiv " ></div>
		<!-- 查看修改读取层 -->
		<div id="div_edit_user_show" class="gxg_tcdiv"></div>

<%}%>