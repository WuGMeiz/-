package WYCommunity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class SetLog {
	
	/**
	 * 自己指定日志文件名称
	 * @param sWord
	 * @param filename
	 */
	public static void logwriter(String sWord,String filename) {
		FileWriter writer = null;
		try {
			String localPath = "logs";// 得到XML文件的日志路径
			String realPath=new GetClassPath().getClassPath();
			realPath=realPath.substring(0, realPath.indexOf("WEB-INF"))+localPath+"\\Wy";
			File f=new File(realPath);
			if(!f.isDirectory()) {
				f.mkdirs();
			}
			writer = new FileWriter(realPath +"/"+new T_time().getIntTimeymd()+filename,true);
			writer.write(sWord+"\n\r");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}