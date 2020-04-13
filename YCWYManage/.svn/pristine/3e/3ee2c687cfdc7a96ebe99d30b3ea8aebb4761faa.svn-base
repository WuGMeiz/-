package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTB_SEV_ORG_DAO;
import WYCommunity.S_string;

public class UpdateOrgServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor of the object.
     */
    public UpdateOrgServlet() {
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
        request.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("GB2312");
        response.setContentType("text/html;charset=UTF-8"); //这样设置可以让弹出框在IE和火狐下显示正常
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        if(session.getAttribute("USER_ID")==null) {         
        	String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
        	out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
            out.println("window.location='"+url+"';");
            out.println("</script>");
        } else {
        String org_id=S_string.formatString(request.getParameter("org_id").toString());
        String org_name=S_string.formatString(request.getParameter("org_name").toString());
        String remark=S_string.formatString(request.getParameter("remark").toString());
        String user_id = session.getAttribute("USER_ID").toString();
        String tu_id = session.getAttribute("TU_ID").toString();
        String ts_id = session.getAttribute("U_ID").toString();
        /*String ts_id = new MyTB_SEV_ORG_DAO().getTs_id(org_id);
        if(new MyTB_SEV_ORG_DAO().checkadd(org_name, ts_id)) {
            out.print("errors-添加失败!单位名称不能重复输入！");
        } else {*/
            boolean b = new MyTB_SEV_ORG_DAO().updateOrg(org_id,org_name,remark,ts_id);
            if(b==true){
                String l_content="修改机构："+org_name;
                new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                out.print("sok-修改机构成功");
                
            } 
            else
            {
                out.print("errors-修改机构失败");
            }
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
