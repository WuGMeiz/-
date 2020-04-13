/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYBack_Stage.Dao.TB_Village_Dao;
import WYCommunity.S_string;
import WYCommunity.T_time;

// import manage.dao.AdminLogin;
/**
 * <p> 物业小区servlet <p>
 * 
 * @date 2018-3-5 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class TB_Estate_Village_SERVLET extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public TB_Estate_Village_SERVLET() {
        super();
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String args = request.getParameter("args");
        if (session.getAttribute("USER_ID") == null) {
            String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
            out.println("window.location='" + url + "';");
            out.println("</script>");
        } else {
            try {
                if ("1".equals(args)) {// 新增小区
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String EsName = S_string.formatString(request.getParameter("EsName"));
                    String EsHead = S_string.formatString(request.getParameter("EsHead"));
                    String EsContact = S_string.formatString(request.getParameter("EsContact"));
                    String EsPhone = S_string.formatString(request.getParameter("EsPhone"));
                    String EsAddress = S_string.formatString(request.getParameter("EsAddress"));
                    String build_Number = S_string.formatString(request.getParameter("build_Number"));
                    String House_Number = S_string.formatString(request.getParameter("House_Number"));
                    String BuildArea = S_string.formatString(request.getParameter("BuildArea"));
                    String FloorArea = S_string.formatString(request.getParameter("FloorArea"));
                    String GreenArea = S_string.formatString(request.getParameter("GreenArea"));
                    String VolumeRate = S_string.formatString(request.getParameter("VolumeRate"));
                    String remark = S_string.formatString(request.getParameter("remark"));
                    if (new TB_Village_Dao().checkadd(ts_id, EsName)) {
                        out.print("errors-添加失败!小区名称不能重复输入！");
                    } else {
                        String time = new T_time().getTime();
                        boolean b = new TB_Village_Dao().xqadd(ts_id, EsName, EsHead, EsContact, EsPhone, EsAddress, build_Number, House_Number,
                                BuildArea, FloorArea, GreenArea, VolumeRate, remark, time);
                        if (b) {
                            String userid = session.getAttribute("USER_ID").toString();
                            String l_content = "添加小区：" + EsName;
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("ssok-添加小区成功！");
                        } else {
                            out.print("errors-添加小区失败!");
                        }
                    }
                }
                if ("2".equals(args)) {// 修改小区信息
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String Es_id = request.getParameter("Es_id");
                    String EsName = S_string.formatString(request.getParameter("EsName"));
                    String EsHead = S_string.formatString(request.getParameter("EsHead"));
                    String EsContact = S_string.formatString(request.getParameter("EsContact"));
                    String EsPhone = S_string.formatString(request.getParameter("EsPhone"));
                    String EsAddress = S_string.formatString(request.getParameter("EsAddress"));
                    String build_Number = S_string.formatString(request.getParameter("build_Number"));
                    String House_Number = S_string.formatString(request.getParameter("House_Number"));
                    String BuildArea = S_string.formatString(request.getParameter("BuildArea"));
                    String FloorArea = S_string.formatString(request.getParameter("FloorArea"));
                    String GreenArea = S_string.formatString(request.getParameter("GreenArea"));
                    String VolumeRate = S_string.formatString(request.getParameter("VolumeRate"));
                    String remark = S_string.formatString(request.getParameter("remark"));
                    List<String> namelist = new TB_Village_Dao().findTBVillageNamenot(ts_id, Es_id);
                    if (namelist.contains(EsName)) {
                        out.print("errors-修改失败!已有该小区名称！");
                    } else {
                        boolean b = new TB_Village_Dao().xqxg(ts_id, Es_id, EsName, EsHead, EsContact, EsPhone, EsAddress, build_Number,
                                House_Number, BuildArea, FloorArea, GreenArea, VolumeRate, remark);
                        if (b) {
                            String userid = session.getAttribute("USER_ID").toString();
                            String l_content = "修改小区：" + EsName;
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("ssok-修改小区成功！");
                        } else {
                            out.print("errors-修改小区失败!");
                        }
                    }
                }
                if ("3".equals(args)) {// 删除小区信息
                    String Es_id = request.getParameter("Es_id");
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    boolean bl = new TB_Village_Dao().checklouyu(Es_id, ts_id);
                    if (bl) {
                        out.print("errors-删除失败!请先删除小区下的楼宇信息！");
                    } else {
                        boolean b = new TB_Village_Dao().delete_TB_Village(Es_id, ts_id);
                        if (b) {
                            String userid = session.getAttribute("USER_ID").toString();
                            String esname = new TB_Village_Dao().getEsname(Es_id, ts_id);
                            String l_content = "删除小区：" + esname;
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("ssok-删除小区信息成功！");
                        } else {
                            out.print("errors-删除小区信息失败!");
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

    public void init() throws ServletException {
        // Put your code here
    }
}
