package com.fc.bean;

/**
 * 车主信息对应的问卷问题及答案
 * @author lq
 *
 */
public class DmsObQuestionsBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5126579618217418771L;
	private String  returnDate;
	private String  vin;
	private String  ownerMobile;
	private String  questionName;
	private String  answerValue;
	private String  oid;
	private Integer qnumber=0;
	
	public Integer getQnumber() {
		return qnumber;
	}
	public void setQnumber(Integer qnumber) {
		this.qnumber = qnumber;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getAnswerValue() {
		return answerValue;
	}
	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}
	
}
