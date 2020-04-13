<%@page import="WYBack_Stage.Bean.TB_Estate_Extopic"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_RepPeopleDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.MyTB_SEV_USER"%>



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
		
		String Es_id=S_string.formatString(request.getParameter("Es_id"));  //小区
		String type1=S_string.formatString(request.getParameter("type1"));  //类型
		String ex_id=S_string.formatString(request.getParameter("ex_id"));  //问卷表主键id
		String Condition=" 1=1  and r.status='1' ";
		if(!"".equals(ex_id)){
		  Condition+=" and r.ex_id='"+ex_id+"'";
		}
	
		session.setAttribute("Condition",Condition);
		
		//String Innerj = " r inner join TB_Estate_Extopic b on r.to_id=b.to_id  inner join TB_Estate_Examine c on c.ex_id=r.ex_id inner join TB_Estate_House d on r.Eh_id=d.Eh_id inner join TB_SEV e on c.ts_id=e.ts_id inner join TB_Estate_Village f on c.Es_id=f.Es_id";
		String Innerj = " r ";
		session.setAttribute("Innerj", Innerj);
		
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Extopic");

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
		
	List<TB_Estate_Extopic> list=new TB_Estate_RepPeopleDao().getWjTj(pages.getPagesize(),pages.getPagenum(),pages.getCondition(),pages.getInnerj());
	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
	wulifilepath=wulifilepath.replace('\\','/');
	String filename="问卷统计查看表.csv";
	
%>
 <table>
  <%-- 	<tr height="45">
	  	<td height="40" width="100%" >
			<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/createReportWJ.jsp','dcddbb');"/>
			<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
		</td>
	</tr> --%>
</table> 
<div style="width:100%; overflow-x:scroll;">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="40">
					<th style="width: 3%">序号</th>
					<th style="width: 5%">类型</th>
					<th style="width: 5%">题目要求</th>
					<th style="width: 20%">各选项情况(题目*人数 # 题目*人数)</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_Extopic tev= (TB_Estate_Extopic) list.get(i);
					String type="";
					if(tev.getType()==1){
					  type="单选";
					}else if(tev.getType()==2){
					  type="多选";
					}				
			%>
						
				<tr height="40">
					<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
					
					<td align="center">
					    <%=type %>
					</td>
				    <td align="center">
				   <%=tev.getTopic() %> 
				    </td>
				     <td align="center">
				   <% int to_id=tev.getTo_id();
				 String xinxi=  new TB_Estate_RepPeopleDao().getWjXuanXiang(to_id);
				    %> 
				    <%=xinxi %>
				    </td>
				    
				</tr>
				<%
				}}else{ %>
				<tr height="38">
				<td colspan="11" align="center">暂无内容</td>
				</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/ProperService/wjjl_update1.jsp";
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