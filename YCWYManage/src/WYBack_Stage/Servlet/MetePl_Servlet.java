package WYBack_Stage.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYCommunity.ManagerFile;
import WYCommunity.MeteChaobiao_Excel;
import WYCommunity.MutiFileUpload;

public class MetePl_Servlet extends MutiFileUpload {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the object.
     */
    public MetePl_Servlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
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
                String type = parameters.get("type");
                String unit = parameters.get("unit");
                String ts_id = parameters.get("ts_id");
                if (!isfilesizeMax.equals("")) {
                    out.print("<script>alert(\"" + isfilesizeMax + "\");parent.showselect('" + return_divid + "','" + return_url + "');</script>");
                } else {
                    String FilePathName = savePath + File.separator + files;
                    List<List<String>> list = new MeteChaobiao_Excel().getMeteList(FilePathName);
                    if (list != null) {
                        if (list.size() > 50000) {
                            out.print("<script>alert('您模版中的数据过多，请分批导入，每次最多导入50000行数据！');parent.showselect('" + return_divid + "','" + return_url
                                    + "');</script>");
                        } else {
                        	String LEVELS=session.getAttribute("LEVELS").toString();
                    		String tu_id = session.getAttribute("TU_ID").toString();
                            String str = new MeteChaobiao_Excel().Excel_check_orders1(list, ts_id, xiaoquid, louid, type, Un_id,LEVELS,tu_id);
                            if (!str.equals("")) {
                                out.print("<script>alert('批量抄表信息失败！第" + str + "请修正后再进行导入！');parent.showselect('" + return_divid + "','" + return_url
                                        + "');</script>");
                            } else {
                                String bl = new MeteChaobiao_Excel().insert_order(list, ts_id, type, unit, xiaoquid, louid, Un_id);
                                if (bl.equals("")) {
                                    new ManagerFile().deleteFile(files);
                                    out.print("<script>alert('批量导入订单成功！共导入条" + list.size() + "数据');parent.showselect('" + return_divid + "','"
                                            + return_url + "');</script>");
                                } else {
                                    out.print("<script>alert('文件上传失败！无法完成更新！请确认表格数据格式！');parent.showselect('" + return_divid + "','" + return_url
                                            + "');</script>");
                                }
                            }
                        }
                    } else {
                        out.print("<script>alert('文件上传失败！无法完成更新！请确认订单模版是否修改！');parent.showselect('" + return_divid + "','" + return_url
                                + "');</script>");
                    }
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }
}
