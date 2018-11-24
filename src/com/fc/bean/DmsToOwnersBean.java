package com.fc.bean;

/**
 * 车主信息表，数据唯一表
 * (FC_DMS_TO_OWNERS)
 * @author lq
 *
 */
public class DmsToOwnersBean implements java.io.Serializable  {
	
	private static final long serialVersionUID = -9175417186481739406L;
	private String owner_id="";
	private String brand="";
	private String serise="";
	private String model="";
	private String color="";
	private String vin="";
	private String engine_no="";
	private String radio_no="";
	private String key_no="";
	private String lisence_no="";
	private String sales_date="";
	private String dealer_sale_code="";
	private String short_name="";
	private String dealer_id="";
	private String dealer_name="";
	private String seller="";
	private String guarantee_start_date="";
	private String guarantee_end_date="";
	private String is_delay_service="";
	private String is_wholesale="";
	private String use_type="";
	private String pay_type="";
	private Double inmileage=0.0;
	private String last_maintain_date="";
	private String invoice_no="";
	private String owner_name="";
	private String contactor_name="";
	private String owner_spell="";
	private String owner_property="";
	private String gender="";
	private String birthday="";
	private String certificate_type="";
	private String certificate_code="";
	private String industry_first="";
	private String industry_second="";
	private String family_income="";
	private String edu_level="";
	private String owner_marriage="";
	private String choose_reasion="";
	private String buy_purpose="";
	private String wish_connect_type="";
	private String province="";
	private String city="";
	private String district="";
	private String zip_code="";
	private String address="";
	private String e_mail="";
	private String phone="";
	private String fax="";
	private String mobile="";
	private String contactor_mobile="";
	private String contactor_phone="";
	private String contactor_email="";
	private String remark="";
	private String create_date="";
	private String update_date="";
	private String importDate="";
	private String time_stamp="";
	private String sequnce="";
	private Long is_valid = 0L;
	private String buy_type = "";
	private String car_dsid = "";
	private String car_source = "";
	private String dealer_service_code = "";
	private String confgation="";
	private String cat_code="";
	private String contactor_address;
	private String contactor_gender="";
	private String tvb_create_date="";
	
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String ownerId) {
		owner_id = ownerId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSerise() {
		return serise;
	}
	public void setSerise(String serise) {
		this.serise = serise;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEngine_no() {
		return engine_no;
	}
	public void setEngine_no(String engineNo) {
		engine_no = engineNo;
	}
	public String getRadio_no() {
		return radio_no;
	}
	public void setRadio_no(String radioNo) {
		radio_no = radioNo;
	}
	public String getKey_no() {
		return key_no;
	}
	public void setKey_no(String keyNo) {
		key_no = keyNo;
	}
	public String getLisence_no() {
		return lisence_no;
	}
	public void setLisence_no(String lisenceNo) {
		lisence_no = lisenceNo;
	}
	public String getSales_date() {
		return sales_date;
	}
	public void setSales_date(String salesDate) {
		sales_date = salesDate;
	}
	public String getDealer_sale_code() {
		return dealer_sale_code;
	}
	public void setDealer_sale_code(String dealerSaleCode) {
		dealer_sale_code = dealerSaleCode;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String shortName) {
		short_name = shortName;
	}
	public String getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(String dealerId) {
		dealer_id = dealerId;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealerName) {
		dealer_name = dealerName;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getGuarantee_start_date() {
		return guarantee_start_date;
	}
	public void setGuarantee_start_date(String guaranteeStartDate) {
		guarantee_start_date = guaranteeStartDate;
	}
	public String getGuarantee_end_date() {
		return guarantee_end_date;
	}
	public void setGuarantee_end_date(String guaranteeEndDate) {
		guarantee_end_date = guaranteeEndDate;
	}
	public String getIs_delay_service() {
		return is_delay_service;
	}
	public void setIs_delay_service(String isDelayService) {
		is_delay_service = isDelayService;
	}
	public String getIs_wholesale() {
		return is_wholesale;
	}
	public void setIs_wholesale(String isWholesale) {
		is_wholesale = isWholesale;
	}
	public String getUse_type() {
		return use_type;
	}
	public void setUse_type(String useType) {
		use_type = useType;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String payType) {
		pay_type = payType;
	}
	public Double getInmileage() {
		return inmileage;
	}
	public void setInmileage(Double inmileage) {
		this.inmileage = inmileage;
	}
	public String getLast_maintain_date() {
		return last_maintain_date;
	}
	public void setLast_maintain_date(String lastMaintainDate) {
		last_maintain_date = lastMaintainDate;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoiceNo) {
		invoice_no = invoiceNo;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String ownerName) {
		owner_name = ownerName;
	}
	public String getContactor_name() {
		return contactor_name;
	}
	public void setContactor_name(String contactorName) {
		contactor_name = contactorName;
	}
	public String getOwner_spell() {
		return owner_spell;
	}
	public void setOwner_spell(String ownerSpell) {
		owner_spell = ownerSpell;
	}
	public String getOwner_property() {
		return owner_property;
	}
	public void setOwner_property(String ownerProperty) {
		owner_property = ownerProperty;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public String getIndustry_first() {
		return industry_first;
	}
	public void setIndustry_first(String industryFirst) {
		industry_first = industryFirst;
	}
	public String getIndustry_second() {
		return industry_second;
	}
	public void setIndustry_second(String industrySecond) {
		industry_second = industrySecond;
	}
	public String getFamily_income() {
		return family_income;
	}
	public void setFamily_income(String familyIncome) {
		family_income = familyIncome;
	}
	public String getEdu_level() {
		return edu_level;
	}
	public void setEdu_level(String eduLevel) {
		edu_level = eduLevel;
	}
	public String getOwner_marriage() {
		return owner_marriage;
	}
	public void setOwner_marriage(String ownerMarriage) {
		owner_marriage = ownerMarriage;
	}
	public String getChoose_reasion() {
		return choose_reasion;
	}
	public void setChoose_reasion(String chooseReasion) {
		choose_reasion = chooseReasion;
	}
	public String getBuy_purpose() {
		return buy_purpose;
	}
	public void setBuy_purpose(String buyPurpose) {
		buy_purpose = buyPurpose;
	}
	public String getWish_connect_type() {
		return wish_connect_type;
	}
	public void setWish_connect_type(String wishConnectType) {
		wish_connect_type = wishConnectType;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zipCode) {
		zip_code = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String eMail) {
		e_mail = eMail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContactor_mobile() {
		return contactor_mobile;
	}
	public void setContactor_mobile(String contactorMobile) {
		contactor_mobile = contactorMobile;
	}
	public String getContactor_phone() {
		return contactor_phone;
	}
	public void setContactor_phone(String contactorPhone) {
		contactor_phone = contactorPhone;
	}
	public String getContactor_email() {
		return contactor_email;
	}
	public void setContactor_email(String contactorEmail) {
		contactor_email = contactorEmail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String createDate) {
		create_date = createDate;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String updateDate) {
		update_date = updateDate;
	}
	public String getImportDate() {
		return importDate;
	}
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String timeStamp) {
		time_stamp = timeStamp;
	}
	public String getSequnce() {
		return sequnce;
	}
	public void setSequnce(String sequnce) {
		this.sequnce = sequnce;
	}
	public Long getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(Long isValid) {
		is_valid = isValid;
	}
	public String getBuy_type() {
		return buy_type;
	}
	public void setBuy_type(String buyType) {
		buy_type = buyType;
	}
	public String getCar_dsid() {
		return car_dsid;
	}
	public void setCar_dsid(String carDsid) {
		car_dsid = carDsid;
	}
	public String getCar_source() {
		return car_source;
	}
	public void setCar_source(String carSource) {
		car_source = carSource;
	}
	public String getDealer_service_code() {
		return dealer_service_code;
	}
	public void setDealer_service_code(String dealerServiceCode) {
		dealer_service_code = dealerServiceCode;
	}
	public String getConfgation() {
		return confgation;
	}
	public void setConfgation(String confgation) {
		this.confgation = confgation;
	}
	public String getCat_code() {
		return cat_code;
	}
	public void setCat_code(String catCode) {
		cat_code = catCode;
	}
	public String getContactor_address() {
		return contactor_address;
	}
	public void setContactor_address(String contactorAddress) {
		contactor_address = contactorAddress;
	}
	public String getContactor_gender() {
		return contactor_gender;
	}
	public void setContactor_gender(String contactorGender) {
		contactor_gender = contactorGender;
	}
	public String getTvb_create_date() {
		return tvb_create_date;
	}
	public void setTvb_create_date(String tvbCreateDate) {
		tvb_create_date = tvbCreateDate;
	}
}
