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
import WYBack_Stage.Dao.MyTB_House_DAO;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYCommunity.S_string;
import WYCommunity.T_time;

/**
 * <p> 物业房屋servlet <p>
 * 
 * @date 2018-3-7 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class TB_Estate_House_SERVLET extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public TB_Estate_House_SERVLET() {
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
        response.setContentType("text/html");
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
                if ("1".equals(args)) {// 新增房屋
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = session.getAttribute("U_ID").toString();
                    String Es_id = request.getParameter("Es_id");
                    String Bu_id = request.getParameter("Bu_id");
                    String Un_id1 = request.getParameter("Un_id1");
                    String EhNumber = request.getParameter("EhNumber").trim();
                    String EhName = request.getParameter("EhName");
                    String EhNature = S_string.formatString(request.getParameter("EhNature"));
                    String EhStru = S_string.formatString(request.getParameter("EhStru"));
                    String EhType = S_string.formatString(request.getParameter("EhType"));
                    String EhTurn = S_string.formatString(request.getParameter("EhTurn"));
                    String EhStatus = S_string.formatString(request.getParameter("EhStatus"));
                    String PropertyRight = S_string.formatString(request.getParameter("PropertyRight"));
                    String HousingUse = S_string.formatString(request.getParameter("HousingUse"));
                    String OftenNumber = S_string.formatString(request.getParameter("OftenNumber"));
                    String OwnerName = S_string.formatString(request.getParameter("ownerName"));
                    String OwnerPhone = S_string.formatString(request.getParameter("OwnerPhone"));
                    String BuildArea = S_string.formatString(request.getParameter("BuildArea"));
                    String UseArea = S_string.formatString(request.getParameter("UseArea"));
                    String HeatingArea = S_string.formatString(request.getParameter("HeatingArea"));
                    String CarNum = S_string.formatString(request.getParameter("CarNum"));
                    String remark = S_string.formatString(request.getParameter("remark"));
                    String handIs = S_string.formatString(request.getParameter("handIs"));
                    String storer = S_string.formatString(request.getParameter("storer"));
                    if (new MyTB_House_DAO().checkadd(ts_id, EhNumber, Es_id)) {
                        out.print("errors-添加失败!同一小区下房屋编号不能重复输入！");
                    } else {
                        String time = new T_time().getTime();
                        boolean b = new MyTB_House_DAO().fwadd(ts_id, Es_id, Bu_id,Un_id1, EhNumber, EhName, EhNature, EhStru, EhType, EhTurn, EhStatus,
                                PropertyRight, HousingUse, OftenNumber, OwnerName, OwnerPhone, BuildArea, UseArea, HeatingArea, CarNum, remark, time,storer,handIs);
                        if (b) {
                            String userid = session.getAttribute("USER_ID").toString();
                            String l_content = "添加房屋，房屋编号：" + EhNumber;
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("ssok-添加房屋成功！");
                        } else {
                            out.print("errors-添加房屋失败!");
                        }
                    }
                }
                if ("2".equals(args)) {// 修改房屋信息
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String Eh_id = request.getParameter("Eh_id");
                    String EhNumber = request.getParameter("EhNumber");
                    String EhName = request.getParameter("EhName");
                    String EhNature = S_string.formatString(request.getParameter("EhNature"));
                    String EhStru = S_string.formatString(request.getParameter("EhStru"));
                    String EhType = S_string.formatString(request.getParameter("EhType"));
                    String EhTurn = S_string.formatString(request.getParameter("EhTurn"));
                    String EhStatus = S_string.formatString(request.getParameter("EhStatus"));
                    String PropertyRight = S_string.formatString(request.getParameter("PropertyRight"));
                    String HousingUse = S_string.formatString(request.getParameter("HousingUse"));
                    String OftenNumber = S_string.formatString(request.getParameter("OftenNumber"));
                    String OwnerName = S_string.formatString(request.getParameter("OwnerName"));
                    String OwnerPhone = S_string.formatString(request.getParameter("OwnerPhone"));
                    String BuildArea = S_string.formatString(request.getParameter("BuildArea"));
                    String UseArea = S_string.formatString(request.getParameter("UseArea"));
                    String HeatingArea = S_string.formatString(request.getParameter("HeatingArea"));
                    String CarNum = S_string.formatString(request.getParameter("CarNum"));
                    String remark = S_string.formatString(request.getParameter("remark"));
                    String handIs = S_string.formatString(request.getParameter("handIs"));
                    String storer = S_string.formatString(request.getParameter("storer"));
                    String es_id = new MyTB_House_DAO().getEsid(ts_id, Eh_id);
                    List<String> namelist = new MyTB_House_DAO().findTBHouseNamenot(ts_id, es_id, Eh_id);
                    if (namelist.contains(EhNumber)) {
                        out.print("errors-修改失败!该小区下已有该房屋编号！");
                    } else {
                        boolean b = new MyTB_House_DAO().fangwuxg(ts_id, Eh_id, EhNumber, EhName, EhNature, EhStru, EhType, EhTurn, EhStatus,
                                PropertyRight, HousingUse, OftenNumber, OwnerName, OwnerPhone, BuildArea, UseArea, HeatingArea, CarNum, remark,Integer.valueOf(handIs),Integer.valueOf(storer));
                        if (b) {
                        	
                            String userid = session.getAttribute("USER_ID").toString();
                            String l_content = "修改房屋编号：" + EhNumber;
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("cssok-修改房屋信息成功！");
                        } else {
                            out.print("errors-修改房屋信息失败!");
                        }
                    }
                }
                if ("3".equals(args)) {// 删除房屋信息
                    String Eh_id = request.getParameter("Eh_id");
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    boolean b = new MyTB_House_DAO().delete_TB_House(Eh_id, ts_id);
                    if (b) {
                        String userid = session.getAttribute("USER_ID").toString();
                        String ehNumber = new MyTB_House_DAO().getEhNumber(Eh_id, ts_id);
                        String l_content = "删除房屋编号：" + ehNumber;
                        try {
                            new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        out.print("ssok-删除房屋信息成功！");
                    } else {
                        out.print("errors-删除房屋信息失败!");
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
