<%@page import="WYBack_Stage.Dao.TB_Estate_ExoptionDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Extopic"%>
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
	String wj_id=S_string.formatString(request.getParameter("wj_id"));
	 //new  ArrayList();
	 List<TB_Estate_Extopic> list2 = new ArrayList<TB_Estate_Extopic>();
	 //System.out.print(wj_id);
	 if(wj_id != "" && !wj_id.equals("")){
		 list2 = new TB_Estate_ExoptionDao().select_Extopic(wj_id);
	 }
	 
	//List<TB_Estate_Build> list2 = new MyTB_Estate_Order().select_louyu(ts_id, Es_id);
%>
		<select id="To_id" name="To_id" class="input_txt">
				<%
				if(list2.size()>0){
				%>
				<option value="">--请选择题目--</option>
				<%
				TB_Estate_Extopic extopic=null;
					for(int i=0;i<list2.size();i++){
						extopic=(TB_Estate_Extopic)list2.get(i);
				%><!-- strs.substring(0, i) + "..." -->
				<option value="<%=extopic.getTo_id()%>"><%=extopic.getTopic().length() > 7?extopic.getTopic().substring(0, 7) + "...":extopic.getTopic()%></option>
				<%
					}
				}else{
				%>
				<option value="">--请选择题目--</option>
				<%} %>
		</select>
