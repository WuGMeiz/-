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
import WYCommunity.T_time;


public class TBCms_Article_update extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
			//response.setCharacterEncoding("gb2312");
			response.setContentType("text/html;charset=UTF-8"); //这样设置可以让弹出框在IE和火狐下显示正常
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			 
			if(session.getAttribute("USER_ID")==null)
			{			
				out.print("sessionOvertime-您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！");				
			}
			else
			{

				
				String return_divid = request.getParameter("return_divid");	//成功后返回的刷新DIV的ID
				String return_url = request.getParameter("return_url");	//成功后返回的页面路径
				String close_divid = request.getParameter("close_divid");	//成功后关闭的DIV的ID
				String cms_a_id = request.getParameter("cms_a_id");	//文章内容表ID
				String user_id = session.getAttribute("USER_ID").toString();
				
				String cms_c_id = request.getParameter("cms_c_id_update");	//频道
				
				String cms_a_title = request.getParameter("TITLE");	//标题

				String cms_a_source = request.getParameter("cms_a_source");	//来源
				
		        String cms_a_body = request.getParameter("CONTENT");//内容
		        cms_a_body=cms_a_body.replace('\'', '"');
		        cms_a_body=cms_a_body.replace('＜', '<'); 
		        cms_a_body=cms_a_body.replace('＞', '>');
		        String cms_a_create_time=new T_time().getTime();	//修改时间
		     
		        String flag=new TB_Village_Dao().update_Article(cms_c_id, user_id, cms_a_source, cms_a_title, cms_a_body, cms_a_create_time, cms_a_id);
		       
				if(flag.equals(""))
				{	
					String l_content="修改文章："+cms_a_title;
					String tu_id = session.getAttribute("TU_ID").toString();
					String userid = session.getAttribute("USER_ID").toString();
				    new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
					
					return_url=return_url+"?cms_c_id="+cms_c_id;
					out.print("<script>alert('文章修改成功');parent.showselect('"+return_divid+"','"+return_url+"');parent.close_tanchu_div('"+close_divid+"');</script>");
				}
				else
				{
					out.print("<script>alert(\""+flag+"\");</script>");
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
