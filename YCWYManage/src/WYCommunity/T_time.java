/**
 * 获得系统当前时间的类
 */
package WYCommunity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class T_time {

	private String time;
	
	public T_time() {

	}

	/**
	 * 得到系统当前格式为yyyy-MM-dd HH:mm:ss 的时间
	 *	例如 2011-11-11 11:11:11
	 */
	public String getTime() 
	{
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		this.time = sm.format(date);
		
		return time;
	}
	public String getTimeymdhms(Date date) {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.time = sm.format(date);
		return time;
	}	
	/**
	 * 得到格式为yyyy-MM-dd的日期
	 * @param date
	 * @return
	 */
	public String getTimeymd(Date date) {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		this.time = sm.format(date);
		return time;
	}
	/**
	 * 得到格式为yyyy-MM-dd的日期
	 * @return
	 */
	public String getTimeymd() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		this.time = sm.format(date);
		return time;
	}
	/**
	 * 得到YYYY年MM月DD日
	 * @return
	 */
	public String getTimeymdzw() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String times = sm.format(date);
		
		String yyyy=times.substring(0, times.indexOf("-"))+"年";
		String mm=times.substring(5, times.lastIndexOf("-"))+"月";
		String dd=times.substring(times.lastIndexOf("-")+1, times.length())+"日";
		
		String stime=yyyy+mm+dd;
		return stime;
	}
	
	/**
	 * 得到格式为yyyy的日期
	 * @return
	 */
	public String getTimeyyyy() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String times = sm.format(date);
		String yyyy=times.substring(0, 4);
		this.time=yyyy;
		return time;
	}
	/**
	 * 得到格式为MM的日期
	 * @return
	 */
	public String getTimemm() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String times = sm.format(date);
		String mm=times.substring(5, 7);
		this.time=mm;
		return time;
	}	
	/**
	 * 得到格式为DD的日期
	 * @return
	 */
	public String getTimedd() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String times = sm.format(date);
		String dd=times.substring(8, 10);
		this.time=dd;
		return time;
	}	
	/**
	 * 得到String型的无符号年月日时分秒的日期。例如：200808081818
	 * @return
	 */
	public String getymdhm() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();		
		String times = sm.format(date);
		String yyyy=times.substring(0, 4);
		String mm=times.substring(5, 7);
		String dd=times.substring(8, 10);
		
		String h=times.substring(11, 13);
		String m=times.substring(14, 16);
		this.time=yyyy+mm+dd+h+m;
		return time;
	}		
	/**
	 * 得到String型的无符号年月日时分秒的日期。例如：20080808181818
	 * @return
	 */
	public String getymdhms() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();		
		String times = sm.format(date);
		String yyyy=times.substring(0, 4);
		String mm=times.substring(5, 7);
		String dd=times.substring(8, 10);
		
		String h=times.substring(11, 13);
		String m=times.substring(14, 16);
		String s=times.substring(17, 19);
		this.time=yyyy+mm+dd+h+m+s;
		return time;
	}	
	/**
	 * 把yyyy-MM-dd hh:mm:ss格式的日期参数封装转换成整形返回。例如：2008-08-08 10:10:10封装转换成20080808101010
	 * @return 
	 */
	public String getymdhms(String times) {
	
		String yyyy=times.substring(0, times.indexOf("-"));
		String mm=times.substring(times.indexOf("-")+1, times.lastIndexOf("-"));
		String dd=times.substring(times.lastIndexOf("-")+1, times.indexOf(" "));
		
		String h=times.substring(times.indexOf(" ")+1, times.indexOf(":"));
		String m=times.substring(times.indexOf(":")+1, times.lastIndexOf(":"));
		String s=times.substring(times.lastIndexOf(":")+1, times.length());
		
		this.time=yyyy+mm+dd+h+m+s;
	
		return time;
	}		
	/**
	 * 把yyyy-MM-dd hh:mm:ss格式的日期参数封装转换成整形返回。例如：2008-08-08 10:10:10封装转换成2008年08月08日10:10
	 * @return 
	 */
	public String getymdhm(String times) {
	
		String yyyy=times.substring(0, times.indexOf("-"));
		String mm=times.substring(times.indexOf("-")+1, times.lastIndexOf("-"));
		String dd=times.substring(times.lastIndexOf("-")+1, times.indexOf(" "));
		
		String h=times.substring(times.indexOf(" ")+1, times.indexOf(":"));
		String m=times.substring(times.indexOf(":")+1, times.lastIndexOf(":"));
		
		this.time=yyyy+"年"+mm+"月"+dd+"日"+h+":"+m;
	
		return time;
	}		
	/**
	 * 得到String型的无符号年月日日期。例如：20080808
	 * @return
	 */
	public String getymd() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();		
		String times = sm.format(date);
		String yyyy=times.substring(0, 4);
		String mm=times.substring(5, 7);
		String dd=times.substring(8, 10);
		this.time=yyyy+mm+dd;
		return time;
	}
	/**
	 *把yyyy-MM-dd格式的日期参数封装转换成整形返回。例如：2008-08-08转换成20080808
	 * @param ymd
	 * @return
	 */
	public String getymd(String ymd) {
	
		String times = ymd;
		String yyyy=times.substring(0, 4);
		String mm=times.substring(5, 7);
		String dd=times.substring(8, 10);
		this.time=yyyy+mm+dd;
		return time;
	}
	/**
	 * 得到整型的系统当天日期的前一天的年月日格式的日期
	 * @return
	 */
	public int getIntTimeymdj1() 
	{
		int time=0;

		try { 
			Calendar c =Calendar.getInstance();
			//c.getActualMaximum(Calendar.DATE);
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(this.getTimeymd()));
			c.add(Calendar.DATE,-1);
			
			String times=this.getTimeymd(c.getTime());
			time=this.getIntTimeymd(times);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time;
	}
	/**
	 * 基于系统当前日期得到具体某天时间，
	 * @param tian 天数,tian可以为负数，-1代表当前日期减1天，1代表当前日期加1天，0代表当天时间
	 * @return 2010-01-01
	 */
	public String getTimeymd(int tian) 
	{
		try { 
			Calendar c =Calendar.getInstance();
			//c.getActualMaximum(Calendar.DATE);
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(this.getTimeymd()));
			c.add(Calendar.DATE, tian);
			
			this.time=this.getTimeymd(c.getTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time;
	}
	/**
	 * 通过2个参数得到加减运算后的年月日格式的日期
	 * @param ymd  YYYY-MM-DD日期字符串
	 * @param tian 天数,tian可以为负数，-1代表当前日期减1天，1代表当前日期加1天
	 * @return
	 */
	public String getTimeymd(String ymd,int tian) 
	{
		try { 
			Calendar c =Calendar.getInstance();
			//c.getActualMaximum(Calendar.DATE);
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(ymd));
			c.add(Calendar.DATE, tian);
			
			this.time=this.getTimeymd(c.getTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time;
	}
	public String getTimeymdhms(String ymdhms,int tian) 
	{
		try { 
			Calendar c =Calendar.getInstance();
			//c.getActualMaximum(Calendar.DATE);
			c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ymdhms));
			c.add(Calendar.DATE, tian);
			
			this.time=this.getTimeymdhms(c.getTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time;
	}
	/**
	 * 得到Int型的年月日日期。例如：20080808
	 * @return
	 */
	public int getIntTimeymd()
	{
		int time=0;
		String yyyy=this.getTimeymd().substring(0,4);
		String mm=this.getTimeymd().substring(5,7);
		String dd=this.getTimeymd().substring(8,10);
		time=Integer.parseInt(yyyy+mm+dd);
		return time;
	}

	/**
	 * 把yyyy-MM-dd格式的日期参数封装转换成整形返回。例如：2008-08-08封装转换成20080808
	 * @return
	 */	
	public int getIntTimeymd(String time)
	{

		int times=0;
		String yyyy=time.substring(0,4);
		String mm=time.substring(5,7);
		String dd=time.substring(8,10);
		times=Integer.parseInt(yyyy+mm+dd); 

		return times;
	}
	
	/**
	 * 把整形日期转换成字符串日期。例如：传20080808返回2008-08-08
	 * @param time
	 * @return
	 */
	public String getStringTimeymd(int time)
	{
		String times=time+"";
		String yyyy=times.substring(0, 4)+"-";
		String mm=times.substring(4, 6)+"-";
		String dd=times.substring(6, 8);
		times=yyyy+mm+dd;
		return times;
	}
	/**
	 * 把整形日期转换成字符串日期。例如：传20080808015959返回2008-08-08 01:59:59
	 * @param time
	 * @return
	 */
	public String getStringTimeymdhms(String time)
	{
		String times=time+"";
		String yyyy=times.substring(0, 4)+"-";
		String mm=times.substring(4, 6)+"-";
		String dd=times.substring(6, 8)+" ";
		String h=times.substring(8, 10)+":";
		String m=times.substring(10, 12)+":";
		String s=times.substring(12, 14);
		times=yyyy+mm+dd+h+m+s;
		return times;
	}
	/**
	 * 把整形时间转换成字符串时间。例如：传101010返回10:10:10,传81010返回8:10:10
	 * @param hms
	 * @return
	 */
	public String getStringhms(int hms)
	{
		String times=hms+"";
		
		String hh,mm,ss;
		
		if(times.length()==6)
		{
			 hh=times.substring(0, 2)+":";
			 mm=times.substring(2, 4)+":";
			 ss=times.substring(4, 6);
		}
		else
		{
			 hh=times.substring(0, 1)+":";
			 mm=times.substring(1, 3)+":";
			 ss=times.substring(3, 5);
		}
		times=hh+mm+ss;
		return times;
	}
	/**
	 * 把整形时间转换成字符串时间。例如：传101010返回10:10:10,传81010返回8:10:10
	 * @param hms
	 * @return
	 */
	public String getStringhms(String hms)
	{
		String times=hms+"";
		
		String hh,mm,ss;
		
		if(times.length()==6)
		{
			 hh=times.substring(0, 2)+":";
			 mm=times.substring(2, 4)+":";
			 ss=times.substring(4, 6);
		}
		else
		{
			 hh=times.substring(0, 1)+":";
			 mm=times.substring(1, 3)+":";
			 ss=times.substring(3, 5);
		}
		times=hh+mm+ss;
		return times;
	}	
	/**
	 * yyyy-MM 年-月
	 * @return
	 */
	public String getTimeym() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM");
		Date date=new Date();
		this.time = sm.format(date);
		return time;
	}
	/**
	 * 只得到int型的YYYY日期
	 * @return
	 */
	public int getintTimeyyyy() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy");
		Date date=new Date();
		String time = sm.format(date);
		int yyyy=Integer.parseInt(time);
		return yyyy;
	}
	/**
	 * 返回当前日期的月的1日
	 * @return
	 */
	public int getdTimeymd()
	{
		String ym=this.getTimeym();
		int time=0;
		String yyyy=ym.substring(0,4);
		String mm=ym.substring(5,7);
		String dd="01";
		time=Integer.parseInt(yyyy+mm+dd);
		return time;
	}
	/**
	 * HH:mm:ss时：分：秒
	 * @return
	 */
	public String getTimehms() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("HH:mm:ss");
		Date date=new Date();
		this.time = sm.format(date);
		return time;
	}
	/**
	 * HH:mm时：分
	 * @return
	 */
	public String getTimehm() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("HH:mm");
		Date date=new Date();
		this.time = sm.format(date);
		return time;
	}
	/**
	 * 得到系统当前的小时时间
	 * @return
	 */
	public int getTimeH()
	{
		int daytime;
		java.text.SimpleDateFormat sm=new SimpleDateFormat("HH");
		Date date=new Date();
		daytime = Integer.parseInt(sm.format(date));
		return daytime;
	}

	/**
	 * 根据日期YYYY-DD-MM字符串得到YYYY-MM日期返回
	 * @param time
	 * @return
	 */
	public String getYYYYMM(String time)
	{
		String mm=time.substring(0, time.lastIndexOf("-"));
		
		return mm;
	}
	/**
	 * 时间按秒生成流水号
	 * @return
	 */
	public String getliushui()
	{
		String CODE=new Date().getTime()+""; 
		CODE=CODE.substring(0,CODE.length()-3);
		
		return CODE;
	}
	/**
	 * 根据穿过来的时间，得到上个月年和月
	 * @param lastmonth
	 * @return
	 */
	public String getlastNY(String lastmonth)
	{	  	   	  	
		String lastMonth="";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(lastmonth));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
			lastMonth = df.format(c.getTime()).toString();
		} catch (java.text.ParseException e)
		{
			e.printStackTrace();
		}
		return lastMonth;	
	}

	/**
	 * 日期比较
	 */
	public boolean compartToDate(String date) 
	{
		boolean flag=false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
				int r = (df.parse(getTimeymd())).compareTo(df.parse(date));
				if(r==0)
				{
					flag=true;
				}
				if(r>0)
				{
					flag= false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return flag;
	
	}	   
	/**
	 * 传入YYYY-MM-DD hh:mm:ss格式的时间 比较是不是当天时间
	 * @param date
	 * @return 是返回true不是返回false
	 */
	public boolean istTheday(String date)
	{
		date=date.substring(0, date.indexOf(" "));
		String tday=this.getTimeymd();
		if(tday.equals(date))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * 得到2个日期之间的天数差
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysBetween (String beginDate, String endDate) throws ParseException
    {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date bDate = format.parse(beginDate);
         Date eDate = format.parse(endDate);
         Calendar d1 = new GregorianCalendar();
         d1.setTime(bDate);
         Calendar d2 = new GregorianCalendar();
        d2.setTime(eDate);
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2)
        {
               d1 = (Calendar) d1.clone();
              do   {
                          days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
                         d1.add(Calendar.YEAR, 1);
            }    while (d1.get(Calendar.YEAR) != y2);
       }
        return days;
   } 
	/**
	 * 得到2个日期之间的秒数差
	 * @param beginmiao
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static long getSecondBetween(long beginmiao, String endDate) throws ParseException
    {
		long endmiao=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date eDate = format.parse(endDate);
        endmiao=eDate.getTime();
        long rmiao = (endmiao - beginmiao) / 1000;
        
        return rmiao;
    } 	

	/**
	 * 传入2个日期得到相差的秒数
	 * @param beginDate 格式为yyyy-MM-dd HH:mm:ss日期字符串
	 * @param endDate 格式为yyyy-MM-dd HH:mm:ss日期字符串
	 * @return
	 * @throws ParseException
	 */
	public static long getSecondBetween(String beginDate, String endDate) throws ParseException
    {
		long beginmiao=0;
		long endmiao=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bDate = format.parse(beginDate);
        beginmiao=bDate.getTime();
        
        Date eDate = format.parse(endDate);
        endmiao=eDate.getTime();
        
        long rmiao = (endmiao - beginmiao) / 1000;
        
        return rmiao;
    } 	
	/**
	 * 传入毫秒转换成日期时间返回
	 * @param haomiao
	 * @return 返回格式为yyyy-MM-dd HH:mm:ss日期字符串
	 * @throws ParseException
	 */
	public static String getriqi(long haomiao) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(haomiao);
		String riqi=format.format(calendar.getTime());
		
        return riqi;
    } 
	/**
	 * 传入毫秒转换成时间返回
	 * @param haomiao
	 * @return 返回格式为yyyy-MM-dd HH:mm:ss日期字符串
	 * @throws ParseException
	 */
	public static String getshijian(long haomiao) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(haomiao);
		String riqi=format.format(calendar.getTime());
		
        return riqi;
    } 
	/**
	 * 得到当天是第几周
	 * @return
	 */
	public String getzhou()
	{
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
		Calendar calendar = Calendar.getInstance();
		//Date date = new Date();
//		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
//		if(dayOfWeek <0)dayOfWeek=0;
		return dayNames[dayOfWeek];

	}
	/**
	 * 传入yyyy-MM-dd格式日期得到该日期位当周的周几
	 * @param ymd
	 * @return
	 */
	public String getzhou(String ymd)
	{
		String zhou="";
		try {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" };
		Calendar calendar = Calendar.getInstance();
		//Date date = new Date();
		//calendar.setTime(date);
		calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(ymd));
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
//		if(dayOfWeek <0)dayOfWeek=0;
		zhou=dayNames[dayOfWeek];
		
		//Calendar c =Calendar.getInstance();
		//c.getActualMaximum(Calendar.DATE);
		//c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(ymd));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return zhou;
	}
	/**
	 * 通过传入开始结束2个YYYY-MM-DD格式的日期，封装2个日期之间的所有日期到list集合返回
	 * @param timek
	 * @param timej
	 * @return
	 */
	public List<String> getdatelist(String timek,String timej)
	{
		List<String> list=new ArrayList<String>(); 
		
		int yyyyk=Integer.parseInt(timek.substring(0, timek.indexOf("-")));
		int mmk=Integer.parseInt(timek.substring(timek.indexOf("-")+1, timek.lastIndexOf("-")));
		int ddk=Integer.parseInt(timek.substring(timek.lastIndexOf("-")+1, timek.length()));
		int yyyyj=Integer.parseInt(timej.substring(0, timej.indexOf("-")));
		int mmj=Integer.parseInt(timej.substring(timej.indexOf("-")+1, timej.lastIndexOf("-")));
		int ddj=Integer.parseInt(timej.substring(timej.lastIndexOf("-")+1, timej.length()));
		//System.out.println(mmk+"|"+ddk+"|"+mmj+"|"+ddj);
        for (int i = yyyyk; i <= yyyyj; i++) //循环年
        {
            int yuek = 0;   //开始月，用于循环月的变量
            int yuej = 0;   //结束月，用于循环月的变量
            if ((yyyyj - yyyyk) >= 1)   //结束年减去开始年大于、等于1时，即：最少2个年跨度
            {
                if (i == yyyyk) //年循环变量i为开始年份时
                {
                    yuek = mmk; //开始月为开始时间里的月份
                    yuej = 12;  //结束月为12月
                }
                else if (i == yyyyj)    //年循环变量i为结束年份时
                {
                    yuek = 1;   //开始月为1月
                    yuej = mmj; //结束月为结束时间里的月份
                }
                else //年循环变量i为开始年和结束年中间的年份时
                {
                    yuek = 1;   //开始月为1月
                    yuej = 12;  //结束月为12月
                }
            }
            if ((yyyyj - yyyyk) == 0) //结束年减去开始年等于0时，即：开始年和结束年为同一年
            {
                yuek = mmk; //开始月为开始时间里的月份
                yuej = mmj; //结束月为结束时间里的月份
            }
            
            for (int ii = yuek; ii <= yuej; ii++)   //循环月
            {
                int ri = GetMonthCount(i, ii);  //通过年、月得到该月的天数

                int rik = 0;    //开始日，用于循环日的变量
                int rij = 0;    //结束日，用于循环日的变量
                if ((yuej - yuek) >= 1)  //结束月减去开始月大于等于1时，即：最少有2个月跨度
                {
                    if (i == yyyyk && ii == yuek) //当年变量i为开始时间年份时，并且当月循环变量ii为开始月时
                    {
                        rik = ddk;  //开始日为开始时间里选择的日
                        rij = ri;   //结束日为该月的总天数
                    }
                    else if (i == yyyyj && ii == yuej) ////当年变量i为结束时间年份时，并且当月循环变量ii为结束月时
                    {
                        rik = 1;  //开始日为1日
                        rij = ddj;   //结束日为结束时间里选择的日
                    }
                    else //当月循环变量ii为开始月和结束月中间的月份时
                    {
                        rik = 1;  //开始日为1日
                        rij = ri;   //结束日为该月的总天数
                    }
                }
                if ((yuej - yuek) == 0)  //结束月减去开始月等于0时，即：开始月和结束月为同一个月
                {

                    if (i == yyyyk && yuej == mmk)  //当年变量i为开始时间年份时，并且结束月为开始时间的月份时（主要为12月）
                    {
                        rik = ddk;  //开始日为开始时间里的日
                        rij = ri;   //结束日为当月的天数
                    }
                    if (i == yyyyj && yuej == mmj)  //当年变量i为结束时间年份时，并且结束月为结束时间的月份时（主要为1月）
                    {
                        rik = 1;  //开始日为1日
                        rij = ddj;  //结束日为结束时间里的日
                    }
                    if (yyyyk == yyyyj && mmk == mmj) //当开始年和结束年为同一年，并且为同一月时变量日的设置
                    {
                        rik = ddk;
                        rij = ddj;
                    }
                }
				
                for (int iii = rik; iii <= rij; iii++)  //循环日，并且封装年月日的时间格式到list
                {
                	String yuestr=ii+"";
                	if(ii<10)
                	{
                		yuestr="0"+ii;
                	}
                	String ristr=iii+"";
                	if(iii<10)
                	{
                		ristr="0"+iii;
                	}
                    String newtime = i + "-" + yuestr + "-" + ristr;
                    list.add(newtime);
                }
				
			}
			
		}
		return list;
	}

	public boolean IsPinYear(int year)            //判断是否闰平年
	{
	    if (0==year%4&&((year%100!=0)||(year%400==0)))
	    {
	    	return true;
	    }
	    else 
	    {
	    	return false;
	    }
	}

	public int GetMonthCount(int year,int month)  //闰年二月为29天
	{
		int MonHead[]=new int[12];
	    MonHead[0] = 31; 
	    MonHead[1] = 28; 
	    MonHead[2] = 31; 
	    MonHead[3] = 30; 
	    MonHead[4]  = 31; 
	    MonHead[5]  = 30;
	    MonHead[6] = 31; 
	    MonHead[7] = 31; 
	    MonHead[8] = 30;
	    MonHead[9] = 31; 
	    MonHead[10] = 30; 
	    MonHead[11] = 31;
	    
	    int c=MonHead[month-1];
	    if((month==2) && this.IsPinYear(year)) 
	    {
	    	c++;
	    }
	    return c;
	}
	/**
	 * 得到当前月有几天
	 * @return
	 */
	public int getTMontCount()
	{
		int year = Integer.parseInt( this.getTimeyyyy() );
		int month = Integer.parseInt( this.getTimemm() );
		int ri = GetMonthCount(year, month);  //通过年、月得到该月的天数
		
		return ri;
	}
	/**
	 * 根据传入的月份返回替换获得的年月
	 * @return
	 */
	public String returndate(int yuefen){
		String date = this.getTimeyyyy();
		date = date +"-"+yuefen+"-1";
		return date;
	}
	/**
	 * 将传入的20141212设置成2014年12月12日返回
	 * @param riqi
	 * @return
	 */
	public static String getnyr(String riqi) {
		
		String yyyy=riqi.substring(0, 4);
		String mm=riqi.substring(4, 6);
		String dd=riqi.substring(6, 8);
		
		String date=yyyy+"年"+mm+"月"+dd+"日";
	
		return date;
	}	
	/**
	 * 将传入的2014-12-12设置成20141212返回
	 * @param riqi
	 * @return
	 */
	public static String getIntymd(String riqi) 
	{
		
		String date=riqi.replace("-", "");
	
		return date;
	}	
	/**
	 * 通过传入往前推移月份和往后推移月份，封装2个月份之间的所有日期到list集合返回
	 * @param timek
	 * @param timej
	 * @return 返回格式如：“2014年12月”
	 */
	public static List<String> getdatelist(int qianyue,int houyue)
	{
		List<String> list=new ArrayList<String>(); 
		
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String times = sm.format(date);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		try {

			int oldyuestr=qianyue;
			while(oldyuestr>0)
			{
				c.setTime(df.parse(times));
				c.set(Calendar.MONTH, c.get(Calendar.MONTH) - oldyuestr);
				String ny = df.format(c.getTime()).toString();
				String nian=ny.substring(0, times.indexOf("-"));
				String yue=ny.substring(5, times.lastIndexOf("-"));
				
				String temp=nian+"年"+yue+"月";	
				//System.out.println(temp);
				list.add(temp);
				oldyuestr--;
			}
			
			int newyuestr=0;
			while(newyuestr<=houyue)
			{
				c.setTime(df.parse(times));
				c.set(Calendar.MONTH, c.get(Calendar.MONTH) + newyuestr);
				String ny = df.format(c.getTime()).toString();
				String nian=ny.substring(0, times.indexOf("-"));
				String yue=ny.substring(5, times.lastIndexOf("-"));
				
				String temp=nian+"年"+yue+"月";	
				//System.out.println(temp);
				list.add(temp);
				newyuestr++;
			}
			
		} catch (java.text.ParseException e)
		{
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 通过传入往前推移月份和往后推移月份，封装2个月份之间的所有日期到list集合返回
	 * @param timek
	 * @param timej
	 * @return 返回格式如：“201412”
	 */
	public static List<String> getdatelistInt(int qianyue,int houyue)
	{
		List<String> list=new ArrayList<String>(); 
		
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String times = sm.format(date);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		try {

			int oldyuestr=qianyue;
			while(oldyuestr>0)
			{
				c.setTime(df.parse(times));
				c.set(Calendar.MONTH, c.get(Calendar.MONTH) - oldyuestr);
				String ny = df.format(c.getTime()).toString();
				String nian=ny.substring(0, times.indexOf("-"));
				String yue=ny.substring(5, times.lastIndexOf("-"));
				
				String temp=nian+yue;	
				//System.out.println(temp);
				list.add(temp);
				oldyuestr--;
			}
			
			int newyuestr=0;
			while(newyuestr<=houyue)
			{
				c.setTime(df.parse(times));
				c.set(Calendar.MONTH, c.get(Calendar.MONTH) + newyuestr);
				String ny = df.format(c.getTime()).toString();
				String nian=ny.substring(0, times.indexOf("-"));
				String yue=ny.substring(5, times.lastIndexOf("-"));
				
				String temp=nian+yue;	
				//System.out.println(temp);
				list.add(temp);
				newyuestr++;
			}
			
		} catch (java.text.ParseException e)
		{
			e.printStackTrace();
		}

		return list;
	}
	/**
	 * 得到String型的无符号年月日时分秒的日期。例如：200808
	 * @return
	 */
	public static String getym() {
		java.text.SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();		
		String times = sm.format(date);
		String yyyy=times.substring(0, 4);
		String mm=times.substring(5, 7);
		String time=yyyy+mm;
		return time;
	}
	//转换正确格式
	public static String getStringAndData(String datetime){
		String date="";
		Calendar c=Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime));
			Date d=new Date(c.getTimeInMillis());
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			date=format.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}
	
	/**
     * 获取两个日期相差几个月
     * @param start
     * @param end
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }
    
  //转换正确格式
  	public static List<String> getStringAndMath(String startTime,String endTime){
  		List<String> list = new ArrayList<String>();
  		try {
            Date startDate = new SimpleDateFormat("yyyy-MM").parse(startTime);
            Date endDate = new SimpleDateFormat("yyyy-MM").parse(endTime);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            // 获取开始年份和开始月份
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            // 获取结束年份和结束月份
            calendar.setTime(endDate);
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            //
            for (int i = startYear; i <= endYear; i++) {
                String date = "";
                if (startYear == endYear) {
                    for (int j = startMonth; j <= endMonth; j++) {
                        if (j < 9) {
                            date = i + "-0" + (j + 1);
                        } else {
                            date = i + "-" + (j + 1);
                        }
                        list.add(date);
                    }
                } else {
                    if (i == startYear) {
                        for (int j = startMonth; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else if (i == endYear) {
                        for (int j = 0; j <= endMonth; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    }

                }

            }

            // 所有的月份已经准备好
            //System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
  		return list;
  	}
    
  	 /*
  	输入日期字符串比如201703，返回当月第一天的Date
  	*/
  	public static Date getMinDateMonth(String month){
  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
  		Calendar calendar=Calendar.getInstance();
  		try {
		  	Date nowDate=sdf.parse(month);
		  	calendar = Calendar.getInstance();
		  	calendar.setTime(nowDate);
		  	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		  	return calendar.getTime();
	  	} catch (ParseException e) {
	  		e.printStackTrace();
	  	}
	  	return null;
  	}

  	/*
  	输入日期字符串，返回当月最后一天的Date
  	*/
  	public static Date getMaxDateMonth(String month){
  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
  		Calendar calendar=Calendar.getInstance();
  		try {
		  	Date nowDate=sdf.parse(month);
		  	calendar = Calendar.getInstance();
		  	calendar.setTime(nowDate);
		  	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		  	return calendar.getTime();
	  	} catch (ParseException e) {
	  		e.printStackTrace();
	  	}
	  	return null;
  	}
    
    public static void main(String[] args){
    	
    	List<String> list = getStringAndMath("2019-10-1","2019-10-01");
    	for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
            
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String time = sdf.format(calendar.getTime());//将Date类型转换成String类型
            System.out.println(sdf.format(getMinDateMonth(list.get(i))));
        	System.out.println(sdf.format(getMaxDateMonth(list.get(i))));
        }
    }
    
}
