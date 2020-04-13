package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_Order;
import ccbjf.system.db.PublicDBhandles;

public class MyTB_Estate_Order_bb_DAO {
	
	/**
	 * 按条件模糊查询订单信息（分页）
	 * @param pagesize
	 * @param pagenum
	 * @param Condition
	 * @param Con
	 * @return
	 */
		public  List<TB_Estate_Order> select_orders_baobiaoxinxi(int pagesize,int pagenum,String Condition){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
			TB_Estate_Order order =null;
			int num=(pagenum-1)*pagesize;
			String sql="select top "+pagesize+" a.payitem,c.Es_id,c.EsName,b.itemName,COUNT(*) as num,sum(total) as total_yj_all,sum(total_sj) as total_sj_all from " +
					"TB_Estate_Order a inner join TB_Estate_item b on a.payItem=b.Ei_id inner join TB_Estate_Village c on a.Es_id=c.Es_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
					" inner join TB_Estate_Build e on a.Bu_id=e.Bu_id where b.itemName not in(select top "+num+" COUNT(*) from TB_Estate_Order a inner join TB_Estate_item b on" +
					" a.payItem=b.Ei_id inner join TB_Estate_Village c on a.Es_id=c.Es_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_Build e on a.Bu_id=e.Bu_id where "+Condition+") and "+Condition+" " +
					" group by a.payitem, c.Es_id, c.EsName,c.EsName,b.Ei_id,b.itemName ";
			try {		
				pu.init(sql.toString());
				rs=pu.Query();
				while(rs.next())
				{
					order =new TB_Estate_Order();
					order.setEs_id(rs.getInt("Es_id"));
					order.setEsName(rs.getString("EsName"));
					order.setPayItem(rs.getString("payItem"));
					order.setItemName(rs.getString("itemName"));
					order.setNum(rs.getString("num"));
					order.setTotal_yj_all(rs.getString("total_yj_all"));
					order.setTotal_sj_all(rs.getString("total_sj_all"));
					
					list.add(order);
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
		
		/**
		 * 按条件模糊查询订单信息（分页）
		 * @param pagesize
		 * @param pagenum
		 * @param Condition
		 * @param Con
		 * @return
		 */
			public  List<TB_Estate_Order> select_orders_baobiaoxinxi_dc(String Condition){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				TB_Estate_Order order =null;
				String sql="select  a.payitem,c.Es_id,c.EsName,b.itemName,COUNT(*) as num,sum(total) as total_yj_all,sum(total_sj) as total_sj_all from " +
						"TB_Estate_Order a inner join TB_Estate_item b on a.payItem=b.Ei_id inner join TB_Estate_Village c on a.Es_id=c.Es_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_Build e on a.Bu_id=e.Bu_id " +
						"  where  "+Condition+" group by a.payitem, c.Es_id, c.EsName,c.EsName,b.Ei_id,b.itemName ";
				try {		
					pu.init(sql.toString());
					rs=pu.Query();
					while(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEs_id(rs.getInt("Es_id"));
						order.setEsName(rs.getString("EsName"));
						order.setPayItem(rs.getString("payItem"));
						order.setItemName(rs.getString("itemName"));
						order.setNum(rs.getString("num"));
						order.setTotal_yj_all(rs.getString("total_yj_all"));
						order.setTotal_sj_all(rs.getString("total_sj_all"));
						
						list.add(order);
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
