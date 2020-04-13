package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.common.LengthUnit;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import WYBack_Stage.Dao.MyTBAdmin_Log;
import WYBack_Stage.Dao.MyTB_SEV_USER;
import WYBack_Stage.Dao.TB_Estate_Extopic_Dao;
import WYBack_Stage.Dao.TB_Village_Dao;
import WYCommunity.S_string;
import WYCommunity.T_time;

/**
 * Servlet implementation class TB_Estate_Extopic_SERVLET
 */
@WebServlet("/TB_Estate_Extopic_SERVLET")
public class TB_Estate_Extopic_SERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TB_Estate_Extopic_SERVLET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String args = request.getParameter("args");
        InputStream imgIs = null;
        
        if (session.getAttribute("USER_ID") == null) {
            String url = request.getContextPath() + "/YCWYPage/BackPage/login.jsp";
            out.println("<script>alert('您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！')");
            out.println("window.location='" + url + "';");
            out.println("</script>");
        } else {
            try {
            	if ("1".equals(args)) {
            		
            		//"TopicSort"+TopicSort+"&TopicName="+TopicName+"&TopicIfTw="+TopicIfTw+"&TopicType="+TopicType+"&AmineId="+AmineId;
                    //String TopicSort = new MyTB_SEV_USER().getTs_id(tu_id);
            		String tu_id = session.getAttribute("TU_ID").toString();
                    String TopicSort = S_string.formatString(request.getParameter("TopicSort"));
                    String TopicName =S_string.formatString(request.getParameter("TopicName"));
                    String TopicIfTw = S_string.formatString(request.getParameter("TopicIfTw"));
                    String TopicType = S_string.formatString(request.getParameter("TopicType"));
                    String wenjuanId = S_string.formatString(request.getParameter("wenjuanId"));
               
                    String time = new T_time().getTime();
                    
                    boolean b = new TB_Estate_Extopic_Dao().add(wenjuanId, TopicIfTw, TopicName, TopicType, new T_time().getTime(), "1", TopicSort);
                    if (b) {
                        String userid = session.getAttribute("USER_ID").toString();
                        String l_content = "添加题目：" + TopicName;
                        try {
                            new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        out.print("ssok-添加题目成功！");
                    } else {
                        out.print("errors-添加题目失败!");
                    }
				}
            	if("2".equals(args)){
            		//"xiugaiIfwg="+xiugaiIfwg+"&xiugaiTopicName="+xiugaiTopicName+"&xiugaiType="+xiugaiType+"&xiugaisort="+xiugaisort;
            		String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String Es_id = request.getParameter("Es_id");
                    String xiugaiIfwg = S_string.formatString(request.getParameter("xiugaiIfwg"));
                    String xiugaiTopicName = S_string.formatString(request.getParameter("xiugaiTopicName"));
                   // String xiugaiTopicName = new String(S_string.formatString(request.getParameter("xiugaiTopicName")).getBytes("ISO8859_1"),"UTF-8");
                    String xiugaiType = S_string.formatString(request.getParameter("xiugaiType"));
                    String xiugaisort = S_string.formatString(request.getParameter("xiugaisort"));
                    //to_id
                    String to_id = S_string.formatString(request.getParameter("to_id"));
                    boolean b = new TB_Estate_Extopic_Dao().update(to_id, xiugaiIfwg, xiugaiTopicName, xiugaiType, xiugaisort);
                    if (b) {
                        String userid = session.getAttribute("USER_ID").toString();
                        String l_content = "修改题目";
                        try {
                            new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        out.print("ssok-修改题目成功！");
                    } else {
                        out.print("errors-修改题目失败!");
                    }
				}
            	if ("3".equals(args)) {// 删除小区信息
            		String tu_id = session.getAttribute("TU_ID").toString();
                    String to_id = request.getParameter("to_id");
                    boolean bl = new TB_Estate_Extopic_Dao().if_two(to_id);
                    if (bl) {
                        out.print("errors-删除失败!请先删除题目下的题目选项");
                    } else {
                        boolean b = new TB_Estate_Extopic_Dao().del(to_id);
                        if (b) {
                            String userid = session.getAttribute("USER_ID").toString();
                            String l_content = "删除题目";
                            try {
                                new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            out.print("ssok-删除题目成功！");
                        } else {
                            out.print("errors-删除题目失败!");
                        }
                    }
                }
            	if("4".equals(args)){
            		//上传图片
            		String tu_id = session.getAttribute("TU_ID").toString();
                    String TopicSort = S_string.formatString(request.getParameter("TopicSort"));
                    String TopicName = S_string.formatString(request.getParameter("TopicName"));
                    String TopicIfTw = S_string.formatString(request.getParameter("TopicIfTw"));
                    String TopicType = S_string.formatString(request.getParameter("TopicType"));
                    String wenjuanId = S_string.formatString(request.getParameter("wenjuanId"));
            		
            	    
                    response.setContentType("text/html;charset=UTF-8");
            	    //上传工厂配置类 配置上传文件大小等
            	    DiskFileItemFactory fc = new DiskFileItemFactory();
            	    ServletFileUpload upload = new ServletFileUpload(fc);
            	    
            	    @SuppressWarnings("unchecked")
					List<FileItem> list = upload.parseRequest(request);
            	    long size = 0;
            	   
        			for(FileItem fileItem:list){
        				if (!fileItem.isFormField()) {
        					imgIs = fileItem.getInputStream();
        					size = fileItem.getSize();
        				}
        			}
        			
        			//String ex_id,String if_tw,String topic,String type,String create_time,String status,String sort,InputStream is,int length
					boolean b;
					if(size != 0){
						b = new TB_Estate_Extopic_Dao().addImg(wenjuanId, TopicIfTw, TopicName, TopicType, new T_time().getTime(), "1", TopicSort, imgIs, (int)size);
					}else{
						b = new TB_Estate_Extopic_Dao().add(wenjuanId, TopicIfTw, TopicName, TopicType, new T_time().getTime(), "1", TopicSort);
					}
                    if (b) {
                        String userid = session.getAttribute("USER_ID").toString();
                        String l_content = "添加题目：" + TopicName;
                        try {
                            new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        out.print("ssok-添加题目成功！");
                    } else {
                        out.print("errors-添加题目失败!");
                    }
            	}
            	
            	
            	if("5".equals(args)){
            		//上传图片
            		String tu_id = session.getAttribute("TU_ID").toString();
                    String ts_id = new MyTB_SEV_USER().getTs_id(tu_id);
                    String Es_id = request.getParameter("Es_id");
                    String xiugaiIfwg = S_string.formatString(request.getParameter("xiugaiIfwg"));
                    String xiugaiTopicName = S_string.formatString(request.getParameter("xiugaiTopicName"));
                    //String xiugaiTopicName = new String(S_string.formatString(request.getParameter("xiugaiTopicName")).getBytes("iso-8859-1"),"utf-8");
                    String xiugaiType = S_string.formatString(request.getParameter("xiugaiType"));
                    String xiugaisort = S_string.formatString(request.getParameter("xiugaisort"));
                  //to_id
                    String to_id = S_string.formatString(request.getParameter("to_id"));
            		
            	    response.setContentType("text/html;charset=UTF-8");
            	    //上传工厂配置类 配置上传文件大小等
            	    DiskFileItemFactory fc = new DiskFileItemFactory();
            	    ServletFileUpload upload = new ServletFileUpload(fc);
            	    
            	    @SuppressWarnings("unchecked")
					List<FileItem> list = upload.parseRequest(request);
            	    long size = 0;
        			for(FileItem fileItem:list){
        				if (!fileItem.isFormField()) {
        					imgIs = fileItem.getInputStream();
        					size = fileItem.getSize();
        				}
        			}
        			boolean b;
        			if(size == 0){
        				b = new TB_Estate_Extopic_Dao().update(to_id, xiugaiIfwg, xiugaiTopicName, xiugaiType, xiugaisort);
        			}else{
        				b = new TB_Estate_Extopic_Dao().updateImg(to_id, xiugaiIfwg, xiugaiTopicName, xiugaiType, xiugaisort,imgIs,(int)size);
        			}
        			
					
                    if (b) {
                        String userid = session.getAttribute("USER_ID").toString();
                        String l_content = "添加题目";
                        try {
                            new MyTBAdmin_Log().add_Log(l_content, userid, tu_id);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        out.print("ssok-修改题目成功！");
                    } else {
                        out.print("errors-修改题目失败!");
                    }
            	}
            	
                out.flush();
                out.close();
                if(imgIs!=null){
                	imgIs.close();
                }
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            }
	}

}
