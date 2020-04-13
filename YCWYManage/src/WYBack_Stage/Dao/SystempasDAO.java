package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ccbjf.system.db.PublicDBhandles;

public class SystempasDAO {
    /**
     * 检查旧密码
     * 
     * @param user_id
     * @param oldPassword
     * @return
     */
    public boolean checkPassword(String userid, String oldPassword) {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        boolean flag = false;
        String sql = "select password from TB_SEV_USER where userid='" + userid
                + "' and password='" + oldPassword + "'";
        try {
            pu.init(sql.toString());
            rs=pu.Query();
            if (rs.next()) {
                flag = true;
            }
        }   catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                pu.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    public boolean updateUserInfo(String userid, String newPassword,String tu_id,String ts_id) {
        PublicDBhandles PublicDBhandles=new PublicDBhandles();
        boolean bl=false;
        
        String sql = "update TB_SEV_USER set password='" + newPassword
                + "' where status=1 and userid='" + userid + "' and tu_id='"+tu_id+"' and u_id='"+ts_id+"'";
        try {
            PublicDBhandles.init(sql);
            bl=PublicDBhandles.update();
        }  catch (Exception e) {
            e.printStackTrace();
        }  
        return bl;
    }
}
