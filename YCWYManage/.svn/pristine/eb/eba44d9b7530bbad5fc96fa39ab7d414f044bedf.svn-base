package WYBack_Stage.Dao;

import java.sql.ResultSet;

import WYBack_Stage.Bean.TB_CDE_ROLE;
import WYCommunity.CreateID;


import ccbjf.system.db.PublicDBhandles;


public class MyTB_CDE_ROLE {
    /**
     * 添加时验是否存在
     * @return
     */
    public boolean checkadd(String role_name,String tu_id) {
        boolean bl=false;
        ResultSet rs=null;
        PublicDBhandles pu=null;
        String sql="select role_name from TB_CDE_ROLE where status='1' and  role_name='"+role_name+"' and tu_id='"+tu_id+"'  ";
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
     * 修改时验证是否存在
     * @return
     */
    public boolean checkupdate(String role_name,String ts_id,String role_code) {
        boolean bl=false;
        ResultSet rs=null;
        PublicDBhandles pu=null;
        String sql="select role_name from TB_CDE_ROLE where status=1 and role_name='"+role_name+"' and ts_id='"+ts_id+"' and role_code<>'"+role_code+"' ";
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
     * 新添加角色
     * @param ROLE_NAME
     * @param MENU_CODE
     * @param ORG_CODE
     */
    public boolean add_Role(String ROLE_NAME,String MENU_CODE,String tu_id,String description) {
        boolean bl=false;
        PublicDBhandles pu=null;
        String sql ="insert into TB_CDE_ROLE(role_code,role_name,menu_code,role_level,status,tu_id,description,type) values ('"+new CreateID().getID("TB_CDE_ROLE","role_code")+"','"+ROLE_NAME+"','"+MENU_CODE+"', '1','1','"+tu_id+"','"+description+"','2')";
        try {
            pu = new PublicDBhandles();
            pu.init(sql);
    		bl=pu.updates();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bl;
    }
    /**
     * 修改角色
     * @param ROLE_NAME
     * @param MENU_CODE
     * @param ORG_CODE
     * @param ROLE_CODE
     * @return
     */
    public boolean update_Role(String ROLE_NAME,String MENU_CODE,String ROLE_CODE,String description) {
        boolean bl=false;
        PublicDBhandles pu=null;
        String sql ="update TB_CDE_ROLE set role_name='"+ROLE_NAME+"',menu_code='"+MENU_CODE+"',description='"+description+"' where role_code='"+ROLE_CODE+"' ";
        try {
            pu = new PublicDBhandles();
            pu.init(sql);
    		bl=pu.updates();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bl;
    }
    /**
     * 删除角色，实际是置状态不可用
     * @param ROLE_CODE
     * @return
     */
    public boolean delete_Role(String ROLE_CODE) {
        boolean bl=false;
        PublicDBhandles pu=null;
        String sql ="update TB_CDE_ROLE set status='0' where role_code='"+ROLE_CODE+"' ";
        try {
            pu = new PublicDBhandles();
            pu.init(sql);
    		bl=pu.updates();
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bl;
    }
    /**
     * 通过ROLE_CODE得到角色表相关内容
     * @param ROLE_CODE
     * @return
     */
    public TB_CDE_ROLE get_Role(String ROLE_CODE) 
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        TB_CDE_ROLE role = null;
        String sql = "select * from TB_CDE_ROLE  where ROLE_CODE='"+ROLE_CODE+"' order by role_code ";
        try {
            pu.init(sql.toString());
            rs=pu.Query();
            if(rs.next()) 
            {           
                role = new TB_CDE_ROLE();
                role.setRole_code(rs.getString("role_code"));
                role.setRole_name(rs.getString("role_name"));
                role.setMenu_code(rs.getString("menu_code"));
                role.setStatus(rs.getString("status"));
                role.setRole_level(rs.getString("role_level"));
                role.setTu_id(rs.getString("tu_id"));
                role.setDescription(rs.getString("description")==null?"":rs.getString("description"));
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
        return role;
    }
}
