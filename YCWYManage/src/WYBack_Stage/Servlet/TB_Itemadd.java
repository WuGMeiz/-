package WYBack_Stage.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WYBack_Stage.Bean.TB_Estate_item;
import WYBack_Stage.Dao.ChangeDao;
import WYCommunity.MyTBCommit;
import WYCommunity.S_string;

public class TB_Itemadd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
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
				String arg=S_string.formatString(request.getParameter("arg").trim());
				String xiaoqu=S_string.formatString(request.getParameter("xiaoqu").trim());
				String sfname=S_string.formatString(request.getParameter("sfname").trim());
				String sftype=S_string.formatString(request.getParameter("sftype").trim());
				String price=S_string.formatString(request.getParameter("price").trim()); //电梯费的默认为0
				String remark=S_string.formatString(request.getParameter("remark").trim());
				String EhType=S_string.formatString(request.getParameter("EhType").trim()); //房屋类型
				String adminuser_id=session.getAttribute("USER_ID").toString();
				String ts_id=session.getAttribute("U_ID").toString();
				String a_id=session.getAttribute("A_ID").toString();
				String znjday=S_string.formatString(request.getParameter("znjday").trim());
				String zjnbl=S_string.formatString(request.getParameter("zjnbl").trim());
				String sfdesc="";//request.getParameter("sfdesc");
				String Limited="";//request.getParameter("Limited");
				/*if(!Limited.equals("")){
					Limited=Limited.substring(0, Limited.length()-1);
				}*/
				String LimitNumber="";//request.getParameter("LimitNumber");
				String gdxz="";//request.getParameter("gdxz");
				String isyj="";//request.getParameter("isyj");
				String yhCon="";
				String yhJzCon="";
				String yhRatio="";
				String guRatio="";
				String HouseStandard="";
				String BelowStandard="";
				String AboveStandard="";
				String se_qz1="",se_hz1="",Seprice1="",se_qz2="",se_hz2="",Seprice2="";
				String se_qz3="",se_hz3="",Seprice3="",se_qz4="",se_hz4="",Seprice4="";
				String se_qz5="",se_hz5="",Seprice5="",se_qz6="",se_hz6="",Seprice6="";
			    if("1".equals(arg)){ //物业费
			    }
			    if("2".equals(arg)){ //供暖费
			    }
			    if("3".equals(arg)){ //电梯费 
			    	se_qz1=S_string.formatString(request.getParameter("se_qz1"));
			    	se_hz1=S_string.formatString(request.getParameter("se_hz1"));
			    	Seprice1=S_string.formatString(request.getParameter("Seprice1"));
			    	se_qz2=S_string.formatString(request.getParameter("se_qz2"));
			    	se_hz2=S_string.formatString(request.getParameter("se_hz2"));
			    	Seprice2=S_string.formatString(request.getParameter("Seprice2"));
			    	se_qz3=S_string.formatString(request.getParameter("se_qz3"));
			    	se_hz3=S_string.formatString(request.getParameter("se_hz3"));
			    	Seprice3=S_string.formatString(request.getParameter("Seprice3"));
			    	se_qz4=S_string.formatString(request.getParameter("se_qz4"));
			    	se_hz4=S_string.formatString(request.getParameter("se_hz4"));
			    	Seprice4=S_string.formatString(request.getParameter("Seprice4"));
			    	se_qz5=S_string.formatString(request.getParameter("se_qz5"));
			    	se_hz5=S_string.formatString(request.getParameter("se_hz5"));
			    	Seprice5=S_string.formatString(request.getParameter("Seprice5"));
			    	se_qz6=S_string.formatString(request.getParameter("se_qz6"));
			    	se_hz6=S_string.formatString(request.getParameter("se_hz6"));
			    	Seprice6=S_string.formatString(request.getParameter("Seprice6"));
			    	if("".equals(price)){
			    		price=Seprice1;
			    	}
			    }
			    if("4".equals(arg)){ //车位费
			    }
			    if("5".equals(arg)){ //水费
			    	Limited=request.getParameter("Limited");
			    	if(!Limited.equals("")){
						Limited=Limited.substring(0, Limited.length()-1);
					}
			    	LimitNumber=request.getParameter("LimitNumber");
			    	gdxz=request.getParameter("gdxz");
			    	isyj=request.getParameter("isyj");
			    	HouseStandard=request.getParameter("HouseStandard");
			    	BelowStandard=request.getParameter("BelowStandard");
			    	AboveStandard=request.getParameter("AboveStandard");
			    }
                if("6".equals(arg)){ //电费
                	Limited=request.getParameter("Limited");
			    	if(!Limited.equals("")){
						Limited=Limited.substring(0, Limited.length()-1);
					}
			    	LimitNumber=request.getParameter("LimitNumber");
			    	gdxz=request.getParameter("gdxz");
			    	isyj=request.getParameter("isyj");
			    	HouseStandard=request.getParameter("HouseStandard");
			    	BelowStandard=request.getParameter("BelowStandard");
			    	AboveStandard=request.getParameter("AboveStandard");
			    }
               //先判断该小区下是否存在该缴费项
               TB_Estate_item item=new ChangeDao().getTBPay_Item_NameInfo(xiaoqu, sfname,ts_id);
               String Ei_id="";  //缴费项id
               if(item==null){
            	  boolean b=new  ChangeDao().add_Item(a_id, ts_id, xiaoqu, sfname);
            	  if(b){
            		  item=new ChangeDao().getTBPay_Item_NameInfo(xiaoqu,sfname,ts_id);
            		  Ei_id=item.getEi_id()+"";
            	  }else{
            		  out.print("errors-添加收费项目失败！"); 
            	  }
               }else{
            	   Ei_id=item.getEi_id()+"";
               }
               if(!"".equals(Ei_id)){
            	   int pd= new ChangeDao().getTBPay_ChargeTypeInfo(Ei_id, EhType);
            	   if(pd==0){// 返回0为没有；1为已经存在该数据
                 	   boolean bl= new ChangeDao().TB_Estate_ChargeType( EhType,Ei_id,price,sftype, remark,znjday, zjnbl, 
                 			Limited, LimitNumber,gdxz,isyj, yhCon, yhJzCon, yhRatio, guRatio,HouseStandard,BelowStandard,AboveStandard);
                 		if(bl)
         				{
                 		  if(!"3".equals(arg)){
                 		    String l_content="添加收费项目："+sfname+"，房屋类型"+EhType;
              				String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
              				ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
              				out.print("ssok-添加收费项目成功！");
                 		  }else{
                 			int num=new ChangeDao().selDitiqj(xiaoqu, Ei_id);
                  		    if(num==0){
                  			   List<String> Sqllist = new ArrayList<String>();
                      		   String sql1="",sql2="",sql3="",sql4="",sql5="",sql6="";
             				  if(!"".equals(se_qz1) || !"".equals(se_hz1) || !"".equals(Seprice1)){
             					// out.print("errors-添加电梯费项目失败！请设置区间1信息");    
             					 sql1="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+xiaoqu+"','"+Ei_id+"','"+se_qz1+"','"+se_hz1+"','"+Seprice1+"',getdate(),'1')"; 
             				   }
             				    if(!"".equals(se_qz2) && !"".equals(se_hz2) && !"".equals(Seprice2)){
             				      sql2="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+xiaoqu+"','"+Ei_id+"','"+se_qz2+"','"+se_hz2+"','"+Seprice2+"',getdate(),'1')"; 
             				     }
             				    if(!"".equals(se_qz3) && !"".equals(se_hz3) && !"".equals(Seprice3)){
             				      sql3="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+xiaoqu+"','"+Ei_id+"','"+se_qz3+"','"+se_hz3+"','"+Seprice3+"',getdate(),'1')"; 
             				     }
             				   if(!"".equals(se_qz4) && !"".equals(se_hz4) && !"".equals(Seprice4)){
           				        sql4="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+xiaoqu+"','"+Ei_id+"','"+se_qz4+"','"+se_hz4+"','"+Seprice4+"',getdate(),'1')"; 
           				       }
             				    if(!"".equals(se_qz5) && !"".equals(se_hz5) && !"".equals(Seprice5)){
           				        sql5="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+xiaoqu+"','"+Ei_id+"','"+se_qz5+"','"+se_hz5+"','"+Seprice5+"',getdate(),'1')"; 
           				        }
             			       if(!"".equals(se_qz6) && !"".equals(se_hz6) && !"".equals(Seprice6)){
           				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+xiaoqu+"','"+Ei_id+"','"+se_qz6+"','"+se_hz6+"','"+Seprice6+"',getdate(),'1')"; 
           				       }
    	           				if(!"".equals(sql1)){
    	           					Sqllist.add(sql1);
    	           				}
    	           				if(!"".equals(sql2)){
    	           					Sqllist.add(sql2);
    	           				}
    	           				if(!"".equals(sql3)){
    	           					Sqllist.add(sql3);
    	           				}
    	           				if(!"".equals(sql4)){
    	           					Sqllist.add(sql4);
    	           				}
    	           				if(!"".equals(sql5)){
    	           					Sqllist.add(sql5);
    	           				}
    	           				String flag= MyTBCommit.TBCommit(Sqllist);
    	           				if("".equals(flag)){
    	           					String l_content="添加电梯费成功，房屋类型"+EhType+se_qz1+se_hz1+Seprice1;
                     				String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
                     				ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
                     				out.print("ssok-添加电梯费成功！"); 
    	           				}else{
    	           					out.print("errors-电梯费楼层区间设置失败！");
    	           				}
                 				   
                  		   }else{
                  			   out.print("ssok-该小区电梯费楼层区间已设置，请直接修改！"); 
                  		   }  
                 		  }
         				}else{
         					out.print("errors-添加收费项目失败！");
         				}
                 	 }else{
                 		   out.print("errors-添加收费项目失败！此房屋类型下收费项目已存在！");
         			 }  
            	   
               }else{
            	   out.print("errors-添加收费项目失败！"); 
               }
			
			}
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
