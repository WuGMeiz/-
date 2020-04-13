package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WYBack_Stage.Bean.TB_Estate_Examine;
import WYBack_Stage.Dao.GetWj_Dao;

import com.alibaba.fastjson.JSON;

public class GetWjServlet extends HttpServlet {

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
	      response.setCharacterEncoding("UTF-8");
	      response.setContentType("text/json;charset=utf-8");
	     
			String Es_id=request.getParameter("Es_id");
			int type=Integer.valueOf(request.getParameter("type"));
			List<TB_Estate_Examine> lists=new GetWj_Dao().getWjByEs_id(Es_id,type);
			
			
			String json = JSON.toJSONString(lists);
			
			PrintWriter pw =  response.getWriter();
			pw.print(json);
	}

}
