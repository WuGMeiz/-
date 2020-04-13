<%@page import="WYCommunity.Base64Utils"%><%@ page language="java" pageEncoding="UTF-8"%><%@ page import="java.io.*"%><%//页面里不能有回车跟空格，否则会报错
try{
	String filepath=Base64Utils.jiemi(request.getParameter("filepath"));//下载文件所在服务器的目录
	String filename=Base64Utils.jiemi(request.getParameter("filename"));//下载文件的名称				
    File files=new File(filepath,filename);
    if(files.exists()){
    response.setContentType("binary/octet-stream");
    response.setHeader("Content-Disposition","attachment;filename="+new String(files.getName().getBytes("gb2312"),"iso8859-1"));
    ServletOutputStream os = response.getOutputStream();
    InputStream is=new FileInputStream(files);  
    byte[] bytes = new byte[1024];
    while(true){
    	int readSize = is.read(bytes);
    	if (readSize == -1) {
    		break;
    	}
        os.write(bytes, 0, readSize);
    }
    is.close();
    is=null;
    os.flush();
    os.close();
    os = null ;
    response.flushBuffer();
    out.clear();
    out = pageContext.pushBody();
    }else{
    out.print("下载的文件"+filename+"不存在!");
    }
} catch (Exception e){
}%>