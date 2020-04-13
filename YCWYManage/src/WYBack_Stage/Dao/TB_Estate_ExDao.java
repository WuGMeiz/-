package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ccbjf.system.db.PublicDBhandles;
import WYBack_Stage.Bean.TB_Estate_Examine;

public class TB_Estate_ExDao {
	/**
	 * 查询投票满意度活动
	 * @param pagesize
	 * @param pagenum
	 * @param condition
	 * @return
	 */
	public List<TB_Estate_Examine> getExamine(int pagesize, int pagenum, String condition){
		List<TB_Estate_Examine> list=new ArrayList<TB_Estate_Examine>();
		TB_Estate_Examine examine=null;
		PublicDBhandles pu = new PublicDBhandles();
	    ResultSet rs = null;
	    int num = (pagenum - 1) * pagesize;
	    try {
	    String sql = "select top " + pagesize + " r.* ,v.EsName from TB_Estate_Examine r inner join TB_Estate_Village v on r.Es_id=v.Es_id  where ex_id not in (select top " + num
	              + " ex_id from TB_Estate_Examine r inner join TB_Estate_Village v on r.Es_id=v.Es_id  where " + condition + " order by r.ex_id desc) and " + condition + " order by r.ex_id desc";
	    pu.init(sql);
	    rs=pu.Query();
		while(rs.next()){
			examine=new TB_Estate_Examine();
			examine.setEx_id(rs.getInt("ex_id"));
			examine.setEs_id(rs.getInt("es_id"));
			examine.setEsName(rs.getString("esName"));
			examine.setCounts(rs.getInt("counts"));
			examine.setCreate_time(rs.getDate("create_time")+" "+rs.getTime("create_time"));
			examine.setDescription(rs.getString("description")==null ?"":rs.getString("description"));
			examine.setEnd_time(rs.getDate("end_time")+" "+rs.getTime("end_time"));
			examine.setStart_time(rs.getDate("start_time")+" "+rs.getTime("start_time"));
			examine.setRemark(rs.getString("remark")==null ?"":rs.getString("remark"));
			examine.setStatus(rs.getInt("status"));
			examine.setTitle(rs.getString("title")==null?"":rs.getString("title"));
			examine.setType(rs.getInt("type"));
			list.add(examine);
		 }
		
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            pu.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return list;
		
	}
	/**
	 * 查询问卷信息
	 * @param ts_id
	 * @param ex_id
	 * @return
	 */
	public TB_Estate_Examine getExamInfo(String ts_id,String ex_id){
		TB_Estate_Examine examine=null;
		PublicDBhandles pu = new PublicDBhandles();
	    ResultSet rs = null;
	    try {
		    String sql = "select r.* ,v.EsName from TB_Estate_Examine r inner join TB_Estate_Village v on r.Es_id=v.Es_id  where  r.ts_id='"+ts_id+"' and  ex_id='"+ex_id+"'";
		    pu.init(sql);
		    rs=pu.Query();
			while(rs.next()){
				examine=new TB_Estate_Examine();
				examine.setEx_id(rs.getInt("ex_id"));
				examine.setEs_id(rs.getInt("es_id"));
				examine.setEsName(rs.getString("esName"));
				examine.setCounts(rs.getInt("counts"));
				examine.setCreate_time(rs.getDate("create_time")+" "+rs.getTime("create_time"));
				examine.setDescription(rs.getString("description")==null ?"":rs.getString("description"));
				examine.setEnd_time(rs.getDate("end_time")+" "+rs.getTime("end_time"));
				examine.setStart_time(rs.getDate("start_time")+" "+rs.getTime("start_time"));
				examine.setRemark(rs.getString("remark")==null ?"":rs.getString("remark"));
				examine.setStatus(rs.getInt("status"));
				examine.setTitle(rs.getString("title")==null?"":rs.getString("title"));
				examine.setType(rs.getInt("type"));
			 }
			
		   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		            pu.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		return examine;
	}
	/**
	 * 添加问卷
	 * @return
	 */
	public boolean addExamine(String ts_id, String Es_id,String title,String description,String Type,String status,String counts,String start_time,String end_time,String remark){
		boolean b=false;
		PublicDBhandles pu = new PublicDBhandles();
		try {
			String sql="insert into TB_Estate_Examine (ts_id,Es_id,title,start_time,end_time,description,remark,type,counts,status,create_time) values" +
			  "('"+ts_id+"','"+Es_id+"','"+title+"','"+start_time+"','"+end_time+"','"+description+"','"+remark+"','"+Type+"','"+counts+"','"+status+"', getdate())";
			pu.init(sql);
			b=pu.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 修改问卷
	 * @return
	 */
	public boolean updateExamine(String ts_id, String Es_id,String title,String description,String Type,String status,String counts,String start_time,String end_time,String remark,String ex_id){
		boolean b=false;
		PublicDBhandles pu = new PublicDBhandles();
		try {
			String sql="update TB_Estate_Examine set title='"+title+"',start_time='"+start_time+"', end_time='"+end_time+"', description='"+description+"', remark='"+remark+"', type='"+Type+"',counts='"+counts+"',status='"+status+"'" +
			  " where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and ex_id='"+ex_id+"'";
			pu.init(sql);
			b=pu.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 删除问卷
	 * @param ts_id
	 * @param ex_id
	 * @return
	 */
	public boolean delExamine(String ts_id,String ex_id){
		boolean b=false;
		PublicDBhandles pu = new PublicDBhandles();
		try {
			String sql="update TB_Estate_Examine set status=0 where ts_id='"+ts_id+"' and  ex_id='"+ex_id+"'";
			pu.init(sql);
			b=pu.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 检查问卷名称
	 * @param Es_id
	 * @param title
	 * @return
	 */
	public boolean checkExamine(String ts_id, String Es_id,String title){
		boolean b=false;
		ResultSet rs = null;
	    PublicDBhandles pu = new PublicDBhandles();
	    String sql="select title from TB_Estate_Examine where Es_id='"+Es_id+"' and ts_id='"+ts_id+"' and " +
	      		" title='"+title+"' and status<>0";
	      try {
	          pu.init(sql);
	          rs = pu.Query();
	          if (rs.next()) {
	              b = true;
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      } finally {
	          try {
	              if (rs != null) {
	                  rs.close();
	              }
	              pu.close();
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
	      }
		return b;
	}
}
