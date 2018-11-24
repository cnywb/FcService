package com.fc.bean;

public class DmsAddItemBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5908439444480460577L;
	private String order_id="";
	private String add_id="";
	private String ro_no="";
	private String manage_sort_code="";
	private String add_item_code="";
	private String add_item_name="";
	private Double add_item_amount=0.0;
	private Double discount=0.0;
	private String importDate="";

	// Constructors

	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		order_id = orderId;
	}
	/** default constructor */
	public DmsAddItemBean() {
	}

	public String getAdd_id() {
		return add_id;
	}

	public void setAdd_id(String add_id) {
		this.add_id = add_id;
	}

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getManage_sort_code() {
		return manage_sort_code;
	}

	public void setManage_sort_code(String manage_sort_code) {
		this.manage_sort_code = manage_sort_code;
	}

	public String getAdd_item_code() {
		return add_item_code;
	}

	public void setAdd_item_code(String add_item_code) {
		this.add_item_code = add_item_code;
	}

	public String getAdd_item_name() {
		return add_item_name;
	}

	public void setAdd_item_name(String add_item_name) {
		this.add_item_name = add_item_name;
	}

	public Double getAdd_item_amount() {
		return add_item_amount;
	}

	public void setAdd_item_amount(Double add_item_amount) {
		this.add_item_amount = add_item_amount;
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
	
	

}