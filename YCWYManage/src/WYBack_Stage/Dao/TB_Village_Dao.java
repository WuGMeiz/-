/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import WYBack_Stage.Bean.TBCms_Article;
import WYBack_Stage.Bean.TB_Estate_Village;
import ccbjf.system.db.PublicDBhandles;

/**
 * <p> 物业小区DAO <p>
 * 
 * @date 2018-3-5 <br>
 * @author Administrator <br>
 * @version 9.0.0 <br>
 * 
 */
public class TB_Village_Dao {
    /**
     * <p> 添加时验是否存在 <p>
     * 
     * @param esName
     * @return
     */
    public boolean checkadd(String ts_id, String esName) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select EsName from TB_Estate_Village where " + "EsName='" + esName + "' and ts_id='" + ts_id + "'  and status='1'";
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
     * 物业添加小区
     * 
     * @param ts_id
     * @param EsName
     * @param EsHead
     * @param EsContact
     * @param EsPhone
     * @param EsAddress
     * @param build_Number
     * @param House_Number
     * @param BuildArea
     * @param EsArea
     * @param GreenArea
     * @param VolumeRate
     * @param remark
     * @param time
     * @return
     */
    public boolean xqadd(String ts_id, String EsName, String EsHead, String EsContact, String EsPhone, String EsAddress, String build_Number,
            String House_Number, String BuildArea, String FloorArea, String GreenArea, String VolumeRate, String remark, String time) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "insert into TB_Estate_Village( ts_id,EsName,EsHead,EsContact,EsPhone,EsAddress,build_Number,House_Number,BuildArea,FloorArea,GreenArea,VolumeRate,remark,creat_time,yznr,status) "
                    + " values ( '"
                    + ts_id
                    + "','"
                    + EsName
                    + "','"
                    + EsHead
                    + "','"
                    + EsContact
                    + "','"
                    + EsPhone
                    + "','"
                    + EsAddress
                    + "','"
                    + build_Number
                    + "','"
                    + House_Number
                    + "','"
                    + BuildArea
                    + "','"
                    + FloorArea
                    + "','"
                    + GreenArea
                    + "','"
                    + VolumeRate
                    + "','"
                    + remark
                    + "','"
                    + time + "','100','1') ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 用户查询 ，查询符合条件的小区
     * 
     * @param pagesize
     * @param pagenum
     * @return
     * @throws Exception
     */
    public List<TB_Estate_Village> getTB_Village(int pagesize, int pagenum, String condition) throws Exception {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_Village> list = new ArrayList<TB_Estate_Village>();
        int num = (pagenum - 1) * pagesize;
        String sql = " select top " + pagesize + " * from TB_Estate_Village   where Es_id not in " + "(select top " + num
                + " Es_id from TB_Estate_Village   where " + condition + " ) and " + condition + " order by Es_id asc";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Village tev =null;
            while (rs.next()) {
                tev = new TB_Estate_Village();
                tev.setBuildArea(rs.getString("BuildArea") == null ? "" : rs.getString("BuildArea"));
                tev.setBuild_Number(rs.getInt("build_Number"));
                tev.setCreat_time(rs.getString("creat_time"));
                tev.setEs_id(rs.getInt("es_id"));
                tev.setEsAddress(rs.getString("esAddress") == null ? "" : rs.getString("esAddress"));
                tev.setEsContact(rs.getString("EsContact") == null ? "" : rs.getString("EsContact"));
                tev.setEsHead(rs.getString("esHead") == null ? "" : rs.getString("esHead"));
                tev.setEsName(rs.getString("esName") == null ? "" : rs.getString("esName"));
                tev.setEsPhone(rs.getString("esPhone") == null ? "" : rs.getString("esPhone"));
                tev.setFloorArea(rs.getString("floorArea") == null ? "" : rs.getString("floorArea"));
                tev.setGreenArea(rs.getString("greenArea") == null ? "" : rs.getString("greenArea"));
                tev.setHouse_Number(rs.getInt("house_Number"));
                tev.setRemark(rs.getString("remark") == null ? "" : rs.getString("remark"));
                tev.setYznr(rs.getString("yznr"));
                tev.setVolumeRate(rs.getString("volumeRate") == null ? "" : rs.getString("volumeRate"));
                tev.setStatus(rs.getInt("status"));
                tev.setTs_id(rs.getInt("ts_id"));
                list.add(tev);
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
     * 修改小区信息
     * 
     * @param es_id
     * @param EsName
     * @param EsHead
     * @param EsContact
     * @param EsPhone
     * @param EsAddress
     * @param build_Number
     * @param House_Number
     * @param BuildArea
     * @param FloorArea
     * @param GreenArea
     * @param VolumeRate
     * @param remark
     * @return
     */
    public boolean xqxg(String ts_id, String es_id, String EsName, String EsHead, String EsContact, String EsPhone, String EsAddress,
            String build_Number, String House_Number, String BuildArea, String FloorArea, String GreenArea, String VolumeRate, String remark) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "update TB_Estate_Village set  EsName='" + EsName + "',EsHead='" + EsHead + "',EsContact='" + EsContact + "' ,EsPhone='"
                    + EsPhone + "' ,EsAddress='" + EsAddress + "' ,build_Number='" + build_Number + "' ,House_Number='" + House_Number
                    + "' ,BuildArea='" + BuildArea + "' ,FloorArea='" + FloorArea + "' ,GreenArea='" + GreenArea + "' ,VolumeRate='" + VolumeRate
                    + "' ,remark='" + remark + "'   where Es_id='" + es_id + "' and ts_id='" + ts_id + "' and status='1' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 查询小区名称
     * 
     * @param ts_id
     * @param es_id
     * @return
     */
    public List<String> findTBVillageNamenot(String ts_id, String es_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        String sql = "";
        sql = "select EsName from TB_Estate_Village where   Es_id not in('" + es_id + "') and ts_id='" + ts_id + "' and status=1";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            while (rs.next()) {
                list.add(rs.getString("EsName"));
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
     * 判断小区下有无楼宇
     * 
     * @param es_id
     * @param ts_id
     * @return
     */
    public boolean checklouyu(String es_id, String ts_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select * from TB_Estate_Build where Es_id='" + es_id + "' and ts_id='" + ts_id + "' and status='1'";
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
     * 删除小区信息
     * 
     * @param
     * @return
     */
    public boolean delete_TB_Village(String es_id, String ts_id) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean bl = false;
        try {
            String sql = "update TB_Estate_Village set status='0' where Es_id='" + es_id + "' and ts_id='" + ts_id + "' ";
            PublicDBhandles.init(sql);
            bl = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 删除小区时，通过小区ID得到小区名称
     * 
     * @param
     * @return
     */
    public String getEsname(String es_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String str = "";
        String sql = "select EsName from TB_Estate_Village where Es_id='" + es_id + "' and ts_id='" + ts_id + "'";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                str = rs.getString("EsName");
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
	 * 编辑页面用于分页的方法
	 * @param pagesize
	 * @param pagenum
	 * @return
	 */
	public List<TBCms_Article> getArticlePage(int pagesize,int pagenum,String Condition)
	{
		List<TBCms_Article> list=new ArrayList<TBCms_Article>();
		int num=(pagenum-1)*pagesize;
		String sql="select top "+pagesize+" * from TBCms_Article where cms_a_id not in(select top "+num+" cms_a_id from " +
				"TBCms_Article where "+Condition+" order by cms_index_tuijian desc,cms_a_order desc,cms_index_show desc,cms_a_create_time desc) and "+Condition+" order by cms_index_tuijian desc,cms_a_order desc,cms_index_show desc,cms_a_create_time desc ";

		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		try {
			pu.init(sql);
			rs=pu.Query();
			TBCms_Article Article=null;
			while (rs.next()) 
			{
				Article=new TBCms_Article();
				Article.setCms_a_id(rs.getString("cms_a_id"));
		        Article.setCms_c_id(rs.getString("cms_c_id"));
		        Article.setUser_id(rs.getString("user_id"));
		        Article.setCms_a_source(rs.getString("cms_a_source"));
		        Article.setCms_a_title(rs.getString("cms_a_title"));
		        Article.setCms_a_body(rs.getString("cms_a_body"));
		        Article.setCms_a_create_time(rs.getDate("cms_a_create_time").toString());
		        Article.setCms_a_comment_count(rs.getString("cms_a_comment_count"));
		        Article.setCms_a_view_count(rs.getString("cms_a_view_count"));
		        Article.setStatus(rs.getString("status"));
		        Article.setCms_a_order(rs.getString("cms_a_order"));
		        Article.setCms_a_link(rs.getString("cms_a_link"));
		        Article.setCms_index_show(rs.getString("cms_index_show"));
		        Article.setCms_a_level(rs.getString("cms_a_level"));
		        Article.setAdmin_user_id(rs.getString("admin_user_id"));
		        Article.setAudit_time(rs.getDate("audit_time")==null?"":rs.getDate("audit_time").toString());
		        Article.setAudit_status(rs.getString("audit_status"));
		        
		        Article.setCms_a_img(rs.getString("cms_a_img"));
		        Article.setCms_a_beizhu(rs.getString("cms_a_beizhu"));
		        Article.setCms_index_tuijian(rs.getString("cms_index_tuijian"));
		        
				list.add(Article);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
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
	 * 频道文章内容发布
	 * @param Article
	 * @return
	 */
	public String add_Article(TBCms_Article Article)
	{			
		String flag="";
		PublicDBhandles pu=new PublicDBhandles();
		try {
			String sql ="insert into TBCms_Article(cms_c_id,user_id,cms_a_source,cms_a_title,cms_a_body,cms_a_create_time,cms_a_comment_count,cms_a_view_count,status," +
					" cms_a_order,cms_a_link,cms_index_show,cms_a_level,admin_user_id,audit_time,audit_status,cms_a_img,cms_a_beizhu,cms_index_tuijian,ts_id) "+
					" values ('"+Article.getCms_c_id()+"','"+Article.getUser_id()+"','"+Article.getCms_a_source()+"','"+Article.getCms_a_title()+"', '"+Article.getCms_a_body()+"', " +
					" '"+Article.getCms_a_create_time()+"','"+Article.getCms_a_comment_count()+"','"+Article.getCms_a_view_count()+"','"+Article.getStatus()+"','"+Article.getCms_a_order()+"', " +
					" '"+Article.getCms_a_link()+"','"+Article.getCms_index_show()+"','"+Article.getCms_a_level()+"', '"+Article.getAdmin_user_id()+"','"+Article.getAudit_time()+"', " +
					" '"+Article.getAudit_status()+"','"+Article.getCms_a_img()+"','"+Article.getCms_a_beizhu()+"','"+Article.getCms_index_tuijian()+"','"+Article.getTs_id()+"') ";
			pu.init(sql);
			pu.updates();
			
		} catch (Exception e) {
			flag=e.getMessage();
			//e.printStackTrace();	
		} 
		finally {
			try {
				pu.close();
			} catch (Exception e) {
				flag=e.getMessage();
				//e.printStackTrace();
			}
		}		
		
		return flag;
	}
	/**
	 * 频道文章内容修改
	 * @param Article
	 * @return
	 */
	public String update_Article(String cms_c_id,String user_id,String cms_a_source,String cms_a_title,String cms_a_body,String cms_a_create_time,String cms_a_id)
	{			
		String flag="";
		PublicDBhandles pu=new PublicDBhandles();
		try {
			String sql ="update TBCms_Article set cms_c_id=?,user_id=?,cms_a_source=?,cms_a_title=?," +
					"cms_a_body=?,cms_a_create_time=? where cms_a_id=? ";
			pu.init(sql);
			pu.setString(1, cms_c_id);
			pu.setString(2, user_id);
			pu.setString(3, cms_a_source);
			pu.setString(4, cms_a_title);
			pu.setString(5, cms_a_body);
			pu.setString(6, cms_a_create_time);
			pu.setString(7, cms_a_id);
			pu.updates();
			
		} catch (Exception e) {
			flag=e.getMessage();
			//e.printStackTrace();	
		} 
		finally {
			try {
				pu.close();
			} catch (Exception e) {
				flag=e.getMessage();
				//e.printStackTrace();
			}
		}		
		
		return flag;
	}	
 
	/**
	 * 频道文章内容删除
	 * @param cms_a_id
	 * @return
	 */
	public boolean delete_Article(String cms_a_id)
	{			
		PublicDBhandles pu=new PublicDBhandles();
		boolean bl=false;
		try {
			String sql ="update TBCms_Article set status='0' where cms_a_id=? ";
			pu.init(sql);
			pu.setString(1, cms_a_id);
			bl=pu.updates();
			
		} catch (Exception e) {
			//e.printStackTrace();
		} 
		finally {
			try {
				pu.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}		
		
		return bl;
	}
 
	/**
	 * 通过文章ID得到文章标题
	 * @param cms_a_id
	 * @return
	 */
	public String getArticle_title(String cms_a_id)
	{	
		String str="";
		String sql;
		sql="select cms_a_title  from TBCms_Article where cms_a_id=? ";

		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		try {
			pu.init(sql);
			pu.setString(1, cms_a_id);
			rs=pu.Query();
			if(rs.next())
			{
				str=rs.getString(1);
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();			
		}
		finally
		{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}			
		}				
		return str;
	}	
	/**
	 * 通过频道ID得到频道的类别ID
	 * @param cms_c_id
	 * @return
	 */
	public String getcms_category_id(String cms_c_id)
	{
		String cms_category_id="";
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		try {
			String sql="select cms_category_id from TBCms_Channel where cms_c_id=? ";
			pu.init(sql);
			pu.setString(1, cms_c_id);
			rs=pu.Query();
			if(rs.next())
			{
				cms_category_id=rs.getString(1);
			}

		} 
		catch (Exception e) 
		{
			 e.printStackTrace();			
		}
		finally
		{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				 e.printStackTrace();
			}			
		}			
		
		return cms_category_id;
	}
	/**
	 * 根据文章ID得到文章内容
	 * @param cms_a_id
	 * @return 返回文章对象
	 */
	public TBCms_Article getArticle_index(String cms_a_id)
	{

		TBCms_Article Article=new TBCms_Article();
		String sql;
		sql = "select * from TBCms_Article where cms_a_id=? ";
		PublicDBhandles pu=new PublicDBhandles();
		ResultSet rs=null;
		try {
			pu.init(sql);
			pu.setString(1, cms_a_id);
			rs=pu.Query();
			if(rs.next())
			{ 
				
				Article.setCms_a_id(rs.getString("cms_a_id"));
		        Article.setCms_c_id(rs.getString("cms_c_id"));
		        Article.setUser_id(rs.getString("user_id"));
		        Article.setCms_a_source(rs.getString("cms_a_source"));
		        Article.setCms_a_title(rs.getString("cms_a_title"));
		        Article.setCms_a_body(rs.getString("cms_a_body"));
		        Article.setCms_a_create_time(rs.getString("cms_a_create_time"));
		        Article.setCms_a_comment_count(rs.getString("cms_a_comment_count"));
		        Article.setCms_a_view_count(rs.getString("cms_a_view_count"));
		        Article.setStatus(rs.getString("status"));
		        Article.setCms_a_order(rs.getString("cms_a_order"));
		        Article.setCms_a_link(rs.getString("cms_a_link"));
		        Article.setCms_index_show(rs.getString("cms_index_show"));
		        Article.setCms_a_level(rs.getString("cms_a_level"));
		        Article.setAdmin_user_id(rs.getString("admin_user_id"));
		        Article.setAudit_time(rs.getDate("audit_time")==null?"":rs.getDate("audit_time").toString());
		        Article.setAudit_status(rs.getString("audit_status"));
		        Article.setCms_a_img(rs.getString("cms_a_img"));
		        Article.setCms_a_beizhu(rs.getString("cms_a_beizhu"));
		        Article.setCms_index_tuijian(rs.getString("cms_index_tuijian"));
			}

		} catch (Exception e) {
			 e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null){
					rs.close();
				}
				pu.close();
			} catch (Exception e) {
				 e.printStackTrace();
			}
			
		}		
		
		return Article;
	}		

}
