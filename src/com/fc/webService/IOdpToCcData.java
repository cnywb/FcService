package com.fc.webService;
/**
 * WebService 售前接口
 * FC >> CallCenter
 * @author qp
 *
 */
public interface IOdpToCcData {
	/**
	 * 获取售前数据接口
	 * @param password
	 * @return
	 */
	public String[] downPreSellDataFile(String password);
	/**
	 * 查询是否存在售前数据
	 * @param password
	 * @return
	 */
	public  int  isPreSellDataExist(String password);
}
