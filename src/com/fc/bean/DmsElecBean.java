package com.fc.bean;


public class DmsElecBean implements java.io.Serializable {

	// Fields LVSHCAAE2BF701989

	/**
	 * 
	 */
	private static final long serialVersionUID = 6199508978264375406L;
	
	private String time_stamp;
	private String sequnce;
	private Double account_id=0.0;
	private String vin="";
	private String ro_no="";
	private String dealer_sale_code="";
	private String dealer_service_code="";
	private String used_date="";
	private Double used_amount=0.0;
	private Double lost_amount=0.0;
	private String ele_type="";
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getRo_no() {
		return ro_no;
	}
	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getDealer_service_code() {
		return dealer_service_code;
	}
	public void setDealer_service_code(String dealer_service_code) {
		this.dealer_service_code = dealer_service_code;
	}

	public Double getUsed_amount() {
		return used_amount;
	}
	public void setUsed_amount(Double used_amount) {
		this.used_amount = used_amount;
	}
	public Double getLost_amount() {
		return lost_amount;
	}
	public void setLost_amount(Double lost_amount) {
		this.lost_amount = lost_amount;
	}
	public String getEle_type() {
		return ele_type;
	}
	public void setEle_type(String ele_type) {
		this.ele_type = ele_type;
	}
	public Double getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Double account_id) {
		this.account_id = account_id;
	}

	public String getDealer_sale_code() {
		return dealer_sale_code;
	}
	public void setDealer_sale_code(String dealer_sale_code) {
		this.dealer_sale_code = dealer_sale_code;
	}
	public String getUsed_date() {
		return used_date;
	}
	public void setUsed_date(String used_date) {
		this.used_date = used_date;
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
}