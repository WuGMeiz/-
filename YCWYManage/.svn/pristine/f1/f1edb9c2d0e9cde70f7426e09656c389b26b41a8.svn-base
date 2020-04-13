package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.TB_Village_Dao;


public class TBCms_Article_delete extends HttpServlet {

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

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			
			if (session.getAttribute("USER_ID") == null) {
	            String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
	            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	            out.println("window.location='" + url + "';");
	            out.println("</script>");
	        }
			else
			{

				String cms_a_id=request.getParameter("cms_a_id");

				boolean bl=new TB_Village_Dao().delete_Article(cms_a_id);
	
				if(bl)
				{
					
					String l_content="删除文章："+new TB_Village_Dao().getArticle_title(cms_a_id);
					String tu_id = session.getAttribute("TU_ID").toString();
					String userid = session.getAttribute("USER_ID").toString();
				    new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
					
					out.print("ssok-删除频道文章内容成功！");
				}
				else
				{
					out.print("errors-删除频道文章内容失败！");
				}
			}
			out.flush();
			out.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
