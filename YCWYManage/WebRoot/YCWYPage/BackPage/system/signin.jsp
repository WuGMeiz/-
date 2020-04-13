<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />

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
	String divid=request.getParameter("divid");
	String org_id = session.getAttribute("ORG_ID").toString();
	String level = session.getAttribute("LEVELS").toString();
	String ts_id = session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	String org_idTemp = "";

%>
<div class="tables">
	<table class="show_table">
		<tr height="45">					
			<td align="right" width="20%">
				选择小区：
			</td>
			    <td align="left" width="80%">
				<select id="Es_id" class="input_txt" style="width:42%;" name="Es_id" onchange="select_yonghudlszshow1(this,'dlsz_show','<%=request.getContextPath() %>/YCWYPage/BackPage/system/signin_show.jsp');" >
							<%
							int es_id=0;
							List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
							if(list.size()>0){
								TB_Estate_Village village=null;
								for(int i=0;i<list.size();i++){
									village=(TB_Estate_Village)list.get(i);
									
									if(yznr.equals("")){
									    if(i==0){
											es_id=village.getEs_id();
										}
									 %>
									<option value="<%=village.getEs_id()%>"><%=village.getEsName() %></option>
								<%	
									}else{
									    if(yznr.contains(village.getEs_id()+"")){
									       String [] str= yznr.split(",");
									        es_id=Integer.parseInt(str[0]) ;
										    %>
											<option value="<%=village.getEs_id()%>"><%=village.getEsName() %></option>
									<%	}
									}
								}
							}else{
							    %>
							       <option value="">暂无小区</option>
							<%}
							 %>
					</select>
			</td>
			
		</tr>
	</table>
	
	<div id="dlsz_show">
		<jsp:include page="signin_show.jsp" flush="true">
		 <jsp:param value="<%=es_id %>" name="es_id"/></jsp:include>
	</div>
	<table class="show_table">
		<tr height="45">	
			<td align="right" width="10%" style="color: red;font-size: 12px;" valign="top">
				提示：
			</td>
			<td align="left" width="90%" style="color: red;font-size: 12px;" >
				1.系统提供三个用户登录验证项，从左至右“第一项验证内容”必须是唯一内容，用来判别用户的唯一性；其他2个验证内容无唯一性要求。<br>
				2.您可以在此设置3个验证项在用户缴费登录页面上展示。勾选的验证项为用户登录页面需要填写的项目。<br>
			</td>
		</tr>
	</table>
</div>
<%
}
%>
