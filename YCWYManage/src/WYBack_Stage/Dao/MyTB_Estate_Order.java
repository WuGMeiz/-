package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WYBack_Stage.Bean.TB_Estate_Build;
import WYBack_Stage.Bean.TB_Estate_Order;
import WYBack_Stage.Bean.TB_Estate_Unit;
import WYBack_Stage.Bean.TB_Estate_Village;
import WYBack_Stage.Bean.TB_SEV_CCBBank;
import WYBack_Stage.Bean.TB_SEV_USER;
import WYCommunity.S_string;
import WYCommunity.T_time;
import ccbjf.system.db.PublicDBhandles;

public class MyTB_Estate_Order {

	/**
	 * 根据ts_id查询小区信息
	 * @param ts_id 
	 * @author 金鑫
	 *
	 */
		public  List<TB_Estate_Village> select_xiaoqu(String ts_id){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			List<TB_Estate_Village> list = new ArrayList<TB_Estate_Village>();
			String sql="select Es_id, EsName from TB_Estate_Village where ts_id='"+ts_id+"' and status='1' ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_Village village=null;
				while(rs.next())
				{
					village =new TB_Estate_Village();
					village.setEs_id(rs.getInt("Es_id"));
					village.setEsName(rs.getString("EsName"));
					
					list.add(village);
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
		 * 根据Es_id查询楼宇信息
		 * @param ts_id
		 * @param Es_id
		 * @return
		 */
		public List<TB_Estate_Build> select_louyu(String ts_id, String Es_id,String tu_id,String LEVELS){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			List<TB_Estate_Build> list = new ArrayList<TB_Estate_Build>();
			String sql="select Bu_id, BuName from TB_Estate_Build where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and status='1'  ";
			if("1".equals(LEVELS)){
				sql+=" and (BuHead='' or BuHead is null or BuHead='"+tu_id+"')";
			}
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_Build build =null;
				while(rs.next())
				{
					build =new TB_Estate_Build();
					build.setBu_id(rs.getInt("Bu_id"));
					build.setBuName(rs.getString("BuName"));
					
					list.add(build);
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
			public  List<TB_Estate_Order> select_orders(int pagesize,int pagenum,String Condition){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				TB_Estate_Order order =null;
				int num=(pagenum-1)*pagesize;
				String sql="select top "+pagesize+" b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName,g.Ct_ItemType,g.Ct_znjDay," +
				"g.Ct_znjRatio,a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
				"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType   where  a.Eo_id not in(" +
				"select top "+num+" a.Eo_id from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id" +
				" inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on " +
				"a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType  where "+Condition+" order by d.EhNumber,a.eo_id ) and "+Condition+" order by d.EhNumber,a.eo_id ";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					while(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
						order.setA_id(rs.getInt("a_id"));
						order.setEs_id(rs.getInt("Es_id"));
						order.setBu_id(rs.getInt("Bu_id"));
						order.setEh_id(rs.getInt("Eh_id"));
						order.setOrderType(rs.getInt("orderType"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
						order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
						order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
						order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
						order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
						order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
						order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
						order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
						order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
						order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
						order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
						order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
						order.setStatus(rs.getInt("status"));
						order.setLockSign(rs.getString("LockSign"));
						order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
						
						order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
						order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));//单元名称
						order.setOwnerName(rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber"));//房屋编号
						order.setItemName(rs.getString("itemName"));//缴费项
						order.setItemType(rs.getString("Ct_ItemType"));//收费方式
						order.setZnjDay(rs.getString("Ct_znjDay"));//滞纳金天数
						order.setZnjRatio(rs.getString("Ct_znjRatio"));//滞纳金比例
						
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
				public  List<TB_Estate_Order> select_orders1(int pagesize,int pagenum,String Condition){
					ResultSet rs = null;
					PublicDBhandles pu=new PublicDBhandles();
					List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
					TB_Estate_Order order =null;
					int num=(pagenum-1)*pagesize;
					String sql="select top "+pagesize+" b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName,g.Ct_ItemType,g.Ct_znjDay," +
					"g.Ct_znjRatio,a.*,p.status printStatus,p.print_status printStatus1 ,p.paper_num from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
					"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType inner JOIN TB_Estate_paper p  on a.bankid = p.bankid   where  a.Eo_id not in(" +
					"select top "+num+" a.Eo_id from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id" +
					" inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on " +
					"a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType inner JOIN TB_Estate_paper p  on a.bankid = p.bankid   where "+Condition+"  order by p.paper_num desc) and "+Condition+" order by p.paper_num desc ";//d.EhNumber,a.eo_id
					try {
						pu.init(sql.toString());
						rs=pu.Query();
						while(rs.next())
						{
							order =new TB_Estate_Order();
							order.setPrintStatus(rs.getInt("printStatus"));
							order.setPrintStatus1(rs.getInt("printStatus1"));
							order.setPjNumber(rs.getInt("paper_num"));
							order.setEo_id(rs.getInt("Eo_id"));
							order.setTs_id(rs.getInt("ts_id"));
							order.setA_id(rs.getInt("a_id"));
							order.setEs_id(rs.getInt("Es_id"));
							order.setBu_id(rs.getInt("Bu_id"));
							order.setEh_id(rs.getInt("Eh_id"));
							order.setOrderType(rs.getInt("orderType"));
							order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
							order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
							order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
							order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
							order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
							order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
							order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
							order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
							order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
							order.setPayStatus(rs.getInt("payStatus"));
							order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
							order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
							order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
							order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
							order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
							order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
							order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
							order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
							order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
							order.setStatus(rs.getInt("status"));
							order.setLockSign(rs.getString("LockSign"));
							order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
							
							order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
							order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
							order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));//单元名称
							order.setOwnerName(rs.getString("OwnerName"));//户主姓名
							order.setEhNumber(rs.getString("EhNumber"));//房屋编号
							order.setItemName(rs.getString("itemName"));//缴费项
							order.setItemType(rs.getString("Ct_ItemType"));//收费方式
							order.setZnjDay(rs.getString("Ct_znjDay"));//滞纳金天数
							order.setZnjRatio(rs.getString("Ct_znjRatio"));//滞纳金比例
							
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
				public  List<TB_Estate_Order> selectGE_orders(int pagesize,int pagenum,String Condition){
					ResultSet rs = null;
					PublicDBhandles pu=new PublicDBhandles();
					List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
					TB_Estate_Order order =null;
					int num=(pagenum-1)*pagesize;
					String sql="select top "+pagesize+" b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName, " +
						" a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
						"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where  a.Eo_id not in(" +
						"select top "+num+" a.Eo_id from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id" +
						" inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id  inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where "+Condition+"  order by d.EhNumber,a.eo_id  ) and "+Condition+"   order by d.EhNumber,a.eo_id ";
					try {
						pu.init(sql.toString());
						rs=pu.Query();
					while(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
						order.setA_id(rs.getInt("a_id"));
						order.setEs_id(rs.getInt("Es_id"));
						order.setBu_id(rs.getInt("Bu_id"));
						order.setEh_id(rs.getInt("Eh_id"));
						order.setOrderType(rs.getInt("orderType"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
						order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
						order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
						order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
						order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
						order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
						order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
						order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
						order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
						order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
						order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
						order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
						order.setStatus(rs.getInt("status"));
						order.setLockSign(rs.getString("LockSign"));
						order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
						
						order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
						order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
						order.setOwnerName(rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber"));//房屋编号
						order.setItemName(rs.getString("itemName"));//房屋编号
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
			public  List<TB_Estate_Order> selectGE_orders_TJ(int pagesize,int pagenum,String Condition){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				TB_Estate_Order order =null;
				int num=(pagenum-1)*pagesize;
				String sql="select top "+pagesize+" MAX(b.EsName) as EsName, MAX(EhNumber) as EhNumber, MAX(OwnerName) as OwnerName, MAX(c.BuName) as BuName,MAX(f.UnName) as UnName," +
						"MAX(e.itemName) as itemName, bankid, MAX(pay_time) as pay_time , MAX(PayType) as PayType ," +
						"MAX(PayStatus) as PayStatus ,SUM(a.total) as total, SUM(a.total_sj) as total_sj ,MAX(a.Eh_id) as Eh_id" +
						" from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
					"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
					"inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id " +
					"where  a.bankid not in( select top "+num+" bankid from TB_Estate_Order a inner join TB_Estate_Village b " +
					"on a.Es_id=b.Es_id  inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d " +
					"on a.Eh_id=d.Eh_id  inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f " +
					"on d.Un_id=f.Un_id where "+Condition+"  group by bankid   ) and "+Condition+"  group by bankid   ";
			//	System.out.println(sql);
				try {
					pu.init(sql.toString());
					rs=pu.Query();
				while(rs.next())
				{
					order =new TB_Estate_Order();
					order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
					order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
					order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
					order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
					order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
					order.setPayStatus(rs.getInt("payStatus"));
					
					order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
					order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
					order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
					order.setOwnerName(rs.getString("OwnerName"));//户主姓名
					order.setEhNumber(rs.getString("EhNumber"));//房屋编号
					order.setItemName(rs.getString("itemName"));//房屋编号
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
			
		public  List<TB_Estate_Order> select_orders_all(String Condition){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			TB_Estate_Order order = null;
			List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
			String sql="select b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName,g.Ct_ItemType," +
					"g.Ct_znjDay,g.Ct_znjRatio,a.* from TB_Estate_Order a inner join TB_Estate_Village b " +
					"on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join " +
					"TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on a.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType where "+Condition+" order by d.EhNumber ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				while(rs.next())
				{
					order =new TB_Estate_Order();
					order.setEo_id(rs.getInt("Eo_id"));
					order.setTs_id(rs.getInt("ts_id"));
					order.setA_id(rs.getInt("a_id"));
					order.setEs_id(rs.getInt("Es_id"));
					order.setBu_id(rs.getInt("Bu_id"));
					order.setEh_id(rs.getInt("Eh_id"));
					order.setOrderType(rs.getInt("orderType"));
					order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
					order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
					order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
					order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
					order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
					order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
					order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
					order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
					order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
					order.setPayStatus(rs.getInt("payStatus"));
					order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
					order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
					order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
					order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
					order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
					order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
					order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
					order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
					order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
					order.setStatus(rs.getInt("status"));
					order.setLockSign(rs.getString("LockSign"));
					order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
					
					order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
					order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
					order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));//单元名称
					order.setOwnerName(rs.getString("OwnerName"));//户主姓名
					order.setEhNumber(rs.getString("EhNumber"));//房屋编号
					order.setItemName(rs.getString("itemName"));//收费项
					order.setItemType(rs.getString("Ct_ItemType"));//收费方式
					order.setZnjDay(rs.getString("Ct_znjDay"));//滞纳金天数
					order.setZnjRatio(rs.getString("Ct_znjRatio"));//滞纳金比例
					
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
		public  List<TB_Estate_Order> selectGE_orders_all(String Condition){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			TB_Estate_Order order = null;
			List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				String sql="select  b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemname, " +
						" a.* from TB_Estate_Order a inner join TB_Estate_Village b " +
						"on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join " +
						"TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on a.Un_id=f.Un_id where "+Condition+"  order by d.EhNumber ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				while(rs.next())
				{
					order =new TB_Estate_Order();
					order.setEo_id(rs.getInt("Eo_id"));
					order.setTs_id(rs.getInt("ts_id"));
					order.setA_id(rs.getInt("a_id"));
					order.setEs_id(rs.getInt("Es_id"));
					order.setBu_id(rs.getInt("Bu_id"));
					order.setEh_id(rs.getInt("Eh_id"));
					order.setOrderType(rs.getInt("orderType"));
					order.setOrderType(rs.getInt("orderType"));
					order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
					order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
					order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
					order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
					order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
					order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
					order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
					order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
					order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
					order.setPayStatus(rs.getInt("payStatus"));
					order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
					order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
					order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
					order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
					order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
					order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
					order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
					order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
					order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
					order.setStatus(rs.getInt("status"));
					order.setLockSign(rs.getString("LockSign"));
					order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
					
					order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
					order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
					order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
					order.setOwnerName(rs.getString("OwnerName"));//户主姓名
					order.setEhNumber(rs.getString("EhNumber"));//房屋编号
					order.setItemName(rs.getString("itemName"));
					
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
		 * 订单主键查询订单信息（分页）
		 * @param pagesize
		 * @param pagenum
		 * @param Condition
		 * @param Con
		 * @return
		 */
			public  TB_Estate_Order select_orders_mx(String Eo_id){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				TB_Estate_Order order = null;
				String sql= "select b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName,g.Ct_ItemType," +
						"g.Ct_znjDay,g.Ct_znjRatio,a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
						"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
						"inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on a.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType " +
						"where Eo_id='"+Eo_id+"' and a.status='1'";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					if(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
						order.setA_id(rs.getInt("a_id"));
						order.setEs_id(rs.getInt("Es_id"));
						order.setBu_id(rs.getInt("Bu_id"));
						order.setEh_id(rs.getInt("Eh_id"));
						order.setOrderType(rs.getInt("orderType"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
						order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
						order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
						order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getDate("pay_time")+" "+rs.getTime("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
						order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
						order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
						order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
						order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
						order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
						order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
						order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
						order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
						order.setStatus(rs.getInt("status"));
						order.setLockSign(rs.getString("LockSign"));
						order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
						
						order.setEsName(rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName"));//楼宇名称
						order.setUnName(rs.getString("UnName")==null?"无单元":rs.getString("UnName"));//楼宇名称
						order.setOwnerName(rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber"));//房屋编号
						order.setItemName(rs.getString("itemName"));//收费方式 
						order.setItemType(rs.getString("Ct_ItemType"));//收费方式
						order.setZnjDay(rs.getString("Ct_znjDay"));//滞纳金天数
						order.setZnjRatio(rs.getString("Ct_znjRatio"));//滞纳金比例
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
				return order;
			}
			/**
			 * 订单主键查询订单信息（分页）
			 * @param pagesize
			 * @param pagenum
			 * @param Condition
			 * @param Con
			 * @return
			 */
			public  TB_Estate_Order selectGE_orders_mx(String Eo_id){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				TB_Estate_Order order = null;
				String sql= "select b.EsName,c.BuName,d.OwnerName,d.EhNumber," +
						" a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join" +
						" TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
						" where Eo_id='"+Eo_id+"' and a.status='1'";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					if(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
						order.setA_id(rs.getInt("a_id"));
						order.setEs_id(rs.getInt("Es_id"));
						order.setBu_id(rs.getInt("Bu_id"));
						order.setEh_id(rs.getInt("Eh_id"));
						order.setOrderType(rs.getInt("orderType"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
						order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
						order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
						order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
						order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
						order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
						order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
						order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
						order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
						order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
						order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
						order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
						order.setStatus(rs.getInt("status"));
						order.setLockSign(rs.getString("LockSign"));
						order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
						
						order.setEsName(rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName"));//楼宇名称
						order.setOwnerName(rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber"));//房屋编号
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
				return order;
			}
		/**
		 * 删除订单
		 * @param ts_id
		 * @param Eo_id
		 * @return
		 */
		public boolean delete_order(String ts_id,String Eo_id) {
			boolean flag=false;
			PublicDBhandles pu=null;
			String sql = "update TB_Estate_Order set status='0' WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
			try {
				pu = new PublicDBhandles();
				pu.init(sql);
				flag=pu.updates();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					pu.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
		/**
		 * 修改订单金额
		 * @param ts_id
		 * @param Eo_id
		 * @return
		 */
		public boolean update_order(String ts_id,String Eo_id,String total,String up_Reason,String b2) {
			boolean flag=false;
			PublicDBhandles pu=null;
			String sql = "update TB_Estate_Order set total='"+ Double.parseDouble(total)+"',up_Reason='"+up_Reason+"',b2='"+b2+"' WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
			try {
				pu = new PublicDBhandles();
				pu.init(sql);
				flag=pu.updates();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					pu.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
		/**
		 * 人工收费修改订单
		 * @param ts_id
		 * @param Eo_id
		 * @return
		 */
		public boolean update_order_rgsf(String ts_id,String Eo_id,String total,String total_znj,String payType,String total_sj) {
			boolean flag=false;
			PublicDBhandles pu=null;
			String bankid = "XJ"+new T_time().getymdhms();
			String sql = "update TB_Estate_Order set bankid='"+bankid+"', total='"+ Double.parseDouble(total)+"',total_znj='"+ Double.parseDouble(total_znj)+"'  , total_sj='"+Double.parseDouble(total_sj)+"' , payType='"+payType+"' , payStatus='1' , pay_time='"+new T_time().getTime()+"'  WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
			try {
				pu = new PublicDBhandles();
				pu.init(sql);
				flag=pu.updates();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					pu.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
		
		/**
		 * 人工收费修改订单并添加操作员id tu_id
		 * @param ts_id
		 * @param Eo_id
		 * @return
		 */
		public boolean update_order_rgsf(String ts_id,String Eo_id,String total,String total_znj,String payType,String total_sj,String tu_id) {
			boolean flag=false;
			PublicDBhandles pu=null;
			String bankid = "XJ"+new T_time().getymdhms();
			String sql = "update TB_Estate_Order set bankid='"+bankid+"', total='"+ Double.parseDouble(total)+"',total_znj='"+ Double.parseDouble(total_znj)+"'  , total_sj='"+Double.parseDouble(total_sj)+"' , payType='"+payType+"' , payStatus='1' , pay_time='"+new T_time().getTime()+"' , tu_id='"+tu_id+"'  WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
			try {
				pu = new PublicDBhandles();
				pu.init(sql);
				flag=pu.updates();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					pu.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
		
		
		
		/**
		 * 批量订单删除
		 * @return
		 */
		public boolean delete_order_pl(String ts_id,String orderids){
			boolean flag=false;
			PublicDBhandles pu=null;
			String orderid = orderids.replaceAll(";", "','");
			try {
				pu = new PublicDBhandles();
				String sql = "update TB_Estate_Order set status='0' where ts_id='"+ts_id+"' and  Eo_id in ('"+orderid+"')";
				pu.init(sql);
				flag=pu.updates();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pu.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
	/**
	 * 查询该楼宇下是否有该用户
	 * @param ts_id
	 * @param Es_id
	 * @param Bu_id
	 * @param EhNumber
	 * @return
	 */
	 public String select_user_sfcz(String ts_id, String Es_id, String Bu_id,String Un_id,String EhNumber,String LEVELS,String tu_id) {
	        PublicDBhandles pu = new PublicDBhandles();
	        ResultSet rs = null;
	        String str="";
	        String sql = "select Eh_id,EhNumber from TB_Estate_House a inner join TB_Estate_Build c on a.bu_id=c.bu_id where a.ts_id='" + ts_id + "' and a.Es_id='" + Es_id + "' and a.status='1' and c.status=1 and a.EhNumber='"+EhNumber+"' ";
	          if(!"".equals(Bu_id)){
	            sql += "and a.Bu_id= '"+Bu_id+"'";
	          }else{
	            if("1".equals(LEVELS)){
	              sql += " and (c.BuHead='' or c.BuHead is null or c.BuHead='"+tu_id+"')";
	            }
	          }
	          if(!"".equals(Un_id)){
	            sql += " and Un_id='"+Un_id+"'";
	          }
	        try {
	            pu.init(sql.toString());
	            rs = pu.Query();
	            if (rs.next()) {
	            	str = rs.getString("Eh_id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                pu.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return str;
	    }
	 
		/**
		 * 查询该用户是否有未缴订单
		 * @param ts_id
		 * @param Es_id
		 * @param Bu_id
		 * @param EhNumber
		 * @return
		 */
		 public String select_user_order(String ts_id, String Es_id, String Bu_id,String EhNumber) {
		        PublicDBhandles pu = new PublicDBhandles();
		        ResultSet rs = null;
		        String time=new T_time().getTimeymd()+" 00:00:00.000";
		        String str="";
		      //  String sql = "select a.Eo_id from TB_Estate_Order a inner join TB_Estate_House b on a.Eh_id=b.Eh_id where a.payStatus='0' and  a.ts_id='" + ts_id + "' and a.Es_id='" + Es_id + "' and a.status='1' and a.Bu_id= '"+Bu_id+"' and b.EhNumber='"+EhNumber+"'";
		        String sql="select a.Eo_id from TB_Estate_Order a inner join TB_Estate_House b on a.Eh_id=b.Eh_id where a.payStatus='0' and orderType in(1,3) and  a.ts_id='"+ts_id+"' and a.Es_id='"+Es_id+"' and a.status='1' and b.status='1' and a.Bu_id= '"+Bu_id+"' and b.EhNumber='"+EhNumber+"'" +
		        	" and Cost_startTime <= '"+time+"' and  Cost_endTime>='"+time+"'";
		        try {
		            pu.init(sql.toString());
		            rs = pu.Query();
		            if (rs.next()) {
		            	str = rs.getString("Eo_id");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                if (rs != null) {
		                    rs.close();
		                }
		                pu.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return str;
		    }
		 
		 /**
		  * 根据收费项查询限额
		  * @param Ei_id
		  * @return
		  */
		 public String select_item_xe(String Ei_id) {
		        PublicDBhandles pu = new PublicDBhandles();
		        ResultSet rs = null;
		        String str="";
		        String sql = "select * from TB_Estate_item where Ei_id = '"+Ei_id+"'";
		        try {
		            pu.init(sql.toString());
		            rs = pu.Query();
		            if (rs.next()) {
		            	str = rs.getString("Limited")+"##"+rs.getString("LimitNumber")+"##"+rs.getString("BuyLimited");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                if (rs != null) {
		                    rs.close();
		                }
		                pu.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return str;
		    }
		 /**
		  * 根据收费项查询限额
		  * @param Ei_id
		  * @return
		  */
		 public String select_item_xe2(String Ei_id) {
		        PublicDBhandles pu = new PublicDBhandles();
		        ResultSet rs = null;
		        String str="";
		        String sql = "select * from TB_Estate_item where Ei_id = '"+Ei_id+"'";
		        try {
		            pu.init(sql.toString());
		            rs = pu.Query();
		            if (rs.next()) {
		            	String s = rs.getString("LimitNumber");
		            	if(s.equals("")){
		            		s="aaa";
		            	}
		            	str = rs.getString("Limited")+"##"+s+"##"+rs.getString("BuyLimited");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                if (rs != null) {
		                    rs.close();
		                }
		                pu.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        return str;
		    }
		 /**
		     * 获取当前月支付了几条电费订单
		     * 
		     * @param es_id
		     * @return
		     */
		    public int select_paynum(String ts_id,String Es_id,String Bu_id,String Eh_id,String payitem) {
		        PublicDBhandles pu = new PublicDBhandles();
		        ResultSet rs = null;
		        int num=0;
		        String year = new T_time().getTimeyyyy() ;
		        String month = new T_time().getTimemm();
		        String startime =getFirstDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
		        String lasttime = getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
		        try {
		            String sql = "select COUNT(*) as num from TB_Estate_Order where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and Bu_id='"+Bu_id+"' and Eh_id='"+Eh_id+"' and pay_time>='"+startime+"'and pay_time<='"+lasttime+"' and payitem='"+payitem+"' and orderType=2 and payStatus=1";
		            pu.init(sql);
		           
		            rs = pu.Query();
		            if (rs.next()) {
		                num = rs.getInt("num");
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
		        return num;
		    }
		    /** 
		     * 获得该月第一天 
		     * @param year 
		     * @param month 
		     * @return 
		     */  
		     public static String getFirstDayOfMonth(int year,int month){  
		             Calendar cal = Calendar.getInstance();  
		             //设置年份  
		             cal.set(Calendar.YEAR,year);  
		             //设置月份  
		             cal.set(Calendar.MONTH, month-1);  
		             //获取某月最小天数  
		             int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);  
		             //设置日历中月份的最小天数  
		             cal.set(Calendar.DAY_OF_MONTH, firstDay);  
		             //格式化日期  
		             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		             String firstDayOfMonth = sdf.format(cal.getTime());  
		             return firstDayOfMonth;  
		         }  
		       
		     /** 
		      * 获得该月最后一天 
		      * @param year 
		      * @param month 
		      * @return 
		      */  
		      public static String getLastDayOfMonth(int year,int month){  
		              Calendar cal = Calendar.getInstance();  
		              //设置年份  
		              cal.set(Calendar.YEAR,year);  
		              //设置月份  
		              cal.set(Calendar.MONTH, month-1);  
		              //获取某月最大天数  
		              int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
		              //设置日历中月份的最大天数  
		              cal.set(Calendar.DAY_OF_MONTH, lastDay);  
		              //格式化日期  
		              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		              String lastDayOfMonth = sdf.format(cal.getTime());  
		              return lastDayOfMonth;  
		       }
		      
	      /**
			 * 根据Bu_id查询单元信息
			 * @param ts_id
			 * @param Es_id
			 * @return
			 */
			public List<TB_Estate_Unit> select_unite(String ts_id, String ly_id){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				List<TB_Estate_Unit> list = new ArrayList<TB_Estate_Unit>();
				String sql="select Un_id, UnName from TB_Estate_Unit where ts_id='"+ts_id+"' and Bu_id='"+ly_id+"' and status='1'  ";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					TB_Estate_Unit unite =null;
					while(rs.next())
					{
						unite =new TB_Estate_Unit();
						unite.setUn_id(rs.getInt("Un_id"));
						unite.setUnName(rs.getString("UnName"));
						
						list.add(unite);
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
			 * 人工现金退款修改订单
			 * @param ts_id
			 * @param Eo_id
			 * @return
			 */
			public boolean update_order_rgtk(String ts_id,String Eo_id,String tk_Reason) {
				boolean flag=false;
				PublicDBhandles pu=null;
				String sql = "update TB_Estate_Order set tk_Reason='"+tk_Reason+"', tk_total=total_sj,tk_time='"+new T_time().getTime()+"',payStatus=3,tk_type=1,tk_status=1 WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
				try {
					pu = new PublicDBhandles();
					pu.init(sql);
					flag=pu.update();
				} catch (Exception e) {
					flag = false;
					e.printStackTrace();
				} 
				return flag;
			}	
			
			/**
			 * 根据订单主键获取订单的信息
			 * @param Eo_id
			 * @return
			 */
			public  TB_Estate_Order getOrders(String Eo_id ){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				TB_Estate_Order order = null;
				String sql= "select b.EsName,c.BuName,d.OwnerName,d.EhNumber,f.itemName,e.unname, " +
						" a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join" +
						" TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
						" left join TB_Estate_Unit e on a.Un_id=e.Un_id  left join TB_Estate_item f on f.ei_id=a.payItem " +
						" where Eo_id='"+Eo_id+"' and a.status='1' and b.status='1' and c.status='1'  and d.status='1' and f.status='1'";
				 
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					if(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
						order.setA_id(rs.getInt("a_id"));
						order.setEs_id(rs.getInt("Es_id"));
						order.setBu_id(rs.getInt("Bu_id"));
						order.setEh_id(rs.getInt("Eh_id"));
						order.setOrderType(rs.getInt("orderType"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
						order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
						order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
						order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
						order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
						order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
						order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
						order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
						order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
						order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
						order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
						order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
						order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
						order.setStatus(rs.getInt("status"));
						order.setLockSign(rs.getString("LockSign"));
						order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
						order.setItemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
						order.setUnName(rs.getString("unName")==null?"":rs.getString("unName"));
						order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
						order.setOwnerName(rs.getString("OwnerName")==null?"":rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber")==null?"":rs.getString("EhNumber"));//房屋编号
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
				return order;
			}
			 /**
			 * 根据商户id得到商户名称
			 * @param ts_id
			 * @return
			 */
			public String getTB_SEV_Name(String ts_id)
			{	
				PublicDBhandles pu=new PublicDBhandles();
				ResultSet rs = null;
				String str="";
				String sql="select ts_company_name from TB_SEV where ts_id='"+ts_id+"' ";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					if(rs.next())
					{
						str=rs.getString("ts_company_name");
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
				return str;
			}
			
			/**
			 * 得到选中的订单对象
			 * @param orderid
			 * @return
			 */
			public List<TB_Estate_Order> getTBorder(String orderids ) {
				ResultSet rs=null;
				TB_Estate_Order od=null;
				List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				String orderid = orderids.replaceAll(";", "','");
				
				String sql="select Eh_id from TB_Estate_Order where Eo_id in ('"+orderid+"') ";
				PublicDBhandles pu=null;
				try {
					pu = new PublicDBhandles();
					pu.init(sql);
					rs = pu.Query();
					while(rs.next()) {
						od=new TB_Estate_Order();
						od.setEh_id(rs.getInt("eh_id"));
						list.add(od);
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
			
			/**
			 * 批量现金支付
			 * @param ts_id
			 * @param orderids
			 * @return
			 */
			public boolean delete_order_plxj(String ts_id,String orderids,String payType){
				boolean flag=false;
				PublicDBhandles pu=null;
				String orderid = orderids.replaceAll(";", "','");
				try {
					pu = new PublicDBhandles();
					String bankid = "XJ"+new Date().getTime();
					String sql = "update TB_Estate_Order set bankid='"+bankid+"', total_sj=total , payType='"+payType+"' , payStatus='1' , pay_time='"+new T_time().getTime()+"'  WHERE ts_id = '"+ts_id+"' and Eo_id in ('"+orderid+"')";
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}
			
			
			
			/**
			 * 批量现金支付并附加操作员id tu_id
			 * @param ts_id
			 * @param orderids
			 * @return
			 */
			public boolean delete_order_plxj(String ts_id,String orderids,String payType,String tu_id){
				boolean flag=false;
				PublicDBhandles pu=null;
				String orderid = orderids.replaceAll(";", "','");
				try {
					pu = new PublicDBhandles();
					String bankid = "XJ"+new Date().getTime();
					String sql = "update TB_Estate_Order set bankid='"+bankid+"', total_sj=total+total_znj , payType='"+payType+"' , payStatus='1' , pay_time='"+new T_time().getTime()+"' ,tu_id='"+tu_id+"'  WHERE ts_id = '"+ts_id+"' and Eo_id in ('"+orderid+"')";
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}
			
			/**
			 * 批量现金支付并附加操作员id tu_id
			 * @param ts_id
			 * @param orderids
			 * @return
			 */
			public boolean delete_order_plxj1(String ts_id,String orderid,String payType,String tu_id,String znj,String sjje){
				boolean flag=false;
				PublicDBhandles pu=null;
//				String orderid = orderids.replaceAll(";", "','");
				try {
					pu = new PublicDBhandles();
					String bankid = "XJ"+new Date().getTime();
					String sql = "update TB_Estate_Order set bankid='"+bankid+"', total_sj= total + "+znj+" ,total_znj='"+znj+"', payType='"+payType+"' , payStatus='1' , pay_time='"+new T_time().getTime()+"' ,tu_id='"+tu_id+"'  WHERE ts_id = '"+ts_id+"' and Eo_id ='"+orderid+"'";
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}
			
			/**
			 * 根据订单主键获取订单的信息
			 * @param Eo_id
			 * @return
			 */
			public  TB_Estate_Order getHBOrders(String Eo_id ){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				TB_Estate_Order order = new TB_Estate_Order();
				Map<String, Double > map = new HashMap<String, Double >();
				String sql= "select ItemName,a.Eo_id, b.EsName,c.BuName, a.Remark, bankid, pay_time ,EhNumber," +
						"OwnerName,PayType ,PayStatus ,a.total, a.total_sj , a.Eh_id from TB_Estate_Order a " +
						"inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
						"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id " +
						"inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
						"left join TB_Estate_Unit e on a.Un_id=e.Un_id  " +
						"left join TB_Estate_item f on f.ei_id=a.payItem  " +
						"where bankid='"+Eo_id+"' and a.status='1' " +
						"and b.status='1' and c.status='1'  and d.status='1' and f.status='1' ";
 
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					while(rs.next())
					{
						order.setEo_id(rs.getInt("Eo_id"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
						order.setOwnerName(rs.getString("OwnerName")==null?"":rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber")==null?"":rs.getString("EhNumber"));//房屋编号
						String ItemName = rs.getString("ItemName")==null?"":rs.getString("ItemName");
						double total_sj = Double.parseDouble(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						if(!map.containsKey(ItemName)){
							map.put(ItemName, total_sj);
						}else{
							total_sj = map.get(ItemName)+total_sj;
							DecimalFormat data=new DecimalFormat("#.00");
							total_sj = Double.parseDouble(data.format(total_sj));
							map.put(ItemName, total_sj);
						}
						String remark = "";
						String ItemNames = "";
						double total_sjs = 0.00;
						for (Map.Entry<String, Double> entry : map.entrySet()) { 
							remark = remark+","+ entry.getKey() + ": " + entry.getValue(); 
							ItemNames = ItemNames+","+entry.getKey();
							total_sjs = total_sjs + entry.getValue();
						}
						order.setItemName(ItemNames.substring(1, ItemNames.length()));
						double total = Double.parseDouble(rs.getString("total")==null?"0.00":rs.getString("total"));
						if(order.getTotal()!=null){
							total = Double.parseDouble(order.getTotal()) + total;
						}
						order.setTotal(total+"");
						order.setTotal_sj(total_sjs+"");
						order.setRemark(remark.substring(1,remark.length()));
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
				return order;
			}
			
			/**
			 * 得到订单的信息
			 * @param Eo_id
			 * @return
			 */
			public  List<TB_Estate_Order> selectHBorders_mx(String Eo_id){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				TB_Estate_Order order = null;
				String sql= "select b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName,g.Ct_ItemType," +
						"g.Ct_znjDay,g.Ct_znjRatio,a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
						"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
						"inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on a.Un_id=f.Un_id  inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType " +
						"where bankid='"+Eo_id+"' and a.status='1'";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					while(rs.next())
					{
						order =new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
						order.setA_id(rs.getInt("a_id"));
						order.setEs_id(rs.getInt("Es_id"));
						order.setBu_id(rs.getInt("Bu_id"));
						order.setEh_id(rs.getInt("Eh_id"));
						order.setOrderType(rs.getInt("orderType"));
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
						order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
						order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
						order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
						order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
						order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
						order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
						order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
						order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
						order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
						order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
						order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
						order.setStatus(rs.getInt("status"));
						order.setLockSign(rs.getString("LockSign"));
						order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
						
						order.setEsName(rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName"));//楼宇名称
						order.setUnName(rs.getString("UnName")==null?"无单元":rs.getString("UnName"));//楼宇名称
						order.setOwnerName(rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber"));//房屋编号
						order.setItemName(rs.getString("itemName"));//收费方式 
						order.setItemType(rs.getString("Ct_ItemType"));//收费方式
						order.setZnjDay(rs.getString("Ct_znjDay"));//滞纳金天数
						order.setZnjRatio(rs.getString("Ct_znjRatio"));//滞纳金比例
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
			 * 根据订单主键获取订单的信息
			 * @param Eo_id
			 * @return
			 */
			public  TB_Estate_Order getPLXJSFOrders(String Eo_id ){
				Eo_id = Eo_id.replaceAll(";", "','");
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				TB_Estate_Order order = null;
				Map<String, Double > map = new HashMap<String, Double >();
				Map<String, Double > map1 = new HashMap<String, Double >();
				String sql= "select a.ts_id,e.UnName,ItemName,a.Eo_id, b.EsName,c.BuName, a.Remark, bankid, pay_time ,EhNumber," +
						"OwnerName,PayType,PayStatus,a.total,a.total_sj,a.Eh_id,a.orderType,a.total_znj from TB_Estate_Order a " +
						"inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
						"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id " +
						"inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
						"left join TB_Estate_Unit e on a.Un_id=e.Un_id  " +
						"left join TB_Estate_item f on f.ei_id=a.payItem  " +
						"where Eo_id in ('"+Eo_id+"') and a.status='1' " +
						"and b.status='1' and c.status='1'  and d.status='1' and f.status='1' ";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
					while(rs.next())
					{
						order = new TB_Estate_Order();
						order.setEo_id(rs.getInt("Eo_id"));
						order.setTs_id(rs.getInt("ts_id"));
					
						order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
						order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
						order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
						order.setPayStatus(rs.getInt("payStatus"));
						order.setUnName(rs.getString("unName")==null?"":rs.getString("unName"));
						order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
						order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
						order.setOwnerName(rs.getString("OwnerName")==null?"":rs.getString("OwnerName"));//户主姓名
						order.setEhNumber(rs.getString("EhNumber")==null?"":rs.getString("EhNumber"));//房屋编号
						order.setOrderType(rs.getInt("orderType"));
						String ItemName = rs.getString("ItemName")==null?"":rs.getString("ItemName");
						//double total_sj = Double.parseDouble(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
						double total = Double.parseDouble(rs.getString("total")==null?"0.00":rs.getString("total"));
						double total_znj = Double.parseDouble(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
						if(!map.containsKey(ItemName)){
							DecimalFormat data=new DecimalFormat("#.00");
							total = Double.parseDouble(data.format(total));
							map.put(ItemName, total);
						}else{
							total = map.get(ItemName)+total;
							DecimalFormat data=new DecimalFormat("#.00");
							total = Double.parseDouble(data.format(total));
							map.put(ItemName, total);
						}
						
						
						if(!map1.containsKey(ItemName)){
							DecimalFormat data=new DecimalFormat("#.00");
							total_znj = Double.parseDouble(data.format(total_znj));
							map1.put(ItemName, total_znj);
						}else{
							total_znj = map1.get(ItemName)+total_znj;
							DecimalFormat data=new DecimalFormat("#.00");
							total_znj = Double.parseDouble(data.format(total_znj));
							map1.put(ItemName, total_znj);
						}
						String remark = "";
						String ItemNames = "";
						
						double total_sjs = 0.00;
						for (Map.Entry<String, Double> entry : map.entrySet()) { 
							remark = remark+","+ entry.getKey() + ": " + entry.getValue(); 
							ItemNames = ItemNames+","+entry.getKey();
							total_sjs = total_sjs + entry.getValue();
						}
						String total_znjRemk = "  ";
						for (Map.Entry<String, Double> entry : map1.entrySet()) { 
							if(entry.getValue() > 0 ){
								total_znjRemk = total_znjRemk+","+ entry.getKey() + "滞纳金: " + entry.getValue(); 
							}
							
						}
						DecimalFormat data=new DecimalFormat("#.00");
						total_sjs = Double.parseDouble(data.format(total_sjs));
						
						order.setItemName(ItemNames.substring(1, ItemNames.length()));
						order.setTotal(total_sjs+"");
						order.setTotal_sj(total_sjs+"");
						order.setRemark(remark.substring(1,remark.length()));
						order.setTotal_znj(total_znjRemk.substring(1,total_znjRemk.length()));
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
				return order;
			}
			/**
			 * 通过商户ID得到银行信息
			 * @param Condition
			 * @return
			 */
			public TB_SEV_CCBBank getTB_SEV_CCBBank(String ts_id)
			{
				PublicDBhandles pu=new PublicDBhandles();
				TB_SEV_CCBBank ts = null;
				ResultSet rs = null;
				String sql="select * from TB_SEV_CCBBank where status='1' and ts_id='"+ts_id+"' ";
				//System.out.println(sql);
				try {
					pu.init(sql);
					rs = pu.Query();
					while (rs.next()) 
					{
					    ts=new TB_SEV_CCBBank();

						ts.setPartner(rs.getString("partner")==null?"":rs.getString("partner"));
						ts.setPublickey(rs.getString("publickey")==null?"":rs.getString("publickey"));
						ts.setBz(rs.getString("bz")==null?"":rs.getString("bz"));
						ts.setDzczy(rs.getString("dzczy")==null?"":rs.getString("dzczy"));
						ts.setDzmm(rs.getString("dzmm")==null?"":rs.getString("dzmm"));
						ts.setFhdm(rs.getString("fhdm")==null?"":rs.getString("fhdm"));
						ts.setGtdm(rs.getString("gtdm")==null?"":rs.getString("gtdm"));
						ts.setJym(rs.getString("jym")==null?"":rs.getString("jym"));
						ts.setPubkey(rs.getString("pubkey")==null?"":rs.getString("pubkey"));
						ts.setShdm(rs.getString("shdm")==null?"":rs.getString("shdm"));
						ts.setTs_id(rs.getString("ts_id"));
						ts.setTscb_id(rs.getString("tscb_id")==null?"":rs.getString("tscb_id"));
						ts.setZflx(rs.getString("zflx"));
						ts.setCreate_time(rs.getDate("create_time")+" "+rs.getTime("create_time"));
						ts.setStatus(rs.getString("status")==null?"":rs.getString("status"));
						
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
						pu.close(rs);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return ts;
			}	
			
			/**
			 * 被扫修改订单
			 * @param ts_id
			 * @param Eo_id
			 * @return
			 */
			public boolean update_order_swept(String yqStatus,String bankid ,String ts_id,String orderid,String total_sj) {
				boolean flag=false;
				PublicDBhandles pu=null;
				String sql = "update TB_Estate_Order set  yqStatus='"+yqStatus+"' ,bankid='"+bankid+"'," +
						" total_sj=total + total_znj ," +
						" payType='2' , payStatus='1' , pay_time='"+new T_time().getTime()+"' " +
						" WHERE ts_id = '"+ts_id+"' and bankid= '"+orderid+"'";
				try {
					pu = new PublicDBhandles();
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					flag = false;
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}	
			/**
			 * 被扫验签失败修改订单
			 * @param ts_id
			 * @param Eo_id
			 * @return
			 */
			public boolean updateYQ_swept(String yqStatus,String ts_id,String Eo_id) {
				boolean flag=false;
				PublicDBhandles pu=null;
				String sql = "update TB_Estate_Order set  yqStatus='"+yqStatus+"'   WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
				try {
					pu = new PublicDBhandles();
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					flag = false;
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}	
			
			/**
			 * 为防止多次被扫，所以提交支付的时候 使用bankid去提交支付，修改bankid 的值
			 * @param eoid
			 * @param bankid
			 * @param ts_id
			 * @return
			 */
			public boolean updateBSorder(String eoid,String bankid ,String ts_id) {
				boolean flag=false;
				PublicDBhandles pu=null;
				if(eoid.contains(";")){
					eoid = eoid.replaceAll(";", "','");
				}
				
				String sql = "update TB_Estate_Order set " +
						" bankid='"+bankid+"',payType='2'  WHERE ts_id = '"+ts_id+"' and eo_id in ('"+eoid+"')";
				try {
					pu = new PublicDBhandles();
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					flag = false;
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}	
			
			
			
			/**
			 * 为防止多次被扫，所以提交支付的时候 使用bankid去提交支付，修改bankid 的值   添加操作员信息
			 * @param eoid
			 * @param bankid
			 * @param ts_id
			 * @return
			 */
			public boolean updateBSorder(String eoid,String bankid ,String ts_id,String tu_id) {
				boolean flag=false;
				PublicDBhandles pu=null;
				if(eoid.contains(";")){
					eoid = eoid.replaceAll(";", "','");
				}
				
				String sql = "update TB_Estate_Order set " +
						" bankid='"+bankid+"',payType='2',tu_id='"+tu_id+"'  WHERE ts_id = '"+ts_id+"' and eo_id in ('"+eoid+"')";
				try {
					pu = new PublicDBhandles();
					pu.init(sql);
					flag=pu.updates();
				} catch (Exception e) {
					flag = false;
					e.printStackTrace();
				} finally {
					try {
						pu.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}	

 
			/**
			 * 是否开通被扫
			 * @param ts_id
			 * @return
			 */
			 public String getIsswept(String ts_id) {
			        PublicDBhandles pu = new PublicDBhandles();
			        ResultSet rs = null;
			        String str="";
			        String sql = "select isswept from TB_SEV where ts_id='"+ts_id+"' and status=1 ";
			        try {
			            pu.init(sql.toString());
			            rs = pu.Query();
			            if (rs.next()) {
			            	str = rs.getString("isswept");
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                pu.close();
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
			        return str;
			    }
			//全部删除未支付订单
			 public boolean deleteQb_order(String ts_id,String qxid,String lyid,String dyid,String orderType,String jfxid) {
					boolean flag=false;
					PublicDBhandles pu=null;
					String sql = "update TB_Estate_Order set status='0' WHERE ts_id = '"+ts_id+"' and orderType='"+orderType+"' and payStatus='0' and Es_id= '"+qxid+"'";
					if(!"".equals(lyid)){
						sql+=" and Bu_id='"+lyid+"'";
					}
					if(!"".equals(dyid)){
						sql+=" and Un_id='"+dyid+"'";
					}
					if(!"".equals(jfxid)){
						sql+=" and payItem='"+jfxid+"'";
					}
					try {
						pu = new PublicDBhandles();
						pu.init(sql);
						flag=pu.updates();
					} catch (Exception e) {
						flag = false;
						e.printStackTrace();
					} finally {
						try {
							pu.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return flag;
				}
			 /**
			  * 根据收费项查询子表限额
			  * @param Ei_id
			  * @return
			  */
			 public String select_item_xeZb2(String Ei_id,String Ct_id) {
			        PublicDBhandles pu = new PublicDBhandles();
			        ResultSet rs = null;
			        String str="";
			        String sql = "select * from TB_Estate_ChargeType where Ei_id = '"+Ei_id+"' and  Ct_id='"+Ct_id+"' and status=1";
			        try {
			            pu.init(sql.toString());
			            rs = pu.Query();
			            if (rs.next()) {
			            	String s = rs.getString("Ct_LimitNumber");
			            	if(s.equals("")){
			            		s="aaa";
			            	}
			            	str = rs.getString("Ct_Limited")+"##"+s+"##"+rs.getString("Ct_BuyLimited");
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        } finally {
			            try {
			                if (rs != null) {
			                    rs.close();
			                }
			                pu.close();
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
			        return str;
			    }
	/**
	 * 查询该小区的所有负责人
	 * @param Es_id
	 * @param ts_id
	 * @return
	 */
	public List<TB_SEV_USER> selFzr(String Es_id,String ts_id){
		List<TB_SEV_USER> users=new ArrayList<TB_SEV_USER>();
		PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        TB_SEV_USER user=null;
        String sql="select b.tu_id,b.username from  TB_SEV_USER b where  b.status<>0  and " +
        	 " (b.qq='"+Es_id+"' or b.qq like '%,"+Es_id+"' or b.qq like '%,"+Es_id+",%' or b.qq like '"+Es_id+",%') and  b.u_id='"+ts_id+"'";
		pu.init(sql);
		rs=pu.Query();
		try {
			while(rs.next()){
				user=new TB_SEV_USER();
				user.setTu_id(rs.getString("tu_id"));
				user.setUsername((rs.getString("username")==null || "".equals(rs.getString("username")))?"管理员":rs.getString("username"));
				users.add(user);
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
        
        return users;
	}
	public  TB_Estate_Order getHBOrders1(String Eo_id ){
		ResultSet rs = null;
		PublicDBhandles pu=new PublicDBhandles();
		TB_Estate_Order order = new TB_Estate_Order();
		Map<String, Double > map = new HashMap<String, Double >();
		Map<String,String> map1=new HashMap<String, String>();
		Map<String,TB_Estate_Order> map2=new HashMap<String, TB_Estate_Order>();
		String sql= "select ItemName,a.Eo_id, b.EsName,a.Es_id,c.BuName, a.Remark, bankid, pay_time ,EhNumber," +
				"OwnerName,PayType ,PayStatus ,a.total, a.total_sj , a.Eh_id,a.Cost_startTime,a.Cost_endTime  from TB_Estate_Order a " +
				"inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
				"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id " +
				"inner join TB_Estate_House d on a.Eh_id=d.Eh_id " +
				"left join TB_Estate_Unit e on a.Un_id=e.Un_id  " +
				"left join TB_Estate_item f on f.ei_id=a.payItem  " +
				"where bankid='"+Eo_id+"' and a.status='1' " +
				"and b.status='1' and c.status='1'  and d.status='1' and f.status='1' ";

		try {
			pu.init(sql.toString());
			rs=pu.Query();
			while(rs.next())
			{
				order.setEo_id(rs.getInt("Eo_id"));
				order.setEs_id(rs.getInt("Es_id"));
				order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
				order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
				order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
				order.setPayStatus(rs.getInt("payStatus"));
				order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
				order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
				order.setOwnerName(rs.getString("OwnerName")==null?"":rs.getString("OwnerName"));//户主姓名
				order.setEhNumber(rs.getString("EhNumber")==null?"":rs.getString("EhNumber"));//房屋编号
				String ItemName = rs.getString("ItemName")==null?"":rs.getString("ItemName");
				double total_sj = Double.parseDouble(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
				String Cost_startTime=rs.getDate("Cost_startTime")==null?"":rs.getDate("Cost_startTime")+"";
				String Cost_endTime=rs.getDate("Cost_endTime")==null?"":rs.getDate("Cost_endTime")+"";
				String rq="";  //费用起止日期
				if(!"".equals(Cost_startTime) && !"".equals(Cost_endTime)){
			      String ks=Cost_startTime.substring(5, 7);
			      String jz=Cost_endTime.substring(5,7);
			      if(ks.equals(jz)){
			    	  rq=ks+"月,";
			      }else{
			    	  rq=ks+"-"+jz+"月,";
			      }
			     }
				TB_Estate_Order od=new TB_Estate_Order();
				if(!map2.containsKey(ItemName)){
					od.setRemark(rq);
					od.setTotal_sj(total_sj+"");
					map2.put(ItemName,od);
				}else{
					od=map2.get(ItemName);
					rq=od.getRemark()+rq;
					total_sj=Double.parseDouble(od.getTotal_sj())+total_sj;
					DecimalFormat data=new DecimalFormat("#.00");
					total_sj = Double.parseDouble(data.format(total_sj));
					od.setRemark(rq);
					od.setTotal_sj(total_sj+"");
					map2.put(ItemName, od);
				}
				String remark = "";
				String ItemNames = "";
				double total_sjs = 0.00;
				for(Map.Entry<String, TB_Estate_Order> entry :map2.entrySet()){
					if("".equals(entry.getValue().getRemark()) ){
					  remark=remark+";"+entry.getKey()+":" + entry.getValue().getTotal_sj();
					}else{
					 remark=remark+";("+entry.getValue().getRemark().substring(0,entry.getValue().getRemark().length()-1)+")"+entry.getKey()+":" + entry.getValue().getTotal_sj();
					}
					ItemNames = ItemNames+","+entry.getKey();
					total_sjs = total_sjs + Double.parseDouble(entry.getValue().getTotal_sj()) ;
				}
				order.setItemName(ItemNames.substring(1, ItemNames.length()));
				double total = Double.parseDouble(rs.getString("total")==null?"0.00":rs.getString("total"));
				if(order.getTotal()!=null){
					total = Double.parseDouble(order.getTotal()) + total;
				}
				order.setTotal(total+"");
				order.setTotal_sj(total_sjs+"");
				order.setRemark(remark.substring(1,remark.length()));
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
		return order;
	}
	/**
	 * 根据查询条件得到应缴金额
	 * @param Condition
	 * @return
	 */
	public double getyjje(String Condition)
	{
		PublicDBhandles pu = new PublicDBhandles();
		ResultSet rs=null;
		double je=0.0d;
		String sql="select sum(total) from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id  inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType where "+Condition;
		try {
			pu.init(sql);
			rs = pu.Query();
			if(rs.next()) 
			{
				je=rs.getDouble(1);
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
		
		return je;
	}
	/**
	 * 根据查询条件得到实缴金额
	 * @param Condition
	 * @return
	 */
	public double getsjje(String Condition)
	{
		PublicDBhandles pu = new PublicDBhandles();
		ResultSet rs=null;
		double je=0.0d;
		String sql="select sum(total_sj) from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id  inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType where "+Condition;
		try {
			pu.init(sql);
			rs = pu.Query();
			if(rs.next()) 
			{
				je=rs.getDouble(1);
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
		
		return je;
	}
	/**
	 * 根据查询条件得到滞纳金金额
	 * @param Condition
	 * @return
	 */
	public double getZnjje(String Condition)
	{
		PublicDBhandles pu = new PublicDBhandles();
		ResultSet rs=null;
		double je=0.0d;
		String sql="select sum(total_znj) from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id  inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on a.payItem=e.Ei_id left join TB_Estate_Unit f on d.Un_id=f.Un_id inner join TB_Estate_ChargeType g on a.payItem=g.Ei_id and g.Ht_id=d.EhType where "+Condition+" and payStatus=1";
		try {
			pu.init(sql);
			rs = pu.Query();
			if(rs.next()) 
			{
				je=rs.getDouble(1);
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
		
		return je;
	}
	/**
	 * 根据查询条件得到应缴金额
	 * @param Condition
	 * @return
	 */
	public double getGeyjje(String Condition)
	{
		PublicDBhandles pu = new PublicDBhandles();
		ResultSet rs=null;
		double je=0.0d;
		String sql="select sum(total) from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where "+Condition;
		try {
			pu.init(sql);
			rs = pu.Query();
			if(rs.next()) 
			{
				je=rs.getDouble(1);
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
		
		return je;
	}
	/**
	 * 根据查询条件得到实缴金额
	 * @param Condition
	 * @return
	 */
	public double getGesjje(String Condition)
	{
		PublicDBhandles pu = new PublicDBhandles();
		ResultSet rs=null;
		double je=0.0d;
		String sql="select sum(total_sj) from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where "+Condition;
		try {
			pu.init(sql);
			rs = pu.Query();
			if(rs.next()) 
			{
				je=rs.getDouble(1);
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
		
		return je;
	}
	//年度预收订单
		public  List<TB_Estate_Order> selectNDYS_orders(int pagesize,int pagenum,String Condition){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
			TB_Estate_Order order =null;
			int num=(pagenum-1)*pagesize;
			String sql="select top "+pagesize+" b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName, " +
				" a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
				"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where  a.Eo_id not in(" +
				"select top "+num+" a.Eo_id from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id" +
				" inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id  inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where "+Condition+"  order by d.EhNumber,a.eo_id  ) and "+Condition+"   order by d.EhNumber,a.eo_id ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
			while(rs.next())
			{
				order =new TB_Estate_Order();
				order.setEo_id(rs.getInt("Eo_id"));
				order.setTs_id(rs.getInt("ts_id"));
				order.setA_id(rs.getInt("a_id"));
				order.setEs_id(rs.getInt("Es_id"));
				order.setBu_id(rs.getInt("Bu_id"));
				order.setEh_id(rs.getInt("Eh_id"));
				order.setOrderType(rs.getInt("orderType"));
				order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
				order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
				order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
				order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
				order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
				order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
				order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
				order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
				order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
				order.setPayStatus(rs.getInt("payStatus"));
				order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
				order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
				order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
				order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
				order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
				order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
				order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
				order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
				order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
				order.setStatus(rs.getInt("status"));
				order.setLockSign(rs.getString("LockSign"));
				order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
				
				order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
				order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
				order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
				order.setOwnerName(rs.getString("OwnerName"));//户主姓名
				order.setEhNumber(rs.getString("EhNumber"));//房屋编号
				order.setItemName(rs.getString("itemName"));//房屋编号
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
		//年度预收订单
	  public  List<TB_Estate_Order> selectNDYS_orders(String Condition){
				ResultSet rs = null;
				PublicDBhandles pu=new PublicDBhandles();
				List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
				TB_Estate_Order order =null;
				String sql="select  b.EsName,c.BuName,f.UnName,d.OwnerName,d.EhNumber,e.itemName, " +
					" a.* from TB_Estate_Order a inner join TB_Estate_Village b on a.Es_id=b.Es_id " +
					"inner join TB_Estate_Build c on a.Bu_id=c.Bu_id inner join TB_Estate_House d on a.Eh_id=d.Eh_id inner join TB_Estate_item e on e.Ei_id=a.payitem left join TB_Estate_Unit f on d.Un_id=f.Un_id where " +
					" "+Condition+"   order by d.EhNumber,a.eo_id ";
				try {
					pu.init(sql.toString());
					rs=pu.Query();
				while(rs.next())
				{
					order =new TB_Estate_Order();
					order.setEo_id(rs.getInt("Eo_id"));
					order.setTs_id(rs.getInt("ts_id"));
					order.setA_id(rs.getInt("a_id"));
					order.setEs_id(rs.getInt("Es_id"));
					order.setBu_id(rs.getInt("Bu_id"));
					order.setEh_id(rs.getInt("Eh_id"));
					order.setOrderType(rs.getInt("orderType"));
					order.setBankid(rs.getString("bankid")==null?"":rs.getString("bankid"));
					order.setPayItem(rs.getString("payItem")==null?"":rs.getString("payItem"));
					order.setTotal(rs.getString("total")==null?"0.00":rs.getString("total"));
					order.setTotal_yh(rs.getString("total_yh")==null?"0.00":rs.getString("total_yh"));
					order.setTotal_znj(rs.getString("total_znj")==null?"0.00":rs.getString("total_znj"));
					order.setTotal_sj(rs.getString("total_sj")==null?"0.00":rs.getString("total_sj"));
					order.setPayType(rs.getString("payType")==null?"":rs.getString("payType"));
					order.setCreat_time(rs.getString("creat_time")==null?"":rs.getString("creat_time"));
					order.setPay_time(rs.getString("pay_time")==null?"":rs.getString("pay_time"));
					order.setPayStatus(rs.getInt("payStatus"));
					order.setTk_total(rs.getString("tk_total")==null?"0.00":rs.getString("tk_total"));
					order.setTk_time(rs.getString("tk_time")==null?"":rs.getString("tk_time"));
					order.setTk_type(rs.getString("tk_type")==null?"":rs.getString("tk_type"));
					order.setTk_status(rs.getString("tk_status")==null?"":rs.getString("tk_status"));
					order.setTk_Reason(rs.getString("tk_Reason")==null?"":rs.getString("tk_Reason"));
					order.setCost_startTime(rs.getString("Cost_startTime")==null?"":rs.getString("Cost_startTime"));
					order.setCost_endTime(rs.getString("Cost_endTime")==null?"":rs.getString("Cost_endTime"));
					order.setScost_startTime(rs.getString("Scost_startTime")==null?"":rs.getString("Scost_startTime"));
					order.setScost_endTime(rs.getString("Scost_endTime")==null?"":rs.getString("Scost_endTime"));
					order.setStatus(rs.getInt("status"));
					order.setLockSign(rs.getString("LockSign"));
					order.setLockTime(rs.getString("LockTime")==null?"":rs.getString("LockTime"));
					
					order.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));//小区名称
					order.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));//楼宇名称
					order.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
					order.setOwnerName(rs.getString("OwnerName"));//户主姓名
					order.setEhNumber(rs.getString("EhNumber"));//房屋编号
					order.setItemName(rs.getString("itemName"));//房屋编号
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
		 * 修改订单的滞纳金
		 * @param ts_id
		 * @param Eo_id
		 * @param total_znj
		 * @return
		 */
		public boolean updateTotalZNJ(String ts_id,String Eo_id,String total_znj) {
			boolean flag=false;
			PublicDBhandles pu=null;
			String sql = "update TB_Estate_Order set total_znj='"+total_znj+"' WHERE ts_id = '"+ts_id+"' and Eo_id= '"+Eo_id+"'";
			try {
				pu = new PublicDBhandles();
				pu.init(sql);
				flag=pu.updates();
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			} finally {
				try {
					pu.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return flag;
		}
	  
	  
	  
	  
	  
}
