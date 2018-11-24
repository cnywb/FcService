package com.fc.bean;

import java.util.Date;

/**
 * 待发送至Call Center 的DMS反馈的空错号数据
 * @author lq
 *
 */
public class SubCcDmsToErrorBean implements java.io.Serializable {
	private static final long serialVersionUID = -2323450567138199307L;
	
	private String sub_cc_id;//发送Call Center数据ID
	private String owner_vin;//VIN码
	private String owner_data_id;//车主表数据ID
	private String owner_create_date;//车主表数据产生时间
	private String is_get_data="N";//Call Center是否已获取数据N(未获取)Y(已获取)
	private Date   get_date;//获取时间
	private Integer obLsCount; //Ob历史数量
	public Integer getObLsCount() {
		return obLsCount;
	}
	public void setObLsCount(Integer obLsCount) {
		this.obLsCount = obLsCount;
	}
	public String getSub_cc_id() {
		return sub_cc_id;
	}
	public void setSub_cc_id(String subCcId) {
		sub_cc_id = subCcId;
	}
	public String getOwner_vin() {
		return owner_vin;
	}
	public void setOwner_vin(String ownerVin) {
		owner_vin = ownerVin;
	}
	public String getOwner_data_id() {
		return owner_data_id;
	}
	public void setOwner_data_id(String ownerDataId) {
		owner_data_id = ownerDataId;
	}
	public String getOwner_create_date() {
		return owner_create_date;
	}
	public void setOwner_create_date(String ownerCreateDate) {
		owner_create_date = ownerCreateDate;
	}
	public String getIs_get_data() {
		return is_get_data;
	}
	public void setIs_get_data(String isGetData) {
		is_get_data = isGetData;
	}
	public Date getGet_date() {
		return get_date;
	}
	public void setGet_date(Date getDate) {
		get_date = getDate;
	}

}
