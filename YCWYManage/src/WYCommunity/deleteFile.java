package WYCommunity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class deleteFile {

	
	public deleteFile(){
		
	}
	
	
	/* 以下这个方法删除上传到服务器上的图片************************************ */
	/* 以下是处理图片的方法************************************************** */
	
	/**
	 * 判断文件是否存在
	 * @param path
	 * @param file
	 * @return
	 */
	public boolean isHave(String path,String file)
	{   
		 File filename=new File(path,file);   
		 return filename.exists();   
	}   
	/**
	 * 删除文件返回�?
	 * @param path
	 * @param file
	 * @return
	 */
	public boolean isDelete(String path,String file) 
	{   
		 File filename=new File(path,file);   
		 return filename.delete();   
	}   
	/**
	 * 删除文件不返回�?
	 * @param path
	 * @param file
	 */
	public void DeleteFlie(String path,String file) 
	{   
		 File filename=new File(path,file);   
		 filename.delete();   
	} 
		/**  
		 * 删除单个文件  
		 * @param   sPath    被删除文件的文件�? 
		 * @return 单个文件删除成功返回true，否则返回false  
		 */  
		public static void deleteisFile(String FilePath,String FileName)
		{   
			//System.out.println(FilePath+"|"+ FileName);
			File filename = new File(FilePath,FileName);   
		    // 路径为文件且不为空则进行删除   
		    if (filename.exists()) 
		    {   
		    	//System.out.println(FileName);
		    	filename.delete(); 
		    } 
		}  
		/**
		 * 删除文件
		 * @param filepath 包含文件名称的完整路�?
		 */
		public static void DeleteFile(String filepath){
			if(filepath.length()>0)
			{
			String path=filepath.substring(0, filepath.lastIndexOf("/"));
			String file=filepath.substring(filepath.lastIndexOf("/")+1,filepath.length());
			File filename=new File(path,file);   
			filename.delete(); 
			}
		}
		
		   /**
	     * 删除空目�?
	     * @param dir 将要删除的目录路�?
	     */
	    private static void doDeleteEmptyDir(String dir) {
	        boolean success = (new File(dir)).delete();
	        if (success) {
	            System.out.println("Successfully deleted empty directory: " + dir);
	        } else {
	            System.out.println("Failed to delete empty directory: " + dir);
	        }
	    }

	    /**
	     * 递归删除目录下的�?��文件及子目录下所有文�?
	     * @param dir 将要删除的文件目�?
	     * @return boolean Returns "true" if all deletions were successful.
	     *                 If a deletion fails, the method stops attempting to
	     *                 delete and returns "false".
	     */
	    private static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
	            //递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        // 目录此时为空，可以删�?
	        return dir.delete();
	    }
//	    /**
//	     *测试
//	     */
//	    public static void main(String[] args) {
//	        doDeleteEmptyDir("new_dir1");
//	        String newDir2 = "new_dir2";
//	        boolean success = deleteDir(new File(newDir2));
//	        if (success) {
//	            System.out.println("Successfully deleted populated directory: " + newDir2);
//	        } else {
//	            System.out.println("Failed to delete populated directory: " + newDir2);
//	        }     
//	    }
	    public static void fileCopy(String oldFilePath,String newFilePath) throws Exception 
	    {
	    	  FileInputStream fi=new FileInputStream(oldFilePath);
	    	  BufferedInputStream in=new BufferedInputStream(fi);
	    	  FileOutputStream fo=new FileOutputStream(newFilePath);
	    	  BufferedOutputStream out=new BufferedOutputStream(fo);
	    	  
	    	  byte[] buf=new byte[1024];
	    	  int len=in.read(buf);//读文件，将读到的内容放入到buf数组中，返回的是读到的长�?
	    	  while(len!=-1)
	    	  {
	    	   out.write(buf, 0, len);
	    	   len=in.read(buf);
	    	  }

	    	  out.close();
	    	  fo.close();
	    	  in.close();
	    	  fi.close();
	    }
		
}
