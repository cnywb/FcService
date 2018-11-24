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

import com.fc.bean.DmsObToData;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.dao.db.businessDAO.GetCallCenterDataDao;
import com.fc.util.FcUtil;
import com.fc.util.PropUtils;
import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;
import com.fc.webClient.FordWebServiceClient;
import com.fc.webClient.FordWebServiceSoap;

public class CallCenterTask implements Job {
	private static Logger log = Logger.getLogger(CallCenterTask.class);
	private XmlSaveUtil xmlBean;
	private GetCallCenterDataDao getccDao = new GetCallCenterDataDao();

	public CallCenterTask() {
		this.xmlBean = new XmlSaveUtil();
		this.xmlBean.importDate=this.xmlBean.formaty.format(new Date());
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	        DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
		getCallCenterData(formaty.format(new Date()));
	}

	/**
	 * 接收CallCenter数据方法类
	 */
	public void getCallCenterData(String importDate) {
		log.info("[接收CallCenter数据]业务开始importDate:" +importDate+";logDate():" + logDate());
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
				PropUtils p = new PropUtils();//20160126 xuw
				p.loadFile("ftpConn.properties");//20160126 xuw
				client = new FordWebServiceClient(p.getValue("local.WebService.ODPURL"));//20160126 xuw
				
				service = client.getFordWebServiceSoap();
				String mm = "FC" + importDate;
				password = XMLString.MD5(mm);
				returnNumber = service.isODPCustCompDataExist(password);
				returnError = service.isODPCustErrDataExist(password);
			} catch (Exception e) {
				log.info("[接收CallCenter数据]出现异常，未能经通信获取远程数据", e);
			}
			if (returnNumber != 99 || returnError != 99) {
				if (returnNumber == 1) {
					log.info("[接收CallCenter数据]" + text1
							+ " 密码验证通过，已检测到数据，开始执行数据处理importDate:" +importDate+";logDate():" + logDate());
					try {
						log.info("[接收CallCenter数据]" + text1
								+ " WebServices获取数据开始，调用returnODPCustCompData");
						compData = service.returnODPCustCompData(password);
						File f = saveFile(text1, compData, "Z");
						if (f != null) {
							fList.add(f);
						}
					} catch (Exception e) {
						log.error("[接收CallCenter数据]"
												+ text1
												+ " WebServices获取数据出现异常，方法名：returnODPCustCompData",e);
					}
				} else {
					passMessage(returnError, text1,importDate);
				}
				if (returnError == 1) {
					log.info("[接收CallCenter数据]" + text2
							+ " 密码验证通过，已检测到数据，开始执行数据处理importDate:" +importDate+";logDate():" + logDate());
					try {
						log.info("[接收CallCenter数据]" + text2
								+ " WebServices获取数据开始，调用returnODPCustErrData");
						compErorData = service.returnODPCustErrData(password);
						File f = saveFile(text2, compErorData, "N");
						if (f != null) {
							fList.add(f);
						}
					} catch (Exception e) {
						log.error("[接收CallCenter数据]"
												+ text2
												+ " WebServices获取数据出现异常，方法名：returnODPCustErrData",e);
					}

				} else {
					passMessage(returnError, text2,importDate);
				}
			}

