package com.fc.webService.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.FcUtil;
import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;
import com.fc.webService.IAftermarket;

/**
 * WebService FC >> CallCenter
 * 
 * @author lq
 * 
 */
public class AftermarketImpl implements IAftermarket {
	private static Logger log = Logger.getLogger(AftermarketImpl.class);
	private SaveDmsDataDao dmsdao=new SaveDmsDataDao();
	private XmlSaveUtil util = new XmlSaveUtil();

	/**
	 * CallCenter调用该webService接口获取数据
	 */
	public String[] downFcDataFile(String password) {
		log.info("[CallCenter调用本机Webservice获取数据]开始:"
				+ XmlSaveUtil.formatDate(new Date(), 1));
		List<String> xmlList=new ArrayList<String>();
		String importDate = util.formaty.format(new Date());
		if (password != null && !"".equals(password)) {
			if (XMLString.MD5("FC" + importDate).equals(password)) {
				if(endStopDate()){
					addFileDmsToCCStr(xmlList);
				}else{
					addFileStr(xmlList,importDate);
				}
			} else {
				log.info("[CallCenter调用本机Webservice获取数据]密码验证错误!:"
						+ XmlSaveUtil.formatDate(new Date(), 1));
			}
		} else {
			log.info("[CallCenter调用本机Webservice获取数据]密码为空!:"
					+ XmlSaveUtil.formatDate(new Date(), 1));
		}
		if(xmlList.size()>0){
			String[] xmls=new String[xmlList.size()];
			for (int i = 0; i < xmls.length; i++) {
				xmls[i]=xmlList.get(i);
			}
			return xmls;
		}else{
			return null;
		}
	}
	
	/**
	 * 加入Fc 发送至Call Center可拨打数据
	 * @param xmlList
	 * @param importDate
	 */
	private void addFileStr(List<String> xmlList,String importDate){
		String filePath = util.SUB_CALLCENTER_SH_PATH + "\\" +importDate+ "\\";
		File file = new File(filePath);
		if (!file.exists()) {
			log.info("[CallCenter调用本机Webservice获取数据]本机获取目录错误，需检查目录："
					+ XmlSaveUtil.formatDate(new Date(), 1));
		} else {
			File[] files = file.listFiles();
			if (files.length > 0) {
				try {
					for (int i = 0; i < files.length; i++) {
						File f = files[i];
						xmlList.add(fileStr(f));
					}
					if (FcUtil.getFcLOG() != null) {
						if (FcUtil.getFcLOG().getIsCCGet() == 0) {
							FcUtil.getFcLOG().setIsCCGet(1L);
							dmsdao.updateFcServiceLog(FcUtil.getFcLOG());
						}
					}
					log.info("[CallCenter调用本机Webservice获取数据]数据读取成功，已返回至CallCenter:"
									+ XmlSaveUtil.formatDate(new Date(), 1));
				} catch (Exception e) {
					log.error("[CallCenter调用本机Webservice获取数据]读取数据文件出现异常!", e);
				}
			} else {
				log.info("[CallCenter调用本机Webservice获取数据]目录中没有已生成的数据文件:"
						+ XmlSaveUtil.formatDate(new Date(), 1));
			}
		}
	}
	/**
	 * 加入 DMS回馈至Call Center数据
	 * @param xmlList
	 */
	private void addFileDmsToCCStr(List<String> xmlList){
		try {
			String d1 = currentDay();
			String d2 = currentMonth();
			if (Integer.parseInt(d1) >= Integer.parseInt(d2)) {
				boolean isget = dmsdao.searchIsCCGet(d2);
				if (isget) {
					log.info("[CallCenter调用本机Webservice获取数据]检测到数据表中存在DMS反馈的数据："
							+ XmlSaveUtil.formatDate(new Date(), 1));
					String filePath = util.LOCAL_SUBCC_MONTH_DIRECTORY + "\\"
							+ d2 + "\\";
					File file = new File(filePath);
					if (!file.exists()) {
						log.info("[CallCenter调用本机Webservice获取数据]DMS反馈的数据在目录中未找到，目录："+filePath +XmlSaveUtil.formatDate(new Date(), 1));
					}else{
						File[] files = file.listFiles();
						if (files.length > 0) {
							for (int i = 0; i < files.length; i++) {
								File f = files[i];
								xmlList.add(fileStr(f));
							}
							dmsdao.updateDmsToCC(d2);
						}else{
							log.info("[CallCenter调用本机Webservice获取数据]DMS反馈的数据在目录中没有文件，目录："+filePath +XmlSaveUtil.formatDate(new Date(), 1));
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("[加入 DMS回馈至Call Center数据]出现异常", e);
		}
	}
	
	
	
	private String fileStr(File f)throws Exception{
		StringBuilder bud = new StringBuilder();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "GBK");
		BufferedReader in = new BufferedReader(isr);
		String str;
		while ((str = in.readLine()) != null) {
			bud.append(str);
		}
		in.close();
		return bud.toString();
	}
	private String currentMonth()throws Exception{
		String d1=dmsdao.getFedbackCurrentDay();
		return d1;
	}
	private String currentDay(){
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		return date;
	}
	/**
	 * 反馈截止日期
	 * @return
	 * @throws Exception
	 */
	private boolean endStopDate(){
		boolean fany=false;
		try {
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String starDate=format.format(new Date());
			String endDate=dmsdao.getFedbackCurrentDay()+"100000";
			if(Long.parseLong(starDate)>=Long.parseLong(endDate)){
				String d2 = currentMonth();
				boolean isget = dmsdao.searchIsCCGet(d2);
				if (isget) {
					fany=true;
				}
			}
		} catch (Exception e) {
			log.error("[CallCenter调用本机Webservice获取数据]判断反馈截止日期出现异常",e);
		}
		return fany;
	}
	
	
	
	/**
	 * 校验当前是否存在可调用的数据
	 * 
	 * @param password
	 * @return
	 */
	public int isDataExist(String password) {
		log.info("[CallCenter调用本机Webservice数据判断]开始:"
				+ XmlSaveUtil.formatDate(new Date(), 1));
		if (password != null && !"".equals(password)) {
			if (XMLString.MD5("FC" + util.formaty.format(new Date())).equals(password)) {
				String filePath = util.SUB_CALLCENTER_SH_PATH
						+ "\\" + util.formaty.format(new Date()) + "\\";
				File file = new File(filePath);
				if (!file.isDirectory()) {
					log.info("[CallCenter调用本机Webservice数据判断]没有对应的目录 "+filePath
							+ XmlSaveUtil.formatDate(new Date(), 1));
					return 0;
				}else{
					File[] files = file.listFiles();
					if (files.length > 0) {
						return 1;
					}else{
						log.info("[CallCenter调用本机Webservice数据判断]目录中没有已生成的数据文件:"
								+ XmlSaveUtil.formatDate(new Date(), 1));
						return 0;
					}
				}
			} else {
				log.info("[CallCenter调用本机Webservice数据判断]密码验证错误!:"
						+ XmlSaveUtil.formatDate(new Date(), 1));
				return 2;
			}
		} else {
			log.info("[CallCenter调用本机Webservice数据判断]密码为空!:"
					+ XmlSaveUtil.formatDate(new Date(), 1));
			return 2;
		}
	}

}