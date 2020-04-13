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
import WYBack_Stage.Dao.TB_Village_Dao;
import WYCommunity.S_string;

public class TBGrOrder_Deleteqb extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         try{
		 request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html;charset=UTF-8"); // 这样设置可以让弹出框在IE和火狐下显示正常
         PrintWriter out = response.getWriter();
         HttpSession session = request.getSession();
         if (session.getAttribute("USER_ID") == null) {
             String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
             out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
             out.println("window.location='" + url + "';");
             out.println("</script>");
         }else{
        	 String ts_id = session.getAttribute("U_ID").toString();
        	 String qxid=S_string.formatString(request.getParameter("qxid"));
        	 String xqName=new TB_Village_Dao().getEsname(qxid, ts_id);
        	 String lyid=S_string.formatString(request.getParameter("lyid"));
        	 String dyid=S_string.formatString(request.getParameter("dyid"));
        	 String tu_id=session.getAttribute("TU_ID").toString();
        	 String user_id = session.getAttribute("USER_ID").toString();
        	 String orderType=request.getParameter("orderType");
        	 String jfxid=S_string.formatString(request.getParameter("jfxid"));
        	 boolean bl = new MyTB_Estate_Order().deleteQb_order(ts_id,qxid,lyid,dyid,orderType,jfxid);
        	 if(bl){
        		String l_content="全部删除小区："+xqName+"下"+"的未支付订单";
             	 ChangeDao.add_Log(l_content, user_id, "2", tu_id);
             	out.print("ssok-删除批量订单成功！");
        	 }else{
        		 out.print("errors-删除批量订单失败！");
        	 }
         }
	
		out.flush();
		out.close();
     } catch (Exception e) {
		e.printStackTrace();
	}
}

}
