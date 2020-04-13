package WYBack_Stage.Dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import WYCommunity.MD5;
import WYCommunity.S_string;
import WYCommunity.T_time;
import ccbjf.system.db.PublicDBhandle;
import ccbjf.system.db.PublicDBhandles;

public class quartersDao {
    /**
     * 批量导入房屋信息模板下载
     * 
     * @param org_id
     * @param FilePath
     * @param FileName
     * @return
     * @throws WriteException
     */
    public boolean Excel_out_Mb1(String org_id, String FilePath, String FileName) throws WriteException {
        // TODO Auto-generated method stub
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
            format3.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
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
            Label label;
            sheet.mergeCells(0, 0, 11, 0);
            String title = "房屋信息批量导入模版";
            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
            label = new Label(0, 0, title, titlewcfF);
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // 设置行高
            sheet.setRowView(0, 1000);// 将第一行的高度设为800
            sheet.setColumnView(0, 15);
            sheet.setColumnView(1, 22);
            sheet.setColumnView(2, 14);
            sheet.setColumnView(3, 14);
            sheet.setColumnView(4, 20);
            sheet.setColumnView(5, 14);
            sheet.setColumnView(6, 14);
            sheet.setColumnView(7, 14);
            sheet.setColumnView(8, 14);
            sheet.setColumnView(9, 14);
            sheet.setColumnView(10, 25);
            sheet.setColumnView(11, 30);
          
            sheet.addCell(new Label(0, 1, "房屋编号", format1));
            sheet.mergeCells(0, 1, 0, 1);
        
            sheet.addCell(new Label(1, 1, "房屋用途(商用/民用)", format1));
            sheet.mergeCells(1, 1, 1, 1);
            sheet.addCell(new Label(2, 1, "常住人口数", format1));
            sheet.mergeCells(2, 1, 2, 1);
            sheet.addCell(new Label(3, 1, "业主姓名", format1));
            sheet.mergeCells(3, 1, 3, 1);
            sheet.addCell(new Label(4, 1, "业主电话", format1));
            sheet.mergeCells(4, 1, 4, 1);
            sheet.addCell(new Label(5, 1, "建筑面积", format1));
            sheet.mergeCells(5, 1, 5, 1);
            sheet.addCell(new Label(6, 1, "使用面积", format1));
            sheet.mergeCells(6, 1, 6, 1);
            sheet.addCell(new Label(7, 1, "供暖面积", format1));
            sheet.mergeCells(7, 1, 7, 1);
            sheet.addCell(new Label(8, 1, "车位数", format1));
            sheet.mergeCells(8, 1, 8, 1);
            sheet.addCell(new Label(9, 1, "车位编号", format1));
            sheet.mergeCells(9, 1, 9, 1);
            sheet.addCell(new Label(10, 1, "楼层数(1,2...30)", format1));
            sheet.mergeCells(10, 1, 10, 1);
            sheet.addCell(new Label(11, 1, "是否交房(未交房/已交房)", format1));
            sheet.mergeCells(11, 1, 11, 1);
            
       
            sheet.setColumnView(9,cv);//设置单元格的格式
            sheet.setRowView(1, 800);
            wbook.write(); // 写入文件
            wbook.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户批量导入读取Excel内容
     * 
     * @param FilePathName
     * @param org_id
     * @return
     */
    public List<Map<String, String>> getTbPay_User_ReadExcel(String FilePathName) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Workbook book = Workbook.getWorkbook(new File(FilePathName));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rownum = sheet.getRows();
            int columns = sheet.getColumns()+6;
            String[] heads = {"EhNumber", "EhName", "EhNature", "EhStru", "EhType", "EhTurn", "EhStatus", "PropertyRight", "HousingUse",
                    "OftenNumber", "OwnerName", "OwnerPhone", "BuildArea", "UseArea", "HeatingArea", "CarNum", "remark", "storer", "handIs" };
            Map<String, String> map = null;
            // 增加验证 如果其修改导入模板 结构
            if (columns == heads.length) {
                for (int i = 2; i < rownum; i++) {
                    map = new HashMap<String, String>();
                    // 循环得到工作表Excel当前的行的所有列，并且拼成SQL
                    String EhNumber = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(0, i).getContents()));
                   /* String EhName = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(1, i).getContents()));
                    String EhNature = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(2, i).getContents()));
                    String EhStru = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(3, i).getContents()));
                    String EhType = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(4, i).getContents()));
                    String EhTurn = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(5, i).getContents()));
                    String EhStatus = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(6, i).getContents()));
                    String PropertyRight = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(7, i).getContents()));*/
                    String HousingUse = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(1, i).getContents()));
                    String OftenNumber = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(2, i).getContents()));
                    String OwnerName = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(3, i).getContents()));
                    String OwnerPhone = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(4, i).getContents()));
                    String BuildArea = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(5, i).getContents()));
                    String UseArea = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(6, i).getContents()));
                    String HeatingArea = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(7, i).getContents()));
                    String CarNum = S_string.spaceReplace(S_string.ToBJ(sheet.getCell(8, i).getContents()));
                    String remark =S_string.spaceReplace(S_string.ToBJ(sheet.getCell(9, i).getContents()));
                    String storer =S_string.spaceReplace(S_string.ToBJ(sheet.getCell(10, i).getContents()));
                    String handIs =  S_string.spaceReplace(S_string.ToBJ(sheet.getCell(11, i).getContents()));
                    map.put(heads[0], EhNumber);
                    map.put(heads[1], "");
                    map.put(heads[2], "");
                    map.put(heads[3], "");
                    map.put(heads[4], "");
                    map.put(heads[5], "");
                    map.put(heads[6], "");
                    map.put(heads[7], "");
                    map.put(heads[8], HousingUse);
                    map.put(heads[9], OftenNumber);
                    map.put(heads[10], OwnerName);
                    map.put(heads[11], OwnerPhone);
                    map.put(heads[12], BuildArea);
                    map.put(heads[13], UseArea);
                    map.put(heads[14], HeatingArea);
                    map.put(heads[15], CarNum);
                    map.put(heads[16], remark);
                    map.put(heads[17], storer);
                    map.put(heads[18], handIs);
                    list.add(map);
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 批量导入时验证用户是否存在
     * 
     * @return
     */
    public boolean checkuser1(String EhNumber, String Es_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        boolean bl = false;
        String sql = "select * from TB_Estate_House  where EhNumber='" + EhNumber + "' " + " and Es_id='" + Es_id + "'    and ts_id='" + ts_id
                + "'  and status='1' ";
        try {
            pu.init(sql.toString());
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
     * 用户导入时检查用户是否存在及数据格式 身份证号、姓名验证不能为空，导入文件是否存在重复身份证号，以及数据是否含有特殊字符
     * 
     * @param list
     * @param org_id
     * @return
     * @throws Exception
     */
    public String Excel_check_TBPay_User1(List<Map<String, String>> list, String org_id, String ts_id, String Es_id) throws Exception {
        String repeatId = "";
        String nullStr = "";
        String formatExceptionStr = "";
        String NumExceptionStr = "";
        Map<String, String> userIds = new HashMap<String, String>();
        List<String> listUserId = new ArrayList<String>();
        boolean flag;
        try {
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                // 循环得到工作表Excel当前的行的所有列
                String EhNumber = map.get("EhNumber");
                if (EhNumber.equals("")) {
                    nullStr += (i + 3) + "行,1列;";
                } else if (userIds.containsKey(EhNumber)) {
                    repeatId += (i + 3) + "行,1列;";
                    userIds.put(EhNumber, Integer.parseInt(userIds.get(EhNumber)) + 1 + "");
                    listUserId.add(EhNumber);// 导入文件中 房屋编号 重复验证
                } else if (checkuser1(EhNumber, Es_id, ts_id)) {
                    listUserId.add(EhNumber);
                }
                userIds.put(EhNumber, "1");
           
                String HousingUse = map.get("HousingUse");
                if (!HousingUse.equals("")) {
                    if (!S_string.dealSpecialString(HousingUse)) {
                        formatExceptionStr += (i + 3) + "行2列,";
                    }
                    
                }else{
                	nullStr += (i + 3) + "行2列";
                }
                String OftenNumber = map.get("OftenNumber");
                if (!OftenNumber.equals("")) {
                    if (!S_string.isNum(OftenNumber)) {
                        formatExceptionStr += (i + 3) + "行3列,";
                    }
                    
                }else{
                	nullStr += (i + 3) + "行3列";
                }
                String OwnerName = map.get("OwnerName");
                if (!OwnerName.equals("")) {
                    if (!S_string.dealSpecialString(OwnerName)) {
                        formatExceptionStr += (i + 3) + "行4列,";
                    }
                   
                }else{
                	 nullStr += (i + 3) + "行4列";
                }
                String OwnerPhone = map.get("OwnerPhone");
                if (!OwnerPhone.equals("")) {
                    if (!S_string.dealSpecialString(OwnerPhone)) {
                        formatExceptionStr += (i + 3) + "行5列,";
                    }
                  
                }else{
                	  nullStr += (i + 3) + "行5列";
                }
                String BuildArea = map.get("BuildArea");
                if (!BuildArea.equals("")) {
                    if (!S_string.isZX(BuildArea)) {
                        NumExceptionStr += (i + 3) + "行6列,";
                    }
                } else {
                    nullStr += (i + 3) + "行6列,";
                }
                String UseArea = map.get("UseArea");
                if (!UseArea.equals("")) {
                    if (!S_string.isZX(UseArea)) {
                        NumExceptionStr += (i + 3) + "行7列,";
                    }
                } else {
                    nullStr += (i + 3) + "行7列,";
                }
                String HeatingArea = map.get("HeatingArea");
                if (!HeatingArea.equals("")) {
                    if (!S_string.isZX(HeatingArea)) {
                        NumExceptionStr += (i + 3) + "行8列,";
                    }
                } else {
                    nullStr += (i + 3) + "行8列,";
                }
                String CarNum = map.get("CarNum");
                if (!CarNum.equals("")) {
                    if (!S_string.isNum(CarNum)) {
                        formatExceptionStr += (i + 3) + "行9列,";
                    }
                }else{
                	 nullStr += (i + 3) + "行9列,";
                }
                String remark = map.get("remark");
                if (remark.equals("")) {
//                    if (!S_string.dealSpecialString(remark)) {
                	 nullStr += (i + 3) + "行10列,";
                    //}
                }
                String storer = map.get("storer");
                if (!storer.equals("")) {
                    if (!S_string.isNum(storer)) {
                        formatExceptionStr += (i + 3) + "行11列,";
                    }
                }else{
                	 nullStr += (i + 3) + "行11列,";
                }
                String handIs = map.get("handIs");
                if (!handIs.equals("")) {
                    if (!S_string.dealSpecialString(handIs)) {
                        formatExceptionStr += (i + 3) + "行12列,";
                    }
                }else{
                	 nullStr += (i + 3) + "行12列,";
                }
            }
            if (!formatExceptionStr.equals("")) {
                formatExceptionStr = formatExceptionStr + "存在错误数据,数据格式只能使用中文、英文字符、数字 ";
            }
            if (!NumExceptionStr.equals("")) {
                NumExceptionStr = NumExceptionStr + "存在错误数据,数据格式只能使用数字 ";
            }
            if (!nullStr.equals("")) {
                nullStr += "数据不能为空；";
            }
            if (listUserId.size() > 0) {
                repeatId = "房屋编号：";
                for (int i = 0, len = listUserId.size(); i < len; i++) {
                    repeatId = repeatId + listUserId.get(i) + ",";
                }
                repeatId = repeatId + "重复；";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return repeatId + nullStr + formatExceptionStr + NumExceptionStr;
    }

    /**
     * 获取单位里 所有用户的 user_id,用于验证 批量导入用户时，没有重复数据
     * 
     * @param ts_id
     * @return
     * @throws Exception
     */
    public List<String> getTBPay_UserIDs(String org_id, String ts_id) throws Exception {
        PublicDBhandles pu1 = new PublicDBhandles();
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append("EhNumber");
        sql.append(" from TB_Estate_House where Es_id='" + org_id + "' and ts_id='" + ts_id + "' and status=1 ");
        try {
            pu1.init(sql.toString());
            rs = pu1.Query();
            while (rs.next()) {
                list.add(rs.getString("EhNumber") == null ? "" : rs.getString("EhNumber"));
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
                pu1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 批量导入 房屋信息
     * 
     * @param list
     * @param org_id
     * @param ts_id
     * @return
     * @throws Exception
     */
    public String insertTBHouse(String dy_id,List<Map<String, String>> list, String Es_id, String ts_id, String Bu_id,String EhType) throws Exception {
        PublicDBhandle pu = new PublicDBhandle();
        boolean flag = true;
        String retStr = "";
        StringBuffer flag_userId = new StringBuffer("");
        List<String> mapToList = null;
        Map<String, String> tpu_map = null;
        List<List<String>> sqlList = new ArrayList<List<String>>();
        String create_time = new T_time().getTime();// 导入时间
        String sql = "insert into TB_Estate_House (ts_id,Es_id,Bu_id,Un_id,EhNumber,EhName,EhNature,EhStru,EhType,"
                + "EhTurn,EhStatus,PropertyRight,HousingUse,OftenNumber,OwnerName,OwnerPhone,BuildArea,UseArea,"
                + "HeatingArea,CarNum,storey,handIs,creat_time,status,remark,password,userSign) " + "values ('" + ts_id + "','" + Es_id + "','" + Bu_id
                + "',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'" + create_time + "','1',?,'" + new MD5().createMd5("111111") + "','0')";
        try {
            List<String> user_ids = getTBPay_UserIDs(Es_id, ts_id);
            Map<String, String> map = new TB_Estate_UnitDao().getUnByBu_id(ts_id, Es_id, Bu_id);
            for (int i = 0, len = list.size(); i < len; i++) {
                tpu_map = list.get(i);
                mapToList = new ArrayList<String>();
                String EhNumber = tpu_map.get("EhNumber");
                if (user_ids.contains(EhNumber)) {
                    flag_userId.append(EhNumber + "，"); // 将重复的房屋编号
                } else {
                    String EhName = tpu_map.get("EhName");
                    String EhNature = tpu_map.get("EhNature");
                    String EhStru = tpu_map.get("EhStru");
                    String Eh_Type = tpu_map.get("EhType");
                    String EhTurn = tpu_map.get("EhTurn");
                    String EhStatus = tpu_map.get("EhStatus");
                    String PropertyRight = tpu_map.get("PropertyRight");
                    String HousingUse = tpu_map.get("HousingUse");
                    String OftenNumber = tpu_map.get("OftenNumber");
                    String OwnerName = tpu_map.get("OwnerName");
                    String OwnerPhone = tpu_map.get("OwnerPhone");
                    String BuildArea = tpu_map.get("BuildArea");
                    String UseArea = tpu_map.get("UseArea");
                    String HeatingArea = tpu_map.get("HeatingArea");
                    String CarNum = tpu_map.get("CarNum");
                    String remark = tpu_map.get("remark");
                    String handIs = tpu_map.get("handIs");
                    String storer = tpu_map.get("storer");
                    String dyh_id = "";
                    if("".equals(dy_id)){
                    	int count = 0;
                	    int index = 0;
                	    while(EhNumber.indexOf("-")!=-1){
                	        index=EhNumber.indexOf("-") + "-".length();
                	        EhNumber = EhNumber.substring(index);
                	        count++;
                	    }
                	    EhNumber = tpu_map.get("EhNumber");
                	    if(count==2){
                	    	String dyh = EhNumber.substring(EhNumber.indexOf("-") + 1, EhNumber.lastIndexOf("-"));
                	    	dyh_id = map.get(dyh)==null?"0":map.get(dyh);
                	    }
                	    mapToList.add(dyh_id);
                    }else{
                    	mapToList.add(dy_id);
                    }
                    mapToList.add(EhNumber);
                    mapToList.add(EhName);
                    mapToList.add(EhNature);
                    mapToList.add(EhStru);
                    mapToList.add(EhType);
                    mapToList.add(EhTurn);
                    mapToList.add(EhStatus);
                    mapToList.add(PropertyRight);
                    mapToList.add(HousingUse);
                    mapToList.add(OftenNumber);
                    mapToList.add(OwnerName);
                    mapToList.add(OwnerPhone);
                    mapToList.add(BuildArea);
                    mapToList.add(UseArea);
                    mapToList.add(HeatingArea);
                    mapToList.add(CarNum);
                    mapToList.add(storer);
                    if(handIs.equals("已交房")){
                    	  mapToList.add(1+"");
                    }else{
                    	  mapToList.add(0+"");
                    }
                    mapToList.add(remark);
                    sqlList.add(mapToList);
                }
            }
            String temp = "";
            if (!"".equals(flag_userId.toString())) {
                temp = flag_userId.toString();
                retStr = "房屋编号为：" + temp.substring(0, temp.length() - 1) + " 的信息已存在；";
                flag = false;
            }
            if (flag) {
                retStr = pu.pUpdatecommit(sqlList, sql);
                //System.out.println("retStr---"+retStr);
            }
        } catch (Exception e) {
            retStr = e.getMessage();
            e.printStackTrace();
        } finally {
            try {
                pu.closeSql();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retStr;
    }
}
