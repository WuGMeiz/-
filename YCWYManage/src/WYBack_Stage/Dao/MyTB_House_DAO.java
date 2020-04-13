/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Bean.TB_Estate_Housetype;
import WYCommunity.MD5;
import ccbjf.system.db.PublicDBhandles;

/**
 * <p> 物业房屋DAO <p>
 * 
 * @date 2018-3-7 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class MyTB_House_DAO {
    
	/**
     * 用户查询 ，查询符合条件的房屋信息
     * 
     * @param pagesize
     * @param pagenum
     * @return
     * @throws Exception
     */
    public List<TB_Estate_House> getTB_House( int pagesize, int pagenum, String condition ) throws Exception {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_House> list = new ArrayList<TB_Estate_House>();
        int num = (pagenum - 1) * pagesize;
        String sql = "select top " + pagesize + " a.* ,b.BuName ,c.EsName,d.UnName,e.HtName from TB_Estate_House   a left join TB_Estate_Build b on a.ts_id=b.ts_id and a.Es_id=b.Es_id" +
                " and a.Bu_id=b.Bu_id left join TB_Estate_Village c on a.ts_id=c.ts_id and a.Es_id=c.Es_id left join TB_Estate_Unit d on a.un_id =d.un_id left join TB_Estate_Housetype e on a.EhType =e.Ht_id " +
             		"where Eh_id not in  (select top "  + num + " Eh_id from TB_Estate_House a left join TB_Estate_Build b on a.ts_id=b.ts_id and a.Es_id=b.Es_id" +
                " and a.Bu_id=b.Bu_id left join TB_Estate_Village c on a.ts_id=c.ts_id and a.Es_id=c.Es_id left join TB_Estate_Unit d on a.un_id =d.un_id left join TB_Estate_Housetype e on a.EhType =e.Ht_id where "
                     + condition + " order by a.EhNumber ) and " + condition + " order by a.EhNumber ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_House teh=null;
            while (rs.next()) {
                teh = new TB_Estate_House();
                teh.setBu_id(rs.getInt("Bu_id"));
                teh.setUn_id(rs.getInt("Un_id"));
                teh.setBuildArea(rs.getString("BuildArea") == null ? "" : rs.getString("BuildArea"));
                teh.setCarNum(rs.getInt("carNum"));
                teh.setCreat_time(rs.getString("creat_time") == null ? "" : rs.getString("creat_time"));
                teh.setEh_id(rs.getInt("eh_id"));
                teh.setEhName(rs.getString("ehName") == null ? "" : rs.getString("ehName"));
                teh.setEhNature(rs.getString("ehNature") == null ? "" : rs.getString("ehNature"));
                teh.setEhNumber(rs.getString("ehNumber") == null ? "" : rs.getString("ehNumber"));
                teh.setEhStatus(rs.getString("ehStatus") == null ? "" : rs.getString("ehStatus"));
                teh.setEhStru(rs.getString("ehStru") == null ? "" : rs.getString("ehStru"));
                teh.setEhTurn(rs.getString("ehTurn") == null ? "" : rs.getString("ehTurn"));
                teh.setEhType(rs.getString("ehType") == null ? "" : rs.getString("ehType"));
                teh.setEs_id(rs.getInt("es_id"));
                teh.setHeatingArea(rs.getString("heatingArea") == null ? "" : rs.getString("heatingArea"));
                teh.setHousingUse(rs.getString("housingUse") == null ? "" : rs.getString("housingUse"));
                teh.setOftenNumber(rs.getInt("oftenNumber"));
                teh.setOwnerName(rs.getString("ownerName") == null ? "" : rs.getString("ownerName"));
                teh.setOwnerPhone(rs.getString("ownerPhone") == null ? "" : rs.getString("ownerPhone"));
                teh.setPropertyRight(rs.getString("propertyRight") == null ? "" : rs.getString("propertyRight"));
                teh.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
                teh.setStatus(rs.getInt("status"));
                teh.setTs_id(rs.getInt("ts_id"));
                teh.setStorey(rs.getInt("storey"));
                teh.setHandIs(rs.getInt("handIs"));
                teh.setUseArea(rs.getString("useArea") == null ? "" : rs.getString("useArea"));
                teh.setEsName(rs.getString("EsName") == null ? "" : rs.getString("EsName"));
                teh.setBuName(rs.getString("BuName") == null ? "" : rs.getString("BuName"));
                teh.setUnName(rs.getString("unName") == null ? "" : rs.getString("unName"));
                teh.setHtName(rs.getString("HtName")==null?"":rs.getString("HtName"));
                list.add(teh);
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
     * 查询楼宇id
     * 
     * @param eh_id
     * @return
     */
    public String getBuid(String ts_id, String eh_id) {
        String Bu_id = "";
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String sql = "select Bu_id from TB_Estate_House where Eh_id='" + eh_id + "' and ts_id='" + ts_id + "' and status='1'";
        try {
            pu.init(sql);
            rs = pu.Query();
            if (rs.next()) {
                Bu_id = rs.getString("Bu_id");
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
        return Bu_id;
    }
    
    /**
     * 查询楼宇id
     * 
     * @param eh_id
     * @return
     */
    public String getBuUnid(String ts_id, String eh_id) {
        String str = "";
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String sql = "select Bu_id,Un_id from TB_Estate_House where Eh_id='" + eh_id + "' and ts_id='" + ts_id + "' and status='1'";
        try {
            pu.init(sql);
            rs = pu.Query();
            if (rs.next()) {
            	String Bu_id = rs.getString("Bu_id")==null?"0":rs.getString("Bu_id");
            	String Un_id = rs.getString("Un_id")==null?"0":rs.getString("Un_id");
            	str = Bu_id + "|" + Un_id;
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
     * 查询小区id
     * 
     * @param eh_id
     * @return
     */
    public String getEsid(String ts_id, String eh_id) {
        String Es_id = "";
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String sql = "select Es_id from TB_Estate_House where Eh_id='" + eh_id + "' and ts_id='" + ts_id + "' and status='1'";
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
     * 通过用户ID得到房屋信息
     * 
     * @param org_id
     * @return
     */
    public TB_Estate_House getTBHouse_Xx( String eh_id,String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        TB_Estate_House teh = null;
        String sql = "select a.*,e.BuName ,b.EsName,d.UnName,d.Un_id,f.HtName  from TB_Estate_House a left join " +
        		"TB_Estate_Village b on a.Es_id=b.Es_id left join TB_Estate_Unit d on a.Un_id =d.un_id " +
        		" left join TB_Estate_Build e on a.Bu_id=e.Bu_id left join TB_Estate_Housetype f on a.EhType =f.Ht_id  where       " +
        				"a.Eh_id='" + eh_id+"'   and a.ts_id='"+ts_id+"' and a.status='1' " +
        				" and b.status='1'     and e.status='1' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                teh = new TB_Estate_House();
                teh.setBu_id(rs.getInt("Bu_id"));
                teh.setBuildArea(rs.getString("BuildArea") == null ? "" : rs.getString("BuildArea"));
                teh.setCarNum(rs.getInt("carNum"));
                teh.setCreat_time(rs.getString("creat_time") == null ? "" : rs.getString("creat_time"));
                teh.setEh_id(rs.getInt("eh_id"));
                teh.setEhName(rs.getString("ehName") == null ? "" : rs.getString("ehName"));
                teh.setEhNature(rs.getString("ehNature") == null ? "" : rs.getString("ehNature"));
                teh.setEhNumber(rs.getString("ehNumber") == null ? "" : rs.getString("ehNumber"));
                teh.setEhStatus(rs.getString("ehStatus") == null ? "" : rs.getString("ehStatus"));
                teh.setEhStru(rs.getString("ehStru") == null ? "" : rs.getString("ehStru"));
                teh.setEhTurn(rs.getString("ehTurn") == null ? "" : rs.getString("ehTurn"));
                teh.setEhType(rs.getString("ehType") == null ? "1" : rs.getString("ehType"));
                teh.setHtName(rs.getString("HtName")==null?"普通住宅":rs.getString("HtName"));
                teh.setEs_id(rs.getInt("es_id"));
                teh.setHeatingArea(rs.getString("heatingArea") == null ? "" : rs.getString("heatingArea"));
                teh.setHousingUse(rs.getString("housingUse") == null ? "" : rs.getString("housingUse"));
                teh.setOftenNumber(rs.getInt("oftenNumber"));
                teh.setOwnerName(rs.getString("ownerName") == null ? "" : rs.getString("ownerName"));
                teh.setOwnerPhone(rs.getString("ownerPhone") == null ? "" : rs.getString("ownerPhone"));
                teh.setPropertyRight(rs.getString("propertyRight") == null ? "" : rs.getString("propertyRight"));
                teh.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
                teh.setStatus(rs.getInt("status"));
                teh.setTs_id(rs.getInt("ts_id"));
                teh.setUseArea(rs.getString("useArea") == null ? "" : rs.getString("useArea"));
                teh.setEsName(rs.getString("EsName") == null ? "" : rs.getString("EsName"));
                teh.setBuName(rs.getString("BuName") == null ? "" : rs.getString("BuName"));
                teh.setUnName(rs.getString("unName") == null ? "" : rs.getString("unName"));
                teh.setHandIs(rs.getInt("handIs"));
                teh.setStorey(rs.getInt("storey"));
                teh.setUn_id(rs.getInt("un_id"));
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
        return teh;
    }

    /**
     * 查寻房屋编号
     * 
     * @param ts_id
     * @param es_id
     * @return
     */
    public List<String> findTBHouseNamenot(String ts_id, String es_id, String eh_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        String sql = "select EhNumber from TB_Estate_House where ts_id='" + ts_id + "' and Es_id='" + es_id + "' and status='1' and Eh_id not in('" + eh_id
                + "')";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            while (rs.next()) {
                list.add(rs.getString("EhNumber"));
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
     * 修改房屋信息
     * 
     * @param eh_id
     * @param EhNumber
     * @param EhName
     * @param EhNature
     * @param EhStru
     * @param EhType
     * @param EhTurn
     * @param EhStatus
     * @param PropertyRight
     * @param HousingUse
     * @param OftenNumber
     * @param OwnerName
     * @param OwnerPhone
     * @param BuildArea
     * @param UseArea
     * @param HeatingArea
     * @param CarNum
     * @param remark
     * @return
     */
    public boolean fangwuxg(String ts_id, String eh_id, String EhNumber, String EhName, String EhNature, String EhStru, String EhType, String EhTurn,
            String EhStatus, String PropertyRight, String HousingUse, String OftenNumber, String OwnerName, String OwnerPhone, String BuildArea,
            String UseArea, String HeatingArea, String CarNum, String remark,int handIs,int storer) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "update TB_Estate_House set  EhNumber='" + EhNumber + "',EhName='" + EhName + "',EhNature='" + EhNature + "' ,EhStru='"
                    + EhStru + "' ,EhType='" + EhType + "' ,EhTurn='" + EhTurn + "' ,EhStatus='" + EhStatus + "' ,PropertyRight='" + PropertyRight
                    + "' ,HousingUse='" + HousingUse + "' ,OftenNumber='" + OftenNumber + "' ,OwnerName='" + OwnerName + "' ,OwnerPhone='"
                    + OwnerPhone + "',BuildArea='" + BuildArea + "',UseArea='" + UseArea + "',HeatingArea='" + HeatingArea + "',CarNum='" + CarNum
                    + "'" + ",remark='" + remark + "',handIs="+handIs+",storey="+storer+"   where Eh_id='" + eh_id + "' and ts_id='" + ts_id + "' and status='1' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 删除房屋信息
     * 
     * @param
     * @return
     */
    public boolean delete_TB_House(String eh_id, String ts_id) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "update TB_Estate_House set status='0' where Eh_id='" + eh_id + "' and ts_id='" + ts_id + "' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 删除房屋时，通过房屋ID得到房屋编号
     * 
     * @param
     * @return
     */
    public String getEhNumber(String eh_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String str = "";
        String sql = "select EhNumber from TB_Estate_House where Eh_id='" + eh_id + "' and ts_id='" + ts_id + "' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                str = rs.getString("EhNumber");
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
     * <p> 添加时验房屋编号是否存在 <p>
     * 
     * @param esName
     * @return
     */
    public boolean checkadd(String ts_id, String ehNumber, String es_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select EhNumber from TB_Estate_House where EhNumber='" + ehNumber + "' and ts_id='" + ts_id + "' and Es_id='" + es_id
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

    /**
     * 添加房屋信息
     * 
     * @param ts_id
     * @param es_id
     * @param bu_id
     * @param EhNumber
     * @param EhName
     * @param EhNature
     * @param EhStru
     * @param EhType
     * @param EhTurn
     * @param EhStatus
     * @param PropertyRight
     * @param HousingUse
     * @param OftenNumber
     * @param OwnerName
     * @param OwnerPhone
     * @param BuildArea
     * @param UseArea
     * @param HeatingArea
     * @param CarNum
     * @param remark
     * @param time
     * @return
     */
    public boolean fwadd(String ts_id, String es_id, String bu_id,String Un_id, String EhNumber, String EhName, String EhNature, String EhStru, String EhType,
            String EhTurn, String EhStatus, String PropertyRight, String HousingUse, String OftenNumber, String OwnerName, String OwnerPhone,
            String BuildArea, String UseArea, String HeatingArea, String CarNum, String remark, String time,String storer,String handIs) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "insert into TB_Estate_House( ts_id,Es_id,Bu_id,Un_id,EhNumber,EhName,EhNature,EhStru,EhType,EhTurn,EhStatus,PropertyRight,HousingUse,OftenNumber,OwnerName,OwnerPhone,BuildArea,UseArea,HeatingArea,CarNum,remark,creat_time,status,password,userSign,storey,handIs) "
                    + " values ( '"
                    + ts_id
                    + "','"
                    + es_id
                    + "','"
                    + bu_id
                    + "','"
                    + Un_id
                    + "','"
                    + EhNumber
                    + "','"
                    + EhName
                    + "','"
                    + EhNature
                    + "','"
                    + EhStru
                    + "','"
                    + EhType
                    + "','"
                    + EhTurn
                    + "','"
                    + EhStatus
                    + "','"
                    + PropertyRight
                    + "','"
                    + HousingUse
                    + "','"
                    + OftenNumber
                    + "','"
                    + OwnerName
                    + "','"
                    + OwnerPhone
                    + "','"
                    + BuildArea
                    + "','"
                    + UseArea
                    + "','"
                    + HeatingArea
                    + "','"
                    + CarNum
                    + "','"
                    + remark
                    + "','"
                    + time
                    + "','1','" + MD5.getCCBMd5("111111") + "','0' ,'"+storer+"','"+handIs+"')";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 前台密码重置
     * 
     * @param tu_id 用户表ID
     * @param resetPassword 充值的密码
     * @param tablename 为平台、银行、商户三个管理员表的表名
     * @return
     */
    public boolean resetPassword(String Eh_id, String resetPassword, String tablename) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean flag = false;
        String sql;
        sql = "update " + tablename + " set password='" + resetPassword + "' where Eh_id='" + Eh_id + "' and status='1' ";
        try {
            PublicDBhandles.init(sql);
            flag = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过商户ID得到用户账号级别
     * 
     * @param u_id
     * @return
     */
    public String getuserid(String Eh_id, String tablename) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String name = "";
        String sql = "select EhNumber from " + tablename + " where Eh_id='" + Eh_id + "' and status='1' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                name = rs.getString("EhNumber");
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
        return name;
    }
    
    /**
     * 用户查询 ，查询符合条件的房屋信息
     * 
     * @param pagesize
     * @param pagenum
     * @return
     * @throws Exception
     */
    public List<TB_Estate_House> getTB_HouseReport(String condition ) throws Exception {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_House> list = new ArrayList<TB_Estate_House>();
        String sql = "select a.* ,b.BuName ,c.EsName,d.UnName from TB_Estate_House   a left join TB_Estate_Build b on a.ts_id=b.ts_id and a.Es_id=b.Es_id" +
                " and a.Bu_id=b.Bu_id left join TB_Estate_Village c on a.ts_id=c.ts_id and a.Es_id=c.Es_id left join TB_Estate_Unit d on a.un_id =d.un_id  " +
             		"where " + condition + " order by a.EhNumber asc";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_House teh=null;
            while (rs.next()) {
                teh = new TB_Estate_House();
                teh.setBu_id(rs.getInt("Bu_id"));
                teh.setUn_id(rs.getInt("Un_id"));
                teh.setBuildArea(rs.getString("BuildArea") == null ? "" : rs.getString("BuildArea"));
                teh.setCarNum(rs.getInt("carNum"));
                teh.setCreat_time(rs.getString("creat_time") == null ? "" : rs.getString("creat_time"));
                teh.setEh_id(rs.getInt("eh_id"));
                teh.setEhName(rs.getString("ehName") == null ? "" : rs.getString("ehName"));
                teh.setEhNature(rs.getString("ehNature") == null ? "" : rs.getString("ehNature"));
                teh.setEhNumber(rs.getString("ehNumber") == null ? "" : rs.getString("ehNumber"));
                teh.setEhStatus(rs.getString("ehStatus") == null ? "" : rs.getString("ehStatus"));
                teh.setEhStru(rs.getString("ehStru") == null ? "" : rs.getString("ehStru"));
                teh.setEhTurn(rs.getString("ehTurn") == null ? "" : rs.getString("ehTurn"));
                teh.setEhType(rs.getString("ehType") == null ? "" : rs.getString("ehType"));
                teh.setEs_id(rs.getInt("es_id"));
                teh.setHeatingArea(rs.getString("heatingArea") == null ? "" : rs.getString("heatingArea"));
                teh.setHousingUse(rs.getString("housingUse") == null ? "" : rs.getString("housingUse"));
                teh.setOftenNumber(rs.getInt("oftenNumber"));
                teh.setOwnerName(rs.getString("ownerName") == null ? "" : rs.getString("ownerName"));
                teh.setOwnerPhone(rs.getString("ownerPhone") == null ? "" : rs.getString("ownerPhone"));
                teh.setPropertyRight(rs.getString("propertyRight") == null ? "" : rs.getString("propertyRight"));
                teh.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
                teh.setStatus(rs.getInt("status"));
                teh.setTs_id(rs.getInt("ts_id"));
                teh.setUseArea(rs.getString("useArea") == null ? "" : rs.getString("useArea"));
                teh.setEsName(rs.getString("EsName") == null ? "" : rs.getString("EsName"));
                teh.setBuName(rs.getString("BuName") == null ? "" : rs.getString("BuName"));
                teh.setUnName(rs.getString("unName") == null ? "" : rs.getString("unName"));
                list.add(teh);
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
     * 根据房屋编号查询户主姓名
     * 
     * @param u_id
     * @return
     */
    public String getEsName(String Condition) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String name = "该房屋编号不存在&";
        String sql = "select OwnerName,BuildArea,Bu_id from TB_Estate_House where status='1' "+Condition;
      //  System.out.println(sql);
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
            	 name =rs.getString("OwnerName")==null?"":rs.getString("OwnerName")+"#"+rs.getString("Bu_id");
            	 name+="&"+(rs.getString("BuildArea")==null?"0":rs.getString("BuildArea"));
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
        return name;
    }

    /**
     * 批量删除房屋信息
     * 
     * @param
     * @return
     */
    public boolean deletePl_TB_House( String ts_id,String Eh_ids) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        String Eh_id = Eh_ids.replaceAll(";", "','");
        try {
            String sql = "update TB_Estate_House set status='0' where Eh_id in ('"+Eh_id+"')  and ts_id='" + ts_id + "' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }
    /**
     * 房屋类型查询remark为空的
     * @return
     */
  public List<TB_Estate_Housetype> findHouType(){
	  List<TB_Estate_Housetype> list=new ArrayList<TB_Estate_Housetype>();
	  TB_Estate_Housetype housetype=null;
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      String sql="select Ht_id,HtName from TB_Estate_Housetype where status=1  and  remark1 is null";
      pu.init(sql);
      rs=pu.Query();
      try {
		while(rs.next()){
			housetype=new  TB_Estate_Housetype();
			housetype.setHt_id(rs.getInt("ht_id"));
			housetype.setHtName(rs.getString("htName"));
			list.add(housetype);
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
	  return list;
  }
  
  /**
   * 根据小区id，查询房屋类型 remark1代表小区
   * @return
   */
public List<TB_Estate_Housetype> findHouseType(String es_id){
  List<TB_Estate_Housetype> list=new ArrayList<TB_Estate_Housetype>();
  TB_Estate_Housetype housetype=null;
  PublicDBhandles pu = new PublicDBhandles();
    ResultSet rs = null;
    String sql="select Ht_id,HtName from TB_Estate_Housetype where status=1  and  remark1='"+es_id+"'";
    pu.init(sql);
    rs=pu.Query();
    try {
    while(rs.next()){
      housetype=new  TB_Estate_Housetype();
      housetype.setHt_id(rs.getInt("ht_id"));
      housetype.setHtName(rs.getString("htName"));
      list.add(housetype);
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
    return list;
}
  
  
  
}
