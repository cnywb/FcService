package com.fc.util;

import org.apache.log4j.Logger;

import com.fc.bean.FcServiceLogBean;
import com.fc.bean.M2FServiceLogBean;
import com.fc.dao.db.SaveDmsDataDao;

public class FcUtil {
	private static Logger log = Logger.getLogger(FcUtil.class);
	private static FcServiceLogBean fclog = null;//售后
	private static M2FServiceLogBean m2log=null; //售前
	private static SaveDmsDataDao dao = new SaveDmsDataDao();

	/**
	 * 获取售后全局日志
	 */
	public static FcServiceLogBean getFcLOG() {
		return fclog;
	}
	/**
	 * 获取售前全局日志
	 * @return
	 */
	public static M2FServiceLogBean getM2LOG(){
		return m2log;
	}
	/**
	 * 创建售后全局业务日志
	 * 
	 * @param readDate
	 */
	public static void setFcLOG(String readDate) {
		log.info("[初始化售后全局业务日志]---------开始---------");
		fclog = new FcServiceLogBean();
		fclog.setStartDate(readDate);
		log.info("[初始化售后全局业务日志]插入日志数据到数据库！");
		try {
			int k=dao.selectFcServiceCount(readDate); 
			if(!(k>0)){
				dao.saveFcServiceLog(fclog);
			}
		} catch (Exception e) {
			log.error("[初始化售后全局业务日志]出现异常",e);
		}
		log.info("[初始化售后全局业务日志]---------结束---------");
	}
	/**
	 * 创建售前全局业务日志
	 * 
	 * @param readDate
	 */
	public static void setM2LOG(String readDate) {
		log.info("[初始化售前全局业务日志]---------开始---------");
		m2log = new M2FServiceLogBean();
		m2log.setCREATE_DATE(readDate);
		log.info("[初始化售前全局业务日志]插入日志数据到数据库！");
		try {
			int k=dao.selectM2ServiceCount(readDate); 
			if(!(k>0)){
				dao.saveM2ServiceLog(m2log);
			}
		} catch (Exception e) {
			log.error("[初始化售前全局业务日志]出现异常",e);
		}
		log.info("[初始化售前全局业务日志]---------结束---------");
	}
}
