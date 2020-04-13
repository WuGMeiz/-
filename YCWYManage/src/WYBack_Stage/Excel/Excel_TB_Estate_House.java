package WYBack_Stage.Excel;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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

import WYBack_Stage.Bean.TB_Estate_House;
import WYBack_Stage.Bean.TB_HMBean;
import WYBack_Stage.Dao.Mete_ReadClass;
import WYBack_Stage.Dao.MyTB_House_DAO;
import WYCommunity.S_string;
import WYCommunity.T_time;
import WYCommunity.deleteFile;


public class Excel_TB_Estate_House {
	
	/**
	 * Excel导出方法
	 */
	public boolean Excel_out_Housemx(String FilePath,String FileName,String Condition){
		boolean bl=true;
		try {
			deleteFile.deleteisFile(FilePath, FileName);
			
			//标题字体设置
			WritableCellFormat titlewcfF = new WritableCellFormat(new WritableFont(WritableFont.createFont("黑体"),16,WritableFont.BOLD));
			titlewcfF.setAlignment(Alignment.CENTRE);// 标题水平居中
			titlewcfF.setVerticalAlignment(VerticalAlignment.CENTRE);// 标题垂直居中
			//列头标字体设置
			WritableCellFormat format1 = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD));
			format1.setAlignment(Alignment.CENTRE);// 标题水平居中
			format1.setVerticalAlignment(VerticalAlignment.CENTRE);// 标题垂直居中
			
			WritableCellFormat format2 = new WritableCellFormat();
			format2.setAlignment(Alignment.CENTRE);// 标题水平居中
			format2.setVerticalAlignment(VerticalAlignment.CENTRE);// 标题垂直居中
			
			   //写入示例的样式
			 WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
	                    Colour.RED);// ① 指定了字串格式：字体为TIMES，字号16，加粗显示。
            WritableCellFormat format4 = new WritableCellFormat(NumberFormats.TEXT);// ②处代码使用了WritableCellFormat类，这个类非常重要，通过它可以指定单元格的各种属性。
            format4.setFont(font3);
            format4.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
            format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
            format4.setWrap(true);// 设置自动换行
//            format4.setBackground(Colour.GRAY_25);
            format4.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
            format4.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
            format4.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
            format4.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
			
			//创建文件   、sheet表单
			WritableWorkbook wbook = Workbook.createWorkbook(new File(FilePath+FileName));
			WritableSheet sheet = wbook.createSheet("sheet1", 0);
			//设置
			sheet.setRowView(0,600);//将第一行的高度设为800
			int colum = 0;
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15);
			sheet.setColumnView(colum++, 15);
			sheet.setColumnView(colum++, 15);
			sheet.setColumnView(colum++, 15);
			sheet.setColumnView(colum++, 15);
			StringBuffer headStr = new StringBuffer();
			headStr.append("小区名称;所在楼宇;所在单元;房屋编号;业主姓名;业主电话;建筑面积;使用面积;供暖面积;车位数;常住人口数;车位编号");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,11, 0);//列，行
			label=new Label(0,0,"房屋信息明细表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			
		    /*========写入示例============*/
          /*  sheet.addCell(new Label(0, 2, "示例：测试小区", format4));
            //sheet.mergeCells(0, 2, 0, 1);
            sheet.addCell(new Label(1, 2, "示例:测试楼宇", format4));
            //sheet.mergeCells(1, 2, 1, 1);
            sheet.addCell(new Label(2, 2, "示例：3单元", format4));
            //sheet.mergeCells(2, 2, 2, 1);
            sheet.addCell(new Label(3, 2, "示例：1702", format4));
           // sheet.mergeCells(3, 2, 3, 1);
            sheet.addCell(new Label(4, 2, "示例：testName", format4));
            sheet.addCell(new Label(5, 2, "示例：17701266789", format4));
            sheet.addCell(new Label(6, 2, "示例：110.5", format4));
            sheet.addCell(new Label(7, 2, "示例：109.56", format4));
            sheet.addCell(new Label(8, 2, "示例：109.56", format4));
            sheet.addCell(new Label(9, 2, "示例：3", format4));
            sheet.addCell(new Label(10, 2, "示例：5", format4));
            sheet.addCell(new Label(11, 2, "示例：21", format4));
            sheet.addCell(new Label(12, 2, "填写数据时，请删除本行", format4));*/
            //sheet.setColumnView(4,cv);//设置单元格的格式
            //sheet.mergeCells(4, 2, 4, 1);
            
            /*WritableFont wf = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD, false);
            WritableCellFormat wcfF = new WritableCellFormat(NumberFormats.TEXT);//定义一个单元格样式
            wcfF.setFont(wf);//设置字体
     		
    		ws.setColumnView(n, cv);//设置工作表中第n列的样式
*/    	
            	
            /*====================*/
			
			
			List<TB_Estate_House> list=new MyTB_House_DAO().getTB_HouseReport(Condition);//按条件查询得到所有订单数据
			
			for(int i=0;i<list.size();i++){
				TB_Estate_House house=(TB_Estate_House)list.get(i);//循环得到订单对象
				
				//写入数据
	  			colum = 0;
	  			sheet.addCell(new Label(colum++,i+2, house.getEsName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getBuName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getUnName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getEhNumber(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getOwnerName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getOwnerPhone(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getBuildArea(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getUseArea(),format2));
				sheet.addCell(new Label(colum++,i+2, house.getHeatingArea(),format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getCarNum()+"",format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getOftenNumber()+"",format2));
	  			sheet.addCell(new Label(colum++,i+2, house.getRemark(),format2));
			}
			
			wbook.write();
			wbook.close();
		}catch(Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
	
}