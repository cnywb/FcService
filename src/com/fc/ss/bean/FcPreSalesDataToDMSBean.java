package com.fc.ss.bean;

/**
 * 售前发送至dms xml字段
 * @author qp
 *
 */
public class FcPreSalesDataToDMSBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 4354603903387011825L;
	
//	private String dataUid;//主键ID
	private String dataDistDate;//数据分批日期
	private String dataPriority;//优先级
	private String name;//姓名
	private String sex;//性别
	private String mobile;//移动手机号码1
	private String mobile2;//移动手机号码2
	private String pTel;//公司电话
	private String phone;//住宅电话
	private String email;//Email
	private String province;//省份
	private String city;//城市
	private String clientSource;//客户来源
	private String likeModel;//感兴趣车型
	private String jhCarTime;//计划购车时间
	private String clientDemand;//客户提出的需求
	private String maname;//市场活动名称
	private String dealerCode;//经销商代码
	private String dealerRegion;//所属大区
	private String dealerCommunity;//所属小区
	private String dealerName;//经销商名称
	private String sellId;//销售线索表ID(外键)
//	private String importCode;//导入的批次号代码格式为当期时间的yyyyMMdd（后方附导入序号）以1开头
//	private Long processedFlag;
	
	public String getDataDistDate() {
	    return dataDistDate;
	}
	public void setDataDistDate(String dataDistDate) {
	    this.dataDistDate = dataDistDate;
	}
	public String getDataPriority() {
	    return dataPriority;
	}
	public void setDataPriority(String dataPriority) {
	    this.dataPriority = dataPriority;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public String getSex() {
	    return sex;
	}
	public void setSex(String sex) {
	    this.sex = sex;
	}
	public String getMobile() {
	    return mobile;
	}
	public void setMobile(String mobile) {
	    this.mobile = mobile;
	}
	public String getMobile2() {
	    return mobile2;
	}
	public void setMobile2(String mobile2) {
	    this.mobile2 = mobile2;
	}
	public String getpTel() {
	    return pTel;
	}
	public void setpTel(String pTel) {
	    this.pTel = pTel;
	}
	public String getPhone() {
	    return phone;
	}
	public void setPhone(String phone) {
	    this.phone = phone;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	public String getProvince() {
	    return province;
	}
	public void setProvince(String province) {
	    this.province = province;
	}
	public String getCity() {
	    return city;
	}
	public void setCity(String city) {
	    this.city = city;
	}
	public String getClientSource() {
	    return clientSource;
	}
	public void setClientSource(String clientSource) {
	    this.clientSource = clientSource;
	}
	public String getLikeModel() {
	    return likeModel;
	}
	public void setLikeModel(String likeModel) {
	    this.likeModel = likeModel;
	}
	public String getJhCarTime() {
	    return jhCarTime;
	}
	public void setJhCarTime(String jhCarTime) {
	    this.jhCarTime = jhCarTime;
	}
	public String getClientDemand() {
	    return clientDemand;
	}
	public void setClientDemand(String clientDemand) {
	    this.clientDemand = clientDemand;
	}
	public String getManame() {
	    return maname;
	}
	public void setManame(String maname) {
	    this.maname = maname;
	}
	public String getDealerCode() {
	    return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
	    this.dealerCode = dealerCode;
	}
	public String getDealerRegion() {
	    return dealerRegion;
	}
	public void setDealerRegion(String dealerRegion) {
	    this.dealerRegion = dealerRegion;
	}
	public String getDealerCommunity() {
	    return dealerCommunity;
	}
	public void setDealerCommunity(String dealerCommunity) {
	    this.dealerCommunity = dealerCommunity;
	}
	public String getDealerName() {
	    return dealerName;
	}
	public void setDealerName(String dealerName) {
	    this.dealerName = dealerName;
	}
	public String getSellId() {
	    return sellId;
	}
	public void setSellId(String sellId) {
	    this.sellId = sellId;
	}
//	public String getImportCode() {
//		return importCode;
//	}
//	public void setImportCode(String importCode) {
//		this.importCode = importCode;
//	}
//	public String getDataUid() {
//		return dataUid;
//	}
//	public void setDataUid(String dataUid) {
//		this.dataUid = dataUid;
//	}
//	public Long getProcessedFlag() {
//		return processedFlag;
//	}
//	public void setProcessedFlag(Long processedFlag) {
//		this.processedFlag = processedFlag;
//	}

  
}
