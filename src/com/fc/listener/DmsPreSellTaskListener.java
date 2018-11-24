package com.fc.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.dao.db.SaveDmsDataDao;
import com.fc.service.DmsPreSellService;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

/**
 * 自动上传DMS售前数据
 * @author qp
 *
 */
public class DmsPreSellTaskListener implements ServletContextListener {
	private static Logger log = Logger.getLogger(DmsPreSellTaskListener.class);
	private static XmlSaveUtil xml=new XmlSaveUtil();
	private SaveDmsDataDao dmsdao = new SaveDmsDataDao();
	DmsPreSellService subPreSellService = new DmsPreSellService();
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[自动上传DMS售前数据定时任务]开启"+ XmlSaveUtil.formatDate(new Date(), 1));
		/*try {
			if(xml.DMSPRESELL_READ_SWITCH!=null && !"".equals(xml.DMSPRESELL_READ_SWITCH)){
				String [] args=xml.DMSPRESELL_READ_SWITCH.split(";");
				if(args.length>1){
					String importDate=xml.formaty.format(new Date());
					if(importDate.equals(args[0])){
						DmsTask  dm=new DmsTask();
						log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行开始");
						String[] dates=args[1].split(",");
						for (int i =0; i < dates.length; i++) {
							log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
							//1.手动执行， 清除sendDate  
							dmsdao.updateFcM2fToDms(dates[i]);
							 // 1.把回复表Y的数据插入到dms表 并且姓名，手机....不为空
							  dmsdao.saveFcm2fToDms();
							 //2.创建xml 并更改senddate
							subPreSellService.createDmsPreSellXml(dates[i]);
							log.info("[执行批次："+dates[i]+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>结束");
						}
						log.info("[当前时间("+importDate+")符合当天手动开启功能，手动执行程序]执行完毕");
					}
				}
		}
			QuartzManager.addJob(Constant.JOBDETAIL_07, Constant.LISTENER_MAP(Constant.JOBDETAIL_07), xml.DMSPRESELL_DATA_TIME);  
		} catch (Exception e) {
			log.error("启动定时任务异常",e);
		}*/
	}
}
