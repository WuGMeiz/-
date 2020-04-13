package WYBack_Stage.Dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import netpay.merchant.crypto.RSA;
import WYBack_Stage.Bean.TB_Estate_Examine;
import WYBack_Stage.Bean.TB_Estate_Extopic;
import WYCommunity.Base64Utils;
import WYCommunity.MyPublicDBhandles;
import ccbjf.system.db.PublicDBhandles;

public class TB_Estate_Extopic_Dao {
	
	/**
	 * 
	 * @param ex_id 主键id
	 * @param if_tw	是否图文
	 * @param topic	题目设置
	 * @param type	类型设置
	 * @param create_time 创建时间
	 * @param status 状态
	 * @param sort 排序
	 * @return
	 */
	public boolean add(String ex_id,String if_tw,String topic,String type,String create_time,String status,String sort) {
		//通过问卷id添加
		String sql = "insert into TB_Estate_Extopic(ex_id,if_tw,topic,type,create_time,status,sort) values(?,?,?,?,?,?,?)";
		
		PublicDBhandles ps = new PublicDBhandles();
		boolean update =false;
		try {
			ps.init(sql);
			ps.setString(1, ex_id);
			ps.setString(2, if_tw);
			ps.setString(3, topic==null?" ":topic);
			ps.setString(4, type);
			ps.setString(5, create_time);
			ps.setString(6, status);
			ps.setString(7, sort);
			update = ps.update();
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return update;
	}
	
	
	
	public boolean addImg(String ex_id,String if_tw,String topic,String type,String create_time,String status,String sort,InputStream is,int length) {
		//通过问卷id添加
		String sql = "insert into TB_Estate_Extopic(ex_id,if_tw,topic,type,create_time,status,sort,images) values(?,?,?,?,?,?,?,?)";
		
		MyPublicDBhandles ps = new MyPublicDBhandles();
		boolean update =false;
		try {
			ps.init(sql.toString());
			ps.setString(1, ex_id);
			ps.setString(2, if_tw);
			ps.setString(3, topic);
			ps.setString(4, type);
			ps.setString(5, create_time);
			ps.setString(6, status);
			ps.setString(7, sort);
			ps.setBinaryStream(8, is, length);
			update = ps.updates();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			ps.close();
		}
		return update;
	}
	/**
	 * 删除
	 * @param to_id 题目主 id
	 * @return
	 */
	public boolean del(String to_id){
		String sql = "update TB_Estate_Extopic set status = 0 where to_id = ?";
		PublicDBhandles ps = new PublicDBhandles();
		
		boolean update = false;
		try {
			ps.init(sql);
			ps.setString(1, to_id);
			update = ps.update();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return update;
	}
	
	/**
	 * 更新
	 * @param to_id
	 * @param if_tw
	 * @param topic
	 * @param type
	 * @param status
	 * @param sort
	 * @return
	 */
	public boolean updateImg(String to_id,String if_tw,String topic,String type,String sort,InputStream is,int size){
		String sql = "update TB_Estate_Extopic set if_tw=?,topic=?,type=?,sort=?,images=? where to_id = ?";
		
		MyPublicDBhandles ps = new MyPublicDBhandles();
		boolean update = false;
		try {
			ps.init(sql);
			ps.setString(1, if_tw);
			ps.setString(2, topic==null?" ":topic);
			ps.setString(3, type);
			ps.setString(4, sort);
			ps.setString(6, to_id);
			ps.setBinaryStream(5, is, size);
			update = ps.updates();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
	         ps.close();
	     }
		return update;
	}
	
	public boolean update(String to_id,String if_tw,String topic,String type,String sort){
		String sql = "update TB_Estate_Extopic set if_tw=?,topic=?,type=?,sort=? where to_id = ?";
		
		MyPublicDBhandles ps = new MyPublicDBhandles();
		boolean update = false;
		try {
			ps.init(sql);
			ps.setString(1, if_tw);
			ps.setString(2, topic==null?" ":topic);
			ps.setString(3, type);
			ps.setString(4, sort);
			ps.setString(5, to_id);
			update = ps.updates();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
	         ps.close();
	     }
		return update;
	}
	
	
	//判断该题目下有没有 选项 有返回true
	public boolean if_two(String to_id){
		String sql = "select * from TB_Estate_Exoption where to_id = ? and status = 1;";
		
		PublicDBhandles ps = new PublicDBhandles();
		boolean update = false;
		ResultSet rs = null;
		try {
			ps.init(sql);
			ps.setString(1, to_id);
			rs = ps.Query();
			if(rs.next()){
				update = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ps.close();
		}
		return update;
	}


	/**
     * 用户查询 ，查询符合条件的题目
     * 
     * @param pagesize
     * @param pagenum
     * @return
     * @throws Exception
     */
    public List<TB_Estate_Extopic> getTB_EState_Extopic(int pagesize, int pagenum, String condition) throws Exception {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_Extopic> list = new ArrayList<TB_Estate_Extopic>();
        int num = (pagenum - 1) * pagesize;
        String sql = " select top " + pagesize + " * from TB_Estate_Extopic where to_id not in " + "(select top " + num
                + " to_id from TB_Estate_Extopic where " + condition + " ) and " + condition + " order by sort asc";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            TB_Estate_Extopic tev =null;
            while (rs.next()) {
                tev = new TB_Estate_Extopic();
                /*tev.setBuildArea(rs.getString("BuildArea") == null ? "" : rs.getString("BuildArea"));
                tev.setBuild_Number(rs.getInt("build_Number"));
                tev.setCreat_time(rs.getString("creat_time"));
                tev.setEs_id(rs.getInt("es_id"));*/
                tev.setTo_id(rs.getInt("to_id"));
                tev.setIf_tw(rs.getInt("if_tw"));
                tev.setSort(rs.getInt("sort"));
                tev.setTopic(rs.getString("topic")== null?"":rs.getString("topic"));
                tev.setType(rs.getInt("type") == 0?2:rs.getInt("type"));
                InputStream in = null;
                if(rs.getBlob("images")!=null && rs.getString("images").length() != 0){
                    in = rs.getBinaryStream("images");
                          int i = in.available(); 
                          byte[] buffer = new byte[i];  
                          while(-1!=(in.read(buffer,0,buffer.length)));
                          String base64Str ;
                          base64Str = Base64Utils.getBASE64(buffer);
                          tev.setImages(base64Str);
                          in.close();
                  }
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

    //通过小区 id 获取 问卷表list集合
    public List<TB_Estate_Examine> getTB_Estate_ExamineByTsId(String tsId){
    	PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        List<TB_Estate_Examine> list = new ArrayList<TB_Estate_Examine>();
        String sql = "select * from TB_Estate_Examine where ts_id = ?";
        try {
            pu.init(sql.toString());
            pu.setString(1, tsId);
            rs = pu.Query();
            TB_Estate_Examine tev =null;
            while (rs.next()) {
                tev = new TB_Estate_Examine();
                tev.setEx_id(rs.getInt("ex_id"));
                tev.setTitle(rs.getString("title"));
                tev.setType(rs.getInt("type"));
              
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


    //根据小区id和类型查询 问卷list
    public List<HashMap<String,String>> wenjuans(String xqId,String typeId){
    	String sql="select ex_id,title from TB_Estate_Examine where Es_id = ? and type = ? and status<>0";
    	PublicDBhandles pu = new PublicDBhandles();
    	LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String,String>>();
    	ResultSet rs = null;
    	try {
			pu.init(sql);
			pu.setString(1, xqId);
			pu.setString(2, typeId);
			rs = pu.Query();
			while(rs.next()){
				HashMap<String,String> hashMap = new HashMap<String,String>();
				hashMap.put("value", rs.getString("ex_id"));
				hashMap.put("name", rs.getString("title"));
				list.add(hashMap);
			}
    		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
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
}
