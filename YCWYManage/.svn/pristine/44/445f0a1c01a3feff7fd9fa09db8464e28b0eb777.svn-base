package WYCommunity;

import java.util.Random;

public class CreateRandom {
	
	private int iNum = 0;
	private String codeList = "";
	
	public CreateRandom(){}
	/**
	 * 生成随机数的构�?函数
	 * @param iNum 返回随机数的位数
	 * @param codeList [值为：num时在0�?数字中取随机数；值为：str时在小写字母a到z中取随机数；值也可以自己输入随机数范围，例如：�?12345abcde”]
	 */
	public CreateRandom(int iNum,String codeList) 
	{
		this.iNum = iNum;
		if(codeList.equals("num"))
		{
			this.codeList ="0123456789";
		}
		else if(codeList.equals("str"))
		{
			this.codeList ="abcdefghijklmnopqrstuvwxyz";
		}
		else
		{
			this.codeList = codeList; 
		}
	} 
	/**
	 * 得到随机�?
	 * @return
	 */
	public String getRandstr()
	{
		Random random = new Random();

		String sRand="";
		for (int i=0;i<iNum;i++)
		{
			int rand=random.nextInt(codeList.length());
			String strRand=codeList.substring(rand,rand+1);
			sRand+=strRand;
		}

		return sRand;
	}
	/**
	 * 生成随机数的构�?函数
	 * @param iNum 返回随机数的位数
	 * @param codeList [值为：num时在0�?数字中取随机数；值为：str时在小写字母a到z中取随机数；值也可以自己输入随机数范围，例如：�?12345abcde”]
	 */
	public static String getRandstr(int iNum,String codeList)
	{

		if(codeList.equals("num"))
		{
			codeList ="0123456789";
		}
		if(codeList.equals("str"))
		{
			codeList ="abcdefghijklmnopqrstuvwxyz";
		}
		
		Random random = new Random();

		String sRand="";
		for (int i=0;i<iNum;i++)
		{
			int rand=random.nextInt(codeList.length());
			String strRand=codeList.substring(rand,rand+1);
			sRand+=strRand;
		}

		return sRand;
	}

//  public static void main(String[] args){
//	  for(int i=0;i<100;i++)
//	  {
//		  String userselpass=CreateRandom.getRandstr(6, "1234567890abcdefghijklmnopqrstuvwxyz");
//	  	System.out.println(userselpass);
//	  }
//  }
}
