<%@page import="WYBack_Stage.Bean.TB_Estate_Unit"%>
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
	String ly_id=S_string.formatString(request.getParameter("ly_id"));
	String sign=S_string.formatString(request.getParameter("sign"));

	List<TB_Estate_Unit> list2 = new MyTB_Estate_Order().select_unite(ts_id, ly_id);
%>
<%
	if(list2.size()>0){
		if("1".equals(sign)){
%>
<td align="right">选择单元：&nbsp;</td>
<td>
<%			
		}
%>
	<select id="Un_id" name="Un_id"  class="input_txt">
		<option value="">--请选择单元--</option>
<%
	TB_Estate_Unit unite=null;
	for(int i=0;i<list2.size();i++){
		unite=(TB_Estate_Unit)list2.get(i);
%>
		<option value="<%=unite.getUn_id()%>"><%=unite.getUnName()%></option>
<%
	}
%>
	</select>
<%
	if("1".equals(sign)){
%>
</td>
<%	
	} 
}else{
	if("2".equals(sign)){
%>
	<select id="Un_id" name="Un_id"  class="input_txt">
		<option value="">--请选择单元--</option>
	</select>
<%		
	}else{
%>
	<input type="hidden" name="Un_id" id="Un_id" value="">
<%		
	}
}
%>

