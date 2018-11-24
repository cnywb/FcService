package com.fc.ss.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.kohsuke.rngom.parse.Parseable;

import com.fc.ss.bean.FcBeforeOBBean;
import com.fc.ss.dao.db.BeforeOBDao;
import com.fc.ss.util.CommonFunction;
import com.fc.ss.util.RemoteFTP;
import com.fc.util.PropUtils;

/**
 * 提交至dms售前数据操作类 
 * 
 * @author lq
 * 
 */
public class BeforeOBService {
    private static Logger log = Logger.getLogger(BeforeOBService.class);
    private BeforeOBDao dao = new BeforeOBDao();
    public static final DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
    private CommonFunction cf = new CommonFunction();
    
    List<FcBeforeOBBean> upList;
    
    public void lockedTable(List<FcBeforeOBBean> list) throws Exception{
    	String sql="SELECT b.object_name FROM v$locked_object a, dba_objects b WHERE a.object_id = b.object_id and b.object_name IN ('FC_SS_BEFOREOB','FC_SS_CAMPAIGNCHANNEL','FC_SS_CAMPAIGNMASTER')";
    	String str=cf.lockedTable(sql);
    	if(str.length()!=0){
    		log.info("警告：表"+str+"被锁,请检查!稍后将重新执行UPDATE操作.");
    		log.info("60秒后重新执行...");
    		Thread.sleep(60000);
    		updateBean(list);
    	}else{
    		log.info("No Table Be Locked");
    	}
    }
    
    public void updateBean(List<FcBeforeOBBean> list) throws Exception{
//    	lockedTable(list);
//    	dao.updateSapFlag(list);
    }
    
    public void GenerateText(String startDate,String endDate) {
		try {
		    String dir = cf.GetPropertiesValue("local.ftpToSAP");
		    String dir2 = dir + format.format(new Date());
		    log.info("SAP文件生成路径："+dir2);
		    String fileName;
		    log.info("获取FC_SS_BEFOREOB数据开始!");
		    upList = dao.queryPage(startDate,endDate);
		    log.info("获取FC_SS_BEFOREOB数据结束!");
		    
		    int countSum = upList.size();
	    	int maxSize = Integer.parseInt(cf.GetPropertiesValue("local.fileToSap.record.max"));
			int pageCount=countSum/maxSize+1;
			
			for(int i=1;i<=pageCount;i++){
				fileName = "CAFCHN_LMC_BEFOREOB_"+ formaty.format(new Date())+"_BATCH" + i;
				String txtFilePath = dir2 + "\\" + File.separator + fileName;
				
				List<FcBeforeOBBean> upList1 = new ArrayList<FcBeforeOBBean>();
				if(i==pageCount){
					upList1 = upList.subList((i-1)*maxSize, countSum);
				}else {
					upList1 = upList.subList((i-1)*maxSize, i*maxSize);
				}
				try {
				    log.info("开始生成BeforeOB的txt！");
					this.exportDataText(upList1,dir2,fileName);
					log.info("BeforeOB的txt文件已生成!");
	
					dao.insertUpdateFile(txtFilePath,"FC_SS_BEFOREOB","成功");
				} catch (Exception e) {
				    e.printStackTrace();
				    dao.insertUpdateFile(txtFilePath,"FC_SS_BEFOREOB","失败");
				    log.info("生成BeforeOB的txt失败！");
				}
			}
			if(upList.size()!=0){
				dao.insertSapId(upList);
			}
		}catch (Exception e) {
			    e.printStackTrace();
			    log.info("生成BeforeOB的txt失败！");
		}
    }
    
