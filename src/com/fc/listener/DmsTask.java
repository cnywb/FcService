package com.fc.listener;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.bean.DmsSubCallCenterBean;
import com.fc.bean.DmsToDataBean;
import com.fc.bean.DmsToLogBean;
import com.fc.bean.UploadDmsDataErrorBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.service.SaveDmsXmlService;
import com.fc.service.SubmitCallCenterService;
import com.fc.util.DateUtil;
import com.fc.util.FcUtil;
import com.fc.util.XmlSaveUtil;

/**
 * 定时任务工作类
 * 
 * @author lq
 * 
 */
public class DmsTask implements Job{
	private static Logger log = Logger.getLogger(DmsTask.class);
	private XmlSaveUtil xmlBean;
	private String[] aryReadType = null;
	public static Map<String, DmsToLogBean> toLogBeanMap = new HashMap<String, DmsToLogBean>();
	private SaveDmsDataDao dmsdao = new SaveDmsDataDao();
	SubmitCallCenterService subService = new SubmitCallCenterService();
	private static Map<String, String> directory;
	public DmsTask(){
		this.xmlBean=new XmlSaveUtil();
		this.xmlBean.importDate=this.xmlBean.formaty.format(new Date());
	}
	public void execute(JobExecutionContext arg0)
	throws JobExecutionException {
		String importDate=xmlBean.formaty.format(new Date());
		execuitFcToData(importDate);
	}
	public  boolean   execuitFcToData(String importDate){
		boolean isError = false;
		boolean flag=true;
		int count=0;// 读取计数
		boolean sessin=true;
		try {
			log.info("[读取DMS数据任务开始]开始时间:" + XmlSaveUtil.formatDate(new Date(), 1));
			XmlSaveUtil.importDate=importDate;
			FcUtil.setFcLOG(xmlBean.importDate);//初始化全局日志
			String readTypeStr = xmlBean.READ_TYPES; // 读取DMS相关读取类型
			aryReadType = readTypeStr.split(",");
			directory = addMap(aryReadType);
			while(flag){
				String xmlPath=xmlBean.DMS_TO_BACK_PATH;
				String bxmlPath=xmlBean.DMS_TO_DATE_PATH+"\\"+xmlBean.importDate;
				xmlPath=xmlPath+xmlBean.importDate+"\\xml";
				log.info("[获取已落地XML目录]开始，目录路径："+xmlPath);
				/**
				 * 已获取的文件目录
				 */
				File   fXMLML=new File(xmlPath);
				if(fXMLML!=null){
					if(fXMLML.exists()){
						if(fXMLML.listFiles().length>0){
							log.info("[复制相关目录]开始  "+XmlSaveUtil.formatDate(new Date(), 1));
							xmlBean.map.clear();
							xmlBean.copyFolder(fXMLML, bxmlPath);
							if(xmlBean.map.size()>0){
								for (Map.Entry<String,Integer> m : xmlBean.map.entrySet()) {
									log.info("[已获取相关目录数据]目录名："+m.getKey()+"  文件个数："+m.getValue());
								}
								count=xmlBean.map.size();
								flag=false;
							}
						}else{
							log.info("[目录下没有文件夹及文件]路径地址："+xmlPath+"  "+XmlSaveUtil.formatDate(new Date(), 1));
							flag=false;
						}
					}else{
						log.info("[未找到已落地XML目录]路径地址："+xmlPath+"  "+XmlSaveUtil.formatDate(new Date(), 1));
						flag=false;
					}
				}
			}
		} catch (Exception e) {
			log.error("拷贝目录出现异常！", e);
		}
		if (count>0) {
			try {
				SaveDmsXmlService.setpNumber = 0;
				toLogBeanMap.clear();
				initDmsLogMAP(importDate);
				// 执行数据读取及更新
				SaveDmsXmlService sdx = new SaveDmsXmlService(xmlBean,
						aryReadType, directory);
				sdx.readDmsXml();
				// 整理发送至CallCenter数据
				if (SaveDmsXmlService.isSub) {
					
					//创建发送至Call Center的DMS反馈数据XML
					//subService.createSubCCErrorXML(xmlBean.importDate); xuwei_20150722  空错号反馈数据不发送给cc
					
					if (dmsdao.getDayCallCenterCount(xmlBean.importDate) <= 0) {
					//if (dmsdao.getDayCallCenterCount(xmlBean.importDate) > 0) {//20140814 by xuw
					    //发送给CC数据的时间差	
					     List<String> lists=this.dmsdao.queryPropertiesByKey("createCCxmlDays");
					    
						// 符合条件并且可以拨打的数据
						List<DmsSubCallCenterBean> dmsCallCenter =new ArrayList<DmsSubCallCenterBean>();
						dmsdao.getSubCallCenterData(xmlBean.importDate, dmsCallCenter,lists);
						if (dmsCallCenter != null && dmsCallCenter.size() > 0) {
							if (FcUtil.getFcLOG().getIsSubCall() == 0) {
								FcUtil.getFcLOG().setIsSubCall(1L);
								dmsdao.updateFcServiceLog(FcUtil.getFcLOG());
							}
							// 整理预发送CallCenter的数据保存到数据库
							dmsdao.arrangeCallCenterData(dmsCallCenter);
							// 生成预发送CallCenter的数据为XML文件
							SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");	
							Date importDate_n= sdf.parse(xmlBean.importDate);	
							String path = xmlBean.SUB_CALLCENTER_SH_PATH
									+ "\\" + xmlBean.formaty.format(importDate_n);
							File f = new File(path);
							if (f.exists()) {
								File[] flt = f.listFiles();
								for (int i = 0; i < flt.length; i++) {
									flt[i].delete();
								}
							}
							SaveDmsXmlService.createXmlDocument(DmsSubCallCenterBean.class, dmsCallCenter,"GBK", path, xmlBean.importDate, Calendar.getInstance().getTimeInMillis()+ "");
						}
					}
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
				log.error("主业务出现异常！",e);
			}
			if (isError) {
				if (SaveDmsXmlService.isSub) {
					log.info("保存数据出现异常！"
							+ XmlSaveUtil.formatDate(new Date(), 1));
				} else {
					log.info("读取数据出现异常！"
							+ XmlSaveUtil.formatDate(new Date(), 1));
				}
			}
			log.info("[读取DMS数据任务结束]本次任务共完成了(" + SaveDmsXmlService.setpNumber
					+ ")个数据的读取解析，结束时间:" + XmlSaveUtil.formatDate(new Date(), 1));
		}else{
			log.info("[读取DMS数据任务结束]本次任务未读取到FTP的XML数据文件！"+ XmlSaveUtil.formatDate(new Date(), 1));
		}
		if((SaveDmsXmlService.setpNumber==0)){
			sessin=false;
		}
		return sessin;
	}
	/**
	 * 初始化日志MAP
	 */
	private void initDmsLogMAP(String importDate) {
		log.info("初始化Servlet访问页面所有日志开始");
		DateUtil.initLog();
		log.info("初始化Servlet访问页面所有日志完毕");
		log.info("初始化DMS数据接收日志");
		for (int i = 0; i <6; i++) {
			if (!(DmsTask.toLogBeanMap.containsKey(aryReadType[i]))) {
				DmsToLogBean tologBean = DmsTask.createDmsToLogBean(aryReadType[i],
						importDate);
				DmsTask.toLogBeanMap.put(aryReadType[i], tologBean);
			}
		}
	}

	/**
	 * 创建文件夹名字
	 * 
	 * @param aryReadType
	 * @return
	 */
	private Map<String, String> addMap(String[] aryReadType) {
		Map<String, String> m = new HashMap<String, String>();
		m.put(aryReadType[0], "owner");
		m.put(aryReadType[1], "repair");
		m.put(aryReadType[2], "dic");
		m.put(aryReadType[3], "parts");
		m.put(aryReadType[4], "deal");
		m.put(aryReadType[5], "elec");
		return m;
	}

	/**
	 * 获取相关条数
	 * 
	 * @param date
	 * @return
	 */
	private int getNumber(String date) {
		int k = 0;
		try {
			k = dmsdao.getDmsDataLogCount(xmlBean.importDate);
		} catch (Exception e) {
			log.error("获取DMS读取数据的条目，日志数量异常", e);
		}
		return k;
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
