package com.fc.ss.bean;

import java.util.Date;

/**
 * 售前发送至dms xml字段
 * @author qp
 *
 */

public class FcCampaignMasterBean implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4794564502558296245L;
	
	private Long campaign_Master_ID;//ID编号
	private String campaignName_ENG;
	private String campaignName_CHI;
	private String Source;
	private String car_Type;
	private String remark;
	private String memo1;
	private String memo2;
	private Date import_Date;
	private Date update_Date;
	private String create_User;
	private String update_User;
//	private String isDownload;
//	private Long deleteFlag;
//	private Long isValid;//新增字段
//	private Long processedFlag;
	
	public Long getCampaign_Master_ID() {
		return campaign_Master_ID;
	}
	public void setCampaign_Master_ID(Long campaign_Master_ID) {
		this.campaign_Master_ID = campaign_Master_ID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getMemo2() {
		return memo2;
	}
	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}
	public String getCampaignName_ENG() {
		return campaignName_ENG;
	}
	public void setCampaignName_ENG(String campaignName_ENG) {
		this.campaignName_ENG = campaignName_ENG;
	}
	public String getCampaignName_CHI() {
		return campaignName_CHI;
	}
	public void setCampaignName_CHI(String campaignName_CHI) {
		this.campaignName_CHI = campaignName_CHI;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getCar_Type() {
		return car_Type;
	}
	public void setCar_Type(String car_Type) {
		this.car_Type = car_Type;
	}
	public Date getImport_Date() {
		return import_Date;
	}
	public void setImport_Date(Date import_Date) {
		this.import_Date = import_Date;
	}
	public Date getUpdate_Date() {
		return update_Date;
	}
	public void setUpdate_Date(Date update_Date) {
		this.update_Date = update_Date;
	}
	public String getCreate_User() {
		return create_User;
	}
	public void setCreate_User(String create_User) {
		this.create_User = create_User;
	}
	public String getUpdate_User() {
		return update_User;
	}
	public void setUpdate_User(String update_User) {
		this.update_User = update_User;
	}
	

}
