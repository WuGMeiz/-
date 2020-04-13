package WYCommunity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Bean.TB_HMBean;
import WYBack_Stage.Dao.Mete_ReadClass;

public class MeteChaobiao_Excel {
    /**
     * 重新修改后的 用户批量导入读取Excel内容
     * 
     * @param FilePathName
     * @param org_id
     * @return
     * @author yf
     */
    public List<List<String>> getMeteList(String FilePathName) {
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
                String Ma_id = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(1, i).getContents()));
                sql.add(Ma_id);
                String LastReadNum = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(2, i).getContents()));
                sql.add(LastReadNum);
                String NowReadNum = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(3, i).getContents()));
                sql.add(NowReadNum);
                String ReadDate = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(4, i).getContents()));
                sql.add(ReadDate);
                if (S_string.isZX(NowReadNum) == false || S_string.isZX(LastReadNum) == false) {
                    sql.add(0 + "");
                } else {
                    double usernumber = Double.parseDouble(NowReadNum) - Double.parseDouble(LastReadNum);
                    sql.add(usernumber + "");
                }
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
    public String Excel_check_orders1(List<List<String>> list, String ts_id, String xiaoquid, String louid, String type, String Un_id,String LEVELS,String tu_id) {
        String str = "";
        String temp = "";
        String temp1 = "";
        String temp2 = "";
        String temp3 = "";
        String temp4 = "";
        try {
            for (int i = 0; i < list.size(); i++) {
                List<String> liststr = list.get(i);
                // 循环得到工作表Excel当前的行的所有列，并且拼成SQL
                String EhNumber = liststr.get(0);
                if (EhNumber.equals("")) {
                    temp1 += (i + 3) + "行1列,";
                } else {
                    // 查询小区下房屋信息
                    Map<String, String> map = new Mete_ReadClass().findHoseMap1(ts_id, xiaoquid, louid, Un_id, LEVELS, tu_id);
                   // if (!S_string.isNum(EhNumber)) {
                    //    str += (i + 3) + "行1列,";
                  //  } else {
                        if (map.size() == 0) {
                            temp2 += (i + 3) + "行1列,";
                        } else {
                            if (!map.containsKey(EhNumber)) {
                                temp3 += (i + 3) + "行1列,";
                            }
                        }
                    //}
                    // 表编号
                    String Ma_id = liststr.get(1);
                    if (Ma_id.equals("")) {
                        temp1 += (i + 3) + "行2列,";
                    }
                    // 上次表度数
                    String LastReadNum = liststr.get(2);
                    if (!LastReadNum.equals("")) {
                        if (!S_string.isZX(LastReadNum)) {
                            str += (i + 3) + "行3列,";
                        } else {
                            // 本次表读数
                            String NowReadNum = liststr.get(3);
                            if (!NowReadNum.equals("")) {
                                if (!S_string.isZX(NowReadNum)) {
                                    str += (i + 3) + "行4列,";
                                } else {
                                    if (((Double.parseDouble(NowReadNum)) - (Double.parseDouble(LastReadNum))) < 0) {
                                        temp4 += (i + 3) + "行3/4列,";
                                    }
                                }
                            } else {
                                temp1 += (i + 3) + "行4列,";
                            }
                        }
                    } else {
                        temp1 += (i + 3) + "行3列,";
                    }
                    // 抄表日期
                    String ReadDate = liststr.get(4);
                    if (!ReadDate.equals("")) {
                        if (S_string.isDate(ReadDate) == true) {
                            boolean bl = new Mete_ReadClass().findMeteDate(ts_id, ReadDate, EhNumber, xiaoquid, louid, type);
                            if (bl != false) {
                                temp += (i + 3) + "行5列,";
                            }
                        } else {
                            str += (i + 3) + "行5列,";
                        }
                    } else {
                        temp1 += (i + 3) + "行5列,";
                    }
                }
            }
            if (!str.equals("")) {
                str = str + "数据格式错误;";
            }
            if (!temp.equals("")) {
                temp += "该房屋编号已经存在本次抄表日期记录;";
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
            if (!temp4.equals("")) {
                temp4 += "本次抄表数不能小于上次抄表数;";
            }
            str = str + temp + temp1 + temp2 + temp3 + temp4;
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
    public String Excel_check_orders(List<List<String>> list, String ts_id, String xiaoquid, String louid, String type, String Un_id) {
        String str = "";
        String temp = "";
        String temp1 = "";
        String temp2 = "";
        String temp3 = "";
        String temp4 = "";
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
                   // if (!S_string.isNum(EhNumber)) {
                    //    str += (i + 3) + "行1列,";
                  //  } else {
                        if (map.size() == 0) {
                            temp2 += (i + 3) + "行1列,";
                        } else {
                            if (!map.containsKey(EhNumber)) {
                                temp3 += (i + 3) + "行1列,";
                            }
                        }
                    //}
                    // 表编号
                    String Ma_id = liststr.get(1);
                    if (Ma_id.equals("")) {
                        temp1 += (i + 3) + "行2列,";
                    }
                    // 上次表度数
                    String LastReadNum = liststr.get(2);
                    if (!LastReadNum.equals("")) {
                        if (!S_string.isZX(LastReadNum)) {
                            str += (i + 3) + "行3列,";
                        } else {
                            // 本次表读数
                            String NowReadNum = liststr.get(3);
                            if (!NowReadNum.equals("")) {
                                if (!S_string.isZX(NowReadNum)) {
                                    str += (i + 3) + "行4列,";
                                } else {
                                    if (((Double.parseDouble(NowReadNum)) - (Double.parseDouble(LastReadNum))) < 0) {
                                        temp4 += (i + 3) + "行3/4列,";
                                    }
                                }
                            } else {
                                temp1 += (i + 3) + "行4列,";
                            }
                        }
                    } else {
                        temp1 += (i + 3) + "行3列,";
                    }
                    // 抄表日期
                    String ReadDate = liststr.get(4);
                    if (!ReadDate.equals("")) {
                        if (S_string.isDate(ReadDate) == true) {
                            boolean bl = new Mete_ReadClass().findMeteDate(ts_id, ReadDate, EhNumber, xiaoquid, louid, type);
                            if (bl != false) {
                                temp += (i + 3) + "行5列,";
                            }
                        } else {
                            str += (i + 3) + "行5列,";
                        }
                    } else {
                        temp1 += (i + 3) + "行5列,";
                    }
                }
            }
            if (!str.equals("")) {
                str = str + "数据格式错误;";
            }
            if (!temp.equals("")) {
                temp += "该房屋编号已经存在本次抄表日期记录;";
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
            if (!temp4.equals("")) {
                temp4 += "本次抄表数不能小于上次抄表数;";
            }
            str = str + temp + temp1 + temp2 + temp3 + temp4;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    /**
     * 抄表信息插入到数据库
     * 
     * @param list
     * @param ordtype
     * @return
     * @throws Exception
     */
    public String insert_order(List<List<String>> list, String ts_id, String type, String unit, String xiaoquid, String louid,String Un_id) throws Exception {
        String Eh_id = "";
        String returnstr = "";
        List<List<String>> sqllist = new ArrayList<List<String>>();
        try {
            // System.out.println("订单拼串开始："+new T_time().getTime());
            for (int i = 0; i < list.size(); i++) {
                List<String> liststr = (List<String>) list.get(i);
                // 查询小区下的房屋编号 和主键
                List<TB_Estate_House> listHouse = new Mete_ReadClass().findHose(ts_id, xiaoquid, louid,Un_id);
                String EhNumber = liststr.get(0);
                for (TB_Estate_House teh : listHouse) {
                    if (teh.getEhNumber().equals(EhNumber)) {
                        Eh_id = teh.getEh_id() + "";
                    }
                }
                String Ma_id = liststr.get(1);
                String LastReadNum = liststr.get(2);
                String NowReadNum = liststr.get(3);
                String ReadDate = liststr.get(4);
                String UserNumber = liststr.get(5);
                List<String> sql = new ArrayList<String>();
                sql.add(ts_id);
                sql.add(Eh_id);
                sql.add(Ma_id);
                sql.add(LastReadNum);
                sql.add(NowReadNum);
                sql.add(ReadDate);
                sql.add(UserNumber);
                sql.add("1");
                sql.add("0");
                sql.add(type);
                sql.add(unit);
                sqllist.add(sql);
            }
            String sql1 = "insert into TB_Estate_Mation (ts_id,Eh_id,Ma_id,LastReadNum,NowReadNum,ReadDate,UserNumber,status,orderStatus,type,unit) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            returnstr = MyTBCommit.TBCommitp(sqllist, sql1);
            // System.out.println("订单插入结束："+new T_time().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnstr;
    }

    /**
     * 订单信息生成报表
     * 
     * @param map 存放前台获取参数
     * @param FilePath
     * @param FileName
     */
    public boolean Excel_out_Mete(String ts_id, String FilePath, String FileName, String Condition) throws Exception {
        boolean bl = true;
        try {
            deleteFile.deleteisFile(FilePath, FileName);
            // 标题字体设置
            WritableCellFormat titlewcfF = new WritableCellFormat(new WritableFont(WritableFont.createFont("黑体"), 16, WritableFont.BOLD));
            titlewcfF.setAlignment(Alignment.CENTRE);// 标题水平居中
            titlewcfF.setVerticalAlignment(VerticalAlignment.CENTRE);// 标题垂直居中
            // 列头标字体设置
            WritableCellFormat format1 = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD));
            format1.setAlignment(Alignment.CENTRE);// 标题水平居中
            format1.setVerticalAlignment(VerticalAlignment.CENTRE);// 标题垂直居中
            WritableCellFormat format2 = new WritableCellFormat();
            format2.setAlignment(Alignment.CENTRE);// 标题水平居中
            format2.setVerticalAlignment(VerticalAlignment.CENTRE);// 标题垂直居中
            // 创建文件 、sheet表单
            WritableWorkbook wbook = Workbook.createWorkbook(new File(FilePath + FileName));
            WritableSheet sheet = wbook.createSheet("sheet1", 0);
            // 设置
            sheet.setRowView(0, 600);// 将第一行的高度设为800
            int colum = 0;
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 20);
            sheet.setColumnView(colum++, 15);
            sheet.setColumnView(colum++, 15);
            sheet.setColumnView(colum++, 15);
            StringBuffer headStr = new StringBuffer();
            headStr.append("小区名称;楼宇;单元;房屋编号;表名称;上次抄表数;本次抄表数;用量;抄表日期;订单状态;类型");
            String[] heads = headStr.toString().split(";");
            Label label;
            sheet.mergeCells(0, 0, 10, 0);// 列，行
            label = new Label(0, 0, "抄表信息", titlewcfF);
            sheet.addCell(label);
            // 写入表头
            for (int i = 0; i < heads.length; i++) {
                label = new Label(i, 1, heads[i], format1);
                sheet.addCell(label);
            }
            //Map<String, String> mapEs = new Mete_ReadClass().findxqName(ts_id);
            //Map<String, String> mapBu = new Mete_ReadClass().findLyuName(ts_id);
            List<TB_HMBean> list = new Mete_ReadClass().findHM_ExceOut(Condition);
            TB_HMBean thmb = null;
            /* String heji=((TBorders) list.get(list.size()-1)).getD17(); */
            // 写入数据
            for (int row = 0; row < list.size(); row++) {
                String s = "";
                String type = "";
                thmb = new TB_HMBean();
                colum = 0;
                thmb = list.get(row);
                sheet.addCell(new Label(colum++, row + 2, thmb.getEsName(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getBuName(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getUnName(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getEhNumber(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getMa_id() + "", format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getLastReadNum(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getNowReadNum(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getUserNumber(), format2));
                sheet.addCell(new Label(colum++, row + 2, thmb.getReadDate(), format2));
                if (thmb.getOrderStatus() == 0) {
                    s = "未生成收费单";
                } else if (thmb.getOrderStatus() == 1) {
                    s = "已成收费单";
                }
                sheet.addCell(new Label(colum++, row + 2, s, format2));
                if (thmb.getType() == 0) {
                    type = "水费";
                } else if (thmb.getType() == 1) {
                    type = "电费";
                } else if (thmb.getType() == 2) {
                    type = "燃气费";
                }
                sheet.addCell(new Label(colum++, row + 2, type, format2));// 缴费状态
            }
            wbook.write();
            wbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            bl = false;
        }
        return bl;
    }

    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws WriteException
     */
    public boolean Excel_out_Mb1(String ts_id, String FilePath, String FileName, String xiaoquid, String louid, String Mtype, String unit)
            throws WriteException {
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
            WritableCellFormat format5 = new WritableCellFormat(NumberFormats.TEXT);
            format5.setFont(font1);
            CellView cv = new CellView(); //定义一个列显示样式 
            cv.setFormat(format5);//把定义的单元格格式初始化进去
            cv.setSize(10*500);//设置列宽度（不设置的话是0，不会显示）
            /*** titlewcfF为标题字体 ***/
            WritableCellFormat titlewcfF = new WritableCellFormat(new WritableFont(WritableFont.createFont("黑体"), 18, WritableFont.BOLD));
            // 标题水平居中
            titlewcfF.setAlignment(Alignment.CENTRE);
            // 标题垂直居中
            titlewcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 获得对应的item表的name,list
            Label label;
            // sheet.mergeCells(0, 0, 3+item.size(), 0);
            sheet.mergeCells(0, 0, 4, 0);
            String title = "批量录入抄表信息";
            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
            label = new Label(0, 0, title, titlewcfF);
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // String user_id = "1";
            // 设置行高
            sheet.setRowView(0, 1000);// 将第一行的高度设为800
            sheet.setColumnView(0, 20);
            sheet.setColumnView(1, 30);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 15);
            sheet.setColumnView(4, 30);
            sheet.addCell(new Label(0, 1, "房屋编号", format1));
            sheet.mergeCells(0, 1, 0, 1);
            sheet.addCell(new Label(1, 1, "表名称(水表/电表)", format1));
            sheet.mergeCells(1, 1, 1, 1);
            sheet.addCell(new Label(2, 1, "上次抄表数", format1));
            sheet.mergeCells(2, 1, 2, 1);
            sheet.addCell(new Label(3, 1, "本次抄表数", format1));
            sheet.mergeCells(3, 1, 3, 1);
            sheet.addCell(new Label(4, 1, "抄表日期(yyyy-MM-dd)", format1));
            sheet.mergeCells(4, 1, 4, 1);
            sheet.setColumnView(4,cv);//设置单元格的格式
            sheet.setRowView(1, 800);
            String con = "";
            if (louid.equals("")) {
                con = " b.status=1 and a.ts_id='" + ts_id + "' and b.es_id='" + xiaoquid + "' and a.status=1";
            } else {
                con = " b.status=1 and a.ts_id='" + ts_id + "' and b.es_id='" + xiaoquid + "' and b.bu_id='" + louid + "' and a.status=1";
            }
            List<TB_HMBean> list = new Mete_ReadClass().findHM_Out(con, Mtype, unit, ts_id);
            TB_HMBean tbh = null;
            int colum = 0;
            for (int row = 0; row < list.size(); row++) {
                String type = "";
                tbh = new TB_HMBean();
                colum = 0;
                tbh = list.get(row);
                sheet.addCell(new Label(colum++, row + 2, tbh.getEhNumber(), format2));
                /*
                 * if(tbh.getType()==0){ type="水费"; }else if(tbh.getType()==1){
                 * type="电费"; }else if(tbh.getType()==2){ type="燃气费"; }
                 * sheet.addCell(new Label(colum++,row+2,type,format2));
                 */
                sheet.addCell(new Label(colum++, row + 2, tbh.getMa_id() + "", format2));
                sheet.addCell(new Label(colum++, row + 2, tbh.getNowReadNum(), format2));
                sheet.addCell(new Label(colum++, row + 2, "", format2));
                sheet.addCell(new Label(colum++, row + 2, "", format2));
            }
            wbook.write(); // 写入文件
            wbook.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
