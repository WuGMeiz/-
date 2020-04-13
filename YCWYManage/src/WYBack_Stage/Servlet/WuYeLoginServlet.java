package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import WYBack_Stage.Bean.TB_SEV_USER;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYCommunity.MD5;
import WYCommunity.MyCookie;
import WYCommunity.S_string;
import WYCommunity.T_time;

public class WuYeLoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Constructor of the object.
     */
    public WuYeLoginServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }
    private void checkSession(String userId, HttpServlet servlet, HttpSession session)
	  {
	    ServletContext sc = servlet.getServletContext();
	    if (sc.getAttribute(userId) != null)
	    {
	      try
	      {
	        ((HttpSession)sc.getAttribute(userId)).invalidate();
	      } catch (Exception e) {
	        sc.setAttribute(userId, session);
	      }
	    }
	    else
	    {
	      sc.setAttribute(userId, session);
	    }
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
     * This method is called when a form has its tag value method equals to post.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("GB2312");
        response.setContentType("text/html;charset=UTF-8"); ////这样设置可以让弹出框在IE和火狐下显示正常
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String login_ip=request.getRemoteAddr();////得到本次登录IP
        String login_time=new T_time().getTime();////得到本次登录时间
        String tablename="";
        String url="";
        String loginurl="";
        tablename="TB_SEV_USER";
        url="YCWYPage/BackPage/wymain.jsp";
        loginurl="YCWYPage/BackPage/login.jsp";
        String user_id=request.getParameter("user_id");
        String password=request.getParameter("password");
        
        String code = request.getParameter("code");
        String rand = S_string.formatString(session.getAttribute("rand").toString());
        if(!code.equals(rand))
        {
            out.println("<script>alert('登陆失败！验证码错误！');");
            out.println("window.location='"+loginurl+"';");
            out.println("</script>");
        }else{
        	checkSession(user_id, this, request.getSession());
            if(S_string.dealSpecialString(user_id) && S_string.dealSpecialString(password))
            {
                password=new MD5().createMd5(password);
                TB_SEV_USER str =new MyTB_SEV_USER().getUserLogin(user_id, password, tablename);                   
                if(str!=null){

                    String CookiesName=tablename;
                    String CookiesValue=user_id;
                    String jzyhm=S_string.formatString(request.getParameter("jzyhm"));
                    if(jzyhm.equals(""))
                    {
                        MyCookie.setCookies(response, CookiesName, CookiesValue, 0);
                    }       
                    else
                    {
                        MyCookie.setCookies(response, CookiesName, CookiesValue, 365*24*60*60);
                    }
                    // session封装信息  
                    session.setAttribute("CKFinder_UserRole", "ccbjf_ckfd_admin");////给CKFinder图片管理器赋值权限
                    
                    session.setAttribute("LOGIN_IP",str.getLogin_ip().equals("")?login_ip:str.getLogin_ip());  // 用户上次登录IP
                    session.setAttribute("LOGIN_TIME",str.getLogin_time().equals("")?login_time:str.getLogin_time());  // 用户上次登录时间
        
                    session.setAttribute("USER_ID",user_id);  // 用户登陆账号
                    session.setAttribute("loginurl",loginurl);  // 用户登陆页面
                    session.setAttribute("TABLENAME",tablename);  // 管理员用户表名
                    session.setAttribute("TU_ID",str.getTu_id());  // 用户的ID
                    session.setAttribute("ROLE_CODE",str.getRole_code());  // 用户角色ID
                    session.setAttribute("USER_NAME",str.getUsername().equals("")?"管理员":str.getUsername());  // 用户姓名
                    session.setAttribute("ORG_ID",str.getOrg_id());  // 所属机构ID
                    session.setAttribute("U_ID",str.getU_id());  // 所属银行或商户的ID
                    session.setAttribute("LEVELS",str.getLevels());  // 用户级别
                    session.setAttribute("YZNR",str.getQq());  // 获取小区权限
                    session.setAttribute("ORG_LEVEL",str.getLevels());
                    session.setAttribute("IsDrawBack",str.getIsdrawback()); //退款权限
                    session.setAttribute("PayWays",str.getPayWays());//线下支付方式
//                    String ORG_ID0=new SystemDao().getORG_id(str.getU_id());
//                    session.setAttribute("ORG_ID0", ORG_ID0);
                    MyTB_SEV_USER.update_user_login_timeorip(user_id, login_time, login_ip, tablename);//将本次登录的时间和IP更新到数据库
                    String FowrUrl=url; //成功登陆后转向管理主界面
                    response.sendRedirect(FowrUrl);
                
                }else
                { 
                    out.println("<script>alert('登陆失败！用户名或密码错误！！');");
                    out.println("window.location='"+loginurl+"';");
                    out.println("</script>");
                }
            }else
            {                   
                out.println("<script>alert('登陆失败！帐号或密码格式不合法！');");
                out.println("window.location='"+loginurl+"'"); 
                out.println("</script>");
            }
        }
        out.flush();
        out.close();
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
