package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ccbjf.system.db.PublicDBhandles;

import WYBack_Stage.Bean.TB_Estate_Examine;
import WYBack_Stage.Bean.TB_Estate_Exoption;
import WYBack_Stage.Bean.TB_Estate_Extopic;
import WYBack_Stage.Bean.TB_Estate_Village;

public class GetWj_Dao {

	public List<TB_Estate_Examine> getWjByEs_id(String es_id,int type) {

		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		List<TB_Estate_Examine> list=new ArrayList<TB_Estate_Examine>();
		String sql="select ex_id,title from TB_Estate_Examine where Es_id='"+es_id+"'and type="+type+" and status in (1,2) order by ex_id";
		//System.out.println("查询问卷"+sql);
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Examine tev=null;
			while(rs.next()) 
			{
				tev=new TB_Estate_Examine();
				tev.setEx_id(rs.getInt("ex_id"));
				tev.setTitle(rs.getString("title"));
				list.add(tev);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		return list;
	}

	
	
	
	
	
	//#################################################################3
	 //select * from TB_Estate_Exoption b left join(select id from TB_Estate_ExRecord a outer Apply fn_Split(a.con_selects,'|')) c on  b.op_id=c.id
	 
			 //问卷题目表
			  public  Map<TB_Estate_Extopic, List> select_XuanXiang1(int ex_id,int Eh_id,String selectTime){	  
				  PublicDBhandles pu = new PublicDBhandles();
			      ResultSet rs = null;
			      List<TB_Estate_Extopic> list=new ArrayList<TB_Estate_Extopic>();
			      
			      
			      //List<TB_Estate_Exoption> list1=new ArrayList<TB_Estate_Exoption>();
			      Map<TB_Estate_Extopic, List> a = new HashMap<TB_Estate_Extopic, List>();		      
			      
			      TB_Estate_Extopic peop=null;
			      String sql = "select * from TB_Estate_Extopic where status=1 and ex_id="+ex_id;
			    //System.out.println("问卷题目表"+sql);
			      try {
			    	  pu.init(sql.toString());
			          rs=pu.Query();
					while(rs.next()){
						peop=new TB_Estate_Extopic();
						peop.setTo_id(rs.getInt("to_id")==0?0:rs.getInt("to_id"));
						peop.setEx_id(rs.getInt("ex_id"));
						peop.setIf_tw(rs.getInt("if_tw"));
						peop.setTopic(rs.getString("topic"));
						peop.setImages(rs.getString("images")==null?"":rs.getString("images"));
						peop.setSort(rs.getInt("sort")==0?0:rs.getInt("sort"));
						peop.setType(rs.getInt("type")==0?0:rs.getInt("type"));
						peop.setCreate_time(rs.getString("create_time"));
						
						list.add(peop);
					  }
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
			        try {
			            if (rs != null) {
			                rs.close();
			            }
			            pu.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			      
			      
			      for(int i=0;i<list.size();i++){
			    	  peop=list.get(i);
			    	 
			    	  GetWj_Dao dao=new GetWj_Dao();
			    	  List<TB_Estate_Exoption> list1=dao.getTB_Estate_Exoption1(ex_id,Eh_id,peop.getTo_id(),selectTime);
			         a.put(peop, list1);
			      }
			 //System.out.println(a); 
			      return a;
			  }
	 
	 
			//得到每道题的选项
			  public List<TB_Estate_Exoption> getTB_Estate_Exoption1(int ex_id,int Eh_id,int to_id, String selectTime){	  
				  PublicDBhandles pu = new PublicDBhandles();
			      ResultSet rs = null;
			      List<TB_Estate_Exoption> list=new ArrayList<TB_Estate_Exoption>();
			      TB_Estate_Exoption peop=null;
			      selectTime=selectTime.replace("_", " ");
			      String sql = "select * from TB_Estate_Exoption b left join(select id from TB_Estate_ExRecord a outer Apply fn_Split(a.con_selects,'|') where status=1 and ex_id="+ex_id+" and Eh_id="+Eh_id+" and to_id="+to_id+" and selectTime='"+selectTime+"') c on  b.op_id=c.id where status=1 and b.to_id="+to_id+" order by b.op_id desc";
			      //System.out.println("每道题的选项"+sql);
			      try {
			    	  pu.init(sql.toString());
			          rs=pu.Query();
					while(rs.next()){
						peop=new TB_Estate_Exoption();
						peop.setOp_id(rs.getInt("op_id"));
						peop.setIf_tw(rs.getInt("if_tw"));
						peop.setOptionName(rs.getString("optionName"));
						peop.setImages(rs.getString("images")==null?"":rs.getString("images"));
						peop.setSort(rs.getInt("sort")==0?0:rs.getInt("sort"));
						peop.setCreate_time(rs.getString("create_time"));
						peop.setId(rs.getString("id")==null?0:rs.getInt("id"));
						list.add(peop);
					  }
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
			        try {
			            if (rs != null) {
			                rs.close();
			            }
			            pu.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			      
			      return list;
			  }
	 
	 
	 
	 //#######################//#########################################3
	 
}
