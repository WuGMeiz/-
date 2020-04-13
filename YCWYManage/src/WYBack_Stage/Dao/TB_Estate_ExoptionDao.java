package WYBack_Stage.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import WYCommunity.Base64Utils;
import WYCommunity.MyPublicDBhandles;
import WYBack_Stage.Bean.TB_Estate_Exoption;
import WYBack_Stage.Bean.TB_Estate_Extopic;
import ccbjf.system.db.PublicDBhandles;

public class TB_Estate_ExoptionDao {
	/**
	 * 
	 * @Title: select_Extopic
	 * @Description: 查询指定的题目
	 * @param ex_id 套题ID
	 * @return List<TB_Estate_Extopic>
	 */
	public List<TB_Estate_Extopic> select_Extopic(String ex_id){
		ResultSet rs = null;
		TB_Estate_Extopic extopic = null;
		PublicDBhandles pu = new PublicDBhandles();
		List<TB_Estate_Extopic> list = new ArrayList<TB_Estate_Extopic>();
		InputStream in = null;
		String sql = "select * from TB_Estate_Extopic where status = '1' and ex_id = '"+ex_id+"'";
		try {
			pu.init(sql.toString());
			rs=pu.Query();
			while(rs.next())
			{
				extopic =new TB_Estate_Extopic();
				extopic.setTo_id(rs.getInt("to_id"));
				extopic.setEx_id(rs.getInt("ex_id"));
				extopic.setIf_tw(rs.getInt("if_tw"));
				extopic.setTopic(rs.getString("topic")==null?"":rs.getString("topic"));
				if(rs.getBlob("images")!=null && rs.getString("images").length() != 0){
					in = rs.getBinaryStream("images");
		            int i = in.available(); 
		            byte[] buffer = new byte[i];  
		            while(-1!=(in.read(buffer,0,buffer.length)));
		            String base64Str ;
		            base64Str = Base64Utils.getBASE64(buffer);
		            extopic.setImages(base64Str);
		            in.close();
				}
				//extopic.setImages(rs.getString("images")==null?"":rs.getString("images"));
				extopic.setType(rs.getInt("type"));
				extopic.setSort(rs.getInt("sort"));
				extopic.setCreate_time(rs.getString("create_time")==null?"":rs.getString("create_time"));
				list.add(extopic);
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
		return list;
	}
	/**
	 * 
	 * @Title: select_Exoption
	 * @Description: 查询所有有效的选项
	 * @param 
	 * @return List<TB_Estate_Exoption>
	 */
	public List<TB_Estate_Exoption> select_Exoption(int to_id){
		ResultSet rs = null;
		TB_Estate_Exoption exoption = null;
		PublicDBhandles pu = new PublicDBhandles();
		InputStream in = null;
		List<TB_Estate_Exoption> list = new ArrayList<TB_Estate_Exoption>();
		String sql = "select * from TB_Estate_Exoption where status = 1 and to_id = ?";
		try {
			pu.init(sql.toString());
			pu.setInt(1, to_id);
			rs=pu.Query();
			while(rs.next())
			{
				exoption =new TB_Estate_Exoption();
				exoption.setOp_id(rs.getInt("op_id"));
				exoption.setTo_id(rs.getInt("to_id"));
				exoption.setIf_tw(rs.getInt("if_tw"));
				exoption.setOptionName(rs.getString("optionName")==null?"":rs.getString("optionName"));
				if(rs.getBlob("images")!=null && rs.getString("images").length() != 0){
					in = rs.getBinaryStream("images");
		            int i = in.available(); 
		            byte[] buffer = new byte[i];  
		            while(-1!=(in.read(buffer,0,buffer.length)));
		            String base64Str ;
		            base64Str = Base64Utils.getBASE64(buffer);
		            exoption.setImages(base64Str);
		            in.close();
				}
				//exoption.setImages(rs.getString("images")==null?"":rs.getString("images"));
				exoption.setSort(rs.getInt("sort"));
				exoption.setCreate_time(rs.getString("create_time")==null?"":rs.getString("create_time"));
				exoption.setStatus(rs.getInt("status"));
				list.add(exoption);
				
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
		return list;
	}
	/**
	 * 
	 * @Title: select_Exoption
	 * @Description: 查询指定有效的选项
	 * @param 
	 * @return List<TB_Estate_Exoption>
	 */
	public TB_Estate_Exoption select_Exoption_Designation(int op_id){
		ResultSet rs = null;
		TB_Estate_Exoption exoption = null;
		PublicDBhandles pu = new PublicDBhandles();
		InputStream in = null;
		String sql = "select op_id,to_id,if_tw,optionName,images,sort,create_time,status from TB_Estate_Exoption where status = 1 and op_id = ?";
		try {
			pu.init(sql.toString());
			pu.setInt(1, op_id);
			rs=pu.Query();
			while(rs.next())
			{
				exoption =new TB_Estate_Exoption();
				exoption.setOp_id(rs.getInt("op_id"));
				exoption.setTo_id(rs.getInt("to_id"));
				exoption.setIf_tw(rs.getInt("if_tw"));
				exoption.setOptionName(rs.getString("optionName")==null?"":rs.getString("optionName"));
				if(rs.getBlob("images")!=null && rs.getString("images").length() != 0){
					in = rs.getBinaryStream("images");
		            int i = in.available(); 
		            byte[] buffer = new byte[i];  
		            while(-1!=(in.read(buffer,0,buffer.length)));
		            String base64Str ;
		            base64Str = Base64Utils.getBASE64(buffer);
		            exoption.setImages(base64Str);
		            in.close();
				}
				//exoption.setImages(rs.getString("images")==null?"":rs.getString("images"));
				exoption.setSort(rs.getInt("sort"));
				exoption.setCreate_time(rs.getString("create_time")==null?"":rs.getString("create_time"));
				exoption.setStatus(rs.getInt("status"));
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
		return exoption;
	}
	
	/**
	 * 
	 * @Title: insert_Exoption
	 * @Description: 添加选项
	 * @param list
	 * @return boolean
	 */
	public boolean insert_Exoption(TB_Estate_Exoption exoption){
		boolean bl = false;
		String sql = "";
		MyPublicDBhandles pu=new MyPublicDBhandles();
		try{
			if(exoption.getImages() != null){
				sql = "insert into TB_Estate_Exoption(to_id,if_tw,optionName,images,sort,create_time,status)" +
						"values (?,?,?,?,?,?,?)";
				
				pu.init(sql);
				pu.setInt(1, exoption.getTo_id());
				pu.setInt(2, exoption.getIf_tw());
				pu.setString(3, exoption.getOptionName());
				FileInputStream fis = null;
				File file = new File(exoption.getImages());
				fis = new FileInputStream(file);
				int length = (int) file.length();
				pu.setBinaryStream(4, fis, length);
				pu.setInt(5, exoption.getSort());
				pu.setString(6, exoption.getCreate_time());
				pu.setInt(7, exoption.getStatus());
			}else{
				sql = "insert into TB_Estate_Exoption(to_id,if_tw,optionName,sort,create_time,status)" +
						"values (?,?,?,?,?,?)";
				pu.init(sql);
				pu.setInt(1, exoption.getTo_id());
				pu.setInt(2, exoption.getIf_tw());
				pu.setString(3, exoption.getOptionName());
				pu.setInt(4, exoption.getSort());
				pu.setString(5, exoption.getCreate_time());
				pu.setInt(6, exoption.getStatus());
			}
			bl = pu.updates();
		}catch (Exception e) {
			e.printStackTrace();
			bl = false;
		}finally{
		  pu.close();
		}
		return bl;
	}
	
	/**
	 * 
	 * @Title: update_Exoption
	 * @Description: 修改选项
	 * @param exoption
	 * @return boolean
	 */
	public boolean update_Exoption(TB_Estate_Exoption exoption){
		boolean bl = false;
		StringBuffer sql = new StringBuffer();
		MyPublicDBhandles pu = new MyPublicDBhandles();
		try{
			sql.append("update TB_Estate_Exoption  set if_tw = ?,optionName = ? ");
			if(exoption.getImages() != null){
				sql.append(", images = ? ");
			}
			sql.append(" where op_id = ?");
			
			
			
			pu.init(sql.toString());
			pu.setInt(1, exoption.getIf_tw());
			pu.setString(2, exoption.getOptionName());
			//pu.setString(4, exoption.getImages());
			if(exoption.getImages() != null){
				FileInputStream fis = null;
				File file = new File(exoption.getImages());
				fis = new FileInputStream(file);
				int length = (int) file.length();
				pu.setBinaryStream(3, fis, length);
				pu.setInt(4, exoption.getOp_id());
			}else{
				pu.setInt(3, exoption.getOp_id());
			}
			bl = pu.updates();
		}catch (Exception e) {
			e.printStackTrace();
			bl = false;
		}finally{
			 pu.close();
		}
		return bl;
	}
	
	/**
	 * 
	 * @Title: deleted_Exoption
	 * @Description: 删除选项
	 * @param op_id
	 * @return boolean
	 */
	public boolean deleted_Exoption(int op_id){
		boolean bl = false;
		String sql = "";
		try{
			sql = "update TB_Estate_Exoption " +
					"set status = 0 where op_id = ?";
			PublicDBhandles pu=new PublicDBhandles();
			pu.init(sql);
			pu.setInt(1, op_id);
			bl = pu.update();
		}catch (Exception e) {
			e.printStackTrace();
			bl = false;
		}
		return bl;
	}
}
