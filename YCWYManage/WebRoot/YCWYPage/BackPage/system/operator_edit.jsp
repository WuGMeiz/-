<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_CDE_ROLE"%>
<%@page import="com.sun.corba.se.spi.servicecontext.UEInfoServiceContext"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Bean.TB_CDE_ROLE"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_USER"%>
<%@page import="WYCommunity.MD5"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_ORG"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
	String tu_id = request.getParameter("parID");
	TB_SEV_USER user=new MyTB_SEV_USER().getTB_CDE_USERByUser_id(tu_id,"TB_SEV_USER");
	String orgname=new MyTB_SEV_ORG_DAO().getOrgName(user.getOrg_id());
	TB_CDE_ROLE role=new MyTB_CDE_ROLE().get_Role(user.getRole_code());
	String MENU_CODE=role.getMenu_code();
    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
  	List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
  	String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
  	String [] str = yznr.split("|");
  
 	/**赋值到session**/
	session.setAttribute("MENU_CODE_select",MENU_CODE);
	session.setAttribute("ROLE_CODE_XG",session.getAttribute("ROLE_CODE").toString());
%>
 <div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_user_show');">×</b></div>
   <table border="0" width="100%" cellpadding="0" cellspacing="0">
   
       <tr height="45">
		<td width="12%" align="right" >所属单位：&nbsp;</td>
		<td width="13%" align="left"><input disabled="disabled" type="text" id="czy_edit_orgid" name="czy_edit_orgid"  value="<%=orgname %>"  class="input_txt" maxlength="11"/></td>
		<td width="10%" align="right">操作员账号：&nbsp;</td>
		<td width="13%" align="left"><input disabled="disabled" maxlength="19" style="width:96%;" type="text" id="czy_edit_id" name="czy_edit_id"  value="<%=user.getUserid() %>"  class="input_txt" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
		<td width="10%" align="right">操作员姓名：&nbsp;</td>
		<td width="13%" align="left"><input type="text" style="width:96%;"  id="czy_edit_name" name="czy_edit_name" value="<%=user.getUsername() %>"  class="input_txt" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
	 </tr>
	 <tr height="45">
		<td width="10%" align="right">手机号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czy_edit_phone" name="czy_edit_phone"  value="<%=user.getPhone() %>"  class="input_txt" maxlength="11"/></td>
		<td width="10%" align="right">备注：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czy_edit_remark" name="czy_edit_remark"  value="<%=user.getAddr() %>"  class="input_txt" maxlength="25"/>
		<input type="hidden" id="tu_idx" name="tu_idx" value="<%=tu_id%>">
       <input type="hidden" id="role_codex" name="role_codex" value="<%=role.getRole_code() %>">
		<td width="10%" align="right">开通退款权限：&nbsp;</td>
		</td>
		 <td width="13%" align="left">
		    <input type="radio" name="isTkqx" id="cus1" value="1" <% if("1".equals(user.getIsdrawback()) || "".equals(user.getIsdrawback()) ){ %>checked="checked"<%} %>/>
            <label for="cus1">开通</label>
            <input type="radio" name="isTkqx" id="cus2" value="2" <% if("2".equals(user.getIsdrawback())  ){ %>checked="checked"<%} %> />
            <label for="cus2">不开通</label>
		</td>
	 </tr>
	   <tr height="35">
		<td align="right">
			缴费权限：
		</td>
		<td align="left" colspan="5"></td>
	 </tr>
	 <tr>
	   <td colspan="6">
	      <ul class="qxul">
		   <li class="qxli" align="right">
			 <input type="checkbox" <%if(user.getPayWays().contains("1")){ %>checked="checked"<%} %> name="xxjf" id="xxjf" 
				value="1"/>现金支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" <%if(user.getPayWays().contains("6")){ %>checked="checked"<%} %> name="xxjf" id="xxjf" 
				value="6"/>主扫支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" <%if(user.getPayWays().contains("3")){ %>checked="checked"<%} %> name="xxjf" id="xxjf" 
				value="3"/>转账支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" <%if(user.getPayWays().contains("4")){ %>checked="checked"<%} %> name="xxjf" id="xxjf" 
				value="4"/>刷卡支付
			</li>
			<li class="qxli" align="right">
			 <input type="checkbox" <%if(user.getPayWays().contains("5")){ %>checked="checked"<%} %> name="xxjf" id="xxjf" 
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
						<input type="checkbox" checked="checked" name="xiaoqunane" id="xiaoquname" 
						 value="<%= village.getEs_id()%>"/> <%=village.getEsName() %></li>
					<%	}else{%>
					    <li class="qxli" align="right">
						<input type="checkbox"  name="xiaoqunane" id="xiaoquname" 
						value="<%= village.getEs_id()%>"/> <%=village.getEsName() %></li>
					<%}
						%>	
				<%	}
				}
			 %>
			 <div style="clear:both;"></div>
		 	</ul>
		 </td>
	 </tr>
	 
	<tr height="45">
		<td align="right">
			操作员权限：
		</td>
		<td align="left" colspan="5">
		<div id="menu" class="sevjsgl325ht" style="border:1px solid #d8d8d8;height:280px;overflow:auto;">		
			<jsp:include flush="true" page="/YCWYPage/BackPage/system/edit_role_new_menuTree.jsp"></jsp:include>
		</div></td>
	</tr>
	
 
   </table>
	<table style="width:100%;">
		<tr height="50">
			<td align="center"><input type="button" id="addczy2"
				class="submit_input" value="提&nbsp;&nbsp;交"
				onclick="updateczy('xiaoqunane','<%=request.getContextPath()%>/TB_CDE_ROLE_STATE?act=2','<%=request.getContextPath()%>/YCWYPage/BackPage/system/operator.jsp?type=0');">
			</td>
		</tr>
	</table>
	<!-- 查看修改读取层 -->
		<div id="div_user_show" class="gxg_tcdiv " ></div>
		<!-- 查看修改读取层 -->
		<div id="div_edit_user_show" class="gxg_tcdiv"></div>
<%}%>