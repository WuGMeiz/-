package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTB_Build_DAO;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYBack_Stage.Dao.TB_Estate_UnitDao;
import WYCommunity.S_string;
import WYCommunity.T_time;

public class TB_Estate_UnitServlet extends HttpServlet {

	private static final long serialVersionUID = -1875852888257344614L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        HttpSession session = request.getSession();
	        String args = request.getParameter("args");
	        if (session.getAttribute("USER_ID") == null) {
	            String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
	            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
	            out.println("window.location='" + url + "';");
	            out.println("</script>");
	        } else {
	          try{
	           if("1".equals(args)){ //新增单元
	        	   String tu_id = session.getAttribute("TU_ID").toString();
                   String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                   String Bu_id = request.getParameter("bu_id");
                   String Es_id = request.getParameter("es_id");
                   String qianzhui = S_string.formatString(request.getParameter("qianzhui"));
                   String qsBuName = request.getParameter("qsBuName");
                   String zzBuName = S_string.formatString(request.getParameter("zzBuName"));
                   String houzhui = S_string.formatString(request.getParameter("houzhui"));
                   String Remark = S_string.formatString(request.getParameter("remark").trim());
                   if (!zzBuName.equals("")) {
                       int k = Integer.parseInt(qsBuName);
                       int j = Integer.parseInt(zzBuName);
                       for (int i = k; i <= j; i++) {
                           String louhao = qianzhui + i + houzhui;
                           if (new TB_Estate_UnitDao().checkaddDy(ts_id, louhao, Es_id,Bu_id)) {
                               out.print("errors-添加失败!单元名称不能重复输入！");
                           } else {
                               boolean b = new TB_Estate_UnitDao().dyAdd(ts_id, Es_id,Bu_id, louhao, Remark);
                               if (b) {
                                   String userid = session.getAttribute("USER_ID").toString();
                                   String l_content = "添加单元：" + i;
                                   try {
                                       new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                                   } catch (Exception e) {
                                       // TODO Auto-generated catch block
                                       e.printStackTrace();
                                   }
                                   if (i == j) {
                                       out.print("ssok-添加单元成功！");
                                   }
                               } else {
                                   out.print("errors-添加单元失败!");
                               }
                           }
                       }
                   
                   }else{
                       String louhao1 = qianzhui + qsBuName + houzhui;
                       if (new TB_Estate_UnitDao().checkaddDy(ts_id, louhao1, Es_id,Bu_id)) {
                           out.print("errors-添加失败!单元名称不能重复输入！");
                       } else {
                           boolean b = new TB_Estate_UnitDao().dyAdd(ts_id, Es_id,Bu_id, louhao1, Remark);
                           if (b) {
                               String userid = session.getAttribute("USER_ID").toString();
                               String l_content = "添加单元：" + qsBuName;
                               try {
                                   new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                               } catch (Exception e) {
                                   // TODO Auto-generated catch block
                                   e.printStackTrace();
                               }
                               out.print("ssok-添加单元成功！");
                           } else {
                               out.print("errors-添加单元失败!");
                           }
                       }
                   
                   }
	           }
	           if("2".equals(args)){ //修改单元
	        	   String tu_id = session.getAttribute("TU_ID").toString();
                   String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                   String Un_id = request.getParameter("Un_id");
                   String UnName = S_string.formatString(request.getParameter("UnName").trim());
                   String Remark = S_string.formatString(request.getParameter("Remark").trim());
                   String esAndly=new TB_Estate_UnitDao().getESAndBuid(Un_id, ts_id);
                   if(!"".equals(esAndly)){
                	   String Es_id=esAndly.split("#")[0];
                       String Bu_id=esAndly.split("#")[1];
                       List<String> namelist =new TB_Estate_UnitDao().TB_Estate_UnitNameNot(ts_id, Es_id, Bu_id, Un_id);
                       if (namelist.contains(UnName)) {
                           out.print("errors-修改失败!该小区楼宇下已有该单元号！");
                       }else{
                    	   boolean b =new TB_Estate_UnitDao().dyxg(ts_id, Un_id, UnName, Remark);
                    	   if (b) {
                               String userid = session.getAttribute("USER_ID").toString();
                               String l_content = "修改单元：" + UnName;
                               try {
                                   new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                               } catch (Exception e) {
                                   // TODO Auto-generated catch block
                                   e.printStackTrace();
                               }
                               out.print("ssok-修改单元成功！");
                           } else {
                               out.print("errors-修改单元失败!");
                           }
                       }
                   }else{
                	   out.print("errors-修改单元信息失败!");
                   }
	           }
	           if("3".equals(args)){//删除单元
	        	   String tu_id = session.getAttribute("TU_ID").toString();
                   String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                   String Un_id = request.getParameter("Un_id");
                   boolean bl =new TB_Estate_UnitDao().checkDyfw(Un_id, ts_id);
                   if (bl) {
                       out.print("errors-删除失败!请先删除单元下的房屋信息！");
                   }else{
                	   boolean b=new TB_Estate_UnitDao().delete_TB_Unit(Un_id, ts_id);
                	   if(b){
                		   String userid = session.getAttribute("USER_ID").toString();
                           String esname = new TB_Estate_UnitDao().getUnname(Un_id,ts_id);
                           String l_content = "删除单元：" + esname;
                           try {
                               new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                           } catch (Exception e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace();
                           }
                           out.print("ssok-删除单元信息成功！");
                       } else {
                           out.print("errors-删除单元信息失败!");
                       }
                   }
	           }
        	   out.flush();
               out.close();
	          }catch (Exception e) {
	                e.printStackTrace();
	          }
	       }
		
	}

}
