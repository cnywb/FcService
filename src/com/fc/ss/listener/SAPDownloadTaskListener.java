package com.fc.ss.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.listener.QuartzManager;
import com.fc.ss.dao.db.BeforeOBDao;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * soundsun
 * @author 
 *
 */
public class SAPDownloadTaskListener implements ServletContextListener {
	
	private static Logger log = Logger.getLogger(SAPDownloadTaskListener.class);
	
	private static XmlSaveUtil xml=new XmlSaveUtil();

	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent arg0) {
		
		BeforeOBDao dao=new BeforeOBDao();
		
		
		
			try {
				dao.getSeqNextValue("TEST1_SEQ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
		
		log.info("[SAPDownload定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		try {

			if(xml.MANUAL_SS_READ_SWITCH!=null && !"".equals(xml.MANUAL_SS_READ_SWITCH)){
				String [] args=xml.MANUAL_SS_READ_SWITCH.split(";");
				if(args.length>1){
					String importDate=xml.formaty.format(new Date());
					if(importDate.equals(args[0])){
						SAPDownloadTask  task=new SAPDownloadTask();
						String[] dates=args[1].split(",");
						for (int i =0; i < dates.length; i++) {
							task.execute(null);
						}
					}
				}
			}
			QuartzManager.addJob(Constant.JOBDETAIL_11, Constant.LISTENER_MAP(Constant.JOBDETAIL_11), xml.GET_SAP_DOWNLOAD_TIME);
			log.info("[SAPDownload定时任务]结束"+ XmlSaveUtil.formatDate(new Date(), 1));
		} catch (Exception e) {
			log.error("启动SAPDownload定时任务异常",e);
		}
	}
}
