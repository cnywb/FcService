package com.fc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.service.UploadDmsXmlService;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

public class DcUpListener implements ServletContextListener {
	private static XmlSaveUtil xml = new XmlSaveUtil();
	private static Logger log = Logger.getLogger(UploadDmsXmlService.class);

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("启动上传空错号数据的定时任务!");
		QuartzManager.addJob(Constant.JOBDETAIL_01,Constant.LISTENER_MAP(Constant.JOBDETAIL_01), xml.UPAD_DMS_ERROR_TIME);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
