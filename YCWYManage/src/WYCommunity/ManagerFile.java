package WYCommunity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManagerFile {
	/**
	 * 写文件
	 * 
	 * @param str
	 * @param filePath
	 * @return
	 */
	public boolean writeFile(String str, String filePath) {
		boolean reFlag = true;
		File wFile = new File(filePath);
		try {
			if (!wFile.exists()) {
				wFile.createNewFile();
			}
			BufferedWriter bw;

			bw = new BufferedWriter(new FileWriter(wFile, true));
			bw.write(str, 0, str.length());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			reFlag = false;
			e.printStackTrace();
		}
		return reFlag;
	}

	public boolean writeFile2(String str) {
		boolean reFlag = true;
		File wFile = new File(getUrl());
		try {
			if (!wFile.exists()) {
				wFile.createNewFile();
			}
			BufferedWriter bw;

			bw = new BufferedWriter(new FileWriter(wFile));
			bw.write(str, 0, str.length());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			reFlag = false;
			e.printStackTrace();
		}
		return reFlag;
	}

	/**
	 * 读文件
	 * 
	 * @param filePath
	 * @return
	 */
	public String readFile() {
		String s = null;
		StringBuffer sb = new StringBuffer();
		File wFile = new File(getUrl());
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(wFile));
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 删除文件
	 */

	public boolean deleteFile(String fielPath) {
		boolean flag = false;
		File file = new File(fielPath);
		if (file.exists()) {
			flag = file.delete();
		}

		return flag;
	}

	/**
	 * 得到目录下的所有文件
	 * @param filePath
	 * @return
	 */
	public File[] getFileList(String filePath) {

		File[] fileList=null;
		File file = new File(filePath);
		if(file.isDirectory())
		{
			fileList=file.listFiles();
		}
		return fileList;
	}

	/**
	 * 得到提示信息文本文件的路径
	 */
	public static String getUrl() {

		String filePath = "";
		try {
			filePath = ManagerFile.class.getClassLoader().getResource("../../")
					.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path = filePath.substring(1, filePath.length()) + "Tishimessage"
				+ File.separator;
		String realPath = path.replace("/", File.separator).replace("%20", " ")
				+ "tishimessage.txt";
		return realPath;
	}

	/**
	 * 得到tomcat根目录
	 */
	public static String getUrl(Class t) {

		String filePath = "";
		try {
			filePath = t.getClassLoader().getResource("../../").getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path = filePath.substring(1, filePath.length());
		String realPath = path.replace("/", File.separator).replace("%20", " ");
		return realPath;
	}

}
