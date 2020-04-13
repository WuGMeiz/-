package WYBack_Stage.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import WYBack_Stage.Bean.TB_CDE_MENU;
import WYCommunity.S_string;
import ccbjf.system.db.PublicDBhandle;
import ccbjf.system.db.PublicDBhandles;
public class MyTB_CDE_MENU {
    public String getMenu(String ROLE_CODE)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        String sql="select t.menu_code from tb_cde_role t where t.role_code in ("+ROLE_CODE+") ";

        String menu="";
        try {
            pu.init(sql.toString());
            rs=pu.Query();
            
            while(rs.next())
            {
                menu+=rs.getString("menu_code");
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
        return menu;
    }
    public List<TB_CDE_MENU> getMenu(String menu,String MENU_LEVEL)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        List<TB_CDE_MENU> list=new ArrayList<TB_CDE_MENU>();
        try {
            String str="";
            List<String> liststr=new S_string().getIndexString(menu, ',');
            for(int i=0;i<liststr.size();i++)
            {
                
                str+="'"+(String)liststr.get(i)+"',";
            }
            str=str.substring(0, str.length()-1);
                
            String sql = "select MENU_CODE,MENU_NAME,URL,MENU_LEVEL,UP_MENU_CODE from tb_cde_menu  where menu_code in ("+str+") and menu_level='"+MENU_LEVEL+"' and status='1' order by menu_code ";         
            
            pu.init(sql.toString());
            rs=pu.Query();
            TB_CDE_MENU mm=null;
            while(rs.next())
            {
                mm=new TB_CDE_MENU();
                mm.setMenu_code(rs.getString("menu_code"));
                mm.setMenu_name(rs.getString("menu_name"));
                mm.setUrl(rs.getString("url"));
                mm.setMenu_level(rs.getString("menu_level"));
                mm.setUp_menu_code(rs.getString("up_menu_code"));
                
                list.add(mm);
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
        
        return list;
    }
    public List<TB_CDE_MENU> getMenu2(String menu,String menu_code,String menu_level)
    {
        PublicDBhandles pu=new PublicDBhandles();
        ResultSet rs = null;
        List<TB_CDE_MENU> list=new ArrayList<TB_CDE_MENU>();
        try {
            String str="";
            List<String> liststr=new S_string().getIndexString(menu, ',');
            for(int i=0;i<liststr.size();i++)
            {
                str+="'"+(String)liststr.get(i)+"',";
            }
            str=str.substring(0, str.length()-1);   
            
            String sql="select t.menu_code, t.menu_name, t.url, t.MENU_LEVEL, t.UP_MENU_CODE from tb_cde_menu t where t.menu_code like '"+menu_code+"%' and t.menu_level='"+menu_level+"' " +
            " and t.menu_code in("+str+") and status='1' order by t.menu_code ";
            
            pu.init(sql.toString());
            rs=pu.Query();
            TB_CDE_MENU mm=null;
            while(rs.next())
            {
                mm=new TB_CDE_MENU();
                mm.setMenu_code(rs.getString("menu_code"));
                mm.setMenu_name(rs.getString("menu_name"));
                mm.setUrl(rs.getString("url"));
                mm.setMenu_level(rs.getString("menu_level"));
                mm.setUp_menu_code(rs.getString("up_menu_code"));
                
                list.add(mm);
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
        
        return list;
    }  
    public List<TB_CDE_MENU> get_Menu_All(String menu_code, String up_menu_code) {
        ResultSet rs=null;
        PublicDBhandle pu=null;
        List<TB_CDE_MENU> tu = new ArrayList<TB_CDE_MENU>();
        try {
            String str = "";
            List<String> liststr = new S_string().getIndexString(menu_code, ',');
            for (int i = 0;i < liststr.size();i++) {
                str += "'"+(String) liststr.get(i)+"',";
            }
            str = str.substring(0, str.length() - 1);
            String sql = "select menu_code,menu_name,url,menu_level,up_menu_code from TB_CDE_MENU where menu_code in("+str+") and up_menu_code='"+up_menu_code+"' and status='1' order by menu_code ";
            pu = new PublicDBhandle();
            rs = pu.eQuery(sql);
            TB_CDE_MENU menu=null;
            while (rs.next()) {
                menu = new TB_CDE_MENU();
                menu.setMenu_code(rs.getString("menu_code"));
                menu.setMenu_name(rs.getString("menu_name"));
                menu.setUrl(rs.getString("url"));
                menu.setMenu_level(rs.getString("menu_level"));
                menu.setUp_menu_code(rs.getString("up_menu_code"));
                tu.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                pu.closeSql();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tu;
    }
    public String getMenu1(String role_code) {
        String menu_code="";
        ResultSet rs=null;
        PublicDBhandle pu=null;
        String sql = "select menu_code from TB_CDE_ROLE a left join TB_SEV_USER b on a.role_code=b.role_code where b.tu_id= '"+role_code+"'";
        try {
            pu = new PublicDBhandle();
            rs = pu.eQuery(sql);
            while (rs.next()) {
                menu_code = rs.getString("menu_code");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null){
                    rs.close();
                }
                pu.closeSql();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return menu_code;
    }
}
