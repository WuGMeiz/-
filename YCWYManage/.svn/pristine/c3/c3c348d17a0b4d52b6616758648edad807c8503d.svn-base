package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Bean.TBCms_Article;
import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.TB_Village_Dao;
import WYCommunity.T_time;

public class TBCms_Article_add extends HttpServlet {

	private static final long serialVersionUID = -1036456929156042561L;

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
			
		   if (session.getAttribute("USER_ID") == null) {
	            String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
	            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	            out.println("window.location='" + url + "';");
	            out.println("</script>");
	        }
			else
			{
				//System.out.println("bbbbbbbbbbbb"+new T_time().getTime());
				String return_divid = request.getParameter("return_divid");	//成功后返回的刷新DIV的ID
				String return_url = request.getParameter("return_url");	//成功后返回的页面路径
				String close_divid = request.getParameter("close_divid");	//成功后关闭的DIV的ID
				
				String user_id = session.getAttribute("USER_ID").toString();
				String cms_c_id = request.getParameter("cms_c_id_add");	//频道
				
				String cms_a_title = request.getParameter("TITLE");	//标题
				
				String cms_a_source = request.getParameter("cms_a_source");	//来源
				
		        String cms_a_body = request.getParameter("CONTENT");//内容
		        cms_a_body=cms_a_body.replace('\'', '"');  
		        cms_a_body=cms_a_body.replace('＜', '<'); 
		        cms_a_body=cms_a_body.replace('＞', '>');
		        String cms_a_create_time=new T_time().getTime();	//创建时间
		        String cms_a_link=cms_c_id+new T_time().getliushui()+".html";	//生成静态页面名称
		        String cms_a_comment_count="0";	//评论数
		        String cms_a_view_count="0";	//浏览数
		        String status="1";	//状态0不显示1显示
		        String cms_a_order="0";	//频道板块中是否置顶0否1是
		        String cms_index_show="0";	//是否在首页显示0否1是
		        String cms_index_tuijian="0";	//是否在首页推荐阅读0否1是
		        String cms_a_level="0";
		        String admin_user_id=user_id;//"";
		        String audit_time="";
		        String audit_status="1";
       
		        String cms_a_img="";//图片
		        String cms_a_beizhu="";//招聘职位或者专业
		        if(cms_a_beizhu!=null)
		        {
		        	cms_a_beizhu=new String(cms_a_beizhu.getBytes("ISO8859_1"), "UTF-8");
		        	cms_a_beizhu=cms_a_beizhu.replaceAll("\'", "\"");
			        //cms_a_beizhu=cms_a_beizhu.replaceAll("\n", "<br>"); 
		        }
		        
		        
		        TBCms_Article Article=new TBCms_Article();
		        Article.setCms_c_id(cms_c_id);
		        Article.setUser_id(user_id);
		        Article.setCms_a_source(cms_a_source);
		        Article.setCms_a_title(cms_a_title);
		        Article.setCms_a_body(cms_a_body);
		        Article.setCms_a_create_time(cms_a_create_time);
		        Article.setCms_a_comment_count(cms_a_comment_count);
		        Article.setCms_a_view_count(cms_a_view_count);
		        Article.setStatus(status);
		        Article.setCms_a_order(cms_a_order);
		        Article.setCms_a_link(cms_a_link);
		        Article.setCms_index_show(cms_index_show);
		        Article.setCms_a_level(cms_a_level);
		        Article.setAdmin_user_id(admin_user_id);
		        Article.setAudit_time(audit_time);
		        Article.setAudit_status(audit_status);
		        Article.setCms_a_img(cms_a_img);
		        Article.setCms_a_beizhu(cms_a_beizhu==null?"":cms_a_beizhu);
		        Article.setCms_index_tuijian(cms_index_tuijian);
		        Article.setTs_id(session.getAttribute("U_ID").toString());

		        String flag=new TB_Village_Dao().add_Article(Article);
		       
				if(flag.equals(""))
				{
					String l_content="发布文章："+cms_a_title;
					String tu_id = session.getAttribute("TU_ID").toString();
					String userid = session.getAttribute("USER_ID").toString();
				    new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
					out.print("<script>alert('文章发布成功');parent.showselect('"+return_divid+"','"+return_url+"');parent.close_tanchu_div('"+close_divid+"');</script>");
				}
				else
				{
					out.print("<script>alert(\""+flag+"\");</script>");
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
