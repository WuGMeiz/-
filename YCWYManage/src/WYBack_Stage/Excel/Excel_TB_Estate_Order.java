package WYBack_Stage.Excel;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import WYBack_Stage.Bean.TB_Estate_Order;
import WYBack_Stage.Bean.TB_HMBean;
import WYBack_Stage.Dao.Mete_ReadClass;
import WYBack_Stage.Dao.MyTB_Estate_Order;
import WYBack_Stage.Dao.MyTB_Estate_Order_bb_DAO;
import WYCommunity.S_string;
import WYCommunity.T_time;
import WYCommunity.deleteFile;


public class Excel_TB_Estate_Order {
	
	/**
	 * Excel导出方法
	 */
	public boolean Excel_out_ddmxSev(String FilePath,String FileName,String Condition){
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
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15);
			StringBuffer headStr = new StringBuffer();
			headStr.append("所在小区;所在楼宇;所在单元;房屋编号;户主姓名;订单类型;银行流水号;缴费项;费用起止日期;应缴金额;滞纳金金额;实缴金额;缴费方式;支付状态;支付时间;退款时间;退款方式;退款状态;退款原因");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,18, 0);//列，行
			label=new Label(0,0,"收费单查询明细表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_Order> list=new MyTB_Estate_Order().select_orders_all(Condition);//按条件查询得到所有订单数据
			
			 String orderType = null;//订单类型（1查缴订单 2预缴订单）
			 String payType = null;//缴费方式（现金支付，网上支付）
			 String payStatus = null;//支付状态（0未支付，1已支付，2部分支付）
			 String tk_type = null;//退款方式（0线上退款 1线下退款）
			 String tk_status = null;//退款状态（0默认， 1全部退款，2部分退款，）
			 double z_total=0;//总金额
			 double yj_total=0;//已缴总金额
			 double zn_total=0;//滞纳金额
			 double total_znj_all=0;
			 int i=0;
			 double znj_total = 0;//滞纳金
			 double znj_gongshi = 0;//滞纳金计算公式
			 double total_sj_all=0;
			for( i=0;i<list.size();i++){
				TB_Estate_Order order=(TB_Estate_Order)list.get(i);//循环得到订单对象
				z_total+=Double.parseDouble(String.valueOf(order.getTotal()));
				//订单类型（1查缴订单 2预缴订单）
				switch (order.getOrderType()) {//根据支付状态值进行状态字符串匹配处理
					case 1:
						orderType="查缴订单";
						break;
					case 2:
						orderType="预缴订单";
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
					payStatus="已全额退款";
					break;
				}
				
				//退款方式（0线上退款 1线下退款）
				if(order.getTk_type().equals("")){
					tk_type="";
				}else if(order.getTk_type().equals("0")){
					tk_type="线上退款";
				}else if(order.getTk_type().equals("1")){
					tk_type="线下退款";
				}
				
				//退款状态（0默认， 1全部退款，2部分退款，）
				if(order.getTk_status().equals("")){
					tk_status="";
				}else if(order.getTk_status().equals("0")){
					tk_status="";
				}else if(order.getTk_status().equals("1")){
					tk_status="全部退款";
				}else if(order.getTk_status().equals("2")){
					tk_type="部分退款";
				}
				
				if(order.getPayStatus()==0){
				yj_total = Double.parseDouble(order.getTotal());//应缴金额
				String znjDay = order.getZnjDay();//滞纳规定天数
				String znjRatio = order.getZnjRatio();//滞纳比例
				if(order.getScost_endTime().equals("1900-01-01 00:00:00.0") ||order.getScost_endTime().equals("")||order.getScost_endTime()==null){
					 znj_total = 0;//滞纳金
					 znj_gongshi = 0;//滞纳金计算公式
				}else{
					String str = order.getScost_endTime().substring(0,10)+" 23:59:59";
					String str1 = new T_time().getTime();
					String Scost_endTime = new T_time().getymdhms(str);//缴费结束日期
					long endtime = Long.parseLong(Scost_endTime);
					
					String the_day = new T_time().getymdhms(str1);//当前时间
					long theday = Long.parseLong(the_day);
					
					int zndaynum = 0;
					if(endtime-theday<0){
						zndaynum = new  T_time().getDaysBetween(str,str1);//计算滞纳天数
					}
					
					if(znjDay.equals("") || znjDay.equals("null")){//如果为空
							znj_gongshi = 0;
					}else if(Integer.parseInt(znjDay)<0){//如果为负数
						if((zndaynum+Integer.parseInt(znjDay))<=0){//小于等于0，为宽限天数
							znj_gongshi = 0;
						}else{
							znj_gongshi = (zndaynum+Integer.parseInt(znjDay))*Double.parseDouble(znjRatio);//（滞纳天数+滞纳规定天数）*滞纳金比例
						}
					}else{//如果为正数
						
						if((zndaynum-Integer.parseInt(znjDay))<0){//大于0
							znj_gongshi = zndaynum*Double.parseDouble(znjRatio);//（滞纳天数）*滞纳金比例
						}else{
							znj_gongshi = Integer.parseInt(znjDay)*Double.parseDouble(znjRatio);//滞纳规定天数*滞纳金比例
						}
					}
				}
				znj_total = yj_total*znj_gongshi;//滞纳金	
				zn_total = znj_total;
				total_znj_all+=znj_total;
			  	}else{
			  		zn_total=Double.parseDouble(order.getTotal_znj());
			  		total_znj_all=total_znj_all+Double.parseDouble(order.getTotal_znj());
				}
				//写入数据
	  			colum = 0;
	  			sheet.addCell(new Label(colum++,i+2, order.getEsName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getBuName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getUnName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getEhNumber(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getOwnerName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, orderType,format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getBankid(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getItemName(),format2));
	  			String startTime="";
	  			String endTime="";
	  			if(order.getCost_startTime().equals("")){
	  				startTime="";
	  			}else{
	  				startTime=order.getCost_startTime().substring(0, 10);
	  			}
	  			
	  			if(order.getCost_endTime().equals("")){
	  				endTime="";
	  			}else{
	  				endTime=order.getCost_endTime().substring(0, 10);
	  			}
	  				
				sheet.addCell(new Label(colum++, i + 2, startTime+ "至"+ endTime, format2));
				
	  			
				sheet.addCell(new Label(colum++,i+2, S_string.DecimalFormat_string(order.getTotal(),2),format2));
	  			sheet.addCell(new Label(colum++,i+2, S_string.DecimalFormat_double(zn_total,2),format2));
	  			sheet.addCell(new Label(colum++,i+2, S_string.DecimalFormat_string(order.getTotal_sj(),2),format2));
	  			sheet.addCell(new Label(colum++,i+2, payType,format2));
	  			sheet.addCell(new Label(colum++,i+2, payStatus,format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getPay_time(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getTk_time(),format2));
	  			sheet.addCell(new Label(colum++,i+2, tk_type,format2));
	  			sheet.addCell(new Label(colum++,i+2, tk_status,format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getTk_Reason(),format2));
	  			
	  			total_sj_all+=Double.parseDouble(order.getTotal_sj());
			}
			
			sheet.addCell(new Label(8,i+2,"合计：",format2));
			sheet.addCell(new Label(9,i+2,S_string.DecimalFormat_string(z_total+"",2),format2));
			sheet.addCell(new Label(10,i+2,S_string.DecimalFormat_string(total_znj_all+"",2),format2));
			sheet.addCell(new Label(11,i+2,S_string.DecimalFormat_string(total_sj_all+"",2),format2));
			wbook.write();
			wbook.close();
		}catch(Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
	/**
	 * Excel导出方法(自助缴费模块)
	 */
	public boolean Excel_out_ddmxSev_zzjf(String FilePath,String FileName,String Condition){
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
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 30); 
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			sheet.setColumnView(colum++, 20);
			StringBuffer headStr = new StringBuffer();
			headStr.append("流水号;小区名称;楼宇名称;单元名称;业主姓名;房屋编号;缴费时间;缴费金额;缴费项目;订单类型;缴费方式");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,10, 0);//列，行
			label=new Label(0,0,"自助缴费订单明细表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_Order> list=new MyTB_Estate_Order().select_orders_all(Condition);//按条件查询得到所有订单数据
			
			double z_total=0;//总金额
			String orderType = null;//订单类型（1查缴订单 2预缴订单）
			String payType = null;//缴费方式（现金支付，网上支付）
			int i=0;
			for( i=0;i<list.size();i++){
				TB_Estate_Order order=(TB_Estate_Order)list.get(i);//循环得到订单对象
				z_total+=Double.parseDouble(String.valueOf(order.getTotal_sj()));
				
				switch (order.getOrderType()) {//根据支付状态值进行状态字符串匹配处理
				case 1:
					orderType="查缴订单";
					break;
				case 2:
					orderType="预缴订单";
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
				
				//写入数据
	  			colum = 0;
	  			sheet.addCell(new Label(colum++,i+2, order.getBankid(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, order.getEsName(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, order.getBuName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getUnName(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, order.getOwnerName(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, order.getEhNumber(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, order.getPay_time(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, S_string.DecimalFormat_string(order.getTotal_sj(),2),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getItemName(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2, orderType,format2)); 
	  			sheet.addCell(new Label(colum++,i+2, payType,format2)); 
			}
			
				sheet.addCell(new Label(6,i+2,"合计：",format2));
				sheet.addCell(new Label(7,i+2,S_string.DecimalFormat_string(z_total+"",2),format2));
				wbook.write();
				wbook.close();
		}catch(Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
	
	/**
	 * Excel导出方法(交易管理---报表模块)
	 */
	public boolean Excel_out_jygl_bbdd(String FilePath,String FileName,String Condition){
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
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 20); 
			StringBuffer headStr = new StringBuffer();
			headStr.append("小区名称;收费项;交易笔数;交易金额");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,3, 0);//列，行
			label=new Label(0,0,"交易管理统计数据表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_Order> list = new MyTB_Estate_Order_bb_DAO().select_orders_baobiaoxinxi_dc(Condition);//按条件查询得到所有订单数据
			int zfbs=0;
		 	double total_sj_hj=0;
		 	int i=0;
			for( i=0;i<list.size();i++){
				TB_Estate_Order order=(TB_Estate_Order)list.get(i);//循环得到订单对象
				zfbs+=Integer.parseInt(order.getNum());
	  			total_sj_hj+=Double.parseDouble(order.getTotal_sj_all());
	  			//写入数据
	  			colum = 0;
	  			sheet.addCell(new Label(colum++,i+2,order.getEsName(),format2)); 
	  			sheet.addCell(new Label(colum++,i+2,order.getItemName(),format2));
	  			sheet.addCell(new Label(colum++,i+2,order.getNum(),format2));
	  			sheet.addCell(new Label(colum++,i+2,S_string.DecimalFormat_string(order.getTotal_sj_all(),2),format2));
			}
			sheet.addCell(new Label(1,i+2,"合计：",format2));
			sheet.addCell(new Label(2,i+2,zfbs+"",format2));
			sheet.addCell(new Label(3,i+2,total_sj_hj+"",format2));
			wbook.write();
			wbook.close();
		}catch(Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
	//年度预收统计
	public boolean Excel_out_ddYSSev(String FilePath,String FileName,String Condition){
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
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 25); 
			sheet.setColumnView(colum++, 15);
			
		
			StringBuffer headStr = new StringBuffer();
			headStr.append("小区;楼宇;房屋编号;户主姓名;缴费项;费用起止日期;实缴金额");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,6, 0);//列，行
			label=new Label(0,0,"年度预收明细表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_Order> list=new MyTB_Estate_Order().selectNDYS_orders(Condition);//按条件查询得到所有订单数据
			
			
			 double z_total=0;//总金额
			 double yj_total=0;//已缴总金额
			 double zn_total=0;//滞纳金额
			 
			 int i=0;
			
			 double total_sj_all=0;
			for( i=0;i<list.size();i++){
				TB_Estate_Order order=(TB_Estate_Order)list.get(i);//循环得到订单对象
				z_total+=Double.parseDouble(String.valueOf(order.getTotal()));
		
				//写入数据
	  			colum = 0;
	  			sheet.addCell(new Label(colum++,i+2, order.getEsName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getBuName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getEhNumber(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getOwnerName(),format2));
	  			sheet.addCell(new Label(colum++,i+2, order.getItemName(),format2));
	  			String startTime="";
	  			String endTime="";
	  			if(order.getCost_startTime().equals("")){
	  				startTime="";
	  			}else{
	  				startTime=order.getCost_startTime().substring(0, 10);
	  			}
	  			if(order.getCost_endTime().equals("")){
	  				endTime="";
	  			}else{
	  				endTime=order.getCost_endTime().substring(0, 10);
	  			}
	  				
				sheet.addCell(new Label(colum++, i + 2, startTime+ "至"+ endTime, format2));
				sheet.addCell(new Label(colum++, i + 2,S_string.DecimalFormat_string(order.getTotal_sj(),2), format2));
	  			total_sj_all+=Double.parseDouble(order.getTotal_sj());
			}
			
			sheet.addCell(new Label(5,i+2,"合计：",format2));
			sheet.addCell(new Label(6,i+2,S_string.DecimalFormat_string(total_sj_all+"",2),format2));
			wbook.write();
			wbook.close();
		}catch(Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
}