package WYBack_Stage.Bean;

public class TB_Estate_Mation {

	private int Em_id;//编号，主键
	private int ts_id;//商户id
	private int Eh_id;//房屋主键
	private String Ma_id;//表编号
	private String ReadDate;//抄表日期
	private String LastReadNum;//上次抄表数
	private String NowReadNum;//本次抄表数
	private String UserNumber;//用量数
	private int status;//状态
	private int orderStatus;//订单状态（0未生成订单   1已生成订单）
	private int type;//类型（1水 2电 3燃）
	private int unit;//单位（㎡/度/吨）
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
	
}
