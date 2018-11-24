package com.fc.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.bean.DmsToLogBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.service.DmsPreSellService;
import com.fc.util.XmlSaveUtil;

/**
 * 发送给dms的售前数据定时任务工作类
 * 
 * @author qp
 * 
 */
public class DmsPreSellTask implements Job{
	private static Logger log = Logger.getLogger(DmsPreSellTask.class);
	private XmlSaveUtil xmlBean;
//	private String[] aryReadType = null;
	public static Map<String, DmsToLogBean> toLogBeanMap = new HashMap<String, DmsToLogBean>();
	private SaveDmsDataDao dmsdao = new SaveDmsDataDao();
	DmsPreSellService subPreSellService = new DmsPreSellService();
	public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
	public DmsPreSellTask(){
		this.xmlBean=new XmlSaveUtil();
	}
	public void execute(JobExecutionContext arg0)
	throws JobExecutionException {
		String importDate=xmlBean.formaty.format(new Date());
		try {
		  // 1.把回复表Y的数据插入到dms表 并且姓名，手机....不为空
		    dmsdao.saveFcm2fToDms();
		  //2.创建Excel 并更改senddate
		   subPreSellService.createDmsPreSellXml(importDate);
		 
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
	}
	
}
