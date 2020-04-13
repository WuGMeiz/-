package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Bean.TB_Estate_Housetype;
import WYBack_Stage.Dao.HostTypeDao;
import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.TB_Estate_RepPeopleDao;
import WYCommunity.S_string;
import WYCommunity.T_time;

public class HostTypeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public HostTypeServlet(){
		super();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
	    if (session.getAttribute("USER_ID") == null) {
            String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
            out.println("window.location='" + url + "';");
            out.println("</script>");
        } else {
        	String arg = request.getParameter("arg");
        	String tu_id = session.getAttribute("TU_ID").toString();
            String user_id = session.getAttribute("USER_ID").toString();
        	try{
        	 if("1".equals(arg)){ //添加
        		 String Es_id=S_string.formatString(request.getParameter("Es_id"));
        		 String ReName=S_string.formatString(request.getParameter("ReName").trim());
        		 if(new HostTypeDao().checkhostType(ReName, Es_id)){
        			 out.print("errors-添加失败!该小区下已存在该房屋类型！");
        		 }else {
        			 TB_Estate_Housetype houseType = new TB_Estate_Housetype();
        			 houseType.setHtName(ReName);
        			 houseType.setRemark1(Es_id);
        			 houseType.setStatus(1);
        			 houseType.setCreat_time(new T_time().getTime());
        			 boolean addAll = new HostTypeDao().addAll(houseType);
                  
                     if (addAll) {
                         String l_content="添加房屋类型："+ReName;
                         new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                         out.print("sok-添加房屋类型成功");
                     } else {
                         out.print("errors-添加房屋类型失败");
                     }
              }
        	 }
        	 
        	 if("2".equals(arg)){//修改
            	 String ReName=request.getParameter("ReName"); 
            	 String Re_id=request.getParameter("Re_id"); 
            	 String remark=S_string.formatString(request.getParameter("remark").trim());
            	 TB_Estate_Housetype hoseType = new TB_Estate_Housetype();
            	 hoseType.setHtName(ReName);
            	 hoseType.setRemark2(remark);
            	 hoseType.setHt_id(Integer.valueOf(Re_id));
            	 boolean updataHostType = new HostTypeDao().updataHostType(hoseType);
                 if (updataHostType) {
                     String l_content="修改房屋类型："+ReName;
                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                     out.print("sok-修改房屋类型成功");
                 } else {
                     out.print("errors-修改房屋类型失败");
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
