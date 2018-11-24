package com.fc.ss.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.fc.listener.QuartzManager;
import com.fc.util.Constant;
import com.fc.util.XmlSaveUtil;

public class SAPToFcTaskListener implements ServletContextListener {

	private static Logger log = Logger.getLogger(SAPToFcTaskListener.class);
	private static XmlSaveUtil xml = new XmlSaveUtil();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("[SAPToFcTask]定时任务开始!");
		try {
			if (xml.MANUAL_SS_READ_SWITCH != null
					&& !"".equals(xml.MANUAL_SS_READ_SWITCH)) {
				String[] args = xml.MANUAL_SS_READ_SWITCH.split(";");
				if (args.length >= 1) {
					String importDate = xml.formaty.format(new Date());
					if (importDate.equals(args[0])) {
						SAPToFcTask task = new SAPToFcTask();
						// 我看不明白 这两个importDate的值会有什么变化~ 压根就是一个值 何必放两遍呢 ~
						log.info("[当前时间(" + importDate + ")]开始自动执行SAPToFcTask");
						task.execute(null);
						importDate = xml.formaty.format(new Date());
						log.info("[当前时间(" + importDate + ")]自动执行SAPToFcTask完毕");
					}
				}
			}
			QuartzManager.addJob(Constant.JOBDETAIL_12,
					Constant.LISTENER_MAP(Constant.JOBDETAIL_12),
					xml.MOVE_SAP_TO_FC_TIME);
			log.info("[SAPToFcTask]定时任务结束!");
		} catch (Exception e) {
			log.error("启动SAPToFcTask定时任务异常", e);
		}

	}
}