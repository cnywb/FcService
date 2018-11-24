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

import com.fc.ss.bean.FcAfterOBBean;
import com.fc.ss.bean.FcDistributionBean;
import com.fc.ss.dao.db.AfterOBDao;
import com.fc.ss.dao.db.DistributionDao;
import com.fc.ss.util.CommonFunction;

public class AfterOBService {
    private static Logger log = Logger.getLogger(AfterOBService.class);
    private AfterOBDao afterobDao = new AfterOBDao();
    private DistributionDao distributionDao = new DistributionDao();
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
            	
                read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                
                while((lineTxt = bufferedReader.readLine()) != null){
                	list.add(lineTxt);
                }
                
                List<FcAfterOBBean> afterobDatas = new ArrayList<FcAfterOBBean>();
                List<FcDistributionBean> distributionDatas = new ArrayList<FcDistributionBean>();
                
                int rowStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.afterob.row.start.no"));
                int colStartIndex = Integer.parseInt(cf.GetPropertiesValue("sap.remote.file.afterob.col.start.no"));
                
                for(int i = rowStartIndex; i < list.size() - 1; i++){
                	
                	String[] values = list.get(i).toString().split("\\|", -1);
                	
                	String localToDealerId = (values[(colStartIndex + 32)]+"").trim();
                	Boolean afterobDataExists = afterobDao.CheckDataExistsByLocalToDealerId(localToDealerId);
                	
                	String dateOfBirthString = (values[7]+"").trim().replace("00000000", "");
                	Date dateOfBirth = cf.GetDate(dateOfBirthString);
                	
                	String startObDateString = cf.GetDateString((values[(colStartIndex + 21)]+"").trim());
                	Date excutionDate = cf.GetDate((values[(colStartIndex + 43)]+"").trim());
                	String excutionDateString = cf.GetDateString((values[(colStartIndex + 43)]+"").trim());
                	String createDateString = cf.GetDateString((values[(colStartIndex + 44)]+"").trim());
                	String receiveDateString = cf.GetDateString((values[(colStartIndex + 45)]+"").trim());
                	
                	if(!afterobDataExists){
	                	FcAfterOBBean afterObBean = new FcAfterOBBean();
	                	afterObBean.setName((values[(colStartIndex + 0)]+"").trim());
	                	afterObBean.setJobTitle((values[(colStartIndex + 1)]+"").trim());
	                	afterObBean.setMobile((values[(colStartIndex + 2)]+"").trim());
	                	afterObBean.setMobile2((values[(colStartIndex + 3)]+"").trim());
	                	afterObBean.setHomePhone((values[(colStartIndex + 4)]+"").trim());
	                	afterObBean.setTelephone((values[(colStartIndex + 5)]+"").trim());
	                	afterObBean.setEmail((values[(colStartIndex + 6)]+"").trim());
	                	afterObBean.setDateOfBirth(dateOfBirth);
	                	afterObBean.setEducationLevel((values[(colStartIndex + 8)]+"").trim());
	                	afterObBean.setGender((values[(colStartIndex + 9)]+"").trim());
	                	afterObBean.setMaritalStatus((values[(colStartIndex + 10)]+"").trim());
	                	afterObBean.setProvince((values[(colStartIndex + 11)]+"").trim());
	                	afterObBean.setCity((values[(colStartIndex + 12)]+"").trim());
	                	afterObBean.setAddress((values[(colStartIndex + 13)]+"").trim());
	                	afterObBean.setMonthlyHouseholdIncome((values[(colStartIndex + 14)]+"").trim());
	                	afterObBean.setOccupation((values[(colStartIndex + 15)]+"").trim());
	                	afterObBean.setPrimaryCommunication((values[(colStartIndex + 16)]+"").trim());
	                	afterObBean.setSecondaryCommunication((values[(colStartIndex + 17)]+"").trim());
	                	
	                	afterObBean.setEstimateBuycartimeAfterOB((values[(colStartIndex + 18)]+"").trim());
	                	afterObBean.setInterestedCarCodelAfterOB((values[(colStartIndex + 19)]+"").trim());
	                	afterObBean.setInterestedCarEeriesAfterOB((values[(colStartIndex + 20)]+"").trim());
	                	
	                	afterObBean.setStartOBDate(startObDateString);
	                	afterObBean.setCampaignnameEng((values[(colStartIndex + 22)]+"").trim());
	                	afterObBean.setCampaignnameChi((values[(colStartIndex + 23)]+"").trim());
	                	afterObBean.setMediaSource((values[(colStartIndex + 24)]+"").trim());
	                	afterObBean.setMediaCodeId((values[(colStartIndex + 26)]+"").trim());
	                	afterObBean.setCallAttempt((values[(colStartIndex + 27)]+"").trim());
	                	afterObBean.setVerifyStatus((values[(colStartIndex + 28)]+"").trim());
	                	afterObBean.setVerifyMode((values[(colStartIndex + 29)]+"").trim());
	                	afterObBean.setAssignDealerName((values[(colStartIndex + 30)]+"").trim());
	                	afterObBean.setAssignDealerCode((values[(colStartIndex + 31)]+"").trim());
	                	afterObBean.setLocalToDealerId((values[(colStartIndex + 32)]+"").trim());
	                	
	                	afterObBean.setMemo1((values[(colStartIndex + 36)]+"").trim());
	                	afterObBean.setMemo2((values[(colStartIndex + 37)]+"").trim());
	                	afterObBean.setMemo3((values[(colStartIndex + 38)]+"").trim());
	                	afterObBean.setMemo4((values[(colStartIndex + 39)]+"").trim());
	                	afterObBean.setMemo5((values[(colStartIndex + 40)]+"").trim());
	                	afterObBean.setSymbol((values[(colStartIndex + 41)]+"").trim());
	                	
	                	afterObBean.setExecutionDate(excutionDate);
	                	afterObBean.setCreateDate(createDateString);
	                	afterObBean.setReceiveDate(receiveDateString);
	                	afterObBean.setLeadsType((values[(colStartIndex + 46)]+"").trim());
	                	afterObBean.setIsHotLeads((values[(colStartIndex + 47)]+"").trim());
	                	afterObBean.setBudgetRange((values[(colStartIndex + 49)]+"").trim());
	                	
	                	afterobDatas.add(afterObBean);
                	}
                	
                	Boolean distributionDataExists = distributionDao.CheckDataExistsByLocalToDealerId(localToDealerId);
                	
                	if(!distributionDataExists){
	                	FcDistributionBean distributionBean = new FcDistributionBean();
	                	distributionBean.setName((values[(colStartIndex + 0)]+"").trim());
	                	distributionBean.setMobile((values[(colStartIndex + 1)]+"").trim());
	                	distributionBean.setSecondaryMobilePhone((values[(colStartIndex + 2)]+"").trim());
	                	distributionBean.setHomePhone((values[(colStartIndex + 3)]+"").trim());
	                	distributionBean.setWorkPhone((values[4]+"").trim());
	                	distributionBean.setGender((values[(colStartIndex + 9)]+"").trim());
	                	distributionBean.setProvince((values[(colStartIndex + 11)]+"").trim());
	                	distributionBean.setCity((values[(colStartIndex + 12)]+"").trim());
	                	
	                	distributionBean.setVechileOfInterests((values[(colStartIndex + 18)]+"").trim());
	                	distributionBean.setIntendedPurchaseTime((values[(colStartIndex + 19)]+"").trim());
	                	
	                	distributionBean.setCampaignNameEng((values[(colStartIndex + 22)]+"").trim());
	                	distributionBean.setCampaignNameChi((values[(colStartIndex + 23)]+"").trim());
	                	distributionBean.setMediasource((values[(colStartIndex + 24)]+"").trim());
	                	distributionBean.setMediaSourceType((values[(colStartIndex + 25)]+"").trim());
	                	distributionBean.setChannel((values[(colStartIndex + 26)]+"").trim());
	                	
	                	distributionBean.setObCallStatus((values[(colStartIndex + 28)]+"").trim());
	                	distributionBean.setAssignDealerName((values[(colStartIndex + 30)]+"").trim());
	                	distributionBean.setAssignDealerCode((values[(colStartIndex + 31)]+"").trim());
	                	distributionBean.setLocalToDealerId((values[(colStartIndex + 32)]+"").trim());
	                	
	                	distributionBean.setMemo1((values[(colStartIndex + 36)]+"").trim());
	                	distributionBean.setMemo2((values[(colStartIndex + 37)]+"").trim());
	                	distributionBean.setMemo3((values[(colStartIndex + 38)]+"").trim());
	                	distributionBean.setMemo4((values[(colStartIndex + 39)]+"").trim());
	                	distributionBean.setMemo5((values[(colStartIndex + 40)]+"").trim());
	                	
	                	distributionBean.setExecutionDate(excutionDateString);
	                	distributionBean.setCreateDate(createDateString);
	                	distributionBean.setReceiveDate(receiveDateString);
	                	distributionBean.setIsFollowUp((values[(colStartIndex + 48)]+"").trim());
	                	
	                	distributionDatas.add(distributionBean);
                	}
                }
                
                afterobDao.InsertData(afterobDatas);
                distributionDao.InsertData(distributionDatas);
                
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
