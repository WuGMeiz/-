package WYCommunity;
/**
 * 继承了HttpServlet用于文件上传的类
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MutiFileUpload extends HttpServlet{


	private static final long serialVersionUID = 3704100136602272239L;
	
	protected Map<String, String> parameters;//保存普通form表单域
	protected String files;//保存上传的文件
	//protected Map<String, FileItem> itemfiles;//保存上传的文件
	
	protected Map<String, String> parametersfile_size;//保存普通form表单域
	
	protected String isImgWH;  //存放上传文件超过限定大小时，返回的内容
	protected String isfilesizeMax;  //存放上传文件超过限定大小时，返回的内容
    /**
     * private int sizeThreshold = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;该目录中,如果上传的文件需要储存将被储存在硬盘上。
     * 当主体内容的数据超过DiskFileUpload.setSizeThreshold方法设置的临界值大小时，这个流对象关联到硬盘上的一个临时文件，主体内容将被保存到该临时文件中
     */
    private int sizeThreshold = 100*1024*1024;//DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;	    

    /**
     * 设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
     * { @link # fileSizeMax }。-1表示没有最大值。
     */
    private long sizeMax = -1;
    /**
     * 字符编码，当读取上传表单的各部分时会用到该encoding
     */
    private String encoding = "utf-8";
    
    String []img_type={".gif",".jpg",".jpeg",".png",".bmp"}; //定义图片类型数组
    
	public String getEncoding()
    {
        return encoding;
    }
    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }
    
    public long getSizeMax()
    {
        return sizeMax;
    }
    public void setSizeMax(long sizeMax)
    {
        this.sizeMax = sizeMax*1024*1024;
    }
    
    public int getSizeThreshold()
    {
        return sizeThreshold;
    }
    public void setSizeThreshold(int sizeThreshold)
    {
        this.sizeThreshold = sizeThreshold*1024*1024;
    }
    /**
     * 适合上传文件不限大小
     * @param request
     * @param tmpPath
     * @param savePath
     */
    public void parse(HttpServletRequest request, String tmpPath, String savePath)
    {
        parameters = new HashMap<String, String>();
        files="";
        //itemfiles = new HashMap<String, FileItem>();
        
		File tmpDir=null;//初始化上传文件的临时存放目录 
		File saveDir=null;//初始化上传文件后的保存目录 
		
		tmpDir=new File(tmpPath); 
		saveDir=new File(savePath); 
		if(!tmpDir.isDirectory())
		{
			tmpDir.mkdirs();
		}
		if(!saveDir.isDirectory())
		{
			saveDir.mkdirs();
		}
		
        //创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        //设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        //创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        //设置编码
        upload.setHeaderEncoding(encoding);

        try{
        	
            List items = upload.parseRequest(request);
           
            Iterator iterator = items.iterator();
            while(iterator.hasNext())
            {
                FileItem item = (FileItem)iterator.next();
               
                if(item.isFormField())//isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {                    
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    parameters.put(fieldName, value);
                }
                else
                {
					String fileName=item.getName();
					if(!fileName.equals(""))
					{
						//取上传的文件名 
						if(fileName.lastIndexOf("\\")!=-1)
						{ 
							fileName=fileName.substring(fileName.lastIndexOf("\\")+1); 
						}
						else
						{ 
							fileName=fileName.substring(fileName.lastIndexOf("/")+1); 
						}
						//System.out.println(item.getSize()/1024);
						//保存文件到指定目录 
						item.write(new File(saveDir+File.separator+fileName)); 
						files+=fileName+"|";
					}
					
                	String fieldName = item.getFieldName();
                	//itemfiles.put(fieldName, item);
					parameters.put(fieldName, fileName);
					//System.out.println(fieldName);
                }
            }
			if(!files.equals(""))
			{
				files=files.substring(0, files.lastIndexOf("|"));//files.length()-1);
			}
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 适合控制表单中单个文件大小的方法
     * @param request
     * @param tmpPath
     * @param savePath
     * @param filesizeMax 单个文件大小，以KB为单位
     */
    public void parse(HttpServletRequest request, String tmpPath, String savePath, long filesizeMax)
    {
        parameters = new HashMap<String, String>();
        files="";
        isfilesizeMax="";
        
		File tmpDir=null;//初始化上传文件的临时存放目录 
		File saveDir=null;//初始化上传文件后的保存目录 
		
		tmpDir=new File(tmpPath); 
		saveDir=new File(savePath); 
		if(!tmpDir.isDirectory())
		{
			tmpDir.mkdirs();
		}
		if(!saveDir.isDirectory())
		{
			saveDir.mkdirs();
		}
		
        //创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        //设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        //创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        //设置编码
        upload.setHeaderEncoding(encoding);

        try{
        	
            List items = upload.parseRequest(request);
            
            Iterator iterator = items.iterator();
            while(iterator.hasNext())
            {
                FileItem item = (FileItem)iterator.next();
                
                if(item.isFormField())//isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {                    
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    //value = new String(value.getBytes("ISO-8859-1"),"UTF-8"); //中文乱码
                    parameters.put(fieldName, value);
                }
                else
                {
    				
					String fileName=item.getName();
					if(!fileName.equals(""))
					{
						//取上传的文件名 
						if(fileName.lastIndexOf("\\")!=-1)
						{ 
							fileName=fileName.substring(fileName.lastIndexOf("\\")+1); 
						}
						else
						{ 
							fileName=fileName.substring(fileName.lastIndexOf("/")+1); 
						}
	                	if(item.getSize() > filesizeMax*1024)
	                	{
	                		isfilesizeMax="上传的文件"+fileName+"大小为："+item.getSize()/1024+"KB,超出了系统限定的大小"+filesizeMax+"BK，请修改文件大小后再上传！";
	                		return;
	                	}
	                	else
	                	{
	         	           /***************************20150715鲍彦坡改/
	                		if(fileName.indexOf(".")>-1){
	                			fileName=new Date().getTime()+CreateRandom.getRandstr(7, "str")+fileName.substring(fileName.lastIndexOf("."), fileName.length());//fileName;//为了确保上传到服务器的文件名不重复，以毫秒+上传文件名拼成新的文件名。
	                		}else{
	                			fileName=new Date().getTime()+CreateRandom.getRandstr(7, "str");//fileName;//为了确保上传到服务器的文件名不重复，以毫秒+上传文件名拼成新的文件名。
	                		}
	                		/********************************/
	                		//保存文件到指定目录 
	                		item.write(new File(saveDir+File.separator+fileName)); 
	                	}
						files+=fileName+"|";
					}
					//System.out.println(fileName);
                	String fieldName = item.getFieldName();
					parameters.put(fieldName, fileName);
					//System.out.println(fileName+"|"+fieldName);
                }
            }
			if(!files.equals(""))
			{
				files=files.substring(0, files.lastIndexOf("|"));//files.length()-1);
			}
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 适合控制表单中单个图片大小以及图片宽高的方法
     * @param request
     * @param tmpPath
     * @param savePath
     * @param filesizeMax 单个文件大小，以KB为单位
     */
    public void parseimg(HttpServletRequest request, String tmpPath, String savePath, long filesizeMax,int imgwidth,int imgheight)
    {
        parameters = new HashMap<String, String>();
        files="";
        isfilesizeMax="";
        isImgWH="";
		File tmpDir=null;//初始化上传文件的临时存放目录 
		File saveDir=null;//初始化上传文件后的保存目录 
		
		tmpDir=new File(tmpPath); 
		saveDir=new File(savePath); 
		if(!tmpDir.isDirectory())
		{
			tmpDir.mkdirs();
		}
		if(!saveDir.isDirectory())
		{
			saveDir.mkdirs();
		}
		
        //创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        //设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        //创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        //设置编码
        upload.setHeaderEncoding(encoding);

        try{
        	
            List items = upload.parseRequest(request);
            
            Iterator iterator = items.iterator();
            while(iterator.hasNext())
            {
                FileItem item = (FileItem)iterator.next();
                
                if(item.isFormField())//isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {                    
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    parameters.put(fieldName, value);
                }
                else
                {

					String fileName=item.getName();
					if(!fileName.equals(""))
					{
						//取上传的文件名 
						if(fileName.lastIndexOf("\\")!=-1)
						{ 
							fileName=fileName.substring(fileName.lastIndexOf("\\")+1); 
						}
						else
						{ 
							fileName=fileName.substring(fileName.lastIndexOf("/")+1); 
						}
	                	//图片分辨率限制判断控制
	                	if(imgwidth>0 && imgheight>0)
	                	{
		                	InputStream iis=item.getInputStream();  
		                    BufferedImage bi=ImageIO.read(iis);  
		                    int width=bi.getWidth();  
		                    int height=bi.getHeight(); 
		    		
							if(width>imgwidth || height>imgheight )
							{
								isImgWH="您上传的图片分辨率为："+width+"×"+height+"像素，超过了最大的宽、高度限制，最大宽高分辨率限制为："+imgwidth+"×"+imgheight+"像素";
								return;
							}
	                	}
	                	//图片大小限制控制
	                	if(item.getSize() > filesizeMax*1024)
	                	{
	                		isfilesizeMax="上传的图片"+fileName+"大小为："+item.getSize()/1024+"KB,超出了系统限定的大小"+filesizeMax+"BK，请修改图片大小后再上传！";
	                		return;
	                	}
	                	else
	                	{
	                		fileName=new Date().getTime()+CreateRandom.getRandstr(7, "str")+fileName.substring(fileName.lastIndexOf("."), fileName.length());//fileName;//为了确保上传到服务器的文件名不重复，以毫秒+上传文件名拼成新的文件名。
	                		//保存文件到指定目录 
	                		item.write(new File(saveDir+File.separator+fileName)); 
	                	}
						files+=fileName+"|";
					}
					//System.out.println(fileName);
                	String fieldName = item.getFieldName();
					parameters.put(fieldName, fileName);
					//System.out.println(fileName+"|"+fieldName);
                }
            }
			if(!files.equals(""))
			{
				files=files.substring(0, files.lastIndexOf("|"));//files.length()-1);
			}
        } catch (Exception e){
            e.printStackTrace();
        }
    }    
    /**
     * 适合控制表单中单个文件大小的方法
     * @param request
     * @param tmpPath
     * @param savePath
     * @param filesizeMax 单个文件大小，以KB为单位
     * @param FileName 指定上传文件的名字
     */
    public void parse(HttpServletRequest request, String tmpPath, String savePath, long filesizeMax,String FileName)
    {
        parameters = new HashMap<String, String>();
        isfilesizeMax="";
        
		File tmpDir=null;//初始化上传文件的临时存放目录 
		File saveDir=null;//初始化上传文件后的保存目录 
		
		tmpDir=new File(tmpPath); 
		saveDir=new File(savePath); 
		if(!tmpDir.isDirectory())
		{
			tmpDir.mkdirs();
		}
		if(!saveDir.isDirectory())
		{
			saveDir.mkdirs();
		}
		
        //创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        //设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        //创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        //设置编码
        upload.setHeaderEncoding(encoding);

        try{
        	
            List items = upload.parseRequest(request);
            
            Iterator iterator = items.iterator();
            while(iterator.hasNext())
            {
                FileItem item = (FileItem)iterator.next();
                
                if(item.isFormField())//isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {                    
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    parameters.put(fieldName, value);
                }
                else
                {
					String fileName=item.getName();
					if(!fileName.equals(""))
					{

	                	if(item.getSize() > filesizeMax*1024)
	                	{
	                		isfilesizeMax="上传的文件"+fileName+"大小为："+item.getSize()/1024+"KB,超出了系统限定的大小"+filesizeMax+"BK，请修改文件大小后再上传！";
	                		return;
	                	}
	                	else
	                	{
	                		fileName=FileName;
	                		//保存文件到指定目录 
	                		item.write(new File(saveDir+File.separator+fileName)); 
	                	}
					}
					
                	String fieldName = item.getFieldName();
					parameters.put(fieldName, fileName);
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }    
    /**
     * 适合控制表单中同时上传图片跟文件时要求控制单个图片文件大小和单个文件大小的方法，单个文件大小，
     * @param request
     * @param tmpPath
     * @param savePath
     * @param imgsizeMax 以KB为单位
     * @param filesizeMax 以MB为单位
     */
    public void parse(HttpServletRequest request, String tmpPath, String savePath, long imgsizeMax,long filesizeMax)
    {
        parameters = new HashMap<String, String>();
        parametersfile_size = new HashMap<String, String>();
        
        files="";
        isfilesizeMax="";
        
		File tmpDir=null;//初始化上传文件的临时存放目录 
		File saveDir=null;//初始化上传文件后的保存目录 
		
		tmpDir=new File(tmpPath); 
		saveDir=new File(savePath); 
		if(!tmpDir.isDirectory())
		{
			tmpDir.mkdirs();
		}
		if(!saveDir.isDirectory())
		{
			saveDir.mkdirs();
		}
		
        //创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        //设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        //创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        //设置编码
        upload.setHeaderEncoding(encoding);

        try{
        	
            List items = upload.parseRequest(request);
            
            Iterator iterator = items.iterator();
            while(iterator.hasNext())
            {
                FileItem item = (FileItem)iterator.next();
                
                if(item.isFormField())//isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {                    
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    parameters.put(fieldName, value);
                }
                else
                {
                	
                	NumberFormat nf = NumberFormat.getNumberInstance(java.util.Locale.CHINA); 
                	nf.setMaximumFractionDigits(2);     //两位小数 

                	String filesize = nf.format((float)item.getSize()/(1024*1024)); //这里把你得到的输入的字符串传入 

					//String fileName=item.getName();
					String fileName=getFileName(item);//取得文件名
					
					if(!fileName.equals(""))
					{
						String houzhui=fileName.substring(fileName.lastIndexOf("."), fileName.length());
						int isimg=0;
						for(int i=0;i<this.img_type.length;i++)//循环对比图片类型后缀，如果是图片文件，计数变量加1
						{
							if(img_type[i].equals(houzhui))
							{
								isimg++;
							}
						}
						
						if(isimg>0)//如果图片计数变量大于0说明是图片文件
						{
				          	if(item.getSize() > imgsizeMax*1024)
		                	{
		                		isfilesizeMax="上传的图片"+fileName+"大小为："+item.getSize()/1024+"KB,超出了系统限定的大小"+imgsizeMax+"BK，请修改文件大小后再上传！";
		                		return;
		                	}
		                	else
		                	{
		                		fileName=new Date().getTime()+CreateRandom.getRandstr(7, "str")+fileName.substring(fileName.lastIndexOf("."), fileName.length());//fileName;//为了确保上传到服务器的文件名不重复，以毫秒+上传文件名拼成新的文件名。
		                		//保存文件到指定目录 
		                		item.write(new File(saveDir+File.separator+fileName)); 
		                	}
						}
						else
						{
		                	if(item.getSize() > filesizeMax*1024*1024)
		                	{
		                		isfilesizeMax="上传的文件"+fileName+"大小为："+item.getSize()/(1024*1024)+"MB,超出了系统限定的大小"+filesizeMax+"BK，请修改文件大小后再上传！";
		                		return;
		                	}
		                	else
		                	{
		                		fileName=new Date().getTime()+CreateRandom.getRandstr(7, "str")+fileName.substring(fileName.lastIndexOf("."), fileName.length());//+fileName;//为了确保上传到服务器的文件名不重复，以毫秒+上传文件名拼成新的文件名。
		                		//保存文件到指定目录 
		                		item.write(new File(saveDir+File.separator+fileName)); 
		                	}
						}

						files+=fileName+"|";
					}
					
                	String fieldName = item.getFieldName();
					parameters.put(fieldName, fileName);
					parametersfile_size.put(fieldName, filesize);
					//System.out.println(fileName+"|"+fieldName);
                }
            }
			if(!files.equals(""))
			{
				files=files.substring(0, files.lastIndexOf("|"));//files.length()-1);
			}
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /** *//** 得到上传文件的文件名
     * @param item
     * @return
     */
    public String getFileName(FileItem item)
    {
        String fileName = item.getName();
        fileName = replace(fileName,"\\","/");
        fileName = fileName.substring(fileName.lastIndexOf("/")+1);
        
        return fileName;
    }
    
    /** *//**字符串替换
     * @param source
     * @param oldString
     * @param newString
     * @return
     */
    public static String replace(String source, String oldString, String newString)
    {
        StringBuffer output = new StringBuffer();
        int lengthOfSource = source.length(); 
        int lengthOfOld = oldString.length(); 
        int posStart = 0; 
        int pos; 

        while ((pos = source.indexOf(oldString, posStart)) >= 0)
        {
            output.append(source.substring(posStart, pos));
            output.append(newString);
            posStart = pos + lengthOfOld;
        }
        if (posStart < lengthOfSource)
        {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }
    

    /**
     * 适合控制表单中单个文件大小的方法
     * @param request
     * @param tmpPath
     * @param savePath
     * @param filesizeMax 单个文件大小，以KB为单位
     * @param FileName 指定上传文件的名字
     */
    public boolean wyx_saveFile(HttpServletRequest request, String tmpPath, String savePath, long filesizeMax,String FileName)
    {
    	boolean flag = true;
        parameters = new HashMap<String, String>();
        isfilesizeMax="";
        
		File tmpDir=null;//初始化上传文件的临时存放目录 
		File saveDir=null;//初始化上传文件后的保存目录 
		
		tmpDir=new File(tmpPath); 
		saveDir=new File(savePath); 
		if(!tmpDir.isDirectory())
		{
			tmpDir.mkdirs();
		}
		if(!saveDir.isDirectory())
		{
			saveDir.mkdirs();
		}
		
        //创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        //设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        //创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        //设置编码
        upload.setHeaderEncoding(encoding);

        try{
        	
            List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while(iterator.hasNext())
            {
                FileItem item = (FileItem)iterator.next();
                
                if(item.isFormField())//isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {                    
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    parameters.put(fieldName, value);
                }
                else
                {
					String fileName=item.getName();
					String filename = FileName+".rar";//+ fileName.substring(fileName.lastIndexOf(".") + 1);//保存的文件名
					if(!fileName.equals(""))
					{

	                	if(item.getSize() > filesizeMax*1024)
	                	{
	                		isfilesizeMax="上传的文件"+fileName+"大小为："+item.getSize()/1024+"KB,超出了系统限定的大小"+filesizeMax+"BK，请修改文件大小后再上传！";
	                		flag = false;
	                	}
	                	else
	                	{
	                		item.write(new File(saveDir+File.separator+filename)); 
	                	}
					}
					
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}

