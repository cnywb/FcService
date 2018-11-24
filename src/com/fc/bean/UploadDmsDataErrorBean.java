package com.fc.bean;

/**
 * 上传DMS空错号数据实体Bean
 * @author lq
 *
 */
public class UploadDmsDataErrorBean implements java.io.Serializable {
	private static final long serialVersionUID = 2643831546495116643L;
	private String vin;
	private String dealer_sale_code;
	//private String Owner_Status; 
	//private String Contact_Status;
	private String owner_name;
	private String gender;
	private String address;
	private String email;
	private String mobile;
	private String phone;
	private String reback_date;
	private String contactor_name;
	private String contactor_gender;
	private String contactor_phone;
	private String contactor_mobile;
	private String contactor_province;
	private String contactor_city;
	private String contactor_address;
	private String contactor_email;
	private String CERTIFICATE_TYPE;
	private String CERTIFICATE_CODE;
	private String BATCH_COMMENT;//批次
	//private String ODP1;
	//private String ODP2;
	//private String odp1_code;
	//private String odp2_code;
	private String owner_status;
	private String contact_status;
	private String odp_type;
	
	public String getOwner_status() {
	    return owner_status;
	}
	public void setOwner_status(String owner_status) {
	    this.owner_status = owner_status;
	}
	public String getContact_status() {
	    return contact_status;
	}
	public void setContact_status(String contact_status) {
	    this.contact_status = contact_status;
	}
	public String getOdp_type() {
	    return odp_type;
	}
	public void setOdp_type(String odp_type) {
	    this.odp_type = odp_type;
	}
	/*public String getOdp1_code() {
	    return odp1_code;
	}
	public void setOdp1_code(String odp1_code) {
	    this.odp1_code = odp1_code;
	}
	public String getOdp2_code() {
	    return odp2_code;
	}
	public void setOdp2_code(String odp2_code) {
	    this.odp2_code = odp2_code;
	}
	public String getOwner_Status() {
	    return Owner_Status;
	}
	public void setOwner_Status(String owner_Status) {
	    Owner_Status = owner_Status;
	}
	public String getContact_Status() {
	    return Contact_Status;
	}
	public void setContact_Status(String contact_Status) {
	    Contact_Status = contact_Status;
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
	}*/
	public String getBATCH_COMMENT() {
	    return BATCH_COMMENT;
	}
	public void setBATCH_COMMENT(String bATCH_COMMENT) {
	    BATCH_COMMENT = bATCH_COMMENT;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getDealer_sale_code() {
		return dealer_sale_code;
	}
	public void setDealer_sale_code(String dealerSaleCode) {
		dealer_sale_code = dealerSaleCode;
	}
	
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String ownerName) {
		owner_name = ownerName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReback_date() {
		return reback_date;
	}
	public void setReback_date(String rebackDate) {
		reback_date = rebackDate;
	}
	public String getContactor_name() {
		return contactor_name;
	}
	public void setContactor_name(String contactorName) {
		contactor_name = contactorName;
	}
	public String getContactor_gender() {
		return contactor_gender;
	}
	public void setContactor_gender(String contactorGender) {
		contactor_gender = contactorGender;
	}
	public String getContactor_phone() {
		return contactor_phone;
	}
	public void setContactor_phone(String contactorPhone) {
		contactor_phone = contactorPhone;
	}
	public String getContactor_mobile() {
		return contactor_mobile;
	}
	public void setContactor_mobile(String contactorMobile) {
		contactor_mobile = contactorMobile;
	}
	public String getContactor_province() {
		return contactor_province;
	}
	public void setContactor_province(String contactorProvince) {
		contactor_province = contactorProvince;
	}
	public String getContactor_city() {
		return contactor_city;
	}
	public void setContactor_city(String contactorCity) {
		contactor_city = contactorCity;
	}
	public String getContactor_address() {
		return contactor_address;
	}
	public void setContactor_address(String contactorAddress) {
		contactor_address = contactorAddress;
	}
	public String getContactor_email() {
		return contactor_email;
	}
	public void setContactor_email(String contactorEmail) {
		contactor_email = contactorEmail;
	}
	public String getCERTIFICATE_TYPE() {
		return CERTIFICATE_TYPE;
	}
	public void setCERTIFICATE_TYPE(String cERTIFICATETYPE) {
		CERTIFICATE_TYPE = cERTIFICATETYPE;
	}
	public String getCERTIFICATE_CODE() {
		return CERTIFICATE_CODE;
	}
	public void setCERTIFICATE_CODE(String cERTIFICATECODE) {
		CERTIFICATE_CODE = cERTIFICATECODE;
	}

}
