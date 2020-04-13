package WYBack_Stage.Bean;

public class TB_SEV_ORG {
	
	private String org_id;
	private String org_name;
	private String status;
	private String up_org_id;
	private String org_level;
	private String creat_time;
	private String creat_user;
	private String userset;
	private String remark;
	private String org_desc;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String orgId) {
		org_id = orgId;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String orgName) {
		org_name = orgName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUp_org_id() {
		return up_org_id;
	}
	public void setUp_org_id(String upOrgId) {
		up_org_id = upOrgId;
	}
	public String getOrg_level() {
		return org_level;
	}
	public void setOrg_level(String orgLevel) {
		org_level = orgLevel;
	}
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creatTime) {
		creat_time = creatTime;
	}
	public String getCreat_user() {
		return creat_user;
	}
	public void setCreat_user(String creatUser) {
		creat_user = creatUser;
	}
	public String getUserset() {
		return userset;
	}
	public void setUserset(String userset) {
		this.userset = userset;
	}
	
	public void setOrg_desc(String org_desc) {
		this.org_desc = org_desc;
	}
	public String getOrg_desc() {
		return org_desc;
	}
}