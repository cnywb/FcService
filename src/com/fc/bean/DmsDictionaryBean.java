package com.fc.bean;

/**
 * 数据字典
 * @author lq
 *
 */
public class DmsDictionaryBean implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2286708538690872279L;
	private Long type=0L;
	private String code="";
	private String type_name="";
	private String code_name="";
	private String importDate="";
	private String time_stamp="";
	private String sequnce="";

	// Constructors

	/** default constructor */
	public DmsDictionaryBean() {
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
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