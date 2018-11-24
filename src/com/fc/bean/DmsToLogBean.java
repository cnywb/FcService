package com.fc.bean;

/**
 * 获取DMS数据相关日志表
 * (FC_DMS_TO_LOG)
 * @author lq
 *
 */
public class DmsToLogBean implements java.io.Serializable  {
	
	private static final long serialVersionUID = -6988610478848153990L;
	private String readDate;//获取数据当前时间
	private String readType;//获取数据对应类型
	private Long   readCount=0L;//获取数据条数
	private String fReadType; //DMS数据父类型
	public String getfReadType() {
		return fReadType;
	}
	public void setfReadType(String fReadType) {
		this.fReadType = fReadType;
	}
	public String getReadDate() {
		return readDate;
	}
	public void setReadDate(String readDate) {
		this.readDate = readDate;
	}
	public String getReadType() {
		return readType;
	}
	public void setReadType(String readType) {
		this.readType = readType;
	}
	public Long getReadCount() {
		return readCount;
	}
	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}
	
}
