<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String shuju=S_string.formatString(request.getParameter("Ei_id"));
	shuju = new String(shuju.getBytes("iso-8859-1"),"utf-8");//防止中文出现乱码
	//System.out.println(shuju);
%>
	<input type="hidden" id="shuju" name="shuju" value="<%=shuju%>"  />
<%
	if(shuju.equals("")){
%>
	<input type="text" class="input_txt" placeholder="请输入缴费金额" name="total" id="total" value=""  style="width: 320px;"/>
<%	
	}else{
		String[] str = shuju.split("##");
		String limited = str[3];
		%>
		<input type="hidden" id="limited" name="limited" value="<%=limited%>"  />
		<input type="hidden" id="isYjLimited" name="isYjLimited" value="<%=str[5] %>"  />
		<input type="hidden" id="HouseStandard"  name="HouseStandard" value="<%=str[6]%>"/>
		<input type="hidden" id="BelowStandard"  name="BelowStandard" value="<%=str[7]%>"/>
		<input type="hidden" id="AboveStandard"  name="AboveStandard" value="<%=str[8]%>"/>
		<%
		if(limited.equals("")){
    %>
		<input type="text" class="input_txt" placeholder="请输入缴费金额" name="total" id="total" value=""  style="width: 320px;"/>
  <%		
		}else{
				String[] limited_xq = limited.split("#");
				for(int i=0;i<limited_xq.length;i++){
	%>
			    <input type="radio"  id="total<%=i %>" name="total" value="<%=limited_xq[i] %>"/><%=limited_xq[i] %>元
	  <%		
		}
      %>
       &nbsp;&nbsp;
       <input type="text" class="input_txt" placeholder="其他金额" name="totalqt" id="totalqt" value=""  style="width: 80px;"/>
 <%   }
   }
 %>