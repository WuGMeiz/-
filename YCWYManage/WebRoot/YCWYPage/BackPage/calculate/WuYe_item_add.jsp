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
 		<font style="color: red;padding-left: 60px;">
 		温馨提示：收费方式选择，<br>
 		"按建筑面积计算",计算公式为：单价*建筑面积,<br>
 		"按每月单价计算"，计算公式为：单价*建筑面积*月份（月份在生成订单时根据选择的日期系统自动计算）<br>
 		</font>
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
							<option value="2" selected="selected">按建筑面积计算</option>
							<option value="10">按建筑面积每月一条订单</option>
							<option value="9">按每月单价计算</option>
						</select>
						<input class="input_txt" id="sfname" name="sfname" type="hidden" maxlength="100" value="物业费"/>
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
				   <td align="right">备注：</td>
					<td>
						<input class="input_txt" id="remark" name="remark" type="text" maxlength="100" />
					</td>
				</tr>
				<tr height="45">
					<td  align="center" colspan="8">
					
						<input type="button" onfocus="this.blur();" class="submit_input" 
						onclick="TB_WuYeItemadd('<%=request.getContextPath()%>/TB_Itemadd?arg=1','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/WuYe_item.jsp"%>');" value="提交">					
						&emsp;
						<input type="reset" class="submit_input" value="重置">
					</td>
				</tr>
			</table>
		</form>
<%
}
%>
