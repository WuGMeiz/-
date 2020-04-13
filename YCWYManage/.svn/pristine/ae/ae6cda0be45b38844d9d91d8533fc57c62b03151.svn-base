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
					<td height="40" align="right" width="10%">小区:&nbsp;</td>
					<td height="40" align="left" width="20%">
					<select id="Es_id" name="Es_id" class="input_txt"  >
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
					<td align="right" width="10%">故障类型：</td>
		             <td align="left" width="20%">
						<select id="Type" name="Type" class="input_txt">
							   <option value="">请选择类型</option>
						       <option value="1">供水故障</option>
								<option value="2">供电故障 </option>
								<option value="3">下水堵塞</option>
								<option value="4">暖气跑水</option>
								<option value="5">房顶漏水</option>
								<option value="6">墙体裂缝</option>
								<option value="7">电梯故障</option>
								<option value="8" selected="selected">其他故障</option>
						</select>
					</td>
				 <td align="right" width="60%">
			                   起始时间：
					<input type="text" class="input_txt" id="timesk" name="timesk" style="width: 160px;" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
					结束时间：<input type="text" class="input_txt" id="timesj" name="timesj" style="width: 160px;" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
			     	</td>
					<td align="center">
                    	<input class="submit_input" name="chaxun" value=" 查 询 " onclick="selRepairInf('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/repairInfo_update.jsp"%>');return false;" type="button" />
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