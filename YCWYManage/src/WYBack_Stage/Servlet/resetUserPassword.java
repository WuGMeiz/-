package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTB_House_DAO;
import WYCommunity.MD5;

public class resetUserPassword extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor of the object.
     */
    public resetUserPassword() {
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
     * This method is called when a form has its tag value method equals to post.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession session=request.getSession();
            if(session.getAttribute("USER_ID")==null)
            {           
            	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
            	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
                out.println("window.location='"+url+"';");
                out.println("</script>");
                
            }
            else
            {
                String eh_id = request.getParameter("eh_id");
                String password = request.getParameter("password");
                String resetPassword = new MD5().createMd5(password);
                String tu_id= session.getAttribute("TU_ID").toString();
                String tablename="TB_Estate_House";
                if (new MyTB_House_DAO().resetPassword(eh_id, resetPassword,tablename)) 
                {
                    String l_content="给管理员用户账号："+new MyTB_House_DAO().getuserid(eh_id, tablename)+"重置密码。";
                    String adminuser_id=session.getAttribute("USER_ID").toString();
                    new MyTBAdmin_Log().add_Log(l_content,adminuser_id, tu_id);
                    out.print("xok-密码重置成功");
                } 
                else 
                {
                    out.print("errors-密码重置失败");
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
