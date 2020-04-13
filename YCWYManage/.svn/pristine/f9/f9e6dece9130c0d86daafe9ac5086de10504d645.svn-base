package WYBack_Stage.Excel;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import WYBack_Stage.Bean.TB_Estate_Order;
import WYBack_Stage.Dao.MyTB_Estate_Order;
import WYBack_Stage.Dao.MyTB_Estate_Order_bb_DAO;
import WYCommunity.S_string;
import WYCommunity.T_time;
import WYCommunity.deleteFile;

public class Excel_TB_Estate_Order_gue {
	
	/**
	 * Excel导出方法
	 */
	public boolean Excel_out_gue(String FilePath,String FileName,String Condition){
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
			
			//创建文件   、sheet表单
			WritableWorkbook wbook = Workbook.createWorkbook(new File(FilePath+FileName));
			WritableSheet sheet = wbook.createSheet("sheet1", 0);
			//设置
			sheet.setRowView(0,600);//将第一行的高度设为800
			int colum = 0;
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15);
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			StringBuffer headStr = new StringBuffer();
			headStr.append("所在小区;所在楼宇;所在单元;房屋编号;户主姓名;缴费项;订单类型;银行流水号;费用起止日期;应缴金额;实缴金额;缴费方式;支付状态;支付时间");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,13, 0);//列，行
			label=new Label(0,0,"批量导入订单明细表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_Order> list=new MyTB_Estate_Order().selectGE_orders_all(Condition);//按条件查询得到所有订单数据
			String orderType = null;//订单类型（1查缴订单 2预缴订单 3固额订单）
			String payType = null;//缴费方式（现金支付，网上支付）
			String payStatus = null;//支付状态（0未支付，1已支付，2部分支付）
			double z_total=0;//总金额
			double yj_total=0;//已缴总金额
			 double yh_total=0;//优惠金额
			int zfbs=0;
		 	double total_sj_hj=0;
		 	int i=0;
			for( i=0;i<list.size();i++){
				TB_Estate_Order order=(TB_Estate_Order)list.get(i);//循环得到订单对象
				z_total+=Double.parseDouble(String.valueOf(order.getTotal()));
				yj_total+=Double.parseDouble(String.valueOf(order.getTotal_sj()));
				//订单类型（1查缴订单 2预缴订单）
				switch (order.getOrderType()) {//根据支付状态值进行状态字符串匹配处理
					case 1:
						orderType="查缴订单";
						break;
					case 2:
						orderType="预缴订单";
						break;
					case 3:
						orderType="固额订单";
						break;
				}
				
				//缴费方式（现金支付，网上支付）
				if(order.getPayType().equals("0")){
					payType="网上支付";
				}else if(order.getPayType().equals("")){
					payType="";
				}else if(order.getPayType().equals("1")){
					payType="现金支付";
				}else if(order.getPayType().equals("2")){
					payType="被扫支付";
				}else if(order.getPayType().equals("3")){
					payType="转账支付";
				}else if(order.getPayType().equals("4")){
					payType="刷卡支付";
				}else if(order.getPayType().equals("5")){
					payType="调账支付";
				}else if(order.getPayType().equals("6")){
					payType="主扫支付";
				}
				
				//支付状态（0未支付，1已支付，2部分支付）
				switch (order.getPayStatus()) {//根据支付状态值进行状态字符串匹配处理
				case 0:
					payStatus="未支付";
					break;
				case 1:
					payStatus="已支付";
					break;
				case 2:
					payStatus="部分支付";
					break;
				case 3:
					payStatus="已退款";
					break;
				}
	  			//写入数据
	  			colum = 0;
	  			sheet.addCell(new Label(colum++,i+2, order.getEsName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getBuName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getUnName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getEhNumber(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getOwnerName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getItemName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, orderType,format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getBankid(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getCost_startTime().substring(0, 10)+"至"+order.getCost_endTime().substring(0, 10),format2));
	  			sheet.addCell(new Label(colum++,i+2, S_string.DecimalFormat_string(order.getTotal(),2),format2));
	  			sheet.addCell(new Label(colum++,i+2, S_string.DecimalFormat_string(order.getTotal_sj(),2),format2));
	  			sheet.addCell(new Label(colum++,i+2, payType,format2));
	  			sheet.addCell(new Label(colum++,i+2, payStatus,format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getPay_time(),format2));
			}
				
			sheet.addCell(new Label(6,i+2,"合计：",format2));
			sheet.addCell(new Label(7,i+2,S_string.DecimalFormat_string(z_total+"",2),format2));
			sheet.addCell(new Label(8,i+2,S_string.DecimalFormat_string(yj_total+"",2),format2));
			wbook.write();
			wbook.close();
		}catch(Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}

}
