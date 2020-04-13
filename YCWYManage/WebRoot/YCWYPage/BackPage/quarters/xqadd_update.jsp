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


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store");//HTTP 1.1
response.setHeader("Pragma","no-cache");//HTTP 1.0
response.setDateHeader ("Expires", 0);//prevents caching at the proxy server
request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
%>

<%
if(session.getAttribute("USER_ID")==null) {
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} else {
%>
<%
	
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		
		String tu_id = session.getAttribute("TU_ID").toString();
        String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
		String yznr = new MyTB_SEV_USER().getyznr(tu_id,ts_id);
		//String str = yznr.replace("|", "','");
		String esName=S_string.formatString(request.getParameter("EsName"));
	
		
		String Condition=" 1=1 and status='1'  ";
		Condition+=" and ts_id ='"+ts_id+"' ";
		if(!esName.equals("")) {
			Condition+=" and Es_id = '"+esName+"' ";
		}else{
			if(!yznr.equals("")){
		   		Condition+=" and Es_id in ("+yznr+") ";
			}
		}
		
		session.setAttribute("Condition",Condition);
		
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Village");

	//设置多表联合查询时的连接语句
	pages.setInnerj("");
	
	//分页条件
	if(session.getAttribute("Condition")!=null) {
		pages.setCondition(session.getAttribute("Condition").toString());
	}
	//每页显示多少条
	pages.setPagesize(10);
	//设置要显示哪页
	if(request.getParameter("pagenum")!=null) {
		String temp=request.getParameter("pagenum").toString();
		pages.setPagenum(Integer.parseInt(temp));
		session.setAttribute("pagenum", temp);
	} else {
		if(session.getAttribute("pagenum")!=null) {
			pages.setPagenum(Integer.parseInt(session.getAttribute("pagenum").toString()));
		}
	}
		
	List<TB_Estate_Village> list=new TB_Village_Dao().getTB_Village(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
	
%>
<div style="width:100%; overflow-x:scroll;">
<table border="0" width="160%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="38">
					<th style="width: 2%">序号</th>
					<th style="width: 8%">小区名称</th>
					<th style="width: 5%">小区负责人</th>
					<th style="width: 5%">小区联系人</th>
					<th style="width: 5%">小区联系电话</th>
					<th style="width: 12%">小区地址</th>
					<th style="width: 2%">楼宇数量（幢）</th>
					<th style="width: 2%">房屋数量（户）</th>
					<th style="width: 2%">建筑面积（㎡）</th>
					<th style="width: 2%">占地面积（㎡）</th>
					<th style="width: 2%">绿化面积（㎡）</th>
					<th style="width: 2%">容积率（%）</th>
					<th style="width: 12%">备注</th>
					<th style="width: 6%" >编辑</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_Village tev= (TB_Estate_Village) list.get(i);
				
			%>
						
				<tr height="38">
					<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
					<td align="center">
						<input  type="text" class="input_txt1" id="EsName<%=i%>" name="EsName<%=i%>" value="<%=tev.getEsName() %>"  maxlength="15"/>
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="EsHead<%=i%>" name="EsHead<%=i%>" value="<%=tev.getEsHead() %>"  maxlength="15" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="EsContact<%=i%>" name="EsContact<%=i%>" value="<%=tev.getEsContact() %>"  maxlength="15" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="EsPhone<%=i%>" name="EsPhone<%=i%>" value="<%=tev.getEsPhone() %>"  maxlength="11" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="EsAddress<%=i%>" name="EsAddress<%=i%>" value="<%=tev.getEsAddress() %>"  maxlength="25" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="build_Number<%=i%>" name="build_Number<%=i%>" value="<%=tev.getBuild_Number() %>"  maxlength="5" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="House_Number<%=i%>" name="House_Number<%=i%>" value="<%=tev.getHouse_Number() %>"  maxlength="5" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="BuildArea<%=i%>" name="BuildArea<%=i%>" value="<%=tev.getBuildArea() %>"  maxlength="8" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="FloorArea<%=i%>" name="FloorArea<%=i%>" value="<%=tev.getFloorArea() %>"  maxlength="8" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="GreenArea<%=i%>" name="GreenArea<%=i%>" value="<%=tev.getGreenArea() %>"  maxlength="8" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="VolumeRate<%=i%>" name="VolumeRate<%=i%>" value="<%=tev.getVolumeRate() %>"  maxlength="5" />
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="remark<%=i%>" name="remark<%=i%>" value="<%=tev.getRemark() %>"  maxlength="25" />
					</td>
					<td align="center">
						<input width="45%" type="button" name="xiu<%=i%>" value=" 修改 "
							onclick="TBVillage_update('<%=tev.getEs_id() %>','<%=i%>','<%=request.getContextPath()%>/TB_Estate_Village_SERVLET?args=2','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/xqadd_update.jsp?type=1"%>')" />
					  <input width="45%" type="button" onfocus="this.blur();" name="shan<%=i%>" value="删除"  
					   onclick="TBVillage_delete('<%=tev.getEs_id()%>','<%=i%>','<%=request.getContextPath()%>/TB_Estate_Village_SERVLET?args=3','show_update','<%=request.getContextPath()
									+ "/YCWYPage/BackPage/quarters/xqadd_update.jsp?type=1"%>');" />
					</td>
				</tr>
				<%
				}}else{ %>
				<tr height="38">
				<td colspan="4" align="center">暂无内容</td>
				</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/quarters/xqadd_update.jsp";
%>
	</tbody>
</table>
</div>
<div class="pages">
	<jsp:include page="/YCWYPage/BackPage/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="show_update"/>
	</jsp:include>
</div>
<div class="clear"></div>
<div id="div_user_show" class="gxg_tcdiv"></div>
<!-- 查看修改读取层 -->
<div id="div_edit_user_show" class="gxg_tcdiv"></div>
<%
}
%>