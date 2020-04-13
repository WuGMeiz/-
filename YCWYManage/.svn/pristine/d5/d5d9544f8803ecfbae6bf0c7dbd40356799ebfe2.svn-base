 
<%@page import="WYBack_Stage.Dao.WwTB_Estate_itemDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_item"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	String ts_id=session.getAttribute("U_ID").toString();

 	String Es_id=S_string.formatString(request.getParameter("Es_id"));
	List<TB_Estate_item> list= new WwTB_Estate_itemDao().selectitem1(Es_id,ts_id);
%>
     <select id="payItem" name="payItem" class="input_txt">
     	<option value="">--请选择收费项--</option>
<%
	 if(list.size()>0){
        for(TB_Estate_item tei : list){
  %>
		<option value="<%=tei.getEi_id()%>"><%=tei.getItemName() %></option>
<%
		}
	 } 
%>		
  	 </select>
<%
}
%>
