<%@page import="WYBack_Stage.Bean.TB_HMBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="WYBack_Stage.Dao.Mete_ReadClass"%>
<%@page import="WYCommunity.Base64Utils"%>
<%@page import="WYCommunity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
/*********每次查询时生成报表，写在这里是为了避免分页时频繁生成********/
String  ts_id=session.getAttribute("U_ID").toString();
	if(request.getParameter("type")==null) {
		session.setAttribute("Condition",null);//清除其它具有分页功能的页面可能保存的session值
		session.setAttribute("pagenum", null);//清除其它具有分页功能的页面可能保存的页码session值
		String xiaoquid=request.getParameter("xiaoquid");
		String louid=request.getParameter("louid");
		String Un_id=request.getParameter("Un_id");
		String fwid=request.getParameter("fwid");
		String type=request.getParameter("metetype");
		String orderStatus=request.getParameter("orderStatus");
		String Condition="";
		String MationStr="";
		String str1="";
		if(!xiaoquid.equals("")){
			str1 = " and b.Es_id='"+xiaoquid+"'";
		}
		String str2="";
		if(!louid.equals("")){
			str2 = " and b.Bu_id='"+louid+"'";
		}
		String str6="";
		if(!Un_id.equals("")&&!louid.equals("")){
			str6 = " and b.Un_id='"+Un_id+"'";
		}
		String str3="";
		if(!fwid.equals("")){
			//str3 = " and b.EhNumber like'%"+fwid+"%'";
			str3 = " and b.EhNumber='"+fwid+"'";
		}
		String str4="";
		if(!type.equals("")){
			str4 = " and a.type='"+type+"'";
		}
		String str5="";
		if(!orderStatus.equals("")){
			str5 = " and a.orderStatus='"+orderStatus+"'";
		}
		String LEVELS=session.getAttribute("LEVELS").toString();
		String tu_id = session.getAttribute("TU_ID").toString();
		if("1".equals(LEVELS)){
		   LEVELS="  and (d.BuHead='' or d.BuHead is null or d.BuHead='"+tu_id+"') ";
		}else{
		   LEVELS="";
		}
		Condition+=" a.ts_id='"+ts_id+"' and b.ts_id='"+ts_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 "+str1+str2+str3+str4+str5+str6+LEVELS;
		
		session.setAttribute("Condition",Condition);
		String Innerj = " inner join TB_Estate_House b on a.eh_id=b.eh_id inner join TB_Estate_Village c on b.Es_id=c.Es_id inner join TB_Estate_Build d on b.Bu_id=d.Bu_id left join TB_Estate_Unit e on b.Un_id=e.Un_id";
		session.setAttribute("Innerj", Innerj);
	}
	//设置分页的属性
	Page pages=new Page();
	//表名
	pages.setTablename("TB_Estate_Mation a");
	
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
	
	List<TB_HMBean> list = new Mete_ReadClass().findHM(pages.getPagesize(),pages.getPagenum(),pages.getCondition());
	
	String wulifilepath=application.getRealPath("/Report")+"/";//绝对物理路径
	wulifilepath=wulifilepath.replace('\\','/');
	String filename="抄表明细表.csv";
	String str = "";
	
%>

<div id="show_mete"  style="width:100%">
<table class="show_table" style="float: left; margin-top: 10px;">
	<tr height="25">
		<td align="left" width="80%">
			<input type="button" id="ex" name="ex" value="【生成报表】" onclick="metecreateReport('<%=Base64Utils.jiami(ts_id) %>','<%=Base64Utils.jiami(wulifilepath) %>','<%=Base64Utils.jiami(filename) %>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/Mete_CreateReport.jsp','dcddbb');"  />
			<input type="button" id="dcddbb" name="dcddbb" value="【导出报表】" disabled="disabled" onclick="window.location='<%=request.getContextPath()+"/YCWYPage/BackPage/down.jsp?filepath="+Base64Utils.jiami(wulifilepath)+"&filename="+Base64Utils.jiami(filename) %>';return false;" />
		</td>
	</tr>
