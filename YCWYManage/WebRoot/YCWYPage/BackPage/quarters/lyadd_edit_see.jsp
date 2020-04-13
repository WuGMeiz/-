<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_USER"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Dao.TB_Village_Dao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>


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
	String parID=request.getParameter("parID");
	
	String tu_id = session.getAttribute("TU_ID").toString();
    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
	TB_Estate_Build teb =new MyTB_Build_DAO().getTBBuild_Xx(parID);
	String es_id = new MyTB_Build_DAO().getEsid(parID,ts_id);
	String EsName = new TB_Village_Dao().getEsname(es_id,ts_id);
	
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_edit_app_show');">×</b></div>
	<div class="new-layer">	
	<div align="center" class="gxg_tcdiv_nr">
<br>
		<form name="form1">
		
			<table border="0" width="100%"  >
				<tr height="45">
					<td align="right" >
						小区名称:
					</td>
					<td  height="45" width="15%"> 
						<select id="es_id" name="es_id" class="input_txt" style="width: 80%;" >
					 			<option value=""><%=EsName %></option>
					 	</select>
					</td>
					<td align="right">楼号:</td>
					<td align="left">
						<input type="text" id="BuName" name="BuName" class="input_txt"  style="width: 60%;" value="<%=teb.getBuName() %>"  maxlength="20"/>
					</td>
				</tr>
				<tr height="45">
					<td align="right">楼宇朝向:</td>
					<td align="left">
						<select id="BuTurn" name="BuTurn" class="input_txt hidd_fist" style="width: 80%;" >
					 			<option value="<%=teb.getBuTurn() %>"><%=teb.getBuTurn() %></option>
					 			<option value="">--请选择楼宇朝向--</option>
					 			<option value="东向">东向</option>
					 			<option value="西向">西向</option>
					 			<option value="南向">南向</option>
					 			<option value="北向">北向</option>
					 			<option value="东南">东南</option>
					 			<option value="东北">东北</option>
					 			<option value="西南">西南</option>
					 			<option value="西北">西北</option>
					 			
					 	</select>
					</td>
				    <td align="right">楼宇类别:</td>
					<td align="left">
						<select id="BuType" name="BuType" class="input_txt hidd_fist" style="width: 60%;" >
					 			<option value="<%=teb.getBuType() %>"><%=teb.getBuType() %></option>
					 			<option value="">--请选择楼宇类别--</option>
					 			<option value="住宅">住宅</option>
					 			<option value="公寓">公寓</option>
					 			<option value="别墅">别墅</option>
					 			<option value="写字楼">写字楼</option>
					 			<option value="商铺">商铺</option>
					 			<option value="其他">其他</option>
					 	</select>
					</td>
				</tr>
				<tr height="45">
					<td align="right">房屋结构:</td>
					<td align="left">
						<select id="BuStru" name="BuStru" class="input_txt" style="width: 80%;" >
					 			<option value="<%=teb.getBuStru() %>"><%=teb.getBuStru() %></option>
					 			<option value="">--请选择楼宇房屋结构-</option>
					 			<option value="砖木结构">砖木结构</option>
					 			<option value="砖混结构">砖混结构</option>
					 			<option value="钢筋混凝土结构">钢筋混凝土结构</option>
					 			<option value="钢结构">钢结构</option>
					    </select>
					</td>
				    <td align="right">楼宇总层数（层）:</td>
					<td align="left">
						<input type="text" id="BuNumber" name="BuNumber" class="input_txt"  style="width: 60%;" value="<%=teb.getBuNumber() %>"  maxlength="4"/>
					</td>
				</tr>
				<tr height="45">
					<td align="right">单元数（个）:</td>
					<td align="left">
						<input type="text" id="UnitNumber" name="UnitNumber" class="input_txt"  style="width: 80%;" value="<%=teb.getUnitNumber() %>"  maxlength="5"/>
					</td>
				    <td align="right">房屋数量（户）:</td>
					<td align="left">
						<input type="text" id="HouseNumber" name="HouseNumber" class="input_txt"  style="width: 60%;" value="<%=teb.getHouseNumber() %>"  maxlength="5"/>
					</td>
				</tr>
				<tr height="45">
					<td align="right">楼宇总面积:</td>
					<td align="left">
						<input type="text" id="TotalArea" name="TotalArea" class="input_txt"  style="width: 80%;" value="<%=teb.getTotalArea() %>"  maxlength="8"/>
					</td>
				    <td align="right">建筑面积（㎡）:</td>
					<td align="left">
						<input type="text" id="BuildArea" name="BuildArea" class="input_txt"  style="width: 60%;" value="<%=teb.getBuildArea() %>"  maxlength="8"/>
					</td>
				</tr>
				<tr height="45">
					<td align="right">使用面积（㎡）:</td>
					<td align="left">
						<input type="text" id="UseArea" name="UseArea" class="input_txt"  style="width: 80%;" value="<%=teb.getUseArea() %>"  maxlength="8"/>
					</td>
				    <td align="right">竣工日期:</td>
					<td align="left">
						<input type="text" id="FinishDate" name="FinishDate" class="input_txt"  style="width: 60%;" value="<%=teb.getFinishDate() %>"  maxlength="15"/>
					</td>
				</tr>
				<tr height="45">
					<td align="right">负责人:</td>
					<td align="left">
					   <select id="BuHead" name="BuHead" class="input_txt" style="width: 80%;" >
					      <option value="">--请选择楼宇负责人-</option>
					   <%
					    List<TB_SEV_USER> list2 = new MyTB_Estate_Order().selFzr(teb.getEs_id()+"",ts_id);
			 		     if(list2.size()>0){
			 		      for(TB_SEV_USER user:list2){
			 		  %>
			 		       <option value="<%=user.getTu_id()%>" <%if(teb.getBuHead().equals(user.getTu_id()) ){%>selected="selected"<%}%> ><%=user.getUsername()%></option>
			 		    <%}}else{ %>
			 		        <option value="">--暂未设置该小区下的楼宇负责人-</option>
				 		<%} %>
					</select>
					    </select>
					</td>
				    <td align="right">备注:</td>
					<td align="left">
						<input type="text" id="remark" name="remark" class="input_txt"  style="width: 60%;" value="<%=teb.getRemark() %>"  maxlength="25"/>
					</td>
				</tr>
				<tr height="45">
					<td align="center" colspan="4">
						<input type="button" class="submit_input" value="提交" 
						onclick="Update_louyu('<%=parID %>','<%=request.getContextPath()%>/TB_Estate_Build_SERVLET?args=2','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/lyadd_update.jsp?type=1" %>','show_update','div_edit_app_show');" value="  ">					
						&emsp;<input type="reset" class="submit_input" value="重置"  value="  ">
					</td>
				</tr>
			</table>
		</form>
</div>
</div>
<%
}
%>
