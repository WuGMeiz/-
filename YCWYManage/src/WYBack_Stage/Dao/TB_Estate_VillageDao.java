package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_Village;

import ccbjf.system.db.PublicDBhandles;

public class TB_Estate_VillageDao {
    /**
     * 通过商户ID得到用户账号级别
     * @param u_id
     * @return
     */
    public String getyznr(String es_id,String ts_id)
    {

        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        String name="";
        String sql;     
        sql="select yznr from TB_Estate_Village where  " +
        		" Es_id='"+es_id+"' and ts_id='"+ts_id+"' and status=1 ";
        try {
            pu.init(sql.toString());
            rs=pu.Query();
            if(rs.next())
            {
                name=rs.getString("yznr");
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
    public boolean updateEstate(String es_id, String yznr) {
        PublicDBhandles PublicDBhandles=new PublicDBhandles();
        boolean bl=false;
        
        String sql = "update TB_Estate_Village set yznr='" + yznr
                + "' where Es_id='" + es_id + "'";
        try {
            PublicDBhandles.init(sql);
            bl=PublicDBhandles.update();
        }  catch (Exception e) {
            e.printStackTrace();
        }  
        return bl;
    }
    /**
     * 根据ts_id查询小区信息
     * 
     * @param ts_id
     * @author 
     * 
     */
    public List<TB_Estate_Village> select_xiaoqu(String ts_id) {
        ResultSet rs = null;
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_Village> list = new ArrayList<TB_Estate_Village>();
        String sql = "select Es_id, EsName from TB_Estate_Village where" +
        		" ts_id='" + ts_id + "' and status='1'";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Village village =null;
            while (rs.next()) {
                village = new TB_Estate_Village();
                village.setEs_id(rs.getInt("Es_id"));
                village.setEsName(rs.getString("EsName"));
                list.add(village);
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
        return list;
    }
   
}
