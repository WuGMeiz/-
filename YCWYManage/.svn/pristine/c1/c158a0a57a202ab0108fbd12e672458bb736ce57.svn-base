/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_Build;
import WYBack_Stage.Bean.TB_Estate_Unit;
import WYBack_Stage.Bean.TB_Estate_Village;
import ccbjf.system.db.PublicDBhandles;

/**
 * <p> 物业楼宇DAO <p>
 * 
 * @date 2018-3-7 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class MyTB_Build_DAO {
    /**
     * 根据ts_id查询小区信息
     * 
     * @param ts_id
     * @author 金鑫
     * 
     */
    public List<TB_Estate_Village> select_xiaoqu(String ts_id) {
        ResultSet rs = null;
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_Village> list = new ArrayList<TB_Estate_Village>();
        String sql = "select Es_id, EsName from TB_Estate_Village where ts_id='" + ts_id + "' and status='1'";
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
   /**
    * 查询单元信息
    * @param ts_id
    * @param Es_id
    * @param Bu_id
    * @return
    */
   public List<TB_Estate_Unit> select_dy(String ts_id, String Es_id,String Bu_id){
	 ResultSet rs = null;
     PublicDBhandles pu = new PublicDBhandles();
     List<TB_Estate_Unit> units=new ArrayList<TB_Estate_Unit>();
     TB_Estate_Unit unit=null;
     String sql="select Un_id,UnName from TB_Estate_Unit  where ts_id='" + ts_id + "' and Es_id='" + Es_id + "' and Bu_id='"+Bu_id+"'  and status='1'";
     try {
         pu.init(sql);
         rs=pu.Query();
		 while(rs.next()){
			 unit=new TB_Estate_Unit();
			 unit.setUn_id(rs.getInt("Un_id"));
			 unit.setUnName(rs.getString("unName"));
			 units.add(unit);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         return units;
    }
    /**
     * 根据Es_id查询楼宇信息
     * 
     * @param ts_id
     * @param Es_id
     * @return
     */
    public List<TB_Estate_Build> select_louyu(String ts_id, String Es_id,String tu_id,String LEVELS) {
        ResultSet rs = null;
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_Build> list = new ArrayList<TB_Estate_Build>();
        String sql = "select Bu_id, BuName from TB_Estate_Build where ts_id='" + ts_id + "' and Es_id='" + Es_id + "' and status='1'";
        if("1".equals(LEVELS)){
			sql+=" and (BuHead='' or BuHead is null or BuHead='"+tu_id+"')";
		}
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Build build=null;
            while (rs.next()) {
                build = new TB_Estate_Build();
                build.setBu_id(rs.getInt("Bu_id"));
                build.setBuName(rs.getString("BuName"));
                list.add(build);
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

    /**
     * 用户查询 ，查询符合条件的楼宇
     * 
     * @param pagesize
     * @param pagenum
     * @return
     * @throws Exception
     */
    public List<TB_Estate_Build> getTB_Build(int pagesize, int pagenum, String condition, String ts_id) throws Exception {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_Build> list = new ArrayList<TB_Estate_Build>();
        int num = (pagenum - 1) * pagesize;
        String sql = "select top " + pagesize + " a.* ,b.EsName,c.username from TB_Estate_Build a left join TB_SEV_USER c on a.BuHead=c.tu_id inner join TB_Estate_Village b on " 
                + "  a.Es_id=b.Es_id  where Bu_id not in " + "(select top " + num
                + " Bu_id from TB_Estate_Build a left join TB_SEV_USER c on a.BuHead=c.tu_id inner join TB_Estate_Village b on "
                + "  a.Es_id=b.Es_id where " + condition + ") and " + condition + " order by a.Bu_id asc";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Build teb=null;
            while (rs.next()) {
                teb = new TB_Estate_Build();
                teb.setBu_id(rs.getInt("Bu_id"));
                teb.setBuHead(rs.getString("username") == null ? "" : rs.getString("username"));
                teb.setBuildArea(rs.getString("buildArea") == null ? "" : rs.getString("buildArea"));
                teb.setBuName(rs.getString("buName") == null ? "" : rs.getString("buName"));
                teb.setBuNumber(rs.getInt("buNumber"));
                teb.setBuStru(rs.getString("buStru") == null ? "" : rs.getString("buStru"));
                teb.setBuTurn(rs.getString("buTurn") == null ? "" : rs.getString("buTurn"));
                teb.setBuType(rs.getString("buType") == null ? "" : rs.getString("buType"));
                teb.setCreat_time(rs.getString("creat_time") == null ? "" : rs.getString("creat_time"));
                teb.setEs_id(rs.getInt("es_id"));
                teb.setFinishDate(rs.getString("finishDate") == null ? "" : rs.getString("finishDate"));
                teb.setHouseNumber(rs.getInt("houseNumber"));
                teb.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
                teb.setStatus(rs.getInt("status"));
                teb.setTotalArea(rs.getString("totalArea") == null ? "" : rs.getString("totalArea"));
                teb.setTs_id(rs.getInt("ts_id"));
                teb.setUnitNumber(rs.getInt("unitNumber"));
                teb.setUseArea(rs.getString("useArea") == null ? "" : rs.getString("useArea"));
                teb.setEsName(rs.getString("EsName") == null ? "" : rs.getString("EsName"));
                list.add(teb);
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

    /**
     * 通过用户ID得到楼宇信息
     * 
     * @param org_id
     * @return
     */
    public TB_Estate_Build getTBBuild_Xx(String bu_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        TB_Estate_Build teb = null;
        String sql = "select a.*,b.EsName from TB_Estate_Build a ,TB_Estate_Village b where a.Bu_id='" + bu_id
                + "' and a.ts_id=b.ts_id and a.status='1' and b.status='1' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                teb = new TB_Estate_Build();
                teb.setBu_id(rs.getInt("Bu_id"));
                teb.setBuHead(rs.getString("BuHead") == null ? "" : rs.getString("BuHead"));
                teb.setBuildArea(rs.getString("buildArea") == null ? "" : rs.getString("buildArea"));
                teb.setBuName(rs.getString("buName") == null ? "" : rs.getString("buName"));
                teb.setBuNumber(rs.getInt("buNumber"));
                teb.setBuStru(rs.getString("buStru") == null ? "" : rs.getString("buStru"));
                teb.setBuTurn(rs.getString("buTurn") == null ? "" : rs.getString("buTurn"));
                teb.setBuType(rs.getString("buType") == null ? "" : rs.getString("buType"));
                teb.setCreat_time(rs.getString("creat_time") == null ? "" : rs.getString("creat_time"));
                teb.setEs_id(rs.getInt("es_id"));
                teb.setFinishDate(rs.getString("finishDate") == null ? "" : rs.getString("finishDate"));
                teb.setHouseNumber(rs.getInt("houseNumber"));
                teb.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
                teb.setStatus(rs.getInt("status"));
                teb.setTotalArea(rs.getString("totalArea") == null ? "" : rs.getString("totalArea"));
                teb.setTs_id(rs.getInt("ts_id"));
                teb.setUnitNumber(rs.getInt("unitNumber"));
                teb.setUseArea(rs.getString("useArea") == null ? "" : rs.getString("useArea"));
                teb.setEsName(rs.getString("EsName") == null ? "" : rs.getString("EsName"));
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
        return teb;
    }

    /**
     * 查询小区id
     * 
     * @param bu_id
     * @return
     */
    public String getEsid(String bu_id, String ts_id) {
        String Es_id = "";
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String sql = "select Es_id from TB_Estate_Build where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' and status='1'";
        try {
            pu.init(sql);
            rs = pu.Query();
            if (rs.next()) {
                Es_id = rs.getString("Es_id");
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
        return Es_id;
    }

    /**
     * 查寻楼宇名称
     * 
     * @param ts_id
     * @param es_id
     * @return
     */
    public List<String> findTBBuildNamenot(String ts_id, String es_id, String bu_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        String sql = "";
        sql = "select BuName from TB_Estate_Build where ts_id='" + ts_id + "' and Es_id='" + es_id + "' and status='1' and Bu_id not in('" + bu_id
                + "')";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            while (rs.next()) {
                list.add(rs.getString("BuName"));
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
        return list;
    }

    /**
     * 修改楼宇信息
     * 
     * @param bu_id
     * @param BuName
     * @param BuTurn
     * @param BuType
     * @param BuStru
     * @param BuNumber
     * @param UnitNumber
     * @param HouseNumber
     * @param TotalArea
     * @param BuildArea
     * @param UseArea
     * @param FinishDate
     * @param BuHead
     * @return
     */
    public boolean louyuxg(String ts_id, String bu_id, String BuName, String BuTurn, String BuType, String BuStru, String BuNumber,
            String UnitNumber, String HouseNumber, String TotalArea, String BuildArea, String UseArea, String FinishDate, String BuHead, String remark) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "update TB_Estate_Build set  BuName='" + BuName + "',BuTurn='" + BuTurn + "',BuType='" + BuType + "' ,BuStru='" + BuStru
                    + "' ,BuNumber='" + BuNumber + "' ,UnitNumber='" + UnitNumber + "' ,HouseNumber='" + HouseNumber + "' ,TotalArea='" + TotalArea
                    + "' ,BuildArea='" + BuildArea + "' ,UseArea='" + UseArea + "' ,FinishDate='" + FinishDate + "' ,BuHead='" + BuHead
                    + "',remark='" + remark + "'   where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' and status='1' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 判断楼宇下有无房屋
     * 
     * @param es_id
     * @param ts_id
     * @return
     */
    public boolean checkfw(String bu_id, String ts_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select * from TB_Estate_House where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' and status='1'";
        try {
            pu = new PublicDBhandles();
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
    /**
     * 判断楼宇下有无单元信息
     * 
     * @param es_id
     * @param ts_id
     * @return
     */
    public boolean checkDy(String bu_id, String ts_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select * from TB_Estate_Unit where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' and status='1'";
        try {
            pu = new PublicDBhandles();
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
    /**
     * 删除楼宇信息
     * 
     * @param
     * @return
     */
    public boolean delete_TB_Build(String bu_id, String ts_id) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "update TB_Estate_Build set status='0' where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 删除楼宇时，通过楼宇ID得到楼宇名称
     * 
     * @param
     * @return
     */
    public String getBuName(String bu_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String str = "";
        String sql = "select BuName from TB_Estate_Build where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                str = rs.getString("BuName");
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
        return str;
    }

    /**
     * <p> 添加时验楼宇是否存在 <p>
     * 
     * @param esName
     * @return
     */
    public boolean checkadd(String ts_id, int i, String es_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select BuName from TB_Estate_Build where BuName='" + i + "' and Es_id='" + es_id + "' and ts_id='" + ts_id + "' and status='1'";
        try {
            pu = new PublicDBhandles();
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

    /**
     * 添加楼宇信息
     * 
     * @param ts_id
     * @param es_id
     * @param louhao
     * @param BuTurn
     * @param BuType
     * @param BuStru
     * @param BuNumber
     * @param UnitNumber
     * @param HouseNumber
     * @param TotalArea
     * @param BuildArea
     * @param UseArea
     * @param FinishDate
     * @param BuHead
     * @param remark
     * @param time
     * @return
     */
    public boolean lyadd(String ts_id, String es_id, String louhao, String BuTurn, String BuType, String BuStru, String BuNumber, String UnitNumber,
            String HouseNumber, String TotalArea, String BuildArea, String UseArea, String FinishDate, String BuHead, String remark, String time) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "insert into TB_Estate_Build( ts_id,Es_id,BuName,BuTurn,BuType,BuStru,BuNumber,UnitNumber,HouseNumber,TotalArea,BuildArea,UseArea,FinishDate,BuHead,remark,creat_time,status) "
                    + " values ( '"
                    + ts_id
                    + "','"
                    + es_id
                    + "','"
                    + louhao
                    + "','"
                    + BuTurn
                    + "','"
                    + BuType
                    + "','"
                    + BuStru
                    + "','"
                    + BuNumber
                    + "','"
                    + UnitNumber
                    + "','"
                    + HouseNumber
                    + "','"
                    + TotalArea
                    + "','"
                    + BuildArea
                    + "','"
                    + UseArea
                    + "','"
                    + FinishDate
                    + "','"
                    + BuHead + "','" + remark + "','" + time + "','1') ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 通过楼宇ID得到楼宇名称
     * 
     * @param
     * @return
     */
    public String getBuname(String bu_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String str = "";
        String sql = "select BuName from TB_Estate_Build where Bu_id='" + bu_id + "' and ts_id='" + ts_id + "' and status='1' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                str = rs.getString("BuName");
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
        return str;
    }

    /**
     * <p> 添加时验楼宇是否存在 <p>
     * 
     * @param esName
     * @return
     */
    public boolean checkadd1(String ts_id, String qsbuname, String es_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select BuName from TB_Estate_Build where BuName='" + qsbuname + "' and Es_id='" + es_id + "' and ts_id='" + ts_id
                + "' and status='1'";
        try {
            pu = new PublicDBhandles();
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

    public boolean lyadd1(String ts_id, String es_id, String qsbuname, String BuTurn, String BuType, String BuStru, String BuNumber,
            String UnitNumber, String HouseNumber, String TotalArea, String BuildArea, String UseArea, String FinishDate, String BuHead,
            String remark, String time) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "insert into TB_Estate_Build( ts_id,Es_id,BuName,BuTurn,BuType,BuStru,BuNumber,UnitNumber,HouseNumber,TotalArea,BuildArea,UseArea,FinishDate,BuHead,remark,creat_time,status) "
                    + " values ( '"
                    + ts_id
                    + "','"
                    + es_id
                    + "','"
                    + qsbuname
                    + "','"
                    + BuTurn
                    + "','"
                    + BuType
                    + "','"
                    + BuStru
                    + "','"
                    + BuNumber
                    + "','"
                    + UnitNumber
                    + "','"
                    + HouseNumber
                    + "','"
                    + TotalArea
                    + "','"
                    + BuildArea
                    + "','"
                    + UseArea
                    + "','"
                    + FinishDate
                    + "','"
                    + BuHead + "','" + remark + "','" + time + "','1') ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }
}
