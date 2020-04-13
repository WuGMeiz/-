package WYCommunity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyCookie {
	/** 
	 * 设置SSO认证标识(把用户名,密码 写入客户端浏览器的cookie),关闭浏览器后，cookie立即失效 
	 *  
	 * @param response 
	 *            HttpServletResponse 
	 * @param userName 
	 *            用户名 
	 * @param password 
	 *            密码 
	 */  
	public static void setReffer(final HttpServletResponse response, final String userName, final String password)  
	{  
	    final String sSession = password + userName;  //密码是SHA1加密,长度为40个字符(160位)  
	    Cookie oItem;  
	    // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..  
	    oItem = new Cookie("ccbjf", Base64Utils.jiami(sSession));  
	    oItem.setDomain(".eqiaotong.com");  //请用自己的域  
	    oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效          
	    oItem.setPath("/");  
	    response.addCookie(oItem);  
	}  
	
	/** 
	 * 认证SSO标识（从客户端浏览器读入cookie, 并取得用户名、密码，不能取得时返回null） 
	 *  
	 * @param request 
	 * @return 返回从cookie中取得的用户名、密码，不能取得时返回null.String[0]中保存用户名,String[1]中保存密码 
	 */  
	public static String[] checkReffer(final HttpServletRequest request)  
	{  
	    final Cookie[] oCookies = request.getCookies();  
	    if (oCookies != null)  
	    {  
	        for (final Cookie oItem : oCookies)  
	        {  
	            final String sName = oItem.getName();  
	  
	            if (sName.equals("ccbjf"))  
	            {  
	                final String sSession = Base64Utils.jiemi(oItem.getValue()); //解码 CookieUtil.decode()是BASE64解码方法,略..  
	                if (sSession.length() > 40)  
	                {  
	                    // 获得存储在 Cookies 中的用户名称和密码  
	                    final String sUser = sSession.substring(40);  
	                    final String sPass = sSession.substring(0, 40);  
	                    final String[] strArr =  
	                    { sUser, sPass };  
	                    return strArr; //返回用户名,密码  
	                }  
	            }  
	        }  
	    }  
	    return null;  
	}  
	/**
	 * 写入Cookie
	 * @param response 返回客户端页面对象
	 * @param CookiesName 要写入的Cookie名称
	 * @param CookiesValue 要写入的Cookie值
	 * @param time 要写入的Cookie的存活时间
	 */
	public static void setCookies(final HttpServletResponse response, final String CookiesName, final String CookiesValue,final int time)  
	{  
		//System.out.println("设置："+CookiesName+"|"+CookiesValue);
	    //初始化有两个参数，第一个参数cookieName定义了Cookie的名字，后一个参数，也是一个字符串，定义了Cookie的内容
	    Cookie oItem = new Cookie(CookiesName, Base64Utils.jiami(CookiesValue));  // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码
	    oItem.setMaxAge(time); //以秒为单位，-1关闭浏览器后，cookie立即失效，0可以删除cookie，365*24*60*60表示一年的时间
	    oItem.setPath("/");
	    response.addCookie(oItem);  
	 
	}  
	/**
	 * 得到指定名称的Cookie的值
	 * @param request 页面请求对象
	 * @param CookiesName 要得到的Cookie名称
	 * @return
	 */
	public static String getCookies(final HttpServletRequest request, final String CookiesName)
	{
		String CookiesValue="";
		try{
			Cookie[] cookies=request.getCookies();   //读出用户硬盘上的Cookie，并将所有的Cookie放到一个cookie对象数组里面
			if(cookies!=null)
			{   
				for(int i=0;i<cookies.length;i++)	//用一个循环语句遍历Cookie对象数组
				{   
					if(cookies[i].getName().equals(CookiesName)) //如果取出数组中的Cookie对象名称是储存的内容，将值赋值给name对象
					{    
						CookiesValue=Base64Utils.jiemi(cookies[i].getValue());
					}   
					
				}   
			} 
		}catch(Exception e){   
			e.printStackTrace();   
		} 
		
		return CookiesValue;
	}
//  Cookie的主要属性，及其方法：
//	String	getComment()	返回cookie中注释,如果没有注释的话将返回空值.
//	String	getDomain()	返回cookie中Cookie适用的域名. 使用getDomain() 方法可以指示浏览器把Cookie返回给同 一域内的其他服务器,而通常Cookie只返回给与发送它的服务器名字完全相同的服务器。注意域名必须以点开始（例如.yesky.com）
//	int	getMaxAge()	返回Cookie过期之前的最大时间，以秒计算。
//	String	getName()	返回Cookie的名字。名字和值是我们始终关心的两个部分，笔者会在后面详细介绍 getName/setName。
//	String	getPath()	返回Cookie适用的路径。如果不指定路径，Cookie将返回给当前页面所在目录及其子目录下 的所有页面。
//	boolean	getSecure()	如果浏览器通过安全协议发送cookies将返回true值，如果浏览器使用标准协议则返回false值。
//	String	getValue()	返回Cookie的值。笔者也将在后面详细介绍getValue/setValue。
//	int	getVersion()	返回Cookie所遵从的协议版本。
//	void	setComment(String purpose)	设置cookie中注释。
//	void	setDomain(String pattern)	设置cookie中Cookie适用的域名
//	void	setMaxAge(int expiry)	以秒计算，设置Cookie过期时间。
//	void	setPath(String uri)	指定Cookie适用的路径。
//	void	setSecure(boolean flag)	指出浏览器使用的安全协议，例如HTTPS或SSL。
//	void	setValue(String newValue)	cookie创建后设置一个新的值。
//	void	setVersion(int v)	设置Cookie所遵从的协议版本。 　
}
