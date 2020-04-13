package WYCommunity;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ccbjf.system.db.ConnManager;
import ccbjf.system.util.SetLog;

public class MyPublicDBhandles {
    public Connection conn = null;

    public ResultSet rs = null;

    public PreparedStatement pstmt = null;

    public void init(String sql) {
        try {
            conn = ConnManager.getConnection();
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            String sWord = e.getMessage();
            SetLog.sqlexc_logwriter(sWord);
            e.printStackTrace();
        }
    }

    public void setString(int id, String value) {
        try {
            pstmt.setString(id, value);
        } catch (SQLException e) {
            String sWord = e.getMessage();
            SetLog.sqlexc_logwriter(sWord);
            e.printStackTrace();
        }
    }

    public void setInt(int id, int value) {
        try {
            pstmt.setInt(id, value);
        } catch (SQLException e) {
            String sWord = e.getMessage();
            SetLog.sqlexc_logwriter(sWord);
            e.printStackTrace();
        }
    }

    public void setBinaryStream(int id, InputStream in, int length) {
        try {
            pstmt.setBinaryStream(id, in, length);
        } catch (SQLException e) {
            String sWord = e.getMessage();
            SetLog.sqlexc_logwriter(sWord);
            e.printStackTrace();
        }
    }

    /**
     * 插入、修改、删除数据库时调用的方法(在其他更新方法中再次调用数据库更新时使用，调用完成后不关闭记录集和数据库连接)
     * 
     * @return
     */
    public boolean updates() {
        boolean flag = true;
        try {
            int i = pstmt.executeUpdate();
            if (i == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            String sWord = e.getMessage();
            SetLog.sqlexc_logwriter(sWord);
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 关闭数据库连接
     * 
     * @throws SQLException
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            ConnManager.releaseConnection(conn);
        } catch (SQLException e) {
            String sWord = e.getMessage();
            SetLog.sqlexc_logwriter(sWord);
            e.printStackTrace();
        }
    }
    public ResultSet Query(){
    	try {
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			 String sWord = e.getMessage();
	         SetLog.sqlexc_logwriter(sWord);
	         e.printStackTrace();
		}
    	return rs;
    }
}
