package com.fc.ss.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

import com.fc.listener.QuartzManager;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

public class AEMListener implements ServletContextListener{

	private static Logger log = Logger.getLogger(AEMListener.class);
	
	private static XmlSaveUtil xml=new XmlSaveUtil();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[AEM监控定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		try {
			if (xml.MANUAL_SS_READ_SWITCH != null
					&& !"".equals(xml.MANUAL_SS_READ_SWITCH)){
				AEMTask aemTask=new AEMTask();
				aemTask.execute(null);
			}
			QuartzManager.addJob(Constant.JOBDETAIL_13, Constant.LISTENER_MAP(Constant.JOBDETAIL_13), xml.LISTENER_AEM_INTERFACE);
		} catch (JobExecutionException e) {
			log.error("启动AEM监控定时任务异常",e);
		}
	}

}
