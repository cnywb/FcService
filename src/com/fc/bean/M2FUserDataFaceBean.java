package com.fc.bean;

/**
 * 售前数据公共字段类
 * @author lq
 *
 */
public class M2FUserDataFaceBean {
	//是否存在历史
	protected int  isHistory=0;
	//接收CallCenter返回的时间
	protected String memo7;
	public int getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(int isHistory) {
		this.isHistory = isHistory;
	}
	public String getMemo7() {
		return memo7;
	}
	public void setMemo7(String memo7) {
		this.memo7 = memo7;
	}

	
}
