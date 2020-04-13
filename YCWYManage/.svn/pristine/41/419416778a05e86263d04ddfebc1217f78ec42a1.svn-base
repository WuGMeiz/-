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
%> 
<div class="content">
	<div class="containtNav">
		<div class="addBox">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td height="40" align="right">小区:&nbsp;</td>
					<td height="40" align="left" width="230">
					<select id="Es_id" name="Es_id" class="input_txt" >
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
				   <td height="40" align="right">类别:&nbsp;</td>
					<td height="40" align="left" width="230">
					  <select id="Type" name="Type" class="input_txt" >
						<option value="">--请选择类别--</option>
	   					<option value="1">满意度调查</option>
	   					<option value="2">投票</option>
					</select>
					</td>
                     <td align="right">标题：</td>
                       <td align="left">
                      <input type="text" class="input_txt" id="title" name="title" />
                     </td>
			 </tr>
			 <tr height="45">
			        <td align="right">创建时间：</td>
			        <td align="left">
					<input type="text" class="input_txt" id="timesk" name="timesk"  onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
				   </td>
				   <td align="right">状态：</td>
                     <td height="40" align="left" width="230">
					  <select id="status" name="status" class="input_txt" >
						<option value="">--请选择状态--</option>
	   					<option value="1">启用</option>
	   					<option value="2">暂停</option>
					</select>
					</td>
					
			      <td align="center" colspan="2">
                      <input class="submit_input" name="chaxun" value=" 查 询 " onclick="selMytp('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/mytp_update.jsp"%>');return false;" type="button" />
                      &nbsp; &nbsp; &nbsp;
                      <input class="submit_input" type="button" value="添加" onclick="new_show_see_tanchu_div('div_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/mytp_add_see.jsp"%>');return false;">
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