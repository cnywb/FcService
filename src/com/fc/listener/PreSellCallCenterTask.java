package com.fc.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.bean.M2FUserDataBean;
import com.fc.dao.db.businessDAO.GetCCPreSellDataDao;
import com.fc.util.FcUtil;
import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;
import com.fc.webClient.FordWebServiceClient;
import com.fc.webClient.FordWebServiceSoap;

/**
 * 获取售前数据业务处理
 * @author lq
 *
 */
public class PreSellCallCenterTask implements Job {
	private static Logger log = Logger.getLogger(CallCenterTask.class);
	private XmlSaveUtil xmlBean;
	private GetCCPreSellDataDao getccDao = new GetCCPreSellDataDao();

	public PreSellCallCenterTask() {
		this.xmlBean = new XmlSaveUtil();
		this.xmlBean.importDate=this.xmlBean.formaty.format(new Date());
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		getPreCallCenter();
	}

	/**
	 * 接收CC售前数据方法类
	 */
	public void getPreCallCenter() {
		log.info("[接收CC售前数据]定时任务业务开始" + logDate());
			int returnNumber = 99;
			int returnError = 99;
			String password = "";
			String compData = "";
			String compErorData = "";
			String text1 = "[处理正常数据]";
			String text2 = "[处理空错号数据]";
			List<File> fList = new ArrayList<File>();
			FordWebServiceClient client=null;
			FordWebServiceSoap service=null;
			try {
				 client = new FordWebServiceClient();
				 service = client.getFordWebServiceSoap();
				DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
				String mm = "FC" + formaty.format(new Date());
				password = XMLString.MD5(mm);
				returnNumber = service.isWebCustCompDataExist(password);
				returnError = service.isWebCustErrDataExist(password);
			} catch (Exception e) {
				log.info("[接收CC售前数据]出现异常，未能经通信获取远程数据", e);
			}
			if (returnNumber != 99 || returnError != 99) {
				if (returnNumber == 1) {
					log.info("[接收CC售前数据]" + text1
							+ " 密码验证通过，已检测到数据，开始执行数据处理" + logDate());
					try {
						log.info("[接收CC售前数据]" + text1
								+ " WebServices获取数据开始，调用returnWebCustCompData");
						compData = service.returnWebCustCompData(password);
						File f = saveFile(text1, compData, "Z");
						if (f != null) {
							fList.add(f);
						}
					} catch (Exception e) {
						log.error("[接收CC售前数据]"
												+ text1
												+ " WebServices获取数据出现异常，方法名：returnWebCustCompData",e);
					}
				} else {
					passMessage(returnError, text1);
				}
				if (returnError == 1) {
					log.info("[接收CC售前数据]" + text2
							+ " 密码验证通过，已检测到数据，开始执行数据处理" + logDate());
					try {
						log.info("[接收CC售前数据]" + text2
								+ " WebServices获取数据开始，调用returnWebCustErrData");
						compErorData = service.returnWebCustErrData(password);
						File f = saveFile(text2, compErorData, "N");
						if (f != null) {
							fList.add(f);
						}
					} catch (Exception e) {
						log.error("[接收CC售前数据]"
												+ text2
												+ " WebServices获取数据出现异常，方法名：returnWebCustErrData",e);
					}

				} else {
					passMessage(returnError, text2);
				}
			}

			if (returnNumber == 99) {
				passMessage(returnNumber, text1);
			}
			if (returnError == 99) {
				passMessage(returnError, text2);
			}
			if(fList.size()>0){
				try {
					processCompData(fList);
					String importDate=xmlBean.formaty.format(new Date());
					if(FcUtil.getM2LOG()!=null){
						if(Integer.parseInt(importDate)==Integer.parseInt(FcUtil.getM2LOG().getCREATE_DATE())){
							FcUtil.getM2LOG().setIS_GET_CALL_CENTER(1);
							getccDao.updateM2ServiceLog(FcUtil.getM2LOG());
						}else{
							FcUtil.setM2LOG(importDate);
							FcUtil.getM2LOG().setIS_GET_CALL_CENTER(1);
							getccDao.updateM2ServiceLog(FcUtil.getM2LOG());
						}
					}else{
						FcUtil.setM2LOG(importDate);
						FcUtil.getM2LOG().setIS_GET_CALL_CENTER(1);
						getccDao.updateM2ServiceLog(FcUtil.getM2LOG());
					}
				} catch (Exception e) {
					log.error("[接收CC售前数据]处理业务数据出现异常",e);
					File parentFile=null;
					for (File file : fList) {
						parentFile=file.getParentFile();
						file.delete();
					}
					if(parentFile!=null){
						log.info("[接收CC售前数据]不正常数据,清除原保存目录:"+parentFile.getAbsolutePath());
						if(parentFile.exists()){
							parentFile.delete();
						}
					}
				}
				
			}else{
				log.info("[接收CC售前数据] 未获取到保存在本机的售前数据 ");
			}
	}

