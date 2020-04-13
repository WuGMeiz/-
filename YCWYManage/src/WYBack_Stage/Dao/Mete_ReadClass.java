package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WYBack_Stage.Bean.TB_Estate_Build;
import WYBack_Stage.Bean.TB_Estate_ChargeType;
import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Bean.TB_Estate_Mation;
import WYBack_Stage.Bean.TB_Estate_Village;
import WYBack_Stage.Bean.TB_Estate_item;
import WYBack_Stage.Bean.TB_HMBean;
import WYCommunity.T_time;
import ccbjf.system.db.PublicDBhandles;

/**
 * 水费抄表类
 * 
 * @author admin
 * 
 */
public class Mete_ReadClass {
    /**
     * 查询某商户下全部小区信息
     * 
     * @param ts_id
     * @return
     */
    public List<TB_Estate_Village> findxiaoqu(String ts_id, String strEs_id) {
        List<TB_Estate_Village> list = new ArrayList<TB_Estate_Village>();
        PublicDBhandles pu = new PublicDBhandles();
        TB_Estate_Village tev = null;
        ResultSet rs = null;
        String sql  = "select *  from TB_Estate_Village where ts_id=? and status=? and Es_id in (" + strEs_id + ")";
        try {
            pu.init(sql);
            pu.setString(1, ts_id);
            pu.setInt(2, 1);
            rs = pu.Query();
            while (rs.next()) {
                tev = new TB_Estate_Village();
                tev.setEs_id(rs.getInt("es_id"));
                tev.setTs_id(rs.getInt("ts_id"));
                tev.setEsName(rs.getString("EsName"));
                tev.setEsHead(rs.getString("EsHead"));
                tev.setEsContact(rs.getString("EsContact"));
                tev.setEsPhone(rs.getString("EsPhone"));
                tev.setEsAddress(rs.getString("EsAddress"));
                tev.setBuildArea(rs.getString("BuildArea"));
                tev.setFloorArea(rs.getString("FloorArea"));
                tev.setGreenArea(rs.getString("GreenArea"));
                tev.setVolumeRate(rs.getString("VolumeRate"));
                tev.setBuild_Number(rs.getInt("build_Number"));
                tev.setHouse_Number(rs.getInt("House_Number"));
                tev.setCreat_time(rs.getString("creat_time"));
                tev.setYznr(rs.getString("yznr"));
                tev.setRemark(rs.getString("remark"));
                tev.setTscb_id(rs.getString("tscb_id"));
                tev.setStatus(rs.getInt("status"));
                list.add(tev);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pu.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 查询某商户下全部小区信息
     * 
     * @param ts_id
     * @return
     */
    public List<TB_Estate_Village> findxiaoquALL(String ts_id) {
        List<TB_Estate_Village> list = new ArrayList<TB_Estate_Village>();
        PublicDBhandles pu = new PublicDBhandles();
        TB_Estate_Village tev = null;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "select *  from TB_Estate_Village where ts_id=? and status=? ";
            pu.init(sql);
            pu.setString(1, ts_id);
            pu.setInt(2, 1);
            rs = pu.Query();
            while (rs.next()) {
                tev = new TB_Estate_Village();
                tev.setEs_id(rs.getInt("es_id"));
                tev.setTs_id(rs.getInt("ts_id"));
                tev.setEsName(rs.getString("EsName"));
                tev.setEsHead(rs.getString("EsHead"));
                tev.setEsContact(rs.getString("EsContact"));
                tev.setEsPhone(rs.getString("EsPhone"));
                tev.setEsAddress(rs.getString("EsAddress"));
                tev.setBuildArea(rs.getString("BuildArea"));
                tev.setFloorArea(rs.getString("FloorArea"));
                tev.setGreenArea(rs.getString("GreenArea"));
                tev.setVolumeRate(rs.getString("VolumeRate"));
                tev.setBuild_Number(rs.getInt("build_Number"));
                tev.setHouse_Number(rs.getInt("House_Number"));
                tev.setCreat_time(rs.getString("creat_time"));
                tev.setYznr(rs.getString("yznr"));
                tev.setRemark(rs.getString("remark"));
                tev.setTscb_id(rs.getString("tscb_id"));
                tev.setStatus(rs.getInt("status"));
                list.add(tev);
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
     * 根据小区ID查询该小区下的楼宇的ID和名称
     */
    public List<TB_Estate_Build> findlouyuname(String Es_id, String ts_id,String tu_id,String LEVELS) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_Build> list = new ArrayList<TB_Estate_Build>();
        TB_Estate_Build teb = null;
        ResultSet rs = null;
        String sql = "select bu_id,ts_id,es_id,buname from TB_Estate_Build where es_id=? and ts_id=? and status=?";
        if("1".equals(LEVELS)){
			sql+=" and (BuHead='' or BuHead is null or BuHead='"+tu_id+"')";
		}
        try {
            pu.init(sql);
            pu.setString(1, Es_id);
            pu.setString(2, ts_id);
            pu.setInt(3, 1);
            rs = pu.Query();
            while (rs.next()) {
                teb = new TB_Estate_Build();
                teb.setBu_id(rs.getInt("Bu_id"));
                teb.setTs_id(rs.getInt("ts_id"));
                teb.setEs_id(rs.getInt("Es_id"));
                teb.setBuName(rs.getString("BuName"));
                list.add(teb);
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 根据小区ID查询该小区下的楼宇全部信息
     */
    public List<TB_Estate_Build> findlouyu(String Es_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_Build> list = new ArrayList<TB_Estate_Build>();
        TB_Estate_Build teb = null;
        ResultSet rs = null;
        String sql = "select * from TB_Estate_Build where es_id=? and ts_id=? and status=?";
        try {
            pu.init(sql);
            pu.setString(1, Es_id);
            pu.setString(2, ts_id);
            pu.setInt(3, 1);
            rs = pu.Query();
            while (rs.next()) {
                teb = new TB_Estate_Build();
                teb.setBu_id(rs.getInt("Bu_id"));
                teb.setTs_id(rs.getInt("ts_id"));
                teb.setEs_id(rs.getInt("Es_id"));
                teb.setBuName(rs.getString("BuName"));
                teb.setBuTurn(rs.getString("BuTurn"));
                teb.setBuType(rs.getString("BuType"));
                teb.setBuStru(rs.getString("BuStru"));
                teb.setBuNumber(rs.getInt("BuNumber"));
                teb.setUnitNumber(rs.getInt("UnitNumber"));
                teb.setHouseNumber(rs.getInt("HouseNumber"));
                teb.setTotalArea(rs.getString("TotalArea"));
                teb.setBuildArea(rs.getString("BuildArea"));
                teb.setUseArea(rs.getString("UseArea"));
                teb.setFinishDate(rs.getString("FinishDate"));
                teb.setBuHead(rs.getString("BuHead"));
                teb.setCreat_time(rs.getString("creat_time"));
                teb.setRemark(rs.getString("remark"));
                teb.setStatus(rs.getInt("status"));
                list.add(teb);
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 查询该房屋编号已经存在该抄表日期
     */
    public boolean findMeteDate(String ts_id, String ReadDate, String EhNumber, String xiaoquid, String louid, String type) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        boolean bl = false;
        String sql = "";
        try {
            if (louid.equals("")) {
                sql = "select * from TB_Estate_Mation where eh_id in("
                        + "select eh_id from TB_Estate_House where EhNumber =? and ts_id=? and Es_id=? and status=? ) and ReadDate=?  and ts_id=? and status=? and type=?";
                pu.init(sql);
                
                pu.setString(1, EhNumber);
                pu.setString(2, ts_id);
                pu.setString(3, xiaoquid);
                pu.setString(4, "1");
                pu.setString(5, ReadDate);
                pu.setString(6, ts_id);
                pu.setString(7, "1");
                pu.setString(8, type);
                rs = pu.Query();
                if (rs.next()) {
                    bl = true;
                }
            } else {
                sql = "select * from TB_Estate_Mation where eh_id in("
                        + "select eh_id from TB_Estate_House where EhNumber =? and ts_id=? and Es_id=? and bu_id=?  and status=? ) and ReadDate=?  and ts_id=? and status=?	and type=?";
                pu.init(sql);
           
                pu.setString(1, EhNumber);
                pu.setString(2, ts_id);
                pu.setString(3, xiaoquid);
                pu.setString(4, louid);
                pu.setString(5, "1");
                pu.setString(6, ReadDate);
                pu.setString(7, ts_id);
                pu.setString(8, "1");
                pu.setString(9, type);
                rs = pu.Query();
                if (rs.next()) {
                    bl = true;
                }
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
        return bl;
    }

    /**
     * 查询该商户下的全部房屋信息
     */
    public List<TB_Estate_House> findHose(String ts_id, String xiaoquid, String louid,String Un_id) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_House> list = new ArrayList<TB_Estate_House>();
        TB_Estate_House teh = null;
        ResultSet rs = null;
        String sql = "";
        try {
            // 如果楼宇ID为空，查询全部小区全部房屋信息
            if (louid.equals("")) {
                sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and status=?";
                pu.init(sql);
                pu.setString(1, ts_id);
                pu.setString(2, xiaoquid);
                pu.setString(3, "1");
                rs = pu.Query();
                while (rs.next()) {
                    teh = new TB_Estate_House();
                    teh.setEh_id(rs.getInt("eh_id"));
                    teh.setEhNumber(rs.getString("ehnumber"));
                    list.add(teh);
                }
            } else {
            	if("".equals(Un_id)){
            		// 如果不为空查询该小区楼宇下房屋信息
                    sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and Bu_id=? and status=?";
                    pu.init(sql);
                    pu.setString(1, ts_id);
                    pu.setString(2, xiaoquid);
                    pu.setString(3, louid);
                    pu.setString(4, "1");
                    rs = pu.Query();
                    while (rs.next()) {
                        teh = new TB_Estate_House();
                        teh.setEh_id(rs.getInt("eh_id"));
                        teh.setEhNumber(rs.getString("ehnumber"));
                        list.add(teh);
                    }
            	}else{
            		// 如果不为空查询该小区楼宇下房屋信息
                    sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and Bu_id=? and Un_id=? and status=?";
                    pu.init(sql);
                    pu.setString(1, ts_id);
                    pu.setString(2, xiaoquid);
                    pu.setString(3, louid);
                    pu.setString(4, Un_id);
                    pu.setString(5, "1");
                    rs = pu.Query();
                    while (rs.next()) {
                        teh = new TB_Estate_House();
                        teh.setEh_id(rs.getInt("eh_id"));
                        teh.setEhNumber(rs.getString("ehnumber"));
                        list.add(teh);
                    }
            	}
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 查询该商户下的全部房屋信息
     */
    public Map<String, String> findHoseMap(String ts_id, String xiaoquid, String louid, String Un_id) {
        PublicDBhandles pu = new PublicDBhandles();
        Map<String, String> map = new HashMap<String, String>();
        TB_Estate_House teh = null;
        ResultSet rs = null;
        String sql = "";
        try {
            // 如果楼宇ID为空，查询全部小区全部房屋信息
            if (louid.equals("")) {
                sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and status=?";
                pu.init(sql);
                pu.setString(1, ts_id);
                pu.setString(2, xiaoquid);
                pu.setString(3, "1");
                rs = pu.Query();
                while (rs.next()) {
                    teh = new TB_Estate_House();
                    teh.setEh_id(rs.getInt("eh_id"));
                    teh.setEhNumber(rs.getString("ehnumber"));
                    map.put(teh.getEhNumber(), teh.getEh_id() + "");
                }
            } else{
            	if("".equals(Un_id)){
            		// 如果不为空查询该小区楼宇下房屋信息
                    sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and Bu_id=? and status=?";
                    pu.init(sql);
                    pu.setString(1, ts_id);
                    pu.setString(2, xiaoquid);
                    pu.setString(3, louid);
                    pu.setString(4, "1");
                    rs = pu.Query();
                    while (rs.next()) {
                        teh = new TB_Estate_House();
                        teh.setEh_id(rs.getInt("eh_id"));
                        teh.setEhNumber(rs.getString("ehnumber"));
                        map.put(teh.getEhNumber(), teh.getEh_id() + "");
                    }
            	}else{
            		// 如果不为空查询该小区楼宇下房屋信息
                    sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and Bu_id=? and Un_id=? and status=?";
                    pu.init(sql);
                    pu.setString(1, ts_id);
                    pu.setString(2, xiaoquid);
                    pu.setString(3, louid);
                    pu.setString(4, Un_id);
                    pu.setString(5, "1");
                    rs = pu.Query();
                    while (rs.next()) {
                        teh = new TB_Estate_House();
                        teh.setEh_id(rs.getInt("eh_id"));
                        teh.setEhNumber(rs.getString("ehnumber"));
                        map.put(teh.getEhNumber(), teh.getEh_id() + "");
                    }
            	}
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }
    /**
     * 查询该商户下的全部房屋信息
     */
    public Map<String, String> findHoseMap1(String ts_id, String xiaoquid, String louid, String Un_id,String LEVELS,String tu_id) {
        PublicDBhandles pu = new PublicDBhandles();
        Map<String, String> map = new HashMap<String, String>();
        TB_Estate_House teh = null;
        ResultSet rs = null;
        String sql = "";
        try {
            // 如果楼宇ID为空，查询全部小区全部房屋信息
            if (louid.equals("")) {
            	if("1".equals(LEVELS)){
            	 sql="select Eh_id,EhNumber from TB_Estate_House a inner join TB_Estate_Build b on a.Bu_id=b.Bu_id  where " +
            	 	" a.ts_id=? and a.Es_id=? and a.status=? and (b.BuHead='' or b.BuHead is null or b.BuHead='"+tu_id+"')";	
            	}else{
                 sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and status=?";
            	}
                pu.init(sql);
                pu.setString(1, ts_id);
                pu.setString(2, xiaoquid);
                pu.setString(3, "1");
                rs = pu.Query();
                while (rs.next()) {
                    teh = new TB_Estate_House();
                    teh.setEh_id(rs.getInt("eh_id"));
                    teh.setEhNumber(rs.getString("ehnumber"));
                    map.put(teh.getEhNumber(), teh.getEh_id() + "");
                }
            } else{
            	if("".equals(Un_id)){
            		// 如果不为空查询该小区楼宇下房屋信息
                    sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and Bu_id=? and status=?";
                    pu.init(sql);
                    pu.setString(1, ts_id);
                    pu.setString(2, xiaoquid);
                    pu.setString(3, louid);
                    pu.setString(4, "1");
                    rs = pu.Query();
                    while (rs.next()) {
                        teh = new TB_Estate_House();
                        teh.setEh_id(rs.getInt("eh_id"));
                        teh.setEhNumber(rs.getString("ehnumber"));
                        map.put(teh.getEhNumber(), teh.getEh_id() + "");
                    }
            	}else{
            		// 如果不为空查询该小区楼宇下房屋信息
                    sql = "select Eh_id,EhNumber from TB_Estate_House where ts_id=? and Es_id=? and Bu_id=? and Un_id=? and status=?";
                    pu.init(sql);
                    pu.setString(1, ts_id);
                    pu.setString(2, xiaoquid);
                    pu.setString(3, louid);
                    pu.setString(4, Un_id);
                    pu.setString(5, "1");
                    rs = pu.Query();
                    while (rs.next()) {
                        teh = new TB_Estate_House();
                        teh.setEh_id(rs.getInt("eh_id"));
                        teh.setEhNumber(rs.getString("ehnumber"));
                        map.put(teh.getEhNumber(), teh.getEh_id() + "");
                    }
            	}
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 房屋表和抄表信息表联合查询抄表信息
     */
    public List<TB_HMBean> findHM(int pagesize, int pagenum, String Condition) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_HMBean> list = new ArrayList<TB_HMBean>();
        TB_HMBean thmb = null;
        int num = (pagenum - 1) * pagesize;
        String sql = "select top "
                + pagesize
                + " a.*,b.es_id,b.bu_id,b.eh_id,b.EhNumber,b.EhName,c.EsName,d.BuName,e.UnName from TB_Estate_Mation a inner join TB_Estate_House b on a.eh_id=b.eh_id inner join TB_Estate_Village c on b.Es_id=c.Es_id "
                + " inner join TB_Estate_Build d on b.Bu_id=d.Bu_id left join TB_Estate_Unit e on b.Un_id=e.Un_id where a.Em_id not in("
                + "select top " + num + " Em_id from TB_Estate_Mation a inner join TB_Estate_House b on a.eh_id=b.eh_id where " + Condition
                + ") and " + Condition + " order by b.EhNumber";
        //System.out.println(sql);
        try {
            pu.init(sql);
            rs = pu.Query();
            while (rs.next()) {
                thmb = new TB_HMBean();
                thmb.setEm_id(rs.getInt("Em_id"));
                thmb.setEs_id(rs.getInt("Es_id"));
                thmb.setBu_id(rs.getInt("Bu_id"));
                thmb.setEh_id(rs.getInt("Eh_id"));
                thmb.setMa_id(rs.getString("Ma_id"));
                thmb.setEhNumber(rs.getString("EhNumber"));
                thmb.setEhName(rs.getString("EhName")==null?"":rs.getString("EhName"));
                thmb.setReadDate(rs.getString("ReadDate"));
                thmb.setLastReadNum(rs.getString("LastReadNum")==null?"":rs.getString("LastReadNum"));
                thmb.setNowReadNum(rs.getString("NowReadNum")==null?"":rs.getString("NowReadNum"));
                thmb.setUserNumber(rs.getString("UserNumber"));
                thmb.setOrderStatus(rs.getInt("orderStatus"));
                thmb.setType(rs.getInt("type"));
                thmb.setUnit(rs.getInt("unit"));
                thmb.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));
                thmb.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));
                thmb.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
                list.add(thmb);
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 房屋表和抄表信息表联合查询抄表信息导出报表
     */
    public List<TB_HMBean> findHM_ExceOut(String Condition) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_HMBean> list = new ArrayList<TB_HMBean>();
        TB_HMBean thmb = null;
        String sql = "select a.*,b.es_id,b.bu_id,b.eh_id,b.EhNumber,b.EhName,c.EsName,d.BuName,e.UnName from TB_Estate_Mation a " +
            	"inner join TB_Estate_House b on a.eh_id=b.eh_id inner join TB_Estate_Village c on b.Es_id=c.Es_id "
                + " inner join TB_Estate_Build d on b.Bu_id=d.Bu_id left join TB_Estate_Unit e on b.Un_id=e.Un_id where "
                + Condition + " order by b.EhNumber";
        try {
            pu.init(sql);
            rs = pu.Query();
            while (rs.next()) {
                thmb = new TB_HMBean();
                thmb.setEm_id(rs.getInt("Em_id"));
                thmb.setEs_id(rs.getInt("Es_id"));
                thmb.setBu_id(rs.getInt("Bu_id"));
                thmb.setEh_id(rs.getInt("Eh_id"));
                thmb.setMa_id(rs.getString("Ma_id"));
                thmb.setEhNumber(rs.getString("EhNumber"));
                thmb.setEhName(rs.getString("EhName"));
                thmb.setReadDate(rs.getString("ReadDate"));
                thmb.setLastReadNum(rs.getString("LastReadNum"));
                thmb.setNowReadNum(rs.getString("NowReadNum"));
                thmb.setUserNumber(rs.getString("UserNumber"));
                thmb.setOrderStatus(rs.getInt("orderStatus"));
                thmb.setEsName(rs.getString("EsName")==null?"":rs.getString("EsName"));
                thmb.setBuName(rs.getString("BuName")==null?"":rs.getString("BuName"));
                thmb.setUnName(rs.getString("UnName")==null?"":rs.getString("UnName"));
                thmb.setType(rs.getInt("type"));
                thmb.setUnit(rs.getInt("unit"));
                list.add(thmb);
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 根据抄表信息主键 查询 单个抄表信息
     */
    public TB_Estate_Mation findMa(String em_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        TB_Estate_Mation rem = null;
        String sql = "select * from TB_Estate_Mation where em_id=? and status=?";
        try {
            pu.init(sql);
            pu.setString(1, em_id);
            pu.setString(2, "1");
            rs = pu.Query();
            while (rs.next()) {
                rem = new TB_Estate_Mation();
                rem.setMa_id(rs.getString("ma_id"));
                rem.setReadDate(rs.getString("ReadDate"));
                rem.setLastReadNum(rs.getString("LastReadNum"));
                rem.setNowReadNum(rs.getString("NowReadNum"));
                rem.setUserNumber(rs.getString("UserNumber"));
                rem.setType(rs.getInt("type"));
                rem.setUnit(rs.getInt("unit"));
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rem;
    }

    /**
     * 根据抄表信息主键修改抄表信息
     */
    public boolean UpdateMa(String em_id, Map<String, String> map) {
        PublicDBhandles pu = new PublicDBhandles();
        boolean bl = false;
        String sql = "update TB_Estate_Mation set Ma_id=?, ReadDate=?,LastReadNum=?,NowReadNum=?,UserNumber=?,type=?,unit=? where em_id=? and status=? ";
        try {
            pu.init(sql);
            pu.setString(1, map.get("Maid"));
            pu.setString(2, map.get("ReadDate"));
            pu.setString(3, map.get("LastReadNum"));
            pu.setString(4, map.get("NowReadNum"));
            pu.setString(5, map.get("UserNumber"));
            pu.setString(6, map.get("MeteType"));
            pu.setString(7, map.get("Unit"));
            pu.setString(8, em_id);
            pu.setString(9, "1");
            bl = pu.update();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 根据小区查询缴费缴费项目
     */
    public List<TB_Estate_item> find_item(String xiaoquid, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_item> list = new ArrayList<TB_Estate_item>();
        ResultSet rs = null;
        TB_Estate_item tei = null;
        String sql = "select * from  TB_Estate_item where ts_id=? and es_id=? and status=?";
        try {
            pu.init(sql);
            pu.setString(1, ts_id);
            pu.setString(2, xiaoquid);
            pu.setString(3, "1");
            rs = pu.Query();
            while (rs.next()) {
                tei = new TB_Estate_item();
                tei.setEi_id(rs.getInt("Ei_id"));
                tei.setItemName(rs.getString("itemName"));
                tei.setItemType(rs.getString("ItemType"));
                tei.setPrice(rs.getString("price"));
                tei.setTscb_id(rs.getString("tscb_id"));
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
     * 房屋表和抄表信息表联合查询 抄表信息是属于哪个小区那个楼宇的那个单元的
     */
    public List<TB_HMBean> findchaobiao(String Un_id, String xiaoquid, String louid, String fwid, String type, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_HMBean> list = new ArrayList<TB_HMBean>();
        TB_HMBean thmb = null;
        String sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,a.EhType,c.HtName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id inner join TB_Estate_Housetype c on a.EhType=c.Ht_id where a.status=1 and b.status=1 and a.ts_id='"
                + ts_id + "' and b.ts_id='" + ts_id + "' " + " and b.orderStatus='0' ";
        try {
        	if(!xiaoquid.equals("")){
        		sql+="and  a.Es_id='" + xiaoquid + "' ";
        	}
        	if(!louid.contentEquals("")){
        		sql+="and a.Bu_id='" + louid + "' ";
        	}
        	if(!Un_id.equals("")){
        		sql+="and a.Un_id='" + Un_id + "' ";
        	}
        	if(!fwid.equals("")){
        		sql+="and a.EhNumber='" + fwid + "' ";
        	}
        	if(!type.equals("")){
        		sql+="and b.type='" + type + "' ";
        	}
        //	System.out.println(sql);
            /*if (!Un_id.equals("") && !xiaoquid.equals("") && !louid.contentEquals("") && !fwid.equals("") && !type.equals("")) {// 小区、楼宇、房屋、类型都选
                                                                                                           // 生成某一房屋的某一类型收费单
                sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id where a.status=1 and b.status=1 and a.ts_id='"
                        + ts_id
                        + "' and b.ts_id='"
                        + ts_id
                        + "' "
                        + "and  a.Es_id='"
                        + xiaoquid
                        + "' and a.Bu_id='"
                        + louid
                        + "' and a.Un_id='"
                        + Un_id+"' and a.EhNumber='"
                        + fwid + "' and b.type='" + type + "' and b.orderStatus='0'";
            } else if (!Un_id.equals("") && !xiaoquid.equals("") && !louid.contentEquals("") && !fwid.equals("")) {// 小区、楼宇、房屋、不选类型
                                                                                              // 生成某一房屋下
                                                                                              // 全部类型的收费单
                sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id where a.status=1 and b.status=1 and a.ts_id='"
                        + ts_id
                        + "' and b.ts_id='"
                        + ts_id
                        + "' "
                        + "and  a.Es_id='"
                        + xiaoquid
                        + "' and a.Bu_id='"
                        + louid
                        + "' and a.Un_id='"
                        + Un_id+"' and a.EhNumber='"
                        + fwid + "' and b.orderStatus='0'";
            } else if (!Un_id.equals("") && !xiaoquid.equals("") && !louid.contentEquals("") && !type.equals("")) {// 小区、楼宇、类型、不选房屋
                                                                                              // 生成某个楼宇下全部房屋的某一类型收费单
                sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id where a.status=1 and b.status=1 and a.ts_id='"
                        + ts_id
                        + "' and b.ts_id='"
                        + ts_id
                        + "' "
                        + "and  a.Es_id='"
                        + xiaoquid
                        + "' and a.Bu_id='"
                        + louid
                        + "' and a.Un_id='"
                        + Un_id+"' and b.type='"
                        + type + "' and b.orderStatus='0'";
            } else if (!Un_id.equals("") && !xiaoquid.equals("") && !louid.contentEquals("")) {// 小区
                                                                          // 、楼宇
                                                                          // 不选房屋和类型
                                                                          // 生成
                                                                          // 某一楼宇下全部房屋
                                                                          // 全部类型的收费单
                sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id where a.status=1 and b.status=1 and a.ts_id='"
                        + ts_id
                        + "' and b.ts_id='"
                        + ts_id
                        + "' "
                        + "and  a.Es_id='"
                        + xiaoquid
                        + "' and a.Bu_id='"
                        + louid
                        + "' and a.Un_id='"
                        + Un_id+"' and b.orderStatus='0' ";
            } else if (!xiaoquid.equals("") && !type.equals("")) {// 小区、类型、不选楼宇和房屋
                                                                  // 生成 某一小区下
                                                                  // 全部房屋信息的
                                                                  // 某一类型收费单
                sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id where a.status=1 and b.status=1 and a.ts_id='"
                        + ts_id
                        + "' and b.ts_id='"
                        + ts_id
                        + "' "
                        + "and  a.Es_id='"
                        + xiaoquid
                        + "' and b.type='"
                        + type
                        + "' and b.orderStatus='0' ";
            } else if (!xiaoquid.equals("")) {// 小区不为空 楼宇 、房屋、类型 都为空 生成
                                              // 某一小区下全部房屋信息的 全部类型收费单
                sql = "select a.Es_id,a.Bu_id,a.Un_id,a.EhNumber,a.EhName,b.* from TB_Estate_House a inner join TB_Estate_Mation b on a.Eh_id=b.Eh_id where a.status=1 and b.status=1 and a.ts_id='"
                        + ts_id + "' and b.ts_id='" + ts_id + "' " + "and  a.Es_id='" + xiaoquid + "' and b.orderStatus='0' ";
            }*/
            pu.init(sql);
            rs = pu.Query();
            while (rs.next()) {
                thmb = new TB_HMBean();
                thmb.setEm_id(rs.getInt("Em_id"));
                thmb.setEs_id(rs.getInt("Es_id"));
                thmb.setBu_id(rs.getInt("Bu_id"));
                thmb.setUn_id(rs.getInt("Un_id"));
                thmb.setEh_id(rs.getInt("Eh_id"));
                thmb.setMa_id(rs.getString("Ma_id"));
                thmb.setEhNumber(rs.getString("EhNumber"));
                thmb.setEhName(rs.getString("EhName"));
                thmb.setReadDate(rs.getString("ReadDate"));
                thmb.setLastReadNum(rs.getString("LastReadNum"));
                thmb.setNowReadNum(rs.getString("NowReadNum"));
                thmb.setUserNumber(rs.getString("UserNumber"));
                thmb.setOrderStatus(rs.getInt("orderStatus"));
                thmb.setType(rs.getInt("type"));
                thmb.setUnit(rs.getInt("unit"));
                thmb.setEhType(rs.getInt("EhType"));
                thmb.setHtName(rs.getString("htName"));
                list.add(thmb);
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 删除抄表信息记录
     */
    public boolean MeteDelete(int Em_id, String Ma_id) {
        PublicDBhandles pu = new PublicDBhandles();
        boolean bl = false;
        String sql = "";
        try {
            sql = "update TB_Estate_Mation set status=? where Em_id=? and Ma_id=?";
            pu.init(sql);
            pu.setString(1, "0");
            pu.setInt(2, Em_id);
            pu.setString(3, Ma_id);
            bl = pu.update();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 根据询全部的小区名称
     */
    public Map<String, String> findxqName(String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        Map<String, String> map = new HashMap<String, String>();
        TB_Estate_Village tev = null;
        String sql = "select Es_id, EsName from TB_Estate_Village where ts_id=? and status=?";
        try {
            pu.init(sql);
            pu.setString(1, ts_id);
            pu.setString(2, "1");
            rs = pu.Query();
            while (rs.next()) {
                tev = new TB_Estate_Village();
                tev.setEs_id(rs.getInt("es_id"));
                tev.setEsName(rs.getString("esname"));
                map.put(tev.getEs_id() + "", tev.getEsName());
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 根据询全部的楼宇名称
     */
    public Map<String, String> findLyuName(String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        Map<String, String> map = new HashMap<String, String>();
        TB_Estate_Build tev = null;
        String sql = "select Bu_id, BuName from TB_Estate_Build where ts_id=? and status=?";
        try {
            pu.init(sql);
            pu.setString(1, ts_id);
            pu.setString(2, "1");
            rs = pu.Query();
            while (rs.next()) {
                tev = new TB_Estate_Build();
                tev.setBu_id(rs.getInt("Bu_id"));
                tev.setBuName(rs.getString("BuName"));
                map.put(tev.getBu_id() + "", tev.getBuName());
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 通过操作员编号的到 操作员主键
     * 
     * @param userid
     * @return
     */
    public String getu_id(String userid, String tablename) {
        String name = "";
        String sql = "select tu_id from " + tablename + " where userid='" + userid + "' ";
        
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        try {
            pu.init(sql);
            rs = pu.Query();
            if (rs.next()) {
                name = rs.getString("tu_id");
            }
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
        return name;
    }

    /**
     * 添加管理员操作日志
     * 
     * @param l_content
     * @param user_id
     */
    public static void add_Log(String l_content, String user_id, String tu_id, String type) {
        try {
            PublicDBhandles pu = new PublicDBhandles();
            String sql = " insert into TBAdmin_Log(l_content,user_id,l_time,Status,tu_id,type) values ('" + l_content + "','" + user_id + "','"
                    + new T_time().getTime() + "','1','" + tu_id + "','" + type + "') ";
            pu.init(sql);
            pu.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 房屋表和抄表信息表联合查询出最近一次抄表的信息
     */
    public List<TB_HMBean> findHM_Out(String Condition, String Mtype, String unit, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_HMBean> list = new ArrayList<TB_HMBean>();
        TB_HMBean thmb = null;
        String sql = "select a.* , b.EhNumber from  TB_Estate_Mation a inner join TB_Estate_House b on a.eh_id=b.eh_id,("
                + "select eh_id,max(ReadDate) ReadDate from TB_Estate_Mation where ts_id='" + ts_id + "' and type='" + Mtype + "' and unit='"
                + unit + "' and status=1 group by eh_id) c " + "where a.eh_id=c.eh_id and a.ReadDate=c.ReadDate  and " + Condition
                + " order by a.Eh_id ";
        try {
            
            pu.init(sql);
            rs = pu.Query();
            while (rs.next()) {
                thmb = new TB_HMBean();
                thmb.setEm_id(rs.getInt("Em_id"));
                thmb.setEh_id(rs.getInt("Eh_id"));
                thmb.setMa_id(rs.getString("Ma_id"));
                thmb.setEhNumber(rs.getString("EhNumber"));
                thmb.setReadDate(rs.getString("ReadDate"));
                thmb.setLastReadNum(rs.getString("LastReadNum"));
                thmb.setNowReadNum(rs.getString("NowReadNum"));
                thmb.setUserNumber(rs.getString("UserNumber"));
                thmb.setOrderStatus(rs.getInt("orderStatus"));
                thmb.setType(rs.getInt("type"));
                thmb.setUnit(rs.getInt("unit"));
                list.add(thmb);
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
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
    /**
     * 根据小区查询缴费缴费项目对应物业类型
     */
    public List<TB_Estate_ChargeType> find_itemChargeType(String xiaoquid, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_ChargeType> list = new ArrayList<TB_Estate_ChargeType>();
        ResultSet rs = null;
        TB_Estate_ChargeType tei = null;
        String sql = "select b.itemName,a.Ei_id,c.HtName,a.Ht_id,a.Ct_price,a.Ct_ItemType from TB_Estate_ChargeType a inner join  TB_Estate_item b on a.Ei_id=b.Ei_id left join TB_Estate_Housetype c on a.Ht_id=c.Ht_id" +
        		" where b.ts_id='"+ts_id+"' and b.es_id='"+xiaoquid+"' and b.status='1' and a.status='1'";
        try {
            pu.init(sql);
            rs = pu.Query();
            while (rs.next()) {
                tei = new TB_Estate_ChargeType();
                tei.setCt_itemName(rs.getString("itemName"));
                tei.setEi_id(rs.getInt("Ei_id"));
                tei.setCt_HtName(rs.getString("HtName"));
                tei.setHt_id(rs.getInt("Ht_id"));
                tei.setCt_price(rs.getString("Ct_price"));
                tei.setCt_ItemType(rs.getString("ct_ItemType"));
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
     * 根据小区查询缴费项目对应房屋类型
     */
    public List<TB_Estate_ChargeType> findChargeType_item(String xiaoquid, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_ChargeType> list = new ArrayList<TB_Estate_ChargeType>();
        ResultSet rs = null;
        TB_Estate_ChargeType tei = null;
        String sql = "select b.itemName,a.Ei_id,a.Ct_price,a.Ct_ItemType,a.Ht_id from  TB_Estate_ChargeType a left join  TB_Estate_item b on a.Ei_id=b.Ei_id where b.ts_id=? " +
        		"and b.es_id=? and a.status='1' and b.status='1' order by a.Ei_id";
        try {
            pu.init(sql);
            pu.setString(1, ts_id);
            pu.setString(2, xiaoquid);
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
    
}
