/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import WYBack_Stage.Dao.ChangeDao;
import WYCommunity.Gue_Excel;
import WYCommunity.ManagerFile;

/**
 * <p> 固额订单批量导入 <p>
 * 
 * @date 2018-3-15 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class GuePl_Servlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Map<String, String> parameters;// 保存普通form表单域

    protected String files;// 保存上传的文件

    // protected Map<String, FileItem> itemfiles;//保存上传的文件
    protected Map<String, String> parametersfile_size;// 保存普通form表单域

    protected String isImgWH; // 存放上传文件超过限定大小时，返回的内容

    protected String isfilesizeMax; // 存放上传文件超过限定大小时，返回的内容

    /**
     * private int sizeThreshold =
     * DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;该目录中,如果上传的文件需要储存将被储存在硬盘上。
     * 当主体内容的数据超过DiskFileUpload
     * .setSizeThreshold方法设置的临界值大小时，这个流对象关联到硬盘上的一个临时文件，主体内容将被保存到该临时文件中
     */
    private int sizeThreshold = 100 * 1024 * 1024;// DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;

    /**
     * 设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。 { @link #
     * fileSizeMax }。-1表示没有最大值。
     */
    private long sizeMax = -1;

    /**
     * 字符编码，当读取上传表单的各部分时会用到该encoding
     */
    private String encoding = "utf-8";

    String[] img_type = {".gif", ".jpg", ".jpeg", ".png", ".bmp" }; // 定义图片类型数组

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public long getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(long sizeMax) {
        this.sizeMax = sizeMax * 1024 * 1024;
    }

    public int getSizeThreshold() {
        return sizeThreshold;
    }

    public void setSizeThreshold(int sizeThreshold) {
        this.sizeThreshold = sizeThreshold * 1024 * 1024;
    }

    /**
     * 适合控制表单中单个文件大小的方法
     * 
     * @param request
     * @param tmpPath
     * @param savePath
     * @param filesizeMax 单个文件大小，以KB为单位
     */
    public void parse(HttpServletRequest request, String tmpPath, String savePath, long filesizeMax) {
        parameters = new HashMap<String, String>();
        files = "";
        isfilesizeMax = "";
        File tmpDir = null;// 初始化上传文件的临时存放目录
        File saveDir = null;// 初始化上传文件后的保存目录
        tmpDir = new File(tmpPath);
        saveDir = new File(savePath);
        if (!tmpDir.isDirectory()) {
            tmpDir.mkdirs();
        }
        if (!saveDir.isDirectory()) {
            saveDir.mkdirs();
        }
        // 创建一个disk-based文件工厂的项目
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置是否使用临时文件保存解析出的数据的那个临界值，该方法传入的参数的单位是字节。
        factory.setSizeThreshold(sizeThreshold);
        // 设置setSizeThreshold方法中提到的临时文件的存放目录，这里要求使用绝对路径。
        factory.setRepository(tmpDir);
        // 创建一个新文件上传处理模块
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置请求消息实体内容的最大允许大小，以防止客户端故意通过上传特大的文件来塞满服务器端的存储空间，单位为字节。
        upload.setSizeMax(sizeMax);
        // 设置编码
        upload.setHeaderEncoding(encoding);
        try {
            List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                if (item.isFormField())// isFormField方法用于判断FileItem类对象封装的数据是否属于一个普通表单字段，还是属于一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
                {
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    //value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
                    //中文乱码
                    parameters.put(fieldName, value);
                } else {
                    String fileName = item.getName();
                    if (!fileName.equals("")) {
                        // 取上传的文件名
                        if (fileName.lastIndexOf("\\") != -1) {
                            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                        } else {
                            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                        }
                        if (item.getSize() > filesizeMax * 1024) {
                            isfilesizeMax = "上传的文件" + fileName + "大小为：" + item.getSize() / 1024 + "KB,超出了系统限定的大小" + filesizeMax + "BK，请修改文件大小后再上传！";
                            return;
                        } else {
                            /***************************
                             * 20150715鲍彦坡改/ if(fileName.indexOf(".")>-1){
                             * fileName=new
                             * Date().getTime()+CreateRandom.getRandstr(7,
                             * "str")+
                             * fileName.substring(fileName.lastIndexOf("."),
                             * fileName
                             * .length());//fileName;//为了确保上传到服务器的文件名不重复，以毫秒
                             * +上传文件名拼成新的文件名。 }else{ fileName=new
                             * Date().getTime()+CreateRandom.getRandstr(7,
                             * "str") ;//fileName;//为了确保上传到服务器的文件名不重复，以毫秒+
                             * 上传文件名拼成新的文件名。 } /
                             ********************************/
                            // 保存文件到指定目录
                            item.write(new File(saveDir + File.separator + fileName));
                        }
                        files += fileName + "|";
                    }
                    // System.out.println(fileName);
                    String fieldName = item.getFieldName();
                    parameters.put(fieldName, fileName);
                    // System.out.println(fileName+"|"+fieldName);
                }
            }
            if (!files.equals("")) {
                files = files.substring(0, files.lastIndexOf("|"));// files.length()-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuePl_Servlet() {
        super();
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8"); // 这样设置可以让弹出框在IE和火狐下显示正常
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            if (session.getAttribute("USER_ID") == null) {
                String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
                out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
                out.println("window.location='" + url + "';");
                out.println("</script>");
            } else {
                /* 对上传文件夹和临时文件夹进行初始化 */
                String tmpPath = this.getServletContext().getRealPath("") + File.separator + "tmpdir";
                String savePath = this.getServletContext().getRealPath("") + File.separator + "UploadFile" + File.separator + "Service";
                long filesizeMax = 4096;// 通过随机启动的配置文件得到上传图片限定的大小 kB
                parse(request, tmpPath, savePath, filesizeMax);// 调用父类文件上传方法。
                String return_divid = parameters.get("return_divid"); // 成功后返回的刷新DIV的ID
                String return_url = parameters.get("return_url"); // 成功后返回的页面路径
                String xiaoquid = parameters.get("xiaoquid");
                String louid = parameters.get("louid");
                String Un_id = parameters.get("Un_id");
                String cost_startTime = parameters.get("cost_startTime");
                String cost_endTime = parameters.get("cost_endTime");
                String a_id = session.getAttribute("A_ID").toString();
                String ts_id = parameters.get("ts_id");
                if (!isfilesizeMax.equals("")) {
                    out.print("<script>alert(\"" + isfilesizeMax + "\");parent.showselect('" + return_divid + "','" + return_url + "');</script>");
                } else {
                    String FilePathName = savePath + File.separator + files;
                    List<List<String>> list = new Gue_Excel().getMeteList(FilePathName, ts_id, a_id, xiaoquid);
                    if (list == null || list.size() < 1) {
                        out.print("<script>alert('文件上传失败！无法完成更新！请确认订单模版是否修改！');parent.showselect('" + return_divid + "','" + return_url
                                + "');</script>");
                    } else if (list.size() > 50000) {
                        out.print("<script>alert('您模版中的数据过多，请分批导入，每次最多导入50000行数据！');parent.showselect('" + return_divid + "','" + return_url
                                + "');</script>");
                    } else {
                    	String LEVELS=session.getAttribute("LEVELS").toString();
                		String tu_id1 = session.getAttribute("TU_ID").toString();
                        String str = new Gue_Excel().Excel_check_orders1(list, ts_id, xiaoquid, louid, a_id, Un_id,LEVELS,tu_id1);
                        if (!str.equals("")) {
                            out.print("<script>alert('批量定额订单导入信息失败！第" + str + "请修正后再进行导入！');parent.showselect('" + return_divid + "','" + return_url
                                    + "');</script>");
                        } else {
                            boolean bl1 = new Gue_Excel().check_date(list, a_id, ts_id, cost_startTime, cost_endTime, xiaoquid, louid, Un_id);
                            if (bl1) {
                            	//此方法内容太乱了
                                String bl = new Gue_Excel().insert_order(list, a_id, ts_id, cost_startTime, cost_endTime, xiaoquid, louid, Un_id);
                                if (bl.equals("")) {
                                    new ManagerFile().deleteFile(files);
                                    String l_content = "批量导入 [ " + list.size() + " ]户房屋订单";
                                    String adminuser_id = session.getAttribute("USER_ID").toString();
                                    String tu_id = new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
                                    ChangeDao.add_Log(l_content, adminuser_id, "2", tu_id);
                                    out.print("<script>alert('批量导入订单成功！');parent.showselect('" + return_divid + "','" + return_url + "');</script>");
                                } else {
                                    out.print("<script>alert('文件上传失败！无法完成更新！请确认表格数据格式！');parent.showselect('" + return_divid + "','" + return_url
                                            + "');</script>");
                                }
                            } else {
                                out.print("<script>alert('文件上传失败！存在某缴费项的缴费起止日期已存在，请检查！');parent.showselect('" + return_divid + "','" + return_url
                                        + "');</script>");
                            }
                        }
                    }
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
        // Put your code here
    }
}
