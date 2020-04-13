package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import WYBack_Stage.Bean.TB_Estate_item;
import WYBack_Stage.Bean.TB_HMBean;
import WYBack_Stage.Dao.ChangeDao;
import WYBack_Stage.Dao.Mete_ReadClass;
import WYCommunity.MyTBCommit;
import WYCommunity.T_time;

public class Mete_Order extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the object.
     */
    public Mete_Order() {
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8"); // 这样设置可以让弹出框在IE和火狐下显示正常
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String ts_id = request.getParameter("ts_id");
        String xiaoquid = request.getParameter("xiaoquid");
        String louid = request.getParameter("louid");
        String Uniteid = request.getParameter("Un_id");
        String fwid = request.getParameter("fwid");
        String type = request.getParameter("type");
        String a_id = session.getAttribute("A_ID").toString();
        
       // List<TB_Estate_item> lis = new Mete_ReadClass().find_item(xiaoquid, ts_id);
        List<TB_Estate_ChargeType> lis = new Mete_ReadClass().findChargeType_item(xiaoquid, ts_id);
        
        List<TB_HMBean> list = new Mete_ReadClass().findchaobiao(Uniteid, xiaoquid, louid, fwid, type, ts_id);
        List<List<String>> sqlListInsert = new ArrayList<List<String>>();
        List<List<String>> sqlListUpdate = new ArrayList<List<String>>();
        if (list.size() > 0) {
            if (lis.size() > 0) {
                for (TB_HMBean tbm : list) {
                    int Em_id = tbm.getEm_id();
                    int Es_id = tbm.getEs_id();
                    int Bu_id = tbm.getBu_id();
                    int Un_id = tbm.getUn_id();
                    int Eh_id = tbm.getEh_id(); 
                    int Ht_id = tbm.getEhType(); //房屋类型
                    String htName=tbm.getHtName(); //类型名称
                    String UserNumber = tbm.getUserNumber();
                    int type1 = tbm.getType();
                    String typename = "";
                    int ei_id = 0;
                    double price = 0;
                    if (type1 == 0) {
                        typename = "水费";
                    } else if (type1 == 1) {
                        typename = "电费";
                    } else if (type1 == 2) {
                        typename = "燃气费";
                    }
                    List<String> l = new ArrayList<String>();
                    Map<String,String> map=new HashMap<String,String>();
                    for (TB_Estate_ChargeType teii : lis) {
                        l.add(teii.getCt_itemName()); //缴费项名称
                        map.put(teii.getCt_itemName()+"#"+teii.getHt_id(),teii.getCt_price()); //缴费项名称#房屋类型，价格#收费方式
                    }
                    if (l.contains(typename)) {
                       if(map.containsKey(typename+"#"+Ht_id)){  //存在某个房屋类型的缴费项
                    	   for (TB_Estate_ChargeType tei : lis) {
                              if ((typename.equals(tei.getCt_itemName())) && ( Ht_id==tei.getHt_id())) {
                                   List<String> sql = new ArrayList<String>();
                                   List<String> sql1 = new ArrayList<String>();
                                   ei_id = tei.getEi_id();
                                   price = Double.parseDouble(tei.getCt_price());
                                   double total = Double.parseDouble(UserNumber) * price;
                                   sql.add(ts_id);// 商户id
                                   sql.add(a_id);// 应用类别主键
                                   sql.add(Es_id + "");// 小区主键
                                   sql.add(Bu_id + "");// 楼宇主键
                                   sql.add(Un_id + "");// 单元主键
                                   sql.add(Eh_id + "");// 房屋主键
                                   sql.add("1");// 订单类型（1查缴订单 2预缴订单）
                                   sql.add(ei_id + "");// 缴费项
                                   sql.add(total + "");// 应缴金额
                                   sql.add(new T_time().getTime());// 创建时间
                                   sql.add("0");// 支付状态（0未支付，1已支付，2部分支付）
                                   sql.add("1");// 状态（0删除1正常）
                                   sql1.add("1");
                                   sql1.add(Em_id + "");
                                   sqlListInsert.add(sql);
                                   sqlListUpdate.add(sql1);
                                   /*
                                    * sql.add(Cost_startTime);
                                    * sql.add(Cost_endTime);
                                    * sql.add(Scost_startTime);
                                    * sql.add(Scost_endTime);
                                    */
                               }
                           } 
                       }else {
                           out.print("ok-系统缺少房屋类型为：" +htName+"的"+ typename + "的收费信息");
                           return;
                       }
                      
                    } else {
                        out.print("ok-系统缺少" + typename + "收费项，请先到收费项设置菜单进行添加");
                        return;
                    }
                }
                String sql2 = "insert into TB_Estate_Order (ts_id,a_id,Es_id,Bu_id,Un_id,Eh_id,orderType,payItem,total,creat_time,payStatus,status) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
                String sql3 = "update TB_Estate_Mation set orderStatus=? where Em_id=?";
                MyTBCommit.TBCommitp(sqlListUpdate, sql3);
                String returnstr = MyTBCommit.TBCommitp(sqlListInsert, sql2);
                if (returnstr.equals("")) {
                	String l_content="插入收费单："+list.size()+"条";
                	String adminuser_id=session.getAttribute("USER_ID").toString();
					String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
					ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
                    out.print("sok-插入收费单成功，共插入" + sqlListInsert.size() + "条收费单");
                } else {
                    out.print("ok-插入收费单失败");
                }
            } else {
                out.print("ok-系统暂无收费项，请先到收费项设置菜单进行添加");
            }
        } else {
            out.print("ok-暂无可生成收费单的抄表信息 ");
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
