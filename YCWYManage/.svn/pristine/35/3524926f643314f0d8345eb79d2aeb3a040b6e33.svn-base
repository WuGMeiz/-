<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
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
  	String ts_id=session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
%>
<div class="tables">
<table class="show_table">
	<tr>	
		<td align="left" style="color: red;font-size: 12px; white-space: normal;" colspan="6">
		&emsp;提示：现金支付对账可查询当天数据；网上支付对账由于建行的记账规则，所以最早只能查询到前一天的数据。商户的建行直连信息设置以及网络因素会影响对账的准确性，网上支付对账信息仅供参考，如有问题请联系建行热线。
		</td>	
	</tr>	
	<tr>
	    <td align="right" width="20%">查询小区：</td>
		<td align="left" width="15%">
			<select id="Es_id"  name="Es_id" class="input_txt" onchange="select_sfy(this,'fzrid','<%=request.getContextPath()+"/YCWYPage/BackPage/tstion/duizhang_select_fzr.jsp"%>','<%=ts_id %>',);" >
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
		<td align="right" width="5%">收费员:</td> 
		  <td align="left" width="15%">
			<div id="fzrid">
				<select id="sfy" name="sfy" class="input_txt">
					<option value="">请选择收费员</option>
				</select>
			</div>
		 </td>
		<td align="right" width="50%">
			起始时间：
			<input type="text" class="input_txt" id="timesk" name="timesk" style="width: 160px;" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
			结束时间：<input type="text" class="input_txt" id="timesj" name="timesj" style="width: 160px;" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly">
		</td>
		<td align="left" width="25%">
			&emsp;<input type="button" name="xinjjs" class="submit_input" value=" 查 询 " onclick="select_duizhang('show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/tstion/duizhang_show.jsp"%>');" />	
		</td>	
	</tr>

</table>
<hr width="100%" style="margin-bottom: 10px;margin-top: 10px;color: #999999;text-align: center;"/>

<div id="show_update">

</div>	
</div>
<!-- 查看修改读取层 -->  
<div id="div_app_show" class="gxg_tcdiv">
</div>	
<!-- 查看修改读取层 -->  
<div id="div_edit_app_show" class="gxg_tcdiv">
</div>	
<%
}
%>