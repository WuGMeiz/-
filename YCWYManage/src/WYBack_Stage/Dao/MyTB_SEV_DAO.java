package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import WYBack_Stage.Bean.TB_SEV;

import ccbjf.system.db.PublicDBhandles;


public class MyTB_SEV_DAO {
    /**
     * 获取单位信息（远程）
     * @param ts_id
     * @return
     */
    public TB_SEV getTB_SEV1(String ts_id)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        TB_SEV ts=null;
        StringBuffer sql = new StringBuffer("select * from TB_SEV where ts_id='");
        try {
             sql.append(ts_id+"'");
             pu.init(sql.toString());
             rs=pu.Query();
            if(rs.next()) 
            {
                ts=new TB_SEV();
                ts.setTs_id(rs.getString("ts_id"));
                ts.setA_id(rs.getString("a_id"));
                ts.setCreate_time(rs.getDate("create_time")+" "+rs.getTime("create_time"));
                ts.setJing(rs.getString("jing")==null?"":rs.getString("jing"));
                ts.setLogo(rs.getString("logo")==null?"":rs.getString("logo"));
                ts.setShenhe(rs.getString("shenhe")==null?"":rs.getString("shenhe"));
                ts.setStatus(rs.getString("status")==null?"":rs.getString("status"));
                ts.setTa_id(rs.getString("ta_id"));
                ts.setTs_balance(rs.getString("ts_balance"));
                ts.setTs_company_name(rs.getString("ts_company_name")==null?"":rs.getString("ts_company_name"));
                ts.setTs_email(rs.getString("ts_email")==null?"":rs.getString("ts_email"));
                ts.setTs_id(rs.getString("ts_id"));
                ts.setTs_introduction(rs.getString("ts_introduction")==null?"":rs.getString("ts_introduction"));
                ts.setTs_live(rs.getString("ts_live")==null?"":rs.getString("ts_live"));
                ts.setTs_name(rs.getString("ts_name")==null?"":rs.getString("ts_name"));
                ts.setTs_phone(rs.getString("ts_phone")==null?"":rs.getString("ts_phone"));
                ts.setTs_qq(rs.getString("ts_qq")==null?"":rs.getString("ts_qq"));
                ts.setTs_revision_level(rs.getString("ts_revision_level"));
                ts.setTs_tel(rs.getString("ts_tel")==null?"":rs.getString("ts_tel"));
                ts.setTs_virtual_currency(rs.getString("ts_virtual_currency"));
                ts.setTs_yyzz(rs.getString("ts_yyzz")==null?"":rs.getString("ts_yyzz"));
                ts.setWei(rs.getString("wei")==null?"":rs.getString("wei"));
                ts.setOrg_id(rs.getString("org_id")==null?"":rs.getString("org_id"));
                ts.setRole_code(rs.getString("role_code")==null?"":rs.getString("role_code"));
                ts.setShdm(rs.getString("shdm")==null?"":rs.getString("shdm"));
                ts.setStart_time(rs.getString("start_time")==null?"":rs.getDate("start_time")+" "+rs.getTime("start_time"));
                ts.setEnd_time(rs.getString("end_time")==null?"":rs.getDate("end_time")+" "+rs.getTime("end_time"));
                ts.setIsxjjf(rs.getString("isxjjf"));
                ts.setIsqz_hb(rs.getString("isqz_hb"));
                ts.setIsupload(rs.getString("isupload"));
                ts.setD_count(rs.getString("d_count"));
                ts.setStartbs(rs.getString("startbs")==null?"":rs.getString("startbs"));
                ts.setEndbs(rs.getString("endbs")==null?"":rs.getString("endbs"));
                ts.setYhme(rs.getString("yhme")==null?"":rs.getString("yhme"));
                ts.setYhxx(rs.getString("yhxx")==null?"":rs.getString("yhxx"));
                ts.setSh_type(rs.getString("sh_type"));
                ts.setSort(rs.getString("sort"));

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
        return ts;
        
    }
}
