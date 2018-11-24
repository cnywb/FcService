package com.fc.ss.bean;

import java.util.Date;


public class FcDistributionBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -833467977531909711L;
	private Long id;// ID编号
	
	private String ExecutionDate;
	private String name;
	private String gender;
	private String leadsLevel;
	private String mobile;
	private String secondaryMobilePhone;
	private String workPhone;
	private String homePhone;
	
	private String Province;
	private String city;
	
	private String vechileOfInterests;
	private String intendedPurchaseTime;
	
	private String typeOfDemand;
	private String campaignNameEng;
	private String campaignNameChi;
	
	private String AssignDealerCode;
	private String dealerZone;
	private String AssignDealerName;
	
	private String localToDealerId;
	private String mediaSourceType;
	private String customerSource;
	
	private String Mediasource;
	private String dealerFeedBack;
	private Date dealerFeedBackTime;
	private String salesProfileCreatedFlag;

	private Date systemTime;
	private String channel;
	private Date distributionTimeOfData;
	private String distributionStatus;
	private String memo1;
	private String memo2;
	private String memo3;
	private String memo4;
	private String memo5;
	
	private String ImportDate;
	private String WillingToShop;
	private String WillingToDriving;
	private String IsFirstShowroom;
	private String FirstShowroomTime;
	
	private String IsSecondShowroom;
	private String SecondShowroomTime;
	
	private String IsTestDrive;
	private String TestDriveTime;
	
	private String ObCallStatus;
	private String IsFollowUp;
	private String dealerCode;
	private String dealerName;
	
	private String CusRecordDate;
	private String CusCreateDate;
	
	private String createDate;
	private String ReceiveDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaignNameChi() {
		return campaignNameChi;
	}

	public void setCampaignNameChi(String campaignNameChi) {
		this.campaignNameChi = campaignNameChi;
	}

	public String getCampaignNameEng() {
		return campaignNameEng;
	}

	public void setCampaignNameEng(String campaignNameEng) {
		this.campaignNameEng = campaignNameEng;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocalToDealerId() {
		return localToDealerId;
	}

	public void setLocalToDealerId(String localToDealerId) {
		this.localToDealerId = localToDealerId;
	}

	public String getMemo1() {
		return memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public String getMemo3() {
		return memo3;
	}

	public void setMemo3(String memo3) {
		this.memo3 = memo3;
	}

	public String getMemo4() {
		return memo4;
	}

	public void setMemo4(String memo4) {
		this.memo4 = memo4;
	}
	
	public String getMemo5() {
		return memo5;
	}

	public void setMemo5(String memo5) {
		this.memo5 = memo5;
	}

	public Date getDistributionTimeOfData() {
		return distributionTimeOfData;
	}

	public void setDistributionTimeOfData(Date distributionTimeOfData) {
		this.distributionTimeOfData = distributionTimeOfData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeadsLevel() {
		return leadsLevel;
	}

	public void setLeadsLevel(String leadsLevel) {
		this.leadsLevel = leadsLevel;
	}

	public String getSecondaryMobilePhone() {
		return secondaryMobilePhone;
	}

	public void setSecondaryMobilePhone(String secondaryMobilePhone) {
		this.secondaryMobilePhone = secondaryMobilePhone;
	}

	public String getVechileOfInterests() {
		return vechileOfInterests;
	}

	public void setVechileOfInterests(String vechileOfInterests) {
		this.vechileOfInterests = vechileOfInterests;
	}

	public String getIntendedPurchaseTime() {
		return intendedPurchaseTime;
	}

	public void setIntendedPurchaseTime(String intendedPurchaseTime) {
		this.intendedPurchaseTime = intendedPurchaseTime;
	}

	public String getTypeOfDemand() {
		return typeOfDemand;
	}

	public void setTypeOfDemand(String typeOfDemand) {
		this.typeOfDemand = typeOfDemand;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	
	public String getDealerZone() {
		return dealerZone;
	}

	public void setDealerZone(String dealerZone) {
		this.dealerZone = dealerZone;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getMediaSourceType() {
		return mediaSourceType;
	}

	public void setMediaSourceType(String mediaSourceType) {
		this.mediaSourceType = mediaSourceType;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getDealerFeedBack() {
		return dealerFeedBack;
	}

	public void setDealerFeedBack(String dealerFeedBack) {
		this.dealerFeedBack = dealerFeedBack;
	}

	public Date getDealerFeedBackTime() {
		return dealerFeedBackTime;
	}

	public void setDealerFeedBackTime(Date dealerFeedBackTime) {
		this.dealerFeedBackTime = dealerFeedBackTime;
	}

	public String getSalesProfileCreatedFlag() {
		return salesProfileCreatedFlag;
	}

	public void setSalesProfileCreatedFlag(String salesProfileCreatedFlag) {
		this.salesProfileCreatedFlag = salesProfileCreatedFlag;
	}
	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getDistributionStatus() {
		return distributionStatus;
	}
	public void setDistributionStatus(String distributionStatus) {
		this.distributionStatus = distributionStatus;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}
	public String getExecutionDate() {
		return ExecutionDate;
	}

	public void setExecutionDate(String executionDate) {
		ExecutionDate = executionDate;
	}
	public String getMediasource() {
		return Mediasource;
	}

	public void setMediasource(String mediasource) {
		Mediasource = mediasource;
	}
	public String getImportDate() {
		return ImportDate;
	}

	public void setImportDate(String importDate) {
		ImportDate = importDate;
	}
	public String getWillingToShop() {
		return WillingToShop;
	}

	public void setWillingToShop(String willingToShop) {
		WillingToShop = willingToShop;
	}
	public String getWillingToDriving() {
		return WillingToDriving;
	}

	public void setWillingToDriving(String willingToDriving) {
		WillingToDriving = willingToDriving;
	}
	public String getIsFirstShowroom() {
		return IsFirstShowroom;
	}

	public void setIsFirstShowroom(String isFirstShowroom) {
		IsFirstShowroom = isFirstShowroom;
	}
	public String getFirstShowroomTime() {
		return FirstShowroomTime;
	}

	public void setFirstShowroomTime(String firstShowroomTime) {
		FirstShowroomTime = firstShowroomTime;
	}
	public String getIsSecondShowroom() {
		return IsSecondShowroom;
	}

	public void setIsSecondShowroom(String isSecondShowroom) {
		IsSecondShowroom = isSecondShowroom;
	}
	public String getSecondShowroomTime() {
		return SecondShowroomTime;
	}

	public void setSecondShowroomTime(String secondShowroomTime) {
		SecondShowroomTime = secondShowroomTime;
	}
	public String getIsTestDrive() {
		return IsTestDrive;
	}

	public void setIsTestDrive(String isTestDrive) {
		IsTestDrive = isTestDrive;
	}
	public String getTestDriveTime() {
		return TestDriveTime;
	}

	public void setTestDriveTime(String testDriveTime) {
		TestDriveTime = testDriveTime;
	}
	public String getObCallStatus() {
		return ObCallStatus;
	}

	public void setObCallStatus(String obCallStatus) {
		ObCallStatus = obCallStatus;
	}
	public String getIsFollowUp() {
		return IsFollowUp;
	}

	public void setIsFollowUp(String isFollowUp) {
		IsFollowUp = isFollowUp;
	}
	public String getAssignDealerCode() {
		return AssignDealerCode;
	}

	public void setAssignDealerCode(String assignDealerCode) {
		AssignDealerCode = assignDealerCode;
	}
	public String getAssignDealerName() {
		return AssignDealerName;
	}

	public void setAssignDealerName(String assignDealerName) {
		AssignDealerName = assignDealerName;
	}
	public String getCusRecordDate() {
		return CusRecordDate;
	}

	public void setCusRecordDate(String cusRecordDate) {
		CusRecordDate = cusRecordDate;
	}
	
	public String getReceiveDate() {
		return ReceiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		ReceiveDate = receiveDate;
	}
	
	public String getCusCreateDate() {
		return CusCreateDate;
	}

	public void setCusCreateDate(String cusCreateDate) {
		CusCreateDate = cusCreateDate;
	}
	
}
