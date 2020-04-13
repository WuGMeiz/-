package WYBack_Stage.Dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import WYBack_Stage.Bean.TB_Estate_Complaint;
import WYBack_Stage.Bean.TB_Estate_Exoption;
import WYBack_Stage.Bean.TB_Estate_Exrecord;
import WYBack_Stage.Bean.TB_Estate_Extopic;
import WYBack_Stage.Bean.TB_Estate_RepPeople;
import WYBack_Stage.Bean.TB_Estate_RepairInfo;
import WYCommunity.Base64Utils;
import WYCommunity.deleteFile;
import ccbjf.system.db.PublicDBhandles;

public class TB_Estate_RepPeopleDao {

  /**
   * 验证是否有有相同人员
   * @param Es_id
   * @param ReName
   * @param phone
   * @return
   */
  public boolean checkRepPeo(String Es_id,String ReName,String phone){
	  boolean bl = false;
      ResultSet rs = null;
      PublicDBhandles pu = new PublicDBhandles();
      String sql="select ReName from TB_Estate_RepPeople where Es_id='"+Es_id+"' and ReName='"+ReName+"' and " +
      		" phone='"+phone+"' and status=1";
      try {
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
   * 添加维修人员
   */
  public boolean addRepPeo(String Es_id,String ReName,String phone){
	  PublicDBhandles PublicDBhandles = new PublicDBhandles();
      boolean bl = false;
      try {
          String sql = "insert into TB_Estate_RepPeople (Es_id,ReName,phone,remark,status,creat_time) values(" +
          		"'"+Es_id+"','"+ReName+"','"+phone+"','','1',getdate())";
          PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return bl;
  }
  /**
   * 修改维修人员信息
   * @return
   */
  public boolean updateRepPeo(String Re_id,String ReName,String phone,String remark){
	  PublicDBhandles PublicDBhandles = new PublicDBhandles();
      boolean bl = false;
      try {
          String sql = "update TB_Estate_RepPeople set ReName='"+ReName+"',phone='"+phone+"',remark='"+remark+"'  where Re_id='" + Re_id + "'  ";
          PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return bl;
  }
  /**
   * 删除维修人员
   */
  public boolean deleteRepPeo(String Re_id) {
      PublicDBhandles PublicDBhandles = new PublicDBhandles();
      boolean bl = false;
      try {
          String sql = "update TB_Estate_RepPeople set status='0' where Re_id='" + Re_id + "'  ";
          PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return bl;
  }
  /**
   * 分页查询维修人员信息
   * @param pagesize
   * @param pagenum
   * @param condition
   * @return
   */
  public List<TB_Estate_RepPeople> getRepPeo(int pagesize, int pagenum, String condition){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_RepPeople> list=new ArrayList<TB_Estate_RepPeople>();
      TB_Estate_RepPeople peop=null;
      int num = (pagenum - 1) * pagesize;
      String sql = "select top " + pagesize + " r.* ,v.EsName from TB_Estate_RepPeople r inner join TB_Estate_Village v on r.Es_id=v.Es_id  where Re_id not in (select top " + num
              + " Re_id from TB_Estate_RepPeople r inner join TB_Estate_Village v on r.Es_id=v.Es_id  where " + condition + " order by r.Re_id desc) and " + condition + " order by r.Re_id desc";
    
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_RepPeople();
			peop.setEs_id(rs.getInt("es_id"));
			peop.setEsName(rs.getString("esName"));
			peop.setPhone(rs.getString("phone"));
			peop.setRe_id(rs.getInt("re_id"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setReName(rs.getString("reName"));
			peop.setRe_id(rs.getInt("re_id"));
			list.add(peop);
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
   * 分页查询投诉信息
   * @param pagesize
   * @param pagenum
   * @param condition
   * @return
   */
  public List<TB_Estate_Complaint> getCom(int pagesize, int pagenum, String condition){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_Complaint> list=new ArrayList<TB_Estate_Complaint>();
      TB_Estate_Complaint peop=null;
      int num = (pagenum - 1) * pagesize;
      String sql = "select top " + pagesize + " r.* ,v.EsName,h.EhNumber from TB_Estate_Complaint r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id where co_id not in (select top " + num
              + " co_id from TB_Estate_Complaint r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id  where " + condition + " order by r.co_id desc) and " + condition + " order by r.co_id desc";
    
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_Complaint();
			peop.setEs_id(rs.getInt("es_id"));
			peop.setEsName(rs.getString("esName"));
			peop.setComplContent(rs.getString("complContent"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setCo_id(rs.getInt("co_id"));
			peop.setCreat_time(rs.getDate("creat_time")+" "+rs.getTime("creat_time"));
			peop.setTousuType(rs.getInt("tousuType"));
			peop.setStatus(rs.getInt("status"));
			peop.setEhNumber(rs.getString("ehNumber"));
			list.add(peop);
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
   * 分页查询维修人员信息
   * @param pagesize
   * @param pagenum
   * @param condition
   * @return
   */
  public List<TB_Estate_RepairInfo> getRepInfo (int pagesize, int pagenum, String condition){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      InputStream in = null;
      List<TB_Estate_RepairInfo> list=new ArrayList<TB_Estate_RepairInfo>();
      TB_Estate_RepairInfo peop=null;
      int num = (pagenum - 1) * pagesize;
      String sql = "select top " + pagesize + " r.* ,v.EsName,h.EhNumber from TB_Estate_RepairInfo r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id  where Inf_id not in (select top " + num
              + " Inf_id from TB_Estate_RepairInfo r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id  where " + condition + " order by r.Inf_id desc) and " + condition + " order by r.Inf_id desc";
    
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_RepairInfo();
			peop.setEs_id(rs.getInt("es_id"));
			peop.setEsName(rs.getString("esName"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setAddress(rs.getString("address")==null?"":rs.getString("address"));
			peop.setInf_id(rs.getInt("inf_id"));
			peop.setStatus(rs.getInt("status"));
			peop.setEhNumber(rs.getString("ehNumber"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setInfContent(rs.getString("infContent")==null?"":rs.getString("infContent"));
			if(rs.getBlob("InfImage")!=null){
				in = rs.getBinaryStream("InfImage");
	            int i = in.available(); 
	            byte[] buffer = new byte[i];  
	            while(-1!=(in.read(buffer,0,buffer.length)));
	            String base64Str ;
	            base64Str = Base64Utils.getBASE64(buffer);
	            peop.setInfImage(base64Str);
	            in.close();
			}
			list.add(peop);
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
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
   * 受理维修信息
   */
  public boolean RepInfo(String Inf_id) {
      PublicDBhandles PublicDBhandles = new PublicDBhandles();
      boolean bl = false;
      try {
          String sql = "update TB_Estate_RepairInfo set status='1' where Inf_id='" + Inf_id + "'  ";
          PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return bl;
  }
  /**
   * 受理投诉信息
   */
  public boolean TouInfo(String Co_id) {
      PublicDBhandles PublicDBhandles = new PublicDBhandles();
      boolean bl = false;
      try {
          String sql = "update TB_Estate_Complaint set status='2' where Co_id='" + Co_id + "'  ";
          PublicDBhandles.init(sql);
          bl = PublicDBhandles.update();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return bl;
  }
  //投诉信息
  public boolean Excel_out_TouInfo(String FilePath,String FileName,String Condition) throws Exception
	{
		boolean bl=true;
		try 
		{
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
			sheet.setRowView(0,800);//将第一行的高度设为800
			int colum = 0;
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 10); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 50); 
			sheet.setColumnView(colum++, 10); 
			StringBuffer headStr = new StringBuffer();
			headStr.append("小区名称;房屋编号;类型;上报时间;内容描述;状态");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,5, 0);//列，行
			label=new Label(0,0,"投诉建议明细表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_Complaint> lists=new TB_Estate_RepPeopleDao().getComBao(Condition);
			TB_Estate_Complaint com=null;
			for(int row=0;row<lists.size();row++){
				colum = 0;
				com=lists.get(row);
				String dhm="",type="";
				if(com.getStatus()==1){
					dhm="待处理";
				}else if(com.getStatus()==2){
					dhm="已处理";
				}
				if(com.getTousuType()==1){
					type="建议";
				}else if(com.getTousuType()==2){
					type="投诉";
				}else if(com.getTousuType()==3){
					type="表扬";
				}
				sheet.addCell(new Label(colum++,row+2,com.getEsName(),format2));
				sheet.addCell(new Label(colum++,row+2,com.getEhNumber(),format2));
				sheet.addCell(new Label(colum++,row+2,type,format2));
				sheet.addCell(new Label(colum++,row+2,com.getCreat_time(),format2));
				sheet.addCell(new Label(colum++,row+2,com.getComplContent(),format2));
				sheet.addCell(new Label(colum++,row+2,dhm,format2));
			}
			wbook.write();
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
  /**
         报表数据查询
   * @return
   */
  public List<TB_Estate_Complaint> getComBao(String condition){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_Complaint> list=new ArrayList<TB_Estate_Complaint>();
      TB_Estate_Complaint peop=null;
      String sql = "select  r.* ,v.EsName,h.EhNumber from TB_Estate_Complaint r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id where "+ condition + " order by r.co_id desc";
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_Complaint();
			peop.setEs_id(rs.getInt("es_id"));
			peop.setEsName(rs.getString("esName"));
			peop.setComplContent(rs.getString("complContent"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setCo_id(rs.getInt("co_id"));
			peop.setCreat_time(rs.getDate("creat_time")+" "+rs.getTime("creat_time"));
			peop.setTousuType(rs.getInt("tousuType"));
			peop.setStatus(rs.getInt("status"));
			peop.setEhNumber(rs.getString("ehNumber"));
			list.add(peop);
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
  
  //报修信息
  public boolean Excel_out_BaoXiuInfo(String FilePath,String FileName,String Condition) throws Exception
	{
		boolean bl=true;
		try 
		{
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
			sheet.setRowView(0,800);//将第一行的高度设为800
			int colum = 0;
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 15); 
			sheet.setColumnView(colum++, 10); 
			sheet.setColumnView(colum++, 20); 
			sheet.setColumnView(colum++, 50); 
			sheet.setColumnView(colum++, 10); 
			StringBuffer headStr = new StringBuffer();
			headStr.append("小区名称;房屋编号;故障类型;上报时间;地址;内容描述;状态");
	
			String[] heads = headStr.toString().split(";");
			
			Label label;
			sheet.mergeCells(0, 0,6, 0);//列，行
			label=new Label(0,0,"报修信息表",titlewcfF);
			sheet.addCell(label);
			
			//写入表头
			for(int i=0;i<heads.length;i++){
				label=new Label(i,1,heads[i],format1);
				sheet.addCell(label);
			}
			List<TB_Estate_RepairInfo> lists=new TB_Estate_RepPeopleDao().getBaoInfo(Condition);
			TB_Estate_RepairInfo com=null;
			for(int row=0;row<lists.size();row++){
				colum = 0;
				com=lists.get(row);
				String dhm="";
				if(com.getStatus()==0){
					dhm="待受理";
				}else if(com.getStatus()==1){
					dhm="已受理";
				}
				String str="其他故障";
				if("1".equals(com.getRemark())){
				  str="供水故障";
				}else if("2".equals(com.getRemark())){
				  str="供电故障";
				}else if("3".equals(com.getRemark())){
				  str="下水堵塞";
				}else if("4".equals(com.getRemark())){
				  str="暖气跑水";
				}else if("5".equals(com.getRemark())){
				  str="房顶漏水";
				}else if("6".equals(com.getRemark())){
				  str="墙体裂缝";
				}else if("7".equals(com.getRemark())){
				  str="电梯故障";
				}else if("8".equals(com.getRemark())){
				  str="其他故障";
				}
				sheet.addCell(new Label(colum++,row+2,com.getEsName(),format2));
				sheet.addCell(new Label(colum++,row+2,com.getEhNumber(),format2));
				sheet.addCell(new Label(colum++,row+2,str,format2));
				sheet.addCell(new Label(colum++,row+2,com.getCreat_time(),format2));
				sheet.addCell(new Label(colum++,row+2,com.getAddress(),format2));
				sheet.addCell(new Label(colum++,row+2,com.getInfContent(),format2));
				sheet.addCell(new Label(colum++,row+2,dhm,format2));
			}
			wbook.write();
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			bl=false;
		}
		return bl;
	}
  //维修信息
  public List<TB_Estate_RepairInfo> getBaoInfo(String condition){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      InputStream in = null;
      List<TB_Estate_RepairInfo> list=new ArrayList<TB_Estate_RepairInfo>();
      TB_Estate_RepairInfo peop=null;
      String sql = "select r.* ,v.EsName,h.EhNumber from TB_Estate_RepairInfo r inner join TB_Estate_Village v on r.Es_id=v.Es_id inner join TB_Estate_House h on r.Eh_id=h.Eh_id  where" + condition + " order by r.Inf_id desc";
    
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_RepairInfo();
			peop.setEs_id(rs.getInt("es_id"));
			peop.setEsName(rs.getString("esName"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setAddress(rs.getString("address")==null?"":rs.getString("address"));
			peop.setInf_id(rs.getInt("inf_id"));
			peop.setStatus(rs.getInt("status"));
			peop.setCreat_time(rs.getDate("creat_time")+" "+rs.getTime("creat_time"));
			peop.setEhNumber(rs.getString("ehNumber"));
			peop.setRemark(rs.getString("remark")==null?"":rs.getString("remark"));
			peop.setInfContent(rs.getString("infContent")==null?"":rs.getString("infContent"));
			if(rs.getBlob("InfImage")!=null){
				in = rs.getBinaryStream("InfImage");
	            int i = in.available(); 
	            byte[] buffer = new byte[i];  
	            while(-1!=(in.read(buffer,0,buffer.length)));
	            String base64Str ;
	            base64Str = Base64Utils.getBASE64(buffer);
	            peop.setInfImage(base64Str);
	            in.close();
			}
			list.add(peop);
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
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
   * 分页查询问卷调查信息
   * @param pagesize
   * @param pagenum
   * @param condition
   * @return
   */
  public List<TB_Estate_Exrecord> getWjdc(int pagesize, int pagenum, String condition,String Innerj){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_Exrecord> list=new ArrayList<TB_Estate_Exrecord>();
      TB_Estate_Exrecord peop=null;
      int num = (pagenum - 1) * pagesize;
      /*String sql = "select top " + pagesize + " r.*,b.topic,c.OwnerName,d.ex_id from TB_Estate_ExRecord "+Innerj+" where re_id not in (select top " + num
              + " re_id from TB_Estate_ExRecord "+Innerj+" where " + condition + " order by r.re_id desc) and " + condition + " order by r.re_id desc";
      String sql1=select top 10 r.Eh_id,r.selectTime,b.OwnerName from TB_Estate_ExRecord  r inner join TB_Estate_House b on r.Eh_id=b.Eh_id where selectTime not in (select top 0 selectTime from TB_Estate_ExRecord  r inner join TB_Estate_House b on r.Eh_id=b.Eh_id group by r.Eh_id,OwnerName,selectTime order by selectTime)and  1=1  and r.status='1' group by r.Eh_id,OwnerName,selectTime order by selectTime */
     String sql="select top " + pagesize+" r.Eh_id,b.EhNumber,b.OwnerName,selectTime from TB_Estate_ExRecord"+Innerj+" where selectTime not in (select top "+num+" selectTime from TB_Estate_ExRecord "+Innerj+" where"+condition+" group by r.Eh_id,EhNumber,OwnerName,selectTime order by selectTime ) and "+condition+" group by r.Eh_id,EhNumber,OwnerName,selectTime order by selectTime";
    	     
    // System.out.println("分页查询问卷调查信息"+sql);
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_Exrecord();
			peop.setEh_id(rs.getInt("Eh_id"));
			peop.setSelectTime(rs.getString("selectTime"));
			peop.setOwnerName(rs.getString("OwnerName")==null?"":rs.getString("OwnerName"));
			peop.setEhNumber(rs.getString("ehNumber"));
			list.add(peop);
		  }
	} catch (SQLException e) {
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
  
  
  //问卷题目表
  public List<TB_Estate_Extopic> select_XuanXiang(int ex_id){	  
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_Extopic> list=new ArrayList<TB_Estate_Extopic>();
      TB_Estate_Extopic peop=null;
      String sql = "select * from TB_Estate_Extopic where status=1 and ex_id="+ex_id;
    //System.out.println("问卷题目表"+sql);
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_Extopic();
			peop.setTo_id(rs.getInt("to_id")==0?0:rs.getInt("to_id"));
			peop.setEx_id(rs.getInt("ex_id"));
			peop.setIf_tw(rs.getInt("if_tw"));
			peop.setTopic(rs.getString("topic"));
			peop.setImages(rs.getString("images")==null?"":rs.getString("images"));
			peop.setSort(rs.getInt("sort")==0?0:rs.getInt("sort"));
			peop.setType(rs.getInt("type")==0?0:rs.getInt("type"));
			peop.setCreate_time(rs.getString("create_time"));
			
			list.add(peop);
		  }
	} catch (SQLException e) {
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
  
//得到每道题的选项
  public List<TB_Estate_Exoption> getTB_Estate_Exoption(int to_id){	  
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_Exoption> list=new ArrayList<TB_Estate_Exoption>();
      TB_Estate_Exoption peop=null;
      String sql = "select * from TB_Estate_Exoption where status=1 and to_id="+to_id;
      // System.out.println("每道题的选项"+sql);
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_Exoption();
			peop.setOp_id(rs.getInt("op_id"));
			peop.setIf_tw(rs.getInt("if_tw"));
			peop.setOptionName(rs.getString("optionName"));
			peop.setImages(rs.getString("images")==null?"":rs.getString("images"));
			peop.setSort(rs.getInt("sort")==0?0:rs.getInt("sort"));
			peop.setCreate_time(rs.getString("create_time"));
			
			list.add(peop);
		  }
	} catch (SQLException e) {
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
  //查找用户每道题的选项，并将选项放到一个int的list集合中
 public List<Integer> getXuanXiangId(String ex_id1,String Eh_id1,int to_id, String selectTime){	  
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;  
      List<Integer> list=new ArrayList();
      int ex_id=Integer.valueOf(ex_id1);
      int Eh_id=Integer.valueOf(Eh_id1);
      selectTime=selectTime.replace("_", " ");
      String sql = "select con_selects from TB_Estate_ExRecord where status=1 and ex_id="+ex_id+" and Eh_id="+Eh_id+" and to_id="+to_id+" and selectTime='"+selectTime+"'";
 // System.out.println(selectTime+"查找用户选的选项"+sql);
  String select=null;
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		if(rs.next()){
			select=rs.getString("con_selects");
		  }
	} catch (SQLException e) {
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
	     if(select!=null){
	      boolean status = select.contains("|");
	        if(status){
		      String[] a=select.split("\\|");
		      //System.out.println(a.length+a[0]);
			  	for(int j=0;j<a.length;j++){
			  		list.add(Integer.valueOf(a[j]));
			  	}
		    }else{
		  		list.add(Integer.valueOf(select));
		  	}
	     } 
      return list;
  }

  
  /**
   * 分页查询问卷统计信息
   * @param pagesize
   * @param pagenum
   * @param condition
   * @return
   */
  public List<TB_Estate_Extopic> getWjTj(int pagesize, int pagenum, String condition,String Innerj){
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      List<TB_Estate_Extopic> list=new ArrayList<TB_Estate_Extopic>();
      TB_Estate_Extopic peop=null;
      int num = (pagenum - 1) * pagesize;
      String sql = "select top " + pagesize + " r.* from TB_Estate_Extopic "+Innerj+" where to_id not in (select top " + num
              + " to_id from TB_Estate_Extopic "+Innerj+" where " + condition + " order by r.to_id desc) and " + condition + " order by r.to_id desc";
   //System.out.println(sql);
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			peop=new TB_Estate_Extopic();
			peop.setTo_id(rs.getInt("to_id"));
			peop.setEx_id(rs.getInt("ex_id"));
			peop.setIf_tw(rs.getInt("if_tw"));
		    peop.setTopic(rs.getString("topic"));
		    peop.setImages(rs.getString("images"));
			peop.setType(rs.getInt("type"));
			peop.setSort(rs.getInt("sort"));
			peop.setCreate_time(rs.getString("create_time"));
			list.add(peop);
		  }
	} catch (SQLException e) {
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
  public String getWjXuanXiang(int to_id){
	  String xinxi="";
	  TB_Estate_RepPeopleDao dao=new TB_Estate_RepPeopleDao();
	  List<TB_Estate_Exoption> lists=dao.getTB_Estate_Exoption(to_id);
	    for(int i=0;i<lists.size();i++){
	    	if(i!=0){
	    		xinxi=xinxi+" # ";
	    	}
	    	TB_Estate_Exoption option=lists.get(i);
	    	int op_id=option.getOp_id();
	    	int count =dao.getCount(to_id,op_id);
	    	option.setCount(count);
	    	xinxi=xinxi+option.getOptionName()+"*"+count;
	    }
	  
	  
	  return xinxi;
  }
  
  
  public int getCount(int to_id,int op_id){	  
	  PublicDBhandles pu = new PublicDBhandles();
      ResultSet rs = null;
      int count=0;
     String sql = "select count(*) count from TB_Estate_ExRecord where status=1 and to_id="+to_id+"and (con_selects='"+op_id+"' or con_selects like '%|"+op_id+"|%' or con_selects like '%|"+op_id+"' or con_selects like '"+op_id+"|%')";
    //System.out.println("每道题有多少人选："+sql);
      try {
    	  pu.init(sql.toString());
          rs=pu.Query();
		while(rs.next()){
			count=rs.getInt("count");
		  }
	} catch (SQLException e) {
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
      
      return count;
  }
//将png文件转为jpg
  public static void convertPngToJpg(File file,String newFilePath){
		try {
		    BufferedImage bufferedImage = ImageIO.read(file);
		    BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
		    newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, null);
		    ImageIO.write(newBufferedImage,"jpg",new File(newFilePath));
		} catch (IOException e) {
		    e.printStackTrace();
		}
  }
}
