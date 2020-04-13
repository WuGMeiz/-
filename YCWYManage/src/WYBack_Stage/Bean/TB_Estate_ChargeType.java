package WYBack_Stage.Bean;
/**
 * 缴费项子表
 * @author Admic
 */
public class TB_Estate_ChargeType {
	private int Ct_id; //主键
	private int Ei_id; //缴费项id
	private int Ht_id; //房屋类型id
	private String Ct_price; //房屋类型对应缴费项单价
	private int status;  //状态（0正常  1删除）
	private String Ct_remark; //备注
	private String Ct_znjDay; //滞纳天数
	private String Ct_znjRatio; //滞纳比例
	private String Ct_Limited;  //电费限购量/元
	private String Ct_LimitNumber; //电费限购量次数/月
	private String Ct_BuyLimited; // 购电限制（1:是 0:否）   
	private String Ct_isYjLimited; // 是否预缴（1:是 0:否）   
	private String Ct_yhCon; //优惠开始条件
	private String Ct_yhJzCon; //优惠截止条件
	private String Ct_yhRatio; //优惠比例
	private String Ct_guRatio; //故障比例
	private String HouseStandard;//平米标准
	private String BelowStandard;//标准以下
	private String AboveStandard;//标准以上  
    private String Ct_xiaoquName;  //小区名称
	private String Ct_HtName; //房屋类型名
	private String Ct_itemName; //收费项名称
    private int count;
    private String Ct_ItemType; //收费方式
    private int Es_id; //小区id
    private String Se_id; //楼层区间id
    private String qj;//区间值
	public int getCt_id() {
		return Ct_id;
	}
	public void setCt_id(int ct_id) {
		Ct_id = ct_id;
	}
	public int getEi_id() {
		return Ei_id;
	}
	public void setEi_id(int ei_id) {
		Ei_id = ei_id;
	}
	public int getHt_id() {
		return Ht_id;
	}
	public void setHt_id(int ht_id) {
		Ht_id = ht_id;
	}
	public String getCt_price() {
		return Ct_price;
	}
	public void setCt_price(String ct_price) {
		Ct_price = ct_price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCt_remark() {
		return Ct_remark;
	}
	public void setCt_remark(String ct_remark) {
		Ct_remark = ct_remark;
	}
	public String getCt_znjDay() {
		return Ct_znjDay;
	}
	public void setCt_znjDay(String ct_znjDay) {
		Ct_znjDay = ct_znjDay;
	}
	public String getCt_znjRatio() {
		return Ct_znjRatio;
	}
	public void setCt_znjRatio(String ct_znjRatio) {
		Ct_znjRatio = ct_znjRatio;
	}
	public String getCt_Limited() {
		return Ct_Limited;
	}
	public void setCt_Limited(String ct_Limited) {
		Ct_Limited = ct_Limited;
	}
	public String getCt_LimitNumber() {
		return Ct_LimitNumber;
	}
	public void setCt_LimitNumber(String ct_LimitNumber) {
		Ct_LimitNumber = ct_LimitNumber;
	}
	public String getCt_BuyLimited() {
		return Ct_BuyLimited;
	}
	public void setCt_BuyLimited(String ct_BuyLimited) {
		Ct_BuyLimited = ct_BuyLimited;
	}
	public String getCt_isYjLimited() {
		return Ct_isYjLimited;
	}
	public void setCt_isYjLimited(String ct_isYjLimited) {
		Ct_isYjLimited = ct_isYjLimited;
	}
	public String getCt_yhCon() {
		return Ct_yhCon;
	}
	public void setCt_yhCon(String ct_yhCon) {
		Ct_yhCon = ct_yhCon;
	}
	public String getCt_yhJzCon() {
		return Ct_yhJzCon;
	}
	public void setCt_yhJzCon(String ct_yhJzCon) {
		Ct_yhJzCon = ct_yhJzCon;
	}
	public String getCt_yhRatio() {
		return Ct_yhRatio;
	}
	public void setCt_yhRatio(String ct_yhRatio) {
		Ct_yhRatio = ct_yhRatio;
	}
	public String getCt_guRatio() {
		return Ct_guRatio;
	}
	public void setCt_guRatio(String ct_guRatio) {
		Ct_guRatio = ct_guRatio;
	}
	public String getCt_xiaoquName() {
		return Ct_xiaoquName;
	}
	public void setCt_xiaoquName(String ct_xiaoquName) {
		Ct_xiaoquName = ct_xiaoquName;
	}
	public String getCt_HtName() {
		return Ct_HtName;
	}
	public void setCt_HtName(String ct_HtName) {
		Ct_HtName = ct_HtName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCt_itemName() {
		return Ct_itemName;
	}
	public void setCt_itemName(String ct_itemName) {
		Ct_itemName = ct_itemName;
	}
	public String getCt_ItemType() {
		return Ct_ItemType;
	}
	public void setCt_ItemType(String ct_ItemType) {
		Ct_ItemType = ct_ItemType;
	}
	public int getEs_id() {
		return Es_id;
	}
	public void setEs_id(int es_id) {
		Es_id = es_id;
	}
	public String getHouseStandard() {
		return HouseStandard;
	}
	public void setHouseStandard(String houseStandard) {
		HouseStandard = houseStandard;
	}
	public String getBelowStandard() {
		return BelowStandard;
	}
	public void setBelowStandard(String belowStandard) {
		BelowStandard = belowStandard;
	}
	public String getAboveStandard() {
		return AboveStandard;
	}
	public void setAboveStandard(String aboveStandard) {
		AboveStandard = aboveStandard;
	}
	public String getSe_id() {
		return Se_id;
	}
	public void setSe_id(String se_id) {
		Se_id = se_id;
	}
	public String getQj() {
		return qj;
	}
	public void setQj(String qj) {
		this.qj = qj;
	}
    
}
