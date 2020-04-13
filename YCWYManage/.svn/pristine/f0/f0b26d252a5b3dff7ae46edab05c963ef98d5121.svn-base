package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Bean.TB_Estate_Housetype;
import WYBack_Stage.Dao.MyTB_House_DAO;
import WYCommunity.S_string;

public class HouseTypeSeclect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("USER_ID") == null) {
			String url = request.getContextPath()
					+ "/YCWYPage/BackPage/login.jsp";
			out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
			out.println("window.location='" + url + "';");
			out.println("</script>");
		} else {

			String ts_id = S_string.formatString(request.getParameter("ts_id"));
			String Es_id = S_string.formatString(request.getParameter("Es_id"));

			List<TB_Estate_Housetype> list3 = new MyTB_House_DAO()
					.findHouseType(Es_id);
			String msg = "<option value=''>请选择房屋类型</option>";
			if (list3.size() > 0) {

				for (TB_Estate_Housetype houseTyoe : list3) {
					msg += "<option value='" + houseTyoe.getHt_id() + "'>"
							+ houseTyoe.getHtName() + "</option>";
				}

				out.print(msg);

			}

		}
		out.flush();
		out.close();
	}

}
