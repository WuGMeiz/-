<%@page import="WYBack_Stage.Dao.Mete_ReadClass"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//根据选择的小区ID 反显楼宇信息 
	String xiaoquid = request.getParameter("xiaoquid"); 
	String ts_id = request.getParameter("ts_id");
	String LEVELS=session.getAttribute("LEVELS").toString();
    String tu_id = session.getAttribute("TU_ID").toString();
	// 查询楼宇信息的方法  
	List<TB_Estate_Build> list =new Mete_ReadClass().findlouyuname(xiaoquid, ts_id,tu_id,LEVELS);
%>
<div id="lou">
	<select id="louid" class="input_txt" name="louid" onchange="select_unite(this,'sl_unite','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_unite.jsp"%>','<%=ts_id %>')">
				<option value="">请选择楼宇名称</option>
<% 
	if(list.size()>0){
		for(TB_Estate_Build teb : list){
%>
				<option value=<%=teb.getBu_id() %>><%=teb.getBuName() %></option>
<%
		}
	}
%>
	</select>
</div>
