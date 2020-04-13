package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.SystempasDAO;
import WYCommunity.MD5;

public class WuYePasWordServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor of the object.
     */
    public WuYePasWordServlet() {
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
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession session=request.getSession();
            if(session.getAttribute("USER_ID")==null) {         
            	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
            	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
                out.println("window.location='"+url+"';");
                out.println("</script>");
            } else {
                String newPassword = new MD5().createMd5(request.getParameter("newPassword"));
                String oldpassword = new MD5().createMd5(request.getParameter("oldPassword"));
                String userid = session.getAttribute("USER_ID").toString();
                String tu_id = session.getAttribute("TU_ID").toString();
                String ts_id = session.getAttribute("U_ID").toString();
                if(new SystempasDAO().checkPassword(userid,oldpassword)){
                    if (new  SystempasDAO().updateUserInfo(userid,newPassword,tu_id,ts_id)) {
                        String l_content="操作员修改密码";
                        new MyTBAdmin_Log().add_Log(l_content,userid, tu_id);
                        out.print("sok-密码修改成功");
                    } else {
                        out.print("errors-密码修改失败");
                    }
                }else {
                    out.print("errors-密码修改失败！旧密码输入有误！");
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
