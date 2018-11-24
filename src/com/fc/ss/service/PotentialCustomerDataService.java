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

import com.fc.ss.bean.FcPotentialCustomerDataBean;
import com.fc.ss.dao.db.PCustomerDataDao;
import com.fc.ss.util.CommonFunction;

public class PotentialCustomerDataService {
    private static Logger log = Logger.getLogger(PotentialCustomerDataService.class);
    private PCustomerDataDao dao = new PCustomerDataDao();
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
    
    private CommonFunction cf = new CommonFunction();
    
    public Boolean ProcessDataIntoDb(String filePath) throws IOException{
    	
    	Boolean processResult = null;
    	
    	BufferedReader bufferedReader = null;
    	
    	List<String> list = new LinkedList<String>(); 
    	InputStreamReader read = null;
    	
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
                
                List<FcPotentialCustomerDataBean> datas = new ArrayList<FcPotentialCustomerDataBean>();
                
                int rowStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.pcustomer.row.start.no"));
                int colStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.pcustomer.col.start.no"));
                
                for(int i = rowStartIndex; i < list.size() - 1; i++){
                	
                	String[] values = list.get(i).toString().split("\\|", -1);
                	
                	String localToDealerId = (values[(colStartIndex + 0)]+"").trim();
                	Boolean dataExists = dao.CheckDataExistsByLocalToDealerId(localToDealerId);
                	
                	if(!dataExists){
                		FcPotentialCustomerDataBean bean = new FcPotentialCustomerDataBean();
                		bean.setSellNo(localToDealerId);
                		bean.setCrmCustomerNumber((values[(colStartIndex + 1)]+"").trim());
                		bean.setName((values[(colStartIndex + 2)]+"").trim());
                		bean.setSex((values[(colStartIndex + 3)]+"").trim());
                		bean.setBirthDay((values[(colStartIndex + 4)]+"").trim());
                		bean.setMobile((values[(colStartIndex + 5)]+"").trim());
                		bean.setEmail((values[(colStartIndex + 6)]+"").trim());
                		bean.setPhone((values[(colStartIndex + 7)]+"").trim());
                		bean.setProvince((values[(colStartIndex + 8)]+"").trim());
                		bean.setCity((values[(colStartIndex + 9)]+"").trim());
                		bean.setPostCode((values[(colStartIndex + 10)]+"").trim());
                		bean.setAddress((values[(colStartIndex + 11)]+"").trim());
                		bean.setSource((values[(colStartIndex + 12)]+"").trim());
                		bean.setChannel((values[(colStartIndex + 13)]+"").trim());
                		bean.setAcitity((values[(colStartIndex + 14)]+"").trim());
                		bean.setPayCharFactor((values[(colStartIndex + 15)]+"").trim());
                		bean.setInitLevel((values[(colStartIndex + 16)]+"").trim());
                		bean.setIntentLevel((values[(colStartIndex + 17)]+"").trim());
                		bean.setSalesGw((values[(colStartIndex + 18)]+"").trim());
                		bean.setIsToShop((values[(colStartIndex + 19)]+"").trim());
                		bean.setToShopTime((values[(colStartIndex + 20)]+"").trim());
                		bean.setIsTestDrive((values[(colStartIndex + 21)]+"").trim());
                		bean.setTestDriveTime((values[(colStartIndex + 22)]+"").trim());
                		bean.setIfFirstPurchase((values[(colStartIndex + 23)]+"").trim());
                		bean.setIsTwoToShop((values[(colStartIndex + 24)]+"").trim());
                		bean.setTwoToShopTime((values[(colStartIndex + 25)]+"").trim());
                		bean.setCarBrand((values[(colStartIndex + 26)]+"").trim());
                		bean.setCarModel((values[(colStartIndex + 27)]+"").trim());
                		bean.setChildrenN((values[(colStartIndex + 28)]+"").trim());
                		bean.setBudget((values[(colStartIndex + 29)]+"").trim());
                		bean.setIntentBrand((values[(colStartIndex + 30)]+"").trim());
                		bean.setIntentCars((values[(colStartIndex + 31)]+"").trim());
                		bean.setIntentCarModel((values[(colStartIndex + 32)]+"").trim());
                		bean.setDefeatType((values[(colStartIndex + 33)]+"").trim());
                		bean.setDefeatModels((values[(colStartIndex + 34)]+"").trim());
                		bean.setModelsReason((values[(colStartIndex + 35)]+"").trim());
                		bean.setDealerCode((values[(colStartIndex + 36)]+"").trim());
                		bean.setDealerName((values[(colStartIndex + 37)]+"").trim());
                		bean.setCusRecordDate((values[(colStartIndex + 38)]+"").trim());
                		bean.setCusCreateDate((values[(colStartIndex + 39)]+"").trim());
	                	datas.add(bean);
                	}
                	
                }
                
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
