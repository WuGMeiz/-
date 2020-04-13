<%@page import="WYBack_Stage.Bean.TB_Estate_ChargeType"%>
<%@page import="WYBack_Stage.Dao.WwTB_Estate_itemDao"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_item"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
%>
<%
	String ts_id=session.getAttribute("U_ID").toString();
 	String Es_id=S_string.formatString(request.getParameter("Es_id"));
 	String EhNumber=S_string.formatString(request.getParameter("fw_id")); //房屋编号
 	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
 
%>

            <%
            	List<TB_Estate_ChargeType> list= new WwTB_Estate_itemDao().selectitemType(Es_id,ts_id,EhNumber,Bu_id);
	        %>
	                <select id="payItem" name="payItem" class="input_txt" 
	                onchange="select_jine(this,'jine','<%=request.getContextPath()+"/YCWYPage/BackPage/artificial/jine.jsp"%>','<%=ts_id %>')" 
	                >
	                <option value="">--请选择收费项--</option>
	        <%
	        	if(list.size()>0){
	                for(TB_Estate_ChargeType tei : list){
            %>
    			<option value="<%=tei.getEi_id()%>##<%=tei.getCt_id() %>##<%=tei.getCt_itemName() %>##<%=tei.getCt_Limited()%>##<%=tei.getCt_BuyLimited()%>##<%=tei.getCt_isYjLimited()%>##<%=tei.getHouseStandard()%>##<%=tei.getBelowStandard()%>##<%=tei.getAboveStandard()%>"><%=tei.getCt_itemName()%>(单价/<%=S_string.DecimalFormat_string(tei.getCt_price(),3)%>)</option>
		    		<%
		    		}
			        %>
	        <%}else{ %>	
	                <option value="">--暂无可预缴项--</option>
	        <%} %>	
              	 </select>
    			
			
<%
}
%>
