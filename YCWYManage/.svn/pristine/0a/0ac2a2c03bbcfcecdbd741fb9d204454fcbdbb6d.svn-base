package WYCommunity;
import java.security.*;

public class MD5 {

	public MD5() {
		// TODO 自动生成构造函数存根
	}

    public String createMd5(String plainText )
    {    
         StringBuffer buf = new StringBuffer("");
	     try {
		      MessageDigest md = MessageDigest.getInstance("MD5");
		      md.update(plainText.getBytes());
		      byte b[] = md.digest();
		      int i;
		       
		      for (int offset = 0; offset < b.length; offset++)
		       {
			       i = b[offset];
			       if(i<0) i+= 256;
			       if(i<16)
			         buf.append("0");
			       buf.append(Integer.toHexString(i));
		      }
	      	} 
     	 catch (NoSuchAlgorithmException e)
	     {
	      		e.printStackTrace();
	     }
	     return buf.toString();
	        	
    }
    /**
     * 建行md5加密方法
     * @param plainText
     * @return
     */
	public static String getCCBMd5(String plainText )
	{  
		int len,i;
		byte tb;
		char high,tmp,low;
		String result=new String();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte bySourceByte[] = md.digest();

			len=bySourceByte.length;
			for(i=0;i<len;i++)
			{
				tb=bySourceByte[i];
				
				tmp=(char)((tb>>>4)&0x000f);
				if(tmp>=10)
					high=(char)('a'+tmp-10);
				else
					high=(char)('0'+tmp);
				result+=high;
				tmp=(char)(tb&0x000f);
				if(tmp>=10)
					low=(char)('a'+tmp-10);
				else
					low=(char)('0'+tmp);
				
				result+=low;
			}
		} 
    	catch (NoSuchAlgorithmException e)
	    {
    		e.printStackTrace();
	    }
		return result; 
	        	
    }

    
}
