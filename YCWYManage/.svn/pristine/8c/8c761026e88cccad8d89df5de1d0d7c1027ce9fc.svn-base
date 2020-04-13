<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
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
	 String ts_id= session.getAttribute("U_ID").toString();



%>
<div class="content">
	<div class="containtNav">
			<table class="show_table">
				<tr >
					<td align="left" width="100%">
					<input type="button" class="submit_input" onfocus="this.blur();" name="xinjjs" value="新增收费项" style="margin-left: 10px" onclick="new_show_see_tanchu_div1('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/charge_item_add.jsp"%>');return false;"/>	
					</td>
				</tr>	
			</table>  
			<div id="show_update">
			<jsp:include page="charge_item_show.jsp" flush="true"></jsp:include>
			</div> 
		</div>
	</div>	
			<!-- 查看修改读取层 -->  
			<div id="div_app_show" class="gxg_tcdiv" style="height:150%;display: none;">
			</div>	
			<!-- 查看修改读取层 -->  
			<div id="div_edit_app_show" class="gxg_tcdiv" style="height:150%;display: none;">
			</div>	

<%
 }
%>