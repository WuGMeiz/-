package WYBack_Stage.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import WYBack_Stage.Bean.TB_Estate_Exoption;
import WYBack_Stage.Dao.TB_Estate_ExoptionDao;
import WYBack_Stage.Dao.TB_Estate_RepPeopleDao;
import WYCommunity.MutiFileUpload;


/**
 * Servlet implementation class TB_Estate_ExoptionUpdateServlet
 */
public class TB_Estate_ExoptionUpdateServlet extends MutiFileUpload{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TB_Estate_ExoptionUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		//获取头像图片
		int imgwidth = 0;
		int imgheight = 0;
		/* 对上传文件夹和临时文件夹进行初始化 */
		String tmpPath = this.getServletContext().getRealPath("") + File.separator + "tmpdir";
		String savePath = this.getServletContext().getRealPath("") + File.separator + "upload";//
		long filesizeMax = Long.parseLong("6144");// 通过随机启动的配置文件得到上传图片限定的大小
		parseimg(request, tmpPath, savePath, filesizeMax, imgwidth, imgheight);// 调用父类文件上传图片的方法。
		TB_Estate_Exoption exoption = new TB_Estate_Exoption();
		String return_divid =parameters.get("return_divid"); // 成功后返回的刷新DIV的ID 
		String return_url=parameters.get("return_url");  // 成功后返回的页面
		String close_divid = parameters.get("close_divid");	//成功后关闭的DIV的ID
		exoption.setOp_id(Integer.parseInt(parameters.get("op_id")));
		exoption.setIf_tw(Integer.parseInt(parameters.get("if_tw")));
		//传入中文会乱码  重新编码
		String optionName=parameters.get("optionName");
		optionName=new String(optionName.getBytes("ISO8859_1"),"UTF-8");
		exoption.setOptionName(optionName);
		String InfImage=parameters.get("InfImage");
		String cuttFile = null;
		String srcImageFile = null;
		if(InfImage.length() > 0){
			InfImage=new String(InfImage.getBytes("ISO8859_1"),"UTF-8");
		    srcImageFile = savePath + File.separator + InfImage;// 保存到服务器上的包括图片的全部物理路径
		    cuttFile = savePath + File.separator + "cut" + InfImage; // 缩略图路径
		    File file = new File(srcImageFile);
		    String style=InfImage.split("\\.")[1];
		    //将png转换成jpg
	    	if("png".equalsIgnoreCase(style)){
	    		srcImageFile=savePath + File.separator +InfImage.split("\\.")[0]+".jpg";
	    		TB_Estate_RepPeopleDao.convertPngToJpg(file,srcImageFile);

	    	}
	    	Thumbnails.of(srcImageFile).scale(1f).outputQuality(0.1f).toFile(cuttFile);// 图片尺寸不变，压缩图片大小
	    	Thumbnails.of(srcImageFile).size(500, 500).toFile(cuttFile); //指定大小进行缩放
		}
    	exoption.setImages(cuttFile);
    	boolean bl = new TB_Estate_ExoptionDao().update_Exoption(exoption);
    	if(bl){
    		/*HttpSession session = request.getSession();
    		session.setAttribute("To_id", request.getParameter("To_id").toString());*/
    		//out.print("<script>alert('修改成功');");
    		out.print("<script>alert('修改成功');parent.showselect('"+return_divid+"','"+return_url+"');parent.close_tanchu_div('"+close_divid+"');</script></script>");
    	}else{
    		//out.print("<script>alert('修改失败');");
    		out.print("<script>alert('修改失败');parent.showselect('"+return_divid+"','"+return_url+"');parent.close_tanchu_div('"+close_divid+"');</script></script>");
    	}
	   
    	if(srcImageFile != null){
    		//删除保存在服务器上的图片
    	    String str[] = {srcImageFile, cuttFile };
            for (int i = 0; i < str.length; i++) {
                File f = new File(str[i]);
                if (f.exists()) {
                    f.delete();
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
