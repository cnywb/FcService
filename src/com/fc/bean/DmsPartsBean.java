package com.fc.bean;

/**
 * 零件
 * @author lq
 *
 */
public class DmsPartsBean implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 535834316771666716L;
	private String parts_no="";
	private String en_cesc="";
	private String product_code="";
	private Double recommend_price=0.0;
	private Double stock_price=0.0;
	private String importDate="";
	private String time_stamp="";
	private String sequnce="";

	// Constructors

	/** default constructor */
	public DmsPartsBean() {
	}

	public String getParts_no() {
		return parts_no;
	}

	public void setParts_no(String parts_no) {
		this.parts_no = parts_no;
	}

	public String getEn_cesc() {
		return en_cesc;
	}

	public void setEn_cesc(String en_cesc) {
		this.en_cesc = en_cesc;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public Double getRecommend_price() {
		return recommend_price;
	}

	public void setRecommend_price(Double recommend_price) {
		this.recommend_price = recommend_price;
	}

	public Double getStock_price() {
		return stock_price;
	}

	public void setStock_price(Double stock_price) {
		this.stock_price = stock_price;
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

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getSequnce() {
		return sequnce;
	}

	public void setSequnce(String sequnce) {
		this.sequnce = sequnce;
	}


}