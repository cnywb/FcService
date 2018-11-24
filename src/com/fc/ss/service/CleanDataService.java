package com.fc.ss.service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.fc.ss.bean.FcPreSalesDataToDMSBean;
import com.fc.ss.dao.db.CleanDataDao;
import com.fc.util.ExcelExportUtilForDms;
import com.fc.util.PageBean;
import com.fc.util.PropUtils;

/**
 * 提交至dms售前数据操作类
 * 
 * @author lq
 * 
 */
public class CleanDataService {
    private static Logger log = Logger.getLogger(CleanDataService.class);
    private CleanDataDao dao = new CleanDataDao();
    public static final DateFormat formaty = new SimpleDateFormat(
	    "yyyyMMddHHmmss");
    public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");

    public void createDmsPreSellExcel(String createDate) {
	try {
	    String fileName = "DMS_CRM_PH_"+ formaty.format(new Date());
	    log.info("调用获取数据方法!");
	    int countSum=dao.queryCountByFcToDmsPreSell(createDate);
	    if (countSum > 0) {
	    	int pageCount=1;
			for(int i=1;i<=pageCount;i++){
			    PageBean p=dao.queryPageByFcToDmsPreSell(i, 3000, createDate);
			    List<FcPreSalesDataToDMSBean> upList=p.getDataList();
			    if(upList != null){
				try {
				    log.info("开始生成EXCEL！");
				    this.expM2TODmsExcelFile( upList);
				    //3.修改dms发送的processed_flag
				    dao.updateFcM2fToDmsByProcessedFlag(3000);
				    dao.insertUpdateFile(fileName,"FC_PRESALES_DATA_TO_DMS",createDate,"成功");
				} catch (Exception e) {
				    e.printStackTrace();
				    log.info("生成EXCEL失败！");
				}
			    }
				 if (upList != null) {
					upList.clear();
				   }
			}
			
			   
			
	    }else {
		log.info("售前数据为空，生成EXCEL失败");
	    }
	}catch (Exception e) {
		    e.printStackTrace();
		    log.info("生成EXCEL失败！");
		}
	 
    }
    public File expM2TODmsExcelFile(List<FcPreSalesDataToDMSBean> upList) throws Exception {
		ExcelExportUtilForDms exBean = new ExcelExportUtilForDms();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String uploadNo=sdf.format(new Date());
		exBean.exportTypeFomat = "XLS";
		exBean.sheetName = "DMS_CRM_PH_" + uploadNo;
		exBean.fileName = exBean.getFileName(exBean.sheetName, "xls");
		exBean.path="D:\\fcftp2\\fc2dms\\CRMTRACK\\"+uploadNo.substring(0,8)+"\\";
		if (upList != null && upList.size() > 0) {
			exBean.pageIndex = 1;
			exBean.exportExcel2003(FcPreSalesDataToDMSBean.class, upList);
			exBean.isExport = false;
			exBean.exportExcel2003(FcPreSalesDataToDMSBean.class, upList);
			return exBean.file;
		}		
		return null;
	}
    
}
