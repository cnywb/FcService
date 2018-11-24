package com.fc.ss.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.ss.bean.FcDataToDMSBean;
import com.fc.ss.dao.db.DataToDMSDao;
import com.fc.ss.util.CommonFunction;

public class DataToDMSService {
	
    private static Logger log = Logger.getLogger(DataToDMSService.class);
    private DataToDMSDao dataToDmsDao = new DataToDMSDao();
    private CommonFunction cf = new CommonFunction();
    
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
    
    public Boolean ProcessDataIntoDb(String filePath) throws IOException{
    	
    	Boolean processResult = null;
    	
    	BufferedReader bufferedReader = null;
    	InputStreamReader read = null;
    	
    	List<String> list = new LinkedList<String>(); 
    	
    	try {
            String encoding = "UTF8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
            	
                read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                
                while((lineTxt = bufferedReader.readLine()) != null){
                	list.add(lineTxt);
                }
                
                List<FcDataToDMSBean> datas = new ArrayList<FcDataToDMSBean>();
                
                int rowStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.distribution.row.start.no"));
                int colStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.distribution.col.start.no"));
                
                for(int i = rowStartIndex; i < list.size() - 1; i++){
                	
                	String[] values = list.get(i).toString().split("\\|", -1);
                	
                	String localToDealerId = (values[(colStartIndex + 11)]+"").trim();
                	Boolean dataExists = dataToDmsDao.CheckDataExistsByLocalToDealerId(localToDealerId);
                	
                	if(!dataExists){
                		FcDataToDMSBean bean = new FcDataToDMSBean();
                		bean.setDataDistDate((values[(colStartIndex + 0)]+"").trim());
                		bean.setName((values[(colStartIndex + 1)]+"").trim());
                		bean.setSex((values[(colStartIndex + 2)]+"").trim());
                		bean.setMobile((values[(colStartIndex + 3)]+"").trim());
                		bean.setMobile2((values[(colStartIndex + 4)]+"").trim());
                		bean.setPhone((values[(colStartIndex + 5)]+"").trim());
                		bean.setpTel((values[(colStartIndex + 6)]+"").trim());
                		bean.setEmail((values[(colStartIndex + 7)]+"").trim());
                		bean.setProvince((values[(colStartIndex + 8)]+"").trim());
                		bean.setCity((values[(colStartIndex + 9)]+"").trim());
                		bean.setClientSource((values[(colStartIndex + 10)]+"").trim());
                		bean.setSellId(localToDealerId);
                		bean.setMname((values[(colStartIndex + 12)]+"").trim());
                		bean.setDataPriority((values[(colStartIndex + 13)]+"").trim());
                		bean.setLikeModel((values[(colStartIndex + 14)]+"").trim());
                		bean.setJhCarTime((values[(colStartIndex + 15)]+"").trim());
                		bean.setDealerCode((values[(colStartIndex + 17)]+"").trim());
                		bean.setDealerName((values[(colStartIndex + 18)]+"").trim());
                		bean.setDealerRegion((values[(colStartIndex + 19)]+"").trim());
                		bean.setDealerCommunity((values[(colStartIndex + 20)]+"").trim());
                		bean.setSapCrmId((values[(colStartIndex + 21)]+"").trim());
                		
                		datas.add(bean);
                	}
                }
                
                read.close();
                
                dataToDmsDao.InsertData(datas);
                
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
