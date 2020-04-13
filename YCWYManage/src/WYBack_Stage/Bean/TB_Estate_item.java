/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Bean;

/**
 * <p> 物业收费项表TB_Estate_item <p>
 * 
 * @date 2018-3-5 <br>
 * @author flx <br>
 * @version 9.0.0 <br>
 * 
 */
public class TB_Estate_item {
   
	private int Ei_id; // 编号，主键

    private int ts_id; // 商户id

    private int Es_id; // 小区主键

    private int Bs_id; // 楼宇主键

    private String itemName; // 收费名称

    private String itemDesc; // 收费描述

    private String ItemType; // 收费方式（固定死）

    private String price; // 单价

    private String tscb_id; // 柜台

    private int status; // 状态
    
    private int a_id; 
    private String remark;
    private int count;
    private String znjDay;
    private String znjRatio;
    private String xiaoquName;
    private String Limited;
    private String LimitNumber;
    private String BuyLimited;//购电限制 0否 1是
    private String isYjLimited;
    private String yhCon; //优惠开始条件
    private String yhJzCon; //优惠截止条件
    private String yhRatio; //优惠比例
    private String guRatio; //故障比例
    
    public String getIsYjLimited() {
		return isYjLimited;
	}

	public void setIsYjLimited(String isYjLimited) {
		this.isYjLimited = isYjLimited;
	}

	public String getBuyLimited() {
		return BuyLimited;
	}

	public void setBuyLimited(String buyLimited) {
		BuyLimited = buyLimited;
	}

	public String getLimited() {
		return Limited;
	}

	public void setLimited(String limited) {
		Limited = limited;
	}

	public String getLimitNumber() {
		return LimitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		LimitNumber = limitNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXiaoquName() {
		return xiaoquName;
	}

	public void setXiaoquName(String xiaoquName) {
		this.xiaoquName = xiaoquName;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public int getEi_id() {
        return Ei_id;
    }

    public void setEi_id(int ei_id) {
        Ei_id = ei_id;
    }

    public int getTs_id() {
        return ts_id;
    }

    public void setTs_id(int ts_id) {
        this.ts_id = ts_id;
    }

    public int getEs_id() {
        return Es_id;
    }

    public void setEs_id(int es_id) {
        Es_id = es_id;
    }

    public int getBs_id() {
        return Bs_id;
    }

    public void setBs_id(int bs_id) {
        Bs_id = bs_id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTscb_id() {
        return tscb_id;
    }

    public void setTscb_id(String tscb_id) {
        this.tscb_id = tscb_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


	public String getYhRatio() {
		return yhRatio;
	}

	public void setYhRatio(String yhRatio) {
		this.yhRatio = yhRatio;
	}

	public String getGuRatio() {
		return guRatio;
	}

	public void setGuRatio(String guRatio) {
		this.guRatio = guRatio;
	}

	public String getYhJzCon() {
		return yhJzCon;
	}

	public void setYhJzCon(String yhJzCon) {
		this.yhJzCon = yhJzCon;
	}

	public String getYhCon() {
		return yhCon;
	}

	public void setYhCon(String yhCon) {
		this.yhCon = yhCon;
	}
   
    
}
