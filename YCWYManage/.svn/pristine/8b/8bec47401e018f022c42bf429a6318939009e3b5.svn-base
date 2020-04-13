<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_ORG_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Extopic"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_ExoptionDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>


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
    String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
	//List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
	//List<TB_Estate_Extopic> list = new TB_Estate_ExoptionDao().select_Extopic();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
%> 

			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					
					<td align="right" width="80">小区名称:</td>
					<td align="left"  >
						<select id="Es_id" style="width: 100%;"    name="Es_id" class="input_txt" 
						onchange="select_Examine(this,'Wenjuan','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_wj.jsp"%>','<%=ts_id %>')"
						>
							<option value="">--请选择小区--</option>
								<%
								if(list.size()>0){
									TB_Estate_Village village=null;
									for(int i=0;i<list.size();i++){
										village=(TB_Estate_Village)list.get(i);
										if(!yznr.equals("")){
											
											if(yznr.contains(village.getEs_id()+"")){

						%>
							<option value="<%=village.getEs_id() %>" ><%=village.getEsName()%></option>
							<% 
											}
										}else{
								%>
								<option value="<%=village.getEs_id() %>" ><%=village.getEsName()%></option>
								<% 
										}
									}
								}
								 %>
						</select>
					</td>
					<td align="right" width="80">问卷名称:</td>
					<td align="left">
						<div id="Wenjuan">
							<jsp:include flush="ture" page="tmxx_wj.jsp">
								<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td>
					<td align="right" width="80">题目名称:</td>
					<td align="left">
						<div id="Timu">
							<jsp:include flush="ture" page="tmxx_tm.jsp">
								<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td>
					<td align="center">
                    	<input class="submit_input" name="chaxun" value=" 查 询 " onclick="selExoption('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_update.jsp"%>');return false;" type="button" />
                    	<input class="submit_input" name="chaxun" value=" 添加 " onclick="insert_Exoption('div_user_show','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_insert.jsp"%>');return false;" type="button" />
                    </td>
                  
			</tr>
			</table>
			<hr width="100%" style="margin-bottom: 10px;margin-top: 10px;color: #999999;text-align: center;"/>
            <div id="show_update">
            	<jsp:include page="tmxx_update.jsp" flush="true"></jsp:include>
			</div>
	
<div id="div_user_show" class="gxg_tcdiv"></div>
<!-- 查看修改读取层 -->
<div id="div_edit_user_show" class="gxg_tcdiv"></div>
 <%
}
%> 