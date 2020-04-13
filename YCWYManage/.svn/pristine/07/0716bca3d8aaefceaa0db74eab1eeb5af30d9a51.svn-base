package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_Build;
import WYBack_Stage.Bean.TB_Estate_ChargeType;
import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Bean.TB_Estate_Village;
import WYBack_Stage.Bean.TB_Estate_item;
import WYCommunity.S_string;
import WYCommunity.T_time;

import ccbjf.system.db.PublicDBhandle;
import ccbjf.system.db.PublicDBhandles;

public class ChangeDao {
	
	
	/**
	 * 通过用户ID得到用户名称
	 * @param userid
	 * @return
	 */
	public String getu_id(String userid,String tablename)
	{

		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String name="";
		String sql="select tu_id from "+tablename+" where userid='"+userid+"' ";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				name=rs.getString("tu_id");
			}
		} catch (Exception e) {
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
		
		return name;
	}
	/**
	 * 根据tsid得到小区的名称
	 * @param ts_id
	 * @return
	 */
	public List<TB_Estate_Village> getXiaoqu(String ts_id)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		List<TB_Estate_Village> list=new ArrayList<TB_Estate_Village>();
		String sql="select Es_id,EsName from TB_Estate_Village where ts_id='"+ts_id+"'  and status=1 order by EsName";
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			while(rs.next()) 
			{
				tev=new TB_Estate_Village();
				tev.setEs_id(rs.getInt("es_id"));
				tev.setEsName(rs.getString("EsName"));
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
	
	/**
	 * 根据org_id和名称查询是否存在重名的
	 * @param org_id
	 * @param tpi_name
	 * @param ts_id
	 * @return
	 */
	public int getTBPay_Item_Name(String Es_id ,String itemName,String ts_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int bl=0;
		String sql="select count(*) count from TB_Estate_item where Es_id='"+Es_id+"' " +
				"and itemName='"+itemName+"' and status='1' and ts_id='"+ts_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			while(rs.next())
			{
				bl = rs.getInt("count");
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
		return bl;
	}
	
	/**
	 * 添加缴费项目
	 * @param a_id
	 * @param tpi_name
	 * @param tpi_desc
	 * @return
	 */
	public boolean add_TBPay_Item(String isyj,String gdxz,String Limited,String LimitNumber,String a_id,String ts_id,String xiaoqu,
			String sfname,String sftype,String price,String znjday,String zjnbl,String remark,String sfdesc)
	{	
		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql =" insert into TB_Estate_item(isYjLimited,BuyLimited,Limited,LimitNumber,a_id,ts_id,Es_id,itemName,itemDesc," +
					"ItemType,price,status,remark,znjDay,znjRatio) "+
			" values ('"+isyj+"','"+gdxz+"','"+Limited+"','"+LimitNumber+"','"+a_id+"','"+ts_id+"','"+xiaoqu+"','"+sfname+"','"+sfdesc+"'," +
					"'"+sftype+"','"+price+"','1','"+remark+"','"+znjday+"','"+zjnbl+"') ";
			
			PublicDBhandles.init(sql);
    		bl=PublicDBhandles.update();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return bl;
	}

	/**
	 * 添加管理员操作日志
	 * @param l_content
	 * @param user_id
	 */
	public static void add_Log(String l_content,String user_id,String type,String tu_id)
	{		
		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		try {
			
			String sql =" insert into TBAdmin_Log(l_content,user_id,l_time,Status,tu_id,type) values ('"+l_content+"','"+user_id+"','"+new T_time().getTime()+"','1','"+tu_id+"','"+type+"') ";

			PublicDBhandles.init(sql);
    		PublicDBhandles.update();
		} catch (Exception e) {
			e.printStackTrace();
		} 	

	}
	
	/**
	 *	查询总数据条数（不分页情况）
	 * @param tableName  表名
	 * @param condition  查询条件
	 * @return
	 * @throws Exception 
	 */
	public int getCount(String tableName,String condition) throws Exception
	{	
		int count = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(condition);
		 PublicDBhandle pu1=new PublicDBhandle();
		 ResultSet rs1 = null;
        try {
			rs1=pu1.eQuery(sql.toString());
			if(rs1.next())
			{
				count = Integer.parseInt(rs1.getString("count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			try {
				if(rs1!=null){
					rs1.close();
				}
				pu1.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}				
		return count;
	}
	
	/**
	 * 通过应用ID和商户ID得到该应用下所有可见状态的缴费项目
	 * @param a_id
	 * @param user_id
	 * @return
	 * @throws Exception 
	 */
	public List<TB_Estate_item> getTBPay_Item(String yznr,String ts_id,int pagesize,int pagenum,String a_id) throws Exception
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int num=(pagenum-1)*pagesize;
		List<TB_Estate_item> list=new ArrayList<TB_Estate_item>();
		StringBuffer sql= new StringBuffer();
		int count=0;
		  
		 sql.append("select top "+pagesize+" b.EsName,a.* from TB_Estate_item a ,TB_Estate_Village b where  " +
		 		"a.Es_id=b.Es_id and a.ts_id='"+ts_id+"'  ");
		 if(!yznr.equals("")){
			 sql.append(" and b.Es_id in ("+yznr+") ");
		 }
		 
		 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1" +
		 				" and a.Ei_id  not in (select top "+num+" a.Ei_id from TB_Estate_item a ," +
		 						"TB_Estate_Village b where  a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' ");
		 if(!yznr.equals("")){
			 sql.append(" and b.Es_id in ("+yznr+") ");
		 }
		 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1  order by b.EsName)  order by b.EsName  ");
		
		 if(yznr.equals("")){
			 count = getCount("TB_Estate_item a ,TB_Estate_Village b  ", " a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' " +
			 		"and a.a_id='"+a_id+"' and a.status=1 and b.status=1    ");
		 }else{
			 count = getCount("TB_Estate_item a ,TB_Estate_Village b  ", " a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' " +
			 		" and b.Es_id in ("+yznr+") and a.a_id='"+a_id+"' and a.status=1 and b.status=1    ");
		 }
			 
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			TB_Estate_item item=null;
			while(rs.next())
			{ 
				item=new TB_Estate_item();
				item.setCount(count);//总数据条数
				item.setEi_id(rs.getInt("ei_id"));
				item.setXiaoquName(rs.getString("EsName")==null?"":rs.getString("EsName"));
				item.setItemDesc(rs.getString("itemDesc")==null?"":rs.getString("itemDesc"));
				item.setItemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
				item.setItemType(rs.getString("itemType"));
				item.setPrice(rs.getString("price"));
				item.setZnjDay(rs.getString("znjDay")==null?"":rs.getString("znjDay"));
				item.setZnjRatio(rs.getString("znjRatio")==null?"":rs.getString("znjRatio"));
				item.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
				item.setLimited(rs.getString("limited")==null?"":rs.getString("limited"));
				item.setLimitNumber(rs.getString("limitNumber")==null?"":rs.getString("limitNumber"));
				item.setBuyLimited(rs.getString("buyLimited")==null?"":rs.getString("buyLimited"));
				item.setIsYjLimited(rs.getString("isYjLimited")==null?"":rs.getString("isYjLimited"));
				item.setYhCon(rs.getString("yhCon")==null?"":rs.getString("yhCon"));
				item.setYhJzCon(rs.getString("yhJzCon")==null?"":rs.getString("yhJzCon"));
				item.setYhRatio(rs.getString("yhRatio")==null?"":rs.getString("yhRatio"));
				item.setGuRatio(rs.getString("guRatio")==null?"":rs.getString("guRatio"));
				list.add(item);
			}

		} catch (Exception e) {
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
	 * 修改收费项目
	 * @param Ei_id
	 * @param ts_id
	 * @param name
	 * @param price1
	 * @param znjday1
	 * @param zjnbl1
	 * @return
	 * @throws Exception
	 */
	public boolean update_TBPay_Item(String gdxz,String isyj,String  LimitNumber,String Limited,String Ei_id,String ts_id,String name,String price1,String znjday1,
			String zjnbl1,String status,String shoufeifs) throws Exception
	{			
		PublicDBhandle PublicDBhandle=new PublicDBhandle();
		boolean bl=false;
		List<String> addTBMonitor = new ArrayList<String>();
		try {
			String sql ="update TB_Estate_item set BuyLimited='"+gdxz+"' ,isYjLimited='"+isyj+"' ," +
					"LimitNumber='"+LimitNumber+"' ,Limited='"+Limited+"',itemName='"+name+"',price='"+price1+"',znjDay='"+znjday1+"'" +
					",znjRatio='"+zjnbl1+"',status='"+status+"',itemtype='"+shoufeifs+"' where Ei_id='"+Ei_id+"' and ts_id='"+ts_id+"' ";
			addTBMonitor.add(sql);
    		String str=PublicDBhandle.eUpdatecommitbl(addTBMonitor);
    		
    		if(str.equals("")){
    			bl=true;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				PublicDBhandle.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return bl;
	}
	
	
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
			String sql="select Es_id, EsName from TB_Estate_Village where ts_id='"+ts_id+"'  and status='1'  ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_Village village =null;
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
		public  List<TB_Estate_Build> select_louyu(String ts_id, String Es_id){
			ResultSet rs = null;
			PublicDBhandles pu=new PublicDBhandles();
			List<TB_Estate_Build> list = new ArrayList<TB_Estate_Build>();
			String sql="select Bu_id, BuName from TB_Estate_Build where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and status='1' ";
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
		 * 通过应用ID和商户ID得到该应用下所有可见状态的缴费项目
		 * @param a_id
		 * @param user_id
		 * @return
		 * @throws Exception 
		 */
		public List<TB_Estate_item> getTBItem(String ts_id,String Es_id) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			List<TB_Estate_item> list=new ArrayList<TB_Estate_item>();
			String sql="";
				 sql="select  ei_id,itemName from TB_Estate_item  where ts_id='"+ts_id+"' " +
				 		"and Es_id='"+Es_id+"' and status=1 ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_item item=null;
				while(rs.next())
				{ 
					item=new TB_Estate_item();
					item.setEi_id(rs.getInt("ei_id"));
					item.setItemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
				
					list.add(item);
				}

			} catch (Exception e) {
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
		 * 新增收费单，预缴的缴费项不在这里显示 
		 * @param ts_id
		 * @param Es_id
		 * @return
		 * @throws Exception
		 */
		public List<TB_Estate_item> getTBItemNoyj(String ts_id,String Es_id) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			List<TB_Estate_item> list=new ArrayList<TB_Estate_item>();
			String sql="";
		 /* sql="select  ei_id,itemName,itemType,price from TB_Estate_item  where ts_id='"+ts_id+"' " +
				 "and Es_id='"+Es_id+"' and status=1 and isYjLimited=0 ";*/
			sql="select distinct  a.Ei_id,a.itemName from TB_Estate_item a right join TB_Estate_ChargeType b on a.Ei_id=b.Ei_id where ts_id='"+ts_id+"' " +
					"and Es_id='"+Es_id+"' and a.status=1 and b.status=1 and b.Ct_isYjLimited=0  ";
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_item item=null;
				while(rs.next())
				{ 
					item=new TB_Estate_item();
					item.setEi_id(rs.getInt("ei_id"));
					item.setItemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
					list.add(item);
				}

			} catch (Exception e) {
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
		public List<TB_Estate_House> getHouseinfoUP(String ts_id,String Un_id,String Es_id,String Bu_id,String EhNumber,String LEVELS,String tu_id) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			List<TB_Estate_House> list=new ArrayList<TB_Estate_House>();
			String sql="";
				 sql="select  a.EhNumber,a.eh_id,a.BuildArea,a.UseArea,a.HeatingArea,a.bu_id,a.CarNum,a.Un_id,a.EhType,a.storey,b.HtName from TB_Estate_House a inner join TB_Estate_Housetype b on a.EhType=b.Ht_id " +
				 " inner join TB_Estate_Build c on a.bu_id=c.bu_id  where a.Es_id='"+Es_id+"'  and a.ts_id='"+ts_id+"'  ";
				 if(!Bu_id.equals("")){
					 sql+="and a.Bu_id='"+Bu_id+"' ";
				 }else{
					 if("1".equals(LEVELS)){
						 sql+="  and (c.BuHead='' or c.BuHead is null or c.BuHead='"+tu_id+"') ";
					 }
				 }
				 if(!Un_id.equals("")){
					 sql+="and Un_id='"+Un_id+"' ";
				 }
				 if(EhNumber.equals("")){
					 sql+=" and a.status=1 and b.status=1 and handIs=1";
				 }else{
					 sql+=" and EhNumber='"+EhNumber+"' and a.status=1 and b.status=1 and handIs=1";
				 }
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				while(rs.next())
				{ 
					TB_Estate_House teh=new TB_Estate_House();
					teh.setBu_id(rs.getInt("bu_id"));
					teh.setUn_id(rs.getInt("Un_id"));
					teh.setEhNumber(rs.getString("EhNumber"));
					teh.setEh_id(rs.getInt("eh_id"));
					teh.setBuildArea(rs.getString("BuildArea"));
					teh.setUseArea(rs.getString("UseArea"));
					teh.setHeatingArea(rs.getString("HeatingArea"));
					teh.setCarNum(rs.getInt("CarNum"));
					teh.setEhType(rs.getString("ehType"));
					teh.setHtName(rs.getString("htName"));
					teh.setStorey(rs.getInt("storey"));
					list.add(teh);
					
				}

			} catch (Exception e) {
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
		public List<TB_Estate_House> getHouseinfo(String ts_id,String Un_id,String Es_id,String Bu_id,String EhNumber) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			List<TB_Estate_House> list=new ArrayList<TB_Estate_House>();
			String sql="";
				 sql="select EhNumber,eh_id,BuildArea,UseArea,HeatingArea,bu_id,CarNum,Un_id,EhType,b.HtName from TB_Estate_House a inner join TB_Estate_Housetype b on a.EhType=b.Ht_id " +
				 		"where Es_id='"+Es_id+"'  and ts_id='"+ts_id+"'  ";
				 if(!Bu_id.equals("")){
					 sql+="and Bu_id='"+Bu_id+"' ";
				 }
				 if(!Un_id.equals("")){
					 sql+="and Un_id='"+Un_id+"' ";
				 }
				 if(EhNumber.equals("")){
					 sql+=" and a.status=1 and b.status=1";
				 }else{
					 sql+=" and EhNumber='"+EhNumber+"' and a.status=1 and b.status=1";
				 }
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				while(rs.next())
				{ 
					TB_Estate_House teh=new TB_Estate_House();
					teh.setBu_id(rs.getInt("bu_id"));
					teh.setUn_id(rs.getInt("Un_id"));
					teh.setEhNumber(rs.getString("EhNumber"));
					teh.setEh_id(rs.getInt("eh_id"));
					teh.setBuildArea(rs.getString("BuildArea"));
					teh.setUseArea(rs.getString("UseArea"));
					teh.setHeatingArea(rs.getString("HeatingArea"));
					teh.setCarNum(rs.getInt("CarNum"));
					teh.setEhType(rs.getString("ehType"));
					teh.setHtName(rs.getString("htName"));
					list.add(teh);
					
				}

			} catch (Exception e) {
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
		 * 添加电梯 和 车位缴费项目
		 * @param a_id
		 * @param tpi_name
		 * @param tpi_desc
		 * @return
		 */
		public boolean add_DianTiItem(String a_id,String ts_id,String xiaoqu,String sfname,String sftype,String price,String remark)
		{	
			PublicDBhandles PublicDBhandles=new PublicDBhandles();
			boolean bl=false;
			try {
				String sql =" insert into TB_Estate_item(a_id,ts_id,Es_id,itemName,ItemType,price,status,remark) "+
				" values ('"+a_id+"','"+ts_id+"','"+xiaoqu+"','"+sfname+"','"+sftype+"','"+price+"','1','"+remark+"') ";
				
				PublicDBhandles.init(sql);
	    		bl=PublicDBhandles.update();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return bl;
		}	
		/**
		 * 添加各类缴费项目
		 * @param a_id
		 * @param tpi_name
		 * @param tpi_desc
		 * @return
		 */
		public boolean add_GLItem(String isyj,String gdxz,String Limited,String LimitNumber,String a_id,String ts_id,String xiaoqu,
				String sfname,String sftype,String price,String znjday,String zjnbl,String remark,String sfdesc,String yhCon,String yhJzCon,String yhRatio,String guRatio)
		{	
			PublicDBhandles PublicDBhandles=new PublicDBhandles();
			boolean bl=false;
			try {
				String sql =" insert into TB_Estate_item(isYjLimited,BuyLimited,Limited,LimitNumber,a_id,ts_id,Es_id,itemName,itemDesc," +
						"ItemType,price,status,remark,znjDay,znjRatio,yhCon,yhJzCon,yhRatio,guRatio) "+
				" values ('"+isyj+"','"+gdxz+"','"+Limited+"','"+LimitNumber+"','"+a_id+"','"+ts_id+"','"+xiaoqu+"','"+sfname+"','"+sfdesc+"'," +
						"'"+sftype+"','"+price+"','1','"+remark+"','"+znjday+"','"+zjnbl+"','"+yhCon+"','"+yhJzCon+"','"+yhRatio+"','"+guRatio+"') ";
				
				PublicDBhandles.init(sql);
	    		bl=PublicDBhandles.update();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return bl;
		}
		//指定收费项
		public List<TB_Estate_item> getTBItem(String yznr,String ts_id,int pagesize,int pagenum,String a_id,String sfname) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			int num=(pagenum-1)*pagesize;
			List<TB_Estate_item> list=new ArrayList<TB_Estate_item>();
			StringBuffer sql= new StringBuffer();
			int count=0;
			  
				 sql.append("select top "+pagesize+" b.EsName,a.* from TB_Estate_item a ,TB_Estate_Village b where  " +
				 		"a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' and a.itemName like '%"+sfname+"%' ");
				 if(!yznr.equals("")){
					 sql.append(" and b.Es_id in ("+yznr+") ");
				 }
				 
				 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1" +
				 				" and a.Ei_id  not in (select top "+num+" a.Ei_id from TB_Estate_item a ," +
				 						"TB_Estate_Village b where  a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' and a.itemName like '%"+sfname+"%' ");
				 if(!yznr.equals("")){
					 sql.append(" and b.Es_id in ("+yznr+") ");
				 }
				 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1  order by b.EsName)  order by b.EsName  ");
				
				 if(yznr.equals("")){
					 count = getCount("TB_Estate_item a ,TB_Estate_Village b  ", " a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' " +
					 		"and a.a_id='"+a_id+"' and a.status=1 and b.status=1  and a.itemName like '%"+sfname+"%'  ");
				 }else{
					 count = getCount("TB_Estate_item a ,TB_Estate_Village b  ", " a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' " +
					 		" and b.Es_id in ("+yznr+") and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and a.itemName like '%"+sfname+"%'   ");
				 }
				 
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_item item=null;
				while(rs.next())
				{ 
					item=new TB_Estate_item();
					item.setCount(count);//总数据条数
					item.setEi_id(rs.getInt("ei_id"));
					item.setXiaoquName(rs.getString("EsName")==null?"":rs.getString("EsName"));
					item.setItemDesc(rs.getString("itemDesc")==null?"":rs.getString("itemDesc"));
					item.setItemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
					item.setItemType(rs.getString("itemType"));
					item.setPrice(rs.getString("price"));
					item.setZnjDay(rs.getString("znjDay")==null?"":rs.getString("znjDay"));
					item.setZnjRatio(rs.getString("znjRatio")==null?"":rs.getString("znjRatio"));
					item.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
					item.setLimited(rs.getString("limited")==null?"":rs.getString("limited"));
					item.setLimitNumber(rs.getString("limitNumber")==null?"":rs.getString("limitNumber"));
					item.setBuyLimited(rs.getString("buyLimited")==null?"":rs.getString("buyLimited"));
					item.setIsYjLimited(rs.getString("isYjLimited")==null?"":rs.getString("isYjLimited"));
					item.setYhCon(rs.getString("yhCon")==null?"":rs.getString("yhCon"));
					item.setYhJzCon(rs.getString("yhJzCon")==null?"":rs.getString("yhJzCon"));
					item.setYhRatio(rs.getString("yhRatio")==null?"":rs.getString("yhRatio"));
					item.setGuRatio(rs.getString("guRatio")==null?"":rs.getString("guRatio"));
					list.add(item);
				}

			} catch (Exception e) {
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
		  修改各个缴费项
		 * @return
		 * @throws Exception
		 */
		public boolean updateGeItem(String gdxz,String isyj,String  LimitNumber,String Limited,String Ei_id,String ts_id,String name,String price,String znjday,
				String znjbl,String status,String shoufeifs,String yhCon,String yhJzCon,String yhRatio,String guRatio,String remark) throws Exception
		{			
			PublicDBhandle PublicDBhandle=new PublicDBhandle();
			boolean bl=false;
			List<String> addTBMonitor = new ArrayList<String>();
			try {
				String sql ="update TB_Estate_item set BuyLimited='"+gdxz+"' ,isYjLimited='"+isyj+"' , yhCon='"+yhCon+"', yhJzCon='"+yhJzCon+"' ," +
						"LimitNumber='"+LimitNumber+"',Limited='"+Limited+"',price='"+price+"',znjDay='"+znjday+"', yhRatio='"+yhRatio+"', guRatio='"+guRatio+"'" +
						",znjRatio='"+znjbl+"',status='"+status+"',itemtype='"+shoufeifs+"',remark='"+remark+"' where Ei_id='"+Ei_id+"' and ts_id='"+ts_id+"' and itemName='"+name+"'";
//				System.out.println(sql);
				addTBMonitor.add(sql);
	    		String str=PublicDBhandle.eUpdatecommitbl(addTBMonitor);
	    		
	    		if(str.equals("")){
	    			bl=true;
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					PublicDBhandle.closeSql();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			return bl;
		}		
		//其他收费项
		public List<TB_Estate_item> getQtTBItem(String yznr,String ts_id,int pagesize,int pagenum,String a_id,String sfname) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			int num=(pagenum-1)*pagesize;
			List<TB_Estate_item> list=new ArrayList<TB_Estate_item>();
			StringBuffer sql= new StringBuffer();
			int count=0;
			  
				 sql.append("select top "+pagesize+" b.EsName,a.* from TB_Estate_item a ,TB_Estate_Village b where  " +
				 		"a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' and a.itemName not in("+sfname+") ");
				 if(!yznr.equals("")){
					 sql.append(" and b.Es_id in ("+yznr+") ");
				 }
				 
				 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1" +
				 				" and a.Ei_id  not in (select top "+num+" a.Ei_id from TB_Estate_item a ," +
				 						"TB_Estate_Village b where  a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' and a.itemName not in("+sfname+") ");
				 if(!yznr.equals("")){
					 sql.append(" and b.Es_id in ("+yznr+") ");
				 }
				 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1  order by b.EsName)  order by b.EsName  ");
				
				 if(yznr.equals("")){
					 count = getCount("TB_Estate_item a ,TB_Estate_Village b  ", " a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' " +
					 		"and a.a_id='"+a_id+"' and a.status=1 and b.status=1  and a.itemName not in("+sfname+") ");
				 }else{
					 count = getCount("TB_Estate_item a ,TB_Estate_Village b  ", " a.Es_id=b.Es_id and a.ts_id='"+ts_id+"' " +
					 		" and b.Es_id in ("+yznr+") and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and a.itemName not in("+sfname+")  ");
				 }
				 
			try {
				pu.init(sql.toString());
//				System.out.println(sql);
				rs=pu.Query();
				TB_Estate_item item=null;
				while(rs.next())
				{ 
					item=new TB_Estate_item();
					item.setCount(count);//总数据条数
					item.setEi_id(rs.getInt("ei_id"));
					item.setXiaoquName(rs.getString("EsName")==null?"":rs.getString("EsName"));
					item.setItemDesc(rs.getString("itemDesc")==null?"":rs.getString("itemDesc"));
					item.setItemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
					item.setItemType(rs.getString("itemType"));
					item.setPrice(rs.getString("price"));
					item.setZnjDay(rs.getString("znjDay")==null?"":rs.getString("znjDay"));
					item.setZnjRatio(rs.getString("znjRatio")==null?"":rs.getString("znjRatio"));
					item.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
					item.setLimited(rs.getString("limited")==null?"":rs.getString("limited"));
					item.setLimitNumber(rs.getString("limitNumber")==null?"":rs.getString("limitNumber"));
					item.setBuyLimited(rs.getString("buyLimited")==null?"":rs.getString("buyLimited"));
					item.setIsYjLimited(rs.getString("isYjLimited")==null?"":rs.getString("isYjLimited"));
					item.setYhCon(rs.getString("yhCon")==null?"":rs.getString("yhCon"));
					item.setYhJzCon(rs.getString("yhJzCon")==null?"":rs.getString("yhJzCon"));
					item.setYhRatio(rs.getString("yhRatio")==null?"":rs.getString("yhRatio"));
					item.setGuRatio(rs.getString("guRatio")==null?"":rs.getString("guRatio"));
					list.add(item);
				}

			} catch (Exception e) {
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
		//其他收费项
		public List<TB_Estate_ChargeType> getQtTBItemType(String yznr,String ts_id,int pagesize,int pagenum,String a_id,String sfname) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			int num=(pagenum-1)*pagesize;
			List<TB_Estate_ChargeType> list=new ArrayList<TB_Estate_ChargeType>();
			StringBuffer sql= new StringBuffer();
			int count=0;
			  
				 sql.append("select top "+pagesize+" b.EsName,c.HtName,d.*,a.itemName,a.Es_id from TB_Estate_item a ,TB_Estate_Village b,TB_Estate_Housetype c,TB_Estate_ChargeType d where  " +
				 	"a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' and a.itemName not in("+sfname+") ");
				 if(!yznr.equals("")){
					 sql.append(" and b.Es_id in ("+yznr+") ");
				 }
				 
				 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 " +
				 				" and d.Ct_id  not in (select top "+num+" d.Ct_id  from TB_Estate_item a ," +
				 	"TB_Estate_Village b,TB_Estate_Housetype c,TB_Estate_ChargeType d   where  a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' and a.itemName not in("+sfname+") ");
				 if(!yznr.equals("")){
					 sql.append(" and b.Es_id in ("+yznr+") ");
				 }
				 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 order by b.EsName)  order by b.EsName  ");
				
				 if(yznr.equals("")){
					 count = getCount("TB_Estate_item a ,TB_Estate_Village b ,TB_Estate_Housetype c, TB_Estate_ChargeType d  ", " a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' " +
					 		"and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1  and a.itemName not in("+sfname+") ");
				 }else{
					 count = getCount("TB_Estate_item a ,TB_Estate_Village b,TB_Estate_Housetype c, TB_Estate_ChargeType d  ", " a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' " +
					 	" and b.Es_id in ("+yznr+") and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 and a.itemName not in("+sfname+")  ");
				 }
				 
			try {
				pu.init(sql.toString());
//				System.out.println(sql);
				rs=pu.Query();
				TB_Estate_ChargeType item=null;
				while(rs.next())
				{ 
					item=new TB_Estate_ChargeType();
					item.setCount(count);//总数据条数
					item.setEs_id(rs.getInt("es_id"));
					item.setEi_id(rs.getInt("ei_id"));
					item.setHt_id(rs.getInt("ht_id")); //房屋类型id
					item.setCt_id(rs.getInt("ct_id"));
					item.setCt_HtName(rs.getString("HtName")==null?"":rs.getString("HtName"));
					item.setCt_xiaoquName(rs.getString("EsName")==null?"":rs.getString("EsName"));
					item.setCt_itemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
					item.setCt_ItemType(rs.getString("Ct_itemType")); //收费方式
					item.setCt_price(rs.getString("Ct_price"));
					item.setCt_znjDay(rs.getString("Ct_znjDay")==null?"":rs.getString("Ct_znjDay"));
					item.setCt_znjRatio(rs.getString("Ct_znjRatio")==null?"":rs.getString("Ct_znjRatio"));
					item.setCt_remark(rs.getString("Ct_remark")==null?"":rs.getString("Ct_remark"));
					item.setCt_Limited(rs.getString("Ct_limited")==null?"":rs.getString("Ct_limited"));
					item.setCt_LimitNumber(rs.getString("Ct_limitNumber")==null?"":rs.getString("Ct_limitNumber"));
					item.setCt_BuyLimited(rs.getString("Ct_buyLimited")==null?"":rs.getString("Ct_buyLimited"));
					item.setCt_isYjLimited(rs.getString("Ct_isYjLimited")==null?"":rs.getString("Ct_isYjLimited"));
					item.setCt_yhCon(rs.getString("Ct_yhCon")==null?"":rs.getString("Ct_yhCon"));
					item.setCt_yhJzCon(rs.getString("Ct_yhJzCon")==null?"":rs.getString("Ct_yhJzCon"));
					item.setCt_yhRatio(rs.getString("Ct_yhRatio")==null?"":rs.getString("Ct_yhRatio"));
					item.setCt_guRatio(rs.getString("Ct_guRatio")==null?"":rs.getString("Ct_guRatio"));
					list.add(item);
				}

			} catch (Exception e) {
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
		//指定收费项
	public List<TB_Estate_ChargeType> getTBItemType(String yznr,String ts_id,int pagesize,int pagenum,String a_id,String sfname) throws Exception
		{
			PublicDBhandles pu=new PublicDBhandles();
			ResultSet rs = null;
			int num=(pagenum-1)*pagesize;
			List<TB_Estate_ChargeType> list=new ArrayList<TB_Estate_ChargeType>();
			StringBuffer sql= new StringBuffer();
			int count=0;
			  
			 sql.append("select top "+pagesize+" b.EsName,c.HtName,d.*,a.itemName,a.Es_id  from TB_Estate_item a ,TB_Estate_Village b,TB_Estate_Housetype c,TB_Estate_ChargeType d where  " +
			 	"a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' and a.itemName = '"+sfname+"' ");
			 if(!yznr.equals("")){
				 sql.append(" and b.Es_id in ("+yznr+") ");
			 }
			 
			 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1" +
			  " and d.Ct_id  not in (select top "+num+" d.Ct_id from TB_Estate_item a ," +
			 "TB_Estate_Village b ,TB_Estate_Housetype c,TB_Estate_ChargeType d where  a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id  and a.ts_id='"+ts_id+"' and a.itemName = '"+sfname+"' ");
			 if(!yznr.equals("")){
				 sql.append(" and b.Es_id in ("+yznr+") ");
			 }
			 sql.append("and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 order by b.EsName)  order by b.EsName  ");
			
			 if(yznr.equals("")){
				 count = getCount("TB_Estate_item a ,TB_Estate_Village b,TB_Estate_Housetype c,TB_Estate_ChargeType d  ", " a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' " +
				 		"and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 and a.itemName='"+sfname+"'  ");
			 }else{
				 count = getCount("TB_Estate_item a ,TB_Estate_Village b,TB_Estate_Housetype c,TB_Estate_ChargeType d  ", " a.Es_id=b.Es_id and d.Ei_id=a.Ei_id and d.Ht_id=c.Ht_id and a.ts_id='"+ts_id+"' " +
				 		" and b.Es_id in ("+yznr+") and a.a_id='"+a_id+"' and a.status=1 and b.status=1 and c.status=1 and d.status=1 and a.itemName = '"+sfname+"'   ");
			 }
			 
			try {
				pu.init(sql.toString());
				rs=pu.Query();
				TB_Estate_ChargeType item=null;
				while(rs.next())
				{ 
					item=new TB_Estate_ChargeType();
					item.setCount(count);//总数据条数
					item.setEi_id(rs.getInt("ei_id"));
					item.setHt_id(rs.getInt("ht_id")); //房屋类型id
					item.setCt_id(rs.getInt("ct_id"));
					item.setEs_id(rs.getInt("es_id"));
					item.setCt_HtName(rs.getString("HtName")==null?"":rs.getString("HtName"));
					item.setCt_xiaoquName(rs.getString("EsName")==null?"":rs.getString("EsName"));
					item.setCt_itemName(rs.getString("itemName")==null?"":rs.getString("itemName"));
					item.setCt_ItemType(rs.getString("Ct_itemType")); //收费方式
					item.setCt_price(rs.getString("Ct_price"));
					item.setCt_znjDay(rs.getString("Ct_znjDay")==null?"":rs.getString("Ct_znjDay"));
					item.setCt_znjRatio(rs.getString("Ct_znjRatio")==null?"":rs.getString("Ct_znjRatio"));
					item.setCt_remark(rs.getString("Ct_remark")==null?"":rs.getString("Ct_remark"));
					item.setCt_Limited(rs.getString("Ct_limited")==null?"":rs.getString("Ct_limited"));
					item.setCt_LimitNumber(rs.getString("Ct_limitNumber")==null?"":rs.getString("Ct_limitNumber"));
					item.setCt_BuyLimited(rs.getString("Ct_buyLimited")==null?"":rs.getString("Ct_buyLimited"));
					item.setCt_isYjLimited(rs.getString("Ct_isYjLimited")==null?"":rs.getString("Ct_isYjLimited"));
					item.setCt_yhCon(rs.getString("Ct_yhCon")==null?"":rs.getString("Ct_yhCon"));
					item.setCt_yhJzCon(rs.getString("Ct_yhJzCon")==null?"":rs.getString("Ct_yhJzCon"));
					item.setCt_yhRatio(rs.getString("Ct_yhRatio")==null?"":rs.getString("Ct_yhRatio"));
					item.setCt_guRatio(rs.getString("Ct_guRatio")==null?"":rs.getString("Ct_guRatio"));
					item.setHouseStandard(rs.getString("houseStandard")==null?"":rs.getString("houseStandard"));
					item.setBelowStandard(rs.getString("belowStandard")==null?"":rs.getString("belowStandard"));
					item.setAboveStandard(rs.getString("aboveStandard")==null?"":rs.getString("aboveStandard"));
					list.add(item);
				}

			} catch (Exception e) {
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
	 * 查询小区下某个收费项的信息
	 * @param Es_id
	 * @param itemName
	 * @param ts_id
	 * @return
	 */
	public TB_Estate_item getTBPay_Item_NameInfo(String Es_id ,String itemName,String ts_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		TB_Estate_item item=null;
		String sql="select Ei_id from TB_Estate_item where Es_id='"+Es_id+"' " +
				"and itemName='"+itemName+"' and status='1' and ts_id='"+ts_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				item=new TB_Estate_item();
				item.setEi_id(rs.getInt("Ei_id"));
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
		return item;
	}
	/**
	 * 添加各类缴费项目
	 * @param a_id
	 * @param tpi_name
	 * @param tpi_desc
	 * @return
	 */
	public boolean add_Item(String a_id,String ts_id,String xiaoqu,String sfname)
	{	
		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql =" insert into TB_Estate_item(a_id,ts_id,Es_id,itemName,status) "+
			" values ('"+a_id+"','"+ts_id+"','"+xiaoqu+"','"+sfname+"','1') ";
			
			PublicDBhandles.init(sql);
    		bl=PublicDBhandles.update();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return bl;
	}
	/**
	 * 查询子表中缴费项对应的房屋类型信息是否存在
	 */
	public int getTBPay_ChargeTypeInfo(String Ei_id,String Ht_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int bl=0;
		String sql="select count(*) count from TB_Estate_ChargeType where Ei_id='"+Ei_id+"' and status='1' and Ht_id='"+Ht_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				bl = rs.getInt("count");
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
		return bl;
	}
	/**
	 * 添加缴费项对应的房屋类型子表
	 * @return
	 */
	public boolean TB_Estate_ChargeType(String EhType,String Ei_id,String price,String sftype,String remark,String znjday,String zjnbl, 
			String Limited,String LimitNumber,String gdxz,String isyj,String yhCon,String yhJzCon,String yhRatio,String guRatio,String HouseStandard,String BelowStandard,String AboveStandard)
	{	
		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql =" insert into TB_Estate_ChargeType(Ht_id,Ei_id,Ct_price,Ct_ItemType,status,Ct_remark,Ct_znjDay,Ct_znjRatio,Ct_Limited,Ct_LimitNumber,Ct_BuyLimited,Ct_isYjLimited,Ct_yhCon,Ct_yhJzCon,Ct_yhRatio,Ct_guRatio,HouseStandard,BelowStandard,AboveStandard)" +
			" values ('"+EhType+"','"+Ei_id+"','"+price+"','"+sftype+"','1','"+remark+"'," +
			"'"+znjday+"','"+zjnbl+"','"+Limited+"','"+LimitNumber+"','"+gdxz+"','"+isyj+"','"+yhCon+"','"+yhJzCon+"','"+yhRatio+"','"+guRatio+"','"+HouseStandard+"','"+BelowStandard+"','"+AboveStandard+"') ";
			
			PublicDBhandles.init(sql);
    		bl=PublicDBhandles.update();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return bl;
	}
	/**
	  修改缴费项子表
	 * @return
	 * @throws Exception
	 */
	public boolean updateChargeType(String EhType,String gdxz,String isyj,String  LimitNumber,String Limited,String Ei_id,String Ct_id,String price,String znjday,
			String znjbl,String status,String shoufeifs,String yhCon,String yhJzCon,String yhRatio,String guRatio,String remark,String HouseStandard,String BelowStandard,String AboveStandard) throws Exception
	{			
		PublicDBhandle PublicDBhandle=new PublicDBhandle();
		boolean bl=false;
		List<String> addTBMonitor = new ArrayList<String>();
		try {
		String sql="update TB_Estate_ChargeType set Ht_id='"+EhType+"', Ct_BuyLimited='"+gdxz+"',Ct_isYjLimited='"+isyj+"',Ct_yhCon='"+yhCon+"',Ct_yhJzCon='"+yhJzCon+"',Ct_yhRatio='"+yhRatio+"',Ct_LimitNumber='"+LimitNumber+"',Ct_Limited='"+Limited+"'," +
	"Ct_price='"+price+"',Ct_znjDay='"+znjday+"',Ct_znjRatio='"+znjbl+"',Ct_guRatio='"+guRatio+"',status='"+status+"',Ct_ItemType='"+shoufeifs+"',Ct_remark='"+remark+"',HouseStandard='"+HouseStandard+"', BelowStandard='"+BelowStandard+"',AboveStandard='"+AboveStandard+"' where Ei_id='"+Ei_id+"' and Ct_id='"+Ct_id+"' and status=1";
			//System.out.println(sql);
			addTBMonitor.add(sql);
  		String str=PublicDBhandle.eUpdatecommitbl(addTBMonitor);
  		
  		if(str.equals("")){
  			bl=true;
  		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				PublicDBhandle.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return bl;
	}
	/**
	 * 查询子表中缴费项对应的房屋类型信息是否存在
	 */
	public int getTB_ChargeTypeInfo(String Ei_id,String Ht_id,String Ct_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int bl=0;
		String sql="select count(*) count from TB_Estate_ChargeType where Ei_id='"+Ei_id+"' and status='1' and Ht_id='"+Ht_id+"' and Ct_id <>'"+Ct_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				bl = rs.getInt("count");
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
		return bl;
	}
	/**
	 * 查询缴费项名称是否存在
	 */
	public int getTB_Estate_itemInfo(String Es_id,String name,String ts_id,String Ei_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int bl=0;
		String sql="select count(*) count from TB_Estate_item where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and status='1' and itemName='"+name+"' and Ei_id <>'"+Ei_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				bl = rs.getInt("count");
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
		return bl;
	}
	/**
	 * 修改收费项名称
	 * @return
	 */
	public boolean TB_Estate_itemUp(String Es_id,String name,String ts_id,String Ei_id)
	{	
		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql ="update TB_Estate_item set itemName='"+name+"' where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and Ei_id='"+Ei_id+"'";
			PublicDBhandles.init(sql);
    		bl=PublicDBhandles.update();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return bl;
	}
	/**
     * 查询某个收费项对应子表信息
     * @param Ei_id
     * @return
     */
    public List<TB_Estate_ChargeType> findChargeType_itemByid(String Ei_id) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_ChargeType> list = new ArrayList<TB_Estate_ChargeType>();
        ResultSet rs = null;
        TB_Estate_ChargeType tei = null;
        String sql = "select b.itemName,a.Ei_id,a.Ct_price,a.Ct_ItemType,a.Ht_id from  TB_Estate_ChargeType a left join  TB_Estate_item b on a.Ei_id=b.Ei_id where " +
        		" b.Ei_id='"+Ei_id+"' and a.status='1' and b.status='1' order by a.Ht_id";
        try {
            pu.init(sql);
            rs = pu.Query();
            while (rs.next()) {
                tei = new TB_Estate_ChargeType();
                tei.setEi_id(rs.getInt("Ei_id"));
                tei.setCt_itemName(rs.getString("itemName"));
                tei.setCt_ItemType(rs.getString("Ct_ItemType"));
                tei.setCt_price(rs.getString("Ct_price"));
                tei.setHt_id(rs.getInt("Ht_id"));
                list.add(tei);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
            	if(rs!=null){
					rs.close();
				}
				pu.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
    /**
	 * 查询子表中车位费缴费项信息
	 */
	public int getTBPay_ChargeTypeInfoCarDiti(String Ei_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int bl=0;
		String sql="select count(*) count from TB_Estate_ChargeType where Ei_id='"+Ei_id+"' and status='1' ";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				bl = rs.getInt("count");
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
		return bl;
	}
	/**
	 * 查询小区电梯费楼层区间是否设置过
	 * @param es_id
	 * @param ei_id
	 * @return
	 */
	public int selDitiqj(String es_id,String ei_id){
		int num=0;
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String sql="select count(se_id) count from TB_Estate_DTFSection where Ei_id='"+ei_id+"' and status='1' and Es_id='"+es_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				num = rs.getInt("count");
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
		return num;
	}
    /**
     * 查询电梯费楼层区间
     * @param Es_id
     * @param Ei_id
     * @return
     */
	public List<TB_Estate_ChargeType> getTB_Estate_DTFSection(int Es_id,int Ei_id){
		List<TB_Estate_ChargeType> list=new ArrayList<TB_Estate_ChargeType>();
		TB_Estate_ChargeType type=null;
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String sql="select * from TB_Estate_DTFSection where Es_id='"+Es_id+"' and Ei_id='"+Ei_id+"' and status=1";
		pu.init(sql);
		rs=pu.Query();
		try {
			while(rs.next()){
				type=new TB_Estate_ChargeType();
				String qhzPrice=rs.getString("se_qz")+"-"+rs.getString("se_hz")+"#"+S_string.DecimalFormat_string( rs.getString("Seprice"),2);
				type.setQj(qhzPrice);
				type.setSe_id(rs.getString("se_id"));
				list.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
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
	 * 查询缴费项名称是否存在
	 */
	public String getTB_Estate_itemPrice(String Es_id,String ts_id,String Ei_id,String lc)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String price = "0.00";
		String sql="select Seprice from TB_Estate_DTFSection where se_qz<='"+lc+"' and se_hz >='"+lc+"' and ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and Ei_id = '"+Ei_id+"' and status=1 ";
		
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				price = rs.getString("Seprice");
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
		return price;
	}
}
