<%@page import="WYBack_Stage.Bean.TBCms_Article"%>
<%@page import="java.util.List"%>
<%@page import="WYBack_Stage.Dao.TB_Village_Dao"%>
<%@page import="WYCommunity.Page"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%
//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%
if(session.getAttribute("USER_ID")==null)
{	
	out.print("sessionOvertime-您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！");
	
} 
else
{
%>
<%
	if(request.getParameter("type")==null)
	{	
		session.setAttribute("Condition",null);	//清除其它具有分页功能的页面可能保存的session值
	
		if(request.getParameter("cms_c_id")!=null)
		{
			session.setAttribute("cms_c_id", request.getParameter("cms_c_id"));
		}
		
		String Condition="";
		if(session.getAttribute("cms_c_id")!=null)
		{
			Condition=" 1=1 and cms_c_id='"+session.getAttribute("cms_c_id").toString()+"' and status='1' and cms_a_level='0' ";
		}
		else
		{
			Condition=" 1=1 and status='1' and cms_a_level='0' ";
		}
		Condition+=" and ts_id='"+session.getAttribute("U_ID").toString()+"'";
		session.setAttribute("Condition",Condition);
	}	
//	设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TBCms_Article");

	//设置多表联合查询时的连接语句
	//pages.setInnerj(session.getAttribute("Innerj").toString());
	//分页条件
	if(session.getAttribute("Condition")!=null)
	{
		pages.setCondition(session.getAttribute("Condition").toString());
	}	

	//每页显示多少条
	pages.setPagesize(8);
	//设置要显示哪页
	if(request.getParameter("pagenum")!=null)
	{
		pages.setPagenum(Integer.parseInt(request.getParameter("pagenum").toString()));
	}
		
	List<TBCms_Article> list=new TB_Village_Dao().getArticlePage(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
%>
<div class="addBoxList" style="height:auto !important;min-height:345px;height:345px;" >
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">
<thead class="thead">
	<tr height="38">
		<th width="8%">
			序号
		</th>
		<th width="52%">
			标题
		</th>
		<th width="10%">
			发布时间
		</th>			
		<th width="10%">
			浏览数
		</th>			
		<th width="20%">
			编辑
		</th>
	</tr>
	</thead>
	<tbody class="tableTbody">
	<%
	if(list.size()>0)
	{
		for (int i = 0; i < list.size(); i++) 
		{
			TBCms_Article cms = (TBCms_Article) list.get(i);
	%>
	<tr height="38" <%if(cms.getCms_index_tuijian().equals("1") || cms.getCms_a_order().equals("1") || cms.getCms_index_show().equals("1")){%> bgcolor="#B7DC07" <%}%>>
		<td align="center">
			<%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%>
		</td>
		<td align="center">
			<%=cms.getCms_a_title().length()>50?cms.getCms_a_title().substring(0,50)+"...":cms.getCms_a_title() %>
		</td>		
		<td align="center">
			<%=cms.getCms_a_create_time() %>
		</td>
		<td align="center">
			<%=cms.getCms_a_view_count()%>
		</td>			
		<td align="center">
			<input type="button" name="xiu<%=i%>" value="修改"
				onclick="TBCms_Article_update_show('','','div_sj_xxbj_show','<%=cms.getCms_a_id() %>','<%=cms.getCms_c_id()%>','<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/article_update.jsp');return false;"  />
			<input type="button" name="shan<%=i%>" value="删除"
				onclick="TBCms_Article_delete('<%=cms.getCms_a_id() %>','<%=request.getContextPath()%>/TBCms_Article_delete','<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/article_edit_show.jsp','Article_edit_show')" />
		</td>
	</tr>
	<%
		}
	}
	else
	{
	%>
	<tr height="38">
		<td colspan="8" align="center">
		<font style="color: red;font-size: 12px;"> 暂无信息！</font>			
		</td>
	</tr>	
	<% 
	}
	%>
</table>	

</div>
<!--分页-->
	<div class="pages">
		<ul>
			<li>共&nbsp;<%=pages.getPagecount()%>&nbsp;页&nbsp;/&nbsp;<%=pages.getCountnum() %>&nbsp;条&nbsp;
				<%
				if (pages.getPagenum() == 1){
				%>

				<li>上一页</li>

<%
	} else {
%>	
		<li onclick="javascript:Back('<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/article_edit_show.jsp','<%=pages.getPagenum()-1 %>','Article_edit_show')">上一页</li>
    
<%
    	}
    %>    
  <li>  [<%=pages.getPagenum() %>] </li>
<%
	if(pages.getPagenum()==pages.getPagecount() | pages.getPagecount()==0) {
%>	
		<li>下一页</li>
<%
	} else {
%>     
  	<li onclick="javascript:Next('<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/article_edit_show.jsp','<%=pages.getPagenum()+1 %>','Article_edit_show')">下一页</li>
  	
<%
  		}
  	%>
     <li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=request.getContextPath()%>/YCWYPage/BackPage/ProperService/article_edit_show.jsp',this,'Article_edit_show')">
	<option value="0">&nbsp;</option>
	<%
	 for(int i=1;i<=pages.getPagecount();i++) {
	%>
	<option value="<%=i%>" <%if (i==pages.getPagenum()){%> selected="selected" <%}%>><%=i%></option>
	<%
		}
	%>
	</select>    
   	</li>
			</ul>
		</div>
		<!--/分页-->
<div class="clear"></div>
<%
}
%>