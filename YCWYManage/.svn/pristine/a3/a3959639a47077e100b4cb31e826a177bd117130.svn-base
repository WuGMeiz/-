package myfilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharacterAllTurnHalfAngle implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
        try {              
        	/*HttpServletRequest hsr = (HttpServletRequest) request;
        	HttpSession session=hsr.getSession();
        	session.setAttribute("service_index_url", hsr.getRequestURL().toString());*/
        	
        	request = new Request((HttpServletRequest) request);
        	
            filterChain.doFilter(request, response);   
        } catch (Exception localException) {
  		  
  		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	/**
	 * 过滤所有全角字符，转换成半角字符的方法
	 * @param param
	 * @return
	 */
	public String filter(String param) 
	{
		String returnString="";
		try {

			char c[] = param.toCharArray();
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
			returnString = new String(c);		
			returnString=returnString.replaceAll("\\s*", "");//可以替换大部分空白字符， 不限于空格   \s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
			returnString = Rep_str(returnString);
		} catch (Exception localException) {
		  
		}
		return returnString;
	}
	public String Rep_str(String rstr) {
	    rstr = rstr.replace("'", "’");
	    rstr = rstr.replace("<", "＜");
	    rstr = rstr.replace(">", "＞");
	    return rstr;
	}
	//内部类 为了重写request的getParameter 和 getParameterValues
	class Request extends HttpServletRequestWrapper
	{
		public Request(HttpServletRequest request) 
		{    
			super(request);   
		}
		
		@Override   
		public String getParameter(String name) 
		{	
			if(super.getParameter(name)!=null)
			{
				return filter(super.getParameter(name));    //返回值之前 先进行过滤  
			}
			else
			{
				return null;
			}
		}
		
		@Override   
		public String[] getParameterValues(String name) //返回值之前 先进行过滤 
		{ 
			String[] values = super.getParameterValues(name);      
			for (int i = 0; i < values.length; i++) 
			{ 
				values[i] = filter(values[i]);    
			}    
			return values;  
		}
	}
	
}
