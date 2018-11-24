package com.fc.bean;

import java.util.List;

/**
 * 拨打后的数据由CallCenter返回数据格式
 * @author lq
 *
 */
public class DmsObToData extends DmsObDataBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -599089799423403021L;
	private String returnDate;
	private String buyCarDate;
	private String carType;
	private String cars;
	private String catcode;
	private String dealerCode;
	private String dealerName;
	private String ownerName;
	private String infoClear1;
	private String uOwnerName;
	private String contactName;
	private String infoClear2;
	private String uContactName;
	private String buyCarNature;
	private String carUseFolk;
	private String referColor;
	private String whetherColor;
	private String whetherCompany;
	private String companyName;
	private String infoClear3;
	private String uCompanyName;
	private String ownerSex;
	private String infoClear4;
	private String uOwnerSex;
	private String ownerLandline;
	private String ownerMobile;
	private String infoClear5;
	private String uOwnerLandline;
	private String uOwnerMobile;
	private String ownerProvince;
	private String infoClear6;
	private String uOwnerProvince;
	private String ownerCity;
	private String infoClear7;
	private String uOwnerCity;
	private String ownerAddress;
	private String infoClear8;
	private String uOwnerAddress;
	private String ownerZipCode;
	private String infoClear9;
	private String uOwnerZipCode;
	private String ownerEmail;
	private String infoClear10;
	private String uOwnerEmail;
	private String contactSex;
	private String infoClear11;
	private String uContactSex;
	private String contactLandline;
	private String contactMobile;
	private String infoClear12;
	private String uContactLandline;
	private String uContactMobile;
	private String contactAddress;
	private String infoClear13;
	private String uContactAddress;
	private String contactEmail;
	private String infoClear14;
	private String uContactEmail;
	private String plateCode;
	private String vin;
	private String infoClear15;
	private String uVin;
	private String obDate;
	private String verificationRes;
	private String ownerObStatus;
	private String contactObStatus;
	private String obCount;
	private List<DmsObQuestionsBean> listQuestions;
	private String CAR_SYNC;
	private String CAR_EA;
	private String OB_TYPE;
	private String Owner_Birthday;
	private String Owner_Marital;
	private String subDate;
	private String ODP1;
	private String ODP2;
	private String certificate_type;
	private String certificate_code;
	private String identity_sit;//身份证号清理情况
	private String identity_code;//更新后的车主身份证号
	private String oid;
        private String calling1;//拨打第一次结果
        private String recording1;//拨打第一次语音链接
        private String calling2;//拨打第二次结果
        private String recording2;//拨打第二次语音链接
        private String calling3;//拨打第三次结果
        private String recording3;//拨打第三次语音链接
        private String calling4;//拨打第四次结果
        private String recording4;//拨打第四次语音链接
	private String dup_type;//手机号重复标识
	private String priority;//分类级别
	
	public String getPriority() {
	    return priority;
	}
	public void setPriority(String priority) {
	    this.priority = priority;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getIdentity_sit() {
		return identity_sit;
	}
	public void setIdentity_sit(String identitySit) {
		identity_sit = identitySit;
	}
	public String getIdentity_code() {
		return identity_code;
	}
	public void setIdentity_code(String identityCode) {
		identity_code = identityCode;
	}
	public String getCertificate_type() {
		return certificate_type;
	}
	public void setCertificate_type(String certificateType) {
		certificate_type = certificateType;
	}
	public String getCertificate_code() {
		return certificate_code;
	}
	public void setCertificate_code(String certificateCode) {
		certificate_code = certificateCode;
	}
	public String getODP1() {
		return ODP1;
	}
	public void setODP1(String oDP1) {
		ODP1 = oDP1;
	}
	public String getODP2() {
		return ODP2;
	}
	public void setODP2(String oDP2) {
		ODP2 = oDP2;
	}
	public List<DmsObQuestionsBean> getListQuestions() {
		return listQuestions;
	}
	public void setListQuestions(List<DmsObQuestionsBean> listQuestions) {
		this.listQuestions = listQuestions;
	}
	public String getSubDate() {
		return subDate;
	}
	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getBuyCarDate() {
		return buyCarDate;
	}
	public void setBuyCarDate(String buyCarDate) {
		this.buyCarDate = buyCarDate;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCars() {
		return cars;
	}
	public void setCars(String cars) {
		this.cars = cars;
	}
	public String getCatcode() {
		return catcode;
	}
	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getInfoClear1() {
		return infoClear1;
	}
	public void setInfoClear1(String infoClear1) {
		this.infoClear1 = infoClear1;
	}
	public String getuOwnerName() {
		return uOwnerName;
	}
	public void setuOwnerName(String uOwnerName) {
		this.uOwnerName = uOwnerName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getInfoClear2() {
		return infoClear2;
	}
	public void setInfoClear2(String infoClear2) {
		this.infoClear2 = infoClear2;
	}
	public String getuContactName() {
		return uContactName;
	}
	public void setuContactName(String uContactName) {
		this.uContactName = uContactName;
	}
	public String getBuyCarNature() {
		return buyCarNature;
	}
	public void setBuyCarNature(String buyCarNature) {
		this.buyCarNature = buyCarNature;
	}
	public String getCarUseFolk() {
		return carUseFolk;
	}
	public void setCarUseFolk(String carUseFolk) {
		this.carUseFolk = carUseFolk;
	}
	public String getReferColor() {
		return referColor;
	}
	public void setReferColor(String referColor) {
		this.referColor = referColor;
	}
	public String getWhetherColor() {
		return whetherColor;
	}
	public void setWhetherColor(String whetherColor) {
		this.whetherColor = whetherColor;
	}
	public String getWhetherCompany() {
		return whetherCompany;
	}
	public void setWhetherCompany(String whetherCompany) {
		this.whetherCompany = whetherCompany;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getInfoClear3() {
		return infoClear3;
	}
	public void setInfoClear3(String infoClear3) {
		this.infoClear3 = infoClear3;
	}
	public String getuCompanyName() {
		return uCompanyName;
	}
	public void setuCompanyName(String uCompanyName) {
		this.uCompanyName = uCompanyName;
	}
	public String getOwnerSex() {
		return ownerSex;
	}
	public void setOwnerSex(String ownerSex) {
		this.ownerSex = ownerSex;
	}
	public String getInfoClear4() {
		return infoClear4;
	}
	public void setInfoClear4(String infoClear4) {
		this.infoClear4 = infoClear4;
	}
	public String getuOwnerSex() {
		return uOwnerSex;
	}
	public void setuOwnerSex(String uOwnerSex) {
		this.uOwnerSex = uOwnerSex;
	}
	public String getOwnerLandline() {
		return ownerLandline;
	}
	public void setOwnerLandline(String ownerLandline) {
		this.ownerLandline = ownerLandline;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getInfoClear5() {
		return infoClear5;
	}
	public void setInfoClear5(String infoClear5) {
		this.infoClear5 = infoClear5;
	}
	public String getuOwnerLandline() {
		return uOwnerLandline;
	}
	public void setuOwnerLandline(String uOwnerLandline) {
		this.uOwnerLandline = uOwnerLandline;
	}
	public String getuOwnerMobile() {
		return uOwnerMobile;
	}
	public void setuOwnerMobile(String uOwnerMobile) {
		this.uOwnerMobile = uOwnerMobile;
	}
	public String getOwnerProvince() {
		return ownerProvince;
	}
	public void setOwnerProvince(String ownerProvince) {
		this.ownerProvince = ownerProvince;
	}
	public String getInfoClear6() {
		return infoClear6;
	}
	public void setInfoClear6(String infoClear6) {
		this.infoClear6 = infoClear6;
	}
	public String getuOwnerProvince() {
		return uOwnerProvince;
	}
	public void setuOwnerProvince(String uOwnerProvince) {
		this.uOwnerProvince = uOwnerProvince;
	}
	public String getOwnerCity() {
		return ownerCity;
	}
	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity;
	}
	public String getInfoClear7() {
		return infoClear7;
	}
	public void setInfoClear7(String infoClear7) {
		this.infoClear7 = infoClear7;
	}
	public String getuOwnerCity() {
		return uOwnerCity;
	}
	public void setuOwnerCity(String uOwnerCity) {
		this.uOwnerCity = uOwnerCity;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getInfoClear8() {
		return infoClear8;
	}
	public void setInfoClear8(String infoClear8) {
		this.infoClear8 = infoClear8;
	}
	public String getuOwnerAddress() {
		return uOwnerAddress;
	}
	public void setuOwnerAddress(String uOwnerAddress) {
		this.uOwnerAddress = uOwnerAddress;
	}
	public String getOwnerZipCode() {
		return ownerZipCode;
	}
	public void setOwnerZipCode(String ownerZipCode) {
		this.ownerZipCode = ownerZipCode;
	}
	public String getInfoClear9() {
		return infoClear9;
	}
	public void setInfoClear9(String infoClear9) {
		this.infoClear9 = infoClear9;
	}
	public String getuOwnerZipCode() {
		return uOwnerZipCode;
	}
	public void setuOwnerZipCode(String uOwnerZipCode) {
		this.uOwnerZipCode = uOwnerZipCode;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getInfoClear10() {
		return infoClear10;
	}
	public void setInfoClear10(String infoClear10) {
		this.infoClear10 = infoClear10;
	}
	public String getuOwnerEmail() {
		return uOwnerEmail;
	}
	public void setuOwnerEmail(String uOwnerEmail) {
		this.uOwnerEmail = uOwnerEmail;
	}
	public String getContactSex() {
		return contactSex;
	}
	public void setContactSex(String contactSex) {
		this.contactSex = contactSex;
	}
	public String getInfoClear11() {
		return infoClear11;
	}
	public void setInfoClear11(String infoClear11) {
		this.infoClear11 = infoClear11;
	}
	public String getuContactSex() {
		return uContactSex;
	}
	public void setuContactSex(String uContactSex) {
		this.uContactSex = uContactSex;
	}
	public String getContactLandline() {
		return contactLandline;
	}
	public void setContactLandline(String contactLandline) {
		this.contactLandline = contactLandline;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getInfoClear12() {
		return infoClear12;
	}
	public void setInfoClear12(String infoClear12) {
		this.infoClear12 = infoClear12;
	}
	public String getUcontactLandline() {
		return uContactLandline;
	}
	public void setUcontactLandline(String ucontactLandline) {
		this.uContactLandline = ucontactLandline;
	}
	public String getUcontactMobile() {
		return uContactMobile;
	}
	public void setUcontactMobile(String ucontactMobile) {
		this.uContactMobile = ucontactMobile;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getInfoClear13() {
		return infoClear13;
	}
	public void setInfoClear13(String infoClear13) {
		this.infoClear13 = infoClear13;
	}
	public String getUcontactAddress() {
		return uContactAddress;
	}
	public void setUcontactAddress(String ucontactAddress) {
		this.uContactAddress = ucontactAddress;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getInfoClear14() {
		return infoClear14;
	}
	public void setInfoClear14(String infoClear14) {
		this.infoClear14 = infoClear14;
	}
	public String getUcontact_email() {
		return uContactEmail;
	}
	public void setUcontact_email(String ucontactEmail) {
		uContactEmail = ucontactEmail;
	}
	public String getPlateCode() {
		return plateCode;
	}
	public void setPlateCode(String plateCode) {
		this.plateCode = plateCode;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getInfoClear15() {
		return infoClear15;
	}
	public void setInfoClear15(String infoClear15) {
		this.infoClear15 = infoClear15;
	}
	public String getuVin() {
		return uVin;
	}
	public void setuVin(String uVin) {
		this.uVin = uVin;
	}
	public String getObDate() {
		return obDate;
	}
	public void setObDate(String obDate) {
		this.obDate = obDate;
	}
	public String getVerificationres() {
		return verificationRes;
	}
	public void setVerificationres(String verificationres) {
		this.verificationRes = verificationres;
	}
	public String getOwnerObstatus() {
		return ownerObStatus;
	}
	public void setOwnerObstatus(String ownerObstatus) {
		this.ownerObStatus = ownerObstatus;
	}
	public String getContactObstatus() {
		return contactObStatus;
	}
	public void setContactObstatus(String contactObstatus) {
		this.contactObStatus = contactObstatus;
	}
	public String getObCount() {
		return obCount;
	}
	public void setObCount(String obCount) {
		this.obCount = obCount;
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
	public String getOB_TYPE() {
		return OB_TYPE;
	}
	public void setOB_TYPE(String oBTYPE) {
		OB_TYPE = oBTYPE;
	}
	public String getOwner_Birthday() {
		return Owner_Birthday;
	}
	public void setOwner_Birthday(String ownerBirthday) {
		Owner_Birthday = ownerBirthday;
	}
	public String getOwner_Marital() {
		return Owner_Marital;
	}
	public void setOwner_Marital(String ownerMarital) {
		Owner_Marital = ownerMarital;
	}
	public String getCalling1() {
	    return calling1;
	}
	public void setCalling1(String calling1) {
	    this.calling1 = calling1;
	}
	public String getRecording1() {
	    return recording1;
	}
	public void setRecording1(String recording1) {
	    this.recording1 = recording1;
	}
	public String getCalling2() {
	    return calling2;
	}
	public void setCalling2(String calling2) {
	    this.calling2 = calling2;
	}
	public String getRecording2() {
	    return recording2;
	}
	public void setRecording2(String recording2) {
	    this.recording2 = recording2;
	}
	public String getCalling3() {
	    return calling3;
	}
	public void setCalling3(String calling3) {
	    this.calling3 = calling3;
	}
	public String getRecording3() {
	    return recording3;
	}
	public void setRecording3(String recording3) {
	    this.recording3 = recording3;
	}
	public String getCalling4() {
	    return calling4;
	}
	public void setCalling4(String calling4) {
	    this.calling4 = calling4;
	}
	public String getRecording4() {
	    return recording4;
	}
	public void setRecording4(String recording4) {
	    this.recording4 = recording4;
	}
	public String getDup_type() {
	    return dup_type;
	}
	public void setDup_type(String dup_type) {
	    this.dup_type = dup_type;
	}

}
