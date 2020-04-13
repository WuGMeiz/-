package WYBack_Stage.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.ChangeDao;
import WYBack_Stage.Dao.quartersDao;
import WYCommunity.ManagerFile;
import WYCommunity.MutiFileUpload;

public class WyTB_House_addpl extends MutiFileUpload {
    /**
     * 
     */
    private static final long serialVersionUID = 1201816321299189050L;

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
                long filesizeMax = 4096;// 通过随机启动的配置文件得到上传图片限定的大小
                parse(request, tmpPath, savePath, filesizeMax);// 调用父类文件上传方法。
                String ts_id = session.getAttribute("U_ID").toString();
                String return_divid = parameters.get("return_divid"); // 成功后返回的刷新DIV的ID
                String return_url = parameters.get("return_url"); // 成功后返回的页面路径
                if (!isfilesizeMax.equals("")) {
                    out.print("<script>alert(\"" + isfilesizeMax + "\");parent.showselect('" + return_divid + "','" + return_url + "');</script>");
                } else {
                    String org_id = parameters.get("Bu_id");// 楼宇id
                    String Es_id = parameters.get("Es_id");//小区id
                    String dy_id = parameters.get("Un_id");//单元id
                    String EhType= parameters.get("EhType");//房屋类型
                    String FilePathName = savePath + File.separator + files;
                    List<Map<String, String>> list = new quartersDao().getTbPay_User_ReadExcel(FilePathName);
                    if (list == null || list.size() < 1) {
                        out.print("<script>alert('导入失败，请按导入模板格式填写导入文件！');parent.showselect('" + return_divid + "','" + return_url + "');</script>");
                    } else if (list.size() > 50000) {
                        out.print("<script>alert('导入数据过多，请分批导入，每次最多导入50000行数据！');parent.showselect('" + return_divid + "','" + return_url
                                + "');</script>");
                    } else {
                        String str = new quartersDao().Excel_check_TBPay_User1(list, org_id, ts_id, Es_id);
                        if (!str.equals("")) {
                            if (str.length() > 100) {
                                out.print("<script>parent.showAlertMsg('批量更新房屋信息失败！失败原因：" + str + "','" + return_divid + "','" + return_url
                                        + "');</script>");
                            } else {
                                out.print("<script>alert('批量添加房屋信息失败！失败原因：" + str + " ');parent.showselect('" + return_divid + "','" + return_url
                                        + "');</script>");
                            }
                        } else {
                            String bl = new quartersDao().insertTBHouse(dy_id,list, Es_id, ts_id, org_id,EhType);
                            if (bl.equals("")) {
                                new ManagerFile().deleteFile(files);
                                String l_content = "批量导入房屋信息 [ " + list.size() + " ]人";
                                String adminuser_id = session.getAttribute("USER_ID").toString();
                                String tu_id = new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
                                ChangeDao.add_Log(l_content, adminuser_id, "2", tu_id);
                                out.print("<script>alert('批量导入房屋信息成功，共导入房屋信息 【 " + list.size() + " 】人！');parent.showselect('" + return_divid + "','"
                                        + return_url + "');</script>");
                            } else {
                                if (bl.length() > 100) {
                                    out.print("<script>parent.showAlertMsg('导入失败！" + bl + "请核对导入信息。','" + return_divid + "','" + return_url
                                            + "');</script>");
                                } else {
                                    out.print("<script>alert('导入房屋信息失败！" + bl + "请核对导入信息。');parent.showselect('" + return_divid + "','" + return_url
                                            + "');</script>");
                                }
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
}
