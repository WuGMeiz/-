package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import WYBack_Stage.Dao.Mete_ReadClass;

public class MeteDelete_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public MeteDelete_Servlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		response.setContentType("text/html;charset=UTF-8"); //这样设置可以让弹出框在IE和火狐下显示正常
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String Em_id = request.getParameter("Em_id");
		String Ma_id = request.getParameter("ma_id");
		boolean bl = new Mete_ReadClass().MeteDelete(Integer.parseInt(Em_id),Ma_id);
		if(bl){
			String l_content="删除表编号为："+Ma_id+"的抄表信息";
			String adminuser_id=session.getAttribute("USER_ID").toString();
			String tu_id=new Mete_ReadClass().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
			Mete_ReadClass.add_Log(l_content, adminuser_id, tu_id,"2");
			out.print("ssok-删除成功");
		}else{
			out.print("ok-删除失败");
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
