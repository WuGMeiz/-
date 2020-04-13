package WYBack_Stage.Bean;
/**
 * 房屋类型
 * @author Admic
 */
public class TB_Estate_Housetype {
	private int Ht_id;  //主键
	private String HtName; //名称
	private String create_time;
	private int status;
	private String remark1;
	private String remark2;
	
	
	private String EsName; // 小区名称
	
	
	public String getEsName() {
		return EsName;
	}
	public void setEsName(String esName) {
		EsName = esName;
	}
	public int getHt_id() {
		return Ht_id;
	}
	public void setHt_id(int ht_id) {
		Ht_id = ht_id;
	}
	public String getHtName() {
		return HtName;
	}
	public void setHtName(String htName) {
		HtName = htName;
	}
	public String getCreat_time() {
		return create_time;
	}
	public void setCreat_time(String create_time) {
		this.create_time = create_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}


}
