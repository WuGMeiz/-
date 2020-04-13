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

public class TB_Estate_OrderServlrt extends HttpServlet {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
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
    @SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("GB2312");
        response.setContentType("text/html;charset=UTF-8"); ////这样设置可以让弹出框在IE和火狐下显示正常
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        
        String ts_id=request.getParameter("ts_id");
        String Eo_id=request.getParameter("Eo_id");
        String user_id = session.getAttribute("USER_ID").toString();
        String tu_id=session.getAttribute("TU_ID").toString();
       
        boolean bl = new MyTB_Estate_Order().delete_order(ts_id, Eo_id);
        if(bl){
        	String l_content="操作员："+user_id+"删除订单："+Eo_id;
        	new ChangeDao().add_Log(l_content, user_id, "2", tu_id);
        	out.print("ssok-删除订单成功");
        }else{
        	out.print("ssok-删除订单失败");
        }
        
    }

}
