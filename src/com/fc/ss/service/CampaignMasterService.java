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

import com.fc.ss.bean.FcCampaignChannelBean;
import com.fc.ss.bean.FcCampaignMasterBean;
import com.fc.ss.dao.db.BeforeOBDao;
import com.fc.ss.dao.db.CampaignMasterDao;
import com.fc.ss.util.CommonFunction;
import com.fc.ss.util.RemoteFTP;
import com.fc.util.PropUtils;

/**
 * 提交至dms售前数据操作类
 * 
 * @author lq
 * 
 */
public class CampaignMasterService {
    private static Logger log = Logger.getLogger(CampaignMasterService.class);
    private CampaignMasterDao dao = new CampaignMasterDao();
    public static final DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
    private CommonFunction cf = new CommonFunction();
    
    List<FcCampaignMasterBean> upList;
    
    public void lockedTable(List<FcCampaignMasterBean> list) throws Exception{
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
    
    public void updateBean(List<FcCampaignMasterBean> list) throws Exception{
//    	lockedTable(list);
    	dao.updateSapFlag(list);
    }
    
    public List<FcCampaignMasterBean> GenerateText(String startDate, String endDate) {
		try {
		    String dir = cf.GetPropertiesValue("local.ftpToSAP");
			String dir2 = dir + format.format(new Date());
		    String fileName;
		    log.info("获取FC_SS_CAMPAIGNMASTER数据开始!");
		    upList = dao.queryPage(startDate,endDate);
		    log.info("获取FC_SS_CAMPAIGNMASTER数据结束!");
		    
		    int countSum = upList.size();
	    	int maxSize = Integer.parseInt(cf.GetPropertiesValue("local.fileToSap.record.max"));
			int pageCount=countSum/maxSize+1;
			
			for(int i = 1; i <= pageCount; i++){
				fileName="CAFCHN_LMC_CAMPAIGNMASTER_"+ formaty.format(new Date())+"_BATCH" + i;
				String txtFilePath = dir2 + "\\" + File.separator + fileName;
				
				List<FcCampaignMasterBean> upList1 = new ArrayList<FcCampaignMasterBean>();
				if(i==pageCount){
					upList1 = upList.subList((i-1)*maxSize, countSum);
				}else {
					upList1 = upList.subList((i-1)*maxSize, i*maxSize);
				}
	
				try {
				    log.info("开始生成CampaignMaster的txt！");
				    this.exportDataText(upList1,dir2,fileName);
				    log.info("CampaignMaster的txt文件已生成！");
					dao.insertUpdateFile(txtFilePath, "FC_SS_CAMPAIGNMASTER","成功");
					
				} catch (Exception e) {
				    e.printStackTrace();
				    dao.insertUpdateFile(txtFilePath, "FC_SS_CAMPAIGNMASTER","失败");
				    log.info("生成CampaignMaster的txt失败！");
				}
			}
			return upList;
		}catch (Exception e) {
			    e.printStackTrace();
			    log.info("生成CampaignMaster的txt失败！");
			    return null;
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
    	    header.addElement("FIELIDENTIFR").setText("CAMPAINMASTER");
    	    header.addElement("Date_Time_Stamp").setText(now);
    	    header.addElement("Control_Number").setText("00001");
    	    header.addElement("Feed_Indicator").setText("P");
    	    
    	    Field[] properties = obj.getDeclaredFields();// 获得实体类的所有属性
    	    Element detail = root.addElement("Detail"); // 二级节点
    	    for (Object t : entitys) { // 递归实体
	    		Element element = detail.addElement("bean"); // 二级节点
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
    	    footer.addElement("FIELIDENTIFR").setText("CAMPAINMASTER");
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

    
    public void exportDataText(List<FcCampaignMasterBean> list, String path, String fileName) {
		log.info("begin exportDataText()");
		
		String readFilePah = "";
		try {
			SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = dfs.format(new Date());
			fileName = fileName + ".txt";
			File file = new File(path);// 获得文件
		    if (!file.exists()) {
		    	file.mkdirs();
		    }
		    
			readFilePah = path + "\\" + File.separator + fileName;
			
	        FileOutputStream outSTr = null;  
	        BufferedOutputStream Buff = null;  
	        String enter = "\r\n";  
		        
	        FcCampaignMasterBean u;  
		    StringBuffer write;
		    
	        try {  
	        	
	        	/*int ctrlNumb = Integer.parseInt(cf.GetPropertiesValue("sap.campaignmaster.ctrlnumb.value"));
			    int ctrlNumbMax = Integer.parseInt(cf.GetPropertiesValue("sap.campaignmaster.ctrlnumb.max"));
			    
			    ctrlNumb += 1;
			    
			    if(ctrlNumb >= ctrlNumbMax){
			    	ctrlNumb = 1;
			    }
			    
			    cf.SetPropertiesValue("sap.campaignmaster.ctrlnumb.value", ctrlNumb + "");*/
			    
			    BeforeOBDao dao=new BeforeOBDao();
			    
			    int ctrlNumb=dao.getSeqNextValue("SEQ_TO_SAP_MASTER_NO");
			    
			    int ctrlNumbMax = Integer.parseInt(cf.GetPropertiesValue("sap.campaignmaster.ctrlnumb.max"));
			    String ctrlNumbString = cf.GetTxtCtrlNumbString((ctrlNumbMax + "").length(), ctrlNumb);
	        	
	            outSTr = new FileOutputStream(new File(readFilePah));  
	            Buff = new BufferedOutputStream(outSTr);

	            StringBuffer header=new StringBuffer();
	            header.append("AA00").append("|").append("CHN").append("|").append("LMC").append("|").append("CAMPAINMASTER").append("|").append("|");
	            header.append(time).append("|").append(ctrlNumbString).append("|").append("P");
	            header.append(enter);
	            Buff.write(header.toString().getBytes("UTF-8"));
	            
	            StringBuffer title=new StringBuffer();
	        
	            title.append("\"IDENTIFIER\"").append("|");
	            title.append("\"campaign_Master_ID\"").append("|").append("\"campaignName_ENG\"").append("|").append("\"campaignName_CHI\"").append("|");
	            title.append("\"Source\"").append("|").append("\"car_Type\"").append("|").append("\"remark\"").append("|").append("\"memo1\"").append("|");
	            title.append("\"memo2\"").append("|").append("\"import_Date\"").append("|").append("\"update_Date\"").append("|");
	            title.append("\"create_User\"").append("|").append("\"update_User\"");
	            
	            title.append(enter);
	            
	            Buff.write(title.toString().getBytes("UTF-8"));  
	            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	            if(list!=null){
	            	for (int i = 0; i < list.size(); i++) {
	            		u = (FcCampaignMasterBean) list.get(i);  
	            		write = new StringBuffer();  
	            		
	            		String importDate = "";
	            		if(u.getImport_Date() != null){
	            			importDate = sf.format(u.getImport_Date());
	            		}
	            		
	            		String updateDate = "";
	            		if(u.getUpdate_Date() != null){
	            			updateDate = sf.format(u.getUpdate_Date());
	            		}
	            		
	            		write.append("CC00").append("|");
	            		write.append(u.getCampaign_Master_ID()).append("|").append(u.getCampaignName_ENG()).append("|").append(u.getCampaignName_CHI()).append("|");
	            		write.append(u.getSource()).append("|").append(u.getCar_Type()).append("|").append(u.getRemark()).append("|");
	            		write.append(u.getMemo1()).append("|").append(u.getMemo2()).append("|").append(importDate).append("|");
	            		write.append(updateDate).append("|").append(u.getCreate_User()).append("|").append(u.getUpdate_User());
	            		write.append(enter);
	            		
	            		Buff.write(write.toString().getBytes("UTF-8"));  
	            	}
	            }

	            StringBuffer footer=new StringBuffer();
	            footer.append("ZZ99").append("|").append("CHN").append("|").append("LMC").append("|").append("CAMPAINMASTER").append("|");
	            footer.append(time).append("|").append(list.size()).append("|").append(list.size()+2);
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
