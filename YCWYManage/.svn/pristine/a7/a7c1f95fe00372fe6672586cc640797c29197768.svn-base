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
				<tr height="45">
					
					<td height="40" width="5%" align="right">小区:</td>
					<td height="40" width="15%">
					<select id="es_id" name="es_id"  class="input_txt" onchange="select_operator(this,'fzrid','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/charge_select_fzr.jsp"%>','<%=ts_id %>');">
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
					</select><font style="color: red;">*</font>
					</td>
					<td width="12%" align="right">楼号：&nbsp;</td>
					<td colspan="3" align="center">
					    <input type="text" style="width: 23%;"  class="input_txt" id="qianzhui" placeholder="前缀*" name="qianzhui" maxlength="8" />
						<input type="text" style="width: 23%;"  class="input_txt" id="qsBuName" placeholder="起始编号*"  name="qsBuName" maxlength="3" />
						<span style="color: black">-</span>
						<input type="text" style="width: 23%;"  class="input_txt" id="zzBuName"  placeholder="终止编号"  name="zzBuName" maxlength="3" />
						<input type="text" style="width: 23%;"  class="input_txt" id="houzhui" placeholder="后缀*"  name="houzhui" maxlength="6" value="#" />
					    <font style="color: red;">*</font>
					</td>
					<td width="12%" align="right">楼宇朝向：&nbsp;</td>
					<td width="12%" align="left">
						<select id="BuTurn" name="BuTurn" class="input_txt">
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
					
				</tr>
				<tr height="45">
				<td width="12%" align="right">楼宇类别：&nbsp;</td>
					<td width="12%" align="left">
							<select id="BuType" name="BuType" class="input_txt">
					 			<option value="">--请选择楼宇类别--</option>
					 			<option value="住宅">住宅</option>
					 			<option value="公寓">公寓</option>
					 			<option value="别墅">别墅</option>
					 			<option value="写字楼">写字楼</option>
					 			<option value="商铺">商铺</option>
					 			<option value="其他">其他</option>
					 	</select>
					</td>
					<td width="12%" align="right">房屋结构：&nbsp;</td>
					<td width="12%" align="left">
						<select id="BuStru" name="BuStru" class="input_txt">
					 			<option value="">--请选择楼宇房屋结构-</option>
					 			<option value="砖木结构">砖木结构</option>
					 			<option value="砖混结构">砖混结构</option>
					 			<option value="钢筋混凝土结构">钢筋混凝土结构</option>
					 			<option value="钢结构">钢结构</option>
					    </select>
					</td>
					<td width="12%" align="right">楼宇总层数（层）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="BuNumber" name="BuNumber" maxlength="4" />
					</td>
					<td width="12%" align="right">单元数（个）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="UnitNumber" name="UnitNumber" maxlength="5" />
					</td>
				</tr>
				<tr height="45">
					<td width="12%" align="right">房屋数量（户）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="HouseNumber" name="HouseNumber" maxlength="5" />
					</td>
					<td width="12%" align="right">楼宇总面积：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="TotalArea" name="TotalArea" maxlength="8" />
					</td>
					<td width="12%" align="right">建筑面积（㎡）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="BuildArea" name="BuildArea" maxlength="8" />
					</td>
					<td width="12%" align="right">使用面积（㎡）：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="UseArea" name="UseArea" maxlength="8" />
					</td>
					
				</tr>
				<tr>
					<td width="12%" align="right">竣工日期：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text"  class="input_txt" id="FinishDate" name="FinishDate" maxlength="15" />
					</td>
				    <td width="12%" align="right">楼宇负责人：&nbsp;</td>
					<td width="12%" align="left">
					    <div id="fzrid">
							<select id="BuHead" name="BuHead" class="input_txt">
								<option value="">请选择负责人</option>
							</select>
						</div>
					</td>
					<td width="12%" align="right">备注：&nbsp;</td>
					<td width="12%" align="left">
						<input type="text" class="input_txt" id="remark" name="remark" maxlength="25" />
					</td>
				</tr>
				<tr height="100">
					<td align="center" colspan="8">
						<input type="button" class="submit_input"  onfocus="this.blur();" name="tianjia" value=" 添加 " onclick="TBBuild_add('<%=divid %>','<%=request.getContextPath()%>/TB_Estate_Build_SERVLET?args=1','<%=request.getContextPath() %>/YCWYPage/BackPage/quarters/ladd.jsp');" />
						&emsp;<input type="reset" class="submit_input" value="重置">
					
					</td>
				</tr>
			</table>
		</form>
		<div style="color:red; font-size:14px; line-height:20px; text-align:left; padding-left:30px;">
		温馨提示：
		楼号应大于0<br/>
		楼号书写规则 ： 前缀、起始编号、后缀不能为空，如批量添加楼宇,输入 前缀+起始编号+终止编号+后缀，<br/>
		例 万达+1+6+#，批量新增前缀为‘万达’，后缀为‘#’的1到6号楼宇信息<br/>
		如果单独添加只需填写 前缀+起始编号+后缀<br/>
		例 万达+1+#，批量新增前缀为‘万达’，后缀为‘#’的1号楼宇信息
		</div>
</div>
</div>
<%
}
%>
