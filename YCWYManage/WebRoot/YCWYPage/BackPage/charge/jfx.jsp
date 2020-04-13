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
  </head>

  <body>
	
	<% 
			List<TB_Estate_item> listItem=new ChangeDao().getTBItemNoyj(ts_id,Es_id);//Es_id
				for(int i=0;i<listItem.size();i++)
					{
					TB_Estate_item tei=(TB_Estate_item)listItem.get(i);
					/* String type="";
				  	if(tei.getItemType().equals("1")){type="按使用面积计算";}
				  	else if(tei.getItemType().equals("2")){type="按建筑面积计算";}
				  	else if(tei.getItemType().equals("3")){type="按供暖面积计算";}
				  	else if(tei.getItemType().equals("4")){type="按月计算";}
				  	else if(tei.getItemType().equals("5")){type="按季度计算";}
				  	else if(tei.getItemType().equals("6")){type="按立方计算";}
				  	else if(tei.getItemType().equals("7")){type="按度计算";}
				  	else if(tei.getItemType().equals("8")){type="按固定金额计算";}
				  	else if(tei.getItemType().equals("9")){type="按每月单价计算";} */
		%>
		<table>
		<tr height="25">
		<td>
		<input type="checkbox" id="itembox_<%=tei %>" name="itembox" value="<%=tei.getEi_id() %>#<%=tei.getItemName()%>" /> <%=tei.getItemName() %>
		  <%-- 	<%=type %>&nbsp;&nbsp;&nbsp;单价&nbsp;:&nbsp;<%=tei.getPrice() %>(元) --%>
		  	
		</td>
		  	</tr></table>
		<% 
					}
			
		%>

  </body>

<%
}
%>