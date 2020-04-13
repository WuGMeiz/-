<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>


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
    String tu_id = session.getAttribute("TU_ID").toString();
    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
    //String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
	//List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
%> 
<div class="content">
	<div class="containtNav">
		<div class="addBox">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td height="40" align="right">小区:&nbsp;</td>
					<td height="40" align="left" width="230">
					<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/xq.jsp">
						<jsp:param value="ly" name="dividly"/>
						<jsp:param value="dy" name="dividdy" />
						<jsp:param value="<%=ts_id %>" name="ts_id"/>
					</jsp:include>
					<%-- <select id="Es_id" name="Es_id" class="input_txt" onchange="select_louyu(this,'louyuid','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_ly2.jsp"%>','<%=ts_id %>')" >
						<option value="">--请选择小区--</option>
							<%
							if(list.size()>0){
								TB_Estate_Village tev=null;
								for(int i=0;i<list.size();i++){
									tev=(TB_Estate_Village)list.get(i);
									if(!yznr.equals("")){
										
										if(yznr.contains(tev.getEs_id()+"")){

					%>
						<option value="<%=tev.getEs_id() %>" ><%=tev.getEsName()%></option>
						<% 
										}
									}else{
							%>
							<option value="<%=tev.getEs_id() %>" ><%=tev.getEsName()%></option>
							<% 
									}
						    	}
							}
							 %>
					</select> --%>
					</td>
					<td height="40" align="right">楼宇:&nbsp;
					</td>
					<td align="left" width="230" id="ly">
						
					<select id="Bu_id" name="Bu_id" class="input_txt">
						<option value="">请选择楼宇</option>
					</select>
						
					</td>
					<td height="40"   align="right">单元:&nbsp;
					</td>
					<td height="40"  width="230" id="dy">
						<select class="input_txt" id="Un_id" name="Un_id" >
							<option value="">请选择单元</option>
						</select>
					</td>
					<td align="center">
                    	<input class="submit_input" name="chaxun" value=" 查 询 " onclick="select_Unit('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/dyadd_update.jsp"%>');return false;" type="button" />
                    </td>
                    <td align="center">
						 <% 
		                  if(new MyTB_SEV_ORG_DAO().getorg_level(session.getAttribute("ORG_ID").toString(),ts_id)<2)
		                   {
		                       %>	
		                   <input type="button" class="submit_input" onfocus="this.blur();" name="xinjjs" value="新建单元" style="margin-left: 10px" onclick="new_show_see_tanchu_div('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/dyadd_see.jsp"%>');return false;"/>	
		                <% 
		                    }
		                  else
		                     {
			           out.print("&nbsp;");
		                   }
		%>
					</td>
			</tr>
			</table>
			<hr width="100%" style="margin-bottom: 10px;margin-top: 10px;color: #999999;text-align: center;"/>
            <div id="show_update">
			</div>
			
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