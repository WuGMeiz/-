package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.TB_Estate_VillageDao;
import WYCommunity.S_string;

public class TB_Estate_VillageServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor of the object.
     */
    public TB_Estate_VillageServlet() {
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
                String yznr = S_string.formatString(request.getParameter("yznr"));
                String es_id = S_string.formatString(request.getParameter("es_id"));
                String userid = session.getAttribute("USER_ID").toString();
                if (new  TB_Estate_VillageDao().updateEstate(es_id,yznr)) {
                    String l_content="操作员设置验证内容:"+yznr+"";
                    String tu_id = session.getAttribute("TU_ID").toString();
                    new MyTBAdmin_Log().add_Log(l_content,userid, tu_id);
                    out.print("sok-设置登陆项成功");
                } else {
                    out.print("errors-设置登陆项失败");
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
