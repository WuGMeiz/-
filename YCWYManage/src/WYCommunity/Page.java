package WYCommunity;
import java.sql.*;

import ccbjf.system.db.PublicDBhandle;
public class Page {
//	传值数据必须是tablename,condition,pagesize,pagenum
	private int pagenum=1;//当前页数
	private String tablename;//表名
	private String condition="1=1";//分页的条件
	private int pagesize;//每页显示条数
	private int pagecount;//共多少页
	private int countnum=0;
	private String innerj="";	//适用多表联合查询时设置表之间的连接语句
	private String Un_id="";
    private String groupby="";//group by 子句
	private int Allcount;/*张均鹏*/
   

	public String getUn_id() {
		return Un_id;
	}
	public void setUn_id(String un_id) {
		Un_id = un_id;
	}
	public int getAllcount() {
		return Allcount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public void setCountnum(int countnum) {
		this.countnum = countnum;
	}
	public int getPagenum()
	{
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		if(pagenum>this.getPagecount())
		{
			pagenum=this.getPagecount();
		}
		if(pagenum<1)
		{
			pagenum=1;
		}
		this.pagenum = pagenum;
		
	}
	public int getPagesize() {
		return pagesize;
	}
	
	public void setTablename(String tablename)
	{
		this.tablename=tablename;
	}
	
	public int getPagecount() {
		return pagecount;
	}
	public int getCountnum()
	{
		return this.countnum;
		
	}
	public String getTablename()
	{
		return this.tablename;
	}
	public String getInnerj() {
		return innerj;
	}
	public void setInnerj(String innerj) {
		this.innerj = innerj;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
		int i=0;
		try {
		PublicDBhandle con=new PublicDBhandle();
		ResultSet rs;
		ResultSet rs1;
		
		rs=con.eQuery("select count(*) from "+this.getTablename()+" "+this.getInnerj()+" where "+this.getCondition());
		if(rs.next())
			{
				i=rs.getInt(1);
				
			}
			rs.close();
			rs1=con.eQuery("select count(*) from "+this.getTablename());
			
			if(rs1.next())
			{
				this.countnum=i;
			}
			rs1.close();
			
			con.closeSql();
		} catch (Exception e) 
		{
			System.out.println("Page:setPagesize2--"+e.toString());
		}
		if((i%this.pagesize)>0)
		{
			this.pagecount=i/this.pagesize+1;		
		}
		else
		{
			this.pagecount=i/this.pagesize;
		}	
	}
	/*张均鹏*/
	public void setAllcount(int allcount) 
	{

		this.countnum=this.Allcount = allcount;
		if((Allcount%this.pagesize)>0)
		{
			this.pagecount=Allcount/this.pagesize+1;		
		}
		else
		{
			this.pagecount=Allcount/this.pagesize;
		}
		
		

	}
	public void setcountpage(int pagecount)
	{
		this.pagesize=pagecount;

	}
	
	/*小雨用于group by */
	
	public void setPagesize_g(int pagesize) {
		this.pagesize = pagesize;
		int i=0;
		try {
		PublicDBhandle con=new PublicDBhandle();
		PublicDBhandle con1=new PublicDBhandle();
		ResultSet rs;
		ResultSet rs1;
		String sql="select count(*) from ("
			+"select count(*) as 'number' from "+this.getTablename()+" "+this.getInnerj()+" where "+this.getCondition()+" "+this.getGroupby()
			+") t";
		rs=con.eQuery(sql);
				   
			if(rs.next())
			{
				i=rs.getInt(1);
				
			}
			rs.close();
			con.closeSql();
			
			rs1=con1.eQuery("select count(*) from "+this.getTablename());
			
			if(rs1.next())
			{
				this.countnum=i;
			}
			rs1.close();
			
			con1.closeSql();
		} catch (Exception e) 
		{
			System.out.println("Page:setPagesize--"+e.toString());
		}
		if((i%this.pagesize)>0)
		{
			this.pagecount=i/this.pagesize+1;		
		}
		else
		{
			this.pagecount=i/this.pagesize;
		}	
	}
	
	
	
	public String getGroupby() {
		return groupby;
	}
	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}
	
	//添加字段
	private String fenzuTiaojian;
	
	public String getFenzuTiaojian() {
		return fenzuTiaojian;
	}
	public void setFenzuTiaojian(String fenzuTiaojian) {
		this.fenzuTiaojian = fenzuTiaojian;
	}
	//添加方法
	public void setPagesize1(int pagesize) {
			this.pagesize = pagesize;
			int i=0;
			try {
			PublicDBhandle con=new PublicDBhandle();
			ResultSet rs;
			ResultSet rs1;
			//System.out.println("select count(*) from (select count(*) count from "+this.getTablename()+" "+this.getInnerj()+" where "+this.getCondition()+this.getFenzuTiaojian()+") as temp");

			rs=con.eQuery("select count(*) from (select count(*) count from "+this.getTablename()+" "+this.getInnerj()+" where "+this.getCondition()+this.getFenzuTiaojian()+") as temp");
			if(rs.next())
				{
					i=rs.getInt(1);
					
				}
				rs.close();
				//System.out.println("select count(*) from (select count(*) count from "+this.getTablename()+") as temp");

				rs1=con.eQuery("select count(*) from (select count(*) count from "+this.getTablename()+") as temp");

				if(rs1.next())
				{
					this.countnum=i;
				}
				rs1.close();
				
				con.closeSql();
			} catch (Exception e) 
			{
				System.out.println("Page:setPagesize2--"+e.toString());
			}
			if((i%this.pagesize)>0)
			{
				this.pagecount=i/this.pagesize+1;		
			}
			else
			{
				this.pagecount=i/this.pagesize;
			}	
		}
	

}
