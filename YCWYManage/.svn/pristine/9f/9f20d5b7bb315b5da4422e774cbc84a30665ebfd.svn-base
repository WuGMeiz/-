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
//String parID=request.getParameter("parID");
//TB_SEV_ORG sev=new SystemDao().getTB_SEV_ORG(parID);
String org_id=session.getAttribute("ORG_ID").toString();
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_app_show');">×</b></div>
<div class="new-layer">	
<div align="center" class="gxg_tcdiv_nr">
<br>
		 <form name="form1"> 
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td width="12%" align="right">小区名称：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 85%;"  class="input_txt" id="EsName" name="EsName" maxlength="15" /><span style="color: red">*</span>
					</td>
					<td width="12%" align="right">小区负责人：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 100%;"  class="input_txt" id="EsHead" name="EsHead" maxlength="15" />
					</td>
					<td width="12%" align="right">小区联系人：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 100%;"  class="input_txt" id="EsContact" name="EsContact" maxlength="15" />
					</td>
					<td width="12%" align="right">小区联系电话：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 95%;"  class="input_txt" id="EsPhone" name="EsPhone" maxlength="11" />
					</td>
				</tr>
				<tr height="45">
					<td width="12%" align="right">小区地址：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 85%;"  class="input_txt" id="EsAddress" name="EsAddress" maxlength="25" />
					</td>
					<td width="12%" align="right">楼宇数量（幢）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 100%;"  class="input_txt" id="build_Number" name="build_Number" maxlength="5" />
					</td>
					<td width="12%" align="right">房屋数量（户）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 100%;"  class="input_txt" id="House_Number" name="House_Number" maxlength="5" />
					</td>
					<td width="12%" align="right">建筑面积（㎡）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 95%;"  class="input_txt" id="BuildArea" name="BuildArea" maxlength="8" />
					</td>
				</tr>
				<tr height="45">
					<td width="12%" align="right">占地面积（㎡）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 85%;"  class="input_txt" id="FloorArea" name="FloorArea" maxlength="8" />
					</td>
					<td width="12%" align="right">绿化面积（㎡）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 100%;"  class="input_txt" id="GreenArea" name="GreenArea" maxlength="8" />
					</td>
					<td width="12%" align="right">容积率（%）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 100%;"  class="input_txt" id="VolumeRate" name="VolumeRate" maxlength="5" />
					</td>
					<td width="12%" align="right">备注：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" style="width: 95%;"  class="input_txt" id="remark" name="remark" maxlength="25" />
					</td>
				</tr>
				<tr height="100">
					<td align="center" colspan="8">
						<input type="button" class="submit_input"  onfocus="this.blur();" name="tianjia" value=" 添加 " onclick="TBVillage_add('<%=divid %>','<%=request.getContextPath()%>/TB_Estate_Village_SERVLET?args=1','<%=request.getContextPath() %>/YCWYPage/BackPage/quarters/xqadd.jsp');" />
						&emsp;<input type="reset" class="submit_input" value="重置">
					
					</td>
				</tr>
			</table>
		</form>
</div>
</div>
<%
}
%>
