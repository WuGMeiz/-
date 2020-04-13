package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import WYBack_Stage.Bean.TB_Estate_Unit;

import ccbjf.system.db.PublicDBhandles;

public class TB_Estate_UnitDao {
	  /**
	   * 根据条件分页查询
	   * @param pagesize
	   * @param pagenum
	   * @param condition
	   * @return
	   * @throws Exception
	   */
	   public List<TB_Estate_Unit> getTB_Unit(int pagesize, int pagenum, String condition) throws Exception {
	        PublicDBhandles pu = new PublicDBhandles();
	        ResultSet rs = null;
	        List<TB_Estate_Unit> list = new ArrayList<TB_Estate_Unit>();
	        int num = (pagenum - 1) * pagesize;
	        String sql = "select top " + pagesize + " u.* ,v.EsName,b.buName from TB_Estate_Unit u inner join TB_Estate_Build b on u.Bu_id=b.Bu_id inner join TB_Estate_Village v on u.Es_id=v.Es_id  where Un_id not in " + "(select top " + num
	                + " Un_id from TB_Estate_Unit u inner join TB_Estate_Build b on u.Bu_id=b.Bu_id inner join TB_Estate_Village v on u.Es_id=v.Es_id  where " + condition + ") and " + condition + " order by u.Un_id asc";
	        try {
	            pu.init(sql.toString());
//	            System.out.println(sql);
	            rs = pu.Query();
	            TB_Estate_Unit teb=null;
	            while (rs.next()) {
	                teb = new TB_Estate_Unit();
	                teb.setBuName(rs.getString("buName"));
	                teb.setEsName(rs.getString("esName"));
	                teb.setRemark(rs.getString("remark") == null ? "" :rs.getString("remark"));
	                teb.setUn_id(rs.getInt("un_id"));
	                teb.setUnName(rs.getString("unName"));
	                teb.setBu_id(rs.getInt("bu_id"));
	                teb.setEs_id(rs.getInt("es_id"));
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
	   * 查询单元号所在的小区id  楼宇id
	   * @param Un_id
	   * @param ts_id
	   * @return
	   */
	  public String getESAndBuid(String Un_id, String ts_id){
		  String str="";
		  PublicDBhandles pu = new PublicDBhandles();
	      ResultSet rs = null;
	      String sql="select Es_id,Bu_id from TB_Estate_Unit where ts_id='"+ts_id+"' and Un_id='"+Un_id+"' and status='1'";
	    try {
	      pu.init(sql);
		  rs=pu.Query();
		  if(rs.next()){
			  str=rs.getString("Es_id")+"#"+rs.getString("Bu_id");
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
	      return str;
	  }
	  /**
	   * 查询单元名称
	   * @param ts_id
	   * @param es_id
	   * @param bu_id
	   * @param un_id
	   * @return
	   */
	  public List<String> TB_Estate_UnitNameNot(String ts_id, String es_id, String bu_id,String un_id){
		  PublicDBhandles pu = new PublicDBhandles();
	      ResultSet rs = null;
	      List<String> list = new ArrayList<String>();
	      String sql="select unName from TB_Estate_Unit where ts_id='" + ts_id + "' and Es_id='" + es_id + "' and Bu_id='"+bu_id+"' and status='1' and un_id not in('" + un_id+"')";
	      try {
	          pu.init(sql.toString());
	          rs = pu.Query();
	          while (rs.next()) {
	              list.add(rs.getString("unName"));
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
	   * 修改单元信息
	   * @param ts_id
	   * @param un_id
	   * @param unName
	   * @param remark
	   * @return
	   */
	  public boolean dyxg(String ts_id, String un_id, String unName, String remark) {
	      PublicDBhandles PublicDBhandles = new PublicDBhandles();
	      boolean bl = false;
	      try {
	          String sql = "update TB_Estate_Unit set  unName='" + unName + "',remark='" + remark + "' where Un_id='" + un_id + "' and ts_id='" + ts_id + "' and status='1' ";
	          PublicDBhandles.init(sql);
	          bl = PublicDBhandles.update();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return bl;
	  }
	  /**
	   * 判断单元下有无房屋
	   * 
	   * @param un_id
	   * @param ts_id
	   * @return
	   */
	  public boolean checkDyfw(String un_id, String ts_id) {
	      boolean bl = false;
	      ResultSet rs = null;
	      PublicDBhandles pu = null;
	      String sql = "select * from TB_Estate_House where un_id='" + un_id + "' and ts_id='" + ts_id + "' and status='1'";
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
	   * 删除单元信息
	   */
	  public boolean delete_TB_Unit(String un_id, String ts_id) {
	      PublicDBhandles PublicDBhandles = new PublicDBhandles();
	      boolean bl = false;
	      try {
	          String sql = "update TB_Estate_Unit set status='0' where un_id='" + un_id + "' and ts_id='" + ts_id + "' ";
	          PublicDBhandles.init(sql);
	          bl = PublicDBhandles.update();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return bl;
	  }
	  /**
	   * 删除单元时，得到单元名称
	   * 
	   * @param
	   * @return
	   */
	  public String getUnname(String un_id, String ts_id) {
	      PublicDBhandles pu = new PublicDBhandles();
	      ResultSet rs = null;
	      String str = "";
	      String sql = "select unName from TB_Estate_Unit where un_id='" + un_id + "' and ts_id='" + ts_id + "'";
	      try {
	          pu.init(sql.toString());
	          rs = pu.Query();
	          if (rs.next()) {
	              str = rs.getString("unName");
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
	   * <p> 添加时验单元号是否存在 <p>
	   * 
	   * @param esName
	   * @return
	   */
	  public boolean checkaddDy(String ts_id, String qsbuname, String es_id,String bu_id) {
	      boolean bl = false;
	      ResultSet rs = null;
	      PublicDBhandles pu = null;
	      String sql = "select unName from TB_Estate_Unit where unName='" + qsbuname + "' and Es_id='" + es_id + "' and ts_id='" + ts_id
	              + "' and status='1' and Bu_id='"+bu_id+"'";
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
	   * 添加单元
	   * @param ts_id
	   * @param es_id
	   * @param Bu_id
	   * @param louhao
	   * @param Remark
	   * @return
	   */
	  public boolean dyAdd(String ts_id, String es_id,String Bu_id, String louhao,String Remark){
		  PublicDBhandles PublicDBhandles = new PublicDBhandles();
	      boolean bl = false;
	      try {
	          String sql = "insert into TB_Estate_Unit (ts_id,Es_id,Bu_id,UnName,remark,status,creat_time) values('"+ts_id+"'" +
	          		",'"+es_id+"','"+Bu_id+"','"+louhao+"','"+Remark+"','1',getdate())";
	          PublicDBhandles.init(sql);
	          bl = PublicDBhandles.update();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return bl;
	  }
	  
	  /**
	   * 删除单元时，得到单元名称
	   * 
	   * @param
	   * @return
	   */
	  public Map<String, String> getUnByBu_id(String ts_id, String Es_id, String Bu_id) {
	      PublicDBhandles pu = new PublicDBhandles();
	      ResultSet rs = null;
	      Map<String, String> map = new HashMap<String, String>();
	      String sql = "select Un_id,UnName from TB_Estate_Unit where ts_id='"+ts_id+"' and Es_id='"+Es_id+"' and Bu_id='"+Bu_id+"'";
	      String regEx="[^0-9]";  
  		  Pattern p = Pattern.compile(regEx);
	      try {
	          pu.init(sql.toString());
	          rs = pu.Query();
	          while (rs.next()) {
	        	  String Un_id = rs.getString("Un_id");
	        	  String UnName = rs.getString("UnName");
	      		  Matcher m = p.matcher(UnName);  
	      		  map.put(m.replaceAll("").trim(), Un_id);
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
	      return map;
	  }
}
