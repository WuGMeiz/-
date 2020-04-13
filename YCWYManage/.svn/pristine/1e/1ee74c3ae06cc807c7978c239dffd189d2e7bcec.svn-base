<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_ORG"%>
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
		<td  align="right">机构名称：&nbsp;</td>
		<td   align="left">
			<select id="org_id" name="org_id"  class="input_txt">
				<%
					List<TB_SEV_ORG> lista=new MyTB_SEV_ORG_DAO().getTB_SEV_ORG(org_id,ts_id);
					for(int i=0;i<lista.size();i++) {
						TB_SEV_ORG cat=lista.get(i);
				%>
				<option value="<%=cat.getOrg_id() %>"><%=cat.getOrg_name() %></option>
				<%
					}
				%>
			</select>
		</td>
		<td   align="right">单位名称：&nbsp;</td>
		<td   align="left"><input type="text" style="width:95%;" id="orgName" name="orgName" value="" class="input_txt" maxlength="25"/><font style="color:red;font-size:11px;">*</font></td>
		<td   align="right">单位描述：&nbsp;</td>
		<td   align="left"><input type="text" id="orgRemark" name="orgRemark" maxlength="50" value=""   class="input_txt" maxlength="25"/></td>
		<td   align="right"><input class="submit_input" type="button" value="新建" onclick="return addorg('showmain','<%=request.getContextPath()%>/AddOrgServlet','<%=request.getContextPath()%>/YCWYPage/BackPage/system/org.jsp');"></td>
		<td   align="right"><input class="submit_input" type="button" value="查询" onclick="return selectorg('show_update','<%=request.getContextPath()%>/YCWYPage/BackPage/system/org_show.jsp');"></td>
		
	</tr>
 <%  }else{
 %>
     <tr height="35">
		<td width="12%" align="right">机构名称：&nbsp;</td>
		<td width="13%" align="left">
			<select  id="org_id" name="org_id" class="input_txt">
				<%
					List<TB_SEV_ORG> lista=new MyTB_SEV_ORG_DAO().getTB_SEV_ORG(org_id,ts_id);
					for(int i=0;i<lista.size();i++) {
						TB_SEV_ORG cat=lista.get(i);
				%>
				<option value="<%=cat.getOrg_id() %>"><%=cat.getOrg_name() %></option>
				<%
					}
				%>
			</select>
		</td>
		<td width="10%" align="right">单位名称：&nbsp;</td>
		<td width="13%" align="left"><input  type="text" id="orgName" name="orgName" style="width:100%;" class="input_txt" maxlength="25"/></td>
		<td width="10%" align="right">单位描述：&nbsp;</td>
		<td width="13%" align="left"><input type="text" id="orgRemark" name="orgRemark" style="width:100%;" class="input_txt" maxlength="25"/></td>
		<td width="10%" align="right"><input  class="submit_input" type="button" style="margin-left: 10px;color: gray" value="新建" onclick="return addorg1();"></td>
		<td width="10%" align="right"><input type="button" class="submit_input" value="查询" onclick="return selectorg('show_update','<%=request.getContextPath()%>/YCWYPage/BackPage/system/org_show.jsp');"></td>
		
	</tr>
<% }
   
   %>
	</table>
	<hr width="100%" style="margin-bottom:10px;margin-top:10px;color:#999999;text-align:center;"/>
	<div id="show_update" class="addBoxList">
		 <jsp:include page="org_show.jsp" flush="true"></jsp:include>
	</div>
 
<%}%>