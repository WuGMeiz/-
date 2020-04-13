<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_item"%>
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
%>
 <%
 	String ts_id=session.getAttribute("U_ID").toString();
	
	 String Es_id=S_string.formatString(request.getParameter("Es_id"));
	 
 %>
<% 
		List<TB_Estate_item> listItem=new ChangeDao().getTBItem(ts_id,Es_id);//Es_id
%>
	<select id="itemName" name="itemName" class="input_txt" >
			<option value="">--请选择收费项--</option>
			<%
				if(listItem.size()>0){
					TB_Estate_item tei = null;
					for(int i=0;i<listItem.size();i++){
					tei=(TB_Estate_item)listItem.get(i);
					String type="";
				  /*if(tei.getItemType().equals("1")){type="按使用面积计算";}
				  	else if(tei.getItemType().equals("2")){type="按建筑面积计算";}
				  	else if(tei.getItemType().equals("3")){type="按供暖面积计算";}
				  	else if(tei.getItemType().equals("4")){type="按月计算";}
				  	else if(tei.getItemType().equals("5")){type="按季度计算";}
				  	else if(tei.getItemType().equals("6")){type="按立方计算";}
				  	else if(tei.getItemType().equals("7")){type="按度计算";}
				  	else if(tei.getItemType().equals("8")){type="按固定金额计算";} */
				%>
				<option value="<%=tei.getEi_id()%>"><%=tei.getItemName()%></option>
				<%
					}
				}
				%>
	</select>

<%
}
%>