package WYCommunity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class S_string {	//特殊字符串处理类
	
	//public PublicDBhandle pu;
	//public ResultSet rs;
	
	public S_string() throws Exception{
		//pu = new PublicDBhandle();

	}

	/**
	 * 得到字符串和单个字符进行处理，按照参数ch把字符串str截取放到List中返回。
	 * 该方法主要适用于进行模糊查询时输入多个匹配名字
	 */
	public List<String> getIndexString(String str,char ch)
	{
		List<String> list=new ArrayList<String>();
		String temp;
		int j=0;
		for(int i=0;i<str.length();i++)
		{
			if((str.charAt(i))==ch)
			{
				// && this.getchar(str.substring(j,i), ch) && i!=j
				if(i!=j)
				{
					temp=str.substring(j, i);
					list.add(temp);
				}
				j=i+1;
				
			}
		}
		if(j<str.length())
		{
			list.add(str.substring(j,str.length()));
		}
		
		return list;
	}
	/**
	 * 传入字符串及分隔符号，截取封装成String对象的List返回。
	 * @param str原始字符串
	 * @param ch 分割字符号
	 * @return
	 */
	public static List<String> getIndexString(String str,String ch)
	{
		List<String> list=new ArrayList<String>();
		String strtemp=str;
		int j=0;
		while(j<1)
		{
			String temp="";
			int end=strtemp.indexOf(ch);
			if(end==-1)
			{
				if(strtemp.length()>0)
				{
					temp=strtemp.substring(0, strtemp.length());
					list.add(temp);
				}
				j=1;
			}
			else
			{
				temp=strtemp.substring(0, end);
				list.add(temp);
				strtemp=strtemp.substring(end+1, strtemp.length());
			}

		}
		
		return list;
	}
	/**
	 * 验证登陆帐号、密码不能为数据库特定字符串的方法
	 * 合法返回true; 否则返回false;
	 * @param str
	 * @return Boolean
	 */
	public static boolean dealSpecialString(String str)
	{
		boolean bl=false;
		if(str.equals(""))
		{
			 bl=true;
		}
		else
		{
			String regex="^[\u4E00-\uFA29A-Za-z0-9_.@#$()|\\/+-]+$"; //正则表达式	
			bl=str.matches(regex);
		}
		return bl;
	}

	public static boolean isChn(String str)
	{
		String regex="^[\u4E00-\uFA29]+$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}
	public static boolean isEngNum(String str)
	{
		String regex="^[A-Za-z0-9_@#$|\\/-]+$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}
	public static boolean isNum(String str)
	{
		String regex="^[0-9]+$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}
	/**
	 * 验证整数或者小数
	 * @param str
	 * @return
	 */
	public static boolean isZX(String str)
	{
		String regex="^[+-]?\\d+(\\.\\d+)?$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}
	public static boolean isEngD(String str)
	{
		String regex="^[A-Z]+$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}	
	/**
	 * 验证是否是日期格式
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date)    
    {    
        /**  
         * 判断日期格式和范围  
         */    
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";    
            
        Pattern pat = Pattern.compile(rexp);      
            
        Matcher mat = pat.matcher(date);      
            
        boolean dateType = mat.matches();    

        return dateType;    
    }    
	public static int getStringWidth(String str)
	{
		int count=0;
		for(int i=0;i<str.length();i++)
		{
			String temp=str.substring(i, i+1);
			if(isEngNum(temp))
			{
				count+=7;
			}
			else if(isEngD(temp))
			{
				count+=10;
			}			
			else
			{
				count+=13;
			}
		}

		return count;
	}
	/**
	 * 返回字符串String 该方法主要适用于ajax使用get传递参数时候的特殊字符串替换。
	 */
	public String getReplaceString(String str) {
		String temp = "";
		char b = '%';
		char j = '+';
		char bbb = '&';
		
		char bb = '％';
		char jj = '＋';
		char jjj = '＆';
		
		char bbbb='#';
		char jjjj='＃';
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == b) {
				temp = temp + bb;

			} else if (str.charAt(i) == j) {
				temp = temp + jj;
			} else if (str.charAt(i) == bbb) {
				temp = temp + jjj;
			} else if(str.charAt(i)==bbbb){
				temp=temp+jjjj;
				
			}else{
				temp = temp + str.charAt(i);
			}
		}

		return temp;
	}
	public static String Rep_strold(String rstr)
	{
	  rstr=rstr.replace("％","%");
	  rstr=rstr.replace("＆","&");
	  rstr=rstr.replace("＋","+");
	  rstr=rstr.replace("－","-");
	  rstr=rstr.replace("＃","#");
	  rstr=rstr.replace("＼","\\");
	  rstr=rstr.replace("～","~");
	  rstr=rstr.replace("！","!");
	  rstr=rstr.replace("＠","@");
	  rstr=rstr.replace("＄","$");
	  rstr=rstr.replace("＾","^");
	  rstr=rstr.replace("＊","*");
	  rstr=rstr.replace("（","(");
	  rstr=rstr.replace("）",")");
	  rstr=rstr.replace("＿","_");
	  rstr=rstr.replace("＝","=");
	  rstr=rstr.replace("｜","|");
	  rstr=rstr.replace("｀","`");
	  rstr=rstr.replace("？","?");
	  rstr=rstr.replace("，",",");
	  rstr=rstr.replace("。",".");
	  rstr=rstr.replace("；",";");
	  rstr=rstr.replace("’","'");
	  rstr=rstr.replace("＜","<");
	  rstr=rstr.replace("＞","<");  	  
	  return rstr;
	}
	public static String Rep_str(String rstr)
	{
	  rstr=rstr.replace("%", "％");
	  rstr=rstr.replace("&", "＆");
	  rstr=rstr.replace("+", "＋");
	  rstr=rstr.replace("#","＃");
	  rstr=rstr.replace("\\","＼");
	  rstr=rstr.replace("-","－");
	  rstr=rstr.replace("~","～");
	  rstr=rstr.replace("!","！");
	  rstr=rstr.replace("@","＠");
	  rstr=rstr.replace("$","＄");
	  rstr=rstr.replace("^","＾");
	  rstr=rstr.replace("*","＊");
	  rstr=rstr.replace("(","（");
	  rstr=rstr.replace(")","）");
	  rstr=rstr.replace("_","＿");
	  rstr=rstr.replace("=","＝");
	  rstr=rstr.replace("|","｜");
	  rstr=rstr.replace("`","｀");
	  rstr=rstr.replace("?","？");
	  rstr=rstr.replace(",","，");
	  rstr=rstr.replace(".","。");
	  rstr=rstr.replace(";","；");
	  rstr=rstr.replace("'","’");
	  rstr=rstr.replace("<","＜");
	  rstr=rstr.replace(">","＞");
	  return rstr;
	}
	
	/**
	 * 去掉所有空格 
	 * @param str
	 */
	public static String spacetrim(String str)
	{
		str=str.replaceAll(" +","");
		return str;
	} 
	/**
	 * 可以替换大部分空白字符， 不限于空格   \s 可以匹配空格、制表符、换页符等空白字符的其中任意一个  
	 * @param str
	 */
	public static String spaceReplace(String str)
	{
		str=str.replaceAll("\\s*", "");
		return str;
	}
	
	/**
	* 全角转半角的 转换函数
	* @Methods Name full2HalfChange
	* @Create In 2012-8-24 By v-jiangwei
	* @param QJstr
	* @return String
	*/
	public static final String full2HalfChange(String QJstr)
	{
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) 
		{
			Tstr = QJstr.substring(i, i + 1);
			// 全角空格转换成半角空格
			if (Tstr.equals("　")) 
			{
				outStrBuf.append(" ");
				continue;
			}
			try {		
				b = Tstr.getBytes("unicode");
				// 得到 unicode 字节数据
				if (b[2] == -1) 
				{
				// 表示全角
				b[3] = (byte) (b[3] + 32);
				b[2] = 0;
				outStrBuf.append(new String(b, "unicode"));
				} 
				else 
				{
					outStrBuf.append(Tstr);
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
	
		} // end for.
		return outStrBuf.toString();
	}

	/**
	* 半角转全角
	* @Methods Name half2Fullchange
	* @Create In 2012-8-24 By v-jiangwei
	* @param QJstr
	* @return String
	*/
	public static final String half2Fullchange(String QJstr)
	{
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) 
		{
			Tstr = QJstr.substring(i, i + 1);
			if (Tstr.equals(" ")) {
			// 半角空格
				outStrBuf.append(Tstr);
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				if (b[2] == 0) 
				{
					// 半角
					b[3] = (byte) (b[3] - 32);
					b[2] = -1;
					outStrBuf.append(new String(b, "unicode"));
				} 
				else 
				{
					outStrBuf.append(Tstr);
				}
				return outStrBuf.toString();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return outStrBuf.toString();
	}


	/**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     */
	public static String ToQJ(String input) 
	{
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) 
		{
			if (c[i] == ' ') 
			{
            	c[i] = '\u3000';
         	} 
			else if (c[i] < '\177') 
         	{
         		c[i] = (char) (c[i] + 65248);
         	}
         }
         return new String(c);
	}
 
	/**
	 * 全角转半角
	 * @param input　String
	 * @return半角字符串
	 */
	public static String ToBJ(String input) 
	{
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) 
		{
            if (c[i] == '\u3000') {
              c[i] = ' ';
            } 
            else if (c[i] > '\uFF00' && c[i] < '\uFF5F') 
            {
            	c[i] = (char) (c[i] - 65248);
            }
		}
		String returnString = new String(c);
		return returnString;
	}
	
	public static String getToGBK(String str) throws Exception
	{
		String temp = new String(str.getBytes("ISO-8859-1"), "gbk"); 
		return temp;
	}
	/**
	 * 把GET传递的参数转换成中文
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getToChina(String str) throws Exception
	{
		String temp = new String(str.getBytes("ISO8859-1"), "gb2312"); 
		return temp;
	}
	/**
	 * 把GET传递的参数转换成中文,如果得到的值为null则转换为空字符串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getUTF8ToChina(String str) throws Exception
	{
		if(str == null) {
			str=""; 
		}
		String temp = new String(str.getBytes("ISO-8859-1"), "UTF-8"); 
		return temp;
	}
	/**
	 * 将传入float类型数据转换为指定精度小数返回
	 * @param f
	 * @param wei
	 * @return
	 */
	public static String DecimalFormat_float(float f,int wei)
	{ 
		String xiaoshu="0.";
		for(int i=0;i<wei;i++)
		{
			xiaoshu+="0";
		}
		DecimalFormat de=new DecimalFormat(xiaoshu);
		
		return de.format(f);
	}
	/**
	 * 将传入double类型数据转换为指定精度小数返回
	 * @param f
	 * @param wei
	 * @return
	 */
	public static String DecimalFormat_double(double f,int wei)
	{ 
		String xiaoshu="0.";
		for(int i=0;i<wei;i++)
		{
			xiaoshu+="0";
		}
		DecimalFormat de=new DecimalFormat(xiaoshu);
		
		return de.format(f);
	}

	/**
	 * 将传入String类型数据转换为指定精度小数返回
	 * @param f
	 * @param wei
	 * @return
	 */
	public static String DecimalFormat_string(String f,int wei)
	{ 
		String xiaoshu="0.";
		for(int i=0;i<wei;i++)
		{
			xiaoshu+="0";
		}
		DecimalFormat de=new DecimalFormat(xiaoshu);
		
		return de.format(Double.parseDouble(f));
	}
 
	public String pipei(String test)
	{
		String str="";
		if(test.equals("0"))
		{
			str="S";
		}
		else if(test.equals("1"))
		{
			str="J";
		}	
		else if(test.equals("2"))
		{
			str="Z";
		}	
		else if(test.equals("3"))
		{
			str="G";
		}	
		else if(test.equals("4"))
		{
			str="u";
		}	
		else if(test.equals("5"))
		{
			str="o";
		}	
		else if(test.equals("6"))
		{
			str="C";
		}	
		else if(test.equals("7"))
		{
			str="h";
		}	
		else if(test.equals("8"))
		{
			str="a";
		}	
		else if(test.equals("9"))
		{
			str="i";
		}
		else
		{
			str=test;
		}
		return str;
	}
 
	
	public String ChartoF(char c)
	{
		  String   s   =   String.valueOf(c);   
		  byte[]   bytes   =   s.getBytes();   
		  String str="";
		  for(int   i=0;   i<bytes.length;   i++)
		  {
			  str+=Integer.toHexString(bytes[i] &0xff);
		  }
		  return str;
	}
	
	/**
	 * 
	 * 基本功能：判断标记是否存在
	 * <p>
	 * 
	 * @param input
	 * @return boolean
	 */
	public boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}	
	public String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}

		}
		return (filtered.toString());
	}	
	
	/**
	 * 用于得到请求的数据为NULL的转换为空
	 * @param text
	 * @return
	 */
	public static String formatString(String text){ 
		if(text == null) {
			return ""; 
		}
		return text;
	}
	
	/**
	 * 验证密码不能为数据库特定字符串的方法
	 * 合法返回true; 否则返回false;
	 * @param str
	 * @return Boolean
	 */
	public static boolean dealSpecialStringpass(String str)
	{
		String regex="^[A-Za-z0-9_.@-]+$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}
	public static boolean isPrice(String str)
	{
		String regex="^[0-9]+([.]{1}[0-9]+){0,1}$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}
    /**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String toUpperRMB(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        String head = n < 0? "负": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
    public static boolean isTszf(String str) {
        String regex = "^.*[(/) | (\\\\) | (:) | (\\*) | (\\?) | (\") | (<) | (>)].*$"; // 正则表达式
        boolean bl = false;
        bl = str.matches(regex);
        return bl;
    }
    /**
	 * 验证正数或者负数的正则
	 * @param str
	 * @return
	 */
	public static boolean isIntNum(String str)
	{
		String regex="^(-)?[1-9][0-9]*$"; //正则表达式
		boolean bl=false;
		bl=str.matches(regex);

		return bl;
	}	

}
