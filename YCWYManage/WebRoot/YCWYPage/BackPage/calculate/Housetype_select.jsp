
<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
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
	String Es_id=S_string.formatString(request.getParameter("Es_id"));

	List<TB_Estate_Housetype> list2 = new MyTB_House_DAO().findHouseType(Es_id);
%>
		<select id="EhType" name="EhType"  class="input_txt" />
				<%
				if(list2.size()>0){
				%>
				<%
					TB_Estate_Housetype houType=null;
					for(int i=0;i<list2.size();i++){
						houType=(TB_Estate_Housetype)list2.get(i);
				%>
				<option value="<%=houType.getHt_id()%>"><%=houType.getHtName()%></option>
				<%
					}
				}else{
				%>
				<option value="">--暂无房屋类型信息--</option>
				<%} %>
		</select>
