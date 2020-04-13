<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<%
if (session.getAttribute("USER_ID") == null) 
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
String divid=request.getParameter("divid");
String tu_id = session.getAttribute("TU_ID").toString();
String ts_id = new MyTB_SEV_USER().getTs_id(tu_id); 
String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_app_show');">×</b></div>
<div class="new-layer">	
<div align="center" class="gxg_tcdiv_nr">
<br>
		 <form name="form1" > 
			<table border="0" class="nowrap dyTab" width="100%" cellpadding="0" cellspacing="0">
                  <tbody>
                  <tr height="45">
                      <td align="right">小区信息:</td>
                      <td height="40">
                          <select id="es_id3" name="es_id3" class="input_txt" onchange="select_louyu3(this,'louyuid3','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_ly3.jsp"%>','<%=ts_id %>')">
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
                          <font style="color: red;">*</font>
                      </td>
                      <td align="right">楼宇:</td>
                      <td>
                          <div id="louyuid3">
                              <select class="input_txt" name="Bu_id3" id="Bu_id3">
                                  <option value="">请选择楼宇</option>
                              </select>
                              <font style="color: red;">*</font>
                          </div>
                          
                      </td>
                  </tr>
                  <tr>
                      <td align="right">单元编号:</td>
                      <td colspan="3" align="left">
                          <input type="text" style="width: 23%;" class="input_txt" id="qianzhui" placeholder="前缀*" name="qianzhui" maxlength="8">
                          <input type="text" style="width: 23%;" class="input_txt" id="qsBuName" placeholder="起始编号*" name="qsBuName" maxlength="3">
                          <span style="color: black">-</span>
                          <input type="text" style="width: 23%;" class="input_txt" id="zzBuName" placeholder="终止编号" name="zzBuName" maxlength="3">
                          <input type="text" style="width: 23%;" class="input_txt" id="houzhui" placeholder="后缀*" name="houzhui" maxlength="6" value="#">
                          <font style="color: red;">*</font>
                      </td>
                  </tr>
                  <tr height="45">
                      <td align="right">备注:</td>
                      <td colspan="3" align="left">
                          <input type="text" class="input_txt" id="remark" name="remark" maxlength="25" />
                      </td>
                  </tr>
                  <tr height="100">
                      <td align="center" colspan="8">
                          <input type="button" class="submit_input" onfocus="this.blur();" name="tianjia" value=" 添加 " onclick="TBUnit_add('<%=divid %>','<%=request.getContextPath()%>/TB_Estate_UnitServlet?args=1','<%=request.getContextPath() %>/YCWYPage/BackPage/quarters/dyadd.jsp');">
                          &emsp;<input type="reset" class="submit_input" value="重置">
                      </td>
                  </tr>
                  </tbody></table>
		</form>
		<div style="color:red; font-size:14px; line-height:20px; text-align:left; padding-left:30px;">
		温馨提示：
		单元号应大于0<br/>
		楼号书写规则 ： 前缀、起始编号、后缀不能为空，如批量添加单元,输入 前缀+起始编号+终止编号+后缀，<br/>
		例 第+1+6+#，批量新增前缀为‘第’，后缀为‘#’的1到6号单元信息<br/>
		如果单独添加只需填写 前缀+起始编号+后缀,后缀可改为“单元”<br/>
		例 第+1+#，批量新增前缀为‘第’，后缀为‘#’的1号单元信息
		</div>
</div>
</div>
<%
}
%>
