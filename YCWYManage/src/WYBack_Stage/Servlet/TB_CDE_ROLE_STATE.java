package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTBCommit;
import WYBack_Stage.Dao.MyTB_CDE_ROLE;
import WYBack_Stage.Dao.MyTB_SEV_ORG_DAO;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYCommunity.CreateID;
import WYCommunity.MD5;
import WYCommunity.S_string;
import WYCommunity.T_time;

public class TB_CDE_ROLE_STATE extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor of the object.
     */
    public TB_CDE_ROLE_STATE() {
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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        if (session.getAttribute("USER_ID") == null) {
        	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
        	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
            out.println("window.location='"+url+"';");
            out.println("</script>");
        } else {
            String act = request.getParameter("act");
            if ("1".equals(act)) {//新增角色
                try {
                    String selectOrg_id = S_string.Rep_str(S_string.spacetrim(request.getParameter("Org_id")));
                    String MENU_CODE = request.getParameter("menu_code");
                    String czyname = S_string.Rep_str(S_string.spacetrim(request.getParameter("czyname")));
                    String czyid = S_string.Rep_str(S_string.spacetrim(request.getParameter("czyid")));
                    String czyphone = S_string.Rep_str(S_string.spacetrim(request.getParameter("czyphone")));
                    String description = S_string.Rep_str(S_string.spacetrim(request.getParameter("czyremark")));
                    String stres_id = request.getParameter("stres_id").substring(0,request.getParameter("stres_id").length()-1);
                    String Isdrawback=request.getParameter("Isdrawback");
                    String payWays=request.getParameter("payWays");
                    if(!"".equals(payWays)){
                    	payWays=payWays.substring(0, payWays.length()-1);
                    }
                    List<String> list = new ArrayList<String>();
                    String pass="111111";
                    String password = MD5.getCCBMd5(pass);
                    String create_time = new T_time().getTime();
                    int role_code=new CreateID().getID("TB_CDE_ROLE","role_code");
                    String user_id = session.getAttribute("USER_ID").toString();
                    String czyidall=user_id+"-"+czyid;
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String org_id = session.getAttribute("ORG_ID").toString();
                    String ts_id = new MyTB_SEV_ORG_DAO().getTs_id(org_id);
                    if (new MyTB_CDE_ROLE().checkadd(czyname,tu_id)) {
                        out.print("ok-操作员名已存在，请更换其他名称作为角色名");
                    }else if (new MyTB_SEV_USER().checkadd(czyidall,ts_id)) {
                            out.print("ok-操作员ID已存在，请更换其他ID作为操作员编号");
                        }else {
                            String sql1 ="insert into TB_CDE_ROLE(role_code,role_name,menu_code,role_level,status,tu_id,description,type) values ('"+role_code+"','"+czyname+"','"+MENU_CODE+"', '1','1','"+tu_id+"','"+description+"','2')";
                            String sql2 = "insert into TB_SEV_USER(role_code,userid,password,username,phone,create_time,levels,status,login_ip,u_id,org_id,addr,qq,Isdrawback,payWays) values " +
                                    "     ('"+role_code+"','"+user_id+'-'+czyid+"','"+password+"','"+czyname+"','"+czyphone+"','"+create_time+"','1','1','"+session.getAttribute("LOGIN_IP")+"','"+session.getAttribute("U_ID")+"','"+selectOrg_id+"','"+description+"','"+stres_id+"','"+Isdrawback+"','"+payWays+"')";
                            list.add(sql1);
                            list.add(sql2);
                            String flag = MyTBCommit.TBCommit(list);
                            if (flag.equals("")) 
                            {
                                String l_content = "添加操作员：" + czyname;
                                new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                                out.print("sok-添加操作员成功");
                            } 
                            else
                            {
                                out.print("errors-添加操作员失败");
                            }
                        }
                     
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("2".equals(act)) {//修改角色
                try {
                    List<String> list1 = new ArrayList<String>();
                    String tuid = S_string.formatString(request.getParameter("tu_id"));
                    String userid = S_string.formatString(request.getParameter("userid"));
                    String username = S_string.formatString(request.getParameter("username"));
                    String phone = S_string.formatString(request.getParameter("phone"));
                    String ramark = S_string.formatString(request.getParameter("ramark"));
                    String role_code = S_string.formatString(request.getParameter("role_code"));
                    String stres_id = request.getParameter("stres_id").substring(0,request.getParameter("stres_id").length()-1);
                    String Isdrawback=request.getParameter("Isdrawback");
                    String payWays=request.getParameter("payWays");
                    if(!"".equals(payWays)){
                    	payWays=payWays.substring(0, payWays.length()-1);
                    }
                    String org_id = session.getAttribute("ORG_ID").toString();
                    String ts_id = new MyTB_SEV_ORG_DAO().getTs_id(org_id);
                    String MENU_CODE = request.getParameter("menu_code");
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String user_id = session.getAttribute("USER_ID").toString();
                    String sql1 ="update TB_CDE_ROLE set role_name='"+username+"',menu_code='"+MENU_CODE+"',description='"+ramark+"' where role_code='"+role_code+"' ";
                    String sql2="update TB_SEV_USER set username='"+username+"',phone='"+phone+"',addr='"+ramark+"',qq='"+stres_id+"', Isdrawback='"+Isdrawback+"',payWays='"+payWays+"' where role_code='"+role_code+"' and u_id='"+ts_id+"' ";
                    list1.add(sql1);
                    list1.add(sql2);
                    String flag = MyTBCommit.TBCommit(list1);
                    if (flag.equals("")){
                        String l_content = "修改操作员：" + username;
                        new MyTBAdmin_Log().add_Log(l_content, user_id, tu_id);
                        out.print("sok-修改操作员成功！");
                } else {
                    out.print("errors-修改操作员失败！");
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(act)) {//删除角色
                try {
                    List<String> list2 = new ArrayList<String>();
                    String userid = request.getParameter("userid");
                    String name = request.getParameter("czyname");
                    String u_id = session.getAttribute("U_ID").toString();
                    String tu_id = session.getAttribute("TU_ID").toString();
                    String user_id = session.getAttribute("USER_ID").toString();
                    String sql1 ="update TB_SEV_USER set status='0' where userid='"+userid+"' and u_id='"+u_id+"' ";
                    
                    String sql2 ="update TB_CDE_ROLE set status='0' where role_code='"+new MyTB_SEV_USER().rolecode(userid,u_id)+"' ";
                    list2.add(sql1);
                    list2.add(sql2);
                    String flag = MyTBCommit.TBCommit(list2);
                    if (flag.equals("")){
                        String l_content = "删除操作员："+name;
                        new MyTBAdmin_Log().add_Log(l_content, user_id, tu_id);
                        out.print("sok-删除角色成功！");
                    } else {
                        out.print("errors-删除角色失败！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.flush();
            out.close();
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
