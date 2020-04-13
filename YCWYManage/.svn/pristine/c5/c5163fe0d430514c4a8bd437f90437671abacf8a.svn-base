<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_Estate_paper"%>
<%@page import="WYBack_Stage.Dao.TB_Estate_paperDao"%>
<%@page import="WYCommunity.T_time"%>
<%@page import="WYCommunity.S_string"%>
<%@page import="java.lang.StringBuffer"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="welcss" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/welcss.css" />
	<link id="jhshgl" type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/YCWYPage/BackPage/css/jhshgl.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/YCWYPage/BackPage/js/jquery-1.9.1.min.js"></script>
	
	<!-- 打印控件 -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/YCWYPage/BackPage/js/jquery.PrintAreaPri.js"></script>

  </head>
  
  <body>
  <%
	String divid=request.getParameter("divid");
	String parID=request.getParameter("parID");
	String ts_id = session.getAttribute("U_ID").toString();
	TB_Estate_Order order=new MyTB_Estate_Order().getOrders(parID);
	String sname=new MyTB_Estate_Order().getTB_SEV_Name(ts_id);
	
	
	//我修改得 start
   	TB_Estate_paperDao tEstate_paperDao = new TB_Estate_paperDao();
   	TB_Estate_paper paper1 = tEstate_paperDao.selectPaperByEsIdAndEoId( order.getEs_id(),order.getBankid());
   	if( paper1.getPrint_status() == 2){
   	
   		int paperSum = tEstate_paperDao.paperSum(order.getEs_id()); //根据小区id计算出票据序号
	   	String time = new T_time().getTime();
	   	TB_Estate_paper paper = new TB_Estate_paper();
	   	paper.setBankid(order.getBankid());
	   	paper.setEs_id(order.getEs_id());
	   	paper.setCreate_time(time);
	   	paper.setPaper_num(paperSum);
	   	paper.setStatus(1);
	   	paper.setPrint_status(1);
	   	tEstate_paperDao.addPaper(paper);
	    	//我修改 end
   	}
   
	Map<String,Object> map = new TB_Estate_paperDao().selectPidandoaoerNum(order.getBankid());
	StringBuffer dt=new StringBuffer(order.getPay_time().substring(0, 10));
	
	dt.replace(4, 5, "年");
	dt.replace(7, 8, "月");
	dt.append("日");
     String bz="";
    if(!"".equals(order.getCost_startTime()) && !"".equals(order.getCost_endTime()) ){
      String ks=order.getCost_startTime().substring(5, 7);
      String jz=order.getCost_endTime().substring(5,7);
      if(ks.equals(jz)){
        bz=ks+"月,";
      }else{
        bz=ks+"-"+jz+"月,";
      }
    }
  //  bz+=order.getItemName()+":"+Double.parseDouble(order.getTotal_sj());
	String jfxName=order.getItemName();
    if("1256".equals(ts_id)){
     if("物业费".equals(jfxName)){
      jfxName="物业费(电梯费)";
     }
     if("车位费".equals(jfxName)){
      jfxName="车辆停放服务费";
     }
    }
     bz+=jfxName+":"+Double.parseDouble(order.getTotal_sj())+"元"+order.getRemark();
   
	
%>

