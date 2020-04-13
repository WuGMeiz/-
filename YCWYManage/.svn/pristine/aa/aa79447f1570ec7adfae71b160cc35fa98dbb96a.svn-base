package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Bean.TB_Estate_Order;
import WYBack_Stage.Dao.ChangeDao;
import WYBack_Stage.Dao.MyTB_Estate_Order;


public class TB_Estate_Order_plxj extends HttpServlet {
	
	private static final long serialVersionUID = 2942908169760149567L;

	public TB_Estate_Order_plxj() {
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
				 
					
					String tu_id=session.getAttribute("TU_ID").toString();
					String ts_id=session.getAttribute("U_ID").toString();
					String user_id=session.getAttribute("USER_ID").toString();
				
					String sqlstr=request.getParameter("sqlstr");//以“;” 分隔的订单号
					String payType=request.getParameter("payType");
					Set<String> set = new HashSet<String>();
					TB_Estate_Order order =null;
					List<TB_Estate_Order> odList = new MyTB_Estate_Order().getTBorder(sqlstr);
					for(int i=0;i<odList.size();i++){
			  			order=(TB_Estate_Order)odList.get(i);
			  			set.add(order.getEh_id()+"");
					}
					 if(set.size()>1){
						 out.print("ssok-只能选择一个用户，请检查选中的订单");
					 } else{
						 //boolean flag = new MyTB_Estate_Order().delete_order_plxj(ts_id, sqlstr,payType);
						boolean flag = new MyTB_Estate_Order().delete_order_plxj(ts_id, sqlstr,payType,tu_id);
						if(flag==true) {
							int offset = 0;
							int cnt = 0;
							while((offset = sqlstr.indexOf(";", offset)) != -1){
								offset = offset + ";".length();
								cnt++;
							}
							String l_content="批量现金订单缴费："+cnt+"个,"+"订单号:"+sqlstr;
							new ChangeDao().add_Log(l_content, user_id, "2", tu_id);
							out.print("ssok-批量现金订单缴费成功！");
						} else {
							out.print("errors-批量现金订单缴费失败！");
						}
					 }
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
