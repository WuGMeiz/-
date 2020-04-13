<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
 String tu_id = session.getAttribute("TU_ID").toString();
 String dividly=S_string.formatString(request.getParameter("dividly"));
 String dividdy=S_string.formatString(request.getParameter("dividdy"));
 String ts_id=S_string.formatString(request.getParameter("ts_id"));
 String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
 List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
 %>
 <select id="Es_id" name="Es_id" class="input_txt" onchange="select_ly(this,'<%=dividly %>','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_ly2.jsp"%>','<%=dividdy %>','<%=ts_id %>')" >
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
<%
}
%>