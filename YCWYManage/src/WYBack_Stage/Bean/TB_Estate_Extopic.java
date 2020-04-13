package WYBack_Stage.Bean;

public class TB_Estate_Extopic {
	private int to_id;	//编号，主键
	private int ex_id;	//问卷表主键id
	private int if_tw;	//是否是图文（0 只图片 1 图文 2 文字）
	private String topic;	//题目要求
	private String images;	//上传图片（图片地址）
	private int type;	//类型（1 单选  2 多选）
	private int sort;	//排序
	private String create_time;	//创建时间
	private int status;	//状态 （0 删除  1正常）
	
	public int getTo_id() {
		return to_id;
	}
	public void setTo_id(int to_id) {
		this.to_id = to_id;
	}
	public int getEx_id() {
		return ex_id;
	}
	public void setEx_id(int ex_id) {
		this.ex_id = ex_id;
	}
	public int getIf_tw() {
		return if_tw;
	}
	public void setIf_tw(int if_tw) {
		this.if_tw = if_tw;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
