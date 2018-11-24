package com.fc.bean;
/**
 * 售前业务全局日志
 * @author lq
 *
 */
public class M2FServiceLogBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3908760433090729552L;
	private String CREATE_DATE;  //创建时间
	private int    IS_SUB_CALL_CENTER=0;//是否将售前数据发送至CallCenter
	private int    IS_GET_CALL_CENTER=0;//是否接收了CallCenter返回的售前数据
	private int    IS_SUB_DMS=0;//是否发送至DMS售前数据
	private int    IS_GET_DMS=0;//是否接收DMS售前数据
	public String getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(String cREATEDATE) {
		CREATE_DATE = cREATEDATE;
	}
	public int getIS_SUB_CALL_CENTER() {
		return IS_SUB_CALL_CENTER;
	}
	public void setIS_SUB_CALL_CENTER(int iSSUBCALLCENTER) {
		IS_SUB_CALL_CENTER = iSSUBCALLCENTER;
	}
	public int getIS_GET_CALL_CENTER() {
		return IS_GET_CALL_CENTER;
	}
	public void setIS_GET_CALL_CENTER(int iSGETCALLCENTER) {
		IS_GET_CALL_CENTER = iSGETCALLCENTER;
	}
	public int getIS_SUB_DMS() {
		return IS_SUB_DMS;
	}
	public void setIS_SUB_DMS(int iSSUBDMS) {
		IS_SUB_DMS = iSSUBDMS;
	}
	public int getIS_GET_DMS() {
		return IS_GET_DMS;
	}
	public void setIS_GET_DMS(int iSGETDMS) {
		IS_GET_DMS = iSGETDMS;
	}
	

}
