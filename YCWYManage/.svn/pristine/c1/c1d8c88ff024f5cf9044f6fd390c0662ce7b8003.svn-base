<%@page import="WYBack_Stage.Dao.MyTB_House_DAO"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Build"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYCommunity.S_string"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
	request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%	
	String ly_id=S_string.formatString(request.getParameter("ly_id"));
	String ts_id=S_string.formatString(request.getParameter("ts_id"));
	String Es_id=S_string.formatString(request.getParameter("Es_id"));
	String Bu_id=S_string.formatString(request.getParameter("Bu_id"));
	String Un_id=S_string.formatString(request.getParameter("Un_id"));
	
	ts_id=" and ts_id='"+ts_id+"' ";
	if(!Es_id.equals("")){
		Es_id=" and Es_id='"+Es_id+"' ";
	}
	if(!Bu_id.equals("")){
		Bu_id=" and Bu_id='"+Bu_id+"' ";
	}
	if(!Bu_id.equals("")&&!Un_id.equals("")){
		Un_id=" and Un_id='"+Un_id+"' ";
	}
	if(!ly_id.equals("")){
		ly_id=" and EhNumber='"+ly_id+"' ";
	}
	
	String Condition= ts_id+Es_id+Bu_id+Un_id+ly_id;
	String EhName = new MyTB_House_DAO().getEsName(Condition);
	String yzName="",fwmj="",ly="";
	String str[]=EhName.split("&");
	if(str.length>0){
	   //yzName=str[0];
	   if(str[0].contains("#")){ //业主姓名#楼宇id
	     String str2[]=str[0].split("#");  
	     yzName=str2[0];
	     ly=str2[1];
	   }else{
	    yzName=str[0];
	   }
	}
	if(str.length>1){
	   fwmj=str[1];
	}
%>

<input type="text" id="EhName" name="EhName" class="input_txt" value="<%=yzName %>"  style="width: 320px;color: #ff0000;" readonly="readonly"/>
<input type="hidden" id="fwmj" name="fwmj" value="<%=fwmj%>" />	
<input type="hidden" id="lyid" name="lyid" value="<%=ly%>" />		
