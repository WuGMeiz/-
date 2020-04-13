package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ccbjf.system.db.PublicDBhandles;

public class SystemLogDao {
    /**
     * 得到某个单位及其下的所有子单位ID，并拼接成‘1’，‘2’的形式返回，便于查询使用。
     * @param org_id
     * @param tb_id
     * @return
     */
    public String getthisChildrenAll1(String org_id,String tb_id)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        String str="'"+org_id+"',"; 
        try {   
            String temp="'"+org_id+"'";
            boolean bl=true;
            while(bl)
            {
                int i=0;
                String sql="select org_id from TB_BANK_ORG where up_org_id in("+temp+")" +
                		" and tb_id='"+tb_id+"' ";
                
                pu.init(sql.toString());
                rs=pu.Query();
                
                temp="";
                while(rs.next())
                {
                    i++;
                    temp+="'"+rs.getString("org_id")+"',";
                }
                str+=temp;//放这里为了不被下边处理temp后边的逗号时产生拼接错误
                if(temp.length()>0)
                {
                    temp=temp.substring(0, temp.length()-1);
                }
                
                
                if(i==0) 
                {   
                    bl=false;
                }
                
            }
            if(str.length()>0)
            {
                str=str.substring(0, str.length()-1);
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
     * 得到某个单位及其下的所有子单位ID，并拼接成‘1’，‘2’的形式返回，便于查询使用。
     * @param org_id
     * @param tb_id
     * @return
     */
    public String getthisChildrenAll2(String org_id,String ts_id)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        String str="'"+org_id+"',"; 
        try {   
            String temp="'"+org_id+"'";
            boolean bl=true;
            while(bl)
            {
                int i=0;
                String sql="select org_id from TB_SEV_ORG where up_org_id in("+temp+") and ts_id='"+ts_id+"' ";
                pu.init(sql.toString());
                rs=pu.Query();
                
                temp="";
                while(rs.next())
                {
                    i++;
                    temp+="'"+rs.getString("org_id")+"',";
                }
                str+=temp;//放这里为了不被下边处理temp后边的逗号时产生拼接错误
                if(temp.length()>0)
                {
                    temp=temp.substring(0, temp.length()-1);
                }
                
                
                if(i==0) 
                {   
                    bl=false;
                }
                
            }
            if(str.length()>0)
            {
                str=str.substring(0, str.length()-1);
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
}
