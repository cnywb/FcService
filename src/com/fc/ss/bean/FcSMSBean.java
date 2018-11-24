package com.fc.ss.bean;

import java.util.Date;

/**
 * 售前发送至dms xml字段
 * @author qp
 *
 */

public class FcSMSBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5317229296149009280L;
	
	private Long mid;
	private String mobile;
	private String status;
	private String sendTime;
	private String sendStatus;
	private String replyTime;
	private String replyContent;
	private String remark;
	private String msgId;
	private String wgCode;
	private String receiveDate;
	private String statusDesc;
	private Date systemDate;
	private String localToDealerId;
	
	
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getWgCode() {
		return wgCode;
	}
	public void setWgCode(String wgCode) {
		this.wgCode = wgCode;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Date getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}
	public String getLocalToDealerId() {
		return localToDealerId;
	}
	public void setLocalToDealerId(String localToDealerId) {
		this.localToDealerId = localToDealerId;
	}
		
}
