package com.fc.ss.bean;

import java.util.Date;

/**
 * 售前发送至dms xml字段
 * @author qp
 *
 */

public class FcBeforeOBBean implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8369350275749968541L;
	
	private Long id;
	private String receive_Date;
	private String create_Date;
	private String gender;
	private String name;
	private String job_Title;
	private String ageRange;
	private Date dateOfBirth;
	private String email;
	private String homePhone;
	private String mobile;
	private String mobile_2;
	private String telephone;
	private String address;
	private String city;
	private String province;
	private String zipCode;
	private String primary_CommunicationPreference;
	private String secondary_CommunicationPreference;
	private String occupation;
	private String current_VehicleOwned;
	private String current_DrivingLicense;
	private String current_VehicleModel;
	private String current_VehicleModelList;
	private String curVinYear;
	private String maritalStatus;
	private String noChild;
	private String monthlyHouseholdIncome;
	private String educationLevel;	
	private String campaignName_CHI;
	private String campaignName_ENG;
	private String interested_Car_Model;
	private String interested_Car_Model_Detail;
	private String budget_Range;
	private String estimate_BuyCarTime;
	private String mediaSource;
	private Long Campaign_Channel_ID;
	private String local_To_Dealer_ID;
	private String process_Mode;
	private String memo1;
	private String memo2;
	private String memo3;
	private String memo4;
	private String memo5;
	private String validity_Mark;
	private String import_Mode;
	private String assign_Dealer_Name;
	private String assign_Dealer_Code;
	private String import_Date;
	private String symbol;
//	private Long processedFlag;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile_2() {
		return mobile_2;
	}
	public void setMobile_2(String mobile_2) {
		this.mobile_2 = mobile_2;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNoChild() {
		return noChild;
	}
	public void setNoChild(String noChild) {
		this.noChild = noChild;
	}
	public String getMonthlyHouseholdIncome() {
		return monthlyHouseholdIncome;
	}
	public void setMonthlyHouseholdIncome(String monthlyHouseholdIncome) {
		this.monthlyHouseholdIncome = monthlyHouseholdIncome;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getMediaSource() {
		return mediaSource;
	}
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
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
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getReceive_Date() {
		return receive_Date;
	}
	public void setReceive_Date(String receive_Date) {
		this.receive_Date = receive_Date;
	}
	public String getCreate_Date() {
		return create_Date;
	}
	public void setCreate_Date(String create_Date) {
		this.create_Date = create_Date;
	}
	public String getJob_Title() {
		return job_Title;
	}
	public void setJob_Title(String job_Title) {
		this.job_Title = job_Title;
	}
	public String getPrimary_CommunicationPreference() {
		return primary_CommunicationPreference;
	}
	public void setPrimary_CommunicationPreference(
			String primary_CommunicationPreference) {
		this.primary_CommunicationPreference = primary_CommunicationPreference;
	}
	public String getSecondary_CommunicationPreference() {
		return secondary_CommunicationPreference;
	}
	public void setSecondary_CommunicationPreference(
			String secondary_CommunicationPreference) {
		this.secondary_CommunicationPreference = secondary_CommunicationPreference;
	}
	public String getCurrent_VehicleOwned() {
		return current_VehicleOwned;
	}
	public void setCurrent_VehicleOwned(String current_VehicleOwned) {
		this.current_VehicleOwned = current_VehicleOwned;
	}
	public String getCurrent_DrivingLicense() {
		return current_DrivingLicense;
	}
	public void setCurrent_DrivingLicense(String current_DrivingLicense) {
		this.current_DrivingLicense = current_DrivingLicense;
	}
	public String getCurrent_VehicleModel() {
		return current_VehicleModel;
	}
	public void setCurrent_VehicleModel(String current_VehicleModel) {
		this.current_VehicleModel = current_VehicleModel;
	}
	public String getCurrent_VehicleModelList() {
		return current_VehicleModelList;
	}
	public void setCurrent_VehicleModelList(String current_VehicleModelList) {
		this.current_VehicleModelList = current_VehicleModelList;
	}
	public String getCurVinYear() {
		return curVinYear;
	}
	public void setCurVinYear(String curVinYear) {
		this.curVinYear = curVinYear;
	}
	public String getCampaignName_CHI() {
		return campaignName_CHI;
	}
	public void setCampaignName_CHI(String campaignName_CHI) {
		this.campaignName_CHI = campaignName_CHI;
	}
	public String getCampaignName_ENG() {
		return campaignName_ENG;
	}
	public void setCampaignName_ENG(String campaignName_ENG) {
		this.campaignName_ENG = campaignName_ENG;
	}
	public String getInterested_Car_Model() {
		return interested_Car_Model;
	}
	public void setInterested_Car_Model(String interested_Car_Model) {
		this.interested_Car_Model = interested_Car_Model;
	}
	public String getInterested_Car_Model_Detail() {
		return interested_Car_Model_Detail;
	}
	public void setInterested_Car_Model_Detail(
			String interested_Car_Model_Detail) {
		this.interested_Car_Model_Detail = interested_Car_Model_Detail;
	}
	public String getBudget_Range() {
		return budget_Range;
	}
	public void setBudget_Range(String budget_Range) {
		this.budget_Range = budget_Range;
	}
	public String getEstimate_BuyCarTime() {
		return estimate_BuyCarTime;
	}
	public void setEstimate_BuyCarTime(String estimate_BuyCarTime) {
		this.estimate_BuyCarTime = estimate_BuyCarTime;
	}
	public String getLocal_To_Dealer_ID() {
		return local_To_Dealer_ID;
	}
	public void setLocal_To_Dealer_ID(String local_To_Dealer_ID) {
		this.local_To_Dealer_ID = local_To_Dealer_ID;
	}
	public String getProcess_Mode() {
		return process_Mode;
	}
	public void setProcess_Mode(String process_Mode) {
		this.process_Mode = process_Mode;
	}
	public String getImport_Mode() {
		return import_Mode;
	}
	public void setImport_Mode(String import_Mode) {
		this.import_Mode = import_Mode;
	}
	public String getValidity_Mark() {
		return validity_Mark;
	}
	public void setValidity_Mark(String validity_Mark) {
		this.validity_Mark = validity_Mark;
	}
	public String getAssign_Dealer_Name() {
		return assign_Dealer_Name;
	}
	public void setAssign_Dealer_Name(String assign_Dealer_Name) {
		this.assign_Dealer_Name = assign_Dealer_Name;
	}
	public String getAssign_Dealer_Code() {
		return assign_Dealer_Code;
	}
	public void setAssign_Dealer_Code(String assign_Dealer_Code) {
		this.assign_Dealer_Code = assign_Dealer_Code;
	}
	public String getImport_Date() {
		return import_Date;
	}
	public void setImport_Date(String import_Date) {
		this.import_Date = import_Date;
	}
	public Long getCampaign_Channel_ID() {
		return Campaign_Channel_ID;
	}
	public void setCampaign_Channel_ID(Long campaign_Channel_ID) {
		Campaign_Channel_ID = campaign_Channel_ID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
