package com.fc.listener;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * 获取DMS反馈售前数据监听
 * @author lq
 *
 */
public class GetDmsPreSellTaskListener implements ServletContextListener {
	private static Logger log = Logger.getLogger(GetDmsPreSellTaskListener.class);
	private static XmlSaveUtil xml=new XmlSaveUtil();
	private Timer timer =new Timer(true);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[接收DMS售前反馈数据定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		String timing=xml.GET_DMS_PRE_TIME;
		try {
		QuartzManager.addJob(Constant.JOBDETAIL_06, Constant.LISTENER_MAP(Constant.JOBDETAIL_06), timing);  
		} catch (Exception e) {
			log.error("[接收DMS售前反馈数据定时任务]异常",e);
		}
	}

}
