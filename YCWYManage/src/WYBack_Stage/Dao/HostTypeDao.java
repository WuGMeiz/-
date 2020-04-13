package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ccbjf.system.db.PublicDBhandles;
import WYBack_Stage.Bean.TB_Estate_Housetype;

public class HostTypeDao {
	
	/**
	   * 验证是否有有相同人员
	   * @param Es_id
	   * @param ReName
	   * @param phone
	   * @return
	   */
	  public boolean checkhostType(String HtName,String remark1){
		  boolean bl = false;
	      ResultSet rs = null;
	      PublicDBhandles pu = new PublicDBhandles();
	      String sql="select HtName from TB_Estate_Housetype where  HtName = '"+HtName+"' and  remark1 = '"+remark1+"' and status = 1";
	      try {
	          pu.init(sql);
	          rs = pu.Query();
	          if (rs.next()) {
	              bl = true;
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
	      return bl;
	  }
	
	
	//添加房屋类型
	public boolean addAll(TB_Estate_Housetype houseType){
		 PublicDBhandles PublicDBhandles = new PublicDBhandles();
		 boolean bl  = false ;
		try{
		String sql = "insert into TB_Estate_Housetype(HtName,create_time,status,remark1) VALUES('"+houseType.getHtName()+"','"+houseType.getCreat_time()+"',"+houseType.getStatus()+",'"+houseType.getRemark1()+"')";
		  PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      } 
		
		return bl;
	}
	
	
	/**
     * 用户查询 ，查询符合条件的房屋类型信息
     * 
     * @param pagesize
     * @param pagenum
     * @return
     * @throws Exception
     */
    public List<TB_Estate_Housetype> getTB_HouseType( int pagesize, int pagenum, String condition ) throws Exception {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_Housetype> list = new ArrayList<TB_Estate_Housetype>();
        int num = (pagenum - 1) * pagesize;
        String sql = "select top " + pagesize + " h.* ,v.EsName from TB_Estate_Housetype h inner join TB_Estate_Village v on h.remark1=v.Es_id  where h.Ht_id not in (select top " + num
                + "  Ht_id from TB_Estate_Housetype h inner join TB_Estate_Village v on h.remark1=v.Es_id  where" + condition + " order by h.Ht_id desc) and " + condition + " order by h.Ht_id desc";
        
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Housetype houseType=null;
            while (rs.next()) {
            	houseType = new TB_Estate_Housetype();
            	houseType.setHt_id(rs.getInt("Ht_id"));
            	houseType.setHtName(rs.getString("HtName") == null ? "" : rs.getString("HtName"));
            	houseType.setCreat_time(rs.getString("create_time") == null ? "" : rs.getString("create_time") );
            	houseType.setRemark1(rs.getString("remark1"));
            	houseType.setRemark2(rs.getString("remark2") == null ? "" : rs.getString("remark2"));
            	houseType.setEsName(rs.getString("EsName") == null ? "" : rs.getString("EsName"));
                list.add(houseType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return list;
    }
    
    public boolean updataHostType(TB_Estate_Housetype hoseType){
    	
    	 PublicDBhandles PublicDBhandles = new PublicDBhandles();
		 boolean bl  = false ;
		try{
		String sql = " update TB_Estate_Housetype set HtName = '"+hoseType.getHtName()+"' , remark2 = '"+hoseType.getRemark2()+"' where Ht_id = "+hoseType.getHt_id()+" ";
		  PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      } 
      
		
		return bl;
    	
    	
    }
	

}
