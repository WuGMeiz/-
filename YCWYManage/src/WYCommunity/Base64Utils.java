package WYCommunity;

import sun.misc.BASE64Decoder;

public class Base64Utils {
	/**
	 * 加密方法
	 * @param b 
	 * @return
	 */
	public static String getBASE64(byte[] b) 
	{
		String s = null;
		if (b != null) 
		{
		s = new sun.misc.BASE64Encoder().encode(b);
		}
		return s;
	}
		 
	public static byte[] getFromBASE64(String s) 
	{
		byte[] b = null;
		if (s != null) 
		{
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				return b;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	/**
	 * BASE64加密字符�?	 * @param s 传入普�?的字符串
	 * @return 返回加密后字符串
	 */
	public static String jiami(String s)
	{
		String x = getBASE64(s.getBytes());
		//x=x.replace("%", "|");
		//x=x.replace("&", "$");
		//x=x.replace("-", "_");
		x=x.replace("+", "@");
		x=x.replace("/", "$");
		x=x.replace("=", "~");
		x=x.replace("\r", "");
		x=x.replace("\n", "");
		//System.out.println(x);
		
		return x;
	}
	/**
	 * BASE64解密字符�?	 * @param s 传入加密的字符串
	 * @return 返回解密后的普�?字符�?	 */
	public static String jiemi(String s)
	{
		//s=s.replace("|","%");
		//s=s.replace("$","&");
		//s=s.replace("_","-");
		s=s.replace("@","+");
		s=s.replace("$","/");
		s=s.replace("~","=");
		//System.out.println(s);
		String x = new String(getFromBASE64(s));
		//System.out.println(x);
		return x;
	}
//	public static void main(String[] args) throws UnsupportedEncodingException {
//			    String s = "abcd啊啊，，—�?！@#%#%…�?…�?&*）（�? 35460986·~";
//			    System.out.println("加密前：" + s);
//			    String x = getBASE64(s.getBytes());
//			    System.out.println("加密后：" + x);
//			    String x1 = new String(getFromBASE64(x));
//			    System.out.println("解密后：" + x1);		    
//			    String ss = "abcd啊啊，，—�?！@#%#%…�?…�?&*）（�? 11111111·~";
//			    System.out.println("加密前：" + ss);
//			    String xx =Base64.encode(s.getBytes());
//			    System.out.println("加密后：" + xx);
//			    String xx1 = new String(Base64.decode(xx));
//			    System.out.println("解密后：" + xx1);
//	}
	
}
