package com.fc.bean;

import java.util.Date;

/**
 * @author WangJian
 *
 * 2013-11-28
 */
public class DmsErrorBean implements java.io.Serializable{

	private static final long serialVersionUID = 4284988797792841603L;
	private String ownerId="";  //车主ID
	private String createDate="";  //数据接收时间
	private Date   endDate;     //截至日期
	private Integer obCount=0;  //ob次数
	private String remark;      //备注
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getObCount() {
		return obCount;
	}
	public void setObCount(Integer obCount) {
		this.obCount = obCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
