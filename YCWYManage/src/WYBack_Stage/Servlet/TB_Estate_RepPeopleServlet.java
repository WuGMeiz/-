package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTB_SEV_ORG_DAO;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYBack_Stage.Dao.TB_Estate_ExDao;
import WYBack_Stage.Dao.TB_Estate_RepPeopleDao;
import WYCommunity.S_string;

public class TB_Estate_RepPeopleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
        		 String phone=S_string.formatString(request.getParameter("phone").trim());
        		 if(new TB_Estate_RepPeopleDao().checkRepPeo(Es_id,ReName,phone)){
        			 out.print("errors-添加失败!该小区下已存在用户！");
        		 }else {
                     boolean bl = new TB_Estate_RepPeopleDao().addRepPeo(Es_id, ReName, phone);
                     if (bl) {
                         String l_content="添加维修人员："+ReName;
                         new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                         out.print("sok-添加维修人员成功");
                     } else {
                         out.print("errors-添加维修人员失败");
                     }
              }
        	 }
        	 if("2".equals(arg)){//修改
        		 String Re_id = request.getParameter("Re_id");
            	 String ReName=request.getParameter("ReName"); 
            	 String phone=S_string.formatString(request.getParameter("phone").trim());
            	 String remark=S_string.formatString(request.getParameter("remark").trim());
            	 boolean bl = new TB_Estate_RepPeopleDao().updateRepPeo(Re_id,ReName,phone,remark);
                 if (bl) {
                     String l_content="修改维修人员："+ReName;
                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                     out.print("sok-修改维修人员成功");
                 } else {
                     out.print("errors-修改维修人员失败");
                 }
        	 }
             if("3".equals(arg)){//删除
            	 String Re_id = request.getParameter("Re_id");
            	 String ReName=request.getParameter("ReName");
            	 boolean bl = new TB_Estate_RepPeopleDao().deleteRepPeo(Re_id);
                 if (bl) {
                     String l_content="删除维修人员："+ReName;
                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                     out.print("sok-添加维修人员成功");
                 } else {
                     out.print("errors-添加维修人员失败");
                 }
        	 }
             if("4".equals(arg)){//受理用户报修信息
            	 String Inf_id = request.getParameter("Inf_id");
            	 String content=request.getParameter("content");
            	 boolean bl = new TB_Estate_RepPeopleDao().RepInfo(Inf_id);
                 if (bl) {
                	 if(content.length()>5){
                		 content=content.substring(0, 4);
                	 }
                     String l_content="受理报修信息："+content;
                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                     out.print("sok-受理成功");
                 } else {
                     out.print("errors-受理失败");
                 }
        	 }
             if("5".equals(arg)){//受理用户投诉信息
            	 String Co_id = request.getParameter("Inf_id");
            	 String content=request.getParameter("content");
            	 boolean bl = new TB_Estate_RepPeopleDao().TouInfo(Co_id);
                 if (bl) {
                	 if(content.length()>5){
                		 content=content.substring(0, 4);
                	 }
                     String l_content="处理投诉信息："+content;
                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                     out.print("sok-处理成功");
                 } else {
                     out.print("errors-处理失败");
                 }
        	 }
             if("6".equals(arg)){ //添加满意度  投票问卷信息
            	 String ts_id = session.getAttribute("U_ID").toString(); 
            	 String Es_id=S_string.formatString(request.getParameter("Es_id").trim());
            	 String title=S_string.formatString(request.getParameter("title").trim());
            	 String description=S_string.formatString(request.getParameter("description").trim());
            	 String Type=S_string.formatString(request.getParameter("Type").trim());
            	 String status=S_string.formatString(request.getParameter("status").trim());
            	 String counts=S_string.formatString(request.getParameter("counts").trim());
            	 String start_time=S_string.formatString(request.getParameter("start_time").trim());
            	 start_time=start_time+" 00:00:00";
            	 String end_time=S_string.formatString(request.getParameter("end_time").trim());
            	 end_time=end_time+" 23:59:59";
            	 String remark=S_string.formatString(request.getParameter("remark").trim());
            	 if(new TB_Estate_ExDao().checkExamine(ts_id, Es_id, title)){
            		 out.print("errors-添加失败!同一小区下问卷标题不能重复！");
            	 }else{
            		boolean b=new TB_Estate_ExDao().addExamine(ts_id, Es_id, title, description, Type, status, counts, start_time, end_time, remark);
            	    if(b){
            	    	 String l_content="添加问卷标题："+title;
                         new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                         out.print("ssok-添加问卷成功！");
                    } else {
                        out.print("errors-添加问卷失败!");
                    }
            	 }
             }
             if("7".equals(arg)){ //删除问卷
            	 String ts_id = session.getAttribute("U_ID").toString(); 
            	 String Ex_id=S_string.formatString(request.getParameter("Ex_id").trim());
            	 boolean b=new TB_Estate_ExDao().delExamine(ts_id, Ex_id);
            	 if(b){
            		 String l_content="删除问卷编号："+Ex_id;
                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
                     out.print("ssok-删除成功");
                 } else {
                     out.print("errors-删除失败");
                 }
             }
             if("8".equals(arg)){ //修改满意度  投票问卷信息
            	 String ex_id=request.getParameter("ex_id").trim();
            	 String ts_id = session.getAttribute("U_ID").toString(); 
            	 String Es_id=S_string.formatString(request.getParameter("Es_id").trim());
            	 String title=S_string.formatString(request.getParameter("title").trim());
            	 String description=S_string.formatString(request.getParameter("description").trim());
            	 String Type=S_string.formatString(request.getParameter("Type").trim());
            	 String status=S_string.formatString(request.getParameter("status").trim());
            	 String counts=S_string.formatString(request.getParameter("counts").trim());
            	 String start_time=S_string.formatString(request.getParameter("start_time").trim());
            	 if(start_time.length()<17){
            		 start_time=start_time+" 00:00:00";
            	 }else{
            		 start_time=start_time.substring(0,10)+" 00:00:00";
            	 }
            	 String end_time=S_string.formatString(request.getParameter("end_time").trim());
            	 if(end_time.length()<17){
            		 end_time=end_time+" 23:59:59";
            	 }else{
            		 end_time=end_time.substring(0,10)+" 00:00:00";
            	 }
            	 String remark=S_string.formatString(request.getParameter("remark").trim());
            	 
        		 boolean b=new TB_Estate_ExDao().updateExamine(ts_id, Es_id, title, description, Type, status, counts, start_time, end_time, remark,ex_id);
	        	   if(b){
	        	    	 String l_content="=修改问卷标题："+title;
	                     new MyTBAdmin_Log().add_Log(l_content,user_id, tu_id);
	                     out.print("cssok-修改问卷成功！");
	                } else {
	                    out.print("errors-添加问卷失败!");
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
