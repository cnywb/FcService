package com.fc.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.XmlSaveUtil;

public class GetDmsPreSellTask implements Job {
	private static Logger log = Logger.getLogger(GetDmsPreSellTask.class);
	private XmlSaveUtil xmlBean;
	private static SaveDmsDataDao dao = new SaveDmsDataDao();
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.xmlBean = new XmlSaveUtil();
		String importDate=xmlBean.formaty.format(new Date());
		getDmsPreSell(importDate);
	}
	
	public boolean getDmsPreSell(String importDate){
		log.info("[接收DMS售前反馈数据]定时任务业务开始" + logDate());
		boolean temp=false;
		try {
			String readPath_qk=xmlBean.GET_DMS_PRE_PATH+"\\CRMPOTCUSFY\\"+importDate;
			String readPath_xs=xmlBean.GET_DMS_PRE_PATH+"\\CRMTRACKFY\\"+importDate;
			File fMnul_qk=new File(readPath_qk);
			File fMnul_xs=new File(readPath_xs);
			findFile("销售线索",fMnul_xs,importDate,"CRMTRACKFY");
			findFile("潜客",fMnul_qk,importDate,"CRMPOTCUSFY");
		} catch (Exception e) {
			log.error("[接收DMS售前反馈数据]程序出现异常！",e);
		}
		log.info("[接收DMS售前反馈数据]定时任务业务结束" + logDate());
		return temp;
	}
	/**
	 * 读取文件相关类
	 */
	private void findFile(String title,File files,String importDate,String checkedStr) throws Exception{
		if(files.exists()){
			File[] dics = files.listFiles();
			if(dics.length>0){
				for (int i = 0; i < dics.length; i++) {
					if(dics[i].getName().indexOf(checkedStr)>-1){
						List<String[]> list=readFile(dics[i]);
						if(list.size()>0){
							String importFileNumber=filterStr(dics[i].getName());
							boolean bay=false;
							if("CRMTRACKFY".equals(checkedStr)){
								bay=dao.insertCrmtrackfy(list,importDate,importFileNumber);//插入销售线索数据
							}else if("CRMPOTCUSFY".equals(checkedStr)){
								bay=dao.insertCrmpotcusfy(list,importDate,importFileNumber);//插入潜客数据
							}
							if(bay){
								log.info("[接收DMS售前反馈数据]["+title+"]"+dics[i].getName()+"数据已获取并保存成功！");
							}else{
								log.info("[接收DMS售前反馈数据]["+title+"]"+dics[i].getName()+"保存失败！");
							}
						}else{
							log.info("[接收DMS售前反馈数据]["+title+"]文件名："+dics[i].getName()+" 没有销售线索数据！");
						}
					}
				}
			}else{
				log.info("[接收DMS售前反馈数据]["+title+"]目录："+files.getAbsolutePath()+" 下没有对应的文件！");
			}
		}else{
			log.info("[接收DMS售前反馈数据]["+title+"]目录不存在，无法继续本业务！");
		}
	}
	
	
	
	private List<String[]> readFile(File f)throws Exception{
		BufferedReader  read=new BufferedReader(new FileReader(f));
		List<String[]> list=new ArrayList<String[]>();
		String line;
		int i=0;
		int number=0;
		while((line=read.readLine())!=null){
			if(i==0){
				number=line.split("\\|").length;
			}else{
				String[] datas=line.split("\\|");
				if(datas.length==number){
					list.add(datas);
				}else{
					try {
						String a=datas[number-1];
					} catch (Exception e) {
						log.info("[接收DMS售前反馈数据]文件："+f.getAbsolutePath()+" 读取失败，文件中第 "+i+" 行数据，列的元素数量不一致！");
						throw e;
					}
					
				}
			}
			i++;
		}
		return list;
	}
	private String filterStr(String fileName){
		fileName=fileName.toUpperCase();
		fileName=fileName.replaceAll("CRMTRACKFY","");
		fileName=fileName.replaceAll(".TXT","");
		fileName=fileName.trim();
		return fileName;
	}
	
	/**
	 * 返回日志时间
	 * 
	 * @return
	 */
	private String logDate() {
		return XmlSaveUtil.formatDate(new Date(), 1);
	}
	
	public static void main(String[] args) throws Exception{
		GetDmsPreSellTask g = new GetDmsPreSellTask();
		File fMnul_qk=new File("c:\\xuw\\20160309");
		g.findFile("潜客",fMnul_qk,"20160309","CRMPOTCUSFY");
	}
}
