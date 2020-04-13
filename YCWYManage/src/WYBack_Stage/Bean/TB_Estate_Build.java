/*
 * Copyright (c) InforSuite CVICSE Middleware Co., LTD. All rights reserved.
 */
package WYBack_Stage.Bean;

/**
 * <p> 物业楼宇表TB_Estate_Build <p>
 * 
 * @date 2018-3-5 <br>
 * @author flx <br>
 * @version 9.0.0 <br>
 * 
 */
public class TB_Estate_Build {
   
   private int Bu_id; // 编号，主键

    private int ts_id; // 商户id

    private int Es_id; // 小区主键

    private String BuName; // 楼宇名称

    private String BuTurn; // 楼宇朝向

    private String BuType; // 楼宇类别

    private String BuStru; // 房屋结构

    private int BuNumber; // 楼宇总层数（层）

    private int UnitNumber; // 单元数（个）

    private int HouseNumber; // 房屋数量（户）

    private String TotalArea; // 楼宇总面积

    private String BuildArea; // 建筑面积（㎡）

    private String UseArea; // 使用面积（㎡）

    private String FinishDate; // 竣工日期

    private String BuHead; // 负责人

    private String creat_time; // 创建时间

    private String remark; // 备注

    private int status; // 状态
    
    private String EsName; // 小区名称

    public int getBu_id() {
        return Bu_id;
    }

    public void setBu_id(int bu_id) {
        Bu_id = bu_id;
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

    public String getBuName() {
        return BuName;
    }

    public void setBuName(String buName) {
        BuName = buName;
    }

    public String getBuTurn() {
        return BuTurn;
    }

    public void setBuTurn(String buTurn) {
        BuTurn = buTurn;
    }

    public String getBuType() {
        return BuType;
    }

    public void setBuType(String buType) {
        BuType = buType;
    }

    public String getBuStru() {
        return BuStru;
    }

    public void setBuStru(String buStru) {
        BuStru = buStru;
    }

    public int getBuNumber() {
        return BuNumber;
    }

    public void setBuNumber(int buNumber) {
        BuNumber = buNumber;
    }

    public int getUnitNumber() {
        return UnitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        UnitNumber = unitNumber;
    }

    public int getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getTotalArea() {
        return TotalArea;
    }

    public void setTotalArea(String totalArea) {
        TotalArea = totalArea;
    }

    public String getBuildArea() {
        return BuildArea;
    }

    public void setBuildArea(String buildArea) {
        BuildArea = buildArea;
    }

    public String getUseArea() {
        return UseArea;
    }

    public void setUseArea(String useArea) {
        UseArea = useArea;
    }

    public String getFinishDate() {
        return FinishDate;
    }

    public void setFinishDate(String finishDate) {
        FinishDate = finishDate;
    }

    public String getBuHead() {
        return BuHead;
    }

    public void setBuHead(String buHead) {
        BuHead = buHead;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getEsName() {
        return EsName;
    }

    public void setEsName(String esName) {
        EsName = esName;
    }

   
}
