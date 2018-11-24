package com.fc.listener;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * 定时获取售前数据监听
 * @author lq
 *
 */
public class PreSellCallCenterTaskListener implements ServletContextListener {
	private static Logger log = Logger.getLogger(PreSellCallCenterTaskListener.class);
	private static XmlSaveUtil xml=new XmlSaveUtil();
	private Timer timer =new Timer(true);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}
	public void contextInitialized(ServletContextEvent arg0) {
		/*log.info("[接收CC售前数据定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		String timing=xml.GET_CALLcENTER_PRE_TIME;
		try {
		QuartzManager.addJob(Constant.JOBDETAIL_04, Constant.LISTENER_MAP(Constant.JOBDETAIL_04), timing);  
		} catch (Exception e) {
			log.error("[接收CC售前数据定时任务]异常",e);
		}*/
	}
}
