package com.fc.ss.listener;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.ss.util.CommonFunction;

public class SAPToFcTask implements Job {
	
	private static Logger log = Logger.getLogger(SAPToFcTask.class);
	private static CommonFunction cf = new CommonFunction();
	
	public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("SAPToFc任务开始执行!");
		String ftpLocalPath = cf.GetSapToFcErrorFileMove("sap.fc.local.path");
		
		if(format.format(new Date()).equals("20171012")){
			log.info("执行一次大清洗");
			historyExecuteDate(ftpLocalPath);
		}
		
		ftpLocalPath = ftpLocalPath + format.format(new Date()) + "\\\\";
		String ftpLocalErrorPath = ftpLocalPath + "ERROR" + "\\\\";
		
		
		moveFile(ftpLocalErrorPath,ftpLocalPath);
		log.info("SAPToFc执行成功!");
	}
	
	private void historyExecuteDate(String ftpLocalPath){
		try {
			File files1;
			File files2;
			String urls="";
			String ftpLocalPaths=ftpLocalPath+format.format(new Date())+"\\\\BACKUP\\\\";
			List<String> dateList=historyDate();
			for(String url:dateList){
				urls=ftpLocalPath+url+"\\\\"; //ERROR\\\\
				files1=new File(urls);
				files2=new File(ftpLocalPath);
				if(files1.exists() && files2.exists()){
					log.info("正在清理"+urls+"文件夹下的内容!@@@@@@@@@@@@@@@@@@@@@@@@@");
					moveFile(urls,ftpLocalPaths);
				}else{
					log.info(urls+"或"+ftpLocalPaths+"文件夹不存在!");
				}
			}
			log.info("清洗完成!");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void moveFile(String ftpLocalErrorPath,String ftpLocalPath){
		File ftpLocalErrorPathFile = new File(ftpLocalErrorPath);// 获得文件
		File [] fileList=ftpLocalErrorPathFile.listFiles();
		
		if(fileList.length>0){
			log.info("ERROR目录下有文件,正在将文件移出!");
			for(int i=0;i<fileList.length;i++){
				if(fileList[i].isFile()){
					fileList[i].renameTo(new File(ftpLocalPath+fileList[i].getName()));
				}
			}
			log.info("ERROR目录文件已移除!");
		}
	}
	
	private List<String> historyDate() throws ParseException{
		Calendar startCal=Calendar.getInstance();
		Calendar endCal=Calendar.getInstance();
		
		String startDate="20170101";
		String endDate=format.format(new Date());
		
		List<String> lDate=new ArrayList<String>();
		lDate.add(startDate);

		startCal.setTime(format.parse(startDate));
		endCal.setTime(format.parse(endDate));
		
		while(endCal.after(startCal)){
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(format.format(startCal.getTime()));
		}
		return lDate;
	}
}
