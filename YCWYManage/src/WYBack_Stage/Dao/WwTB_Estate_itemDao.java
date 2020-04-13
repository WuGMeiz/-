package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_ChargeType;
import WYBack_Stage.Bean.TB_Estate_item;
import ccbjf.system.db.PublicDBhandles;


public class WwTB_Estate_itemDao {
    /**
     * 获取缴费项
     * @param es_id
     * @param ts_id
     * @return
     */
    public List<TB_Estate_item> selectitem(String es_id,String ts_id){
        PublicDBhandles pu=new PublicDBhandles();
        List<TB_Estate_item>  list = new ArrayList<TB_Estate_item>();
        ResultSet rs=null;
        TB_Estate_item te=null;
        try {
        String sql="select * from TB_Estate_item where Es_id='"+es_id+"' and ts_id='"+ts_id+"' and itemName in ('电费','燃气费') and status='1'";
        pu.init(sql);
        rs=pu.Query();
            while(rs.next()){
               te=new TB_Estate_item();
               te.setEi_id(rs.getInt("ei_id"));
               te.setItemName(rs.getString("itemName"));
               te.setPrice(rs.getString("price"));
               te.setLimited(rs.getString("limited"));
               te.setLimitNumber(rs.getString("limitNumber"));
               te.setBuyLimited(rs.getString("buyLimited"));
               te.setIsYjLimited(rs.getString("isYjLimited"));
               list.add(te);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
     * 获取可预缴缴费项
     * @param es_id
     * @param ts_id
     * @return
     */
    public List<TB_Estate_item> selectitem1(String es_id,String ts_id){
        PublicDBhandles pu=new PublicDBhandles();
        List<TB_Estate_item>  list = new ArrayList<TB_Estate_item>();
        ResultSet rs=null;
        TB_Estate_item te=null;
        try {
	    //  String sql="select * from TB_Estate_item where Es_id='"+es_id+"'and isYjLimited=1  and ts_id='"+ts_id+"' and status='1'";
        	String 	sql="select distinct  a.Ei_id,a.itemName from TB_Estate_item a right join TB_Estate_ChargeType b on a.Ei_id=b.Ei_id where ts_id='"+ts_id+"' " +
				"and Es_id='"+es_id+"' and a.status=1 and b.status=1 and b.Ct_isYjLimited=1  ";  
	        pu.init(sql);
	        rs=pu.Query();
            while(rs.next()){
               te=new TB_Estate_item();
               te.setEi_id(rs.getInt("ei_id"));
               te.setItemName(rs.getString("itemName"));
              /* te.setPrice(rs.getString("price"));
               te.setLimited(rs.getString("limited"));
               te.setLimitNumber(rs.getString("limitNumber")==null?"":rs.getString("limitNumber"));
               te.setBuyLimited(rs.getString("buyLimited"));
               te.setIsYjLimited(rs.getString("isYjLimited"));*/
               list.add(te);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
     * 获取用户缴费项子表信息
     * @param es_id
     * @param ts_id
     * @return
     */
    public List<TB_Estate_ChargeType> selectitemType(String es_id,String ts_id,String EhNumber,String Bu_id){
        PublicDBhandles pu=new PublicDBhandles();
        List<TB_Estate_ChargeType>  list = new ArrayList<TB_Estate_ChargeType>();
        ResultSet rs=null;
        TB_Estate_ChargeType te=null;
        try {
	        String sql="select a.itemName,b.* from TB_Estate_ChargeType b inner join TB_Estate_item a  on a.Ei_id=b.Ei_id  where a.ts_id='"+ts_id+"' and a.Es_id='"+es_id+"' and a.status=1 and b.status=1 " +
	        "and b.Ct_isYjLimited=1 and b.Ht_id=(select EhType from TB_Estate_House  where ts_id='"+ts_id+"' and Es_id='"+es_id+"'  and EhNumber='"+EhNumber+"' and status=1)";
	      
	        pu.init(sql);
	        rs=pu.Query();
            while(rs.next()){
               te=new TB_Estate_ChargeType();
               te.setCt_id(rs.getInt("Ct_id"));
               te.setEi_id(rs.getInt("ei_id"));
               te.setCt_itemName(rs.getString("itemName"));
               te.setCt_price(rs.getString("Ct_price"));
               te.setCt_Limited(rs.getString("Ct_Limited"));
               te.setCt_LimitNumber(rs.getString("Ct_LimitNumber")==null?"":rs.getString("Ct_LimitNumber"));
               te.setCt_BuyLimited(rs.getString("Ct_BuyLimited"));
               te.setCt_isYjLimited(rs.getString("Ct_isYjLimited"));
               String houseStandard = rs.getString("houseStandard")==null?"0":rs.getString("houseStandard");
               String aboveStandard = rs.getString("aboveStandard")==null?"0":rs.getString("aboveStandard");
               String belowStandard = rs.getString("belowStandard")==null?"0":rs.getString("belowStandard");
               
               te.setHouseStandard("".equals(houseStandard)?"0":houseStandard);
               te.setBelowStandard("".equals(belowStandard)?"0":belowStandard);
               te.setAboveStandard("".equals(aboveStandard)?"0":aboveStandard);
               list.add(te);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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

