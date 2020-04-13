package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import WYBack_Stage.Dao.Mete_ReadClass;

public class MeteUpate_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public MeteUpate_Servlet() {
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
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				HttpSession session =request.getSession();
				String em_id = request.getParameter("em_id");
				String Maid = request.getParameter("Maid");
				String LastReadNum = request.getParameter("LastReadNum");
				String NowReadNum = request.getParameter("NowReadNum");
				String UserNumber = request.getParameter("UserNumber");
				String ReadDate = request.getParameter("ReadDate");
				String MeteType = request.getParameter("MeteType");
				String Unit = request.getParameter("Unit");
				Map<String,String> map = new HashMap<String,String>();
				map.put("Maid", Maid);
				map.put("LastReadNum", LastReadNum);
				map.put("NowReadNum", NowReadNum);
				map.put("UserNumber", UserNumber);
				map.put("ReadDate", ReadDate);
				map.put("MeteType", MeteType);
				map.put("Unit", Unit);
				boolean bl = new Mete_ReadClass().UpdateMa(em_id, map);
				if (bl==true) 
				{
					String l_content="修改表编号为："+Maid+"的抄表信息";
					String adminuser_id=session.getAttribute("USER_ID").toString();
					String tu_id=new Mete_ReadClass().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
					Mete_ReadClass.add_Log(l_content, adminuser_id, tu_id,"2");
					out.print("cssok-修改订单成功");
				} 
				else
				{
					out.print("errors-修改订单失败");
				}
					
				out.flush();
				out.close();
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
