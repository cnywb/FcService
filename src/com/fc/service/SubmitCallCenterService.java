package com.fc.service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.bean.DmsSubCallCenterBean;
import com.fc.bean.DmsToDataBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.XmlSaveUtil;

/**
 * 提交至CallCenter数据操作类
 * 
 * @author lq
 * 
 */
public class SubmitCallCenterService {
	private static Logger log = Logger.getLogger(SubmitCallCenterService.class);
	private SaveDmsDataDao dao = new SaveDmsDataDao();
	private XmlSaveUtil xmlutil = new XmlSaveUtil();

	
	public String getVins(List<DmsToDataBean> dmsToData){
		StringBuilder bud = new StringBuilder();
		for (DmsToDataBean dmsbean : dmsToData) {
			bud.append("'" + dmsbean.getVin() + "',");
		}
		String vins = bud.toString();
		vins = vins.substring(0, vins.length() - 1);
		return vins;
	}

	/**
	 * 整理存在OB历史的数据
	 */
	public void arrangeSubCallCenter(String createDate) throws Exception {
		log.info("[整理存在OB历史的数据]开始");
//		List<DmsSubCallCenterBean> subList = dao.getSubCcDmsList(createDate);
//		dao.saveSubccDmsList(subList);
		dao.saveSubccDmsList(createDate);//20140522 xuw
		log.info("[整理存在OB历史的数据]结束");
	}

	/**
	 * 创建发送至Call Center的DMS反馈数据XML
	 * 
	 * @throws Exception
	 */
	public void createSubCCErrorXML(String d1)throws Exception {
		log.info("[创建发送至Call Center的DMS反馈数据XML]开始");
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		//String d1 = dao.getFedbackCurrentDay();
		//if (Integer.parseInt(date) >= Integer.parseInt(d1)) {
			boolean ba = dao.searchDmstoCCisXMl(d1);
			if (!ba) {
			    //ping.qi update 20141028
				//String dat1=getLastMonth();
				//String dat2=getLastOneMonth();
				List<DmsSubCallCenterBean> subList = dao.getSubCallList(d1);
				if (subList != null && subList.size() > 0) {
					log.info("[创建发送至Call Center的DMS反馈数据XML]开始创建文件");
					String path = xmlutil.LOCAL_SUBCC_MONTH_DIRECTORY + "\\"+d1;
					File f = new File(path);
					if (f.exists()) {
						File[] flt = f.listFiles();
						for (int i = 0; i < flt.length; i++) {
							flt[i].delete();
						}
					}else{
						f.mkdirs();
					}
					SaveDmsXmlService.createXmlDocument(DmsSubCallCenterBean.class,subList, "GBK", path, d1, Calendar.getInstance().getTimeInMillis()+ "");
					log.info("[创建发送至Call Center的DMS反馈数据XML]保存创建纪录");
					dao.saveDmstoCCisXMlInfo(d1);
					//更新DMS反馈的数据为已发送状态
					dao.updateOSendDay(d1);
				}else{
					log.info("[创建发送至Call Center的DMS反馈数据XML]未查询到之间的可发送数据。");
				}
			}else{
				log.info("[创建发送至Call Center的DMS反馈数据XML]检测到文件已创建");
			}
		/*}else{
			log.info("[创建发送至Call Center的DMS反馈数据XML]未达到可创建时间");
		}*/
		log.info("[创建发送至Call Center的DMS反馈数据XML]结束");
	}

	/**
	 * 创建发送至Call Center的DMS反馈数据XML
	 * @throws Exception
	 */
	public void createSubCCErrorXML2()throws Exception {
		log.info("[创建发送至Call Center的数据XML]开始");
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
				List<DmsSubCallCenterBean> subList = dao.getSubCallList2();
				if (subList != null && subList.size() > 0) {
					log.info("[创建发送至Call Center的DMS反馈数据XML]开始创建文件");
					String path = xmlutil.SUB_CALLCENTER_SH_PATH + "\\"+20150602;
					File f = new File(path);
					if (f.exists()) {
						File[] flt = f.listFiles();
						for (int i = 0; i < flt.length; i++) {
							flt[i].delete();
						}
					}else{
						f.mkdirs();
					}
					SaveDmsXmlService.createXmlDocument(DmsSubCallCenterBean.class,subList, "GBK", path, date, Calendar.getInstance().getTimeInMillis()+ "");
					log.info("[创建发送至Call Center的数据XML]保存创建纪录");
				}else{
					log.info("[创建发送至Call Center的数据XML]未查询到之间的可发送数据。");
				}
		log.info("[创建发送至Call Center的数据XML]结束");
	}

	/**
	 * 返回前2个月的26号
	 * 
	 * @param createDate
	 * @return
	 */
	private String getLastMonth()throws Exception {
		String lastDate =dao.getFedbackStartDay();
		return lastDate;
	}

	/**
	 * 返回本月的截止日
	 * 
	 * @param createDate
	 * @return
	 */
	private String getLastOneMonth()throws Exception {
		String lastDate =dao.getFedbackCurrentDay();
		return lastDate;
	}
}
