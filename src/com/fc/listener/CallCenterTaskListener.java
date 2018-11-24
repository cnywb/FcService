package com.fc.listener;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.dao.db.businessDAO.GetCallCenterDataDao;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * 接收CallCenter数据定时任务
 * @author lq
 *
 */
public class CallCenterTaskListener implements ServletContextListener {
	private static Logger log = Logger.getLogger(CallCenterTaskListener.class);
	private static XmlSaveUtil xml=new XmlSaveUtil();
	private Timer timer =new Timer(true);
	private GetCallCenterDataDao ccdao = new GetCallCenterDataDao();
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[接收CallCenter数据定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		String timing=xml.GET_CALLCENTER_OB_TIME;
		try {
		    
		    if(xml.MANUAL_READCC_SWITCH!=null && !"".equals(xml.MANUAL_READCC_SWITCH)){
			String [] args=xml.MANUAL_READCC_SWITCH.split(";");
			if(args.length>1){
				String importDate=xml.formaty.format(new Date());
				if(importDate.equals(args[0])){
					CallCenterTask  ccT=new CallCenterTask();
					log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行开始");
					String[] dates=args[1].split(",");
					for (int i =0; i < dates.length; i++) {
						log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
						ccdao.getCCDateRemove(dates[i]);
						ccT.getCallCenterData(dates[i]);
						log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>结束");
					}
					log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行完毕");
				}
			}
		    }
		    
		   QuartzManager.addJob(Constant.JOBDETAIL_03, Constant.LISTENER_MAP(Constant.JOBDETAIL_03), timing);  
		} catch (Exception e) {
			log.error("[接收CallCenter数据定时任务]异常",e);
		}
	}
}
