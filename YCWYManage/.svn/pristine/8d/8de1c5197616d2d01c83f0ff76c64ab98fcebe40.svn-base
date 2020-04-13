package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTB_Estate_Order;
import WYCommunity.MyTBCommit;
import WYCommunity.T_time;

public class Order_rgsf_swept extends HttpServlet {


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
		
	if(session.getAttribute("USER_ID")==null) {
		String url=request.getContextPath()+"/YCWYPage/BackPage/login.jsp";
		out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	    out.println("window.location='"+url+"';");
	    out.println("</script>");
	} else {
		String Es_id=request.getParameter("Es_id");
		String Bu_id=request.getParameter("Bu_id");//楼宇主键
		String Un_id=request.getParameter("Un_id");
		String EhNumber=request.getParameter("EhNumber");//房屋编号
		String payItem=request.getParameter("payItem");
		String[] pay_Item = payItem.split("##");
		payItem = pay_Item[0];
		String ct_id=pay_Item[1]; //子表主键
		
		String total=request.getParameter("total");
		String bankid=request.getParameter("bankid");
		String user_id=session.getAttribute("USER_ID").toString();
		String ts_id=session.getAttribute("U_ID").toString();
		String a_id=session.getAttribute("A_ID").toString();
		String tu_id=session.getAttribute("TU_ID").toString();
		String LEVELS = session.getAttribute("LEVELS").toString();
		String time = new T_time().getTime();
		
		 
		
		String l_content="人工收费：插入收费单1个,订单号："+bankid;
		
		//查询该用户下是否有该房屋编号存在
		String str = new MyTB_Estate_Order().select_user_sfcz(ts_id,Es_id, Bu_id, Un_id, EhNumber,LEVELS,tu_id);
		if(!"".equals(str))
		{
			//查询该用户下，是否有未缴费订单
			String wjorder = new MyTB_Estate_Order().select_user_order(ts_id,Es_id, Bu_id, EhNumber);
			//查询该月购买次数
			int num = new MyTB_Estate_Order().select_paynum(ts_id, Es_id, Bu_id, str, payItem);//该月购买次数
			//根据收费项主键查询，收费项限额
			//String xiane = new MyTB_Estate_Order().select_item_xe2(payItem);
			
			String xiane = new MyTB_Estate_Order().select_item_xeZb2(payItem,ct_id);
			String[] str_xiane = xiane.split("##");
//			double  Limited = Double.parseDouble(str_xiane[0]);//限购金额
			
			int LimitNumber=0;//限购次数
			if(str_xiane[1].equals("aaa")){
				LimitNumber=num+1;
			}else{
				LimitNumber = Integer.parseInt(str_xiane[1]);
			}
			int buylimited = Integer.parseInt(str_xiane[2]);//购电限制
			if(buylimited==1){
				if("".equals(wjorder)){//开启购电限制判断是否有未缴订单
//					if(Limited>=Double.parseDouble(total)){
						if(LimitNumber>num){
							String sql = "insert into TB_Estate_Order (ts_id,a_id,Es_id,Bu_id,Eh_id,orderType,payItem,total,creat_time,status,bankid) " +
									"values ('"+ts_id+"','"+a_id+"','"+Es_id+"','"+Bu_id+"','"+str+"','2','"+payItem+"','"+total+"','"+time+"','1','"+bankid+"')";
							String sql2 = "insert into TBAdmin_Log(l_content,user_id,l_time,Status,tu_id,type) values ('"+l_content+"','"+user_id+"','"+time+"','1','"+tu_id+"','2')";
							List<String> list = new ArrayList<String>();
							list.add(sql);
							list.add(sql2);
							
							String returnstr = MyTBCommit.TBCommit(list);
							if(returnstr.equals("")){
//								request.setAttribute("bankid", bankid);
//								request.setAttribute("ts_id", ts_id);
//								request.setAttribute("Total", total);
							 	//request.getRequestDispatcher("/YCWYPage/BackPage/artificial/Swept.jsp").forward(request, response);//成功后跳转详情页
								out.print("sssok-被扫订单增加完成");
							}else{
								out.print("errors-插入收费单失败！");
							}
						}else{
							out.print("ok-抱歉，该月购买次数已大于限购次数！");
						}
//					}else{
//						out.print("ok-抱歉，缴费金额不能大于限购金额！");
//					}
				}else{
					out.print("ok-抱歉，该房屋编号下有未缴订单，不能缴费！");
				}
			}else{
				if(LimitNumber>num){
					String sql = "insert into TB_Estate_Order (ts_id,a_id,Es_id,Bu_id,Eh_id,orderType,payItem,total,creat_time,status,bankid) " +
							"values ('"+ts_id+"','"+a_id+"','"+Es_id+"','"+Bu_id+"','"+str+"','2','"+payItem+"','"+total+"','"+time+"','1','"+bankid+"')";
					String sql2 = "insert into TBAdmin_Log(l_content,user_id,l_time,Status,tu_id,type) values ('"+l_content+"','"+user_id+"','"+time+"','1','"+tu_id+"','2')";
					List<String> list = new ArrayList<String>();
					list.add(sql);
					list.add(sql2);
					
					String returnstr = MyTBCommit.TBCommit(list);
					if(returnstr.equals("")){	
						/*request.setAttribute("bankid", bankid);
						request.setAttribute("ts_id", ts_id);
						request.setAttribute("Total", total);*/
					 	//request.getRequestDispatcher("/YCWYPage/BackPage/artificial/Swept.jsp").forward(request, response);//成功后跳转详情页
						//response.sendRedirect("/YCWYPage/BackPage/artificial/Swept.jsp");
						out.print("sssok-被扫订单增加完成");
					}else{
						out.print("errors-插入收费单失败！");
					}
				}else{
					out.print("ok-抱歉，该月购买次数已大于限购次数！");
				}
				
			}
				
		}else{
			out.print("ok-抱歉，未能根据所在小区楼宇单元，查询到该房屋编号！");
		}
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
