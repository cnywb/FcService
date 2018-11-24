package com.fc.webService;




/**
 * WebService 售后接口
 * FC >> CallCenter 
 * @author lq
 *
 */
public interface IAftermarket {
	/**
	 * 获取数据
	 * @param password 
	 * @return
	 */
	public String[] downFcDataFile(String password);
	/**
	 * 校验当前日期是否存在可调用的数据
	 * @param password
	 * @return
	 */
	public int isDataExist(String password);
}