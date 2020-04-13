package WYBack_Stage.Servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Bean.TB_Estate_item;
import WYBack_Stage.Dao.ChangeDao;

public class WyTBPay_Item_update extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2150740790922386837L;

	/**
	 * Constructor of the object.
	 */
	public WyTBPay_Item_update() {
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

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession session=request.getSession();
			
			if(session.getAttribute("USER_ID")==null)
			{			
				String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
				out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
			    out.println("window.location='"+url+"';");
			    out.println("</script>");
			}
			else
			{
				String Es_id=request.getParameter("Es_id");  //小区id
				String Ct_id=request.getParameter("Ct_id");  //子表主键
				String EhType=request.getParameter("EhType"); //房屋类型id
				String Ei_id=request.getParameter("Ei_id");
				String name=request.getParameter("name");
				String price=request.getParameter("price1");
				String LimitNumber=request.getParameter("LimitNumber1")==null?"":request.getParameter("LimitNumber1");
				String Limited=request.getParameter("Limited1")==null?"":request.getParameter("Limited1");
				String znjday=request.getParameter("znjday1")==null?"":request.getParameter("znjday1");
				String znjbl=request.getParameter("znjbl1")==null?"":request.getParameter("znjbl1");
				String status=request.getParameter("status");
				String gdxz=request.getParameter("gdxz1");
				String isyj=request.getParameter("isyj1");
				String shoufeifs=request.getParameter("shoufeifs");//收费方式
				String adminuser_id=session.getAttribute("USER_ID").toString();
				String ts_id=session.getAttribute("U_ID").toString();
				
				//先判断小区下是否存在该缴费项
	            int num=new ChangeDao().getTB_Estate_itemInfo(Es_id, name,ts_id,Ei_id);
	            if(num==0){
	              boolean b=new ChangeDao().TB_Estate_itemUp(Es_id, name, ts_id, Ei_id);
	              if(b){
	            	  int pd= new ChangeDao().getTB_ChargeTypeInfo(Ei_id, EhType,Ct_id);
	                  if(pd==0){
	                  	boolean bl=new ChangeDao().updateChargeType(EhType,gdxz,isyj,LimitNumber,Limited,Ei_id,Ct_id,price,znjday,znjbl,status,shoufeifs,"","","","","","","","");
	      				if(bl)
	      				{
	      					String l_content="修改收费项目："+name+"房屋类型："+EhType;
	      					String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
	      					ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
	      					
	      					out.print("ssok-修改收费项目成功！");
	      				}else{
	      					out.print("errors-修改收费项目失败！");
	      				}
	                  }else{
	                  	out.print("errors-修改收费项目失败！此项目下已设置该房屋类型信息！");
	                  }  
	              }else{
	            	  out.print("errors-收费项目修改失败！"); 
	              }
	            }else{
	            	out.print("errors-修改失败！！收费项目已设置！");	
	            }
				/*boolean bl=new ChangeDao().update_TBPay_Item(gdxz,isyj,LimitNumber1,Limited1,Ei_id,ts_id,name,price1, znjday1, znjbl1,status,shoufeifs);
				if(bl)
				{
					
					String l_content="修改收费项目："+name;
			
					String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
					ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
					out.print("ssok-修改收费项目成功！");
				}
				else
				{
					out.print("errors-修改收费项目失败！");
				}*/
			}
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
