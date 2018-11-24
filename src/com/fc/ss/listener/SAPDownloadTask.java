package com.fc.ss.listener;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sun.net.ftp.FtpProtocolException;

import com.fc.bean.DmsToLogBean;
import com.fc.ss.service.AfterOBService;
import com.fc.ss.service.DataToDMSService;
import com.fc.ss.service.PotentialCustomerDataService;
import com.fc.ss.service.LeadsDataService;
import com.fc.ss.util.CommonFunction;
import com.fc.ss.util.RemoteFTP;

import com.fc.util.PropUtils;
import com.fc.util.XmlSaveUtil;

public class SAPDownloadTask implements Job {
	private static Logger log = Logger.getLogger(SAPDownloadTask.class);
	public static Map<String, DmsToLogBean> toLogBeanMap = new HashMap<String, DmsToLogBean>();
	private static CommonFunction cf = new CommonFunction();
	
	public SAPDownloadTask() {
		
	}

	public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			log.info("[SAPDownload定时任务]处理流程开始"+ XmlSaveUtil.formatDate(new Date(), 1));
			//GetFilesFromRemoteServer();
			GetFilesFromLocalServer();
			log.info("[SAPDownload定时任务]处理流程结束"+ XmlSaveUtil.formatDate(new Date(), 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("[SAPDownload定时任务]处理流程失败", e);
			e.printStackTrace();
		}
	}
	
public static void GetFilesFromLocalServer() throws Exception{
		
		AfterOBService afterOBService = new AfterOBService();
		DataToDMSService dataToDMSService = new DataToDMSService();
		LeadsDataService leadsDataService = new LeadsDataService();
		PotentialCustomerDataService potentialCustomerDataService = new PotentialCustomerDataService();
		
		String ftpLocalPath = cf.GetPropertiesValue("sap.ftp.download.local.path");
		ftpLocalPath = ftpLocalPath + format.format(new Date()) + "\\\\";
		String ftpLocalBackupPath = ftpLocalPath + "BACKUP" + "\\\\";
		String ftpLocalErrorPath = ftpLocalPath + "ERROR" + "\\\\";
		
		File ftpLocalPathFile = new File(ftpLocalPath);
	    if (!ftpLocalPathFile.exists()) {
	    	ftpLocalPathFile.mkdirs();
	    }
		
		File ftpLocalBackupPathFile = new File(ftpLocalBackupPath);
	    if (!ftpLocalBackupPathFile.exists()) {
	    	ftpLocalBackupPathFile.mkdirs();
	    }
		    
	    File ftpLocalErrorPathFile = new File(ftpLocalErrorPath);// 获得文件
	    if (!ftpLocalErrorPathFile.exists()) {
	    	ftpLocalErrorPathFile.mkdirs();
	    }
		
	    List<File> fileList = cf.GetFileList(ftpLocalPath, false, "txt"); 
	    
		Boolean processFileResult = false;
		
		for (File file : fileList)
        {
			String fileFullName = file.getAbsolutePath();
			try  
            {  
				if(fileFullName.toUpperCase().indexOf("AFTEROB") > 0){
					log.info("[SAPDownload定时任务]处理AFTEROB文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
					processFileResult = afterOBService.ProcessDataIntoDb(fileFullName);
					log.info("[SAPDownload定时任务]处理AFTEROB文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
				}
				else if(fileFullName.toUpperCase().indexOf("DISTRIBUTION") > 0){
					log.info("[SAPDownload定时任务]处理DISTRIBUTION文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
					processFileResult = dataToDMSService.ProcessDataIntoDb(fileFullName);
					log.info("[SAPDownload定时任务]处理DISTRIBUTION文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
				}
				else if(fileFullName.toUpperCase().indexOf("LEADFEEDBACK") > 0){
					log.info("[SAPDownload定时任务]处理LEADFEEDBACK文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
					processFileResult = leadsDataService.ProcessDataIntoDb(fileFullName);
					log.info("[SAPDownload定时任务]处理LEADFEEDBACK文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
				}
				else if(fileFullName.toUpperCase().indexOf("POTCUSTOMER") > 0){
					log.info("[SAPDownload定时任务]处理POTCUSTOMER文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
					processFileResult = potentialCustomerDataService.ProcessDataIntoDb(fileFullName);
					log.info("[SAPDownload定时任务]处理POTCUSTOMER文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + fileFullName);
				}

				if(processFileResult){
					log.info("[SAPDownload定时任务]处理文件成功："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					cf.MoveFile(fileFullName, ftpLocalBackupPath);
				}else{
					log.info("[SAPDownload定时任务]处理文件失败："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					cf.MoveFile(fileFullName, ftpLocalErrorPath);
				}
				
            } catch (Exception e)  
            {  
            	cf.MoveFile(fileFullName, ftpLocalErrorPath);
                e.printStackTrace();  
            }                 
        }
	}
	
	public static void GetFilesFromRemoteServer() throws Exception{
		
		AfterOBService afterOBService = new AfterOBService();
		DataToDMSService dataToDMSService = new DataToDMSService();
		LeadsDataService leadsDataService = new LeadsDataService();
		PotentialCustomerDataService potentialCustomerDataService = new PotentialCustomerDataService();
		
		String ftpIp = cf.GetPropertiesValue("sap.ftp.ip");
		Integer ftpPort = Integer.parseInt(cf.GetPropertiesValue("sap.ftp.port"));
		String ftpUser = cf.GetPropertiesValue("sap.ftp.user");
		String ftpPwd = cf.GetPropertiesValue("sap.ftp.pwd");
		String ftpDownloadPath = cf.GetPropertiesValue("sap.ftp.download.remote.path");
		String ftpLocalPath = cf.GetPropertiesValue("sap.ftp.download.local.path");
		ftpLocalPath = ftpLocalPath + format.format(new Date()) + "\\\\";
		String ftpLocalBackupPath = ftpLocalPath + "BACKUP" + "\\\\";
		String ftpLocalErrorPath = ftpLocalPath + "ERROR" + "\\\\";
		
		File ftpLocalPathFile = new File(ftpLocalPath);
	    if (!ftpLocalPathFile.exists()) {
	    	ftpLocalPathFile.mkdirs();
	    }
		
		File ftpLocalBackupPathFile = new File(ftpLocalBackupPath);
	    if (!ftpLocalBackupPathFile.exists()) {
	    	ftpLocalBackupPathFile.mkdirs();
	    }
		    
	    File ftpLocalErrorPathFile = new File(ftpLocalErrorPath);// 获得文件
	    if (!ftpLocalErrorPathFile.exists()) {
	    	ftpLocalErrorPathFile.mkdirs();
	    }
		
		RemoteFTP remoteFtp = new RemoteFTP(ftpIp, ftpPort, ftpUser, ftpPwd);
		log.info("[SAPDownload定时任务]获取远程文件开始"+ XmlSaveUtil.formatDate(new Date(), 1));
		List<String> downloadResult = remoteFtp.DownloadFileFromRemoteFTP(ftpDownloadPath, ftpLocalPath);
		log.info("[SAPDownload定时任务]获取远程文件结束:"+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件个数：" + downloadResult.size());
		Boolean processFileResult = false;
		String remoteFileName = "";
		String remoteErrorPath = cf.GetPropertiesValue("sap.ftp.download.remote.error.path");
		String remoteBackupPath = cf.GetPropertiesValue("sap.ftp.download.remote.backup.path");
		
		for (String file : downloadResult)
        {
			
			try  
            {  
				if(file.toUpperCase().indexOf("AFTEROB") > 0){
					log.info("[SAPDownload定时任务]处理AFTEROB文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					processFileResult = afterOBService.ProcessDataIntoDb(file);
					log.info("[SAPDownload定时任务]处理AFTEROB文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
				}
				else if(file.toUpperCase().indexOf("DISTRIBUTION") > 0){
					log.info("[SAPDownload定时任务]处理DISTRIBUTION文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					processFileResult = dataToDMSService.ProcessDataIntoDb(file);
					log.info("[SAPDownload定时任务]处理DISTRIBUTION文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
				}
				else if(file.toUpperCase().indexOf("LEADFEEDBACK") > 0){
					log.info("[SAPDownload定时任务]处理LEADFEEDBACK文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					processFileResult = leadsDataService.ProcessDataIntoDb(file);
					log.info("[SAPDownload定时任务]处理LEADFEEDBACK文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
				}
				else if(file.toUpperCase().indexOf("POTCUSTOMER") > 0){
					log.info("[SAPDownload定时任务]处理POTCUSTOMER文件开始："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					processFileResult = potentialCustomerDataService.ProcessDataIntoDb(file);
					log.info("[SAPDownload定时任务]处理POTCUSTOMER文件结束："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
				}
				
				String remoteFileFullPath = file.trim();
		        String remoteFileFullPathArray[] = remoteFileFullPath.split("\\\\");
		        remoteFileName = remoteFileFullPathArray[remoteFileFullPathArray.length-1];  

				if(processFileResult){
					log.info("[SAPDownload定时任务]处理文件成功："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					remoteFtp.UploadFileToRemoteFTP(file, remoteFileName, remoteBackupPath);
					cf.MoveFile(file, ftpLocalBackupPath);
				}else{
					log.info("[SAPDownload定时任务]处理文件失败："+ XmlSaveUtil.formatDate(new Date(), 1) + "; 文件名：" + file);
					remoteFtp.UploadFileToRemoteFTP(file, remoteFileName, remoteErrorPath);
					cf.MoveFile(file, ftpLocalErrorPath);
				}
				
            } catch (Exception e)  
            {  
            	log.error("[SAPDownload定时任务]处理文件失败", e);
            	cf.MoveFile(file, ftpLocalErrorPath);
                e.printStackTrace();  
            }                 
        }
	}
	
}
