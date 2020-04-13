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
import WYBack_Stage.Dao.MyTB_Build_DAO;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYCommunity.S_string;
import WYCommunity.T_time;

/**
 * <p> 物业楼宇servlet <p>
 * 
 * @date 2018-3-7 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class TB_Estate_Build_SERVLET extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public TB_Estate_Build_SERVLET() {
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
                if ("1".equals(args)) {// 新增楼宇
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String Es_id = request.getParameter("es_id");
                    String qianzhui = S_string.formatString(request.getParameter("qianzhui"));
                    String qsBuName = request.getParameter("qsBuName");
                    String zzBuName = S_string.formatString(request.getParameter("zzBuName"));
                    String houzhui = S_string.formatString(request.getParameter("houzhui"));
                    String BuTurn = S_string.formatString(request.getParameter("BuTurn"));
                    String BuType = S_string.formatString(request.getParameter("BuType"));
                    String BuStru = S_string.formatString(request.getParameter("BuStru"));
                    String BuNumber = S_string.formatString(request.getParameter("BuNumber"));
                    String UnitNumber = S_string.formatString(request.getParameter("UnitNumber"));
                    String HouseNumber = S_string.formatString(request.getParameter("HouseNumber"));
                    String TotalArea = S_string.formatString(request.getParameter("TotalArea"));
                    String BuildArea = S_string.formatString(request.getParameter("BuildArea"));
                    String UseArea = S_string.formatString(request.getParameter("UseArea"));
                    String FinishDate = S_string.formatString(request.getParameter("FinishDate"));
                    String BuHead = S_string.formatString(request.getParameter("BuHead"));
                    String remark = S_string.formatString(request.getParameter("remark"));
                    if (!zzBuName.equals("")) {
                        int k = Integer.parseInt(qsBuName);
                        int j = Integer.parseInt(zzBuName);
                        for (int i = k; i <= j; i++) {
                            String louhao = qianzhui + i + houzhui;
                            if (new MyTB_Build_DAO().checkadd1(ts_id, louhao, Es_id)) {
                                out.print("errors-添加失败!楼宇名称不能重复输入！");
                                return;
                            } else {
                                String time = new T_time().getTime();
                                boolean b = new MyTB_Build_DAO().lyadd(ts_id, Es_id, louhao, BuTurn, BuType, BuStru, BuNumber, UnitNumber,
                                        HouseNumber, TotalArea, BuildArea, UseArea, FinishDate, BuHead, remark, time);
                                if (b) {
                                    String userid = session.getAttribute("USER_ID").toString();
                                    String l_content = "添加楼宇：" + i;
                                    try {
                                        new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    if (i == j) {
                                        out.print("ssok-添加楼宇成功！");
                                        return;
                                    }
                                } else {
                                    out.print("errors-添加楼宇失败!");
                                    return;
                                }
                            }
                        }
                    } else {
                        String louhao1 = qianzhui + qsBuName + houzhui;
                        if (new MyTB_Build_DAO().checkadd1(ts_id, louhao1, Es_id)) {
                            out.print("errors-添加失败!楼宇名称不能重复输入！");
                            return;
                        } else {
                            String time = new T_time().getTime();
                            boolean b = new MyTB_Build_DAO().lyadd1(ts_id, Es_id, louhao1, BuTurn, BuType, BuStru, BuNumber, UnitNumber, HouseNumber,
                                    TotalArea, BuildArea, UseArea, FinishDate, BuHead, remark, time);
                            if (b) {
                                String userid = session.getAttribute("USER_ID").toString();
                                String l_content = "添加楼宇：" + qsBuName;
                                try {
                                    new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                out.print("ssok-添加楼宇成功！");
                            } else {
                                out.print("errors-添加楼宇失败!");
                            }
                            return;
                        }
                    }
                }
                if ("2".equals(args)) {// 修改楼宇信息
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String Bu_id = request.getParameter("Bu_id");
                    String BuName = S_string.formatString(request.getParameter("BuName"));
                    String BuTurn = S_string.formatString(request.getParameter("BuTurn"));
                    String BuType = S_string.formatString(request.getParameter("BuType"));
                    String BuStru = S_string.formatString(request.getParameter("BuStru"));
                    String BuNumber = S_string.formatString(request.getParameter("BuNumber"));
                    String UnitNumber = S_string.formatString(request.getParameter("UnitNumber"));
                    String HouseNumber = S_string.formatString(request.getParameter("HouseNumber"));
                    String TotalArea = S_string.formatString(request.getParameter("TotalArea"));
                    String BuildArea = S_string.formatString(request.getParameter("BuildArea"));
                    String UseArea = S_string.formatString(request.getParameter("UseArea"));
                    String FinishDate = S_string.formatString(request.getParameter("FinishDate"));
                    String BuHead = S_string.formatString(request.getParameter("BuHead"));
                    String remark = S_string.formatString(request.getParameter("remark"));
                    String es_id = new MyTB_Build_DAO().getEsid(Bu_id, ts_id);
                    List<String> namelist = new MyTB_Build_DAO().findTBBuildNamenot(ts_id, es_id, Bu_id);
                    if (namelist.contains(BuName)) {
                        out.print("errors-修改失败!该小区下已有该楼号！");
                        return;
                    } else {
                        boolean b = new MyTB_Build_DAO().louyuxg(ts_id, Bu_id, BuName, BuTurn, BuType, BuStru, BuNumber, UnitNumber, HouseNumber,
                                TotalArea, BuildArea, UseArea, FinishDate, BuHead, remark);
                        if (b) {
                            String userid = session.getAttribute("USER_ID").toString();
                            String l_content = "修改楼宇：" + BuName;
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("cssok-修改楼宇信息成功！");
                        } else {
                            out.print("errors-修改楼宇信息失败!");
                        }
                        return;
                    }
                }
                if ("3".equals(args)) {// 删除楼宇信息
                    String Bu_id = request.getParameter("Bu_id");
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    if(new MyTB_Build_DAO().checkDy(Bu_id, ts_id)){
                    	out.print("errors-删除失败!请先删除楼宇下的单元信息！");
                    	return;
                    }else{
                    	boolean bl = new MyTB_Build_DAO().checkfw(Bu_id, ts_id);
                        if (bl) {
                            out.print("errors-删除失败!请先删除楼宇下的房屋信息！");
                            return;
                        } else {
                            boolean b = new MyTB_Build_DAO().delete_TB_Build(Bu_id, ts_id);
                            if (b) {
                                String userid = session.getAttribute("USER_ID").toString();
                                String buName = new MyTB_Build_DAO().getBuName(Bu_id, ts_id);
                                String l_content = "删除楼宇：" + buName;
                                try {
                                    new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                out.print("ssok-删除楼宇信息成功！");
                            } else {
                                out.print("errors-删除楼宇信息失败!");
                            }
                            return;
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
