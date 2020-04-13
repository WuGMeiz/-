<%@page import="WYBack_Stage.Dao.TB_Estate_Extopic_Dao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Extopic"%>
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
		String wenjuanId=S_string.formatString(request.getParameter("wenjuanId"));
	
		
		String Condition=" 1=1 and status='1'  ";
		/* Condition+=" and ts_id ='"+wenjuanId+"' "; */
		if(!wenjuanId.equals("")) {
			Condition+=" and ex_id = '"+wenjuanId+"' ";
		}
		
		session.setAttribute("Condition",Condition);
		
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Extopic");

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
		
	List<TB_Estate_Extopic> list=new TB_Estate_Extopic_Dao().getTB_EState_Extopic(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
	
%>
<div style="width:100%; overflow-x:scroll;">
<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList"> 
			<thead class="thead">
				<tr height="38">
					<th style="width: 2%">序号</th>
					<th style="width: 10%">题目名称</th>
					<th style="width: 2%">是否图文</th>
					<th style="width: 5%">图片</th>
					<th style="width: 8%">修改上传图片</th>
					<th style="width: 2%">类型</th>
					<th style="width: 2%">排序</th>
					<th style="width: 10%">操作</th>
				</tr>
			</thead>
			<tbody class="tableTbody">
			<%
				if(list.size()>0){
				for (int i = 0; i < list.size(); i++) 
				{
					TB_Estate_Extopic tev= (TB_Estate_Extopic) list.get(i);
				
			%>
						
				<tr height="50">
					<td align="center"><%=pages.getPagesize()*(pages.getPagenum()-1)+i+1%></td>
					<td align="center">
						<%if(tev.getIf_tw() == 0) {%>
							<input  style="display: none" type="text" class="input_txt1" id="xiugaiTopicName<%=i%>" name="xiugaiTopicName<%=i%>" value="<%=tev.getTopic() %>"  maxlength="15"/>
						<%}else {%>
							<input  type="text" class="input_txt1" id="xiugaiTopicName<%=i%>" name="xiugaiTopicName<%=i%>" value="<%=tev.getTopic() %>"  maxlength="15"/>
						<%} %>
					</td>
					<td align="center">
						<select id="xiugaiIfwg<%=i%>" name="xiugaiIfwg<%=i%>" class="input_txt" onchange="wjtmchange('xiugaiTopicName','xiugaiImg',this,<%=i%>)">
							<%
							for(int j=0;j<3;j++){
								if(j == 0){
									 
							
								}else if(j==1){
									if(j==tev.getIf_tw()){
										%>
											<option value="<%=j%>" selected="selected">图文</option>
										<%
									}else{
										%>
											<option value="<%=j%>">图文</option>
										<%
									}
								}else if(j==2){
						     		if(j==tev.getIf_tw()){
										%>
											<option value="<%=j%>" selected="selected">文字</option>
										<%
									}else{
										%>
											<option value="<%=j%>">文字</option>
										<%
									}
						     	}
							}%>
						</select>
					</td>
					<td align="center">
						<%if(tev.getIf_tw() != 2) {%>
							<img alt="" width="60px" src="data:image/jpg;base64,<%=tev.getImages() %>" />
						<%}else {%>
							文字信息无图片
						<%} %>
					</td>
					<td align="center">
						<%if(tev.getIf_tw() != 2) {%>
							<input type="file" id="xiugaiImg<%=i%>" name="xiugaiImg<%=i%>"  maxlength="11" />
						<%}else {%>
							<input style="display:none" type="file" id="xiugaiImg<%=i%>" name="xiugaiImg<%=i%>"  maxlength="11" />
						<%} %>
					</td>
					
					<td align="center">
						<select id="xiugaiType<%=i%>" name="xiugaiType<%=i%>" class="input_txt">
							<%
							for(int j=1;j<=2;j++){
								if(j == 1){
									if(j==tev.getType()){
										%>
											<option value="<%=j%>" selected="selected">单选</option>
										<%
									}else{
										%>
											<option value="<%=j%>">单选</option>
										<%
									}
							
								}else if(j==2){
									if(j==tev.getType()){
										%>
											<option value="<%=j%>" selected="selected">多选</option>
										<%
									}else{
										%>
											<option value="<%=j%>">多选</option>
										<%
									}
								}
							}%>
						</select>
					</td>
					<td align="center">
						<input type="text" class="input_txt1" id="xiugaisort<%=i%>" name="xiugaisort<%=i%>" value="<%=tev.getSort() %>"  maxlength="5" />
					</td>
					<td align="center">
						<input width="45%" type="button" name="xiu<%=i%>" value=" 修改 "
							onclick="update_TB_Extopic('<%=tev.getTo_id() %>','<%=i%>','<%=request.getContextPath()%>/TB_Estate_Extopic_SERVLET?args=2','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/wjtm_update.jsp?type=1"%>')" />
					  <input width="45%" type="button" onfocus="this.blur();" name="shan<%=i%>" value="删除"  
					   onclick="delete_TB_Extopic('<%=tev.getTo_id() %>','<%=i%>','<%=request.getContextPath()%>/TB_Estate_Extopic_SERVLET?args=3','show_update','<%=request.getContextPath()
									+ "/YCWYPage/BackPage/ProperService/wjtm_update.jsp?type=1"%>');" />
					</td>
				</tr>
				<%
				}}else{ %>
				<tr height="38">
				<td colspan="4" align="center">暂无内容</td>
				</tr>
				
<%
		}
		String url = request.getContextPath()+"/YCWYPage/BackPage/ProperService/wjtm_update.jsp";
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