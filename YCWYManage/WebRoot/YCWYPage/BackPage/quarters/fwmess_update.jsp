<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>
<%@page import="WYBack_Stage.Dao.TB_Village_Dao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_Build_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_House"%>
<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>


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
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		
		String es_id=S_string.formatString(request.getParameter("Es_id"));
		String bu_id=S_string.formatString(request.getParameter("Bu_id"));
		String Un_id=S_string.formatString(request.getParameter("Un_id"));
		String ehNumber=S_string.formatString(request.getParameter("EhNumber"));
		String ownerName=S_string.formatString(request.getParameter("OwnerName"));
		
	    String LEVELS=session.getAttribute("LEVELS").toString();
		String tu_id = session.getAttribute("TU_ID").toString();
        String ts_id = session.getAttribute("U_ID").toString();
		
		String Condition="";
		Condition+=" 1=1 and a.ts_id ='"+ts_id+"' and a.Es_id='"+es_id+ "' ";
		
		if("1".equals(LEVELS)){
		  Condition+="   and (b.BuHead='' or b.BuHead is null or b.BuHead='"+tu_id+"') ";
		}
		if(!bu_id.equals("")) {
			Condition+=" and a.Bu_id ='"+bu_id+"' ";
		}
		if(!Un_id.equals("")) {
			Condition+=" and a.Un_id ='"+Un_id+"' ";
		}
		if(!ehNumber.equals("")) {
			Condition+=" and a.EhNumber like '%"+ehNumber+"%' ";
		}
	    if(!ownerName.equals("")) {
			Condition+=" and a.OwnerName like '%"+ownerName+"%' ";
		}
		Condition+="  and a.status='1' and b.status='1' and c.status='1'  ";
		
		session.setAttribute("Condition",Condition);
		
		String Innerj = " a left join TB_Estate_Build b on a.ts_id=b.ts_id and a.Es_id=b.Es_id" +
                " and a.Bu_id=b.Bu_id left join TB_Estate_Village c on a.ts_id=c.ts_id and a.Es_id=c.Es_id left join  TB_Estate_Unit d on a.un_id =d.un_id  ";
		
		session.setAttribute("Innerj", Innerj);
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_House");
	//设置多表联合查询时的连接语句
	pages.setInnerj(session.getAttribute("Innerj").toString());
	
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
		
	List<TB_Estate_House> list=new MyTB_House_DAO().getTB_House( pages.getPagesize(),pages.getPagenum(),pages.getCondition() );
	
	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
	wulifilepath=wulifilepath.replace('\\','/');
	String filename="房屋信息明细表.csv";
%>
<table>
  	<tr height="45">
	  	<td height="40" width="100%" >
			<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/quarters/createReport.jsp','dcddbb');"/>
			<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
		</td>
	</tr>
