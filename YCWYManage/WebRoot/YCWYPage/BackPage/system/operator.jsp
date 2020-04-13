<%@page import="WYBack_Stage.Bean.TB_SEV_ORG"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
%>
<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
    String org_id = session.getAttribute("ORG_ID").toString();
    String ts_id = session.getAttribute("U_ID").toString();
%>
 
    <%int org_level=new MyTB_SEV_ORG_DAO().getorg_level(org_id,ts_id); %>
   <table border="0" width="100%" cellpadding="0" cellspacing="0">
   <%if(org_level==0){
   %>
       <tr height="35">
		<td width="12%" align="right">商户名称：&nbsp;</td>
		<td width="13%" align="left">
			<select id="Org_id2" name="Org_id2" class="input_txt">
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
		<td width="10%" align="right">操作员名称：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyname2" 
		name="czyname2" value="" style="width:100%;" class="input_txt" maxlength="25"/></td>
		<td width="10%" align="right">操作员编号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyid2" 
		name="czyid2" maxlength="50" value="" style="width:100%;" class="input_txt" maxlength="25"/></td>
		<td width="10%" align="right"><input class="submit_input" type="button" value="新建" 
		onclick="new_show_see_tanchu_div('div_user_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/system/operator_add.jsp"%>');"></td>
		<td align="center"><input class="submit_input" type="button" name="chaxun" value="查询" 
		onclick="return selectczy('<%=org_level %>','show_select_divid','<%=request.getContextPath()%>/YCWYPage/BackPage/system/operator_show.jsp');"></td>
		
	</tr>
 <%  }else{
 %>
     <tr height="35">
		<td width="12%" align="right">商户名称：&nbsp;</td>
		<td width="13%" align="left">
			<select id="Org_id2" name="Org_id2" class="input_txt">
				<%
					List<TB_SEV_ORG> lista=new MyTB_SEV_ORG_DAO().getTB_SEV_ORGAll1(org_id,ts_id);
					for(int i=0;i<lista.size();i++) {
						TB_SEV_ORG cat=lista.get(i);
				%>
				<option value="<%=cat.getOrg_id() %>"><%=cat.getOrg_name() %></option>
				<%
					}
				%>
			</select>
		</td>
		<td width="10%" align="right">操作员名称：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyname2" name="czyname2" value="" style="width:100%;" class="input_txt" maxlength="25"/></td>
		<td width="10%" align="right">操作员编号：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="czyid2" name="czyid2" maxlength="50" value="" style="width:100%;" class="input_txt" maxlength="25"/></td>
		<td width="10%" align="right"><input class="submit_input" type="button" style="margin-left: 10px;color: gray" value="新建" onclick="addczy1();"></td>
		<td align="center"><input class="submit_input" type="button" name="chaxun" value="查询" onclick="return selectczy('<%=org_level %>','show_select_divid','<%=request.getContextPath()%>/YCWYPage/BackPage/system/operator_show.jsp');"></td>
		
	</tr>
<% }
   
   %>
	</table>
	<hr width="100%" style="margin-bottom:10px;margin-top:10px;color:#999999;text-align:center;"/>
	<div id="show_select_divid" class="addBoxList">
		 <jsp:include page="operator_show.jsp" flush="true">
		  <jsp:param value="<%=org_level %>" name="org_level"/>
		 </jsp:include>
	</div>
		<!-- 查看修改读取层 -->
		<div id="div_user_show" class="gxg_tcdiv " ></div>
		<!-- 查看修改读取层 -->
		<div id="div_edit_user_show" class="gxg_tcdiv"></div>
 

<%
}
%>