</table>
<div style="width: 100%; overflow: hidden; overflow-x: auto;" >
<table  class="tableList" width="130%" cellspacing="0" cellpadding="0" border="0" style="background: #fff;">
	<thead class="thead">
	<tr>
		<!-- <th colspan="2">
			订单号
		</th> -->
		<th width="10%">
			小区
		</th>
		<th width="10%">
			楼宇
		</th>
		<th width="10%">
			单元名称
		</th>
		<th width="10%">
			房屋编号
		</th>
		<th width="10%">
			表名称
		</th>
		<th width="10%">
			上次抄表数
		</th>
		<th width="10%">
			本次抄表数
		</th>
		<th width="5%">
			用量
		</th>
		<th width="10%">
			抄表日期
		</th>
		<th width="10%">
			订单状态
		</th>
		<th width="10%">
			类型
		</th>
		<th width="10%">
			编辑
		</th>
	
	</tr>
	</thead>
	<tbody class="tableTbody">
	<% if(list.size()>0){
	%>
	<%
		for(TB_HMBean thm : list){
	%>
		<tr height="34">
			<td align="center">
				<%=thm.getEsName() %>
			</td>
			<td  align="center">
				<%=thm.getBuName() %>
			</td>
			<td  align="center">
				<%=thm.getUnName() %>
			</td>
			<td  align="center">
				<%=thm.getEhNumber() %>
			</td>
			<td  align="center">
				<%=thm.getMa_id() %>
			</td>
			<td  align="center">
				<%=thm.getLastReadNum() %>
			</td>
			<td  align="center">
				<%=thm.getNowReadNum() %>
			</td>
			<td  align="center">
				<%=thm.getUserNumber() %>
			</td>
			<td  align="center">
				<%=thm.getReadDate().substring(0, 10) %>
			</td>
			<%
				if(thm.getOrderStatus()==0){
			%>
				<td  align="center">
					未生成收费单
				</td>
			<%		
				}else{
			%>
				<td  align="center">
					已生成收费单
				</td>
			<%			
				}
			%>
			<%
				if(thm.getType()==0){
			%>
				<td  align="center">
					水费
				</td>
			<%		
				}else if(thm.getType()==1){
			%>
				<td  align="center">
					电费
				</td>
			<%			
				}else if(thm.getType()==2){
			%>
				<td  align="center">
					燃气费
				</td>
			<%	
				}
			%>
			<%
				if(thm.getOrderStatus()==0){
			%>
				<td align="center">
					<input type="button" name="xiu"   value="修改" onclick="Mete_tanchu_div('div_edit_app_show','','','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/Mete_xiugai_show.jsp"%>','<%=thm.getEm_id() %>','<%=thm.getMa_id() %>');return false;"/>
					<input type="button" name="shan"  value="删除" onclick="Mete_delete('<%=thm.getMa_id() %>','<%=thm.getEm_id() %>','<%=request.getContextPath()%>/MeteDelete_Servlet','show_mete','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/mete_selectshow.jsp?type=0"%>');" />
				</td>
			<%		
				}else{
			%>
			<td align="center">
					<input type="button" name="xiu"   value="修改" disabled="disabled"/>
					<input type="button" name="shan"  value="删除" disabled="disabled"/>
				</td>
			<%
				}
			%>
			
	<%} %>
		</tr>
		<%-- <tr>
			<td align="center">
				<input type="checkbox" name="chkall" onclick="CheckAll_checkboxs(this,'orderid');" />
			</td>
		</tr>
		 <td align="left">
				全选&emsp;<input type="button" value="批量删除订单" onclick="user_xinxi_sh('orderid','<%=request.getContextPath()+"/SZDW_TBOrder_deletepl"%>','<%=request.getContextPath()%>/SZDW/Managepage/order_update.jsp?type=0','show_update');return false;"/>
		</td>  --%>
		<tr>
			<td>
				<input type="button" id="qk" name="qk" value="一键生成收费单" onclick="Mete_Order('<%=request.getContextPath()+"/Mete_Order"%>','<%=request.getContextPath()%>/YCWYPage/BackPage/charge/mete_selectshow.jsp?type=1','show_mete');return false;" />
			</td>
			<td colspan="12" align="left"></td>
		</tr>
		</tbody>

	
	<%
	}else{
	%>
	<tbody class="tableTbody">
		<tr height="34">
			<td colspan="13" align="center" height="38">
				暂无内容
			</td>
		</tr>
	</tbody>
	<%	} %>
</table>
	
</div>
<%
		String url = request.getContextPath()+"/YCWYPage/BackPage/charge/mete_selectshow.jsp";
	%>
<!--分页-->
	<div class="pages"  style="float: right;">
		<jsp:include page="/YCWYPage/BackPage/page.jsp">
			<jsp:param name="Pagecount" value="<%=pages.getPagecount()%>"/>
			<jsp:param name="Countnum" value="<%=pages.getCountnum()%>"/>
			<jsp:param name="Pagenum" value="<%=pages.getPagenum()%>"/>
			<jsp:param name="suburl" value="<%=url%>"/>
			<jsp:param name="divid" value="show_mete"/> 
		</jsp:include>
	</div>
<!--/分页-->
</div>
<div class="clear"></div>
<%

}
%>