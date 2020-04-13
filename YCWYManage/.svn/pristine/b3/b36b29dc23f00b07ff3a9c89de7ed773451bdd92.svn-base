package WYCommunity;

import java.sql.ResultSet;

import ccbjf.system.db.PublicDBhandle;


public class CreateID {

	/**
	 * 返回数据库主键为INT型，不是自增长的表的最大行数加1，用于新添加数据。（较适用于oracle数据库无自增长列）
	 * @param table_name
	 * @return
	 */
	public int getID(String table_name, String colname) {
		int id = 1;
		PublicDBhandle pu=null;
		ResultSet rs=null;
		String sql="select max(cast("+colname+" as int))+1 from "+table_name+"";
		try {
			pu = new PublicDBhandle();
			rs = pu.eQuery(sql);
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				pu.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	/**
	 * 返回数据库主键为INT型，不是自增长的表的最大行数加1，用于新添加数据。（较适用于oracle数据库无自增长列）
	 * @param table_name
	 * @return
	 */
	public int getUser_ID(String org_id) {
		int id = 1;
		PublicDBhandle pu=null;
		ResultSet rs=null;
		String sql="select right(max(cast(USER_ID as int)),4)+1 from TBPay_User where org_id = '"+org_id+"'";
		try {
			pu = new PublicDBhandle();
			rs = pu.eQuery(sql);
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				pu.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}
}