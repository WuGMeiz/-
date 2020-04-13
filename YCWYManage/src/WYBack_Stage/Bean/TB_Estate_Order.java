package WYBack_Stage.Bean;

public class TB_Estate_Order {
	
	private int Eo_id;//编号，主键
	private int ts_id;//商户id
	private int a_id;//应用类别主键
	private int Es_id;//小区主键
	private int Bu_id;//楼宇主键
	private int Eh_id;//房屋主键
	private int orderType;//订单类型（1查缴订单 2预缴订单3导入订单）
	private String bankid;//银行流水号
	private String payItem;//缴费项
	private String total;//应缴金额
	private String total_yh;//优惠金额
	private String total_znj;//滞纳金金额
	private String total_sj;//实缴金额
	private String payType;//缴费方式（0网上支付，1现金支付 2扫码盒子 被扫支付  3转账支付 4刷卡支付 5调账支付 6主扫支付 ）
	private String creat_time;//创建时间
	private String pay_time;//支付时间
	private int payStatus;//支付状态（0未支付，1已支付，2部分支付）
	private String tk_total;//退款金额
	private String tk_time;//退款时间
	private String tk_type;//退款方式（0线上退款 1线下退款）
	private String tk_status;//退款状态（0默认， 1全部退款，2部分退款，）
	private String tk_Reason;//退款原因（not null）
	private String Cost_startTime;//费用开始期
	private String Cost_endTime;//费用结束期
	private String Scost_startTime;//收取开始时间
	private String Scost_endTime;//收取结束时间
	private int status;//状态（0删除1正常）
	private String LockSign;//锁定标志
	private String LockTime;//锁定时间
	
	private String EsName;//小区名称（联合查询字段）
	private String BuName;//楼宇名称（联合查询字段）
	private String UnName;//单元名称（联合查询字段）
	private String OwnerName;//业主姓名（联合查询字段）
	private String EhNumber;//房屋编号（联合查询字段）
	private String ItemName;//房屋编号（联合查询字段）
	private String ItemType;//收费方式（联合查询字段）
	
	private String sjje;
	private String sjbishu;
	private String xsjje;  //系统现金支付金额
	private String xsjbishu;
	private String tkje;
	private String tkbishu;
	private String znjDay;//滞纳金天数（联合查询字段）
	private String znjRatio;//滞纳金比例（联合查询字段）
	
	private String num;
	private String total_yj_all;
	private String total_sj_all;
	private String remark;
	
	private int printStatus; //订单状态
	private int printStatus1;//打印状态
	
	private int pjNumber;
	
	private String jbishu;   //建行支付笔数
	private String jhje;      //建行支付金额
	private String xwsbishu;  //系统网上支付笔数
	private String xwsje;
	private String xzsbishu;  //系统主扫支付笔数(静态二维码)
	private String xzsje;
	
	private String xbsbishu;  //系统被扫支付笔数（扫码盒子支付）
	private String xbsje;
	
	
	
