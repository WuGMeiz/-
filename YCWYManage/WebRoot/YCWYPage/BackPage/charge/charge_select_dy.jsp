<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Unit"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
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
	String ts_id=S_string.formatString(request.getParameter("ts_id"));
	String Es_id=S_string.formatString(request.getParameter("Es_id"));
	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
	List<TB_Estate_Unit> listDy = new MyTB_Build_DAO().select_dy(ts_id, Es_id,Bu_id);
%>
		<select id="Un_id" name="Un_id"  class="input_txt">
				<%
				if(listDy.size()>0){
				%>
				<option value="">--请选择单元--</option>
				<%
				TB_Estate_Unit dy=null;
					for(int i=0;i<listDy.size();i++){
						dy=(TB_Estate_Unit)listDy.get(i);
				%>
				<option value="<%=dy.getUn_id()%>"><%=dy.getUnName()%></option>
				<%
					}
				}else{
				%>
				<option value="">--请选择单元--</option>
				<%} %>
		</select>
