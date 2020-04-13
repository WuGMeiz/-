<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_Extopic_Dao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Examine"%>
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
String tu_id = session.getAttribute("TU_ID").toString();
String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_app_show');">×</b></div>
<div class="new-layer">	
<div align="center" class="gxg_tcdiv_nr">
<br>
		 <form name="form1"> 
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr>
				<td height="40" align="right">小区:&nbsp;</td>
					<td height="40" align="left" width="230">
						<select id="Es_ida" name="Es_ida" class="input_txt" onchange="getWenJuan('Es_ida','wenjuanTypea','wenjuana','<%=request.getContextPath()%>/GetWenJuanServlet')" >
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
					<td align="left" width="15%" 
						onchange="getWenJuan('Es_ida','wenjuanTypea','wenjuana','<%=request.getContextPath()%>/GetWenJuanServlet')">
						<select id="wenjuanTypea" name="wenjuanTypea" class="input_txt">
								<option value="0">--请选择问卷类型--</option>
								<option value="1">满意度调查问卷</option>
								<option value="2">投票</option>
						</select>
					</td>
					<td width="12%" align="right">选择问卷：&nbsp;</td>
					<td width="12%" align="left">
						<select id="wenjuana" name="wenjuana" class="input_txt">
							<option value="0">--请选择问卷表--</option>
						</select>
					</td>
				</tr>
				<tr height="45">
					<td width="12%" align="right" id="togicnameTd">题目要求：&nbsp;</td>
					<td width="12%" align="left" id="togicnameFuTd">
						<input type="text" style="width: 85%;"  class="input_txt" id="TopicName" name="TopicName" maxlength="15" /><span style="color: red">*</span>
					</td>
					<td width="12%" align="right">是否图文：&nbsp;</td>
					<td width="12%" align="left">
						<select id="TopicIfTw" name="TopicIfTw" class="input_txt" onchange="wjtmchange2('togicnameTd','togicnameFuTd','togicimgTd','togicimgfuTd',this)">
						       <option value="2">文字</option>
						       <option value="1">图文</option>
						</select>
					</td>
					<td width="12%" align="right">类型：&nbsp;</td>
					<td width="12%" align="left">
						<select id="TopicType" name="TopicType" class="input_txt">
						       <option value="1">单选</option>
						       <option value="2">复选</option>
						</select>
					</td>
					
				</tr>
				<tr height="45" id="upImage">
					<td width="12%" align="right">排序：&nbsp;</td>
						<td width="12%" align="left">
							<input type="text" style="width: 95%;"  class="input_txt" id="TopicSort" name="TopicSort" maxlength="11" placeholder="数字越大排序越靠后"/>
						</td>
					
				   <td width="12%" align="right" id="togicimgTd" style="display:none">上传图片：&nbsp;</td>
                   <td width="12%" align="left" id="togicimgfuTd" style="display:none">
                         <input type="file" style="width: 95%;" id="TopicImg" maxlength="11" />
                   </td>
					
				</tr>
				<tr height="100">
					<td align="center" colspan="8">
						<input type="button" class="submit_input"  onfocus="this.blur();" name="tianjia" value=" 添加 " onclick="TBExtopic_add('<%=divid %>','<%=request.getContextPath()%>/TB_Estate_Extopic_SERVLET?args=1','<%=request.getContextPath() %>/YCWYPage/BackPage/ProperService/wjtm.jsp');" />
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
