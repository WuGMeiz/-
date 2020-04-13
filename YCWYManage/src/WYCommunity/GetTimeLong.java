package WYCommunity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTimeLong {

	/**
	 * 毫秒转成日期
	 * @author Administrator
	 *
	 */
	public static String getDataTime(long second){
		Date d=new Date(second);
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date=format.format(d);
		return date;
	}
	
	/**
	 * 日期转成毫秒
	 */
	public static long getMillis(String datetime){
		
		Calendar c=Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat("yyyyMMdd").parse(datetime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTimeInMillis();
	}
	/**
	 * 日期格式
	 */
	public static String getFormatDate(String date){
		long millis=getMillis(date);
		String time=getDataTime(millis);
		return time;
	}
	
	/*public static void main(String[] args) {
		String date=getFormatDate("2014-01-02");
	System.out.println(date);
}*/
}
