package com.fc.bean;

/**
 * 提交至CallCenter数据Bean
 * @author lq
 *
 */
public class DmsSubCallCenterBean extends DmsExpandBean implements java.io.Serializable {
	
	/**
	 */
	private static final long serialVersionUID = -392258904626196089L;
	private String ym;
	private String sale_date;
	private String dealer_code;
	private String dealer_name;
	private String vin_name;
	private String color;
	private String car_brand;
	private String car_model;
	private String catecode;
	private String car_carreg;
	private String owner_name;
	private String owner_sex;
	private String owner_birthday;
	private String owner_marital;
	private String owner_tel;
	private String owner_mobile;
	private String owner_province;
	private String owner_city;
	private String owner_address;
	private String owner_zip;
	private String owner_email;
	private String contacter_name;
	private String contacter_sex;
	private String contacter_tel;
	private String contacter_mobile;
	private String contacter_address;
	private String contacter_email;
	private String car_sync;
	private String car_ea;
	private String ob_type;
	private String certificate_type;
	private String certificate_code;
	private Integer subCcNumber=0;
	private String priority;//分类级别
	private String dup_type;//手机重复标识
	private String receiveDate;
	public String getReceiveDate() {
	    return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
	    this.receiveDate = receiveDate;
	}
	public Integer getSubCcNumber() {
		return subCcNumber;
	}
	public void setSubCcNumber(Integer subCcNumber) {
		this.subCcNumber = subCcNumber;
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
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String saleDate) {
		sale_date = saleDate;
	}
	public String getDealer_code() {
		return dealer_code;
	}
	public void setDealer_code(String dealerCode) {
		dealer_code = dealerCode;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealerName) {
		dealer_name = dealerName;
	}
	public String getVin_name() {
		return vin_name;
	}
	public void setVin_name(String vinName) {
		vin_name = vinName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCar_brand() {
		return car_brand;
	}
	public void setCar_brand(String carBrand) {
		car_brand = carBrand;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String carModel) {
		car_model = carModel;
	}
	public String getCatecode() {
		return catecode;
	}
	public void setCatecode(String catecode) {
		this.catecode = catecode;
	}
	public String getCar_carreg() {
		return car_carreg;
	}
	public void setCar_carreg(String carCarreg) {
		car_carreg = carCarreg;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String ownerName) {
		owner_name = ownerName;
	}
	public String getOwner_sex() {
		return owner_sex;
	}
	public void setOwner_sex(String ownerSex) {
		owner_sex = ownerSex;
	}
	public String getOwner_birthday() {
		return owner_birthday;
	}
	public void setOwner_birthday(String ownerBirthday) {
		owner_birthday = ownerBirthday;
	}
	public String getOwner_marital() {
		return owner_marital;
	}
	public void setOwner_marital(String ownerMarital) {
		owner_marital = ownerMarital;
	}
	public String getOwner_tel() {
		return owner_tel;
	}
	public void setOwner_tel(String ownerTel) {
		owner_tel = ownerTel;
	}
	public String getOwner_mobile() {
		return owner_mobile;
	}
	public void setOwner_mobile(String ownerMobile) {
		owner_mobile = ownerMobile;
	}
	public String getOwner_province() {
		return owner_province;
	}
	public void setOwner_province(String ownerProvince) {
		owner_province = ownerProvince;
	}
	public String getOwner_city() {
		return owner_city;
	}
	public void setOwner_city(String ownerCity) {
		owner_city = ownerCity;
	}
	public String getOwner_address() {
		return owner_address;
	}
	public void setOwner_address(String ownerAddress) {
		owner_address = ownerAddress;
	}
	public String getOwner_zip() {
		return owner_zip;
	}
	public void setOwner_zip(String ownerZip) {
		owner_zip = ownerZip;
	}
	public String getOwner_email() {
		return owner_email;
	}
	public void setOwner_email(String ownerEmail) {
		owner_email = ownerEmail;
	}
	public String getContacter_name() {
		return contacter_name;
	}
	public void setContacter_name(String contacterName) {
		contacter_name = contacterName;
	}
	public String getContacter_sex() {
		return contacter_sex;
	}
	public void setContacter_sex(String contacterSex) {
		contacter_sex = contacterSex;
	}
	public String getContacter_tel() {
		return contacter_tel;
	}
	public void setContacter_tel(String contacterTel) {
		contacter_tel = contacterTel;
	}
	public String getContacter_mobile() {
		return contacter_mobile;
	}
	public void setContacter_mobile(String contacterMobile) {
		contacter_mobile = contacterMobile;
	}
	public String getContacter_address() {
		return contacter_address;
	}
	public void setContacter_address(String contacterAddress) {
		contacter_address = contacterAddress;
	}
	public String getContacter_email() {
		return contacter_email;
	}
	public void setContacter_email(String contacterEmail) {
		contacter_email = contacterEmail;
	}
	public String getCar_sync() {
		return car_sync;
	}
	public void setCar_sync(String carSync) {
		car_sync = carSync;
	}
	public String getCar_ea() {
		return car_ea;
	}
	public void setCar_ea(String carEa) {
		car_ea = carEa;
	}
	public String getOb_type() {
		return ob_type;
	}
	public void setOb_type(String obType) {
		ob_type = obType;
	}
	public String getPriority() {
	    return priority;
	}
	public void setPriority(String priority) {
	    this.priority = priority;
	}
	public String getDup_type() {
	    return dup_type;
	}
	public void setDup_type(String dup_type) {
	    this.dup_type = dup_type;
	}

}
