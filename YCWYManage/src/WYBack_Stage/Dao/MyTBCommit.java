package WYBack_Stage.Dao;

import java.util.List;

import ccbjf.system.db.PublicDBhandle;



public class MyTBCommit {

	/**
	 * 使用事务提交批量执行回收站删除或还原的方法，失败后数据库操作回滚。
	 * @param sql
	 * @return
	 */
	public static String TBCommit(List<String> sql)
	{
		String bl="";
		PublicDBhandle pu=null;
		try {
			pu = new PublicDBhandle();
			bl=pu.eUpdatecommitbl(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pu.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bl;
	}	

	/**
	 * 使用事务提交批量执行回收站删除或还原的方法，失败后数据库操作回滚。(预编译传sql字符串)
	 * @param sql
	 * @return
	 */
	public static String TBCommitp(List list,String sql)
	{
		String bl="";
		PublicDBhandle pu=null;
		try {
			pu = new PublicDBhandle();
			bl=pu.pUpdatecommit(list, sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pu.closeSql();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bl;
	}	
}


