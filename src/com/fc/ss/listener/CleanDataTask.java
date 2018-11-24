package com.fc.ss.listener;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.bean.DmsToLogBean;
import com.fc.service.SubmitCallCenterService;
import com.fc.ss.dao.db.CleanDataDao;
import com.fc.ss.service.CleanDataService;

import com.fc.util.XmlSaveUtil;

/**
 * soundsun
 * @author 
 * 定时任务工作类
 */
public class CleanDataTask implements Job{
	private static Logger log = Logger.getLogger(CleanDataTask.class);
	private XmlSaveUtil xmlBean;
	private String[] aryReadType = null;
	public static Map<String, DmsToLogBean> toLogBeanMap = new HashMap<String, DmsToLogBean>();
	private CleanDataDao cleandatadao = new CleanDataDao();
	SubmitCallCenterService subService = new SubmitCallCenterService();
	private static Map<String, String> directory;
	public CleanDataTask(){
		this.xmlBean=new XmlSaveUtil();
//		this.xmlBean.importDate=this.xmlBean.formaty.format(new Date());
	}
	CleanDataService subPreSellService = new CleanDataService();
	public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	public void execute(JobExecutionContext arg0)
			throws JobExecutionException {
				String importDate=xmlBean.formaty.format(new Date());
				try {

				  //2.创建Excel 并更改processed_flag
				   subPreSellService.createDmsPreSellExcel(importDate);
				 
				} catch (Exception e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
				
			}
	
	/**
	 * 获取DMS数据相关日志表实例化相关类
	 * 
	 * @return
	 */
	public static DmsToLogBean createDmsToLogBean(String readType,
			String readDate) {
		DmsToLogBean logBean = new DmsToLogBean();
		logBean.setReadType(readType);
		logBean.setReadDate(readDate);
		return logBean;
	}
}