	private void passMessage(int number, String text) {
		if (number == 0) {
			log.info("[接收CC售前数据]" + text + " 密码验证通过，未找到可用于接收的数据。"
					+ logDate());
		} else if (number == 2) {
			log.info("[接收CC售前数据]" + text + " 未通过密码验证，密码不正确 " + logDate());
		} else {
			log.info("[接收CC售前数据]" + text + " 未接收到校验返回值，返回参数为" + number);
		}
	}

	/**
	 * 处理正常数据
	 */
	private void processCompData(List<File> fileList) throws Exception {
		log.info("[接收CC售前数据]处理业务数据开始！");
		int kch=0;
		int zc=0;
		int temp=0;
		List<M2FUserDataBean>  obtoDataListN=null;
		List<M2FUserDataBean>  obtoDataListZ=null;
		for (File file : fileList) {
			if("N".equals(file.getName().substring(0,1))){
				kch++;
				obtoDataListN=(List<M2FUserDataBean>) XMLString.parseXmlPreSellList(file,M2FUserDataBean.class);
				if(obtoDataListN!=null && obtoDataListN.size()>0){
					for (M2FUserDataBean M2 : obtoDataListN) {
						int number=getccDao.getPreSellCountById(M2.getLocal_to_dealer_id());
						if(number>0){
							M2.setIsHistory(1);
							M2.setMemo7(logDate());
						}else{
							M2.setMemo7(logDate());
						}
					}
				}
			}
			if("Z".equals(file.getName().substring(0,1))){
				zc++;
				obtoDataListZ=(List<M2FUserDataBean>) XMLString.parseXmlPreSellList(file,M2FUserDataBean.class);
				if(obtoDataListZ!=null && obtoDataListZ.size()>0){
					for (M2FUserDataBean M2 : obtoDataListZ) {
						int number=getccDao.getPreSellCountById(M2.getLocal_to_dealer_id());
						if(number>0){
							M2.setIsHistory(1);
							M2.setMemo7(logDate());
						}else{
							M2.setMemo7(logDate());
						}
					}
				}
			}
		}
		if(zc>0){
			if(obtoDataListZ!=null && obtoDataListZ.size()>0){
				getccDao.insertPreSell(obtoDataListZ);
			}
			temp++;
		}
		if(kch>0){
			if(obtoDataListN!=null && obtoDataListN.size()>0){
				getccDao.insertPreSell(obtoDataListN);
			}
			temp++;
		}
		if(temp>0){
			log.info("[接收CC售前数据]处理业务数据完毕"+logDate());
		}
	}

	/**
	 * 将字符保存到本机器
	 * 
	 * @param text
	 * @param data
	 * @return
	 */
	private File saveFile(String text, String data, String FileName)
			throws Exception {
		log.info("[接收CC售前数据] " + text + " [保存字符到本机文件名目录] 开始");
		File fr = null;
		if (data != null && !"".equals(data)) {
			try {
				String directory = xmlBean.GET_CALLCENTER_PRE_PATH;
				directory = directory + "\\" + getMlDate();
				File c = new File(directory);
				if(!c.exists()){
					c.mkdirs();
				}
				directory=directory+"\\"+returnXMlName(FileName) + ".xml";
				File f=new File(directory);
				FileOutputStream out = new FileOutputStream(f);
				out.write(data.getBytes());
				out.close();
				fr=f;
			} catch (Exception e) {
				log.error("[接收CallCenter数据] " + text + " 保存数据至本机文件出现异常", e);
				throw e;
			}
		} else {
			log.info("[接收CC售前数据] " + text
					+ " [保存字符到本机文件名目录] 接收到的数据为NULL " + logDate());
		}
		return fr;
	}

	/**
	 * 返回XML文件名
	 * 
	 * @return
	 */
	private String returnXMlName(String XMLName) {
		return XMLName + Calendar.getInstance().getTimeInMillis();
	}

	private String getMlDate(){
		return XmlSaveUtil.formaty.format(new Date());
	}
	
	/**
	 * 返回日志时间
	 * 
	 * @return
	 */
	private String logDate() {
		return XmlSaveUtil.formatDate(new Date(), 1);
	}

}