    public static void createXmlDocument(Class<?> obj, List<?> entitys,
    	    String Encode, String XMLPath, String XmlName) {
    	log.info("动态生成XML文件开始");
    	try {
    	    XMLWriter writer = null;// 声明写XML的对象
    	    OutputFormat format = OutputFormat.createPrettyPrint();
    	    format.setEncoding(Encode); // 设置XML文件的编码格式 UTF-8

    	    File file = new File(XMLPath);// 获得文件
    	    if (!file.exists()) {
    	    	file.mkdirs();
    	    }
    	    // 创建xml文件
    	    Document document = DocumentHelper.createDocument();
    	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    String time = sf.format(new Date());
    	    String now = time.substring(0, 10)+ "T" + time.substring(11, 19);
//    	    Element root = document.addElement("ns0:DMS_SalesLeadTwo_MT");// 添加根节点
    	    Element root = document.addElement("Datas");
//    	    root.addAttribute("xmlns:ns0", "urn:ford-com:IN:ID:TH:VN:PH:DMS:SalesLeadTwo");
    	    Element header = root.addElement("Header"); // 二级节点
    	    header.addElement("Record_Identifier").setText("AA00");
    	    header.addElement("ISO_Code").setText("CHN");
    	    header.addElement("System_Identifier").setText("LMC");
    	    header.addElement("FIELIDENTIFR").setText("BEFOREOB");
    	    header.addElement("Date_Time_Stamp").setText(now);
    	    header.addElement("Control_Number").setText("00001");
    	    header.addElement("Feed_Indicator").setText("P");

    	    Field[] properties = obj.getDeclaredFields();// 获得实体类的所有属性
//    	    int i = 0;
    	    for (Object t : entitys) { // 递归实体
	    		Element element = root.addElement("Detail"); // 二级节点
	    		for (int i = 0; i < properties.length; i++) {
	    		    if (!"serialVersionUID".equals(properties[i].getName())) {
		    			// 反射get方法
	    		    	Method meth = t.getClass().getMethod("get" + properties[i].getName().substring(0,1).toUpperCase()+ properties[i].getName().substring(1));
	    		    	
		    			// 为二级节点添加属性，属性值为对应属性的值
		    			Object invo = meth.invoke(t);
		    			element.addElement(properties[i].getName().substring(0,1).toUpperCase()+ properties[i].getName().substring(1)).setText((invo != null ? invo.toString() : ""));
	    			}
	    		}
    	    }
    	    int m = entitys.size();
    	    Element footer = root.addElement("Footer"); // 二级节点
    	    footer.addElement("Record_Identifier").setText("ZZ99");
    	    footer.addElement("ISO_Code").setText("CHN");
    	    footer.addElement("System_Identifier").setText("LMC");
    	    footer.addElement("FIELIDENTIFR").setText("BEFOREOB");
    	    footer.addElement("Date_Time_Stamp").setText(now);
    	    footer.addElement("Detail_Record").setText(String.valueOf(m));
    	    footer.addElement("Total_Record").setText(String.valueOf(m+2));

    	    
    	    File fil = new File(XMLPath + "\\" + XmlName + ".xml");
    	    writer = new XMLWriter(new FileOutputStream(fil), format);
    	    writer.write(document);
    	    writer.close();
    	} catch (Exception e) {
    	    log.error("动态生成XML失败", e);
    	}
    }

    
    public void exportDataText(List<FcBeforeOBBean> list, String path, String fileName) {
		log.info("begin exportDataText()");
		
		String readFilePah = "";
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(new Date());
			fileName = fileName + ".txt";
			File file = new File(path);// 获得文件
		    if (!file.exists()) {
		    	file.mkdirs();
		    }
		    
			readFilePah = path + "\\" + File.separator + fileName;
			
//			String fullPathName = readFilePah;
			
	        FileOutputStream outSTr = null;  
	        BufferedOutputStream Buff = null;  
	        String enter = "\r\n";  
		        
	        FcBeforeOBBean u;  
		    StringBuffer write;
		    
	        try {  
	        	
	        	/*int ctrlNumb = Integer.parseInt(cf.GetPropertiesValue("sap.beforeob.ctrlnumb.value"));
			    int ctrlNumbMax = Integer.parseInt(cf.GetPropertiesValue("sap.beforeob.ctrlnumb.max"));
			    
			    ctrlNumb += 1;
			    
			    if(ctrlNumb >= ctrlNumbMax){
			    	ctrlNumb = 1;
			    }
			    
			    log.info("[ctrlNumb]: " + ctrlNumb);
			    log.info("[ctrlNumbMax]: " + ctrlNumbMax);*/
			    
                BeforeOBDao dao=new BeforeOBDao();
			    
			    int ctrlNumb=dao.getSeqNextValue("SEQ_TO_SAP_BO_NO");
			    
			    int ctrlNumbMax = Integer.parseInt(cf.GetPropertiesValue("sap.beforeob.ctrlnumb.max"));
			    
			    String ctrlNumbString = cf.GetTxtCtrlNumbString((ctrlNumbMax + "").length(), ctrlNumb);
			    
	            outSTr = new FileOutputStream(new File(readFilePah));  
	            Buff = new BufferedOutputStream(outSTr);
	            
	            StringBuffer header=new StringBuffer();
	            header.append("AA00").append("|").append("CHN").append("|").append("LMC").append("|").append("BEFOREOB").append("|").append("|");
	            header.append(time).append("|").append(ctrlNumbString).append("|").append("P");
	            header.append(enter);
	            Buff.write(header.toString().getBytes("UTF-8"));
	            
	            StringBuffer title=new StringBuffer();
	            
	            title.append("\"IDENTIFIER\"").append("|");
	            title.append("\"receive_Date\"").append("|").append("\"create_Date\"").append("|").append("\"gender\"").append("|").append("\"name\"").append("|");
	            title.append("\"job_Title\"").append("|").append("\"ageRange\"").append("|").append("\"DateOfBirth\"").append("|").append("\"email\"").append("|");
	            title.append("\"homePhone\"").append("|").append("\"mobile\"").append("|").append("\"mobile_2\"").append("|").append("\"telephone\"").append("|");
	            title.append("\"address\"").append("|").append("\"city\"").append("|").append("\"province\"").append("|").append("\"zipCode\"").append("|");
	            title.append("\"primary_CommunicationPreference\"").append("|").append("\"secondary_CommunicationPreference\"").append("|").append("\"occupation\"").append("|");
	            title.append("\"current_VehicleOwned\"").append("|").append("\"current_DrivingLicense\"").append("|").append("\"current_VehicleModel\"").append("|");
	            title.append("\"current_VehicleModelList\"").append("|").append("\"curVinYear\"").append("|").append("\"maritalStatus\"").append("|");
	            title.append("\"noChild\"").append("|").append("\"monthlyHouseholdIncome\"").append("|").append("\"educationLevel\"").append("|");
	            title.append("\"campaignName_CHI\"").append("|").append("\"campaignName_ENG\"").append("|").append("\"interested_Car_Model\"").append("|");
	            title.append("\"interested_Car_Model_Detail\"").append("|").append("\"budget_Range\"").append("|").append("\"estimate_BuyCarTime\"").append("|");
	            title.append("\"mediaSource\"").append("|").append("\"Campaign_Channel_ID\"").append("|").append("\"local_To_Dealer_ID\"").append("|");
	            title.append("\"process_Mode\"").append("|").append("\"memo1\"").append("|").append("\"memo2\"").append("|").append("\"memo3\"").append("|");
	            title.append("\"memo4\"").append("|").append("\"memo5\"").append("|").append("\"validity_Mark\"").append("|").append("\"import_Mode\"").append("|");
	            title.append("\"assign_Dealer_Name\"").append("|").append("\"assign_Dealer_Code\"").append("|").append("\"import_Date\"").append("|").append("\"symbol\"").append("|").append("\"verify_status\"");
	            title.append(enter);
	            
	            Buff.write(title.toString().getBytes("UTF-8"));  
	            if(list!=null){
		            for (int i = 0; i < list.size(); i++) {  
		                u = (FcBeforeOBBean) list.get(i);  
		                
		                String dateOfBirth = "";
		                if(u.getDateOfBirth() != null){
		                	dateOfBirth = sdf.format(u.getDateOfBirth());
		                }
		                
		                String importDate = "";
		                if(u.getImport_Date() != null){
		                	importDate = u.getImport_Date().replace("-", "").replace(":", "").replace(" ", "");
		                }
		                
		                String createDate = "";
		                if(u.getCreate_Date() != null){
		                	createDate = u.getCreate_Date().replace("-", "") + "000000";
		                }
		                
		                String receiveDate = "";
		                if(u.getReceive_Date() != null){
		                	receiveDate = u.getReceive_Date().replace("-", "").replace(":", "").replace(" ", "");
		                }
		                
		                String symbol = "";
		                if(u.getSymbol() != null){
		                	symbol = u.getSymbol().replace("-", "_");
		                }
		                
		                write = new StringBuffer();
		                write.append("CC00").append("|");
		                write.append(receiveDate).append("|").append(createDate).append("|").append(u.getGender()).append("|");
		                write.append(u.getName()).append("|").append(u.getJob_Title()).append("|").append(u.getAgeRange()).append("|");
		                write.append(dateOfBirth).append("|").append(u.getEmail()).append("|").append(u.getHomePhone()).append("|");
		                write.append(u.getMobile()).append("|").append(u.getMobile_2()).append("|").append(u.getTelephone()).append("|");
		                write.append(u.getAddress()).append("|").append(u.getCity()).append("|").append(u.getProvince()).append("|");
		                write.append(u.getZipCode()).append("|").append(u.getPrimary_CommunicationPreference()).append("|");
		                write.append(u.getSecondary_CommunicationPreference()).append("|").append(u.getOccupation()).append("|");
		                write.append(u.getCurrent_VehicleOwned()).append("|").append(u.getCurrent_DrivingLicense()).append("|");
		                write.append(u.getCurrent_VehicleModel()).append("|").append(u.getCurrent_VehicleModelList()).append("|");
		                write.append(u.getCurVinYear()).append("|").append(u.getMaritalStatus()).append("|").append(u.getNoChild()).append("|");
		                write.append(u.getMonthlyHouseholdIncome()).append("|").append(u.getEducationLevel	()).append("|");
		                write.append(u.getCampaignName_CHI()).append("|").append(u.getCampaignName_ENG()).append("|");
		                write.append(u.getInterested_Car_Model()).append("|").append(u.getInterested_Car_Model_Detail()).append("|");
		                write.append(u.getBudget_Range()).append("|").append(u.getEstimate_BuyCarTime()).append("|");
		                write.append(u.getMediaSource()).append("|").append(u.getCampaign_Channel_ID()).append("|");
		                write.append(u.getLocal_To_Dealer_ID()).append("|").append(u.getProcess_Mode()).append("|");
		                write.append(u.getMemo1()).append("|").append(u.getMemo2()).append("|").append(u.getMemo3()).append("|");
		                write.append(u.getMemo4()).append("|").append(u.getMemo5()).append("|").append(u.getValidity_Mark()).append("|");
		                write.append(u.getImport_Mode()).append("|").append(u.getAssign_Dealer_Name()).append("|");
		                write.append(u.getAssign_Dealer_Code()).append("|").append(importDate).append("|").append(symbol).append("|").append(u.getMemo1());
		                write.append(enter);
		                Buff.write(write.toString().getBytes("UTF-8"));
		            }
	            }
	            
	            StringBuffer footer=new StringBuffer();
	            footer.append("ZZ99").append("|").append("CHN").append("|").append("LMC").append("|").append("BEFOREOB").append("|");
	            footer.append(time).append("|").append(list.size()).append("|").append(list.size()+2);
//	            footer.append(enter);
	            Buff.write(footer.toString().getBytes("UTF-8"));  
	            
	            Buff.flush();  
	            Buff.close();

	        } catch (Exception e) {  
	            e.printStackTrace();

	        } finally {  
	            try {  
	                Buff.close();  
	                outSTr.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
		} catch (Exception e) {
			log.error("[根据params导出txt]出现异常",e);
		}
		log.info("end exportDataText()");
    }
    
}
