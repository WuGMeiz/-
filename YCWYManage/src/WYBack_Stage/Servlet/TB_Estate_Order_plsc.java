package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.ChangeDao;
import WYBack_Stage.Dao.MyTB_Estate_Order;


public class TB_Estate_Order_plsc extends HttpServlet {
	
	private static final long serialVersionUID = 2942908169760149567L;

	public TB_Estate_Order_plsc() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String args = request.getParameter("args");
		if(session.getAttribute("USER_ID")==null) {
			String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
			out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
		    out.println("window.location='"+url+"';");
		    out.println("</script>");
		} else {
			try {
				//批量删除订单
					
					String tu_id=session.getAttribute("TU_ID").toString();
					String ts_id=session.getAttribute("U_ID").toString();
					String user_id=session.getAttribute("USER_ID").toString();
				
					String sqlstr=request.getParameter("sqlstr");//以“;” 分隔的订单号
					boolean flag = new MyTB_Estate_Order().delete_order_pl(ts_id, sqlstr);
					if(flag==true) {
						int offset = 0;
						int cnt = 0;
						while((offset = sqlstr.indexOf(";", offset)) != -1){
							offset = offset + ";".length();
							cnt++;
						}
						
						String l_content="批量删除订单："+cnt+"个,"+"订单号:"+sqlstr;
						new ChangeDao().add_Log(l_content, user_id, "2", tu_id);
						out.print("ssok-批量删除订单成功！");
					} else {
						out.print("errors-批量删除订单失败！");
					}
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
