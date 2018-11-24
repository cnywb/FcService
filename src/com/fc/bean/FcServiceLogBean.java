package com.fc.bean;

/**
 * 系统日志
 * @author lq
 *
 */
public class FcServiceLogBean implements java.io.Serializable {
	private static final long serialVersionUID = -2250247028565456466L;
	private String  startDate; //业务开始时间
	private Long isDmsToData=0L; //是否已获取DMS数据
	private Long isCheckedData=0L; //是否校验车主数据
	private Long isProduceError=0L; //是否产生空错号数据
	private Long isSubCall=0L; //是否存在可OB的数据
	private Long isCCGet=0L;   //CallCenter是否在当天获取过本数据
	private Long isZeitCC=0L;  //是否在当天获取CallCenter数据
	private Long isCCError=0L; //是否查询到CallCenter数据含空错号
	private Long isSubDms=0L;  //是否将空错号数据发送至DMS
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Long getIsDmsToData() {
		return isDmsToData;
	}
	public void setIsDmsToData(Long isDmsToData) {
		this.isDmsToData = isDmsToData;
	}
	public Long getIsCheckedData() {
		return isCheckedData;
	}
	public void setIsCheckedData(Long isCheckedData) {
		this.isCheckedData = isCheckedData;
	}
	public Long getIsProduceError() {
		return isProduceError;
	}
	public void setIsProduceError(Long isProduceError) {
		this.isProduceError = isProduceError;
	}
	public Long getIsSubCall() {
		return isSubCall;
	}
	public void setIsSubCall(Long isSubCall) {
		this.isSubCall = isSubCall;
	}
	public Long getIsCCGet() {
		return isCCGet;
	}
	public void setIsCCGet(Long isCCGet) {
		this.isCCGet = isCCGet;
	}
	public Long getIsZeitCC() {
		return isZeitCC;
	}
	public void setIsZeitCC(Long isZeitCC) {
		this.isZeitCC = isZeitCC;
	}
	public Long getIsCCError() {
		return isCCError;
	}
	public void setIsCCError(Long isCCError) {
		this.isCCError = isCCError;
	}
	public Long getIsSubDms() {
		return isSubDms;
	}
	public void setIsSubDms(Long isSubDms) {
		this.isSubDms = isSubDms;
	}
	
}
