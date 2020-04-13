package WYBack_Stage.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import WYCommunity.T_time;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String s="1-5-101";
	    int count = 0;
	    int index = 0;
	    while(s.indexOf("-")!=-1){
	        index=s.indexOf("-") + "-".length();
	        s = s.substring(index);
	        count++;
	    }
	    System.out.println(count);
	    s="1-5-101";
	    String tempStr = s.substring(s.indexOf("-") + 1, s.lastIndexOf("-"));
	    System.out.println(tempStr);*/
	    
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String startDate = "2018-10-1";
//        String ednDate = "2018-08-30";
//        
//		try {
//			int month = T_time.getMonth(sdf.parse(startDate), sdf.parse(ednDate));
//			System.out.println("两者之间相差：" + month);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String a="第1单元";
		String regEx="[^0-9]";  
		Pattern p = Pattern.compile(regEx);  
		Matcher m = p.matcher(a);  
		System.out.println( m.replaceAll("").trim());

	}

}
