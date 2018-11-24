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
import org.springframework.util.FileCopyUtils;

import com.fc.bean.M2FToCcDataBean;
import com.fc.dao.db.businessDAO.GetCCPreSellDataDao;
import com.fc.util.FcUtil;
import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;
import com.fc.webClient.FordWebServiceClient;
import com.fc.webClient.FordWebServiceSoap;

/**
 * 获取售前数据业务处理
 * @author qp
 *
 */
public class PreSellCallCenterNewTask implements Job {
	private static Logger log = Logger.getLogger(PreSellCallCenterNewTask.class);
	private XmlSaveUtil xmlBean;
	private GetCCPreSellDataDao getccDao = new GetCCPreSellDataDao();

	public PreSellCallCenterNewTask() {
		this.xmlBean = new XmlSaveUtil();
		this.xmlBean.importDate=this.xmlBean.formaty.format(new Date());
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	    DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
	    getPreCallCenter(formaty.format(new Date()));
	}

	/**
	 * 接收CC售前数据方法类
	 */
	public void getPreCallCenter(String importDate) {
		log.info("[接收CC售前数据new task]定时任务业务开始" + logDate());
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
				log.info("[接收CC售前数据new task]出现异常，未能经通信获取远程数据", e);
			}
			if (returnNumber != 99 || returnError != 99) {
				if (returnNumber == 1) {
					log.info("[接收CC售前数据new task]" + text1
							+ " 密码验证通过，已检测到数据，开始执行数据处理" + logDate());
					try {
						log.info("[接收CC售前数据new task]" + text1
								+ " WebServices获取数据开始，调用returnWebCustCompData");
						compData = service.returnWebCustCompData(password);
						File f = saveFile(text1, compData, "Z");
						if (f != null) {
							fList.add(f);
						}
					} catch (Exception e) {
						log.error("[接收CC售前数据new task]"
												+ text1
												+ " WebServices获取数据出现异常，方法名：returnWebCustCompData",e);
					}
				} else {
					passMessage(returnError, text1);
				}
				if (returnError == 1) {
					log.info("[接收CC售前数据new task]" + text2
							+ " 密码验证通过，已检测到数据，开始执行数据处理" + logDate());
					try {
						log.info("[接收CC售前数据new task]" + text2
								+ " WebServices获取数据开始，调用returnWebCustErrData");
						compErorData = service.returnWebCustErrData(password);
						File f = saveFile(text2, compErorData, "N");
						if (f != null) {
							fList.add(f);
						}
					} catch (Exception e) {
						log.error("[接收CC售前数据new task]"
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
				    File f=new File("D:\\PYSYSTEM\\getPreSellCallCenter\\"+importDate);
				    File ftoPath=new File("D:\\PYSYSTEM\\getPreSellCallCenter\\"+importDate+"Bf");
				    if(!ftoPath.exists()){
					ftoPath.mkdirs();
				      }
				    
					if(f.exists()){
						File [] fl=f.listFiles();
						if(fl.length>0){
							List<File> fListM=new ArrayList<File>();
							for (int i = 0; i < fl.length; i++) {
								fListM.add(fl[i]);
								  File from=new File("D:\\PYSYSTEM\\getPreSellCallCenter\\"+importDate+"\\"+fl[i].getName());
								  File to=new File("D:\\PYSYSTEM\\getPreSellCallCenter\\"+importDate+"Bf\\"+fl[i].getName());
								FileCopyUtils.copy(from, to);//备份接收到的cc数据
							}
							 processCompData(fListM);
						}
					}
					String importDate1=xmlBean.formaty.format(new Date());
					if(FcUtil.getM2LOG()!=null){
						if(Integer.parseInt(importDate1)==Integer.parseInt(FcUtil.getM2LOG().getCREATE_DATE())){
							FcUtil.getM2LOG().setIS_GET_CALL_CENTER(1);
							getccDao.updateM2ServiceLog(FcUtil.getM2LOG());
						}else{
							FcUtil.setM2LOG(importDate1);
							FcUtil.getM2LOG().setIS_GET_CALL_CENTER(1);
							getccDao.updateM2ServiceLog(FcUtil.getM2LOG());
						}
					}else{
						FcUtil.setM2LOG(importDate1);
						FcUtil.getM2LOG().setIS_GET_CALL_CENTER(1);
						getccDao.updateM2ServiceLog(FcUtil.getM2LOG());
					}
				} catch (Exception e) {
					log.error("[接收CC售前数据]处理业务数据出现异常",e);
					/*File parentFile=null;
					for (File file : fList) {
						parentFile=file.getParentFile();
						file.delete();
					}
					if(parentFile!=null){
						log.info("[接收CC售前数据]不正常数据,清除原保存目录:"+parentFile.getAbsolutePath());
						if(parentFile.exists()){
							parentFile.delete();
						}
					}*/
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
		List<M2FToCcDataBean>  obtoDataListN=null;
		List<M2FToCcDataBean>  obtoDataListZ=null;
		for (File file : fileList) {
			if("N".equals(file.getName().substring(0,1))){
				kch++;
				obtoDataListN=(List<M2FToCcDataBean>) XMLString.parseXmlPreSellList(file,M2FToCcDataBean.class);
				if(obtoDataListN!=null && obtoDataListN.size()>0){
					for (M2FToCcDataBean M2 : obtoDataListN) {
						int number=getccDao.getPreSellCountById_New(M2.getData_uid());
						if(number>0){
							M2.setC_ext7("1");//setIsHistory 
							M2.setC_ext8(logDate());//当前时间
						}else{
							M2.setC_ext8(logDate());//当前时间
						}
					}
				}
				if(kch>0){
					if(obtoDataListN!=null && obtoDataListN.size()>0){
						getccDao.insertPreSellNew(obtoDataListN);
					}
					temp++;
				}
			}
			if("Z".equals(file.getName().substring(0,1))){
				zc++;
				obtoDataListZ=(List<M2FToCcDataBean>) XMLString.parseXmlPreSellList(file,M2FToCcDataBean.class);
				if(obtoDataListZ!=null && obtoDataListZ.size()>0){
					for (M2FToCcDataBean M2 : obtoDataListZ) {
						int number=getccDao.getPreSellCountById_New(M2.getData_uid());
						if(number>0){
							M2.setC_ext7("1");
							M2.setC_ext8(logDate());
						}else{
							M2.setC_ext8(logDate());
						}
					}
				}
				if(zc>0){
					if(obtoDataListZ!=null && obtoDataListZ.size()>0){
						getccDao.insertPreSellNew(obtoDataListZ);
					}
					temp++;
				}
				
			}
			
		}
		
		if(temp>0){
		    //1.把FC_M2F_CCTOFC_TEMP根据姓名，手机，购车时间，感兴趣车型，城市/经销商 不为空的数据，更新dup_error=1  
		    //2.把FC_M2F_CCTOFC_TEMP表数据dup_error为1的插入到fc_m2f_to_dms(注意dms的DATA_UID为它的seq，createDate为当前时间yyyyMMdd)表   
		    //3.把temp所有数据插入到总表FC_M2F_CCTOFC 4.清空temp表数据
		    // getccDao.updateFcM2fCctofcTemp();
		    getccDao.updateCctofcTemp();
		    log.info("[接收CC售前数据new task]处理业务数据完毕"+logDate());
		    
		    File parentFile=null;
			for (File file : fileList) {
				parentFile=file.getParentFile();
				file.delete();
			}
			if(parentFile!=null){
				log.info("[接收CC售前数据]完毕,清除原保存目录:"+parentFile.getAbsolutePath());
				if(parentFile.exists()){
					parentFile.delete();
				}
			}
	            log.info("[接收CC售前数据new task]清除文件完毕"+logDate()); 
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
				log.error("[接收CallCenter数据new task] " + text + " 保存数据至本机文件出现异常", e);
				throw e;
			}
		} else {
			log.info("[接收CC售前数据new task] " + text
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
