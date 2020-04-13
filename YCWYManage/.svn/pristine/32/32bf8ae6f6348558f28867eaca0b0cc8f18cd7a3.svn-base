<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
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
	String ts_id=S_string.formatString(request.getParameter("ts_id"));
	String es_id=S_string.formatString(request.getParameter("Es_id"));
    String LEVELS=session.getAttribute("LEVELS").toString();
    String tu_id = session.getAttribute("TU_ID").toString();
	List<TB_Estate_Build> list2 = new MyTB_Build_DAO().select_louyu(ts_id, es_id,tu_id,LEVELS);
%>

<div id ="louyuid3">
	<select id="Bu_id3" class="input_txt" name="Bu_id3" class="input_txt">
		<option value="">--请选择楼宇--</option>
			<%
			if(list2.size()>0){
			%>
			
			<%
				TB_Estate_Build build=null;
				
				for(int i=0;i<list2.size();i++){
				
					build=(TB_Estate_Build)list2.get(i);
			%>
					<option value="<%=build.getBu_id()%>"><%=build.getBuName()%></option>
				
			<%
				}
				
			}
			%>
	</select>
</div>
