package WYCommunity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

public class FileDownload {
	
	public HttpServletResponse downloadx(String path, HttpServletResponse response) {
	     try {
	         // path是指欲下载的文件的路径�?
	         File file = new File(path);
	         // 取得文件名�?
	         String filename = file.getName();
	         // 取得文件的后�?���?
	         String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

	         // 以流的形式下载文件�?
	         InputStream fis = new BufferedInputStream(new FileInputStream(path));
	         byte[] buffer = new byte[fis.available()];
	         fis.read(buffer);
	         fis.close();
	         // 清空response
	         response.reset();
	         // 设置response的Header
	         //response.setHeader("Content-disposition","attachment;filename="+"book.zip");  
	         response.addHeader("Content-Disposition", "inline;filename=" + new String(filename.getBytes()));
	         response.addHeader("Content-Length", "" + file.length());
	         OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	         
	         response.setContentType("application/octet-stream");
	         toClient.write(buffer);
	         toClient.flush();
	         toClient.close();
	     } catch (IOException ex) {
	         ex.printStackTrace();
	     }
	     return response;
	 }
	 //不会弹出保存对话�?
	  public static void downloadNet(HttpServletResponse response,String desFilePath,String furl) throws MalformedURLException {
	         // 下载网络文件
	         int bytesum = 0;
	         int byteread = 0;
	         URL url = new URL(furl);

	         try {
	             URLConnection conn = url.openConnection();
	             InputStream inStream = conn.getInputStream();
	             FileOutputStream fs = new FileOutputStream(desFilePath);

	             byte[] buffer = new byte[1204];
	             int length;
	             while ((byteread = inStream.read(buffer)) != -1) {
	                 bytesum += byteread;
	                 System.out.println(bytesum);
	                 fs.write(buffer, 0, byteread);
	             }
	             fs.flush();//很重�?
	             fs.close();//很重要，否则下载的文件无法打�?
	             
	         } catch (FileNotFoundException e) {
	             e.printStackTrace();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }


/*************************************************************************************************/
    public  HttpServletResponse download(String path, HttpServletResponse response) {
        try  {
            //  path是指欲下载的文件的路径�? 
           File file  =   new  File(path);
            //  取得文件名�? 
           String filename  =  file.getName();
            //  取得文件的后�?���?
           String ext  =  filename.substring(filename.lastIndexOf( " . " )  +   1 ).toUpperCase();

            //  以流的形式下载文件�? 
           InputStream fis  =   new  BufferedInputStream( new  FileInputStream(path));
            byte [] buffer  =   new   byte [fis.available()];
           fis.read(buffer);
           fis.close();
            //  清空response 
           response.reset();
            //  设置response的Header 
           response.addHeader( " Content-Disposition " ,  " attachment;filename= "   +   new  String(filename.getBytes()));
           response.addHeader( " Content-Length " ,  ""   +  file.length());
           OutputStream toClient  =   new  BufferedOutputStream(response.getOutputStream());
           response.setContentType( " application/octet-stream " );
           toClient.write(buffer);
           toClient.flush();
           toClient.close();
       }  catch  (IOException ex) {
           ex.printStackTrace();
       }
        return  response;
   }

    public   void  downloadLocal(HttpServletResponse response)  throws  FileNotFoundException {
        //  下载本地文件 
       String fileName  =   " Operator.doc " .toString();  //  文件的默认保存名
        //  读到流中 
       InputStream inStream  =   new  FileInputStream( " c:/Operator.doc " ); //  文件的存放路�?
        //  设置输出的格�?
       response.reset();
       response.setContentType( " bin " );
       response.addHeader( " Content-Disposition ","attachment;filename=\"" + fileName + "\"" );
        //  循环取出流中的数�?
        byte [] b  =   new   byte [ 100 ];
        int  len;
        try  {
            while  ((len  =  inStream.read(b))  >   0 )
               response.getOutputStream().write(b,  0 , len);
           inStream.close();
       }  catch  (IOException e) {
           e.printStackTrace();
       }
   }

    public   void  downloadNet(HttpServletResponse response)  throws  MalformedURLException {
        //  下载网络文件 
        int  bytesum  =   0 ;
        int  byteread  =   0 ;

       URL url  =   new  URL( " windine.blogdriver.com/logo.gif " );

        try  {
           URLConnection conn  =  url.openConnection();
           InputStream inStream  =  conn.getInputStream();
           FileOutputStream fs  =   new  FileOutputStream( " c:/abc.gif " );

            byte [] buffer  =   new   byte [ 1204 ];
            int  length;
            while  ((byteread  =  inStream.read(buffer))  !=   - 1 ) {
               bytesum  +=  byteread;
               System.out.println(bytesum);
               fs.write(buffer,  0 , byteread);
           }
       }  catch  (FileNotFoundException e) {
           e.printStackTrace();
       }  catch  (IOException e) {
           e.printStackTrace();
       }
   } 
//支持在线打开文件的一种方�?
    public   void  downLoad(String filePath, HttpServletResponse response,  boolean  isOnLine){
    	
    	try {
    	       File f  =   new  File(filePath);
    	        if  ( ! f.exists()) {
    	           response.sendError( 404 ,  " File not found! " );
    	            return ;
    	       }
    	       BufferedInputStream br  =   new  BufferedInputStream( new  FileInputStream(f));
    	        byte [] buf  =   new   byte [ 1024 ];
    	        int  len  =   0 ;

    	       response.reset();  //  非常重要 
    	        if  (isOnLine) {  //  在线打开方式 
    	           URL u  =   new  URL( " file:/// "   +  filePath);
    	           response.setContentType(u.openConnection().getContentType());
    	           response.setHeader( " Content-Disposition " ,  " inline; filename= "   +  f.getName());
    	            //  文件名应该编码成UTF-8 
    	       }  else  {  //  纯下载方�?
    	           response.setContentType( " application/x-msdownload " );
    	           response.setHeader( " Content-Disposition " ,  " attachment; filename= "   +  f.getName());
    	       }
    	       OutputStream out  =  response.getOutputStream();
    	        while  ((len  =  br.read(buf))  >   0 )
    	           out.write(buf,  0 , len);
    	       br.close();
    	       out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

   } 


	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param file
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean downLoadFile(HttpServletResponse response, File file) {
		boolean flag = true;
		byte[] buffer = new byte[4096];
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));

			int n = (-1);
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			response.setStatus(response.SC_OK);
			response.flushBuffer();

		} catch (Exception e) {
			flag = false;
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {

				}
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {

				}
		}
		return flag;

	}

}	
