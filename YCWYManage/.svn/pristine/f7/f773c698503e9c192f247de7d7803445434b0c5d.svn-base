package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.Mete_ReadClass;
import WYBack_Stage.Dao.TB_Estate_paperDao;

public class TB_Estater_ProperServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor of the object.
	 */
	public TB_Estater_ProperServlet() {
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
		response.setContentType("text/html;charset=UTF-8"); //这样设置可以让弹出框在IE和火狐下显示正常
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String eo_id = request.getParameter("eo_id");
		String es_id = request.getParameter("es_id");
		String Bankid = request.getParameter("Bankid");
		String status = request.getParameter("status");
		
		
		
		//if(status.equals("1")){
			//从合并打印跳转过来得
			Map<String, Object> selectPidandoaoerNum = new TB_Estate_paperDao().selectPidandoaoerNum(Bankid);
			int po_id = new TB_Estate_paperDao().selectIDbyBankidAndEsIdAndPrintStatus(Bankid, Integer.valueOf(selectPidandoaoerNum.get("es_id").toString()), 1);
			boolean update = new TB_Estate_paperDao().update(1,0, Integer.valueOf(selectPidandoaoerNum.get("es_id").toString()), Bankid);
			if(update){
				String l_content="打印订单流水号为："+Bankid+"的收费单信息";
				String adminuser_id=session.getAttribute("USER_ID").toString();
				String tu_id=new Mete_ReadClass().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
				Mete_ReadClass.add_Log(l_content, adminuser_id, tu_id,"2");
			}
		
			/*}else{
				int selectIssumOrder = new TB_Estate_paperDao().selectIssumOrder(Bankid);
				if(selectIssumOrder <= 1 || Bankid == null || Bankid.equals("")){
				boolean update = new TB_Estate_paperDao().update(1,0, Integer.valueOf(es_id), Bankid);
				if(update){
					String l_content="打印订单流水号为："+Bankid+"的收费单信息";
					String adminuser_id=session.getAttribute("USER_ID").toString();
					String tu_id=new Mete_ReadClass().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
					Mete_ReadClass.add_Log(l_content, adminuser_id, tu_id,"2");
					
				}	
				}else{
					out.print("当前订单为合并订单请前往合并订单中打印");
				}
				
			
		}*/
		
	}

}
