package com.fc.webService.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

import com.fc.bean.M2FToCcDataBean;
import com.fc.bean.M2FUserDataBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.FcUtil;
import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;
import com.fc.webService.IOdpToCcData;

/**
 * 售前数据接口 WebService FC >> CallCenter
 * 
 * @author lq
 * 
 */
public class OdpToCcDataImpl implements IOdpToCcData {
	private static Logger log = Logger.getLogger(OdpToCcDataImpl.class);
	private SaveDmsDataDao dao = new SaveDmsDataDao();
	private XmlSaveUtil util = new XmlSaveUtil();

	/**
	 * 下载售前数据文件
	 */
	public String[] downPreSellDataFile(String password) {
		log.info("[CC调用本机WebService售前数据XML new impl]开始：" + getLogDate());
		String importDate = util.formaty.format(new Date());
		String[] xmlList = null;
		if (password != null && !"".equals(password)) {
			if (XMLString.MD5("FC" + importDate).equals(password)) {
				log.info("[CC调用本机WebService售前数据XML new impl]载入售前数据开始：" + getLogDate());
				List<M2FToCcDataBean> preData = null;
				try {
					preData = dao.getPreSellDataByNewImpl(getDate());//获得数据
				} catch (Exception e) {
					log.error("[CC调用本机WebService售前数据XML new impl]查询售前数据出现异常！", e);
				}
				if (preData != null && preData.size() > 0) {
					log.info("[CC调用本机WebService售前数据XML]根据数据库数据，生成本机XML配置文件开始："
							+ getLogDate());
					String ml = util.SUB_CALLCENTER_PRE_PATH;
					ml = ml + "\\" + importDate;
					
					String mlNewPath = util.SUB_CALLCENTER_PRE_PATH;
					mlNewPath = mlNewPath + "\\" + importDate+"Bf";
					File fl = new File(ml);
					if (fl.isDirectory()) {
						if (fl.exists()) {
							File[] f = fl.listFiles();
							for (int i = 0; i < f.length; i++) {
								f[i].delete();
							}
						}
					}
					XMLString.createXMLPreSellDocument(M2FToCcDataBean.class,
							preData, "UTF-8", ml, ymds(), Calendar.getInstance()
									.getTimeInMillis()
									+ "");
					
					log.info("[CC调用本机WebService售前数据XML  new impl ]生成本机XML完成"
							+ getLogDate());
					//修改发送给cc数据的，发送时间sendDate
					try {
					    dao.updateSendDate(getDate());//更新fc_m2f_to_callcenter的sendDate
					} catch (Exception e1) {
					    // TODO Auto-generated catch block
					    e1.printStackTrace();
					}
					try {
					    
					    File fnewpath = new File(mlNewPath);
					    if(!fnewpath.exists()){
					      fnewpath.mkdirs();
					     }
					     File f = new File(ml);
						File[] files = f.listFiles();
						xmlList = new String[files.length];
						for (int i = 0; i < files.length; i++) {
							File fi = files[i];
							
							  File from = new File(ml+"\\"+fi.getName());
							  File to = new File(mlNewPath+"\\"+fi.getName());
							  FileCopyUtils.copy(from, to); //备份发送到cc的文件
							  
							StringBuilder bud = new StringBuilder();
							InputStreamReader isr = new InputStreamReader(
									new FileInputStream(fi), "UTF-8");
							BufferedReader in = new BufferedReader(isr);
							String str;
							while ((str = in.readLine()) != null) {
								bud.append(str);
							}
							in.close();
							xmlList[i] = bud.toString();
						}
						log
								.info("[CC调用本机WebService售前数据XML  new impl]文件读取完成，现将字符串返回至CallCenter"
										+ getLogDate());
						FcUtil.setM2LOG(importDate);//初始化全局日志
						FcUtil.getM2LOG().setIS_SUB_CALL_CENTER(1);
						dao.updateM2ServiceLog(FcUtil.getM2LOG());
					} catch (Exception e) {
						log
								.error(
										"[CC调用本机WebService售前数据XML new impl]读取数据文件产生字符串出现异常",
										e);
					}
					} else {
					log.info("[CC调用本机WebService售前数据XML  new impl]未查询到售前数据！"
							+ getLogDate());
				}
				} else {
				log.info("[CC调用本机WebService售前数据XML  new impl ]密码验证错误!:" + getLogDate());
			}
		} else {
			log.info("[CC调用本机WebService售前数据XML  new impl ]密码为空!:" + getLogDate());
		}
		return xmlList;
	}

	/**
	 * 校验售前数据是否存在
	 */
	public int isPreSellDataExist(String password) {
		log.info("[CC调用本机WebService售前数据校验判断 new impl]开始:" + getLogDate());
		if (password != null && !"".equals(password)) {
			if (XMLString.MD5("FC" + util.formaty.format(new Date())).equals(
					password)) {
			    
				int preSellNumber = 0;
				try {
				    //插入fctocc表数据  1.把回复表Y的数据插入到dms表 并且姓名，手机....为空 
				        dao.saveFcm2fTocc();
					preSellNumber = dao.getPreSellCountByNewImpl(getDate());
				} catch (Exception e) {
					log.error("[查询当天是否存在售前数据 new impl]出现异常", e);
				}
				if (preSellNumber > 0) {
					log.info("[CC调用本机WebService售前数据校验判断 new impl]已查询到当天存在售前数据！"
							+ getLogDate());
					return 1;
				} else {
					log.info("[CC调用本机WebService售前数据校验判断 new impl]当天没有可提供与CC的售前数据:"
							+ getLogDate());
					return 0;
				}
				} else {
				log.info("[CC调用本机WebService售前数据校验判断 new impl]密码验证错误!:" + getLogDate());
				return 2;
			}
		} else {
			log.info("[CC调用本机WebService售前数据校验判断 new impl]密码为空!:" + getLogDate());
			return 2;
		}
		
	}

	private String getLogDate() {
		return XmlSaveUtil.formatDate(new Date(), 1);
	}

	private String getDate() {
		DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
		return formaty.format(new Date());
	}

	/**
	 * 获取年月日时分秒
	 * 
	 * @return
	 */
	private String ymds() {
		DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");
		return formaty.format(new Date());
	}
	
	public static void main(String[] args) throws Exception {
	    //isPreSellDataExist("");
	    
	}
}
