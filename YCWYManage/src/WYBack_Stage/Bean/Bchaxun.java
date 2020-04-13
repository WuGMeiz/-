package WYBack_Stage.Bean;

public class Bchaxun {
	
	private String TBWID;
	private String MERCHANTID;//商户代码
	private String BRANCHID;//商户所在分行
	private String POSID;//商户的POS号
	private String ORDERID;//订单号
	private String ORDERDATE;//支付/退款交易时间
	private String ACCDATE;//记账日期
	private String AMOUNT;//支付金额
	private String STATUSCODE;//支付/退款状态码
	private String STATUS;//支付/退款状态
	private String REFUND;//退款金额
	private String TYPE;//流水类型【0支付流水，1退款流水】
	private String sjje;
	private String sjbishu;
	private String tkje;
	private String tkbishu;
	
	public String getTkbishu() {
		return tkbishu;
	}
	public void setTkbishu(String tkbishu) {
		this.tkbishu = tkbishu;
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
	public String getTkje() {
		return tkje;
	}
	public void setTkje(String tkje) {
		this.tkje = tkje;
	}
	public String getTBWID() {
		return TBWID;
	}
	public void setTBWID(String tBWID) {
		TBWID = tBWID;
	}
	public String getMERCHANTID() {
		return MERCHANTID;
	}
	public void setMERCHANTID(String mERCHANTID) {
		MERCHANTID = mERCHANTID;
	}
	public String getBRANCHID() {
		return BRANCHID;
	}
	public void setBRANCHID(String bRANCHID) {
		BRANCHID = bRANCHID;
	}
	public String getPOSID() {
		return POSID;
	}
	public void setPOSID(String pOSID) {
		POSID = pOSID;
	}
	public String getORDERID() {
		return ORDERID;
	}
	public void setORDERID(String oRDERID) {
		ORDERID = oRDERID;
	}
	public String getORDERDATE() {
		return ORDERDATE;
	}
	public void setORDERDATE(String oRDERDATE) {
		ORDERDATE = oRDERDATE;
	}
	public String getACCDATE() {
		return ACCDATE;
	}
	public void setACCDATE(String aCCDATE) {
		ACCDATE = aCCDATE;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getSTATUSCODE() {
		return STATUSCODE;
	}
	public void setSTATUSCODE(String sTATUSCODE) {
		STATUSCODE = sTATUSCODE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getREFUND() {
		return REFUND;
	}
	public void setREFUND(String rEFUND) {
		REFUND = rEFUND;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	
	
}
