/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import WYBack_Stage.Bean.TB_Estate_Order;
import WYBack_Stage.Bean.TB_Estate_item;
import ccbjf.system.db.PublicDBhandles;

/**
 * <p> 固额 <p>
 * 
 * @date 2018-3-18 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class Gue_Excel_Dao {
    public List<TB_Estate_item> getTB_Item(String ts_id, String a_id, String es_id) {
        ResultSet rs = null;
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_item> list = new ArrayList<TB_Estate_item>();
        String sql = "select itemName from TB_Estate_item where ts_id='" + ts_id + "' and a_id='" + a_id + "' and Es_id='" + es_id
                + "' and status='1'";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_item item=null;
            while (rs.next()) {
                item = new TB_Estate_item();
                item.setItemName(rs.getString("itemName") == null ? "" : rs.getString("itemName"));
                list.add(item);
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

    public List<TB_Estate_item> getTB_Itemid(String ts_id, String a_id, String es_id) {
        ResultSet rs = null;
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_item> list = new ArrayList<TB_Estate_item>();
        String sql = "select Ei_id,itemName from TB_Estate_item where ts_id='" + ts_id + "' and a_id='" + a_id + "' and Es_id='" + es_id
                + "'  and status='1'";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_item item =null;
            while (rs.next()) {
                item = new TB_Estate_item();
                item.setEi_id(rs.getInt("Ei_id"));
                item.setItemName(rs.getString("itemName") == null ? "" : rs.getString("itemName"));
                list.add(item);
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
     * 查询房屋对应缴费项的费用起止期
     * 
     * @param ts_id
     * @param a_id
     * @param es_id
     * @param bu_id
     * @param eh_id
     * @param payItem
     * @return
     */
    public List<TB_Estate_Order> getCost_time(String ts_id, String a_id, String es_id, String bu_id, String eh_id, String payItem) {
        ResultSet rs = null;
        PublicDBhandles pu = new PublicDBhandles();
        List<TB_Estate_Order> list = new ArrayList<TB_Estate_Order>();
        String sql = "select Cost_startTime,Cost_endTime from TB_Estate_Order where ts_id='" + ts_id + "' and a_id='" + a_id + "' and Es_id='"
                + es_id + "' and Bu_id='" + bu_id + "' and Eh_id='" + eh_id + "' and payItem='" + payItem + "' and orderType='3' and status='1'";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Order ieo =null;
            while (rs.next()) {
                ieo = new TB_Estate_Order();
                ieo.setCost_startTime(rs.getString("Cost_startTime"));
                ieo.setCost_endTime(rs.getString("Cost_endTime"));
                list.add(ieo);
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

    public static List<Date> getMonthBetweenDate(Date beginDate, Date endDate) {
        if (beginDate.getTime() == endDate.getTime()) {
            return null;
        }
        List lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    /**
     * 判断时间是否在时间段内
     * 
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
