package com.fc.ss.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
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

import com.fc.ss.bean.FcSMSBean;
import com.fc.ss.dao.db.BeforeOBDao;
import com.fc.ss.dao.db.SMSDao;
import com.fc.ss.util.CommonFunction;
import com.fc.ss.util.RemoteFTP;
import com.fc.util.PropUtils;

/**
 * 提交至dms售前数据操作类
 * 
 * @author lq
 * 
 */
public class SMSService {
    private static Logger log = Logger.getLogger(SMSService.class);
    private SMSDao dao = new SMSDao();
    public static final DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
    private CommonFunction cf = new CommonFunction();
    
    public void GenerateText(String startDate, String endDate) {
	try {
	    String dir = cf.GetPropertiesValue("local.ftpToSAP");
		String dir2 = dir + format.format(new Date());
	    String fileName = "CAFCHN_LMC_SMS_"+ formaty.format(new Date());
	    log.info("调用获取FC_M2F_MOBILE_SMS数据方法!");
	    List<FcSMSBean> upList = dao.queryPage(startDate,endDate);
	    log.info("获取FC_M2F_MOBILE_SMS数据结束!");
	    
	    int countSum = upList.size();
    	int maxSize = Integer.parseInt(cf.GetPropertiesValue("local.fileToSap.record.max"));
		int pageCount=countSum/maxSize+1;
		
		for(int i=1;i<=pageCount;i++){
			
			fileName += "_BATCH" + i;
			
			String txtFilePath = dir2 + "\\" + File.separator + fileName;
			
			List<FcSMSBean> upList1 = new ArrayList<FcSMSBean>();
			if(i==pageCount){
				upList1 = upList.subList((i-1)*maxSize, countSum);
			}else {
				upList1 = upList.subList((i-1)*maxSize, i*maxSize-1);
			}
			try {
			    log.info("开始生成FC_M2F_MOBILE_SMS的txt！");
				this.exportDataText(upList1,dir2,fileName);
				dao.insertUpdateFile(txtFilePath,"FC_M2F_MOBILE_SMS","成功");
				if(upList1 != null){
					dao.updateSapFlag(upList);
				}
//						String ftpIp = cf.GetPropertiesValue("sap.ftp.ip");
//						Integer ftpPort = Integer.parseInt(cf.GetPropertiesValue("sap.ftp.port"));
//						String ftpUser = cf.GetPropertiesValue("sap.ftp.user");
//						String ftpPwd = cf.GetPropertiesValue("sap.ftp.pwd");
//						String ftpPath = cf.GetPropertiesValue("sap.ftp.path");
//						
//						RemoteFTP uploader = new RemoteFTP(ftpIp, ftpPort, ftpUser, ftpPwd);
//					    uploader.UploadFileToRemoteFTP(txtFilePath, fileName + ".txt", ftpPath);
				
				upList1.clear();
				
			} catch (Exception e) {
			    e.printStackTrace();
			    dao.insertUpdateFile(txtFilePath,"FC_M2F_MOBILE_SMS","失败");
			    log.info("生成FC_M2F_MOBILE_SMS的txt失败！");
			}
		}
		 if (upList != null) {
			upList.clear();
		   }
	}catch (Exception e) {
		    e.printStackTrace();
		    log.info("生成FC_M2F_MOBILE_SMS的txt失败！");
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
    	    Element root = document.addElement("Datas");// 添加根节点	    
//    	    root.addAttribute("xmlns:ns0", "urn:ford-com:IN:ID:TH:VN:PH:DMS:SalesLeadTwo");
    	    
    	    Element header = root.addElement("Header"); // 二级节点
    	    header.addElement("Record_Identifier").setText("AA00");
    	    header.addElement("ISO_Code").setText("CHN");
    	    header.addElement("System_Identifier").setText("LMC");
    	    header.addElement("FIELIDENTIFR").setText("SMS");
    	    header.addElement("Date_Time_Stamp").setText(now);
    	    header.addElement("Control_Number").setText("00001");
    	    header.addElement("Feed_Indicator").setText("F");
    	    
    	    Field[] properties = obj.getDeclaredFields();// 获得实体类的所有属性

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
    	    footer.addElement("FIELIDENTIFR").setText("SMS");
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

    
    public void exportDataText(List<FcSMSBean> list, String path, String fileName) {
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
			
//			String fullPathName =readFilePah;
			
	        FileOutputStream outSTr = null;  
	        BufferedOutputStream Buff = null;  
	        String enter = "\r\n";  
		        
	        FcSMSBean u;  
		    StringBuffer write;
		    
	        try {  
	        	
	        	BeforeOBDao dao=new BeforeOBDao();
			    
			    int ctrlNumb=dao.getSeqNextValue("SEQ_TO_SAP_SMS_NO");
			    
			    int ctrlNumbMax = Integer.parseInt(cf.GetPropertiesValue("sap.sms.ctrlnumb.max"));
			    
//			    ctrlNumb += 1;
			    
//			    if(ctrlNumb >= ctrlNumbMax){
//			    	ctrlNumb = 1;
//			    }
			    
//			    cf.SetPropertiesValue("sap.sms.ctrlnumb.value", ctrlNumb + "");
			    
			    log.info("[ctrlNumb]: " + ctrlNumb);
			    
			    String ctrlNumbString = cf.GetTxtCtrlNumbString((ctrlNumbMax + "").length(), ctrlNumb);
	        	
			    log.info("[ctrlNumbString]: " + ctrlNumbString);
			    
	            outSTr = new FileOutputStream(new File(readFilePah));  
	            Buff = new BufferedOutputStream(outSTr);
	            
	            StringBuffer header=new StringBuffer();
	            header.append("AA00").append("|").append("CHN").append("|").append("LMC").append("|").append("SMS").append("|").append("|");
	            header.append(time).append("|").append(ctrlNumbString).append("|").append("F");
	            header.append(enter);
	            Buff.write(header.toString().getBytes("UTF-8"));
	            
	            StringBuffer title=new StringBuffer();
	            title.append("\"IDENTIFIER\"").append("|");
	            title.append("\"mid\"").append("|").append("\"mobile\"").append("|").append("\"status\"").append("|").append("\"sendTime\"").append("|");
	            title.append("\"sendStatus\"").append("|").append("\"replyTime\"").append("|").append("\"replyContent\"").append("|").append("\"remark\"").append("|");
	            title.append("\"msgId\"").append("|").append("\"wgCode\"").append("|").append("\"receiveDate\"").append("|").append("\"statusDesc\"").append("|");
	            title.append("\"systemDate\"").append("|").append("\"localToDealerId\"");
	            title.append(enter);
	            
	            Buff.write(title.toString().getBytes("UTF-8"));  
	            if(list!=null){
	            	for (int i = 0; i < list.size(); i++) {  
	            		u = (FcSMSBean) list.get(i);  
	            		
	            		String systemDate = "";
	            		if(u.getSystemDate() != null){
	            			systemDate = sdf.format(u.getSystemDate());
	            		}
	            		
	            		String receiveDate = "";
	            		if(u.getReceiveDate() != null){
	            			receiveDate = u.getReceiveDate().replace("-", "").replace(":", "").replace(" ", "");
	            		}
	            		
	            		String localToDealerId = "";
	            		if(u.getLocalToDealerId() != null){
	            			localToDealerId = u.getLocalToDealerId();
	            		}
	            		
	            		write = new StringBuffer();  
	            		write.append("CC00").append("|");
	            		write.append(u.getMid()).append("|").append(u.getMobile()).append("|").append(u.getStatus()).append("|");
	            		write.append(u.getSendTime()).append("|").append(u.getSendStatus()).append("|").append(u.getReplyTime()).append("|");
	            		write.append(u.getReplyContent()).append("|").append(u.getRemark()).append("|").append(u.getMsgId()).append("|");
	            		write.append(u.getWgCode()).append("|").append(receiveDate).append("|").append(u.getStatusDesc()).append("|");
	            		write.append(systemDate).append("|").append(localToDealerId);
	            		write.append(enter);
	            		Buff.write(write.toString().getBytes("UTF-8"));  
	            	}
	            }
	            
	            StringBuffer footer=new StringBuffer();
	            footer.append("ZZ99").append("|").append("CHN").append("|").append("LMC").append("|").append("SMS").append("|");
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
