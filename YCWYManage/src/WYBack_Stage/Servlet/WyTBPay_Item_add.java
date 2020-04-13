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

public class WyTBPay_Item_add extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349946073430430472L;

	/**
	 * Constructor of the object.
	 */
	public WyTBPay_Item_add() {
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
				String xiaoqu=request.getParameter("xiaoqu");
				String sfname=request.getParameter("sfname");
				String sftype=request.getParameter("sftype");
				String price=request.getParameter("price");
				String znjday=request.getParameter("znjday");
				String zjnbl=request.getParameter("zjnbl");
				String remark=request.getParameter("remark");
				String EhType=request.getParameter("EhType");
				String Limited=request.getParameter("Limited");
				if(!Limited.equals("")){
					Limited=Limited.substring(0, Limited.length()-1);
				}
				String LimitNumber=request.getParameter("LimitNumber");
				String gdxz=request.getParameter("gdxz");
				String isyj=request.getParameter("isyj");
				String adminuser_id=session.getAttribute("USER_ID").toString();
				String ts_id=session.getAttribute("U_ID").toString();
				String a_id=session.getAttribute("A_ID").toString();
			 
				  //先判断该小区下是否存在该缴费项
	               TB_Estate_item item=new ChangeDao().getTBPay_Item_NameInfo(xiaoqu, sfname,ts_id);
	               String Ei_id="";  //缴费项id
	               if(item==null){
	            	  boolean b=new  ChangeDao().add_Item(a_id, ts_id, xiaoqu, sfname);
	            	  if(b){
	            		  item=new ChangeDao().getTBPay_Item_NameInfo(xiaoqu,sfname,ts_id);
	            		  Ei_id=item.getEi_id()+"";
	            	  }else{
	            		  out.print("errors-添加收费项目失败！"); 
	            	  }
	               }else{
	            	   Ei_id=item.getEi_id()+"";
	               }
	               if(!"".equals(Ei_id)){
	            	 int pd= new ChangeDao().getTBPay_ChargeTypeInfo(Ei_id, EhType);
	            	 if(pd==0){// 返回0为没有；1为已经存在该数据
	            		boolean bl= new ChangeDao().TB_Estate_ChargeType(EhType,Ei_id,price,sftype, remark,znjday, zjnbl, 
	            			Limited, LimitNumber,gdxz,isyj, "", "", "", "","","","");
	            		if(bl)
	    				{
	    					String l_content="添加收费项目："+sfname+"，房屋类型"+EhType;
	    					String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
	    					ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
	    					out.print("ssok-添加收费项目成功！");
	    				}else{
	    					out.print("errors-添加收费项目失败！");
	    				}
	            	 }else{
	     				out.print("errors-添加收费项目失败！此房屋类型下收费项目已存在！");
	    			 }
	               }else{
	            	   out.print("errors-添加收费项目失败！"); 
	               }
				/*int pd = new ChangeDao().getTBPay_Item_Name(xiaoqu, sfname,ts_id);
				if(pd==0){  //返回0为没有；1为已经存在该数据
					boolean bl=new ChangeDao().add_TBPay_Item(isyj,gdxz,Limited,LimitNumber,a_id,ts_id,xiaoqu,
							sfname, sftype, price, znjday, zjnbl,remark,sfdesc);
					
					if(bl)
					{
						String l_content="添加收费项目："+sfname;
						String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
						ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
						
						out.print("ssok-添加收费项目成功！");
					}else{
						out.print("errors-添加收费项目失败！");
					}
				}else{
					out.print("errors-添加收费项目失败！收费项目已存在！");
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
