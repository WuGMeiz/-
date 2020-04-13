package WYBack_Stage.Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import WYBack_Stage.Bean.TB_Estate_ChargeType;
import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Dao.ChangeDao;
import WYCommunity.MyTBCommit;
import WYCommunity.S_string;
import WYCommunity.T_time;


public class WyTBorder_add extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349946073430430472L;

	/**
	 * Constructor of the object.
	 */
	public WyTBorder_add() {
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
				String xiaoqu=request.getParameter("Es_id");
				String louyu=request.getParameter("Bu_id");
				String unite=request.getParameter("Un_id");
				String EhNumber=request.getParameter("EhNumber");
				String cost_startTime=request.getParameter("cost_startTime");
				String cost_endTime=request.getParameter("cost_endTime");
				String Scost_startTime=request.getParameter("Scost_startTime");
				String Scost_endTime=request.getParameter("Scost_endTime");
				String is_number = request.getParameter("is_number");
				String adminuser_id=session.getAttribute("USER_ID").toString();
				String ts_id=session.getAttribute("U_ID").toString();
				String a_id=session.getAttribute("A_ID").toString();
				
				String sqlstr=request.getParameter("sqlstr");
				String create_time = new T_time().getTime();
				String retStr = "";
				String ehname = "";
				List<String> list=new S_string().getIndexString(sqlstr, ';');  //缴费项id#名称;缴费项id#名称
				Map<String,List<TB_Estate_ChargeType>> map=new HashMap<String,List<TB_Estate_ChargeType>>();
				List<List<String>> orderSqlList = new ArrayList<List<String>>();
				List<String> orderSql = null;
				for(int i=0;i<list.size();i++){
					String itemstr=(String)list.get(i);
					String[] strInfoArr = itemstr.split("#");   //缴费项id#名称
					List<TB_Estate_ChargeType> lis=new ChangeDao().findChargeType_itemByid(strInfoArr[0]);
					map.put(strInfoArr[0], lis);
				}
				String LEVELS=session.getAttribute("LEVELS").toString();
				String tu_id1 = session.getAttribute("TU_ID").toString();
				List<TB_Estate_House> tehlist = new ChangeDao().getHouseinfoUP(ts_id,unite,xiaoqu, louyu,EhNumber,LEVELS,tu_id1);
				if(tehlist.size()>0){
					for(int j=0;j<list.size();j++)//选择的收费项目 
					{
						String itemstr=(String)list.get(j);
						String[] strInfoArr = itemstr.split("#");   //缴费项id#名称
						ehname+=strInfoArr[1];
						List<TB_Estate_ChargeType> fwlx=map.get(strInfoArr[0]);
						for(int i=0,len=tehlist.size();i<len;i++){//房屋信息
							
							Double total=0.00;
							boolean bj=false;
							int a=0;
							 
							for(TB_Estate_ChargeType chargeType:fwlx){
							  if(tehlist.get(i).getEhType().equals(chargeType.getHt_id()+"")){
								 bj=true;
									//按使用面积计算 
								  if(chargeType.getCt_ItemType().equals("1")){
									  total=Double.parseDouble(chargeType.getCt_price()) * Double.parseDouble(tehlist.get(i).getUseArea());
								  }
								   //按建筑面积计算
							  	  else if(chargeType.getCt_ItemType().equals("2")){
							  		 total=Double.parseDouble(chargeType.getCt_price()) * Double.parseDouble(tehlist.get(i).getBuildArea());
							      }
									//按供暖面积计算
								  else if(chargeType.getCt_ItemType().equals("3")){
								  	  total=Double.parseDouble(chargeType.getCt_price()) * Double.parseDouble(tehlist.get(i).getHeatingArea());
								  }
									//按月计算
								  else if(chargeType.getCt_ItemType().equals("4")){
								  	  total=Double.parseDouble(chargeType.getCt_price()) * 1;
								  }
									//按季度计算
								  else if(chargeType.getCt_ItemType().equals("5")){ 
								  	  total=Double.parseDouble(chargeType.getCt_price()) * 3;
								  }
								    //按立方计算
								  else if(chargeType.getCt_ItemType().equals("6")){ }
									//按度计算
								  else if(chargeType.getCt_ItemType().equals("7")){ }
									//按固定金额计算
								  else if(chargeType.getCt_ItemType().equals("8")){ 
								  	 total=Double.parseDouble(chargeType.getCt_price());
								  }
							  	 else if(chargeType.getCt_ItemType().equals("9")){ 
							  		//新加的，物业费按建筑面积乘单价乘月份
							  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							  		int month = T_time.getMonth(sdf.parse(cost_startTime), sdf.parse(cost_endTime));
							  		if(month<=0){
							  			month = 1;
							  		}
							  		total=Double.parseDouble(chargeType.getCt_price()) * Double.parseDouble(tehlist.get(i).getBuildArea()) * month;
							     }else{
							    	 a++;
							    	 List<String> li = T_time.getStringAndMath(cost_startTime,cost_endTime);
								  		if(li.size()>0){
								  			
								  			String price = "0.00";
								  			if(chargeType.getCt_ItemType().equals("13")){
								  				price = new ChangeDao().getTB_Estate_itemPrice(xiaoqu, ts_id, strInfoArr[0], String.valueOf(tehlist.get(i).getStorey()));
								  			}
								  			
								  			for(int m = 0;m < li.size();m++){
								  				int b=0;
									            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									            
									            String cost_start = sdf.format(T_time.getMinDateMonth(li.get(m)));
									            String cost_end = sdf.format(T_time.getMaxDateMonth(li.get(m)));
									            
									             if(chargeType.getCt_ItemType().equals("10")){
									            	 total=Double.parseDouble(chargeType.getCt_price()) * Double.parseDouble(tehlist.get(i).getBuildArea());
											  	 }
											  	 else if(chargeType.getCt_ItemType().equals("11")){
											  		total=Double.parseDouble(chargeType.getCt_price());
											  	 }
											  	 else if(chargeType.getCt_ItemType().equals("12")){
											  		if(tehlist.get(i).getCarNum()>0){
											  			total=Double.parseDouble(chargeType.getCt_price()) * Double.parseDouble(tehlist.get(i).getCarNum()+"");
											  		}else{
											  			b++;
											  		}
											  	 }
											  	 else if(chargeType.getCt_ItemType().equals("13")){
											  		 if(tehlist.get(i).getStorey()>0){
											  			total=Double.parseDouble(price) * Double.parseDouble(tehlist.get(i).getBuildArea());
											  		 }else{
											  			b++;
											  		 }
											  	 }
									            if(b==0){
									            	orderSql = new ArrayList<String>();
													orderSql.add(ts_id); 
													orderSql.add(a_id); 
													orderSql.add(xiaoqu);
													if(louyu.equals("")){
														orderSql.add(tehlist.get(i).getBu_id()+"");
													}else{
														orderSql.add(louyu);
													}
													orderSql.add(unite);
													orderSql.add(tehlist.get(i).getEh_id()+"");
													
													orderSql.add("1"); //订单类型     1查缴订单
													orderSql.add(strInfoArr[0]); //缴费项
													
													if("1".equals(is_number) ){				
														orderSql.add(String.valueOf(Math.round(total)));			   
													}else{
														orderSql.add(total.toString()); 
													}
													orderSql.add(create_time);
													orderSql.add("0"); 
													orderSql.add(cost_start); 
													orderSql.add(cost_end); 
													orderSql.add(Scost_startTime);  
													orderSql.add(Scost_endTime); 
													orderSql.add("1");
													
													orderSqlList.add(orderSql);
									            }
									        }
								  		}
							     }
							  }
							}
							if(a==0){
								if(bj){
									orderSql = new ArrayList<String>();
									orderSql.add(ts_id); 
									orderSql.add(a_id); 
									orderSql.add(xiaoqu);
									if(louyu.equals("")){
										orderSql.add(tehlist.get(i).getBu_id()+"");
									}else{
										orderSql.add(louyu);
									}
									orderSql.add(unite);
									orderSql.add(tehlist.get(i).getEh_id()+"");
									
									orderSql.add("1"); //订单类型     1查缴订单
									orderSql.add(strInfoArr[0]); //缴费项
									
									if("1".equals(is_number) ){				
										orderSql.add(String.valueOf(Math.round(total)));			   
									}else{
										orderSql.add(total.toString()); 
									}
									orderSql.add(create_time);
									orderSql.add("0"); 
									orderSql.add(cost_startTime); 
									orderSql.add(cost_endTime); 
									orderSql.add(Scost_startTime);  
									orderSql.add(Scost_endTime); 
									orderSql.add("1");
									
									orderSqlList.add(orderSql);
								}else{
									 out.print("errors-"+tehlist.get(i).getHtName()+"类型下的"+strInfoArr[1]+"信息未设置！！");
									 return; 
								}
							}
						}
					}
					
					if (ehname.contains("水费")){
						out.print("errors-您选择的收费项目[水费]无法在此处生成收费单,请前往抄表录入生成收费信息！");
						return;
					}else if (ehname.contains("电费")){
						out.print("errors-您选择的收费项目[电费]无法在此处生成收费单,请前往抄表录入生成收费信息！");
						return;
					}else if (ehname.contains("燃气费")){
						out.print("errors-您选择的收费项目[燃气费]无法在此处生成收费单,请前往抄表录入生成收费信息！");
						return;
					}else{
						StringBuffer insertOrderSql = new StringBuffer();
						insertOrderSql.append("insert into TB_Estate_Order ");
						insertOrderSql.append("(ts_id,a_id,Es_id,Bu_id,Un_id,Eh_id,orderType,payItem,total,creat_time," +
								"payStatus,Cost_startTime,Cost_endTime,Scost_startTime,Scost_endTime,status");
						insertOrderSql.append(")  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
						
						retStr = MyTBCommit.TBCommitp(orderSqlList, insertOrderSql.toString());
					}	
						
					if("".equals(retStr)){
						String l_content="批量新增收费单成功！共导入[ "+tehlist.size()+" ]条数据";
						String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
						ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
						out.print("ssok-新增收费单成功！");
					}else{
						out.print("errors-新增收费单失败！");
					}
				}else{
					out.print("errors-您选择的楼宇信息或单元下不存在房屋信息 ,请前往房屋信息添加房屋信息！");
					return;
				}
			}
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
