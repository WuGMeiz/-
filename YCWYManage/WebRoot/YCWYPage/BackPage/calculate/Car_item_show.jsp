<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Housetype"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_ChargeType"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_item"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%//清空缓存 保证再此调用该页时重新打开
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<% 

	
	if(session.getAttribute("USER_ID")==null)
	{
		String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
		out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	    out.println("window.location='"+url+"';");
	    out.println("</script>");
	}
	else
	{
	String a_id=session.getAttribute("A_ID").toString();
	String ts_id=session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	
	//每页显示多少条
	int pageSize = 15;
	int pageNum = 1;
	//设置要显示哪页
	if (request.getParameter("pagenum") != null) {
		String temp = request.getParameter("pagenum").toString();
		pageNum = Integer.parseInt(temp);
	}
	
	
	/* List<TB_Estate_item> listi = new ArrayList<TB_Estate_item>();

	listi = new ChangeDao().getTBItem(yznr,ts_id,pageSize, pageNum,a_id,"车位费"); */
		
	List<TB_Estate_ChargeType> listi = new ArrayList<TB_Estate_ChargeType>();
	listi = new ChangeDao().getTBItemType(yznr,ts_id,pageSize, pageNum,a_id,"车位费");
	int countNum = 0;
	int pageCount = 0;
	if (listi.size() > 0) {
		countNum = ((TB_Estate_ChargeType) listi.get(0)).getCount();
		if (countNum % pageSize == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = (countNum / pageSize) + 1;
		}
	}
 

%>
<div class="addBoxList">
	<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableList">	
		<thead class="thead">
			<tr height="38">
				<th align="center" style="width: 5%">小区</th>
				<th align="center" style="width: 5%">房屋类型</th>
				<th align="center" style="width: 5%">收费方式</th>
				<th align="center" style="width: 5%">单价（元）</th>
				<th align="center" style="width: 3%">滞纳天数</th>
				<th align="center" style="width: 3%">滞纳金比例</th>
				<th align="center" style="width: 8%">备注</th>
				<th align="center" style="width: 5%">编辑</th>
			</tr>
		</thead>
		<tbody class="tableTbody">
		<%
			
			if(listi.size()==0){
		%>
		<tr height="38">
				<td align="center" colspan="12">
				暂无车位费收取项
				</td>
		</tr>
		<%
			}else{
				for (int i = 0; i < listi.size(); i++) 
				{
					TB_Estate_ChargeType item= (TB_Estate_ChargeType) listi.get(i);
					
			%>
			<tr height="38">
				
				 
				<td align="center">
					<%=item.getCt_xiaoquName() %>
				</td>		
				<td align="center">
				     <select class="input_txt"  id="EhType<%=i%>" name="EhType<%=i%>">
				     <%
				        List<TB_Estate_Housetype> housetypes = new MyTB_House_DAO().findHouseType(item.getEs_id()+"");
			 		     if(housetypes.size()>0){
			 		      for(TB_Estate_Housetype type:housetypes){
			 		    %>
			 		        <option value="<%=type.getHt_id()%>" <%if(item.getHt_id()==type.getHt_id()){%>selected="selected"<%}%> ><%=type.getHtName() %></option>
			 		    <%}}else{ %>
			 		        <option value="">--暂未设置房屋类型-</option>
				 		<%} %>
					</select>
				 </td>
				<td align="center">
					<select class="input_txt"  id="shoufeifs<%=i%>" name="shoufeifs<%=i%>">
					        <option value="12" <%if(item.getCt_ItemType().equals("12")){%>selected="selected"<%}%>>按车位数每月一条订单</option>
							<option value="8" <%if(item.getCt_ItemType().equals("8")){%>selected="selected"<%}%>>按固定金额计算</option>
							<option value="4" <%if(item.getCt_ItemType().equals("4")){%>selected="selected"<%}%>>按月计算</option>
							<option value="5" <%if(item.getCt_ItemType().equals("5")){%>selected="selected"<%}%>>按季度计算</option>
					</select>
				</td>		
				<td align="center">
					<input type="text"  class="input_txt" style="width: 90%;" id="price<%=i%>" name="price<%=i%>" value="<%=S_string.DecimalFormat_string(item.getCt_price(), 2) %>" />
				</td>	
				<td align="center">
					 <input type="text"  class="input_txt" style="width: 90%;" id="znjday<%=i%>" name="znjday<%=i%>" value="<%=item.getCt_znjDay() %>" />
				</td>	
				<td align="center">
					 <input type="text"  class="input_txt" style="width: 90%;" id="znjbl<%=i%>" name="znjbl<%=i%>" value="<%=item.getCt_znjRatio()%>" />
				</td>
				<td align="center">
					 <input type="text"  class="input_txt" style="width: 90%;" id="remark<%=i%>" name="remark<%=i%>" value="<%=item.getCt_remark()%>" />
				</td>
				<td align="center">
					<input type="button" name="xiu<%=i%>" value=" 修改 " onclick="CarItem_update('1','<%=item.getCt_id() %>','<%=item.getEi_id() %>','<%=item.getCt_itemName() %>',
					'<%=i%>','<%=request.getContextPath()%>/TB_ItemUpdate?arg=4','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/Car_item_show.jsp?type=1"%>')" />
					<%-- <input type="button" name="xiu<%=i%>" value=" 暂停 " onclick="TBPay_Item_update('2','<%=item.getEi_id() %>',
					'<%=i%>','<%=request.getContextPath()%>/WyTBPay_Item_update','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/charge_item_show.jsp?type=1"%>')" />
					<input type="button" name="xiu<%=i%>" value=" 启用 " onclick="TBPay_Item_update('1','<%=item.getEi_id() %>',
					'<%=i%>','<%=request.getContextPath()%>/WyTBPay_Item_update','show_update','<%=request.getContextPath()+"/YCWYPage/BackPage/calculate/charge_item_show.jsp?type=1"%>')" />
		 --%>		
				
				</td>
			</tr>
			<%
				}
			}
			%>
		</tbody>
	</table>
</div>
<!--分页-->
	<div class="pages">
		<ul>
			<li>共&nbsp;<%=pageCount%>&nbsp;页&nbsp;/&nbsp;<%=countNum %>&nbsp;条&nbsp;
				<%
				if (pageNum == 1){
				%>

				<li>上一页</li>

<%
	} else {
%>	
		<li onclick="javascript:Back('<%=request.getContextPath()%>/YCWYPage/BackPage/calculate/Car_item_show.jsp','<%=pageNum - 1%>','show_update')">上一页</li>
    
<%
    	}
    %>    
  <li>  [<%=pageNum %>] </li>
<%
if (pageNum == pageCount | pageCount == 0) {
%>	
		<li>下一页</li>
<%
	} else {
%>     
  	<li onclick="javascript:Next('<%=request.getContextPath()%>/YCWYPage/BackPage/calculate/Car_item_show.jsp','<%=pageNum + 1%>','show_update')">下一页</li>
  	
<%
  		}
  	%>
     <li> 到&nbsp;
	<select id="showpages" onchange="showpages('<%=request.getContextPath()%>/YCWYPage/BackPage/calculate/Car_item_show.jsp',this,'show_update')">
	<option value="0">&nbsp;</option>
	<%
		for (int i = 1; i <= pageCount; i++) {
	%>
	<option value="<%=i%>" <%if (i == pageNum) {%> selected="selected" <%}%>><%=i%></option>
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