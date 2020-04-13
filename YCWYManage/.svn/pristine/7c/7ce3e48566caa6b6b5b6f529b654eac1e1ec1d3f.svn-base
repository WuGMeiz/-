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
		
		String es_id=S_string.formatString(request.getParameter("Es_id"));
		String bu_id=S_string.formatString(request.getParameter("Bu_id"));
		
	    String LEVELS=session.getAttribute("LEVELS").toString();
		String tu_id = session.getAttribute("TU_ID").toString();
        String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
        
        session.setAttribute("ts_id", ts_id);
		
		String Condition=" 1=1 and a.status='1' and b.status='1'  ";
		Condition+=" and a.ts_id ='"+ts_id+"' and b.ts_id='"+ts_id+"' and a.Es_id='"+es_id+ "' ";
		
		if("1".equals(LEVELS)){
		  Condition+="   and (a.BuHead='' or a.BuHead is null or a.BuHead='"+tu_id+"') ";
		}
		if(!bu_id.equals("")) {
			Condition+=" and a.Bu_id ='"+bu_id+"' ";
		}
		session.setAttribute("Condition",Condition);
		
		String Innerj = " a left join TB_SEV_USER c on a.BuHead=c.tu_id  inner join TB_Estate_Village b on  a.Es_id=b.Es_id ";
		session.setAttribute("Innerj", Innerj);
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Build");

	//设置多表联合查询时的连接语句
	pages.setInnerj(session.getAttribute("Innerj").toString());
	
	//分页条件
	if(session.getAttribute("Condition")!=null) {
		pages.setCondition(session.getAttribute("Condition").toString());
	}
	//每页显示多少条
	pages.setPagesize(15);
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
		
	List<TB_Estate_Build> list=new MyTB_Build_DAO().getTB_Build(pages.getPagesize(),pages.getPagenum(),pages.getCondition(),session.getAttribute("ts_id").toString());
	
%>
<div style="width:100%; overflow-x:scroll;">
<table border="0" width="160%" cellpadding="0" cellspacing="0" class="tableList"> 
	<thead class="thead">
		<tr height="38">
			<th style="width: 2%">序号</th>
			<th style="width: 2%">小区名称</th>
			<th style="width: 6%">楼号</th>
			<th style="width: 4%">负责人</th>
			<th style="width: 2%">楼宇类别</th>
			<th style="width: 2%">房屋结构</th>
			<th style="width: 2%">楼宇总层数（层）</th>
			<th style="width: 2%">单元数（个）</th>
			<th style="width: 2%">房屋数量（户）</th>
			<th style="width: 2%">楼宇总面积</th>
			<th style="width: 2%">建筑面积（㎡）</th>
			<th style="width: 2%">使用面积（㎡）</th>
			<th style="width: 2%">竣工日期</th>
			<th style="width: 2%">楼宇朝向</th>
			<th style="width: 8%">备注</th>
			<th style="width: 5%" colspan="2">编辑</th>
		</tr>
	</thead>
	<tbody class="tableTbody">
	<%
		if(list.size()>0){
		for (int i = 0; i < list.size(); i++) 
		{
			TB_Estate_Build teb= (TB_Estate_Build) list.get(i);
		
	%>
				
		<tr height="38">
			<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
			<td align="center">
			<%=teb.getEsName()  %>
			</td>
			<td align="center">
			<%=teb.getBuName() %>
			</td>
			<td align="center">
			<%=teb.getBuHead() %>
			</td>
			<td align="center">
			<%=teb.getBuType() %>
			</td>
			<td align="center">
			<%=teb.getBuStru() %>
			</td>
			<td align="center">
			<%=teb.getBuNumber() %>
			</td>
			<td align="center">
			<%=teb.getUnitNumber() %>
			</td>
			<td align="center">
			<%=teb.getHouseNumber() %>
			</td>
			<td align="center">
			<%=teb.getTotalArea() %>
			</td>
			<td align="center">
			<%=teb.getBuildArea() %>
			</td>
			<td align="center">
			<%=teb.getUseArea() %>
			</td>
			<td align="center">
			<%=teb.getFinishDate() %>
			</td>
			<td align="center">
			<%=teb.getBuTurn() %>
			</td>
			<td align="center">
			<%=teb.getRemark() %>
			</td>
			<td align="center">
				<input type="button" name="xiu<%=i%>" value="编辑" onclick="edit_show_see_tanchu_div('div_edit_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/lyadd_edit_see.jsp"%>','<%=teb.getBu_id() %>');return false;"/>
				<input type="button" name="shan<%=i%>" value="删除" onclick="TBBuild_deletenew('<%=teb.getBu_id() %>','<%=request.getContextPath()%>/TB_Estate_Build_SERVLET?args=3','<%=request.getContextPath()+"/YCWYPage/BackPage/quarters/lyadd_update.jsp?type=1" %>','show_update');" /> 
			</td>
		</tr>
		<%
		}}else{ %>
		<tr height="38">
		 <td colspan="18" align="center">暂无内容</td>
		</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/quarters/lyadd_update.jsp";
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