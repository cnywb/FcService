package com.fc.service;

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

import com.fc.bean.M2FToDmsDataBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.ExcelExportUtilForDms;
import com.fc.util.PageBean;
import com.fc.util.PropUtils;

/**
 * 提交至dms售前数据操作类
 * 
 * @author lq
 * 
 */
public class DmsPreSellService {
    private static Logger log = Logger.getLogger(DmsPreSellService.class);
    private SaveDmsDataDao dao = new SaveDmsDataDao();
    public static final DateFormat formaty = new SimpleDateFormat(
	    "yyyyMMddHHmmss");
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");

    public void createDmsPreSellXml(String createDate) {
	try {
	    String dir = getPath("local.ftpToDmsPreSell");
	    /*String dir2 = dir + "/" + format.format(new Date());
	    File file = new File(dir2);
	    if (file.exists()) {
		if (file.listFiles().length > 0) {
		    File[] file1 = file.listFiles();
		    for (File f : file1) {
			f.delete();
		    }
		}
	    }*/
		String sendDate = format.format(new Date());
	    log.info("调用获取数据方法!");
	    int countSum=dao.queryCountByFcToDmsPreSell(createDate);
	    if (countSum > 0) {
			int pageCount=0;
			if(countSum%5000 == 0){
			   pageCount=countSum/5000;
			}else{
			    pageCount=countSum/5000+1;
			}
			for(int i=1;i<=pageCount;i++){
			   // ResultSet upListSet=dao.queryPageByFcToDmsPreSell(i, 2, createDate);
			  //  List<M2FToDmsDataBean> upList=dao.settingDmsPreSellData(upListSet);
			    PageBean p=dao.queryPageByFcToDmsPreSell(i, 500, createDate);
			    List<M2FToDmsDataBean> upList=p.getDataList();
			    if(upList != null){
				try {
				    log.info("开始生成XML！");
				    this.expM2TODmsExcelFile( upList);
				   // createXmlDocument(M2FToDmsDataBean.class, upList,
					   // "GBK", file.getAbsolutePath(), createDate, "ODP"
						//    + formaty.format(new Date())+i);
				} catch (Exception e) {
				    e.printStackTrace();
				    log.info("生成XML失败！");
				}
			    }
				 if (upList != null) {
					upList.clear();
				   }
			}
			 //3.修改dms发送的sendData
			   dao.updateFcM2fToDmsBySendDate(format.format(new Date()));
			
			
	    }else {
		log.info("售前数据为空，生成XML失败");
	    }
	}catch (Exception e) {
		    e.printStackTrace();
		    log.info("生成XML失败！");
		}
	 
    }
    public File expM2TODmsExcelFile(List<M2FToDmsDataBean> upList) throws Exception {
	ExcelExportUtilForDms exBean = new ExcelExportUtilForDms();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String uploadNo=sdf.format(new Date());
	exBean.exportTypeFomat = "XLS";
	exBean.sheetName = uploadNo;
	exBean.fileName = exBean.getFileName(exBean.sheetName, "xls");
	exBean.path="D:\\fcftp2\\fc2dms\\CRMTRACK\\"+uploadNo.substring(0,8)+"\\";
	if (upList != null && upList.size() > 0) {
		exBean.pageIndex = 1;
		exBean.exportExcel2003(M2FToDmsDataBean.class, upList);
		exBean.isExport = false;
		exBean.exportExcel2003(M2FToDmsDataBean.class, upList);
		return exBean.file;
	}		
	return null;
}
    public static void createXmlDocument(Class<?> obj, List<?> entitys,
	    String Encode, String XMLPath, String createDate, String XmlName) {
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
	    Element root = document.addElement("data");// 添加根节点
	    root.addElement("time_stamp").setText(formaty.format(new Date()));
	    Element zroot = root.addElement("beans");// 添加根节点
	    Field[] properties = obj.getDeclaredFields();// 获得实体类的所有属性

	    for (Object t : entitys) { // 递归实体
		Element element = zroot.addElement("bean"); // 二级节点
		for (int i = 0; i < properties.length; i++) {
		    if (!"serialVersionUID".equals(properties[i].getName())) {
			// 反射get方法
			Method meth = t.getClass().getMethod(
				"get"
					+ (properties[i].getName().substring(0,
						1)).toUpperCase()
					+ properties[i].getName().substring(1));
			// 为二级节点添加属性，属性值为对应属性的值
			Object invo = meth.invoke(t);
			element.addElement(properties[i].getName()).setText(
				(invo != null ? invo.toString() : ""));
		    }
		}
	    }
	    File fil = new File(XMLPath + "\\" + XmlName + ".xml");
	    writer = new XMLWriter(new FileOutputStream(fil), format);
	    writer.write(document);
	    writer.close();
	} catch (Exception e) {
	    log.error("动态生成XML失败", e);
	}
    }

    /**
     * 通过KEY得到对应的值
     * 
     * @param getValue
     * @return
     */
    private static String getPath(String getValue) {
	PropUtils p = new PropUtils();
	try {
	    p.loadFile("ftpConn.properties");
	} catch (Exception e) {
	    log.error("读取ftpConn.properties异常", e);
	}
	String path = p.getValue(getValue);
	return path;
    }
}
