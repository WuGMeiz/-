<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>


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
    String ts_id = session.getAttribute("U_ID").toString();
	String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
	List<TB_Estate_Village> list= new MyTB_Build_DAO().select_xiaoqu(ts_id);
	String es_id = "";
%>
<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_app_show');">×</b></div>
<div class="new-layer">	
<div align="center" class="gxg_tcdiv_nr">
<br>
		 <form name="form1">
			<table border="0" class="nowrap" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td align="right">小区信息<font style="color: red;">*</font></td>
					<td height="40" width="15%">
					<select id="es_id" name="es_id" class="input_txt" onchange="select_louyu1(this,'louyuid1','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_ly1.jsp"%>','<%=ts_id %>');">
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
					<td align="right">楼宇<font style="color: red;">*</font></td>
				    <td>
						<div id="louyuid1">
							<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/charge_select_ly1.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td> 
				<td height="40"  width="5%" align="right">单元<font style="color: red;">*</font></td>
					<td height="40"  width="15%">
						<div id="dy1">
							<jsp:include flush="ture" page="/YCWYPage/BackPage/quarters/charge_select_dy1.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					 </td>
					<td align="right">房屋编号<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="ehNumber" name="ehNumber" class="input_txt" maxlength="25" />
					</td>
					
				</tr>
				<tr height="45">
					<td align="right">房屋名称:</td>
					<td align="left">
						<input type="text" id="EhName" name="EhName" class="input_txt" maxlength="10" />
					</td>
				    <td align="right">房屋性质:</td>
					<td align="left">
					
						<select id="EhNature" name="EhNature" class="input_txt" >
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
						<select id="EhStru" name="EhStru" class="input_txt">
					 			<option value="">--请选择房屋结构--</option>
					 			<option value="砖木结构">砖木结构</option>
					 			<option value="砖混结构">砖混结构</option>
					 			<option value="钢筋混凝土结构">钢筋混凝土结构</option>
					 			<option value="钢结构">钢结构</option>
					 	</select>
					</td>
					<td align="right">房屋类型<font style="color: red;">*</font></td>
					<td align="left">
					  <div id="dy2">
					  <select id="EhType" class="input_txt" name="EhType" class="input_txt">
					  <option value=''>请选择房屋类型</option>
						<option value="">暂无信息</option>
					</select>
							
					</td>
					
				</tr>
				<tr height="45">
					<td align="right">楼宇朝向:</td>
					<td align="left">
						<select id="EhTurn" name="EhTurn" class="input_txt">
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
						<select id="EhStatus" name="EhStatus" class="input_txt">
					 			<option value="">--请选择房屋现状--</option>
					 			<option value="自用">自用</option>
					 			<option value="租赁">租赁</option>
					 			<option value="空闲">空闲</option>
					 	</select>
					</td>
				    <td align="right">产权状况:</td>
					<td align="left">
						<select id="PropertyRight" name="PropertyRight" class="input_txt">
					 			<option value="">--请选择产权状况--</option>
					 			<option value="大产权">大产权</option>
					 			<option value="小产权">小产权</option>
					 	</select>
					</td>
					<td align="right">房屋用途:</td>
					<td align="left">
						<select id="HousingUse" name="HousingUse" class="input_txt">
					 			<option value="">--请选择房屋用途--</option>
					 			<option value="商用">商用</option>
					 			<option value="民用">民用</option>
					 	</select>
					</td>
					
				</tr>
			
					
				<tr height="45">
					<td align="right">是否交房<font style="color: red;">*</font></td>
					<td align="left">
						<select id="handIs" name="handIs" class="input_txt">
					 			<option value="">--请选择是否交房--</option>
					 			<option value="1">已交房</option>
					 			<option value="0">未交房</option>
					 	</select>
					</td>
					<td align="right">楼层数<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="storer" name="storer" class="input_txt" maxlength="5"/>
					</td>
					<td align="right">常住人口数:</td>
					<td align="left">
						<input type="text" id="OftenNumber" name="OftenNumber" class="input_txt" maxlength="5"/>
					</td>
			    	<td align="right">业主姓名:</td>
					<td align="left">
						<input type="text" id="ownerName" name="ownerName" class="input_txt" maxlength="10"/>
					</td>
				</tr>
				<tr height="45">
				<td align="right">业主电话:</td>
					<td align="left">
						<input type="text" id="OwnerPhone" name="OwnerPhone" class="input_txt" maxlength="11"/>
					</td>
					<td align="right">建筑面积<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="BuildArea" name="BuildArea" class="input_txt" maxlength="8"/>
					</td>
					<td align="right">使用面积<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="UseArea" name="UseArea" class="input_txt" maxlength="8"/>
					</td>
				    <td align="right">供暖面积<font style="color: red;">*</font></td>
					<td align="left">
						<input type="text" id="HeatingArea" name="HeatingArea" class="input_txt" maxlength="8"/>
					</td>
					 
				</tr>
				<tr height="45">
					 <td align="right">车位数:</td>
					<td align="left">
						<input type="text" id="CarNum" name="CarNum" class="input_txt" maxlength="2" />
					</td>
					 <td align="right">车位编号数:</td>
					<td align="left">
						<input type="text" id="remark" name="remark" class="input_txt" maxlength="16"/>
					</td>
					<!--   <td align="right">备注:</td>
					<td align="left">
						<input type="text" id="remark" name="remark" class="input_txt" maxlength="100"/>
					</td> -->
				</tr>
				<tr height="100">
					<td align="center" colspan="8">
						<input type="button" class="submit_input"  onfocus="this.blur();" name="tianjia" value=" 添加 " onclick="TBHouse_add('<%=divid %>','<%=request.getContextPath()%>/TB_Estate_House_SERVLET?args=1','<%=request.getContextPath() %>/YCWYPage/BackPage/quarters/fwmess.jsp');" />
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
