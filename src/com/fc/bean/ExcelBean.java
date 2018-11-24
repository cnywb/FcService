package com.fc.bean;

/**
 * 导出车主信息Excel对应Bean
 * @author lq
 *
 */
public class ExcelBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3710555281400114686L;
	private String VIN;//VIN码
	private String ownerName;//车主姓名
	private String contactorName;//联系人姓名
	private String ownerMobile;//车主手机
	private String ownerPhone;//车主电话
	private String contactorMobile;//联系人手机
	private String contactorPhone;//联系人电话
	private String ownerAddress;//车主地址
	private String contactorAddress;//联系人地址
	private String carTvbDate;//车辆销售时间
	private String dealerCode;//经销商代码
	private String dataMark;//数据标识
	private String isNewOwner;//是否为新车主
	private String obType;//OB类型
	private String CAR_SYNC;//CAR_SYNC
	private String CAR_EA;//CAR_EA
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getContactorName() {
		return contactorName;
	}
	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getContactorMobile() {
		return contactorMobile;
	}
	public void setContactorMobile(String contactorMobile) {
		this.contactorMobile = contactorMobile;
	}
	public String getContactorPhone() {
		return contactorPhone;
	}
	public void setContactorPhone(String contactorPhone) {
		this.contactorPhone = contactorPhone;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getContactorAddress() {
		return contactorAddress;
	}
	public void setContactorAddress(String contactorAddress) {
		this.contactorAddress = contactorAddress;
	}
	public String getCarTvbDate() {
		return carTvbDate;
	}
	public void setCarTvbDate(String carTvbDate) {
		this.carTvbDate = carTvbDate;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public String getDataMark() {
		return dataMark;
	}
	public void setDataMark(String dataMark) {
		this.dataMark = dataMark;
	}
	public String getIsNewOwner() {
		return isNewOwner;
	}
	public void setIsNewOwner(String isNewOwner) {
		this.isNewOwner = isNewOwner;
	}
	public String getObType() {
		return obType;
	}
	public void setObType(String obType) {
		this.obType = obType;
	}
	public String getCAR_SYNC() {
		return CAR_SYNC;
	}
	public void setCAR_SYNC(String cARSYNC) {
		CAR_SYNC = cARSYNC;
	}
	public String getCAR_EA() {
		return CAR_EA;
	}
	public void setCAR_EA(String cAREA) {
		CAR_EA = cAREA;
	}
}
