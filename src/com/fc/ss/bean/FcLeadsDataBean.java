package com.fc.ss.bean;

public class FcLeadsDataBean implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 848965940689145863L;

	private Long id;// ID编号
	
	private String sellCreateDate;
	private String sellDealerTime;
	private String sellNo;
	private String sellStatus;
	private String phoneStatus;
	private String intentLevel;
	private String intentModels;
	private String isDiveOff;
	
	private String noDiveReason;
	private String distSellTime;
	
	private String sellConsultant;
	private String sellFollowTime;
	
	private String impDate;
	private String impNumber;
	private String willingToShop;
	
	private String willingToDriving;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellCreateDate() {
		return sellCreateDate;
	}

	public void setSellCreateDate(String sellCreateDate) {
		this.sellCreateDate = sellCreateDate;
	}

	public String getSellDealerTime() {
		return sellDealerTime;
	}

	public void setSellDealerTime(String sellDealerTime) {
		this.sellDealerTime = sellDealerTime;
	}

	public String getSellNo() {
		return sellNo;
	}

	public void setSellNo(String sellNo) {
		this.sellNo = sellNo;
	}

	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getIntentLevel() {
		return intentLevel;
	}

	public void setIntentLevel(String intentLevel) {
		this.intentLevel = intentLevel;
	}

	public String getIntentModels() {
		return intentModels;
	}

	public void setIntentModels(String intentModels) {
		this.intentModels = intentModels;
	}

	public String getIsDiveOff() {
		return isDiveOff;
	}

	public void setIsDiveOff(String isDiveOff) {
		this.isDiveOff = isDiveOff;
	}

	public String getNoDiveReason() {
		return noDiveReason;
	}

	public void setNoDiveReason(String noDiveReason) {
		this.noDiveReason = noDiveReason;
	}

	public String getDistSellTime() {
		return distSellTime;
	}

	public void setDistSellTime(String distSellTime) {
		this.distSellTime = distSellTime;
	}

	public String getSellFollowTime() {
		return sellFollowTime;
	}

	public void setSellFollowTime(String sellFollowTime) {
		this.sellFollowTime = sellFollowTime;
	}

	public String getSellConsultant() {
		return sellConsultant;
	}

	public void setSellConsultant(String sellConsultant) {
		this.sellConsultant = sellConsultant;
	}

	public String getImpDate() {
		return impDate;
	}

	public void setImpDate(String impDate) {
		this.impDate = impDate;
	}

	public String getImpNumber() {
		return impNumber;
	}

	public void setImpNumber(String impNumber) {
		this.impNumber = impNumber;
	}

	public String getWillingToShop() {
		return willingToShop;
	}

	public void setWillingToShop(String willingToShop) {
		this.willingToShop = willingToShop;
	}

	public String getWillingToDriving() {
		return willingToDriving;
	}

	public void setWillingToDriving(String willingToDriving) {
		this.willingToDriving = willingToDriving;
	}

}
