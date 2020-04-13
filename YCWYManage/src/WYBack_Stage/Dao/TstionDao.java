package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import WYBack_Stage.Bean.Bchaxun;
import WYBack_Stage.Bean.TB_Estate_Order;
import WYCommunity.T_time;


import ccbjf.system.db.PublicDBhandles;

public class TstionDao {
	
	/**
	 * 通过商户ID得到（建行的商户代码）
	 * @param ts_id
	 * @return
	 */
	public String getshdm(String ts_id)
	{	
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String str="";
		String sql="select shdm from TB_SEV where ts_id='"+ts_id+"' ";

		try {
			pu.init(sql.toString());
			rs=pu.Query();
			if(rs.next())
			{
				str=rs.getString(1);
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
	 * 通过商户ID得到该商户所有的柜台代码并且拼接成串，用于商户对账时根据商户柜台条件检索，如: gtdm in('1','2')
	 * @param ts_id
	 * @return
	 * *2015年3月5日修改银行后台同一个商户编号可以添加成多个商户增加功能，并且一个机构对应了一个柜台号，所以要根据柜台进行对账查询
	 *（如商户编号105130173720007，有多个机构按多商户添加时可以带后缀区分，如：105130173720007-1，105130173720007-2的形式命名商户编号）
	 * 所以在商户
	 */
	public String getgtdmall(String ts_id)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String gtdm="";
		try {
			String sql ="select gtdm from TB_SEV_CCBBank where ts_id='"+ts_id+"' and status='1' ";

			pu.init(sql.toString());
			rs=pu.Query();
			while(rs.next())
			{
				String str=rs.getString("gtdm");
				gtdm=gtdm+"'"+str+"',";
			}
			gtdm=gtdm.substring(0, gtdm.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		return gtdm;
	}
	
	/***********************修改后的系统网上实缴笔数和金额、退款笔数和金额及现金实缴金额和笔数**********************************************************/
	/**
	 * 根据查询条件得到网上、现金实缴笔数和实缴金额
	 * @param Condition
	 * @return
	 * @author yf
	 */
	public TB_Estate_Order getcondition1(Map<String, String> Condition)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
        StringBuffer sql = new StringBuffer("select sum(case when payType='0' and payStatus=1 then " +
        		"total_sj end) sjje,count(case when payType='0' and payStatus=1 then Eo_id end) sjbishu," +
        		"sum(case when payType='1' and payStatus ='1' then total_sj end) xsjje," +
        		"count(case when payType='1' and payStatus ='1' then Eo_id end) xsjbishu from TB_Estate_Order a where ");
        TB_Estate_Order org =null;
        try {
            String temptimes  = Condition.get("temptimes");
            String ts_id = Condition.get("ts_id");
            
            sql.append(" pay_time>='"+temptimes+" 00:00:00' and pay_time<='"+temptimes+" 23:59:59'    and ts_id='"+ts_id+"' ");
          
            pu.init(sql.toString());
			rs=pu.Query();
			
			while(rs.next())
			{
				org=new TB_Estate_Order();
				org.setSjje(rs.getFloat("sjje")+"");
		        org.setSjbishu(rs.getInt("sjbishu")+"");
		        org.setXsjje(rs.getFloat("xsjje")+"");
			    org.setXsjbishu(rs.getInt("xsjbishu")+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}				
		return org;
	}
	/**
	 * 根据查询条件得到网上退款笔数和退款金额
	 * @param Condition
	 * @return
	 * @author yf
	 */
	public TB_Estate_Order getcondition2(Map<String, String> Condition)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		TB_Estate_Order org =null;
		StringBuffer sql = new StringBuffer("select sum(tk_total) tkje,count(*) tkbishu from TB_Estate_Order a where ");
        try {
            String temptimes  = Condition.get("temptimes");
            String ts_id = Condition.get("ts_id");
           
            sql.append(" payType='0' and payStatus in(3,4) and " +
            		"tk_time>='"+temptimes+" 00:00:00' and tk_time<='"+temptimes+" 23:59:59'  and ts_id='"+ts_id+"' ");
          
            pu.init(sql.toString());
			rs=pu.Query();
			
			while(rs.next())
			{
				org=new TB_Estate_Order();
            	
		        org.setTkbishu(rs.getInt("tkbishu")+"");
		        org.setTkje(rs.getFloat("tkje")+"");
	
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}				
		return org;
	}
	/**
	 * 根据查询条件得到本系统网上(线上网上支付、线下主扫静态二维码、线下被扫指扫码盒子)、现金实缴笔数和实缴金额
	 * @param Condition
	 * @return
	 * @author yf
	 */
	public TB_Estate_Order getcondition3(Map<String, String> Condition)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
       StringBuffer sql = new StringBuffer("select sum(case when payType='0' and payStatus=1 then total_sj end) xwsje, " +
        		"count(case when payType='0' and payStatus=1 then Eo_id end) xwsbishu," +
        		"sum(case when payType='2' and payStatus=1 then total_sj end) xbsje, "+
        		"count(case when payType='2' and payStatus=1 then Eo_id end) xbsbishu," +
        		"sum(case when payType='6' and payStatus=1 then total_sj end) xzsje, "+
        		"count(case when payType='6' and payStatus=1 then Eo_id end) xzsbishu," +
        		"sum(case when payType='1' and payStatus ='1' then total_sj end) xsjje," +
        		"count(case when payType='1' and payStatus ='1' then Eo_id end) xsjbishu from TB_Estate_Order  where ");
        TB_Estate_Order org =null;
        try {
            String temptimes  = Condition.get("temptimes");
            String ts_id = Condition.get("ts_id");
            String Es_id = Condition.get("Es_id");
            String sfy=Condition.get("sfy");
            sql.append(" pay_time>='"+temptimes+" 00:00:00' and pay_time<='"+temptimes+" 23:59:59'  and ts_id='"+ts_id+"' and  Es_id='"+Es_id+"'");
            if(!"".equals(sfy)){
            	sql.append("  and tu_id='"+sfy+"' ");
            }
            pu.init(sql.toString());
			rs=pu.Query();
			while(rs.next())
			{
				org=new TB_Estate_Order();
				org.setXwsje(rs.getFloat("xwsje")+"");
				org.setXwsbishu(rs.getInt("xwsbishu")+"");
				org.setXbsje(rs.getFloat("xbsje")+"");
				org.setXbsbishu(rs.getInt("xbsbishu")+"");
		        org.setXsjje(rs.getFloat("xsjje")+"");
			    org.setXsjbishu(rs.getInt("xsjbishu")+"");
			    org.setXzsje(rs.getFloat("xzsje")+"");
			    org.setXzsbishu(rs.getInt("xzsbishu")+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}				
		return org;
	}
	/**
	 * 联合统一支付表查询 建行支付金额和笔数 （对应系统网上支付和被扫支付）
	 * @param Condition
	 * @return
	 * @author yf
	 */
	public TB_Estate_Order getcondition4(Map<String, String> Condition)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
       StringBuffer sql = new StringBuffer(" select SUM(b.total_sj) as jhje,COUNT(b.Eo_id) as jbishu  from TBOrders_ccbjfpay a inner join TB_Estate_Order b on a.orderid=b.bankid " +
       		" where a.status=1 and a.pay_status=1  and ");
        TB_Estate_Order org =null;
        try {
            String temptimes  = Condition.get("temptimes");
            String ts_id = Condition.get("ts_id");
            String Es_id = Condition.get("Es_id");
            String sfy=Condition.get("sfy");
            sql.append(" a.pay_time>='"+temptimes+" 00:00:00' and a.pay_time<='"+temptimes+" 23:59:59'  and a.ts_id='"+ts_id+"' and b.ts_id='"+ts_id+"' and  b.Es_id='"+Es_id+"'");
            if(!"".equals(sfy)){
            	sql.append("  and tu_id='"+sfy+"' ");
            }
            pu.init(sql.toString());
			rs=pu.Query();
			while(rs.next())
			{
				org=new TB_Estate_Order();
				org.setJhje(rs.getFloat("jhje")+"");
				org.setJbishu(rs.getInt("jbishu")+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}				
		return org;
	}
   /**
    * 统一支付流水
    * @param pagesize
    * @param pagenum
    * @param Condition
    * @return
    */
	public List<TB_Estate_Order> getBanid(int pagesize,int pagenum,String Condition){
		List<TB_Estate_Order> list=new ArrayList<TB_Estate_Order>();
		TB_Estate_Order order=null;
		int num=(pagenum-1)*pagesize;
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		String sql="select top "+pagesize+" b.Eo_id,b.bankid,b.total_sj,b.pay_time  from TBOrders_ccbjfpay a inner join TB_Estate_Order b on a.orderid=b.bankid  where " +
		" b.Eo_id not in (select top "+num+" b.Eo_id from TBOrders_ccbjfpay a inner join TB_Estate_Order b on a.orderid=b.bankid where "+Condition+" order by a.pay_time  desc)  and "+Condition+" order by a.pay_time desc ";
		pu.init(sql);
		rs=pu.Query();
		try {
			while(rs.next()){
				order=new TB_Estate_Order();
				order.setEo_id(rs.getInt("Eo_id"));
				order.setBankid(rs.getString("bankid"));
				order.setTotal_sj(rs.getString("total_sj"));
				order.setPay_time(rs.getDate("pay_time")+" "+rs.getTime("pay_time"));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(rs!=null){
				rs.close();
			}
			pu.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
/******************************************************************************************************/
	
	/***********************修改后的建行网上实缴笔数和金额、退款笔数和金额********************************************/
	/**
	 * 根据查询条件得到实缴笔数与实缴金额、退款笔数和金额
	 * @param Condition
	 * @return
	 * @author yf
	 */
	public Bchaxun getcondition(Map<String, String> Condition)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select sum(case when TYPE='0' then AMOUNT else 0 end) sjje,count(case when TYPE='0' then TBWID  end) sjbishu,sum(case when TYPE='1' then REFUND else 0 end) tkje,count(case when TYPE='1' then TBWID  end) tkbishu from TB_Bank_water where ");
		Bchaxun bcx =null;
        try {
            String temptimes  = Condition.get("temptimes");
            String shdm = Condition.get("shdm");
            String gtdm = Condition.get("gtdm");
           
            sql.append(" ORDERDATE>='"+temptimes+" 00:00:00' and ORDERDATE<='"+temptimes+" 23:59:59' and MERCHANTID='"+shdm+"' and POSID in("+gtdm+") ");
          
            pu.init(sql.toString());
			rs=pu.Query();
			
			while(rs.next())
			{
				bcx=new Bchaxun();
				bcx.setSjje(rs.getFloat("sjje")+"");
				bcx.setSjbishu((rs.getInt("sjbishu")+"")==null?"0":(rs.getInt("sjbishu")+""));
				bcx.setTkje(rs.getFloat("tkje")+"");
				bcx.setTkbishu((rs.getInt("tkbishu")+"")==null?"0":(rs.getInt("tkbishu")+""));
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
        return bcx;
   }
	/******************************************************************************************************/
	
	public List<Bchaxun> getBchaxunPage(int pagesize,int pagenum,String Condition)
	{
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		List<Bchaxun> list=new ArrayList<Bchaxun>();	
		int num=(pagenum-1)*pagesize;
		String sql="select top "+pagesize+" * from TB_Bank_water where TBWID not in(select top "+num+" TBWID from " +
				"TB_Bank_water where "+Condition+" order by ORDERDATE desc) and "+Condition+" order by ORDERDATE desc ";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			Bchaxun bc=null;
			while(rs.next()) 
			{
				bc=new Bchaxun();
				bc.setACCDATE(new T_time().getStringTimeymd(rs.getInt("ACCDATE")));
				bc.setAMOUNT(rs.getFloat("AMOUNT")+"");
				bc.setBRANCHID(rs.getString("BRANCHID"));
				bc.setMERCHANTID(rs.getString("MERCHANTID"));
				bc.setORDERDATE(rs.getDate("ORDERDATE")+" "+rs.getTime("ORDERDATE"));
				bc.setORDERID(rs.getString("ORDERID"));
				bc.setPOSID(rs.getString("POSID"));
				bc.setREFUND(rs.getFloat("REFUND")+"");
				bc.setSTATUS(rs.getString("STATUS"));
				bc.setSTATUSCODE(rs.getString("STATUSCODE"));
				bc.setTBWID(rs.getString("TBWID"));
				bc.setTYPE(rs.getString("TYPE"));
				
				list.add(bc);
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

}