<div class="allitemJF" id="allitemJF" align="center"  >
      <table class="new_table_content" style="width: 90%;height:290px;border: 0;margin-top: 5px;margin-left:45px;" >

        <tr height="60">
        	<th colspan="5" align="center" style="height:60px;width: 85%;border: 0;font-size:20px;" ><%=sname %></th>
       		<th rowspan="6" style="width:5%; padding-top: 95px;font-size: 14px;border: 0"></th>
        </tr>
        <tr>
        	<th colspan="4"  align="left"    style=" padding-left: 5px;font-size: 14px;border: 0">
        	<div style="width:70%;float: left;font-weight: 400;">收款单位：<%=sname %></div>
        	<!-- SHL修改 start -->
        	<% if(Integer.valueOf(map.get("status").toString()) == 0 ) { %>
        	<div style="width:18%;float: right;font-weight: 400;text-align: right">NO.W<%=order.getEo_id() %></div></th>
        	<%}else{ %>
        	<div style="width:18%;float: right;font-weight: 400;text-align: right">NO.W<%=map.get("paper_num") %></div></th>
        	<%} %>
        	<!-- SHL修改 end -->
        	<%-- <div style="width:18%;float: right;font-weight: 400;text-align: right">NO.W<%=order.getEo_id() %></div></th> --%>
        </tr> 
        <tr>
        	<td align="center" style="width: 20%;font-size: 14px;">收款方式</td>
         	<td align="left" style="width: 20%; padding-left: 5px;font-size: 14px">
            <% if(order.getPayType().equals("")){%><%}else if(order.getPayType().equals("0")){%>网上支付<%}else if(order.getPayType().equals("1")){%>现金支付<%}else if(order.getPayType().equals("2")){%>被扫支付<%}else if(order.getPayType().equals("3")){%>转账支付<%}else if(order.getPayType().equals("4")){%>刷卡支付<%}else if(order.getPayType().equals("5")){%>调账支付<%}else if(order.getPayType().equals("6")){%>主扫支付<%}%>
         	</td>
         	<td align="center" style="width: 20%;font-size: 14px;">收款日期</td>
         	<td align="left" style=" width: 20%;padding-left: 5px;font-size: 14px"><%=dt %></td>
       </tr> 
         <tr>
        	<td align="center" style="width: 10%;font-size: 14px;">房号</td>
         	<td align="left" style=" width: 20%;padding-left: 5px;font-size: 14px"><%=order.getEhNumber() %></td>
         	<td align="center" style="width: 10%;font-size: 14px;">客户姓名</td>
         	<td align="left" style="width: 20%; padding-left: 5px;font-size: 14px"><%=order.getOwnerName() %></td>
       </tr>
        <tr>
        	<td align="center" style="width: 10%;font-size: 14px;">费用项目</td>
         	<td align="left" style=" width: 20%;padding-left: 5px;font-size: 14px"><%=jfxName %></td>
         	<td align="center" style="width: 10%;font-size: 14px;">金额(小写)</td>
         	<td align="left" style="width: 20%; padding-left: 5px;font-size: 14px"><%=java.text.NumberFormat.getCurrencyInstance().format(Double.parseDouble( order.getTotal_sj())) %></td>
       </tr>
       <tr>
         	<td align="center" style="width: 10%;font-size: 14px;">金额(大写)</td>
         	<td align="left" colspan="3" style=" width: 20%;padding-left: 5px;font-size: 14px"><%=S_string.toUpperRMB(Double.parseDouble( order.getTotal_sj())) %></td>
       </tr>
        <tr>
         	<td align="center" style="width: 10%;font-size: 14px;">备注</td>
         	<td align="left" colspan="3" style=" padding-left: 5px;font-size: 14px"><%=bz %></td>
       </tr>
         <tr>
        	 <th colspan="4"  align="left"    style=" padding-left: 5px;font-size: 14px;border: 0">
        	<div style="width:30%;float: left;font-weight: 400; ">收款单位盖章</div>
        	<div style="width:30%;float: left;font-weight: 400; text-align: center;">收款人:<%=session.getAttribute("USER_NAME").toString() %></div>
        	<div style="width:30%;float: left;font-weight: 400; text-align: right">交款人:</div>
        </th>
        </tr>  
   </table>
</div></br>
<div align="center" id="buttonDiv">
	<input type="button" id="print" name="print" value="打印" onclick="XiaoShiAndPrint('<%=divid %>','<%= order.getEo_id()%>','<%= order.getEs_id()%>','<%=order.getBankid() %>');"/>
</div>
<script>
function XiaoShiAndPrint(ID,eo_id,es_id,Bankid)
{
	document.all.print.style.display = "none";	

	//$('#allitemJF').printArea();
	
	
	
	$.ajax({
	    		type : 'POST',
	    		url : "/YCWYManage/TB_Estater_ProperServlet",
	    		data : {"eo_id" : eo_id,"es_id" : es_id,"Bankid" : Bankid,"status":0 },
	    		dataType:"text",
	    		success : function(data) {
		    		if(data != null && data != ""){
		    		alert(data)
		    		}else{
		    		 setTimeout(function (){
					 $('#allitemJF').printArea();
					}, 600);
					}
	    		
		        
		    	},
		    	error : function() {
					
	    		}
	    	});
	
	document.all.print.style.display ="";
	
}

</script>
  </body>
</html>
