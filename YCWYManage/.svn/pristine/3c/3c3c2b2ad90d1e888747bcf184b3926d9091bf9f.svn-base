<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
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
	 String yznr = session.getAttribute("YZNR").toString();
%> 
<div class="content">
	<div class="containtNav">
		<div class="addBox">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td width="12%" align="right">小区名称：&nbsp;</td>
					<td width="12%" align="left">
					<!-- 	<input type="text" style="width: 100%;"  class="input_txt" id="esName" name="esName" maxlength="25" /> -->
						<select id="esName" name="esName" class="input_txt">
							<option value="">请选择单位</option>
							<% 
								List list=new ChangeDao().getXiaoqu(ts_id);
								if(list.size()>0){
									for(int i=0;i<list.size();i++)
										{
										TB_Estate_Village tev=(TB_Estate_Village)list.get(i);
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
					<td colspan="2" align="center">
                    	<input class="submit_input" name="chaxun" value=" 查 询 " onclick="select_TB_Village('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/xqadd_update.jsp"%>');return false;" type="button" />
                    </td>
                    <td colspan="2" align="center">
						 <% 
		                  if(new MyTB_SEV_ORG_DAO().getorg_level(session.getAttribute("ORG_ID").toString(),ts_id)==0)
		                   {
		                       %>	
		                   <input type="button" class="submit_input" onfocus="this.blur();" name="xinjjs" value="新建小区" style="margin-left: 10px" onclick="new_show_see_tanchu_div('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/xqadd_see.jsp"%>');return false;"/>	
		                <% 
		                    }
		                  else
		                     {
			           %>	
		                   <input type="button" class="submit_input"  onfocus="this.blur();" name="xinjjs" value="新建小区" style="margin-left: 10px;color: gray" onclick="cant_xinjian();"/>
		                  	
		                <% 
		                   }
		%>
					</td>
			</tr>
			</table>
			<hr width="100%" style="margin-bottom: 10px;margin-top: 10px;color: #999999;text-align: center;"/>
            <div id="show_update">
				<jsp:include page="xqadd_update.jsp" flush="true"></jsp:include>
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