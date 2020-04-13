<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Unit"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
	request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
 if(session.getAttribute("USER_ID")==null)
{
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
}
else
{	
	String ts_id=S_string.formatString(request.getParameter("ts_id"));
	String Es_id=S_string.formatString(request.getParameter("Es_id"));
	List<TB_Estate_Housetype> list3= new MyTB_House_DAO().findHouseType(Es_id);
%>
	<select id="EhType" class="input_txt" name="EhType" class="input_txt">
		<option value="">--请选择房屋类型--</option>
			<%
			if(list3.size()>0){
			%>
			
			<%
			if(list3.size()>0){
				TB_Estate_Housetype unit=null;
				
				for(int i=0;i<list3.size();i++){
				
					unit=(TB_Estate_Housetype)list3.get(i);
			%>
					<option value="<%=unit.getHt_id()%>"><%=unit.getHtName()%></option>
				
			<%
				}
				
			}else{
			%>
			 <option value="">暂无信息</option>
			<%} %>
	</select>
<%} %>
