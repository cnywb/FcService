package com.fc.ss.bean;

import java.util.Date;

/**
 * 售前发送至dms xml字段
 * @author qp
 *
 */

public class FcCampaignChannelBean implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -483198812794695978L;
	
	private Long campaign_Channel_ID;
	private Long campaign_Master_ID;
	private String campaignName_ENG;
	private String campaignName_CHI;
	private String mediaSource_Original;
	private String mediaSource;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private String level6;
	private String level7;
	private String level8;
	private String level9;
	private String remark;
	private String memo1;
	private String memo2;
	private Date import_Date;
	private Date update_Date;
	private String create_User;
	private String update_User;

	

	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getLevel5() {
		return level5;
	}
	public void setLevel5(String level5) {
		this.level5 = level5;
	}
	public String getLevel6() {
		return level6;
	}
	public void setLevel6(String level6) {
		this.level6 = level6;
	}
	public String getLevel7() {
		return level7;
	}
	public void setLevel7(String level7) {
		this.level7 = level7;
	}
	public String getLevel8() {
		return level8;
	}
	public void setLevel8(String level8) {
		this.level8 = level8;
	}
	public String getLevel9() {
		return level9;
	}
	public void setLevel9(String level9) {
		this.level9 = level9;
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
	public Long getCampaign_Channel_ID() {
		return campaign_Channel_ID;
	}
	public void setCampaign_Channel_ID(Long campaign_Channel_ID) {
		this.campaign_Channel_ID = campaign_Channel_ID;
	}
	public Long getCampaign_Master_ID() {
		return campaign_Master_ID;
	}
	public void setCampaign_Master_ID(Long campaign_Master_ID) {
		this.campaign_Master_ID = campaign_Master_ID;
	}
	public String getCampaignName_CHI() {
		return campaignName_CHI;
	}
	public void setCampaignName_CHI(String campaignName_CHI) {
		this.campaignName_CHI = campaignName_CHI;
	}
	public String getMediaSource_Original() {
		return mediaSource_Original;
	}
	public void setMediaSource_Original(String mediaSource_Original) {
		this.mediaSource_Original = mediaSource_Original;
	}
	public String getCampaignName_ENG() {
		return campaignName_ENG;
	}
	public void setCampaignName_ENG(String campaignName_ENG) {
		this.campaignName_ENG = campaignName_ENG;
	}
	public String getMediaSource() {
		return mediaSource;
	}
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
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
