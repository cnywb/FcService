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
public class CleanDataTaskListener implements ServletContextListener {
	
	private static Logger log = Logger.getLogger(CleanDataTaskListener.class);
	
	private static XmlSaveUtil xml=new XmlSaveUtil();
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[cleanData数据定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		try {
//			CleanDataTask  cd=new CleanDataTask();
//			cd.execute(null);
			if(xml.MANUAL_READ_SWITCH!=null && !"".equals(xml.MANUAL_READ_SWITCH)){
				String [] args=xml.MANUAL_READ_SWITCH.split(";");
				if(args.length>1){
					String importDate=xml.formaty.format(new Date());
					if(importDate.equals(args[0])){
						CleanDataTask  cd=new CleanDataTask();
						log.info("[当前时间("+importDate+")开始自动化清理");
						String[] dates=args[1].split(",");
						for (int i =0; i < dates.length; i++) {
							log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
							cd.execute(null);
							log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>结束");
						}
						log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行完毕");
					}
				}
		}
			QuartzManager.addJob(Constant.JOBDETAIL_09, Constant.LISTENER_MAP(Constant.JOBDETAIL_09), xml.GET_CLEAN_DATA_TIME);  
		} catch (Exception e) {
			log.error("启动定时任务异常",e);
		}
	}
}
