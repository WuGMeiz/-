<%@page import="WYBack_Stage.Bean.TB_Estate_Exoption"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_ExoptionDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="WYCommunity.Page"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.S_string"%>



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
		
		String Es_id=S_string.formatString(request.getParameter("Es_id"));
		String timesk=S_string.formatString(request.getParameter("timesk"));
	    String timesj=S_string.formatString(request.getParameter("timesj"));
	    String Type=S_string.formatString(request.getParameter("Type"));
		String Condition=" 1=1  and v.status='1' and r.Es_id='"+Es_id+"'";
	    if(!"".equals(Type)){
		  Condition+=" and r.status='"+Type+"'";
		}
		if(!timesk.equals("") && !timesj.equals("")) {
		  Condition+=" and r.creat_time>='"+timesk+" 00:00:00' and r.creat_time<='"+timesj+" 23:59:59' ";
	    }
		if(!timesk.equals("") && timesj.equals("")) {
			Condition+=" and r.creat_time>='"+timesk+" 00:00:00' ";
		}
		if(timesk.equals("") && !timesj.equals("")) {
			Condition+=" and r.creat_time<='"+timesj+" 23:59:59' ";
		}
		session.setAttribute("Condition",Condition);
		
		String Innerj = " r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id ";
		session.setAttribute("Innerj", Innerj);
		
	}/*
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_RepairInfo");

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
	} */
	Object To_id = 0;
	if(request.getParameter("type")==null){
		To_id=request.getParameter("To_id") ;
		session.setAttribute("To_id", To_id);
	}else{
		To_id = session.getAttribute("To_id");
	}
	if(null==To_id){
		To_id="0";
	}
	List<TB_Estate_Exoption> list=new TB_Estate_ExoptionDao().select_Exoption(Integer.parseInt((String)To_id));
	/* Object To_id=request.getParameter("To_id") ;
	System.out.print(To_id);
	List<TB_Estate_Exoption> list= null;
	if(null==To_id){
		To_id="0";
		list=new TB_Estate_ExoptionDao().select_Exoption(Integer.parseInt((String)To_id));
	}else{
		To_id = session.getAttribute("To_id");
		session.setAttribute("To_id", To_id);
		list=new TB_Estate_ExoptionDao().select_Exoption(Integer.parseInt((String)To_id));
	} */
	/* String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
	wulifilepath=wulifilepath.replace('\\','/');
	String filename="报修信息表.csv"; */
	
%>
<%-- <table>
  	<tr height="45">
	  	<td height="40" width="100%" >
			<input type="button" id="ex" name="ex" value="【生成报表】" onclick="createReportTB_Estate_Order('<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/createReportBao.jsp','dcddbb');"/>
			<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"//YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;"/>
		</td>
	</tr>
</table> --%>
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="38">
					<th style="width: 2%">序	号</th>
					<th style="width: 5%">选项类型</th>
					<th style="width: 15%">选项名称</th>
					<th style="width: 10%">上传图片</th>
					<th style="width: 8%">创建时间</th>
					<th style="width: 5%" >编辑</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_Exoption exoption= (TB_Estate_Exoption) list.get(i);
			%>
						
				<tr height="38">
					<td align="center"><%=exoption.getOp_id() %></td>
					<td align="center">
					<% switch(exoption.getIf_tw()){
							case 0 :
		        				%>图片<%
        						break;
							case 1 :
		        				%>图文<%
        						break;
							case 2 :
		        				%>文字<%
        						break;
						}
					%>
					</td>
					<td align="center">
					   <%=exoption.getOptionName() %>
					</td>
					  <%
					   if(exoption.getImages()=="" || exoption.getImages()==null){
					   %>
					   <td align="center">
					             未上传图片
					   </td>
					   <%}else{ %>
					     <td align="center">
					     <img src="data:image/jpg;base64,<%=exoption.getImages() %>" height="40px" width="40px">
				     	</td>
					  <%} %>
					<td align="center">
					    <%=exoption.getCreate_time() %>
					</td>
					<td align="center">
						<input type="button" value="编辑" onclick="Update_Exoption2('div_user_show','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_edit.jsp"%>','<%=exoption.getOp_id() %>');return false;"/>
						<input type="button" value="删除" onclick="deleteExoption('<%=To_id%>','<%=exoption.getOp_id()%>','<%=exoption.getOptionName() %>','<%=request.getContextPath()%>/TB_Estate_ExoptionServlet?arg=2','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_update.jsp?type=1" %>','show_update');" /> 
						
					</td>
				</tr>
				<%
				}}else{ %>
				<tr height="38">
				<td colspan="6" align="center">暂无内容</td>
				</tr>
				
<%
		}
		//String url = request.getContextPath()+"/YCWYPage/BackPage/ProperService/tmxx_update.jsp";
%>
	</tbody>
</table>
<%-- <div class="pages">
	<jsp:include page="/YCWYPage/BackPage/page.jsp">
		<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
		<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
		<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
		<jsp:param name="suburl" value="<%=url%>"/>
		<jsp:param name="divid" value="show_update"/>
	</jsp:include>
</div> --%>
 
<div id="div_user_show" class="gxg_tcdiv"></div>
<!-- 查看修改读取层 -->
<div id="div_edit_user_show" class="gxg_tcdiv"></div>
<%
}
%>