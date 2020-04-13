<%@page import="WYBack_Stage.Dao.TB_Estate_Extopic_Dao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Examine"%>
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
    String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
	List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
	//List<TB_Estate_Examine> listAll = new TB_Estate_Extopic_Dao().getTB_Estate_ExamineByTsId(ts_id);
%>
<div class="content">
	<div class="containtNav">
		<div class="addBox">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td height="40" align="right">小区:&nbsp;</td>
					<td height="40" align="left" width="230">
						<select id="Es_id" name="Es_id" class="input_txt" onchange="getWenJuan('Es_id','wenjuanType','wenjuan','<%=request.getContextPath()%>/GetWenJuanServlet')" >
							<option value="0">--请选择小区--</option>
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
					<td height="40" align="right">问卷类型:&nbsp;</td>
					<td align="left" width="20%" 
						onchange="getWenJuan('Es_id','wenjuanType','wenjuan','<%=request.getContextPath()%>/GetWenJuanServlet')">
						<select id="wenjuanType" name="wenjuanType" class="input_txt">
								<option value="0">--请选择问卷类型--</option>
								<option value="1">满意度调查问卷</option>
								<option value="2">投票</option>
						</select>
					</td>
					
					
			     	<td  align="right" width="10%">问卷表:&nbsp;</td>
			     	<td align="left" width="20%">
						<select id="wenjuan" name="wenjuan" class="input_txt">
								<option>--请选择问卷表--</option>
						</select>
					</td>  
					<td colspan="2" align="left" width="20%">
                    	<input class="submit_input" name="chaxun" value=" 查 询 " onclick="select_TB_Extopic('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/wjtm_update.jsp"%>');return false;" type="button" />
                    	<input type="button" class="submit_input" onfocus="this.blur();" name="xinjjs" value="新建题目" style="margin-left: 10px" onclick="new_show_see_tanchu_div('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/wjtm_see.jsp"%>');return false;"/>
                    </td>
			</tr>
			<%-- <tr>
				<td  align="right" width="20%" colspan="2">&nbsp;</td>
				<td align="center" width="20%">
					<input type="button" class="submit_input" onfocus="this.blur();" name="xinjjs" value="新建题目" style="margin-left: 10px" onclick="new_show_see_tanchu_div('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/wjtm_see.jsp"%>');return false;"/>
				</td>	
			</tr> --%>
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