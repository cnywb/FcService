package com.fc.ss.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.listener.QuartzManager;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * soundsun
 * @author 
 *
 */
public class SAPUploadTaskListener implements ServletContextListener {
	
	private static Logger log = Logger.getLogger(SAPUploadTaskListener.class);
	
	private static XmlSaveUtil xml=new XmlSaveUtil();
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[生成TXT文件至SAP-FTP服务器定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		try {

			if(xml.MANUAL_SS_READ_SWITCH != null && !"".equals(xml.MANUAL_SS_READ_SWITCH)){
				String [] args=xml.MANUAL_SS_READ_SWITCH.split(";");
				if(args.length >= 1){
					String importDate = xml.formaty.format(new Date());
					if(importDate.equals(args[0])){
						SAPUploadTask  task = new SAPUploadTask();
						//我看不明白 这两个importDate的值会有什么变化~  压根就是一个值 何必放两遍呢  ~
						log.info("[当前时间("+importDate+")]开始自动生成TXT");
						task.execute(null);
						importDate = xml.formaty.format(new Date());
						log.info("[当前时间("+importDate+")]自动生成TXT执行完毕");
					}
				}
			}
			QuartzManager.addJob(Constant.JOBDETAIL_10, Constant.LISTENER_MAP(Constant.JOBDETAIL_10), xml.GET_SAP_UPLOAD_TIME);
			log.info("[生成TXT文件至SAP-FTP服务器定时任务]结束"+ XmlSaveUtil.formatDate(new Date(), 1));
		} catch (Exception e) {
			log.error("启动生成TXT文件至SAP-FTP服务器定时任务异常",e);
		}
	}
}
