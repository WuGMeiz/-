package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ccbjf.system.db.PublicDBhandles;

import WYBack_Stage.Bean.TB_Estate_Examine;

public class TB_Estate_ExamineDao {
	
	/**
	 * 
	 * 
	 * @Title: select_Examine
	 * @Description: 查询当前时间段调查问卷
	 * @param date（进入后台时间）
	 * @returnList<TB_Estate_Examine>s
	 */
	public List<TB_Estate_Examine> select_Examine(String es_id){
		ResultSet rs = null;
		TB_Estate_Examine examine = null;
		PublicDBhandles pu = new PublicDBhandles();
		List<TB_Estate_Examine> list = new ArrayList<TB_Estate_Examine>();
		String sql = "select * from TB_Estate_Examine " +
				"WHERE Es_id = ? " +
				"and (status = '1' or status = '2') ";
				//"and type = '1'";
		try {
			pu.init(sql.toString());
			pu.setInt(1, Integer.parseInt(es_id));
			rs=pu.Query();
			while(rs.next())
			{
				examine =new TB_Estate_Examine();
				examine.setEx_id(rs.getInt("ex_id"));
				examine.setTs_id(rs.getInt("ts_id"));
				examine.setEs_id(rs.getInt("es_id"));
				examine.setTitle(rs.getString("title")==null?"":rs.getString("title"));
				examine.setStart_time(rs.getString("start_time")==null?"":rs.getString("start_time"));
				examine.setEnd_time(rs.getString("end_time")==null?"":rs.getString("end_time"));
				examine.setDescription(rs.getString("description")==null?"":rs.getString("description"));
				examine.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
				examine.setType(rs.getInt("type"));
				examine.setCounts(rs.getInt("counts"));
				examine.setStatus(rs.getInt("status"));
				examine.setCreate_time(rs.getString("create_time")==null?"":rs.getString("create_time"));
				
				list.add(examine);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
		finally
		{
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
