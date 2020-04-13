package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ccbjf.system.db.PublicDBhandles;

import WYBack_Stage.Bean.TBAdmin_Log;
import WYCommunity.T_time;



public class MyTBAdmin_Log {
	
	/**
	 * 添加管理员操作日志(用户添加，type=1;平台管理员添加，type=2)
	 * @param l_content
	 * @param user_id
	 */
	public void add_Log(String l_content,String user_id,String tu_id) {
		PublicDBhandles pu=null;
		String sql ="insert into TBAdmin_Log(l_content,user_id,tu_id,l_time,status,type) values ('"+l_content+"','"+user_id+"','"+tu_id+"','"+new T_time().getTime()+"','1','2')";
		try {
			pu = new PublicDBhandles();
			pu.init(sql);
			pu.updates();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 用于查询显示管理员日志分页的方法
	 * @param pagesize
	 * @param pagenum
	 * @param condition
	 * @return
	 */
	public List<TBAdmin_Log> getadmin_log(int pagesize,int pagenum,String condition) {
		ResultSet rs=null;
		PublicDBhandles pu=null;
		int num=(pagenum-1)*pagesize;
		List<TBAdmin_Log> list=new ArrayList<TBAdmin_Log>();
		String sql="select top "+pagesize+" a.l_id,a.l_content,a.l_time,b.username czyxm,b.userid czybh FROM TBAdmin_Log a left join TB_SEV_USER b on a.tu_id=b.tu_id where l_id not in " +
			"(select top "+num+" l_id from TBAdmin_Log a left join TB_SEV_USER b on a.tu_id=b.tu_id where "+condition+" order by l_time desc) and "+condition+" order by l_time desc";
		try {
			pu = new PublicDBhandles();
			pu.init(sql.toString());
			rs=pu.Query();
			TBAdmin_Log log=null;
			while(rs.next()) {
				log=new TBAdmin_Log();
				log.setL_id(rs.getString("l_id"));
				log.setL_content(rs.getString("l_content")==null?"":rs.getString("l_content"));
				log.setL_time(rs.getString("l_time")==null?"":rs.getDate("l_time")+" "+rs.getTime("l_time"));
				log.setCzyxm(rs.getString("czyxm")==null?"":rs.getString("czyxm"));
				log.setCzybh(rs.getString("czybh")==null?"":rs.getString("czybh"));
				list.add(log);
			}
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
	
	/**
	 * 添加管理员操作日志(传参数type)
	 */
	public void add_Log2(String l_content,String user_id,String type,String ts_id) {
		PublicDBhandles pu=null;
		String sql ="insert into TBAdmin_Log(l_content,tu_id,l_time,status,type,ts_id) values ('"+l_content+"','"+user_id+"','"+new T_time().getTime()+"','1','"+type+"','"+ts_id+"')";
		try {
			pu = new PublicDBhandles();
			pu.init(sql);
			pu.updates();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 用于查询显示管理员日志分页的方法
	 * @param pagesize
	 * @param pagenum
	 * @param condition
	 * @return
	 */
	public List<TBAdmin_Log> getadmin_log2(int pagesize,int pagenum,String condition) {
		ResultSet rs=null;
		PublicDBhandles pu=null;
		int num=(pagenum-1)*pagesize;
		List<TBAdmin_Log> list=new ArrayList<TBAdmin_Log>();
		String sql="select top "+pagesize+" a.l_id,a.l_content,a.l_time,b.username czyxm,b.userid czybh FROM TBAdmin_Log a left join TB_CDE_USER b on a.tu_id=b.tu_id where l_id not in " +
			"(select top "+num+" l_id from TBAdmin_Log a left join TB_CDE_USER b on a.tu_id=b.tu_id where "+condition+" order by l_time desc) and "+condition+" order by l_time desc";
		try {
			pu = new PublicDBhandles();
			pu.init(sql.toString());
			rs=pu.Query();
			TBAdmin_Log log=null;
			while(rs.next()) {
				log=new TBAdmin_Log();
				log.setL_id(rs.getString("l_id"));
				log.setL_content(rs.getString("l_content")==null?"":rs.getString("l_content"));
				log.setL_time(rs.getString("l_time")==null?"":rs.getDate("l_time")+" "+rs.getTime("l_time"));
				log.setCzyxm(rs.getString("czyxm")==null?"":rs.getString("czyxm"));
				log.setCzybh(rs.getString("czybh")==null?"":rs.getString("czybh"));
				list.add(log);
			}
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
}