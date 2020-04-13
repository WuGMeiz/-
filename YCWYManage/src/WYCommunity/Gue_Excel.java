/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYCommunity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Bean.TB_Estate_Order;
import WYBack_Stage.Bean.TB_Estate_item;
import WYBack_Stage.Dao.Gue_Excel_Dao;
import WYBack_Stage.Dao.Mete_ReadClass;
import WYBack_Stage.Dao.MyTB_House_DAO;

/**
 * <p> 固额订单批量导入 <p>
 * 
 * @date 2018-3-15 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class Gue_Excel {
    /**
     * 
     * @param org_id
     * @param FilePath
     * @param FileName
     * @return
     * @throws WriteException
     */
    public boolean Excel_out_Mb1(String org_id, String a_id, String es_id, String FilePath, String FileName) throws WriteException {
        try {
            // 打开文件
            WritableWorkbook wbook = Workbook.createWorkbook(new File(FilePath + FileName));
            WritableSheet sheet = wbook.createSheet("sheet1", 0);
            WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);// ①
                                                                                                        // 指定了字串格式：字体为TIMES，字号16，加粗显示。
            WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);// ①
                                                                                                        // 指定了字串格式：字体为TIMES，字号16，加粗显示。
            WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.RED);// ① 指定了字串格式：字体为TIMES，字号16，加粗显示。
            WritableCellFormat format1 = new WritableCellFormat(font1);// ②处代码使用了WritableCellFormat类，这个类非常重要，通过它可以指定单元格的各种属性。
            format1.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
            format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
            format1.setWrap(true);// 设置自动换行
            format1.setBackground(Colour.GRAY_25);
            format1.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
            format1.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
            format1.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
            format1.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
            // 设置数据格式
            WritableCellFormat format2 = new WritableCellFormat(font2);// ②处代码使用了WritableCellFormat类，这个类非常重要，通过它可以指定单元格的各种属性。
            format2.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
            format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
            format2.setWrap(true);// 设置自动换行
            format2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
            format2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
            format2.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
            format2.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
            WritableCellFormat format3 = new WritableCellFormat(font3);// ②处代码使用了WritableCellFormat类，这个类非常重要，通过它可以指定单元格的各种属性。
            format3.setAlignment(jxl.format.Alignment.LEFT);// 把水平对齐方式指定为居中
            format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
            format3.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
            format3.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
            format3.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
            format3.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
            /*** titlewcfF为标题字体 ***/
            WritableCellFormat titlewcfF = new WritableCellFormat(new WritableFont(WritableFont.createFont("黑体"), 18, WritableFont.BOLD));
            // 标题水平居中
            titlewcfF.setAlignment(Alignment.CENTRE);
            // 标题垂直居中
            titlewcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 获得对应的item表的name,list
            Label label;
            // sheet.mergeCells(0, 0, 3+item.size(), 0);
            List<TB_Estate_item> list1 = new Gue_Excel_Dao().getTB_Item(org_id, a_id, es_id);
            sheet.setRowView(0, 1000);// 将第一行的高度设为800
            sheet.setColumnView(0, 15);// 房屋编号
            sheet.setColumnView(1, 15);// 业主姓名
            sheet.setColumnView(2, 15);// 业主电话
            if (list1.size() > 0) {
                for (int i = 0; i < list1.size(); i++) {
                    sheet.setColumnView(i + 3, 15);// 缴费项
                }
            }
            sheet.setColumnView(list1.size() + 3, 15);// 备注
            /*
             * sheet.setColumnView(2, 15);//本次抄表数 sheet.setColumnView(3,
             * 15);//抄表日期 sheet.setColumnView(4, 15);//用量数
             * sheet.setColumnView(5, 15);//用量数
             */
            sheet.addCell(new Label(0, 1, "房屋编号", format1));
            sheet.mergeCells(0, 1, 0, 1);
            sheet.addCell(new Label(1, 1, "业主姓名", format1));
            sheet.mergeCells(1, 1, 1, 1);
            sheet.addCell(new Label(2, 1, "业主电话", format1));
            sheet.mergeCells(2, 1, 2, 1);
            if (es_id.equals("")) {
                return false;
            }
            if (list1.size() > 0) {
                for (int i = 0; i < list1.size(); i++) {
                    TB_Estate_item ite = (TB_Estate_item) list1.get(i);
                    String itemname = ite.getItemName();
                    sheet.addCell(new Label(i + 3, 1, itemname, format1));
                    sheet.mergeCells(i + 3, 1, i + 3, 1); // 缴费项
                }
            } else {
                return false;
            }
            sheet.mergeCells(0, 0, list1.size() + 3, 0);
            String title = "批量导入信息模版";
            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
            label = new Label(0, 0, title, titlewcfF);
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // 设置行高
            sheet.addCell(new Label(list1.size() + 3, 1, "备注", format1));
            sheet.mergeCells(list1.size() + 3, 1, list1.size() + 3, 1);
            sheet.setRowView(1, 400);
            wbook.write(); // 写入文件
            wbook.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 重新修改后的 固额订单批量导入读取Excel内容
     * 
     * @param FilePathName
     * @param org_id
     * @return
     * @author yf
     */
    public List<List<String>> getMeteList(String FilePathName, String ts_id, String a_id, String es_id) {
        List<List<String>> list = null;
        try {
            // System.out.println("excel表读取开始："+new T_time().getTime());
            Workbook book = Workbook.getWorkbook(new File(FilePathName));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rownum = sheet.getRows();
            // int colnum1=sheet.getColumns();//工作表列数
            list = new ArrayList<List<String>>();
            for (int i = 2; i < rownum; i++) {
                List<String> sql = new ArrayList<String>();
                // 循环得到工作表Excel当前的行的所有列，并且拼成SQL
                String EhNumber = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(0, i).getContents()));
                sql.add(EhNumber);
                String OwnerName = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(1, i).getContents()));
                sql.add(OwnerName);
                String OwnerPhone = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(2, i).getContents()));
                sql.add(OwnerPhone);
                List<TB_Estate_item> list2 = new Gue_Excel_Dao().getTB_Item(ts_id, a_id, es_id);
                if (list2.size() > 0) {
                    for (int j = 0; j < list2.size(); j++) {
                        // TB_Estate_item ite=(TB_Estate_item)list2.get(j);
                        // String itemname =ite.getItemName();
                        String total = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(j + 3, i).getContents()));
                        sql.add(total);
                    }
                }
                String remark = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(list2.size() + 3, i).getContents()));
                sql.add(remark);
                list.add(sql);
            }
            book.close();
            // System.out.println("excel表读取结束："+new T_time().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 对导入数据是否存在及数据格式进行检查
     * 
     * @param list
     * @param org_id
     * @param u_id
     * @param a_id
     * @return
     */
    public String Excel_check_orders(List<List<String>> list, String ts_id, String xiaoquid, String louid, String a_id, String Un_id) {
        String str = "";
        String temp = "";
        String temp1 = "";
        String temp2 = "";
        String temp3 = "";
        try {
            for (int i = 0; i < list.size(); i++) {
                List<String> liststr = list.get(i);
                // 循环得到工作表Excel当前的行的所有列，并且拼成SQL
                String EhNumber = liststr.get(0);
                if (EhNumber.equals("")) {
                    temp1 += (i + 3) + "行1列,";
                } else {
                    // 查询小区下房屋信息
                    Map<String, String> map = new Mete_ReadClass().findHoseMap(ts_id, xiaoquid, louid, Un_id);
                   /* if (!S_string.isNum(EhNumber)) {
                        str += (i + 3) + "行1列,";
                    } else {*/
                        if (map.size() == 0) {
                            temp2 += (i + 3) + "行1列,";
                        } else {
                            if (!map.containsKey(EhNumber)) {
                                temp3 += (i + 3) + "行1列,";
                            }
                        }
                  //  }
                    List<TB_Estate_item> list3 = new Gue_Excel_Dao().getTB_Item(ts_id, a_id, xiaoquid);
                    if (list3.size() > 0) {
                        for (int j = 0; j < list3.size(); j++) {
                            // TB_Estate_item ite=(TB_Estate_item)list3.get(j);
                            // String itemname =ite.getItemName();
                            String total = liststr.get(j + 3);
                            if (!total.equals("")) {
                                if (!S_string.isPrice(total)) {
                                    int k = j + 4;
                                    String je = "行" + k + "列,";
                                    str += (i + 3) + je;
                                }
                            } else {
                                int k = j + 4;
                                String je = "行" + k + "列,";
                                temp1 += (i + 3) + je;
                            }
                        }
                    }
                    // 备注
                    String remark = liststr.get(list3.size() + 3);
                    if (!remark.equals("")) {
                        if (S_string.isTszf(remark)) {
                            int m = list3.size() + 4;
                            String re = "行" + m + "列,";
                            str += (i + 3) + re;
                        }
                    }
                }
            }
            if (!str.equals("")) {
                str = str + "数据格式错误;";
            }
            if (!temp1.equals("")) {
                temp1 += "数据不能为空;";
            }
            if (!temp2.equals("")) {
                temp2 += "该小区或楼宇或单元下暂无房屋信息;";
            }
            if (!temp3.equals("")) {
                temp3 += "系统不存在此房屋编号;";
            }
            str = str + temp1 + temp2 + temp3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    /**
     * 对导入数据是否存在及数据格式进行检查
     * 
     * @param list
     * @param org_id
     * @param u_id
     * @param a_id
     * @return
     */
    public String Excel_check_orders1(List<List<String>> list, String ts_id, String xiaoquid, String louid, String a_id, String Un_id,String LEVELS,String tu_id) {
        String str = "";
        String temp = "";
        String temp1 = "";
        String temp2 = "";
        String temp3 = "";
        try {
            for (int i = 0; i < list.size(); i++) {
                List<String> liststr = list.get(i);
                // 循环得到工作表Excel当前的行的所有列，并且拼成SQL
                String EhNumber = liststr.get(0);
                if (EhNumber.equals("")) {
                    temp1 += (i + 3) + "行1列,";
                } else {
                    // 查询小区下房屋信息
                    Map<String, String> map = new Mete_ReadClass().findHoseMap1(ts_id, xiaoquid, louid, Un_id,LEVELS, tu_id);
                   /* if (!S_string.isNum(EhNumber)) {
                        str += (i + 3) + "行1列,";
                    } else {*/
                        if (map.size() == 0) {
                            temp2 += (i + 3) + "行1列,";
                        } else {
                            if (!map.containsKey(EhNumber)) {
                                temp3 += (i + 3) + "行1列,";
                            }
                        }
                  //  }
                    List<TB_Estate_item> list3 = new Gue_Excel_Dao().getTB_Item(ts_id, a_id, xiaoquid);
                    if (list3.size() > 0) {
                        for (int j = 0; j < list3.size(); j++) {
                            // TB_Estate_item ite=(TB_Estate_item)list3.get(j);
                            // String itemname =ite.getItemName();
                            String total = liststr.get(j + 3);
                            if (!total.equals("")) {
                                if (!S_string.isPrice(total)) {
                                    int k = j + 4;
                                    String je = "行" + k + "列,";
                                    str += (i + 3) + je;
                                }
                            } else {
                                int k = j + 4;
                                String je = "行" + k + "列,";
                                temp1 += (i + 3) + je;
                            }
                        }
                    }
                    // 备注
                    String remark = liststr.get(list3.size() + 3);
                    if (!remark.equals("")) {
                        if (S_string.isTszf(remark)) {
                            int m = list3.size() + 4;
                            String re = "行" + m + "列,";
                            str += (i + 3) + re;
                        }
                    }
                }
            }
            if (!str.equals("")) {
                str = str + "数据格式错误;";
            }
            if (!temp1.equals("")) {
                temp1 += "数据不能为空;";
            }
            if (!temp2.equals("")) {
                temp2 += "该小区或楼宇或单元下暂无房屋信息;";
            }
            if (!temp3.equals("")) {
                temp3 += "系统不存在此房屋编号;";
            }
            str = str + temp1 + temp2 + temp3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    /**
     * 定额订单信息插入到数据库
     * 
     * @param list
     * @param ordtype
     * @return
     * @throws Exception
     */
    public String insert_order(List<List<String>> list, String a_id, String ts_id, String cost_startTime, String cost_endTime, String xiaoquid,
            String louid, String Un_id) throws Exception {
        
        String returnstr = "";
        String Bu_id = "";
        List<List<String>> sqllist = new ArrayList<List<String>>();
        try {
            // System.out.println("订单拼串开始："+new T_time().getTime());
        	List<TB_Estate_House> listHouse = new Mete_ReadClass().findHose(ts_id, xiaoquid, louid, Un_id);
            for (int i = 0; i < list.size(); i++) {
                List<String> liststr = (List<String>) list.get(i);
                // 查询小区下的房屋编号 和主键
                String Eh_id = "";
                String EhNumber = liststr.get(0);
                for (TB_Estate_House teh : listHouse) {
                    if (teh.getEhNumber().equals(EhNumber)) {
                       Eh_id = teh.getEh_id() + "";
                       String str = new MyTB_House_DAO().getBuUnid(ts_id, Eh_id);
                       Bu_id = str.split("\\|")[0];
                       Un_id = str.split("\\|")[1];
                    }
                }
                List<TB_Estate_item> list4 = new Gue_Excel_Dao().getTB_Itemid(ts_id, a_id, xiaoquid);
                if (list4.size() > 0) {
                    for (int j = 0; j < list4.size(); j++) {
                        TB_Estate_item ite = (TB_Estate_item) list4.get(j);
                        String total = liststr.get(j + 3);
                        if (!total.equals("0")) {
                            String payItem = String.valueOf(ite.getEi_id());
                            String remark = liststr.get(list4.size() + 3);
                            List<String> sql = new ArrayList<String>();
                            sql.add("0");
                            sql.add(a_id);
                            sql.add(ts_id);
                            sql.add(xiaoquid);
                            sql.add(Bu_id);
                            sql.add(Un_id); 
                            sql.add(Eh_id);
                            sql.add(total);
                            sql.add(cost_startTime);
                            sql.add(cost_endTime);
                            sql.add(new T_time().getTime());
                            sql.add("1");
                            sql.add(remark);
                            sql.add("3");
                            sql.add(payItem);
                            sqllist.add(sql);
                        }
                    }
                }
            }
            String sql1 = "insert into TB_Estate_Order (payStatus,a_id,ts_id,Es_id,Bu_id,Un_id,Eh_id,total,cost_startTime,cost_endTime,creat_time,status,remark,orderType,payItem) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            returnstr = MyTBCommit.TBCommitp(sqllist, sql1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnstr;
    }

    /**
     * 检查该房屋的该缴费项费用起止期是否重合
     * 
     * @param list
     * @param a_id
     * @param ts_id
     * @param cost_startTime
     * @param cost_endTime
     * @param xiaoquid
     * @param louid
     * @return
     * @throws Exception
     */
    public boolean check_date(List<List<String>> list, String a_id, String ts_id, String cost_startTime, String cost_endTime, String xiaoquid,
            String louid,String Un_id) throws Exception {
        String Eh_id = "";
        String Bu_id = "";
        Boolean bl = false;
        try {
            // System.out.println("订单拼串开始："+new T_time().getTime());
            for (int i = 0; i < list.size(); i++) {
                List<String> liststr = (List<String>) list.get(i);
                // 查询小区下的房屋编号 和主键
                List<TB_Estate_House> listHouse = new Mete_ReadClass().findHose(ts_id, xiaoquid, louid, Un_id);
                String EhNumber = liststr.get(0);
                for (TB_Estate_House teh : listHouse) {
                    if (teh.getEhNumber().equals(EhNumber)) {
                        Eh_id = teh.getEh_id() + "";
                        Bu_id = new MyTB_House_DAO().getBuid(ts_id, Eh_id);
                    }
                }
                List<TB_Estate_item> list5 = new Gue_Excel_Dao().getTB_Itemid(ts_id, a_id, xiaoquid);
                if (list5.size() > 0) {
                    for (int j = 0; j < list5.size(); j++) {
                        TB_Estate_item ite = (TB_Estate_item) list5.get(j);
                        String total = liststr.get(j + 3);
                        if (!total.equals("0")) {
                            String payItem = String.valueOf(ite.getEi_id());
                            List<TB_Estate_Order> list6 = new Gue_Excel_Dao().getCost_time(ts_id, a_id, xiaoquid, Bu_id, Eh_id, payItem);
                            if (list6.size() > 0) {
                                for (int k = 0; k < list6.size(); k++) {
                                    TB_Estate_Order ieo = (TB_Estate_Order) list6.get(k);
                                    SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                    String st = ieo.getCost_startTime().substring(0, ieo.getCost_startTime().length());
                                    Date startTime = formatter.parse(st);
                                    String en = ieo.getCost_endTime().substring(0, ieo.getCost_endTime().length());
                                    Date endTime = formatter.parse(en);
                                    String dt = cost_startTime.substring(0, cost_startTime.length());
                                    Date datestart = formatter.parse(dt);
                                    String de = cost_endTime.substring(0, cost_endTime.length());
                                    Date dateend = formatter.parse(de);
                                    boolean falg1 = new Gue_Excel_Dao().belongCalendar(startTime, datestart, dateend);
                                    boolean falg2 = new Gue_Excel_Dao().belongCalendar(endTime, datestart, dateend);
                                    if (falg1 || falg2) {
                                        bl = false;
                                    } else {
                                        bl = true;
                                    }
                                }
                            } else {
                                bl = true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }
}
