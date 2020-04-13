package WYCommunity;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

public class FileUpload {

	/**
	 * 上传文件
	 * @param request
	 * @param rootPath
	 * @return files=0文件大太, -1失败 ,1成功
	 */
	public String uploadFile(HttpServletRequest request, String rootPath) 
	{
	
		int MAX_SIZE=500000000;
		String files = "-1";
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		try{
		   
			String contentType = request.getContentType();

			in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength(); // 文件长度

			byte dataBytes[] = new byte[formDataLength];
			int bytesRead = 0;
			int totalBytesRead = 0;
			int sizeCheck = 0;

			while (totalBytesRead < formDataLength) {
				sizeCheck = totalBytesRead + in.available();
				if (sizeCheck > MAX_SIZE) {

					files = "0"; // 上传文件大于设定大小的返�?

					return files;
				}
				bytesRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += bytesRead;
			}

			String fileStr = new String(dataBytes, "ISO8859_1"); // fileStr是文件转换后的字符串

			dataBytes = null;
			int lastIndex = contentType.lastIndexOf("=");
			String boundary = contentType.substring(lastIndex + 1, contentType.length());
			String tempStr = fileStr.substring(fileStr.indexOf("filename=\"") + 10);
			tempStr = tempStr.substring(0, tempStr.indexOf("\n"));
			tempStr = tempStr.substring(tempStr.lastIndexOf("\\") + 1, tempStr.indexOf("\""));
			String fileName = new String(tempStr);
			
			//System.out.println(fileName);
			// 以下两行将文件名转为中文

			fileName = new S_string().getReplaceString(new String(fileName.getBytes("ISO8859_1"), "UTF-8"));
		
			int pos;
			pos = fileStr.indexOf("filename=\"");
			pos = fileStr.indexOf("\n", pos) + 1;
			pos = fileStr.indexOf("\n", pos) + 1;
			pos = fileStr.indexOf("\n", pos) + 1;
			int boundaryLocation = fileStr.indexOf(boundary, pos) - 4;

			fileStr = fileStr.substring(pos, boundaryLocation);
			String jdPath = rootPath + File.separator + fileName;
			fileOut = new FileOutputStream(jdPath); // �?行是�?��务器上jdPath路径下保存文�?
			fileOut.write(fileStr.getBytes("ISO8859_1"), 0, fileStr.length());
			files = jdPath;
		} catch (Exception e) {
			files = "-1";
			e.printStackTrace();
		} finally {
			try {
				fileOut.close();
			} catch (Exception f) {
				files = "-1";
			}
			try {
				in.close();
			} catch (Exception f) {
				files = "-1";
			}
		}
		return files;
	}
	/**
	 * 上传文件,指定保存到服务器上的图片名称
	 * @param request
	 * @param rootPath
	 * @return files=0文件大太, -1失败 ,1成功
	 */
	public String uploadFile(HttpServletRequest request, String rootPath,long filesize,String filename) 
	{
	
		long MAX_SIZE=filesize*1000;
		String files = "-1";
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		try{
		   
			String contentType = request.getContentType();

			in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength(); // 文件长度
			//System.out.println(formDataLength);
			
			byte dataBytes[] = new byte[formDataLength];
			int bytesRead = 0;
			int totalBytesRead = 0;
			int sizeCheck = 0;

			while (totalBytesRead < formDataLength) {
				sizeCheck = totalBytesRead + in.available();
				
				if (sizeCheck > MAX_SIZE) {

					files = "0"; // 上传文件大于设定大小的返�?

					return files;
				}
				bytesRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += bytesRead;
			}

			String fileStr = new String(dataBytes, "ISO8859_1"); // fileStr是文件转换后的字符串

			dataBytes = null;
			int lastIndex = contentType.lastIndexOf("=");
			String boundary = contentType.substring(lastIndex + 1, contentType.length());
			String tempStr = fileStr.substring(fileStr.indexOf("filename=\"") + 10);
			tempStr = tempStr.substring(0, tempStr.indexOf("\n"));
			tempStr = tempStr.substring(tempStr.lastIndexOf("\\") + 1, tempStr.indexOf("\""));
			String fileName = new String(tempStr);
			
			//System.out.println(fileName);
			// 以下两行将文件名转为中文

			//fileName = new S_string().getReplaceString(new String(fileName.getBytes("ISO8859_1"), "UTF-8"));
			fileName=filename+fileName.substring(fileName.lastIndexOf("."), fileName.length());
			int pos;
			pos = fileStr.indexOf("filename=\"");
			pos = fileStr.indexOf("\n", pos) + 1;
			pos = fileStr.indexOf("\n", pos) + 1;
			pos = fileStr.indexOf("\n", pos) + 1;
			int boundaryLocation = fileStr.indexOf(boundary, pos) - 4;

			fileStr = fileStr.substring(pos, boundaryLocation);
			String jdPath = rootPath + File.separator + fileName;
			fileOut = new FileOutputStream(jdPath); // �?行是�?��务器上jdPath路径下保存文�?
			fileOut.write(fileStr.getBytes("ISO8859_1"), 0, fileStr.length());
			files = fileName;
		} catch (Exception e) {
			files = "-1";
			e.printStackTrace();
		} finally {
			try {
				fileOut.close();
			} catch (Exception f) {
				files = "-1";
			}
			try {
				in.close();
			} catch (Exception f) {
				files = "-1";
			}
		}
		return files;
	}
	public String getFileName(HttpServletRequest request, String rootPath)
	{	
		String filename="";
		int MAX_SIZE=500000000;
		String files = "-1";
		DataInputStream in = null;
		FileOutputStream fileOut = null;
		try{
		   
			String contentType = request.getContentType();

			in = new DataInputStream(request.getInputStream());
			int formDataLength = request.getContentLength(); // 文件长度

			byte dataBytes[] = new byte[formDataLength];
			int bytesRead = 0;
			int totalBytesRead = 0;
			int sizeCheck = 0;

			while (totalBytesRead < formDataLength) {
				sizeCheck = totalBytesRead + in.available();
				if (sizeCheck > MAX_SIZE) {

					files = "0"; // 上传文件大于设定大小的返�?

					return files;
				}
				bytesRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += bytesRead;
			}

			String fileStr = new String(dataBytes, "ISO8859_1"); // fileStr是文件转换后的字符串

			dataBytes = null;
			int lastIndex = contentType.lastIndexOf("=");
			String boundary = contentType.substring(lastIndex + 1, contentType.length());
			String tempStr = fileStr.substring(fileStr.indexOf("filename=\"") + 10);
			tempStr = tempStr.substring(0, tempStr.indexOf("\n"));
			tempStr = tempStr.substring(tempStr.lastIndexOf("\\") + 1, tempStr.indexOf("\""));
			filename = new String(tempStr);
			
			//System.out.println(fileName);
			// 以下两行将文件名转为中文

			filename = new S_string().getReplaceString(new String(filename.getBytes("ISO8859_1"), "UTF-8"));
		} catch (Exception e) {
			files = "-1";
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception f) {
				files = "-1";
			}
		}
		return filename;
	}
}
