package WYBack_Stage.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


import WYBack_Stage.Dao.TB_Estate_Extopic_Dao;

/**
 * Servlet implementation class GetWenJuanServlet
 */
public class GetWenJuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWenJuanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	     doPost(request, response);
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/json;charset=utf-8");
	    
	    //准备数据
	    String xqId = request.getParameter("xqId");
	    String typeId = request.getParameter("typeId");
	    List<HashMap<String,String>> wenjuans = new TB_Estate_Extopic_Dao().wenjuans(xqId, typeId);
	    
	    
	    //json处理
	    String json = JSON.toJSONString(wenjuans);
	    response.setContentType("application/json; charset=utf-8");
	    //返回结果
	    response.getWriter().print(json);
	}

}
