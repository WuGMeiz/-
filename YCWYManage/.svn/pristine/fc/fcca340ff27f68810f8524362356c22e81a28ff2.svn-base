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

import WYBack_Stage.Dao.ChangeDao;
import WYCommunity.MyTBCommit;
import WYCommunity.S_string;

public class TB_ItemUpdate extends HttpServlet {

	private static final long serialVersionUID = 5915064187313726152L;

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
				String Ei_id=request.getParameter("Ei_id");
				String name=request.getParameter("name");
				String price=request.getParameter("price");
				String remark=S_string.formatString(request.getParameter("remark").trim());
				String shoufeifs=request.getParameter("shoufeifs");//收费方式
				String status=request.getParameter("status");
				String EhType=request.getParameter("EhType"); //房屋类型id
				String Ct_id=request.getParameter("Ct_id");  //子表主键
				String yhCon="";
				String yhJzCon="";
				String yhRatio="";
				String guRatio="";
				String LimitNumber="";//request.getParameter("LimitNumber1")==null?"":request.getParameter("LimitNumber1");
				String Limited="";//request.getParameter("Limited1")==null?"":request.getParameter("Limited1");
				String znjday=S_string.formatString(request.getParameter("znjday").trim());
				String znjbl=S_string.formatString(request.getParameter("znjbl").trim());
				String gdxz="";//request.getParameter("gdxz");
				String isyj="";//request.getParameter("isyj");
				String HouseStandard="";
				String BelowStandard="";
				String AboveStandard="";
				String adminuser_id=session.getAttribute("USER_ID").toString();
				String ts_id=session.getAttribute("U_ID").toString();
				String a_id=session.getAttribute("A_ID").toString();
				String qjsize="",Se_id1="",Se_id2="",Se_id3="",Se_id4="",Se_id5="",Se_id6="";
				String qj1="",qj2="",qj3="",qj4="",qj5="",qj6="",es_id="";
			    if("1".equals(arg)){ //物业费
			    }
			    if("2".equals(arg)){ //供暖费
			    }
			    if("3".equals(arg)){ //电梯费
			    
			    	qjsize=S_string.formatString(request.getParameter("qjsize").trim());
			    	Se_id1=S_string.formatString(request.getParameter("Se_id1").trim());
			    	Se_id2=S_string.formatString(request.getParameter("Se_id2").trim());
			    	Se_id3=S_string.formatString(request.getParameter("Se_id3").trim());
			    	Se_id4=S_string.formatString(request.getParameter("Se_id4").trim());
			    	Se_id5=S_string.formatString(request.getParameter("Se_id5").trim());
			    	Se_id6=S_string.formatString(request.getParameter("Se_id6").trim());
			    	qj1=S_string.formatString(request.getParameter("qj1").trim());
			    	qj2=S_string.formatString(request.getParameter("qj2").trim());
			    	qj3=S_string.formatString(request.getParameter("qj3").trim());
			    	qj4=S_string.formatString(request.getParameter("qj4").trim());
			    	qj5=S_string.formatString(request.getParameter("qj5").trim());
			    	qj6=S_string.formatString(request.getParameter("qj6").trim());
			    	es_id=S_string.formatString(request.getParameter("es_id").trim());
			    	if(("".equals(price) || "0.00".equals(price)) && !"".equals(qj1) ){
			    		price=qj1.split("#")[1];
			    	}
			    }
			    if("4".equals(arg)){ //车位费
			    }
			    if("5".equals(arg)){ //水费
			    	Limited=request.getParameter("Limited");
			    	LimitNumber=request.getParameter("LimitNumber");
			    	gdxz=request.getParameter("gdxz");
			    	isyj=request.getParameter("isyj");
			    	HouseStandard=request.getParameter("HouseStandard");
			    	BelowStandard=request.getParameter("BelowStandard");
			    	AboveStandard=request.getParameter("AboveStandard");
			    }
                if("6".equals(arg)){ //电费
                	Limited=request.getParameter("Limited");
			    	LimitNumber=request.getParameter("LimitNumber");
			    	gdxz=request.getParameter("gdxz");
			    	isyj=request.getParameter("isyj");
			    	HouseStandard=request.getParameter("HouseStandard");
			    	BelowStandard=request.getParameter("BelowStandard");
			    	AboveStandard=request.getParameter("AboveStandard");
			    }
                //先判断子表中是否已设置此房屋类型信息
                int pd= new ChangeDao().getTB_ChargeTypeInfo(Ei_id, EhType,Ct_id);
                if(pd==0){
                	boolean bl=new ChangeDao().updateChargeType(EhType,gdxz,isyj,LimitNumber,Limited,Ei_id,Ct_id,price,znjday,znjbl,status,shoufeifs,yhCon,yhJzCon,yhRatio,guRatio,remark,HouseStandard,BelowStandard,AboveStandard);
    				if(bl)
    				{
    					if(!"3".equals(arg)){
    						String l_content="修改收费项目："+name+"房屋类型："+EhType;
        					String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
        					ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
        					out.print("ssok-修改收费项目成功！");
    					}else{
    						List<String> Sqllist = new ArrayList<String>();
                   		    String sql1="",sql2="",sql3="",sql4="",sql5="",sql6="";
                       	    if(qjsize.equals("0")){ //电梯费区间之前未设置
                       	      if(!"".equals(qj1)){
                       	    	  sql1="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj1.split("#")[0].split("-")[0]+"','"+qj1.split("#")[0].split("-")[1]+"','"+qj1.split("#")[1]+"',getdate(),'1')"; 
                       	      }
             				  if(!"".equals(qj2)){
             				      sql2="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj2.split("#")[0].split("-")[0]+"','"+qj2.split("#")[0].split("-")[1]+"','"+qj2.split("#")[1]+"',getdate(),'1')";  
             				   }
             				  if(!"".equals(qj3)){
             				      sql3="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj3.split("#")[0].split("-")[0]+"','"+qj3.split("#")[0].split("-")[1]+"','"+qj3.split("#")[1]+"',getdate(),'1')"; 
             				   }
             				   if(!"".equals(qj4) ){
           				        sql4="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj4.split("#")[0].split("-")[0]+"','"+qj4.split("#")[0].split("-")[1]+"','"+qj4.split("#")[1]+"',getdate(),'1')"; 
           				       }
             				    if(!"".equals(qj5)){
           				        sql5="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj5.split("#")[0].split("-")[0]+"','"+qj5.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
           				        }
             			       if(!"".equals(qj6)){
           				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj6.split("#")[0].split("-")[0]+"','"+qj6.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
           				       }  
                       	      
                       	    }else if(qjsize.equals("1")){//之前设置一个区间
                       	      if("".equals("qj1")){
                       	    	sql1=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                         	  }else{
                         		sql1=" update TB_Estate_DTFSection set se_qz='"+qj1.split("#")[0].split("-")[0]+"',se_hz='"+qj1.split("#")[0].split("-")[1]+"',Seprice='"+qj1.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                         	  }
                       	 	  if(!"".equals(qj2)){
             				      sql2="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj2.split("#")[0].split("-")[0]+"','"+qj2.split("#")[0].split("-")[1]+"','"+qj2.split("#")[1]+"',getdate(),'1')";  
             				     }
             				   if(!"".equals(qj3)){
             				      sql3="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj3.split("#")[0].split("-")[0]+"','"+qj3.split("#")[0].split("-")[1]+"','"+qj3.split("#")[1]+"',getdate(),'1')"; 
             				   }
             				   if(!"".equals(qj4) ){
           				        sql4="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj4.split("#")[0].split("-")[0]+"','"+qj4.split("#")[0].split("-")[1]+"','"+qj4.split("#")[1]+"',getdate(),'1')"; 
           				       }
             				    if(!"".equals(qj5)){
           				        sql5="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj5.split("#")[0].split("-")[0]+"','"+qj5.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
           				        }
             			       if(!"".equals(qj6)){
           				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj6.split("#")[0].split("-")[0]+"','"+qj6.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
           				       }  
                       	  
                       	    }else if(qjsize.equals("2")){
                       	    	if("".equals("qj1")){
                           	    	sql1=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}else{
                             		sql1=" update TB_Estate_DTFSection set se_qz='"+qj1.split("#")[0].split("-")[0]+"',se_hz='"+qj1.split("#")[0].split("-")[1]+"',Seprice='"+qj1.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}
                       	    	if("".equals("qj2")){
                           	    	sql2=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}else{
                             		sql2=" update TB_Estate_DTFSection set se_qz='"+qj2.split("#")[0].split("-")[0]+"',se_hz='"+qj2.split("#")[0].split("-")[1]+"',Seprice='"+qj2.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}
                       	       if(!"".equals(qj3)){
            				      sql3="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj3.split("#")[0].split("-")[0]+"','"+qj3.split("#")[0].split("-")[1]+"','"+qj3.split("#")[1]+"',getdate(),'1')"; 
            				   }
            				   if(!"".equals(qj4) ){
          				        sql4="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj4.split("#")[0].split("-")[0]+"','"+qj4.split("#")[0].split("-")[1]+"','"+qj4.split("#")[1]+"',getdate(),'1')"; 
          				       }
            				   if(!"".equals(qj5)){
          				        sql5="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj5.split("#")[0].split("-")[0]+"','"+qj5.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				        }
            			       if(!"".equals(qj6)){
          				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj6.split("#")[0].split("-")[0]+"','"+qj6.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				       } 
                       	    	
                       	    }else if(qjsize.equals("3")){
                       	    	if("".equals("qj1")){
                           	    	sql1=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}else{
                             		sql1=" update TB_Estate_DTFSection set se_qz='"+qj1.split("#")[0].split("-")[0]+"',se_hz='"+qj1.split("#")[0].split("-")[1]+"',Seprice='"+qj1.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}
                       	    	if("".equals("qj2")){
                           	    	sql2=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}else{
                             		sql2=" update TB_Estate_DTFSection set se_qz='"+qj2.split("#")[0].split("-")[0]+"',se_hz='"+qj2.split("#")[0].split("-")[1]+"',Seprice='"+qj2.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}
                       	    	if("".equals("qj3")){
                           	    	sql3=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}else{
                             		sql3=" update TB_Estate_DTFSection set se_qz='"+qj3.split("#")[0].split("-")[0]+"',se_hz='"+qj3.split("#")[0].split("-")[1]+"',Seprice='"+qj3.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}
            				   if(!"".equals(qj4) ){
          				        sql4="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj4.split("#")[0].split("-")[0]+"','"+qj4.split("#")[0].split("-")[1]+"','"+qj4.split("#")[1]+"',getdate(),'1')"; 
          				       }
            				   if(!"".equals(qj5)){
          				        sql5="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj5.split("#")[0].split("-")[0]+"','"+qj5.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				        }
            			       if(!"".equals(qj6)){
          				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj6.split("#")[0].split("-")[0]+"','"+qj6.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				       } 
                       	    }else if(qjsize.equals("4")){
                       	    	if("".equals("qj1")){
                           	    	sql1=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}else{
                             		sql1=" update TB_Estate_DTFSection set se_qz='"+qj1.split("#")[0].split("-")[0]+"',se_hz='"+qj1.split("#")[0].split("-")[1]+"',Seprice='"+qj1.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}
                       	    	if("".equals("qj2")){
                           	    	sql2=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}else{
                             		sql2=" update TB_Estate_DTFSection set se_qz='"+qj2.split("#")[0].split("-")[0]+"',se_hz='"+qj2.split("#")[0].split("-")[1]+"',Seprice='"+qj2.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}
                       	    	if("".equals("qj3")){
                           	    	sql3=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}else{
                             		sql3=" update TB_Estate_DTFSection set se_qz='"+qj3.split("#")[0].split("-")[0]+"',se_hz='"+qj3.split("#")[0].split("-")[1]+"',Seprice='"+qj3.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}
                       	    	if("".equals("qj4")){
                           	    	sql4=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id4+"'"; 
                             	}else{
                             		sql4=" update TB_Estate_DTFSection set se_qz='"+qj4.split("#")[0].split("-")[0]+"',se_hz='"+qj4.split("#")[0].split("-")[1]+"',Seprice='"+qj4.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id4+"'"; 
                             	}
            				   if(!"".equals(qj5)){
          				        sql5="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj5.split("#")[0].split("-")[0]+"','"+qj5.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				        }
            			       if(!"".equals(qj6)){
          				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj6.split("#")[0].split("-")[0]+"','"+qj6.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				       } 
                       	    }else if(qjsize.equals("5")){
                       	    	if("".equals("qj1")){
                           	    	sql1=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}else{
                             		sql1=" update TB_Estate_DTFSection set se_qz='"+qj1.split("#")[0].split("-")[0]+"',se_hz='"+qj1.split("#")[0].split("-")[1]+"',Seprice='"+qj1.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}
                       	    	if("".equals("qj2")){
                           	    	sql2=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}else{
                             		sql2=" update TB_Estate_DTFSection set se_qz='"+qj2.split("#")[0].split("-")[0]+"',se_hz='"+qj2.split("#")[0].split("-")[1]+"',Seprice='"+qj2.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}
                       	    	if("".equals("qj3")){
                           	    	sql3=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}else{
                             		sql3=" update TB_Estate_DTFSection set se_qz='"+qj3.split("#")[0].split("-")[0]+"',se_hz='"+qj3.split("#")[0].split("-")[1]+"',Seprice='"+qj3.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}
                       	    	if("".equals("qj4")){
                           	    	sql4=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id4+"'"; 
                             	}else{
                             		sql4=" update TB_Estate_DTFSection set se_qz='"+qj4.split("#")[0].split("-")[0]+"',se_hz='"+qj4.split("#")[0].split("-")[1]+"',Seprice='"+qj4.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id4+"'"; 
                             	}
                       	    	if("".equals("qj5")){
                           	    	sql5=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id5+"'"; 
                             	}else{
                             		sql5=" update TB_Estate_DTFSection set se_qz='"+qj5.split("#")[0].split("-")[0]+"',se_hz='"+qj5.split("#")[0].split("-")[1]+"',Seprice='"+qj5.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id5+"'"; 
                             	}
            			       if(!"".equals(qj6)){
          				        sql6="insert into TB_Estate_DTFSection (ts_id,Es_id,Ei_id,se_qz,se_hz,Seprice,create_time,status) values('"+ts_id+"','"+es_id+"','"+Ei_id+"','"+qj6.split("#")[0].split("-")[0]+"','"+qj6.split("#")[0].split("-")[1]+"','"+qj6.split("#")[1]+"',getdate(),'1')"; 
          				       }
                       	    }else if(qjsize.equals("6")){
                       	    	if("".equals("qj1")){
                           	    	sql1=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}else{
                             		sql1=" update TB_Estate_DTFSection set se_qz='"+qj1.split("#")[0].split("-")[0]+"',se_hz='"+qj1.split("#")[0].split("-")[1]+"',Seprice='"+qj1.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id1+"'"; 
                             	}
                       	    	if("".equals("qj2")){
                           	    	sql2=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}else{
                             		sql2=" update TB_Estate_DTFSection set se_qz='"+qj2.split("#")[0].split("-")[0]+"',se_hz='"+qj2.split("#")[0].split("-")[1]+"',Seprice='"+qj2.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id2+"'"; 
                             	}
                       	    	if("".equals("qj3")){
                           	    	sql3=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}else{
                             		sql3=" update TB_Estate_DTFSection set se_qz='"+qj3.split("#")[0].split("-")[0]+"',se_hz='"+qj3.split("#")[0].split("-")[1]+"',Seprice='"+qj3.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id3+"'"; 
                             	}
                       	    	if("".equals("qj4")){
                           	    	sql4=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id4+"'"; 
                             	}else{
                             		sql4=" update TB_Estate_DTFSection set se_qz='"+qj4.split("#")[0].split("-")[0]+"',se_hz='"+qj4.split("#")[0].split("-")[1]+"',Seprice='"+qj4.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id4+"'"; 
                             	}
                       	    	if("".equals("qj5")){
                           	    	sql5=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id5+"'"; 
                             	}else{
                             		sql5=" update TB_Estate_DTFSection set se_qz='"+qj5.split("#")[0].split("-")[0]+"',se_hz='"+qj5.split("#")[0].split("-")[1]+"',Seprice='"+qj5.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id5+"'"; 
                             	}
                       	    	if("".equals("qj6")){
                           	    	sql6=" update TB_Estate_DTFSection set status=0 where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id6+"'"; 
                             	}else{
                             		sql6=" update TB_Estate_DTFSection set se_qz='"+qj6.split("#")[0].split("-")[0]+"',se_hz='"+qj6.split("#")[0].split("-")[1]+"',Seprice='"+qj6.split("#")[1]+"'  where Es_id='"+es_id+"'and Ei_id='"+Ei_id+"' and Se_id='"+Se_id6+"'"; 
                             	}
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
		           				String l_content="修改电梯费区间成功，房屋类型"+EhType+qj1+qj2;
	              				String tu_id=new ChangeDao().getu_id(adminuser_id, session.getAttribute("TABLENAME").toString());
	              				ChangeDao.add_Log(l_content, adminuser_id,"2",tu_id);
	              				out.print("ssok-修改电梯费区间成功！"); 
	           				}else{
	           				    out.print("errors-修改电梯费区间失败！");
	           				}
    					}
    					
    				}else{
    					out.print("error-修改收费项目失败！");
    				}
                }else{
                	out.print("error-修改收费项目失败！此项目下已设置该房屋类型信息！");
                }
				
			}
				 
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
