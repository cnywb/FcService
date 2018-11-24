package com.fc.bean;



public class DmsLabourPartBean implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8328106270039286097L;
	private String order_id="";
	private String labour_id="";
	private String ro_no="";
	private String labour_code="";
	private String labour_name="";
	private Double labour_price=0.0;
	private Double std_labour_hour=0.0;
	private Double labour_amount=0.0;
	private Double discount=0.0;
	private String importDate="";
	private String primary_code="";
	private String primary_name="";
	private String second_code=""; 
	private String second_name=""; 
	private String std_hour="";
	private String sp_hour="";
	private String pg_hour="";

	

	// Constructors
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		order_id = orderId;
	}
	/** default constructor */
	public DmsLabourPartBean() {
	}

	public String getLabour_id() {
		return labour_id;
	}

	public void setLabour_id(String labour_id) {
		this.labour_id = labour_id;
	}

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getLabour_code() {
		return labour_code;
	}

	public void setLabour_code(String labour_code) {
		this.labour_code = labour_code;
	}

	public String getLabour_name() {
		return labour_name;
	}

	public void setLabour_name(String labour_name) {
		this.labour_name = labour_name;
	}

	public Double getLabour_price() {
		return labour_price;
	}

	public void setLabour_price(Double labour_price) {
		this.labour_price = labour_price;
	}

	public Double getStd_labour_hour() {
		return std_labour_hour;
	}

	public void setStd_labour_hour(Double std_labour_hour) {
		this.std_labour_hour = std_labour_hour;
	}

	public Double getLabour_amount() {
		return labour_amount;
	}

	public void setLabour_amount(Double labour_amount) {
		this.labour_amount = labour_amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public String getPrimary_code() {
		return primary_code;
	}

	public void setPrimary_code(String primary_code) {
		this.primary_code = primary_code;
	}

	public String getPrimary_name() {
		return primary_name;
	}

	public void setPrimary_name(String primary_name) {
		this.primary_name = primary_name;
	}

	public String getSecond_code() {
		return second_code;
	}

	public void setSecond_code(String second_code) {
		this.second_code = second_code;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getStd_hour() {
		return std_hour;
	}

	public void setStd_hour(String std_hour) {
		this.std_hour = std_hour;
	}

	public String getSp_hour() {
		return sp_hour;
	}

	public void setSp_hour(String sp_hour) {
		this.sp_hour = sp_hour;
	}

	public String getPg_hour() {
		return pg_hour;
	}

	public void setPg_hour(String pg_hour) {
		this.pg_hour = pg_hour;
	}

	

}