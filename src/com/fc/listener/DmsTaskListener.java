package com.fc.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * 接收DMS数据定时任务
 * @author lq
 *
 */
public class DmsTaskListener implements ServletContextListener {
	private static Logger log = Logger.getLogger(DmsTaskListener.class);
	private static XmlSaveUtil xml=new XmlSaveUtil();
	private SaveDmsDataDao dmsdao = new SaveDmsDataDao();
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[接收DMS数据定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		try {
			if(xml.MANUAL_READ_SWITCH!=null && !"".equals(xml.MANUAL_READ_SWITCH)){
				String [] args=xml.MANUAL_READ_SWITCH.split(";");
				if(args.length>1){
					String importDate=xml.formaty.format(new Date());
					if(importDate.equals(args[0])){
						DmsTask  dm=new DmsTask();
						log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行开始");
						String[] dates=args[1].split(",");
						for (int i =0; i < dates.length; i++) {
							log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
							dmsdao.getDateRemove(dates[i]);
							boolean isbool=dm.execuitFcToData(dates[i]);
							if(!isbool){
								dmsdao.getDateRemove(dates[i]);
							}
							log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>结束");
						}
						log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行完毕");
					}
				}
		}
			QuartzManager.addJob(Constant.JOBDETAIL_02, Constant.LISTENER_MAP(Constant.JOBDETAIL_02), xml.VISIT_DMS_TO_DATA_TIME);  
		} catch (Exception e) {
			log.error("启动定时任务异常",e);
		}
	}
}