	public int getPjNumber() {
		return pjNumber;
	}
	public void setPjNumber(int pjNumber) {
		this.pjNumber = pjNumber;
	}
	public int getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(int printStatus) {
		this.printStatus = printStatus;
	}
	public int getPrintStatus1() {
		return printStatus1;
	}
	public void setPrintStatus1(int printStatus1) {
		this.printStatus1 = printStatus1;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTotal_yj_all() {
		return total_yj_all;
	}
	public void setTotal_yj_all(String total_yj_all) {
		this.total_yj_all = total_yj_all;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTotal_sj_all() {
		return total_sj_all;
	}
	public void setTotal_sj_all(String total_sj_all) {
		this.total_sj_all = total_sj_all;
	}
	
	public String getZnjDay() {
		return znjDay;
	}
	public void setZnjDay(String znjDay) {
		this.znjDay = znjDay;
	}
	public String getZnjRatio() {
		return znjRatio;
	}
	public void setZnjRatio(String znjRatio) {
		this.znjRatio = znjRatio;
	}
	
	
	
	public String getItemType() {
		return ItemType;
	}
	public void setItemType(String itemType) {
		ItemType = itemType;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getEhNumber() {
		return EhNumber;
	}
	public void setEhNumber(String ehNumber) {
		EhNumber = ehNumber;
	}
	public String getEsName() {
		return EsName;
	}
	public void setEsName(String esName) {
		EsName = esName;
	}
	public String getBuName() {
		return BuName;
	}
	public void setBuName(String buName) {
		BuName = buName;
	}
	public String getOwnerName() {
		return OwnerName;
	}
	public void setOwnerName(String ownerName) {
		OwnerName = ownerName;
	}
	
	public int getEo_id() {
		return Eo_id;
	}
	public void setEo_id(int eo_id) {
		Eo_id = eo_id;
	}
	public int getTs_id() {
		return ts_id;
	}
	public void setTs_id(int ts_id) {
		this.ts_id = ts_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public int getEs_id() {
		return Es_id;
	}
	public void setEs_id(int es_id) {
		Es_id = es_id;
	}
	public int getBu_id() {
		return Bu_id;
	}
	public void setBu_id(int bu_id) {
		Bu_id = bu_id;
	}
	public int getEh_id() {
		return Eh_id;
	}
	public void setEh_id(int eh_id) {
		Eh_id = eh_id;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getPayItem() {
		return payItem;
	}
	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}
	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotal_yh() {
		return total_yh;
	}
	public void setTotal_yh(String total_yh) {
		this.total_yh = total_yh;
	}
	public String getTotal_znj() {
		return total_znj;
	}
	public void setTotal_znj(String total_znj) {
		this.total_znj = total_znj;
	}
	public String getTotal_sj() {
		return total_sj;
	}
	public void setTotal_sj(String total_sj) {
		this.total_sj = total_sj;
	}
	public String getTk_total() {
		return tk_total;
	}
	public void setTk_total(String tk_total) {
		this.tk_total = tk_total;
	}
	public String getTk_time() {
		return tk_time;
	}
	public void setTk_time(String tk_time) {
		this.tk_time = tk_time;
	}
	public String getTk_type() {
		return tk_type;
	}
	public void setTk_type(String tk_type) {
		this.tk_type = tk_type;
	}
	public String getTk_status() {
		return tk_status;
	}
	public void setTk_status(String tk_status) {
		this.tk_status = tk_status;
	}
	public String getTk_Reason() {
		return tk_Reason;
	}
	public void setTk_Reason(String tk_Reason) {
		this.tk_Reason = tk_Reason;
	}
	public String getCost_startTime() {
		return Cost_startTime;
	}
	public void setCost_startTime(String cost_startTime) {
		Cost_startTime = cost_startTime;
	}
	public String getCost_endTime() {
		return Cost_endTime;
	}
	public void setCost_endTime(String cost_endTime) {
		Cost_endTime = cost_endTime;
	}
	public String getScost_startTime() {
		return Scost_startTime;
	}
	public void setScost_startTime(String scost_startTime) {
		Scost_startTime = scost_startTime;
	}
	public String getScost_endTime() {
		return Scost_endTime;
	}
	public void setScost_endTime(String scost_endTime) {
		Scost_endTime = scost_endTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLockSign() {
		return LockSign;
	}
	public void setLockSign(String lockSign) {
		LockSign = lockSign;
	}
	public String getLockTime() {
		return LockTime;
	}
	public void setLockTime(String lockTime) {
		LockTime = lockTime;
	}
	public String getSjje() {
		return sjje;
	}
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
	public String getSjbishu() {
		return sjbishu;
	}
	public void setSjbishu(String sjbishu) {
		this.sjbishu = sjbishu;
	}
	public String getXsjje() {
		return xsjje;
	}
	public void setXsjje(String xsjje) {
		this.xsjje = xsjje;
	}
	public String getXsjbishu() {
		return xsjbishu;
	}
	public void setXsjbishu(String xsjbishu) {
		this.xsjbishu = xsjbishu;
	}
	public String getTkje() {
		return tkje;
	}
	public void setTkje(String tkje) {
		this.tkje = tkje;
	}
	public String getTkbishu() {
		return tkbishu;
	}
	public void setTkbishu(String tkbishu) {
		this.tkbishu = tkbishu;
	}
	public String getUnName() {
		return UnName;
	}
	public void setUnName(String unName) {
		UnName = unName;
	}
	public String getJbishu() {
		return jbishu;
	}
	public void setJbishu(String jbishu) {
		this.jbishu = jbishu;
	}
	public String getJhje() {
		return jhje;
	}
	public void setJhje(String jhje) {
		this.jhje = jhje;
	}
	public String getXwsbishu() {
		return xwsbishu;
	}
	public void setXwsbishu(String xwsbishu) {
		this.xwsbishu = xwsbishu;
	}
	public String getXwsje() {
		return xwsje;
	}
	public void setXwsje(String xwsje) {
		this.xwsje = xwsje;
	}
	public String getXzsbishu() {
		return xzsbishu;
	}
	public void setXzsbishu(String xzsbishu) {
		this.xzsbishu = xzsbishu;
	}
	public String getXzsje() {
		return xzsje;
	}
	public void setXzsje(String xzsje) {
		this.xzsje = xzsje;
	}
	public String getXbsbishu() {
		return xbsbishu;
	}
	public void setXbsbishu(String xbsbishu) {
		this.xbsbishu = xbsbishu;
	}
	public String getXbsje() {
		return xbsje;
	}
	public void setXbsje(String xbsje) {
		this.xbsje = xbsje;
	}
	
	
	
}
