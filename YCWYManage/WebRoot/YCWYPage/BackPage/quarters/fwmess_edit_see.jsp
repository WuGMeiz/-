<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
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
<%@page import="WYBack_Stage.Bean.TB_Estate_House"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>

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
    String ts_id = session.getAttribute("U_ID").toString();
	TB_Estate_House teh =new MyTB_House_DAO().getTBHouse_Xx( parID,ts_id);
	
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_edit_app_show');">×</b></div>
	<div class="new-layer">	
	<div align="center" class="gxg_tcdiv_nr">
<br>
		<form name="form1">
		
			<table border="0" width="100%"  >
				<tr height="45">
					<td align="right" >
						小区名称<font style="color: red;">*</font>
					</td>
					<td  height="45" width="15%"> 
						<select id="es_id" name="es_id" class="input_txt" style="width: 60%;" >
					 			<option value=""><%=teh.getEsName() %></option>
					 	</select>
					</td>
					<td align="right">楼号<font style="color: red;">*</font></td>
					<td  height="45" width="15%"> 
						<select id="BuName" name="BuName" class="input_txt" style="width: 60%;" >
					 			<option value=""><%=teh.getBuName() %></option>
					 	</select>
					</td>
					<td align="right">单元<font style="color: red;">*</font></td>
				    <td  height="45" width="15%"> 
						<select id="UnName" name="UnName" class="input_txt" style="width: 60%;" >
							<option value=""><%=teh.getUnName() %></option>
					 	</select>
					</td>  
					 <td align="right">房屋编号<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="ehNumber" name="ehNumber" class="input_txt"  style="width: 60%;" value="<%=teh.getEhNumber() %>" maxlength="25"/>
					</td>
					
				</tr>
				<tr height="45">
					<td align="right">房屋名称:</td>
					<td align="left">
						<input type="text" id="EhName" name="EhName" class="input_txt"  style="width: 60%;" value="<%=teh.getEhName() %>"  maxlength="10"/>
					</td>
				    <td align="right">房屋性质:</td>
					<td align="left">
						<select id="EhNature" name="EhNature" class="input_txt hidd_fist" style="width: 60%;" >
					 			<option value="<%=teh.getEhNature() %>"><%=teh.getEhNature() %></option> 
					 			<option value="">--请选择房屋性质--</option>
					 			<option value="高层">高层</option>
					 			<option value="排屋">排屋</option>
					 			<option value="小高层">小高层</option>
					 			<option value="低层">低层</option>
					 			<option value="别墅">别墅</option>
					 	</select>
					</td>
					 <td align="right">房屋结构:</td>
					<td align="left">
						<select id="EhStru" name="EhStru" class="input_txt hidd_fist" style="width: 60%;" >
					 			<option value="<%=teh.getEhStru()%>"><%=teh.getEhStru() %></option>
					 			<option value="">--请选择房屋结构--</option>
					 			<option value="砖木结构">砖木结构</option>
					 			<option value="砖混结构">砖混结构</option>
					 			<option value="钢筋混凝土结构">钢筋混凝土结构</option>
					 			<option value="钢结构">钢结构</option>
					 	</select>
					</td>
					  <td align="right">房屋类型<font style="color: red;">*</font></td>
					<td align="left">
						<select id="EhType" name="EhType" class="input_txt hidd_fist" style="width: 60%;" >
					 	<option value="<%=teh.getEhType() %>" selected="selected"><%=teh.getHtName()%></option>
					 	<%
			 			List<TB_Estate_Housetype> housetypes=new MyTB_House_DAO().findHouseType(String.valueOf(teh.getEs_id()));
			 		     if(housetypes.size()>0){
			 		      for(TB_Estate_Housetype type:housetypes){
			 		    %>
			 		        <option value="<%=type.getHt_id()%>" ><%=type.getHtName() %></option>
			 		    <%}}else{ %>
			 		        <option value="">--暂未设置房屋类型-</option>
				 		<%} %>
					 	</select>
					</td>
					
				</tr>
				<tr height="45">
					<td align="right">楼宇朝向:</td>
						<td align="left">
							<select id="EhTurn" name="EhTurn" class="input_txt hidd_fist" style="width: 60%;" >
						 			<option value="<%=teh.getEhTurn() %>"><%=teh.getEhTurn() %></option>
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
					<td align="right">房屋现状:</td>
					<td align="left">
						<select id="EhStatus" name="EhStatus" class="input_txt hidd_fist" style="width: 60%;" >
					 			<option value="<%=teh.getEhStatus() %>"><%=teh.getEhStatus() %></option>
					 			<option value="">--请选择房屋现状--</option>
					 			<option value="自用">自用</option>
					 			<option value="租赁">租赁</option>
					 			<option value="空闲">空闲</option>
					 	</select>
					</td>
				    <td align="right">产权状况:</td>
					<td align="left">
						<select id="PropertyRight" name="PropertyRight" class="input_txt hidd_fist" style="width: 60%;" >
					 			<option value="<%=teh.getPropertyRight() %>"><%=teh.getPropertyRight() %></option>
					 			<option value="">--请选择产权状况--</option>
					 			<option value="大产权">大产权</option>
					 			<option value="小产权">小产权</option>
					 	</select>
					</td>
					<td align="right">房屋用途:</td>
					<td align="left">
						<select id="HousingUse" name="HousingUse" class="input_txt hidd_fist" style="width: 60%;" >
					 			<option value="<%=teh.getHousingUse() %>"><%=teh.getHousingUse() %></option>
					 			<option value="">--请选择房屋用途--</option>
					 			<option value="商用">商用</option>
					 			<option value="民用">民用</option>
					 	</select>
					</td>
					
				</tr>
				<tr height="45">
				<td align="right">是否交房<font style="color: red;">*</font></td>
					<td align="left">
						<select id="handIs" name="handIs" class="input_txt hidd_fist" style="width: 60%;" >
						<% if(teh.getHandIs() == 1){ %>
					 			<option value="1">已交房</option>
					 			<%}else{ %>
					 			<option value="0">未交房</option>
					 			<%} %>
					 			<option value="">--请选择是否交房--</option>
					 			<option value="1">已交房</option>
					 			<option value="0">未交房</option>
					 	</select>
					</td>
					<td align="right">楼层数<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="storer" name="storer" class="input_txt"  style="width: 60%;" value="<%=teh.getStorey() %>" maxlength="5"/>
					</td>
					
				<td align="right">常住人口数:</td>
					<td align="left">
						<input type="text" id="OftenNumber" name="OftenNumber" class="input_txt"  style="width: 60%;" value="<%=teh.getOftenNumber() %>" maxlength="5"/>
					</td>
				<td align="right">业主姓名:</td>
					<td align="left">
						<input type="text" id="OwnerName1" name="OwnerName1" class="input_txt"  style="width: 60%;" value="<%=teh.getOwnerName() %>" maxlength="10"/>
					</td>
					
					
				</tr>
				<tr height="45">
				<td align="right">业主电话:</td>
					<td align="left">
						<input type="text" id="OwnerPhone" name="OwnerPhone" class="input_txt"  style="width: 60%;" value="<%=teh.getOwnerPhone() %>" maxlength="11"/>
					</td>
					<td align="right">建筑面积<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="BuildArea1" name="BuildArea" class="input_txt"  style="width: 60%;" value="<%=teh.getBuildArea() %>" maxlength="8"/>
					</td>
					<td align="right">使用面积<font style="color: red;">*</font></td>
						<td align="left">
							<input type="text" id="UseArea" name="UseArea" class="input_txt"  style="width: 60%;" value="<%=teh.getUseArea() %>" maxlength="8"/>
						</td>
				    <td align="right">供暖面积<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="HeatingArea" name="HeatingArea" class="input_txt"  style="width: 60%;" value="<%=teh.getHeatingArea() %>"maxlength="8"/>
					</td>
					
				</tr>
				<tr height="45">
				 <td align="right">车位数:</td>
					<td align="left">
						<input type="text" id="CarNum" name="CarNum" class="input_txt"  style="width: 60%;" value="<%=teh.getCarNum() %>" maxlength="3"/>
					</td>
					  <td align="right">车位编号:</td>
					<td align="left">
						<input type="text" id="remark" name="remark" class="input_txt"  style="width: 60%;" value="<%=teh.getRemark() %>" maxlength="100"/>
					</td>
				</tr>
				
				<tr height="45">
					<td align="center" colspan="8">
						<input type="button" class="submit_input" value="提交" 
						onclick="Update_fangwu('<%=parID %>','<%=request.getContextPath()%>/TB_Estate_House_SERVLET?args=2','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwmess_update.jsp?type=1" %>','show_update','div_edit_app_show');" value="  ">					
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
