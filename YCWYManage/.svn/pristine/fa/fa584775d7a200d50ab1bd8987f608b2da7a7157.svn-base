<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />

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
    String ts_id=session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	String divid=request.getParameter("divid");
%>
<style>
* {
	box-sizing: border-box;
}

.hidden-check {
	display: none;
}

input[type=checkbox].hidden-check+label.valbtn {
	display: inline-block;
	text-align: center;
	line-height: 40px;
	float: left;
	width: 23%;
	height: 43px;
	margin-right: 1%;
	border-radius: 5px;
	border: 2px solid #cccccc;
}

.valbtn input[type=text] {
	border: none;
	width: 100%;
	height: 40px;
	line-height: 40px;
	background: transparent;
	outline: none;
}

input[type=checkbox].hidden-check:checked+label {
	border: 2px solid #4a87df;
	color: #4a87df;
}

input[type=checkbox].hidden-check:checked+label input {
	color: #4a87df;
}
</style>

<div class="div_app_showTite"><b title="关闭" onclick="close_tanchu_div('div_app_show');">×</b></div>
 		<br /><br />
		<form name="form1" style="display:block;">
			<table style="display:block; width:800px; margin:0 auto;">
				<tr  height="45">
					<td align="right" width="150">小区名称：<font style="color: red;">*</font></td>
					<td width="200">
						<select id="xiaoqu" name="xiaoqu" class="input_txt" onchange="selHouType(this,'houTypeid','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/Housetype_select.jsp"%>')">
							<option value="0">请选择单位</option>
							<% 
								List list=new ChangeDao().getXiaoqu(ts_id);
									for(int i=0;i<list.size();i++)
										{
										TB_Estate_Village tev=(TB_Estate_Village)list.get(i);
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
								
							%>
						</select>
					</td>
					<td align="right" width="150">收费方式：<font style="color: red;">*</font></td>
					<td width="200">
					   <select class="input_txt" id="sftype" name="sftype">
							<option value="0">请选择收费方式</option>
							<option value="6" selected="selected">按立方计算</option>
							<option value="8">按固定金额计算</option>
						</select>
						<input class="input_txt" id="sfname" name="sfname" type="hidden" maxlength="100" value="水费"/>
					</td>
				</tr>
				
				<tr height="45">
				    <td align="right" width="150">房屋类型：<font style="color: red;">*</font></td>
					<td width="200">
					   <div id="houTypeid">
							<select id="EhType" name="EhType" class="input_txt">
								<option value="">请选择房屋类型</option>
							</select>
						</div>
					</td>
					<td align="right">单价(元)：<font style="color: red;">*</font></td>
					<td>
						<input class="input_txt" id="price" name="price" type="text" />
					</td>
					
				</tr>
				<tr height="45">
				  <td align="right">限购次数/月：</td>
					<td>
						<input class="input_txt" id="LimitNumber" name="LimitNumber" type="text" />
					</td>
				   <td align="right">购买金额：<font style="color: red;">*</font></td>
					<td>
					    <input type="checkbox"   class="hidden-check" id="fir" name="ch"  value="50"/>
					    <label for="fir" class="valbtn">50</label>
					    <input type="checkbox"    class="hidden-check" id="sec" name="ch" value="100" />
					    <label for="sec" class="valbtn">100</label>
					    <input type="checkbox"   class="hidden-check" id="thi" name="ch"  value="200"/>
					    <label for="thi" class="valbtn">200</label>
					    <input type="checkbox"   class="hidden-check"  />
					    <label for="fou" class="valbtn">
					   		<input type="text" id="Limited" name="Limited" placeholder="其他">
					    </label>
						 
					</td>
				</tr>
				
				<tr height="45">
					<td align="right">自助缴费限制：</td>
					<td >
						<input type="radio" id="gdxz1" name="gdxz" value="1" >是
						<input type="radio" id="gdxz2" name="gdxz" value="0" checked="checked" >否
					</td>
					<td align="right">是否预交费：</td>
					<td  >
						<input type="radio" id="isyj1" name="isyj" value="1" >是
						<input type="radio" id="isyj2" name="isyj" value="0" checked="checked" >否
					</td>
				</tr>
				<!-- <tr height="45">
					<td align="right">滞纳金天数：</td>
					<td>
						<input class="input_txt" id="znjday" name="znjday" type="text" />
					</td>
					<td align="right">滞纳金比例：</td>
					<td>
						<input class="input_txt" id="zjnbl" name="zjnbl" type="text" />
					</td>
				</tr> -->
				<tr height="45">
					<td align="right">房屋面积标准(平米)：</td>
					<td>
						<input class="input_txt" id="HouseStandard" name="HouseStandard" type="text" maxlength="5" />
					</td>
					
					<td align="right">低于标准最高限额：</td>
					<td>
						<input class="input_txt" id="BelowStandard" name="BelowStandard" type="text" maxlength="3" />
					</td>
				</tr>
				<tr height="45">
					<td align="right">超过标准最高限额：</td>
					<td>
						<input class="input_txt" id="AboveStandard" name="AboveStandard" type="text" maxlength="3" />
					</td>
					<td align="right">备注：</td>
					<td>
						<input class="input_txt" id="remark" name="remark" type="text" maxlength="100" />
					</td>
				</tr>
				<tr height="45">
					<td  align="center" colspan="8">
					
						<input type="button" onfocus="this.blur();" class="submit_input" 
						onclick="TB_WaterItemadd( '<%=request.getContextPath()%>/TB_Itemadd?arg=5','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/Water_item.jsp"%>');" value="提交">					
						&emsp;
						<input type="reset" class="submit_input" value="重置">
					</td>
				</tr>
			</table>
		</form>
<%
}
%>
