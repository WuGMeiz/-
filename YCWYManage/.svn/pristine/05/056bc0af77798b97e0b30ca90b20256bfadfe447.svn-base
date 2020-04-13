<%@page import="WYCommunity.CreateImgYanZhengMa"%><%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
//CreateImgYanZhengMa rc = new CreateImgYanZhengMa(response);
CreateImgYanZhengMa rc = new CreateImgYanZhengMa(response,4,"abcdefghijklmnopqrstuvwxyz");//new CreateImgYanZhengMa(response,4,"0123456789abcdefghijklmnopqrstuvwxyz");
//rc.setBgColor(100,100,100);
String rand = rc.createRandImage();
session.setAttribute("rand",rand);
%>