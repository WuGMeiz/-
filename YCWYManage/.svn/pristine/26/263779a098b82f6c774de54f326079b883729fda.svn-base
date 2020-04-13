package WYCommunity;
import java.io.File;
import java.net.URL;

public class GetClassPath{ 
    public GetClassPath(){
        super();
    } 
    /** * main 
     * * @param args String[] 
     */ 
//    public static void main(String[] args){
//        GetClassPath getclasspath = new GetClassPath(); 
//        //System.out.println(getclasspath.getClassPath()); 
//        String aaa=getclasspath.getClassPath();
//        System.out.println(aaa);
//    }
    /** * 在类中取得当前文件所在的相对路径与绝对路�?
     * * @return String */
    public String getClassPath(){ 
        String strClassName = getClass().getName();  
        String strPackageName = ""; 
        if(getClass().getPackage() != null) { 
            strPackageName = getClass().getPackage().getName(); 
        } 
        //System.out.println("ClassName:" + strClassName); 
       // System.out.println("PackageName:" + strPackageName); 
        String strClassFileName = ""; 
        if(!"".equals(strPackageName)){
            strClassFileName = strClassName.substring(strPackageName.length() + 1,strClassName.length()); 
        } 
        else { 
            strClassFileName = strClassName; 
        } 
        //System.out.println("ClassFileName:" + strClassFileName); 
        URL url = null; 
        url = getClass().getResource(strClassFileName + ".class"); 
        String strURL = url.toString();
        String middleString = System.getProperty("file.separator"); //取得操作系统路径分割�?        //System.out.println( "[" + middleString + "]" );
        //System.out.println( "[" + System.getProperty("user.dir") + "]" );
       
        strURL = strURL.substring(strURL.indexOf( "/" ) + 1,strURL.lastIndexOf( "/" ));//System.getProperty("user.dir");//strURL.substring(strURL.indexOf( "/" ) + 1,strURL.lastIndexOf( "/" ));
      String os = getOSName();      
   
      if(os.startsWith("windows"))
      {      
    	  strURL=strURL;
      }
      else
      {      
    	  strURL=middleString+strURL;
      }     
      
      strURL = strURL.replace("/", File.separator).replace("%20", " ");
        return strURL; 
    }
    public static String getOSName() {      
        return System.getProperty("os.name").toLowerCase();      
    } 
}
