package com.fc.bean;



public class DmsSalesPartBean implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5461038115178703616L;
	private String order_id="";
	private String sales_id="";
	private String ro_no="";
	private String part_no="";
	private String part_name="";
	private Double part_quantity=0.0;
	private String charge_partition_code="";
	private String manage_sort_code="";
	private Double part_cost_price=0.0;
	private Double part_sales_price=0.0;
	private Double part_cost_amount=0.0;
	private Double part_sales_amount=0.0;
	private Double discount=0.0;
	private String importDate="";
	private String charge_partition_name = "";

	// Constructors

	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		order_id = orderId;
	}
	/** default constructor */
	public DmsSalesPartBean() {
	}

	public String getSales_id() {
		return sales_id;
	}

	public void setSales_id(String sales_id) {
		this.sales_id = sales_id;
	}

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getPart_no() {
		return part_no;
	}

	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public Double getPart_quantity() {
		return part_quantity;
	}

	public void setPart_quantity(Double part_quantity) {
		this.part_quantity = part_quantity;
	}

	public String getCharge_partition_code() {
		return charge_partition_code;
	}

	public void setCharge_partition_code(String charge_partition_code) {
		this.charge_partition_code = charge_partition_code;
	}

	public String getManage_sort_code() {
		return manage_sort_code;
	}

	public void setManage_sort_code(String manage_sort_code) {
		this.manage_sort_code = manage_sort_code;
	}

	public Double getPart_cost_price() {
		return part_cost_price;
	}

	public void setPart_cost_price(Double part_cost_price) {
		this.part_cost_price = part_cost_price;
	}

	public Double getPart_sales_price() {
		return part_sales_price;
	}

	public void setPart_sales_price(Double part_sales_price) {
		this.part_sales_price = part_sales_price;
	}

	public Double getPart_cost_amount() {
		return part_cost_amount;
	}

	public void setPart_cost_amount(Double part_cost_amount) {
		this.part_cost_amount = part_cost_amount;
	}

	public Double getPart_sales_amount() {
		return part_sales_amount;
	}

	public void setPart_sales_amount(Double part_sales_amount) {
		this.part_sales_amount = part_sales_amount;
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

	public String getCharge_partition_name() {
		return charge_partition_name;
	}

	public void setCharge_partition_name(String charge_partition_name) {
		this.charge_partition_name = charge_partition_name;
	}


}