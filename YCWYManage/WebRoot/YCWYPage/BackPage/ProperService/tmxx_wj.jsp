<%@page import="WYBack_Stage.Bean.TB_Estate_Examine"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_ExamineDao"%>
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
	String xq_id=S_string.formatString(request.getParameter("xq_id"));
	 //new  ArrayList();
	 List<TB_Estate_Examine> list2 = new ArrayList<TB_Estate_Examine>();
	 if(xq_id != "" && !xq_id.equals("")){
		 list2 = new TB_Estate_ExamineDao().select_Examine(xq_id);
	 }
	 
	//List<TB_Estate_Build> list2 = new MyTB_Estate_Order().select_louyu(ts_id, Es_id);
%>
		<select id="Ex_id" name="Ex_id" class="input_txt" 
		onchange="select_Extopic(this,'Timu','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_tm.jsp"%>','<%=ts_id %>')"
		>
				<%
				if(list2.size()>0){
				%>
				<option value="">--请选择问卷--</option>
				<%
					TB_Estate_Examine examine=null;
					for(int i=0;i<list2.size();i++){
						examine=(TB_Estate_Examine)list2.get(i);
				%>
				<option value="<%=examine.getEx_id()%>"><%=examine.getTitle().length() > 7?examine.getTitle().substring(0, 7) + "...":examine.getTitle()%></option>
				<%
					}
				}else{
				%>
				<option value="">--请选择问卷--</option>
				<%} %>
		</select>
