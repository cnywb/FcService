package com.fc.bean;

import java.util.Date;

/**
 * 空错号信息表
 * (FC_DMS_TO_ERROR)
 * @author lq
 */
public class DmsToErorBean  extends DmsErrorBean implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3331563079828786710L;
	private String vin="";      //VIN号
	private String dealer_sale_code;//经销商代码
	private String odp1_code;
	private String odp2_code;
	private String owner_name;// 车主姓名
	private String gender;// 性别
	private String address;// 地址
	private String email;// 邮箱
	private String mobile;// 电话
	private String phone;//手机
	private String reback_date;//返回时间
	private String contactor_name;// 联系人姓名
	private String contactor_gender;// 联系人性别
	private String contactor_phone;// 联系人手机
	private String contactor_mobile;// 联系人电话
	private String contactor_province;// 联系人省
	private String contactor_city;// 联系人城市
	private String contactor_address;//联系人地址
	private String contactor_email;//联系人邮箱
	private String certificate_type;//证件类别
	private String certificate_code;//证件编号
	
	private String dataType;//是否为反馈数据空错号
	
	public String getDataType() {
	    return dataType;
	}
	public void setDataType(String dataType) {
	    this.dataType = dataType;
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
	public String getOdp1_code() {
		return odp1_code;
	}
	public void setOdp1_code(String odp1Code) {
		odp1_code = odp1Code;
	}
	public String getOdp2_code() {
		return odp2_code;
	}
	public void setOdp2_code(String odp2Code) {
		odp2_code = odp2Code;
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
}
