package com.fc.ss.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.ss.bean.FcLeadsDataBean;
import com.fc.ss.dao.db.LeadsDataDao;
import com.fc.ss.util.CommonFunction;

public class LeadsDataService {
    private static Logger log = Logger.getLogger(LeadsDataService.class);
    private LeadsDataDao dao = new LeadsDataDao();
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
    
    private CommonFunction cf = new CommonFunction();
    
    public Boolean ProcessDataIntoDb(String filePath) throws IOException{
    	
    	Boolean processResult = null;
    	
    	BufferedReader bufferedReader = null;
    	InputStreamReader read = null;
    	
    	List<String> list = new LinkedList<String>(); 
    	
    	try {
            String encoding = "UTF8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
            	
                read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                
                while((lineTxt = bufferedReader.readLine()) != null){
                	list.add(lineTxt);
                }
                
                List<FcLeadsDataBean> datas = new ArrayList<FcLeadsDataBean>();
                
                int rowStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.leadsdata.row.start.no"));
                int colStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.leadsdata.col.start.no"));
                
                for(int i = rowStartIndex; i < list.size() - 1; i++){
                	
                	String[] values  = list.get(i).toString().split("\\|", -1);
                	
                	String localToDealerId = (values[(colStartIndex + 2)]+"").trim();
                	Boolean dataExists = dao.CheckDataExistsByLocalToDealerId(localToDealerId);
                	
                	if(!dataExists){
                		FcLeadsDataBean bean = new FcLeadsDataBean();
                		bean.setSellCreateDate((values[(colStartIndex + 0)]+"").trim());
                		bean.setSellDealerTime((values[(colStartIndex + 1)]+"").trim());
                		bean.setSellNo(localToDealerId);
                		bean.setSellStatus((values[(colStartIndex + 3)]+"").trim());
                		bean.setPhoneStatus((values[(colStartIndex + 4)]+"").trim());
                		bean.setIntentLevel((values[(colStartIndex + 5)]+"").trim());
                		bean.setIntentModels((values[(colStartIndex + 6)]+"").trim());
                		bean.setIsDiveOff((values[(colStartIndex + 7)]+"").trim());
                		bean.setNoDiveReason((values[(colStartIndex + 8)]+"").trim());
                		bean.setDistSellTime((values[(colStartIndex + 9)]+"").trim());
                		bean.setSellConsultant((values[(colStartIndex + 10)]+"").trim());
                		bean.setSellFollowTime((values[(colStartIndex + 11)]+"").trim());
                		bean.setWillingToShop((values[(colStartIndex + 12)]+"").trim());
                		bean.setWillingToDriving((values[(colStartIndex + 13)]+"").trim());
	                	datas.add(bean);
                	}
                }
                
                read.close();
                
                dao.InsertData(datas);
                
                processResult = true;
                
		    }else{
		    	processResult = false;
		        log.error("找不到指定的文件");
		    }
	    } catch (Exception e) {
	    	processResult = false;
	        log.error("读取文件内容出错");
	        e.printStackTrace();
	    }
    	finally{
    		if(read != null){
    			read.close();
    		}
    		if(bufferedReader != null){
    			bufferedReader.close();
    		}
    	}
    	
    	return processResult;
    }
}
