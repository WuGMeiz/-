package WYCommunity;
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
/** 
 * @ClassName: MakeOrderNum 
 * @CreateTime 2017年2月27日 上午11:03:02 
 * @author : 郭晓光 
 * @Description: 订单号生成工具，生成非重复订单号，理论上限1毫秒1000个，可扩展 
 * 
 */  
public class MakeOrderNum {
    /** 
     * 锁对象，可以为任意对象 
     */  
    private static Object lockObj = "lockerOrder";  
    /** 
     * 订单号生成计数器 
     */  
    private static long orderNumCount = 0L;  
    private static long orderNumCount1 = 0L;  
    /** 
     * 每毫秒生成订单号数量最大值 
     */  
    private int maxPerMSECSize=1000;  
    //private int maxPerMSECSize1=100;  
    /** 
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展 
     * @param typestr 订单号前缀（用于区分不同业务的订单号，由业务类提交过来） 
     */  
    public String makeOrderNum(String typestr) {  
    	// 最终生成的订单号  
        String finOrderNum = ""; 
        try {  
            synchronized (lockObj) {  
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount > maxPerMSECSize) {  
                    orderNumCount = 0L;  
                }  
                //组装订单号  
                String countStr=maxPerMSECSize +orderNumCount+"";  
                finOrderNum = typestr + nowLong + countStr.substring(1);	//从下标位置截取到最后       
                orderNumCount++;  
            }  
        } catch (Exception e) {  
            //e.printStackTrace();  
        }  
        return finOrderNum;
    }  
    /** 
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展 
     * @param typestr 订单号前缀（用于区分不同业务的订单号，由业务类提交过来） 
     */ 
    /*public static void main(String args[]){
    	for(int i=0;i<999;i++){
    		
	 synchronized (lockObj) {
    	long nowLong = Long.parseLong(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));  
        // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
        if (orderNumCount > 100) {  
            orderNumCount = 0L;  
        }  
        //组装订单号  
        String finOrderNum = new MakeOrderNum().makeOrderNum1("");	//从下标位置截取到最后       
        System.out.println(finOrderNum);
	 }
    	}
    }*/
    public String makeOrderNum1(String typestr) {  
    	// 最终生成的订单号  
        String finOrderNum = ""; 
        try {  
            synchronized (lockObj) {  
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));  
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount1 >= maxPerMSECSize) {  
                	orderNumCount1 = 0L;  
                }  
                String str=nowLong+"";
                str=str.substring(2);
                //组装订单号  
                finOrderNum = typestr + str + orderNumCount1;	//从下标位置截取到最后       
                orderNumCount1++;  
            }  
        } catch (Exception e) {  
            //e.printStackTrace();  
        }  
        return finOrderNum;
    }  
  /*  public static void main(String[] args) {
		System.out.println(new MakeOrderNum().makeOrderNum("BS1256Y"));
	}*/
//    /** 
//     * 生成非重复订单号，理论上限1毫秒1000个，可扩展 
//     * @param tname 测试用 
//     */  
//    public void makeOrderNum(String tname) {  
//        try {  
//            // 最终生成的订单号  
//            String finOrderNum = "";  
//            synchronized (lockObj) {  
//                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
//                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
//                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
//                if (orderNumCount > maxPerMSECSize) {  
//                    orderNumCount = 0L;  
//                }  
//                //组装订单号  
//                String countStr=maxPerMSECSize +orderNumCount+"";  
//                finOrderNum=nowLong+countStr.substring(1);  
//                orderNumCount++;  
//                System.out.println(finOrderNum + "--" + Thread.currentThread().getName() + "::" + tname );  
//                // Thread.sleep(1000);  
//            }  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    } 
    public static void main(String[] args) {  
        // 测试多线程调用订单号生成工具  
        try {  
            for (int i = 0; i < 200; i++) 
            {  
                MakeOrderNum makeOrder = new MakeOrderNum();  
                       // makeOrder.makeOrderNum1("a");  
                        System.out.println(makeOrder.makeOrderNum1("a"));
                        MakeOrderNum makeOrder1 = new MakeOrderNum();  
                      //  makeOrder1.makeOrderNum1("b"); 
                        System.out.println(makeOrder1.makeOrderNum1("b"));
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
}
