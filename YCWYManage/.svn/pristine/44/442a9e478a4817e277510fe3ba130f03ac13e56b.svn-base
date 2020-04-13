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
 		<font style="color: red;">温馨提示：电梯费按照不同楼层标准计算需输入各个楼层区间以及对应单价；若不分楼层则需要输入统一单价。</font>
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
							<option value="13" selected="selected">按楼层数建筑面积每月一条订单</option>
							<option value="2">按建筑面积计算</option>
							<option value="4">按月计算</option>
							<option value="5">按季度计算</option>
							<option value="8">按固定金额计算</option>
						</select>
						<input class="input_txt" id="sfname" name="sfname" type="hidden" maxlength="100" value="电梯费"/>
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
				   <td align="right">统一单价(元)：</td>
					<td>
						<input class="input_txt" id="price" name="price" type="text" />
					</td>
				
				</tr>
				<tr height="45">
				  <td align="right">楼层区间1：</td>
					<td>
						<input class="input_txt" style="width: 47%" id="se_qz1" name="se_qz1" type="text" /> -
						<input class="input_txt" style="width: 47%" id="se_hz1" name="se_hz1" type="text" />
					</td> 
					<td align="right">区间1单价(元)：</td>
					<td>
						<input class="input_txt" id="Seprice1" name="Seprice1" type="text" />
					</td>	
				</tr>
				<tr height="45">
				  <td align="right">楼层区间2：</td>
					<td>
						<input class="input_txt" style="width: 47%" id="se_qz2" name="se_qz2" type="text" /> -
						<input class="input_txt" style="width: 47%" id="se_hz2" name="se_hz2" type="text" />
					</td> 
					<td align="right">区间2单价(元)：</td>
					<td>
						<input class="input_txt" id="Seprice2" name="Seprice2" type="text" />
					</td>	
				</tr>
				<tr height="45">
				  <td align="right">楼层区间3：</td>
					<td>
						<input class="input_txt" style="width: 47%" id="se_qz3" name="se_qz3" type="text" /> -
						<input class="input_txt" style="width: 47%" id="se_hz3" name="se_hz3" type="text" />
					</td> 
					<td align="right">区间3单价(元)：</td>
					<td>
						<input class="input_txt" id="Seprice3" name="Seprice3" type="text" />
					</td>	
				</tr>
				<tr height="45">
				  <td align="right">楼层区间4：</td>
					<td>
						<input class="input_txt" style="width: 47%" id="se_qz4" name="se_qz4" type="text" /> -
						<input class="input_txt" style="width: 47%" id="se_hz4" name="se_hz4" type="text" />
					</td> 
					<td align="right">区间4单价(元)：</td>
					<td>
						<input class="input_txt" id="Seprice4" name="Seprice4" type="text" />
					</td>	
				</tr>
				<tr height="45">
				  <td align="right">楼层区间5：</td>
					<td>
						<input class="input_txt" style="width: 47%" id="se_qz5" name="se_qz5" type="text" /> -
						<input class="input_txt" style="width: 47%" id="se_hz5" name="se_hz5" type="text" />
					</td> 
					<td align="right">区间5单价(元)：</td>
					<td>
						<input class="input_txt" id="Seprice5" name="Seprice5" type="text" />
					</td>	
				</tr>
				<tr height="45">
				  <td align="right">楼层区间6：</td>
					<td>
						<input class="input_txt" style="width: 47%" id="se_qz6" name="se_qz6" type="text" /> -
						<input class="input_txt" style="width: 47%" id="se_hz6" name="se_hz6" type="text" />
					</td> 
					<td align="right">区间6单价(元)：</td>
					<td>
						<input class="input_txt" id="Seprice6" name="Seprice6" type="text" />
					</td>	
				</tr>
				<tr height="45">
					<td align="right">滞纳金天数：</td>
					<td>
						<input class="input_txt" id="znjday" name="znjday" type="text" />
					</td>
					<td align="right">滞纳金比例：</td>
					<td>
						<input class="input_txt" id="zjnbl" name="zjnbl" type="text" />
					</td>
				</tr>
				<tr height="45">
					<td  align="center" colspan="8">
					
						<input type="button" onfocus="this.blur();" class="submit_input" 
						onclick="TB_Item_DianTiadd('<%=request.getContextPath()%>/TB_Itemadd?arg=3','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/DianTi_item.jsp"%>');" value="提交">					
						&emsp;
						<input type="reset" class="submit_input" value="重置">
					</td>
				</tr>
			</table>
		</form>
<%
}
%>