</table>
<div style="width:100%; overflow-x:scroll;">
<table border="0" width="150%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="38">
					<th style="width: 2%"></th>
					<th style="width: 5%">小区名称</th>
					<th style="width: 5%">楼号</th>
					<th style="width: 5%">单元</th>
					<th style="width: 5%">房屋编号</th>
					<!-- <th style="width: 5%">房屋名称</th> -->
					<th style="width: 5%">楼层数</th>
					<th style="width: 5%">是否交房</th>
					<th style="width: 2%">业主姓名</th>
					<th style="width: 2%">业主电话</th>
					<th style="width: 2%">建筑面积</th>
					<th style="width: 2%">使用面积</th>
					<th style="width: 2%">供暖面积</th>
					<th style="width: 2%">车位数</th>
					<th style="width: 2%">车位编号</th>
					<!-- <th style="width: 2%">房屋性质</th>
					<th style="width: 2%">房屋结构</th> -->
					<th style="width: 2%">房屋类型</th>
					<th style="width: 2%">楼宇朝向</th>
					<!-- <th style="width: 2%">房屋现状</th>
					<th style="width: 2%">产权状况</th>
					<th style="width: 2%">房屋用途</th> -->
					<th style="width: 2%">常住人口数</th>
					
					<!-- <th style="width: 5%">备注</th> -->
					<th style="width: 5%" colspan="2">编辑</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_House teh= (TB_Estate_House) list.get(i);
				
			%>
						
				<tr height="38">
					<td align="center">
					 <input type="checkbox" id="fw_<%=i%>" name="fw" value="<%=teh.getEh_id()%>" />
					 <input  type="hidden" id="Eh_id<%=i %>" name="Eh_id" value="<%=teh.getEh_id() %>"/>
					</td>
					<td align="center">
					<%=teh.getEsName()  %>
					</td>
					<td align="center">
					<%=teh.getBuName()  %>
					</td>
					<td align="center">
					<%=teh.getUnName()  %>
					</td>
					<td align="center">
					<%=teh.getEhNumber() %>
					</td>
					<%-- <td align="center">
					<%=teh.getEhName()  %>
					</td> --%>
					<td align="center">
					<%=teh.getStorey()  %>
					</td>
					<td align="center">
					<%
					 if(teh.getHandIs() == 1){
					
					 %>
					 			已交房
					 			<%}else{ %>
					 			未交房
					 			<%} %>
					</td>
					<td align="center">
					<%=teh.getOwnerName()  %>
					</td>
					<td align="center">
					<%=teh.getOwnerPhone()  %>
					</td>
					<td align="center">
					<%=teh.getBuildArea()  %>
					</td>
					<td align="center">
					<%=teh.getUseArea() %>
					</td>
					<td align="center">
					<%=teh.getHeatingArea()  %>
					</td>
					<td align="center">
					<%=teh.getCarNum()  %>
					</td>
					<td align="center">
					<%=teh.getRemark()  %>
					</td>
					<%-- <td align="center">
					<%=teh.getEhNature()  %>
					</td>
					<td align="center">
					<%=teh.getEhStru()  %>
					</td> --%>
					<td align="center">
					<%=teh.getHtName() %>
					</td>
					<td align="center">
					<%=teh.getEhTurn()  %>
					</td>
					<%-- <td align="center">
					<%=teh.getEhStatus()  %>
					</td>
					<td align="center">
					<%=teh.getPropertyRight() %>
					</td>
					<td align="center">
					<%=teh.getHousingUse()  %>
					</td> --%>
					<td align="center">
					<%=teh.getOftenNumber()  %>
					</td>
					<%-- <td align="center">
					<%=teh.getRemark()  %>
					</td> --%>
					
					 <td align="center" style="white-space: nowrap;">
					    <input type="button" value="密码重置" onclick="resetUserPassword('<%=request.getContextPath()%>/resetUserPassword','<%=teh.getEh_id()%>','111111','<%=request.getContextPath() %>/YCWYPage/BackPage/quarters/fwmess.jsp?type=0')" >
						<input type="button" name="xiu<%=i%>" value="编辑" onclick="edit_show_see_tanchu_div( 'div_edit_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwmess_edit_see.jsp"%>','<%=teh.getEh_id() %>');return false;"/>
						<input type="button" name="shan<%=i%>" value="删除" onclick="TBBuild_delete('<%=teh.getEh_id() %>','<%=request.getContextPath()%>/TB_Estate_House_SERVLET?args=3','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/fwmess_update.jsp?type=1" %>','show_update');" />  
					</td> 
				</tr>
				<%} %>
				<tr height="38">
				 <td align="center">
				   <input type="checkbox" name="chkall" onclick="CheckAllTBOrders(this,'fw');"/>
			    </td>
			     <td style="text-align:left;" colspan="20">
				         &nbsp;全选&emsp;<input type="button" value="批量删除房屋" onclick="pldelTB_Estate_Order('fw','<%=request.getContextPath()+"/TB_Estate_House_plsc"%>','<%=request.getContextPath()%>/YCWYPage/BackPage/quarters/fwmess_update.jsp?type=1','show_update');return false;"/>
			      </td>
				</tr>	
				<%
				}else{ %>
				<tr height="38">
				<td colspan="21" align="center">暂无内容</td>
				</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/quarters/fwmess_update.jsp";
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