<%@page import="WYBack_Stage.Bean.TB_Estate_Village"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	response.setDateHeader("Expires", 0);//prevents caching at the proxy server
	request.setCharacterEncoding("UTF-8");//必须写这个 否则post提交过来的中午会乱码不能兼容IE和火狐
%>
 

<%
if (session.getAttribute("USER_ID") == null) 
{
	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
    out.println("window.location='"+url+"';");
    out.println("</script>");
} 
else 
{
 
	String ts_id=session.getAttribute("U_ID").toString();
	String yznr = session.getAttribute("YZNR").toString();
	List<TB_Estate_Village> list= new MyTB_Estate_Order().select_xiaoqu(ts_id);
%>  
  
   <!--单位列表-->
   <div class="content">
	<div class="containtNav">
		<div class="addBox">
			<!-- <table cellspacing="0" cellpadding="5" style=" display: block;margin: 0 auto;"> -->
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr height="45">
					<td align="right" width="80">小区名称:</td>
					<td align="left"  >
						<select id="Es_id" style="width: 100%;"    name="Es_id" class="input_txt" 
					  onchange="select_louyuLD(this,'louyu','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/charge_select_ly.jsp"%>','<%=ts_id %>','jfxdiv','<%=request.getContextPath()+"/YCWYPage/BackPage/charge/jfx1.jsp"%>')">
							<option value="">--请选择小区--</option>
								<%
								if(list.size()>0){
									TB_Estate_Village village=null;
									for(int i=0;i<list.size();i++){
										village=(TB_Estate_Village)list.get(i);
										if(!yznr.equals("")){
											
											if(yznr.contains(village.getEs_id()+"")){

						%>
							<option value="<%=village.getEs_id() %>" ><%=village.getEsName()%></option>
							<% 
											}
										}else{
								%>
								<option value="<%=village.getEs_id() %>" ><%=village.getEsName()%></option>
								<% 
										}
									}
								}
								 %>
						</select>
					</td>
					<td align="right" width="80">楼宇名称:</td>
					<input type="hidden" name="sign" id="sign" value="2">
					<td align="left"    >
						<div id="louyu">
							<jsp:include flush="ture" page="charge_select_ly.jsp">
							<jsp:param value="<%=ts_id %>" name="ts_id"/>
							</jsp:include>
						</div>
					</td>
					<td align="right" width="80">单元名称:</td>
					<td align="left">
						<div id="sl_unite">
							<jsp:include flush="ture" page="../charge/charge_select_unite.jsp">
								<jsp:param value="<%=ts_id %>" name="ts_id"/>
								<jsp:param value="2" name="sign"/>
							</jsp:include>
						</div>
					</td>
					<td align="right" width="80">房屋编号:</td>
					<td align="left"  >
						<input type="text" style="width: 100%;"  class="input_txt" id="EhNumber" name="EhNumber" />
					</td>
					<td align="right" width="80">业主姓名:</td>
					<td align="left"  >
						<input type="text"  style="width: 90%;"  class="input_txt" id="OwnerName" name="OwnerName" />
					</td>
					
					<td></td>
					</tr>
					<tr height="45">
					<td align="right" width="80">收费项:</td> 
					<td>
						<div id="jfxdiv">
							<jsp:include page="/YCWYPage/BackPage/charge/jfx1.jsp" flush="true"></jsp:include>
						</div>
					</td>
					<td align="right" width="80">缴费状态:</td>
					<td align="left" >
						<select class="input_txt" style="width: 100%;"  id="payStatus" name="payStatus">
							<option value="">--请选择状态--</option>
							<option value="0">未支付</option>
							<option value="1">已支付</option>
							<option value="3">已退款</option>
						</select>
					</td>
					<td align="right" width="80">缴费方式:</td>
					<td align="left"  >
						<select class="input_txt" style="width: 100%;"  id="payType" name="payType">
							<option value="">--请选择方式--</option>
							<option value="0">网上支付</option>
							<option value="2">被扫支付</option>
							<option value="1">现金支付</option>
							<option value="6">主扫支付</option>
							<option value="3">转账支付</option>
							<option value="4">刷卡支付</option>
							<option value="5">调账支付</option>
						</select>
					</td>
					<td align="right" width="80">支付时间:</td>
					<td align="left"  colspan="3"   >
							<input type="text" style="width: 45.7%;" class="input_txt" id="timesk" name="timesk" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />至
							<input type="text" style="width: 45.7%;" class="input_txt" id="timesj" name="timesj" onclick="showcalendar(event,this);" onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''" readonly="readonly" />
					</td>
					<td align="center" ><input  type="button" class="submit_input" value="查询" onclick="select_gue_orders('select_gue_orders','<%=request.getContextPath()+"/YCWYPage/BackPage/baobiao/ge_baobiao_see.jsp"%>','<%=ts_id%>')"/></td>
				</tr>
					
			</table>
<!-- 等待刷新的div层 -->
<div id="select_gue_orders" ></div>

<!-- 显示等待图片的读取层 -->
<div id="Loading_div_da" style="z-index:1000000;filter:Alpha(opacity=10);opacity:0.1;"></div>
<div id="Loading_div_xiao" style="margin-left:-50px;text-align:center;color:red;width:100px;height:100px;display:none;z-index:1000001;filter:Alpha(opacity=70);opacity:0.7;">
<img src="<%=request.getContextPath() %>/YCWYPage/BackPage/images/loading.gif" style="width: 65;height: 65"></img>
</div>
</div></div></div> 
<%} %>

