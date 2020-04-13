package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import ccbjf.system.db.PublicDBhandles;
import WYBack_Stage.Bean.TB_Estate_Village;
import WYBack_Stage.Bean.TB_Estate_paper;

public class TB_Estate_paperDao {
	
	
	//添加票据
	public boolean addPaper(TB_Estate_paper paper){
		
		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql ="insert into TB_Estate_paper(es_id,bankid,paper_num,create_time,status,print_status) " +
					"values("+paper.getEs_id()+",'"+paper.getBankid()+"',"+paper.getPaper_num()+",'"+paper.getCreate_time()+"',"+paper.getStatus()+","+paper.getPrint_status()+")";
			
			PublicDBhandles.init(sql);
    		bl=PublicDBhandles.update();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return bl;
	}
	
	//根据小区id和物业订单id查询票据情况
	public TB_Estate_paper selectPaperByEsIdAndEoId(int es_id,String bankid){
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		TB_Estate_paper paper = null;
		String sql="SELECT * FROM TB_Estate_paper where bankid = '"+bankid+"' and es_id = "+es_id+"and status = 1";
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	paper = new TB_Estate_paper();
			if(rs.next()) 
			{
				paper.setEs_id(rs.getInt("es_id"));
				paper.setBankid(rs.getString("bankid"));
				paper.setPaper_num(rs.getInt("paper_num"));
				paper.setPrint_status(rs.getInt("print_status"));
			}else{
				paper.setPrint_status(2);
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
		
		
		return paper;
	}
	
	//根据小区id和物业订单id修改打印状态
	public boolean update(int status, int print_status,int es_id,String bankid){

		PublicDBhandles PublicDBhandles=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql = "";
			sql ="update TB_Estate_paper set status = "+status+" , print_status = "+print_status+" where bankid = '"+bankid+"' and status = 1 and es_id = "+es_id+"";
			PublicDBhandles.init(sql);
    		bl=PublicDBhandles.update();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return bl;
	}
	
	//根据物业订单表ID查询小区ID
	public int selectXiaoQID(int orderId){
		
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int qv = 0 ;
		String sql="select Es_id from TB_Estate_Order where Eo_id = '"+orderId+"' and status = 1";
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			if(rs.next()){
				qv = rs.getInt("Es_id");
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
		return qv;
	}
	
	
	//计算当前小区内得票据单号
	public int paperSum(int es_id){
		int sum = 0;
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		
		String sql="select top 1 paper_num from TB_Estate_paper where es_id = '"+es_id+"' ORDER BY create_time DESC";
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			if(rs.next()){
				sum = rs.getInt("paper_num");
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
		
		return sum+1;
	}
	
	//查询该订单是不是合并支付订单
	public int selectIssumOrder(String backId){
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs = null;
		int sum = 0;
		String sql="select count(Eo_id) number from TB_Estate_Order where bankid = '"+backId+"' ";
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			if(rs.next()){
				sum = rs.getInt("number");
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
		
		return sum;
	}
	
	//当从合并打印跳转过来时，根据bankid关联物业订单表和票据表是否有数据
	public Map<String,Object>  selectPidandoaoerNum(String bankid){
		
		String sql = "select a.pa_id pa_id,a.paper_num paper_num,a.es_id es_id,a.bankid bankid, a.print_status print_status " +
				"from TB_Estate_paper a  INNER JOIN TB_Estate_Order b  on a.bankid = b.bankid where a.status = 1 and b.bankid = '"+bankid+"'";
		PublicDBhandles pu=new PublicDBhandles();
		Map<String,Object> map = new HashedMap();
		ResultSet rs = null;
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			if(rs.next()){
				map.put("status",1);
				map.put("pa_id", rs.getInt("pa_id"));
				map.put("paper_num", rs.getInt("paper_num"));
				map.put("bankid", rs.getString("bankid"));
				map.put("es_id", rs.getInt("es_id"));
				map.put("print_status", rs.getInt("print_status"));
			}else{
				map.put("status", 0);
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
		return map;	
		
	}
	
	public int selectEO_IDbyBankid(String bankid){
		int Eo_id = 0;
		String sql = "select Eo_id  " +"from  TB_Estate_Order  where bankid = '"+bankid+"'";
		PublicDBhandles pu=new PublicDBhandles();
		Map<String,Integer> map = new HashedMap();
		ResultSet rs = null;
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			if(rs.next()){
				Eo_id = rs.getInt("Eo_id");
			}else{
				map.put("status", 0);
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
		return Eo_id;
	}

	
	//根据bankid 和 小区id  和 打印状态 1 查询出该订单得id
	public int selectIDbyBankidAndEsIdAndPrintStatus(String bankid , int es_id , int printStatus){
		int po_id = 0;
		String sql = "select pa_id  " +"from  TB_Estate_paper  where bankid = '"+bankid+"' and es_id = "+es_id+" and print_status = 1";
		PublicDBhandles pu=new PublicDBhandles();
		Map<String,Integer> map = new HashedMap();
		ResultSet rs = null;
		try {
			pu.init(sql.toString());
        	rs=pu.Query();
        	TB_Estate_Village tev=null;
			if(rs.next()){
				po_id = rs.getInt("pa_id");
			}else{
				map.put("status", 0);
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
		
		return po_id;
	}

}
