package WYBack_Stage.Bean;
/**
 * 房屋信息抄表信息联合查询临时使用
 * @author admin
 *
 */
public class TB_HMBean {
	private int Em_id;//编号，主键
	private int Es_id; // 小区id
	private int Bu_id; // 楼宇ID
	private int Un_id;
	private int Eh_id;//房屋主键
	private String Ma_id;//表编号
	private int ts_id;//商户id
	private String EhNumber; // 房屋编号
	private String EhName; // 房屋名称
	private String ReadDate;//抄表日期
	private String LastReadNum;//上次抄表数
	private String NowReadNum;//本次抄表数
	private String UserNumber;//用量数
	private int status;//状态
	private int orderStatus;//订单状态（0未生成订单   1已生成订单）
	private int type;//类型（1水 2电 3燃）
	private int unit;//单位（㎡/度/吨）
	private String EsName; 
	private String BuName;
	private String UnName;
	private int EhType; //房屋类型
	private String HtName; //类型名称
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
	public String getEhNumber() {
		return EhNumber;
	}
	public void setEhNumber(String ehNumber) {
		EhNumber = ehNumber;
	}
	public String getEhName() {
		return EhName;
	}
	public void setEhName(String ehName) {
		EhName = ehName;
	}
	public int getEm_id() {
		return Em_id;
	}
	public void setEm_id(int em_id) {
		Em_id = em_id;
	}
	public int getTs_id() {
		return ts_id;
	}
	public void setTs_id(int ts_id) {
		this.ts_id = ts_id;
	}
	public int getEh_id() {
		return Eh_id;
	}
	public void setEh_id(int eh_id) {
		Eh_id = eh_id;
	}
	public String getReadDate() {
		return ReadDate;
	}
	public void setReadDate(String readDate) {
		ReadDate = readDate;
	}
	public String getLastReadNum() {
		return LastReadNum;
	}
	public void setLastReadNum(String lastReadNum) {
		LastReadNum = lastReadNum;
	}
	public String getNowReadNum() {
		return NowReadNum;
	}
	public void setNowReadNum(String nowReadNum) {
		NowReadNum = nowReadNum;
	}
	public String getUserNumber() {
		return UserNumber;
	}
	public void setUserNumber(String userNumber) {
		UserNumber = userNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public String getMa_id() {
		return Ma_id;
	}
	public void setMa_id(String ma_id) {
		Ma_id = ma_id;
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
	public String getUnName() {
		return UnName;
	}
	public void setUnName(String unName) {
		UnName = unName;
	}
	public int getUn_id() {
		return Un_id;
	}
	public void setUn_id(int un_id) {
		Un_id = un_id;
	}
	public int getEhType() {
		return EhType;
	}
	public void setEhType(int ehType) {
		EhType = ehType;
	}
	public String getHtName() {
		return HtName;
	}
	public void setHtName(String htName) {
		HtName = htName;
	}
	
}