			if (returnNumber == 99) {
				passMessage(returnNumber, text1,importDate);
			}
			if (returnError == 99) {
				passMessage(returnError, text2,importDate);
			}
			if(fList.size()>0){
				try {
					//File f=new File("D:\\PYSYSTEM\\getCallCenterToXML\\"+getMlDate());
				    File f=new File("D:\\PYSYSTEM\\getCallCenterToXML\\"+importDate);
					if(f.exists()){
						File [] fl=f.listFiles();
						if(fl.length>0){
							List<File> fListM=new ArrayList<File>();
							for (int i = 0; i < fl.length; i++) {
								fListM.add(fl[i]);
							}
							processCompData(fListM,importDate);
						}
					}
				} catch (Exception e) {
					log.error("[接收CallCenter数据]处理业务数据出现异常",e);
					log.info("[接收CallCenter数据]不正常数据 ");
					/*File parentFile=null;
					for (File file : fList) {
						parentFile=file.getParentFile();
						file.delete();
					}
					if(parentFile!=null){
						log.info("[接收CallCenter数据]不正常数据,清除原保存目录:"+parentFile.getAbsolutePath());
						parentFile.delete();
					}*/
				}
				
			}else{
				log.info("[接收CallCenter数据] 未获取到保存在本机的CallCenter拨打数据 ");
			}
	}

	private void passMessage(int number, String text,String importDate) {
		if (number == 0) {
			log.info("[接收CallCenter数据]" + text + " 密码验证通过，未找到可用于接收的数据。importDate:" +importDate+";logDate():" + logDate());
		} else if (number == 2) {
			log.info("[接收CallCenter数据]" + text + " 未通过密码验证，密码不正确importDate:" +importDate+";logDate():" + logDate());
		} else {
			log.info("[接收CallCenter数据]" + text + " 未接收到校验返回值，返回参数为importDate:" +importDate+";logDate():" + logDate()+";number:"+ number);
		}
	}

	public static void main(String[] args) {
		try {
			List<File> flist=new ArrayList<File>();
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z2.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z3.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z4.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z5.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z6.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z7.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z8.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z9.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z10.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z11.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z12.xml"));
			flist.add(new File("C:\\xuw\\odptxtxml\\xml\\Z13.xml"));
			
			//flist.add(new File("C:\\xuw\\odptxtxml\\Z1453795801689.xml"));
			CallCenterTask c=new CallCenterTask();
			c.processCompData(flist,"20160128");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 处理正常数据
	 */
	public void processCompData(List<File> fileList,String importDate) throws Exception {
		log.info("[接收CallCenter数据]处理业务数据开始！");
		int kch=0;
		int zc=0;
		int temp=0;
		List<DmsObToData>  obtoDataListN=null;
		List<DmsObToData>  obtoDataListZ=null;
		for (File file : fileList) {
			if("N".equals(file.getName().substring(0,1))){
				kch++;
				 log.info("=========="+file.getName());
				obtoDataListN=(List<DmsObToData>) XMLString.parseXmlList(file,DmsObToData.class);
				if(obtoDataListN!=null && obtoDataListN.size()>0){
					for (DmsObToData ob : obtoDataListN) {
						if("owner".equals(ob.getOB_TYPE())){
							ob.setOwnerObstatus("2");
						}else{
							if(!"2".equals(ob.getOwnerObstatus())){
								ob.setContactObstatus("2");
							}
						}
//						int number=getccDao.selectCallCenterDataCount(ob.getVin(), ob.getSubDate());
//						if(number>0){
//							ob.setIsHistory(1);
//						}
					}
				}
				if(kch>0){
					if(obtoDataListN!=null && obtoDataListN.size()>0){
						getccDao.insertCallCenterToData(obtoDataListN);
					}
					if (FcUtil.getFcLOG() != null) {
						if (FcUtil.getFcLOG().getIsCCError() == 0) {
							FcUtil.getFcLOG().setIsCCError(1L);
							getccDao.updateFcServiceLog(FcUtil.getFcLOG());
						}
					}
					temp++;
				}
			}
			if("Z".equals(file.getName().substring(0,1))){
			    log.info("=========="+file.getName());
				zc++;
				obtoDataListZ=(List<DmsObToData>) XMLString.parseXmlList(file,DmsObToData.class);
//				if(obtoDataListZ!=null && obtoDataListZ.size()>0){
//					for (DmsObToData ob : obtoDataListZ) {
//						int number=getccDao.selectCallCenterDataCount(ob.getVin(), ob.getSubDate());
//						if(number>0){
//							ob.setIsHistory(1);
//						}
//					}
//				}
				if(zc>0){
					if(obtoDataListZ!=null && obtoDataListZ.size()>0){
						getccDao.insertCallCenterToData(obtoDataListZ);
					}
					if (FcUtil.getFcLOG() != null) {
						if (FcUtil.getFcLOG().getIsZeitCC() == 0) {
							FcUtil.getFcLOG().setIsZeitCC(1L);
							getccDao.updateFcServiceLog(FcUtil.getFcLOG());
						}
					}
					temp++;
				}
			}
		}
		
		
		//DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
		//String createDate=formaty.format(new Date());
		try {
			getccDao.updateObToData(importDate);
		} catch (Exception e) {
			log.info("[更新OB后数据将城市及性别转换为代码]出现异常!");
			e.printStackTrace();
		}
		if(temp>0){
			log.info("[接收CallCenter数据]处理业务数据完毕"+logDate());
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
		log.info("[接收CallCenter数据] " + text + " [保存字符到本机文件名目录] 开始");
		File fr = null;
		if (data != null && !"".equals(data)) {
			try {
				String directory = xmlBean.GET_CALLCENTER_SH_PATH;
				directory = directory + "\\" + getMlDate();
				File c = new File(directory);
				if(!c.exists()){
					c.mkdirs();
				}
				directory=directory+"\\"+returnXMlName(FileName) + ".xml";
				File f=new File(directory);
				FileOutputStream out = new FileOutputStream(f);
				out.write(data.getBytes("GBK"));
				out.close();
				fr=f;
			} catch (Exception e) {
				log.error("[接收CallCenter数据] " + text + " 保存数据至本机文件出现异常", e);
				throw e;
			}
		} else {
			log.info("[接收CallCenter数据] " + text
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
