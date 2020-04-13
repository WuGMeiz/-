package WYBack_Stage.Bean;

public class TB_Estate_Exrecord {
	private int re_id;		//记录ID
	private int ex_id;		//问卷ID
	private int eh_id;		//用户ID
	private int to_id;		//题目ID
	private	String con_selects;	//选择内容
	private String selectTime;	//参与时间
	private int status;		//状态
	private String create_time;	//创建时间
	private String ownerName;
	private String EhNumber;
	
	
	public String getEhNumber() {
		return EhNumber;
	}
	public void setEhNumber(String ehNumber) {
		EhNumber = ehNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	
	public int getRe_id() {
		return re_id;
	}
	public void setRe_id(int re_id) {
		this.re_id = re_id;
	}
	public int getEx_id() {
		return ex_id;
	}
	public void setEx_id(int ex_id) {
		this.ex_id = ex_id;
	}
	public int getEh_id() {
		return eh_id;
	}
	public void setEh_id(int eh_id) {
		this.eh_id = eh_id;
	}
	public int getTo_id() {
		return to_id;
	}
	public void setTo_id(int to_id) {
		this.to_id = to_id;
	}
	public String getCon_selects() {
		return con_selects;
	}
	public void setCon_selects(String con_selects) {
		this.con_selects = con_selects;
	}
	public String getSelectTime() {
		return selectTime;
	}
	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}
