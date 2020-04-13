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

@SuppressWarnings("serial")
public class TB_Estate_Order_uprgsfServlrt extends HttpServlet {


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
        
        String args = request.getParameter("args");
        if("1".equals(args)){
        	String ts_id=request.getParameter("ts_id");
            String Eo_id=request.getParameter("Eo_id");
            String payType=request.getParameter("payType");
            String total = request.getParameter("total");
            String total_znj = request.getParameter("total_znj");
            String total_sj = request.getParameter("total_sj");
            
            String user_id = session.getAttribute("USER_ID").toString();
            String tu_id=session.getAttribute("TU_ID").toString();
            
            boolean bl = new MyTB_Estate_Order().update_order_rgsf(ts_id, Eo_id, total,total_znj, payType,total_sj,tu_id);
            if(bl){
            	String l_content="操作员："+user_id+"修改订单："+Eo_id+"修改为已支付(人工收费:"+total_sj+")";
            	new ChangeDao().add_Log(l_content, user_id, "2", tu_id);
            	out.print("ssok-人工收费成功");
            }else{
            	out.print("ssok--人工收费失败");
            }
        }else if("2".equals(args)){
        	String ts_id=request.getParameter("ts_id");
            String Eo_id=request.getParameter("Eo_id");
            String total_sj = request.getParameter("total_sj");
            String tk_Reason = request.getParameter("tk_Reason");
            String user_id = session.getAttribute("USER_ID").toString();
            String tu_id=session.getAttribute("TU_ID").toString();
            
            boolean bl = new MyTB_Estate_Order().update_order_rgtk(ts_id, Eo_id,tk_Reason);
            if(bl){
            	String l_content="操作员："+user_id+"修改订单："+Eo_id+"修改为已退款(人工退款:"+total_sj+")";
            	new ChangeDao().add_Log(l_content, user_id, "2", tu_id);
            	out.print("ssok-人工退款成功");
            }else{
            	out.print("ssok--人工退款失败");
            }
            
        }
    }

}
