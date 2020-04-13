<%@page import="WYBack_Stage.Dao.ChangeDao"%>
<%@page import="WYBack_Stage.Dao.MyTB_Estate_Order"%>
<%@page import="WYBack_Stage.Bean.TB_SEV_CCBBank"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="ccbjf.system.network.HttpClientUtil"%>
<%@page import="ccbjf.system.util.MD5"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//清空缓存 保证再此调用该页时重新打开
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
%>
<%
	String user_id = session.getAttribute("USER_ID").toString();
	String tu_id=session.getAttribute("TU_ID").toString();
	String TSID = request.getParameter("TSID");
	TB_SEV_CCBBank tsc = new MyTB_Estate_Order().getTB_SEV_CCBBank(TSID);//查询该商户下的银行信息
	
	//+++++++++++++++++++++
	//丽华
	//统一支付平台提供的合作者身份ID【ID为15到28位字符串】
	//String PARTNER = "NjUxMzQxOTl8MTI1Ni0xMDg5";
	//统一支付平台提供的商户秘钥后32位【仅作为源串参加MD5摘要，不作为参数传递】
	//String KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCH2YFXrSQHpUZ7ijSCPYuf3R30GgxZlKWl4zr6GTTkklb1DsksyhgK0Ja2qefrhdtbsk2bZLokmOgfAMQlhdWolPieSbqQzmKsN/HbQijSfKxkHqw+X/P9zFgIwZGYSvG+YmRlDFIlAogIU2CNbf+o5DCq/nL3V52zlVqFN9XUCwIDAQAB";
	
	//+++++++++++++++++++++
	//统一支付平台提供的合作者身份ID【ID为15到28位字符串】
	String PARTNER = tsc.getPartner();
	//统一支付平台提供的商户秘钥后32位【仅作为源串参加MD5摘要，不作为参数传递】
	String KEY = tsc.getPublickey();
	//订单号，从订单查询页面传递过来
	String ORDERID = request.getParameter("ORDERID");
	//订单金额（扣款金额），从订单查询页面传递过来
	String PAYMENT = request.getParameter("PAYMENT");
	//码信息，客户出示的付款码（现支持龙支付、支付宝、微信、银联系的付款码）。
	String QRCODE = request.getParameter("QRCODE");
	//参与摘要的原文串
	String macstr = "PARTNER="+PARTNER+"&KEY="+KEY.substring(KEY.length()-32, KEY.length())+"&ORDERID="+ORDERID+"&PAYMENT="+PAYMENT;
	//生成摘要签名串
	String MAC = MD5.getCCBMd5(macstr);
	//被扫支付接口调用地址 正式地址为：http://sxpay.ccbjf.com/sweptPay
	//String url = "http://127.0.0.1:8080/ccbjfpay/sweptPay";	
	String url = "http://sxpay.ccbjf.com/sweptPay";
	//封装参数到MAP中
	Map<String,String> paramMap = new HashMap<String,String>();
	paramMap.put("PARTNER",PARTNER);
	paramMap.put("ORDERID",ORDERID);
	paramMap.put("PAYMENT",PAYMENT);
	paramMap.put("QRCODE",QRCODE);
	paramMap.put("MAC",MAC);
	//请求被扫支付接口，并接收返回值（返回值为json串）
	String json = HttpClientUtil.httpPost(url, paramMap, "UTF-8", 60, 0);
	
	/***下边是对被扫支付返回的数据做业务处理的内容***/
	//创建json对象，用于解析返回的json数据
	JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
	String RESULT = jsonObject.get("RESULT").getAsString();
	String rORDERID = jsonObject.get("ORDERID").getAsString();
	String rPAYMENT = jsonObject.get("PAYMENT").getAsString();
	String BANKID = jsonObject.get("BANKID").getAsString();
	String RETURN_CODE = jsonObject.get("RETURN_CODE").getAsString();
	String RETURN_MSG = jsonObject.get("RETURN_MSG").getAsString();
	String SIGN = jsonObject.get("SIGN").getAsString();//数字签名
	if(RESULT.equals("Y"))//返回支付成功
	{
		//参与摘要的原文串
		String retmacstr = "PARTNER="+PARTNER+"&KEY="+KEY.substring(KEY.length()-32, KEY.length())+"&BANKID="+BANKID+"&ORDERID="+rORDERID+"&PAYMENT="+rPAYMENT+"&RESULT="+RESULT;
		//生成摘要签名串
		String retMAC = MD5.getCCBMd5(retmacstr);
		if(retMAC.equals(SIGN))	//验签成功
		{
			//System.out.println("验签成功！");
			//验签成功后将本地业务订单数据更新到数据库中
			/*这里写updatesql的业务*/
			
			     boolean bl = new MyTB_Estate_Order().update_order_swept("Y",BANKID, TSID,ORDERID, PAYMENT);
		        if(bl){
		        	String l_content="操作员："+user_id+"修改订单："+ORDERID+"修改为已支付(被扫:"+PAYMENT+")";
		        	ChangeDao.add_Log(l_content, user_id, "2", tu_id);
		        	out.print("Y-交易成功！");//这个输出返回必须写在这里，ok- 后边的描述内容可以修改
		        }else{
		        	String l_content="操作员："+user_id+"修改订单："+ORDERID+"失败(返回值:"+bl+")";
		        	
		        	ChangeDao.add_Log(l_content, user_id, "2", tu_id);
		        	out.print("N-交易失败！");
		        }   
		        
			//out.print("Y-交易成功！");//这个输出返回必须写在这里，ok- 后边的描述内容可以修改
		}
		else	//验签失败
		{
			//System.out.println("验签失败！");
			//如果验签失败，在这里做相应的失败业务处理
			/*这里写处理验签失败的业务*/
			
			new MyTB_Estate_Order().updateYQ_swept("N", TSID,ORDERID);
			out.print("Y-交易成功，但银行返回数据验签失败！请联系平台核实数据！");//这个输出返回必须写在这里，ok- 后边的描述内容可以修改
		}
	}
	else//返回支付失败
	{
		//System.out.println("支付失败！");
		String msgstr = "错误码："+RETURN_CODE+" 错误信息："+RETURN_MSG;
		String l_content="操作员："+user_id+"修改订单："+ORDERID+"失败("+msgstr+")";
    	ChangeDao.add_Log(l_content, user_id, "2", tu_id); 
		out.print("N-"+msgstr);
	}
%>
<%
}
%>