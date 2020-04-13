<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_House"%>
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
    String ts_id = session.getAttribute("U_ID").toString();
	String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
	List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
%> 
<div class="content">
	<div class="containtNav">
		<div class="addBox">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td height="40" width="5%" align="right">小区:</td>
					<td height="40" width="15%">
					<select id="Es_id" class="input_txt" name="Es_id" onchange="select_louyu(this,'louyuid','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_ly.jsp"%>','<%=ts_id %>')" >
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
					</select>
					</td>
					<td height="40"  width="5%" align="right">楼宇:
					</td>
					<td height="40"  width="15%">
						<div id="louyuid">
							<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/charge_select_ly.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td>
					
					<td height="40"  width="5%" align="right">单元：&nbsp;</td>
					<td height="40"  width="15%">
						<div id="dy">
							<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/charge_select_dy.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					 </td>
					 <td align="right">房屋编号:</td>
						<td align="left">
							<input type="text" style="width: 100%;"  class="input_txt" id="EhNumber" name="EhNumber" maxlength="25" />
						</td>
						
					 </tr>
					 <tr>
					
						<td align="right">业主姓名:</td>
						<td align="left">
							<input type="text" style="width: 100%;"  class="input_txt" id="OwnerName" name="OwnerName" maxlength="25" />
						</td>
					<td colspan="5" align="right">
                    	<input class="submit_input" name="chaxun" value=" 查 询 " onclick="select_TB_House('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwmess_update.jsp"%>');return false;" type="button" />
                    </td>
                    <td colspan="4" align="center">
						 <% 
		                  if(new MyTB_SEV_ORG_DAO().getorg_level(session.getAttribute("ORG_ID").toString(),ts_id)<2)
		                   {
		                       %>	
		                   <input type="button" class="submit_input" onfocus="this.blur();" name="xinjjs" value="新建房屋" style="margin-left: 10px" onclick="new_show_see_tanchu_div('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwmess_add_see.jsp"%>');return false;"/>	
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
            <div id="show_update"></div>
			
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