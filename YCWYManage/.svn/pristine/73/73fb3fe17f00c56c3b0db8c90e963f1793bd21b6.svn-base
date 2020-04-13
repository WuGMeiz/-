package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import WYBack_Stage.Bean.TB_SEV_USER;
import ccbjf.system.db.PublicDBhandles;

public class MyTB_SEV_USER {
    public TB_SEV_USER getUserLogin(String userid, String password, String tablename) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        TB_SEV_USER myuser = null;
        String sql;
        sql = "select * from " + tablename + " where userid='" + userid + "'" + " and password='" + password + "' and status='1' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                myuser = new TB_SEV_USER();
                myuser.setTu_id(rs.getString("tu_id"));
                myuser.setRole_code(rs.getString("role_code"));
                myuser.setU_id(rs.getString("u_id"));
                myuser.setUserid(rs.getString("userid"));
                myuser.setPassword(rs.getString("password"));
                myuser.setUsername(rs.getString("username") == null ? "" : rs.getString("username"));
                myuser.setPhone(rs.getString("phone") == null ? "" : rs.getString("phone"));
                myuser.setEmail(rs.getString("email") == null ? "" : rs.getString("email"));
                myuser.setQq(rs.getString("qq") == null ? "" : rs.getString("qq"));
                myuser.setAddr(rs.getString("addr") == null ? "" : rs.getString("addr"));
                myuser.setCreate_time(rs.getDate("create_time") + " " + rs.getTime("create_time"));
                myuser.setLogin_ip(rs.getString("login_ip") == null ? "第一次登陆" : rs.getString("login_ip"));
                myuser.setLogin_time(rs.getString("login_time") == null ? "第一次登陆" : rs.getDate("login_time") + " " + rs.getTime("login_time"));
                myuser.setLevels(rs.getString("levels"));
                myuser.setStatus(rs.getString("status"));
                myuser.setOrg_id(rs.getString("org_id"));
                myuser.setIsdrawback(rs.getString("isdrawback")==null?"1":rs.getString("isdrawback"));
                myuser.setPayWays(rs.getString("payWays")==null?"":rs.getString("payWays"));
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
        return myuser;
    }

    /**
     * 将本次登录的时间和IP更新到数据库
     * 
     * @param userid
     * @param login_time
     * @param login_ip
     * @param tablename
     */
    public static void update_user_login_timeorip(String userid, String login_time, String login_ip, String tablename) {
        try {
            String sql = "update " + tablename + " set login_time='" + login_time + "',login_ip='" + login_ip + "' where userid='" + userid + "' ";
            PublicDBhandles PublicDBhandles = new PublicDBhandles();
            PublicDBhandles.init(sql);
            PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户编辑页面用于分页的方法
     * 
     * @param pagesize
     * @param pagenum
     * @return
     */
    public List<TB_SEV_USER> getuserPage(int pagesize, int pagenum, String Condition) {
        ResultSet rs = null;
        PublicDBhandles pu = null;
        List<TB_SEV_USER> list = new ArrayList<TB_SEV_USER>();
        int num = (pagenum - 1) * pagesize;
        String sql = "select top " + pagesize + " a.* from TB_SEV_USER a left join TB_SEV_ORG b on a.ORG_ID=b.ORG_ID  where userid not in"
                + "(select top " + num + " userid from TB_SEV_USER a left join TB_SEV_ORG b on " + "a.org_id=b.org_id where " + Condition
                + " order by a.org_id asc,a.userid " + "asc) and " + Condition + " order by a.org_id asc,a.TU_ID asc";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            TB_SEV_USER myuser=null;
            while (rs.next()) {
                myuser = new TB_SEV_USER();
                myuser.setTu_id(rs.getString("tu_id"));
                myuser.setUserid(rs.getString("userid"));
                myuser.setPassword(rs.getString("password"));
                myuser.setUsername(rs.getString("username"));
                myuser.setPhone(rs.getString("phone") == null ? "" : rs.getString("phone"));
                myuser.setEmail(rs.getString("email") == null ? "" : rs.getString("email"));
                myuser.setQq(rs.getString("qq") == null ? "" : rs.getString("qq"));
                myuser.setAddr(rs.getString("addr") == null ? "" : rs.getString("addr"));
                myuser.setCreate_time(rs.getDate("create_time") + " " + rs.getTime("create_time"));
                myuser.setLevels(rs.getString("levels"));
                myuser.setStatus(rs.getString("status"));
                myuser.setOrg_id(rs.getString("org_id"));
                list.add(myuser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
     * 密码重置
     * 
     * @param tu_id 用户表ID
     * @param resetPassword 充值的密码
     * @param tablename 为平台、银行、商户三个管理员表的表名
     * @return
     */
    public boolean resetPassword(String tu_id, String resetPassword, String tablename) {
        PublicDBhandles PublicDBhandles = new PublicDBhandles();
        boolean flag = false;
        String sql;
        sql = "update " + tablename + " set password='" + resetPassword + "' where tu_id='" + tu_id + "' ";
        try {
            PublicDBhandles.init(sql);
            flag = PublicDBhandles.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过商户ID得到用户账号级别
     * 
     * @param u_id
     * @return
     */
    public String getuserid(String tu_id, String tablename) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String name = "";
        String sql = "select userid from " + tablename + " where tu_id='" + tu_id + "' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                name = rs.getString("userid");
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
        return name;
    }

    /**
     * 添加时验是否存在
     * 
     * @return
     */
    public boolean checkadd(String userid, String ts_id) {
        boolean bl = false;
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select userid from TB_SEV_USER where userid='" + userid + "'" + " and u_id='" + ts_id + "' and status='1'";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
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

    public TB_SEV_USER getTB_CDE_USERByUser_id(String tu_id, String tablename) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        TB_SEV_USER myuser = null;
        String sql = "select * from " + tablename + "  where tu_id='" + tu_id + "' and status='1' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                myuser = new TB_SEV_USER();
                myuser.setTu_id(rs.getString("tu_id"));
                myuser.setRole_code(rs.getString("role_code"));
                myuser.setU_id(rs.getString("u_id"));
                myuser.setUserid(rs.getString("userid"));
                myuser.setPassword(rs.getString("password"));
                myuser.setUsername(rs.getString("username"));
                myuser.setPhone(rs.getString("phone") == null ? "" : rs.getString("phone"));
                myuser.setEmail(rs.getString("email") == null ? "" : rs.getString("email"));
                myuser.setQq(rs.getString("qq") == null ? "" : rs.getString("qq"));
                myuser.setAddr(rs.getString("addr") == null ? "" : rs.getString("addr"));
                myuser.setCreate_time(rs.getDate("create_time") + " " + rs.getTime("create_time"));
                myuser.setLevels(rs.getString("levels"));
                myuser.setStatus(rs.getString("status"));
                myuser.setLogin_ip(rs.getString("login_ip") == null ? "第一次登陆" : rs.getString("login_ip"));
                myuser.setLogin_time(rs.getString("login_time") == null ? "第一次登陆" : rs.getDate("login_time") + " " + rs.getTime("login_time"));
                myuser.setOrg_id(rs.getString("org_id"));
                myuser.setIsdrawback(rs.getString("isdrawback")==null?"1":rs.getString("isdrawback"));
                myuser.setPayWays(rs.getString("payWays")==null?"":rs.getString("payWays"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
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
        return myuser;
    }

    /**
     * 根据操作员号查询rolecode
     * 
     * @return
     */
    public String rolecode(String userid, String u_id) {
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String role_code = "";
        String sql = "select role_code from TB_SEV_USER where userid='" + userid + "'" + " and u_id='" + u_id + "' and status='1'";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
    		rs=pu.Query();
            if (rs.next()) {
                role_code = rs.getString("role_code");
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
        return role_code;
    }

    // 范玲香
    /**
     * 查询当前登录人的ts_id
     * 
     * @param tu_id
     * @return
     */
    public String getTs_id(String tu_id) {
        String ts_id = "";
        ResultSet rs = null;
        PublicDBhandles pu = null;
        String sql = "select u_id from TB_SEV_USER where tu_id='" + tu_id + "'";
        try {
            pu = new PublicDBhandles();
            pu.init(sql.toString());
            rs = pu.Query();
            while (rs.next()) {
                ts_id = rs.getString("u_id");
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
        return ts_id;
    }

    /**
     * 通过商户ID得到小区
     * 
     * @param u_id
     * @return
     */
    public String getyznr(String tu_id, String ts_id) {
        PublicDBhandles pu = new PublicDBhandles();
        ResultSet rs = null;
        String qq = "";
        String sql = "select qq from TB_SEV_USER where status=1 and tu_id='" + tu_id + "' and u_id='" + ts_id + "' ";
        try {
            pu.init(sql.toString());
            rs = pu.Query();
            if (rs.next()) {
                qq = rs.getString("qq");
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
        return qq;
    }
}
