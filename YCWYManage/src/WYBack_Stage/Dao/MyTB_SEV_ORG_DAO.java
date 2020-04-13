package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ccbjf.system.db.PublicDBhandles;

import WYBack_Stage.Bean.TB_SEV_ORG;

public class MyTB_SEV_ORG_DAO {
    
    /**
     * 通过用户orgid得到单位
     * @param orgid
     * @return
     */
    public List<TB_SEV_ORG> getTB_SEV_ORG(String orgid,String ts_id) {
        ResultSet rs=null;
        PublicDBhandles pu=null;
        List<TB_SEV_ORG> list=new ArrayList<TB_SEV_ORG>();
        String sql="select org_id,org_level,org_name from TB_SEV_ORG where" +
        		" org_id='"+orgid+"' and status=1 and ts_id='"+ts_id+"' order by org_level asc";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            TB_SEV_ORG org=null;
            while(rs.next()) {
                org=new TB_SEV_ORG();
                org.setOrg_id(rs.getString("org_id"));
                org.setOrg_level(rs.getString("org_level"));
                org.setOrg_name(rs.getString("org_name"));
                list.add(org);
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
     * 通过用户orgid得到所有的下级单位
     * @param orgid
     * @return
     */
    public List<TB_SEV_ORG> getTB_SEV_ORGAll(String orgid,String ts_id) {
        ResultSet rs=null;
        PublicDBhandles pu=null;
        List<TB_SEV_ORG> list=new ArrayList<TB_SEV_ORG>();
        String sql="select org_id,org_level,org_name from TB_SEV_ORG where  " +
        		" up_org_id='"+orgid+"' and org_level>0  and ts_id='"+ts_id+"' and status=1 " +
        				" order by org_level asc";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            TB_SEV_ORG org=null;
            while(rs.next()) {
                org=new TB_SEV_ORG();
                org.setOrg_id(rs.getString("org_id"));
                org.setOrg_level(rs.getString("org_level"));
                org.setOrg_name(rs.getString("org_name"));
                list.add(org);
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
     * 得到单位的级别
     * @param org_id
     * @return
     */
    public int getorg_level(String org_id,String ts_id) {
        int str=0;
        ResultSet rs=null;
        PublicDBhandles pu=null;
        String sql="select org_level from TB_SEV_ORG where " +
        		" org_id='"+org_id+"' and ts_id='"+ts_id+"' and  status=1 ";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            if(rs.next()) {
                str=rs.getInt(1);
            }
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
        return str;
    }
    /**
     * 查询当前登录人的ts_id
     * @param tu_id
     * @return
     */
    public String getTs_id(String org_id) {
        String ts_id="";
        ResultSet rs=null;
        PublicDBhandles pu=null;
        String sql="select ts_id from TB_SEV_ORG where org_id='"+org_id+"'";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            while(rs.next()) {
                ts_id = rs.getString("ts_id");
            }
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
        return ts_id;
    }
    /**
     * 添加时验是否存在
     * @return
     */
    public boolean checkadd(String dep_name,String ts_id) {
        boolean bl=false;
        ResultSet rs=null;
        PublicDBhandles pu=null;
        String sql="select org_name from TB_SEV_ORG where org_name='"+dep_name+"'" +
        		" and ts_id='"+ts_id+"' and status='1'";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            if(rs.next()) {
                bl=true;
            }
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
        return bl;
    }
    /**
     * 添加单位
     */
    public boolean addTB_SEV_ORG(int org_id,String up_org_id,String org_name,String ts_id,String remark) {
        boolean bl=false;
        PublicDBhandles pu=null;
        String sql = "insert into TB_SEV_ORG(org_id,org_name,up_org_id,org_level,remark,ts_id,status) values ('"+org_id+"','"+org_name+"','"+up_org_id+"','1','"+remark+"','"+ts_id+"','1')";
        try {
            pu = new PublicDBhandles();
            pu.init(sql);
    		bl=pu.updates();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            try {
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bl;
    }
    /**
     * 查询页面用于分页的方法
     * @param pagesize
     * @param pagenum
     * @return
     */
    public List<TB_SEV_ORG> getTB_SEV_ORGPage(int pagesize,int pagenum,String Condition) {
        ResultSet rs=null;
        PublicDBhandles pu=null;
        List<TB_SEV_ORG> list=new ArrayList<TB_SEV_ORG>();
        int num=(pagenum-1)*pagesize;
        String sql="select top "+pagesize+" org_id,org_level,org_name,up_org_id,remark from TB_SEV_ORG where org_id not in" +
                "(select top "+num+" org_id from TB_SEV_ORG where "+Condition+" order by org_level asc,up_org_id asc) and "+Condition+" order by org_level asc,up_org_id asc";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            TB_SEV_ORG org=null;
            while (rs.next()) {
                org=new TB_SEV_ORG();
                org.setOrg_id(rs.getString("org_id"));
                org.setOrg_level(rs.getString("org_level"));
                org.setOrg_name(rs.getString("org_name"));
                org.setUp_org_id(rs.getString("up_org_id"));
                org.setRemark(rs.getString("remark"));
                list.add(org);
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
     * 通过单位ID得到单位名称 
     * @param org_id
     * @return
     */
    public String getOrg_name(String org_id) {
        String str="";
        ResultSet rs=null;
        PublicDBhandles pu=null;
        String sql="select org_name from TB_SEV_ORG where org_id='"+org_id+"' ";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            if(rs.next()) {
                str=rs.getString("org_name");
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
        return str;
    }
    /**
     * 修改机构名称
     * @param 
     * @return org_id,org_name,remark
     */
    public boolean updateOrg(String org_id,String org_name,String remark,String ts_id ){
        boolean b=false;
        PublicDBhandles pu = new PublicDBhandles();
        try {
            String sql1="update TB_SEV_ORG set org_name='"+org_name+"'," +
            		"remark='"+remark+"' where status=1 and org_id ='"+org_id+"' " +
            				"and ts_id='"+ts_id+"'";
            pu.init(sql1);
            b=pu.updates();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }           
        }   
        return b;
    }
    /**
     * 删除机构名称
     * @param 
     * @return org_id,org_name,remark
     */
    public boolean delectOrg(String org_id,String org_name,String ts_id){
        boolean b=false;
        PublicDBhandles pu = new PublicDBhandles();
        try {
            String sql1="update TB_SEV_ORG set status='0' where status=1 " +
            		"and org_id ='"+org_id+"' and ts_id='"+ts_id+"'";
            pu.init(sql1);
            b=pu.updates();
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }           
        }   
        return b;
    }
    /**
     * 通过用户orgid得到所有的下级单位
     * @param orgid
     * @return
     */
    public List<TB_SEV_ORG> getTB_SEV_ORGAll1(String orgid,String ts_id) {
        ResultSet rs=null;
        PublicDBhandles pu=null;
        List<TB_SEV_ORG> list=new ArrayList<TB_SEV_ORG>();
        String sql="select org_id,org_level,org_name from TB_SEV_ORG " +
        		"where   org_id="+orgid+" and org_level>0  " +
        				"and ts_id='"+ts_id+"' and status=1   order by org_level asc";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            TB_SEV_ORG org=null;
            while(rs.next()) {
                org=new TB_SEV_ORG();
                org.setOrg_id(rs.getString("org_id"));
                org.setOrg_level(rs.getString("org_level"));
                org.setOrg_name(rs.getString("org_name"));
                list.add(org);
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
     * 获取机构的名字
     * @param org_id
     * @return
     */
    @SuppressWarnings("static-access")
    public  String getOrgName(String org_id)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        String orgname="";
        try {
            String sql="select org_name from TB_SEV_ORG where Org_id=?  and status='1'";
            pu.init(sql);
            pu.setString(1, org_id);
            rs =pu.Query();
        
            if(rs.next())
            { 
                orgname=rs.getString("org_name");
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
        return orgname;
    }
    /**
     * 删除机构名称
     * @param 
     * @return org_id,org_name,remark
     */
    public int countczy(String org_id,String ts_id){
        ResultSet rs = null;
        int num=0;
        PublicDBhandles pu = new PublicDBhandles();
        try {
            
            String sql1="select COUNT (*) as num from TB_SEV_USER where org_id ='"+org_id+"' and u_id='"+ts_id+"' and status=1 ";
            pu.init(sql1);
            rs = pu.Query();
            if (rs.next()) {
                num = rs.getInt("num");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
                    rs.close();
                }
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }           
        }   
        return num;
    }
}
