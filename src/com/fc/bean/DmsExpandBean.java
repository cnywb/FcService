package com.fc.bean;

import java.util.Date;

public class DmsExpandBean {
	protected String isNewOwer;        //是否为新车主：X(新车主),L(老车主),Q(其它)
	protected Integer obCount=0;           //呼叫或拨打次数
	protected String isOwnerError="N";     //是否为空错号：Y(表示是空错号)，N(表示不是)
	protected String createOwnerDate="";  //数据接收日期
	protected String isObStr;         //该字段若不为空，则表示该数据不可OB
	protected String obType="";			 //ob类型owner(表示只有车主可呼叫)owner&contacter(表示车主及联系人都可呼叫)
	protected String carSync;
	protected String carEa;
	protected String catecode="";
	protected String noObReason;      //符合条件但任不拨打的原因
	
	//DmsSubCallCenterBean相关-------------------------------------------
	protected Date   subCreateDate; //数据创建时间

	//ODP1,ODP2相关
	protected String odp1Code;
	protected String odp2Code;

	//XML中没有的字段----------------------------------
	protected String short_name="";
	protected String dealer_id="";
	protected String is_wholesale="";
	protected Double inmileage=0.0;
	
	protected int number1=0;
	protected int number2=0;
	protected int number3=0;
	protected int number4=0;
	

	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	public int getNumber3() {
		return number3;
	}
	public void setNumber3(int number3) {
		this.number3 = number3;
	}
	public int getNumber4() {
		return number4;
	}
	public void setNumber4(int number4) {
		this.number4 = number4;
	}
	public String getOdp1Code() {
		return odp1Code;
	}
	public void setOdp1Code(String odp1Code) {
		this.odp1Code = odp1Code;
	}
	public String getOdp2Code() {
		return odp2Code;
	}
	public void setOdp2Code(String odp2Code) {
		this.odp2Code = odp2Code;
	}
	public String getCatecode() {
		return catecode;
	}
	public void setCatecode(String catecode) {
		this.catecode = catecode;
	}
	public String getIsNewOwer() {
		return isNewOwer;
	}
	public void setIsNewOwer(String isNewOwer) {
		this.isNewOwer = isNewOwer;
	}
	public Integer getObCount() {
		return obCount;
	}
	public void setObCount(Integer obCount) {
		this.obCount = obCount;
	}
	public String getIsOwnerError() {
		return isOwnerError;
	}
	public void setIsOwnerError(String isOwnerError) {
		this.isOwnerError = isOwnerError;
	}
	public String getCreateOwnerDate() {
		return createOwnerDate;
	}
	public void setCreateOwnerDate(String createOwnerDate) {
		this.createOwnerDate = createOwnerDate;
	}
	public String getIsObStr() {
		return isObStr;
	}
	public void setIsObStr(String isObStr) {
		this.isObStr = isObStr;
	}
	public String getObType() {
		return obType;
	}
	public void setObType(String obType) {
		this.obType = obType;
	}
	public String getCarSync() {
		return carSync;
	}
	public void setCarSync(String carSync) {
		this.carSync = carSync;
	}
	public String getCarEa() {
		return carEa;
	}
	public void setCarEa(String carEa) {
		this.carEa = carEa;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String shortName) {
		short_name = shortName;
	}
	public String getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(String dealerId) {
		dealer_id = dealerId;
	}
	public String getIs_wholesale() {
		return is_wholesale;
	}
	public void setIs_wholesale(String isWholesale) {
		is_wholesale = isWholesale;
	}
	public Double getInmileage() {
		return inmileage;
	}
	public void setInmileage(Double inmileage) {
		this.inmileage = inmileage;
	}
	

	public Date getSubCreateDate() {
		return subCreateDate;
	}
	public void setSubCreateDate(Date subCreateDate) {
		this.subCreateDate = subCreateDate;
	}
	public String getNoObReason() {
		return noObReason;
	}
	public void setNoObReason(String noObReason) {
		this.noObReason = noObReason;
	}
}
