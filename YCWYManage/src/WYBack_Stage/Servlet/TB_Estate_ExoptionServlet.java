package WYBack_Stage.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import WYBack_Stage.Bean.TB_Estate_Exoption;
import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.TB_Estate_ExoptionDao;
import WYBack_Stage.Dao.TB_Estate_RepPeopleDao;
import WYCommunity.MutiFileUpload;

/**
 * Servlet implementation class TB_Estate_ExoptionServlet
 */
public class TB_Estate_ExoptionServlet extends MutiFileUpload{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TB_Estate_ExoptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
	    if (session.getAttribute("USER_ID") == null) {
            String url = request.getContextPath() + "/WYPage/BackPage/login.jsp";
            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
            out.println("window.location='" + url + "';");
            out.println("</script>");
        } else {
        	String arg = request.getParameter("arg");
        	try{
        		if("2".equals(arg)){//删除
               	 int op_id = Integer.parseInt(request.getParameter("op_id").toString());
               	 boolean bl = new TB_Estate_ExoptionDao().deleted_Exoption(op_id);
                    if (bl) {
                    	session.setAttribute("To_id", request.getParameter("To_id").toString());
                        out.print("ssok-选项删除成功");
                    } else {
                        out.print("errors-选项删除失败");
                    }
           	 	}
//        		 out.flush();
//                 out.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }
        }
	}

}
